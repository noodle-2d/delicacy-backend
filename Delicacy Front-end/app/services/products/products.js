//angular.module('delicacyApp');

angular.module('delicacyApp.services.products', [])

.factory('products', ['$http', function($http) {
	return {
		newest: function(quantity) {
			//return { id: 7, name: 'Name' };
			var url = appConstants.restServerUrl + '/newest?quantity=' + quantity;
			var result = {};
			$http.get(url).then(function(response) { result.data = response.data; });
			return result;
		}
	};
}]);