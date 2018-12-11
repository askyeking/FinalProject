import { VendorService } from './../vendor.service';
import { Vendor } from './../models/vendor';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { SearchService } from '../search.service';

@Component({
  selector: 'app-vendor-list',
  templateUrl: './vendor-list.component.html',
  styleUrls: ['./vendor-list.component.css']
})
export class VendorListComponent implements OnInit, OnDestroy {

  navigationSubscription;
  vendors: Vendor[] = [];
  keyword: string;

  // index route for getting all vendors
  index() {
    this.vendorService.index().subscribe(
      data => {
        this.vendors = data;
        console.log(this.vendors);
      },
      err => {
       console.error('Observer got an error: ' + err);
      }
    );
  }

  // method used when searching for specific vendors
  searchVendors() {
    this.searchService.searchVendors(this.keyword).subscribe(
      data => {
        this.vendors = data;
        console.log(this.vendors);
      },
      err => {
       console.error('Observer got an error: ' + err);
      }
    );
  }

 // method for navigating from the list of vendors to a specific vendor
  viewVendor(vendor: Vendor) {
      this.router.navigateByUrl('vendor/profile/' + vendor.id);
  }

  constructor(private router: Router, private route: ActivatedRoute, private searchService: SearchService,
     private vendorService: VendorService) {

      this.navigationSubscription = this.router.events.subscribe(
        (e: any) => {
        if (e instanceof NavigationEnd) {
          this.keyword = this.route.snapshot.paramMap.get("keyword");
          if (this.keyword) {
            this.searchVendors();
          } else {
            this.index();
          }
        }
      }
      );
     }

  // on init we get the keyword and set it to a field in the component
  // if the keyword is populated it will run the searchVendors() method using the keyword
  // otherwise display a list of all vendors.

  ngOnInit() {
    this.keyword = this.route.snapshot.paramMap.get("keyword");
    if (this.keyword) {
      this.searchVendors();
    } else {
      this.index();
    }
  }

  ngOnDestroy() {
    // unsubscribe to prevent memory leaks
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }


}
