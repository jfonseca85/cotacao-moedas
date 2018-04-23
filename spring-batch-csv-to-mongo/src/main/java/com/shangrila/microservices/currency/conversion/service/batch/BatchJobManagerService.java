package com.shangrila.microservices.currency.conversion.service.batch;

public interface BatchJobManagerService {

    /**
     * Value set batch upload.
     * @param uploadRequestDto
     * @return the value set dto
     * @throws Exception
     *             the exception
     */
    public void batchUpload(String uploadRequestDto) throws Exception;


}