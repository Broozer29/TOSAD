package domain;

public abstract class OperatorDecorator implements Operator{
	
	protected Operator wrappee;
	
	public OperatorDecorator(Operator o) {
		wrappee = o;
	}
	
	public String getCode() {
		return "lol" ;
	}
	
	
	

}
