package DataProvider_Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.DataProvider;

import Generic_Component.ExcelRW;

public class DataproviderClass {
	
	@DataProvider(name="dp_Invalidlogin")
	public static Iterator<Object[]>  getInvaliddata() throws Exception
	{
		return commondp_logic("Login", "InvalidLogin");
	}
	
	@DataProvider(name="dp_validlogin")
	public static Iterator<Object[]>  getValiddata() throws Exception
	{
		return commondp_logic("Login", "ValidLogin");
	}
	
	@DataProvider(name="dp_InvalidSearch")
	public static Iterator<Object[]>  getInvalidSearchData() throws Exception
	{
		return commondp_logic("Search", "InvalidSearch");
	}
	
	@DataProvider(name="dp_validSearch")
	public static Iterator<Object[]>  getValidSearchData() throws Exception
	{
		return commondp_logic("Search", "ValidSearch");
	}
	
	
	public static Iterator<Object[]> commondp_logic(String sheetname, String sName) throws Exception
	{
		ExcelRW xl;
		ExcelRW row = xl = new ExcelRW("D:\\HybridFramework_May\\Testdata\\testdata.xlsx");
		int rowCount = xl.getRow(sheetname);
		int columnCont = xl.getColum(sheetname);
		
		//declare arraylist
		ArrayList<Object[]> arr_list = new ArrayList<Object[]>();
		
		for(int i=1;i<=rowCount;i++)
		{
			String execute_Flag = xl.readCell(sheetname, i, 2);
			String script_Name = xl.readCell(sheetname, i, 3);
			
			if((execute_Flag.equalsIgnoreCase("Y")) && (script_Name.equalsIgnoreCase(sName)))
			{
				//declare map
				Map<String, String> dMap = new HashMap<String, String>();
				
				Object[] x = new Object[1];
				
				for(int j=0; j<columnCont; j++)
				{
					String sKey = xl.readCell(sheetname, 0, j);
					String value = xl.readCell(sheetname, i, j);
					
					dMap.put(sKey, value);
										
				}				
				x[0] = dMap;
				
				arr_list.add(x);
			} // end of if condition
			
		} // end of for loop
		
		return arr_list.iterator();
		
	}
	
	

}
