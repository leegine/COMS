head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.31.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformPTSAccountListInqConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformPTSAccountListInqConditionTest extends TestBaseForMock
{
    /**
     * (ÉçÉOèoóÕÉÜÅ[ÉeÉBÉäÉeÉB)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListInqConditionTest.class);

    public WEB3AdminInformPTSAccountListInqConditionTest(String arg0)
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

    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = " testValidate_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = null;

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01817, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = " testValidate_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        condition.branchCode = null;

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01429, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = " testValidate_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        condition.branchCode = new String[0];

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02175, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = " testValidate_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        String[] branchCode = new String[1];
        branchCode[0] = "ÇPÇPÇP";
        condition.branchCode = branchCode;

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = " testValidate_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        String[] branchCode = new String[1];
        branchCode[0] = "11";
        condition.branchCode = branchCode;

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = " testValidate_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        String[] branchCode = new String[1];
        branchCode[0] = "111";
        condition.branchCode = branchCode;
        condition.accountCode = "ÇPÇQÇR";

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = " testValidate_0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        String[] branchCode = new String[1];
        branchCode[0] = "111";
        condition.branchCode = branchCode;
        condition.accountCode = "1234567";

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = " testValidate_0008()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        String[] branchCode = new String[1];
        branchCode[0] = "111";
        condition.branchCode = branchCode;
        condition.accountCode = "123456";
        condition.ptsAccOpenDiv = "ÇP";

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03042, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = " testValidate_0009()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        String[] branchCode = new String[1];
        branchCode[0] = "111";
        condition.branchCode = branchCode;
        condition.accountCode = "123456";
        condition.ptsAccOpenDiv = "1";
        condition.applyDateFrom = WEB3DateUtility.getDate("20070606", "yyyyMMdd");
        condition.applyDateTo = WEB3DateUtility.getDate("20070605", "yyyyMMdd");

        try
        {
            condition.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03041, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = " testValidate_0010()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInqCondition condition = new WEB3AdminInformPTSAccountListInqCondition();
        condition.informType = "1";
        String[] branchCode = new String[1];
        branchCode[0] = "111";
        condition.branchCode = branchCode;
        condition.accountCode = "123456";
        condition.ptsAccOpenDiv = "1";
        condition.applyDateFrom = WEB3DateUtility.getDate("20070606", "yyyyMMdd");
        condition.applyDateTo = WEB3DateUtility.getDate("20070607", "yyyyMMdd");

        try
        {
            condition.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
