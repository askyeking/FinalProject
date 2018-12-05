import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';

@Component({
  selector: 'app-vendor-inventory-list',
  templateUrl: './vendor-inventory-list.component.html',
  styleUrls: ['./vendor-inventory-list.component.css']
})
export class VendorInventoryListComponent implements OnInit {

  vendorInventory = [];
  selected = null;


  constructor(private inventoryItemService: InventoryItemService, public authService: AuthService) {}

  ngOnInit() {
    this.loadVendorInventory();
  }


  loadVendorInventory() {
    this.inventoryItemService.loadVendorItems().subscribe(
      data => this.vendorInventory = data,
      err => {
        console.log(this.vendorInventory);
        console.error('Observer got an error: ' + err);
      }
    );
  }
}
