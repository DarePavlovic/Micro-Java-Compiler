// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class DesignatorVar extends Designator {

    private String desigVarName;

    public DesignatorVar (String desigVarName) {
        this.desigVarName=desigVarName;
    }

    public String getDesigVarName() {
        return desigVarName;
    }

    public void setDesigVarName(String desigVarName) {
        this.desigVarName=desigVarName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorVar(\n");

        buffer.append(" "+tab+desigVarName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorVar]");
        return buffer.toString();
    }
}
