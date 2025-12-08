package pt.longway.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JunctionBox {
	private final int x;
	private final int y;
	private final int z;

	private List<JunctionBox> circuit;
	private Map<JunctionBox, Integer> distances;

	public JunctionBox(int x, int y, int z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		distances = new HashMap<>();
	}

	public int distance(JunctionBox other) {
		if (!distances.containsKey(other)) {
			int distance = Math.abs(other.x - this.x) + Math.abs(other.y - this.y) + Math.abs(other.z - this.z);
			distances.put(other, distance);
			other.distances.put(this, distance);
		}
		return distances.get(other);
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	public boolean connectJunctionBox(JunctionBox other) {
		if (this.circuit == null && other.circuit == null) {
			this.circuit = new ArrayList<>();
			this.circuit.add(this);
			this.circuit.add(other);
			other.circuit = this.circuit;
			return true;
		} else if (this.circuit != null && other.circuit == null) {
			other.circuit = this.circuit;
			this.circuit.add(other);
			return true;
		} else if (this.circuit == null && other.circuit != null) {
			this.circuit = other.circuit;
			this.circuit.add(this);
			return true;
		} else if (this.circuit == other.circuit) {
			return false;
		} else {
			List<JunctionBox> jointCircuits = new ArrayList<>();
			jointCircuits.addAll(this.circuit);
			jointCircuits.addAll(other.circuit);
			for (JunctionBox junctionBox : this.circuit) {
				junctionBox.circuit = jointCircuits;
			}
			for (JunctionBox junctionBox : other.circuit) {
				junctionBox.circuit = jointCircuits;
			}
			return true;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JunctionBox other = (JunctionBox) obj;
		return x == other.x && y == other.y && z == other.z;
	}

	public List<JunctionBox> getCircuit() {
		return circuit;
	}

}