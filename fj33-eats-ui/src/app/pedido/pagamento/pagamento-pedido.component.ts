import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { RestauranteService } from 'src/app/services/restaurante.service';
import { PedidosService } from 'src/app/services/pedidos.service';
import { PagamentoService } from 'src/app/services/pagamento.service';

@Component({
  selector: 'app-pagamento-pedido',
  templateUrl: './pagamento-pedido.component.html'
})
export class PagamentoPedidoComponent implements OnInit {

  pedido: any;
  formasDePagamento: Array<any>;
  pagamento: any = {};

  numeroCartaoMask = [/\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/, ' ', /\d/, /\d/, /\d/, /\d/];
  codigoCartaoMask= [/\d/, /\d/, /\d/];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private pagamentoService: PagamentoService,
              private pedidoService: PedidosService,
              private restaurantesService: RestauranteService) {
  }

  ngOnInit() {
    const pedidoId = this.route.snapshot.params.pedidoId;
    this.pedidoService.porId(pedidoId)
      .subscribe((pedido: any) => {
        this.pedido = pedido;
        this.pagamento = { pedido, valor: pedido.total };
        this.restaurantesService.formasDePagamento(pedido.restaurante)
          .subscribe(formasDePagamento => this.formasDePagamento = formasDePagamento);
      });
  }

  criaPagamento() {
    this.pagamentoService.cria(this.pagamento)
      .subscribe(pagamento => {
        this.pagamento = pagamento;
      });
  }

  confirmaPagamento() {
    this.pagamentoService.confirma(this.pagamento)
      .subscribe(pagamento => this.router.navigateByUrl(`pedidos/${pagamento.pedido.id}/status`));
  }

  cancelaPagamento() {
    this.pagamentoService.cancela(this.pagamento)
      .subscribe(() => this.router.navigateByUrl(``));
}

}
