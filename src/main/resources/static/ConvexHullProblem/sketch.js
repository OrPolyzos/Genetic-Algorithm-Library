var pointsInput;
var mutationRateInput;
var populationInput;

var runButton;

var canvasParent;
var canvas;
var cnvWidth;
var cnvHeight;

var loadingAnimation;

var index;
var ch_geneticAlgorithm;
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
	canvas.style('border', 'solid white 2px');
	canvas.parent('canvasParent');

	runButton = createButton('Run');
	runButton.parent("sidenav");
	runButton.addClass("btn");
	runButton.addClass("btn-success");
	runButton.mousePressed(checkElements);

	loadingAnimation = new LoadingAnimation(cnvWidth, cnvHeight);

	background(51);
}

function draw() {
	if (ch_geneticAlgorithm != undefined) {
		ch_geneticAlgorithm.show(index, cnvWidth, cnvHeight);
		if (keyIsDown(LEFT_ARROW)) {
			index--;
			if (index < 0) {
				index = 0;
			}
		} else if (keyIsDown(RIGHT_ARROW)) {
			index++;
			if (index > ch_geneticAlgorithm.fittestChromosomes.length - 1) {
				index = ch_geneticAlgorithm.fittestChromosomes.length - 1;
			}
		}
	} else {
		background(51);
		if (loading == true) {
			loadingAnimation.show();
			loadingAnimation.update();
		}
	}
}

function gotData(data) {
	loading = false;
	ch_geneticAlgorithm = new CH_GeneticAlgorithm(data.duration, data.mutationRate, data.population, data.points, data.fittestChromosomes);
	console.log(ch_geneticAlgorithm);
}

function checkElements() {
	if (mutationRateInput.value != '' && pointsInput.value != '' && populationInput.value != '') {
		ch_geneticAlgorithm = undefined;
		index = 0;
		loading = true;
		loadJSON("http://localhost:8080/run?width=" + parseInt(cnvWidth) + "&height=" + parseInt(cnvHeight) + "&mutationRate=" + mutationRateInput.value + "&populationCount=" + populationInput.value + "&pointsCount=" + pointsInput.value, gotData);
	} else {
		alert("Please fill in all the fields!");

	}
}