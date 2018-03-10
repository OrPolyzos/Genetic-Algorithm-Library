function CH_Chromosome(fitness, fitnessTechnique, ch_points) {
	this.fitness = fitness;
	this.fitnessTechnique = fitnessTechnique;
	this.ch_points = [];

	for (var i = 0; i < ch_points.length; i++) {
		var label = ch_points[i].pointDao.label;
		var x = ch_points[i].pointDao.x;
		var y = ch_points[i].pointDao.y;
		var ch_point = new CH_Point(label, x, y);
		this.ch_points.push(ch_point);
	}

	this.show = function() {
		for (var i = 0; i < this.ch_points.length; i++) {
			this.ch_points[i].show(255);
		}

		for (var i = 0; i < this.ch_points.length - 1; i++) {
			var lineSegment = new Segment(this.ch_points[i], this.ch_points[i + 1], color(0, 191, 255));
			lineSegment.show();
		}
		var finalLine = new Segment(this.ch_points[this.ch_points.length - 1], this.ch_points[0], color(0, 191, 255));
		finalLine.show();

	}

}