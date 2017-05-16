var app = angular.module('myApp', []);




/*(function () {
var app = angular.module('myApp', ['ngRoute']);

app.config(function($routeProvider){
    $routeProvider
        .when('/login',{
            templateUrl: '/login.html',
            controller: 'loginController'
        })
        .when('/izvrsiDijagnozu',{
            templateUrl: '/izvrsiDijagnozu.html',
            controller: 'izvrsiDijagnozuController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});
}());

app.controller('myController', ['$location', '$scope', '$http', '$log', function($location, $scope, $http, $log){

	$scope.preusmjeri = function(){
		$log.log("uspio click");
		var REST_SERVICE_URI = 'http://localhost:8081/login';
		$http.get(REST_SERVICE_URI).then(function(response) {
			$scope.login = response.data;
			$log.log(response);
		}, function error(error) {
            ToasterService.pop('error', "Error", error.data.message);});

		//$location.path("/login");
	}

}]);
*/