package com.deep.rooted.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.deep.rooted.beans.SupplyAndDemand;
import com.deep.rooted.beans.Trade;
/**
 * This class performs read and write operations on file
 * @author Dhamodiran D
 *
 */
public class FileOperations {

	Set<SupplyAndDemand> allItems = new LinkedHashSet<>();
	
	/**
	 * This method reads and parse input file and returns set of SupplyAndDemand 
	 * @return Set<SupplyAndDemand>
	 */

	public Set<SupplyAndDemand> readFile()
	{
		Path path = Paths.get("supply-demand-input.txt");
		try (Stream<String> lines = Files.lines(path)) {

			lines.forEach(l -> allItems.add(new SupplyAndDemand(l)));
					
		}catch (Exception e) {
			e.printStackTrace();
		}
		return allItems;
	}
	
	/**
	 * This method write List of Trade in a file
	 * @param tList
	 * @return
	 */
	public boolean writeFile(List<Trade> tList) {
		 
		Path pathOut = Paths.get("supply-demand-output");
		   
		   tList.forEach(t -> {
			try {
				Files.writeString(pathOut, t.toString(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		return true;
	}

}
