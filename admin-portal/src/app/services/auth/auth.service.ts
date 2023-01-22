import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { APIConstants } from 'src/app/constants/api-constants';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser = {};
  constructor(private http: HttpClient, public router: Router) {}

  // login
  login(userName: string, password: string) {
    return this.http.post<any>(
      APIConstants.LOGIN_URL,
      {
        username: userName,
        password: password,
      },
      httpOptions
    );
  }

  // logout
  logout(): Observable<any> {
    return this.http.post(APIConstants.LOGOUT_URL, {}, httpOptions);
  }
}
