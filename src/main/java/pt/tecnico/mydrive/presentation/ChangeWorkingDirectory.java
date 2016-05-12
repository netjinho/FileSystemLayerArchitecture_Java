package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.ChangeDirectoryService;

public class ChangeWorkingDirectory extends MyDriveCommand {
	public ChangeWorkingDirectory(MyDriveShell sh){
		super(sh, "cwd", "Exchange working directory");
	}

	public void execute(String[] args){
		if(args.length == 1){
			String path = args[0];
			ChangeDirectoryService cds = new ChangeDirectoryService(shell().getActiveToken(), path);
			cds.execute();
			System.out.println("Current directory: " + cds.getResult());
		}
		else{
			throw new RuntimeException("USAGE: " + name() + " [<path>]");
		}
	}
}
