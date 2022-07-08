<%@ include file="init.jsp" %>

<div id="uploadContainer" ><br/>
	<div class="panel panel-default">
        <div id="headLogo" style="background-color: #4AC3C1;padding:20px;">
		      <img src="<%=request.getContextPath()%>/images/Logo.png"></img>
		</div>
		<div class="uploadTitle">
			<h1 style="text-align: center; font-family: sans-serif">Upload CSV for Animal Welfare (AW), Milk Quality (MQ) and Milk Analysis (MA)</h1>
		</div>
		<br/><br/>
		<div class="panel-body">
			<div class="input-group">
				<div id="centralLogo" style="margin-left:200px">
					<img src="https://h2020-demeter.eu/wp-content/uploads/2019/11/DEMETER.png"></img>
				</div>
				<form id="formUpload" method="post" action="Upload" enctype="multipart/form-data">
					<div id="selectCSV" style="margin-left:850px">
						<label for="file" class="button buttonSelect">Select</label>					
						<input type="file" id="file" multiple="multiple" accept=".csv" name="file" onchange="updateFilesList()"/>
					</div>
					<br/>
					<br/>	
					<div id="filesList" style="margin-left:850px"></div>
				    <br/><br/>
				    <div id="uploadCSV" style="margin-left:850px">		
						<input type="submit" id="uploadFiles" class="button buttonUpload" style="display:none;" value="Upload"/>
						<button type="reset" id="clearList" class="button buttonClearList" style="display:none" onClick="clearFilesList();">Clear</button>
					</div>
					<br/><br/>
					<c:if test="${not empty report}">
						<div id="reportContainer"><br/>
							<div id="report" style="margin-left:710px">
								<h1 style="text-align: center; font-family: sans-serif">Report</h1>
								<br/><br/>			
								${report}
								<br/><br/>
								<button type="reset" id="clearReportId" class="button buttonClearReport" onClick="clearReport();">Clear report</button>		
								<br/><br/>
							</div>
						</div>
					</c:if>
				</form>			 
			</div>					
		</div>
		<div id="bottomLogo" style="text-align: center;background-color: #F7F7F7;padding:20px;">
		      <img src="<%=request.getContextPath()%>/images/Eng.png"></img>
		      <img src="<%=request.getContextPath()%>/images/Maccarese.png"></img>
		      <img src="<%=request.getContextPath()%>/images/Lattesano.png"></img>
		</div> 
	</div>	
</div>		

