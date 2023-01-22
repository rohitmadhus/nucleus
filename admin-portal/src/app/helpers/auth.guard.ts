import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  CanActivate,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from '../services/auth/storage.service';
@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(public storageSevice: StorageService, public router: Router) {}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | Promise<boolean> | boolean {
    if (this.storageSevice.isLoggedIn() !== true) {
      this.router.navigate(['/login']);
    }
    return true;
  }
}
