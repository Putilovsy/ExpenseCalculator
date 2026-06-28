package com.epxense.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Participant sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Participant receiver;

    public Transaction(){}

    @PrePersist
    public void onCreate(){
        if (this.timestamp == null) this.timestamp = LocalDateTime.now();
    }

    public Long getId() {return id; }
    public void setId(Long id) {this.id = id; }

    public BigDecimal getAmount() {return amount; }

    public void setAmount(BigDecimal amount) {this.amount = amount; }

    public LocalDateTime getTimestamp() {return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp; }

    public Event getEvent() {return event; }
    public void setEvent(Event event) {this.event = event; }

    public Participant getSender() {return sender; }
    public void setSender(Participant sender) {this.sender = sender; }

    public Participant getReceiver() {return receiver; }
    public void setReceiver(Participant receiver) {this.receiver = receiver; }

    @Override
    public String toString() {
        return "ТРАНЗАКЦИЯ: ID = " + id + ", amount = " + amount +
                ", date = " + timestamp + ", sender - " + sender +
                ", receiver - " + receiver;
    }
}
