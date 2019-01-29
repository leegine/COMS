head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.15.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoLapseTargetOrderConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効対象注文条件(WEB3AdminIfoLapseTargetOrderCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP失効対象注文条件)<BR>
 * 先物OP失効対象注文条件<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoLapseTargetOrderConditionTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoLapseTargetOrderConditionTest.class);

    public WEB3AdminIfoLapseTargetOrderConditionTest(String arg0)
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
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            condition.branchCode = null;

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2174" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードがnullです。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
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
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"abc"};
            condition.branchCode = l_strBranchCode;

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("779" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
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
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"12"};
            condition.branchCode = l_strBranchCode;

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("779" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("部店コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
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
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = "あああ";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("1737" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("先物／オプション区分の値が存在しないコード値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
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
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = "1";
            condition.targetProductCode = "1234";
            condition.delivaryMonth = "200606";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0006()
    {
        String STR_METHOD_NAME = "testvalidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = "2";
            condition.targetProductCode = "1234";
            condition.delivaryMonth = "200606";
            condition.strikePrice = "123";
            condition.opProductType = "P";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0007()
    {
        String STR_METHOD_NAME = "testvalidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "abc";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2441" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("指数種別が数字以外の値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0008()
    {
        String STR_METHOD_NAME = "testvalidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "123";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2442" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("指数種別のサイズが不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0009()
    {
        String STR_METHOD_NAME = "testvalidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "12345";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2442" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("指数種別のサイズが不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0010()
    {
        String STR_METHOD_NAME = "testvalidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "1234";
            condition.delivaryMonth = "abc";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2351" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("限月が数字以外の値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0011()
    {
        String STR_METHOD_NAME = "testvalidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "1234";
            condition.delivaryMonth = "20060606";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("268" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("限月がＹＹＹＹＭＭ形式で入力してください。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0012()
    {
        String STR_METHOD_NAME = "testvalidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "1234";
            condition.delivaryMonth = "200606";
            condition.strikePrice = "abc";

            condition.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("272" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("行使価格が数字以外の値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testvalidate_0013()
    {
        String STR_METHOD_NAME = "testvalidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "1234";
            condition.delivaryMonth = "200606";
            condition.strikePrice = "-100";
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("273" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("行使価格が0以下の値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0014()
    {
        String STR_METHOD_NAME = "testvalidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "1234";
            condition.delivaryMonth = "200606";
            condition.strikePrice = "123456789";
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("274" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("行使価格のサイズが不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0015()
    {
        String STR_METHOD_NAME = "testvalidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = "1234";
            condition.delivaryMonth = "200606";
            condition.strikePrice = "12345678";
            condition.opProductType = "あああ";
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("270" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("オプション商品区分の値が存在しないコード値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0016()
    {
        String STR_METHOD_NAME = "testvalidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = "1";
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("334" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("銘柄指定エラー。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0017()
    {
        String STR_METHOD_NAME = "testvalidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = "2";
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("334" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("銘柄指定エラー。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0018()
    {
        String STR_METHOD_NAME = "testvalidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            condition.tradingTypeList = null;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = null;
            condition.delivaryMonth = null;
            condition.strikePrice = null;
            condition.opProductType = null;
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("601" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("取引区分が未指定です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0019()
    {
        String STR_METHOD_NAME = "testvalidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"あああ"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = null;
            condition.delivaryMonth = null;
            condition.strikePrice = null;
            condition.opProductType = null;
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("602" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("取引区分が存在しないコード値です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0020()
    {
        String STR_METHOD_NAME = "testvalidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601","602","603","604","605","606","607","608"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = null;
            condition.delivaryMonth = null;
            condition.strikePrice = null;
            condition.opProductType = null;
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0021()
    {
        String STR_METHOD_NAME = "testvalidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = null;
            condition.delivaryMonth = null;
            condition.strikePrice = null;
            condition.opProductType = null;
            condition.accountCode = "abc";
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("780" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("顧客コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0022()
    {
        String STR_METHOD_NAME = "testvalidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = null;
            condition.delivaryMonth = null;
            condition.strikePrice = null;
            condition.opProductType = null;
            condition.accountCode = "1234567";
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex) 
        {
            assertEquals("780" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("顧客コードの入力が不正です。" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testvalidate_0023()
    {
        String STR_METHOD_NAME = "testvalidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminIfoLapseTargetOrderCondition condition = new WEB3AdminIfoLapseTargetOrderCondition();
            String[] l_strTradingTypeList = {"601"};
            condition.tradingTypeList = l_strTradingTypeList;
            String[] l_strBranchCode = {"123"};
            condition.branchCode = l_strBranchCode;
            condition.fuOpDiv = null;
            condition.targetProductCode = null;
            condition.delivaryMonth = null;
            condition.strikePrice = null;
            condition.opProductType = null;
            condition.accountCode = "123456";
            
            condition.validate();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
