function doctorPageViewModel() {
	var self = this;
	self.id_doktora = ko.observable();
	self.pacijenti = ko.observableArray([]);
	self.listaPacijenata = ko.observableArray();
	self.sviPacijenti = ko.observableArray([]);
	self.imePrezime = ko.observable("");
	self.datumRodjenja = ko.observable("");
	self.spol = ko.observable("M");
	self.imePacijenta = ko.observable("");
	self.dijagnozePacijenta = ko.observableArray([]);
	
	self.nazivLijeka = ko.observable("");
	
	$("#datepicker").datepicker({
		dateFormat: "yy-mm-dd",
		changeMonth: true,
		changeYear: true,
		yearRange: "c-100:c"
	});

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
					self.sviPacijenti.push(data[i]);					
				}
				for (var i = 0; i < self.pacijenti().length; i++)
				{
					self.listaPacijenata.push(self.pacijenti()[i][1]);
				}
				$('#imePrezime').autocomplete({
					minLength: 0,
					source: self.listaPacijenata(),
					search: function(event, ui)
					{
						if(ui.item == null)
						{
							self.pacijenti(self.sviPacijenti().slice(0));
						}
					},
					select: function(event, ui) 
					{
						self.pacijenti(self.sviPacijenti().slice(0));
						if(ui.item !== null)
						{
							for(var i = 0; i < self.sviPacijenti().length; i++)
							{
								if(ui.item.label != self.sviPacijenti()[i][1])
								{
									self.pacijenti.remove(self.sviPacijenti()[i])
									
								}
							}
						}
						
					}
				});
			}
		});
	}

	self.brisanjePacijenta = function(pac) {
		self.listaPacijenata.remove(pac[1]);
		self.pacijenti.remove(this);
		self.sviPacijenti.remove(this);
		var url = "modul-za-korisnike/brisanjePacijentaPoImenuIPrezimenu?imePrezime="+ pac[1];
		
		$.ajax(url, {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) {
				$("#brisanjeSuccessModal").modal('show');
			}
		});
	}

	self.unosPacijenta = function() {
		if (self.imePrezime() == "" || self.datumRodjenja() == "" || self.spol() == "") {
			$("#unosErrorModal").modal('show');
		}else {
			self.pacijenti.push([self.datumRodjenja(), self.imePrezime(), self.spol()]);
			self.sviPacijenti.push([self.datumRodjenja(), self.imePrezime(), self.spol()]);
			self.listaPacijenata.push(self.imePrezime());
			$.ajax("/modul-za-korisnike/unosPacijenta", {
				data: ko.toJSON({ imePrezime: self.imePrezime(), datumRodjenja: self.datumRodjenja(), spol:self.spol()}),
				type: "post", contentType: "application/json",
				success: function(data, textStatus, request) 
				{ 

					self.imePrezime(""); 
					self.datumRodjenja(""); 
					self.spol(""); 
					$("#unosSuccessModal").modal('show');
				},
				error: function(xhr, text, err)
				{
					$("#unosErrorModal").modal('show');
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
							"opis" : "",
							"terapija": "",
							"standardnaDoza": ""
						};
						
						ideviDijagnoza = data[0][2];
						
						for (var i = 1; i < data.length; i++) {
							ideviDijagnoza += ",";
							ideviDijagnoza += data[i][2];
						}
						
						
						var nazivi = [];
						var opisi = [];
						var terapije = [];
						
						for (var i = 0; i < data.length; i++) {
							nazivi.push(data[i][0]);
							opisi.push(data[i][1]);
							terapije.push("nesto");
						}
						
						$.ajax("/modul-za-odredjivanje-terapije/dajNaziveLijekova?ideviDijagnoza=" + ideviDijagnoza, {
							type: "get", contentType: "application/json",
							success: function(data, textStatus, request) { 
								for(var i = 0; i < data.length; i++)
								{
									pom.naziv = nazivi[i];
									pom.opis = opisi[i];
									pom.terapija = data[i];
									self.dijagnozePacijenta.push(pom);
								}
							}
						});
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

	self.dodajDijagnozu = function(pac) {
		self.imePacijenta(pac[1]);
		sessionStorage.setItem('imePacijenta',self.imePacijenta());
		window.location = "dijagnosticiranje.html";
	}

}

var vm = new doctorPageViewModel();
ko.applyBindings(vm);  