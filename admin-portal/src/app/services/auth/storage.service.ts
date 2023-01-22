import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor(private cookieService: CookieService) {}

  clean(): void {
    window.sessionStorage.clear();
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  public isLoggedIn(): boolean {
    let cookieValue = this.cookieService.get('access-token');

    const user = window.sessionStorage.getItem(USER_KEY);
    if (cookieValue) {
      return true;
    }

    return false;
  }
}
