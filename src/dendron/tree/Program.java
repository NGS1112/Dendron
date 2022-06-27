package dendron.tree;

import dendron.machine.Machine;

import java.util.ArrayList;

/**
 * ActionNode representing other ActionNodes
 *
 * @author Nicholas Shinn
 */
public class Program implements ActionNode {

    /** List of ActionNodes */
    private ArrayList<ActionNode> CHILDREN;

    /**
     * Creates an empty list to hold ActionNodes
     */
    public Program(){
        CHILDREN = new ArrayList<>();
    }

    /**
     * Adds an ActionNode to the list
     *
     * @param newNode ActionNode to be added
     */
    public void addAction(ActionNode newNode){
        CHILDREN.add(newNode);
    }

    /**
     * Calls emit() on all ActionNodes and gathers them into a list
     *
     * @return list of all instructions provided by emit() called on each ActionNode
     */
    public java.util.List<Machine.Instruction> emit(){
        java.util.List<Machine.Instruction> instruct_list = new ArrayList<>();
        for (int i = 0; i <= CHILDREN.size()-1; i++) {
            java.util.List<Machine.Instruction> instruct = CHILDREN.get(i).emit();
            instruct_list.addAll(instruct);
        }
        return instruct_list;
    }

    /**
     * Executes all ActionNodes
     *
     * @param symTab the table where variable values are stored
     */
    public void execute(java.util.Map<String,Integer> symTab){
        for (int i = 0; i <= CHILDREN.size()-1; i++) {
            CHILDREN.get(i).execute(symTab);
        }
    }

    /**
     * Prints the infix of all ActionNodes
     */
    public void infixDisplay(){
        for (int i = 0; i <= CHILDREN.size()-1; i++) {
            CHILDREN.get(i).infixDisplay();
        }
    }
}
