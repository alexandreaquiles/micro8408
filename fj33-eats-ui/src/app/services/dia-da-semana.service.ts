import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DiaDaSemanaService {

  diasDaSemana = [
    { nome: 'Segunda', valor: 'MONDAY', ordem: 1},
    { nome: 'Terça', valor: 'TUESDAY', ordem: 2},
    { nome: 'Quarta', valor: 'WEDNESDAY', ordem: 3},
    { nome: 'Quinta', valor: 'THURSDAY', ordem: 4},
    { nome: 'Sexta', valor: 'FRIDAY', ordem: 5},
    { nome: 'Sábado', valor: 'SATURDAY', ordem: 6},
    { nome: 'Domingo', valor: 'SUNDAY', ordem: 7},
  ];

  aPartirDoValor(valor) {
    return this.diasDaSemana.find( d => d.valor == valor);
  }

  compara(a, b) {
    return  this.aPartirDoValor(a).ordem - this.aPartirDoValor(b).ordem;
  }

}
