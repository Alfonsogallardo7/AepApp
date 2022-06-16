import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthLoginComponent } from './components/auth-login/auth-login.component';
import { CampeonatoListComponent } from './components/campeonato-list/campeonato-list.component';
import { HomeComponent } from './components/home/home.component';
import { JuezListComponent } from './components/juez-list/juez-list.component';
import { NewCampeonatoComponent } from './components/new-campeonato/new-campeonato.component';

const routes: Routes = [
  {path: 'auth/admin-login', component: AuthLoginComponent, pathMatch: 'full' },
  {path: 'championships/', component: CampeonatoListComponent, pathMatch: 'full'},
  {path: 'home/', component: HomeComponent, pathMatch: 'full'},
  {path: 'championships/new', component: NewCampeonatoComponent, pathMatch: 'full'},
  {path: 'judges/', component: JuezListComponent, pathMatch: 'full'},
  {path: '', redirectTo: 'home/', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
