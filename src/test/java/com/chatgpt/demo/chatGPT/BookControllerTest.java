package com.chatgpt.demo.chatGPT;

import com.chatgpt.demo.chatGPT.Controller.BookController;
import com.chatgpt.demo.chatGPT.Model.Book;
import com.chatgpt.demo.chatGPT.Service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = Arrays.asList(
                new Book(1L, "Book 1", "Author 1", "Genre 1"),
                new Book(2L, "Book 2", "Author 2", "Genre 2"));
        when(bookService.getAllBooks()).thenReturn(books);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Book 1")))
                .andExpect(jsonPath("$[0].author", is("Author 1")))
                .andExpect(jsonPath("$[0].genre", is("Genre 1")));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = new Book(1L, "Book 1", "Author 1", "Genre 1");
        when(bookService.addBook(book)).thenReturn(book);

        mvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(book)))
                .andExpect(status().isOk());

        verify(bookService, times(1)).addBook(book);
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book(1L, "Book 1", "Author 1", "Genre 1");
        when(bookService.updateBook(1L,book)).thenReturn(book);

        mvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(book)))
                .andExpect(status().isOk());

        verify(bookService, times(1)).updateBook(1L,book);
    }

    @Test
    public void testDeleteBook() throws Exception {
        mvc.perform(delete("/books/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1L);
    }

    // utility method to convert a Book object to JSON
    private static String toJson(Book book) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(book);
    }
}
