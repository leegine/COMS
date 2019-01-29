head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualLapseMainRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効メインリクエスト(WEB3AdminIfoManualLapseMainRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効メインリクエスト)<BR>
 * 管理者・先物OP手動失効メインリクエストクラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoManualLapseMainRequestTest extends TestBaseForMock
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualLapseMainRequestTest.class);
    
    public WEB3AdminIfoManualLapseMainRequestTest(String arg0)
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
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.validate();
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("827" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("証券会社コードが未指定です。" , l_ex.getErrorInfo().getErrorMessage());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
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
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.institutionCode = "1234567";
        request.validate();
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("1974" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("スレッド番号の指定なし。" , l_ex.getErrorInfo().getErrorMessage());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
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
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.institutionCode = "1234567";
        request.threadNo = new Long(1234657L);
        request.validate();
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2421" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("From口座IDが未入力です。" , l_ex.getErrorInfo().getErrorMessage());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
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
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.institutionCode = "1234567";
        request.threadNo = new Long(1234657L);
        request.accountIdFrom = new Long(1234567L);
        request.validate();
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2422" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("To口座ID（至）が未入力です。" , l_ex.getErrorInfo().getErrorMessage());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
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
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.institutionCode = "1234567";
        request.threadNo = new Long(1234657L);
        request.accountIdFrom = new Long(1234567L);
        request.accountIdTo = new Long(1234567L);
        request.validate();
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("2420" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("失効対象注文条件が未入力です。" , l_ex.getErrorInfo().getErrorMessage());
            
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
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.institutionCode = "1234567";
        request.threadNo = new Long(1234657L);
        request.accountIdFrom = new Long(1234567L);
        request.accountIdTo = new Long(1234567L);
        request.ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
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
            condition.targetProductCode = null;
            condition.delivaryMonth = null;
            condition.strikePrice = null;
            condition.opProductType = null;
            condition.accountCode = "123456";
            WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
            request.institutionCode = "1234567";
            request.threadNo = new Long(1234657L);
            request.accountIdFrom = new Long(1234567L);
            request.accountIdTo = new Long(1234567L);
            request.ifoLapseTargetOrderCondition = condition;
            request.validate();
        
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
