import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-resumo-pedido',
  templateUrl: './resumo-pedido.component.html'
})
export class ResumoPedidoComponent {

  @Input() pedido;

}
