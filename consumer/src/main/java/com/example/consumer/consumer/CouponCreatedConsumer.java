package com.example.consumer.consumer;

import com.example.consumer.domain.Coupon;
import com.example.consumer.domain.FailedEvent;
import com.example.consumer.repository.CouponRepository;
import com.example.consumer.repository.FailedEventtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreatedConsumer {

    private final CouponRepository couponRepository;

    private final FailedEventtRepository failedEventtRepository;

    private final Logger logger = LoggerFactory.getLogger(CouponCreatedConsumer.class);

    public CouponCreatedConsumer(final CouponRepository couponRepository, final FailedEventtRepository failedEventtRepository) {
        this.couponRepository = couponRepository;
        this.failedEventtRepository = failedEventtRepository;
    }

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(final Long userId) {
        try {
            couponRepository.save(new Coupon(userId));
        } catch (Exception e) {
            logger.error("failed to create coupon::" + userId);
            failedEventtRepository.save(new FailedEvent(userId));
        }
    }
}
