package it.eng.demeter.servlet;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private String storage;
	private String csvFolderPath;
    private String animalWelfareTrainingPrefixFileName;
    private String animalWelfarePredictionPrefixFileName;
    private String milkQualityTrainingPrefixFileName;
    private String milkQualityPredictionPrefixFileName;
    private String milkAnalysisPrefixFileName;
    private File file ;
	
    private static ResourceBundle properties = ResourceBundle.getBundle("resources/configuration");

    public void init( ){
    	storage = System.getenv("CSV_STORAGE");
		csvFolderPath = properties.getString("demeter.csv-folder-path");
		animalWelfareTrainingPrefixFileName = properties.getString("demeter.animalWelfareTrainingPrefixFileName");
		animalWelfarePredictionPrefixFileName = properties.getString("demeter.animalWelfarePredictionPrefixFileName");
		milkQualityTrainingPrefixFileName = properties.getString("demeter.milkQualityTrainingPrefixFileName");
		milkQualityPredictionPrefixFileName = properties.getString("demeter.milkQualityPredictionPrefixFileName");
		milkAnalysisPrefixFileName = properties.getString("demeter.milkAnalysisPrefixFileName");
    }
   
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
	    
    	response.setContentType("text/html");
	    String report = "";
	    
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setRepository(new File(csvFolderPath));  	
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    
	    try{ 
	    	List fileItems = upload.parseRequest(request); 
	    	Iterator i = fileItems.iterator();
	    	
	        List<String> csvFileNames = new ArrayList<String>(); 
	        
	    	while (i.hasNext ()) {
	    		FileItem fi = (FileItem)i.next();
	    		if (!fi.isFormField()){
			        String csvFileName = fi.getName(); 
			        File csvFolder = null;
			        if (csvFileName.contains(animalWelfareTrainingPrefixFileName)){
			        	csvFolder = new File(csvFolderPath + animalWelfareTrainingPrefixFileName);  
			        }
			        if (csvFileName.contains(animalWelfarePredictionPrefixFileName)){
			        	csvFolder = new File(csvFolderPath + animalWelfarePredictionPrefixFileName);   
			        }
			        if (csvFileName.contains(milkQualityTrainingPrefixFileName)){
			        	csvFolder = new File(csvFolderPath + milkQualityTrainingPrefixFileName);
			        }
			        if (csvFileName.contains(milkQualityPredictionPrefixFileName)){
			        	csvFolder = new File(csvFolderPath + milkQualityPredictionPrefixFileName);
			        }
			        if (csvFileName.contains(milkAnalysisPrefixFileName)){
			        	csvFolder = new File(csvFolderPath + milkAnalysisPrefixFileName);
			        }
			        csvFolder.mkdir();
			        if (storage.equalsIgnoreCase("Y")){
			        	String name = "";
				        String extension = "";
				        name = csvFileName.split("\\.")[0];
				        extension = csvFileName.split("\\.")[1];
				        Date dNow = new Date();
				        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd_hhmmss_Ms");
				        String datetime = ft.format(dNow);
				        csvFileName = name + "_" + datetime+ "." + extension;
			        }
			        if(csvFileName.lastIndexOf("/") >= 0 ){
				       file = new File(csvFolder + "/" + csvFileName.substring(csvFileName.lastIndexOf("/"))) ;
				    }else{
				       file = new File(csvFolder + "/" + csvFileName.substring(csvFileName.lastIndexOf("/")+1)) ;
				    }
			        fi.write(file);
					csvFileNames.add(csvFileName);
	    		}	
	    	}
	    	
	    	report += "CSV files uploaded to the folder <strong>" + csvFolderPath + "</strong>";
	    	report += "<br/><br/>";
	    	
	    	report += "<ul>";
    		for(int l = 0; l < csvFileNames.size(); l++) {
		        report += "<li><strong>" + csvFileNames.get(l) + "</strong></li>";
    		}
    		report += "</ul>";
    		
		  }catch(Exception ex) {
		     System.out.println(ex);
		  }
	    
	      String uploadJSP = "/jsp/upload.jsp";
          RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(uploadJSP);
          request.setAttribute("report", report);
          dispatcher.forward(request, response);
	 }
    
     public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
           throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
     } 
}