package org.youngmonkeys.cursor;

// Command interface
interface Command {
    int execute(int a, int b);
}

// Concrete command for addition
class AddCommand implements Command {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

// Concrete command for subtraction
class SubtractCommand implements Command {
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}

// Invoker class that uses commands
class Calculator {
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public int executeCommand(int a, int b) {
        if (command == null) {
            throw new IllegalStateException("No command set");
        }
        return command.execute(a, b);
    }
}

public class CommandPattern {

    public enum CommandName {
        ADD,
        SUB
    }

    public int operate(int a, int b, CommandName commandName) {
        Calculator calculator = new Calculator();
        
        switch (commandName) {
            case ADD:
                calculator.setCommand(new AddCommand());
                break;
            case SUB:
                calculator.setCommand(new SubtractCommand());
                break;
            default:
                throw new UnsupportedOperationException("unsupported: " + commandName);
        }
        
        return calculator.executeCommand(a, b);
    }
}
