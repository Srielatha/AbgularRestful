var myApp = angular.module("myApp",[]);

myApp.controller("AppCtrl", ['$scope', '$http', function($scope, $http) {

	console.log("hello world from controller");

	var refresh = function(){

		$http.get('http://localhost:8080/Nextgear/student').success(function(response) {
			console.log(response);
			$scope.studentList = response;
			$scope.student = "";
		});

	};

	refresh();

	$scope.addContact = function() {

			console.log($scope.student);
			$http.post('http://localhost:8080/Nextgear/student', $scope.student).success(function(response) {
				refresh();
			});
		};
	$scope.remove = function(id) {
		console.log(id);
	    var confirm = window.confirm("Do you want to delete this?");
		if (confirm) {
			$http.delete('http://localhost:8080/Nextgear/student/'+id).success(function(response) {
				refresh();
			});
		}
		
	};

	$scope.edit = function(id) {
		console.log(id);
      
		$http.get('http://localhost:8080/Nextgear/student/'+id).success(function(response) {

			console.log(response);
			$scope.student = response;

		});
      
	}

	$scope.update = function() {
		console.log($scope.studentId);

		$http.put('http://localhost:8080/Nextgear/student/'+$scope.studentId, $scope.student).success(function(response) {
			refresh();
		});
	}

	$scope.clear = function() {
		$scope.student = "";
	}

}]);
