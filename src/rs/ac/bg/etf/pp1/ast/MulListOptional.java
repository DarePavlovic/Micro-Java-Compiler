// generated with ast extension for cup
// version 0.8
// 16/0/2024 17:46:55


package rs.ac.bg.etf.pp1.ast;

public class MulListOptional extends MulList {

    private MulList MulList;
    private MulFactor MulFactor;

    public MulListOptional (MulList MulList, MulFactor MulFactor) {
        this.MulList=MulList;
        if(MulList!=null) MulList.setParent(this);
        this.MulFactor=MulFactor;
        if(MulFactor!=null) MulFactor.setParent(this);
    }

    public MulList getMulList() {
        return MulList;
    }

    public void setMulList(MulList MulList) {
        this.MulList=MulList;
    }

    public MulFactor getMulFactor() {
        return MulFactor;
    }

    public void setMulFactor(MulFactor MulFactor) {
        this.MulFactor=MulFactor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MulList!=null) MulList.accept(visitor);
        if(MulFactor!=null) MulFactor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MulList!=null) MulList.traverseTopDown(visitor);
        if(MulFactor!=null) MulFactor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MulList!=null) MulList.traverseBottomUp(visitor);
        if(MulFactor!=null) MulFactor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulListOptional(\n");

        if(MulList!=null)
            buffer.append(MulList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulFactor!=null)
            buffer.append(MulFactor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulListOptional]");
        return buffer.toString();
    }
}
