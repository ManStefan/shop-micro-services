package com.man.shop.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by smanolache on 4/8/2017.
 */
@Service
public class RealTimeSource implements TimeSource {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
