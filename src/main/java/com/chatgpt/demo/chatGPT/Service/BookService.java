package com.chatgpt.demo.chatGPT.Service;

import com.chatgpt.demo.chatGPT.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public BookService(List<Book> books) {
        this.books = Arrays.asList(
                new Book(1L, "Book 1", "Author 1", "Genre 1"),
                new Book(2L, "Book 2", "Author 2", "Genre 2"));    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book addBook(Book book) {
        book.setId(nextId);
        books.add(book);
        nextId++;
        return book;
    }

    public Book updateBook(Long id, Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.set(i, book);
                break;
            }
        }
        return book;
    }

    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
