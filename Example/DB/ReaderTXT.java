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
			Path path = Paths.get("txt파일의 절대경로 입력하기");		// 폴더 구분은 '\\'로!!
			Stream<String> lines = Files.lines(path);
			String str;
			
			String content = lines.collect(Collectors.joining(System.lineSeparator()));
			String[] Arr = content.split("\t");
			
			for (int i = 7; i < Arr.length;i++) {
				i++;
				str = Arr[i++];
				System.out.print("이름 : " + str);
	            System.out.println(" / 카테고리1 : " + Arr[i++] + " / 카테고리2 : " + Arr[i++]);
	            str = Arr[i++];
	            System.out.println("재료 : " + str);
	            str = Arr[i++];
	            System.out.print(str);
	            System.out.println();
	            System.out.println("==================================");
			}
			
            lines.close();

	        }catch(IOException e){
	            e.getStackTrace();
	        }
    }
}