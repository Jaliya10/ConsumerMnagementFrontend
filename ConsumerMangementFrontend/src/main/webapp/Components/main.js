$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$('#alertError').hide();
});


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear status msges-------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation----------------
	var status = validateItemForm();
	
	// If not valid-------------------
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid----------------------- 
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
		{
			url: "ConsumerAPI",
			type: type,
			data: $("#formItem").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onItemSaveComplete(response.responseText, status);
			}
		});
});


function validateItemForm() {
	// regNo
	if ($("#txtReg").val().trim() == "") {
		return "Insert Registration Number.";
	}
	
	// NAME
	if ($("#txtName").val().trim() == "") {
		return "Insert name.";
	}
	
	// Address
	if ($("#txtAddress").val().trim() == "") {
		return "Insert address";
	}
	
	// Phone
	if ($("#txtPhone").val().trim() == "") {
		return "Insert phone";
	}
	
	// RegDate
	if ($("#txtRegDate").val().trim() == "") {
		return "Insert registration date";
	}
	
	// Usage
	if ($("#txtUsage").val().trim() == "") {
		return "Insert usage";
	}

	/*
	// RegDate
	if ($("#txtRegDate").val() == "0") {
		return "Select year.";
	}*/
	
	return true;
}

function getStudentCard(regNo, name, address, phone, regDate, usage, type) {
	var regno = "";
	var name = "";
	var address = "";
	var phone = "";
	var date = "";
	var usage = "";
	var type = "";
	var consumer = "";
	consumer += "<div class=\"student card bg-light m-2\" style =\"max-width: 10rem; float: left;\">";
	consumer += "<div class=\"card-body\">";
	consumer += regno + " " + regNo + ",";
	consumer += "<br>";
	consumer += name + " " + name + ",";
	consumer += "<br>";
	consumer += address + " " + address + ",";
	consumer += "<br>";
	consumer += phone + " " + phone + ",";
	consumer += "<br>";
	consumer += date + " " + regDate + ",";
	consumer += "<br>";
	consumer += usage + " " + usage + ",";
	consumer += "<br>";
	consumer += type + " " + type + ",";
	consumer += "<br>";
	consumer += "</div>";
	consumer += "<input type=\"button\" value=\"Remove\" class=\"btn btn-danger remove\">";
	consumer += "</div>";
	
	return consumer;
}

//delete
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "ConsumerAPI",
			type: "DELETE",
			data: "id=" + $(this).data("id"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		}); 
		
/* commented after lab10
	$(this).closest(".student").remove();

	$("#alertSuccess").text("Removed successfully.");
	$("#alertSuccess").show();*/
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) {
	//$("#hidItemIDSave").val($(this).data("id"));
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val()); //changed after lab10
	//$("#id").val($(this).closest("tr").find('td:eq(0)').text()); 					//check the availability of id //this aso has id
	$("#txtReg").val($(this).closest("tr").find('td:eq(0)').text());
	$("#txtName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#txtAddress").val($(this).closest("tr").find('td:eq(2)').text());
	$("#txtPhone").val($(this).closest("tr").find('td:eq(3)').text());
	$("#txtRegDate").val($(this).closest("tr").find('td:eq(4)').text());
	$("#txtUsage").val($(this).closest("tr").find('td:eq(5)').text());
	$("#txtType").val($(this).closest("tr").find('td:eq(6)').text());
});


function onItemSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidItemIDSave").val("");
	$("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	} 
}

/*
// CLIENT-MODEL================================================================ 
function validateConsumerForm() {
	// id 
	if ($("#id").val().trim() == "") {
		return "Insert Consumer ID.";
	}
	
	if ($("#regNo").val().trim() == "") {
		return "Insert Registration Number.";
	}

	// name 
	if ($("#name").val().trim() == "") {
		return "Insert Name.";
	}

	// address 
	if ($("#address ").val().trim() == "") {
		return "Insert Address.";
	}
	// phone
	if ($("#phone").val().trim() == "") {
		return "Insert Contact.";
	}

	// regdate
	if ($("#regDate").val().trim() == "") {
		return "Insert Registration Date.";
	}
	// powerusage
	if ($("#power_usage").val().trim() == "") {
		return "Insert Power Usage.";
	}

	/*
	// is numerical value 
	var tmpPower = $("#power_usage").val().trim(); 
	if (!$.isNumeric(tmpPower)) 
	{ 
		return "Insert a numerical value for Power Usage."; 
	} 
	
	return true;
}
 */