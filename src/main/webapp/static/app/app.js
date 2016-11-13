var app = angular.module('mailapp', ['ui.router','angular-loading-bar','angularUtils.directives.dirPagination']);

app.config(['$stateProvider','$urlRouterProvider',
	function ($stateProvider,$urlRouterProvider) {
		$urlRouterProvider.otherwise('/home');
		$stateProvider
			.state('home', {
				url: "/home",
				templateUrl: "templates/home.html"
			})
			.state('Newmail', {
				url: "/addnew",
				templateUrl: "templates/newmail.html"
			})
			.state('Editmail', {
				url: "/editmail",
				templateUrl: "templates/editmail.html"
			})
	}
]);

app.run(function($rootScope, $state) {
	$rootScope.$state = $state;
});

app.service('sharedmail', function () {
	var property = [];
	return {
		getProperty: function () {
			return property;
		},
		setProperty: function(value) {
			property = value;
		}
	};
});

app.filter('searchFor', function(){
	return function(arr, searchMail){
		if(!searchMail){
			return arr;
		}
		var result = [];
		searchMail = searchMail.toLowerCase();
		angular.forEach(arr, function(item){
			if(item.mailname.toLowerCase().indexOf(searchMail) !== -1 || item.authorname.toLowerCase().indexOf(searchMail) !== -1){
				result.push(item);
			}
		});
		return result;
	};
});

app.controller('mail-list',function($scope,$state,$http,sharedmail,$filter){
	$scope.maillist = {};
	$scope.pageSize = 5;
	$scope.currentPage = 1;
	$http.get("http://localhost:8080/mail").success(function(response){
		if(response.error === 0){
			$scope.maillist = response.Mails;
			$scope.items2 = $scope.maillist;
			$scope.$watch('searchMail', function(val){
				$scope.maillist = $filter('searchFor')($scope.items2, val);
			});
		}else{
			$scope.maillist = [];
		}
	});

	$scope.editMail = function($index){
		$scope.number = ($scope.pageSize * ($scope.currentPage - 1)) + $index;
		sharedmail.setProperty($scope.maillist[$scope.number]);
		$state.go('Editmail');
	};

	$scope.deleteMail = function($index){
		$scope.number = ($scope.pageSize * ($scope.currentPage - 1)) + $index;
		$http.delete("http://localhost:8080/mail/"+$scope.maillist[$scope.number].mailname).success(function(res){
			if(res.error == 0){
				$state.go($state.current, {}, {reload: true});
			}else{

			}
		});
	};
});

app.controller('add-new-mail',function($scope,$http,$state){
	$scope.maildata = {};
	$scope.addMail = function(){
		var payload = {
			"mailname":$scope.maildata.mailname,
			"authorname":$scope.maildata.authorname,
			"price":$scope.maildata.price
		}
		$http.post("http://localhost:8080/mail",payload).success(function(res){
			if(res.error == 0){
				$state.go("home");
			}else{

			}
		});
	};
});

app.controller('edit-mail',function($scope,$http,$state,sharedmail){
	$scope.maildata = sharedmail.getProperty();
	$scope.updateMail = function(){
		var payload = {
			"id":$scope.maildata._id,
			"mailname":$scope.maildata.mailname,
			"authorname":$scope.maildata.authorname,
			"price":$scope.maildata.price
		}
		$http.put("http://localhost:8080/mail",payload).success(function(res){
			if(res.error == 0){
				$state.go("home");
			}else{

			}
		});
	};
	$scope.cancel = function(){
		$state.go("home");
	};
});