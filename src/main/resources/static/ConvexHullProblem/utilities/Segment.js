function Segment(pointOne, pointTwo, color) {
	this.pointOne = pointOne;
	this.pointTwo = pointTwo;
	this.color = color;


	this.show = function() {
		stroke(this.color);
		strokeWeight(1);
		line(this.pointOne.x, this.pointOne.y, this.pointTwo.x, this.pointTwo.y);
	}

}