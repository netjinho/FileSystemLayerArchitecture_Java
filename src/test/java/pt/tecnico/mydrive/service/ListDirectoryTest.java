package pt.tecnico.mydrive.service;

import java.util.List;
import java.util.ArrayList;


import pt.tecnico.mydrive.exception.NoSuchFileException;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.PlainFile;
import pt.tecnico.mydrive.domain.Link;
import pt.tecnico.mydrive.domain.App;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Session;
import pt.tecnico.mydrive.domain.MyDrive;

import static org.junit.Assert.*;

import org.junit.Test;


public class ListDirectoryTest extends AbstractServiceTest{
		ArrayList<String> list;
		String home_string;
		
	protected void populate(){
		MyDrive md = MyDrive.getInstance();
		User u1 = new User("EusebioSilva","pass1", "Eusebio");
		Directory home= u1.getMainDirectory();
		PlainFile p1 = new PlainFile("CasoBruma", 123, u1, "conteudo1", home);
	    PlainFile p2 = new PlainFile("Exemplo", 124, u1, "conteudo3", home);
	    App a1 = new App("application", 125, u1, "conteudo1", home);
	   	Link l1 = new Link("ligacao", 126, u1, "conteudo1", home);
	   	Session s1 = new Session(u1,1,md);
	   	Session s2 = new Session(u1,2,md);
	   	list = new ArrayList<String>();
	   	s2.setCurrentDir(md.getRootDirectory());
	   	list.add(a1.toString());
	   	list.add(p1.toString());
	   	list.add(p2.toString());
	   	list.add(l1.toString());
	   	home_string= md.getRootDirectory().get("home").toString();


	}

	@Test
	public void PermitedListDirectory() throws NoSuchFileException{
		final long token = 1;
        ListDirectoryService service = new ListDirectoryService(token);
        service.execute();
        List<String> cs = service.result();

        // check contact listing
        /*
        assertEquals("List with 6 Contacts", 6, cs.size());
		assertEquals("First File is FatherDir", "..", cs.get(0).getName());
		assertEquals("Second File is SelfDir", ".", cs.get(1).getName());
		assertEquals("Third File is application", "application", cs.get(2).getName());
		assertEquals("Fourth File is Caso Bruma", "Caso Bruma", cs.get(3).getName());
		assertEquals("Fifth File is Exemplo", "Exemplo", cs.get(4).getName());
		assertEquals("Sixth File is ligacao ligacao", "ligacao", cs.get(5).getName());*/

		assertEquals("List with 6 Contacts", 6, cs.size());
		assertEquals("First File is FatherDir", "..", cs.get(0));
		assertEquals("Second File is SelfDir", ".", cs.get(1));
		assertEquals("Third File is application", list.get(0), cs.get(2));
		assertEquals("Fourth File is Caso Bruma", list.get(1), cs.get(3));
		assertEquals("Fifth File is Exemplo", list.get(2), cs.get(4));
		assertEquals("Sixth File is ligacao ligacao", list.get(3), cs.get(5));


	}
	@Test
	public void ListRootDirectory() throws NoSuchFileException{
		final long token = 2;
		ListDirectoryService service= new ListDirectoryService(token);
		service.execute();
		List<String> cs = service.result();
		assertEquals("List with 3 Contacts", 3, cs.size());
		assertEquals("First File is FatherDir", "..", cs.get(0));
		assertEquals("Second File is SelfDir", ".", cs.get(1));
		assertEquals("Third File is Home", home_string, cs.get(2));

	}



	

}