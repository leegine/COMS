head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualLapseInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効入力リクエスト (WEB3AdminIfoManualLapseInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoManualLapseInputRequestTest extends TestBaseForMock
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualLapseInputRequestTest.class);

    public WEB3AdminIfoManualLapseInputRequestTest(String arg0)
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
    
    public void testvalidate_0001()
    {
        String STR_METHOD_NAME = "testvalidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoManualLapseInputRequest request = new WEB3AdminIfoManualLapseInputRequest();
            request.branchCode = null;
            request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2174" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードがnullです。" , l_ex.getErrorInfo().getErrorMessage());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0002()
    {
        String STR_METHOD_NAME = "testvalidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoManualLapseInputRequest request = new WEB3AdminIfoManualLapseInputRequest();
            request.branchCode = new String[0];
            request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2174" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードがnullです。" , l_ex.getErrorInfo().getErrorMessage());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0003()
    {
        String STR_METHOD_NAME = "testvalidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoManualLapseInputRequest request = new WEB3AdminIfoManualLapseInputRequest();
            String[] l_strArray = {"123" , "asd" , "123"};
            request.branchCode = l_strArray;
            request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("779" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0004()
    {
        String STR_METHOD_NAME = "testvalidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoManualLapseInputRequest request = new WEB3AdminIfoManualLapseInputRequest();
            String[] l_strArray = {"1234"};
            request.branchCode = l_strArray;
            request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("779" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0005()
    {
        String STR_METHOD_NAME = "testvalidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoManualLapseInputRequest request = new WEB3AdminIfoManualLapseInputRequest();
            String[] l_strArray = {"123" , "123" ,"123"};
            request.branchCode = l_strArray;
            request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
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
