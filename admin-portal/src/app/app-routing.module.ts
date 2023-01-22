import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { AgeGroupComponent } from './components/age-group/age-group.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { ContentTypesComponent } from './components/content-types/content-types.component';
import { ContentsComponent } from './components/contents/contents.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { NotificationConfigurationComponent } from './components/notification-configuration/notification-configuration.component';
import { PictureConfigurationComponent } from './components/picture-configuration/picture-configuration.component';
import { TileTypesComponent } from './components/tile-types/tile-types.component';
import { TilesComponent } from './components/tiles/tiles.component';
import { UserConfigurationComponent } from './components/user-configuration/user-configuration.component';
import { WebConfigurationComponent } from './components/web-configuration/web-configuration.component';
import { AuthGuard } from './helpers/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent, pathMatch: 'full' },
  {
    path: 'app',
    component: DashboardComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'app/contents',
    component: ContentsComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'app/content/types',
    component: ContentTypesComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'app/tiles',
    component: TilesComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'app/tile/types',
    component: TileTypesComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'app/categories',
    component: CategoriesComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'app/agegroup',
    component: AgeGroupComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'app/notification/configuration',
    component: NotificationConfigurationComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'app/picture/configuration',
    component: PictureConfigurationComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'web/configuration',
    component: WebConfigurationComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  {
    path: 'user/configuration',
    component: UserConfigurationComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard],
  },
  { path: '', redirectTo: 'app', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
