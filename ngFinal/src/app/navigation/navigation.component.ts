import { Vendor } from './../models/vendor';
import { SearchService } from './../search.service';
import { CategoryService } from './../category.service';
import { Category } from './../models/category';
import { environment } from './../../environments/environment';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { AuthService } from '../auth.service';
import { VendorService } from '../vendor.service';
import { UserService } from '../user.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  loginUser = new User();
  public isCollapsed = false;
  public dropdownButtonText = 'Search By';

  categories: Category[] = [];
  selectedCategory: Category;
  parameter: String = "";
  keyword: String = "";
  vendors: Vendor[] = [];
  loggedInUser: User;

  constructor(private router: Router, public authService: AuthService,
     public catService: CategoryService, private searchService: SearchService, route: ActivatedRoute,
     private vendorService: VendorService, private userService: UserService) { }


  loadCategories() {
    this.catService.loadCategories().subscribe(
      data => {
        console.log(data);
        this.categories = data;
        console.log(this.categories);
      },
      err => console.error('Observer got an error: ' + err)
      );
  }

  search() {
    this.parameter = this.dropdownButtonText.toLowerCase();
    if (this.parameter === 'category' || this.parameter === 'name') {
      this.router.navigateByUrl("items/search/" + this.parameter + "/" + this.keyword);
    } else if (this.parameter === 'vendor') {
      this.router.navigateByUrl("vendor/search/" + this.keyword);
    }

  }

  loadVendors() {
    this.vendorService.index().subscribe(
      data => {
        this.vendors = data;
        console.log(this.vendors);
        this.router.navigateByUrl("vendor");
      },
      err => {
       console.error('Observer got an error: ' + err);
      }
    );
  }

  get searchData(): string {
    return this.searchService.searchParameter;
  }

  set searchData(value: string) {
    this.searchService.searchParameter = value;
  }

  selectCategory(selectedCategory: Category) {
      this.selectedCategory = selectedCategory;
      this.dropdownButtonText = 'Category';
      this.keyword = this.selectedCategory.name;
      console.log(this.keyword);
  }

  ngOnInit() {
    this.loadCategories();
  }




  viewVendorProfile() {
    this.userService.retrieveProfiles().subscribe(
      data => {
        this.loggedInUser = data;
        this.router.navigateByUrl("vendor/profile/" + this.loggedInUser.vendor.id);
      },
      err => {
       console.error('Observer got an error: ' + err);
      }
    );
  }

  viewCustomerProfile() {
  }


}
