package pt.longway.day;

import pt.longway.AdventBase;
import pt.longway.util.AdventUtil;

public class FirstExercise extends AdventBase {

	public FirstExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	@Override
	public void solveFirstPart() {

		String[] lines = super.fileContent.split("\r\n");
		int dial = 50;
		int minDial = 0;
		int maxDial = 99;
		int password = 0;
		for (String line : lines) {
			char direction = line.charAt(0);
			int jumpSize = Integer.valueOf(line.substring(1));
			
			while (jumpSize > 99) {
				jumpSize -= 100;
			}

			if (direction == 'L') {
				dial -= jumpSize;
			} else {
				dial += jumpSize;
			}

			if (dial < minDial) {
				dial += 100;
			}

			if (dial > maxDial) {
				dial -= 100;
			}
			
			if (dial == 0) {
				password++;
			}

		}

		System.out.println("First Exercise - First Part - The password is: " + password);

	}

	@Override
	public void solveSecondPart() {

		String[] lines = super.fileContent.split("\r\n");
		int dial = 50;
		int minDial = 0;
		int maxDial = 99;
		int password = 0;
		for (String line : lines) {
			char direction = line.charAt(0);
			int jumpSize = Integer.valueOf(line.substring(1));

			while (jumpSize > 99) {
				jumpSize -= 100;
				password++;
			}

			if (direction == 'L') {
				if (dial == 0) {
					password--;
				}
				dial -= jumpSize;
			} else {
				dial += jumpSize;
			}

			if (dial == 0) {
				password++;
			}
			if (dial < minDial) {
				dial += 100;
				password++;
			}

			if (dial > maxDial) {
				dial -= 100;
				password++;
			}
		}
		System.out.println("First Exercise - Second Part - The password is: " + password);

	}
}
