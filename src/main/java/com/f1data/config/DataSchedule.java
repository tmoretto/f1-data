package com.f1data.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.f1data.adapter.F1DataImporter;
import com.f1data.domain.RaceTable;

@Component
public class DataSchedule {

	private static final Logger logger = LoggerFactory.getLogger(DataSchedule.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	private F1DataImporter importer;
	
	@Scheduled(cron = "*/1 */1 * * * *")
	public void importDataTest() {
		logger.info("ron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		RaceTable race = importer.importRaceTable();
		System.out.println(race);
	}
	
	//Todo dia 1:00
	//0 1 1/1 * *
}
