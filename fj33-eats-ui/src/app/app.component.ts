import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Caelum Eats';
  isNavbarCollapsed = true;
  user: any;
  constructor(private router: Router,
              private authenticationService: AuthenticationService) { }

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(user => this.user = user);
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['']);
  }
}
