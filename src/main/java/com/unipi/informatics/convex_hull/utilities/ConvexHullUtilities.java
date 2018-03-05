package com.unipi.informatics.convex_hull.utilities;

import com.unipi.informatics.convex_hull.domain.Point;

import java.util.*;

import static com.unipi.informatics.convex_hull.utilities.MathUtilities.isInside;

public class ConvexHullUtilities {

    public static List<Point> calculateSickJoints(List<Point> convexHull) {
        int winding = MathUtilities.calculateWinding(convexHull);
        List<Point> sickJoints = new ArrayList<>();
        int sickNumber;
        //Means convexHull is clock wise
        if (winding == 1) {
            sickNumber = 1;
        } else {
            sickNumber = 2;
        }
        for (int i = 0; i < convexHull.size(); i++) {
            Point p0, p1, p2;
            if (i < convexHull.size() - 2) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(i + 1);
                p2 = convexHull.get(i + 2);
                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            } else if (i == convexHull.size() - 2) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(i + 1);
                p2 = convexHull.get(0);
                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            } else if (i == convexHull.size() - 1) {
                p0 = convexHull.get(i);
                p1 = convexHull.get(0);
                p2 = convexHull.get(1);
                if (MathUtilities.orientation(p0, p1, p2) == sickNumber) {
                    sickJoints.add(p1);
                }
            }
        }
        return sickJoints;
    }

    public static List<Point> calculateOutsidePoints(List<Point> convexHull, List<Point> points) {
        List<Point> outsidePoints = new ArrayList<>();
        for (Point point : points) {
            if (!MathUtilities.isInside(convexHull, point) && !convexHull.contains(point)) {
                outsidePoints.add(new Point(point));
            }
        }
        return outsidePoints;
    }

    public static int calculateIntersections(List<Point> convexHull) {
        int intersections = 0;
        for (int i = 0; i < convexHull.size() - 1; i++) {
            int currentEdgeStart = i;
            int currentEdgeEnd = i + 1;
            for (int j = currentEdgeEnd + 1; j < convexHull.size() - 1; j++) {
                int testEdgeStart = j;
                int testEdgeEnd = j + 1;

                if (MathUtilities.doIntersect(convexHull.get(currentEdgeStart), convexHull.get(currentEdgeEnd),
                        convexHull.get(testEdgeStart), convexHull.get(testEdgeEnd))) {
                    intersections++;
                }
            }
        }
        int currentEdgeStart = convexHull.size() - 1;
        int currentEdgeEnd = 0;
        for (int i = 1; i < convexHull.size() - 2; i++) {
            int testEdgeStart = i;
            int testEdgeEnd = i + 1;
            if (MathUtilities.doIntersect(convexHull.get(currentEdgeStart), convexHull.get(currentEdgeEnd),
                    convexHull.get(testEdgeStart), convexHull.get(testEdgeEnd))) {
                intersections++;
            }
        }
        return intersections;
    }

    public static int findClosest(Point checkPoint, List<Point> polygon) {
        double minDistance = Integer.MAX_VALUE;
        int minPoint = 0;
        for (int i = 0; i < polygon.size(); i++) {
            double d = MathUtilities.calculateDistance(checkPoint, polygon.get(i));
            if (d < minDistance && d > 0) {
                minDistance = d;
                minPoint = i;
            }
        }
        return minPoint;
    }

    public static Set<Point> calculateEdgePolygon(List<Point> points) {
        double edgeLeft = Integer.MAX_VALUE;
        Point edgeLeftPoint = null;

        double edgeRight = Integer.MIN_VALUE;
        Point edgeRightPoint = null;

        double edgeTop = Integer.MAX_VALUE;
        Point edgeTopPoint = null;

        double edgeBottom = Integer.MIN_VALUE;
        Point edgeBottomPoint = null;

        for (Point point : points) {
            //Getting the 4 edges
            if (Double.compare(point.getX(), edgeLeft) < 0) {
                edgeLeft = point.getX();
                edgeLeftPoint = point;
            }
            if (Double.compare(point.getX(), edgeRight) > 0) {
                edgeRight = point.getX();
                edgeRightPoint = point;
            }
            if (Double.compare(point.getY(), edgeTop) < 0) {
                edgeTop = point.getY();
                edgeTopPoint = point;
            }
            if (Double.compare(point.getY(), edgeBottom) > 0) {
                edgeBottom = point.getY();
                edgeBottomPoint = point;
            }
        }
        Set<Point> edgePolygon = new LinkedHashSet<>();
        edgePolygon.add(new Point(edgeBottomPoint));
        edgePolygon.add(new Point(edgeLeftPoint));
        edgePolygon.add(new Point(edgeTopPoint));
        edgePolygon.add(new Point(edgeRightPoint));

        return edgePolygon;
    }

    public static List<Point> calculatePossibleHullPoints(List<Point> edgePolygon, List<Point> points) {
        Set<Point> possibleHullPoints = new LinkedHashSet<>();
        for (Point point : points) {
            if (!isInside(edgePolygon, point)) {
                possibleHullPoints.add(new Point(point));
            }
        }
        return new ArrayList<>(possibleHullPoints);
    }

    public static List<Point> generatePoints(int width, int height, int pointsCount) {//}, Canvas canvas) {
        Set<Point> pointSet = new HashSet<>();
//        pointList.add(new Point(0,45,45));
//        pointList.add(new Point(1,150,45));
//        pointList.add(new Point(2,45,355));
//        pointList.add(new Point(3,150,355));
//        pointList.add(new Point(4,65,234));
//        pointList.add(new Point(5,186,434));
//        pointList.add(new Point(6,195,255));
//        pointList.add(new Point(7,250,300));
        //Creating random coordinates (points)

        double v_border_bottom = 0.01 * height;
        double v_border_top = 0.1 * height;
        double h_border = 0.01 * width;

        for (int i = 0; i < pointsCount; i++) {
            double x = new Random().nextDouble() * (double) width;
            if (x < h_border) {
                x += h_border;
            } else if (x >= width - h_border) {
                x -= h_border;
            }
            double y = new Random().nextDouble() * (double) height;
            if (y < v_border_top) {
                y += v_border_top;
            } else if (y >= height - v_border_bottom) {
                y -= v_border_bottom;
            }
            Point point = new Point(i, x, y);
            pointSet.add(point);
        }
//        Point middle = new Point(-1,(double) canvas.getWidth()/2,(double)  2 * canvas.getHeight()/5);
//        int i = 0;
//        int radius = 0;
//        if (canvas.getWidth() < canvas.getHeight()){
//            radius = canvas.getWidth();
//        }
//        else{
//            radius = canvas.getHeight();
//        }
//        while (pointList.size() < pointsCount){
//            double x = new Random().nextDouble() * radius;
//            double y = new Random().nextDouble() * radius;
//            Point testP = new Point(i, x,y);
//            if (MathUtilities.calculateDistance(middle,testP) < (double) radius/3){
//                pointList.add(testP);
//                i++;
//            }
//
//        }
//        pointList.add(new Point(0,7,19));
//        pointList.add(new Point(1,62,68));
//        pointList.add(new Point(2,90,19));
//        pointList.add(new Point(3,63,106));
//        pointList.add(new Point(4,54,104));
//        pointList.add(new Point(5,49,134));
//        pointList.add(new Point(6,74,57));
//        pointList.add(new Point(7,95,42));
//        pointList.add(new Point(8,86,67));
//        pointList.add(new Point(9,75,75));
//        pointList.add(new Point(10,73,88));
//        pointList.add(new Point(11,23,40));
//        pointList.add(new Point(12,33,18));
//        pointList.add(new Point(13,19,22));
//        pointList.add(new Point(14,114,20));
//        pointList.add(new Point(15,49,89));
//        pointList.add(new Point(16,73,133));
//        pointList.add(new Point(17,102,25));
//        pointList.add(new Point(18,44,47));
//        pointList.add(new Point(19,36,53));
//        pointList.add(new Point(20,54,119));
//        pointList.add(new Point(21,33,32));
//        pointList.add(new Point(22,86,34));
//        pointList.add(new Point(23,19,32));
//        pointList.add(new Point(24,48,50));
//        pointList.add(new Point(25,69,118));
//        pointList.add(new Point(26,83,54));
//        pointList.add(new Point(27,73,64));
//        pointList.add(new Point(28,40,68));
//        pointList.add(new Point(29,59,85));

        ////////////////
//        pointList.add(new Point(0,209.50210189819333,195.60152206420895));
//        pointList.add(new Point(1,213.9099424034357,203.94567085057497));
//        pointList.add(new Point(2,211.87145957946774,212.16427021026612));
//        pointList.add(new Point(3,206.7116783574224,204.83763366937637));
//        pointList.add(new Point(4,198.84676968902343,200.83588110059497));
//        pointList.add(new Point(5,188.84878734480588,200.49778507929295));
//        pointList.add(new Point(6,180.9,203.75922202765938));
//        pointList.add(new Point(7,180.89999999999998,213.7605197729077));
//        pointList.add(new Point(8,183.31303389072417,222.40000000000003));
//        pointList.add(new Point(9,193.1187110900879,221.8696762084961));
//        pointList.add(new Point(10,198.8251838684082,215.49999999999997));
//        pointList.add(new Point(11,202.2030548095703,224.36926841735837));
//        pointList.add(new Point(12,199.35298156738278,233.82638206481934));
//        pointList.add(new Point(13,194.1438442468643,228.26478422880172));
//        pointList.add(new Point(14,184.8009821414947,226.52192212343212));
//        pointList.add(new Point(15,180.12862768173218,233.9768527030945));
//        pointList.add(new Point(16,179.73896322250366,243.95356702804565));
//        pointList.add(new Point(17,184.2714312434196,251.68632366657255));
//        pointList.add(new Point(18,194.12421607356518,252.2));
//        pointList.add(new Point(19,204.11851882934573,252.18339691162106));
//        pointList.add(new Point(20,209.0356743335724,243.81105111390352));
//        pointList.add(new Point(21,213.7669225692749,244.25693206787108));
//        pointList.add(new Point(22,211.96920070648196,253.90469818115236));
//        pointList.add(new Point(23,203.58459103833883,257.5270687019452));
//        pointList.add(new Point(24,193.5790277742082,257.349754922581));
//        pointList.add(new Point(25,183.58483649492263,257.1726426720619));
//        pointList.add(new Point(26,173.58885382411532,256.99549867536405));
//        pointList.add(new Point(27,166.3509521484375,253.46976318359376));
//        pointList.add(new Point(28,173.2,247.54651941992338));
//        pointList.add(new Point(29,173.2,237.54988281325785));
//        pointList.add(new Point(30,173.2,227.54386312961574));
//        pointList.add(new Point(31,173.19999999999996,217.54652603944416));
//        pointList.add(new Point(32,173.19999999999996,207.55567702166735));
//        pointList.add(new Point(33,169.08703767359253,199.81299298554657));
//        pointList.add(new Point(34,168.3135359168053,195.5247630596161));
//        pointList.add(new Point(35,178.29108505249025,195.20028991699215));
//        pointList.add(new Point(36,188.30061431378127,195.3282130040228));
//        pointList.add(new Point(37,198.3021906917915,195.4564383422024));
//        pointList.add(new Point(38,208.2960404381156,195.58456462100145));
//        pointList.add(new Point(39,274.20210189819335,209.00210189819336));
//        pointList.add(new Point(40,274.82925680875775,216.7575372338295));
//        pointList.add(new Point(41,265.0943880081177,216.47772903442387));
//        pointList.add(new Point(42,255.63663406372072,219.39333801269532));
//        pointList.add(new Point(43,248.55837178230286,226.3458360671997));
//        pointList.add(new Point(44,246.99999999999997,235.90686216773466));
//        pointList.add(new Point(45,247,245.9174873657525));
//        pointList.add(new Point(46,253.66830691993235,250.89685185402632));
//        pointList.add(new Point(47,263.43842773437495,251.73222656250005));
//        pointList.add(new Point(48,259.25992552032704,257.1240892163711));
//        pointList.add(new Point(49,249.26980651021003,256.8970410570503));
//        pointList.add(new Point(50,239.2754738989286,256.66989713406656));
//        pointList.add(new Point(51,230.0035139083862,254.7035139083862));
//        pointList.add(new Point(52,237.11501541137693,250.78498458862302));
//        pointList.add(new Point(53,239.638683617115,241.73006467819215));
//        pointList.add(new Point(54,239.99105489344802,231.7355339312926));
//        pointList.add(new Point(55,240.3435650156811,221.73706500977278));
//        pointList.add(new Point(56,236.60869329720737,213.680664947629));
//        pointList.add(new Point(57,238.55320041347298,209));
//        pointList.add(new Point(58,246.6927734375,212.28286132812502));
//        pointList.add(new Point(59,251.99371948242185,215.2062805175781));
//        pointList.add(new Point(60,260.733410821855,210.68885927945377));
//        pointList.add(new Point(61,270.34059925079345,208.80702996253967));
//        pointList.add(new Point(62,337.70210189819335,209.00210189819333));
//        pointList.add(new Point(63,338.3292568087577,216.7575372338295));
//        pointList.add(new Point(64,328.5943880081177,216.47772903442385));
//        pointList.add(new Point(65,319.13663406372075,219.39333801269532));
//        pointList.add(new Point(66,312.0583717823029,226.34583606719968));
//        pointList.add(new Point(67,310.5,235.9068621677347));
//        pointList.add(new Point(68,310.49999999999994,245.9174873657525));
//        pointList.add(new Point(69,317.1683069199323,250.89685185402627));
//        pointList.add(new Point(70,326.938427734375,251.73222656250005));
//        pointList.add(new Point(71,322.759925520327,257.1240892163711));
//        pointList.add(new Point(72,312.76980651021006,256.89704105705016));
//        pointList.add(new Point(73,302.7754738989286,256.66989713406656));
//        pointList.add(new Point(74,293.5035139083863,254.7035139083862));
//        pointList.add(new Point(75,300.615015411377,250.78498458862305));
//        pointList.add(new Point(76,303.138683617115,241.73006467819215));
//        pointList.add(new Point(77,303.491054893448,231.73553393129257));
//        pointList.add(new Point(78,303.8435650156811,221.73706500977278));
//        pointList.add(new Point(79,300.1086932972074,213.680664947629));
//        pointList.add(new Point(80,302.0532004134729,209));
//        pointList.add(new Point(81,310.1927734375,212.28286132812497));
//        pointList.add(new Point(82,315.49371948242185,215.20628051757814));
//        pointList.add(new Point(83,324.23341082185505,210.68885927945377));
//        pointList.add(new Point(84,333.8405992507934,208.80702996253967));
//        pointList.add(new Point(85,379.20800737142565,210.99848728179933));
//        pointList.add(new Point(86,389.1384756088257,210.68409729003906));
//        pointList.add(new Point(87,398.34060974121087,214.361962890625));
//        pointList.add(new Point(88,404.92848119735726,221.73567371368412));
//        pointList.add(new Point(89,408.0463947534561,231.21157789230344));
//        pointList.add(new Point(90,407.7262363433838,241.1523483276367));
//        pointList.add(new Point(91,403.6924961090087,250.2302780151367));
//        pointList.add(new Point(92,396.18106414191425,256.50228605642917));
//        pointList.add(new Point(93,386.84380531311035,259.90369567871096));
//        pointList.add(new Point(94,376.89907312393194,259.4912376403808));
//        pointList.add(new Point(95,367.78789062499993,255.50312500000004));
//        pointList.add(new Point(96,361.0009821414948,248.35812003612517));
//        pointList.add(new Point(97,357.61949570178984,238.96119868755343));
//        pointList.add(new Point(98,357.68260164260863,229.00190687179565));
//        pointList.add(new Point(99,361.7311093330383,219.9623103141785));
//        pointList.add(new Point(100,369.15117187499993,213.392578125));
//        pointList.add(new Point(101,378.87424196004866,211.07756143808365));
//        pointList.add(new Point(102,371.19528884887694,218.30732040405277));
//        pointList.add(new Point(103,365.7879538580775,226.70948708206421));
//        pointList.add(new Point(104,364.92513607516884,236.39233066849414));
//        pointList.add(new Point(105,367.5132851600647,245.50293722152708));
//        pointList.add(new Point(106,374.73774629086256,252.12145603746177));
//        pointList.add(new Point(107,384.3329299926758,254.29463195800778));
//        pointList.add(new Point(108,393.57785692214964,250.92029895782468));
//        pointList.add(new Point(109,399.3615055680275,242.93602650314568));
//        pointList.add(new Point(110,401.50870819091796,233.3553497314453));
//        pointList.add(new Point(111,398.9143023490906,223.81053180694582));
//        pointList.add(new Point(112,391.8540575981139,216.88456134796147));
//        pointList.add(new Point(113,382.15666538737713,215.7));
//        pointList.add(new Point(114,372.587882232666,217.64391021728517));
//        pointList.add(new Point(115,467.1021018981933,209.00210189819336));
//        pointList.add(new Point(116,467.7292568087578,216.7575372338295));
//        pointList.add(new Point(117,457.99438800811765,216.47772903442385));
//        pointList.add(new Point(118,448.53663406372067,219.39333801269532));
//        pointList.add(new Point(119,441.45837178230283,226.3458360671997));
//        pointList.add(new Point(120,439.9,235.90686216773466));
//        pointList.add(new Point(121,439.9,245.91748736575246));
//        pointList.add(new Point(122,446.5683069199323,250.89685185402627));
//        pointList.add(new Point(123,456.338427734375,251.73222656250002));
//        pointList.add(new Point(124,452.15992552032696,257.1240892163711));
//        pointList.add(new Point(125,442.16980651021004,256.8970410570502));
//        pointList.add(new Point(126,432.1754738989286,256.66989713406656));
//        pointList.add(new Point(127,422.90351390838623,254.70351390838624));
//        pointList.add(new Point(128,430.01501541137685,250.78498458862302));
//        pointList.add(new Point(129,432.538683617115,241.73006467819215));
//        pointList.add(new Point(130,432.891054893448,231.7355339312926));
//        pointList.add(new Point(131,433.2435650156811,221.73706500977278));
//        pointList.add(new Point(132,429.50869329720734,213.68066494762897));
//        pointList.add(new Point(133,431.4532004134729,208.99999999999997));
//        pointList.add(new Point(134,439.59277343749994,212.282861328125));
//        pointList.add(new Point(135,444.89371948242183,215.20628051757814));
//        pointList.add(new Point(136,453.633410821855,210.68885927945374));
//        pointList.add(new Point(137,463.2405992507934,208.80702996253967));
//        pointList.add(new Point(138,579.1051635742188,188.40516357421876));
//        pointList.add(new Point(139,580.9,197.66223723515864));
//        pointList.add(new Point(140,580.9,207.6568347910535));
//        pointList.add(new Point(141,580.9,217.65273519394685));
//        pointList.add(new Point(142,580.8999999999999,227.66282005310057));
//        pointList.add(new Point(143,585.9369140624999,234.56005859375003));
//        pointList.add(new Point(144,580.9994789553806,241.71752831321206));
//        pointList.add(new Point(145,583.9753906249998,250.57539062499998));
//        pointList.add(new Point(146,576.0988531112671,253.60000000000005));
//        pointList.add(new Point(147,567.8385543823241,251.65361404418945));
//        pointList.add(new Point(148,574.348249346018,245.78326242566106));
//        pointList.add(new Point(149,572.6007108435035,236.5554070830345));
//        pointList.add(new Point(150,563.2817204376681,235.09999999999997));
//        pointList.add(new Point(151,553.2806911587714,235.09999999999997));
//        pointList.add(new Point(152,545,233.38012301027774));
//        pointList.add(new Point(153,545.0000000000001,223.3809410754591));
//        pointList.add(new Point(154,549.2430738449096,214.6213941574096));
//        pointList.add(new Point(155,555.7401172161101,207.02908205986026));
//        pointList.add(new Point(156,563.3787475109099,200.59779047966006));
//        pointList.add(new Point(157,568.8684242248535,192.28267440795895));
//        pointList.add(new Point(158,577.3862460613249,188.24420418739317));
//        pointList.add(new Point(159,546.1021018981933,227.50260925292966));
//        pointList.add(new Point(160,554.3780082663519,231.1));
//        pointList.add(new Point(161,564.3792590698228,231.10000000000002));
//        pointList.add(new Point(162,573.5253692626951,228.9020091056823));
//        pointList.add(new Point(163,575.0549712017179,219.27761307060717));
//        pointList.add(new Point(164,575.1462942196056,209.29227968510241));
//        pointList.add(new Point(165,574.7173034667968,199.30292358398444));
//        pointList.add(new Point(166,567.8125730512663,201.81664510667323));
//        pointList.add(new Point(167,561.346826171875,209.46484375));
//        pointList.add(new Point(168,554.8905600079102,217.10182785056534));
//        pointList.add(new Point(169,548.4374208367196,224.73511309586465));
//        pointList.add(new Point(170,619.7090324461459,189.29956164956093));
//        pointList.add(new Point(171,629.5938202857969,190.4051049232483));
//        pointList.add(new Point(172,638.0283264160155,195.5692932128906));
//        pointList.add(new Point(173,643.2767105102539,204.00101470947268));
//        pointList.add(new Point(174,644.91588473171,213.82665134370328));
//        pointList.add(new Point(175,645.0999999999999,223.81642025336626));
//        pointList.add(new Point(176,644.8846862792967,233.8098163604737));
//        pointList.add(new Point(177,642.3252929687499,243.43137817382816));
//        pointList.add(new Point(178,636.2639404296874,251.26972198486325));
//        pointList.add(new Point(179,627.4624801635741,255.8710026741028));
//        pointList.add(new Point(180,617.6428939819335,256.5397659301758));
//        pointList.add(new Point(181,608.5652770996093,252.5386047363281));
//        pointList.add(new Point(182,602.2670349121092,244.8751281738281));
//        pointList.add(new Point(183,599.4197495410218,235.33657812736925));
//        pointList.add(new Point(184,598.8455407083034,225.41404600143431));
//        pointList.add(new Point(185,599.0537241525947,215.42124067544935));
//        pointList.add(new Point(186,600.4529151439667,205.5629388570786));
//        pointList.add(new Point(187,605.110500907898,196.80513067245482));
//        pointList.add(new Point(188,613.2174726486205,191.10259237289432));
//        pointList.add(new Point(189,620.0927254796027,194.00127045512198));
//        pointList.add(new Point(190,611.0890754699706,198.03163566589353));
//        pointList.add(new Point(191,606.0014223098754,206.4802035331726));
//        pointList.add(new Point(192,604.9853998353705,216.38470584321763));
//        pointList.add(new Point(193,605.4918585196136,226.37575443238018));
//        pointList.add(new Point(194,605.9982155799864,236.3647982597351));
//        pointList.add(new Point(195,610.5132207974791,245.27846714258197));
//        pointList.add(new Point(196,617.8339923143385,251.45196365118028));
//        pointList.add(new Point(197,627.6037532806396,250.71064443588256));
//        pointList.add(new Point(198,634.7618026733398,243.99026145935056));
//        pointList.add(new Point(199,638.3323730468751,234.66596679687498));
//        pointList.add(new Point(200,640.0198318481445,224.82956805229185));
//        pointList.add(new Point(201,639.5353347778318,214.86849093437192));
//        pointList.add(new Point(202,636.5818801879881,205.34334101676941));
//        pointList.add(new Point(203,631.3251281738281,196.86308116912844));
//        pointList.add(new Point(204,622.059357296489,194.29099365789446));
//        pointList.add(new Point(205,693.9051635742186,188.40516357421876));
//        pointList.add(new Point(206,695.6999999999998,197.6622372351587));
//        pointList.add(new Point(207,695.6999999999999,207.65683479105354));
//        pointList.add(new Point(208,695.6999999999998,217.65273519394688));
//        pointList.add(new Point(209,695.6999999999998,227.66282005310057));
//        pointList.add(new Point(210,700.7369140624997,234.56005859374997));
//        pointList.add(new Point(211,695.7994789553804,241.71752831321209));
//        pointList.add(new Point(212,698.7753906249999,250.57539062499998));
//        pointList.add(new Point(213,690.898853111267,253.60000000000002));
//        pointList.add(new Point(214,682.6385543823243,251.65361404418948));
//        pointList.add(new Point(215,689.1482493460178,245.78326242566104));
//        pointList.add(new Point(216,687.4007108435036,236.55540708303448));
//        pointList.add(new Point(217,678.0817204376681,235.10000000000002));
//        pointList.add(new Point(218,668.0806911587715,235.1));
//        pointList.add(new Point(219,659.7999999999998,233.38012301027774));
//        pointList.add(new Point(220,659.8,223.38094107545916));
//        pointList.add(new Point(221,664.0430738449095,214.6213941574096));
//        pointList.add(new Point(222,670.5401172161102,207.02908205986023));
//        pointList.add(new Point(223,678.17874751091,200.59779047966003));
//        pointList.add(new Point(224,683.6684242248534,192.28267440795895));
//        pointList.add(new Point(225,692.1862460613249,188.24420418739317));
//        pointList.add(new Point(226,660.9021018981932,227.50260925292972));
//        pointList.add(new Point(227,669.178008266352,231.09999999999997));
//        pointList.add(new Point(228,679.1792590698226,231.09999999999997));
//        pointList.add(new Point(229,688.3253692626953,228.90200910568234));
//        pointList.add(new Point(230,689.8549712017177,219.27761307060717));
//        pointList.add(new Point(231,689.9462942196055,209.29227968510241));
//        pointList.add(new Point(232,689.5173034667968,199.30292358398438));
//        pointList.add(new Point(233,682.6125730512662,201.81664510667323));
//        pointList.add(new Point(234,676.1468261718747,209.46484375));
//        pointList.add(new Point(235,669.6905600079102,217.10182785056534));
//        pointList.add(new Point(236,663.2374208367196,224.73511309586465));
        return new ArrayList<>(pointSet);
    }

    public static List<Point> getRandomPoints(List<Point> points, int amount) {
        Set<Point> randomPoints = new LinkedHashSet<>();
        while (randomPoints.size() < amount) {
            randomPoints.add(new Point(points.get(new Random().nextInt(points.size()))));
        }
        return new ArrayList<>(randomPoints);
    }

}
