import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'tipoDaFormaDePagamento'
})
export class TipoDaFormaDePagamentoPipe implements PipeTransform {

  descricaoDasFormasDePagamento: any = {
    CARTAO_CREDITO: 'Cartão de Crédito',
    CARTAO_DEBITO: 'Cartão de Débito',
    VALE_REFEICAO: 'Vale Refeição'
  };

  transform(value: any): string {
    return this.descricaoDasFormasDePagamento[value] || value;
  }

}
