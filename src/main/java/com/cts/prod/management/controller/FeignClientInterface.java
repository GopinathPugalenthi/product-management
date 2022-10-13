package com.cts.prod.management.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.prod.management.model.AuditDetail;

@FeignClient(name = "feign-client-interface", url = "http://localhost:8082/mongoDbJob")
public interface FeignClientInterface {

	
	@RequestMapping(value="/auditDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody AuditDetail auditDetail);
}
