import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { RestauranteService } from 'src/app/services/restaurante.service';
import { CardapioService } from 'src/app/services/cardapio.service';
import { AvaliacoesService } from 'src/app/services/avaliacoes.service';
import { PedidosService } from 'src/app/services/pedidos.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-restaurante',
  templateUrl: './restaurante.component.html'
})
export class RestauranteComponent implements OnInit {

  cep: string;
  restaurante: any;
  cardapio: any = {};
  avaliacoes: Array<any> = [];
  pedido: any = {
    itens: []
  };
  itemDoPedidoEscolhido: any;
  adicionandoItemAoPedido = false;

  itemEscolhidoModalRef: NgbModalRef;
  formularioDeEntregaModalRef: NgbModalRef;

  cpfMask = [/\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  cepMask = [/\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/];
  telefoneMask = userInput => {
    const numbers = userInput.match(/\d/g);
    let numberLength = 0;
    if (numbers) {
      numberLength = numbers.join('').length;
    }
    if (numberLength > 10) {
      return ['(', /[1-9]/, /[1-9]/, ')', ' ', /\d/, ' ', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
    } else {
      return ['(', /[1-9]/, /[1-9]/, ')', ' ', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
    }
  }

  constructor(private modal: NgbModal,
              private route: ActivatedRoute,
              private router: Router,
              private restaurantesService: RestauranteService,
              private cardapioService: CardapioService,
              private avaliacoesService: AvaliacoesService,
              private pedidoService: PedidosService) {
  }

  ngOnInit() {
    this.cep = this.route.snapshot.params.cep;
    const restauranteId = this.route.snapshot.params.restauranteId;

    this.restaurantesService.porId(restauranteId)
      .subscribe(restaurante => {
        this.restaurante = restaurante;
        this.pedido.restaurante = restaurante;
        this.restaurantesService.distanciaPorCepEId(this.cep, restauranteId)
          .subscribe(restauranteComDistancia => {
            this.restaurante.distancia = restauranteComDistancia.distancia;
        });
        this.avaliacoesService.porIdDoRestaurante(restauranteId)
          .subscribe(avaliacoes => {
            this.avaliacoes = avaliacoes;
            const media = avaliacoes.reduce( ( acc, cur ) => acc + cur.nota, 0 ) / avaliacoes.length;
            this.restaurante.mediaAvaliacoes = media;
        });
    });

    this.cardapioService
      .porIdDoRestaurante(restauranteId)
      .subscribe(cardapio => this.cardapio = cardapio);
  }

  escolheItemDoCardapio(itemDoPedidoEscolhidoModal, itemDoCardapio) {
    const indice = this.pedido.itens.findIndex(i => i.itemDoCardapio.id === itemDoCardapio.id);
    if (indice < 0) {
      this.itemDoPedidoEscolhido = { itemDoCardapio, quantidade: 1 };
      this.adicionandoItemAoPedido = true;
    } else {
      this.itemDoPedidoEscolhido = Object.assign({}, this.pedido.itens[indice]);
    }
    this.itemEscolhidoModalRef = this.modal.open(itemDoPedidoEscolhidoModal);
  }

  salvaItemNoPedido() {
    if (this.adicionandoItemAoPedido) {
      this.pedido.itens.push(this.itemDoPedidoEscolhido);
    } else if (this.itemDoPedidoEscolhido) {
      const indice = this.pedido.itens.findIndex(i => i.itemDoCardapio.id === this.itemDoPedidoEscolhido.itemDoCardapio.id);
      this.pedido.itens[indice] = this.itemDoPedidoEscolhido;
    }
    this.itemDoPedidoEscolhido = null;
    this.adicionandoItemAoPedido = false;
    this.itemEscolhidoModalRef.close();
  }

  editaItemDoPedido(itemDoPedidoEscolhidoModal, itemPedido) {
    this.itemDoPedidoEscolhido = Object.assign({}, itemPedido);
    this.itemEscolhidoModalRef = this.modal.open(itemDoPedidoEscolhidoModal);
  }

  removeItemDoPedido(itemPedido) {
    this.pedido.itens = this.pedido.itens.filter(i => i.itemDoCardapio.id !== itemPedido.itemDoCardapio.id);
    this.itemDoPedidoEscolhido = null;
    this.adicionandoItemAoPedido = false;
  }

  calculaSubTotal(itemPedido) {
    const itemCardapio = itemPedido.itemDoCardapio;
    const preco = itemCardapio.precoPromocional || itemCardapio.preco;
    return itemPedido.quantidade * preco;
  }

  totalDoPedido() {
    let total = this.restaurante.taxaDeEntregaEmReais || 0;
    this.pedido.itens.forEach(item => {
      total += this.calculaSubTotal(item);
    });
    return total;
  }

  fazPedido(formularioDeEntregaModal) {
    this.pedido.restaurante = this.restaurante;
    this.pedido.entrega = { cep: this.cep, cliente: {} };
    this.formularioDeEntregaModalRef = this.modal.open(formularioDeEntregaModal);
  }

  registraEntrega() {
    this.pedidoService.adiciona(this.pedido)
    .subscribe(pedido => {
      this.router.navigateByUrl(`pedidos/${pedido.id}/pagamento`);
      this.formularioDeEntregaModalRef.close();
    });

  }

}
