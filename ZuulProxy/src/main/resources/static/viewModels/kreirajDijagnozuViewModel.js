function kreirajDijagnozuViewModel() {
	var self = this;
	self.simptomKorisnika = ko.observable("");
	self.simptomi = ko.observableArray();
	self.odabraniSimptomi = ko.observable(false);
	self.pragFiltriranja = ko.observable();
	self.moguceDijagnoze = ko.observableArray([]);
	self.sviSimptomi = ko.observableArray();
	self.sviSimptomiNotEmpty = ko.observable(true);
	
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

	self.login = function() {
		$.ajax("/modul-za-korisnike/login", {
			data: ko.toJSON({ username: self.username, password: self.password}),
			type: "post", contentType: "application/json",
			success: function(data, textStatus, request) { alert(document.cookie); window.location="login.html"; }
		});
	};
}

ko.applyBindings(new kreirajDijagnozuViewModel());