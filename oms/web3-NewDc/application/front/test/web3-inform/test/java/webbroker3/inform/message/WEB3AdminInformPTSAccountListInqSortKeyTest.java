head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.31.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformPTSAccountListInqSortKeyTest.java;


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
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformPTSAccountListInqSortKeyTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListInqSortKeyTest.class);

    public WEB3AdminInformPTSAccountListInqSortKeyTest(String arg0)
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

        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = null;
        
        try
        {
            key.validate();
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
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

        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "aaaaa";

        try
        {
            key.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
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

        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "branchCode";
        key.ascDesc = null;

        try
        {
            key.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
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

        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "accountCode";
        key.ascDesc = "C";

        try
        {
            key.validate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
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

        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "applyDate";
        key.ascDesc = "A";

        try
        {
            key.validate();
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

        WEB3AdminInformPTSAccountListInqSortKey key = new WEB3AdminInformPTSAccountListInqSortKey();
        key.keyItem = "applyDate";
        key.ascDesc = "D";

        try
        {
            key.validate();
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
