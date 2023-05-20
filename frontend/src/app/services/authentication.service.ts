import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {


  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  public async login(username: string, password: string): Promise<void> {
    // LoginController  ?
    // PostMapping "/login" @RequestParam String username, @RequestParam String password
    
    await firstValueFrom(this.http.post(`/login?username=${username}&password=${password}`,''))
    this.router.navigate(['/home']);
  }

  public async register(name: string, surname: string, username:string, password: string): Promise<void> {
    // LoginController  ?
    // PostMapping "/register" @RequestParam String username, @RequestParam String password, @RequestParam String firstName,@RequestParam String lastName
    
    await firstValueFrom(this.http.post(`/register?username=${username}&password=${password}&firstName=${name}&lastName=${surname}`,''))
    this.router.navigate(['/login']);
  }

  public logout() {
    this.router.navigate(['/login']);
  }
}
