package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/**
 * Creates a Print node
 *
 * @author Nicholas Shinn
 */
public class Print implements ActionNode {

    /** Print node */
    private ExpressionNode PRINTEE;

    /** Value of Print node */
    private int VALUE;

    /**
     * Creates a Print node
     *
     * @param printee Node to be printed
     */
    public Print(ExpressionNode printee){
        PRINTEE = printee;
    }

    /**
     * Evaluates the node before displaying it
     *
     * @param symTab the table where variable values are stored
     */
    public void execute(java.util.Map<String,Integer> symTab){
        VALUE = PRINTEE.evaluate(symTab);
        System.out.print("=== " + VALUE);
    }

    /**
     * Issues a push instruction for the value before issuing a print instruction
     *
     * @return Machine.Push + Machine.Print
     */
    public java.util.List<Machine.Instruction> emit(){
        Machine.Instruction push = new Machine.PushConst(VALUE);
        Machine.Instruction print = new Machine.Print();
        java.util.List<Machine.Instruction> instruct_list = new ArrayList<>();
        instruct_list.add(push);
        instruct_list.add(print);
        return instruct_list;
    }

    /**
     * Prints the word "Print" followed by the infix of the node
     */
    public void infixDisplay(){
        System.out.print("Print ");
        PRINTEE.infixDisplay();
    }
}
