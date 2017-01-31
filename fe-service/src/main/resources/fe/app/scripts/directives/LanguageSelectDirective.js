/**
 * @ngdoc function
 * @name translateApp.directive:LanguageSelectDirective
 * @description
 * # LanguageSelectDirective
 * Directive to append language select and set its view and behavior
 */
angular.module('farmacieFitosanitaraApp')
  .directive('ngTranslateLanguageSelect', function (LocaleService) {
    'use strict';

    return {
      restrict: 'A',
      replace: true,
      template: ''+
	  
	  '<li class="dropdown language navbar-right" ng-if="visible">' +
        '<a class="dropdown-toggle" data-toggle="dropdown" href="">{{"directives.language-select.Language" | translate}}' +
        '<span class="caret"></span></a>' +
        '<ul class="dropdown-menu">' + 
			'<li ng-model="localesDisplayName" ng-repeat="localesDisplayName in localesDisplayNames" ng-click="changeLanguage(localesDisplayName)"><a href=""> {{localesDisplayName}}</a> </li>' +	 
        '</ul>' + 
      '</li>' +
      '',
      controller: function ($scope) {
        $scope.currentLocaleDisplayName = LocaleService.getLocaleDisplayName();
        $scope.localesDisplayNames = LocaleService.getLocalesDisplayNames();
        $scope.visible = $scope.localesDisplayNames &&
        $scope.localesDisplayNames.length > 1;

        $scope.changeLanguage = function (locale) {
          LocaleService.setLocaleByDisplayName(locale);
        };
      }
    };
  });