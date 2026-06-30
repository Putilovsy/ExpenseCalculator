package com.epxense.repository;


import com.epxense.model.Event;
import com.epxense.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>
{
    List<Purchase> findByEvent(Event event);
}
