function LoginViewModel() {
	var self = this;
	this.username = ko.observable();
	this.password = ko.observable();
	self.login = function() {
		$.ajax("/modul-za-korisnike/login", {
			data: ko.toJSON({ username: self.username, password: self.password}),
			type: "post", contentType: "application/json",
			success: function(data, textStatus, request) { sessionStorage.setItem('doktor', 3); window.location="doctorPage.html"; }
		});
	};
}

ko.applyBindings(new LoginViewModel());