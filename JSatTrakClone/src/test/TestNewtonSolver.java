package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import objects.NewtonsSolver;

public class TestNewtonSolver {

	@BeforeEach
	public void setUp() {
		
	}
	
	@Test
	void assureCorrectCalculations() {
		double e = 0.5;
		double Me = 6.0;
		double result = NewtonsSolver.solve(e, Me);
		double trueValue = 5.742741851605955d;
		assertEquals(result, trueValue);
	}
}