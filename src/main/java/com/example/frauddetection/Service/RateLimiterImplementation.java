package com.example.frauddetection.Service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterImplementation implements RateLimiterService{
   private final Map<String, List<Long>> requestMap = new ConcurrentHashMap<>();
    private final int maximumRequest = 5;
    private final long timeFrame = 60 * 1000;
    @Override
    public boolean isRateLimited(String ipAddress) {
        long currentTime = System.currentTimeMillis();
        requestMap.putIfAbsent(ipAddress, Collections.synchronizedList(new ArrayList<>()));
        List<Long> requests = requestMap.get(ipAddress);
        requests.removeIf(time -> (currentTime - time) > timeFrame);
        if (requests.size() > maximumRequest){
            return true;
        }
        requests.add(currentTime);
        return false;
    }
}
