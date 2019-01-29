head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	FileFilter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class FileFilter implements FilenameFilter
{
	private Pattern pattern;
	public FileFilter(String regex)
	{
		this.pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
	}
	public boolean accept(File dir, String name)
	{
		return pattern.matcher(new File(name).getName()).matches();
	}

}
@
