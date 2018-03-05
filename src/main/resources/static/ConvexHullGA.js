function ConvexHullGA (id, duration, mutationRate, population, points, fittestChromosomes) {
 this.id = id;
 this.duration = duration;
 this.mutationRate = mutationRate;
 this.population = population;
 this.points = [];
 this.fittestChromosomes = [];

 for (var i = 0; i < points.length; i++){
    var convexHullGAPoint = new ConvexHullGAPoint(points[i].id, points[i].label, points[i].x, points[i].y);
    this.points.push(convexHullGAPoint);
 }
 for (var i = 0; i < fittestChromosomes.length; i++){
    var id = fittestChromosomes[i].id;
    var convexHullChromosome = new ConvexHullChromosome(fittestChromosomes[i].id, fittestChromosomes[i].fitness, fittestChromosomes[i].fitnessTechnique, fittestChromosomes[i].chromosomeConvexHullPoints);
    this.fittestChromosomes.push(convexHullChromosome);
 }

 this.show = function(index,x,y){
    background(51);
    for (var i = 0; i < this.points.length; i++){
        this.points[i].show(0);
    }
    this.fittestChromosomes[index].show();
    textSize(12.5);
    fill(255,255,255);
    noStroke();
    var firstOutput = 'Population: ' + this.population + ' | Points: ' + this.points.length + ' | Duration: ' + this.duration + ' s ';
    var secondOutput = 'Fitness: ' + this.fittestChromosomes[index].fitness + ' ' + this.fittestChromosomes[index].fitnessTechnique;
    text(firstOutput, 0.025*x ,0.04*y);
    text(secondOutput, 0.025*x, 0.08*y);
 }

}