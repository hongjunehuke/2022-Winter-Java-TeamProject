package readTXT;

import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReaderTXT {
	
	public static void main(String[] args) {
		try{
			Path path = Paths.get("txt 파일 절대 경로 입력하기");
			Stream<String> lines = Files.lines(path);
			String str;
			
			String content = lines.collect(Collectors.joining(System.lineSeparator()));
			String[] Arr = content.split("\t");
			
			for (int i = 6; i < Arr.length;) {
				i++;
				str = Arr[i++];
				str = str.replaceAll("\"", "");
				System.out.print("이름 : " + str);
	            System.out.println(" / 카테고리1 : " + Arr[i++] + " / 카테고리2 : " + Arr[i++]);
	            str = Arr[i++];
				str = str.replaceAll("\"", "");
	            System.out.println("재료 : " + str);
	            str = Arr[i++];
				str = str.replaceAll("\"", "");
	            System.out.print(str);
	            System.out.println();
	            i++;
	            System.out.println("==================================");
			}
			
            lines.close();

	        }catch(IOException e){
	            e.getStackTrace();
	        }
    }
}