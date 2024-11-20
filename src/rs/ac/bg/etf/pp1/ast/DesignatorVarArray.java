// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class DesignatorVarArray extends Designator {

    private String desigVarArrayName;
    private Expr Expr;

    public DesignatorVarArray (String desigVarArrayName, Expr Expr) {
        this.desigVarArrayName=desigVarArrayName;
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public String getDesigVarArrayName() {
        return desigVarArrayName;
    }

    public void setDesigVarArrayName(String desigVarArrayName) {
        this.desigVarArrayName=desigVarArrayName;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorVarArray(\n");

        buffer.append(" "+tab+desigVarArrayName);
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorVarArray]");
        return buffer.toString();
    }
}
