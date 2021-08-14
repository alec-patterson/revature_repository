// var fn = function() {
//     console.log('called');
// }

// setTimeout(fn, 1000);

// console.log('Started');

// setInterval(fn, 1000);

var add = function(a,b,cd) {

    setTimeout(function() {
        cd(a + b);
    }, 1000);

}

add(5,7,function(r) {
    console.log(r);
});
