var app = angular.module('myApp');

app.controller('loginController',['$window','$scope','$http', function($window, $scope, $http){
	$scope.message = "";
	$scope.posalji = function()
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
		    	console.log(data);
		    	$scope.message = data;
		    	$window.location.href = '/dijagnoze';
		       }).error(function(error) {
		    	   console.log(error);
		    	   alert("Pogresan username i/ili password!");
		       });
		
		//$window.location.href = '/dijagnoze';
	};

}]);