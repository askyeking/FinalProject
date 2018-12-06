import { Router, ActivatedRoute } from '@angular/router';
import { SearchService } from './../search.service';
import { Component, OnInit } from '@angular/core';
import { InventoryItemService } from '../inventory-item.service';
import { AuthService } from '../auth.service';
import { getValueInRange } from '@ng-bootstrap/ng-bootstrap/util/util';
import { InventoryItem } from '../models/inventory-item';
import { ItemRental } from '../models/item-rental';
import { UserService } from '../user.service';
import { User } from '../models/user';

@Component({
  selector: 'app-inventory-item-view',
  templateUrl: './inventory-item-view.component.html',
  styleUrls: ['./inventory-item-view.component.css']
})
export class InventoryItemViewComponent implements OnInit {


  id = null;
  selected: InventoryItem = null;
  itemRental: ItemRental = new ItemRental();

  // try;
  constructor(private inventoryItemService: InventoryItemService,
    public authService: AuthService, private userService: UserService, private router: Router, private route: ActivatedRoute) {

     }

  ngOnInit() {
    // this.try = this.langId;
    this.refresh();
    this.id  = this.route.snapshot.paramMap.get('id');
    this.inventoryItemService.getOne(this.id).subscribe(
      data => this.selected = data,
      err => console.error('itemView.ngOnInit.getOne: ' + err)
    );
  }

  backToHome() {
    this.router.navigateByUrl('');
  }

  refresh() {
    this.id = null;
    this.selected = null;
    this.itemRental = new ItemRental();
  }

  persistItemRental() {
    let user: User;
    this.userService.retrieveProfiles().subscribe(
      data => {
        user = data;
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
    this.itemRental.customer = user.customer;
    this.itemRental.inventoryItem = this.selected;
    // this.itemRental.paidAmount =

  }

  calculatePrice(): number {
    let oneDay = 1000 * 60 * 60 * 24;

    // this.itemRental.endDate.getT
    // this.itemRental.

    return 3;
  }

}
