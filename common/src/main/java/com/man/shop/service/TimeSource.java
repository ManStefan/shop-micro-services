package com.man.shop.service;

import java.time.LocalDateTime;

/**
 * Created by smanolache on 4/8/2017.
 */
public interface TimeSource {

    LocalDateTime now();
}
