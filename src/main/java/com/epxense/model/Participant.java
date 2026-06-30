package com.epxense.model;

import jakarta.persistence.*;

@Entity
@Table(name = "participants")
public class Participant
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "headcount", nullable = false)
    private Integer headcount;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "delegated_payer_id")
    private Participant delegatedPayer;

    public Participant() {}

    public Long getId() {return id; }
    public void setId(Long id) {this.id = id; }

    public String getName() {return name; }
    public void setName(String name) {this.name = name; }

    public Integer getHeadcount() {return headcount; }
    public void setHeadcount(Integer headcount) {this.headcount = headcount; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public Participant getDelegatedPayer() { return delegatedPayer; }
    public void setDelegatedPayer(Participant delegatedPayer) { this.delegatedPayer = delegatedPayer; }

    @Override
    public String toString() {
        return("Participant{Id: " + id + ", Name: " + name + ", headcount: " + headcount + "}");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;
        return java.util.Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getId());
    }
}
