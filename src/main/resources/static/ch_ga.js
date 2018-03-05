var pointsInput;
var mutationRateInput;
var populationInput;

var runButton;

var canvasParent;
var canvas;
var cnvWidth;
var cnvHeight;

var loadingCircle;

var index;
var convexHullGA;
var loading;

function setup() {
    pointsInput = document.getElementById("pointsInput");
    mutationRateInput = document.getElementById("mutationRateInput");
    populationInput = document.getElementById("populationInput");

//    runButton = document.getElementById("runButton");
//    runButton.mousePressed(checkElements);

    cnvWidth = 4 * windowWidth / 5;
    cnvHeight = windowHeight;

    var canvasParent = document.getElementById("canvasParent");
    canvas = createCanvas(cnvWidth, cnvHeight);
    canvas.style('border','solid white 2px');
    canvas.parent('canvasParent');

    runButton = createButton('Run');
    runButton.parent("sidenav");
    runButton.addClass("btn");
    runButton.addClass("btn-success");
    runButton.mousePressed(checkElements);

    loadingCircle = new LoadingCircle(cnvWidth,cnvHeight);

    background(51);
}

function draw() {
    if (convexHullGA != undefined){
      convexHullGA.show(index,cnvWidth,cnvHeight);
      if (keyIsDown(LEFT_ARROW)){
        index--;
        if (index < 0){
            index = 0;
        }
      } else if (keyIsDown(RIGHT_ARROW)){
        index++;
        if (index > convexHullGA.fittestChromosomes.length - 1){
            index = convexHullGA.fittestChromosomes.length - 1;
        }
      }
    }
    else {
        background(51);
        if (loading == true){
            loadingCircle.show();
            loadingCircle.update();
        }
    }
}
function gotData(data){
    loading = false;
    convexHullGA = new ConvexHullGA(data.id,data.duration,data.mutationRate,data.population,data.points,data.fittestChromosomes);
}

function checkElements(){
    if (mutationRateInput.value != '' && pointsInput.value != '' && populationInput.value != ''){
        convexHullGA = undefined;
        index = 0;
        loading = true;
        loadJSON("http://localhost:8080/run?width="+parseInt(cnvWidth)+"&height="+parseInt(cnvHeight)+"&mutationRate="+mutationRateInput.value+"&populationCount="+populationInput.value+"&pointsCount="+pointsInput.value, gotData);
    }
    else {
        alert("Please fill in all the fields!");

    }
}

