package com.epxense.service;

import com.epxense.model.Event;
import com.epxense.model.Participant;
import com.epxense.model.Purchase;
import com.epxense.repository.EventRepository;
import com.epxense.repository.ParticipantRepository;
import com.epxense.repository.PurchaseRepository;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseCalculatorService
{
    private final PurchaseRepository purchaseRepository;
    private final ParticipantRepository participantRepository;

    public ExpenseCalculatorService(PurchaseRepository purchaseRepository,
                                    ParticipantRepository participantRepository) {

        this.purchaseRepository = purchaseRepository;
        this.participantRepository = participantRepository;

    }

    public Map<Participant, BigDecimal> calculateRawBalance(Event event) {
        List<Participant> participants = participantRepository.findByEvent(event);
        List<Purchase> purchases = purchaseRepository.findByEvent(event);

        Map<Participant, BigDecimal> balances = new HashMap<>();

        for (Participant p: participants) {
            balances.put(p, BigDecimal.ZERO);
        }

        for (Purchase p: purchases) {
            Participant buyer = p.getBuyer();
            balances.computeIfPresent(buyer, (k, currentBuyerBalance) -> currentBuyerBalance.add(p.getAmount()));

            List<Participant> consumers = p.getConsumers();

            int purchaseHeadcount = 0;
            for (Participant consumer: consumers) {
                purchaseHeadcount += consumer.getHeadcount();
            }

            if (purchaseHeadcount > 0) {
                BigDecimal costPerHead = p.getAmount().divide(
                        BigDecimal.valueOf(purchaseHeadcount), 2, RoundingMode.HALF_UP);

                for (Participant consumer: consumers) {
                    BigDecimal currentConsumerBalance = balances.get(consumer);
                    BigDecimal personalDebt = costPerHead.multiply(BigDecimal.valueOf(consumer.getHeadcount()));

                    balances.put(consumer, currentConsumerBalance.subtract(personalDebt));
                }
            }
        }
        return balances;
    }
}
