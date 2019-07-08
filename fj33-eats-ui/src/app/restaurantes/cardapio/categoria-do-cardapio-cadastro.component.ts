import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { RestauranteService } from '../../services/restaurante.service';
import { CardapioService } from '../../services/cardapio.service';

@Component({
  selector: 'app-categoria-do-categoria-cadastro',
  templateUrl: './categoria-do-cardapio-cadastro.component.html'
})
export class CategoriaDoCardapioCadastroComponent implements OnInit {

  restaurante: any = {};
  cardapio: any = {};
  categoria: any = {};

  constructor(private route: ActivatedRoute,
              private restauranteService: RestauranteService,
              private cardapioService: CardapioService) {
  }

  ngOnInit() {

    const restauranteId = this.route.snapshot.params.restauranteId;
    this.restauranteService.porId(restauranteId)
      .subscribe(restaurante => this.restaurante = restaurante);

    const cardapioId = this.route.snapshot.params.cardapioId;
    this.cardapioService.porId(restauranteId, cardapioId)
      .subscribe(cardapio => this.cardapio = cardapio);

    const categoriaId = this.route.snapshot.params.categoriaId;
    this.cardapioService.categoriaDoCardapioPorId(restauranteId, cardapioId, categoriaId)
      .subscribe(categoria => this.categoria = categoria);

  }

  removerItemCardapio(item) {
    this.cardapio.restaurante = this.restaurante;
    this.categoria.cardapio = this.cardapio;
    item.categoria = this.categoria;
    this.cardapioService.removeItemDoCardapio(item)
      .subscribe(() => this.categoria.itens = this.categoria.itens.filter(i => i !== item));
  }
}
