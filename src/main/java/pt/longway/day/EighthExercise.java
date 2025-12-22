package pt.longway.day;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.longway.AdventBase;
import pt.longway.dto.JunctionBox;
import pt.longway.util.AdventUtil;

public class EighthExercise extends AdventBase {

	private final int NUMBER_OF_LARGEST_CIRCUITS = 3;
	private final int NUMBER_OF_CONNECTIONS = 1000;

	public EighthExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	@Override
	public void solveFirstPart() {
		String[] lines = fileContent.split("\r\n");
		List<JunctionBox> junctionBoxList = new ArrayList<>();

		// Transform input into List of JunctionBox
		for (String line : lines) {
			String[] junctionBoxCoordinates = line.split(",");
			junctionBoxList.add(new JunctionBox(Integer.valueOf(junctionBoxCoordinates[0]),
					Integer.valueOf(junctionBoxCoordinates[1]), Integer.valueOf(junctionBoxCoordinates[2])));
		}

		// Calculate every distance between everybox
		for (int i = 0; i < junctionBoxList.size(); i++) {
			JunctionBox currJunctionBox = junctionBoxList.get(i);
			for (int j = i + 1; j < junctionBoxList.size(); j++) {
				JunctionBox otherJunctionBox = junctionBoxList.get(j);
				currJunctionBox.distance(otherJunctionBox);
			}
		}

		int i = 0;
		List<Set<JunctionBox>> connections = new ArrayList<>();
		while (i < NUMBER_OF_CONNECTIONS) {
			JunctionBox nextShortestConnection = null;
			double minDistance = Double.MAX_VALUE;

			for (JunctionBox currJunctionBox : junctionBoxList) {
				if (currJunctionBox.getShortestDistance() < minDistance) {
					minDistance = currJunctionBox.getShortestDistance();
					nextShortestConnection = currJunctionBox;
				}
			}

			addJunctionBoxConnection(connections, nextShortestConnection);

			i++;

		}

		connections.sort(Comparator.comparingInt(Set::size));
		connections = connections.reversed();

		long result = 1L;
		for (int j = 0; j < NUMBER_OF_LARGEST_CIRCUITS && j < connections.size(); j++) {
			result *= connections.get(j).size();
		}
		System.out.println("Eighth Exercise - First Part - " + result);
	}

	private void printAllConnections(List<Set<JunctionBox>> connections) {
		System.out.println("All connections are: ");
		for (Set<JunctionBox> set : connections) {
			System.out.println(set);
		}
	}

	private void addJunctionBoxConnection(List<Set<JunctionBox>> connections, JunctionBox junctionBox) {

		Set<JunctionBox> firstSet = null;
		Set<JunctionBox> secondSet = null;
		for (Set<JunctionBox> connection : connections) {
			if (connection.contains(junctionBox)) {
				firstSet = connection;
			}
			if (connection.contains(junctionBox.getShortestDistanceJunctionBox())) {
				secondSet = connection;
			}
		}

		if (firstSet == null && secondSet == null) {
			Set<JunctionBox> newConnection = new HashSet<>();
			newConnection.add(junctionBox);
			newConnection.add(junctionBox.getShortestDistanceJunctionBox());
			connections.add(newConnection);
		}

		// if they're the same set (same memory reference
		if (firstSet == secondSet) {
		}
		// The second junctionBox doesn't exist in any set
		else if (firstSet != null && secondSet == null) {
			firstSet.add(junctionBox.getShortestDistanceJunctionBox());
		}
		// The first junctionBox doesn't exist in any set
		else if (firstSet == null && secondSet != null) {
			secondSet.add(junctionBox);
		}
		// Both already exist in different sets. Need to merge Sets
		else if (firstSet != null && secondSet != null) {
			connections.remove(secondSet);
			firstSet.addAll(secondSet);
		}
		junctionBox.getShortestDistanceJunctionBox().updateShortestDistance();
		junctionBox.updateShortestDistance();
	}

	@Override
	public void solveSecondPart() {
		int result = 0;
		String[] lines = fileContent.split("\r\n");
		List<JunctionBox> junctionBoxList = new ArrayList<>();

		// Transform input into List of JunctionBox
		for (String line : lines) {
			String[] junctionBoxCoordinates = line.split(",");
			junctionBoxList.add(new JunctionBox(Integer.valueOf(junctionBoxCoordinates[0]),
					Integer.valueOf(junctionBoxCoordinates[1]), Integer.valueOf(junctionBoxCoordinates[2])));
		}

		// Calculate every distance between everybox
		for (int i = 0; i < junctionBoxList.size(); i++) {
			JunctionBox currJunctionBox = junctionBoxList.get(i);
			for (int j = i + 1; j < junctionBoxList.size(); j++) {
				JunctionBox otherJunctionBox = junctionBoxList.get(j);
				currJunctionBox.distance(otherJunctionBox);
			}
		}

		List<Set<JunctionBox>> connections = new ArrayList<>();
		do {
			JunctionBox nextShortestConnection = null;
			double minDistance = Double.MAX_VALUE;

			for (JunctionBox currJunctionBox : junctionBoxList) {
				if (currJunctionBox.getShortestDistance() < minDistance) {
					minDistance = currJunctionBox.getShortestDistance();
					nextShortestConnection = currJunctionBox;
				}
			}
			
			result = nextShortestConnection.getX() * nextShortestConnection.getShortestDistanceJunctionBox().getX();

			addJunctionBoxConnection(connections, nextShortestConnection);

		} while (connections.get(0).size() != NUMBER_OF_CONNECTIONS);

		System.out.println("Eighth Exercise - Second Part - " + result);
	}

}
