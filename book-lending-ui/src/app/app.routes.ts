import {Routes} from '@angular/router';
import {BookListComponent} from './components/book-list/book-list.component';
import {UserListComponent} from './components/user-list/user-list.component';
import {AddUserComponent} from './components/add-user/add-user.component';

export const routes: Routes = [
  { path: '', redirectTo: 'books', pathMatch: 'full' },
  { path: 'books', component: BookListComponent },
  { path: 'users', component: UserListComponent },
  { path: 'users/new', component: AddUserComponent }
];
