var app = angular.module('myApp');

app.controller('loginController',['$window','$scope','$http', function($window, $scope, $http){
	$scope.message = "";
	$scope.posalji = function()
	{
		$http({
			method:'POST',
			url: '/modul-za-korisnike/login',
			contentType: "application/json",
			data: angular.toJson({"username": $scope.username, "password": $scope.password}, true)
		}).success(function(data) {
		    	console.log(data);
		    	$scope.message = data;
		    	$window.location.href = '/drDijagnoze';
		       }).error(function(error) {
		    	   console.log(error);
		    	   alert("Pogresan username i/ili password!");
		       });
		
	};

}]);