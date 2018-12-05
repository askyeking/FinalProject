import { SearchService } from './../search.service';
import { CategoryService } from './../category.service';
import { Category } from './../models/category';
import { environment } from './../../environments/environment';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  loginUser = new User();
  private baseUrl = environment.baseUrl;
  public isCollapsed = false;
  public dropdownButtonText = 'Search By';

  categories: Category[] = [];
  selected: Category;


  constructor(private router: Router, public authService: AuthService,
     public catService: CategoryService, private searchService: SearchService) { }


// login(user: User) {
//   console.log('in here');
//   console.log(user);

//   this.authService.login(user.email, user.password).subscribe(
//     data => {
//       this.loginUser = new User();
//     },
//     err => {
//       console.error('Observer got an error' + err);
//     }
//   );
// }


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

  searchByCategory() {

  }

  searchByKeyword() {

  }

  searchByVendor() {

  }

  get searchData(): string {
    return this.searchService.searchParameter;
  }

  set searchData(value: string) {
    this.searchService.searchParameter = value;
  }

  selectCategory(selectedCategory: Category) {
      this.selected = selectedCategory;
      this.dropdownButtonText = this.selected.name;
  }

  ngOnInit() {
    this.loadCategories();
  }


}
