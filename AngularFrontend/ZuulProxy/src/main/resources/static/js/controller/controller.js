var app = angular.module('myApp');

app.controller('myController',['$window','$scope','$http', function($window, $scope, $http){
	

	$scope.login = function()
	{
		$window.location.href = '/login';
	};

	$scope.dijagnoza = function()
	{
		$window.location.href = '/dijagnoze';
	}

}]);
