package com.jt.pojo;

import project.annotation.ui.PojoUI;
import project.pojo.Bpojo;

public class ResolvePaperCountUI extends Bpojo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = ResolvePaperCountUI.class.hashCode();
	
	@PojoUI(PojoUIName="黑白张数", PojoUIOrder = 00, PojoEditor="EditText", CanBeNull=false)
	public	Integer	blacknum	;
	@PojoUI(PojoUIName="", PojoUIOrder = 05, PojoEditor="TextView")
	public  java.lang.Integer max_blacknum; /** max_BlackNum 总黑白张数 **/
	
	@PojoUI(PojoUIName="专业点彩", PojoUIOrder = 100, PojoEditor="EditText", CanBeNull=false)
	public	Integer	numzydc	;
	@PojoUI(PojoUIName="", PojoUIOrder = 105, PojoEditor="TextView")
	public  java.lang.Integer max_numzydc; /** max_NumZydc 专业点彩 **/
	
	@PojoUI(PojoUIName="普通点彩", PojoUIOrder = 200, PojoEditor="EditText", CanBeNull=false)
	public	Integer	numptdc	;
	@PojoUI(PojoUIName="", PojoUIOrder = 205, PojoEditor="TextView")
	public  java.lang.Integer max_numptdc; /** max_NumPtdc 普通点彩 **/
	
	@PojoUI(PojoUIName="专彩张数", PojoUIOrder = 300, PojoEditor="EditText", CanBeNull=false)
	public	Integer	colornum1	;
	@PojoUI(PojoUIName="", PojoUIOrder = 305, PojoEditor="TextView")
	public  java.lang.Integer max_colornum1 ; /** max_ColorNum1  专彩张数 **/
	
	@PojoUI(PojoUIName="普彩张数", PojoUIOrder = 400, PojoEditor="EditText", CanBeNull=false)
	public	Integer	colornum2	;
	@PojoUI(PojoUIName="", PojoUIOrder = 405, PojoEditor="TextView")
	public  java.lang.Integer max_colornum2 ; /** max_ColorNum1  普彩张数 **/

	
	@PojoUI(PojoUIName="卡纸数", PojoUIOrder = 500, PojoEditor="EditText", CanBeNull=false)
	public	Integer	chokedpapernum	;
	@PojoUI(PojoUIName="", PojoUIOrder = 505, PojoEditor="TextView")
	public  java.lang.Integer max_chokedpapernum  ; /** max_chokedPaperNum 卡纸数 **/
	
	@PojoUI(PojoUIName="制版数", PojoUIOrder = 600, PojoEditor="EditText", CanBeNull=false)
	public	Integer	totalplatenum	;
	@PojoUI(PojoUIName="", PojoUIOrder = 605, PojoEditor="TextView")
	public  java.lang.Integer max_totalplatenum  ; /** max_TotalPlateNum  总制版数 **/
	

	
	@PojoUI(PojoUIName="总张数", PojoUIOrder = 700, PojoEditor="TextView")
	public	Integer	totalcopynum;
	@PojoUI(PojoUIName="", PojoUIOrder = 705, PojoEditor="TextView")
	public  java.lang.Integer max_totalcopynum ; /** max_TotalCopyNum  总复印张数 **/
	

	
	

}
