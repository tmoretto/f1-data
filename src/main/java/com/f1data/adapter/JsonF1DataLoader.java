package com.f1data.adapter;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.f1data.domain.RaceTable;
import com.f1data.domain.RaceTableResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonF1DataLoader implements F1DataLoader {

	private final String url = "https://ergast.com/api/f1/current/last/results.json";
	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public RaceTable loadRaceTableData() throws Exception {
		try {
			ResponseEntity<Map<String, Object>> dataResponse = restTemplate.exchange(
					url, 
					HttpMethod.GET, 
					null,
					new ParameterizedTypeReference<Map<String, Object>>() {});

			RaceTableResponse lastRaceTableResponse = new ObjectMapper().convertValue(dataResponse.getBody(), RaceTableResponse.class);
			
			return lastRaceTableResponse.getMrData().getRaceTable();
		} catch (Exception e) {
			throw new Exception("Loading jsonF1 error ... ", e);
		}
	}

}
