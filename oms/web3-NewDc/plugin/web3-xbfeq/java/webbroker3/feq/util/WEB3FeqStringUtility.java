head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqStringUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 * Created on 2005/08/04
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package webbroker3.feq.util;

import webbroker3.util.WEB3StringTypeUtility;

/**
 * @@author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WEB3FeqStringUtility extends WEB3StringTypeUtility
{

    /**
     * 
     */
    public WEB3FeqStringUtility()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 
     * 
     * @@param l_str ˆê‚Â‚Ì•¶Žš—ñ
     * @@return  
     */
    public static String addForString(String l_str, int l_intAdd)
    {
        int l_intTmp = Integer.parseInt(l_str);
        l_intTmp = l_intTmp + l_intAdd;
        
        return formatNumber(l_intTmp, l_str.length());
    }
    
    public static String fillZero(String str,int len,String fillChar)
    {
        String b=str;
        String c="";
        for(int i=0;i<len;i++)
            c+=fillChar;
        if((b.length()<len)&&(b.length()>0))
            b=c.substring(0,len-b.length())+b;
        return b;
    }  
}
@
