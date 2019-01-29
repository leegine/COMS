head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoOrderCarryOverMainRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoOrderCarryOverMainRequestTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/6/23 孫洪江 (中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoOrderCarryOverMainRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3IfoOrderCarryOverMainRequestTest.class);

    public WEB3IfoOrderCarryOverMainRequestTest(String arg0)
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
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderCarryOverMainRequest l_request = new WEB3IfoOrderCarryOverMainRequest();
        l_request.institutionCode = null;

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoOrderCarryOverMainRequest l_request = new WEB3IfoOrderCarryOverMainRequest();
        l_request.institutionCode = "institutionCode";

        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
}
@
