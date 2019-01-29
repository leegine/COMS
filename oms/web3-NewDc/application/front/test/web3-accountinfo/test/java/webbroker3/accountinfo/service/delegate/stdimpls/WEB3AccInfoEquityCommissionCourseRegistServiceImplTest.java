head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AccInfoEquityCommissionCourseRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報株式委託手数料コース変更申込サービスImpl(WEB3AccInfoEquityCommissionCourseRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/30 謝旋（中訊）　@仕様変更・モデル193
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegistForMock;
import webbroker3.accountinfo.data.CommissionCourseMasterParams;
import webbroker3.accountinfo.data.CommissionCourseMasterRow;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccInfoCommissionDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CommCodeChgMstParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoEquityCommissionCourseRegistServiceImplTest extends TestBaseForMock
{
    public static String l_strFlag = null;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseRegistServiceImplTest.class);

    public WEB3AccInfoEquityCommissionCourseRegistServiceImplTest(String arg0)
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
    
    public void testGetCommissionNo_0001()
    {
        String STR_METHOD_NAME = "testGetCommissionNo_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        CommissionCourseRegistParams l_commissionCourseRegistParams = new CommissionCourseRegistParams();
        l_commissionCourseRegistParams.setCommissionCourseRegistId(123456789L);
        l_commissionCourseRegistParams.setInstitutionCode("0D");
        l_commissionCourseRegistParams.setBranchId(33381L);
        l_commissionCourseRegistParams.setAccountId(333812512203L);
        l_commissionCourseRegistParams.setCommProductCode("12");
        l_commissionCourseRegistParams.setAppliStartDatetime(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setAppliEndDatetime(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setCommissionCourseDiv("12");
        l_commissionCourseRegistParams.setRegistTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setRegistEndTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setDownloadFlag(BooleanEnum.TRUE);
        l_commissionCourseRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_commissionCourseRegistParams.setLastUpdater("abcd");
        l_commissionCourseRegistParams.setCreatedTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        
        CommCodeChgMstParams l_commCodeChgMstParams = new CommCodeChgMstParams();
        try
        {
            TestDBUtility.deleteAll(l_commissionCourseRegistParams.getRowType());
            TestDBUtility.insertWithDel(l_commissionCourseRegistParams);
            
            TestDBUtility.deleteAll(l_commCodeChgMstParams.getRowType());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist = 
                new WEB3AccInfoCommissionCourseRegist(l_commissionCourseRegistParams);
            WEB3AccInfoEquityCommissionCourseRegistServiceImpl l_web3AccInfoEquityCommissionCourseRegistServiceImpl = 
                new WEB3AccInfoEquityCommissionCourseRegistServiceImpl();
            l_web3AccInfoEquityCommissionCourseRegistServiceImpl.getCommissionNo(l_commissionCourseRegist);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398 , l_ex.getErrorInfo());
            
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testGetCommissionNo_0002()
    {
        String STR_METHOD_NAME = "testGetCommissionNo_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        CommissionCourseRegistParams l_commissionCourseRegistParams = new CommissionCourseRegistParams();
        l_commissionCourseRegistParams.setCommissionCourseRegistId(123456789L);
        l_commissionCourseRegistParams.setInstitutionCode("0D");
        l_commissionCourseRegistParams.setBranchId(33381L);
        l_commissionCourseRegistParams.setAccountId(333812512203L);
        l_commissionCourseRegistParams.setCommProductCode("12");
        l_commissionCourseRegistParams.setAppliStartDatetime(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setAppliEndDatetime(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setCommissionCourseDiv("12");
        l_commissionCourseRegistParams.setRegistTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setRegistEndTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setDownloadFlag(BooleanEnum.TRUE);
        l_commissionCourseRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_commissionCourseRegistParams.setLastUpdater("abcd");
        l_commissionCourseRegistParams.setCreatedTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        
        CommCodeChgMstParams l_commCodeChgMstParams = new CommCodeChgMstParams();
        l_commCodeChgMstParams.setCommissionNo("11");
        l_commCodeChgMstParams.setBranchId(33381L);
        l_commCodeChgMstParams.setCommProductCode("12");
        l_commCodeChgMstParams.setAppliStartDate("20060210");
        l_commCodeChgMstParams.setCommissionCourseDiv("12");
        l_commCodeChgMstParams.setInitialSetDiv("1");
        l_commCodeChgMstParams.setAccountChargeRatio(12D);
        l_commCodeChgMstParams.setLastUpdater("20060606");
        l_commCodeChgMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commCodeChgMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));

        CommCodeChgMstParams l_commCodeChgMstParams1 = new CommCodeChgMstParams();
        l_commCodeChgMstParams1 = l_commCodeChgMstParams;
        l_commCodeChgMstParams1.setCommissionNo("22");
        l_commCodeChgMstParams1.setAppliStartDate("20060209");
        
        CommCodeChgMstParams l_commCodeChgMstParams2 = new CommCodeChgMstParams();
        l_commCodeChgMstParams2 = l_commCodeChgMstParams;
        l_commCodeChgMstParams2.setCommissionNo("33");
        l_commCodeChgMstParams2.setAppliStartDate("20060208");

        
        try
        {
            TestDBUtility.deleteAll(l_commissionCourseRegistParams.getRowType());
            TestDBUtility.insertWithDel(l_commissionCourseRegistParams);
            
            TestDBUtility.deleteAll(l_commCodeChgMstParams.getRowType());
            TestDBUtility.insertWithDel(l_commCodeChgMstParams);
            TestDBUtility.insertWithDel(l_commCodeChgMstParams1);
            TestDBUtility.insertWithDel(l_commCodeChgMstParams2);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist = 
                new WEB3AccInfoCommissionCourseRegist(l_commissionCourseRegistParams);
            WEB3AccInfoEquityCommissionCourseRegistServiceImpl l_web3AccInfoEquityCommissionCourseRegistServiceImpl = 
                new WEB3AccInfoEquityCommissionCourseRegistServiceImpl();
            String l_strCommissionNo = 
                l_web3AccInfoEquityCommissionCourseRegistServiceImpl.getCommissionNo(l_commissionCourseRegist);
            
            assertEquals("33" , l_strCommissionNo);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubmitRegist_0001()
    {
        String STR_METHOD_NAME = "testSubmitRegist_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderValidationResult l_orderResult = OrderValidationResult.VALIDATION_OK_RESULT;
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateTradingPassword",
            new Class[] {Trader.class,SubAccount.class,String.class},
            l_orderResult);
        
        CommissionCourseMasterParams l_commissionCourseMasterRow = new CommissionCourseMasterParams();
        l_commissionCourseMasterRow.setInstitutionCode("0D");
        l_commissionCourseMasterRow.setCommProductCode("10");
        l_commissionCourseMasterRow.setCommissionCourseDiv("02");
        l_commissionCourseMasterRow.setStandardName("1234");
        l_commissionCourseMasterRow.setRegistEndDaySpec("00");
        l_commissionCourseMasterRow.setRegistEndTime("122611");
        l_commissionCourseMasterRow.setAppliStartDateDiv("1");
        l_commissionCourseMasterRow.setAppliStartEndTime("122611");
        l_commissionCourseMasterRow.setAppliTermDiv("0");
        l_commissionCourseMasterRow.setAppliTermDateCount(2);
        l_commissionCourseMasterRow.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_commissionCourseMasterRow.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        CommissionCourseRegistParams l_commissionCourseRegistParams = new CommissionCourseRegistParams();
        l_commissionCourseRegistParams.setCommissionCourseRegistId(123456789L);
        l_commissionCourseRegistParams.setInstitutionCode("0D");
        l_commissionCourseRegistParams.setBranchId(33381L);
        l_commissionCourseRegistParams.setAccountId(333812512203L);
        l_commissionCourseRegistParams.setCommProductCode("12");
        l_commissionCourseRegistParams.setAppliStartDatetime(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setAppliEndDatetime(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setCommissionCourseDiv("12");
        l_commissionCourseRegistParams.setRegistTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setRegistEndTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setDownloadFlag(BooleanEnum.TRUE);
        l_commissionCourseRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_commissionCourseRegistParams.setLastUpdater("abcd");
        l_commissionCourseRegistParams.setCreatedTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        
        BranchParams l_branch = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branch);
            
            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            TestDBUtility.insertWithDel(l_commissionCourseMasterRow);
            
            TestDBUtility.deleteAll(l_commissionCourseRegistParams.getRowType());
            TestDBUtility.insertWithDel(l_commissionCourseRegistParams);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams preferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            TestDBUtility.insertWithDel(preferencesParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        WEB3AccInfoEquityCommissionCourseChangeCompleteRequestForTest l_request = 
            new WEB3AccInfoEquityCommissionCourseChangeCompleteRequestForTest();
        
        l_request.commissionCourse = "02";
        
        try
        {
            WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest l_web3AccInfoEquityCommissionCourseRegistServiceImpl = 
                new WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest();
            l_web3AccInfoEquityCommissionCourseRegistServiceImpl.submitRegist(l_request);
            
            assertEquals("start getCommissionNo(WEB3AccInfoCommissionCourseRegist.手数料商品コード = 10," +
                    " WEB3AccInfoCommissionCourseRegist.手数料コースコード = 02)" , l_strFlag);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //（*2）でレコードが取得できた場合
    //validate取扱可能委託手数料コース(boolean, String)
    public void testSubmitRegist_0002()
    {
        String STR_METHOD_NAME = "testSubmitRegist_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderValidationResult l_orderResult = OrderValidationResult.VALIDATION_OK_RESULT;
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateTradingPassword",
            new Class[] {Trader.class,SubAccount.class,String.class},
            l_orderResult);
        
        CommissionCourseMasterParams l_commissionCourseMasterRow = new CommissionCourseMasterParams();
        l_commissionCourseMasterRow.setInstitutionCode("0D");
        l_commissionCourseMasterRow.setCommProductCode("10");
        l_commissionCourseMasterRow.setCommissionCourseDiv("02");
        l_commissionCourseMasterRow.setStandardName("1234");
        l_commissionCourseMasterRow.setRegistEndDaySpec("00");
        l_commissionCourseMasterRow.setRegistEndTime("122611");
        l_commissionCourseMasterRow.setAppliStartDateDiv("1");
        l_commissionCourseMasterRow.setAppliStartEndTime("122611");
        l_commissionCourseMasterRow.setAppliTermDiv("0");
        l_commissionCourseMasterRow.setAppliTermDateCount(2);
        l_commissionCourseMasterRow.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_commissionCourseMasterRow.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        CommissionCourseRegistParams l_commissionCourseRegistParams = new CommissionCourseRegistParams();
        l_commissionCourseRegistParams.setCommissionCourseRegistId(123456789L);
        l_commissionCourseRegistParams.setInstitutionCode("0D");
        l_commissionCourseRegistParams.setBranchId(33381L);
        l_commissionCourseRegistParams.setAccountId(333812512203L);
        l_commissionCourseRegistParams.setCommProductCode("12");
        l_commissionCourseRegistParams.setAppliStartDatetime(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setAppliEndDatetime(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setCommissionCourseDiv("12");
        l_commissionCourseRegistParams.setRegistTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setRegistEndTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        l_commissionCourseRegistParams.setDownloadFlag(BooleanEnum.TRUE);
        l_commissionCourseRegistParams.setDeleteFlag(BooleanEnum.FALSE);
        l_commissionCourseRegistParams.setLastUpdater("abcd");
        l_commissionCourseRegistParams.setCreatedTimestamp(WEB3DateUtility.getDate("20060211","yyyyMMdd"));
        l_commissionCourseRegistParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
        
        BranchParams l_branch = TestDBUtility.getBranchRow();
        
        try
        {
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branch);
            
            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            TestDBUtility.insertWithDel(l_commissionCourseMasterRow);
            
            TestDBUtility.deleteAll(l_commissionCourseRegistParams.getRowType());
            TestDBUtility.insertWithDel(l_commissionCourseRegistParams);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams preferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            TestDBUtility.insertWithDel(preferencesParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
        WEB3AccInfoEquityCommissionCourseChangeCompleteRequestForTest l_request = 
            new WEB3AccInfoEquityCommissionCourseChangeCompleteRequestForTest();
        
        l_request.commissionCourse = "02";
        
        try
        {
            WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest2 l_web3AccInfoEquityCommissionCourseRegistServiceImpl = 
                new WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest2();
            l_web3AccInfoEquityCommissionCourseRegistServiceImpl.submitRegist(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03108, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //（*2）でレコードが取得できた場合
    //validate取扱可能委託手数料コース(boolean, String)
    public void testValidateRegist_0001()
    {
        String STR_METHOD_NAME = "testValidateRegist_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AccInfoEquityCommissionCourseChangeConfirmRequestForTest changeConfirmRequest = 
                new WEB3AccInfoEquityCommissionCourseChangeConfirmRequestForTest();
            changeConfirmRequest.commissionCourse = "02";
            WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest2 l_impl =
                new WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest2();

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams preferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            TestDBUtility.insertWithDel(preferencesParams);

            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            CommissionCourseMasterParams courseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            TestDBUtility.insertWithDel(courseMasterParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);

            l_impl.validateRegist(changeConfirmRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03108, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>test past");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //でレコードが取得できなかった場合
    public void testValidateRegist_0002()
    {
        String STR_METHOD_NAME = "testValidateRegist_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AccInfoEquityCommissionCourseChangeConfirmRequestForTest changeConfirmRequest = 
                new WEB3AccInfoEquityCommissionCourseChangeConfirmRequestForTest();
            changeConfirmRequest.commissionCourse = "02";
            WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest l_impl =
                new WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest();

            TestDBUtility.deleteAll(CommissionCourseMasterRow.TYPE);
            CommissionCourseMasterParams courseMasterParams = TestDBUtility.getCommissionCourseMasterRow();
            TestDBUtility.insertWithDel(courseMasterParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams preferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            preferencesParams.setName("aa");
            TestDBUtility.insertWithDel(preferencesParams);

            WEB3AccInfoEquityCommissionCourseChangeConfirmResponse l_response =
                l_impl.validateRegist(changeConfirmRequest);
            assertEquals(l_response.currentDate, GtlUtils.getSystemTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>test past");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //（*2）でレコードが取得できなかった場合
    //get取扱可能委託手数料コース(証券会社コード : String, 手数料商品コード : String)
    public void test_getInputScreen_0001()
    {
        String STR_METHOD_NAME = "test_getInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        WEB3AccInfoEquityCommissionCourseRegistServiceImpl l_accInfoEquityCommissionCourseRegistServiceImpl =
            new WEB3AccInfoEquityCommissionCourseRegistServiceImpl();
        //お客様情報株式委託手数料コース変更申込入力リクエストデータオブジェクト
        WEB3AccInfoEquityCommissionCourseChangeInputRequest l_accInfoEquityCommissionCourseChangeInputRequest =
            new WEB3AccInfoEquityCommissionCourseChangeInputRequest();
        String l_strCommissionProductCode = "10";
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("33");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            
            //CommissionCourseMasterParams
            CommissionCourseMasterParams l_commissionCourseMasterParams = new CommissionCourseMasterParams();
            l_commissionCourseMasterParams.setInstitutionCode("0D");
            l_commissionCourseMasterParams.setCommProductCode("10");
            l_commissionCourseMasterParams.setCommissionCourseDiv("01");
            l_commissionCourseMasterParams.setStandardName("委託手数");
            l_commissionCourseMasterParams.setRegistEndDaySpec("00");
            l_commissionCourseMasterParams.setRegistEndTime("000000");
            l_commissionCourseMasterParams.setAppliStartDateDiv("1");
            l_commissionCourseMasterParams.setAppliStartEndTime("030201");
            l_commissionCourseMasterParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070211","yyyyMMdd"));
            l_commissionCourseMasterParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071211","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commissionCourseMasterParams);
            l_commissionCourseMasterParams.setInstitutionCode("0D");
            l_commissionCourseMasterParams.setCommProductCode("10");
            l_commissionCourseMasterParams.setCommissionCourseDiv("02");
            l_commissionCourseMasterParams.setStandardName("委託手数");
            l_commissionCourseMasterParams.setRegistEndDaySpec("00");
            l_commissionCourseMasterParams.setRegistEndTime("000000");
            l_commissionCourseMasterParams.setAppliStartDateDiv("1");
            l_commissionCourseMasterParams.setAppliStartEndTime("030201");
            l_commissionCourseMasterParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070211","yyyyMMdd"));
            l_commissionCourseMasterParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071211","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commissionCourseMasterParams);
            
            l_commissionCourseMasterParams.setInstitutionCode("0D");
            l_commissionCourseMasterParams.setCommProductCode("10");
            l_commissionCourseMasterParams.setCommissionCourseDiv("03");
            TestDBUtility.insertWithDel(l_commissionCourseMasterParams);

            l_commissionCourseMasterParams.setInstitutionCode("0D");
            l_commissionCourseMasterParams.setCommProductCode("10");
            l_commissionCourseMasterParams.setCommissionCourseDiv("04");
            TestDBUtility.insertWithDel(l_commissionCourseMasterParams);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams preferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            preferencesParams.setName("00");
            TestDBUtility.insertWithDel(preferencesParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AccInfoClientRequestService",
                    "getMainAccount",
                    new Class[] {},
                    l_mainAccount);
            
            //validate注文受付可能( )
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            //get委託手数料コース変更申込(顧客, String)
            WEB3AccInfoCommissionCourseRegistForMock.mockGetCommissionCourseRegist(l_mainAccount, l_strCommissionProductCode);
            
            l_accInfoEquityCommissionCourseChangeInputRequest.beforCommissionCourse = "01";
            WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response =
                l_accInfoEquityCommissionCourseRegistServiceImpl.getInputScreen(
                    l_accInfoEquityCommissionCourseChangeInputRequest);
            
            Calendar c = Calendar.getInstance();
            c.roll(Calendar.MONTH,true);
            c.roll(Calendar.MONTH,true);
            String l_month = (c.get(Calendar.MONTH)) > 9? ""+(c.get(Calendar.MONTH)):"0"+(c.get(Calendar.MONTH));
            String l_strDateYMD = c.get(Calendar.YEAR) + l_month +"01";
            //String l_strDateYMD = WEB3DateUtility.formatDate(WEB3DateUtility.getDate("", "yyyyMMdd"), "yyyyMMdd");
            
            String l_strAppliStartEndTime = "030201";
            String l_strDate = l_strDateYMD + " " + l_strAppliStartEndTime;
            Date l_datAppliStartDate = WEB3DateUtility.getDate(l_strDate, "yyyyMMdd HHmmss");
                log.debug("log:" + l_datAppliStartDate);
                assertEquals(l_response.changeAbleCommissionCourseList[0], "02");
                assertEquals(l_response.changeOfferDeadlineDateList[0], "00");
                assertEquals(l_response.changeAbleCommissionCourseList[1], "03");
                assertEquals(l_response.changeOfferDeadlineDateList[1], "00");
                assertEquals(l_response.changeAbleCommissionCourseList[2], "04");
                assertEquals(l_response.changeOfferDeadlineDateList[2], "00");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }

    //*2）でレコードが取得できた場合
    //get取扱可能委託手数料コース(String, String, boolean)
    public void test_getInputScreen_0002()
    {
        String STR_METHOD_NAME = "test_getInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        
        WEB3AccInfoEquityCommissionCourseRegistServiceImpl l_accInfoEquityCommissionCourseRegistServiceImpl =
            new WEB3AccInfoEquityCommissionCourseRegistServiceImpl();
        //お客様情報株式委託手数料コース変更申込入力リクエストデータオブジェクト
        WEB3AccInfoEquityCommissionCourseChangeInputRequest l_accInfoEquityCommissionCourseChangeInputRequest =
            new WEB3AccInfoEquityCommissionCourseChangeInputRequest();
        String l_strCommissionProductCode = "10";
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("33");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            
            //CommissionCourseMasterParams
            CommissionCourseMasterParams l_commissionCourseMasterParams = new CommissionCourseMasterParams();
            l_commissionCourseMasterParams.setInstitutionCode("0D");
            l_commissionCourseMasterParams.setCommProductCode("10");
            l_commissionCourseMasterParams.setCommissionCourseDiv("01");
            l_commissionCourseMasterParams.setStandardName("委託手数");
            l_commissionCourseMasterParams.setRegistEndDaySpec("00");
            l_commissionCourseMasterParams.setRegistEndTime("000000");
            l_commissionCourseMasterParams.setAppliStartDateDiv("1");
            l_commissionCourseMasterParams.setAppliStartEndTime("030201");
            l_commissionCourseMasterParams.setCommissionDiv(WEB3AccInfoCommissionDivDef.EQUITY_TRADE_COMMISSION);
            l_commissionCourseMasterParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070211","yyyyMMdd"));
            l_commissionCourseMasterParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071211","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commissionCourseMasterParams);
            l_commissionCourseMasterParams.setInstitutionCode("0D");
            l_commissionCourseMasterParams.setCommProductCode("10");
            l_commissionCourseMasterParams.setCommissionCourseDiv("02");
            l_commissionCourseMasterParams.setStandardName("委託手数");
            l_commissionCourseMasterParams.setRegistEndDaySpec("00");
            l_commissionCourseMasterParams.setRegistEndTime("000000");
            l_commissionCourseMasterParams.setAppliStartDateDiv("1");
            l_commissionCourseMasterParams.setAppliStartEndTime("030201");
            l_commissionCourseMasterParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070211","yyyyMMdd"));
            l_commissionCourseMasterParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071211","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_commissionCourseMasterParams);
            
            l_commissionCourseMasterParams.setInstitutionCode("0D");
            l_commissionCourseMasterParams.setCommProductCode("10");
            l_commissionCourseMasterParams.setCommissionCourseDiv("03");
            TestDBUtility.insertWithDel(l_commissionCourseMasterParams);

            l_commissionCourseMasterParams.setInstitutionCode("0D");
            l_commissionCourseMasterParams.setCommProductCode("10");
            l_commissionCourseMasterParams.setCommissionCourseDiv("04");
            TestDBUtility.insertWithDel(l_commissionCourseMasterParams);

            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams preferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            TestDBUtility.insertWithDel(preferencesParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountinfo.WEB3AccInfoClientRequestService",
                    "getMainAccount",
                    new Class[] {},
                    l_mainAccount);
            
            //validate注文受付可能( )
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            //get委託手数料コース変更申込(顧客, String)
            WEB3AccInfoCommissionCourseRegistForMock.mockGetCommissionCourseRegist(l_mainAccount, l_strCommissionProductCode);
            
            l_accInfoEquityCommissionCourseChangeInputRequest.beforCommissionCourse = "01";
            WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response =
                l_accInfoEquityCommissionCourseRegistServiceImpl.getInputScreen(
                    l_accInfoEquityCommissionCourseChangeInputRequest);
            
            Calendar c = Calendar.getInstance();
            c.roll(Calendar.MONTH,true);
            c.roll(Calendar.MONTH,true);
            String l_month = (c.get(Calendar.MONTH)) > 9? ""+(c.get(Calendar.MONTH)):"0"+(c.get(Calendar.MONTH));
            String l_strDateYMD = c.get(Calendar.YEAR) + l_month +"01";
            
            String l_strAppliStartEndTime = "030201";
            String l_strDate = l_strDateYMD + " " + l_strAppliStartEndTime;
            Date l_datAppliStartDate = WEB3DateUtility.getDate(l_strDate, "yyyyMMdd HHmmss");
                log.debug("log:" + l_datAppliStartDate);
                assertEquals(l_response.changeAbleCommissionCourseList[0], "02");
                assertEquals(l_response.changeOfferDeadlineDateList[0], "00");
                assertEquals(l_response.changeAbleCommissionCourseList[1], "03");
                assertEquals(l_response.changeOfferDeadlineDateList[1], "00");
                assertEquals(l_response.changeAbleCommissionCourseList[2], "04");
                assertEquals(l_response.changeOfferDeadlineDateList[2], "00");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }

    //引数.信用口座開設フラグ == false かつ 引数.手数料区分 == 1 （信用顧客）の場合
    //例外をスローする。
    public void testValidateHandlingPossibleCommCourse_0001()
    {
        String STR_METHOD_NAME = "testValidateHandlingPossibleCommCourse_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AccInfoEquityCommissionCourseRegistServiceImpl l_impl =
                new WEB3AccInfoEquityCommissionCourseRegistServiceImpl();

            l_impl.validateHandlingPossibleCommCourse(false, "1");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03108, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>test past");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testValidateHandlingPossibleCommCourse_0002()
    {
        String STR_METHOD_NAME = "testValidateHandlingPossibleCommCourse_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AccInfoEquityCommissionCourseRegistServiceImpl l_impl =
                new WEB3AccInfoEquityCommissionCourseRegistServiceImpl();

            l_impl.validateHandlingPossibleCommCourse(true, "0");
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>test past");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    class WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest extends WEB3AccInfoEquityCommissionCourseRegistServiceImpl
    {
        public boolean isCommissionNo(
            WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist,
            MainAccount l_mainAccount) throws WEB3BaseException
          {
            return true;
          }
        
        public String getCommissionNo(
            WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) throws WEB3BaseException
        {
            WEB3AccInfoEquityCommissionCourseRegistServiceImplTest.l_strFlag = 
                "start getCommissionNo(WEB3AccInfoCommissionCourseRegist.手数料商品コード = " 
                + l_commissionCourseRegist.getCommissionProductCode() 
                + ", WEB3AccInfoCommissionCourseRegist.手数料コースコード = " + l_commissionCourseRegist.getCommissionCourseCode()
                +")";
            return "12";
        }
        
        public MainAccount getMainAccount() throws WEB3SystemLayerException
        {
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            try
            {
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.insertWithDel(l_institution);
            } catch (WEB3BaseException e)
            {
                fail();
            }
            
            
           // l_mainAccount.
            WEB3GentradeMainAccount l_gentradeMainAccount =  new WEB3GentradeMainAccount(l_mainAccount);
            
           // l_mainAccount
            return  l_gentradeMainAccount;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
        
        public SubAccount getSubAccount()
        throws WEB3SystemLayerException
        {
                return null;
        }
        
        public void saveCommissionNo(
            String l_strCommissionNo, 
            WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist,
            MainAccount l_mainAccount) throws WEB3BaseException
        {
            
        }

        public void validateHandlingPossibleCommCourse(boolean l_blnMarginOpenFlag, String l_strCommissionDiv)
        throws WEB3BaseException
        {
            return;
        }
    }

    class WEB3AccInfoEquityCommissionCourseRegistServiceImplForTest2 extends WEB3AccInfoEquityCommissionCourseRegistServiceImpl
    {
        public boolean isCommissionNo(
            WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist,
            MainAccount l_mainAccount) throws WEB3BaseException
          {
            return true;
          }
        
        public String getCommissionNo(
            WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) throws WEB3BaseException
        {
            WEB3AccInfoEquityCommissionCourseRegistServiceImplTest.l_strFlag = 
                "start getCommissionNo(WEB3AccInfoCommissionCourseRegist.手数料商品コード = " 
                + l_commissionCourseRegist.getCommissionProductCode() 
                + ", WEB3AccInfoCommissionCourseRegist.手数料コースコード = " + l_commissionCourseRegist.getCommissionCourseCode()
                +")";
            return "12";
        }
        
        public MainAccount getMainAccount() throws WEB3SystemLayerException
        {
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            
            InstitutionParams l_institution = TestDBUtility.getInstitutionRow();
            try
            {
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.insertWithDel(l_institution);
            } catch (WEB3BaseException e)
            {
                fail();
            }
            
            
           // l_mainAccount.
            WEB3GentradeMainAccount l_gentradeMainAccount =  new WEB3GentradeMainAccount(l_mainAccount);
            
           // l_mainAccount
            return  l_gentradeMainAccount;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
        
        public SubAccount getSubAccount()
        throws WEB3SystemLayerException
        {
                return null;
        }
        
        public void saveCommissionNo(
            String l_strCommissionNo, 
            WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist,
            MainAccount l_mainAccount) throws WEB3BaseException
        {
            
        }

        public void validateHandlingPossibleCommCourse(boolean l_blnMarginOpenFlag, String l_strCommissionDiv)
        throws WEB3BaseException
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03108,
                this.getClass().getName(),
                "信用口座開設なし、かつ手数料区分は信用顧客です。");
        }
    }
    
    class WEB3AccInfoEquityCommissionCourseChangeCompleteRequestForTest extends WEB3AccInfoEquityCommissionCourseChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    private class WEB3AccInfoEquityCommissionCourseChangeConfirmRequestForTest extends WEB3AccInfoEquityCommissionCourseChangeConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
}
@
