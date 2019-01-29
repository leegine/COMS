head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockObjectReturnValueForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mock.util;


public class WEB3MockObjectReturnValueForMock
{

    public WEB3MockObjectReturnValueForMock()
    {
        super();
    }
    public Object asObject()
    {
        return null;
    }
    
    public void asVoid()
    {        
    }
    
    public int asInt()
    {
        return 0;
    }
    public long asLong()
    {
        return 0;
    }
    public double asDouble()
    {
        return 0;
    }
    public boolean asBoolean()
    {
        return false;
    }
    
    public float asFloat()
    {
        return 0;
    }
    
    public void asWEB3BaseException()
    {
        return;
    }
}
@
