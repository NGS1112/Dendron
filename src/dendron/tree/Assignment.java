package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/**
 * Action Node representing a variable with an assigned value
 *
 * @author Nicholas Shinn
 */
public class Assignment implements ActionNode{

    /** Variable name */
    private String IDENTIFIER;

    /** Node to be used as value for variable */
    private ExpressionNode NODE;

    /**
     * Assigns a value to a variable
     *
     * @param ident variable name
     *
     * @param rhs value
     */
    public Assignment(String ident, ExpressionNode rhs){
        IDENTIFIER = ident;
        NODE = rhs;
    }

    /**
     * Takes the emit() code from a node and follows it with a Machine.Store instruction
     *
     * @return NODE.emit() + Machine.Store instruction
     */
    public java.util.List<Machine.Instruction> emit(){
        java.util.List<Machine.Instruction> instruct_list = new ArrayList<>();
        instruct_list.addAll(NODE.emit());
        instruct_list.add(new Machine.Store(IDENTIFIER));
        return instruct_list;
    }

    /**
     * Puts the variable and its value in the symbol table
     *
     * @param symTab the table where variable values are stored
     */
    public void execute(java.util.Map<String,Integer> symTab){
        symTab.put(IDENTIFIER, NODE.evaluate(symTab));
    }

    /**
     * Displays the assignment as a variable followed by ":=" and Node infix
     */
    public void infixDisplay(){
        System.out.print(IDENTIFIER + ":= ");
        NODE.infixDisplay();
    }
}
