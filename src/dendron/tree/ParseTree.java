package dendron.tree;

import dendron.Errors;
import dendron.machine.Machine;
import dendron.tree.ActionNode;
import dendron.tree.ExpressionNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Operations that are done on a Dendron code parse tree.
 *
 * THIS CLASS IS UNIMPLEMENTED. All methods are stubbed out.
 *
 * @author Nicholas Shinn
 */
public class ParseTree {

    /** Program created after parsing through each action */
    private Program NEW_PROGRAM;
    /**
     * Parse the entire list of program tokens. The program is a
     * sequence of actions (statements), each of which modifies something
     * in the program's set of variables. The resulting parse tree is
     * stored internally.
     * @param program the token list (Strings)
     */
    public ParseTree( List< String > program ) {
        Program new_program = new Program();
        while(!program.isEmpty()){
            ActionNode next = parseAction(program);
            new_program.addAction(next);
        }
        NEW_PROGRAM = new_program;
    }

    /**
     * Parse the next action (statement) in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for the action
     */
    private ActionNode parseAction( List< String > program ) {
        String action = program.remove(0);
        if(action.equals(":=")){
            String variable = program.remove(0);
            ExpressionNode expr = parseExpr(program);
            return new Assignment(variable, expr);
        } else {
            ExpressionNode expr = parseExpr(program);
            return new Print(expr);
        }
    }

    /**
     * Parse the next expression in the list.
     * (This method is not required, just suggested.)
     * @param program the list of tokens
     * @return a parse tree for this expression
     */
    private ExpressionNode parseExpr( List< String > program ) {
        if(program.isEmpty()){
            Errors.report(Errors.Type.PREMATURE_END, null);
            return null;
        } else {
            String expr = program.remove(0);
            if (BinaryOperation.OPERATORS.contains(expr)) {
                return new BinaryOperation(expr, parseExpr(program), parseExpr(program));
            } else if (UnaryOperation.OPERATORS.contains(expr)) {
                return new UnaryOperation(expr, parseExpr(program));
            } else if (expr.matches("\\d+")) {
                return new Constant(Integer.parseInt(expr));
            } else {
                return new Variable(expr);
            }
        }
    }

    /**
     * Print the program the tree represents in a more typical
     * infix style, and with one statement per line.
     * @see dendron.tree.ActionNode#infixDisplay()
     */
    public void displayProgram() {
        NEW_PROGRAM.infixDisplay();
    }

    /**
     * Run the program represented by the tree directly
     * @see dendron.tree.ActionNode#execute(Map)
     */
    public void interpret() {
        HashMap<String,Integer> map = new HashMap<>();
        NEW_PROGRAM.execute(map);
    }

    /**
     * Build the list of machine instructions for
     * the program represented by the tree.
     * @return the Machine.Instruction list
     * @see Machine.Instruction#execute()
     */
    public List< Machine.Instruction > compile() {
        return NEW_PROGRAM.emit();
    }

}
