function doctorPageViewModel() {
	var self = this;
	self.pacijenti = ko.observableArray([]);	
	self.imePrezime = ko.observable("");
	self.datumRodjenja = ko.observable("");
	self.spol = ko.observable("");
	self.proba = ko.observable("Mirnes");

	self.sviPacijentiDoktora = function() {
		var id_doktora = sessionStorage.getItem('doktor'); // dobavljamo id doktora sa login stranice
		
		var url = "modul-za-korisnike/sviPacijentiDoktora?idKorisnika=" + id_doktora;
		
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
				alert(data);
				
			}
		});
	}

	self.unosPacijenta = function() {
		if (self.imePrezime() == "" || self.datumRodjenja() == "" || self.spol() == "") {
			alert("uneseite parametre");
		}else {
			$.ajax("/modul-za-korisnike/unosPacijenta", {
				data: ko.toJSON({ imePrezime: self.imePrezime(), datumRodjenja: self.datumRodjenja(), spol:self.spol()}),
				type: "post", contentType: "application/json",
				success: function(data, textStatus, request) { self.imePrezime(""); self.datumRodjenja(""); self.spol(""); }
			});

		}
	}

	self.informacijePacijent = function() {
		
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
vm.sviPacijentiDoktora();  