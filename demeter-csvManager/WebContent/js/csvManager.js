function clearReport() {
  	document.getElementById("report").style.display = "none";
  	document.getElementById("selectCSV").style.display = "inline-block";
}
function updateFilesList() {
  	var input = document.getElementById('file');
  	var output = document.getElementById('filesList');
  	output.innerHTML = '<ul>';
  	for (var i = 0; i < input.files.length; ++i) {
    	output.innerHTML += '<li>' + input.files.item(i).name + '</li>';
  	}
  	output.innerHTML += '</ul>';
  	document.getElementById("uploadFiles").style.display = "inline-block";
  	document.getElementById("clearList").style.display = "inline-block";
  	document.getElementById("selectCSV").style.display = "none";
}
function clearFilesList(){
	document.getElementById("file").value = "";
	var output = document.getElementById('filesList');
	output.innerHTML = "";
	document.getElementById("uploadFiles").style.display = "none";
  	document.getElementById("clearList").style.display = "none";
	document.getElementById("selectCSV").style.display = "inline-block";
}