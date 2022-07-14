package it.eng.demeter.rest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class ManageCSV {
	
	private static Log log = LogFactory.getLog(ManageCSV.class);
	
	private static ResourceBundle properties = ResourceBundle.getBundle("resources/configuration");
	private static String csvFolderPath = properties.getString("demeter.csv-folder-path");
	private static String separator = properties.getString("demeter.csv-separator");
	
	private static String agriContext = properties.getString("demeter.agriContext");
	private static String livestockFeatureContext = properties.getString("demeter.livestockFeatureContext");
	
	private static String livestockFeatureURL = properties.getString("demeter.livestockFeatureURL");
	private static String foodieURL = properties.getString("demeter.foodieURL");
	
	private static String animal = properties.getString("demeter.animal");
	private static String pedometer = properties.getString("demeter.pedometer");
	
	private static String animalWelfareTrainingPrefixFileName = properties.getString("demeter.animalWelfareTrainingPrefixFileName");
	private static String animalWelfarePredictionPrefixFileName = properties.getString("demeter.animalWelfarePredictionPrefixFileName");
	private static String milkQualityTrainingPrefixFileName = properties.getString("demeter.milkQualityTrainingPrefixFileName");
	private static String milkQualityPredictionPrefixFileName = properties.getString("demeter.milkQualityPredictionPrefixFileName");
	private static String milkAnalysisPrefixFileName = properties.getString("demeter.milkAnalysisPrefixFileName");
	
	private static String date = properties.getString("demeter.date");
	
	private static String activity = properties.getString("demeter.activity");
	private static String activity1 = properties.getString("demeter.activity1");
	private static String activity2 = properties.getString("demeter.activity2");
	private static String activity3 = properties.getString("demeter.activity3");
	
	private static String totalDailyLying = properties.getString("demeter.totalDailyLying");
	private static String averageDailyProduction = properties.getString("demeter.averageDailyProduction");
	private static String dailyProduction = properties.getString("demeter.dailyProduction");
	private static String fat = properties.getString("demeter.fat");
	private static String protein = properties.getString("demeter.protein");
	private static String dailyFat = properties.getString("demeter.dailyFat");
	private static String dailyProteins = properties.getString("demeter.dailyProteins");
	
	private static String conductivity = properties.getString("demeter.conductivity");
	private static String conductivity1 = properties.getString("demeter.conductivity1");
	private static String conductivity2 = properties.getString("demeter.conductivity2");
	private static String conductivity3 = properties.getString("demeter.conductivity3");
	
	private static String averageRuminationTime  = properties.getString("demeter.averageRuminationTime");
	private static String averageRuminationTime1 = properties.getString("demeter.averageRuminationTime1");
	private static String averageRuminationTime2 = properties.getString("demeter.averageRuminationTime2");
	private static String averageRuminationTime3 = properties.getString("demeter.averageRuminationTime3");
	
	private static String averageIngestionTime  = properties.getString("demeter.averageIngestionTime");
	private static String averageIngestionTime1 = properties.getString("demeter.averageIngestionTime1");
	private static String averageIngestionTime2 = properties.getString("demeter.averageIngestionTime2");
	private static String averageIngestionTime3 = properties.getString("demeter.averageIngestionTime3");
	
	private static String actualLameness = properties.getString("demeter.actualLameness");
	private static String predictedLameness = properties.getString("demeter.predictedLameness");
	private static String actualMastitis = properties.getString("demeter.actualMastitis");
	private static String predictedMastitis = properties.getString("demeter.predictedMastitis");
	private static String actualKetosis = properties.getString("demeter.actualKetosis");
	private static String predictedKetosis = properties.getString("demeter.predictedKetosis");
	private static String actualHeatStress = properties.getString("demeter.actualHeatStress");
	private static String predictedHeatStress = properties.getString("demeter.predictedHeatStress");
	
	private static String actualQuality = properties.getString("demeter.actualQuality");
	private static String predictedQuality = properties.getString("demeter.predictedQuality");
	
	private static String acidity = properties.getString("demeter.acidity");
	private static String casein = properties.getString("demeter.casein");
	private static String density = properties.getString("demeter.density");
	private static String freezingPoint = properties.getString("demeter.freezingPoint");
	private static String lactose = properties.getString("demeter.lactose");
	private static String snf = properties.getString("demeter.snf");
	private static String urea = properties.getString("demeter.urea");
	
	private static String remark = properties.getString("demeter.remark");
	private static String qudtUnit = properties.getString("demeter.qudtUnit");
	private static String index = properties.getString("demeter.index");
	private static String milkingInDays = properties.getString("demeter.milkingInDays");
	private static String lactations = properties.getString("demeter.lactations");
	private static String qudtURL = properties.getString("demeter.qudtURL");
	private static String productName = properties.getString("demeter.productName");
	private static String fatProteinRatio = properties.getString("demeter.fatProteinRatio");
	private static String dailyFatProtein = properties.getString("demeter.dailyFatProtein");
	
	private static String lamenessTruePositiveRate = properties.getString("demeter.lamenessTruePositiveRate");
	private static String lamenessFalsePositiveRate = properties.getString("demeter.lamenessFalsePositiveRate");
	private static String lamenessPrecision = properties.getString("demeter.lamenessPrecision");
	private static String lamenessAccuracy = properties.getString("demeter.lamenessAccuracy");
	
	private static String mastitisTruePositiveRate = properties.getString("demeter.mastitisTruePositiveRate");
	private static String mastitisFalsePositiveRate = properties.getString("demeter.mastitisFalsePositiveRate");
	private static String mastitisPrecision = properties.getString("demeter.mastitisPrecision");
	private static String mastitisAccuracy = properties.getString("demeter.mastitisAccuracy");
	
	private static String ketosisTruePositiveRate = properties.getString("demeter.ketosisTruePositiveRate");
	private static String ketosisFalsePositiveRate = properties.getString("demeter.ketosisFalsePositiveRate");
	private static String ketosisPrecision = properties.getString("demeter.ketosisPrecision");
	private static String ketosisAccuracy = properties.getString("demeter.ketosisAccuracy");
	
	private static String heatStressTruePositiveRate = properties.getString("demeter.heatStressTruePositiveRate");
	private static String heatStressFalsePositiveRate = properties.getString("demeter.heatStressFalsePositiveRate");
	private static String heatStressPrecision = properties.getString("demeter.heatStressPrecision");
	private static String heatStressAccuracy = properties.getString("demeter.heatStressAccuracy");
	
	private static String rawTruePositiveRate = properties.getString("demeter.rawTruePositiveRate");
	private static String rawFalsePositiveRate = properties.getString("demeter.rawFalsePositiveRate");
	private static String rawPrecision = properties.getString("demeter.rawPrecision");
	private static String rawAccuracy = properties.getString("demeter.rawAccuracy");
	
	private static String processedTruePositiveRate = properties.getString("demeter.processedTruePositiveRate");
	private static String processedFalsePositiveRate = properties.getString("demeter.processedFalsePositiveRate");
	private static String processedPrecision = properties.getString("demeter.processedPrecision");
	private static String processedAccuracy = properties.getString("demeter.processedAccuracy");
	
	private static String storage = System.getenv("CSV_STORAGE");
	
	private static ManageCSV instance = null;
	private ManageCSV() {}
	public static ManageCSV getInstance() { 
	  if (instance == null) {
		  instance = new ManageCSV(); 
	  } 
	  return instance; 
	}
	
	public static String getCurrentDateTimeMS() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd_hhmmss_Ms");
        String datetime = ft.format(dNow);
        return datetime;
    }

	/**
	 * Convert CSV to Animal Welfare Training JSONLD
	 * 
	 * @return
	 */
	public String convertCSVtoAnimalWelfareTrainingJSONLD() throws Exception {
		
		JSONObject jsonld = new JSONObject();
		File csvFolder = new File(csvFolderPath + animalWelfareTrainingPrefixFileName);
        
		File csvFolderFileList = new File(csvFolder.getPath());
		File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		} catch (NullPointerException e1) {
			return "File Not Found !";
		}
        
    	BufferedReader br = null;
    	try {
            br = new BufferedReader(new FileReader(csvListOfFiles[0].getPath()));
            String line = ""; 
            boolean metrics = false;
            JSONArray contextJsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject();
        	JSONArray liveStockJsonArray = new JSONArray();
        	
        	/*Imposto i contesti*/
            contextJsonArray.put(agriContext);
            contextJsonArray.put(livestockFeatureContext);
            jsonObject.put("qudt-unit", qudtUnit);
            contextJsonArray.put(jsonObject);
            jsonld.put("@context", contextJsonArray);
            jsonObject = new JSONObject();
            
        	/*Recupero i nomi dei campi*/
       	 	String[] fieldsNames = br.readLine().split(separator);
            while ((line = br.readLine()) != null) {
	               JSONObject jsonObject1 = new JSONObject();
	     		   JSONArray  hasMemberJsonArray = new JSONArray();
	     		   JSONArray  hasResultJsonArray = new JSONArray();
        	       String[] fieldsValues = line.split(separator); 
        	       String pedometerId = "";
        	       String animalId = "";
        	       String fieldName = "";
        	       String identifier = "";
        	       String resultTime = "";
        	       String midValue = "";
        	       String lactationsValue = "";
        	       String dailyProductionValue = "";
        	       String averageDailyProductionValue = "";
        	       String fatValue = "";
        	       String proteinValue = "";
        	       String fatProteinRatioValue ="";
        	       String totalDailyLyingValue = "";
        	       String actualLamenessValue = "";
        	       String predictedLamenessValue = "";
        	       String actualKetosisValue = "";
        	       String predictedKetosisValue = "";
        	       String actualMastitisValue = "";
        	       String predictedMastitisValue = "";
        	       String actualHeatStressValue = "";
        	       String predictedHeatStressValue = "";
        	       String conductivity1Value = "";
        	       String conductivity2Value = "";
        	       String conductivity3Value = "";
        	       String activity1Value = "";
        	       String activity2Value = "";
        	       String activity3Value = "";
        	       String averageRuminationTime1Value = "";
        	       String averageRuminationTime2Value = "";
        	       String averageRuminationTime3Value = "";
        	       String averageIngestionTime1Value = "";
        	       String averageIngestionTime2Value = "";
        	       String averageIngestionTime3Value = "";
        	       String lamenessTruePositiveRateValue = "";
        	       String lamenessFalsePositiveRateValue = "";
        	       String lamenessPrecisionValue = "";
        	       String lamenessAccuracyValue = "";
        	       String mastitisTruePositiveRateValue = "";
        	       String mastitisFalsePositiveRateValue = "";
        	       String mastitisPrecisionValue = "";
        	       String mastitisAccuracyValue = "";
        	       String ketosisTruePositiveRateValue = "";
        	       String ketosisFalsePositiveRateValue = "";
        	       String ketosisPrecisionValue = "";
        	       String ketosisAccuracyValue = "";
        	       String heatStressTruePositiveRateValue = "";
        	       String heatStressFalsePositiveRateValue = "";
        	       String heatStressPrecisionValue = "";
        	       String heatStressAccuracyValue = "";
        	       
        	       /*Recupero i valori dei campi*/
	               for (int l = 0; l < fieldsValues.length; l++) { 
	        	   	   fieldName = fieldsNames[l].replace("\"", "").replace("/", "").replace(" ", "").replaceAll("[^\\p{ASCII}]", "");
		               if (fieldName.equalsIgnoreCase(pedometer)){
		            	   pedometerId = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(animal)){
		            	   animalId = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(index)){
		            	   identifier = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(date)){
		            	   resultTime = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(milkingInDays)){
		            	   midValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lactations)){
		            	   lactationsValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyProduction)){
		            	   dailyProductionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageDailyProduction)){
		            	   averageDailyProductionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyFat)){
		            	   fatValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyProteins)){
		            	   proteinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyFatProtein)){
		            	   fatProteinRatioValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(totalDailyLying)){
		            	   totalDailyLyingValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualLameness)){
		            	   actualLamenessValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedLameness)){
		            	   predictedLamenessValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualKetosis)){
		            	   actualKetosisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedKetosis)){
		            	   predictedKetosisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualMastitis)){
		            	   actualMastitisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedMastitis)){
		            	   predictedMastitisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualHeatStress)){
		            	   actualHeatStressValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedHeatStress)){
		            	   predictedHeatStressValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity1)){
		            	   conductivity1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity2)){
		            	   conductivity2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity3)){
		            	   conductivity3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity1)){
		            	   activity1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity2)){
		            	   activity2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity3)){
		            	   activity3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime1)){
		            	   averageIngestionTime1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime2)){
		            	   averageIngestionTime2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime3)){
		            	   averageIngestionTime3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime1)){
		            	   averageRuminationTime1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime2)){
		            	   averageRuminationTime2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime3)){
		            	   averageRuminationTime3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessTruePositiveRate)){
		            	   lamenessTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessFalsePositiveRate)){
		            	   lamenessFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessPrecision)){
		            	   lamenessPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessAccuracy)){
		            	   lamenessAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisTruePositiveRate)){
		            	   mastitisTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisFalsePositiveRate)){
		            	   mastitisFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisPrecision)){
		            	   mastitisPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisAccuracy)){
		            	   mastitisAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisTruePositiveRate)){
		            	   ketosisTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisFalsePositiveRate)){
		            	   ketosisFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisPrecision)){
		            	   ketosisPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisAccuracy)){
		            	   ketosisAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressTruePositiveRate)){
		            	   heatStressTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressFalsePositiveRate)){
		            	   heatStressFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressPrecision)){
		            	   heatStressPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressAccuracy)){
		            	   heatStressAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
	               } 
           	     
	               /*Metrics*/
                   if (!metrics) {
		    		   jsonObject.put("@id", "urn:demeter:predictionMetric"+identifier);
	                   jsonObject.put("@type", "PredictionMetric");
	                   jsonObject.put(lamenessTruePositiveRate, lamenessTruePositiveRateValue);
	                   jsonObject.put(lamenessFalsePositiveRate, lamenessFalsePositiveRateValue);
	                   jsonObject.put(lamenessPrecision, lamenessPrecisionValue);
	                   jsonObject.put(lamenessAccuracy, lamenessAccuracyValue);
	                   jsonObject.put(mastitisTruePositiveRate, mastitisTruePositiveRateValue);
	                   jsonObject.put(mastitisFalsePositiveRate, mastitisFalsePositiveRateValue);
	                   jsonObject.put(mastitisPrecision, mastitisPrecisionValue);
	                   jsonObject.put(mastitisAccuracy, mastitisAccuracyValue);
	                   jsonObject.put(ketosisTruePositiveRate, ketosisTruePositiveRateValue);
	                   jsonObject.put(ketosisFalsePositiveRate, ketosisFalsePositiveRateValue);
	                   jsonObject.put(ketosisPrecision, ketosisPrecisionValue);
	                   jsonObject.put(ketosisAccuracy, ketosisAccuracyValue);
	                   jsonObject.put(heatStressTruePositiveRate, heatStressTruePositiveRateValue);
	                   jsonObject.put(heatStressFalsePositiveRate, heatStressFalsePositiveRateValue);
	                   jsonObject.put(heatStressPrecision, heatStressPrecisionValue);
	                   jsonObject.put(heatStressAccuracy, heatStressAccuracyValue);
	                   
	                   liveStockJsonArray.put(jsonObject);
	                   jsonObject = new JSONObject();
                   }
                   
	    		   /*Animal*/
	    		   jsonObject.put("@id", "urn:demeter:animal"+identifier);
                   jsonObject.put("@type", "FarmAnimal");
                   jsonObject.put("description", "Animal "+identifier);
                   jsonObject.put("livestockNumber", animalId);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Collection of observations*/
                   jsonObject.put("@id", "urn:demeter:"+UUID.randomUUID());
                   jsonObject.put("@type", "HealthPrediction");
                   jsonObject.put("indentifier", identifier);
                   jsonObject.put("description", "Health Prediction collection of observations with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("hasFeatureOfInterest", "urn:demeter:animal"+identifier);
                   if (!metrics) {
	                   jsonObject.put("predictionMetric", "urn:demeter:predictionMetric"+identifier);
	                   metrics = true;
                   }
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Individual properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/1."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Individual properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/3."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/4."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/5."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/6."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/7."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/8."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/9."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/10."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/11."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/12."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/13."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/14."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/15."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/16."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/17."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("resultTime", resultTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Conductivity properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/2."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Conductivity properties collection with identifier "+identifier);
                   
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", foodieURL+conductivity);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Activity properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/3."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Activity properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+activity);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
	     		   
	     		   /*Rumination properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/4."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Rumination properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageRuminationTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
	     		   
	     		   /*Ingestion properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/5."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Ingestion properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageIngestionTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Pedometer*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", pedometerId);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+pedometer);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*MID*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", midValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+milkingInDays);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Lactations*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", lactationsValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+lactations);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Daily Production*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/4."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/4."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", dailyProductionValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+dailyProduction);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Daily Production*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/5."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/5."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageDailyProductionValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageDailyProduction);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/6."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/6."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fat);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Protein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/7."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/7."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", proteinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+protein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat Protein Ratio*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/8."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/8."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatProteinRatioValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fatProteinRatio);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Total Daily Lying*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/9."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/9."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", totalDailyLyingValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+totalDailyLying);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Actual Lameness*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/10."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualLameness);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualLamenessValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Lameness*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/11."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedLameness);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedLamenessValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Actual Ketosis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/12."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualKetosis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualKetosisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Ketosis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/13."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedKetosis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedKetosisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                  
                   /*Actual Mastitis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/14."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualMastitis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualMastitisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Mastitis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/15."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedMastitis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedMastitisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Actual Heat Stress*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/16."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualHeatStress);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualHeatStressValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Heat Stress*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/17."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedHeatStress);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedHeatStressValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Conductivity 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Conductivity 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Conductivity 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
           }
           br.close();	
           jsonld.put("@graph",liveStockJsonArray);
    	} catch (java.io.FileNotFoundException e) {
		   return "File Not Found !";
		} catch (ArrayIndexOutOfBoundsException e) {
		   return "File Not Found !";
		}
        return jsonld.toString().replace("\\", "");
	}
	
	/**
	 * Convert CSV to Animal Welfare Prediction JSONLD
	 * 
	 * @return
	 */
	public String convertCSVtoAnimalWelfarePredictionJSONLD() throws Exception {
		
		JSONObject jsonld = new JSONObject();
		File csvFolder = new File(csvFolderPath + animalWelfarePredictionPrefixFileName);
        
		File csvFolderFileList = new File(csvFolder.getPath());
		File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		} catch (NullPointerException e1) {
			return "File Not Found !";
		}
        
    	BufferedReader br = null;
    	try {
            br = new BufferedReader(new FileReader(csvListOfFiles[0].getPath()));
            String line = ""; 
            boolean metrics = false;
            JSONArray contextJsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject();
        	JSONArray liveStockJsonArray = new JSONArray();
        	
        	/*Imposto i contesti*/
            contextJsonArray.put(agriContext);
            contextJsonArray.put(livestockFeatureContext);
            jsonObject.put("qudt-unit", qudtUnit);
            contextJsonArray.put(jsonObject);
            jsonld.put("@context", contextJsonArray);
            jsonObject = new JSONObject();
            
            /*Recupero i nomi dei campi*/
       	 	String[] fieldsNames = br.readLine().split(separator);
            while ((line = br.readLine()) != null) {
	               JSONObject jsonObject1 = new JSONObject();
	     		   JSONArray  hasMemberJsonArray = new JSONArray();
	     		   JSONArray  hasResultJsonArray = new JSONArray();
        	       String[] fieldsValues = line.split(separator); 
        	       String pedometerId = "";
        	       String animalId = "";
        	       String fieldName = "";
        	       String identifier = "";
        	       String resultTime = "";
        	       String midValue = "";
        	       String lactationsValue = "";
        	       String dailyProductionValue = "";
        	       String averageDailyProductionValue = "";
        	       String fatValue = "";
        	       String proteinValue = "";
        	       String fatProteinRatioValue ="";
        	       String totalDailyLyingValue = "";
        	       String actualLamenessValue = "";
        	       String predictedLamenessValue = "";
        	       String actualKetosisValue = "";
        	       String predictedKetosisValue = "";
        	       String actualMastitisValue = "";
        	       String predictedMastitisValue = "";
        	       String actualHeatStressValue = "";
        	       String predictedHeatStressValue = "";
        	       String conductivity1Value = "";
        	       String conductivity2Value = "";
        	       String conductivity3Value = "";
        	       String activity1Value = "";
        	       String activity2Value = "";
        	       String activity3Value = "";
        	       String averageRuminationTime1Value = "";
        	       String averageRuminationTime2Value = "";
        	       String averageRuminationTime3Value = "";
        	       String averageIngestionTime1Value = "";
        	       String averageIngestionTime2Value = "";
        	       String averageIngestionTime3Value = "";
        	       String lamenessTruePositiveRateValue = "";
        	       String lamenessFalsePositiveRateValue = "";
        	       String lamenessPrecisionValue = "";
        	       String lamenessAccuracyValue = "";
        	       String mastitisTruePositiveRateValue = "";
        	       String mastitisFalsePositiveRateValue = "";
        	       String mastitisPrecisionValue = "";
        	       String mastitisAccuracyValue = "";
        	       String ketosisTruePositiveRateValue = "";
        	       String ketosisFalsePositiveRateValue = "";
        	       String ketosisPrecisionValue = "";
        	       String ketosisAccuracyValue = "";
        	       String heatStressTruePositiveRateValue = "";
        	       String heatStressFalsePositiveRateValue = "";
        	       String heatStressPrecisionValue = "";
        	       String heatStressAccuracyValue = "";
        	       
        	       /*Recupero i valori dei campi*/
	               for (int l = 0; l < fieldsValues.length; l++) { 
	        	   	   fieldName = fieldsNames[l].replace("\"", "").replace("/", "").replace(" ", "").replaceAll("[^\\p{ASCII}]", "");
		               if (fieldName.equalsIgnoreCase(pedometer)){
		            	   pedometerId = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(animal)){
		            	   animalId = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(index)){
		            	   identifier = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(date)){
		            	   resultTime = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(milkingInDays)){
		            	   midValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lactations)){
		            	   lactationsValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyProduction)){
		            	   dailyProductionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageDailyProduction)){
		            	   averageDailyProductionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyFat)){
		            	   fatValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyProteins)){
		            	   proteinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyFatProtein)){
		            	   fatProteinRatioValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(totalDailyLying)){
		            	   totalDailyLyingValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualLameness)){
		            	   actualLamenessValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedLameness)){
		            	   predictedLamenessValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualKetosis)){
		            	   actualKetosisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedKetosis)){
		            	   predictedKetosisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualMastitis)){
		            	   actualMastitisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedMastitis)){
		            	   predictedMastitisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualHeatStress)){
		            	   actualHeatStressValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedHeatStress)){
		            	   predictedHeatStressValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity1)){
		            	   conductivity1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity2)){
		            	   conductivity2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity3)){
		            	   conductivity3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity1)){
		            	   activity1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity2)){
		            	   activity2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity3)){
		            	   activity3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime1)){
		            	   averageIngestionTime1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime2)){
		            	   averageIngestionTime2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime3)){
		            	   averageIngestionTime3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime1)){
		            	   averageRuminationTime1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime2)){
		            	   averageRuminationTime2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime3)){
		            	   averageRuminationTime3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessTruePositiveRate)){
		            	   lamenessTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessFalsePositiveRate)){
		            	   lamenessFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessPrecision)){
		            	   lamenessPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessAccuracy)){
		            	   lamenessAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisTruePositiveRate)){
		            	   mastitisTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisFalsePositiveRate)){
		            	   mastitisFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisPrecision)){
		            	   mastitisPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisAccuracy)){
		            	   mastitisAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisTruePositiveRate)){
		            	   ketosisTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisFalsePositiveRate)){
		            	   ketosisFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisPrecision)){
		            	   ketosisPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisAccuracy)){
		            	   ketosisAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressTruePositiveRate)){
		            	   heatStressTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressFalsePositiveRate)){
		            	   heatStressFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressPrecision)){
		            	   heatStressPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressAccuracy)){
		            	   heatStressAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
	               } 
           	     
	               /*Metrics*/
                   if (!metrics) {
		    		   jsonObject.put("@id", "urn:demeter:predictionMetric"+identifier);
	                   jsonObject.put("@type", "PredictionMetric");
	                   jsonObject.put(lamenessTruePositiveRate, lamenessTruePositiveRateValue);
	                   jsonObject.put(lamenessFalsePositiveRate, lamenessFalsePositiveRateValue);
	                   jsonObject.put(lamenessPrecision, lamenessPrecisionValue);
	                   jsonObject.put(lamenessAccuracy, lamenessAccuracyValue);
	                   jsonObject.put(mastitisTruePositiveRate, mastitisTruePositiveRateValue);
	                   jsonObject.put(mastitisFalsePositiveRate, mastitisFalsePositiveRateValue);
	                   jsonObject.put(mastitisPrecision, mastitisPrecisionValue);
	                   jsonObject.put(mastitisAccuracy, mastitisAccuracyValue);
	                   jsonObject.put(ketosisTruePositiveRate, ketosisTruePositiveRateValue);
	                   jsonObject.put(ketosisFalsePositiveRate, ketosisFalsePositiveRateValue);
	                   jsonObject.put(ketosisPrecision, ketosisPrecisionValue);
	                   jsonObject.put(ketosisAccuracy, ketosisAccuracyValue);
	                   jsonObject.put(heatStressTruePositiveRate, heatStressTruePositiveRateValue);
	                   jsonObject.put(heatStressFalsePositiveRate, heatStressFalsePositiveRateValue);
	                   jsonObject.put(heatStressPrecision, heatStressPrecisionValue);
	                   jsonObject.put(heatStressAccuracy, heatStressAccuracyValue);
	                   
	                   liveStockJsonArray.put(jsonObject);
	                   jsonObject = new JSONObject();
                   }
                   
	    		   /*Animal*/
	    		   jsonObject.put("@id", "urn:demeter:animal"+identifier);
                   jsonObject.put("@type", "FarmAnimal");
                   jsonObject.put("description", "Animal "+identifier);
                   jsonObject.put("livestockNumber", animalId);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Collection of observations*/
                   jsonObject.put("@id", "urn:demeter:"+UUID.randomUUID());
                   jsonObject.put("@type", "HealthPrediction");
                   jsonObject.put("indentifier", identifier);
                   jsonObject.put("description", "Health Prediction collection of observations with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("hasFeatureOfInterest", "urn:demeter:animal"+identifier);
                   if (!metrics) {
	                   jsonObject.put("predictionMetric", "urn:demeter:predictionMetric"+identifier);
	                   metrics = true;
                   }
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Individual properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/1."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Individual properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/3."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/4."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/5."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/6."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/7."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/8."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/9."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/10."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/11."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/12."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/13."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/14."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/15."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/16."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/17."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("resultTime", resultTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Conductivity properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/2."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Conductivity properties collection with identifier "+identifier);
                   
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", foodieURL+conductivity);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Activity properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/3."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Activity properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+activity);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
	     		   
	     		   /*Rumination properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/4."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Rumination properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageRuminationTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
	     		   
	     		   /*Ingestion properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/5."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Ingestion properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageIngestionTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Pedometer*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", pedometerId);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+pedometer);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*MID*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", midValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+milkingInDays);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Lactations*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", lactationsValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+lactations);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Daily Production*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/4."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/4."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", dailyProductionValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+dailyProduction);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Daily Production*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/5."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/5."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageDailyProductionValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageDailyProduction);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/6."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/6."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fat);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Protein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/7."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/7."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", proteinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+protein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat Protein Ratio*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/8."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/8."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatProteinRatioValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fatProteinRatio);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Total Daily Lying*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/9."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/9."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", totalDailyLyingValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+totalDailyLying);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Actual Lameness*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/10."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualLameness);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualLamenessValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Lameness*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/11."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedLameness);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedLamenessValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Actual Ketosis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/12."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualKetosis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualKetosisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Ketosis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/13."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedKetosis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedKetosisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                  
                   /*Actual Mastitis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/14."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualMastitis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualMastitisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Mastitis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/15."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedMastitis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedMastitisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Actual Heat Stress*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/16."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualHeatStress);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualHeatStressValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Heat Stress*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/17."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedHeatStress);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedHeatStressValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Conductivity 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Conductivity 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Conductivity 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
           }
           br.close();	
           jsonld.put("@graph",liveStockJsonArray);
    	} catch (java.io.FileNotFoundException e) {
		   return "File Not Found !";
		} catch (ArrayIndexOutOfBoundsException e) {
		   return "File Not Found !";
		}
        return jsonld.toString().replace("\\", "");
	}
	
	/**
	 * Convert CSV to Animal Welfare Training JSONLD
	 * 
	 * @return
	 */
	public String convertCSVtoAnimalWelfareTrainingJSONLD(String csvRFText) throws Exception {
		
		JSONObject jsonld = new JSONObject();
		File csvFolder = new File(csvFolderPath + animalWelfareTrainingPrefixFileName);
		
		if (!csvRFText.equalsIgnoreCase("")){
			csvFolder = new File(csvFolderPath + animalWelfareTrainingPrefixFileName + "/RF");
	    	csvFolder.mkdirs();
	    	BufferedWriter jbw;
	    	if (storage.equalsIgnoreCase("Y")){
	    		jbw = new BufferedWriter(new FileWriter(new File(csvFolder+"/"+"AWT_RandomForest_"+getCurrentDateTimeMS()+".csv")));
	    	}else {
	    		jbw = new BufferedWriter(new FileWriter(new File(csvFolder+"/"+"AWT_RandomForest.csv")));
	    	}
			jbw.write(csvRFText);
			jbw.close();
	    }
		  
		File csvFolderFileList = new File(csvFolder.getPath());
		File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
       
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		} catch (NullPointerException e1) {
			return "File Not Found !";
		}
        
    	BufferedReader br = null;
    	try {
            br = new BufferedReader(new FileReader(csvListOfFiles[0].getPath()));
            String line = ""; 
            boolean metrics = false;
            JSONArray contextJsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject();
        	JSONArray liveStockJsonArray = new JSONArray();
        	
        	/*Imposto i contesti*/
            contextJsonArray.put(agriContext);
            contextJsonArray.put(livestockFeatureContext);
            jsonObject.put("qudt-unit", qudtUnit);
            contextJsonArray.put(jsonObject);
            jsonld.put("@context", contextJsonArray);
            jsonObject = new JSONObject();
            
            /*Recupero i nomi dei campi*/
       	 	String[] fieldsNames = br.readLine().split(separator);
            while ((line = br.readLine()) != null) {
	               JSONObject jsonObject1 = new JSONObject();
	     		   JSONArray  hasMemberJsonArray = new JSONArray();
	     		   JSONArray  hasResultJsonArray = new JSONArray();
        	       String[] fieldsValues = line.split(separator); 
        	       String pedometerId = "";
        	       String animalId = "";
        	       String fieldName = "";
        	       String identifier = "";
        	       String resultTime = "";
        	       String midValue = "";
        	       String lactationsValue = "";
        	       String dailyProductionValue = "";
        	       String averageDailyProductionValue = "";
        	       String fatValue = "";
        	       String proteinValue = "";
        	       String fatProteinRatioValue ="";
        	       String totalDailyLyingValue = "";
        	       String actualLamenessValue = "";
        	       String predictedLamenessValue = "";
        	       String actualKetosisValue = "";
        	       String predictedKetosisValue = "";
        	       String actualMastitisValue = "";
        	       String predictedMastitisValue = "";
        	       String actualHeatStressValue = "";
        	       String predictedHeatStressValue = "";
        	       String conductivity1Value = "";
        	       String conductivity2Value = "";
        	       String conductivity3Value = "";
        	       String activity1Value = "";
        	       String activity2Value = "";
        	       String activity3Value = "";
        	       String averageRuminationTime1Value = "";
        	       String averageRuminationTime2Value = "";
        	       String averageRuminationTime3Value = "";
        	       String averageIngestionTime1Value = "";
        	       String averageIngestionTime2Value = "";
        	       String averageIngestionTime3Value = "";
        	       String lamenessTruePositiveRateValue = "";
        	       String lamenessFalsePositiveRateValue = "";
        	       String lamenessPrecisionValue = "";
        	       String lamenessAccuracyValue = "";
        	       String mastitisTruePositiveRateValue = "";
        	       String mastitisFalsePositiveRateValue = "";
        	       String mastitisPrecisionValue = "";
        	       String mastitisAccuracyValue = "";
        	       String ketosisTruePositiveRateValue = "";
        	       String ketosisFalsePositiveRateValue = "";
        	       String ketosisPrecisionValue = "";
        	       String ketosisAccuracyValue = "";
        	       String heatStressTruePositiveRateValue = "";
        	       String heatStressFalsePositiveRateValue = "";
        	       String heatStressPrecisionValue = "";
        	       String heatStressAccuracyValue = "";
        	       
        	       /*Recupero i valori dei campi*/
	               for (int l = 0; l < fieldsValues.length; l++) { 
	        	   	   fieldName = fieldsNames[l].replace("\"", "").replace("/", "").replace(" ", "").replaceAll("[^\\p{ASCII}]", "");
		               if (fieldName.equalsIgnoreCase(pedometer)){
		            	   pedometerId = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(animal)){
		            	   animalId = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(index)){
		            	   identifier = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(date)){
		            	   resultTime = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(milkingInDays)){
		            	   midValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lactations)){
		            	   lactationsValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyProduction)){
		            	   dailyProductionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageDailyProduction)){
		            	   averageDailyProductionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyFat)){
		            	   fatValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyProteins)){
		            	   proteinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyFatProtein)){
		            	   fatProteinRatioValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(totalDailyLying)){
		            	   totalDailyLyingValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualLameness)){
		            	   actualLamenessValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedLameness)){
		            	   predictedLamenessValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualKetosis)){
		            	   actualKetosisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedKetosis)){
		            	   predictedKetosisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualMastitis)){
		            	   actualMastitisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedMastitis)){
		            	   predictedMastitisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualHeatStress)){
		            	   actualHeatStressValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedHeatStress)){
		            	   predictedHeatStressValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity1)){
		            	   conductivity1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity2)){
		            	   conductivity2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity3)){
		            	   conductivity3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity1)){
		            	   activity1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity2)){
		            	   activity2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity3)){
		            	   activity3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime1)){
		            	   averageIngestionTime1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime2)){
		            	   averageIngestionTime2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime3)){
		            	   averageIngestionTime3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime1)){
		            	   averageRuminationTime1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime2)){
		            	   averageRuminationTime2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime3)){
		            	   averageRuminationTime3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessTruePositiveRate)){
		            	   lamenessTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessFalsePositiveRate)){
		            	   lamenessFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessPrecision)){
		            	   lamenessPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessAccuracy)){
		            	   lamenessAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisTruePositiveRate)){
		            	   mastitisTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisFalsePositiveRate)){
		            	   mastitisFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisPrecision)){
		            	   mastitisPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisAccuracy)){
		            	   mastitisAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisTruePositiveRate)){
		            	   ketosisTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisFalsePositiveRate)){
		            	   ketosisFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisPrecision)){
		            	   ketosisPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisAccuracy)){
		            	   ketosisAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressTruePositiveRate)){
		            	   heatStressTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressFalsePositiveRate)){
		            	   heatStressFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressPrecision)){
		            	   heatStressPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressAccuracy)){
		            	   heatStressAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
	               } 
           	     
	               /*Metrics*/
                   if (!metrics) {
		    		   jsonObject.put("@id", "urn:demeter:predictionMetric"+identifier);
	                   jsonObject.put("@type", "PredictionMetric");
	                   jsonObject.put(lamenessTruePositiveRate, lamenessTruePositiveRateValue);
	                   jsonObject.put(lamenessFalsePositiveRate, lamenessFalsePositiveRateValue);
	                   jsonObject.put(lamenessPrecision, lamenessPrecisionValue);
	                   jsonObject.put(lamenessAccuracy, lamenessAccuracyValue);
	                   jsonObject.put(mastitisTruePositiveRate, mastitisTruePositiveRateValue);
	                   jsonObject.put(mastitisFalsePositiveRate, mastitisFalsePositiveRateValue);
	                   jsonObject.put(mastitisPrecision, mastitisPrecisionValue);
	                   jsonObject.put(mastitisAccuracy, mastitisAccuracyValue);
	                   jsonObject.put(ketosisTruePositiveRate, ketosisTruePositiveRateValue);
	                   jsonObject.put(ketosisFalsePositiveRate, ketosisFalsePositiveRateValue);
	                   jsonObject.put(ketosisPrecision, ketosisPrecisionValue);
	                   jsonObject.put(ketosisAccuracy, ketosisAccuracyValue);
	                   jsonObject.put(heatStressTruePositiveRate, heatStressTruePositiveRateValue);
	                   jsonObject.put(heatStressFalsePositiveRate, heatStressFalsePositiveRateValue);
	                   jsonObject.put(heatStressPrecision, heatStressPrecisionValue);
	                   jsonObject.put(heatStressAccuracy, heatStressAccuracyValue);
	                   
	                   liveStockJsonArray.put(jsonObject);
	                   jsonObject = new JSONObject();
                   }
                   
	    		   /*Animal*/
	    		   jsonObject.put("@id", "urn:demeter:animal"+identifier);
                   jsonObject.put("@type", "FarmAnimal");
                   jsonObject.put("description", "Animal "+identifier);
                   jsonObject.put("livestockNumber", animalId);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Collection of observations*/
                   jsonObject.put("@id", "urn:demeter:"+UUID.randomUUID());
                   jsonObject.put("@type", "HealthPrediction");
                   jsonObject.put("indentifier", identifier);
                   jsonObject.put("description", "Health Prediction collection of observations with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("hasFeatureOfInterest", "urn:demeter:animal"+identifier);
                   if (!metrics) {
	                   jsonObject.put("predictionMetric", "urn:demeter:predictionMetric"+identifier);
	                   metrics = true;
                   }
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Individual properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/1."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Individual properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/3."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/4."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/5."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/6."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/7."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/8."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/9."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/10."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/11."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/12."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/13."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/14."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/15."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/16."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/17."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("resultTime", resultTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Conductivity properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/2."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Conductivity properties collection with identifier "+identifier);
                   
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", foodieURL+conductivity);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Activity properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/3."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Activity properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+activity);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
	     		  /*Rumination properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/4."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Rumination properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageRuminationTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
	     		   
	     		   /*Ingestion properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/5."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Ingestion properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageIngestionTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
	     		   
                   /*Pedometer*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", pedometerId);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+pedometer);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*MID*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", midValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+milkingInDays);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Lactations*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", lactationsValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+lactations);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Daily Production*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/4."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/4."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", dailyProductionValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+dailyProduction);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Daily Production*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/5."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/5."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageDailyProductionValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageDailyProduction);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/6."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/6."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fat);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Protein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/7."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/7."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", proteinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+protein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat Protein Ratio*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/8."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/8."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatProteinRatioValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fatProteinRatio);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Total Daily Lying*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/9."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/9."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", totalDailyLyingValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+totalDailyLying);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Actual Lameness*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/10."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualLameness);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualLamenessValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Lameness*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/11."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedLameness);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedLamenessValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Actual Ketosis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/12."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualKetosis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualKetosisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Ketosis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/13."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedKetosis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedKetosisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                  
                   /*Actual Mastitis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/14."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualMastitis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualMastitisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Mastitis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/15."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedMastitis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedMastitisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Actual Heat Stress*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/16."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualHeatStress);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualHeatStressValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Heat Stress*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/17."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedHeatStress);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedHeatStressValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Conductivity 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Conductivity 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Conductivity 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
           }
           br.close();	
           jsonld.put("@graph",liveStockJsonArray);
    	} catch (java.io.FileNotFoundException e) {
		   return "File Not Found !";
		} catch (ArrayIndexOutOfBoundsException e) {
		   return "File Not Found !";
		}
        return jsonld.toString().replace("\\", "");
	}
	
	/**
	 * Convert CSV to Animal Welfare Prediction JSONLD
	 * 
	 * @return
	 */
	public String convertCSVtoAnimalWelfarePredictionJSONLD(String csvRFText) throws Exception {
		
		JSONObject jsonld = new JSONObject();
		File csvFolder = new File(csvFolderPath + animalWelfarePredictionPrefixFileName);
		
		if (!csvRFText.equalsIgnoreCase("")){
			csvFolder = new File(csvFolderPath + animalWelfarePredictionPrefixFileName + "/RF");
	    	csvFolder.mkdirs();
	    	BufferedWriter jbw;
	    	if (storage.equalsIgnoreCase("Y")){
	    		jbw = new BufferedWriter(new FileWriter(new File(csvFolder+"/"+"AWP_RandomForest_"+getCurrentDateTimeMS()+".csv")));
	    	}else {
	    		jbw = new BufferedWriter(new FileWriter(new File(csvFolder+"/"+"AWP_RandomForest.csv")));
	    	}
			jbw.write(csvRFText);
			jbw.close();
	    }
		  
		File csvFolderFileList = new File(csvFolder.getPath());
		File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
       
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
		} catch (NullPointerException e1) {
			return "File Not Found !";
		}
        
    	BufferedReader br = null;
    	try {
            br = new BufferedReader(new FileReader(csvListOfFiles[0].getPath()));
            String line = ""; 
            boolean metrics = false;
            JSONArray contextJsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject();
        	JSONArray liveStockJsonArray = new JSONArray();
        	
        	/*Imposto i contesti*/
            contextJsonArray.put(agriContext);
            contextJsonArray.put(livestockFeatureContext);
            jsonObject.put("qudt-unit", qudtUnit);
            contextJsonArray.put(jsonObject);
            jsonld.put("@context", contextJsonArray);
            jsonObject = new JSONObject();
            
            /*Recupero i nomi dei campi*/
       	 	String[] fieldsNames = br.readLine().split(separator);
            while ((line = br.readLine()) != null) {
	               JSONObject jsonObject1 = new JSONObject();
	     		   JSONArray  hasMemberJsonArray = new JSONArray();
	     		   JSONArray  hasResultJsonArray = new JSONArray();
        	       String[] fieldsValues = line.split(separator); 
        	       String pedometerId = "";
        	       String animalId = "";
        	       String fieldName = "";
        	       String identifier = "";
        	       String resultTime = "";
        	       String midValue = "";
        	       String lactationsValue = "";
        	       String dailyProductionValue = "";
        	       String averageDailyProductionValue = "";
        	       String fatValue = "";
        	       String proteinValue = "";
        	       String fatProteinRatioValue ="";
        	       String totalDailyLyingValue = "";
        	       String actualLamenessValue = "";
        	       String predictedLamenessValue = "";
        	       String actualKetosisValue = "";
        	       String predictedKetosisValue = "";
        	       String actualMastitisValue = "";
        	       String predictedMastitisValue = "";
        	       String actualHeatStressValue = "";
        	       String predictedHeatStressValue = "";
        	       String conductivity1Value = "";
        	       String conductivity2Value = "";
        	       String conductivity3Value = "";
        	       String activity1Value = "";
        	       String activity2Value = "";
        	       String activity3Value = "";
        	       String averageRuminationTime1Value = "";
        	       String averageRuminationTime2Value = "";
        	       String averageRuminationTime3Value = "";
        	       String averageIngestionTime1Value = "";
        	       String averageIngestionTime2Value = "";
        	       String averageIngestionTime3Value = "";
        	       String lamenessTruePositiveRateValue = "";
        	       String lamenessFalsePositiveRateValue = "";
        	       String lamenessPrecisionValue = "";
        	       String lamenessAccuracyValue = "";
        	       String mastitisTruePositiveRateValue = "";
        	       String mastitisFalsePositiveRateValue = "";
        	       String mastitisPrecisionValue = "";
        	       String mastitisAccuracyValue = "";
        	       String ketosisTruePositiveRateValue = "";
        	       String ketosisFalsePositiveRateValue = "";
        	       String ketosisPrecisionValue = "";
        	       String ketosisAccuracyValue = "";
        	       String heatStressTruePositiveRateValue = "";
        	       String heatStressFalsePositiveRateValue = "";
        	       String heatStressPrecisionValue = "";
        	       String heatStressAccuracyValue = "";
        	       
        	       /*Recupero i valori dei campi*/
	               for (int l = 0; l < fieldsValues.length; l++) { 
	        	   	   fieldName = fieldsNames[l].replace("\"", "").replace("/", "").replace(" ", "").replaceAll("[^\\p{ASCII}]", "");
		               if (fieldName.equalsIgnoreCase(pedometer)){
		            	   pedometerId = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(animal)){
		            	   animalId = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(index)){
		            	   identifier = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(date)){
		            	   resultTime = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(milkingInDays)){
		            	   midValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lactations)){
		            	   lactationsValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyProduction)){
		            	   dailyProductionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageDailyProduction)){
		            	   averageDailyProductionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyFat)){
		            	   fatValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyProteins)){
		            	   proteinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(dailyFatProtein)){
		            	   fatProteinRatioValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(totalDailyLying)){
		            	   totalDailyLyingValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualLameness)){
		            	   actualLamenessValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedLameness)){
		            	   predictedLamenessValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualKetosis)){
		            	   actualKetosisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedKetosis)){
		            	   predictedKetosisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualMastitis)){
		            	   actualMastitisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedMastitis)){
		            	   predictedMastitisValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualHeatStress)){
		            	   actualHeatStressValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedHeatStress)){
		            	   predictedHeatStressValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity1)){
		            	   conductivity1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity2)){
		            	   conductivity2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(conductivity3)){
		            	   conductivity3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity1)){
		            	   activity1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity2)){
		            	   activity2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(activity3)){
		            	   activity3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime1)){
		            	   averageIngestionTime1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime2)){
		            	   averageIngestionTime2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageIngestionTime3)){
		            	   averageIngestionTime3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime1)){
		            	   averageRuminationTime1Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime2)){
		            	   averageRuminationTime2Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(averageRuminationTime3)){
		            	   averageRuminationTime3Value = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessTruePositiveRate)){
		            	   lamenessTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessFalsePositiveRate)){
		            	   lamenessFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessPrecision)){
		            	   lamenessPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lamenessAccuracy)){
		            	   lamenessAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisTruePositiveRate)){
		            	   mastitisTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisFalsePositiveRate)){
		            	   mastitisFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisPrecision)){
		            	   mastitisPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(mastitisAccuracy)){
		            	   mastitisAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisTruePositiveRate)){
		            	   ketosisTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisFalsePositiveRate)){
		            	   ketosisFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisPrecision)){
		            	   ketosisPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(ketosisAccuracy)){
		            	   ketosisAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressTruePositiveRate)){
		            	   heatStressTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressFalsePositiveRate)){
		            	   heatStressFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressPrecision)){
		            	   heatStressPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(heatStressAccuracy)){
		            	   heatStressAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
	               } 
           	     
	               /*Metrics*/
                   if (!metrics) {
		    		   jsonObject.put("@id", "urn:demeter:predictionMetric"+identifier);
	                   jsonObject.put("@type", "PredictionMetric");
	                   jsonObject.put(lamenessTruePositiveRate, lamenessTruePositiveRateValue);
	                   jsonObject.put(lamenessFalsePositiveRate, lamenessFalsePositiveRateValue);
	                   jsonObject.put(lamenessPrecision, lamenessPrecisionValue);
	                   jsonObject.put(lamenessAccuracy, lamenessAccuracyValue);
	                   jsonObject.put(mastitisTruePositiveRate, mastitisTruePositiveRateValue);
	                   jsonObject.put(mastitisFalsePositiveRate, mastitisFalsePositiveRateValue);
	                   jsonObject.put(mastitisPrecision, mastitisPrecisionValue);
	                   jsonObject.put(mastitisAccuracy, mastitisAccuracyValue);
	                   jsonObject.put(ketosisTruePositiveRate, ketosisTruePositiveRateValue);
	                   jsonObject.put(ketosisFalsePositiveRate, ketosisFalsePositiveRateValue);
	                   jsonObject.put(ketosisPrecision, ketosisPrecisionValue);
	                   jsonObject.put(ketosisAccuracy, ketosisAccuracyValue);
	                   jsonObject.put(heatStressTruePositiveRate, heatStressTruePositiveRateValue);
	                   jsonObject.put(heatStressFalsePositiveRate, heatStressFalsePositiveRateValue);
	                   jsonObject.put(heatStressPrecision, heatStressPrecisionValue);
	                   jsonObject.put(heatStressAccuracy, heatStressAccuracyValue);
	                   
	                   liveStockJsonArray.put(jsonObject);
	                   jsonObject = new JSONObject();
                   }
                   
	    		   /*Animal*/
	    		   jsonObject.put("@id", "urn:demeter:animal"+identifier);
                   jsonObject.put("@type", "FarmAnimal");
                   jsonObject.put("description", "Animal "+identifier);
                   jsonObject.put("livestockNumber", animalId);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Collection of observations*/
                   jsonObject.put("@id", "urn:demeter:"+UUID.randomUUID());
                   jsonObject.put("@type", "HealthPrediction");
                   jsonObject.put("indentifier", identifier);
                   jsonObject.put("description", "Health Prediction collection of observations with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationCollection/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("hasFeatureOfInterest", "urn:demeter:animal"+identifier);
                   if (!metrics) {
	                   jsonObject.put("predictionMetric", "urn:demeter:predictionMetric"+identifier);
	                   metrics = true;
                   }
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Individual properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/1."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Individual properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/3."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/4."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/5."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/6."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/7."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/8."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/9."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/10."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/11."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/12."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/13."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/14."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/15."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/16."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/17."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("resultTime", resultTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Conductivity properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/2."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Conductivity properties collection with identifier "+identifier);
                   
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationC/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", foodieURL+conductivity);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Activity properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/3."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Activity properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationA/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+activity);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
	     		   /*Rumination properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/4."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Rumination properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationR/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageRuminationTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
	     		   
	     		   /*Ingestion properties collection*/
                   jsonObject.put("@id", "urn:demeter:ObservationCollection/5."+identifier);
                   jsonObject.put("@type", "ObservationCollection");
                   jsonObject.put("description", "Ingestion properties collection with identifier "+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/1."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/2."+identifier);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationIN/3."+identifier);
                   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageIngestionTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
	     		   
                   /*Pedometer*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", pedometerId);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+pedometer);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*MID*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", midValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+milkingInDays);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Lactations*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", lactationsValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+lactations);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Daily Production*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/4."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/4."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", dailyProductionValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+dailyProduction);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Daily Production*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/5."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/5."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageDailyProductionValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+averageDailyProduction);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/6."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/6."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fat);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Protein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/7."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/7."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", proteinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+protein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat Protein Ratio*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/8."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/8."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatProteinRatioValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fatProteinRatio);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Total Daily Lying*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/9."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/9."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", totalDailyLyingValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+totalDailyLying);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Actual Lameness*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/10."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualLameness);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualLamenessValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Lameness*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/11."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedLameness);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedLamenessValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Actual Ketosis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/12."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualKetosis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualKetosisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Ketosis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/13."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedKetosis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedKetosisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                  
                   /*Actual Mastitis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/14."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualMastitis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualMastitisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Mastitis*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/15."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedMastitis);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedMastitisValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Actual Heat Stress*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/16."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualHeatStress);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+actualHeatStressValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Predicted Heat Stress*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/17."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedHeatStress);
                   jsonObject.put("hasResult", livestockFeatureURL+"healthStatus-"+predictedHeatStressValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
                   /*Conductivity 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Conductivity 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Conductivity 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationC/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationC/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", conductivity3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Activity 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationA/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationA/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", activity3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Rumination Time 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationR/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationR/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageRuminationTime3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 1*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/1."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/1."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime1Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 2*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/2."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/2."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime2Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Average Ingestion Time 3*/
                   jsonObject.put("@id", "urn:demeter:ObservationIN/3."+identifier);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationIN/3."+identifier+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", averageIngestionTime3Value);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
           }
           br.close();	
           jsonld.put("@graph",liveStockJsonArray);
    	} catch (java.io.FileNotFoundException e) {
		   return "File Not Found !";
		} catch (ArrayIndexOutOfBoundsException e) {
		   return "File Not Found !";
		}
        return jsonld.toString().replace("\\", "");
	}
	
	/**
	 * Convert CSV to MilkQuality Training JSONLD
	 * 
	 * @return
	 */
	public String convertCSVtoMilkQualityTrainingJSONLD() throws Exception {
		
		JSONObject jsonld = new JSONObject();
		File csvFolder = new File(csvFolderPath + milkQualityTrainingPrefixFileName);
        
		File csvFolderFileList = new File(csvFolder.getPath());
        File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			
		} catch (NullPointerException e1) {
			return "File Not Found !";
		}
        
    	BufferedReader br = null;
    	try {
            br = new BufferedReader(new FileReader(csvListOfFiles[0].getPath()));
            String line = ""; 
            boolean metrics = false; 
            JSONArray contextJsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject();
        	JSONArray liveStockJsonArray = new JSONArray();
        	
        	/*Imposto i contesti*/
            contextJsonArray.put(agriContext);
            contextJsonArray.put(livestockFeatureContext);
            jsonObject.put("qudt-unit", qudtUnit);
            contextJsonArray.put(jsonObject);
            jsonld.put("@context", contextJsonArray);
            jsonObject = new JSONObject();
            
        	/*Recupero i nomi dei campi*/
       	 	String[] fieldsNames = br.readLine().split(separator);
       	 	int index = 0;
            while ((line = br.readLine()) != null) {
	               JSONObject jsonObject1 = new JSONObject();
	     		   JSONArray  hasMemberJsonArray = new JSONArray();
	     		   JSONArray  hasResultJsonArray = new JSONArray();
        	       String[] fieldsValues = line.split(separator); 
        	       String productNameValue = "";
        	       String productType = "";
        	       String fieldName = "";
        	       String resultTime = "";
        	       String acidityValue = "";
        	       String caseinValue = "";
        	       String densityValue = "";
        	       String fatValue = "";
        	       String proteinValue = "";
        	       String freezingPointValue = "";
        	       String lactoseValue = "";
        	       String snfValue = "";
        	       String ureaValue = "";
        	       String actualQualityValue = "";
        	       String predictedQualityValue = "";
        	       String rawTruePositiveRateValue = "";
        	       String rawFalsePositiveRateValue = "";
        	       String rawPrecisionValue = "";
        	       String rawAccuracyValue = "";
        	       String processedTruePositiveRateValue = "";
        	       String processedFalsePositiveRateValue = "";
        	       String processedPrecisionValue = "";
        	       String processedAccuracyValue = "";
        	       index++;
        	       
        	       /*Recupero i valori dei campi*/
	               for (int l = 0; l < fieldsValues.length; l++) { 
	        	   	   fieldName = fieldsNames[l].replace("\"", "").replace("/", "").replace(" ", "").replaceAll("[^\\p{ASCII}]", "");
		               if (fieldName.equalsIgnoreCase(productName)){
		            	   productNameValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(remark)){
		            	   productType = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(date)){
		            	   resultTime = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(acidity)){
		            	   acidityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(casein)){
		            	   caseinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(density)){
		            	   densityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(fat)){
		            	   fatValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(protein)){
		            	   proteinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(freezingPoint)){
		            	   freezingPointValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lactose)){
		            	   lactoseValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(snf)){
		            	   snfValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(urea)){
		            	   ureaValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualQuality)){
		            	   actualQualityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedQuality)){
		            	   predictedQualityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawTruePositiveRate)){
		            	   rawTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawFalsePositiveRate)){
		            	   rawFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawPrecision)){
		            	   rawPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawAccuracy)){
		            	   rawAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedTruePositiveRate)){
		            	   processedTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedFalsePositiveRate)){
		            	   processedFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedPrecision)){
		            	   processedPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedAccuracy)){
		            	   processedAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
	               } 
           	     
	               /*Metrics*/
                   if (!metrics) {
		    		   jsonObject.put("@id", "urn:demeter:predictionMetric"+index);
	                   jsonObject.put("@type", "PredictionMetric");
	                   jsonObject.put(rawTruePositiveRate, rawTruePositiveRateValue);
	                   jsonObject.put(rawFalsePositiveRate, rawFalsePositiveRateValue);
	                   jsonObject.put(rawPrecision, rawPrecisionValue);
	                   jsonObject.put(rawAccuracy, rawAccuracyValue);
	                   jsonObject.put(processedTruePositiveRate, processedTruePositiveRateValue);
	                   jsonObject.put(processedFalsePositiveRate, processedFalsePositiveRateValue);
	                   jsonObject.put(processedPrecision, processedPrecisionValue);
	                   jsonObject.put(processedAccuracy, processedAccuracyValue);
	                   
	                   liveStockJsonArray.put(jsonObject);
	                   jsonObject = new JSONObject();
                   }
                   
	    		   /*Milk Product*/
	    		   jsonObject.put("@id", "urn:demeter:milkProduct"+index);
                   jsonObject.put("@type", "MilkProduct");
                   jsonObject.put("productName", productNameValue);
                   jsonObject.put("productType", productType);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
	     		   /*Collection of observations*/
	     		   jsonObject.put("@id", "urn:demeter:"+UUID.randomUUID());
                   jsonObject.put("@type", "MilkQualityPrediction");
                   jsonObject.put("description", "Milk Quality Prediction collection of observations with identifier "+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/1."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/2."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/3."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/4."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/5."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/6."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/7."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/8."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/9."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/10."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/11."+index);
	    		   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("hasFeatureOfInterest", "urn:demeter:milkProduct"+index);
                   if (!metrics) {
	                   jsonObject.put("predictionMetric", "urn:demeter:predictionMetric"+index);
	                   metrics = true;
                   }
                   jsonObject.put("resultTime", resultTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
                   /*Acidity*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/1."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/1."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", acidityValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+acidity);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Casein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/2."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/2."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", caseinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+casein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Density*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/3."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/3."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", densityValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+density);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/4."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/4."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fat);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Protein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/5."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/5."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", proteinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+protein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Freezing Point*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/6."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/6."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", freezingPointValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+freezingPoint);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Lactose*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/7."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/7."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", lactoseValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+lactose);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*SNF*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/8."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/8."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", snfValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+snf);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Urea*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/9."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/9."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", ureaValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+urea);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Actual Quality*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/10."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualQuality);
                   jsonObject.put("hasResult", livestockFeatureURL+"qualityValue-"+actualQualityValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Perdicted Quality*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/11."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedQuality);
                   jsonObject.put("hasResult", livestockFeatureURL+"qualityValue-"+predictedQualityValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   hasResultJsonArray = new JSONArray();
           }
           br.close();	
           jsonld.put("@graph",liveStockJsonArray);
    	} catch (java.io.FileNotFoundException e) {
		   return "File Not Found !";
		} catch (ArrayIndexOutOfBoundsException e) {
		   return "File Not Found !";
		}
        return jsonld.toString().replace("\\", "");
	}
	
	/**
	 * Convert CSV to MilkQuality Prediction JSONLD
	 * 
	 * @return
	 */
	public String convertCSVtoMilkQualityPredictionJSONLD() throws Exception {
		
		JSONObject jsonld = new JSONObject();
		File csvFolder = new File(csvFolderPath + milkQualityPredictionPrefixFileName);
        
		File csvFolderFileList = new File(csvFolder.getPath());
        File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			
		} catch (NullPointerException e1) {
			return "File Not Found !";
		}
        
    	BufferedReader br = null;
    	try {
            br = new BufferedReader(new FileReader(csvListOfFiles[0].getPath()));
            String line = ""; 
            boolean metrics = false; 
            JSONArray contextJsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject();
        	JSONArray liveStockJsonArray = new JSONArray();
        	
        	/*Imposto i contesti*/
            contextJsonArray.put(agriContext);
            contextJsonArray.put(livestockFeatureContext);
            jsonObject.put("qudt-unit", qudtUnit);
            contextJsonArray.put(jsonObject);
            jsonld.put("@context", contextJsonArray);
            jsonObject = new JSONObject();
            
            /*Recupero i nomi dei campi*/
       	 	String[] fieldsNames = br.readLine().split(separator);
       	 	int index = 0;
            while ((line = br.readLine()) != null) {
	               JSONObject jsonObject1 = new JSONObject();
	               JSONObject jsonObject2 = new JSONObject();
	     		   JSONArray  hasMemberJsonArray = new JSONArray();
	     		   JSONArray  hasResultJsonArray = new JSONArray();
        	       String[] fieldsValues = line.split(separator); 
        	       String productNameValue = "";
        	       String productType = "";
        	       String fieldName = "";
        	       String resultTime = "";
        	       String acidityValue = "";
        	       String caseinValue = "";
        	       String densityValue = "";
        	       String fatValue = "";
        	       String proteinValue = "";
        	       String freezingPointValue = "";
        	       String lactoseValue = "";
        	       String snfValue = "";
        	       String ureaValue = "";
        	       String actualQualityValue = "";
        	       String predictedQualityValue = "";
        	       String rawTruePositiveRateValue = "";
        	       String rawFalsePositiveRateValue = "";
        	       String rawPrecisionValue = "";
        	       String rawAccuracyValue = "";
        	       String processedTruePositiveRateValue = "";
        	       String processedFalsePositiveRateValue = "";
        	       String processedPrecisionValue = "";
        	       String processedAccuracyValue = "";
        	       index++;
        	       
        	       /*Recupero i valori dei campi*/
	               for (int l = 0; l < fieldsValues.length; l++) { 
	        	   	   fieldName = fieldsNames[l].replace("\"", "").replace("/", "").replace(" ", "").replaceAll("[^\\p{ASCII}]", "");
		               if (fieldName.equalsIgnoreCase(productName)){
		            	   productNameValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(remark)){
		            	   productType = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(date)){
		            	   resultTime = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(acidity)){
		            	   acidityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(casein)){
		            	   caseinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(density)){
		            	   densityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(fat)){
		            	   fatValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(protein)){
		            	   proteinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(freezingPoint)){
		            	   freezingPointValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lactose)){
		            	   lactoseValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(snf)){
		            	   snfValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(urea)){
		            	   ureaValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualQuality)){
		            	   actualQualityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedQuality)){
		            	   predictedQualityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawTruePositiveRate)){
		            	   rawTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawFalsePositiveRate)){
		            	   rawFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawPrecision)){
		            	   rawPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawAccuracy)){
		            	   rawAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedTruePositiveRate)){
		            	   processedTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedFalsePositiveRate)){
		            	   processedFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedPrecision)){
		            	   processedPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedAccuracy)){
		            	   processedAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
	               } 
           	     
	               /*Metrics*/
                   if (!metrics) {
		    		   jsonObject.put("@id", "urn:demeter:predictionMetric"+index);
	                   jsonObject.put("@type", "PredictionMetric");
	                   jsonObject.put(rawTruePositiveRate, rawTruePositiveRateValue);
	                   jsonObject.put(rawFalsePositiveRate, rawFalsePositiveRateValue);
	                   jsonObject.put(rawPrecision, rawPrecisionValue);
	                   jsonObject.put(rawAccuracy, rawAccuracyValue);
	                   jsonObject.put(processedTruePositiveRate, processedTruePositiveRateValue);
	                   jsonObject.put(processedFalsePositiveRate, processedFalsePositiveRateValue);
	                   jsonObject.put(processedPrecision, processedPrecisionValue);
	                   jsonObject.put(processedAccuracy, processedAccuracyValue);
	                   
	                   liveStockJsonArray.put(jsonObject);
	                   jsonObject = new JSONObject();
                   }
                   
	    		   /*Milk Product*/
	    		   jsonObject.put("@id", "urn:demeter:milkProduct"+index);
                   jsonObject.put("@type", "MilkProduct");
                   jsonObject.put("productName", productNameValue);
                   jsonObject.put("productType", productType);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
	     		   /*Collection of observations*/
	     		   jsonObject.put("@id", "urn:demeter:"+UUID.randomUUID());
                   jsonObject.put("@type", "MilkQualityPrediction");
                   jsonObject.put("description", "Milk Quality Prediction collection of observations with identifier "+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/1."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/2."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/3."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/4."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/5."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/6."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/7."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/8."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/9."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/10."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/11."+index);
	    		   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("hasFeatureOfInterest", "urn:demeter:milkProduct"+index);
                   if (!metrics) {
	                   jsonObject.put("predictionMetric", "urn:demeter:predictionMetric"+index);
	                   metrics = true;
                   }
                   jsonObject.put("resultTime", resultTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
	     		  /*Acidity*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/1."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/1."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", acidityValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+acidity);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Casein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/2."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/2."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", caseinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+casein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Density*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/3."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/3."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", densityValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+density);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/4."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/4."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fat);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Protein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/5."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/5."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", proteinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+protein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Freezing Point*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/6."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/6."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", freezingPointValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+freezingPoint);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Lactose*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/7."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/7."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", lactoseValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+lactose);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*SNF*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/8."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/8."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", snfValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+snf);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Urea*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/9."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/9."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", ureaValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+urea);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Actual Quality*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/10."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualQuality);
                   jsonObject.put("hasResult", livestockFeatureURL+"qualityValue-"+actualQualityValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Perdicted Quality*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/11."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedQuality);
                   jsonObject.put("hasResult", livestockFeatureURL+"qualityValue-"+predictedQualityValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   hasResultJsonArray = new JSONArray();
           }
           br.close();	
           jsonld.put("@graph",liveStockJsonArray);
    	} catch (java.io.FileNotFoundException e) {
		   return "File Not Found !";
		} catch (ArrayIndexOutOfBoundsException e) {
		   return "File Not Found !";
		}
        return jsonld.toString().replace("\\", "");
	}
	
	/**
	 * Convert CSV to MilkQuality Training JSONLD
	 * 
	 * @return
	 */
	public String convertCSVtoMilkQualityTrainingJSONLD(String csvRFText) throws Exception {
		
		JSONObject jsonld = new JSONObject();
		File csvFolder = new File(csvFolderPath + milkQualityTrainingPrefixFileName);
		
		if (!csvRFText.equalsIgnoreCase("")){
			csvFolder = new File(csvFolderPath + milkQualityTrainingPrefixFileName + "/RF");
	    	csvFolder.mkdirs();
	    	BufferedWriter jbw;
	    	if (storage.equalsIgnoreCase("Y")){
	    		jbw = new BufferedWriter(new FileWriter(new File(csvFolder+"/"+"MQT_RandomForest_"+getCurrentDateTimeMS()+".csv")));
	    	}else {
	    		jbw = new BufferedWriter(new FileWriter(new File(csvFolder+"/"+"MQT_RandomForest.csv")));
	    	}
			jbw.write(csvRFText);
			jbw.close();
	    }
        
		File csvFolderFileList = new File(csvFolder.getPath());
		File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			
		} catch (NullPointerException e1) {
			return "File Not Found !";
		}
        
    	BufferedReader br = null;
    	try {
            br = new BufferedReader(new FileReader(csvListOfFiles[0].getPath()));
            String line = ""; 
            boolean metrics = false; 
            JSONArray contextJsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject();
        	JSONArray liveStockJsonArray = new JSONArray();
        	
        	/*Imposto i contesti*/
            contextJsonArray.put(agriContext);
            contextJsonArray.put(livestockFeatureContext);
            jsonObject.put("qudt-unit", qudtUnit);
            contextJsonArray.put(jsonObject);
            jsonld.put("@context", contextJsonArray);
            jsonObject = new JSONObject();
            
            /*Recupero i nomi dei campi*/
       	 	String[] fieldsNames = br.readLine().split(separator);
       	 	int index = 0;
            while ((line = br.readLine()) != null) {
	               JSONObject jsonObject1 = new JSONObject();
	               JSONObject jsonObject2 = new JSONObject();
	     		   JSONArray  hasMemberJsonArray = new JSONArray();
	     		   JSONArray  hasResultJsonArray = new JSONArray();
        	       String[] fieldsValues = line.split(separator); 
        	       String productNameValue = "";
        	       String productType = "";
        	       String fieldName = "";
        	       String resultTime = "";
        	       String acidityValue = "";
        	       String caseinValue = "";
        	       String densityValue = "";
        	       String fatValue = "";
        	       String proteinValue = "";
        	       String freezingPointValue = "";
        	       String lactoseValue = "";
        	       String snfValue = "";
        	       String ureaValue = "";
        	       String actualQualityValue = "";
        	       String predictedQualityValue = "";
        	       String rawTruePositiveRateValue = "";
        	       String rawFalsePositiveRateValue = "";
        	       String rawPrecisionValue = "";
        	       String rawAccuracyValue = "";
        	       String processedTruePositiveRateValue = "";
        	       String processedFalsePositiveRateValue = "";
        	       String processedPrecisionValue = "";
        	       String processedAccuracyValue = "";
        	       index++;
        	       
        	       /*Recupero i valori dei campi*/
	               for (int l = 0; l < fieldsValues.length; l++) { 
	        	   	   fieldName = fieldsNames[l].replace("\"", "").replace("/", "").replace(" ", "").replaceAll("[^\\p{ASCII}]", "");
		               if (fieldName.equalsIgnoreCase(productName)){
		            	   productNameValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(remark)){
		            	   productType = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(date)){
		            	   resultTime = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(acidity)){
		            	   acidityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(casein)){
		            	   caseinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(density)){
		            	   densityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(fat)){
		            	   fatValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(protein)){
		            	   proteinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(freezingPoint)){
		            	   freezingPointValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lactose)){
		            	   lactoseValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(snf)){
		            	   snfValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(urea)){
		            	   ureaValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualQuality)){
		            	   actualQualityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedQuality)){
		            	   predictedQualityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawTruePositiveRate)){
		            	   rawTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawFalsePositiveRate)){
		            	   rawFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawPrecision)){
		            	   rawPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawAccuracy)){
		            	   rawAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedTruePositiveRate)){
		            	   processedTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedFalsePositiveRate)){
		            	   processedFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedPrecision)){
		            	   processedPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedAccuracy)){
		            	   processedAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
	               } 
           	     
	               /*Metrics*/
                   if (!metrics) {
		    		   jsonObject.put("@id", "urn:demeter:predictionMetric"+index);
	                   jsonObject.put("@type", "PredictionMetric");
	                   jsonObject.put(rawTruePositiveRate, rawTruePositiveRateValue);
	                   jsonObject.put(rawFalsePositiveRate, rawFalsePositiveRateValue);
	                   jsonObject.put(rawPrecision, rawPrecisionValue);
	                   jsonObject.put(rawAccuracy, rawAccuracyValue);
	                   jsonObject.put(processedTruePositiveRate, processedTruePositiveRateValue);
	                   jsonObject.put(processedFalsePositiveRate, processedFalsePositiveRateValue);
	                   jsonObject.put(processedPrecision, processedPrecisionValue);
	                   jsonObject.put(processedAccuracy, processedAccuracyValue);
	                   
	                   liveStockJsonArray.put(jsonObject);
	                   jsonObject = new JSONObject();
                   }
                   
	    		   /*Milk Product*/
	    		   jsonObject.put("@id", "urn:demeter:milkProduct"+index);
                   jsonObject.put("@type", "MilkProduct");
                   jsonObject.put("productName", productNameValue);
                   jsonObject.put("productType", productType);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
	     		   /*Collection of observations*/
	     		   jsonObject.put("@id", "urn:demeter:"+UUID.randomUUID());
                   jsonObject.put("@type", "MilkQualityPrediction");
                   jsonObject.put("description", "Milk Quality Prediction collection of observations with identifier "+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/1."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/2."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/3."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/4."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/5."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/6."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/7."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/8."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/9."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/10."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/11."+index);
	    		   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("hasFeatureOfInterest", "urn:demeter:milkProduct"+index);
                   if (!metrics) {
	                   jsonObject.put("predictionMetric", "urn:demeter:predictionMetric"+index);
	                   metrics = true;
                   }
                   jsonObject.put("resultTime", resultTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
	     		  /*Acidity*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/1."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/1."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", acidityValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+acidity);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Casein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/2."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/2."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", caseinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+casein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Density*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/3."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/3."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", densityValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+density);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/4."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/4."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fat);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Protein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/5."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/5."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", proteinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+protein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Freezing Point*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/6."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/6."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", freezingPointValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+freezingPoint);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Lactose*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/7."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/7."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", lactoseValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+lactose);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*SNF*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/8."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/8."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", snfValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+snf);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Urea*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/9."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/9."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", ureaValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+urea);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Actual Quality*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/10."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualQuality);
                   jsonObject.put("hasResult", livestockFeatureURL+"qualityValue-"+actualQualityValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Perdicted Quality*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/11."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedQuality);
                   jsonObject.put("hasResult", livestockFeatureURL+"qualityValue-"+predictedQualityValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   hasResultJsonArray = new JSONArray();
           }
           br.close();	
           jsonld.put("@graph",liveStockJsonArray);
    	} catch (java.io.FileNotFoundException e) {
		   return "File Not Found !";
		} catch (ArrayIndexOutOfBoundsException e) {
		   return "File Not Found !";
		}
        return jsonld.toString().replace("\\", "");
	}
	
	/**
	 * Convert CSV to MilkQuality Prediction JSONLD
	 * 
	 * @return
	 */
	public String convertCSVtoMilkQualityPredictionJSONLD(String csvRFText) throws Exception {
		
		JSONObject jsonld = new JSONObject();
		File csvFolder = new File(csvFolderPath + milkQualityPredictionPrefixFileName);
		
		if (!csvRFText.equalsIgnoreCase("")){
			csvFolder = new File(csvFolderPath + milkQualityPredictionPrefixFileName + "/RF");
	    	csvFolder.mkdirs();
	    	BufferedWriter jbw;
	    	if (storage.equalsIgnoreCase("Y")){
	    		jbw = new BufferedWriter(new FileWriter(new File(csvFolder+"/"+"MQP_RandomForest_"+getCurrentDateTimeMS()+".csv")));
	    	}else {
	    		jbw = new BufferedWriter(new FileWriter(new File(csvFolder+"/"+"MQP_RandomForest.csv")));
	    	}
			jbw.write(csvRFText);
			jbw.close();
	    }
        
		File csvFolderFileList = new File(csvFolder.getPath());
		File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			
		} catch (NullPointerException e1) {
			return "File Not Found !";
		}
        
    	BufferedReader br = null;
    	try {
            br = new BufferedReader(new FileReader(csvListOfFiles[0].getPath()));
            String line = ""; 
            boolean metrics = false; 
            JSONArray contextJsonArray = new JSONArray();
        	JSONObject jsonObject = new JSONObject();
        	JSONArray liveStockJsonArray = new JSONArray();
        	
        	/*Imposto i contesti*/
            contextJsonArray.put(agriContext);
            contextJsonArray.put(livestockFeatureContext);
            jsonObject.put("qudt-unit", qudtUnit);
            contextJsonArray.put(jsonObject);
            jsonld.put("@context", contextJsonArray);
            jsonObject = new JSONObject();
            
            /*Recupero i nomi dei campi*/
       	 	String[] fieldsNames = br.readLine().split(separator);
       	 	int index = 0;
            while ((line = br.readLine()) != null) {
	               JSONObject jsonObject1 = new JSONObject();
	               JSONObject jsonObject2 = new JSONObject();
	     		   JSONArray  hasMemberJsonArray = new JSONArray();
	     		   JSONArray  hasResultJsonArray = new JSONArray();
        	       String[] fieldsValues = line.split(separator); 
        	       String productNameValue = "";
        	       String productType = "";
        	       String fieldName = "";
        	       String resultTime = "";
        	       String acidityValue = "";
        	       String caseinValue = "";
        	       String densityValue = "";
        	       String fatValue = "";
        	       String proteinValue = "";
        	       String freezingPointValue = "";
        	       String lactoseValue = "";
        	       String snfValue = "";
        	       String ureaValue = "";
        	       String actualQualityValue = "";
        	       String predictedQualityValue = "";
        	       String rawTruePositiveRateValue = "";
        	       String rawFalsePositiveRateValue = "";
        	       String rawPrecisionValue = "";
        	       String rawAccuracyValue = "";
        	       String processedTruePositiveRateValue = "";
        	       String processedFalsePositiveRateValue = "";
        	       String processedPrecisionValue = "";
        	       String processedAccuracyValue = "";
        	       index++;
        	       
        	       /*Recupero i valori dei campi*/
	               for (int l = 0; l < fieldsValues.length; l++) { 
	        	   	   fieldName = fieldsNames[l].replace("\"", "").replace("/", "").replace(" ", "").replaceAll("[^\\p{ASCII}]", "");
		               if (fieldName.equalsIgnoreCase(productName)){
		            	   productNameValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(remark)){
		            	   productType = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(date)){
		            	   resultTime = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(acidity)){
		            	   acidityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(casein)){
		            	   caseinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(density)){
		            	   densityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(fat)){
		            	   fatValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(protein)){
		            	   proteinValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(freezingPoint)){
		            	   freezingPointValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(lactose)){
		            	   lactoseValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(snf)){
		            	   snfValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(urea)){
		            	   ureaValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(actualQuality)){
		            	   actualQualityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(predictedQuality)){
		            	   predictedQualityValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawTruePositiveRate)){
		            	   rawTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawFalsePositiveRate)){
		            	   rawFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawPrecision)){
		            	   rawPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(rawAccuracy)){
		            	   rawAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedTruePositiveRate)){
		            	   processedTruePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedFalsePositiveRate)){
		            	   processedFalsePositiveRateValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedPrecision)){
		            	   processedPrecisionValue = fieldsValues[l].replace("\"", "");
		               }
		               if (fieldName.equalsIgnoreCase(processedAccuracy)){
		            	   processedAccuracyValue = fieldsValues[l].replace("\"", "");
		               }
	               } 
           	     
	               /*Metrics*/
                   if (!metrics) {
		    		   jsonObject.put("@id", "urn:demeter:predictionMetric"+index);
	                   jsonObject.put("@type", "PredictionMetric");
	                   jsonObject.put(rawTruePositiveRate, rawTruePositiveRateValue);
	                   jsonObject.put(rawFalsePositiveRate, rawFalsePositiveRateValue);
	                   jsonObject.put(rawPrecision, rawPrecisionValue);
	                   jsonObject.put(rawAccuracy, rawAccuracyValue);
	                   jsonObject.put(processedTruePositiveRate, processedTruePositiveRateValue);
	                   jsonObject.put(processedFalsePositiveRate, processedFalsePositiveRateValue);
	                   jsonObject.put(processedPrecision, processedPrecisionValue);
	                   jsonObject.put(processedAccuracy, processedAccuracyValue);
	                   
	                   liveStockJsonArray.put(jsonObject);
	                   jsonObject = new JSONObject();
                   }
                   
	    		   /*Milk Product*/
	    		   jsonObject.put("@id", "urn:demeter:milkProduct"+index);
                   jsonObject.put("@type", "MilkProduct");
                   jsonObject.put("productName", productNameValue);
                   jsonObject.put("productType", productType);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   
	     		   /*Collection of observations*/
	     		   jsonObject.put("@id", "urn:demeter:"+UUID.randomUUID());
                   jsonObject.put("@type", "MilkQualityPrediction");
                   jsonObject.put("description", "Milk Quality Prediction collection of observations with identifier "+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/1."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/2."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/3."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/4."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/5."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/6."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/7."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/8."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/9."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/10."+index);
	    		   hasMemberJsonArray.put("urn:demeter:ObservationI/11."+index);
	    		   jsonObject.put("hasMember", hasMemberJsonArray);
                   jsonObject.put("hasFeatureOfInterest", "urn:demeter:milkProduct"+index);
                   if (!metrics) {
	                   jsonObject.put("predictionMetric", "urn:demeter:predictionMetric"+index);
	                   metrics = true;
                   }
                   jsonObject.put("resultTime", resultTime);
                   
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
	     		   hasMemberJsonArray = new JSONArray();
                   
	     		  /*Acidity*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/1."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/1."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", acidityValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+acidity);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Casein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/2."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/2."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", caseinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+casein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Density*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/3."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/3."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", densityValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+density);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Fat*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/4."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/4."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", fatValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+fat);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Protein*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/5."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/5."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", proteinValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+protein);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Freezing Point*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/6."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/6."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", freezingPointValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+freezingPoint);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Lactose*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/7."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/7."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", lactoseValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+lactose);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*SNF*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/8."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/8."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", snfValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+snf);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Urea*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/9."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject1.put("@id", "urn:demeter:ObservationI/9."+index+".r");
                   jsonObject1.put("@type", "QuantityValue");
                   jsonObject1.put("numericValue", ureaValue);
                   jsonObject1.put("unit", "qudt-unit:xyz");
                   hasResultJsonArray.put(jsonObject1);
                   jsonObject.put("hasResult", hasResultJsonArray);
                   jsonObject.put("observedProperty", livestockFeatureURL+urea);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   jsonObject1 = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Actual Quality*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/10."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+actualQuality);
                   jsonObject.put("hasResult", livestockFeatureURL+"qualityValue-"+actualQualityValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   hasResultJsonArray = new JSONArray();
                   
                   /*Perdicted Quality*/
                   jsonObject.put("@id", "urn:demeter:ObservationI/11."+index);
                   jsonObject.put("@type", "Observation");
                   jsonObject.put("observedProperty", livestockFeatureURL+predictedQuality);
                   jsonObject.put("hasResult", livestockFeatureURL+"qualityValue-"+predictedQualityValue);
                   liveStockJsonArray.put(jsonObject);
                   jsonObject = new JSONObject();
                   hasResultJsonArray = new JSONArray();
           }
           br.close();	
           jsonld.put("@graph",liveStockJsonArray);
    	} catch (java.io.FileNotFoundException e) {
		   return "File Not Found !";
		} catch (ArrayIndexOutOfBoundsException e) {
		   return "File Not Found !";
		}
        return jsonld.toString().replace("\\", "");
	}
	
	/**
	 * Get Milk Analysis CSV
	 * 
	 * @return
	 */
	public File getMilkAnalysisCSV() throws Exception {
		
		
		File csvFolder = new File(csvFolderPath + milkAnalysisPrefixFileName);
        
		File csvFolderFileList = new File(csvFolder.getPath());
		File[] csvListOfFiles = csvFolderFileList.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        
        try {
			Arrays.sort(csvListOfFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			return csvListOfFiles[0];
		} catch (NullPointerException e1) {
			return null;
		}
        
    	
	}
}
