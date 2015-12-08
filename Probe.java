/*
The MIT License (MIT)

Copyright (c) 2015 Robert Halandut

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

import lib.Jcac;
import java.io.*;

public class Probe  {
	public static void main(String[] args) throws Exception {

		Jcac test = new Jcac();
		//Configure the API 
		test.Configure("<APIKEY>","<LOGIN_EMAIL>");

		System.out.println(test.console(<SID>));
			/*
				Output: [JSON-FORMAT] You'll recive the URL for console access
			*/
		System.out.println(test.runmode(<SID>,"normal"));
			/*
				Output: [JSON-FORMAT] You'll change the run mode of the server
			*/		
		System.out.println(test.powerop(<SID>,"poweron"));
			/*
				Output: [JSON-FORMAT] You'll Power ON the server
			*/				
		System.out.println(test.listsServers());
			/*
				Output: [JSON-FORMAT] List all servers on the account
			*/				
		System.out.println(test.listtemplates());
			/*
				Output: [JSON-FORMAT] List all templates available
			*/			
		System.out.println(test.listtasks());
			/*
				Output: [JSON-FORMAT] List all tasks in operation
			*/	
	}
}
