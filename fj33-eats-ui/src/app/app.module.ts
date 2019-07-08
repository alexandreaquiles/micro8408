import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/pt';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from './app.component';

import { LoginComponent } from './login/login.component';

import { AdminModule } from './admin/admin.module';
import { PedidoModule } from './pedido/pedido.module';
import { RestaurantesModule } from './restaurantes/restaurantes.module';

import { ErrorHandlingInterceptor } from './interceptors/error-handling-interceptor';
import { JwtInterceptor } from './interceptors/jwt-interceptor';

import { appRoutes } from './app.routes';

registerLocaleData(localeFr, 'pt');

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    appRoutes,
    NgbModule,
    ToastrModule.forRoot(),
    AdminModule,
    PedidoModule,
    RestaurantesModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor , multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: ErrorHandlingInterceptor , multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
