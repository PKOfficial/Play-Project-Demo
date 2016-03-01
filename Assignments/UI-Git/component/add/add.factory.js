angular.module("studentApp").factory("addFactory",function($http, $state){
    return {
        calls: function (datas){
            return $http({
                method: 'POST',
                url:'http://localhost:9000/student/save',
                data: JSON.stringify(datas),
                contentType: "application/json",
                dataType: "json"
            });
        }
    };
});