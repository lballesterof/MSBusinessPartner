package com.TemplateNTT.infraestructure.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TemplateNTT.applicationHelpers.GenerateBusinessPartnerCode;
import com.TemplateNTT.domain.Entity.BusinessPartner;
import com.TemplateNTT.domain.Repository.BusinessPartnerRepository;
import com.TemplateNTT.infraestructure.Intefaces.IBusinessPartnerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessPartnerService implements IBusinessPartnerService {

	@Autowired(required = true)
	private BusinessPartnerRepository repository;

	@Override
	public Flux<BusinessPartner> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<BusinessPartner> save(BusinessPartner _request) {
	    BusinessPartner a = 
		BusinessPartner.builder().codeBP(GenerateBusinessPartnerCode.Generate(_request.getDocNum(), _request.getType()))
				   .contactPerson(_request.getContactPerson())
				   .creditCard(_request.getCreditCard())
				   .creditCardLine(_request.getCreditCardLine())
				   .creditLine(_request.getCreditLine())
				   .debitCard(_request.getDebitCard())
				   .debitLine(_request.getDebitLine())
				   .docNum(_request.getDocNum())
				   .docType(_request.getDocType())
				   .email(_request.getEmail())
				   .name(_request.getName())
				   .telephone1(_request.getTelephone1())
				   .telephone2(_request.getTelephone2())
				   .type(_request.getType())
				   .valid(true)
				   .build();
		
		
		return repository.save(a);

	}

	@Override
	public Mono<BusinessPartner> delete(String Id) {
		return repository.findById(Id).flatMap(deleted -> repository.delete(deleted).then(Mono.just(deleted)));
	}

	@Override
	public Mono<BusinessPartner> findById(String Id) {
		return repository.findById(Id);
	}

	public Mono<ResponseEntity<BusinessPartner>> update(String id, BusinessPartner _request) {
		return repository.findById(id).flatMap(a -> {
			
			BusinessPartner.builder()
					   .contactPerson(_request.getContactPerson())
					   .creditCard(_request.getCreditCard())
					   .creditCardLine(_request.getCreditCardLine())
					   .creditLine(_request.getCreditLine())
					   .debitCard(_request.getDebitCard())
					   .debitLine(_request.getDebitLine())
					   .docNum(_request.getDocNum())
					   .docType(_request.getDocType())
					   .email(_request.getEmail())
					   .name(_request.getName())
					   .telephone1(_request.getTelephone1())
					   .telephone2(_request.getTelephone2())
					   .type(_request.getType())
					   .valid(_request.getValid())
					   .build();
							
			return repository.save(a);
		}).map(updated -> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.OK));
	}
}