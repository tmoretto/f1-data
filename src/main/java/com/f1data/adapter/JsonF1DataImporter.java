package com.f1data.adapter;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.f1data.domain.RaceTable;
import com.f1data.domain.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonF1DataImporter implements F1DataImporter {

	private final String url = "https://ergast.com/api/f1/current/last/results.json";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public RaceTable importRaceTable() {
		ResponseEntity<Map<String, Object>> dataResponse = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<Map<String, Object>>() {
				});

		Map<String, Object> data = dataResponse.getBody();

		ObjectMapper mapper = new ObjectMapper();
		Response raceTable = mapper.convertValue(data, Response.class);
		System.out.println(raceTable);
		return raceTable.getMrData().getRaceTable();
	}

}
