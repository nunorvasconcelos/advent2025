package pt.longway.day;

import java.util.ArrayList;
import java.util.List;

import pt.longway.AdventBase;
import pt.longway.dto.JunctionBox;
import pt.longway.util.AdventUtil;

public class EighthExercise extends AdventBase {

	public EighthExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	@Override
	public void solveFirstPart() {
		String[] lines = fileContent.split("\r\n");
		List<JunctionBox> junctionBoxList = new ArrayList<>();
		for (String line : lines) {
			String[] junctionBoxCoordinates = line.split(",");
			junctionBoxList.add(new JunctionBox(Integer.valueOf(junctionBoxCoordinates[0]),
					Integer.valueOf(junctionBoxCoordinates[1]), Integer.valueOf(junctionBoxCoordinates[2])));
		}
		for (int i = 0; i < junctionBoxList.size(); i++) {
			JunctionBox currJunctionBox = junctionBoxList.get(i);
			for (int j = i + 1; j < junctionBoxList.size(); j++) {
				JunctionBox otherJunctionBox = junctionBoxList.get(j);
				currJunctionBox.distance(otherJunctionBox);
			}
		}
		
		long result = 0L;

		System.out.println("Eighth Exercise - First Part - " + result);
	}

	@Override
	public void solveSecondPart() {
		long result = 0L;

		System.out.println("Eighth Exercise - Second Part - " + result);
	}

}
