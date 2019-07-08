import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TiposDeCozinhaComponent } from './tipos-de-cozinha/tipos-de-cozinha.component';
import { FormasDePagamentoComponent } from './formas-de-pagamento/formas-de-pagamento.component';
import { RestauranteEmAprovacaoComponent } from './restaurantes-em-aprovacao/restaurantes-em-aprovacao.component';
import { DetalhesDoRestauranteComponent } from './detalhes-do-restaurante/detalhes-do-restaurante.component';

import { adminRoutes } from './admin.routes';
import { PipesModule } from '../pipes/pipes.module';

@NgModule({
  declarations: [
    TiposDeCozinhaComponent,
    FormasDePagamentoComponent,
    RestauranteEmAprovacaoComponent,
    DetalhesDoRestauranteComponent
  ],
  imports: [CommonModule, FormsModule, adminRoutes, PipesModule ]
})
export class AdminModule { }
