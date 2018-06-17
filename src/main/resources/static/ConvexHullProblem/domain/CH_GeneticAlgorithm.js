function CH_GeneticAlgorithm(duration, mutationRate, population, points, fittestChromosomes) {
    this.duration = duration;
    this.mutationRate = mutationRate;
    this.population = population;
    this.points = [];
    this.fittestChromosomes = [];
    this.showConvexHull = true;

    for (var i = 0; i < points.length; i++) {
        var label = points[i].label;
        var x = points[i].x;
        var y = points[i].y;
        var ch_point = new CH_Point(label, x, y);
        this.points.push(ch_point);
    }
    for (var i = 0; i < fittestChromosomes.length; i++) {
        var fitness = fittestChromosomes[i].fitness;
        var fitnessTechnique = fittestChromosomes[i].fitnessTechnique;
        var ch_points = fittestChromosomes[i].ch_points;

        var ch_chromosome = new CH_Chromosome(fitness, fitnessTechnique, ch_points);

        this.fittestChromosomes.push(ch_chromosome);
    }

    this.show = function (index, x, y, showConvexHull) {
        for (var i = 0; i < this.points.length; i++) {
            this.points[i].show(255);
        }
        if (showConvexHull) {
            this.fittestChromosomes[index].show();
            textSize(12.5);
            fill(255, 255, 255);
            noStroke();
            var firstOutput = 'Population: ' + this.population + ' | Points: ' + this.points.length + ' | Duration: ' + this.duration + ' s ';
            var secondOutput = 'Generation: ' + index + ' | Fitness: ' + this.fittestChromosomes[index].fitness + ' ' + this.fittestChromosomes[index].fitnessTechnique;
            text(firstOutput, 0.025 * x, 0.04 * y);
            text(secondOutput, 0.025 * x, 0.08 * y);
        }

    }
}