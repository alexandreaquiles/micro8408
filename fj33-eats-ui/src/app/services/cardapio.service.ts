import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CardapioService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  porIdDoRestaurante(restauranteId: string): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${restauranteId}/cardapio`);
  }

  porId(restauranteId, cardapioId): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${restauranteId}/cardapio/${cardapioId}`);
  }

  doRestaurante(restaurante): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${restaurante.id}/cardapio`);
  }

  categoriaDoCardapioPorId(restauranteId, cardapioId, categoriaId): Observable<any> {
    return this.http.get(`${this.API}/restaurantes/${restauranteId}/cardapio/${cardapioId}/categoria/${categoriaId}`);
  }

  adicionaCategoriaAoCardapio(categoria): Observable<any> {
    const cardapio = categoria.cardapio;
    const restaurante = cardapio.restaurante;
    return this.http.post(`${this.API}/parceiros/restaurantes/${restaurante.id}/cardapio/${cardapio.id}/categoria`, categoria);
  }

  removeItemDoCardapio(item): Observable<any> {
    const categoria = item.categoria;
    const cardapio = categoria.cardapio;
    const restaurante = cardapio.restaurante;
    return this.http.delete(`${this.API}/parceiros/restaurantes/${restaurante.id}/cardapio/${cardapio.id}/categoria/${categoria.id}/item/${item.id}`);
  }

  itemDoCardapioPorId(restauranteId, cardapioId, categoriaId, itemId): Observable<any> {
    return this.http.get(`${this.API}/parceiros/restaurantes/${restauranteId}/cardapio/${cardapioId}/categoria/${categoriaId}/item/${itemId}`);
  }

  salvaItemDoCardapio(item): Observable<any> {
    const categoria = item.categoria;
    const cardapio = categoria.cardapio;
    const restaurante = cardapio.restaurante;
    if (item.id) {
      return this.http.put(`${this.API}/parceiros/restaurantes/${restaurante.id}/cardapio/${cardapio.id}/categoria/${categoria.id}/item/${item.id}`, item);
    }
    return this.http.post(`${this.API}/parceiros/restaurantes/${restaurante.id}/cardapio/${cardapio.id}/categoria/${categoria.id}/item`, item);
  }

}
