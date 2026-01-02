package com.code.duel.code.duel.Service;

import com.code.duel.code.duel.WebSocketConfiguration.WebSocketEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class PendingService {

    private final Map<Long , Long> pendingDisconnections = new ConcurrentHashMap<Long , Long>();
    private final long maxWaiting = 5000; // 5 seconds max

    private static final Logger logger = LoggerFactory.getLogger(PendingService.class);


    public void markAsPending(Long userId){

        pendingDisconnections.put(userId , System.currentTimeMillis() + maxWaiting);
        logger.info("User {} Marked as Pending with timeout {}" , userId  , System.currentTimeMillis() + maxWaiting);
    }

    public void removeIfPending(Long userId){
        logger.info("User {} removed from Pending" , userId );

        pendingDisconnections.remove(userId);
    }

    public Set<Long> getExpiredUsers(){
        long now = System.currentTimeMillis();
        return pendingDisconnections.entrySet().stream()
                .filter(entry -> now > entry.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
