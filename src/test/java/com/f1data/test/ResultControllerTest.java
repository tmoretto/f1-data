package com.f1data.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.f1data.domain.Constructor;
import com.f1data.domain.Driver;
import com.f1data.domain.FastestLap;
import com.f1data.domain.Result;
import com.f1data.service.ResultService;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
	private ResultService service;

	private List<Result> results;

	private Result result;

    @Test
    public void badRequest() throws Exception {
        this.mockMvc
                .perform(get("/result"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void findResultsBySeasonAndRound() throws Exception {
    		initializeResults();
    		Mockito.when(service.findResultsBySeasonAndRound(Mockito.anyInt(), Mockito.anyInt())).thenReturn(results);
        this.mockMvc
                .perform(get("/result").param("season", "2018").param("round", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findResultBySeasonAndRoundAndDriver() throws Exception {
		initializeResults();
		Mockito.when(service.findResultBySeasonAndRoundAndDriver(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).thenReturn(results.get(0));
        this.mockMvc
                .perform(get("/result/drivers/ti_moretto").param("season", "2018").param("round", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    
    @Test
    public void addDriverResult() throws Exception {
    		initializeResult();
    		Mockito.when(service.addDriverResult(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Result.class))).thenReturn(result);
        String newResult = "{\n" + 
        		"                    \"number\" : 44,\n" + 
        		"                    \"position\" : 10,\n" + 
        		"                    \"points\" : 1,\n" + 
        		"                    \"grid\" : 1,\n" + 
        		"                    \"laps\" : 58,\n" + 
        		"                    \"status\" : \"Finished\",\n" + 
        		"                    \"fastestLap\" : {\n" + 
        		"                        \"rank\" : \"5\",\n" + 
        		"                        \"lap\" : \"53\"\n" + 
        		"                    },\n" + 
        		"                    \"driver\" : {\n" + 
        		"                        \"driverId\" : \"hamilton\",\n" + 
        		"                        \"permanentNumber\" : 44,\n" + 
        		"                        \"code\" : \"HAM\",\n" + 
        		"                        \"givenName\" : \"\",\n" + 
        		"                        \"familyName\" : \"Hamilton\",\n" + 
        		"                        \"dateOfBirth\" : \"1985-01-07\",\n" + 
        		"                        \"nationality\" : \"British\"\n" + 
        		"                    },\n" + 
        		"                    \"constructor\" : {\n" + 
        		"                        \"constructorId\" : \"mercedes\",\n" + 
        		"                        \"name\" : \"Mercedes\",\n" + 
        		"                        \"nationality\" : \"German\"\n" + 
        		"                    }\n" + 
        		"                }";
        
		this.mockMvc
                .perform(post("/result").contentType(
                        MediaType.APPLICATION_JSON).content(newResult).param("season", "2018").param("round", "1"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateResult() throws Exception {
		initializeResult();
		Mockito.when(service.addDriverResult(Mockito.anyInt(), Mockito.anyInt(), Mockito.any(Result.class))).thenReturn(result);
        String newResult = "{\n" + 
	        		"                    \"number\" : 44,\n" + 
	        		"                    \"position\" : 2,\n" + 
	        		"                    \"points\" : 18,\n" + 
	        		"                    \"grid\" : 1,\n" + 
	        		"                    \"laps\" : 58,\n" + 
	        		"                    \"status\" : \"Finished\",\n" + 
	        		"                    \"fastestLap\" : {\n" + 
	        		"                        \"rank\" : \"5\",\n" + 
	        		"                        \"lap\" : \"53\"\n" + 
	        		"                    },\n" + 
	        		"                    \"driver\" : {\n" + 
	        		"                        \"driverId\" : \"hamilton\",\n" + 
	        		"                        \"permanentNumber\" : 44,\n" + 
	        		"                        \"code\" : \"HAM\",\n" + 
	        		"                        \"givenName\" : \"\",\n" + 
	        		"                        \"familyName\" : \"Hamilton\",\n" + 
	        		"                        \"dateOfBirth\" : \"1985-01-07\",\n" + 
	        		"                        \"nationality\" : \"British\"\n" + 
	        		"                    },\n" + 
	        		"                    \"constructor\" : {\n" + 
	        		"                        \"constructorId\" : \"mercedes\",\n" + 
	        		"                        \"name\" : \"Mercedes\",\n" + 
	        		"                        \"nationality\" : \"German\"\n" + 
	        		"                    }\n" + 
	        		"                }";
    		this.mockMvc
            .perform(put("/result/drivers/hamilton").contentType(
                    MediaType.APPLICATION_JSON).content(newResult).param("season", "2018").param("round", "1"))
            .andDo(print())
            .andExpect(status().isNoContent());
    }
    
    @Test
    public void removeRaceTable() throws Exception {
    		Mockito.doNothing().when(service).removeDriverResult(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        this.mockMvc
	        .perform(delete("/result/drivers/hamilton").param("season", "2018").param("round", "1"))
	        .andDo(print())
	        .andExpect(status().isNoContent());
    }

	private List<Result> initializeResults() {
		results = new ArrayList<>();
		Result result = new Result();
		Constructor constructor = new Constructor();
		constructor.setConstructorId("gurgel");
		constructor.setName("Gurgel Motores");
		constructor.setNationality("Brazilian");
		result.setConstructor(constructor);
		Driver driver = new Driver();
		driver.setCode("TML");
		driver.setDateOfBirth("1986-08-05");
		driver.setDriverId("ti_moretto");
		driver.setFamilyName("Moretto");
		driver.setGivenName("Tiago");
		driver.setNationality("Brazilian");
		driver.setPermanentNumber(22);
		result.setDriver(driver);
		FastestLap fastestLap = new FastestLap();
		fastestLap.setLap("1");
		fastestLap.setRank("1");
		result.setFastestLap(fastestLap);
		result.setGrid(1);
		result.setLaps(58);
		result.setNumber(22);
		result.setPoints(25);
		result.setPosition(1);
		result.setStatus("Finished");
		results.add(result);
		return results;
	}
	
	private void initializeResult() {
		result = new Result();
		Constructor constructor = new Constructor();
		constructor.setConstructorId("mercedes");
		constructor.setName("Mercedes");
		constructor.setNationality("German");
		result.setConstructor(constructor);
		Driver driver = new Driver();
		driver.setCode("HAM");
		driver.setDateOfBirth("1985-01-07");
		driver.setDriverId("fulano");
		driver.setFamilyName("Hamilton");
		driver.setGivenName("Lewis");
		driver.setNationality("British");
		driver.setPermanentNumber(44);
		result.setDriver(driver);
		FastestLap fastestLap = new FastestLap();
		fastestLap.setLap("5");
		fastestLap.setRank("53");
		result.setFastestLap(fastestLap);
		result.setGrid(1);
		result.setLaps(58);
		result.setNumber(44);
		result.setPoints(1);
		result.setPosition(10);
		result.setStatus("Finished");
	}
	
}
