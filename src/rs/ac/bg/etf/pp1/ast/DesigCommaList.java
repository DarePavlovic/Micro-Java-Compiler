// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class DesigCommaList extends DCommaList {

    private DCommaList DCommaList;
    private DesigOptComa DesigOptComa;

    public DesigCommaList (DCommaList DCommaList, DesigOptComa DesigOptComa) {
        this.DCommaList=DCommaList;
        if(DCommaList!=null) DCommaList.setParent(this);
        this.DesigOptComa=DesigOptComa;
        if(DesigOptComa!=null) DesigOptComa.setParent(this);
    }

    public DCommaList getDCommaList() {
        return DCommaList;
    }

    public void setDCommaList(DCommaList DCommaList) {
        this.DCommaList=DCommaList;
    }

    public DesigOptComa getDesigOptComa() {
        return DesigOptComa;
    }

    public void setDesigOptComa(DesigOptComa DesigOptComa) {
        this.DesigOptComa=DesigOptComa;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DCommaList!=null) DCommaList.accept(visitor);
        if(DesigOptComa!=null) DesigOptComa.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DCommaList!=null) DCommaList.traverseTopDown(visitor);
        if(DesigOptComa!=null) DesigOptComa.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DCommaList!=null) DCommaList.traverseBottomUp(visitor);
        if(DesigOptComa!=null) DesigOptComa.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesigCommaList(\n");

        if(DCommaList!=null)
            buffer.append(DCommaList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesigOptComa!=null)
            buffer.append(DesigOptComa.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesigCommaList]");
        return buffer.toString();
    }
}
