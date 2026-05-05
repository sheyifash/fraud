package com.example.frauddetection.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public interface RateLimiterService {

    public boolean isRateLimited(String ipAddress);
}
