var app = angular.module('myApp');

app.controller('pregledPacijenataController',['$window','$scope','$http', function($window, $scope, $http){
	
	$scope.sviPacijenti = [];
	$scope.sveDijagnoze = [];
	$scope.prikazModala = false;
	$scope.prikazNalaza = false;
	$scope.spol = "z";
	
	var id_doktora = 0;

	$http({
		method: 'GET',
		url: "modul-za-korisnike/dajIdLogovanogKorisnika",
		contentType: "application/json"
	}).success(function(data){

		id_doktora = data;

	}).error(function(error){
		console.log(error);
		alert("Nije nađen id doktora!");
	}).then(function(){
		
		$http({
			method: 'GET',
			url: 'modul-za-korisnike/sviPacijentiDoktora?idKorisnika=' + id_doktora,
			contentType: "application/json"
		}).success(function(data){

			$scope.sviPacijenti = data;
			
		}).error(function(error){
			console.log(error);
			alert("Nisu dosli pacijenti");
		});
	});
	
	$scope.brisanjePacijenta = function(izabraniPacijent){
		
		var index = $scope.sviPacijenti.indexOf(izabraniPacijent);
		$scope.sviPacijenti.splice(index, 1);
		$http({
			method: 'GET',
			url: 'modul-za-korisnike/brisanjePacijentaPoImenuIPrezimenu?imePrezime=' + izabraniPacijent.imePrezime,
			contentType: "application/json"
		}).success(function(data){
			//alert("Pacijent usmjesno obrisan!");
		}).error(function(error){
			console.log(error);
			alert("Pacijent nije obrisan!");
		});
		
	};
	$scope.informacijePacijent = function(izabraniPacijent){
		$scope.sveDijagnoze = [];
		$scope.imePacijenta = izabraniPacijent.imePrezime;
		
		$scope.prikazModala = true;
	//	alert($scope.prikazModala);
		$http({
			method: 'GET',
			url: 'modul-dijagnoze-pacijenti/sveDijagnozePacijenta?idPacijenta=' + izabraniPacijent.id,
			contentType: "application/json"
		}).success(function(data){
			//alert("Dijagnoze dosle!");
			//console.log(data);
			$scope.sveDijagnoze = data;
		}).error(function(error){
			console.log(error);
			alert("Nisu dosle dijagnoze!");
		});
		
	};
	
	$scope.zatvori = function(){
		$scope.prikazModala = false;
	};
	$scope.unosPacijenta = function(){
		if ($scope.imePrezimeNovi == "" || $scope.datumRodjenjaNovi == "" || $scope.spol == "") {
			alert("unesite sve parametre");
		}
		else {
			console.log($scope.spol);
			$http({
				method:'POST',
				url: '/modul-za-korisnike/unosPacijenta',
				contentType: "application/json",
				data: angular.toJson({ "imePrezime": $scope.imePrezimeNovi, "datumRodjenja": $scope.datumRodjenjaNovi, "spol": $scope.spol}, true)
			}).success(function(data) {
			    alert("Uspjesno dodan novi pacijent!");
			    
			}).error(function(error) {
			    console.log(error);
			    alert("Nije dodan novi pacijent!");
			}).then(function(){
				$http({
					method: 'GET',
					url: 'modul-za-korisnike/dajIdPacijenta?imePrezime=' + $scope.imePrezimeNovi,
					contentType: "application/json"
				}).success(function(data){
					$scope.sviPacijenti.push({"id": data,"imePrezime": $scope.imePrezimeNovi, "datumRodjenja": $scope.datumRodjenjaNovi, "spol": $scope.spol});
				}).error(function(error){
					console.log(error);
					alert("Nije nadjen pacijent!");
				});
			
			});
			
		}
	};
	var izabranaDijagnoza = 0;
	
	$scope.unosNalaza = function(id){
		$scope.prikazNalaza = true;
		
		izabranaDijagnoza = id;
		
	};
	$scope.izracunajTerapiju = function(){
		var doza = 0;
		
		$http({
			method: 'GET',
			url: 'modul-za-odredjivanje-terapije/izracunajDozu?idDijagnoze='+ izabranaDijagnoza +
			                                                 '&secer='+$scope.secer +
			                                                 '&eritrociti='+ $scope.eritrociti+
			                                                 '&leukociti=' + $scope.leukociti +
			                                                 '&trombociti=' + $scope.trombociti,
			contentType: "application/json"
		}).success(function(data){
			$scope.message = "Potrebno je uzimati " + data["doza"] +"mg lijeka " + data["naziv"] + "!";
			$scope.prikazNalaza = false;
		}).error(function(error){
			console.log(error);
			alert("Nije izracunata doza!");
		});
	};
}]);