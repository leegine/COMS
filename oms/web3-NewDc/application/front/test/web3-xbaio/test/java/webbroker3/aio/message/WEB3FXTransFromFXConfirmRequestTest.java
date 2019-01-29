head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransFromFXConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransFromFXConfirmRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransFromFXConfirmRequestTest.class);
    WEB3FXTransFromFXConfirmRequest l_request;
    
    public WEB3FXTransFromFXConfirmRequestTest(String arg0)
    {
        super(arg0);
        l_request = new WEB3FXTransFromFXConfirmRequest();
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testVlidate_Case001()
    {
        final String STR_METHOD_NAME = "testVlidate_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.transferAmount = "100";
            l_request.fxAccInformationUnit = null;
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testVlidate_Case002()
    {
        final String STR_METHOD_NAME = "testVlidate_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_request.transferAmount = "100";
            WEB3FXAccInformationUnit l_informationUnit =
                new WEB3FXAccInformationUnit();
            l_informationUnit.fxAccountCode = "123456";
            l_informationUnit.fxCourseDiv = "1";
            l_request.fxAccInformationUnit = l_informationUnit;
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
