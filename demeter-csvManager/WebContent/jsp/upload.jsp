<%@ include file="init.jsp" %>

<div id="uploadContainerId" class="container"><br/>
    <div class="row">
    	<div class="col-md-12">
			<div class="panel panel-default">
				<div class="uploadtitle">
					<strong>Upload form</strong>
				</div>
				<br/><br/>
				<div class="panel-body">
					<div class="input-group">
						<form id="formId" method="post" action="Upload" enctype="multipart/form-data">
							<label for="file" class="uploadFileLabel">Select files</label>
							<input type="file" id="file" multiple="multiple" accept=".csv" name="file" onchange="updateFileList()"/>
							<br/>
							<br/>
							<div id="fileListId"></div>
							<br/><br/>
							<input type="submit" id="submitFormId" class="submitForm" style="display:none" value="Upload files"/>
							<button type="reset" id="clearFileListId" class="clearFileList" style="display:none" onClick="clearFileList();">Clear list</button>
						</form>			 
					</div>					
				</div>
			</div>
		</div>
	</div>
</div>		
<c:if test="${not empty report}">
	<div id="reportContainerId" class="container"><br/>
		<div class="row">
	    	<div class="col-md-12">
				<div id="reportId" class="panel panel-default">
					<div class="reportTitle">
						<strong>Report</strong>
					</div>
					<br/><br/>
					<div class="panel-body">
						<div class="input-group">
							<form>
								${report}
								<br/><br/>
								<button type="reset" id="clearReportId" class="clearReport" onClick="clearReport();">Clear report</button>		
							</form> 
						</div>					
					</div>
				</div>
			</div>
		</div>
	</div>	
</c:if>
