head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	TestAllMockClass.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mock;

import webbroker3.common.WEB3BaseException;

public class TestAllMockClass
{

    public TestAllMockClass()
    {
        super();
    }
    public void method1(int a, long b, double c,char d, float e, boolean f)
    {
        throw new RuntimeException("method1");
    }
    
    public void method2(int a, long b, double c,boolean e,String f)
    {
        throw new RuntimeException("method1");
    }
    
    public void method5(String a)
    {
        throw new RuntimeException("method5");
    }
    
    public void method5(String a,int b,long c)
    {
        throw new RuntimeException("method5");
    }
    
    public String method6(String a,int b,long c)
        throws WEB3BaseException
    {
        throw new RuntimeException("method6");
    }
    public String method6(String a)
        throws WEB3BaseException
    {
        throw new RuntimeException("method6");
    }
}
@
