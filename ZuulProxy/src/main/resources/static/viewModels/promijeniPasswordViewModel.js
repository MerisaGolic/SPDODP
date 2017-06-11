function promijeniPasswordViewModel() {
	self = this;
	self.email = ko.observable();
	
	function isEmail(email) {
		  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		  return regex.test(email);
	}
	

	self.posaljiMail = function() {
		if (!isEmail(self.email())) {
			$('#emailErrorModal').modal('show');
		}else {
			$.ajax("/modul-za-korisnike/korisnik/resetPassword?email=" + self.email(), {
				data: ko.toJSON({ email: self.email()}),
				type: "post", contentType: "application/json",
				success: function(data, textStatus, request) 
				{ 
					self.email("");
					$('#emailSuccessModal').modal('show');
					//window.location = "promijeniPasswordValidanTokenViewModel.html";
				},
				error(xhr, text, err)
				{
					$('#emailErrorModal').modal('show');
				}
			}); 
			
		}
	}

}

ko.applyBindings(new promijeniPasswordViewModel());