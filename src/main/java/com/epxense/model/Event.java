package com.epxense.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "events")
public class Event
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "is_closed")
    private Integer is_closed;

    public Event(){}

    @PrePersist
    protected void onCreate(){
        if (this.date == null) this.date = LocalDateTime.now();
        if (this.is_closed == null) this.is_closed = 0;
    }

    public Long getId() {return id; }
    public void setId(Long id) {this.id = id; }

    public String getTitle() {return title; }
    public void setTitle(String title) {this.title = title; }

    public LocalDateTime getDate() {return date; }
    public void setDate(LocalDateTime date) {this.date = date; }

    public Integer getIs_closed() {return is_closed; }
    public void setIs_closed(Integer is_closed) {this.is_closed = is_closed; }

    @Override
    public String toString() {
        return "Event{id = " + id + ", title = '" + title + "', date = " + date + ", is_closed = " + is_closed + "}";
    }
}