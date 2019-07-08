import { RouterModule, Routes } from '@angular/router';

import { AuthorizationGuard } from '../guards/authorization.guard';

import { TiposDeCozinhaComponent } from './tipos-de-cozinha/tipos-de-cozinha.component';
import { FormasDePagamentoComponent } from './formas-de-pagamento/formas-de-pagamento.component';
import { RestauranteEmAprovacaoComponent } from './restaurantes-em-aprovacao/restaurantes-em-aprovacao.component';

const routes: Routes = [
  {
    path: 'admin/tipos-de-cozinha',
    component: TiposDeCozinhaComponent,
    canActivate: [AuthorizationGuard],
    data: { role: 'ADMIN'}
  },
  {
    path: 'admin/formas-de-pagamento',
    component: FormasDePagamentoComponent,
    canActivate: [AuthorizationGuard],
    data: { role: 'ADMIN'}
  },
  {
    path: 'admin/restaurantes-em-aprovacao',
    component: RestauranteEmAprovacaoComponent,
    canActivate: [AuthorizationGuard],
    data: { role: 'ADMIN'}
  }
];

export const adminRoutes = RouterModule.forChild(routes);
