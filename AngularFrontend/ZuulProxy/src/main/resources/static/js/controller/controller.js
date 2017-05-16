var app = angular.module('myApp');

//app.config(['$httpProvider', function ($httpProvider) {    
	//$httpProvider.defaults.headers.post['Content-Type'] = 'application/json';
//}]);

app.controller('myController',['$scope','$http', function($scope, $http){
	$scope.message = "";
	$scope.posalji = function()
	{
		/*var data = {
				username : $scope.username,
				password : $scope.password
		};
		var data = '&un=' + $scope.username + '&pw=' + $scope.password;
		$http.post('/provjeraLogina', data )
		.success(function(data, status, headers, config) {
			$scope.message = data;
		})
		.error(function(data, status, headers, config) {
			alert( "failure message: " + JSON.stringify({data: data}));
		});*/
		$http({
		    method: 'GET',
		    url: 'http://localhost:8081/provjeraLogina?username='+$scope.username+'&password='+$scope.password
		    //headers: {
		      //  "Content-Type": "application/json"
		//    }
		}).then(function(result) {
		    	console.log(result.data);
		    	$scope.message = result;
		       }, function(error) {
		    	   console.log(error);
		       });
		$scope.username='';
		$scope.password='';

		
	}
	

}]);
/*

app.controller('myController','$scope','$http', function($scope, $http){
	
	var data = 'username=' + $scope.username + 'password=' + $scope.password;								
	$http.post('/provjeraLogina', data )
	.success(function(data, status, headers, config) {
		$scope.message = data;
	})
	.error(function(data, status, headers, config) {
		alert( "failure message: " + JSON.stringify({data: data}));
	});
	// Making the fields empty
	//
	$scope.username='';
	$scope.password='';


});*/

/*app.controller('loginController', function($scope) {
    $scope.headingTitle = "Login";
});

app.controller('izvrsiDijagnozuController', function($scope) {
    $scope.headingTitle = "Izvrsi dijagnozu";
});

 
angular.module('myApp').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
    var self = this;
    self.user={id:null,username:'',address:'',email:''};
    self.users=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
 
 
    fetchAllUsers();
 
    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
 
    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating User');
            }
        );
    }
 
    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating User');
            }
        );
    }
 
    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting User');
            }
        );
    }
 
    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }
 
    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
 
    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }
 
 
    function reset(){
        self.user={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }
 
}]);*/