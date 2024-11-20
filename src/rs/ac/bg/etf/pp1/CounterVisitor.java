package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.FormPars;
import rs.ac.bg.etf.pp1.ast.FormParsBegin;
import rs.ac.bg.etf.pp1.ast.FormParsExtension;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {
	protected int count;
	
	public int getCount() {
		return count;
	}
	
	public static class FormParamCounter extends CounterVisitor{
		
		public void visit(FormParsBegin formP) {
			count++;
		}
		
		public void visit(FormParsExtension formP) {
			count++;
		}
		
	}
	
public static class VarDeclCounter extends CounterVisitor{
		
		public void visit(VarDecl varDecl) {
			count++;
		}
		
	}
}
