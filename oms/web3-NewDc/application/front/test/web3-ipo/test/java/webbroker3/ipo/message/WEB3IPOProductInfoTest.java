head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IPOProductInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IPOProductInfoTest extends TestBaseForMock
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IPOProductInfo.class);

    public WEB3IPOProductInfoTest(String name)
    {
        super(name);
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

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "募集・売";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00437);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "1";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "2";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "3";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "4";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "5";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(STR_METHOD_NAME);

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "6";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(STR_METHOD_NAME);

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "7";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(STR_METHOD_NAME);

        WEB3IPOProductInfo l_iPOProductInfo = new WEB3IPOProductInfo();
        l_iPOProductInfo.ipoRegistDiv = "1";
        l_iPOProductInfo.ipoRegistDetailDiv = "8";

        try
        {
            l_iPOProductInfo.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00079);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
