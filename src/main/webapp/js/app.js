var ROOT = "/menu";

var URL = {
	MENU:function(){
		return ROOT + "/system/auth/getmenu";
	}
};

$(document).ready(function(){
	getMenu();
});

function getMenu(){
	$.post(URL.MENU(), function(result) {
		
	});
}