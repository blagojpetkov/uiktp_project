import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from "@angular/common/http";
import { firstValueFrom } from 'rxjs';
import { SharedDataService } from './shared-data/shared-data.service';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {


  constructor(
    private http: HttpClient,
    private router: Router,
    private sharedDataService:SharedDataService,
  ) {
  }

  public async login(username: string, password: string): Promise<void> {
    // LoginController  ?
    // PostMapping "/login" @RequestParam String username, @RequestParam String password

    debugger
    // Source component
sessionStorage.setItem('sharedData', JSON.stringify(username));
// Source component
sessionStorage.setItem('sharedData2', JSON.stringify(password));


    // this.sharedDataService.setData(password,username);
    // this.sharedDataService.username=username;
    await firstValueFrom(this.http.post(`/login?username=${username}&password=${password}`, ''))
    this.router.navigate(['/courses']);
  }

  public async register(name: string, surname: string, username: string, password: string): Promise<void> {
    // LoginController  ?
    // PostMapping "/register" @RequestParam String username, @RequestParam String password, @RequestParam String
    // firstName,@RequestParam String lastName

    await firstValueFrom(this.http.post(`/register?username=${username}&password=${password}&firstName=${name}&lastName=${surname}`, ''))
    this.router.navigate(['/login']);
  }

  public logout() {
    this.router.navigate(['/login']);
  }

  authentication() {
    return this.http.get<{ user: string } | null>('/api/authentication');
  }
}
