package pt.longway;

import pt.longway.day.FifthExercise;
import pt.longway.day.FirstExercise;
import pt.longway.day.FourthExercise;
import pt.longway.day.SecondExercise;
import pt.longway.day.SeventhExercise;
import pt.longway.day.SixthExercise;
import pt.longway.day.ThirdExercise;
import pt.longway.util.AdventUtil;

public class Advent {

	public static void main(String[] args) {
		
		AdventUtil util = new AdventUtil();
		FirstExercise first = new FirstExercise(util, "FirstExerciseInputRotations.txt");
		first.solveFirstPart();
		first.solveSecondPart();
		
		SecondExercise second = new SecondExercise(util, "SecondExerciseInput.txt");
		second.solveFirstPart();
		second.solveSecondPart();
		
		ThirdExercise third = new ThirdExercise(util, "ThirdExerciseInput.txt");
		third.solveFirstPart();
		third.solveSecondPart();
		
		FourthExercise fourth = new FourthExercise(util, "FourthExerciseInput.txt");
		fourth.solveFirstPart();
		fourth.solveSecondPart();
		
		FifthExercise fifth = new FifthExercise(util, "FifthExerciseInput.txt");
		fifth.solveFirstPart();
		fifth.solveSecondPart();
		
		SixthExercise sixth = new SixthExercise(util, "SixthExerciseInput.txt");
		sixth.solveFirstPart();
		sixth.solveSecondPart();
		
		SeventhExercise seventh = new SeventhExercise(util, "SeventhExerciseInput.txt");
		seventh.solveFirstPart();
		seventh.solveSecondPart();
		
	}

}
