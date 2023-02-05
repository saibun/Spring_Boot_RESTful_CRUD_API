package com.Rest_Example.CourseList.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Book {
    @Id
    @Column(name = "bookid")
    private long id;
    @Column(name = "book_name")
    private String name;
    @Column(name = "genre")
    private String genre;

    public Book(long id, String name, String genre) {
        super();
        this.id = id;
        this.name = name;
        this.genre = genre;
    }
    public Book(){
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

}
