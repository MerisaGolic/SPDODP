function promijeniPasswordValidanTokenViewModel() {
	self = this;
	self.password = ko.observable();
	
	function validirajPassword(password) {
		  var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;
		  return regex.test(password);
	}

	self.promijeniPassword = function() {
		if (!validirajPassword(self.password())) {
			$('#passwordErrorModal').modal('show');
		}else {
			
			$.ajax("/modul-za-korisnike/korisnik/sacuvajPassword", {
				data: ko.toJSON({ password: self.password()}),
				type: "post", contentType: "application/json",
				success: function(data, textStatus, request) 
				{ 
					self.password("");
					$('#passwordSuccessModal').modal('show');
				}
			});
		}
	}
}

ko.applyBindings(new promijeniPasswordValidanTokenViewModel());