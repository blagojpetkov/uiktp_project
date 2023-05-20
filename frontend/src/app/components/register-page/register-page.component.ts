import { AuthenticationService } from 'src/app/services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css'],
})
export class RegisterPageComponent {
  name: string = '';
  surname: string = '';
  username: string = '';
  password: string = '';
  repeat_password: string = '';

  constructor(private authenticationService: AuthenticationService, private router: Router) {}

  public onSubmit(name:string, surname:string, username:string, password:string) {
  
      this.authenticationService.register(
        name,
        surname,
        username,
        password
      );
}
public onSignInClick(){
  this.router.navigate(['/login']);
}

public onGoogleClick(){
  window.location.href='https://accounts.google.com/'
}

public onFacebookClick(){
  window.location.href='https://www.facebook.com/'
}
}
