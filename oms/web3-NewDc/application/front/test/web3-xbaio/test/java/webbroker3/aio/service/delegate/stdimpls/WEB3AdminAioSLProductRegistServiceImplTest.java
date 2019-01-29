head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminAioSLProductRegistServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/26 �����i���u�j�V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.SecurityProductParams;
import webbroker3.aio.data.SecurityProductRow;
import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputResponse;
import webbroker3.aio.message.WEB3AioCashinSettleCancelRequest;
import webbroker3.aio.message.WEB3SLProductInfoUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLProductRegistServiceImplTest extends TestBaseForMock
{
    private WEB3AdminAioSLProductRegistServiceImpl l_service = null;
    
    private WEB3AdminSLProductRegistInputRequest l_inputRequest = null;
    
    private WEB3AdminSLProductRegistConfirmRequest l_confirmRequest = null;

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminAioSLProductRegistServiceImplTest.class);

    public WEB3AdminAioSLProductRegistServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_service = new WEB3AdminAioSLProductRegistServiceImpl();
        this.l_inputRequest = new WEB3AdminSLProductRegistInputRequest();
        this.l_confirmRequest = new WEB3AdminSLProductRegistConfirmRequestForTest();
        this.initData();
        this.getMockData();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_inputRequest = null;
        this.l_confirmRequest = null;
        this.l_service = null;
        super.tearDown();
    }
    
    /**
     * �p�����[�^�l�s���̏ꍇ
     * �e�o�FSYSTEM_ERROR_80017�ُ�M��
     *
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_service.execute(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^���̓��N�G�X�g�̏ꍇ
     * get�S�ۖ������͉��()���R�[������
     * ���팋��:�����u���c�Ɠ����t�v
     *
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_expectedCalendar = Calendar.getInstance();
            l_expectedCalendar.set(2007,8,27);
            
            WEB3AdminSLProductRegistInputResponse l_response =
                (WEB3AdminSLProductRegistInputResponse)this.l_service.execute(this.l_inputRequest);
            
            assertNotNull(l_response);
            assertEquals(0,WEB3DateUtility.compareToDay(l_expectedCalendar.getTime(),l_response.nextBizDate));

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
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�m�F���N�G�X�g�̏ꍇ
     * validate�S�ۖ����o�^()���R�[������
     * 
     * �K�p����from�͗��c�Ɠ���菬�����̏ꍇ
     * 
     * �e�o�ُ�M���FBUSINESS_ERROR_02929
     *
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_expectedCalendar = Calendar.getInstance();
            l_expectedCalendar.set(2007,8,25);
            
            WEB3SLProductInfoUnit stockLoanProductInfo = new WEB3SLProductInfoUnit();
            stockLoanProductInfo.targetPeriodFrom = l_expectedCalendar.getTime();
            l_confirmRequest.stockLoanProductInfo = stockLoanProductInfo;
            
            WEB3AdminSLProductRegistConfirmResponse l_response =
                (WEB3AdminSLProductRegistConfirmResponse)this.l_service.execute(this.l_confirmRequest);
            
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02929,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�m�F���N�G�X�g�̏ꍇ
     * validate�S�ۖ����o�^()���R�[������
     * 
     * ���������I�u�W�F�N�g���擾�ł��܂���B
     * 
     * �e�o�ُ�M���FBUSINESS_ERROR_02928
     *
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_expectedCalendar = Calendar.getInstance();
            l_expectedCalendar.set(2007,8,28);
            
            WEB3SLProductInfoUnit stockLoanProductInfo = new WEB3SLProductInfoUnit();
            stockLoanProductInfo.targetPeriodFrom = l_expectedCalendar.getTime();
            l_confirmRequest.stockLoanProductInfo = stockLoanProductInfo;
            
            WEB3AdminSLProductRegistConfirmResponse l_response =
                (WEB3AdminSLProductRegistConfirmResponse)this.l_service.execute(this.l_confirmRequest);
            
            
            fail();

        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02928,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�m�F���N�G�X�g�̏ꍇ
     * validate�S�ۖ����o�^()���R�[������
     * 
     * get�S�ۖ������i�j�̗v�f���� != 0 �̏ꍇ
     * �S�ۖ����o�^�m�F���N�G�X�g�I.�S�ۖ����o�^��� = null�̏ꍇ
     * �S�ۖ����o�^�m�F���N�G�X�g�I.�K�p����To = null�̏ꍇ
     * 
     * ����ԉ�F
     * ���X�|���X.�����o�^���.�|�� = 0
     * ���X�|���X.�����o�^���.�K�p����To = 9999/12/31
     *
     */
    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            List l_lisSecurityProductInfos = new ArrayList();
            
            // SecurityProductRow
            SecurityProductParams l_securityProductParams = new SecurityProductParams();
            l_securityProductParams.setInstitutionCode("0D");
            
            l_lisSecurityProductInfos.add(l_securityProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "getSecurityProductInfo",
                    new Class[]
                    { long.class },
                    l_lisSecurityProductInfos);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", 
                    "validateSecurityProductSameTerm",
                    new Class[]{ List.class, Date.class, Date.class },
                    null);
                    
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setProductCode("N8080");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            Calendar l_expectedCalendar = Calendar.getInstance();
            l_expectedCalendar.set(2007,8,28);
            
            Calendar l_calendarExpectedTargetPeriodTo = Calendar.getInstance();
            l_calendarExpectedTargetPeriodTo.set(9999,11,31,0,0,0);
            
            WEB3SLProductInfoUnit stockLoanProductInfo = new WEB3SLProductInfoUnit();
            stockLoanProductInfo.targetPeriodFrom = l_expectedCalendar.getTime();
            
            stockLoanProductInfo.weight = null;
            stockLoanProductInfo.targetPeriodTo = null;
            stockLoanProductInfo.productCode = "N8080";
            l_confirmRequest.stockLoanProductInfo = stockLoanProductInfo;
            
            WEB3AdminSLProductRegistConfirmResponse l_response =
                (WEB3AdminSLProductRegistConfirmResponse)this.l_service.execute(this.l_confirmRequest);
           
            assertNotNull(l_response);
            assertNotNull(l_response.stockLoanProductInfo);
            
            // ���X�|���X.�����o�^���.�|�� = 0
            assertEquals("0",l_response.stockLoanProductInfo.weight);
            
            // ���X�|���X.�����o�^���.�K�p����To = 9999/12/31
            assertEquals(0,WEB3DateUtility.compareToDay(
                l_response.stockLoanProductInfo.targetPeriodTo,
                l_calendarExpectedTargetPeriodTo.getTime()));
            
            // ���X�|���X.�����o�^���.����ID
            assertEquals(3304148080000L,l_response.stockLoanProductInfo.productId);
            
            // ���X�|���X.�����o�^���.������
            assertEquals("�V���Z���e���X",l_response.stockLoanProductInfo.productName);
            
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
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�m�F���N�G�X�g�̏ꍇ
     * validate�S�ۖ����o�^()���R�[������
     * 
     * get�S�ۖ������i�j�̗v�f���� == 0 �̏ꍇ
     * �S�ۖ����o�^�m�F���N�G�X�g�I.�S�ۖ����o�^��� = null�̏ꍇ
     * �S�ۖ����o�^�m�F���N�G�X�g�I.�K�p����To = null�̏ꍇ
     * 
     * ����ԉ�F
     * ���X�|���X.�����o�^���.�|�� = 0
     * ���X�|���X.�����o�^���.�K�p����To = 9999/12/31
     *
     */
    public void testExecute_C0006()
    {
        final String STR_METHOD_NAME = "testExecute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            List l_lisSecurityProductInfos = new ArrayList();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "getSecurityProductInfo",
                    new Class[]
                    { long.class },
                    l_lisSecurityProductInfos);
            
                    
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setProductCode("N8080");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            Calendar l_expectedCalendar = Calendar.getInstance();
            l_expectedCalendar.set(2007,8,28);
            
            Calendar l_calendarExpectedTargetPeriodTo = Calendar.getInstance();
            l_calendarExpectedTargetPeriodTo.set(9999,11,31,0,0,0);
            
            WEB3SLProductInfoUnit stockLoanProductInfo = new WEB3SLProductInfoUnit();
            stockLoanProductInfo.targetPeriodFrom = l_expectedCalendar.getTime();
            
            stockLoanProductInfo.weight = null;
            stockLoanProductInfo.targetPeriodTo = null;
            stockLoanProductInfo.productCode = "N8080";
            l_confirmRequest.stockLoanProductInfo = stockLoanProductInfo;
            
            WEB3AdminSLProductRegistConfirmResponse l_response =
                (WEB3AdminSLProductRegistConfirmResponse)this.l_service.execute(this.l_confirmRequest);
           
            assertNotNull(l_response);
            assertNotNull(l_response.stockLoanProductInfo);
            
            // ���X�|���X.�����o�^���.�|�� = 0
            assertEquals("0",l_response.stockLoanProductInfo.weight);
            
            // ���X�|���X.�����o�^���.�K�p����To = 9999/12/31
            assertEquals(0,WEB3DateUtility.compareToDay(
                l_response.stockLoanProductInfo.targetPeriodTo,
                l_calendarExpectedTargetPeriodTo.getTime()));
            
            // ���X�|���X.�����o�^���.����ID
            assertEquals(3304148080000L,l_response.stockLoanProductInfo.productId);
            
            // ���X�|���X.�����o�^���.������
            assertEquals("�V���Z���e���X",l_response.stockLoanProductInfo.productName);
            
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
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�������N�G�X�g�̏ꍇ
     * submit�S�ۖ����o�^()���R�[������
     * ���N�G�X�g.�S�ۖ����o�^���.�K�p����from < roll()�Ŏ擾�����l�̏ꍇ�A��O���X���[
     * �@@�@@�@@�@@
     * �e�o�ُ�M���FBUSINESS_ERROR_02929
     */
    public void testExecute_C0007()
    {
        final String STR_METHOD_NAME = "testExecute_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(2007,7,25);
            WEB3AdminSLProductRegistCompleteRequest l_request= new WEB3AdminSLProductRegistCompleteRequestForTest();
            WEB3SLProductInfoUnit l_stockLoanProductInfo = new WEB3SLProductInfoUnit();
            l_stockLoanProductInfo.targetPeriodFrom = l_calendar.getTime();
            l_request.stockLoanProductInfo = l_stockLoanProductInfo;
            this.l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02929,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�������N�G�X�g�̏ꍇ
     * submit�S�ۖ����o�^()���R�[������
     * ���������I�u�W�F�N�g���擾�ł��Ȃ��ꍇ
     *
     * �e�o�ُ�M���FBUSINESS_ERROR_02928
     */
    public void testExecute_C0008()
    {
        final String STR_METHOD_NAME = "testExecute_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(2007,11,25);
            WEB3AdminSLProductRegistCompleteRequest l_request= new WEB3AdminSLProductRegistCompleteRequestForTest();
            WEB3SLProductInfoUnit l_stockLoanProductInfo = new WEB3SLProductInfoUnit();
            l_stockLoanProductInfo.targetPeriodFrom = l_calendar.getTime();
            l_request.stockLoanProductInfo = l_stockLoanProductInfo;
            this.l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02928,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
     * 
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�������N�G�X�g�̏ꍇ
     * submit�S�ۖ����o�^()���R�[������
     * 
     * get�S�ۖ������i�j�̗v�f�� != 0 �̏ꍇ�A
     * validate�S�ۖ����������(List, Date, Date)���R�[������
     *
     * ���팋��
     */
    public void testExecute_C0009()
    {
        final String STR_METHOD_NAME = "testExecute_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();

            /*----------------------------------------------------------------------------------*/
            //SecurityProductRow
            SecurityProductParams l_securityProductParams = new SecurityProductParams();
            l_securityProductParams.setProductId(1006169090018L);
            l_securityProductParams.setInstitutionCode("0D");
            l_securityProductParams.setProductCode("1");
            l_securityProductParams.setProductType(ProductTypeEnum.AIO);
            l_securityProductParams.setEstimationRatio(263.3);
            l_securityProductParams.setFitFlg("0");
            l_securityProductParams.setApplyTermFrom(WEB3DateUtility.getDate("20990918","yyyyMMdd"));
            l_securityProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_securityProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(SecurityProductRow.TYPE);
            TestDBUtility.insertWithDel(l_securityProductParams);

            //ProductRow
            ProductParams l_productParams = new ProductParams();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setStandardName("�V���Z���e���X");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeProductRow
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            //TradedProductRow
            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setMarginRatio(0.000000D);
            l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //MarketRow
            MarketParams l_marketParams = new MarketParams();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("SP");
            l_marketParams.setSonarMarketCode("G");
            l_marketParams.setMarketName("�V���K�|�[��");
            l_marketParams.setOpenTime("09:00");
            l_marketParams.setCloseTime("15:00");
            l_marketParams.setSuspension("0");
            l_marketParams.setChangeableType("1");
            l_marketParams.setFeqOrderEmpDiv("7");
            l_marketParams.setFeqOrderRequestDiv("1");
            l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            /*----------------------------------------------------------------------------------*/

            WEB3Administrator l_expectAdministrator = new WEB3Administrator(33381330001L);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            WEB3AdminSLProductRegistCompleteRequest l_request= new WEB3AdminSLProductRegistCompleteRequestForTest();
            l_request.stockLoanProductInfo = new WEB3SLProductInfoUnit();
            l_request.stockLoanProductInfo.targetPeriodFrom = Calendar.getInstance().getTime();
            l_request.stockLoanProductInfo.targetPeriodTo = Calendar.getInstance().getTime();
            l_request.stockLoanProductInfo.productCode = "1";
            l_request.stockLoanProductInfo.productType = "8";
            l_request.stockLoanProductInfo.weight = "1";
            l_request.stockLoanProductInfo.qualifiedDiv = "0";


            this.l_service.execute(l_request);
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
     * �����̃��N�G�X�g�f�[�^���A�S�ۖ����o�^�m�F���N�G�X�g�̏ꍇ
     * validate�S�ۖ����o�^()���R�[������
     * 
     * get�S�ۖ������i�j�̗v�f���� == 0 �̏ꍇ
     * �S�ۖ����o�^�m�F���N�G�X�g�I.�S�ۖ����o�^���.�|�� = 200 �̏ꍇ
     * �S�ۖ����o�^�m�F���N�G�X�g�I.�����o�^���.�K�p����To = 20070928�̏ꍇ
     * 
     * ����ԉ�F
     * ���X�|���X.�����o�^���.�|�� = 200
     * ���X�|���X.�����o�^���.�K�p����To = 2007/09/28
     *
     */
    public void testExecute_C0010()
    {
        final String STR_METHOD_NAME = "testExecute_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            List l_lisSecurityProductInfos = new ArrayList();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AdminAioSLProductRegistControlServiceImpl", "getSecurityProductInfo",
                    new Class[]
                    { long.class },
                    l_lisSecurityProductInfos);
            
                    
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setProductCode("N8080");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            Calendar l_expectedCalendar = Calendar.getInstance();
            l_expectedCalendar.set(2007,8,28);
            
            Calendar l_calendarExpectedTargetPeriodTo = Calendar.getInstance();
            l_calendarExpectedTargetPeriodTo.set(2007,8,28,0,0,0);
            
            WEB3SLProductInfoUnit stockLoanProductInfo = new WEB3SLProductInfoUnit();
            stockLoanProductInfo.targetPeriodFrom = l_expectedCalendar.getTime();
            
            stockLoanProductInfo.weight = "200";
            stockLoanProductInfo.targetPeriodTo = l_expectedCalendar.getTime();
            stockLoanProductInfo.productCode = "N8080";
            l_confirmRequest.stockLoanProductInfo = stockLoanProductInfo;
            
            WEB3AdminSLProductRegistConfirmResponse l_response =
                (WEB3AdminSLProductRegistConfirmResponse)this.l_service.execute(this.l_confirmRequest);
           
            assertNotNull(l_response);
            assertNotNull(l_response.stockLoanProductInfo);
            
            // ���X�|���X.�����o�^���.�|�� = 0
            assertEquals("200",l_response.stockLoanProductInfo.weight);
            
            // ���X�|���X.�����o�^���.�K�p����To = 9999/12/31
            assertEquals(0,WEB3DateUtility.compareToDay(
                l_response.stockLoanProductInfo.targetPeriodTo,
                l_calendarExpectedTargetPeriodTo.getTime()));
            
            // ���X�|���X.�����o�^���.����ID
            assertEquals(3304148080000L,l_response.stockLoanProductInfo.productId);
            
            // ���X�|���X.�����o�^���.������
            assertEquals("�V���Z���e���X",l_response.stockLoanProductInfo.productName);
            
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
     * �p�����[�^�^�C�v�s���̏ꍇ
     * �e�o�FSYSTEM_ERROR_80018�ُ�M��
     *
     */
    public void testExecute_C0011()
    {
        final String STR_METHOD_NAME = "testExecute_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_service.execute(new WEB3AioCashinSettleCancelRequest());
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
            WEB3AdministratorForMock.mockValidateAuthority(l_admin,"B0602",true,true); 
            WEB3AdministratorForMock.mockValidateTradingPassword("password", true);
            
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
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,26);
            l_calendar.set(Calendar.HOUR_OF_DAY,10);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
//            WEB3EquityProduct l_web3EquityProduct = new WEB3EquityProduct(3304148080000L);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.equity.WEB3EquityProductManager",
//                    "getProduct",
//                    new Class[] {Institution.class, String.class},
//                    l_web3EquityProduct);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    private class WEB3AdminSLProductRegistConfirmRequestForTest extends WEB3AdminSLProductRegistConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }

    private class WEB3AdminSLProductRegistCompleteRequestForTest extends WEB3AdminSLProductRegistCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {

        }
    }
}
@
