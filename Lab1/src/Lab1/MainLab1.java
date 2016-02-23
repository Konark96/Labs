package Lab1;

import java.util.Scanner;

public class MainLab1 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		// Inputed Variables
		int years_to_work;
		int years_retired;
		double annual_return_working;
		double annual_return_retired;
		double required_income;
		double monthly_SSI;

		// Calculated Variables
		double save_each_month;
		double need_saved;

		// Asking Questions And Saving Responses
		System.out.println("How many years do you plan to work?");
		years_to_work = sc.nextInt();

		annual_return_working = checkRange("What is the percent of your of annual return while working? (Between 0-20)",
				0, 20, sc);

		System.out.println("How many years do you plan to be retired for?");
		years_retired = sc.nextInt();

		annual_return_retired = checkRange("What is the percent of your of annual return when retired? (Between 0-3)",
				0, 3, sc);

		System.out.println("What is the required income?");
		required_income = sc.nextDouble();

		System.out.println("What is the monthly SSI?");
		monthly_SSI = sc.nextDouble();

		// Calculated Variables 
		need_saved = totalSaved(required_income, monthly_SSI, annual_return_retired, years_retired);
		save_each_month = monthlySavings(need_saved, annual_return_working, years_to_work);

		// Print Out The Calculated Values
		System.out.println("Each month you need to save $" + save_each_month + ".");
		System.out.println("You need $" + need_saved + " saved.");
	}

	//Rounds to the the hundredth's place 
	public static double dollarRounding(double value) {
		value = Math.round(value * 100);
		value = value / 100;
		return value ;
	}

	//Loops the message until a value the fits into the range is given
	public static double checkRange(String message, int min, int max, Scanner sc) {
		double x;
		do {
			System.out.println(message);
			x = sc.nextDouble();
			message = "Please Try Again";
		} while (x < min || x > max);
		return x;
	}

	//Calculates the total amount saved
	public static double totalSaved(double required_income, double monthly_SSI, double annual_return_retired,
			int years_retired) {
		double total = (required_income - monthly_SSI)
				* ((1 - (1 / (Math.pow(1 + (annual_return_retired / 100) / 12, years_retired * 12)))))
				/ ((annual_return_retired / 100) / 12);
		return dollarRounding(total);
	}

	//Calculates the money saved monthly
	public static double monthlySavings(double need_saved, double annual_return_working, int years_to_work) {
		double total = need_saved * (((annual_return_working / 100) / 12)
				/ ((Math.pow(1 + (annual_return_working / 100) / 12, years_to_work * 12)) - 1));
		return dollarRounding(total);

	}
}
