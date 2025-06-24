import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models/book.model';

@Injectable({ providedIn: 'root' })
export class BookService {
  private apiUrl = 'http://localhost:8080/books';

  constructor(private http: HttpClient) {}

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.apiUrl}/extended`);
  }

  reserveBook(bookId: string, userId: string): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${bookId}/reserve?userId=${userId}`, {});
  }

  returnBook(bookId: string): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/${bookId}/return`, {});
  }
}
