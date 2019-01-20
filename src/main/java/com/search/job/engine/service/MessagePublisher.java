package com.search.job.engine.service;

public interface MessagePublisher {
    void publish(final String message);
}