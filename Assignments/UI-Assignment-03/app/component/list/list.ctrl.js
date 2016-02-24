angular.module('studentApp').controller('studentCtrl', function($scope,$state ,$http) {

    $http.post('http://localhost:9000/student/all').success(function(data) {
        $scope.studentsList = data;
    });

    $scope.sortField = 'id';
    $scope.reverse = true;
});

