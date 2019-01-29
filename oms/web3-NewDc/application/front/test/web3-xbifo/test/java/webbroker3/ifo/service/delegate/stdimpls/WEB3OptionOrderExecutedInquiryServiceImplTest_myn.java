head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderExecutedInquiryServiceImplTest_myn.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�������Ɖ�T�[�r�XImpl(WEB3OptionOrderExecutedInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/06 �И��� (���u) �V�K�쐬
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteGroup;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOrderExecutedInquiryServiceImplTest_myn extends TestBaseForMock
{

    public WEB3OptionOrderExecutedInquiryServiceImplTest_myn(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        TestDBUtility.deleteAll(IfoProductParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
        TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
        TestDBUtility.deleteAll(CalendarParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        TestDBUtility.deleteAll(SubAccountRow.TYPE);
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(IfoProductRow.TYPE);
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.deleteAll(CalendarRow.TYPE);
        super.setUp();
        TradingCalendarDetails tradingCalendarDetails =
            new WEB3GentradeTradingClendarDetailsImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getTradingCalendarDetails",
                new Class[] {long.class},
                tradingCalendarDetails);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                WEB3DateUtility.getDate("20070619","yyyyMMdd"));
    }
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionOrderExecutedInquiryServiceImplTest_myn.class);
        
    /**
     * validate�̐��������`�F�b�N
     * date:
     * this.�Ɖ�敪��null
     * �e�X�g�m�F���e:
     * WEB3ErrorCatalog.BUSINESS_ERROR_00081
     */
    public void test_getOrderExecutedInquiry_0001()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00081,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
     * get����(Institution, String)
     * �Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B 
     * 
     * �e�X�g�m�F���e:
     * WEB3ErrorCatalog.BUSINESS_ERROR_00301 
     */
    public void test_getOrderExecutedInquiry_0002()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0002()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g�f�[�^�ɖ������荀��(����s��A�w����ʁA�����A�I�v�V�������i�敪�A�s�g���i)���ݒ肳��Ă���ꍇ
     * get����(Institution, String, String, String, double, String, String)
     * �I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�́A��O���X���[���� 
     * 
     * �e�X�g�m�F���e:
     * WEB3ErrorCatalog.BUSINESS_ERROR_00301 
     */
    public void test_getOrderExecutedInquiry_0003()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0003()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";        
        
        //����s��
        l_request.marketCode = "a";
        //�w�����
        l_request.targetProductCode = "0005";
        //�s�g���i
        l_request.strikePrice = "12";
        //����
        l_request.delivaryMonth = "20080808";
        //�I�v�V�������i�敪
        l_request.opProductType = "C";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ" &&
     * is����\�ڋq(�⏕����) = false
     * 
     * �e�X�g�m�F���e:
     * ��O��throw����
     * WEB3ErrorCatalog.BUSINESS_ERROR_00275
     */
    public void test_getOrderExecutedInquiry_0004()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0004()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.CLOSED);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * ���N�G�X�g�f�[�^.�Ɖ�敪="�������Ɖ�"�̏ꍇ
     * validate������t�\
     * 
     * �e�X�g�m�F���e:
     * ��O��throw����
     * WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void test_getOrderExecutedInquiry_0005()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0005()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="�������Ɖ�"
        l_request.referenceType = "0";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.CLOSED);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * ���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"�̏ꍇ
     * validate������t�\( )(�����F������t�s��)
     * 
     * �e�X�g�m�F���e:
     * ��O��throw����
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void test_getOrderExecutedInquiry_0006()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0006()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lki");
            l_clendarContext.setOrderAcceptTransaction("07");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"�̏ꍇ
     * validate������t�\( )(����F������t�s��)
     * 
     * �e�X�g�m�F���e:
     * ��O��throw����
     * WEB3ErrorCatalog.BUSINESS_ERROR_00011
     */
    public void test_getOrderExecutedInquiry_0007()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0007()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams.setInstitutionCode("1");
            l_orderAcceptStatusParams.setBranchCode("ncn");
            l_orderAcceptStatusParams.setOrderAccProduct("12");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            
            //
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams1.setInstitutionCode("1");
            l_orderAcceptStatusParams1.setBranchCode("ncn");
            l_orderAcceptStatusParams1.setOrderAccProduct("12");
            l_orderAcceptStatusParams1.setOrderAccTransaction("05");
            
            //
            l_orderAcceptStatusParams1.setOrderAcceptStatus("1");
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"�̏ꍇ
     * validate������t�\( )(����F������t�s��)
     * 
     * �e�X�g�m�F���e:
     * ��O��throw����
     * WEB3ErrorCatalog.BUSINESS_ERROR_00012
     */
    public void test_getOrderExecutedInquiry_0008()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0008()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams.setInstitutionCode("1");
            l_orderAcceptStatusParams.setBranchCode("ncn");
            l_orderAcceptStatusParams.setOrderAccProduct("12");
            l_orderAcceptStatusParams.setOrderAccTransaction("06");
            
            //
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams1.setInstitutionCode("1");
            l_orderAcceptStatusParams1.setBranchCode("ncn");
            l_orderAcceptStatusParams1.setOrderAccProduct("12");
            l_orderAcceptStatusParams1.setOrderAccTransaction("05");
            
            //
            l_orderAcceptStatusParams1.setOrderAcceptStatus("2");
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00012,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"�̏ꍇ
     * validate������t�\( )(����F������t�s��)
     * 
     * �e�X�g�m�F���e:
     * ��O��throw����
     * WEB3ErrorCatalog.BUSINESS_ERROR_00013
     */
    public void test_getOrderExecutedInquiry_0009()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0009()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            
            l_tradingTimeParams.setEnableOrder("1");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams.setInstitutionCode("1");
            l_orderAcceptStatusParams.setBranchCode("ncn");
            l_orderAcceptStatusParams.setOrderAccProduct("12");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            
            //
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams1.setInstitutionCode("1");
            l_orderAcceptStatusParams1.setBranchCode("ncn");
            l_orderAcceptStatusParams1.setOrderAccProduct("12");
            l_orderAcceptStatusParams1.setOrderAccTransaction("06");
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            l_service.getOrderExecutedInquiry(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00013,e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create�����R�[�h���̂̕Ԃ�l��NULL�̏ꍇ�A
     * is����\�ڋq�̕Ԃ�l��true
     * �����b�N�ڋq�̏ꍇ�Ffalse
     * �e�X�g�m�F���e:
     */
    public void test_getOrderExecutedInquiry_0010()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0010()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImplForTest l_service = new WEB3OptionOrderExecutedInquiryServiceImplForTest();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams.setInstitutionCode("1");
            l_orderAcceptStatusParams.setBranchCode("ncn");
            l_orderAcceptStatusParams.setOrderAccProduct("12");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            
            //
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams1.setInstitutionCode("1");
            l_orderAcceptStatusParams1.setBranchCode("ncn");
            l_orderAcceptStatusParams1.setOrderAccProduct("12");
            l_orderAcceptStatusParams1.setOrderAccTransaction("06");
            
            BranchIndexDealtCondParams l_BranchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_BranchIndexDealtCondParams.setInstitutionCode("1");
            l_BranchIndexDealtCondParams.setBranchCode("381");
            l_BranchIndexDealtCondParams.setFutureOptionDiv("2");
            l_BranchIndexDealtCondParams.setEnableOrder("1");
            l_BranchIndexDealtCondParams.setTargetProductCode("bcs");
            
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setHoliday(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            TestDBUtility.insertWithDel(l_BranchIndexDealtCondParams);
            TestDBUtility.insertWithDel(l_calendarParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            l_clendarContext.setProductCode("bcs");
            l_clendarContext.setTradingTimeType("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20090201", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
            
            
//            Date l_tsOrderAcceptDate = WEB3DateUtility.getDate("20090201", "yyyyMMdd");
//             ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);
        }
        catch (WEB3BaseException e1)
        {
//            log.debug("" + e1);
            log.debug(e1.getMessage(), e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            WEB3OptionsExecuteReferenceResponse l_response = l_service.getOrderExecutedInquiry(l_request);
            assertNull(l_response.opExecuteGroups);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.pageIndex);
            assertNull(l_response.idList);
            assertNull(l_response.futOpProductCodeNames);
            assertFalse(l_response.accountLock);
            
        }
        catch (WEB3BaseException e)
        {
            log.debug(e.getMessage(), e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create�����R�[�h���̂̕Ԃ�l��NULL�̏ꍇ�A
     * is����\�ڋq�̕Ԃ�l��false
     * �����b�N�ڋq�̏ꍇ�Ftrue
     * �e�X�g�m�F���e:
     */
    public void test_getOrderExecutedInquiry_0011()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0011()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImplForTest l_service = new WEB3OptionOrderExecutedInquiryServiceImplForTest();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "0";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.CLOSED);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1");
            l_tradingTimeParams1.setBranchCode("ncn");
            l_tradingTimeParams1.setTradingTimeType("12");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("bcs");
            
//            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            
//            l_orderAcceptStatusParams.setInstitutionCode("1");
//            l_orderAcceptStatusParams.setBranchCode("ncn");
//            l_orderAcceptStatusParams.setOrderAccProduct("12");
//            l_orderAcceptStatusParams.setOrderAccTransaction("05");
//            
//            //
//            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
//            
//            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
//            
//            l_orderAcceptStatusParams1.setInstitutionCode("1");
//            l_orderAcceptStatusParams1.setBranchCode("ncn");
//            l_orderAcceptStatusParams1.setOrderAccProduct("12");
//            l_orderAcceptStatusParams1.setOrderAccTransaction("06");
            
            BranchIndexDealtCondParams l_BranchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_BranchIndexDealtCondParams.setInstitutionCode("0D");
            l_BranchIndexDealtCondParams.setBranchCode("381");
            l_BranchIndexDealtCondParams.setFutureOptionDiv("2");
            l_BranchIndexDealtCondParams.setEnableOrder("1");
            l_BranchIndexDealtCondParams.setTargetProductCode("bcs");
            
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setHoliday(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            TestDBUtility.insertWithDel(l_BranchIndexDealtCondParams);
            TestDBUtility.insertWithDel(l_calendarParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            l_clendarContext.setProductCode("bcs");
            l_clendarContext.setTradingTimeType("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20090201", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
            
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3OptionsExecuteReferenceResponse l_response = l_service.getOrderExecutedInquiry(l_request);
            assertNull(l_response.opExecuteGroups);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.pageIndex);
            assertNull(l_response.idList);
            assertNull(l_response.futOpProductCodeNames);
            assertTrue(l_response.accountLock);
            
        }
        catch (WEB3BaseException e)
        {
            log.debug(e.getMessage(), e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create�������Ɖ�̕Ԃ�l��NULL�̏ꍇ�A
     * is����\�ڋq�̕Ԃ�l��true
     * �����b�N�ڋq�̏ꍇ�Ffalse
     * �e�X�g�m�F���e:
     */
    public void test_getOrderExecutedInquiry_0012()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0012()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl1ForTest l_service = new WEB3OptionOrderExecutedInquiryServiceImpl1ForTest();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1");
            l_tradingTimeParams1.setBranchCode("ncn");
            l_tradingTimeParams1.setTradingTimeType("12");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("bcs");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams.setInstitutionCode("1");
            l_orderAcceptStatusParams.setBranchCode("ncn");
            l_orderAcceptStatusParams.setOrderAccProduct("12");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            
            //
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams1.setInstitutionCode("1");
            l_orderAcceptStatusParams1.setBranchCode("ncn");
            l_orderAcceptStatusParams1.setOrderAccProduct("12");
            l_orderAcceptStatusParams1.setOrderAccTransaction("06");
            
            BranchIndexDealtCondParams l_BranchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_BranchIndexDealtCondParams.setInstitutionCode("0D");
            l_BranchIndexDealtCondParams.setBranchCode("381");
            l_BranchIndexDealtCondParams.setFutureOptionDiv("2");
            l_BranchIndexDealtCondParams.setEnableOrder("1");
            l_BranchIndexDealtCondParams.setTargetProductCode("bcs");
            
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setHoliday(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            TestDBUtility.insertWithDel(l_BranchIndexDealtCondParams);
            TestDBUtility.insertWithDel(l_calendarParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            l_clendarContext.setProductCode("bcs");
            l_clendarContext.setTradingTimeType("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20090201", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            WEB3OptionsExecuteReferenceResponse l_response = l_service.getOrderExecutedInquiry(l_request);
            assertNull(l_response.opExecuteGroups);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.pageIndex);
            assertNull(l_response.idList);
            assertEquals("1",l_response.futOpProductCodeNames[0].productCode);
            assertFalse(l_response.accountLock);
            
        }
        catch (WEB3BaseException e)
        {
            log.debug(e.getMessage(), e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create�������Ɖ�̕Ԃ�l��NULL�̏ꍇ�A
     * is����\�ڋq�̕Ԃ�l��false
     * �����b�N�ڋq�̏ꍇ�Ftrue
     * �e�X�g�m�F���e:
     */
    public void test_getOrderExecutedInquiry_0013()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0013()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl1ForTest l_service = new WEB3OptionOrderExecutedInquiryServiceImpl1ForTest();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "0";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.CLOSED);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1");
            l_tradingTimeParams1.setBranchCode("ncn");
            l_tradingTimeParams1.setTradingTimeType("12");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("bcs");
            
//            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            
//            l_orderAcceptStatusParams.setInstitutionCode("1");
//            l_orderAcceptStatusParams.setBranchCode("ncn");
//            l_orderAcceptStatusParams.setOrderAccProduct("12");
//            l_orderAcceptStatusParams.setOrderAccTransaction("05");
//            
//            //
//            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
//            
//            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
//            
//            l_orderAcceptStatusParams1.setInstitutionCode("1");
//            l_orderAcceptStatusParams1.setBranchCode("ncn");
//            l_orderAcceptStatusParams1.setOrderAccProduct("12");
//            l_orderAcceptStatusParams1.setOrderAccTransaction("06");
            
            BranchIndexDealtCondParams l_BranchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_BranchIndexDealtCondParams.setInstitutionCode("0D");
            l_BranchIndexDealtCondParams.setBranchCode("381");
            l_BranchIndexDealtCondParams.setFutureOptionDiv("2");
            l_BranchIndexDealtCondParams.setEnableOrder("1");
            l_BranchIndexDealtCondParams.setTargetProductCode("bcs");
            
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setHoliday(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            TestDBUtility.insertWithDel(l_BranchIndexDealtCondParams);
            TestDBUtility.insertWithDel(l_calendarParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            l_clendarContext.setProductCode("bcs");
            l_clendarContext.setTradingTimeType("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20090201", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3OptionsExecuteReferenceResponse l_response = l_service.getOrderExecutedInquiry(l_request);
            assertNull(l_response.opExecuteGroups);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.pageIndex);
            assertNull(l_response.idList);
            assertEquals("1",l_response.futOpProductCodeNames[0].productCode);
            assertTrue(l_response.accountLock);
        }
        catch (WEB3BaseException e)
        {
            log.debug(e.getMessage(), e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create�������Ɖ�̕Ԃ�l��!=NULL�̏ꍇ�A
     * create�����R�[�h���̂̕Ԃ�l�� !=NULL�̏ꍇ�A
     * is����\�ڋq�̕Ԃ�l��true
     * �����b�N�ڋq�̏ꍇ�Ffalse
     * �e�X�g�m�F���e:
     */
    public void test_getOrderExecutedInquiry_0014()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0014()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl2ForTest l_service = new WEB3OptionOrderExecutedInquiryServiceImpl2ForTest();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.referenceType = "1";
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "1";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1");
            l_tradingTimeParams1.setBranchCode("ncn");
            l_tradingTimeParams1.setTradingTimeType("12");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("bcs");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams.setInstitutionCode("1");
            l_orderAcceptStatusParams.setBranchCode("ncn");
            l_orderAcceptStatusParams.setOrderAccProduct("12");
            l_orderAcceptStatusParams.setOrderAccTransaction("05");
            
            //
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            
            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
            
            l_orderAcceptStatusParams1.setInstitutionCode("1");
            l_orderAcceptStatusParams1.setBranchCode("ncn");
            l_orderAcceptStatusParams1.setOrderAccProduct("12");
            l_orderAcceptStatusParams1.setOrderAccTransaction("06");
            
            BranchIndexDealtCondParams l_BranchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_BranchIndexDealtCondParams.setInstitutionCode("0D");
            l_BranchIndexDealtCondParams.setBranchCode("381");
            l_BranchIndexDealtCondParams.setFutureOptionDiv("2");
            l_BranchIndexDealtCondParams.setEnableOrder("1");
            l_BranchIndexDealtCondParams.setTargetProductCode("bcs");
            
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setHoliday(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            TestDBUtility.insertWithDel(l_BranchIndexDealtCondParams);
            TestDBUtility.insertWithDel(l_calendarParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            l_clendarContext.setProductCode("bcs");
            l_clendarContext.setTradingTimeType("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20090201", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        
        try
        {
            WEB3OptionsExecuteReferenceResponse l_response = l_service.getOrderExecutedInquiry(l_request);
            assertTrue(l_response.opExecuteGroups[0].cancelFlag);
            assertEquals("2",l_response.futOpProductCodeNames[0].productCode);
            assertFalse(l_response.accountLock);
            
        }
        catch (WEB3BaseException e)
        {
            log.debug(e.getMessage(), e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * create�������Ɖ�̕Ԃ�l��!=NULL�̏ꍇ�A
     * create�����R�[�h���̂̕Ԃ�l�� !=NULL�̏ꍇ�A
     * is����\�ڋq�̕Ԃ�l��false
     * �����b�N�ڋq�̏ꍇ�Ftrue
     * �e�X�g�m�F���e:
     */
    public void test_getOrderExecutedInquiry_0015()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedInquiry_0015()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
        l_sortKey.ascDesc = "A";
        l_sortKey.keyItem = "opProductCode";
        WEB3FuturesOptionsSortKey[] futOpSortKeys = {l_sortKey};
        
        WEB3OptionOrderExecutedInquiryServiceImpl2ForTest l_service = new WEB3OptionOrderExecutedInquiryServiceImpl2ForTest();
        
        WEB3OptionsExecuteReferenceRequest l_request = new WEB3OptionsExecuteReferenceRequest();
        
        l_request.futOpSortKeys = futOpSortKeys;
        l_request.execType = "1";
        l_request.pageSize = "1";
        l_request.pageIndex = "1";
        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        l_request.opProductCode = "abc";
        
        //���N�G�X�g�f�[�^.�Ɖ�敪="��������ꗗ"
        l_request.referenceType = "0";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(OrderAcceptStatusParams.TYPE);
            TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.CLOSED);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setInstitutionId(33);
            InstitutionParams l_institutionParam = TestDBUtility.getInstitutionRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductCode("abc");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("bcs");
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("1");
            l_tradingTimeParams.setBranchCode("ncn");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("lk");
            l_tradingTimeParams.setProductCode("bcs");
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1");
            l_tradingTimeParams1.setBranchCode("ncn");
            l_tradingTimeParams1.setTradingTimeType("12");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("bcs");
//            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            
//            l_orderAcceptStatusParams.setInstitutionCode("1");
//            l_orderAcceptStatusParams.setBranchCode("ncn");
//            l_orderAcceptStatusParams.setOrderAccProduct("12");
//            l_orderAcceptStatusParams.setOrderAccTransaction("05");
//            
//            //
//            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
//            
//            OrderAcceptStatusParams l_orderAcceptStatusParams1 = TestDBUtility.getOrderAcceptStatusRow();
//            
//            l_orderAcceptStatusParams1.setInstitutionCode("1");
//            l_orderAcceptStatusParams1.setBranchCode("ncn");
//            l_orderAcceptStatusParams1.setOrderAccProduct("12");
//            l_orderAcceptStatusParams1.setOrderAccTransaction("06");
            
            BranchIndexDealtCondParams l_BranchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_BranchIndexDealtCondParams.setInstitutionCode("0D");
            l_BranchIndexDealtCondParams.setBranchCode("381");
            l_BranchIndexDealtCondParams.setFutureOptionDiv("2");
            l_BranchIndexDealtCondParams.setEnableOrder("1");
            l_BranchIndexDealtCondParams.setTargetProductCode("bcs");
            
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setHoliday(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParam);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams1);
            
            TestDBUtility.insertWithDel(l_BranchIndexDealtCondParams);
            TestDBUtility.insertWithDel(l_calendarParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1");
            l_clendarContext.setBranchCode("ncn");
            l_clendarContext.setMarketCode("lk");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setOrderAcceptProduct("12");
            l_clendarContext.setProductCode("bcs");
            l_clendarContext.setTradingTimeType("12");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.tradingcalendarcontext",l_clendarContext);
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20090201", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
        }
        catch (WEB3BaseException e1)
        {
            log.debug("" + e1);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3OptionsExecuteReferenceResponse l_response = l_service.getOrderExecutedInquiry(l_request);
            assertTrue(l_response.opExecuteGroups[0].cancelFlag);
            assertEquals("2",l_response.futOpProductCodeNames[0].productCode);
            assertTrue(l_response.accountLock);
        }
        catch (WEB3BaseException e)
        {
            log.debug(e.getMessage(), e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3OptionOrderExecutedInquiryServiceImplForTest extends WEB3OptionOrderExecutedInquiryServiceImpl
    {
        protected WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeName
            (WEB3GentradeSubAccount l_subAccount, 
            WEB3OptionsExecuteReferenceRequest l_request)
            throws WEB3BaseException
        {
            return null;
        }
    }
    
    class WEB3OptionOrderExecutedInquiryServiceImpl1ForTest extends WEB3OptionOrderExecutedInquiryServiceImpl
    {
        protected WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeName
            (WEB3GentradeSubAccount l_subAccount, 
            WEB3OptionsExecuteReferenceRequest l_request)
            throws WEB3BaseException
        {
            WEB3FuturesOptionsProductCodeNameUnit l_unit = new WEB3FuturesOptionsProductCodeNameUnit();
            l_unit.productCode = "1";
            WEB3FuturesOptionsProductCodeNameUnit[] l_units = {l_unit};
            return l_units;
        }
        
        protected WEB3OptionsExecuteGroup[] createOrderExecutedInquiry
            (WEB3GentradeSubAccount l_subAccount, 
            WEB3OptionsExecuteReferenceRequest l_request,
            WEB3OptionsExecuteReferenceResponse l_response,
            WEB3IfoProductImpl l_ifoProduct)
            throws WEB3BaseException
        {
            return null;
        }
    }
    
//    /**
//     * (get�������ڍ�)
//     * �敨OP�f�[�^�A�_�v�^.get���������敪() = 2�F�o����܂Œ��� �̏ꍇ
//     * ���X�|���X.�����L������ = GtlUtils.getSystemTimestamp()
//     * 
//     * �[��O�J�z�Ώۃt���O = false
//     * ����敪 = 1
//     */
//    public void test_getOrderExecutedDetail_0111()
//    {
//        final String STR_METHOD_NAME = "test_getOrderExecutedDetail_0111()";
//        log.entering(STR_METHOD_NAME);
//        
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        
//        WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
//        l_request.id = "1001";
//        WEB3OptionOrderExecutedInquiryServiceImpl l_impl = new WEB3OptionOrderExecutedInquiryServiceImpl();
//        
//        try
//        {
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            IfoOrderUnitParams l_ifoOrderUnitParams = null;
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            
//            l_mainAccountParams.setAccountId(101001010010L);
//            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
//            
//            ProductParams l_productParams = getProductRow();
//            IfoProductParams l_ifoProductParams = getIfoProductRow();
//            
//            l_ifoOrderUnitParams = ifoOrderUnit();
//            l_ifoOrderUnitParams.setOrderRootDiv("9");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
//            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
//            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
//            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getSystemTimestamp());
//            l_ifoOrderUnitParams.setSessionType("1");
//            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
//            
//            long l_lngOrderId = 1001;
//            l_ifoOrderParams.setOrderId(l_lngOrderId);
//            l_ifoOrderParams.setAccountId(101001010010L);
//            l_ifoOrderParams.setSubAccountId(10100101001007L);
//            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
//            l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
//            
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_productParams);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            
//            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
//            l_context.setInstitutionCode("0D");
//            l_context.setMarketCode("SP");
//            l_context.setBranchCode("381");
//            l_context.setProductCode("0");
//            l_context.setBizDateType("1");
//            l_context.setTradingTimeType("26");
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "web3.tradingcalendarcontext",
//                l_context);
//            
//            Timestamp l_tsBizDate1 = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
//
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate1);
//            
//            CalendarParams l_calendarParams = new CalendarParams();
//            l_calendarParams.setHoliday(l_tsBizDate1);
//            l_calendarParams.setBizDateType("1");
//            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            
//            TestDBUtility.deleteAll(CalendarRow.TYPE);
//            TestDBUtility.insertWithDel(l_calendarParams);
//            
//            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
//
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("381");
//            l_tradingTimeParams.setMarketCode("SP");
//            l_tradingTimeParams.setTradingTimeType("26");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setStartTime("000000");
//            l_tradingTimeParams.setEndTime("235959");
//            l_tradingTimeParams.setSubmitMarketTrigger("0");
//            l_tradingTimeParams.setEnableOrder("0");
//            l_tradingTimeParams.setBizdateCalcParameter("1");
//            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381L);
//            
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(1002);
//            TestDBUtility.deleteAll(MarketParams.TYPE);
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(101001010010L));
//            
//            WEB3OptionsExecuteDetailsResponse l_response = l_impl.getOrderExecutedDetail(l_request);
//            
//            assertEquals("0","" + WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(),l_response.expirationDate));
//            assertFalse(l_response.eveningSessionCarryoverFlag);
//            assertEquals("1",l_response.sessionType);
//            
//            
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
     * this.�h�c��null
     * 
     * �e�X�g�m�F���e
     * WEB3ErrorCatalog.BUSINESS_ERROR_00080
     */
    public void test_getOrderExecutedDetail_0001()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedDetail_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
        //this.�h�c
        l_request.id = null;
        
        try
        {
            l_service.getOrderExecutedDetail(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080,e.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B
     * ���X�R�[�h = null
     * 
     * �e�X�g�m�F���e
     * WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void test_getOrderExecutedDetail_0002()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedDetail_0002()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
        //this.�h�c
        l_request.id = "1001";
        
        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            
            l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getSystemTimestamp());
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            
            long l_lngOrderId = 1001;
            l_ifoOrderParams.setOrderId(l_lngOrderId);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);

            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setMarketCode("SP");
            l_context.setBranchCode(null);
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setTradingTimeType("26");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_context);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            l_service.getOrderExecutedDetail(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006,e.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �����P�ʂɂЂ��Â��g�����U�N�V�����̈ꗗ���擾����B
     * 
     * �e�X�g�m�F���e
     * ���X�|���X.�萔���@@= 356
     * ���X�|���X.����Ł@@= 563
     */
    public void test_getOrderExecutedDetail_0003()
    {
        final String STR_METHOD_NAME = "test_getOrderExecutedDetail_0003()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3OptionOrderExecutedInquiryServiceImpl l_service = new WEB3OptionOrderExecutedInquiryServiceImpl();
        
        WEB3OptionsExecuteDetailsRequest l_request = new WEB3OptionsExecuteDetailsRequest();
        //this.�h�c
        l_request.id = "1001";
        
        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            
            ProductParams l_productParams = getProductRow();
            IfoProductParams l_ifoProductParams = getIfoProductRow();
            //�敨�I�v�V�������i 
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            
            l_ifoOrderUnitParams = ifoOrderUnit();
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setFirstOrderUnitId(0);
            l_ifoOrderUnitParams.setExpirationDate(GtlUtils.getSystemTimestamp());
            l_ifoOrderUnitParams.setSessionType("1");
            l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            l_ifoOrderUnitParams.setExecutedAmount(1);
            
            //�����P��.isUnexecuted��false
            l_ifoOrderUnitParams.setExecutedQuantity(9D);
            
            //isMarketOrder = true
            l_ifoOrderUnitParams.setLimitPrice(0);
            
            long l_lngOrderId = 1001;
            l_ifoOrderParams.setOrderId(l_lngOrderId);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setMarketCode("SP");
            l_context.setBranchCode("381");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setTradingTimeType("26");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_context);
            
            Timestamp l_tsBizDate1 = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate1);
            
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(l_tsBizDate1);
            l_calendarParams.setBizDateType("1");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_calendarParams);
            
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setTradedProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setTradedProductId(1006169090018L);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            IfoFinTransactionParams l_ifoFinTransactionParams = new IfoFinTransactionParams();
            l_ifoFinTransactionParams.setCommissionFee(356);
            l_ifoFinTransactionParams.setCommissionFeeTax(563);
            
            List l_lst = new ArrayList();
            l_lst.add(l_ifoFinTransactionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(101001010010L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl", "getTransactions", 
                    new Class[]{ OrderUnit.class },
                    l_lst);
            

            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(1006169090018L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);
            l_service.getOrderExecutedDetail(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3OptionOrderExecutedInquiryServiceImpl2ForTest extends WEB3OptionOrderExecutedInquiryServiceImpl
    {
        protected WEB3FuturesOptionsProductCodeNameUnit[] createProductCodeName
            (WEB3GentradeSubAccount l_subAccount, 
            WEB3OptionsExecuteReferenceRequest l_request)
            throws WEB3BaseException
        {
            WEB3FuturesOptionsProductCodeNameUnit l_unit = new WEB3FuturesOptionsProductCodeNameUnit();
            l_unit.productCode = "2";
            WEB3FuturesOptionsProductCodeNameUnit[] l_units = {l_unit};
            return l_units;
        }
        
        protected WEB3OptionsExecuteGroup[] createOrderExecutedInquiry
            (WEB3GentradeSubAccount l_subAccount, 
            WEB3OptionsExecuteReferenceRequest l_request,
            WEB3OptionsExecuteReferenceResponse l_response,
            WEB3IfoProductImpl l_ifoProduct)
            throws WEB3BaseException
        {
            WEB3OptionsExecuteGroup l_executeGroup = new WEB3OptionsExecuteGroup();
            l_executeGroup.cancelFlag = true;
            WEB3OptionsExecuteGroup[] l_executeGroups = {l_executeGroup};
            
            l_response.opExecuteGroups = l_executeGroups;
            l_response.idList = new String[]{"22"};
            
            return l_executeGroups;
        }
    }
    
    /**
     * ����Row���쐬.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(1006169090018L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        l_productParams.setStandardName("�V���Z���e���X");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }
    
    /**
     * �敨OP�����e�[�u�� (ifo_product)
     */
    public static IfoProductParams getIfoProductRow()
    {
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setInstitutionCode("10");
        l_ifoProductParams.setProductCode("160030005");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }
    
    /**
     * ���������e�[�u�� (ifo_order_action)
     */
    public static IfoOrderActionParams getIfoOrderActionRow()
    {
        IfoOrderActionParams l_ifoOrderActionParams = new IfoOrderActionParams();
        
        l_ifoOrderActionParams.setOrderActionId(10);
        l_ifoOrderActionParams.setAccountId(101001010010L);
        l_ifoOrderActionParams.setSubAccountId(10100101001007L);
        l_ifoOrderActionParams.setOrderId(1001);
        l_ifoOrderActionParams.setOrderUnitId(1001);
        l_ifoOrderActionParams.setOrderType(OrderTypeEnum.ASSET_IN);
        l_ifoOrderActionParams.setOrderEventType(OrderEventTypeEnum.UNDEFINED);
        l_ifoOrderActionParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        
        l_ifoOrderActionParams.setExpirationDate(Calendar.getInstance().getTime());
        
        l_ifoOrderActionParams.setQuantity(1);
        l_ifoOrderActionParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
        l_ifoOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
        l_ifoOrderActionParams.setOrderActionSerialNo(45);
        l_ifoOrderActionParams.setProductId(1006169090018L);
        l_ifoOrderActionParams.setOrderActionSerialNo(1);
        l_ifoOrderActionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoOrderActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoOrderActionParams;
    }
    
    public IfoOrderUnitParams ifoOrderUnit()
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(1001);
        l_params.setAccountId(101001010010L);
        l_params.setSubAccountId(10100101001007L);
        l_params.setBranchId(33381);
        l_params.setTraderId(null);
        l_params.setOrderId(1001);
        l_params.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_params.setLastOrderActionSerialNo(1);
        l_params.setLastExecutionSerialNo(0);
        l_params.setProductType(ProductTypeEnum.IFO);
        l_params.setFutureOptionDiv("1");
        l_params.setMarketId(1002);
        l_params.setQuantity(100);
        l_params.setLimitPrice(0);
        l_params.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setOrderConditionType("2");
        l_params.setOrderCondOperator(null);
        l_params.setStopPriceType(null);
        l_params.setStopOrderPrice(null);
        l_params.setWLimitPrice(null);
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20040101");
        l_params.setProductId(1006169090018L);
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderRequestNumber("000003006");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");
        
        l_params.setConfirmedExecConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setConfirmedQuantity(100);
        return l_params;
    }
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
            // TODO Auto-generated constructor stub
        }
        
    }
}
@
