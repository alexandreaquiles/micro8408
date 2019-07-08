import { RouterModule, Routes } from '@angular/router';

import { RestauranteCadastroComponent } from './restaurante-cadastro/restaurante-cadastro.component';
import { CategoriaDoCardapioCadastroComponent } from './cardapio/categoria-do-cardapio-cadastro.component';
import { ItemDoCardapioCadastroComponent } from './cardapio/item-do-cardapio-cadastro.component';
import { PedidosPendentesComponent } from './pedidos-pendentes/pedidos-pendentes.component';
import { AuthorizationGuard } from '../guards/authorization.guard';

const routes: Routes = [
  {
    path: 'restaurantes',
    component: RestauranteCadastroComponent
  },
  {
    path: 'restaurantes/:id',
    component: RestauranteCadastroComponent,
    canActivate: [AuthorizationGuard],
    data: { role: 'PARCEIRO'}
  },
  {
    path: 'restaurantes/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId',
    component: CategoriaDoCardapioCadastroComponent,
    canActivate: [AuthorizationGuard],
    data: { role: 'PARCEIRO'}
  },
  {
    path: 'restaurantes/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId/item',
    component: ItemDoCardapioCadastroComponent,
    canActivate: [AuthorizationGuard],
    data: { role: 'PARCEIRO'}
  },
  {
    path: 'restaurantes/:restauranteId/cardapio/:cardapioId/categoria/:categoriaId/item/:itemId',
    component: ItemDoCardapioCadastroComponent,
    canActivate: [AuthorizationGuard],
    data: { role: 'PARCEIRO'}
  },
  {
    path: 'restaurantes/:restauranteId/pedidos/pendentes',
    component: PedidosPendentesComponent,
    canActivate: [AuthorizationGuard],
    data: { role: 'PARCEIRO'}
  }
];

export const restauranteRoutes = RouterModule.forChild(routes);

