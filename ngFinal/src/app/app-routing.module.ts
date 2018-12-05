import { VendorProfileComponent } from './vendor-profile/vendor-profile.component';
import { VendorInventoryListComponent } from './vendor-inventory-list/vendor-inventory-list.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { InventoryItemListComponent } from './inventory-item-list/inventory-item-list.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: CustomerProfileComponent },
  { path: 'vendorInventory', component: VendorInventoryListComponent },
  { path: '', component: InventoryItemListComponent },
  { path: 'landing', component: InventoryItemListComponent },
  { path: 'vendorProfile', component: VendorProfileComponent },
  { path: 'inventoryItems/search/vendor/:vendorName', component: InventoryItemListComponent},
  { path: 'inventoryItems/search/category/:category', component: InventoryItemListComponent},
  { path: 'inventoryItems/search/keyword/:keyword', component: InventoryItemListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
