<<<<<<< HEAD
import { VendorProfileComponent } from './vendor-profile/vendor-profile.component';
=======
import { VendorInventoryListComponent } from './vendor-inventory-list/vendor-inventory-list.component';
>>>>>>> 00dc7bbf8a69fe8de4ba2ed25df1971f28fe8bf3
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
