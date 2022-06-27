package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/**
 *Node representing a constant
 *
 * @author Nicholas Shinn
 */
public class Constant implements ExpressionNode {

    /** Constant value */
    private int VALUE;

    /**
     * Stores integer as constant
     *
     * @param value constant value
     */
    public Constant(int value){
        this.VALUE = value;
    }

    /**
     * Gives instruction to push the value onto stack
     *
     * @return push instruction
     */
    public java.util.List<Machine.Instruction> emit(){
        Machine.Instruction instruct = new Machine.PushConst(VALUE);
        java.util.List<Machine.Instruction> instruct_list = new ArrayList<>();
        instruct_list.add(instruct);
        return instruct_list;
    }

    /**
     * Retrieves value of the constant
     *
     * @param symTab symbol table, if needed, to fetch variable values
     *
     * @return value of constant
     */
    public int evaluate(java.util.Map<String,Integer> symTab){
        return VALUE;
    }

    /**
     * Prints value of constant
     */
    public void infixDisplay(){
        System.out.print(VALUE);
    }
}
