import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TipoDeCozinhaService {

  private API = environment.baseUrl;

  constructor(private http: HttpClient) {
  }

  todos(): Observable<any> {
    return this.http.get(`${this.API}/tipos-de-cozinha`);
  }

  salva(tipoDeCozinha: any): Observable<any> {
    if (tipoDeCozinha.id) {
      return this.http.put(`${this.API}/admin/tipos-de-cozinha/${tipoDeCozinha.id}`, tipoDeCozinha);
    }
    return this.http.post(`${this.API}/admin/tipos-de-cozinha`, tipoDeCozinha);
  }

  remove(tipoDeCozinha: any) {
    return this.http.delete(`${this.API}/admin/tipos-de-cozinha/${tipoDeCozinha.id}`);
  }

}
