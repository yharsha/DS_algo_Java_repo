$( document ).ready(function() {
	
	// GET REQUEST
	$("#getAllCustomerId").click(function(event){
		event.preventDefault();
		ajaxGet();
	});
	
	// DO GET
	function ajaxGet(){
		$.ajax({
			type : "GET",
			url : window.location + "/employees",
			success: function(result){
				if(result.length !== 0){
					$('#getResultDiv ul').empty();
					$('#itemList').find('tbody').empty();
					var custList = "";
					var row = "";
					$.each(result, function(){
//						var customer = "- Customer with Id = " + i + ", firstname = " + customer.empName + ", lastName = " + customer.position + "<br>";
//						$('#getResultDiv .list-group').append(customer)
						row = "<tr><td>" + this.empNo + "</td><td>" + this.empName + "</td><td>" + this.position + "</td></tr>";
						$('#itemList').find('tbody').append(row);
			        });
//					$( rows ).appendTo( "#itemList tbody" );
					console.log("Success: ", result);
				}else{
					$("#getResultDiv").html("<strong>Error</strong>");
					console.log("Fail: ", result);
				}
			},
			error : function(e) {
				$("#getResultDiv").html("<strong>Error</strong>");
				console.log("ERROR: ", e);
			}
		});	
	}
})