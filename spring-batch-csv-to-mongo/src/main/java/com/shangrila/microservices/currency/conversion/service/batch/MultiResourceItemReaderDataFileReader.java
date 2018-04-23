package com.shangrila.microservices.currency.conversion.service.batch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.shangrila.microservices.currency.conversion.model.CotacaoMoeda;
import com.shangrila.microservices.currency.conversion.util.LocalDateUtil;

@StepScope
public class MultiResourceItemReaderDataFileReader extends MultiResourceItemReader<CotacaoMoeda> {
	@Autowired
	private ResourceLoader resourceLoader;

	@Value("#{jobParameters['valueSetCsvdataMultiResource']}")
	public void setValueSetCsvdata(Long valueSetCsvdataMultiResource) {
		if (valueSetCsvdataMultiResource != null) {
			Resource[] listResource = new Resource[valueSetCsvdataMultiResource.intValue()];
			LocalDate localDateTime = LocalDate.now();
			for (int i = 0; i < valueSetCsvdataMultiResource; i++) {
				int diasSubtrair = LocalDateUtil.fimDeSemana(localDateTime);
				LocalDate diaDaSemana = localDateTime.minusDays(diasSubtrair);
				if (LocalDateUtil.isDomingo(diaDaSemana))
					diaDaSemana = diaDaSemana.minusDays(2);
				String dateTime = diaDaSemana.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
				String path = "url:http://www4.bcb.gov.br/Download/fechamento/" + dateTime + ".csv";
				Resource resource = resourceLoader.getResource(path);
				// Resource resource =
				// resourceLoader.getResource("url:http://www4.bcb.gov.br/Download/fechamento/"+dateTime+".csv");
				listResource[i] = resource;
				System.out.println(">>>>>>>>>>url:http://www4.bcb.gov.br/Download/fechamento/" + dateTime + ".csv");
				localDateTime = diaDaSemana;
			}
			setResources(listResource);
		}
	}

}
