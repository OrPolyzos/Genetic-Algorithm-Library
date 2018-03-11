var pointsInput;
var mutationRateInput;
var populationInput;

var runButton;

var slider;

var canvasParent;
var canvas;
var cnvWidth;
var cnvHeight;

var loadingAnimation;

var index;
var ch_geneticAlgorithm;
var loading = true;

function setup() {
	pointsInput = document.getElementById("pointsInput");
	mutationRateInput = document.getElementById("mutationRateInput");
	populationInput = document.getElementById("populationInput");

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
	runButton.mousePressed(run);

	loadingAnimation = new LoadingAnimation(cnvWidth, cnvHeight);

	background(51);
}

function draw() {
	background(51);
	if (ch_geneticAlgorithm != undefined) {
		ch_geneticAlgorithm.show(slider.value(), cnvWidth, cnvHeight);
	} else {
		if (loading == true) {
			loadingAnimation.show();
			loadingAnimation.update();
		}
	}
}

function gotData(data) {
	loading = false;
	ch_geneticAlgorithm = new CH_GeneticAlgorithm(data.duration, data.mutationRate, data.population, data.points, data.fittestChromosomes);
	if (slider != undefined) {
		slider.remove();
	}
	slider = createSlider(0, ch_geneticAlgorithm.fittestChromosomes.length - 1, 0, 1);
	slider.position(0.5 * cnvWidth, cnvHeight - 0.05 * cnvHeight)
	slider.style('width', cnvWidth / 2 + 'px');
}

function checkElements() {
	if ((pointsInput.value).trim() == '') {
		alert("Please fill the Points count.");
		return false;
	}
	if ((mutationRateInput.value).trim() == '') {
		alert("Please fill the Mutation rate.");
		return false;
	}
	if ((populationInput.value).trim() == '') {
		alert("Please fill the Population count.");
		return false;
	}
	if (pointsInput.value < 3) {
		alert("There should be at least 3 points.");
		return false;
	}
	if (mutationRateInput.value <= 0 || mutationRateInput.value > 1) {
		alert("Mutation rate should be greater than 0 and less (or equal) than 1.");
		return false;
	}
	if (populationInput.value < 1) {
		alert("There should be at least 2 individuals in the population.")
		return false;
	}
	return true;
}

function run() {
	if (checkElements() == true) {
		ch_geneticAlgorithm = undefined;
		index = 0;
		loading = true;
		loadJSON("http://localhost:8080/run?width=" + parseInt(cnvWidth) + "&height=" + parseInt(cnvHeight) + "&mutationRate=" + mutationRateInput.value + "&populationCount=" + populationInput.value + "&pointsCount=" + pointsInput.value, gotData);
	}
}