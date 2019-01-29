head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3DateUtilityTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 日付型のデータのユーティリティテスト(WEB3DateUtilityTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/7  齊珂(中訊) 新規作成
*/

package webbroker3.util;

import java.util.Date;

import webbroker3.util.WEB3DateUtility;

import junit.framework.TestCase;

public class WEB3DateUtilityTest extends TestCase
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3DateUtilityTest.class);
    
    public WEB3DateUtilityTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testAddMonth01()
    {
        final String STR_METHOD_NAME = "testAddMonth01()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datNew = null;
        //WEB3DateUtility.getDate("20070207", "yyyyMMdd");
        try
        {
            Date l_datRes = WEB3DateUtility.addMonth(l_datNew, 1);
            assertNull(l_datRes);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAddMonth02()
    {
        final String STR_METHOD_NAME = "testAddMonth02()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datNew = WEB3DateUtility.getDate("20070207", "yyyyMMdd");
        Date l_datCmp = WEB3DateUtility.getDate("20070307", "yyyyMMdd");
        try
        {
            Date l_datRes = WEB3DateUtility.addMonth(l_datNew, 1);
            assertEquals(l_datCmp, l_datRes);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAddMonth03()
    {
        final String STR_METHOD_NAME = "testAddMonth03()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datNew = WEB3DateUtility.getDate("20070207", "yyyyMMdd");
        Date l_datCmp = WEB3DateUtility.getDate("20070107", "yyyyMMdd");
        try
        {
            Date l_datRes = WEB3DateUtility.addMonth(l_datNew, -1);
            assertEquals(l_datCmp, l_datRes);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAddMonth04()
    {
        final String STR_METHOD_NAME = "testAddMonth04()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datNew = WEB3DateUtility.getDate("20070207", "yyyyMMdd");
        Date l_datCmp = WEB3DateUtility.getDate("20080107", "yyyyMMdd");
        try
        {
            Date l_datRes = WEB3DateUtility.addMonth(l_datNew, 11);
            assertEquals(l_datCmp, l_datRes);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAddMonth05()
    {
        final String STR_METHOD_NAME = "testAddMonth05()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datNew = WEB3DateUtility.getDate("20070207", "yyyyMMdd");
        Date l_datCmp = WEB3DateUtility.getDate("20080207", "yyyyMMdd");
        try
        {
            Date l_datRes = WEB3DateUtility.addMonth(l_datNew, 12);
            assertEquals(l_datCmp, l_datRes);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAddMonth06()
    {
        final String STR_METHOD_NAME = "testAddMonth06()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datNew = WEB3DateUtility.getDate("20070207", "yyyyMMdd");
        Date l_datCmp = WEB3DateUtility.getDate("20080307", "yyyyMMdd");
        try
        {
            Date l_datRes = WEB3DateUtility.addMonth(l_datNew, 13);
            assertEquals(l_datCmp, l_datRes);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testAddMonth07()
    {
        final String STR_METHOD_NAME = "testAddMonth07()";
        log.entering(STR_METHOD_NAME);
        
        Date l_datNew = WEB3DateUtility.getDate("20070808", "yyyyMMdd");
        Date l_datCmp = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        try
        {
            Date l_datRes = WEB3DateUtility.addMonth(l_datNew, 12);
            assertEquals(l_datCmp, l_datRes);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
