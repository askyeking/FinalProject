import { VendorService } from './../vendor.service';
import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';
import { SearchService } from '../search.service';
import { ActivatedRoute } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { Router } from '@angular/router';
import { createEmptyStateSnapshot } from '@angular/router/src/router_state';
import { InventoryItem } from '../models/inventory-item';
import { User } from '../models/user';

@Component({
  selector: 'app-inventory-item-list',
  templateUrl: './inventory-item-list.component.html',
  styleUrls: ['./inventory-item-list.component.css']
})
export class InventoryItemListComponent implements OnInit {

  inventoryItems = [];
  selected: InventoryItem = null;
  temp: string;
  parameter: string;
  keyword: string;
  vendorsUser: User;


  loadInventoryItems() {
    this.inventoryItemService.index().subscribe(
      data => this.inventoryItems = data,
      err => console.error('Observer got an error: ' + err)
    );
  }

  openItemView(itemId: number) {
    console.log(itemId);
    this.router.navigateByUrl('inventoryItems/viewItem/' + itemId);
  }

  loadParameterizedInventoryItems() {
      this.searchService.search(this.parameter, this.keyword).subscribe(
          data => {
            this.inventoryItems = data;
          },
          err => {
           console.error('Observer got an error: ' + err);
          }
      );

  }

  setSelectedItem(item: InventoryItem) {
    this.selected = item;
    this.vendorService.getVendorByInventoryItemId(item.id).subscribe(
      data => {
        this.vendorsUser = data;
        this.selected.vendor = this.vendorsUser.vendor;
      },
      err => {
       console.error('Observer got an error: ' + err);
      }
  );
  }

  viewVendor() {
    this.router.navigateByUrl('vendor/profile/' + this.selected.vendor.id);
  }

  constructor(private inventoryItemService: InventoryItemService,
     public authService: AuthService, private searchService: SearchService, private route: ActivatedRoute,
     private router: Router, private vendorService: VendorService) { }

  ngOnInit() {
    this.parameter = this.route.snapshot.paramMap.get("parameter");
    this.keyword = this.route.snapshot.paramMap.get("keyword");


    if (this.parameter && this.keyword) {
      this.loadParameterizedInventoryItems();
    } else {
      this.loadInventoryItems();
    }
  }

}
