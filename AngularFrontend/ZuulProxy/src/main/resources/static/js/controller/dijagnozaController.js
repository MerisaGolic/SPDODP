var app = angular.module('myApp');

app.controller('dijagnozaController',['$window','$scope','$http', function($window, $scope, $http){
	
	$scope.simptomKorisnika = "";
	$scope.simptomi = [];
	$scope.odabraniSimptomi = false;
	$scope.pragFiltriranja = 0;
	$scope.moguceDijagnoze = [];
	$scope.sviSimptomi = [];
	
	$scope.dajDijagnoze = function() {
		// to d
		
		$http.get("modul-dijagnoze/dajSimptome").success(
		function (data) 
		{
			for (var i = 0; i < data.length; i++) 
			{
				$scope.sviSimptomi.push(data[i].naziv);
			}
		}).error(function(data){alert("Nisu dosli simptomi")}); 
	};

	$scope.dodajSimptomKorisnika = function() {
		if ($scope.simptomKorisnika() != "" && $scope.simptomi().length<5) {
			
			$scope.simptomi.push($scope.simptomKorisnika());
			$scope.sviSimptomi.remove($scope.simptomKorisnika());
			$scope.simptomKorisnika("");
			$scope.odabraniSimptomi(true);
			$scope.postaviDijagnozu();
		} else alert("Maksimalno 5 simptoma se može unijeti");
	}

	$scope.izbrisiSimptomKorisnika = function(simptom) {
		$scope.simptomi.remove(simptom);
		$scope.sviSimptomi.push(simptom);
		$scope.postaviDijagnozu();
		if ($scope.simptomi().length < 1) {
			$scope.odabraniSimptomi(false);
		}
	}

	$scope.postaviDijagnozu = function() {
		// to do
		$scope.moguceDijagnoze = []; // izbrisi sve iz niza
		var urlD = "modul-dijagnoze/dijagnosticiranje?simptomi=" + $scope.simptomi().toString();
		
		$http({
			method:'GET',
			url: urlD, 
			contentType: "application/json"}).
			success(function(data, textStatus, request) { 
				var pom = {
					"naziv" : "",
					"opis" : "",
					"broj" :  2
				};
				for (var i = 0; i < data.length; i++) {
					pom.naziv = data[i][0];
					pom.opis = data[i][1];
					pom.broj = data[i][2];
					if (pom.broj >= $scope.pragFiltriranja()){
						$scope.moguceDijagnoze.push(pom);
					}
				}
		});
	}

	$scope.login = function()
	{
		//alert($scope.username + " " + $scope.password);
		$http({
			method:'POST',
			url: '/modul-za-korisnike/login',
			contentType: "application/json",
			data: angular.toJson({username: $scope.username, password: $scope.password}, true)
		
		    //method: 'GET',
		   // url: 'http://localhost:8081/provjeraLogina?username='+$scope.username+'&password='+$scope.password
		}).success(function(data) {
		    	$window.location.href = '/dijagnoze';
		       }).error(function(error) {
		    	   alert("Pogresan username i/ili password!");
		       });
		
		//$window.location.href = '/dijagnoze';
	};
	
}]);