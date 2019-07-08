import { Component, OnInit } from '@angular/core';

import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';

import { FormaDePagamentoService } from 'src/app/services/forma-de-pagamento.service';

@Component({
  selector: 'app-formas-de-pagamento',
  templateUrl: './formas-de-pagamento.component.html'
})
export class FormasDePagamentoComponent implements OnInit {

  tiposDeFormaDePagamento: Array<any> = [];
  formasDePagamento: Array<any> = [];
  formaDePagamento: any = {};

  modalRef: NgbModalRef;

  constructor(private modal: NgbModal,
              private formaDePagamentoService: FormaDePagamentoService) { }

  ngOnInit() {
    this.formaDePagamentoService.tipos().subscribe(data => {
      this.tiposDeFormaDePagamento = data;
    });

    this.formaDePagamentoService.todas()
      .subscribe(data => {
        this.formasDePagamento = data;
      });
  }

  adiciona(formaDePagamentoModal) {
    this.formaDePagamento = {};
    this.modalRef = this.modal.open(formaDePagamentoModal);
  }

  edita(formaDePagamentoModal, formaDePagamento) {
    this.formaDePagamento = Object.assign({}, formaDePagamento);
    this.modalRef = this.modal.open(formaDePagamentoModal);
  }

  remove(formaDePagamento) {
    this.formaDePagamentoService.remove(formaDePagamento)
      .subscribe(() =>
        this.formasDePagamento = this.formasDePagamento.filter(f => f.id !== formaDePagamento.id));
  }

  salva() {
    this.formaDePagamentoService.salva(this.formaDePagamento)
      .subscribe(formaDePagamento => {
        if (this.formaDePagamento.id) {
          const indice = this.formasDePagamento.findIndex(f => f.id === formaDePagamento.id);
          this.formasDePagamento[indice] = formaDePagamento;
        } else {
          this.formasDePagamento.push(formaDePagamento);
        }
        this.formasDePagamento = this.formasDePagamento.sort((a, b) => a.nome.localeCompare(b.nome));
        this.formaDePagamento = {};
        this.modalRef.close();
      });
  }


}
