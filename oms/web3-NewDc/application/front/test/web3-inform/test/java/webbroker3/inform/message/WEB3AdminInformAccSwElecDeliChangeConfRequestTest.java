head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.31.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformAccSwElecDeliChangeConfRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : （WEB3AdminInformAccSwElecDeliChangeConfRequestTest.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/09/21 トウ鋒鋼 (中訊) 新規作成
 */
package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformAccSwElecDeliChangeConfRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AdminInformAccSwElecDeliChangeConfRequestTest.class);

    WEB3AdminInformAccSwElecDeliChangeConfRequest l_request = null;

    public WEB3AdminInformAccSwElecDeliChangeConfRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_request = new WEB3AdminInformAccSwElecDeliChangeConfRequest();
    }

    protected void tearDown() throws Exception
    {
        l_request = null;
        super.tearDown();
    }

    public void testValidate_case0001()
    {
        final String STR_METHOD_NAME = "testValidate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_case0002()
    {
        final String STR_METHOD_NAME = "testValidate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.branchCode = "1235";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_case0003()
    {
        final String STR_METHOD_NAME = "testValidate_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.branchCode = "abc";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_case0004()
    {
        final String STR_METHOD_NAME = "testValidate_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.branchCode = "123";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00835, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_case0005()
    {
        final String STR_METHOD_NAME = "testValidate_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.branchCode = "123";
            l_request.accountCode = "1235";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_case0006()
    {
        final String STR_METHOD_NAME = "testValidate_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.branchCode = "123";
            l_request.accountCode = "abcdfg";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_case0007()
    {
        final String STR_METHOD_NAME = "testValidate_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01817, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_case0008()
    {
        final String STR_METHOD_NAME = "testValidate_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.informType = "1";
            l_request.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate_case0009()
    {
        final String STR_METHOD_NAME = "testValidate_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.informType = "1";
            l_request.requestNumber = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
