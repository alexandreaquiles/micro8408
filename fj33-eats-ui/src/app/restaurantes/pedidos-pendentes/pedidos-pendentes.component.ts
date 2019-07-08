import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Subscription } from 'rxjs';

import { RxStompService} from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';

import { PedidosService } from '../../services/pedidos.service';

@Component({
  selector: 'app-pedidos-pendentes',
  templateUrl: './pedidos-pendentes.component.html'
})
export class PedidosPendentesComponent implements OnInit, OnDestroy {

  private topicSubscription: Subscription;

  pendentes: Array<any>;

  constructor(private route: ActivatedRoute,
              private rxStompService: RxStompService,
              private pedidosService: PedidosService) {
  }

  ngOnInit() {
    const restauranteId = this.route.snapshot.params.restauranteId;
    this.pedidosService.pendentes(restauranteId)
      .subscribe(pedidosPendentes => this.pendentes = pedidosPendentes);

    this.topicSubscription = this.rxStompService.watch(`/parceiros/restaurantes/${restauranteId}/pedidos/pendentes`)
      .subscribe((message: Message) => {
        const pedido = JSON.parse(message.body);
        this.pendentes.push(pedido);
      });
  }

  ngOnDestroy() {
    this.topicSubscription.unsubscribe();
  }

  confirma(pedido) {
    pedido.status = 'CONFIRMADO';
    this.pedidosService.atualizaStatus(pedido)
      .subscribe();
  }

  avisaPronto(pedido) {
    pedido.status = 'PRONTO';
    this.pedidosService.atualizaStatus(pedido)
      .subscribe();
  }

  avisaSaiu(pedido) {
    pedido.status = 'SAIU_PARA_ENTREGA';
    this.pedidosService.atualizaStatus(pedido)
      .subscribe();
  }

  avisaEntregue(pedido) {
    pedido.status = 'ENTREGUE';
    this.pedidosService.atualizaStatus(pedido)
      .subscribe();
  }

}
