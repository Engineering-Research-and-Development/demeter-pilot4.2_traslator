function clearReport() {
  	document.getElementById("reportId").style.display = "none";
}
function updateFileList() {
  	var input = document.getElementById('file');
  	var output = document.getElementById('fileListId');
  	output.innerHTML = '<ul>';
  	for (var i = 0; i < input.files.length; ++i) {
    	output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  	}
  	output.innerHTML += '</ul>';
  	document.getElementById("submitFormId").style.display = "inline-block";
  	document.getElementById("clearFileListId").style.display = "inline-block";
}
function clearFileList(){
	document.getElementById("file").value = "";
	var output = document.getElementById('fileListId');
	output.innerHTML = "";
	document.getElementById("submitFormId").style.display = "none";
	document.getElementById("clearFileListId").style.display = "none";
}