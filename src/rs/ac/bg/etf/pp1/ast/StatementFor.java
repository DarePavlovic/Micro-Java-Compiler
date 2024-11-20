// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class StatementFor extends Statement {

    private ForOppening ForOppening;
    private DesigStateBrack DesigStateBrack;
    private CondFactBrack CondFactBrack;
    private DesigStateBrack DesigStateBrack1;
    private Statement Statement;

    public StatementFor (ForOppening ForOppening, DesigStateBrack DesigStateBrack, CondFactBrack CondFactBrack, DesigStateBrack DesigStateBrack1, Statement Statement) {
        this.ForOppening=ForOppening;
        if(ForOppening!=null) ForOppening.setParent(this);
        this.DesigStateBrack=DesigStateBrack;
        if(DesigStateBrack!=null) DesigStateBrack.setParent(this);
        this.CondFactBrack=CondFactBrack;
        if(CondFactBrack!=null) CondFactBrack.setParent(this);
        this.DesigStateBrack1=DesigStateBrack1;
        if(DesigStateBrack1!=null) DesigStateBrack1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForOppening getForOppening() {
        return ForOppening;
    }

    public void setForOppening(ForOppening ForOppening) {
        this.ForOppening=ForOppening;
    }

    public DesigStateBrack getDesigStateBrack() {
        return DesigStateBrack;
    }

    public void setDesigStateBrack(DesigStateBrack DesigStateBrack) {
        this.DesigStateBrack=DesigStateBrack;
    }

    public CondFactBrack getCondFactBrack() {
        return CondFactBrack;
    }

    public void setCondFactBrack(CondFactBrack CondFactBrack) {
        this.CondFactBrack=CondFactBrack;
    }

    public DesigStateBrack getDesigStateBrack1() {
        return DesigStateBrack1;
    }

    public void setDesigStateBrack1(DesigStateBrack DesigStateBrack1) {
        this.DesigStateBrack1=DesigStateBrack1;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForOppening!=null) ForOppening.accept(visitor);
        if(DesigStateBrack!=null) DesigStateBrack.accept(visitor);
        if(CondFactBrack!=null) CondFactBrack.accept(visitor);
        if(DesigStateBrack1!=null) DesigStateBrack1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForOppening!=null) ForOppening.traverseTopDown(visitor);
        if(DesigStateBrack!=null) DesigStateBrack.traverseTopDown(visitor);
        if(CondFactBrack!=null) CondFactBrack.traverseTopDown(visitor);
        if(DesigStateBrack1!=null) DesigStateBrack1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForOppening!=null) ForOppening.traverseBottomUp(visitor);
        if(DesigStateBrack!=null) DesigStateBrack.traverseBottomUp(visitor);
        if(CondFactBrack!=null) CondFactBrack.traverseBottomUp(visitor);
        if(DesigStateBrack1!=null) DesigStateBrack1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementFor(\n");

        if(ForOppening!=null)
            buffer.append(ForOppening.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesigStateBrack!=null)
            buffer.append(DesigStateBrack.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CondFactBrack!=null)
            buffer.append(CondFactBrack.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesigStateBrack1!=null)
            buffer.append(DesigStateBrack1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementFor]");
        return buffer.toString();
    }
}
