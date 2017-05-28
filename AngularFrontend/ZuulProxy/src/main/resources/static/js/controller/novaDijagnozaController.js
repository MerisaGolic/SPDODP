var app = angular.module('myApp');

app.controller('novaDijagnozaController',['$window','$scope','$http', function($window, $scope, $http){

	$scope.sviSimptomi = [];
	$scope.simptomi = [];
	$scope.pacijenti = [];
	$scope.izabraniSimptom = "";	

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

	$scope.dodajSimptom = function(){

		if ($scope.izabraniSimptom != "") {

			$scope.simptomi.push($scope.izabraniSimptom);

			var index = $scope.sviSimptomi.map(function(d) { return d["naziv"]; }).indexOf($scope.izabraniSimptom);
			$scope.sviSimptomi.splice(index, 1);
			$scope.izabraniSimptom = "";
			$scope.odabraniSimptomi = true;

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
		}).error(function(error){
			console.log(error);
			alert("Nije dodana dijagnoza");
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

		});



	}

}]);