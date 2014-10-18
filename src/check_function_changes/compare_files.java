package check_function_changes;


import java.io.*;
import java.util.*;

public class compare_files {
  
  public class Func{
    public int start_line, end_line;
    String func_name;
  }
  
  public enum ty_language {
    DELPHI
  }
  
  public static final String[] start_func_statement = {
    "begin"
  };
  
  public static final String[] end_func_statement = {
	"end;"
  };
  
  public static final String[] all_functions_delphi = {
	  "function",
	  "procedure"
  };
 
  
  private List<String> source_file, dest_file;
  private ty_language CLanguage;
  private List<Func> all_functions;
    
  private List<String> read_file_into_list(String fname){
    List<String> f_list = new LinkedList<String>();
    try {
      FileReader f_to_read = new FileReader(fname);
      BufferedReader bf = new BufferedReader(f_to_read);

      String line;
      while ((line = bf.readLine()) != null)
        f_list.add(line);

      bf.close();
      
    } 
    catch (FileNotFoundException E){
      System.out.println("file not found!");
    }
    finally{
      return f_list;
    }

  }
  
  public compare_files(String source_file_name, String dest_file_name, ty_language Language){
    CLanguage = Language;
    all_functions = new ArrayList<Func>();
    source_file = read_file_into_list(source_file_name);
    dest_file = read_file_into_list(dest_file_name);
  }
  
  public Func find_function_end(int start_line, boolean is_source){
	  Stack<String> check_func_end = new Stack<String>();
	  List<String> list_to_check = is_source ? (source_file) : (dest_file); 
	  
	  int len = list_to_check.size();
	  String start_str = start_func_statement[CLanguage].toLowerCase();
	  String end_str = end_func_statement[CLanguage].toLowerCase(); 
	  
	  // Find the begin of the function
	  int func_begins;
	  boolean found = false;
	  for (func_begins = start_line; (func_begins < len) && (!found); func_begins++){
		 
		  for (int i = 0; i < 2; i++){
			  found = list_to_check.get(func_begins).startsWith(all_functions_delphi[i]);
			  if (found)
				  break;
		  }
	  }
	  
	  
	  // find where the function ends
	  boolean is_first = true;
	  for (int idx = start_line; idx < len; idx++){
		  String cuurent_str = list_to_check.get(idx).toLowerCase();
		  boolean do_contain_start = cuurent_str.contains(start_str);
		  boolean do_contain_end = cuurent_str.contains(end_str);
		  if (is_first && do_contain_start)
			  is_first = false;
		  
		  if (do_contain_start)
			  check_func_end.push(start_str);
		  else if (do_contain_end)
			  check_func_end.pop();
		  
		  if ((!is_first) && (check_func_end.empty()))
			  break;		  
	  }
	  
  }
}
