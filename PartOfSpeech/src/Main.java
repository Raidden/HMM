import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	
public static void main(String[] args) throws Exception {
		
		File ff = new File("ff");
		PrintWriter pww = new PrintWriter(ff);
		try {
		
		File Fileright = new File("results.txt");

        PrintWriter pw = new PrintWriter(Fileright);
		
		Tagger tagger = new  Tagger();
		
		//load tags from Folder
		//File trainingFolder = new File("brown_training");
		//File testFolder = new File("testSet");
		
		
		//trainingFolder Hier
//		File trainingFolder = new File(args[0]);
		
		File trainingFolder = new File("brown_training");
		
		//testFolder hier
//		File testFolder = new File(args[1]);
		
		File testFolder = new File("testSet");
		
		//load testSet
		ArrayList<String> testSet = tagger.loadTestData(testFolder);
		//load Tags
		tagger.loadTags(tagger, trainingFolder);
		
		//load TrainingSet
		tagger.loadCorpus(tagger , trainingFolder);
		
		System.out.println("Tags : \n"+Arrays.toString(tagger.getBROWNECORPUSTAGS()));
		
		//initialize HMM
		tagger._init_(tagger);
		
		for (String sentence : testSet) {
			
		
		String[] tmp = sentence.trim().split(" ");
		int i= 0;
		String[] tags = new String[tmp.length];
		String[] tockens = new String[tmp.length];
		
		for (String t : tmp) {
			if (t.indexOf("/")!=-1) {
				tags[i] = t.substring(t.lastIndexOf("/")+1, t.length());
				tockens[i] = t.substring( 0 , t.lastIndexOf("/"));	
			}else {
				//throw new Exception("Sentence not Valid");
				
			}
			i++;
		}
		
		
		
		ArrayList< String > b = tagger.veterbiTags(tagger, tockens);
		String result = "";
		
		for (String str : b) {
			result+= str + " ";
		}
		
		System.out.println();
//		System.out.println(sentence);
		System.out.println();
		
		double accuracy = 0.0;
		for (int j = 0; j < tockens.length; j++) {
			if (tags[j].equals(b.get(j))) {
				accuracy++; 
			}
			System.out.println("Guessed : " + b.get(j) + "     Actual : " + tags[j]  + "   |    original :" + tockens[j]);
		}
		System.out.println();
		pww.append( accuracy/(tockens.length)+"\n");
		System.out.println("Accuracy = "  +  accuracy/(tockens.length));
		
		tagger.writeResult(tagger, b, tmp, pw);
		}
		
		pww.append(tagger.getTagTockenMap().toString());
		
		pww.close();
		pw.close();
		
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
