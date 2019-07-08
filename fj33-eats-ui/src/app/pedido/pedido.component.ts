import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  templateUrl: './pedido.component.html'
})
export class PedidoComponent {
  cep: string;

  constructor(private router: Router) { }

  buscar() {
    this.router.navigate(['/pedidos/', this.cep]);
  }
}
