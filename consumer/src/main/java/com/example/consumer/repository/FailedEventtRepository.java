package com.example.consumer.repository;

import com.example.consumer.domain.FailedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedEventtRepository extends JpaRepository<FailedEvent, Long> {
}
