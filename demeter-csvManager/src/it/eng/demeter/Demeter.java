package it.eng.demeter;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.eng.demeter.rest.ManageCSV;

@Path("/")
public class Demeter {
	@GET
	@Path("/traslator/v1/AnimalWelfareTraining")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CSVtoAnimalWelfareTrainingJSONLD() {
		String jsonArray = "";
		try { 
			jsonArray = ManageCSV.getInstance().convertCSVtoAnimalWelfareTrainingJSONLD();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray, MediaType.APPLICATION_JSON).build();
	}
	@GET
	@Path("/traslator/v1/AnimalWelfarePrediction")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CSVtoAnimalWelfarePredictionJSONLD() {
		String jsonArray = "";
		try { 
			jsonArray = ManageCSV.getInstance().convertCSVtoAnimalWelfarePredictionJSONLD();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray, MediaType.APPLICATION_JSON).build();
	}
	@GET
	@Path("/traslator/v1/MilkQualityTraining")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CSVtoMilkQualityTrainingJSONLD() {
		String jsonArray = "";
		try { 
			jsonArray = ManageCSV.getInstance().convertCSVtoMilkQualityTrainingJSONLD();
		} catch (JsonProcessingException e1) {	
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray, MediaType.APPLICATION_JSON).build();
	}
	@GET
	@Path("/traslator/v1/MilkQualityPrediction")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response CSVtoMilkQualityPredictionJSONLD() {
		String jsonArray = "";
		try { 
			jsonArray = ManageCSV.getInstance().convertCSVtoMilkQualityPredictionJSONLD();
		} catch (JsonProcessingException e1) {	
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray, MediaType.APPLICATION_JSON).build();
	}
	@POST
	@Path("/traslator/v1/AnimalWelfareTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public Response CSVtoAnimalWelfareTrainingJSONLD(String csvRFText) {	
		String jsonArray = "";
		try { 
			jsonArray = ManageCSV.getInstance().convertCSVtoAnimalWelfareTrainingJSONLD(csvRFText);
		} catch (JsonProcessingException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray, MediaType.APPLICATION_JSON).build();
	}
	@POST
	@Path("/traslator/v1/AnimalWelfarePrediction")
	@Produces(MediaType.APPLICATION_JSON)
	public Response CSVtoAnimalWelfarePredictionJSONLD(String csvRFText) {	
		String jsonArray = "";
		try { 
			jsonArray = ManageCSV.getInstance().convertCSVtoAnimalWelfarePredictionJSONLD(csvRFText);
		} catch (JsonProcessingException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray, MediaType.APPLICATION_JSON).build();
	}
	@POST
	@Path("/traslator/v1/MilkQualityTraining")
	@Produces(MediaType.APPLICATION_JSON)
	public Response CSVtoMilkQualityTrainingJSONLD(String csvRFText) {	
		String jsonArray = "";
		try { 
			jsonArray = ManageCSV.getInstance().convertCSVtoMilkQualityTrainingJSONLD(csvRFText);
		} catch (JsonProcessingException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray, MediaType.APPLICATION_JSON).build();
	}
	@POST
	@Path("/traslator/v1/MilkQualityPrediction")
	@Produces(MediaType.APPLICATION_JSON)
	public Response CSVtoMilkQualityPredictionJSONLD(String csvRFText) {	
		String jsonArray = "";
		try { 
			jsonArray = ManageCSV.getInstance().convertCSVtoMilkQualityPredictionJSONLD(csvRFText);
		} catch (JsonProcessingException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(jsonArray, MediaType.APPLICATION_JSON).build();
	}
	@GET
	@Path("/getCSV/v1/GetMilkAnalysisCSV")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMilkAnalysisCSV() {
		File csv = null;
		try { 
			csv = ManageCSV.getInstance().getMilkAnalysisCSV();
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(csv, MediaType.APPLICATION_JSON).build();
	}
}
