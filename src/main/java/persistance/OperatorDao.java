package persistance;

import domain.Value;

public interface OperatorDao {
	
	public Value findByID(String ID);
	
	public boolean save(Value o);
	
	public boolean update(Value o);
	
	public boolean delete(Value o);

}
