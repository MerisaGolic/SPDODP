var app = angular.module('myApp');

app.controller('loginController',['$window','$scope','$http', function($window, $scope, $http){
	$scope.message = "";
	$scope.posalji = function()
	{
		alert($scope.username + " " + $scope.password);
		$http({
			method:'POST',
			url: '/modul-za-korisnike/login',
			data: {
				username: $scope.username,
				password: $scope.password				
			},
			contentType: "application/json"
		
		    //method: 'GET',
		   // url: 'http://localhost:8081/provjeraLogina?username='+$scope.username+'&password='+$scope.password
		}).then(function(result) {
		    	console.log(result.data);
		    	$scope.message = result;
		       }, function(error) {
		    	   console.log(error);
		       });
		$scope.username='';
		$scope.password='';
		$window.location.href = '/dijagnoze';
	};

}]);