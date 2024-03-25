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
	
}
