# JCac
CloudatCost API - Java

# Usage:
<ul><li>Edit the "Probe.java" file with your data</li><li>Compile the sources:
<code>> javac lib/Jcac.java</code>
<code>> javac Probe.java</code>
</li>
<li>
Execute: <code>> java Probe</code>
</li>
</ul>
#OR
Add the <code>"lib/Jcac.java"</code> file to your project

# EXAMPLE

>		Jcac test = new Jcac();
		//Configure the API 
		test.Configure("<APIKEY>","<LOGIN_EMAIL>");
		System.out.println(test.listsServers());
		/*
				Output: [JSON-FORMAT] List all servers on the account
		*/	

#References
https://github.com/cloudatcost/api

#License : MIT

#TL;DR
Read the Documentation before use these API.<BR>
(I'm not a master of java so if I wrote something wrong, open a Issues)
