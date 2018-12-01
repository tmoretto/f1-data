package com.f1data.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.f1data.adapter.F1DataLoader;
import com.f1data.domain.RaceTable;
import com.f1data.service.RaceTableService;

@Component
public class DataSchedule {

	private static final Logger logger = LoggerFactory.getLogger(DataSchedule.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	private F1DataLoader loader;
	
	@Autowired
	private RaceTableService raceService;
	
	@Scheduled(cron = "0 */5 * * * *")
	public void importData() {
		logger.info("Loading F1 Data :: Started at: {}", dateTimeFormatter.format(LocalDateTime.now()));
		
		try {
			logger.info("Loading F1 Data :: Looking for new Data ...");
			RaceTable race = loader.loadRaceTableData();
			logger.info("Loading F1 Data :: RaceTable found: {}", race);
			
			logger.info("Loading F1 Data :: Saving new Data ... ");
			raceService.saveNewRoundIfApplicable(race);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		logger.info("Loading F1 Data :: Finished at: {}", dateTimeFormatter.format(LocalDateTime.now()));
		
	}
	
	//Todo dia 1:00
	//0 1 1/1 * *
}
