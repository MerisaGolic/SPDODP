function promijeniPasswordValidanTokenViewModel() {
	self = this;
	self.password = ko.observable();

	self.promijeniPassword = function() {
		if (self.password() == "") {
			alert("uneseite parametre");
		}else {
			
			$.ajax("/modul-za-korisnike/korisnik/sacuvajPassword", {
				data: ko.toJSON({ password: self.password()}),
				type: "post", contentType: "application/json",
				success: function(data, textStatus, request) 
				{ 
					self.password("");
					alert(data);
				}
			});
		}
	}
}

ko.applyBindings(new promijeniPasswordValidanTokenViewModel());