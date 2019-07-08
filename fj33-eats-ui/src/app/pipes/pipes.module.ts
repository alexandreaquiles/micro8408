import { NgModule } from '@angular/core';
import { DiaDaSemanaPipe } from './dia-da-semana.pipe';
import { TipoDaFormaDePagamentoPipe } from './tipo-da-forma-de-pagamento.pipe';
import { StatusDoPedidoPipe } from './status-do-pedido.pipe';

@NgModule({
  declarations: [
    DiaDaSemanaPipe,
    TipoDaFormaDePagamentoPipe,
    StatusDoPedidoPipe
  ],
  exports: [
    DiaDaSemanaPipe,
    TipoDaFormaDePagamentoPipe,
    StatusDoPedidoPipe
  ]
})
export class PipesModule {}
