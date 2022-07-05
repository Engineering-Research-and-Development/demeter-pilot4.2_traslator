package reverseTranslator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class main {
	
   public static void main(String[] args) throws Exception, JSONException{
      animalWelfareReverseTranslator("http://localhost:9080/demeter-csvManager/rest/traslator/v1/AnimalWelfareTraining");
      milkQualityReverseTranslator("http://localhost:9080/demeter-csvManager/rest/traslator/v1/MilkQualityPrediction");
   }

   public static String animalWelfareReverseTranslator(String urlToRead) throws Exception, JSONException {
      
	  /*Chiamo il servizio di acquisizione AIM*/
	  StringBuilder aim = new StringBuilder();
      URL url = new URL(urlToRead);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
         aim.append(line);
      }
      rd.close();
      
      /*Conversione stringa AIM in JSON*/
      JSONObject jsonObject = new JSONObject(aim.toString());  
      JSONArray CSVjsonArray = new JSONArray(jsonObject.get("@graph").toString());
      
      /*Posizioni dei campi all'interno dell'AIM*/
      int cowPos = 0;
      int indexPos = 1;
      int datePos = 2;
      int pedometerPos = 7;
      int midPos = 8;
      int lactationsPos = 9;
      int dailyProdPos = 10;
      int averageDailyProdPos= 11;
      int dailyFatPos = 12;
      int dailyProteinsPos = 13;
      int dailyFatProteinsPos = 14;
      int tdlPos = 15;
      int cond1Pos = 24;
      int cond2Pos = 25;
      int cond3Pos = 26;
      int act1Pos = 27;
      int act2Pos = 28;
      int act3Pos = 29;
      int rum1Pos = 30;
      int rum2Pos = 31;
      int rum3Pos = 32;
      int ing1Pos = 33;
      int ing2Pos = 34;
      int ing3Pos = 35;
      int actualLamenessPos = 16;
      int predictedLamenessPos = 17;
      int actualKetosisPos = 18;
      int predictedKetosisPos = 19;
      int actualMastitisPos = 20;
      int predictedMastitisPos = 21;
      int actualHeatStressPos = 22;
      int predictedHeatStressPos = 23;
      
      String actualLamenessValue = "";
      String predictedLamenessValue = "";
      String actualKetosisValue = "";
      String predictedKetosisValue = "";
      String actualMastitisValue = "";
      String predictedMastitisValue = "";
      String actualHeatStressValue = "";
      String predictedHeatStressValue = "";
      
      String rows = "";
      rows = "<ROWS>";
      
      /*Ogni elemento del grafo è costitituito da 36 sezioni, ad eccezione della prima predizione, a cui sono associate le metriche, che non vanno considerate*/
      for (int l=1; l<CSVjsonArray.length(); l+=36){
    	  try {
			  if (CSVjsonArray.getJSONObject(actualLamenessPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Healthy"))  {
				  actualLamenessValue = "Healthy";
			  }
			  if (CSVjsonArray.getJSONObject(actualLamenessPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Sick"))  {
				  actualLamenessValue = "Sick";
			  }
			  if (CSVjsonArray.getJSONObject(predictedLamenessPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Healthy"))  {
				  predictedLamenessValue = "Healthy";
			  }
			  if (CSVjsonArray.getJSONObject(predictedLamenessPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Sick"))  {
				  predictedLamenessValue = "Sick";
			  }
		
			  if (CSVjsonArray.getJSONObject(actualKetosisPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Healthy"))  {
				  actualKetosisValue = "Healthy";
			  }
			  if (CSVjsonArray.getJSONObject(actualKetosisPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Sick"))  {
				  actualKetosisValue = "Sick";
			  }
			  if (CSVjsonArray.getJSONObject(predictedKetosisPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Healthy"))  {
				  predictedKetosisValue = "Healthy";
			  }
			  if (CSVjsonArray.getJSONObject(predictedKetosisPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Sick"))  {
				  predictedKetosisValue = "Sick";
			  }
			  
			  if (CSVjsonArray.getJSONObject(actualMastitisPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Healthy"))  {
				  actualMastitisValue = "Healthy";
			  }
			  if (CSVjsonArray.getJSONObject(actualMastitisPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Sick"))  {
				  actualMastitisValue = "Sick";
			  }
			  if (CSVjsonArray.getJSONObject(predictedMastitisPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Healthy"))  {
				  predictedMastitisValue = "Healthy";
			  }
			  if (CSVjsonArray.getJSONObject(predictedMastitisPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Sick"))  {
				  predictedMastitisValue = "Sick";
			  }
			  
			  if (CSVjsonArray.getJSONObject(actualHeatStressPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Healthy"))  {
				  actualHeatStressValue = "Healthy";
			  }
			  if (CSVjsonArray.getJSONObject(actualHeatStressPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Stressed"))  {
				  actualHeatStressValue = "Stressed";
			  }
			  if (CSVjsonArray.getJSONObject(predictedHeatStressPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Healthy"))  {
				  predictedHeatStressValue = "Healthy";
			  }
			  if (CSVjsonArray.getJSONObject(predictedHeatStressPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("healthStatus-Stressed"))  {
				  predictedHeatStressValue = "Stressed";
			  }
			  
			  rows += "<ROW Index=\"" + CSVjsonArray.getJSONObject(indexPos+l).get("indentifier") 
	        		   + "\" Date=\"" + CSVjsonArray.getJSONObject(datePos+l).get("resultTime") 
	        		   + "\" Pedometer=\"" + CSVjsonArray.getJSONObject(pedometerPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" Cow=\"" + CSVjsonArray.getJSONObject(cowPos+l).get("livestockNumber") 
	        		   + "\" MID=\"" + CSVjsonArray.getJSONObject(midPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" Lactations=\"" + CSVjsonArray.getJSONObject(lactationsPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" DailyProduction=\"" + CSVjsonArray.getJSONObject(dailyProdPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" AverageDailyProduction=\"" + CSVjsonArray.getJSONObject(averageDailyProdPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" DailyFat=\"" + CSVjsonArray.getJSONObject(dailyFatPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" DailyProteins=\"" + CSVjsonArray.getJSONObject(dailyProteinsPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" DailyFatProteins=\"" + CSVjsonArray.getJSONObject(dailyFatProteinsPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" Conductivity1=\"" + CSVjsonArray.getJSONObject(cond1Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" Conductivity2=\"" + CSVjsonArray.getJSONObject(cond2Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" Conductivity3=\"" + CSVjsonArray.getJSONObject(cond3Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" Activity1=\"" + CSVjsonArray.getJSONObject(act1Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" Activity2=\"" + CSVjsonArray.getJSONObject(act2Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" Activity3=\"" + CSVjsonArray.getJSONObject(act3Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		   + "\" AverageRuminationTime1=\"" + CSVjsonArray.getJSONObject(rum1Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		   + "\" AverageRuminationTime2=\"" + CSVjsonArray.getJSONObject(rum2Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		   + "\" AverageRuminationTime3=\"" + CSVjsonArray.getJSONObject(rum3Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		   + "\" AverageIngestionTime1=\"" + CSVjsonArray.getJSONObject(ing1Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		   + "\" AverageIngestionTime2=\"" + CSVjsonArray.getJSONObject(ing2Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		   + "\" AverageIngestionTime3=\"" + CSVjsonArray.getJSONObject(ing3Pos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		   + "\" TotalDailyLying=\"" + CSVjsonArray.getJSONObject(tdlPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		   + "\" ActualLameness=\"" + actualLamenessValue 
	        		   + "\" PredictedLameness=\"" + predictedLamenessValue 
	        		   + "\" ActualKetosis=\"" + actualKetosisValue 
	        		   + "\" PredictedKetosis=\"" + predictedKetosisValue 
	        		   + "\" ActualMastitis=\"" + actualMastitisValue 
	        		   + "\" PredictedMastitis=\"" + predictedMastitisValue
	        		   + "\" ActualHeatStress=\"" + actualHeatStressValue 
	        		   + "\" PredictedHeatStress=\"" + predictedHeatStressValue
	        		   + "\"/>";
			  
    	  } catch (JSONException e) {};
   	  }
      rows += "</ROWS>";
      
      return rows;
   }
   
   public static String milkQualityReverseTranslator(String urlToRead) throws Exception, JSONException {
	      
      /*Chiamo il servizio di acquisizione AIM*/
      StringBuilder aim = new StringBuilder();
      URL url = new URL(urlToRead);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = rd.readLine()) != null) {
         aim.append(line);
      }
      rd.close();
      
      /*Conversione della stringa AIM in JSON*/
      JSONObject jsonObject = new JSONObject(aim.toString());
      JSONArray CSVjsonArray = new JSONArray(jsonObject.get("@graph").toString());
      
      /*Posizioni dei campi all'interno dell'AIM*/
      int datePos = 1;
      int timePos = 1;
      int productNamePos = 0;
      int acidityPos = 2;
      int caseinPos = 3;
      int densityPos= 4;
      int fatPos = 5;
      int freezingPointPos = 7;
      int lactosePos = 8;
      int proteinPos = 6;
      int snfPos = 9;
      int ureaPos = 10;
      int remarkPos = 0;
      int actualQualityPos = 11;
      int predictedQualityPos = 12;
      
      String actualQualityValue = "";
      String predictedQualityValue = "";
      
      String rows = "";
      rows = "<ROWS>";
      
      /*Ogni elemento del grafo è costitituito da 13 sezioni, ad eccezione della prima predizione, a cui sono associate le metriche, che non vanno considerate*/
      for (int l = 1; l < CSVjsonArray.length(); l+=13){
		  try {
			  if (CSVjsonArray.getJSONObject(actualQualityPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("qualityValue-High"))  {
				  actualQualityValue = "High";
			  }
			  if (CSVjsonArray.getJSONObject(actualQualityPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("qualityValue-Medium"))  {
				  actualQualityValue = "Medium";
			  }
			  if (CSVjsonArray.getJSONObject(actualQualityPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("qualityValue-Low"))  {
				  actualQualityValue = "Low";
			  }
			  if (CSVjsonArray.getJSONObject(predictedQualityPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("qualityValue-High"))  {
				  predictedQualityValue = "High";
			  }
			  if (CSVjsonArray.getJSONObject(predictedQualityPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("qualityValue-Medium"))  {
				  predictedQualityValue = "Medium";
			  }
			  if (CSVjsonArray.getJSONObject(predictedQualityPos+l).get("hasResult").toString().split("#")[1].equalsIgnoreCase("qualityValue-Low"))  {
				  predictedQualityValue = "Low";
			  }
			 
	          rows += "<ROW Date=\"" + CSVjsonArray.getJSONObject(datePos+l).get("resultTime") 
	        		     + "\" Time=\"" + CSVjsonArray.getJSONObject(timePos+l).get("resultTime") 
	        		     + "\" ProductName=\"" + CSVjsonArray.getJSONObject(productNamePos+l).get("productName") 
	        		     + "\" AciditySH=\"" + CSVjsonArray.getJSONObject(acidityPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		     + "\" Casein=\"" + CSVjsonArray.getJSONObject(caseinPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		     + "\" Density=\"" + CSVjsonArray.getJSONObject(densityPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		     + "\" Fat=\"" + CSVjsonArray.getJSONObject(fatPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		     + "\" FreezingPointMC=\"" + CSVjsonArray.getJSONObject(freezingPointPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue") 
	        		     + "\" Lactose=\"" + CSVjsonArray.getJSONObject(lactosePos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		     + "\" Protein=\"" + CSVjsonArray.getJSONObject(proteinPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		     + "\" SNF=\"" + CSVjsonArray.getJSONObject(snfPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		     + "\" Urea=\"" + CSVjsonArray.getJSONObject(ureaPos+l).getJSONArray("hasResult").getJSONObject(0).get("numericValue")
	        		     + "\" Remark=\"" + CSVjsonArray.getJSONObject(remarkPos+l).get("productType")
	        		     + "\" ActualQuality=\"" + actualQualityValue
	        		     + "\" PredictedQuality=\"" + predictedQualityValue
	        		     + "\" />";
	          
		  } catch (JSONException e) {};
   	  }
      rows += "</ROWS>";
     
      return rows;
   }
}
