package com.jt.pojo;




import project.annotation.map.PojoMap;
import project.pojo.Bpojo;

public class TrueFalse extends Bpojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = TrueFalse.class.hashCode();
	@PojoMap( key= 1 )
	public	Boolean	id	;
	@PojoMap( key= 0 )
	public	String	value	;
	static private TrueFalse[] ItemList={new TrueFalse(false,"否"),new TrueFalse(true,"是")};
	
	public TrueFalse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrueFalse(Boolean id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	@Override
	public TrueFalse[] getPojoItemList() throws Exception {
		// TODO Auto-generated method stub
		
		return ItemList;
		
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		// TODO Auto-generated method stub
		return super.clone();
	}


}
