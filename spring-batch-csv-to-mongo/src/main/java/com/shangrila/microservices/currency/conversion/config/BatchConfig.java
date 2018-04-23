package com.shangrila.microservices.currency.conversion.config;

import java.io.ByteArrayInputStream;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.shangrila.microservices.currency.conversion.model.CotacaoMoeda;
import com.shangrila.microservices.currency.conversion.service.batch.MultiResourceItemReaderDataFileReader;
import com.shangrila.microservices.currency.conversion.service.batch.ValueSetDataFileReader;

public abstract class BatchConfig {
		
	@Autowired
	private MongoTemplate mongoTemplate;
	
    // tag::readerwriterprocessor[]
	protected abstract Job valueSetJob();
	protected abstract Step valueSetStep();
	
	@Bean
	@StepScope
	protected FlatFileItemReader<CotacaoMoeda> reader() {

		// To read lines from an input file
		FlatFileItemReader<CotacaoMoeda> csvFilereader = new ValueSetDataFileReader();
		csvFilereader.setResource(new InputStreamResource(new ByteArrayInputStream(new byte[0])));
		csvFilereader.setLineMapper(CotacaomoedasLineMapper());
		return csvFilereader;

	}

	@Bean
	@StepScope
	protected MultiResourceItemReader<CotacaoMoeda> multiResourceItemReader() {

		// To read lines from an input file
		MultiResourceItemReader<CotacaoMoeda> csvFilereader = new MultiResourceItemReaderDataFileReader();
		csvFilereader.setResources(new Resource[0]);
		csvFilereader.setDelegate(reader());
		return csvFilereader;

	}

	@Bean
	protected MongoItemWriter<CotacaoMoeda> writer() {
		MongoItemWriter<CotacaoMoeda> writer = new MongoItemWriter<CotacaoMoeda>();
		writer.setTemplate(mongoTemplate);
		writer.setCollection("CotacaoMoeda");
		return writer;
	}

	protected LineMapper<CotacaoMoeda> CotacaomoedasLineMapper() {
		DefaultLineMapper<CotacaoMoeda> valueSetLineMapper = new DefaultLineMapper<>();
		valueSetLineMapper.setLineTokenizer(createValueSetLineTokenizer());
		valueSetLineMapper.setFieldSetMapper(valueSetFieldSetMapper());
		return valueSetLineMapper;
	}

	protected LineTokenizer createValueSetLineTokenizer() {
		DelimitedLineTokenizer valueSetLineTokenizer = new DelimitedLineTokenizer();
		valueSetLineTokenizer.setDelimiter(";");
		valueSetLineTokenizer.setNames(new String[] { "id.data", "codMoeda", "tipo", "id.siglaMoeda", "taxaCompra",
				"TaxaVenda", "paridadeCompra", "paridadeVenda" });
		return valueSetLineTokenizer;
	}

	protected FieldSetMapper<CotacaoMoeda> valueSetFieldSetMapper() {
		BeanWrapperFieldSetMapper<CotacaoMoeda> valueSetFieldMapper = new BeanWrapperFieldSetMapper<>();
		valueSetFieldMapper.setTargetType(CotacaoMoeda.class);
		return valueSetFieldMapper;
	}

	// end::readerwriterprocessor[]

}
