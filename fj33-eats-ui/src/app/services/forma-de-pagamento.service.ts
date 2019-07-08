import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormaDePagamentoService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  todas(): Observable<any> {
    return this.http.get(`${this.API}/formas-de-pagamento`);
  }

  salva(formaDePagamento: any): Observable<any> {
    if (formaDePagamento.id) {
      return this.http.put(`${this.API}/admin/formas-de-pagamento/${formaDePagamento.id}`, formaDePagamento);
    }
    return this.http.post(`${this.API}/admin/formas-de-pagamento`, formaDePagamento);
  }

  remove(formaDePagamento: any) {
    return this.http.delete(`${this.API}/admin/formas-de-pagamento/${formaDePagamento.id}`);
  }

  tipos(): Observable<any> {
    return this.http.get(`${this.API}/admin/formas-de-pagamento/tipos`);
  }

  doRestaurante(restaurante): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${restaurante.id}/formas-de-pagamento`);
  }

  adicionaAoRestaurante(formaDePagamento): Observable<any> {
    return this.http.post(`${this.API}/parceiros/restaurantes/${formaDePagamento.restaurante.id}/formas-de-pagamento`, formaDePagamento);
  }

  removeDoRestaurante(formaDePagamento) {
    return this.http.delete(`${this.API}/parceiros/restaurantes/${formaDePagamento.restaurante.id}/formas-de-pagamento/${formaDePagamento.id}`);
  }


}
