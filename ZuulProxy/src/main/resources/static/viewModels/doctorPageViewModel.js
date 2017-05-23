function doctorPageViewModel() {
	var self = this;
	self.id_doktora = ko.observable();
	self.pacijenti = ko.observableArray([]);	
	self.imePrezime = ko.observable("");
	self.datumRodjenja = ko.observable("");
	self.spol = ko.observable("");
	self.imePacijenta = ko.observable("");
	self.dijagnozePacijenta = ko.observableArray([]);

	self.dajIdLogovanogDoktora = function() {
		var url = "modul-za-korisnike/dajIdLogovanogKorisnika";
				
				$.ajax(url, {
					type: "get",
					success: function(data, textStatus, request) { 
						self.id_doktora = data;
						self.sviPacijentiDoktora();
					}
				});
	}
	
	self.dajIdLogovanogDoktora();

	self.sviPacijentiDoktora = function() {
		
		
		var url = "modul-za-korisnike/sviPacijentiDoktora?idKorisnika=" + self.id_doktora;
		
		$.ajax(url, {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) { 
				for (var i = 0; i < data.length; i++) {
					
					self.pacijenti.push(data[i]);
										
				}
			}
		});
	}

	self.brisanjePacijenta = function(pac) {
		
		self.pacijenti.remove(this);
		var url = "modul-za-korisnike/brisanjePacijentaPoImenuIPrezimenu?imePrezime="+ pac[1];
		
		$.ajax(url, {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) { 
			}
		});
	}

	self.unosPacijenta = function() {
		if (self.imePrezime() == "" || self.datumRodjenja() == "" || self.spol() == "") {
			alert("uneseite parametre");
		}else {
			self.pacijenti.push([self.datumRodjenja(), self.imePrezime(), self.spol()]);
			$.ajax("/modul-za-korisnike/unosPacijenta", {
				data: ko.toJSON({ imePrezime: self.imePrezime(), datumRodjenja: self.datumRodjenja(), spol:self.spol()}),
				type: "post", contentType: "application/json",
				success: function(data, textStatus, request) 
				{ 

					self.imePrezime(""); 
					self.datumRodjenja(""); 
					self.spol(""); 
				}
			});

		}
	}

	self.informacijePacijent = function(pac) {
		self.imePacijenta(pac[1]);

		$.ajax("modul-za-korisnike/dajIdPacijenta?imePrezime=" + self.imePacijenta(), {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) { 
				self.dijagnozePacijenta([]); // izbrisi sve iz niza
				var url = "modul-dijagnoze-pacijenti/sveDijagnozePacijenta?idPacijenta=" + data;
				
				$.ajax(url, {
					type: "get", contentType: "application/json",
					success: function(data, textStatus, request) { 
						var pom = {
							"naziv" : "",
							"opis" : ""
						};
						for (var i = 0; i < data.length; i++) {
							pom.naziv = data[i][0];
							pom.opis = data[i][1];
							self.dijagnozePacijenta.push(pom);
							
						}
					}
				});
			}
		});

	}

	self.logout = function() {
		$.ajax("/logout", {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) { sessionStorage.removeItem('id_doktora'); window.location="index.html"; }
		});
	};

}

var vm = new doctorPageViewModel();
ko.applyBindings(vm);  