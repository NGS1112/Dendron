package dendron.machine;

import java.util.List;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
import dendron.Errors;

/**
 * An abstraction of a computing machine that reads instructions
 * and executes them. It has an instruction set, a symbol table
 * for variables (instead of general-purpose memory), and a
 * value stack on which calculations are performed.
 *
 * (Everything is static to avoid the need to master the subtleties
 * of nested class instantiation or to pass the symbol table and
 * stack into every instruction when it executes.)
 *
 * THIS CLASS IS INCOMPLETE. The student must add code to it.
 *
 * @author James Heliotis
 * @author Nicholas Shinn
 */
public class Machine {

    /** Do not instatiate this class. */
    private Machine() {}

    public static interface Instruction {
        /**
         * Run this instruction on the Machine, using the Machine's
         * value stack and symbol table.
         */
        void execute();

        /**
         * Show the instruction using text so it can be understood
         * by a person.
         * @return a short string describing what this instruction will do
         */
        @Override
        String toString();
    }

    private static Map< String, Integer > table = null;
    private static Stack< Integer > stack = null;

    /**
     * Reset the Machine to a pristine state.
     * @see Machine#execute
     */
    private static void reset() {
        stack = new Stack<>();
        table = new HashMap<>();
    }

    /**
     * Generate a listing of a program on standard output by
     * calling the toString() method on each instruction
     * contained therein, in order.
     *
     * @param program the list of instructions in the program
     */
    public static void displayInstructions(
            List< Machine.Instruction > program ) {
        System.out.println( "\nCompiled code:" );
        for ( Machine.Instruction instr: program ) {
            System.out.println( instr );
        }
        System.out.println();
    }

    /**
     * Run a "compiled" program by executing in order each instruction
     * contained therein.
     * Report on the final size of the stack (should normally be empty)
     * and the contents of the symbol table.
     * @param program a list of Machine instructions
     */
    public static void execute( List< Instruction > program ) {
        reset();
        System.out.println("Executing compiled code...");
        for ( Instruction instr: program ) {
            instr.execute();
        }
        System.out.println( "Machine: execution ended with " +
                stack.size() + " items left on the stack." );
        System.out.println();
        Errors.dump( table );
    }

    /**
     * The ADD instruction
     */
    public static class Add implements Instruction {
        /**
         * Run the microsteps for the ADD instruction.
         */
        @Override
        public void execute() {
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push( op1 + op2 );
        }

        /**
         * Show the ADD instruction as plain text.
         * @return "ADD"
         */
        @Override
        public String toString() {
            return "ADD";
        }
    }

    /**
     * The STORE instruction
     */
    public static class Store implements Instruction {
        /** stores name of target variable */
        private String name;

        /**
         * Create a STORE instruction
         * @param ident the name of the target variable
         */
        public Store( String ident ) {
            this.name = ident;
        }
        /**
         * Run the microsteps for the STORE instruction.
         */
        @Override
        public void execute() {
            table.put( this.name, stack.pop() );
        }
        /**
         * Show the STORE instruction as plain text.
         * @return "STORE" followed by the target variable name
         */
        @Override
        public String toString() {
            return "STORE " + this.name;
        }
    }

    //
    // ENTER YOUR CODE FOR THE OTHER INSTRUCTION CLASSES HERE.
    //

    /**
     * The DIVIDE instruction
     */
    public static class Divide implements Instruction{

        /**
         * Run the microsteps for the Divide instruction
         */
        @Override
        public void execute(){
            int op1 = stack.pop();
            int op2 = stack.pop();
            stack.push( op1 / op2 );
        }

        /**
         * Show the Divide instruction as plain text
         *
         * @return "DIV"
         */
        @Override
        public String toString(){ return "DIV"; }
    }
    /**
     * The LOAD instruction
     */
    public static class Load implements Instruction{
        /** stores name of target variable */
        private String name;
        /**
         * Create a Load instruction
         *
         * @param ident name of target variable
         */
        public Load(String ident){this.name = ident;}
        /**
         * Run the steps for the Load instruction
         */
        @Override
        public void execute(){stack.push(table.get(this.name));}

        /**
         * Show the Load instruction as plain text
         *
         * @return "LOAD"
         */
        @Override
        public String toString(){ return "LOAD " + this.name;}
    }
    /**
     * The MULTIPLY instruction
     */
    public static class Multiply implements Instruction{
        /**
         * Runs the steps for the Multiply instruction
         */
        @Override
        public void execute(){
            int op2 = stack.pop();
            int op1 = stack.pop();
            stack.push( op1 * op2 );
        }

        /**
         * Show the Multiply instruction as plain text
         *
         * @return "MUL"
         */
        @Override
        public String toString(){return "MUL";}
    }
    /**
     * The NEGATE instruction
     */
    public static class Negate implements Instruction{
        /**
         * Runs the steps for the Negate instruction
         */
        @Override
        public void execute(){
            int value = stack.pop();
            stack.push(0-value);
        }

        /**
         * Show the Negate instruction as plain text
         *
         * @return "NEG"
         */
        @Override
        public String toString(){return "NEG";}
    }
    /**
     * The PRINT instruction
     */
    public static class Print implements Instruction{
        /**
         * Runs the steps for the Print instruction
         */
        @Override
        public void execute(){ System.out.println("*** " + stack.pop()); }

        /**
         * Show the print instruction as plain text
         *
         * @return "PRINT"
         */
        @Override
        public String toString(){return "PRINT";}
    }
    public static class PushConst implements Instruction{
        /** value to be pushed */
        private int value;

        /**
         * Create a PushConst instruction
         *
         * @param constant value to be pushed
         */
        public PushConst(int constant){this.value = constant;}
        /**
         * Runs the steps for the PushConst instruction
         */
        @Override
        public void execute(){stack.push(this.value);}

        /**
         * Show the PushConst instruction as a plain text
         *
         * @return "PUSH " and value
         */
        @Override
        public String toString(){return "PUSH " + this.value;}
    }

    /**
     * The SQUAREROOT instruction
     */
    public static class SquareRoot implements Instruction{
        /**
         * Runs the steps for the SquareRoot instruction
         */
        @Override
        public void execute(){
            double value = Math.sqrt(stack.pop());
            int true_value = (int) value;
            stack.push(true_value);
        }

        /**
         * Show the SquareRoot instruction as a plain text
         *
         * @return "SQRT"
         */
        @Override
        public String toString(){return "SQRT";}
    }
    /**
     * The SUBTRACT instruction
     */
    public static class Subtract implements Instruction{

        /**
         * Runs the steps of the Subtract instruction
         */
        @Override
        public void execute(){
            int op1 = stack.pop();
            int op2 = stack.pop();
            stack.push( op2 - op1 );
        }

        /**
         * Shows the Subtract isntruction as plain text
         *
         * @return "SUB"
         */
        @Override
        public String toString(){return "SUB";}
    }

}
