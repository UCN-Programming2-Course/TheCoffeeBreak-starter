package data.daos;

import data.DataContext;

public abstract class BaseDao {

	protected final DataContext dataContext;
	
	public BaseDao(DataContext dataContext) {
		
		this.dataContext = dataContext;
	}
	
	
}
