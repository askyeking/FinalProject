import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';
import { SearchService } from '../search.service';

@Component({
  selector: 'app-inventory-item-list',
  templateUrl: './inventory-item-list.component.html',
  styleUrls: ['./inventory-item-list.component.css']
})
export class InventoryItemListComponent implements OnInit {

  inventoryItems = [];
  selected = null;
  temp: string;

  loadInventoryItems() {
    this.inventoryItemService.index().subscribe(
      data => this.inventoryItems = data,
      err => console.error('Observer got an error: ' + err)
    );

  }


  get SearchData(): string {
    return this.searchService.searchParameter;
  }

  set searchData(value: string) {
    this.searchService.searchParameter = value;
  }



  constructor(private inventoryItemService: InventoryItemService,
     public authService: AuthService, private searchService: SearchService) { }

  ngOnInit() {
    this.loadInventoryItems();
  }

}
