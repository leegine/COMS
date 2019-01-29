head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoManualLapseConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効確認リクエスト (WEB3AdminIfoManualLapseConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効確認リクエスト )<BR>
 * 管理者・先物OP手動失効確認リクエスト クラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoManualLapseConfirmRequestTest extends TestBaseForMock
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualLapseConfirmRequestTest.class);

    public WEB3AdminIfoManualLapseConfirmRequestTest(String arg0)
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
            WEB3AdminIfoManualLapseConfirmRequest request = new WEB3AdminIfoManualLapseConfirmRequest();
            request.validate();
            
            log.debug(TEST_END + STR_METHOD_NAME);
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
    
    public void testvalidate_0002()
    {
        String STR_METHOD_NAME = "testvalidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try 
        {
            WEB3AdminIfoManualLapseConfirmRequest request = new WEB3AdminIfoManualLapseConfirmRequest();
            request.ifoLapseTargetOrderCondition = new WEB3AdminIfoLapseTargetOrderCondition();
            request.validate();
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
    
    public void testvalidate_0003()
    {
        String STR_METHOD_NAME = "testvalidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try 
        {
            WEB3AdminIfoManualLapseConfirmRequest request = new WEB3AdminIfoManualLapseConfirmRequest();
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
            request.ifoLapseTargetOrderCondition = condition;
            request.validate();
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
