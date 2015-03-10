package com.jt.pojo;

import java.util.List;


/**
 * ���Դ�ṩ�ӿ�
 * @author chengmingwei
 *
 */
public interface DataProvider {
	
	public void foward(String caption) throws Exception;
	/**
	 * @return
	 */
	public List<?> getDataList();
}
