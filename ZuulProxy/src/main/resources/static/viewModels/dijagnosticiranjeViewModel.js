function dijagnosticiranjeViewModel() {
	var self = this;
	self.simptomKorisnika = ko.observable("");
	self.simptomi = ko.observableArray();
	self.odabraniSimptomi = ko.observable(false);
	self.pragFiltriranja = ko.observable();
	self.moguceDijagnoze = ko.observableArray([]);
	self.sviSimptomi = ko.observableArray();
	self.sviSimptomiNotEmpty = ko.observable(true);
	self.dodavanjeOmoguceno = ko.observable(false);
	self.idPacijenta = ko.observable();
	self.unosNoveDijagnozeOmoguceno = ko.observable(false);
	self.sakrijPonudjene = ko.observable(true);

	self.odabranaDijagnozaNaziv = ko.observable("");
	self.nazivDijagnoze = ko.observable("");
	self.opisDijagnoze = ko.observable("");
	
	self.dajDijagnoze = function() {
		// to d
		
		$.getJSON("modul-dijagnoze/dajSimptome",
		function (data) 
		{
			for (var i = 0; i < data.length; i++) 
			{
				self.sviSimptomi.push(data[i].naziv);
			}
		}); 
	};
	
	self.dajDijagnoze();

	self.dodajSimptomKorisnika = function() {
			
			self.simptomi.push(self.simptomKorisnika());
			self.sviSimptomi.remove(self.simptomKorisnika());
			self.simptomKorisnika("");
			self.odabraniSimptomi(true);
			if (self.sviSimptomi().length < 1) {
				self.sviSimptomiNotEmpty(false);
			}
			self.postaviDijagnozu();
	}

	self.izbrisiSimptomKorisnika = function(simptom) {
		self.simptomi.remove(simptom);
		self.sviSimptomi.push(simptom);
		self.postaviDijagnozu();
		if (self.simptomi().length < 1) {
			self.odabraniSimptomi(false);
		}
		if (!(self.sviSimptomi().length < 1)) {
				self.sviSimptomiNotEmpty(true);
		}
	}

	self.postaviDijagnozu = function() {
		// to do
		self.moguceDijagnoze([]); // izbrisi sve iz niza
		var url = "modul-dijagnoze/dijagnosticiranje?simptomi=" + self.simptomi().toString();
		
		$.ajax(url, {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) { 
				self.moguceDijagnoze(data);
				if (self.moguceDijagnoze().length > 0) self.dodavanjeOmoguceno(true)
					else
						self.dodavanjeOmoguceno(false);
				for (var i = self.moguceDijagnoze().length - 1; i >= 0 ; i--) 
				{
					if (self.moguceDijagnoze()[i].postotak < self.pragFiltriranja())
					{
						self.moguceDijagnoze.splice(i, 1);
					}
				}
				
			}
		});
	}

	self.dobaviNazivOpisDijagnoze = function(data) {
		self.odabranaDijagnozaNaziv(data.naziv);
	}

	

	self.dodajDijagnozuZaPacijenta = function() {

		/*if (self.odabranaDijagnozaNaziv() =="") {
			alert("Odaberite neku od dijagnoza ili unseite novu");
			return null;
		}*/
		
		var data = sessionStorage.getItem('imePacijenta');
		
		$.ajax("modul-za-korisnike/dajIdPacijenta?imePrezime=" + data, {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) { 

				self.idPacijenta(data);

				$.ajax("modul-dijagnoze-pacijenti/dajIdDijagnoze?nazivDijagnoze=" + $('input[name=odaberi]:checked').val(), {
					type: "get", contentType: "application/json",
					success: function(data, textStatus, request) { 

						$.ajax("modul-dijagnoze-pacijenti/poveziDijagnozuPacijenta?idPacijenta=" + self.idPacijenta() + "&idDijagnoze=" + data, {
							type: "get", contentType: "application/json",
							success: function(data, textStatus, request) { 
								
							}
						});
					}
				});

			}
		});
	}

	self.dobaviIdPacijenta = function() {
		var data = sessionStorage.getItem('imePacijenta');
		$.ajax("modul-za-korisnike/dajIdPacijenta?imePrezime=" + data, {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) { 

				self.idPacijenta(data);
			}
		});
	}
	self.dobaviIdPacijenta();

	self.dodajNovuDijagnozu = function() {
		if (self.nazivDijagnoze()=="" || self.opisDijagnoze()=="") {
			alert("unesite naziv i opis dijagnoze");
			return null;
		}
		
		$.ajax("modul-dijagnoze/dodavanjeDijagnoze", {
					data: ko.toJSON({ naziv: self.nazivDijagnoze(), opis: self.opisDijagnoze(), postotak: 0}),
					type: "post", contentType: "application/json",
					success: function(data, textStatus, request) { 

						$.ajax("modul-dijagnoze/novaDijagnoza?simptomi=" + self.simptomi() + "&dijagnoza=" + self.nazivDijagnoze(), {
							type: "get", contentType: "application/json",
							success: function(data, textStatus, request) { 

								$.ajax("modul-dijagnoze-pacijenti/dajIdDijagnoze?nazivDijagnoze=" + self.nazivDijagnoze(), {
								type: "get", contentType: "application/json",
								success: function(data, textStatus, request) { 

									$.ajax("modul-dijagnoze-pacijenti/poveziDijagnozuPacijenta?idPacijenta=" + self.idPacijenta() + "&idDijagnoze=" + data, {
										type: "get", contentType: "application/json",
										success: function(data, textStatus, request) { 
											
										}
									});
								}
							});
							}
						});
					}
		});

	}
	
}

ko.applyBindings(new dijagnosticiranjeViewModel());
