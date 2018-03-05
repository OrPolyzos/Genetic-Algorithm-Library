function ConvexHullChromosome (id, fitness, fitnessTechnique, chromosomeConvexHullPoints) {
 this.id = id;
 this.fitness = fitness;
 this.fitnessTechnique = fitnessTechnique;
 this.chromosomeConvexHullPoints = [];

 for (var i = 0; i < chromosomeConvexHullPoints.length; i++){
    var chromosomeConvexHullPoint = new ConvexHullGAPoint(chromosomeConvexHullPoints[i].pointDAO.id,chromosomeConvexHullPoints[i].pointDAO.label, chromosomeConvexHullPoints[i].pointDAO.x,chromosomeConvexHullPoints[i].pointDAO.y);
    this.chromosomeConvexHullPoints.push(chromosomeConvexHullPoint);
 }

 this.show = function(){
    for (var i = 0; i < this.chromosomeConvexHullPoints.length; i++){
        this.chromosomeConvexHullPoints[i].show(255);
    }

     for (var i = 0; i < this.chromosomeConvexHullPoints.length-1; i++){
        var lineSegment = new Segment(this.chromosomeConvexHullPoints[i], this.chromosomeConvexHullPoints[i+1], color(0,191,255));
        lineSegment.show();
     }
     var finalLine = new Segment(this.chromosomeConvexHullPoints[this.chromosomeConvexHullPoints.length-1], this.chromosomeConvexHullPoints[0], color(0,191,255));
     finalLine.show();

 }

}