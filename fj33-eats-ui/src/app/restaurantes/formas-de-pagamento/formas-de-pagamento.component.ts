import { Component, OnInit, Input } from '@angular/core';

import { FormaDePagamentoService } from 'src/app/services/forma-de-pagamento.service';

@Component({
  selector: 'app-formas-de-pagamento',
  templateUrl: './formas-de-pagamento.component.html'
})
export class FormasDePagamentoComponent implements OnInit {

  @Input() restaurante: any;
  todasAsFormasDePagamento: Array<any>;
  formasDePagamentoDoRestaurante: Array<any>;
  formaDePagamentoParaAdicionar: any = {};

  constructor(private formaDePagamentoService: FormaDePagamentoService) {
  }

  ngOnInit() {
    this.formaDePagamentoService.todas()
      .subscribe(todasAsFormas => this.todasAsFormasDePagamento = todasAsFormas);

    this.formaDePagamentoService.doRestaurante(this.restaurante)
      .subscribe((formasDePagamento: any) => this.formasDePagamentoDoRestaurante = formasDePagamento);
  }

  adicionaFormaDePagamentoAoRestaurante() {
    if (this.formaDePagamentoParaAdicionar) {
      const jaTem = this.formasDePagamentoDoRestaurante.some(f => f.id === this.formaDePagamentoParaAdicionar.id);
      if (!jaTem) {
        this.formaDePagamentoParaAdicionar.restaurante = this.restaurante;
        this.formaDePagamentoService.adicionaAoRestaurante(this.formaDePagamentoParaAdicionar)
          .subscribe(() => {
              this.formasDePagamentoDoRestaurante.push(this.formaDePagamentoParaAdicionar);
              this.formasDePagamentoDoRestaurante.sort((a,b) => a.nome.localeCompare(b.nome));
              this.formaDePagamentoParaAdicionar = {};
          });
      }
    }
  }

  remove(formaDePagamento) {
    formaDePagamento.restaurante = this.restaurante;
    this.formaDePagamentoService.removeDoRestaurante(formaDePagamento)
      .subscribe(() => {
        this.formasDePagamentoDoRestaurante = this.formasDePagamentoDoRestaurante.filter(f => f !== formaDePagamento);
      });
  }
}
