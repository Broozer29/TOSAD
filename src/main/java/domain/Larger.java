package domain;

public class Larger extends OperatorDecorator{
	
	public Larger(Operator o) {
		super(o);
		// TODO Auto-generated constructor stub
	}

	private String symbol = ">";
	
	@Override
	public String getCode(){
		return wrappee.getCode()+symbol;
	}

}
