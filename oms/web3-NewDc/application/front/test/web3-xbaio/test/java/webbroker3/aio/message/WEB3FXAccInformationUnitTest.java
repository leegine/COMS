head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXAccInformationUnitTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXAccInformationUnitTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccInformationUnitTest.class);

    public WEB3FXAccInformationUnitTest(String arg0)
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

    /*
     * Test method for 'webbroker3.aio.message.WEB3FXAccInformationUnit.validate()'
     */
    public void testValidateCase1()
    {
        final String STR_METHOD_NAME = "testValidateCase1()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = "1";
            l_unit.fxAccountCode = "12345678912";
            l_unit.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01106, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateCase2()
    {
        final String STR_METHOD_NAME = "testValidateCase2()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = "1";
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase3()
    {
        final String STR_METHOD_NAME = "testValidateCase3()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE;
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase4()
    {
        final String STR_METHOD_NAME = "testValidateCase4()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_2;
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase5()
    {
        final String STR_METHOD_NAME = "testValidateCase5()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_3;
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase6()
    {
        final String STR_METHOD_NAME = "testValidateCase6()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_4;
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase7()
    {
        final String STR_METHOD_NAME = "testValidateCase7()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_5;
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase8()
    {
        final String STR_METHOD_NAME = "testValidateCase8()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_6;
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase9()
    {
        final String STR_METHOD_NAME = "testValidateCase9()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_7;
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase10()
    {
        final String STR_METHOD_NAME = "testValidateCase10()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = WEB3GftTransStatusCourseDivDef.CFD_COURSE_8;
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateCase11()
    {
        final String STR_METHOD_NAME = "testValidateCase11()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXAccInformationUnit l_unit = new WEB3FXAccInformationUnit();
            l_unit.fxCourseDiv = "99";
            l_unit.fxAccountCode = "1234567";
            l_unit.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01793, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
