import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  // , private currentRoute: ActivatedRoute
  constructor(
    private authService: AuthService,
    private router: Router) { }
    // private router: Router, private todo: TodoListComponent) { }

  logout() {
    console.log('logout');
    this.authService.logout();
    // this.todo.clearTodos();
    // this.router.navigateByUrl('home');
  }

  ngOnInit() {
  }

}
