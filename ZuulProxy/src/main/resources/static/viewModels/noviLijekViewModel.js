function noviLijekViewModel() 
{
	var self = this;
	self.naziv = ko.observable("");
	self.standardnaDoza = ko.observable(0);
	
	self.unosLijeka = function()
	{
		if(self.naziv() == "" || self.standardnaDoze == "")
		{
			$("#unosErrorModal").modal('show');
		}
		else
		{
			$.ajax("/modul-za-odredjivanje-terapije/unosLijeka", {
				data: ko.toJSON({ naziv: self.naziv, standardnaDoza: self.standardnaDoza }),
				type: "post", contentType: "application/json",
				success: function(data, textStatus, request) { self.naziv(""); self.standardnaDoza(0); $("#unosSuccessModal").modal('show'); }
			});
		}
	};
}

ko.applyBindings(new noviLijekViewModel());