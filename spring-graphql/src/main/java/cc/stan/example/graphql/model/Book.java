package cc.stan.example.graphql.model;

import java.util.List;

public class Book {
    private Long id;
    private String name;
    private List<Author> authors;

    public Book(Long id, String name, List<Author> authors) {
        this.id = id;
        this.name = name;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
