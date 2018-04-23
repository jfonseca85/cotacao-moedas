package com.shangrila.microservices.currency.conversion.service.batch;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
//@Slf4j
public class DuplicateRecordSkipListener implements SkipPolicy{


    @Override
    public boolean shouldSkip(Throwable throwable, int i) throws SkipLimitExceededException {
        if(throwable instanceof DataIntegrityViolationException){
            DataIntegrityViolationException dive = (DataIntegrityViolationException) throwable;
            System.out.println("An Error Occured while processing the data " +  dive.getMessage());
            return true;
        } else
            return false;
    }
}