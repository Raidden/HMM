import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

public class FileLoader extends Writer {
	
	
	public void loadTags(Tagger tagger, File folder ) throws FileNotFoundException {
		
		/*String [] rem = {".", "1", "16", "16''","be", "bed", "16-inch", "2", "2%", "2''","under", "2-foot", 
				"2-inch", "2-story", "20th", "29", "3", "3%", "32''", "4", "4''", "50th", "64''", 
				"7", "7074", "8", "8''", "8-inch", "8-inch-thick", "9", ":", "Output","active" , "cell" , 
				"chamber" , "cs", "cwt", "day", "destination", "do", "dod", "doz", "sec", "to",""};*/
		
		

		// TODO Auto-generated method stub
		ArrayList<String> tockenTags = new ArrayList<>();
		TreeMap<String, Integer> tags = new TreeMap<>();
		ArrayList<String> TAGS = new ArrayList<>();
		try {
			
			
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				if (file.isFile()) {
//			    	System.out.println(file.getName());
			    	BufferedReader br = new BufferedReader(new FileReader(file));
				    
					   
				    String line;
				    while( (line = br.readLine()) != null){
				    	
				    	if (line.length()!=0) {
							
						
				    	line=line.trim();
				        String[] tokens =line.split(" "); 
				        
				    	for (String t : tokens) {
				    		t=t.trim();
			    			

				    		if (t.length()!=0 && !t.equals("")&&t.contains("/")) {
				    			tockenTags.add(t.substring(t.lastIndexOf("/")+1, t.length()));
//				    			
				    		}
						}
				    }
				    }
				   
				    
				    for (String string : tockenTags) {
						
			    		tags.put(string, 0);
			    		
					}
			    
			    }
			}
			
			
			
			for (String string : tags.keySet()) {
				//if (!Arrays.asList(rem).contains(string)) {
					TAGS.add(string);
				//}
				
				
			}
//			System.out.println(TAGS.size());
			
			String [] s = new String[TAGS.size()];
			
			
			
		
		    tagger.setBROWNECORPUSTAGS(TAGS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	 public static  void loadCorpus(Tagger tagger, File folder)
			    throws IOException {
		 		 
		 
		 try {
			 
		 		ArrayList<String[]> tockenTags = new ArrayList<String[]>();
			    ArrayList<String> sentences = new ArrayList<>();
			    ArrayList<String> testSentences = new ArrayList<>();
			    
			    File f = new File("test");
				 PrintWriter pw= new PrintWriter(f);
			    
				
				File[] listOfFiles = folder.listFiles();
				
				for (File filee : listOfFiles) {
				    if (filee.isFile()) {
		 		BufferedReader br = new BufferedReader(new FileReader(filee));
			    
			   
			    String line;
			    
			    while( (line = br.readLine()) != null){
			    	if (line.length()!=0) {
						line=line.trim();
					
			        String[] tokens =line.split(" "); 
			       
			    	for (String t : tokens) {
			    		
			    		t=t.trim();
			    		if (t.length()!=0 && !t.equals("")&&t.contains("/")&&!t.contains("./.")&&!t.equals("./.")) {
			    			t=t.trim();
			    			String[] a = {t.substring(0,t.lastIndexOf("/")),t.substring(t.lastIndexOf("/")+1,t.length())};
							tockenTags.add(a);
							pw.append(t+"\n");
			    		}
			    		
					}
			    	
			    	String[] tmp = line.split("\\./\\.");
			    	for (String t : tmp) {
			    		t=t.trim();
			    		if (t.length()!=0&&!t.equals("")) {
			    			sentences.add(t);
//			    			System.out.println(t);
//			    			System.out.println();
					 
			    	 }
			    	}
			       }
			      }
				 }
				}
			    
			    tagger.setTestSentences(testSentences);
			    tagger.setTockenTags(tockenTags);
			    pw.append("------"+"\n");
				
			 
				pw.close();

			    
		 		} catch (Exception e) {
					// TODO: handle exception
				}
			  }
	 
	 	public ArrayList<String> loadTestData(File testFolder) {
	 		// TODO Auto-generated method stub
	 		System.out.println("----------------");
	 		ArrayList<String[]> testSentences = new ArrayList<>();
	 		ArrayList<String> testSentences_ = new ArrayList<>();
	 		try {
	 			

				File[] listOfFiles = testFolder.listFiles();

				for (File file : listOfFiles) {
				    if (file.isFile()) {
				    	
				    	BufferedReader br = new BufferedReader(new FileReader(file));
					    String line;
					    while( (line = br.readLine()) != null){
					    	line=line.trim();
					    	if (line.length()>1 && !line.equals("")) {
					    		String [] tmp= line.split("\\./\\.");
					    		testSentences.add(tmp);
							}
					    }
				    }
				}
	 			
				for (String[] s : testSentences ) {
					for (String st : s) {
						testSentences_.add(st);
//						System.out.println(st);
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
				
				return testSentences_;
	 		
	 	}
	 

			  private static String removePunctuation(String token) {
			    token = token.replaceAll("   ", " ");
			    token = token.replaceAll("/n", "");
			    return token;
			  }
}
