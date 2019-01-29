head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	TestAllMockClassForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mock;

import webbroker3.common.WEB3BaseException;

public class TestAllMockClassForMock extends TestAllMockClass
{

    public TestAllMockClassForMock()
    {
        super();
    }

    
    public void method1(int a, long b, double c,char d, float e, boolean f)
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mock.TestAllMockClass",
            "method1",
            new Class[]{int.class,long.class,double.class,char.class,float.class,boolean.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mock.TestAllMockClass",
            "method1",
            new Class[]{int.class,long.class,double.class,char.class,float.class,boolean.class}).asVoid();
            return;
        }
        super.method1(a,b,c,d,e,f);
    }
    
    public void method2(int a, long b, double c,boolean e,String f)
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mock.TestAllMockClass",
            "method2",
            new Class[]{int.class,long.class,double.class,boolean.class, String.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mock.TestAllMockClass",
            "method2",
            new Class[]{int.class,long.class,double.class,boolean.class, String.class}).asVoid();
            return;
        }
        super.method2(a,b,c,e,f);
    }
    
    public void method3()
    {
        
    }
    public void method4()
    {
        
    }
    
    public void method5(String a)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class},
            new Object[]{a});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class}))
        {            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class}).asVoid();
            return;
        }
        super.method5(a);
    }
    
    public void method5(String a, int b, long c)
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue("webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class, int.class, long.class},
            new Object[]{a,new Integer(b), new Long(c)});
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class, int.class, long.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue("webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class, int.class, long.class}).asVoid();
            return;
        }
        super.method5(a, b, c);
    }
    
    public String method6(String a,int b,long c) throws WEB3BaseException
        
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mock.TestAllMockClass",
            "method6",
            new Class[]{String.class, int.class, long.class},
            new Object[]{a, new Integer(b), new Long(c)});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mock.TestAllMockClass",
            "method6",
            new Class[]{String.class, int.class, long.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mock.TestAllMockClass",
                "method6",
                new Class[]{String.class, int.class, long.class}).asWEB3BaseException();
            
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mock.TestAllMockClass",
                "method6",
                new Class[]{String.class, int.class, long.class}).asObject();
            
        }
        return super.method6(a, b, c);
    }
    
    public String method6(String a) throws WEB3BaseException
    
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.mock.TestAllMockClass",
            "method6",
            new Class[]{String.class},
            new Object[]{a});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mock.TestAllMockClass",
            "method6",
            new Class[]{String.class}))
        {
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mock.TestAllMockClass",
                "method6",
                new Class[]{String.class}).asWEB3BaseException();
            
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mock.TestAllMockClass",
                "method6",
                new Class[]{String.class}).asObject();
            
        }
        return super.method6(a);
    }
}
@
