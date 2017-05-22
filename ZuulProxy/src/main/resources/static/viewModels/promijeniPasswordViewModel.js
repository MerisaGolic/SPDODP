function promijeniPasswordViewModel() {
	self = this;
	self.email = ko.observable();
	

	self.posaljiMail = function() {
		if (self.email() == "") {
			alert("uneseite parametre");
		}else {
			$.ajax("/modul-za-korisnike/korisnik/resetPassword?email=" + self.email(), {
				data: ko.toJSON({ email: self.email()}),
				type: "post", contentType: "application/json",
				success: function(data, textStatus, request) 
				{ 
					self.email("");
					//window.location = "promijeniPasswordValidanTokenViewModel.html";
				}
			}); 
			
		}
	}

}

ko.applyBindings(new promijeniPasswordViewModel());