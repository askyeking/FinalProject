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

  constructor(private authService: AuthService) { }


login(user: User) {
  console.log('in here');
  console.log(user);

  this.authService.login(user.email, user.password).subscribe(
    data => {
      // this.todo.selected = user;
      this.loginUser = new User();
      // this.openTodos();
    },
    err => {
      console.error('Observer got an error' + err);
    }
  );
  // this.router.navigateByUrl('todo');
}



  ngOnInit() {

  }

}
