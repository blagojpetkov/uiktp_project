import { AuthenticationService } from 'src/app/services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css'],
})
export class RegisterPageComponent {
  private router: Router
  fullname: string = '';
  email: string = '';
  password: string = '';
  repeat_password: string = '';

  constructor(private authenticationService: AuthenticationService) {}

  public onSubmit(fullname:string, email:string, password:string, repeat_password:string) {
    if(password == repeat_password){
      this.authenticationService.register(
        fullname,
        email,
        password
      );
  }
  else{
    //show error msg
  }
}
public onSignInClick(){
  this.router.navigate(['/login']);
}
}
