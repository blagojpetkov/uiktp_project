import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { User } from "../domain/user";
import { TEST_USER } from "../test-data";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  getUser(): Observable<User> {
    // TODO change to api when it is implemented
    return of(TEST_USER)
  }
}
