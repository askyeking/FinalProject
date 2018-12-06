import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';
import { SearchService } from '../search.service';
import { ActivatedRoute } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Component({
  selector: 'app-inventory-item-list',
  templateUrl: './inventory-item-list.component.html',
  styleUrls: ['./inventory-item-list.component.css']
})
export class InventoryItemListComponent implements OnInit {

  inventoryItems = [];
  selected = null;
  temp: string;
  parameter: string;
  keyword: string;

  loadInventoryItems() {
    this.inventoryItemService.index().subscribe(
      data => this.inventoryItems = data,
      err => console.error('Observer got an error: ' + err)
    );

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




  constructor(private inventoryItemService: InventoryItemService,
     public authService: AuthService, private searchService: SearchService, private route: ActivatedRoute) { }

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
