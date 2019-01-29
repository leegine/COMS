head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.22.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioCashTransferListRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金一覧結果リクエストテスト(WEB3AdminAioCashTransferListRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/06 周捷 (中訊) 新規作成
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （入出金一覧結果リクエストテスト）<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminAioCashTransferListRequestTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AdminAioCashTransferListRequestTest.class);

    public WEB3AdminAioCashTransferListRequestTest(String arg0)
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

    WEB3AdminAioCashTransferListRequest l_request =
        new WEB3AdminAioCashTransferListRequest();

    /*
     * Test method for 'webbroker3.aio.message.WEB3AdminAioCashTransferListRequest.validate()'
     */
    public void testValidate1()
    {
        final String STR_METHOD_NAME = "testMutate1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{};
        l_request.branchCode = l_strBranchCodes;
        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("部店コードの要素数が０です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate2()
    {
        final String STR_METHOD_NAME = "testMutate2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("部店コードが未指定です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate3()
    {
        final String STR_METHOD_NAME = "testMutate3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "asd"};
        l_request.branchCode = l_strBranchCodes;

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("部店コードが数値以外の値です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate4()
    {
        final String STR_METHOD_NAME = "testMutate4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.accountCode = "123ads";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("顧客コードの値が数字以外の値です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate5()
    {
        final String STR_METHOD_NAME = "testMutate5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.accountCode = "123456789";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("顧客コードのサイズが不正です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate6()
    {
        final String STR_METHOD_NAME = "testMutate6()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.accountCode = "123456";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("部店コードの要素数が1以外です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate7()
    {
        final String STR_METHOD_NAME = "testMutate7()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = null;

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("受渡日が未指定です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate8()
    {
        final String STR_METHOD_NAME = "testMutate8()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.orderType = "222";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("注文種別の値が存在しないコード値です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate9()
    {
        final String STR_METHOD_NAME = "testMutate9()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.orderType = "000";
        l_request.cashinoutStatus = "8";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("ステータスが存在しないコード値です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate10()
    {
        final String STR_METHOD_NAME = "testMutate10()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.orderType = "000";
        l_request.cashinoutStatus = "1";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("要求ページ番号が未指定です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate11()
    {
        final String STR_METHOD_NAME = "testMutate11()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.orderType = "000";
        l_request.cashinoutStatus = "1";
        l_request.pageIndex = "-1";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("要求ページ番号の値が0以下です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate12()
    {
        final String STR_METHOD_NAME = "testMutate12()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.orderType = "000";
        l_request.cashinoutStatus = "1";
        l_request.pageIndex = "q";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("要求ページ番号が数字以外の値です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate13()
    {
        final String STR_METHOD_NAME = "testMutate13()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.orderType = "000";
        l_request.cashinoutStatus = "1";
        l_request.pageIndex = "1";
        l_request.pageSize = null;

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("ページ内表示行数が未入力です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate14()
    {
        final String STR_METHOD_NAME = "testMutate14()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.orderType = "000";
        l_request.cashinoutStatus = "1";
        l_request.pageIndex = "1";
        l_request.pageSize = "-1";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("ページ内表示行数の値が0以下です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate15()
    {
        final String STR_METHOD_NAME = "testMutate15()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.orderType = "000";
        l_request.cashinoutStatus = "1";
        l_request.pageIndex = "1";
        l_request.pageSize = "q";

        try
        {
            l_request.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals("ページ内表示行数が数字以外の値です。", l_ex.getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidate16()
    {
        final String STR_METHOD_NAME = "testMutate16()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String[] l_strBranchCodes = new String[]{"123", "222"};
        l_request.branchCode = l_strBranchCodes;
        l_request.deliveryDate = new Date("2007/01/01");
        l_request.pageIndex = "1";
        l_request.pageSize = "1";
        String[] l_strOrderTypes =
            new String[]{
                "000", "100", "101", "102", "103", "104",
                "105", "106", "200", "201", "202", "203","204"};
        String[] l_strCashinoutStatus =
            new String[]{"0", "1", "2", "9"};
        try
        {
            for (int i = 0; i < l_strOrderTypes.length; i++)
            {
                l_request.orderType = l_strOrderTypes[i];
                for (int j = 0; j < l_strCashinoutStatus.length; j++)
                {
                    l_request.cashinoutStatus = l_strCashinoutStatus[j];
                    l_request.validate();
                }
            }
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
