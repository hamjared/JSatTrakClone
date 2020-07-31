package objects;

public class NewtonsSolver {
	
	private static double f(double x, double e, double Me) {
		return x - e*Math.sin(x) - Me;
	}

	private static double fprime(double x, double e) {
		return 1 - e*Math.cos(x);
	}
	
	public static double solve(double e,double Me) {
		int maxIterations = 10000;
		double xGuess = 5;
		
		int iteration = 0;
		double x_prev = xGuess;
		double x_cur = x_prev - f(x_prev, e, Me)/fprime(x_prev, e);
		
		while(true) {
			x_cur = x_prev - f(x_prev, e, Me)/fprime(x_prev, e);
			
			if(iteration > maxIterations) {
				System.out.println("Failed to converge");
				break;
			}
			
			if(Math.abs(x_cur - x_prev) < 0.0001) {
				break;
			}
			
			x_prev = x_cur;
			iteration ++;
		}
		
		return x_cur;
	}
	
	public static void main(String[] args) {
		double E = solve(0.5, 6.0);
		System.out.println(E);	
	}
}