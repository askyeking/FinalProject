import { Router, ActivatedRoute } from '@angular/router';
import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';
import { AuthService } from '../auth.service';
import { getValueInRange } from '@ng-bootstrap/ng-bootstrap/util/util';

@Component({
  selector: 'app-inventory-item-view',
  templateUrl: './inventory-item-view.component.html',
  styleUrls: ['./inventory-item-view.component.css']
})
export class InventoryItemViewComponent implements OnInit {


  langId;
  // try;
  constructor(private inventoryItemService: InventoryItemService,
    public authService: AuthService, private searchService: SearchService, private router: Router, private route: ActivatedRoute) {

     }

  ngOnInit() {
    // this.try = this.langId;
    this.langId  = this.route.snapshot.paramMap.get('id');
  }


}
