package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarDeclCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;

	public int getMainPc() {
		return mainPc;
	}
	
	
	public void visit(StatementPrint print) {
		if(print.getNumConstBrack() instanceof NumConstOptional) {
			if(print.getExpr().struct==Tab.intType) {
				Code.loadConst(((NumConstOptional)(print.getNumConstBrack())).getNumConst());
				Code.put(Code.print);
			}
			else if(print.getExpr().struct==Tab.charType) {
				Code.loadConst(((NumConstOptional)(print.getNumConstBrack())).getNumConst());
				Code.put(Code.bprint);
			}
			else {
				Code.loadConst(((NumConstOptional)(print.getNumConstBrack())).getNumConst());
				Code.put(Code.print);
			}
		}
		else {
			if(print.getExpr().struct==Tab.intType) {
				Code.loadConst(5);
				Code.put(Code.print);
			}
			else if(print.getExpr().struct==Tab.charType) {
				Code.loadConst(1);
				Code.put(Code.bprint);
			}
			else {
				Code.loadConst(5);
				Code.put(Code.print);
			}
		}
	}
	public void visit(FactorNumber num) {
		Obj con = Tab.insert(Obj.Con, "NumFactor", num.struct);
		con.setLevel(0);
		con.setAdr(num.getFactNum());
		
		Code.load(con);
	}
	
	public void visit(FactorChar chr) {
		Obj con = Tab.insert(Obj.Con, "CharFactor", chr.struct);
		con.setLevel(0);
		con.setAdr(chr.getFactChar());
		
		Code.load(con);
	}
	
	public void visit(FactorBool bl) {
		Obj con = Tab.insert(Obj.Con, "BoolFactor", bl.struct);
		con.setLevel(0);
		if(bl.getFactBool()==true) {
			con.setAdr(1);
		}
		else {
			con.setAdr(0);
		}
		
		Code.load(con);
	}
	
	public void visit(FactorDesignator fd) {
		Designator des = fd.getDesignator();
		if(des instanceof DesignatorVar || des instanceof DesignatorNamespace){
				Code.load(des.obj);
		}
		else {
			Code.load(des.obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			if(des.obj.getType().getElemType()==Tab.charType) {
				Code.put(Code.baload);
			}
			else {
				Code.put(Code.aload);
			}
		}
	}
	
	
	public void visit(MethodDeclName methodDeclName) {
		if("main".equalsIgnoreCase(methodDeclName.getMethName())) {
			mainPc=Code.pc;
		}
		methodDeclName.obj.setAdr(Code.pc);
		//Sakupi argumente i lokalne promenljive
		SyntaxNode methodNode = methodDeclName.getParent();
		
		VarDeclCounter varCnt = new VarDeclCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter formCnt = new FormParamCounter();
		methodNode.traverseTopDown(formCnt);
		
		//Generisi entry za funkciju
		Code.put(Code.enter);
		Code.put(formCnt.getCount());
		Code.put(varCnt.getCount()+formCnt.getCount());
	}
	
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(DesignatorStatementAssignOp desAsignOp) {
		
		Designator designator = desAsignOp.getDesignator();
		if(designator instanceof DesignatorVar || designator instanceof DesignatorNamespace) {
			Code.store(designator.obj);
		}
		else{
			
			Code.load(designator.obj);
			Code.put(Code.dup_x2);
			Code.put(Code.pop);
			if(designator.obj.getType().getElemType()==Tab.charType) {
				Code.put(Code.bastore);
			}
			else {
				Code.put(Code.astore);
			}
			
		}
		
	}
	
	
	public void visit(Term t) {
		SyntaxNode parentN = t.getParent();
		if(parentN.getClass()==ExprMinusTermAddOp.class) {
			Code.put(Code.neg);
		}
	}

	
	public void visit(DesignatorStatementPlusPlus plusplus){
		Designator des = plusplus.getDesignator();
		if(des instanceof DesignatorVar || des instanceof DesignatorNamespace) {
			Code.load(des.obj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(des.obj);
		}
		else{
			Code.load(des.obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.dup2);
			Code.put(Code.aload);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.put(Code.astore);
		}
	}
	
	public void visit(DesignatorStatementMinusMinus minusminus) {
		Designator des = minusminus.getDesignator();
		if(des instanceof DesignatorVar || des instanceof DesignatorNamespace) {
			Code.load(des.obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(des.obj);
		}
		else{
			Code.load(des.obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			Code.put(Code.dup2);
			Code.put(Code.aload);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.put(Code.astore);
		}
	}
	
	
	public void visit(StatementRead readS) {
		Designator d = readS.getDesignator();
		
		if(d instanceof DesignatorVar || d instanceof DesignatorNamespace) {
			Code.load(d.obj);
			if(d.obj.getType()==Tab.charType) {
				Code.put(Code.bread);
				
			}
			else {
				Code.put(Code.read);
			}
			Code.store(d.obj);
		}
		else {
			Code.load(d.obj);
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
			
			if(d.obj.getType().getElemType()==Tab.charType) {
				Code.put(Code.bread);
				Code.put(Code.bastore);
			}
			else {
				Code.put(Code.read);
				Code.put(Code.astore);
			}
			
		}
	}

	
	public void visit(AddOpListPlus li) {
		Code.put(Code.add);
	}
	
	public void visit(AddOpListMinus li) {
		Code.put(Code.sub);
	}
	
	public void visit(MulFactor m) {
		if(m.getMulop() instanceof MulOperation) {
			Code.put(Code.mul);
		}
		if(m.getMulop() instanceof DivOperation) {
			Code.put(Code.div);
		}
		if(m.getMulop() instanceof ModOperation) {
			Code.put(Code.rem);
		}
		
	}
	
	public void visit(StatementReturn re) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(FactorTypeExpr fate) {
		Code.put(Code.newarray);
		if(fate.getType().struct==Tab.charType) {
			Code.put(0);
		}
		else {
			Code.put(1);
		}
	}
}
