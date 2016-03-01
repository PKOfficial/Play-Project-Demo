angular.module('studentApp').controller('addCtrl', function($scope,$state ,$http,addFactory) {

    $scope.onAdd = function(){
        var data = {"id":parseInt("1"),"name":$scope.username,"email":$scope.email};
        alert(data)
        addFactory.calls(data).success(function(data){
            alert("Saved");
            $state.go('list');
        });
    }

});

