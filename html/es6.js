// var name = 'Mike'

let name = 'Mike';
const PI = 3.14;
// PI = 4545; will not work a const cant be reassigned

function sample() {

    if(true) {
        var name = 'Mike'
    }
    console.log(name);

}

sample();

// template literal 

let message = 'JavaScript'
//let greeting = 'Hi ' + message;
let greeting = `Hi ` + message;

// Arrow Functions

// function add(a,b) {
//     return a+b;
// }

var add = (a,b) => {
    return a+b;
}

var result = add(6,7);
console.log(result);