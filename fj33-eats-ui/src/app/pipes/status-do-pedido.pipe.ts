import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusDoPedido'
})
export class StatusDoPedidoPipe implements PipeTransform {

  descricaoDosStatusDoPedido: any = {
    REALIZADO: 'Realizado',
    PAGO: 'Pago',
    CONFIRMADO: 'Confirmado',
    PRONTO: 'Pronto',
    SAIU_PARA_ENTREGA: 'Saiu para entrega',
    ENTREGUE: 'Entregue'
  };

  transform(value: any): string {
    return this.descricaoDosStatusDoPedido[value] || value;
  }

}
