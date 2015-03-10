package com.jt.pojo;

import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

public class TrueFalseInt extends Bpojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = TrueFalse.class.hashCode();
	@PojoMap( key= 1 )
	public	int	id =1	;
	@PojoMap( key= 0 )
	public	String	value	;
	static public TrueFalseInt[] ItemList={new TrueFalseInt(0,"不满意"),new TrueFalseInt(1,"满意")};
	
	public TrueFalseInt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrueFalseInt(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	@Override
	public TrueFalseInt[] getPojoItemList() throws Exception {
		// TODO Auto-generated method stub
		
		return ItemList;
		
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		// TODO Auto-generated method stub
		return super.clone();
	}


}
