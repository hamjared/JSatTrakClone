package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import objects.Position;

public class TestPositionClass {
	
	private Position testPos;
	private int mapWidth;
	private int mapHeight;
	
	@BeforeEach
	public void init() {
		testPos = new Position(39.7392d, -104.9903d);
		mapWidth = 800;
		mapHeight = 800;
	}

	@Test
	public void testMercatorLatitude() {
		int returnedValue = testPos.getMercatorLatitude(mapWidth, mapHeight);
		int trueValue = 303;
		assertEquals(returnedValue, trueValue);
	}
	
	@Test
	public void testMercatorLongitude() {
		int returnedValue = testPos.getMercatorLongitude(mapWidth, mapHeight);
		int trueValue = 166;
		assertEquals(returnedValue, trueValue, "Mercator Longitude does not return correctly");
	}
	
	@AfterEach
	public void finalize() {
		testPos = null;
	}

}
