// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementWithMul extends DesignatorStatement {

    private DCommaList DCommaList;
    private DesniDesignator DesniDesignator;
    private Designator Designator;

    public DesignatorStatementWithMul (DCommaList DCommaList, DesniDesignator DesniDesignator, Designator Designator) {
        this.DCommaList=DCommaList;
        if(DCommaList!=null) DCommaList.setParent(this);
        this.DesniDesignator=DesniDesignator;
        if(DesniDesignator!=null) DesniDesignator.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
    }

    public DCommaList getDCommaList() {
        return DCommaList;
    }

    public void setDCommaList(DCommaList DCommaList) {
        this.DCommaList=DCommaList;
    }

    public DesniDesignator getDesniDesignator() {
        return DesniDesignator;
    }

    public void setDesniDesignator(DesniDesignator DesniDesignator) {
        this.DesniDesignator=DesniDesignator;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DCommaList!=null) DCommaList.accept(visitor);
        if(DesniDesignator!=null) DesniDesignator.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DCommaList!=null) DCommaList.traverseTopDown(visitor);
        if(DesniDesignator!=null) DesniDesignator.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DCommaList!=null) DCommaList.traverseBottomUp(visitor);
        if(DesniDesignator!=null) DesniDesignator.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementWithMul(\n");

        if(DCommaList!=null)
            buffer.append(DCommaList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesniDesignator!=null)
            buffer.append(DesniDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementWithMul]");
        return buffer.toString();
    }
}
