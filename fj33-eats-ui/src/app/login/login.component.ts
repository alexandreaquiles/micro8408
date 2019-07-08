import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  loginInfo: any = {};

  constructor(private router: Router,
              private authenticationService: AuthenticationService) {
  }

  efetuaLogin() {
    this.authenticationService.login(this.loginInfo)
      .subscribe(() => this.router.navigate(['']));
  }
}
