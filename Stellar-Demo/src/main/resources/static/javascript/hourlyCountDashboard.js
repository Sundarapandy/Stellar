$(document).ready(function() {
	console.log("Welcome to Hourly Count Dashboard");
	$(".table-wrap").hide();
    var country;
    var selected_date;
    $("#selected_date").on("change",function(){
        selected_date=this.value;
    });
	
    $("#form_submit").on("submit",function(e){
		e.preventDefault();
		country=$("#country").find(":selected").text();
		console.log(country,selected_date);
		$(".table-wrap").hide();
        var URL = window.location.origin+"/getHourlyOrder?country="+country+"&date="+selected_date;
        $.ajax({
        url: URL,
        type: "GET",
        success: function(data) {
            console.log(data);
            if($.fn.dataTable.isDataTable("#hourlyCountTable")){
        		$("#hourlyCountTable").DataTable().clear().destroy();
    			}
			$('#hourlyCountTable').DataTable( {
				dom: 'Bfrtip',  
				   
				data: data,                         
				columns: [

					{ "title" : "Time_Period","data":"timePeriod","defaultContent":" " },
                    { "title" : "Order_Count","data":"orderCount","defaultContent":" " },
                    { "title" : "Hourly_Grand_Total","data":"hourlyGrandTotal","defaultContent":" "},
				]
            }); 
    		$(".table-wrap").show();
	
        },
        error: function(xhr, status, error) {
            console.log(error);
        }
	});
           
    });
});