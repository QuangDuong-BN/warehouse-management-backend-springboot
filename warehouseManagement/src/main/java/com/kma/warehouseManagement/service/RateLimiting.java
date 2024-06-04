package com.kma.warehouseManagement.service;

import com.kma.warehouseManagement.exception.AccessDeniedException;
import org.springframework.stereotype.Service;
import com.google.common.util.concurrent.RateLimiter;
@Service
public class RateLimiting {
    private final RateLimiter rateLimiter = RateLimiter.create(1000); // 1000 requests per second

    public void processRequest() {
        if (rateLimiter.tryAcquire()) {
            // Xử lý yêu cầu
        } else {
            throw new AccessDeniedException("Request denied");
        }
    }
}
