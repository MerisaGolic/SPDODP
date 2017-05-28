var app = angular.module('myApp');

app.controller('dijagnozaController',['$window','$scope','$http', function($window, $scope, $http){

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

	var id_doktora = 0;

	$http({
		method: 'GET',
		url: "modul-za-korisnike/dajIdLogovanogKorisnika",
		contentType: "application/json"
	}).success(function(data){

		console.log(data);
		id_doktora = data;

	}).error(function(error){
		console.log(error);
		alert("Nije nađen id doktora!");
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

	$scope.postaviDijagnozu = function()
	{
		if($scope.simptomi.length == 0)
			alert("Morate unijeti simptome!");
		else{
			$scope.dijagnoze = [];
			$scope.prikazDijagnoza = true;

			var url = "modul-dijagnoze/dijagnosticiranje?simptomi=" + $scope.simptomi.toString();

			$http({
				method: 'GET',
				url: url,
				contentType: "application/json"
			}).success(function(data){

				$scope.dijagnoze = data;
				for (var i = $scope.dijagnoze.length-1; i >= 0 ; i--) 
				{
					if ($scope.dijagnoze[i]["postotak"] < $scope.postotak)
					{
						$scope.dijagnoze.splice(i, 1);
					}
				}

			}).error(function(error){
				console.log(error);
				alert("Nisu dosle dijagnoze");
			});

			$http({
				method: 'GET',
				url: "modul-za-korisnike/sviPacijentiDoktora?idKorisnika=" + id_doktora,
				contentType: "application/json"
			}).success(function(data){
				$scope.pacijenti = data;
			}).error(function(error){
				console.log(error);
				alert("Nisu dosli pacijenti!");
			});	
		}

	};

	$scope.povezi = function(){

		if ($scope.izabranaDijagnoza == undefined && $scope.izabraniPacijent == undefined)
			alert ("Morate izabrati pacijenta i dijagnozu!");
		else if( $scope.izabraniPacijent == undefined)
			alert("Morate izabrati pacijenta!");
		else if($scope.izabranaDijagnoza== undefined)
			alert("Morate izabrati dijagnozu!");
		else{

			$http({
				method: 'GET',
				url: "modul-dijagnoze-pacijenti/poveziDijagnozuPacijenta?idPacijenta=" + $scope.izabraniPacijent +"&idDijagnoze=" + $scope.izabranaDijagnoza ,
				contentType: "application/json"
			}).success(function(data){
				$scope.message = "Dijagnoza uspjesno dodana pacijentu!";
			}).error(function(error){
				console.log(error);
				alert("Greska!");
			});
		}

	};
	
}]);