import { AuthService } from './../auth.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';
import { SearchService } from '../search.service';
import { ActivatedRoute } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { Router } from '@angular/router';
import { createEmptyStateSnapshot } from '@angular/router/src/router_state';

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

  openItemView(itemId: number) {
    console.log(itemId);
    this.router.navigateByUrl('items/viewItem/' + itemId);
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
     public authService: AuthService, private searchService: SearchService, private route: ActivatedRoute,
     private router: Router) { }

  ngOnInit() {
    this.parameter = this.route.snapshot.paramMap.get("parameter");
    this.keyword = this.route.snapshot.paramMap.get("keyword");
    console.log(this.route.snapshot.paramMap);
    console.log("---------------");
    console.log(this.parameter + "-> PARAMETER VALUE");
    console.log(this.keyword + "-> KEYWORD VALUE");
    console.log("---------------");


    if (this.parameter && this.keyword) {
      console.log("INSIDE INIT IF");
      this.loadParameterizedInventoryItems();
    } else {
      this.loadInventoryItems();
    }
  }

}
