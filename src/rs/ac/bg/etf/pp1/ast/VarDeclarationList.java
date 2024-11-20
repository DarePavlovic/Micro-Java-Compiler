// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class VarDeclarationList extends VarDList {

    private VarDList VarDList;
    private VarDeclL VarDeclL;

    public VarDeclarationList (VarDList VarDList, VarDeclL VarDeclL) {
        this.VarDList=VarDList;
        if(VarDList!=null) VarDList.setParent(this);
        this.VarDeclL=VarDeclL;
        if(VarDeclL!=null) VarDeclL.setParent(this);
    }

    public VarDList getVarDList() {
        return VarDList;
    }

    public void setVarDList(VarDList VarDList) {
        this.VarDList=VarDList;
    }

    public VarDeclL getVarDeclL() {
        return VarDeclL;
    }

    public void setVarDeclL(VarDeclL VarDeclL) {
        this.VarDeclL=VarDeclL;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDList!=null) VarDList.accept(visitor);
        if(VarDeclL!=null) VarDeclL.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDList!=null) VarDList.traverseTopDown(visitor);
        if(VarDeclL!=null) VarDeclL.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDList!=null) VarDList.traverseBottomUp(visitor);
        if(VarDeclL!=null) VarDeclL.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclarationList(\n");

        if(VarDList!=null)
            buffer.append(VarDList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclL!=null)
            buffer.append(VarDeclL.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclarationList]");
        return buffer.toString();
    }
}
