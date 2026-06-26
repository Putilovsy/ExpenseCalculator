package com.epxense;

import com.epxense.model.Event;
import com.epxense.model.Participant;
import com.epxense.repository.EventRepository;

import com.epxense.repository.ParticipantRepository;
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
    public CommandLineRunner testDatabase(EventRepository eventRepository, ParticipantRepository participantRepository) {
        return args -> {
            System.out.println("Начало теста\n");
            Event newEvent = new Event();
            newEvent.setTitle("ТЕСТОВОЕ МЕРОПРИЯТИЕ");
            eventRepository.save(newEvent);

            System.out.println("Успешно сохранено:" + newEvent);

            Long savedId = newEvent.getId();

            System.out.println("\nЗапрос к базе данных \n");
            Optional<Event> fetchedEvent = eventRepository.findById(savedId);
            System.out.println("Найдено в БД" + fetchedEvent.orElse(null));

            System.out.println("ТЕСТОВЫЙ ПОЛЬЗОВАТЕЛЬ");

            Participant participant = new Participant();

            participant.setName("ТЕСТОВЫЙ ПОЛЬЗОВАТЕЛЬ");

            participant.setHeadcount(2);
            participant.setEvent(newEvent);
            participantRepository.save(participant);

            System.out.println("УСПЕШНО СОХРАНЕН ПОЛЬЗОВАТЕЛЬ: " + participant);
        };
    }
}