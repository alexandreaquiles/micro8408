import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { AuthenticationService } from 'src/app/services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthorizationGuard implements CanActivate {

  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private toaster: ToastrService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const role = route.data.role;
    if (role && this.authenticationService.hasRole(role)) {
      return true;
    }
    this.toaster.error('Efetue o login para ter acesso.', 'Acesso negado');
    this.router.navigate(['/login']);
    return false;
  }
}
