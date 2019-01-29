head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLCashOutStopReleaseServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAioSLCashOutStopReleaseServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 金傑（中訊）新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListRequest;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListResponse;
import webbroker3.aio.message.WEB3AioCashinSettleCompleteRequest;
import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.SecurityCashoutRestraintDao;
import webbroker3.gentrade.data.SecurityCashoutRestraintParams;
import webbroker3.gentrade.data.SecurityCashoutRestraintRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLCashOutStopReleaseServiceImplTest extends TestBaseForMock 
{

    
    private WEB3AdminAioSLCashOutStopReleaseServiceImpl l_serviceImpl = null;
    
    private WEB3AdminSLCashOutStopReleaseConfirmRequest l_confirmRequest = null;
    
    private WEB3AdminSLCashOutStopReleaseCompleteRequest l_completeRequest = null;
    
    private  SecurityCashoutRestraintRow l_securityCashoutRestraintRow = null;
    
    private WEB3AdminSLRestraintMoneyListRequest l_moneyListRequest = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAioSLCashOutStopReleaseServiceImplTest.class);
    
    
    public WEB3AdminAioSLCashOutStopReleaseServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.initData();
        this.getMockData();
        this.l_serviceImpl = new WEB3AdminAioSLCashOutStopReleaseServiceImpl();
        this.l_confirmRequest = new WEB3AdminSLCashOutStopReleaseConfirmRequest();
        this.l_completeRequest = new WEB3AdminSLCashOutStopReleaseCompleteRequest();
        this.l_moneyListRequest = new WEB3AdminSLRestraintMoneyListRequestForTest();
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_serviceImpl = null;
        this.l_confirmRequest = null;
        this.l_completeRequest = null;
        this.l_securityCashoutRestraintRow = null;
    }
    
    /**
     * パラメータ値不正の場合
     * 抛出：SYSTEM_ERROR_80017異常信息
     *
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_serviceImpl.execute(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * パラメータタイプ不正の場合
     * 抛出：SYSTEM_ERROR_80018異常信息
     *
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AioCashinSettleCompleteRequest l_request = new WEB3AioCashinSettleCompleteRequest();
            this.l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金停止解除確認リクエストの場合,get出金拘束金解除確認画面()をコールする
     * 「get担保ローン出金拘束金テーブル」沒有取到的場合
     * 
     * 抛出：BUSINESS_ERROR_01037異常信息
     *
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminSLCashOutStopReleaseConfirmResponse l_confirmResponse =
                (WEB3AdminSLCashOutStopReleaseConfirmResponse)this.l_serviceImpl.execute(this.l_confirmRequest);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 証券担保ローン出金停止解除確認リクエストの場合,get出金拘束金解除確認画面()をコールする
     * 「get出金拘束金解除確認画面」正常結束
     * 
     *
     *
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_confirmRequest.accountId = 333812512246L;
            WEB3AdminSLCashOutStopReleaseConfirmResponse l_confirmResponse =
                (WEB3AdminSLCashOutStopReleaseConfirmResponse)this.l_serviceImpl.execute(this.l_confirmRequest);
            
            assertNotNull(l_confirmResponse);
            assertNotNull(l_confirmResponse.cashOutStopInfo);
            assertEquals(333812512246L,l_confirmResponse.cashOutStopInfo.accountId);
            assertEquals("378",l_confirmResponse.cashOutStopInfo.branchCode);
            assertEquals("1234566",l_confirmResponse.cashOutStopInfo.accountCode);
            assertEquals("内藤　@四郎",l_confirmResponse.cashOutStopInfo.accountName);
            assertEquals("560060",l_confirmResponse.cashOutStopInfo.cashoutLimit);
            assertEquals("10000",l_confirmResponse.cashOutStopInfo.cashoutRestraint);
            assertEquals("400000",l_confirmResponse.cashOutStopInfo.cashoutPossAmt);
            assertEquals("1",l_confirmResponse.cashOutStopInfo.cashoutStopDiv);
            

            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金停止解除完了リクエストの場合 this.get出金拘束金解除完了画面()をコールする
     * 「get担保ローン出金拘束金テーブル」沒有取到的場合
     * 抛出：BUSINESS_ERROR_01037異常信息
     *
     *
     */
    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_completeRequest.password = "123456";
            WEB3AdminSLCashOutStopReleaseCompleteResponse l_completeResponse =
                (WEB3AdminSLCashOutStopReleaseCompleteResponse)this.l_serviceImpl.execute(this.l_completeRequest);
            
           fail();
  
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金停止解除完了リクエストの場合 this.get出金拘束金解除完了画面()をコールする
     * 「update担保ローン出金拘束金テーブル」如@下字段：
     *  金額ロックフラグ：　@0 (通常)
     *  更新者コード：　@  (引数)管理者コード　@[管理者.get管理者コード()の戻り値]
     *  更新日時：　@      (引数)現在時刻　@[TradingSystem.getSystemTimestamp()の戻り値]
     * 
     *
     *
     */
    public void testExecute_C0006()
    {
        final String STR_METHOD_NAME = "testExecute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_completeRequest.accountId = 333812512246L;
            WEB3AdminSLCashOutStopReleaseCompleteResponse l_completeResponse =
                (WEB3AdminSLCashOutStopReleaseCompleteResponse)this.l_serviceImpl.execute(this.l_completeRequest);
  
            this.getSearchData(333812512246L);
            assertEquals("0",this.l_securityCashoutRestraintRow.getAmountLockFlg());
            assertEquals("330001",this.l_securityCashoutRestraintRow.getLastUpdater());
            assertEquals(0,WEB3DateUtility.compareToDay(
                GtlUtils.getTradingSystem().getSystemTimestamp(),
                this.l_securityCashoutRestraintRow.getLastUpdatedTimestamp()));
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == null　@の場合
     * (引数)顧客コード == null　@の場合
     * (引数)出金停止区分 ==null　@の場合
     * (引数)ページ内表示行数 =4
     * (引数)要求ページ番号 =3 
     * 部店コード(昇順)
     * 總共檢索出符合條件的8條數據，毎頁只顯示4條數據，所以只有2頁，如@果輸入顯示第3頁，則檢索不到數據
     *
     * 修改為
     * 検索対象レコードが存在しない場合、例外をスローする
     */
    public void testExecute_C0007()
    {
        final String STR_METHOD_NAME = "testExecute_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[1];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "3";
            this.l_moneyListRequest.pageSize = "4";
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            fail();
//            assertNull(l_moneyListResponse.totalPages);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00398);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == null　@の場合
     * (引数)顧客コード == null　@の場合
     * (引数)出金停止区分 ==null　@の場合
     * 部店コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0008()
    {
        final String STR_METHOD_NAME = "testExecute_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[1];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("3",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("8",l_moneyListResponse.totalRecords);
            
            assertEquals("378",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234566",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("379",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234564",l_moneyListResponse.cashOutStopInfoList[1].accountCode);
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[2].branchCode);
            assertEquals("1234561",l_moneyListResponse.cashOutStopInfoList[2].accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == null　@の場合
     * (引数)出金停止区分 ==null　@の場合
     * 部店コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第2頁的數據
     *
     */
    public void testExecute_C0009()
    {
        final String STR_METHOD_NAME = "testExecute_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[1];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "2";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("3",l_moneyListResponse.totalPages);
            assertEquals("2",l_moneyListResponse.pageIndex);
            assertEquals("8",l_moneyListResponse.totalRecords);
            
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234562",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("382",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234563",l_moneyListResponse.cashOutStopInfoList[1].accountCode);
            assertEquals("385",l_moneyListResponse.cashOutStopInfoList[2].branchCode);
            assertEquals("1234565",l_moneyListResponse.cashOutStopInfoList[2].accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == null　@の場合
     * (引数)顧客コード == null　@の場合
     * (引数)出金停止区分 ==null　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第3頁的數據
     *
     */
    public void testExecute_C0010()
    {
        final String STR_METHOD_NAME = "testExecute_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[1];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "accountCode";
            l_sortKeys[0].ascDesc = "A";
            

            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "3";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("3",l_moneyListResponse.totalPages);
            assertEquals("3",l_moneyListResponse.pageIndex);
            assertEquals("8",l_moneyListResponse.totalRecords);
            

            assertEquals("388",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234567",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("388",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234568",l_moneyListResponse.cashOutStopInfoList[1].accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == null　@の場合
     * (引数)顧客コード == null　@の場合
     * (引数)出金停止区分 ==null　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第3頁的數據
     *
     */
    public void testExecute_C0011()
    {
        final String STR_METHOD_NAME = "testExecute_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams.setAccountId(333812512249L);
            l_securityCashoutRestraintParams.setInstitutionCode("0D");
            l_securityCashoutRestraintParams.setBranchCode("388");
            l_securityCashoutRestraintParams.setAccountCode("1234569");
            l_securityCashoutRestraintParams.setUseEnableLimit(560090L);
            l_securityCashoutRestraintParams.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams.setAmountLockFlg("1");
            l_securityCashoutRestraintParams.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams);
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            

            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "3";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("3",l_moneyListResponse.totalPages);
            assertEquals("3",l_moneyListResponse.pageIndex);
            assertEquals("9",l_moneyListResponse.totalRecords);
            

            assertEquals("388",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234567",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("388",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234568",l_moneyListResponse.cashOutStopInfoList[1].accountCode);
            assertEquals("388",l_moneyListResponse.cashOutStopInfoList[2].branchCode);
            assertEquals("1234569",l_moneyListResponse.cashOutStopInfoList[2].accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == 385　@の場合
     * (引数)顧客コード == null　@の場合
     * (引数)出金停止区分 ==null　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0012()
    {
        final String STR_METHOD_NAME = "testExecute_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams.setAccountId(333812512249L);
            l_securityCashoutRestraintParams.setInstitutionCode("0D");
            l_securityCashoutRestraintParams.setBranchCode("385");
            l_securityCashoutRestraintParams.setAccountCode("1234569");
            l_securityCashoutRestraintParams.setUseEnableLimit(560090L);
            l_securityCashoutRestraintParams.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams.setAmountLockFlg("1");
            l_securityCashoutRestraintParams.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams);
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            

            this.l_moneyListRequest.branchCode = "385";
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("1",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("2",l_moneyListResponse.totalRecords);
            

            assertEquals("385",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234565",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("385",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234569",l_moneyListResponse.cashOutStopInfoList[1].accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == null　@の場合
     * (引数)顧客コード == 123456　@の場合
     * (引数)出金停止区分 ==null　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0013()
    {
        final String STR_METHOD_NAME = "testExecute_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            

            this.l_moneyListRequest.accountCode = "123456";
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("3",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("8",l_moneyListResponse.totalRecords);
            

            assertEquals("378",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234566",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("379",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234564",l_moneyListResponse.cashOutStopInfoList[1].accountCode);
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[2].branchCode);
            assertEquals("1234561",l_moneyListResponse.cashOutStopInfoList[2].accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == null　@の場合
     * (引数)顧客コード == null　@の場合
     * (引数)出金停止区分 ==1　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0014()
    {
        final String STR_METHOD_NAME = "testExecute_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            

            this.l_moneyListRequest.cashOutStopDiv = "1";
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("3",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("7",l_moneyListResponse.totalRecords);
            

            assertEquals("378",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234566",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("379",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234564",l_moneyListResponse.cashOutStopInfoList[1].accountCode);
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[2].branchCode);
            assertEquals("1234561",l_moneyListResponse.cashOutStopInfoList[2].accountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == 381　@の場合
     * (引数)顧客コード == 123456　@の場合
     * (引数)出金停止区分 ==null　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0015()
    {
        final String STR_METHOD_NAME = "testExecute_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            

            this.l_moneyListRequest.branchCode = "381";
            this.l_moneyListRequest.accountCode = "123456";
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("1",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("2",l_moneyListResponse.totalRecords);
            

            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234561",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234562",l_moneyListResponse.cashOutStopInfoList[1].accountCode);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == 381　@の場合
     * (引数)顧客コード == null　@の場合
     * (引数)出金停止区分 ==1　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0016()
    {
        final String STR_METHOD_NAME = "testExecute_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            

            this.l_moneyListRequest.branchCode = "381";
            this.l_moneyListRequest.cashOutStopDiv = "1";
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("1",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("2",l_moneyListResponse.totalRecords);
            

            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234561",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234562",l_moneyListResponse.cashOutStopInfoList[1].accountCode);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == null　@の場合
     * (引数)顧客コード == 123456　@の場合
     * (引数)出金停止区分 ==1　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0017()
    {
        final String STR_METHOD_NAME = "testExecute_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            

            this.l_moneyListRequest.accountCode = "123456";
            this.l_moneyListRequest.cashOutStopDiv = "1";
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("3",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("7",l_moneyListResponse.totalRecords);
            

            assertEquals("378",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234566",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("379",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234564",l_moneyListResponse.cashOutStopInfoList[1].accountCode);
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[2].branchCode);
            assertEquals("1234561",l_moneyListResponse.cashOutStopInfoList[2].accountCode);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == 381　@の場合
     * (引数)顧客コード == 123456　@の場合
     * (引数)出金停止区分 ==1　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(昇順)」升序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0018()
    {
        final String STR_METHOD_NAME = "testExecute_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            

            this.l_moneyListRequest.branchCode = "381";
            this.l_moneyListRequest.accountCode = "123456";
            this.l_moneyListRequest.cashOutStopDiv = "1";
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("1",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("2",l_moneyListResponse.totalRecords);
            

            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234561",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234562",l_moneyListResponse.cashOutStopInfoList[1].accountCode);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 証券担保ローン出金拘束金一覧リクエストの場合,this.get担保ローン出金拘束金一覧画面()をコールする
     * 証券会社コード = "0D"
     * (引数)部店コード == 381　@の場合
     * (引数)顧客コード == 123456　@の場合
     * (引数)出金停止区分 ==1　@の場合
     * 顧客コード(昇順)
     * 檢索所有以“証券会社コード = 0D”開始的數據，並按「部店コード(昇順)」升序和「顧客コード(降順)」降序,毎頁
     * 顯示3條數據，顯示第1頁的數據
     *
     */
    public void testExecute_C0019()
    {
        final String STR_METHOD_NAME = "testExecute_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            
            WEB3SLSortKey[] l_sortKeys = new WEB3SLSortKey[2];
            l_sortKeys[0] = new WEB3SLSortKey();
            l_sortKeys[0].keyItem = "branchCode";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3SLSortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "D";
            

            this.l_moneyListRequest.branchCode = "381";
            this.l_moneyListRequest.accountCode = "123456";
            this.l_moneyListRequest.cashOutStopDiv = "1";
            this.l_moneyListRequest.sortKeys = l_sortKeys;
            this.l_moneyListRequest.pageIndex = "1";
            this.l_moneyListRequest.pageSize = "3";
          
            WEB3AdminSLRestraintMoneyListResponse l_moneyListResponse =
                (WEB3AdminSLRestraintMoneyListResponse)this.l_serviceImpl.execute(this.l_moneyListRequest);
            
            assertEquals("1",l_moneyListResponse.totalPages);
            assertEquals("1",l_moneyListResponse.pageIndex);
            assertEquals("2",l_moneyListResponse.totalRecords);
            

            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[0].branchCode);
            assertEquals("1234562",l_moneyListResponse.cashOutStopInfoList[0].accountCode);
            assertEquals("381",l_moneyListResponse.cashOutStopInfoList[1].branchCode);
            assertEquals("1234561",l_moneyListResponse.cashOutStopInfoList[1].accountCode);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            WEB3Administrator l_admin = new WEB3Administrator(TestDBUtility.getAdministratorRow());
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_admin);
            WEB3AdministratorForMock.mockValidateAuthority(l_admin,"B0603",true,true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123456",true);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams1 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams1.setAccountId(333812512241L);
            l_securityCashoutRestraintParams1.setInstitutionCode("0D");
            l_securityCashoutRestraintParams1.setBranchCode("381");
            l_securityCashoutRestraintParams1.setAccountCode("1234561");
            l_securityCashoutRestraintParams1.setUseEnableLimit(560010L);
            l_securityCashoutRestraintParams1.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams1.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams1.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams1.setAmountLockFlg("1");
            l_securityCashoutRestraintParams1.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams1);
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams2 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams2.setAccountId(333812512242L);
            l_securityCashoutRestraintParams2.setInstitutionCode("0D");
            l_securityCashoutRestraintParams2.setBranchCode("381");
            l_securityCashoutRestraintParams2.setAccountCode("1234562");
            l_securityCashoutRestraintParams2.setUseEnableLimit(560020L);
            l_securityCashoutRestraintParams2.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams2.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams2.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams2.setAmountLockFlg("1");
            l_securityCashoutRestraintParams2.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams2);
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams3 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams3.setAccountId(333812512243L);
            l_securityCashoutRestraintParams3.setInstitutionCode("0D");
            l_securityCashoutRestraintParams3.setBranchCode("382");
            l_securityCashoutRestraintParams3.setAccountCode("1234563");
            l_securityCashoutRestraintParams3.setUseEnableLimit(560030L);
            l_securityCashoutRestraintParams3.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams3.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams3.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams3.setAmountLockFlg("1");
            l_securityCashoutRestraintParams3.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams3);
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams4 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams4.setAccountId(333812512244L);
            l_securityCashoutRestraintParams4.setInstitutionCode("0D");
            l_securityCashoutRestraintParams4.setBranchCode("379");
            l_securityCashoutRestraintParams4.setAccountCode("1234564");
            l_securityCashoutRestraintParams4.setUseEnableLimit(560040L);
            l_securityCashoutRestraintParams4.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams4.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams4.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams4.setAmountLockFlg("1");
            l_securityCashoutRestraintParams4.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams4);
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams5 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams5.setAccountId(333812512245L);
            l_securityCashoutRestraintParams5.setInstitutionCode("0D");
            l_securityCashoutRestraintParams5.setBranchCode("385");
            l_securityCashoutRestraintParams5.setAccountCode("1234565");
            l_securityCashoutRestraintParams5.setUseEnableLimit(560050L);
            l_securityCashoutRestraintParams5.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams5.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams5.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams5.setAmountLockFlg("1");
            l_securityCashoutRestraintParams5.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams5);
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams6 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams6.setAccountId(333812512246L);
            l_securityCashoutRestraintParams6.setInstitutionCode("0D");
            l_securityCashoutRestraintParams6.setBranchCode("378");
            l_securityCashoutRestraintParams6.setAccountCode("1234566");
            l_securityCashoutRestraintParams6.setUseEnableLimit(560060L);
            l_securityCashoutRestraintParams6.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams6.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams6.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams6.setAmountLockFlg("1");
            l_securityCashoutRestraintParams6.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams6);
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams7 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams7.setAccountId(333812512247L);
            l_securityCashoutRestraintParams7.setInstitutionCode("0D");
            l_securityCashoutRestraintParams7.setBranchCode("388");
            l_securityCashoutRestraintParams7.setAccountCode("1234567");
            l_securityCashoutRestraintParams7.setUseEnableLimit(560070L);
            l_securityCashoutRestraintParams7.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams7.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams7.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams7.setAmountLockFlg("1");
            l_securityCashoutRestraintParams7.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams7);
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams8 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams8.setAccountId(333812512248L);
            l_securityCashoutRestraintParams8.setInstitutionCode("0D");
            l_securityCashoutRestraintParams8.setBranchCode("388");
            l_securityCashoutRestraintParams8.setAccountCode("1234568");
            l_securityCashoutRestraintParams8.setUseEnableLimit(560080L);
            l_securityCashoutRestraintParams8.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams8.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams8.setAgreeCancelFlg("0");
            // 金額ロックフラグ = 0
            l_securityCashoutRestraintParams8.setAmountLockFlg("0");
            l_securityCashoutRestraintParams8.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams8);
            
            SecurityCashoutRestraintParams l_securityCashoutRestraintParams9 = new SecurityCashoutRestraintParams();
            l_securityCashoutRestraintParams9.setAccountId(333812512249L);
            l_securityCashoutRestraintParams9.setInstitutionCode("1D");
            l_securityCashoutRestraintParams9.setBranchCode("388");
            l_securityCashoutRestraintParams9.setAccountCode("1234568");
            l_securityCashoutRestraintParams9.setUseEnableLimit(560080L);
            l_securityCashoutRestraintParams9.setCashoutRestraint(10000L);
            l_securityCashoutRestraintParams9.setCashoutEnablieAmount(400000L);
            l_securityCashoutRestraintParams9.setAgreeCancelFlg("0");
            l_securityCashoutRestraintParams9.setAmountLockFlg("1");
            l_securityCashoutRestraintParams9.setLastUpdater("330001");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintParams9);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void getMockData()
    {
        final String STR_METHOD_NAME = "getMockData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class},
                l_mainAccount);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void getSearchData(long l_lngAccountId)
    {
        final String STR_METHOD_NAME = "getSearchData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            this.l_securityCashoutRestraintRow = SecurityCashoutRestraintDao.findRowByPk(l_lngAccountId);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3AdminSLRestraintMoneyListRequestForTest extends WEB3AdminSLRestraintMoneyListRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }

}
@
