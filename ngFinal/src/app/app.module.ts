import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './auth.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { LandingComponent } from './landing/landing.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { NavigationComponent } from './navigation/navigation.component';
import { RegisterComponent } from './register/register.component';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { InventoryItemListComponent } from './inventory-item-list/inventory-item-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    NavigationComponent,
    RegisterComponent,
    InventoryItemListComponent
  ],
  imports: [
    BsDropdownModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    AuthService,
    InventoryItemListComponent,
    LoginComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
