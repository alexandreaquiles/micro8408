import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { AuthenticationService } from 'src/app/services/authentication.service';
import { TipoDeCozinhaService } from 'src/app/services/tipo-de-cozinha.service';
import { RestauranteService } from '../../services/restaurante.service';

@Component({
  selector: 'app-restaurante-cadastro',
  templateUrl: './restaurante-cadastro.component.html'
})
export class RestauranteCadastroComponent implements OnInit {

  userInfo: any = { };
  registrandoUsuario = true;

  restaurante: any = {
    tipoDeCozinha: { }
  };

  tiposDeCozinha: Array<any>;

  mensagem: any;

  cnpjMask = [/\d/, /\d/, '.', /\d/, /\d/, /\d/, '.', /\d/, /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/];
  cepMask = [/\d/, /\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private toaster: ToastrService,
              private authenticationService: AuthenticationService,
              private tipoDeCozinhaService: TipoDeCozinhaService,
              private restauranteService: RestauranteService) {
  }

  ngOnInit() {
    this.tipoDeCozinhaService.todos().subscribe(data => {
      this.tiposDeCozinha = data;
    });

    const id = this.route.snapshot.params.id;
    if (id) {
      this.registrandoUsuario = false;
      this.restauranteService.parceiroPorId(id)
        .subscribe((restaurante: any) => {
          if (restaurante) {
            this.restaurante = restaurante;
            this.router.navigate([`/restaurantes/${restaurante.id}`]);
          }
        }, () => this.router.navigate(['']));
    }
  }

  validaSenhas() {
    return this.userInfo.password === this.userInfo.passwordConfirmation;
  }

  registraUsuario() {
    this.authenticationService.registraParceiro(this.userInfo)
      .subscribe(id => {
        this.registrandoUsuario = false;
        this.userInfo.id = id;
        this.restaurante.user = { id };
      }, () => this.mensagem = 'Erro ao registrar usuário. Tente novamente mais tarde.');
  }

  estaAdicionando() {
    return !this.restaurante.id;
  }

  salvaRestaurante() {
    this.restauranteService.salva(this.restaurante)
      .subscribe(restaurante => {
        this.toaster.success('Dados do restaurante salvos com sucesso.');
        if (this.estaAdicionando()) {
          this.authenticationService.login(this.userInfo)
            .subscribe(() => {
              this.router.navigate([`/restaurantes/${restaurante.id}`]);
            });
        }
      });
  }

}
