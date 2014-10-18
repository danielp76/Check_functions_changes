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
      return f_list;
    } 
    finally {
     
    }
  }
  
  public compare_files(String source_file_name, String dest_file_name, ty_language Language){
    CLanguage = Language;
    all_functions = new ArrayList<Func>();
    source_file = read_file_into_list(source_file_name);
    dest_file = read_file_into_list(dest_file_name);
  }
  
  public Func find_function_end(int start_line){
	  Stack<String> check_func_end = new Stack<String>();
  }
}
