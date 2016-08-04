//Milind Lingineni Period 4 APCS Calculator Project

public class Calc
{
	public static double calculate(double first, double second, String operator)
	{
		if(operator.equals(""))
			return 0;

		else if(operator.equals("multiply"))
		{
			return (first * second);
		}
		else if(operator.equals("divide"))
		{
			return (first / second);
		}
		else if(operator.equals("subtract"))
		{
			return (first - second);
		}
		else
		{
			return (first + second);
		}
	}
}
