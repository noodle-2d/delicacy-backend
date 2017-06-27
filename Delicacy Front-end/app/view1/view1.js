'use strict';

angular.module('myApp.view1', ['ngRoute', 'delicacyApp.services.products'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {
    templateUrl: 'view1/view1.html',
    controller: 'View1Ctrl'
  });
}])

.controller('View1Ctrl', ['$scope', '$routeParams', 'products', function($scope, $routeParams, products) {
	$scope.quantity = $routeParams.quantity;
	if ($scope.quantity == undefined) {
		$scope.quantity = appConstants.defaultQuantity;
	}
	$scope.response = products.newest($scope.quantity);
}]);