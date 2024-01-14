package Vezbi.tretaAuditoriskaVezbi.vtoraZadaca;



public class Calculator {
    private double result;
    private Strategy strategy;

    public Calculator() {
        this.result = 0.0;
    }

    public double getResult() {
        return result;
    }

    public String execute(char operation, double value)
    {
        if(operation == '+'){
            strategy = new Addition();
        }
        else if(operation == '-'){
            strategy = new Substraction();
        }
        else if(operation == '*'){
            strategy = new Multiplication();
        }
        else if(operation == '/'){
            strategy = new Division();
        }

        result = strategy.calculate(result, value);

        return String.format("updated result = %.2f", result);
    }

    public String init()
    {
        return String.format("result = %.2f", result);
    }

    @Override
    public String toString() {
        return String.format("updated result = %.2f", result);
    }
}
