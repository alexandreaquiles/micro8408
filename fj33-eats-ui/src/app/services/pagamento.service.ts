import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  private API = environment.baseUrl + '/pagamentos';

  constructor(private http: HttpClient) {
  }

  cria(pagamento): Observable<any> {
    return this.http.post(`${this.API}`, pagamento);
  }

  confirma(pagamento): Observable<any> {
    return this.http.put(`${this.API}/${pagamento.id}`, null);
  }

  cancela(pagamento): Observable<any> {
    return this.http.delete(`${this.API}/${pagamento.id}`);
  }

}
