var app = angular.module('myApp');

app.controller('promjenaPasswordaValidanTokenController',['$window','$scope','$http', function($window, $scope, $http){
	
	$scope.promijeniPassword = function(){
		
		if ($scope.password == "")
			alert("Unesite password!");
		else{
			$http({
				method:'POST',
				url: '/modul-za-korisnike/korisnik/sacuvajPassword',
				contentType: "application/json",
				data: {"password": $scope.password}
			}).success(function(data) {
			    	console.log(data);
			    	$scope.password = "";
			    	$scope.message = "Password uspješno promijenjen!";
			       }).error(function(error) {
			    	   console.log(error);
			    	   alert("Greška");
			    	   
			       });
		}
	};
}]);