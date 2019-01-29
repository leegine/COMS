head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPAddDepositMailSendServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.ExtMailProcRow;
import webbroker3.gentrade.data.MailInfoParams;
import webbroker3.gentrade.data.MailProcDao;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.gentrade.data.MailProcRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.message.WEB3TPAddDepositMailSendRequest;
import webbroker3.tradingpower.service.delegate.WEB3TPAddDepositMailSendService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPAddDepositMailSendServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPAddDepositMailSendServiceImplTest.class);


    public WEB3TPAddDepositMailSendServiceImplTest(String arg0)
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

    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第一水準追証情報>型
    public void testExcuteCase1()
    {
        final String STR_METHOD_NAME = " testExcuteCase1";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
//            WEB3DateUtility.getDate("20101010    ", "yyyyMMddHHmm");
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
            l_firstAdddepositInfo.firstDepositPassDay = 2;
            l_firstAdddepositInfo.firstMarginDepositRate = 0.25;
            l_firstAdddepositInfo.firstUncancelAmt = 100000;
            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt = 101000;
            l_firstAdddepositInfo.firstDepositPassDayValid = 3;
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("1");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setMailText("1");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailText(null);
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setMailText(null);
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            
            
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","1","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(4,l_lisResult.size());
            assertEquals("pass_day", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("0.25", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("100,000", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("settlement", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("101,000", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第一水準追証情報>型
    public void testExcuteCase001()
    {
        final String STR_METHOD_NAME = " testExcuteCase001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
//            WEB3DateUtility.getDate("20101010    ", "yyyyMMddHHmm");
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
            l_firstAdddepositInfo.firstDepositPassDay = 3;
            l_firstAdddepositInfo.firstMarginDepositRate = 0.25;
            l_firstAdddepositInfo.firstUncancelAmt = 100000;
            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt = 101000;
            l_firstAdddepositInfo.firstDepositPassDayValid = 3;
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("1");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setMailText("1");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailText(null);
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setMailText(null);
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","1","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(4,l_lisResult.size());
            assertEquals("pass_day", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("0.25", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("100,000", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("settlement", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("101,000", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第一水準追証情報>型
    public void testExcuteCase002()
    {
        final String STR_METHOD_NAME = " testExcuteCase002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
//            WEB3DateUtility.getDate("20101010    ", "yyyyMMddHHmm");
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(false);
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
            l_firstAdddepositInfo.firstDepositPassDay = 4;
            l_firstAdddepositInfo.firstMarginDepositRate = 0.25;
            l_firstAdddepositInfo.firstUncancelAmt = 100000;
            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt = 101000;
            l_firstAdddepositInfo.firstDepositPassDayValid = 3;
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("1");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setMailText("1");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailText(null);
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setMailText(null);
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","1","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(4,l_lisResult.size());
            assertEquals("pass_day", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("0.25", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("100,000", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("settlement", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("101,000", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第一水準追証情報>型
    public void testExcuteCase11()
    {
        final String STR_METHOD_NAME = " testExcuteCase11 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
//            WEB3DateUtility.getDate("20101010    ", "yyyyMMddHHmm");
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
            l_firstAdddepositInfo.firstDepositPassDay = 2;
            l_firstAdddepositInfo.firstMarginDepositRate = 0.25;
            l_firstAdddepositInfo.firstUncancelAmt = 100;
            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt = 101;
            l_firstAdddepositInfo.firstDepositPassDayValid = 3;
            l_firstAdddepositInfo.firstDepositOccurredDate = WEB3DateUtility.getDate("20110101", "yyyyMMdd");
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("1");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","1","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("01/01", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("pass_day", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("0.25", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("100", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());

            assertEquals("settlement", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("101", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase2()
    {
        final String STR_METHOD_NAME = " testExcuteCase2";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());

            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が3
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第一水準追証情報>型
    public void testExcuteCase111()
    {
        final String STR_METHOD_NAME = " testExcuteCase111";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
            l_firstAdddepositInfo.firstDepositPassDay = 2;
            l_firstAdddepositInfo.firstMarginDepositRate = 0.25;
            l_firstAdddepositInfo.firstUncancelAmt = 100;
            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt = 101;
            l_firstAdddepositInfo.firstDepositPassDayValid = 3;
            l_firstAdddepositInfo.firstDepositOccurredDate = WEB3DateUtility.getDate("20110101", "yyyyMMdd");
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("1");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("1");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            l_PaymentRequisitMngParams.setAccountId(333812512247L);
            l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040717","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            l_PaymentRequisitMngParams.setAccountId(333812512248L);
            l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040718","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            l_MainAccountParams.setAccountId(333812512247L);
            l_MainAccountParams.setAccountCode("2512246");
            l_MainAccountParams.setInstitutionId(1002);
            l_MainAccountParams.setInstitutionCode("1D");
//            TestDBUtility.insertWithDel(l_MainAccountParams);
            l_MainAccountParams.setAccountId(333812512248L);
            l_MainAccountParams.setAccountCode("2512246");
            l_MainAccountParams.setInstitutionId(1003);
            l_MainAccountParams.setInstitutionCode("2D");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow1 = MailProcDao.findRowByPk("0D","381","3201","1","2512246",1003L);
            MailProcRow l_MailProcRow2 = MailProcDao.findRowByPk("0D","381","3201","1","2512246",1004L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("01/01", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("pass_day", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("0.25", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("100", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            assertEquals("settlement", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("101", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());

            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    "item_name asc",
                    null,
                    new Object[]{"1004"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());

            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("0.25", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("01/01", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("pass_day", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("settlement", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("101", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("100", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が3
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase3()
    {
        final String STR_METHOD_NAME = " testExcuteCase3";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            l_PaymentRequisitMngParams.setAccountId(333812512247L);
            l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040717","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            l_PaymentRequisitMngParams.setAccountId(333812512248L);
            l_PaymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20040718","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            l_MainAccountParams.setAccountId(333812512247L);
            l_MainAccountParams.setAccountCode("2512246");
            l_MainAccountParams.setInstitutionId(1002);
            l_MainAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            l_MainAccountParams.setAccountId(333812512248L);
            l_MainAccountParams.setAccountCode("2512246");
            l_MainAccountParams.setInstitutionId(1003);
            l_MainAccountParams.setInstitutionCode("2D");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow1 = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            MailProcRow l_MailProcRow2 = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1004L);
            MailProcRow l_MailProcRow3 = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1005L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());

            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
            
            
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    "item_name asc",
                    null,
                    new Object[]{"1004"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());

            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());

            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
            
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1005"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase4()
    {
        final String STR_METHOD_NAME = " testExcuteCase4";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
//            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
            l_secondAdddepositInfo.secondUncancelAmt2 = 3;
            l_secondAdddepositInfo.secondUncancelAmt1 = 1;
            l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("20101231 10:30","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.getDate("20101230","yyyyMMdd");
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondMarginDepositRate2 = 0.13;
            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("12/30", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("発生時 0.13", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("88", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("有り", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase5()
    {
        final String STR_METHOD_NAME = " testExcuteCase5";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
//            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
            l_secondAdddepositInfo.secondUncancelAmt1 = 44;
            l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("20101231","yyyyMMdd");
            l_secondAdddepositInfo.secondCloseDate1 = WEB3DateUtility.getDate("20101129 16:11","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.getDate("20101230","yyyyMMdd");
            l_secondAdddepositInfo.secondDepositOccurredDate1 = WEB3DateUtility.getDate("20101130","yyyyMMdd");
            l_secondAdddepositInfo.secondMarginDepositRate2 = 0.13;
            l_secondAdddepositInfo.secondMarginDepositRate1 = 0.26;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1 = 30;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("11/30", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("11/29 16:11", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("発生時 0.26", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("44", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase6()
    {
        final String STR_METHOD_NAME = " testExcuteCase6";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
//            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
//            l_secondAdddepositInfo.secondUncancelAmt1 = 44;
            l_secondAdddepositInfo.secondUncancelAmtExpect = 4;
            l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("20101231","yyyyMMdd");
            l_secondAdddepositInfo.secondCloseDate1 = WEB3DateUtility.getDate("20101129 11:30","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondCloseDateExpect = WEB3DateUtility.getDate("20101029 11:30","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.getDate("20101230","yyyyMMdd");
            l_secondAdddepositInfo.secondDepositOccurredDate1 = WEB3DateUtility.getDate("20101130","yyyyMMdd");
            l_secondAdddepositInfo.secondDepositOccurredDateExpect = WEB3DateUtility.getDate("20101030","yyyyMMdd");
            l_secondAdddepositInfo.secondMarginDepositRate2 = 0.13;
            l_secondAdddepositInfo.secondMarginDepositRate1 = 0.26;
            l_secondAdddepositInfo.secondMarginDepositRateExpect = 0.39;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1 = 30;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect = 50;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("10/30", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("10/29 11:30", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("現在 0.39", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("4", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());

            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase7()
    {
        final String STR_METHOD_NAME = " testExcuteCase7";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
//            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
//            l_secondAdddepositInfo.secondUncancelAmt1 = 44;
            l_secondAdddepositInfo.secondUncancelAmtExpect = 5607912;
//            l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("20101231","yyyyMMdd");
//            l_secondAdddepositInfo.secondCloseDate1 = WEB3DateUtility.getDate("20101129","yyyyMMdd");
//            l_secondAdddepositInfo.secondCloseDateExpect = WEB3DateUtility.getDate("20101029","yyyyMMdd");
//            l_secondAdddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.getDate("20101230","yyyyMMdd");
//            l_secondAdddepositInfo.secondDepositOccurredDate1 = WEB3DateUtility.getDate("20101130","yyyyMMdd");
//            l_secondAdddepositInfo.secondDepositOccurredDateExpect = WEB3DateUtility.getDate("20101030","yyyyMMdd");
            l_secondAdddepositInfo.secondMarginDepositRate2 = 0.13;
            l_secondAdddepositInfo.secondMarginDepositRate1 = 0.26;
            l_secondAdddepositInfo.secondMarginDepositRateExpect = 25.49;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1 = 30;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect = 50;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(3,l_lisResult.size());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("現在 25.49", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("5,607,912", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase01()
    {
        final String STR_METHOD_NAME = " testExcuteCase01";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 3;
//            l_secondAdddepositInfo.secondUncancelAmt1 = 1;
            l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("20101231 10:30","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.getDate("20101230","yyyyMMdd");
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondMarginDepositRate2 = 0.13;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("-", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("2", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase02()
    {
        final String STR_METHOD_NAME = " testExcuteCase02";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
//            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
            l_secondAdddepositInfo.secondUncancelAmt2 = 3;
//            l_secondAdddepositInfo.secondUncancelAmt1 = 1;
            l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("20101231 10:30","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondDepositOccurredDate2 = WEB3DateUtility.getDate("20101230","yyyyMMdd");
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondMarginDepositRate2 = 0.13;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("12/30", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("発生時 0.13", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("3", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase03()
    {
        final String STR_METHOD_NAME = " testExcuteCase03";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(true);
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
//            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 3;
            l_secondAdddepositInfo.secondUncancelAmt1 = 1;
            l_secondAdddepositInfo.secondCloseDate2 = WEB3DateUtility.getDate("20101231 10:30","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondDepositOccurredDate1 = WEB3DateUtility.getDate("20101231","yyyyMMdd");
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondMarginDepositRate2 = 0.13;
            l_secondAdddepositInfo.secondMarginDepositRate1 = 0.23;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("12/31", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("期日超過", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("発生時 0.23", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("1", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase04()
    {
        final String STR_METHOD_NAME = " testExcuteCase04";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(false);
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
//            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 3;
            l_secondAdddepositInfo.secondUncancelAmt1 = 1;
            l_secondAdddepositInfo.secondCloseDate1 = WEB3DateUtility.getDate("20101231 10:30","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondDepositOccurredDate1 = WEB3DateUtility.getDate("20101231","yyyyMMdd");
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondMarginDepositRate1 = 0.13;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("12/31", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("12/31 10:30", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("発生時 0.13", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("1", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    //入金請求管理テーブルを下記条件で検索する
    //[条件] 
    //第一水準追証経過日数 > 0 or 第二水準追証未入金 > 0 or 
    //第二水準追証請求(1) > 0 or 第二水準追証請求(2) > 0
    //取得した入金請求管理テーブルの件数が1
    //get追証発生情報()の戻り値.get追証情報  instance of  入金請求管理<第二水準追証情報>型
    public void testExcuteCase05()
    {
        final String STR_METHOD_NAME = " testExcuteCase05";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(ExtMailProcRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();
            l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(false);
            WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
//            l_secondAdddepositInfo.secondUncancelAmtNonPay = 2;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 3;
//            l_secondAdddepositInfo.secondUncancelAmt1 = 1;
            l_secondAdddepositInfo.secondUncancelAmtExpect = 3;
            l_secondAdddepositInfo.secondCloseDateExpect = WEB3DateUtility.getDate("20101231 10:30","yyyyMMdd HH:mm");
            l_secondAdddepositInfo.secondDepositOccurredDateExpect = WEB3DateUtility.getDate("20101230","yyyyMMdd");
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay = 5;
            l_secondAdddepositInfo.secondMarginDepositRateExpect = 0.13;
//            l_secondAdddepositInfo.secondUncancelAmt2 = 88;
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 = 60;
            l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl",
                    "getAdddepositGenerationInfo",
                    new Class[] { MainAccount.class },
                    l_adddepositGenerationInfo);
            
            TestDBUtility.deleteAll(MailInfoParams.TYPE);
            MailInfoParams l_MailInfoParams = TestDBUtility.getMailInfoRow();
            l_MailInfoParams.setSendAddress("3201");
            l_MailInfoParams.setDiscernmentId("2");
            TestDBUtility.insertWithDel(l_MailInfoParams);
            
            TestDBUtility.deleteAll(MailProcParams.TYPE);
            MailProcParams l_MailProcParams = TestDBUtility.getMailProcRow();
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1000);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1001);
            TestDBUtility.insertWithDel(l_MailProcParams);
            l_MailProcParams.setInstitutionCode("0D");
            l_MailProcParams.setBranchCode("381");
            l_MailProcParams.setAccountCode("2512246");
            l_MailProcParams.setSendmailDiv("3201");
            l_MailProcParams.setDiscernmentId("2");
            l_MailProcParams.setMailId(1002);
            TestDBUtility.insertWithDel(l_MailProcParams);

            TestDBUtility.deleteAll(PaymentRequisitMngParams.TYPE);
            PaymentRequisitMngParams l_PaymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_PaymentRequisitMngParams.setInstitutionCode("0D");
            l_PaymentRequisitMngParams.setBranchCode("381");
            l_PaymentRequisitMngParams.setAccountCode("2512246");
            l_PaymentRequisitMngParams.setAccountId(333812512246L);
            l_PaymentRequisitMngParams.setFirstDepositPassDay(1);
            l_PaymentRequisitMngParams.setSecondDepositNonPay(1);
            l_PaymentRequisitMngParams.setSecondDeposit1(1);
            l_PaymentRequisitMngParams.setSecondDeposit2(1);
            
            TestDBUtility.insertWithDel(l_PaymentRequisitMngParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512246L);
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.commit();
            WEB3TPAddDepositMailSendRequest l_request = new WEB3TPAddDepositMailSendRequest();
            WEB3TPAddDepositMailSendService l_service =
                (WEB3TPAddDepositMailSendService)Services.getService(WEB3TPAddDepositMailSendService.class);
            l_service.execute(l_request);
            
            
            MailProcRow l_MailProcRow = MailProcDao.findRowByPk("0D","381","3201","2","2512246",1003L);
            
            List l_lisResult = new ArrayList();
            QueryProcessor l_qp = null;
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_lisResult = l_qp.doFindAllQuery(
                    ExtMailProcRow.TYPE,
                    "mail_id = ? ",
                    new Object[]{"1003"});
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            assertEquals(5,l_lisResult.size());
            assertEquals("occurred_date", ((ExtMailProcRow)l_lisResult.get(0)).getItemName());
            assertEquals("12/30", ((ExtMailProcRow)l_lisResult.get(0)).getItemContents());
            
            assertEquals("close_date", ((ExtMailProcRow)l_lisResult.get(1)).getItemName());
            assertEquals("12/31 10:30", ((ExtMailProcRow)l_lisResult.get(1)).getItemContents());
            
            assertEquals("deposit_rate", ((ExtMailProcRow)l_lisResult.get(2)).getItemName());
            assertEquals("現在 0.13", ((ExtMailProcRow)l_lisResult.get(2)).getItemContents());
            
            assertEquals("uncancel_amt", ((ExtMailProcRow)l_lisResult.get(3)).getItemName());
            assertEquals("3", ((ExtMailProcRow)l_lisResult.get(3)).getItemContents());
            
            assertEquals("diff_date_div", ((ExtMailProcRow)l_lisResult.get(4)).getItemName());
            assertEquals("無し", ((ExtMailProcRow)l_lisResult.get(4)).getItemContents());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
