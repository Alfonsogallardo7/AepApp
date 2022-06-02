import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthLoginComponent } from './components/auth-login/auth-login.component';

const routes: Routes = [
  // {path: '', component: AuthLoginComponent, redirectTo: 'auth/admin-login', pathMatch: 'full'},
  {path: 'auth/admin-login', component: AuthLoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
