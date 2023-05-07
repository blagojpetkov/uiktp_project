import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {


  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  public login(username: string, password: string): void {
    // TODO : API CALL
  }

  public register(username: string, email: string, password: string): void {
    //TODO: API CALL
  }

  public logout() {
    this.router.navigate(['/login']);
  }
}
