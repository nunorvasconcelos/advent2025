package pt.longway.day;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import pt.longway.AdventBase;
import pt.longway.util.AdventUtil;

public class FifthExercise extends AdventBase {

	public FifthExercise(AdventUtil util, String fileLocation) {
		super(util, fileLocation);
	}

	@Override
	public void solveFirstPart() {
		String[] allLines = super.fileContent.split("\r\n");
		int separationLine = Integer.MAX_VALUE;
		List<Range> ranges = new ArrayList<>();
		int result = 0;
		for (int i = 0; i < allLines.length; i++) {
			String line = allLines[i];
			if (line.equals("")) {
				separationLine = i;
				continue;
			}

			if (i < separationLine) {
				String[] range = line.split("-");
				ranges.add(new Range(Long.valueOf(range[0]), Long.valueOf(range[1])));
			} else {
				Long ingredientId = Long.valueOf(line);
				for (Range range : ranges) {
					if (range.isInRange(ingredientId)) {
						result++;
						break;
					}
				}
			}
		}

		System.out.println("Fifth Exercise - First Part - Number of fresh ingredients: " + result);

	}

	@Override
	public void solveSecondPart() {
		String[] allLines = super.fileContent.split("\r\n");
		BigInteger result = BigInteger.ZERO;
		List<Range> ranges = new ArrayList<>();

		for (int i = 0; i < allLines.length; i++) {
			String line = allLines[i];
			if (line.equals("")) {
				break;
			}
			// 530646678892456
			// 74210468237423
			String[] rangeSplit = line.split("-");
			Range newRange = new Range(Long.valueOf(rangeSplit[0]), Long.valueOf(rangeSplit[1]));
			ranges.add(newRange);

		}
		ranges.sort(Comparator.comparing(Range::getMin));
		Set<Range> setRanges = removeDuplicateRanges(ranges);
		for (Range range : setRanges) {
			result = result.add(range.numberOfIngredients());
		}

		System.out.println("Fifth Exercise - Second Part - Number of total fresh ingredients: " + result);

	}

	private Set<Range> removeDuplicateRanges(List<Range> currentRangeList) {
		Set<Range> finalList = new LinkedHashSet<>();
		
		for (Range currRange : currentRangeList) {
			boolean ignoreRange = false;
			for (Range finalRange : finalList) {
				if(finalRange.enclosesRange(currRange)) {
					ignoreRange = true;
					break;
				}
				
				if(finalRange.isInRange(currRange.getMin())) {
					finalRange.setMax(currRange.getMax());
					ignoreRange = true;
				}
				
				else if(finalRange.isInRange(currRange.getMax())) {
					finalRange.setMin(currRange.getMin());
					ignoreRange = true;
				}
				
			}
			
			if(!ignoreRange) {
				finalList.add(currRange);
			}
		}


		return finalList;
	}

	private class Range {

		private long min;
		private long max;

		public Range(long min, long max) {
			super();
			this.min = min;
			this.max = max;
		}

		public boolean isInRange(long x) {
			return x >= min && x <= max;
		}
		
		public boolean enclosesRange(Range other) {
			return this.min <= other.min && this.max >= other.max;
		}

		public BigInteger numberOfIngredients() {
			return BigInteger.valueOf(max).subtract(BigInteger.valueOf(min)).add(BigInteger.ONE);
		}

		public long getMin() {
			return min;
		}

		public void setMin(long min) {
			this.min = min;
		}

		public long getMax() {
			return max;
		}

		public void setMax(long max) {
			this.max = max;
		}

		@Override
		public String toString() {
			return min + " - " + max;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(max, min);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Range other = (Range) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return max == other.max && min == other.min;
		}

		private FifthExercise getEnclosingInstance() {
			return FifthExercise.this;
		}

	}

}
