import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { ToastrService } from 'ngx-toastr';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class ErrorHandlingInterceptor implements HttpInterceptor {

  constructor(private router: Router,
              private toaster: ToastrService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req)
      .pipe(
        catchError(err => {
          let mensagemDeErro = '';
          if (err.error instanceof ErrorEvent) {
            mensagemDeErro = err.error.message;
          } else {
            if (err.status && err.message) {
              mensagemDeErro = `[${err.status}] ${err.message}`;
            }
          }
          this.toaster.error(mensagemDeErro, 'Erro no servidor');
          this.router.navigate(['']);
          return throwError(err);
        })
      );
  }

}
