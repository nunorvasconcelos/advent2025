package pt.longway;

import pt.longway.firstday.FirstExercise;
import pt.longway.secondday.SecondExercise;
import pt.longway.thirdday.ThirdExercise;
import pt.longway.util.AdventUtil;

public class Advent {

	public static void main(String[] args) {
		
		AdventUtil util = new AdventUtil();
		FirstExercise first = new FirstExercise(util, "pt/longway/firstday/FirstExerciseInputRotations.txt");
		first.solveFirstPart();
		first.solveSecondPart();
		
		SecondExercise second = new SecondExercise(util, "pt/longway/secondday/SecondExerciseInput.txt");
		second.solveFirstPart();
		second.solveSecondPart();
		
		ThirdExercise third = new ThirdExercise(util, "pt/longway/thirdday/ThirdExerciseInput.txt");
		third.solveFirstPart();
		third.solveSecondPart();
		
	}

}
