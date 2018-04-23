package com.shangrila.microservices.currency.conversion.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.shangrila.microservices.currency.conversion.model.CotacaoMoeda;

@EnableBatchProcessing
@Configuration
public class ValuesetBatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Bean(name = "valueSetJob")
	public Job readCSVFile() {
		return jobBuilderFactory.get("readCSVFile").incrementer(new RunIdIncrementer()).start(step1()).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<CotacaoMoeda, CotacaoMoeda>chunk(10).reader(reader()).writer(writer())
				.build();
	}

	@Bean
	public FlatFileItemReader<CotacaoMoeda> reader() {
		FlatFileItemReader<CotacaoMoeda> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("20180420.csv"));
		reader.setLineMapper(new DefaultLineMapper<CotacaoMoeda>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{	
						setDelimiter(";");
						setNames(new String[] { "id.data", "codMoeda", "tipo", "id.siglaMoeda", "taxaCompra",
								"TaxaVenda", "paridadeCompra", "paridadeVenda" });

					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<CotacaoMoeda>() {
					{
						setTargetType(CotacaoMoeda.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public MongoItemWriter<CotacaoMoeda> writer() {
		MongoItemWriter<CotacaoMoeda> writer = new MongoItemWriter<CotacaoMoeda>();
		writer.setTemplate(mongoTemplate);
		writer.setCollection("CotacaoMoeda");
		return writer;
	}

	// end::jobstep[]

}
