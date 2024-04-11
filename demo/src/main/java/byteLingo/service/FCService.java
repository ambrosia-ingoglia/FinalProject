package byteLingo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import byteLingo.entity.FCEntity;
import byteLingo.repository.FCRepository;

@Service
public class FCService {
	
	@Autowired
	private FCRepository FCRepository;
	
	public List<FCEntity> getDSFlaschcards(){
		return FCRepository.getDSdatabase();
	}
	
	public List<FCEntity> getOOPFlaschcards(){
		return FCRepository.getOOPdatabase();
	}
	
	public List<FCEntity> getMyFlaschcards(String table){
		return FCRepository.getMyTable(table);
	}
	
	public Integer check(String tablename) {
		Integer result = FCRepository.checkDB(tablename);
		return result;
	}
	
	public void newFC(String tablename, String data){
		
		 // Split the input text by tabs
        String[] rows = data.split("\\n"); // Split by newline character
        String[] column1 = new String[rows.length];
        String[] column2 = new String[rows.length];
        //System.out.println(tablename);
        FCRepository.createDB(tablename);
        
        // Populate the arrays
        for (int i = 0; i < rows.length; i++) {
            String[] columns = rows[i].split("\\t"); // Split by tab character
            if (columns.length >= 2) {
                column1[i] = columns[0];
                column2[i] = columns[1];
                //System.out.print(column1[i] + ", ");
                //System.out.print(column2[i] + "\n");
                FCRepository.newFCdata(tablename, i, column1[i], column2[i]);
                
            }
        }
		

	}
	
}
