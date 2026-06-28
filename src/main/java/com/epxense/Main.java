package com.epxense;

import com.epxense.model.Event;
import com.epxense.model.Participant;
import com.epxense.model.Purchase;
import com.epxense.model.Transaction;
import com.epxense.repository.EventRepository;

import com.epxense.repository.ParticipantRepository;
import com.epxense.repository.PurchaseRepository;
import com.epxense.repository.TransactionRepository;
import jakarta.servlet.http.Part;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class Main{
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner testDatabase(
            EventRepository eventRepository,
            ParticipantRepository participantRepository,
            TransactionRepository transactionRepository,
            PurchaseRepository purchaseRepository) {
        return args -> {
            System.out.println("КОМПЛЕКС ТЕСТ\n");

            Event event = new Event();
            event.setTitle("ШАШЛЫКИ");
            eventRepository.save(event);
            System.out.println("Создано мероприятие: " + event + "\n");

            Participant participant1 = new Participant();
            participant1.setName("Денчик");
            participant1.setEvent(event);
            participant1.setHeadcount(1);
            participantRepository.save(participant1);

            Participant participant2 = new Participant();
            participant2.setName("Олежа");
            participant2.setEvent(event);
            participant2.setHeadcount(1);
            participantRepository.save(participant2);

            System.out.println("УЧАСТНИКИ СОХРАНЕНЫ: " + participant1 + participant2 + "\n");

            Purchase purchase = new Purchase();
            purchase.setName("Мясо");
            purchase.setAmount(java.math.BigDecimal.valueOf(2000.00));
            purchase.setDescription("Шашлык свиной 4 кг");
            purchase.setEvent(event);
            purchase.setBuyer(participant1);

            purchase.getConsumers().add(participant1);
            purchase.getConsumers().add(participant2);

            purchaseRepository.save(purchase);

            System.out.println("ПОКУПКА СОХРАНЕНА: " + purchase + "\n На мясо скидываются: "
                    + purchase.getConsumers().size() + "participant's");

            Transaction transaction = new Transaction();
            transaction.setAmount(java.math.BigDecimal.valueOf(1000.00));
            transaction.setEvent(event);
            transaction.setSender(participant2);
            transaction.setReceiver(participant1);

            transactionRepository.save(transaction);

            System.out.println("ТРАНЗАКЦИЯ: " + transaction);
            System.out.println("\n КОНЕЦ ТЕСТРИОВАНИЯ \n");
        };
    }
}