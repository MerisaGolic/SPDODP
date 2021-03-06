var app = angular.module('myApp');

app.controller('novaDijagnozaController',['$window','$scope','$http', function($window, $scope, $http){

	$scope.sviSimptomi = [];
	$scope.simptomi = [];
	$scope.pacijenti = [];
	$scope.izabraniSimptom = "";
	$scope.sviLijekovi = [];
	$scope.izabraniLijek = "";
	$scope.prikazSimptoma = false;
	$scope.prikazUnosa = false;

	$http({
		method: 'GET',
		url: 'modul-dijagnoze/dajSimptome'
	}).success(function(data){

		for (var i = 0; i < data.length; i++) 
		{
			$scope.sviSimptomi.push({"naziv": data[i].naziv});
		}

	}).error(function(error){
		console.log(error);
		alert("Nisu dosli simptomi");
	});

	$http({
		method: 'GET',
		url: 'modul-za-odredjivanje-terapije/dajLijekove'
	}).success(function(data){
		console.log(data);
		$scope.sviLijekovi = data;

	}).error(function(error){
		console.log(error);
		alert("Nisu dosli lijekovi");
	});

	$scope.dodajSimptom = function(){

		if ($scope.izabraniSimptom != "") {

			$scope.prikazSimptoma = true;
			$scope.simptomi.push($scope.izabraniSimptom);

			var index = $scope.sviSimptomi.map(function(d) { return d["naziv"]; }).indexOf($scope.izabraniSimptom);
			$scope.sviSimptomi.splice(index, 1);
			$scope.izabraniSimptom = "";
			$scope.odabraniSimptomi = true;
			$scope.prikazUnosa = true;

		} else alert("Morate prvo izabrati simptome!");
	};

	$scope.izbrisiSimptom = function(simptomZaBrisanje) {

		var index = $scope.simptomi.indexOf(simptomZaBrisanje);

		$scope.simptomi.splice(index, 1);

		$scope.sviSimptomi.push({"naziv": simptomZaBrisanje});

		if ($scope.simptomi.length < 1) {
			$scope.odabraniSimptomi = false;
		}
	};

	$scope.unesiDijagnozu = function(){

		console.log("naziv");
		console.log($scope.nazivNoveDijagnoze);
		$http({
			method: 'POST',
			url: 'modul-dijagnoze/dodavanjeDijagnoze',
			contentType: "application/json",
			data: angular.toJson({"naziv": $scope.nazivNoveDijagnoze, "opis": $scope.opisNoveDijagnoze, "postotak": 0}, true)
		}).success(function(data){
			console.log(data);
			alert("Uspješno dodana dijagnoza " + $scope.nazivNoveDijagnoze);
		}).error(function(error){
			console.log(error);
			alert("Uspješno dodana dijagnoza " + $scope.nazivNoveDijagnoze);

		}).then(function(){
			$http({
				method: 'GET',
				url: 'modul-dijagnoze/novaDijagnoza?dijagnoza='+$scope.nazivNoveDijagnoze+'&simptomi='+$scope.simptomi,
				contentType: "application/json"
			}).success(function(data){
				console.log(data);
			}).error(function(error){
				console.log(error);
				alert("Nije dodana veza dijagnoza-simptomi");
			});		
			
			var id = 0;
			$http({
				method: 'GET',
				url: 'modul-dijagnoze/dajIdDijagnoze?naziv=' + $scope.nazivNoveDijagnoze,
				contentType: "application/json"
			}).success(function(data){
				console.log(data);
				id = data;
			}).error(function(error){
				console.log(error);
				alert("Nije nadjen id dijagnoze");
			}).then(function(data){
				$http({
					method: 'GET',
					url: 'modul-za-odredjivanje-terapije/poveziDijagnozeILijekove?idLijeka='+$scope.izabraniLijek+'&idDijagnoze='+id,
					contentType: "application/json"
				}).success(function(data){
					console.log(data);
				}).error(function(error){
					console.log(error);
					alert("Nije dodana veza dijagnoza-lijek");
				}).then(function(){
					$scope.simptomi = [];
					$scope.izabraniSimptom = "";
					$scope.nazivNoveDijagnoze = "";
					$scope.opisNoveDijagnoze = "";
					$scope.izabraniLijek = "";
				});
				
			});
			
			
			


		});


	}

}]);