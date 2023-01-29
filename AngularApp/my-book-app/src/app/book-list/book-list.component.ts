import { Component } from '@angular/core';
import { BookService } from '../book.service';


@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent {
  books:any;
  constructor(private bookService: BookService) {}
  ngOnInit() {
    this.bookService.getBooks().subscribe(data => {
      console.log(data);
        this.books = data;
    });
}

}
