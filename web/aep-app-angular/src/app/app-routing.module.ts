import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthLoginComponent } from './components/auth-login/auth-login.component';
import { CampeonatoListComponent } from './components/campeonato-list/campeonato-list.component';

const routes: Routes = [
  {path: 'auth/admin-login', component: AuthLoginComponent, pathMatch: 'full' },
  {path: 'championships/', component: CampeonatoListComponent, pathMatch: 'full'},
   {path: '', redirectTo: 'auth/admin-login', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
