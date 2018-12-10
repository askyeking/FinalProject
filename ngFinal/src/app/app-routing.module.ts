import { ItemRentalViewComponent } from './item-rental-view/item-rental-view.component';
import { VendorProfileComponent } from './vendor-profile/vendor-profile.component';
import { VendorInventoryListComponent } from './vendor-inventory-list/vendor-inventory-list.component';
import { CustomerProfileComponent } from './customer-profile/customer-profile.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { InventoryItemListComponent } from './inventory-item-list/inventory-item-list.component';
import { InventoryItemViewComponent } from './inventory-item-view/inventory-item-view.component';
import { VendorListComponent } from './vendor-list/vendor-list.component';
import { ItemsRentalHistoryComponent } from './items-rental-history/items-rental-history.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: InventoryItemListComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: CustomerProfileComponent },
  { path: 'vendor', component: VendorListComponent },
  { path: 'vendorInventory', component: VendorInventoryListComponent },
  { path: '', component: InventoryItemListComponent },
  { path: 'landing', component: InventoryItemListComponent },

  //  TODO: Vendor Profile not showing
  { path: 'vendor/profile/:id', component: VendorProfileComponent, runGuardsAndResolvers: 'always'},
  { path: 'inventoryItems/viewItem/:id', component: InventoryItemViewComponent},
  { path: 'inventoryItems/rental/:id', component: ItemRentalViewComponent},

  // TODO: Search by name not working
  { path: 'items/search/:parameter/:keyword', component: InventoryItemListComponent, runGuardsAndResolvers: 'always'},
  { path: 'vendor/search/:keyword', component: VendorListComponent},

  // customer should not see this page
  { path: 'inventoryItems/viewItem/history/:id', component: ItemsRentalHistoryComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true}),
    RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
