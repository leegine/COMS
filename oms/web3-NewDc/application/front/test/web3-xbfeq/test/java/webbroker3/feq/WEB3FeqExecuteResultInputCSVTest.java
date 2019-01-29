head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqExecuteResultInputCSVTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import test.util.JunitTestBase;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import junit.framework.TestCase;

public class WEB3FeqExecuteResultInputCSVTest extends JunitTestBase
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteResultInputCSVTest.class);
    WEB3FeqExecuteResultInputCSV l_imputCSV = null;
    
    public WEB3FeqExecuteResultInputCSVTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_imputCSV = new WEB3FeqExecuteResultInputCSV();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateDetailLine_Case001()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_imputCSV.addRow("NVjidd0,NVjiddk5,NVjiddk1,123456,123456,123456,NVjiddk6,20080202124512");
            l_imputCSV.validateDetailLine(0, "cc");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03164);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateDetailLine_Case002()
    {
        final String STR_METHOD_NAME = "testValidateDetailLine_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_imputCSV.addRow("NVjidd0,NVjiddk5,NVjiddk1,123456,123456,123456,NVjiddk6,20080202124512");
            l_imputCSV.validateDetailLine(0, "NV");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02035);
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
