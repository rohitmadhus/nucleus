import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/app/login';
import { AuthService } from 'src/app/services/auth/auth.service';
import { StorageService } from 'src/app/services/auth/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  login: Login;
  loginForm: FormGroup;

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private router: Router
  ) {
    this.login = { username: '', password: '' };
    this.loginForm = new FormGroup({
      username: new FormControl(this.login.username, [Validators.required]),
      password: new FormControl(this.login.password, [Validators.required]),
    });
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.router.navigate(['/app']);
    }
  }

  onSubmit(): void {
    this.authService
      .login(this.loginForm.value['username'], this.loginForm.value['password'])
      .subscribe({
        next: (data) => {
          this.router.navigate(['/app']);
        },
        error: (err) => {
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        },
      });
  }
}
