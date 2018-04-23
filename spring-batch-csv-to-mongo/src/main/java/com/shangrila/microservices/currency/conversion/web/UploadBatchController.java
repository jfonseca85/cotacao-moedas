package com.shangrila.microservices.currency.conversion.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.shangrila.microservices.currency.conversion.service.batch.BatchJobManagerService;
import com.shangrila.microservices.currency.conversion.util.Constantes;


@RestController
public class UploadBatchController {

	@Autowired
	@Resource(name = "valueSetJobManager")
	private BatchJobManagerService valueSetJobManagerService;

	@RequestMapping(value = "/valueSet/batchUpload/{quotation}", method = RequestMethod.GET)
	public void batchValueSetUpload(@PathVariable String quotation) throws Exception{
		
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(Constantes.BASE_URL_DATA_SOURCE_COTACAO.replace("{stringDateBussinesUpdate}", quotation), String.class);
		System.out.println(response);
//		valueSetJobManagerService.batchUpload("20180420.csv");
		valueSetJobManagerService.batchUpload(response);
	}
	

}
