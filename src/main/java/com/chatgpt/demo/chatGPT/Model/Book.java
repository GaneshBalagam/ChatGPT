package com.chatgpt.demo.chatGPT.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private Long id;
    private String title;
    private String author;
    private String genre;

    //getters and setters
}
