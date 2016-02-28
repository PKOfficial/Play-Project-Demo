var routerApp = angular.module('studentApp', ['ui.router']);

routerApp.config(function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/list');

    $stateProvider

        .state('list', {
            url: '/list',
            templateUrl: '/app/component/list/list/html'
        })
});