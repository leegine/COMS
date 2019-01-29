head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFXAccOpenStatusUpdCommonRequestTest.java;


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
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFXAccOpenStatusUpdCommonRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenStatusUpdCommonRequest.class);

    public WEB3AdminFXAccOpenStatusUpdCommonRequestTest(String arg0)
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

    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            new WEB3FXAccInformationUnit[1];
        WEB3FXAccInformationUnit l_fXAccInformationUnit =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit.fxCourseDiv = "3";
        l_fXAccInformationUnit.fxAccountCode = "123456789777";
        l_fXAccInformationUnits[0] = l_fXAccInformationUnit;

        WEB3AdminFXAccOpenStatusUpdCommonRequest l_commonRequest =
            new WEB3AdminFXAccOpenStatusUpdCommonRequest();
        l_commonRequest.updatedStatusDiv = "1";
        l_commonRequest.updatedAgreementDiv = "2";
        l_commonRequest.fxAccInformationList = l_fXAccInformationUnits;
        l_commonRequest.fxSearchConditionUnit =
            new WEB3FXSearchConditionUnit();
        l_commonRequest.fxSearchConditionUnit.branchCode = "123";
        l_commonRequest.fxSearchConditionUnit.requestNumber = "0";

        try
        {
            l_commonRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01755, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            new WEB3FXAccInformationUnit[1];
        WEB3FXAccInformationUnit l_fXAccInformationUnit =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit.fxCourseDiv = "4";
        l_fXAccInformationUnit.fxAccountCode = null;
        l_fXAccInformationUnits[0] = l_fXAccInformationUnit;

        WEB3AdminFXAccOpenStatusUpdCommonRequest l_commonRequest =
            new WEB3AdminFXAccOpenStatusUpdCommonRequest();
        l_commonRequest.updatedStatusDiv = "1";
        l_commonRequest.updatedAgreementDiv = "2";
        l_commonRequest.fxAccInformationList = l_fXAccInformationUnits;
        l_commonRequest.fxSearchConditionUnit =
            new WEB3FXSearchConditionUnit();
        l_commonRequest.fxSearchConditionUnit.branchCode = "123";
        l_commonRequest.fxSearchConditionUnit.requestNumber = "0";

        try
        {
            l_commonRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01754, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            new WEB3FXAccInformationUnit[1];
        WEB3FXAccInformationUnit l_fXAccInformationUnit =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit.fxCourseDiv = "3";
        l_fXAccInformationUnit.fxAccountCode = "12345";
        l_fXAccInformationUnits[0] = l_fXAccInformationUnit;

        WEB3AdminFXAccOpenStatusUpdCommonRequest l_commonRequest =
            new WEB3AdminFXAccOpenStatusUpdCommonRequest();
        l_commonRequest.updatedStatusDiv = "1";
        l_commonRequest.updatedAgreementDiv = "2";
        l_commonRequest.fxAccInformationList = l_fXAccInformationUnits;
        l_commonRequest.fxSearchConditionUnit =
            new WEB3FXSearchConditionUnit();
        l_commonRequest.fxSearchConditionUnit.branchCode = "123";
        l_commonRequest.fxSearchConditionUnit.requestNumber = "0";

        try
        {
            l_commonRequest.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            new WEB3FXAccInformationUnit[11];
        WEB3FXAccInformationUnit l_fXAccInformationUnit0 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit0.fxCourseDiv = "0";
        l_fXAccInformationUnit0.fxAccountCode = "12345";
        l_fXAccInformationUnits[0] = l_fXAccInformationUnit0;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit1 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit1.fxCourseDiv = "1";
        l_fXAccInformationUnit1.fxAccountCode = "12345";
        l_fXAccInformationUnits[1] = l_fXAccInformationUnit1;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit2 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit2.fxCourseDiv = "2";
        l_fXAccInformationUnit2.fxAccountCode = "12345";
        l_fXAccInformationUnits[2] = l_fXAccInformationUnit2;

        WEB3FXAccInformationUnit l_fXAccInformationUnit3 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit3.fxCourseDiv = "3";
        l_fXAccInformationUnit3.fxAccountCode = "12345";
        l_fXAccInformationUnits[3] = l_fXAccInformationUnit3;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit4 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit4.fxCourseDiv = "4";
        l_fXAccInformationUnit4.fxAccountCode = "12345";
        l_fXAccInformationUnits[4] = l_fXAccInformationUnit4;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit5 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit5.fxCourseDiv = "5";
        l_fXAccInformationUnit5.fxAccountCode = "12345";
        l_fXAccInformationUnits[5] = l_fXAccInformationUnit5;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit6 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit6.fxCourseDiv = "6";
        l_fXAccInformationUnit6.fxAccountCode = "12345";
        l_fXAccInformationUnits[6] = l_fXAccInformationUnit6;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit7 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit7.fxCourseDiv = "7";
        l_fXAccInformationUnit7.fxAccountCode = "12345";
        l_fXAccInformationUnits[7] = l_fXAccInformationUnit7;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit8 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit8.fxCourseDiv = "8";
        l_fXAccInformationUnit8.fxAccountCode = "12345";
        l_fXAccInformationUnits[8] = l_fXAccInformationUnit8;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit9 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit9.fxCourseDiv = "9";
        l_fXAccInformationUnit9.fxAccountCode = "12345";
        l_fXAccInformationUnits[9] = l_fXAccInformationUnit9;
        
        WEB3FXAccInformationUnit l_fXAccInformationUnit10 =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit10.fxCourseDiv = "10";
        l_fXAccInformationUnit10.fxAccountCode = "12345";
        l_fXAccInformationUnits[10] = l_fXAccInformationUnit10;

        WEB3AdminFXAccOpenStatusUpdCommonRequest l_commonRequest =
            new WEB3AdminFXAccOpenStatusUpdCommonRequest();
        l_commonRequest.updatedStatusDiv = "1";
        l_commonRequest.updatedAgreementDiv = "2";
        l_commonRequest.fxAccInformationList = l_fXAccInformationUnits;
        l_commonRequest.fxSearchConditionUnit =
            new WEB3FXSearchConditionUnit();
        l_commonRequest.fxSearchConditionUnit.branchCode = "123";
        l_commonRequest.fxSearchConditionUnit.requestNumber = "0";

        try
        {
            l_commonRequest.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccInformationUnit[] l_fXAccInformationUnits =
            new WEB3FXAccInformationUnit[1];
        WEB3FXAccInformationUnit l_fXAccInformationUnit =
            new WEB3FXAccInformationUnit();
        l_fXAccInformationUnit.fxCourseDiv = "99";
        l_fXAccInformationUnit.fxAccountCode = "12345";
        l_fXAccInformationUnits[0] = l_fXAccInformationUnit;

        WEB3AdminFXAccOpenStatusUpdCommonRequest l_commonRequest =
            new WEB3AdminFXAccOpenStatusUpdCommonRequest();
        l_commonRequest.updatedStatusDiv = "1";
        l_commonRequest.updatedAgreementDiv = "2";
        l_commonRequest.fxAccInformationList = l_fXAccInformationUnits;
        l_commonRequest.fxSearchConditionUnit =
            new WEB3FXSearchConditionUnit();
        l_commonRequest.fxSearchConditionUnit.branchCode = "123";
        l_commonRequest.fxSearchConditionUnit.requestNumber = "0";

        try
        {
            l_commonRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01754, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
