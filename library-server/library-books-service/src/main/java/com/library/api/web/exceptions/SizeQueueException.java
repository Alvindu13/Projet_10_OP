package com.library.api.web.exceptions;

import java.util.Set;

public class SizeQueueException extends RuntimeException {

    public SizeQueueException(int size) {
        super("Queue Size is too big : " + size);
    }



}
