import { NgModule } from "@angular/core";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { InjectableRxStompConfig, RxStompService, rxStompServiceFactory } from '@stomp/ng2-stompjs';

import { rxStompConfig } from 'src/app/rx-stomp.config';

import { TextMaskModule } from 'angular2-text-mask';

import { PedidoComponent } from './pedido.component';
import { ListaRestaurantesComponent } from './lista-restaurantes/lista-restaurantes.component';
import { RestauranteComponent } from './restaurante/restaurante.component';
import { PagamentoPedidoComponent } from './pagamento/pagamento-pedido.component';
import { ResumoPedidoComponent } from './resumo/resumo-pedido.component';
import { StatusPedidoComponent } from './status/status-pedido.component';

import { pedidoRoutes } from './pedido.routes';

import { PipesModule } from '../pipes/pipes.module';

@NgModule({
  declarations: [
    PedidoComponent,
    ListaRestaurantesComponent,
    RestauranteComponent,
    PagamentoPedidoComponent,
    ResumoPedidoComponent,
    StatusPedidoComponent
  ],
  imports: [CommonModule, FormsModule, NgbModule, TextMaskModule, pedidoRoutes, PipesModule],
  providers: [
    {
      provide: InjectableRxStompConfig,
      useValue: rxStompConfig
    },
    {
      provide: RxStompService,
      useFactory: rxStompServiceFactory,
      deps: [InjectableRxStompConfig]
    }
  ]
})
export class PedidoModule { }
