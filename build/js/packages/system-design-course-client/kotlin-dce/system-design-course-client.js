(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'system-design-course-client'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'system-design-course-client'.");
    }root['system-design-course-client'] = factory(typeof this['system-design-course-client'] === 'undefined' ? {} : this['system-design-course-client'], kotlin);
  }
}(this, function (_, Kotlin) {
  'use strict';
  var ensureNotNull = Kotlin.ensureNotNull;
  var throwCCE = Kotlin.throwCCE;
  var Unit = Kotlin.kotlin.Unit;
  var IntRange = Kotlin.kotlin.ranges.IntRange;
  var contains = Kotlin.kotlin.ranges.contains_basjzs$;
  function main$lambda$lambda(it) {
    var tmp$, tmp$_0;
    console.log('kek');
    var input = Kotlin.isType(tmp$ = document.getElementById('key'), HTMLInputElement) ? tmp$ : throwCCE();
    var input_text = Kotlin.isType(tmp$_0 = document.getElementById('message'), HTMLTextAreaElement) ? tmp$_0 : throwCCE();
    console.log('kek');
    var http = new XMLHttpRequest();
    http.open('POST', '/post', true);
    http.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    http.send(encodeURIComponent(input.value) + ':' + encodeURIComponent(input_text.value));
    return Unit;
  }
  function main$lambda$lambda$lambda(closure$http, closure$input) {
    return function (it) {
      if (contains(new IntRange(200, 399), closure$http.status)) {
        var $receiver = closure$input;
        $receiver.value = closure$http.responseText;
        $receiver.setAttribute('readonly', 'true');
      }return Unit;
    };
  }
  function main$lambda$lambda_0(it) {
    var tmp$;
    var input = Kotlin.isType(tmp$ = document.getElementById('nickname'), HTMLInputElement) ? tmp$ : throwCCE();
    var http = new XMLHttpRequest();
    http.open('POST', '/registration', true);
    http.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    http.onload = main$lambda$lambda$lambda(http, input);
    http.send(encodeURIComponent(input.value));
    return Unit;
  }
  function main$lambda(it) {
    var signButton = ensureNotNull(document.getElementById('sign'));
    var postButton = ensureNotNull(document.getElementById('post'));
    postButton.addEventListener('click', main$lambda$lambda);
    signButton.addEventListener('click', main$lambda$lambda_0);
    return Unit;
  }
  function main() {
    window.onload = main$lambda;
  }
  _.main = main;
  main();
  return _;
}));

//# sourceMappingURL=system-design-course-client.js.map
