package com.epxense;

import com.epxense.model.Event;
import com.epxense.model.Participant;
import com.epxense.model.Purchase;
import com.epxense.model.Transaction;
import com.epxense.repository.EventRepository;

import com.epxense.repository.ParticipantRepository;
import com.epxense.repository.PurchaseRepository;
import com.epxense.repository.TransactionRepository;
import com.epxense.service.ExpenseCalculatorService;
import jakarta.servlet.http.Part;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class Main{
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner testDatabase(
            EventRepository eventRepository,
            ExpenseCalculatorService expenseCalculatorService,
            ParticipantRepository participantRepository,
            TransactionRepository transactionRepository,
            PurchaseRepository purchaseRepository) {
        return args -> {
            Event event = eventRepository.findById(5L).orElseThrow(() -> new RuntimeException("Базы нини"));


            Map<Participant, BigDecimal> balances = expenseCalculatorService.calculateRawBalance(event);
            System.out.println("РЕЗУЛЬТАТЫ: " + balances);
        };
    }
}