function clearReport() {
  	document.getElementById("report").style.display = "none";
  	document.getElementById("reportTitle").style.display = "none";
  	document.getElementById("selectCSV").style.display = "inline-block";
}
function updateFilesList() {
  	var input = document.getElementById('file');
  	var output = document.getElementById('filesList');
  	var output1;
  	var output2;
  	var output3;
  	output3 = '<h3>You have selected the following files :';
  	output3 += '<br/><br/>';
  	output2 = '<ul style="text-align:left;">';
  	for (var i = 0; i < input.files.length; i++) {
  		if (i==0){
  			output1 = '<li>' + input.files.item(i).name + '</li>';
  		}else{
  			output1 += '<li>' + input.files.item(i).name + '</li>';
  		}
  	}
  	output2 += output1;
  	output2 += '</ul>';
  	output3 += output2;
  	output3 += '</h3>';
  	output.innerHTML += output3;
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