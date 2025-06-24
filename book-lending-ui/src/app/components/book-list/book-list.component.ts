import {Component, OnInit} from '@angular/core';
import {BookService} from '../../services/book.service';
import {Book} from '../../models/book.model';
import {FormsModule} from '@angular/forms';
import {User} from '../../models/user.model';
import {UserService} from '../../services/user.service';
import {NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-book-list',
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './book-list.component.html'
})
export class BookListComponent implements OnInit {
  books: Book[] = [];
  users: User[] = [];
  selectedUserId: string = '';

  constructor(
    private bookService: BookService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loadBooks();
    this.loadUsers();
  }

  loadBooks() {
    this.bookService.getAllBooks().subscribe(data => this.books = data);
  }

  loadUsers() {
    this.userService.getAllUsers().subscribe(data => this.users = data);
  }

  reserve(book: Book) {
    if (!this.selectedUserId) {
      alert("Please select a user.");
      return;
    }

    this.bookService.reserveBook(book.id, this.selectedUserId).subscribe(() => {
      this.loadBooks();
    });
  }

  return(book: Book) {
    this.bookService.returnBook(book.id).subscribe(() => {
      this.loadBooks();
    });
  }
}
