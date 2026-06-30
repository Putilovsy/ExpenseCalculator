package com.epxense.model;

import jakarta.persistence.*;
import jakarta.servlet.http.Part;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Participant buyer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchase_consumers",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private List<Participant> consumers = new ArrayList<>();

    public Purchase(){}

    public Long getId() {return id; }
    public void setId(Long id) {this.id = id; }

    public String getName() {return name; }
    public void setName(String name) {this.name = name; }

    public String getDescription() {return description; }
    public void setDescription(String description) {this.description = description; }

    public BigDecimal getAmount() {return amount; }
    public void setAmount(BigDecimal amount) {this.amount = amount; }

    public Event getEvent() {return event; }
    public void setEvent(Event event) {this.event = event; }

    public Participant getBuyer() {return buyer; }
    public void setBuyer(Participant buyer) {this.buyer = buyer; }

    public List<Participant> getConsumers() {return consumers; }
    public void setConsumers(List<Participant> consumers) {this.consumers = consumers; }

    @Override
    public String toString() {
        return ("ТОВАР: ID = " + id + ", name = " + name + "\nDescription: " + description + "\n amount = " + amount);
    }
}
