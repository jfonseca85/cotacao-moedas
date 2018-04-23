package com.shangrila.microservices.currency.conversion.service.batch;

import java.io.ByteArrayInputStream;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;

import com.shangrila.microservices.currency.conversion.model.CotacaoMoeda;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@StepScope
public class ValueSetDataFileReader extends FlatFileItemReader<CotacaoMoeda> {

    @Value("#{jobParameters['valueSetCsvdata']}")
    public void setValueSetCsvdata(String valueSetCsvdata) {
        if(null != valueSetCsvdata) {
            ByteArrayInputStream bais = new ByteArrayInputStream(valueSetCsvdata.getBytes());
            InputStreamResource resource = new InputStreamResource(bais);
            setResource(resource);
        }
    }
}