package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.SessionManager;
import pt.tecnico.mydrive.domain.Session;
import pt.tecnico.mydrive.domain.Env;

import pt.tecnico.mydrive.service.dto.VariableDto;

import java.util.List;
import java.util.ArrayList;

public class AddVariableService extends MyDriveService {

	private long token;
	private String name;
	private String value;
	private Session s;

	public AddVariableService(long token, String name, String value){
		this.token = token;
		this.name = name;
		this.value = value;
	}

	@Override
	public final void dispatch(){
		s = SessionManager.getInstance().getSession(token);
		s.addEnv(new Env(name, value));
	}

	public final List<VariableDto> result() {
		ArrayList<VariableDto> res = new ArrayList<VariableDto>();
 		for(Env e : s.getEnvSet()){
 			res.add(new VariableDto(e.toString()));
		}
		return res;
	} 

}