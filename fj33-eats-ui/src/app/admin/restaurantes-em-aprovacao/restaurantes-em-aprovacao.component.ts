import { Component, OnInit } from '@angular/core';
import { RestauranteService } from 'src/app/services/restaurante.service';

@Component({
  selector: 'app-restaurantes-em-aprovacao',
  templateUrl: './restaurantes-em-aprovacao.component.html'
})
export class RestauranteEmAprovacaoComponent implements OnInit {

  restaurantesEmAprovacao: Array<any> = [];
  restauranteEmDetalhe: any;

  constructor(private restaurantesService: RestauranteService) {
  }

  ngOnInit() {
    this.restaurantesService.emAprovacao()
      .subscribe(restaurantes => this.restaurantesEmAprovacao = restaurantes);
  }

  detalha(restaurante) {
    this.restauranteEmDetalhe = restaurante;
  }

  aprova(restaurante) {
    this.restaurantesService.aprova(restaurante)
      .subscribe(() => {
        restaurante.aprovado = true;
        this.restauranteEmDetalhe = null;
      });
  }
}
