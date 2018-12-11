import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
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
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { VendorProfileComponent } from './vendor-profile/vendor-profile.component';
import { VendorInventoryListComponent } from './vendor-inventory-list/vendor-inventory-list.component';
import { InventoryItemViewComponent } from './inventory-item-view/inventory-item-view.component';
import { VendorListComponent } from './vendor-list/vendor-list.component';
import { ItemRentalViewComponent } from './item-rental-view/item-rental-view.component';
import { MatSelectModule, MatMenuModule, MatInputModule, MatIconModule, MatExpansionModule } from '@angular/material';
import { MatCheckboxModule } from '@angular/material';
import { MatButtonModule } from '@angular/material';
import { MatToolbarModule } from '@angular/material';
import { ItemsRentalHistoryComponent } from './items-rental-history/items-rental-history.component';
import { InactivizerPipe } from './inactivizer.pipe';


@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    NavigationComponent,
    RegisterComponent,
    InventoryItemListComponent,
    CustomerProfileComponent,
    VendorProfileComponent,
    VendorInventoryListComponent,
    InventoryItemViewComponent,
    VendorListComponent,
    ItemRentalViewComponent,
    ItemsRentalHistoryComponent,
    InactivizerPipe
  ],
  imports: [
    BsDropdownModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCheckboxModule,
    MatSelectModule,
    MatToolbarModule,
    MatMenuModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatExpansionModule

  ],
  providers: [
    AuthService,
    InventoryItemListComponent,
    LoginComponent,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
