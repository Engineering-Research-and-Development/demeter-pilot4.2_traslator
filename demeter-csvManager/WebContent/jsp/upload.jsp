<%@ include file="init.jsp" %>

<div id="headLogo" class="header">
   <img src="<%=request.getContextPath()%>/images/Logo.png"></img>
</div>
<div id="bodyRow" class="row">
	<div id="bodyLeft" class="col-3 col-s-3"></div>
	<div id="bodyCenter" class="col-6 col-s-9" style="text-align:center;">
		<h1>Upload CSV</h1>
		<h2>Files must start with :</h2>	
		<h3>
			<ul style="text-align:left;">
		      <li>AW_ for Animal Welfare</li>
		      <li>MQ_ for Milk Quality</li>
		      <li>MA_ for Milk Analysis</li>
		    </ul>
	    </h3>
		<br/><br/>
		<form id="formUpload" method="post" action="Upload" enctype="multipart/form-data">
			<div id="selectCSV">
				<label for="file" class="button buttonSelect">Select</label>					
				<input type="file" id="file" multiple="multiple" accept=".csv" name="file" onchange="updateFilesList()"/>
			</div>
			<br/>
			<br/>	
			<div id="filesList"></div>
		    <br/><br/>
		    <div id="uploadCSV">		
				<input type="submit" id="uploadFiles" class="button buttonUpload" style="display:none;" value="Upload"/>
				<button type="reset" id="clearList" class="button buttonClearList" style="display:none" onClick="clearFilesList();">Clear</button>
			</div>
			<br/><br/>
			<c:if test="${not empty report}">
				<div id="reportContainer"><br/>
					<div id="reportTitle">
						<h2>Report</h2>
					</div>
					<div id="report">	
						<br/><br/>
						<h3 style="text-align:left;">			
						${report}
						</h3>
						<br/><br/>
						<button type="reset" id="clearReportId" class="button buttonClearReport" onClick="clearReport();">Clear report</button>		
						<br/><br/>
					</div>
				</div>
			</c:if>
		</form>			 
	</div>
	<div id="bodyRight" class="col-3 col-s-12"></div>
</div>	
<div id="bottomLogo" class="footer">
      <img src="<%=request.getContextPath()%>/images/Eng.png"></img>
      <img src="<%=request.getContextPath()%>/images/Maccarese.png"></img>
      <img src="<%=request.getContextPath()%>/images/Lattesano.png"></img>
</div> 
