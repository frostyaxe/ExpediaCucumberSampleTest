
package com.github.frostyaxe.cucumber.utils;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.LinkedList;
import java.util.List;

public class NPMUtils
{
	
	private Process process  = null;
	private String npm = null;
	private List<String> createCommand = new LinkedList<>();
	
	
	{
		npm  = isWindows() ? "npm.cmd" : "npm";
		createCommand.add(npm);
	}
	
		
	private boolean isWindows()
	{
	    return System.getProperty("os.name").toLowerCase().contains("win");
	}

	

	public void run(String... command)
	{
		
		for( int counter = 0; counter < command.length; counter++ )
		{
			createCommand.add(command[counter]);
		}
		
		ProcessBuilder builder = new ProcessBuilder(createCommand);

		builder.redirectOutput(Redirect.INHERIT);
		builder.redirectError(Redirect.INHERIT);
		builder.redirectInput(Redirect.INHERIT);
		
	    try
	    {
			 process = builder
			        .directory(new File(System.getProperty("user.dir")))
			        .start();
			 
			 process.waitFor();
		} 
	    catch (IOException | InterruptedException e) 
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally
	    {
	    	try
	    	{
	    		process.destroyForcibly();
	    	}
	    	catch( NullPointerException NPE )
	    	{
	    		System.out.println("Unable to destroy process as this process was not created successfully!");
	    	}
	    }
	   
	}
	
	
	public static void main(String[] args)
	{
		new NPMUtils().run("install", "--prefix",System.getProperty("user.dir"),"chromedriver");
	}
	
	
	
}
