import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  private router: Router
  email: string = '';
  password: string = '';
  constructor(private authenticationService: AuthenticationService) {}

  public onSubmit(email: string, password: string) {
    this.authenticationService.login(
      email,
      password
    );
  }

  public onRegisterClick(){
    this.router.navigate(['/register']);
  }
}
