angular.module('studentApp').config(function($stateProvider, $urlRouterProvider) {

  $urlRouterProvider.otherwise('list');

  $stateProvider

    .state('list', {
        url: '/list',
        templateUrl: 'component/list/list.html',
        controller: 'studentCtrl'
      })
      .state('add', {
          url: '/add',
          templateUrl: 'component/add/add.html',
          controller: 'addCtrl'
      })
});