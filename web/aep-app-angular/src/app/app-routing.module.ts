import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthLoginComponent } from './components/auth-login/auth-login.component';
import { CampeonatoListComponent } from './components/campeonato-list/campeonato-list.component';

const routes: Routes = [
  // {path: 'auth/admin-login', component: AuthLoginComponent },
  {path: 'championships/', component: CampeonatoListComponent},
   {path: '', redirectTo: 'championships/', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
