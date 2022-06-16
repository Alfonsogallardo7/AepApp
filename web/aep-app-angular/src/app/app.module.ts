import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialImportsModule } from './modules/material-imports.module';
import { AuthLoginComponent } from './components/auth-login/auth-login.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CampeonatoItemComponent } from './components/campeonato-item/campeonato-item.component';
import { CampeonatoListComponent } from './components/campeonato-list/campeonato-list.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { DialogCampeonatoDetailComponent } from './components/dialog-campeonato-detail/dialog-campeonato-detail.component';
import { NewCampeonatoComponent } from './components/new-campeonato/new-campeonato.component';
import { JuezItemComponent } from './components/juez-item/juez-item.component';
import { JuezListComponent } from './components/juez-list/juez-list.component';
@NgModule({
  declarations: [
    AppComponent,
    AuthLoginComponent,
    CampeonatoItemComponent,
    CampeonatoListComponent,
    NavbarComponent,
    HomeComponent,
    DialogCampeonatoDetailComponent,
    NewCampeonatoComponent,
    JuezItemComponent,
    JuezListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialImportsModule,
    HttpClientModule,
    ReactiveFormsModule
    

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
