import { Pipe, PipeTransform } from '@angular/core';
import { DiaDaSemanaService } from '../services/dia-da-semana.service';

@Pipe({
  name: 'diaDaSemana'
})
export class DiaDaSemanaPipe implements PipeTransform {

  constructor(private diaDaSemanaService: DiaDaSemanaService) {
  }

  transform(value: any): string {
    const diaDaSemana = this.diaDaSemanaService.aPartirDoValor(value);
    if (diaDaSemana) {
      return diaDaSemana.nome;
    }
    return value;
  }
}
