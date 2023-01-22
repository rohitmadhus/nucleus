import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ContentsComponent } from './components/contents/contents.component';
import { TilesComponent } from './components/tiles/tiles.component';
import { WebConfigurationComponent } from './components/web-configuration/web-configuration.component';
import { UserConfigurationComponent } from './components/user-configuration/user-configuration.component';
import { NotificationConfigurationComponent } from './components/notification-configuration/notification-configuration.component';
import { ContentTypesComponent } from './components/content-types/content-types.component';
import { TileTypesComponent } from './components/tile-types/tile-types.component';
import { PictureConfigurationComponent } from './components/picture-configuration/picture-configuration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ContentDialogComponent } from './dialog/contents/content-dialog/content-dialog.component';
import { ContentTypeDialogComponent } from './dialog/content-type/content-type-dialog/content-type-dialog.component';
import { TileDialogComponent } from './dialog/tiles/tile-dialog/tile-dialog.component';
import { TileTypeDialogComponent } from './dialog/tile-type/tile-type-dialog/tile-type-dialog.component';
import { PictureDialogComponent } from './dialog/pictures/picture-dialog/picture-dialog.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { AgeGroupComponent } from './components/age-group/age-group.component';
import { CategoryDialogComponent } from './dialog/categories/category-dialog/category-dialog.component';
import { ConfirmDialogComponent } from './dialog/common/confirm-dialog/confirm-dialog.component';
import { AgeGroupDialogComponent } from './dialog/age-group/age-group-dialog/age-group-dialog.component';

import { httpInterceptorProviders } from './helpers/auth.interceptor';
import { LoginComponent } from './auth/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DashboardComponent,
    ContentsComponent,
    TilesComponent,
    WebConfigurationComponent,
    UserConfigurationComponent,
    NotificationConfigurationComponent,
    ContentTypesComponent,
    TileTypesComponent,
    PictureConfigurationComponent,
    ContentDialogComponent,
    ContentTypeDialogComponent,
    TileDialogComponent,
    TileTypeDialogComponent,
    PictureDialogComponent,
    CategoriesComponent,
    AgeGroupComponent,
    CategoryDialogComponent,
    ConfirmDialogComponent,
    AgeGroupDialogComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatDividerModule,
  ],
  providers: [httpInterceptorProviders, CookieService],
  bootstrap: [AppComponent],
})
export class AppModule {}
