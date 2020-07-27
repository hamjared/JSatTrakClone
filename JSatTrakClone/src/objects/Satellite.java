package objects;

import java.util.List;

public class Satellite {
	
	private Orbit orbit;
	private String name;
	
	public Satellite(String name, Orbit orbit) {
		this.orbit = orbit;
		this.name = name;
	}
	
	public List<Position> groundTrack(double duration){
		return null;
	}
	
	public String toString() {
		return name;
	}

	public Orbit getOrbit() {
		return orbit;
	}

	public void setOrbit(Orbit orbit) {
		this.orbit = orbit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
