function kreirajDijagnozuViewModel() {
	var self = this;
	self.simptomKorisnika = ko.observable("");
	self.simptomi = ko.observableArray();
	self.odabraniSimptomi = ko.observable(false);
	self.pragFiltriranja = ko.observable();
	self.moguceDijagnoze = ko.observableArray([]);
	self.sviSimptomi = ko.observableArray()
	
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
		if (self.simptomKorisnika() != "" && self.simptomi().length<5) {
			
			self.simptomi.push(self.simptomKorisnika());
			self.sviSimptomi.remove(self.simptomKorisnika());
			self.simptomKorisnika("");
			self.odabraniSimptomi(true);
			self.postaviDijagnozu();
		} else alert("Maksimalno 5 simptoma se može unijeti");
	}

	self.izbrisiSimptomKorisnika = function(simptom) {
		self.simptomi.remove(simptom);
		self.sviSimptomi.push(simptom);
		self.postaviDijagnozu();
		if (self.simptomi().length < 1) {
			self.odabraniSimptomi(false);
		}
	}

	self.postaviDijagnozu = function() {
		// to do
		self.moguceDijagnoze([]); // izbrisi sve iz niza
		var url = "modul-dijagnoze/dijagnosticiranje?simptomi=" + self.simptomi().toString();
		
		$.ajax(url, {
			type: "get", contentType: "application/json",
			success: function(data, textStatus, request) { 
				var pom = {
					"naziv" : "",
					"opis" : "",
					"broj" :  2
				};
				for (var i = 0; i < data.length; i++) {
					pom.naziv = data[i][0];
					pom.opis = data[i][1];
					pom.broj = data[i][2];
					if (pom.broj >= self.pragFiltriranja()){
						self.moguceDijagnoze.push(pom);
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