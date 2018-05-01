function CH_Point(label, x, y) {
	this.label = label;
	this.x = x;
	this.y = y;

	this.show = function(color) {
		stroke(color);
		strokeWeight(5);
		point(this.x, this.y);
	}

}