import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user.service';
import {User} from '../../models/user.model';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-user-list',
  imports: [
    NgForOf
  ],
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
  users: User[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(data => this.users = data);
  }
}
