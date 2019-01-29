head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccOpenVoucherRegAcceptServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票登録受付サービスImpl(WEB3AccOpenVoucherRegAcceptServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/28 何文敏 (中訊)
*/

package webbroker3.accountopen.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.ExpAccountOpenDao;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.HostAccOpenAcceptParams;
import webbroker3.accountopen.data.HostAccOpenAcceptRow;
import webbroker3.accountopen.data.HostAccRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInformAcceptUnitService;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptServiceImpl.WEB3AccOpenVoucherRegAcceptTransactionCallback;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3VoucherCreateStatusDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.EleDeliveryManagementDao;
import webbroker3.gentrade.data.EleDeliveryManagementParams;
import webbroker3.gentrade.data.EleDeliveryManagementRow;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設伝票登録受付サービスImpl)<BR>
 *
 * @@author 何文敏
 */
public class WEB3AccOpenVoucherRegAcceptServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenVoucherRegAcceptServiceImplTest.class);
    WEB3AccOpenVoucherRegAcceptServiceImpl l_impl = new WEB3AccOpenVoucherRegAcceptServiceImpl();

    public WEB3AccOpenVoucherRegAcceptServiceImplTest(String arg0)
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
    
    public void testProcess()
    {
        final String STR_METHOD_NAME = "testProcess()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestDBUtility.deleteAllAndCommit(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = this.getVariousInformRow();
            l_variousInformParams.setRequestCode("GI843");
            l_variousInformParams.setOrderRequestNumber("123456");
            TestDBUtility.insertWithDelAndCommit(l_variousInformParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                    "notifyVoucherRegAccept", new Class[]{HostAccOpenAcceptParams.class },
                    "2");

            //
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("22");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            //HostAccOpenAcceptParams
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
//            l_queryProcesser.doDeleteAllQuery(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_acceptParams =
                this.getHostAccOpenAcceptParams();
            l_acceptParams.setErrorCode("1001");
            l_acceptParams.setRequestCode("GI84C");
            l_acceptParams.setAcceptStatus("0");
            l_acceptParams.setStatus("0");
//            l_queryProcesser.doInsertQuery(l_acceptParams);
            TestDBUtility.insertWithDelAndCommit(l_acceptParams);

            //VariousInformParams
//            TestDBUtility.deleteAllAndCommit(VariousInformParams.TYPE);
//            l_queryProcesser.doDeleteAllQuery(VariousInformParams.TYPE);
//            VariousInformParams l_informParams = this.getVariousInformParams();
//            l_informParams.setStatus(null);
//            l_informParams.setErrorReasonCode(null);
//            l_queryProcesser.doInsertQuery(l_informParams);
//            TestDBUtility.insertWithDelAndCommit(l_informParams);

            //BranchParams
            TestDBUtility.deleteAllAndCommit(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode(l_acceptParams.getInstitutionCode());
            l_branchParams.setBranchCode(l_acceptParams.getBranchCode());
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            Services.unregisterService(WEB3AccOpenInformAcceptUnitService.class);
            Services.registerService(WEB3AccOpenInformAcceptUnitService.class,
                    new WEB3AccOpenInformAcceptUnitServiceImplForTest());
            
            
            WEB3AccOpenVoucherRegAcceptTransactionCallbackForTest l_callBack =
                new WEB3AccOpenVoucherRegAcceptTransactionCallbackForTest(l_impl);
            l_callBack.process();
            
            Services.unregisterService(WEB3AccOpenInformAcceptUnitService.class);
            Services.registerService(WEB3AccOpenInformAcceptUnitService.class,
                    new WEB3AccOpenInformAcceptUnitServiceImpl());
            
            List l_resultList = l_queryProcesser.doFindAllQuery(VariousInformParams.TYPE);

            VariousInformParams l_resultParms = (VariousInformParams)l_resultList.get(0);
            assertEquals("3", l_resultParms.getStatus());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);    
    }
    
    public void testGetVariousInformParams_Case0001()
    {
        final String STR_METHOD_NAME = "testGetVariousInformParams_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = this.getVariousInformRow();
            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callBack =
                l_impl.new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            Method method = WEB3AccOpenVoucherRegAcceptTransactionCallback.class.getDeclaredMethod(
                "getVariousInformParams", new Class[]{String.class, String.class, String.class, String.class});
            method.setAccessible(true);
            Object l_obj = method.invoke(l_callBack, new Object[]{"0D", "381", "023", "GI823"});
            VariousInformParams l_params = (VariousInformParams)l_obj;
            assertEquals("GI82A", l_params.getRequestNumber());
            assertEquals("001", l_params.getTraderCode());
            assertEquals("1", l_params.getInformDiv());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetVariousInformParams_Case0002()
    {
        final String STR_METHOD_NAME = "testGetVariousInformParams_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callBack =
                l_impl.new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            Method method = WEB3AccOpenVoucherRegAcceptTransactionCallback.class.getDeclaredMethod(
                "getVariousInformParams", new Class[]{String.class, String.class, String.class, String.class});
            method.setAccessible(true);
            Object l_obj = method.invoke(l_callBack, new Object[]{"0D", "381", "023", "GI823"});
            
            VariousInformParams l_params = (VariousInformParams)l_obj;
            assertEquals(null, l_params);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testIsExistingAccountAccept_Case0001()
    {
        final String STR_METHOD_NAME = "testIsExistingAccountAccept_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = new HostAccOpenAcceptParams();
            
            l_hostAccOpenAcceptParams.setRequestCode("GI82C");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("3121465");
            l_hostAccOpenAcceptParams.setTraderCode("001");
            l_hostAccOpenAcceptParams.setOrderRequestNumber("023");
            l_hostAccOpenAcceptParams.setAcceptStatus("1");
            l_hostAccOpenAcceptParams.setErrorCode("01");
            l_hostAccOpenAcceptParams.setStatus("0");
            
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callBack =
                l_impl.new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            Method method = WEB3AccOpenVoucherRegAcceptTransactionCallback.class.getDeclaredMethod(
                "isExistingAccountAccept", new Class[]{HostAccOpenAcceptParams.class});
            method.setAccessible(true);
            Object l_obj = method.invoke(l_callBack, new Object[]{l_hostAccOpenAcceptParams});
            assertEquals("true", l_obj.toString());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsExistingAccountAccept_Case0002()
    {
        final String STR_METHOD_NAME = "testIsExistingAccountAccept_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = new HostAccOpenAcceptParams();
            
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callBack =
                l_impl.new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            Method method = WEB3AccOpenVoucherRegAcceptTransactionCallback.class.getDeclaredMethod(
                "isExistingAccountAccept", new Class[]{HostAccOpenAcceptParams.class});
            method.setAccessible(true);
            Object l_obj = method.invoke(l_callBack, new Object[]{l_hostAccOpenAcceptParams});
            assertEquals("false", l_obj.toString());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * GI84C：取報・取残電子交付・特定口座登録受付の場合
     * trueを返却する。
     *
     */
    public void testIsExistingAccountAccept_Case0003()
    {
        final String STR_METHOD_NAME = "testIsExistingAccountAccept_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams = new HostAccOpenAcceptParams();
        
        l_hostAccOpenAcceptParams.setRequestCode("GI84C");
        l_hostAccOpenAcceptParams.setInstitutionCode("0D");
        l_hostAccOpenAcceptParams.setBranchCode("381");
        l_hostAccOpenAcceptParams.setAccountCode("3121465");
        l_hostAccOpenAcceptParams.setTraderCode("001");
        l_hostAccOpenAcceptParams.setOrderRequestNumber("023");
        l_hostAccOpenAcceptParams.setAcceptStatus("1");
        l_hostAccOpenAcceptParams.setErrorCode("01");
        l_hostAccOpenAcceptParams.setStatus("0");
        
        try
        {
            
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callBack =
                l_impl.new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            Method method = WEB3AccOpenVoucherRegAcceptTransactionCallback.class.getDeclaredMethod(
                "isExistingAccountAccept", new Class[]{HostAccOpenAcceptParams.class});
            method.setAccessible(true);
            Object l_obj = method.invoke(l_callBack, new Object[]{l_hostAccOpenAcceptParams});
            assertEquals("true", l_obj.toString());
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * "GI84C"
     */
    public void test_getAcceptQueue_0001()
    {
        final String STR_METHOD_NAME = "test_getAcceptQueue_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
		{
        	WEB3AccOpenVoucherRegAcceptTransactionCallback l_callBack =
                l_impl.new WEB3AccOpenVoucherRegAcceptTransactionCallback();
        	
			TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptRow.TYPE);
			
	        HostAccOpenAcceptParams l_hostAccOpenAcceptParams = new HostAccOpenAcceptParams();
	        
	        l_hostAccOpenAcceptParams.setRequestCode("GI84C");
	        l_hostAccOpenAcceptParams.setInstitutionCode("0D");
	        l_hostAccOpenAcceptParams.setBranchCode("381");
	        l_hostAccOpenAcceptParams.setAccountCode("2512246");
	        l_hostAccOpenAcceptParams.setTraderCode("1234");
	        l_hostAccOpenAcceptParams.setOrderRequestNumber("123456");
	        l_hostAccOpenAcceptParams.setAcceptStatus("1");
	        l_hostAccOpenAcceptParams.setErrorCode("01");
	        l_hostAccOpenAcceptParams.setStatus("0");
	        
	        TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
	        
	        HostAccOpenAcceptParams[] l_params = l_callBack.getAcceptQueue();
	        
	        assertEquals("GI84C",l_params[0].getRequestCode());
		}
		catch (Exception l_ex)
		{
            log.debug(l_ex.getMessage(), l_ex);
            fail();
		}
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public VariousInformParams getVariousInformRow()
    {
        VariousInformParams l_variousInformParams = new VariousInformParams();
        
        l_variousInformParams.setInstitutionCode("0D");
        l_variousInformParams.setInformDiv("1");
        l_variousInformParams.setRequestNumber("GI82A");
        l_variousInformParams.setBranchCode("381");
        l_variousInformParams.setRequestCode("GI823");
        l_variousInformParams.setAccountCode("3121465");
        l_variousInformParams.setTraderCode("001");
        l_variousInformParams.setOrderRequestNumber("023");
        l_variousInformParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_variousInformParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_variousInformParams;
        
    }
    
    public class WEB3AccOpenInformAcceptUnitServiceImplForTest extends WEB3AccOpenInformAcceptUnitServiceImpl
    {
        public void notifyInformAccept(
                HostAccOpenAcceptParams l_hostAccOpenAcceptParams,
                VariousInformParams l_variousInformParams,
                String l_strProcessDiv) throws WEB3BaseException
        {
        	l_variousInformParams.setStatus(WEB3VoucherCreateStatusDef.ACCEPT_COMPLETE);
        	
            try
            {
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                l_queryProcesser.doUpdateQuery(l_variousInformParams);
            }
            catch (DataFindException l_dfe)
            {
                log.error(l_dfe.getMessage(), l_dfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + ".",
                    l_dfe.getMessage(), l_dfe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + ".",
                    l_dne.getMessage(), l_dne);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + ".",
                    l_dqe.getMessage(), l_dqe);
            }
        }
    }
    public class WEB3AccOpenVoucherRegAcceptTransactionCallbackForTest extends WEB3AccOpenVoucherRegAcceptTransactionCallback 
    {
        public WEB3AccOpenVoucherRegAcceptTransactionCallbackForTest(WEB3AccOpenVoucherRegAcceptServiceImpl accOpenVoucherRegAcceptServiceImpl) throws DataCallbackException
        {
            accOpenVoucherRegAcceptServiceImpl.super();
        }

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

//        protected HostAccOpenAcceptParams[] getAcceptQueue() throws WEB3BaseException
//        {
//            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = new HostAccOpenAcceptParams();
//            
////            l_hostAccOpenAcceptParams.setRequestCode("GI82C");
////            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
////            l_hostAccOpenAcceptParams.setBranchCode("381");
////            l_hostAccOpenAcceptParams.setAccountCode("2512246");
////            l_hostAccOpenAcceptParams.setTraderCode("1234");
////            l_hostAccOpenAcceptParams.setOrderRequestNumber("123456");
////            l_hostAccOpenAcceptParams.setAcceptStatus("1");
////            l_hostAccOpenAcceptParams.setErrorCode("01");
////            l_hostAccOpenAcceptParams.setStatus("0");
//            List l_list = null;
//            try
//            {
//                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
//                l_list = l_queryProcesser.doFindAllQuery(HostAccOpenAcceptParams.TYPE);
//            }
//            catch(Exception l_exc)
//            {
//                fail();
//            }
//            l_hostAccOpenAcceptParams =
//                (HostAccOpenAcceptParams)l_list.get(0);
//            
//            HostAccOpenAcceptParams l_hostAccOpenAcceptParams1 = new HostAccOpenAcceptParams();
//            
//            l_hostAccOpenAcceptParams1.setRequestCode("GI82H");
//            l_hostAccOpenAcceptParams1.setInstitutionCode("0D");
//            l_hostAccOpenAcceptParams1.setBranchCode("381");
//            l_hostAccOpenAcceptParams1.setAccountCode("3121465");
//            l_hostAccOpenAcceptParams1.setTraderCode("001");
//            l_hostAccOpenAcceptParams1.setOrderRequestNumber("023");
//            l_hostAccOpenAcceptParams1.setAcceptStatus("1");
//            l_hostAccOpenAcceptParams1.setErrorCode("1234");
//            l_hostAccOpenAcceptParams1.setStatus("0");
//            
//            HostAccOpenAcceptParams[] l_hostAccOpenAcceptParam =
//                new HostAccOpenAcceptParams[]{l_hostAccOpenAcceptParams, l_hostAccOpenAcceptParams1};
//
//            return l_hostAccOpenAcceptParam;
//        }
        
        protected String getBranchPreferences(String l_strBranchId, String l_strPreferencesname)
        throws WEB3BaseException
        {
            return "1";
        }
        
        protected void updateQueue(HostAccOpenAcceptParams l_accOpenAcceptParams, String l_strProcessDiv)
        throws WEB3BaseException
        {
            
        }
    } 
    
    public HostAccOpenAcceptParams getHostAccOpenAcceptParams()
    {
        HostAccOpenAcceptParams l_acceptParams =
            new HostAccOpenAcceptParams();

        //データコード  request_code
        l_acceptParams.setRequestCode("GI82C");
        //証券会社コード institution_code
        l_acceptParams.setInstitutionCode("0D");
        //部店コード   branch_code
        l_acceptParams.setBranchCode("381");
        //顧客コード   account_code
        l_acceptParams.setAccountCode("2512246");
        //扱者コード   trader_code
        l_acceptParams.setTraderCode("1234");
        //識別コード   order_request_number
        l_acceptParams.setOrderRequestNumber("123456");
        //受付結果    accept_status
        l_acceptParams.setAcceptStatus("1");
        //エラーメッセージ    error_message
        l_acceptParams.setErrorCode("01");
        //処理区分    status
        l_acceptParams.setStatus("9");

        return l_acceptParams;
    }

    public VariousInformParams getVariousInformParams()
    {
        VariousInformParams l_informParams = new VariousInformParams();
        //証券会社コード institution_code
        l_informParams.setInstitutionCode("0D");
        //連絡種別     inform_div
        l_informParams.setInformDiv("1");
        //識別コード   request_number
        l_informParams.setRequestNumber("023");
        //部店コード   branch_code
        l_informParams.setBranchCode("381");
        //作成日時     created_timestamp
        l_informParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日時    last_updated_timestamp
        l_informParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        //伝票作成状況   status
        //エラー理由コード   error_reason_code
        //伝票受信日時
        l_informParams.setRequestCode("GI823");
        l_informParams.setOrderRequestNumber("123456");

        return l_informParams;
    }
    
    /**
     * GI82A：顧客登録受付
     */
    public void test_updatePrintFlag_0001()
    {
        final String STR_METHOD_NAME = "test_updatePrintFlag_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback = new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            //受付キュー.識別コード
            l_hostAccOpenAcceptParams.setOrderRequestNumber("11");
            //受付キュー.データコード
            l_hostAccOpenAcceptParams.setRequestCode("GI82A");
            //受付キュー.証券会社コード
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            //受付キュー.部店コード
            l_hostAccOpenAcceptParams.setBranchCode("11");
            //受付キュー.顧客コード
            l_hostAccOpenAcceptParams.setAccountCode("11");
            
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("11");
            l_hostAccRegVoucherParams.setRequestCode("GI821");
            l_hostAccRegVoucherParams.setInstitutionCode("0D");
            l_hostAccRegVoucherParams.setBranchCode("11");
            l_hostAccRegVoucherParams.setAccountCode("11");
            
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("22");
            l_hostAccRegVoucherParams.setSerialNo("0");
            l_hostAccRegVoucherParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("22");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            
            l_callback.updatePrintFlag(l_hostAccOpenAcceptParams);
            
            
            String l_strWhere = " institution_code = ? and acc_open_request_number = ?  ";
            Object[] l_objConds =  new Object[]{"0D", "22"};
            List l_lisRecordexcs = null;
            try
            {
                l_lisRecordexcs = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenParams.TYPE,
                    l_strWhere,
                    l_objConds);
            }
            catch (DataQueryException l_ex)
            {
                fail();
            }
            catch (DataNetworkException l_ex)
            {
                fail();
            }
            ExpAccountOpenRow l_row = (ExpAccountOpenRow)l_lisRecordexcs.get(0);
            assertEquals("0", l_row.getPrintFlag());
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * GI84E：顧客登録（仲介業）受付
     */
    public void test_updatePrintFlag_0002()
    {
        final String STR_METHOD_NAME = "test_updatePrintFlag_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback = new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            //受付キュー.識別コード
            l_hostAccOpenAcceptParams.setOrderRequestNumber("11");
            //受付キュー.データコード
            l_hostAccOpenAcceptParams.setRequestCode("GI84E");
            //受付キュー.証券会社コード
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            //受付キュー.部店コード
            l_hostAccOpenAcceptParams.setBranchCode("11");
            //受付キュー.顧客コード
            l_hostAccOpenAcceptParams.setAccountCode("11");
            
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("11");
            l_hostAccRegVoucherParams.setRequestCode("GI845");
            l_hostAccRegVoucherParams.setInstitutionCode("0D");
            l_hostAccRegVoucherParams.setBranchCode("11");
            l_hostAccRegVoucherParams.setAccountCode("11");
            
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("22");
            l_hostAccRegVoucherParams.setSerialNo("0");
            l_hostAccRegVoucherParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            ExpAccountOpenParams l_expAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            
            l_expAccountOpenParams.setInstitutionCode("0D");
            l_expAccountOpenParams.setAccOpenRequestNumber("22");
            TestDBUtility.insertWithDel(l_expAccountOpenParams);
            
            
            l_callback.updatePrintFlag(l_hostAccOpenAcceptParams);
            
            
            String l_strWhere = " institution_code = ? and acc_open_request_number = ?  ";
            Object[] l_objConds =  new Object[]{"0D", "22"};
            List l_lisRecordexcs = null;
            try
            {
                l_lisRecordexcs = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenParams.TYPE,
                    l_strWhere,
                    l_objConds);
            }
            catch (DataQueryException l_ex)
            {
                fail();
            }
            catch (DataNetworkException l_ex)
            {
                fail();
            }
            ExpAccountOpenRow l_row = (ExpAccountOpenRow)l_lisRecordexcs.get(0);
            assertEquals("0", l_row.getPrintFlag());
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception e)
        {
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //update口座開設登録日
    public void testUpdateAccOpenOpenDateCase1()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenOpenDateCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {      
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_ExpAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_ExpAccountOpenParams.setInstitutionCode("0D");
            l_ExpAccountOpenParams.setAccOpenRequestNumber("1");
            l_ExpAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_ExpAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ExpAccountOpenParams);
            
//            HostAccRegVoucher
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("123456789");//識別コード 
            l_hostAccRegVoucherParams.setRequestCode("GI821");//データコード
            l_hostAccRegVoucherParams.setInstitutionCode("0D");//証券会社コード
            l_hostAccRegVoucherParams.setBranchCode("381");//部店コード
            l_hostAccRegVoucherParams.setAccountCode("2512246");//顧客コード
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostAccRegVoucherParams.setSerialNo("333");
            l_hostAccRegVoucherParams.setAccountName("亜");
            l_hostAccRegVoucherParams.setAccountNameKana("あ");
            l_hostAccRegVoucherParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setProamDiv("1");
            l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
            l_hostAccRegVoucherParams.setForeignerDivAviation("1");
            l_hostAccRegVoucherParams.setForeignerDivNtt("1");
            l_hostAccRegVoucherParams.setDividendTransferDiv("1");
            l_hostAccRegVoucherParams.setAgentDivPermanent("0");
            l_hostAccRegVoucherParams.setAgentDivLegal("0");
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
            l_hostAccOpenAcceptParams.setRequestCode("GI82A");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("2512246");
            l_hostAccOpenAcceptParams.setTraderCode("1234");
            TestDBUtility.insertWithDel(l_hostAccOpenAcceptParams);
            l_callback.updateAccOpenOpenDate(l_hostAccOpenAcceptParams);
            
            ExpAccountOpenRow l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk("0D", "1");
            assertEquals("20070731", WEB3DateUtility.formatDate(l_expAccountOpenRow.getAccountOpenDate(), "yyyyMMdd"));
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testUpdateAccOpenOpenDateCase2()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenOpenDateCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {      
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_ExpAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_ExpAccountOpenParams.setInstitutionCode("0D");
            l_ExpAccountOpenParams.setAccOpenRequestNumber("1");
            l_ExpAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_ExpAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ExpAccountOpenParams);
            
//            HostAccRegVoucher
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("123456789");//識別コード 
            l_hostAccRegVoucherParams.setRequestCode("GI821");//データコード
            l_hostAccRegVoucherParams.setInstitutionCode("0D");//証券会社コード
            l_hostAccRegVoucherParams.setBranchCode("381");//部店コード
            l_hostAccRegVoucherParams.setAccountCode("2512246");//顧客コード
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostAccRegVoucherParams.setSerialNo("333");
            l_hostAccRegVoucherParams.setAccountName("亜");
            l_hostAccRegVoucherParams.setAccountNameKana("あ");
            l_hostAccRegVoucherParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setProamDiv("1");
            l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
            l_hostAccRegVoucherParams.setForeignerDivAviation("1");
            l_hostAccRegVoucherParams.setForeignerDivNtt("1");
            l_hostAccRegVoucherParams.setDividendTransferDiv("1");
            l_hostAccRegVoucherParams.setAgentDivPermanent("0");
            l_hostAccRegVoucherParams.setAgentDivLegal("0");
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setOrderRequestNumber("123456789");
            l_hostAccOpenAcceptParams.setRequestCode("GI82A");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("2512246");
            l_hostAccOpenAcceptParams.setTraderCode("1234");
            TestDBUtility.insertWithDel(l_hostAccOpenAcceptParams);
            l_callback.updateAccOpenOpenDate(l_hostAccOpenAcceptParams);
            
            ExpAccountOpenRow l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk("0D", "1");
            assertEquals("20070731", WEB3DateUtility.formatDate(l_expAccountOpenRow.getAccountOpenDate(), "yyyyMMdd"));
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateAccOpenOpenDateCase3()
    {
        final String STR_METHOD_NAME = "testUpdateAccOpenOpenDateCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {      
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_ExpAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_ExpAccountOpenParams.setInstitutionCode("0D");
            l_ExpAccountOpenParams.setAccOpenRequestNumber("1");
            l_ExpAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_ExpAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ExpAccountOpenParams);
            
//            HostAccRegVoucher
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("1");//識別コード 
            l_hostAccRegVoucherParams.setRequestCode("GI821");//データコード
            l_hostAccRegVoucherParams.setInstitutionCode("0D");//証券会社コード
            l_hostAccRegVoucherParams.setBranchCode("381");//部店コード
            l_hostAccRegVoucherParams.setAccountCode("2512246");//顧客コード
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostAccRegVoucherParams.setSerialNo("333");
            l_hostAccRegVoucherParams.setAccountName("亜");
            l_hostAccRegVoucherParams.setAccountNameKana("あ");
            l_hostAccRegVoucherParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setProamDiv("1");
            l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
            l_hostAccRegVoucherParams.setForeignerDivAviation("1");
            l_hostAccRegVoucherParams.setForeignerDivNtt("1");
            l_hostAccRegVoucherParams.setDividendTransferDiv("1");
            l_hostAccRegVoucherParams.setAgentDivPermanent("0");
            l_hostAccRegVoucherParams.setAgentDivLegal("0");
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            TestDBUtility.deleteAll(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
            l_hostAccOpenAcceptParams.setRequestCode("GI82A");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("2512246");
            l_hostAccOpenAcceptParams.setTraderCode("1234");
            TestDBUtility.insertWithDel(l_hostAccOpenAcceptParams);
            l_callback.updateAccOpenOpenDate(l_hostAccOpenAcceptParams);
            
            ExpAccountOpenRow l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk("0D", "1");
            assertEquals("20070731", WEB3DateUtility.formatDate(l_expAccountOpenRow.getAccountOpenDate(), "yyyyMMdd"));
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get証券会社プリファ@レンス
    public void testGetInstitutionPreferencesCase1()
    {
        final String STR_METHOD_NAME = "testGetInstitutionPreferencesCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {      
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(11);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_InstitutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_InstitutionPreferencesParams.setInstitutionId(11);
            l_InstitutionPreferencesParams.setName("accountopen.mail.send.div");
            l_InstitutionPreferencesParams.setNameSerialNo(1);
            l_InstitutionPreferencesParams.setValue("222");
            TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
            String l_strInstitutionPreferevces = l_callback.getInstitutionPreferences("0D", "accountopendate.update.determination");

            assertEquals("0", l_strInstitutionPreferevces);
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInstitutionPreferencesCase2()
    {
        final String STR_METHOD_NAME = "testGetInstitutionPreferencesCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {      
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(11);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_InstitutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_InstitutionPreferencesParams.setInstitutionId(11);
            l_InstitutionPreferencesParams.setName("accountopendate.update.determination");
            l_InstitutionPreferencesParams.setNameSerialNo(1);
            l_InstitutionPreferencesParams.setValue("222");
            TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
            String l_strInstitutionPreferevces = l_callback.getInstitutionPreferences("0D", "accountopendate.update.determination");

            assertEquals("222", l_strInstitutionPreferevces);
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //process
    public void testProcessCase1()
    {
        final String STR_METHOD_NAME = "testProcessCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {      
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            //instId,branchCode,accountCode: 11,381,2512246
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setInstitutionId(11);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(11);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_InstitutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_InstitutionPreferencesParams.setInstitutionId(11);
            l_InstitutionPreferencesParams.setName("accountopendate.update.determination");
            l_InstitutionPreferencesParams.setNameSerialNo(1);
            l_InstitutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
            
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_ExpAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_ExpAccountOpenParams.setInstitutionCode("0D");
            l_ExpAccountOpenParams.setAccOpenRequestNumber("1");
            l_ExpAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_ExpAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ExpAccountOpenParams);
            
//            HostAccRegVoucher
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("1");//識別コード 
            l_hostAccRegVoucherParams.setRequestCode("GI821");//データコード
            l_hostAccRegVoucherParams.setInstitutionCode("0D");//証券会社コード
            l_hostAccRegVoucherParams.setBranchCode("381");//部店コード
            l_hostAccRegVoucherParams.setAccountCode("2512246");//顧客コード
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostAccRegVoucherParams.setSerialNo("333");
            l_hostAccRegVoucherParams.setAccountName("亜");
            l_hostAccRegVoucherParams.setAccountNameKana("あ");
            l_hostAccRegVoucherParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setProamDiv("1");
            l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
            l_hostAccRegVoucherParams.setForeignerDivAviation("1");
            l_hostAccRegVoucherParams.setForeignerDivNtt("1");
            l_hostAccRegVoucherParams.setDividendTransferDiv("1");
            l_hostAccRegVoucherParams.setAgentDivPermanent("0");
            l_hostAccRegVoucherParams.setAgentDivLegal("0");
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
            l_hostAccOpenAcceptParams.setRequestCode("GI82A");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("2512246");
            l_hostAccOpenAcceptParams.setTraderCode("1234");
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            l_callback.process();
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testProcessCase2()
    {
        final String STR_METHOD_NAME = "testProcessCase2()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                "notifyVoucherRegAccept", new Class[]{HostAccOpenAcceptParams.class },
                "1");
        
        try
        {      
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("22");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
         
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            //instId,branchCode,accountCode: 11,381,2512246
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setInstitutionId(11);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(11);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_InstitutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_InstitutionPreferencesParams.setInstitutionId(11);
            l_InstitutionPreferencesParams.setName("accountopendate.update.determination");
            l_InstitutionPreferencesParams.setNameSerialNo(1);
            l_InstitutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
            
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_ExpAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_ExpAccountOpenParams.setInstitutionCode("0D");
            l_ExpAccountOpenParams.setAccOpenRequestNumber("1");
            l_ExpAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_ExpAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ExpAccountOpenParams);
            
//            HostAccRegVoucher
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("1");//識別コード 
            l_hostAccRegVoucherParams.setRequestCode("GI84C");//データコード
            l_hostAccRegVoucherParams.setInstitutionCode("0D");//証券会社コード
            l_hostAccRegVoucherParams.setBranchCode("381");//部店コード
            l_hostAccRegVoucherParams.setAccountCode("2512246");//顧客コード
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostAccRegVoucherParams.setSerialNo("333");
            l_hostAccRegVoucherParams.setAccountName("亜");
            l_hostAccRegVoucherParams.setAccountNameKana("あ");
            l_hostAccRegVoucherParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setProamDiv("1");
            l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
            l_hostAccRegVoucherParams.setForeignerDivAviation("1");
            l_hostAccRegVoucherParams.setForeignerDivNtt("1");
            l_hostAccRegVoucherParams.setDividendTransferDiv("1");
            l_hostAccRegVoucherParams.setAgentDivPermanent("0");
            l_hostAccRegVoucherParams.setAgentDivLegal("0");
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
            l_hostAccOpenAcceptParams.setRequestCode("GI84C");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("2512246");
            l_hostAccOpenAcceptParams.setTraderCode("1234");
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode(l_hostAccOpenAcceptParams.getInstitutionCode());
            l_branchParams.setBranchCode(l_hostAccOpenAcceptParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_callback.process();
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testProcessCase3()
    {
        final String STR_METHOD_NAME = "testProcessCase3()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                "notifyVoucherRegAccept", new Class[]{HostAccOpenAcceptParams.class },
                "9");
        
        try
        {      
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("22");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
         
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            //instId,branchCode,accountCode: 11,381,2512246
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setInstitutionId(11);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(11);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_InstitutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_InstitutionPreferencesParams.setInstitutionId(11);
            l_InstitutionPreferencesParams.setName("accountopendate.update.determination");
            l_InstitutionPreferencesParams.setNameSerialNo(1);
            l_InstitutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
            
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_ExpAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_ExpAccountOpenParams.setInstitutionCode("0D");
            l_ExpAccountOpenParams.setAccOpenRequestNumber("1");
            l_ExpAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_ExpAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ExpAccountOpenParams);
            
//            HostAccRegVoucher
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("1");//識別コード 
            l_hostAccRegVoucherParams.setRequestCode("GI84C");//データコード
            l_hostAccRegVoucherParams.setInstitutionCode("0D");//証券会社コード
            l_hostAccRegVoucherParams.setBranchCode("381");//部店コード
            l_hostAccRegVoucherParams.setAccountCode("2512246");//顧客コード
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostAccRegVoucherParams.setSerialNo("333");
            l_hostAccRegVoucherParams.setAccountName("亜");
            l_hostAccRegVoucherParams.setAccountNameKana("あ");
            l_hostAccRegVoucherParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setProamDiv("1");
            l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
            l_hostAccRegVoucherParams.setForeignerDivAviation("1");
            l_hostAccRegVoucherParams.setForeignerDivNtt("1");
            l_hostAccRegVoucherParams.setDividendTransferDiv("1");
            l_hostAccRegVoucherParams.setAgentDivPermanent("0");
            l_hostAccRegVoucherParams.setAgentDivLegal("0");
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
            l_hostAccOpenAcceptParams.setRequestCode("GI84C");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("2512246");
            l_hostAccOpenAcceptParams.setTraderCode("1234");
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode(l_hostAccOpenAcceptParams.getInstitutionCode());
            l_branchParams.setBranchCode(l_hostAccOpenAcceptParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_callback.process();
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testProcessCase4()
    {
        final String STR_METHOD_NAME = "testProcessCase4()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                "notifyVoucherRegAccept", new Class[]{HostAccOpenAcceptParams.class },
                "1");
        
        try
        {      
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("22");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
         
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            //instId,branchCode,accountCode: 11,381,2512246
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setInstitutionId(11);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(11);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_InstitutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_InstitutionPreferencesParams.setInstitutionId(11);
            l_InstitutionPreferencesParams.setName("accountopendate.update.determination");
            l_InstitutionPreferencesParams.setNameSerialNo(1);
            l_InstitutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
            
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_ExpAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_ExpAccountOpenParams.setInstitutionCode("0D");
            l_ExpAccountOpenParams.setAccOpenRequestNumber("1");
            l_ExpAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_ExpAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ExpAccountOpenParams);
            
//            HostAccRegVoucher
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("1");//識別コード 
            l_hostAccRegVoucherParams.setRequestCode("GI82A");//データコード
            l_hostAccRegVoucherParams.setInstitutionCode("0D");//証券会社コード
            l_hostAccRegVoucherParams.setBranchCode("381");//部店コード
            l_hostAccRegVoucherParams.setAccountCode("2512246");//顧客コード
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostAccRegVoucherParams.setSerialNo("333");
            l_hostAccRegVoucherParams.setAccountName("亜");
            l_hostAccRegVoucherParams.setAccountNameKana("あ");
            l_hostAccRegVoucherParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setProamDiv("1");
            l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
            l_hostAccRegVoucherParams.setForeignerDivAviation("1");
            l_hostAccRegVoucherParams.setForeignerDivNtt("1");
            l_hostAccRegVoucherParams.setDividendTransferDiv("1");
            l_hostAccRegVoucherParams.setAgentDivPermanent("0");
            l_hostAccRegVoucherParams.setAgentDivLegal("0");
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
            l_hostAccOpenAcceptParams.setRequestCode("GI82A");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("2512246");
            l_hostAccOpenAcceptParams.setTraderCode("1234");
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode(l_hostAccOpenAcceptParams.getInstitutionCode());
            l_branchParams.setBranchCode(l_hostAccOpenAcceptParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_callback.process();
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testProcessCase5()
    {
        final String STR_METHOD_NAME = "testProcessCase5()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl",
                "notifyVoucherRegAccept", new Class[]{HostAccOpenAcceptParams.class },
                "1");
        
        try
        {      
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("22");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
         
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            //instId,branchCode,accountCode: 11,381,2512246
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_params = TestDBUtility.getMainAccountRow();
            l_params.setInstitutionId(11);
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_params);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionId(11);
            l_InstitutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_InstitutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_InstitutionPreferencesParams.setInstitutionId(11);
            l_InstitutionPreferencesParams.setName("accountopendate.update.determination");
            l_InstitutionPreferencesParams.setNameSerialNo(1);
            l_InstitutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
            
            //ExpAccountOpenRow
            TestDBUtility.deleteAll(ExpAccountOpenParams.TYPE);
            ExpAccountOpenParams l_ExpAccountOpenParams = TestDBUtility.getExpAccountOpenRow();
            l_ExpAccountOpenParams.setInstitutionCode("0D");
            l_ExpAccountOpenParams.setAccOpenRequestNumber("1");
            l_ExpAccountOpenParams.setAccountOpenDate(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_ExpAccountOpenParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ExpAccountOpenParams);
            
//            HostAccRegVoucher
            TestDBUtility.deleteAll(HostAccRegVoucherParams.TYPE);
            HostAccRegVoucherParams l_hostAccRegVoucherParams = new HostAccRegVoucherParams();
            l_hostAccRegVoucherParams.setOrderRequestNumber("1");//識別コード 
            l_hostAccRegVoucherParams.setRequestCode("GI84C");//データコード
            l_hostAccRegVoucherParams.setInstitutionCode("0D");//証券会社コード
            l_hostAccRegVoucherParams.setBranchCode("381");//部店コード
            l_hostAccRegVoucherParams.setAccountCode("2512246");//顧客コード
            l_hostAccRegVoucherParams.setAccOpenRequestNumber("1234567890123");
            l_hostAccRegVoucherParams.setSerialNo("333");
            l_hostAccRegVoucherParams.setAccountName("亜");
            l_hostAccRegVoucherParams.setAccountNameKana("あ");
            l_hostAccRegVoucherParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070731", "yyyyMMdd"));
            l_hostAccRegVoucherParams.setProamDiv("1");
            l_hostAccRegVoucherParams.setForeignerDivBroadcast("1");
            l_hostAccRegVoucherParams.setForeignerDivAviation("1");
            l_hostAccRegVoucherParams.setForeignerDivNtt("1");
            l_hostAccRegVoucherParams.setDividendTransferDiv("1");
            l_hostAccRegVoucherParams.setAgentDivPermanent("0");
            l_hostAccRegVoucherParams.setAgentDivLegal("0");
            TestDBUtility.insertWithDel(l_hostAccRegVoucherParams);
            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            HostAccOpenAcceptParams l_hostAccOpenAcceptParams = TestDBUtility.getHostAccOpenAcceptRow();
            l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
            l_hostAccOpenAcceptParams.setRequestCode("GI84C");
            l_hostAccOpenAcceptParams.setInstitutionCode("0D");
            l_hostAccOpenAcceptParams.setBranchCode("381");
            l_hostAccOpenAcceptParams.setAccountCode("2512246");
            l_hostAccOpenAcceptParams.setTraderCode("1234");
            l_hostAccOpenAcceptParams.setAcceptStatus("2");
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode(l_hostAccOpenAcceptParams.getInstitutionCode());
            l_branchParams.setBranchCode(l_hostAccOpenAcceptParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_callback.process();
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEleDeliveryManagementCase0001()
    {
        final String STR_METHOD_NAME = "testUpdateEleDeliveryManagementCase0001()";
        log.entering(STR_METHOD_NAME);
        
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams =
            TestDBUtility.getHostAccOpenAcceptRow();
        l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
        l_hostAccOpenAcceptParams.setRequestCode("GI84C");
        l_hostAccOpenAcceptParams.setInstitutionCode("0D");
        l_hostAccOpenAcceptParams.setBranchCode("381");
        l_hostAccOpenAcceptParams.setAccountCode("2512246");
        
        EleDeliveryManagementParams l_eleDeliveryManagementParams =
            TestDBUtility.getEleDeliveryManagementRow();
        l_eleDeliveryManagementParams.setInstitutionCode(l_hostAccOpenAcceptParams.getInstitutionCode());
        l_eleDeliveryManagementParams.setBranchCode(l_hostAccOpenAcceptParams.getBranchCode());
        l_eleDeliveryManagementParams.setAccountCode(l_hostAccOpenAcceptParams.getAccountCode());
        
        try
        {            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            TestDBUtility.deleteAllAndCommit(EleDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_eleDeliveryManagementParams);
            
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            l_callback.updateEleDeliveryManagement(l_hostAccOpenAcceptParams);
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEleDeliveryManagementCase0002()
    {
        final String STR_METHOD_NAME = "testUpdateEleDeliveryManagementCase0002()";
        log.entering(STR_METHOD_NAME);
        
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams =
            TestDBUtility.getHostAccOpenAcceptRow();
        l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
        l_hostAccOpenAcceptParams.setRequestCode("GI84C");
        l_hostAccOpenAcceptParams.setInstitutionCode("0D");
        l_hostAccOpenAcceptParams.setBranchCode("381");
        l_hostAccOpenAcceptParams.setAccountCode("2512246");
        
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            TestDBUtility.getHostConditionRegVoucherRow();
        l_hostConditionRegVoucherParams.setOrderRequestNumber("1");//識別コード 
        l_hostConditionRegVoucherParams.setRequestCode("GI843");//データコード
        l_hostConditionRegVoucherParams.setInstitutionCode("0D");//証券会社コード
        l_hostConditionRegVoucherParams.setBranchCode("381");//部店コード
        l_hostConditionRegVoucherParams.setAccountCode("2512246");//顧客コード
        
        EleDeliveryManagementParams l_eleDeliveryManagementParams =
            TestDBUtility.getEleDeliveryManagementRow();
        l_eleDeliveryManagementParams.setInstitutionCode(l_hostAccOpenAcceptParams.getInstitutionCode());
        l_eleDeliveryManagementParams.setBranchCode("33");
        l_eleDeliveryManagementParams.setAccountCode(l_hostAccOpenAcceptParams.getAccountCode());
        
        try
        {
            TestDBUtility.deleteAll(HostConditionRegVoucherParams.TYPE);
            TestDBUtility.insertWithDel(l_hostConditionRegVoucherParams);
            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            TestDBUtility.deleteAllAndCommit(EleDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_eleDeliveryManagementParams);
            
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            l_callback.updateEleDeliveryManagement(l_hostAccOpenAcceptParams);
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEleDeliveryManagementCase0003()
    {
        final String STR_METHOD_NAME = "testUpdateEleDeliveryManagementCase0003()";
        log.entering(STR_METHOD_NAME);
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2010);
        l_calendar.set(Calendar.MONTH,10);
        l_calendar.set(Calendar.DAY_OF_MONTH,11);
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,00);
        l_calendar.set(Calendar.MILLISECOND,00);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_tsBizDate);
        
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams =
            TestDBUtility.getHostAccOpenAcceptRow();
        l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
        l_hostAccOpenAcceptParams.setRequestCode("GI84C");
        l_hostAccOpenAcceptParams.setInstitutionCode("0D");
        l_hostAccOpenAcceptParams.setBranchCode("381");
        l_hostAccOpenAcceptParams.setAccountCode("2512246");
        
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            TestDBUtility.getHostConditionRegVoucherRow();
        l_hostConditionRegVoucherParams.setOrderRequestNumber("1");//識別コード 
        l_hostConditionRegVoucherParams.setRequestCode("GI843");//データコード
        l_hostConditionRegVoucherParams.setInstitutionCode("0D");//証券会社コード
        l_hostConditionRegVoucherParams.setBranchCode("381");//部店コード
        l_hostConditionRegVoucherParams.setAccountCode("2512246");//顧客コード
        //電子交付　@取引報告書
        l_hostConditionRegVoucherParams.setTradingEReportDiv("0");
        //取引残高報告書　@電子交付（都度）
        l_hostConditionRegVoucherParams.setPosReportCycleDiv("9");
        //電子交付　@投信運用報告書
        l_hostConditionRegVoucherParams.setInvEReportDiv("1");
        
        EleDeliveryManagementParams l_eleDeliveryManagementParams =
            TestDBUtility.getEleDeliveryManagementRow();
        l_eleDeliveryManagementParams.setInstitutionCode(l_hostAccOpenAcceptParams.getInstitutionCode());
        l_eleDeliveryManagementParams.setBranchCode(l_hostAccOpenAcceptParams.getBranchCode());
        l_eleDeliveryManagementParams.setAccountCode(l_hostAccOpenAcceptParams.getAccountCode());
        
        try
        {
            TestDBUtility.deleteAll(HostConditionRegVoucherParams.TYPE);
            TestDBUtility.insertWithDel(l_hostConditionRegVoucherParams);
            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            TestDBUtility.deleteAllAndCommit(EleDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_eleDeliveryManagementParams);
            
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            l_callback.updateEleDeliveryManagement(l_hostAccOpenAcceptParams);
            
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;

            l_eleDeliveryManagementRow =
                EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                    l_hostAccOpenAcceptParams.getInstitutionCode(),
                    l_hostAccOpenAcceptParams.getBranchCode(),
                    l_hostAccOpenAcceptParams.getAccountCode());
            
            assertEquals("9", l_eleDeliveryManagementRow.getTradingReportRegDiv());
            assertEquals(l_tsBizDate, l_eleDeliveryManagementRow.getTradingReportDivUpdDate());
            assertEquals("9", l_eleDeliveryManagementRow.getPositionReportRegDiv());
            assertEquals(l_tsBizDate, l_eleDeliveryManagementRow.getPositionReportDivUpdDate());
            assertEquals("9", l_eleDeliveryManagementRow.getOpeReportRegDiv());
            assertEquals(l_tsBizDate, l_eleDeliveryManagementRow.getOpeReportDivUpdDate());
            assertEquals("account_open", l_eleDeliveryManagementRow.getLastUpdater());
            assertEquals(l_tsBizDate, l_eleDeliveryManagementRow.getLastUpdatedTimestamp());
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEleDeliveryManagementCase0004()
    {
        final String STR_METHOD_NAME = "testUpdateEleDeliveryManagementCase0004()";
        log.entering(STR_METHOD_NAME);
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2010);
        l_calendar.set(Calendar.MONTH,10);
        l_calendar.set(Calendar.DAY_OF_MONTH,11);
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,00);
        l_calendar.set(Calendar.MILLISECOND,00);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            l_tsBizDate);
        
        HostAccOpenAcceptParams l_hostAccOpenAcceptParams =
            TestDBUtility.getHostAccOpenAcceptRow();
        l_hostAccOpenAcceptParams.setOrderRequestNumber("1");
        l_hostAccOpenAcceptParams.setRequestCode("GI84C");
        l_hostAccOpenAcceptParams.setInstitutionCode("0D");
        l_hostAccOpenAcceptParams.setBranchCode("381");
        l_hostAccOpenAcceptParams.setAccountCode("2512246");
        
        HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
            TestDBUtility.getHostConditionRegVoucherRow();
        l_hostConditionRegVoucherParams.setOrderRequestNumber("1");//識別コード 
        l_hostConditionRegVoucherParams.setRequestCode("GI843");//データコード
        l_hostConditionRegVoucherParams.setInstitutionCode("0D");//証券会社コード
        l_hostConditionRegVoucherParams.setBranchCode("381");//部店コード
        l_hostConditionRegVoucherParams.setAccountCode("2512246");//顧客コード
        //電子交付　@取引報告書
        l_hostConditionRegVoucherParams.setTradingEReportDiv(null);
        //取引残高報告書　@電子交付（都度）
        l_hostConditionRegVoucherParams.setPosReportCycleDiv(null);
        //電子交付　@投信運用報告書
        l_hostConditionRegVoucherParams.setInvEReportDiv(null);
        
        EleDeliveryManagementParams l_eleDeliveryManagementParams =
            TestDBUtility.getEleDeliveryManagementRow();
        l_eleDeliveryManagementParams.setInstitutionCode(l_hostAccOpenAcceptParams.getInstitutionCode());
        l_eleDeliveryManagementParams.setBranchCode(l_hostAccOpenAcceptParams.getBranchCode());
        l_eleDeliveryManagementParams.setAccountCode(l_hostAccOpenAcceptParams.getAccountCode());
        //取引報告書申込区分
        l_eleDeliveryManagementParams.setTradingReportRegDiv("0");
        //取引報告書交付区分更新日
        l_eleDeliveryManagementParams.setTradingReportDivUpdDate(l_tsBizDate);
        //取引残高報告書申込区分
        l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
        //取引残高報告書交付区分更新日
        l_eleDeliveryManagementParams.setPositionReportDivUpdDate(l_tsBizDate);
        //運用報告書申込区分
        l_eleDeliveryManagementParams.setOpeReportRegDiv("2");
        //運用報告書交付区分更新日
        l_eleDeliveryManagementParams.setOpeReportDivUpdDate(l_tsBizDate);
        
        try
        {
            TestDBUtility.deleteAll(HostConditionRegVoucherParams.TYPE);
            TestDBUtility.insertWithDel(l_hostConditionRegVoucherParams);
            
            TestDBUtility.deleteAllAndCommit(HostAccOpenAcceptParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_hostAccOpenAcceptParams);
            
            TestDBUtility.deleteAllAndCommit(EleDeliveryManagementParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_eleDeliveryManagementParams);
            
            WEB3AccOpenVoucherRegAcceptTransactionCallback l_callback =
                new WEB3AccOpenVoucherRegAcceptServiceImpl().new WEB3AccOpenVoucherRegAcceptTransactionCallback();
            
            l_callback.updateEleDeliveryManagement(l_hostAccOpenAcceptParams);
            
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;

            l_eleDeliveryManagementRow =
                EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                    l_hostAccOpenAcceptParams.getInstitutionCode(),
                    l_hostAccOpenAcceptParams.getBranchCode(),
                    l_hostAccOpenAcceptParams.getAccountCode());
            
            assertEquals("0", l_eleDeliveryManagementRow.getTradingReportRegDiv());
            assertEquals(l_tsBizDate, l_eleDeliveryManagementRow.getTradingReportDivUpdDate());
            assertEquals("1", l_eleDeliveryManagementRow.getPositionReportRegDiv());
            assertEquals(l_tsBizDate, l_eleDeliveryManagementRow.getPositionReportDivUpdDate());
            assertEquals("2", l_eleDeliveryManagementRow.getOpeReportRegDiv());
            assertEquals(l_tsBizDate, l_eleDeliveryManagementRow.getOpeReportDivUpdDate());
            assertEquals("account_open", l_eleDeliveryManagementRow.getLastUpdater());
            assertEquals(l_tsBizDate, l_eleDeliveryManagementRow.getLastUpdatedTimestamp());
        }
        catch (DataCallbackException e)
        {
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
