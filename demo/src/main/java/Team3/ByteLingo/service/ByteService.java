package Team3.ByteLingo.service;

import Team3.ByteLingo.repository.ByteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ByteService {
	@Autowired
	private ByteRepository byteRepository;
	
	public void fetchRepository() {
		byteRepository.readUsersFromDB();
	}
	
}
