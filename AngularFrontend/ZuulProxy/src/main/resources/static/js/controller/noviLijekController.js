var app = angular.module('myApp');

app.controller('noviLijekController',['$window','$scope','$http', function($window, $scope, $http){

	$scope.nazivNovogLijeka = "";
	$scope.standardnaDoza = 100;

	$scope.dodajNoviLijek = function(){
		if($scope.nazivNovogLijeka == "")
			alert("Morate prvo unijeti naziv lijeka!");
		else{
			console.log($scope.nazivNovogLijeka);
			console.log($scope.standardnaDoza);

			$http({
				method: 'POST',
				url: 'modul-za-odredjivanje-terapije/unosLijeka',
				contentType: "application/json",
				data: angular.toJson({"naziv": $scope.nazivNovogLijeka, "standardnaDoza": $scope.standardnaDoza}, true)
			}).success(function(data){
				console.log(data);
				alert("Uspješno dodan lijek " + $scope.nazivNovogLijeka);
			}).error(function(error){
				console.log(error);
				alert("Greskaaaaaaa");
			}).then(function(){
				$scope.nazivNovogLijeka = "";
				$scope.standardnaDoza = 100;
			});

		}


	}

}]);