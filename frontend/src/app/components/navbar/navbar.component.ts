import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from "../../services/authentication.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  loggedUser?: string;

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit(): void {
    this.authenticationService.authentication().subscribe(user => {
      this.loggedUser = user?.user;
    })
  }

}
