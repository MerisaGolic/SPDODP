var app = angular.module('myApp');

app.controller('promjenaPasswordaController',['$window','$scope','$http', function($window, $scope, $http){
	
	$scope.posaljiMail = function(){
		
		if ($scope.email == "")
			alert("Unesite email!");
		else{
			$http({
				method:'POST',
				url: '/modul-za-korisnike/korisnik/resetPassword?email='+$scope.email.toString(),
				contentType: "application/json",
				data: {"email": $scope.email}
			}).success(function(data) {
			    	console.log(data);
			    	$scope.email = "";
			    	$scope.message = "Email poslan!";
			       }).error(function(error) {
			    	   console.log(error);
			    	   alert("Greška");
			       });
		}
	};
}]);