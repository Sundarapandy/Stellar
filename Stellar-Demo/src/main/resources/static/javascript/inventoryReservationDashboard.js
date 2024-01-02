$(document).ready(function () {
    console.log("Welcome to Inventory Reservation Dashboard");
    $(".table-wrap").hide();
    var country;

    $(".stateFilterTabs ").on("click", function () {
        $(".stateFilterTabs ").removeClass("active");
        $(this).addClass("active");
        filterByState = $(this).attr('data-value');
        createFilteredTable(filterByState);
    });

    $("#country").on("change", function (e) {
        e.preventDefault();
        country = $("#country").find(":selected").text();
        //console.log(country);
        $(".table-wrap").hide();
        var URL = window.location.origin + "/getStockUnit?country=" + country;
        $.ajax({
            url: URL,
            type: "GET",
            success: function (data) {
                //console.log(data);
                $(".table-wrap").show();
				createDataTable(data);
            },
            error: function (xhr, status, error) {
                console.log(error);
            }
        });

    });
});
function createFilteredTable(filterByState) {
	if(filterByState=="all"){
		$("#country").trigger('change');
	}
	else{
	var data=[];
    $(".table-wrap").hide();
    country = $("#country").find(":selected").text();
    var URL = window.location.origin + "/getStockUnit?country=" + country;
    $.ajax({
        url: URL,
        type: "GET",
        success: function (dataJSON) {
            console.log(dataJSON);
            $.each(dataJSON, function (i) {
                var tempObj = { ...dataJSON[i] };
                //console.log(tempObj);
                var rowState = tempObj.state;
                if (rowState == filterByState)
                    data.push(tempObj);
            });
            //console.log(data);
            $(".table-wrap").show();
            createDataTable(data);
            
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });	
	}
    
}

function createDataTable(data){
	if ($.fn.dataTable.isDataTable("#inventoryTable")) {
                    $("#inventoryTable").DataTable().clear().destroy();
                }
                $('#inventoryTable').DataTable({
                    dom: 'Bfrtip',

                    data: data,
                    columns: [

                        { "title": "SKU", "data": "sku", "defaultContent": " " },
                        { "title": "Quantity", "data": "quantity", "defaultContent": " " },
                        { "title": "Increment_ID", "data": "increment_id", "defaultContent": " " },
                        { "title": "State", "data": "state", "defaultContent": " " },
                        { "title": "Status", "data": "status", "defaultContent": " " },
                        { "title": "Magento_Inventory", "data": "magento_inventory", "defaultContent": " " },
                    ]
                });
}