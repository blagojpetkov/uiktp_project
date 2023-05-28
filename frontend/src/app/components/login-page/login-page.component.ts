import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
})
export class LoginPageComponent {
  email: string = '';
  password: string = '';
  public invalidUsernamePassword:boolean = false;
  constructor(private authenticationService: AuthenticationService, private router: Router) {}

  public onSubmit(username: string, password: string) {
    this.authenticationService.login(
      username,
      password
    );
  }

  public onRegisterClick(){
    this.router.navigate(['/register']);
  }

  public onGoogleClick(){
    window.location.href='https://accounts.google.com/'
  }

  public onFacebookClick(){
    window.location.href='https://www.facebook.com/'
  }
}
