head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoFrontOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3IfoFrontOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/01 ���G�� (���u) �V�K�쐬
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllDao;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3IfoFrontOrderServiceImplTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoFrontOrderServiceImplTest.class);

    WEB3IfoFrontOrderServiceImpl l_ifoFrontOrderServiceImpl = 
        new WEB3IfoFrontOrderServiceImpl();

    public WEB3IfoFrontOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * �����̎s��R�[�h == "����"�̏ꍇ  �����̎s��R�[�h == "1:����"  
     * 2:���؁AJASDAQ�I�[�N�V�����A����"��ԋp����B ����
     */
    public void testGetFrontOrderSystemCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderSystemCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //�����̎s��R�[�h == "����"�̏ꍇ  �����̎s��R�[�h == "1:����" 
        String l_strMarketCode = "1";
        String l_strFrontOrderSystemCode =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderSystemCode(l_strMarketCode);

        log.debug("2:���؁AJASDAQ�I�[�N�V���� = " + l_strFrontOrderSystemCode);
        assertEquals("2", l_strFrontOrderSystemCode);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̎s��R�[�h == "���"�̏ꍇ  �����̎s��R�[�h == "2:���"  
     * "1:���"��ԋp����B    ����
     */
    public void testGetFrontOrderSystemCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderSystemCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //�����̎s��R�[�h == "���"�̏ꍇ  �����̎s��R�[�h == "2:���" 
        String l_strMarketCode = "2";
        String l_strFrontOrderSystemCode =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderSystemCode(l_strMarketCode);

        log.debug("1:��؂�ԋp" + l_strFrontOrderSystemCode);
        assertEquals("1", l_strFrontOrderSystemCode);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̎s��R�[�h����L������ł��Ȃ��ꍇ
     * �����̎s��R�[�h  != ""����"" &&
     * �����̎s��R�[�h != ""���""�̏ꍇ
     * "   "�����̎s��R�[�h  != ""1:����"" &&
     * �����̎s��R�[�h != ""2:���""
     * �����̎s��R�[�h  == null"  "9:���̑�"��ԋp����    ����
     */
    public void testGetFrontOrderSystemCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderSystemCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        //* �����̎s��R�[�h  != ""����"" &&
        //* �����̎s��R�[�h != ""���""�̏ꍇ
        String l_strMarketCode = null;
        String l_strFrontOrderSystemCode =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderSystemCode(l_strMarketCode);

        log.debug("9:���̑���ԋp" + l_strFrontOrderSystemCode);
        assertEquals("9", l_strFrontOrderSystemCode);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̏،���ЃR�[�h�@@== 64
     * �����̎s��R�[�h == 0
     * ������ؑ�.get�L��������ؑ�()�I �ԉ�? == null"    
     * "OrderSwitchingRow�\���s����
     * �i�،���ЃR�[�h == 64 && �s��R�[�h == 0 && 
     * �t�����g�����V�X�e���敪 == 9�j �I�L?
     * "   �����o�H�֑ؑΏۂȂ��v�̗�O��throw[BUSINESS_ERROR_02216]  �ُ�
     */
    public void testGetSubmitOrderRouteDiv_case001()
    {
        final String STR_METHOD_NAME = ".testGetSubmitOrderRouteDiv_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strInstitutionCode = "01";
        String l_strMarketCode = "0";

        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("64");
            l_orderSwitchingParams.setMarketCode("0");
            l_orderSwitchingParams.setFrontOrderSystemCode("1");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            this.l_ifoFrontOrderServiceImpl.getSubmitOrderRouteDiv(l_strInstitutionCode, l_strMarketCode);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02216, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * ������ؑ�.get�L��������ؑ�()�I �ԉ�? != null�̏ꍇ
     * �m�F�ԉ�?���ې��m"  "OrderSwitchingRow�\������
     * �i�،���ЃR�[�h == 01 && �s��R�[�h == 0 && 
     * �t�����g�����V�X�e���敪 == 9�j �I�L?
     * �����o�H�敪 = 0�FSONAR���n" "������ؑ�.get�L��������ؑ�()�̖߂�l.�����o�H�敪 ��ԋp����
     * return 0�FSONAR���n"
     */
    public void testGetSubmitOrderRouteDiv_case002()
    {
        final String STR_METHOD_NAME = ".testGetSubmitOrderRouteDiv_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strInstitutionCode = "01";
        String l_strMarketCode = "0";

        try
        {
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("01");
            l_orderSwitchingParams.setMarketCode("0");
            l_orderSwitchingParams.setFrontOrderSystemCode("9");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
            l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
            l_orderSwitchingParams.setValidFlag("1");

            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            String l_strSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getSubmitOrderRouteDiv(l_strInstitutionCode, l_strMarketCode);
            assertEquals("0", l_strSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �ԉ�?��16
     */
    public void testGetIndexOfOrderRevInCorpCode()
    {
        final String STR_METHOD_NAME = ".testGetIndexOfOrderRevInCorpCode()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intIndexOfOrderRevInCorpCode =
                this.l_ifoFrontOrderServiceImpl.getIndexOfOrderRevInCorpCode();
            assertEquals(16, l_intIndexOfOrderRevInCorpCode);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * �ԉ�?��16
     */
    public void testGetFigureOfOrderRev()
    {
        final String STR_METHOD_NAME = ".testGetFigureOfOrderRev()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intFigureOfOrderRev =
                this.l_ifoFrontOrderServiceImpl.getFigureOfOrderRev();
            assertEquals(2, l_intFigureOfOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�����̒����P�� == null�̏ꍇ
     * ��O��throw"   �����̒����P�� == null "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetCorpCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetCorpCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_orderUnit = null;
            this.l_ifoFrontOrderServiceImpl.getCorpCode(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�����̒����P�� != null�̏ꍇ
     * ���X�I�u�W�F�N�g���擾
     * �@@�ŕԉ�?"  "�����̒����P�� �I= null
     * l_orderUnitRow.getBranchId() == 64246 
     * ��Branch�\������branchId==64246�I�Llu
     * �����̒����P�ʃI�u�W�F�N�g�� �����^�C�v == 6�A���ʃR�[�h = 123456789�A����Rev = 3 
     * ���X�I�u�W�F�N�g���I �،����ID = 10�A���X�R�[�h = 624" 
     * "�،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{����Rev.�{""999""�ԉ�
     * �i10624612345678903999�j"
     */
    public void testGetCorpCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetCorpCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strCorpcode =
                this.l_ifoFrontOrderServiceImpl.getCorpCode(l_orderUnit);
            assertEquals("10624612345678903999", l_strCorpcode);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�����̒����P�� != null
     * ��O��throw"   "�����̒����P�� �I= null
     * l_orderUnitRow.getBranchId() == 64246 
     * ��Branch�\���s����branchId==64246�I�L?" "�e�[�u���ɊY������f�[�^������܂���v�̗�O��throw
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetCorpCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetCorpCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            this.l_ifoFrontOrderServiceImpl.getCorpCode(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�����̒����P�� == null
     * ��O��throw"   �����̒����P�� == null "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case001()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�ȉ��̏����̂����ꂩ�ɍ��v����ꍇ��false��ԋp����B
     * [������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ�i���ꒆ�Ŏ�����͎�����j]"
     * "�����̒����P�� != null
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ"    false��ԋp
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case002()//TODO �����s�� �����s�\�o��
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

//        try
//        {
//            //�����P�ʃI�u�W�F�N�g
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1);
//            l_ifoOrderUnitParams.setBranchId(64246);
//            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
//            l_ifoOrderUnitParams.setOrderRev("03");
//            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderMgr =
//                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
//
//            //������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
//            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
//
//            //������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
//            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
//
//            boolean l_blnReturn =
//                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
//            assertTrue(!l_blnReturn);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�ȉ��̏����̂����ꂩ�ɍ��v����ꍇ��false��ԋp����B
     * [������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ�i���ꒆ�Ŏ�����͎�����j]"
     * "�����̒����P�� != null
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ"   false��ԋp
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case003()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�ȉ��̏����̂����ꂩ�ɍ��v����ꍇ��false��ԋp����B
     * [������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ�i���ꒆ�Ŏ�����͎�����j]"
     * "�����̒����P�� != null
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ"   false��ԋp
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case004()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            //������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�����̒����P��.�������  �s�� ���ʓ��e���̏ꍇ
     * �@@�@@�@@�@@--------------------------------- 
     * �@@�@@�@@�@@�@@
     * �@@�@@�@@�@@�@@ACCEPTED 
     * �@@�@@�@@�@@�@@MODIFY_ACCEPTED 
     * �@@�@@�@@�@@�@@ORDERING 
     * �@@�@@�@@�@@--------------------------------- 
     * "   "�����̒����P�� != null
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
     * �����̒����P��.�������  == NOT_ORDERED"   false��ԋp
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case005()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /**
     * "�敨OP��������L���[�e�[�u�����ȉ��̏����Ō���
     * [����] 
     * �@@�@@�،���ЃR�[�h = �����̒����P��.getBranch().�،���ЃR�[�h 
     * �@@�@@���@@���X�R�[�h = �����̒����P��.getBranch().���X�R�[�h 
     * �@@�@@���@@���ʃR�[�h = �����̒����P��.���ʃR�[�h 
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*2) = �����̒����P��.����Rev. 
     * �@@�@@���@@�����敪 != ""0�F������"" 
     * �Y������f�[�^�����݂��Ȃ��ꍇ �Afalse��ԋp����B 
     * "   "�����̒����P�� != null
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
     * �����̒����P��.������� == �gORDERING�h
     * [��������] 
     * �@@�@@�@@�����̒����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@���@@�����̒����P��.getBranch().���X�R�[�h = 624 
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 123456789
     * �@@�@@���@@�����̒����P��.����Rev. = 50 
     * �@@�@@���@@�����敪 != ""0�F������"" 
     * �ݐ敨OP��������L���[�e�[�u�������L��ʑ���ɝ�"  false��ԋp
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case006()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�敨OP��������L���[�e�[�u�����ȉ��̏����Ō���
     * [����] 
     * �@@�@@�،���ЃR�[�h = �����̒����P��.getBranch().�،���ЃR�[�h 
     * �@@�@@���@@���X�R�[�h = �����̒����P��.getBranch().���X�R�[�h 
     * �@@�@@���@@���ʃR�[�h = �����̒����P��.���ʃR�[�h 
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*2) = �����̒����P��.����Rev. 
     * �@@�@@���@@�����敪 != ""0�F������"" 
     * �Y������f�[�^�����݂��Ȃ��ꍇ �Afalse��ԋp����B 
     * "   "�����̒����P�� != null
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
     * �����̒����P��.������� == �gORDERING�h
     * [��������] 
     * �@@�@@�@@�����̒����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@���@@�����̒����P��.getBranch().���X�R�[�h = 624 
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 123456789
     * �@@�@@���@@�����̒����P��.����Rev. = 50 
     * �@@�@@���@@�����敪 != ""0�F������"" 
     * �ݐ敨OP��������L���[�e�[�u�������L��ʑ���ɝ�"  false��ԋp
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case007()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�敨OP��������L���[�e�[�u�����ȉ��̏����Ō���
     * [����] 
     * �@@�@@�،���ЃR�[�h = �����̒����P��.getBranch().�،���ЃR�[�h 
     * �@@�@@���@@���X�R�[�h = �����̒����P��.getBranch().���X�R�[�h 
     * �@@�@@���@@���ʃR�[�h = �����̒����P��.���ʃR�[�h 
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*2) = �����̒����P��.����Rev. 
     * �@@�@@���@@�����敪 != ""0�F������"" 
     * �Y������f�[�^�����݂��Ȃ��ꍇ �Afalse��ԋp����B 
     * "   "�����̒����P�� != null
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
     * �����̒����P��.������� == �gORDERING�h
     * [��������] 
     * �@@�@@�@@�����̒����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@���@@�����̒����P��.getBranch().���X�R�[�h = 624 
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 123456789
     * �@@�@@���@@�����̒����P��.����Rev. = 50 
     * �@@�@@���@@�����敪 != ""0�F������"" 
     * �ݐ敨OP��������L���[�e�[�u�������L��ʑ���ɝ�"  false��ԋp
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case008()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(!l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�敨OP��������L���[�e�[�u�����ȉ��̏����Ō���
     * [����] 
     * �@@�@@�،���ЃR�[�h = �����̒����P��.getBranch().�،���ЃR�[�h 
     * �@@�@@���@@���X�R�[�h = �����̒����P��.getBranch().���X�R�[�h 
     * �@@�@@���@@���ʃR�[�h = �����̒����P��.���ʃR�[�h 
     * �@@�@@���@@�Г��������ڂɊ܂܂�钍��Rev.(*2) = �����̒����P��.����Rev. 
     * �@@�@@���@@�����敪 != ""0�F������"" 
     * �Y������f�[�^�����݂��Ȃ��ꍇ �Afalse��ԋp����B 
     * "   "�����̒����P�� != null
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
     * ������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
     * �����̒����P��.������� == �gORDERING�h
     * [��������] 
     * �@@�@@�@@�����̒����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@���@@�����̒����P��.getBranch().���X�R�[�h = 624 
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 123456789
     * �@@�@@���@@�����̒����P��.����Rev. = 67 
     * �@@�@@���@@�����敪 != ""0�F������"" 
     * �ݐ敨OP��������L���[�e�[�u�����L��ʑ���ɝ�"  true��ԋp
     */
    public void testIsMarketNotifyingOrderInBreakTimeZone_case009()
    {
        final String STR_METHOD_NAME = ".testIsMarketNotifyingOrderInBreakTimeZone_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("12345678901234567890");
            l_hostFotypeOrderAllParams.setStatus("1");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            boolean l_blnReturn =
                this.l_ifoFrontOrderServiceImpl.isMarketNotifyingOrderInBreakTimeZone(l_orderUnit);
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P�� != null
     * ���X�擾���s�I�ꍇ"  "�����̒����P�� != null
     * �����P��.branchId = 64246
     * Branch�\�����LbranchId��64246�I�L?"  "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetHostFotypeOrderAll_case001()
    {
        final String STR_METHOD_NAME = ".testGetHostFotypeOrderAll_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            this.l_ifoFrontOrderServiceImpl.getHostFotypeOrderAll(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P�� != null
     * ���X�擾����
     * �w��̒����P�ʂɊY������敨OP��������L���[���擾���Ԃ�"  "�����̒����P�� != null
     * �����P��.branchId = 64246
     * Branch�\���LbranchId��64246�I�L?
     * �敨OP��������L���[�e�[�u��������
     * �@@�@@�@@[����] 
     * �@@�@@�@@�����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@����  �����̒����P��.getBranch().���X�R�[�h  = 624
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 123456789
     * �@@�@@���@@�����̒����P��.����Rev.  = 67
     * �@@�@@���@@�����敪 = ""������"" "   "�ԉ�����o�IHostFotypeOrderAllParams
     *       �\����v���e�@@���F
     *       �@@�@@�@@�،���ЃR�[�h = 10
     *       �@@�@@  ���X�R�[�h = 624
     *       �@@�@@  ���ʃR�[�h = �����̒����P��.���ʃR�[�h = 123456789
     *       �@@�@@  �Г��������ڂɊ܂܂�钍��Rev. = 67
     *       �@@�@@  �����敪 = ""������"""
     */
    public void testGetHostFotypeOrderAll_case002()
    {
        final String STR_METHOD_NAME = ".testGetHostFotypeOrderAll_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("12345678901234567890");
            l_hostFotypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            HostFotypeOrderAllParams l_fotypeOrderAllParams =
                this.l_ifoFrontOrderServiceImpl.getHostFotypeOrderAll(l_orderUnit);

            assertEquals("0D", l_fotypeOrderAllParams.getInstitutionCode());
            assertEquals("624", l_fotypeOrderAllParams.getBranchCode());
            assertEquals("123456789", l_fotypeOrderAllParams.getOrderRequestNumber());
            assertEquals("0", l_fotypeOrderAllParams.getStatus());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P�� != null
     * ���X�擾����
     * �w��̒����P�ʂɊY������敨OP��������L���[���擾���Ԃ�"  "�����̒����P�� != null
     * �����P��.branchId = 64246
     * Branch�\���LbranchId��64246�I�L?
     * �敨OP��������L���[�e�[�u�����s����
     * �@@�@@�@@[����] 
     * �@@�@@�@@�����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@����  �����̒����P��.getBranch().���X�R�[�h  = 624
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 123456789
     * �@@�@@���@@�����̒����P��.����Rev.  = 67
     * �@@�@@���@@�����敪 = ""������"" "   "�ԉ�����o�IHostFotypeOrderAllParams
     *       �\����v���e�@@���F
     *       �@@�@@�@@�،���ЃR�[�h = 10
     *       �@@�@@  ���X�R�[�h = 624
     *       �@@�@@  ���ʃR�[�h = �����̒����P��.���ʃR�[�h = 123456789
     *       �@@�@@  �Г��������ڂɊ܂܂�钍��Rev. = 67
     *       �@@�@@  �����敪 = ""������"""
     *       �ԉ�null
     */
    public void testGetHostFotypeOrderAll_case003()
    {
        final String STR_METHOD_NAME = ".testGetHostFotypeOrderAll_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("67");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setInstitutionCode("10");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            HostFotypeOrderAllParams l_fotypeOrderAllParams =
                this.l_ifoFrontOrderServiceImpl.getHostFotypeOrderAll(l_orderUnit);

            assertNull(l_fotypeOrderAllParams);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�����̒����P�� == null
     * ��O��throw"   �����̒����P�� == null "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetHostFotypeOrderAll_case004()
    {
        final String STR_METHOD_NAME = ".testGetHostFotypeOrderAll_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getHostFotypeOrderAll(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�����̒����P�� == null
     * ��O��throw"   �����̒����P�� == null "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetChangeOrderRev_case001()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * "�����̒����P�� != null
     * �����̒����P��.����Rev = 50
     * OP�����}�l�[�W��.is���e�ʒm�ϒ���(�����̒����P��)==true(mork����)" 
     * "�����̒����P��.����Rev.�����̂܂ܕԋp����
     * ?��50"   ����
     */
    public void testGetChangeOrderRev_case002()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(true));

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("50", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("50", l_changeOrderRev);
            WEB3MockObjectParamsValue l_value =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class});

            assertEquals(l_orderUnit, l_value.getCalled(0)[0]);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is�s��J�ǎ��ԑ�()�I=false�̏ꍇ
     * �����̒����P��.�s�ꂩ��m�F�ς̐���==NaN�̏ꍇ
     * �@@�ŕԉ�?"  "�����̒����P�� != null
     * �����̒����P��.����Rev = 50
     * OP�����}�l�[�W��.is���e�ʒm�ϒ���(�����̒����P��)==true(mork����)
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true
     * �����̒����P��.�s�ꂩ��m�F�ς̐���==NaN"  "�����̒����P��.����Rev.�����̂܂ܕԋp����
     * ?��50"
     */
    public void testGetChangeOrderRev_case003()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(true));

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("50", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("50", l_changeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is�s��J�ǎ��ԑ�()=false�̏ꍇ
     * �����̒����P��.�s�ꂩ��m�F�ς̐���!=NaN�̏ꍇ
     * �@@�ŕԉ�?"  "�����̒����P�� != null
     * �����̒����P��.����Rev = 50
     * OP�����}�l�[�W��.is���e�ʒm�ϒ���(�����̒����P��)==false(mork����)
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==false
     * l_orderUnitRow.getConfirmedQuantity() ==20.0D"  "�����̒����P��.����Rev.�����̂܂ܕԋp����
     * ?��50"
     */
    public void testGetChangeOrderRev_case004()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("50");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==false�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("50", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("50", l_changeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is�s��J�ǎ��ԑ�()�I=false�̏ꍇ
     * �����̒����P��.�s�ꂩ��m�F�ς̐���!=NaN�̏ꍇ
     * ������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ
     * ����Rev.�̒l���ő包���𒴉� 2 �̏ꍇ
     * �e�ُ�"    "�����̒����P�� != null
     * �����̒����P��.����Rev = 99
     * OP�����}�l�[�W��.is���e�ʒm�ϒ���(�����̒����P��)==false(mork����)
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true
     * l_orderUnitRow.getConfirmedQuantity() ==20.0D"  "����Rev.�̒l���ő包���𒴉߁v�̗�O��throw
     * BUSINESS_ERROR_02185]"
     */
    public void testGetChangeOrderRev_case005()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("99", l_ifoOrderUnitParams.getOrderRev());

            this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02185, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is�s��J�ǎ��ԑ�()�I=false�̏ꍇ
     * �����̒����P��.�s�ꂩ��m�F�ς̐���!=NaN�̏ꍇ
     * ������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ
     * ����Rev.�̒l���ő包���𒴉� 2 �̏ꍇ
     * �e�ُ�"    "�����̒����P�� != null
     * �����̒����P��.����Rev = 10
     * OP�����}�l�[�W��.is���e�ʒm�ϒ���(�����̒����P��)==false(mork����)
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true
     * l_orderUnitRow.getConfirmedQuantity() ==20.0D"  
     * return 11
     */
    public void testGetChangeOrderRev_case006()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("10");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("10", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("11", l_changeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is�s��J�ǎ��ԑ�()�I=false�̏ꍇ
     * �����̒����P��.�s�ꂩ��m�F�ς̐���!=NaN�̏ꍇ
     * ������ԊǗ�.is������x�e���ԑ�()==false�̏ꍇ
     * ����Rev.�̒l���ő包���𒴉� 2 �̏ꍇ
     * �e�ُ�"    "�����̒����P�� != null
     * �����̒����P��.����Rev = "01"
     * OP�����}�l�[�W��.is���e�ʒm�ϒ���(�����̒����P��)==false(mork����)
     * ������ԊǗ�.is�s��J�ǎ��ԑ�()==true
     * l_orderUnitRow.getConfirmedQuantity() ==20.0D"  
     * return "02"
     */
    public void testGetChangeOrderRev_case007()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("01");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("01", l_ifoOrderUnitParams.getOrderRev());

            String l_changeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("02", l_changeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ
     * �敨OP��������L���[�e�[�u�����ȉ��̏����Ō���
     * ����Rev.�̒l���ő包���𒴉� 2 �̏ꍇ
     * ------>
     * ����Rev.�̒l��������3 �̏ꍇ"  "�����̒����P�� != null
     * �����P��.branchId = 64246
     * Branch�\���LbranchId��64246�I�L?
     * �敨OP��������L���[�e�[�u�����s����
     * �@@�@@�@@[����] 
     * �@@�@@�@@�����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@����  �����̒����P��.getBranch().���X�R�[�h  = 624
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 20
     * �@@�@@���@@�����̒����P��.����Rev.  = 100
     * �@@�@@���@@�����敪 = ""������"" "   "����Rev.�̒l���ő包���𒴉߁v�̗�O��throw
     * BUSINESS_ERROR_02185]"
     */
    public void testGetChangeOrderRev_case008()//TODO ����Rev.�̒l��������3 �̏ꍇ���� �����s��
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ
     * �敨OP��������L���[�e�[�u�����ȉ��̏����Ō���
     * ����Rev.�̒l���ő包���𒴉� 2 �̏ꍇ
     * ------>
     * ����Rev.�̒l��������2 �̏ꍇ"  "�����̒����P�� != null
     * �����P��.branchId = 64246
     * Branch�\���LbranchId��64246�I�L?
     * �敨OP��������L���[�e�[�u�����s����
     * �@@�@@�@@[����] 
     * �@@�@@�@@�����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@����  �����̒����P��.getBranch().���X�R�[�h  = 624
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 20
     * �@@�@@���@@�����̒����P��.����Rev.  = 99
     * �@@�@@���@@�����敪 = ""������"" "   "����Rev.�̒l���ő包���𒴉߁v�̗�O��throw
     * BUSINESS_ERROR_02185]"
     */
    public void testGetChangeOrderRev_case009()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("99", l_ifoOrderUnitParams.getOrderRev());

            this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02185, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ
     * �敨OP��������L���[�e�[�u�����ȉ��̏����Ō���
     * ����Rev.�̒l���ő包����s���� 2 �̏ꍇ
     * ------>
     * ����Rev.�̒l��������1 �̏ꍇ"  "�����̒����P�� != null
     * �����P��.branchId = 64246
     * Branch�\���LbranchId��64246�I�L?
     * �敨OP��������L���[�e�[�u�����s����
     * �@@�@@�@@[����] 
     * �@@�@@�@@�����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@����  �����̒����P��.getBranch().���X�R�[�h  = 624
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 20
     * �@@�@@���@@�����̒����P��.����Rev.  = 01
     * �@@�@@���@@�����敪 = ""������"" "
     * "return "02"
     */
    public void testGetChangeOrderRev_case010()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("01");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));

            //������ԊǗ�.is�s��J�ǎ��ԑ�()==true�̏ꍇ�i��������^��c�Ɠ��j
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);

            //������ԊǗ�.is������x�e���ԑ�() == false �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("01", l_ifoOrderUnitParams.getOrderRev());

            String l_strChangeOrderRev =
                this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);
            assertEquals("02", l_strChangeOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ԊǗ�.is������x�e���ԑ�()==true�̏ꍇ
     * �敨OP��������L���[�e�[�u�����ȉ��̏����Ō���  
     * �@@�@@���R�|�Q�j�ŊY�����郌�R�[�h�����݂���ꍇ 
     * �@@�@@�@@�����̒����P��.����Rev.�����̂܂ܕԋp����B "   "�����̒����P�� != null
     * �����P��.branchId = 624
     * Branch�\���LbranchId��624�I�L?
     * �敨OP��������L���[�e�[�u��������
     * �@@�@@�@@[����] 
     * �@@�@@�@@�����P��.getBranch().�،���ЃR�[�h  = 10
     * �@@�@@����  �����̒����P��.getBranch().���X�R�[�h  = 624
     * �@@�@@���@@�����̒����P��.���ʃR�[�h  = 20
     * �@@�@@���@@�����̒����P��.����Rev.  = 99
     * �@@�@@���@@�����敪 = ""������"" "   �ԉ�99    ����
    */
    public void testGetChangeOrderRev_case011()
    {
        final String STR_METHOD_NAME = ".testGetChangeOrderRev_case011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("12345678901234599890");
            l_hostFotypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "isNotifyEndOrder",
                new Class[] {OrderUnit.class},
                new Boolean(false));


            //������ԊǗ�.is������x�e���ԑ�() == true �̏ꍇ
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            assertEquals("99", l_ifoOrderUnitParams.getOrderRev());

            this.l_ifoFrontOrderServiceImpl.getChangeOrderRev(l_orderUnit);

            assertEquals("99", l_ifoOrderUnitParams.getOrderRev());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 
     */
    public void testWEB3IfoOrderAllTransactionCallback()
    {
        final String STR_METHOD_NAME = ".testWEB3IfoOrderAllTransactionCallback()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("0D");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("10624612345678999999");
            l_hostFotypeOrderAllParams.setStatus("0");
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostFotypeOrderAllParams.setStatus("0");
            l_hostFotypeOrderAllParams.setAccountId(123);

            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            this.l_ifoFrontOrderServiceImpl.lockHostFotypeOrderAll(l_orderUnit);
            boolean l_blnReturn =
                TestDBUtility.isTableLockedSuccessful(HostFotypeOrderAllRow.TYPE);
            assertTrue(l_blnReturn);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̒����P�� == null  �����̒����P�� == null "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetOrderMQDataCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P��.�����o�H�敪==""9�F������~""�̏ꍇ
     * �@@�ŕԉ�?"  �����̒����P��.�����o�H�敪=="9�F������~"    null��ԋp����
     */
    public void testGetOrderMQDataCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertNull(l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * �����̒����P��.�����o�H�敪==�@@"SONAR���n"�̏ꍇ
     * �����̒����P��.�敨/�I�v�V�����敪==�h�敨�h�̏ꍇ 
     * �����̒����P��.�����o�H�敪==�@@"SONAR���n"
     * �����̒����P��.�敨/�I�v�V�����敪==1  �h�敨�h   
     * return �hEI803T�h�i�敨�j
     */
    public void testGetOrderMQDataCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertEquals("EI803T", l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P��.�����o�H�敪==�@@""SONAR���n""�̏ꍇ
     * �����̒����P��.�敨/�I�v�V�����敪==�h�敨�h�ȊO�̏ꍇ"  
     * "�����̒����P��.�����o�H�敪==�@@""SONAR���n""����
     * �̒����P��.�敨/�I�v�V�����敪==2  �hOPTION�h"  return �hEI801T�h�i�I�v�V�����j
     */
    public void testGetOrderMQDataCode_case004()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertEquals("EI801T", l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̒����P��.�����o�H�敪==�@@"�t�����g���n"�̏ꍇ 
     * �����̒����P��.�����o�H�敪==�@@"3�t�����g���n"  
     * return "EI8X2T"��ԋp
     */
    public void testGetOrderMQDataCode_case005()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("3");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertEquals("EI8X2T", l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̒����P��.�����o�H�敪����L�ȊO�̏ꍇ��
     * �����̒����P��.�����o�H�敪==�@@"1�FSONAR_SUB_FACTION"
     * null��ԋp����
     */
    public void testGetOrderMQDataCode_case006()
    {
        final String STR_METHOD_NAME = ".testGetOrderMQDataCode_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrderMQdataCode =
                this.l_ifoFrontOrderServiceImpl.getOrderMQDataCode(l_orderUnit);
            assertNull(l_strOrderMQdataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /*
     * �����̒����P�� == null  �����̒����P�� == null "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetChangeCancelMQDataCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P��.�����o�H�敪==""9�F������~""�̏ꍇ
     * �@@�ŕԉ�?"  �����̒����P��.�����o�H�敪=="9�F������~"    null��ԋp����
     */
    public void testGetChangeCancelMQDataCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertNull(l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * "�����̒����P��.�����o�H�敪==�@@""SONAR���n""�̏ꍇ
     * �����̒����P��.�敨/�I�v�V�����敪==�h�敨�h�̏ꍇ" 
     * "�����̒����P��.�����o�H�敪==�@@""SONAR���n"
     * �����̒����P��.�敨/�I�v�V�����敪==�h�敨
     * return "EI804T";
     */
    public void testGetChangeCancelMQDataCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertEquals("EI804T", l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P��.�����o�H�敪==�@@""SONAR���n""�̏ꍇ
     * �����̒����P��.�敨/�I�v�V�����敪==�h�I�v�V�����h�̏ꍇ"
     * "�����̒����P��.�����o�H�敪==�@@""SONAR���n""
     * �����̒����P��.�敨/�I�v�V�����敪==�h�I�v�V�����h
     * return "EI802T";
     */
    public void testGetChangeCancelMQDataCode_case004()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertEquals("EI802T", l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̒����P��.�����o�H�敪==�@@"�t�����g���n"�̏ꍇ
     * �����̒����P��.�����o�H�敪==�@@"�t�����g���n"  
     * return "EI8X2T";
     */
    public void testGetChangeCancelMQDataCode_case005()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("3");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertEquals("EI8X2T", l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P��.�����o�H�敪����L�ȊO�̏ꍇ
     * �����̒����P��.�����o�H�敪==�@@""SONAR���n""�̏ꍇ" 
     * �����̒����P��.�����o�H�敪==�@@"SONAR���n" 
     * return null;
     */
    public void testGetChangeCancelMQDataCode_case006()
    {
        final String STR_METHOD_NAME = ".testGetChangeCancelMQDataCode_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strChangeCancelMQDataCode =
                this.l_ifoFrontOrderServiceImpl.getChangeCancelMQDataCode(l_orderUnit);
            assertNull(l_strChangeCancelMQDataCode);

        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̒����P�� == null  �����̒����P�� == null "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetChangeSubmitOrderRouteDiv_case001()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "Market�\���s����marketId�� 
     * �����̒����P��.getMarketId()�I�L?"
     * "�����̒����P��.getMarketId() = 666
     * Market�\���s����marketId = 666 �I�L"
     * �e�[�u���ɊY������f�[�^������܂���v�̗�O��throw[SYSTEM_ERROR_80005]
     */
    public void testGetChangeSubmitOrderRouteDiv_case002()//TODO �����s�� case �s���m
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

//        try
//        {
//            //�����P�ʃI�u�W�F�N�g
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1);
//            l_ifoOrderUnitParams.setBranchId(64246);
//            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
//            l_ifoOrderUnitParams.setOrderRev("99");
//            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
//            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
//            l_ifoOrderUnitParams.setMarketId(666);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderMgr =
//                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
//            this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
//            fail();
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug("Error in WEB3BaseException..." , l_ex);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P��.�����o�H�敪 != ""������~""�̏ꍇ
     * ������ؑփe�[�u���������e80005�ُ�"   
     * �����̒����P��.�����o�H�敪() == 0�FSONAR���n
     * " �ԉ� �����̒����P��.�����o�H�敪()
     * return 0�FSONAR���n"
     */
    public void testGetChangeSubmitOrderRouteDiv_case003()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //�s��Row
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);

            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("0", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P��.�����o�H�敪 != ""������~""�̏ꍇ
     * ������ؑփe�[�u���������e80003�ُ�"
     * �e�[�u���ɊY������f�[�^������܂���v�̗�O��throw[SYSTEM_ERROR_80003]
     */
    public void testGetChangeSubmitOrderRouteDiv_case004()//TODO wait
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
//            //�����P�ʃI�u�W�F�N�g
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1);
//            l_ifoOrderUnitParams.setBranchId(64246);
//            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
//            l_ifoOrderUnitParams.setOrderRev("99");
//            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
//            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
//            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
//            l_ifoOrderUnitParams.setMarketId(666);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            //�s��Row
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//
//            l_marketParams.setMarketId(666);
//
//            TestDBUtility.insertWithDel(l_marketParams);
//
//            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderMgr =
//                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
//            assertEquals("0", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
//            String l_strChangeSubmitOrderRouteDiv =
//                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
//            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̒����P��.��������\�t�� = 0�F�s�̏ꍇ
     * "�����̒����P��.��������\�t�� = 0�F�s��
     * �����̒����P��.�����o�H�敪 = ""0�FSONAR���n"
     * "�ԉ� �����̒����P��.�����o�H�敪 = ""0�FSONAR���n""
     */
    public void testGetChangeSubmitOrderRouteDiv_case005()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //�s��Row
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //������ؑփe�[�u��
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams.setMarketCode("1");
            l_orderSwitchingParams.setFrontOrderSystemCode("2");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
            l_orderSwitchingParams.setChangeCancelEnableFlag("0");
            l_orderSwitchingParams.setOrderEngineDiv("0");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(01);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("0", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ؑ�Row.�����G���W���敪 == ""SONAR""�̏ꍇ
     * "   "������ؑ�Row.�����G���W���敪 == 1�FSONAR
     * �����̒����P��.�����o�H�敪 = ""0�FSONAR���n""" �ԉ�
     * �����̒����P��.�����o�H�敪 = "0�FSONAR���n"
     */
    public void testGetChangeSubmitOrderRouteDiv_case006()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(01);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //������ؑփe�[�u��
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams.setMarketCode("1");
            l_orderSwitchingParams.setFrontOrderSystemCode("2");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
            l_orderSwitchingParams.setChangeCancelEnableFlag("1");
            l_orderSwitchingParams.setOrderEngineDiv("1");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
//            l_orderSwitchingParams.setValidFlag("1");
//            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("0", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *������ؑ�.get�L��������ؑ�()�Ŗ߂�l != null�̏ꍇ 
     *"�����̒����P��.getMarketId() = 666�C
     *�����̒����P��.getBrandchId() = 64246�C
     *�����̒����P��.�����o�H�敪 = ""0�FSONAR���n""
     *1�j��Market�\������MarketId = 666�I�L?����marketCode = 1�F����
     *2�j��Brandch�\������BrancdId= 64246�I�L?�C������Brandch���IInstitutionCode = ""10""
     *������ؑփe�[�u���\���L�ޑ���ʞ����I�L?
     *��������ؑփe�[�u���\���I�����o�H�敪=0�FSONAR���n
     *"   "�߂�l.�����o�H�敪 ��ԋp����
     *return 0�FSONAR���n"
     */
    public void testGetChangeSubmitOrderRouteDiv_case007()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //������ؑփe�[�u��
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams.setMarketCode("1");
            l_orderSwitchingParams.setFrontOrderSystemCode("2");
            l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
            l_orderSwitchingParams.setChangeCancelEnableFlag("2");
            l_orderSwitchingParams.setOrderEngineDiv("1");
            l_orderSwitchingParams.setValidFlag("1");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("0", l_orderSwitchingParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            assertEquals("0", l_strChangeSubmitOrderRouteDiv);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * ������ؑ�.get�L��������ؑ�()�Ŗ߂�l == null�̏ꍇ
     * "�����̒����P��.getMarketId() = 666�C
     * �����̒����P��.getBrandchId() = 64246�C
     * �����̒����P��.�����o�H�敪 = ""0�FSONAR���n""
     * 1�j��Market�\������MarketId = 666�I�L?����marketCode = 1�F����
     * 2�j��Brandch�\������BrancdId= 64246�I�L?�C������Brandch���IInstitutionCode = ""10""
     * ������ؑփe�[�u���\�����L�ޑ���ʞ����I�L?"
     * �u�����o�H�֑ؑΏۂȂ��v�̗�O��throw����[BUSINESS_ERROR_02216]
     */
    public void testGetChangeSubmitOrderRouteDiv_case008()
    {
        final String STR_METHOD_NAME = ".testGetChangeSubmitOrderRouteDiv_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("99");
            l_ifoOrderUnitParams.setConfirmedQuantity(20.0D);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_ifoOrderUnitParams.setMarketId(666);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(666);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //������ؑփe�[�u��
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            assertEquals("9", l_ifoOrderUnitParams.getSubmitOrderRouteDiv());
            String l_strChangeSubmitOrderRouteDiv =
                this.l_ifoFrontOrderServiceImpl.getChangeSubmitOrderRouteDiv(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02216, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P�� != null
     * ���X�I�u�W�F�N�g���擾"
     * "�����̒����P�� �I= null
     * l_orderUnitRow.getBranchId() == 64246 
     * ��Branch�\������branchId==64246�I�L?
     * �����̒����P�ʃI�u�W�F�N�g�� �����^�C�v == 1�A���ʃR�[�h = 123456789�A����Rev = 3 
     * ���X�I�u�W�F�N�g���I �،����ID = 10�A���X�R�[�h = 624" 
     * "�،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{����Rev.�{""999""�ԉ�
     * �i10624612345678903999�j"
     */
    public void testGetOrgCorpCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetOrgCorpCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setConfirmedOrderRev("03");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            String l_strOrgCorpCode =
                this.l_ifoFrontOrderServiceImpl.getOrgCorpCode(l_orderUnit);
            assertEquals("10624612345678903999", l_strOrgCorpCode);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P�� != null
     * �e��O"    "�����̒����P�� �I= null
     * l_orderUnitRow.getBranchId() == 64246 
     * ��Branch�\���s����branchId==64246�I�L?" 
     * "�e�[�u���ɊY������f�[�^������܂���v�̗�O��throw
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetOrgCorpCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetOrgCorpCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setOrderRev("03");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            this.l_ifoFrontOrderServiceImpl.getOrgCorpCode(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̎s��R�[�h��"1" �ԉ� 1    ����
     */
    public void testGetFrontOrderExchangeCode_case001()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderExchangeCode_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strMarketCode = "1";
        String l_strFrontOrderExchangeCod =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderExchangeCode(l_strMarketCode);
        assertEquals("1", l_strFrontOrderExchangeCod);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̎s��R�[�h��""2""�i��؁j�̏ꍇ
     * �ԉ�?���ې��m�B"   �����̎s��R�[�h��"2"
     * �ԉ� 2
     */
    public void testGetFrontOrderExchangeCode_case002()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderExchangeCode_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strMarketCode = "2";
        String l_strFrontOrderExchangeCod =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderExchangeCode(l_strMarketCode);
        assertEquals("2", l_strFrontOrderExchangeCod);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̎s��R�[�h!��""1""�i���؁j&&
     * �����̎s��R�[�h!��""2""�i��؁j�̏ꍇ"
     * �����̎s��R�[�h��null   �ԉ� null
     */
    public void testGetFrontOrderExchangeCode_case003()
    {
        final String STR_METHOD_NAME = ".testGetFrontOrderExchangeCode_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        String l_strMarketCode = null;
        String l_strFrontOrderExchangeCod =
            this.l_ifoFrontOrderServiceImpl.getFrontOrderExchangeCode(l_strMarketCode);
        assertNull(l_strFrontOrderExchangeCod);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P�� == null
     * �e��O"    �����̒����P�� == null
     * "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testGetOrderSwitching_case001()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            IfoOrderUnit l_ifoOrderUnit = null;

            this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * �����̒����P��.�����o�H�敪=="9�F������~"�̏ꍇ
     * �����̒����P��.�����o�H�敪==9
     * null��ԋp
     */
    public void testGetOrderSwitching_case002()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            WEB3GentradeOrderSwitching l_orderSwitching =
                this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);

            assertNull(l_orderSwitching);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�s��notfound�̏ꍇ
     * �e��O"    "�����̒����P�� �I= null
     * l_orderUnitRow.getMarketId() == 555555 
     * ��Market�\���s����marketId==555555�L?"
     * "�e�[�u���ɊY������f�[�^������܂���v�̗�O��throw
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetOrderSwitching_case003()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //DeleteMarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "�����̒����P�� != null
     * branch�����s���̏ꍇ
     * �e��O"    "�����̒����P�� �I= null
     * l_orderUnitRow.getBranchId() == 64246 
     * ��Branch�\���s����branchId==64246�I�L?"
     * "�e�[�u���ɊY������f�[�^������܂���v�̗�O��throw
     * [SYSTEM_ERROR_80005]"
     */
    public void testGetOrderSwitching_case004()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //DeleteMarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(555555);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ؑփR���X�g���N�^hrows SYSTEM_ERROR_80003�̏ꍇ"
     * throws SYSTEM_ERROR_80003�̗�O
     */
    public void testGetOrderSwitching_case005()//TODO �@@���eSYSTEM_ERROR_80003
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //DeleteMarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(555555);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //���X�I�u�W�F�N�g
            //���X�I�u�W�F�N�g
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);
//
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingMod =
//                l_finApp.getTradingModule(ProductTypeEnum.IFO);
//            WEB3OptionOrderManagerImpl l_orderMgr =
//                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
//
//            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
//
//            WEB3GentradeOrderSwitching l_orderSwitching =
//                this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);
//            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������ؑփR���X�g���N�^
     * throws SYSTEM_ERROR_80005�̏ꍇ"
     * return null
     */
    public void testGetOrderSwitching_case006()
    {
        final String STR_METHOD_NAME = ".testGetOrderSwitching_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //DeleteMarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();

            l_marketParams.setMarketId(555555);
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDel(l_marketParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setInstitutionCode("10");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //������ؑփe�[�u��
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);

            WEB3GentradeOrderSwitching l_orderSwitching =
                this.l_ifoFrontOrderServiceImpl.getOrderSwitching(l_orderUnit);
            assertNull(l_orderSwitching);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * �����̒����P�ʁi�X�V�O�j== null
     * �e��throw"    "
     * �����̒����P�ʁi�X�V�O�j = null"
     * "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    /*
     * "�����̒����P�� == null
     * �e��O"    �����̒����P�� == null
     * "�p�����[�^�l�s���v�̗�O��throw
     * [SYSTEM_ERROR_80017]"
     */
    public void testUpdateHostFotypeOrderAllAtAcceptOvertime_case001()
    {
        final String STR_METHOD_NAME = ".testUpdateHostFotypeOrderAllAtAcceptOvertime_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnitAfter = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
 
            IfoOrderUnit l_orderUnitBefore = null;
            boolean l_blnIsCancel = false;

            this.l_ifoFrontOrderServiceImpl.updateHostFotypeOrderAllAtAcceptOvertime(
                l_orderUnitBefore, l_orderUnitAfter, l_blnIsCancel);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������is���==true�i����j�̏ꍇ
     * ���i���۔퐳�m�X�V�B" "�����P�ʁi�X�V�O�j.ifoOrderUnit = 1001
     * �����̒����P�ʁi�X�V�O�j�I�u�W�F�N�g�� �����^�C�v == 1�A���ʃR�[�h = 123456789�A����Rev = 30
     * ���X�I�u�W�F�N�g���I �،����ID = 10�A���X�R�[�h = 624" "�敨OP��������L���[ Update
     * �����敪�X�V�O�I?�ׂ��g1�F���M�ρg
     * -------�r
     * �����敪�X�V��I?��""������"""
     */
    public void testUpdateHostFotypeOrderAllAtAcceptOvertime_case002()
    {
        final String STR_METHOD_NAME = ".testUpdateHostFotypeOrderAllAtAcceptOvertime_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setBranchId(64246);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setOrderRequestNumber("123456789");
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_ifoOrderUnitParams.setMarketId(555555);
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_ifoOrderUnitParams.setConfirmedOrderRev("30");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setInstitutionCode("10");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("10");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("10624612345678930999");
            l_hostFotypeOrderAllParams.setStatus("0");
            l_hostFotypeOrderAllParams.setCancelDiv("1");
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostFotypeOrderAllParams.setStatus("1");
            l_hostFotypeOrderAllParams.setAccountId(123);

            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnitAfter = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
 
            IfoOrderUnit l_orderUnitBefore = null;
            boolean l_blnIsCancel = true;

            log.debug("�����敪�X�V�O�ׂ�1�F���M��");
            assertEquals("1", l_hostFotypeOrderAllParams.getStatus());

            this.l_ifoFrontOrderServiceImpl.updateHostFotypeOrderAllAtAcceptOvertime(
                l_orderUnitBefore, l_orderUnitAfter, l_blnIsCancel);

            List l_lisUpdateAfterRows =
                HostFotypeOrderAllDao.findRowsByAccountIdOrderRequestNumber(
                    new Long(123), "123456789");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisUpdateAfterRows.get(0);

            log.debug("�����敪�X�V��� ������");            
            assertEquals("0", l_row.getStatus());
            log.debug("getLastUpdatedTimestamp" + l_row.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "������is���==false�i����ȊO�j�̏ꍇ
     *  ���i���۔퐳�m�X�V�B
     *  �����̒����P�ʁi�X�V�O�j�I�u�W�F�N�g�� �����^�C�v == 1�A���ʃR�[�h = 123456789�A����Rev = 30 
     *  ���X�I�u�W�F�N�g���I �،����ID = 10�A���X�R�[�h = 624
     *  �����̒����P�ʁi�X�V��j�I�u�W�F�N�g�� �����^�C�v == 1�A���ʃR�[�h = 123456789�A����Rev = 30
     *  ���X�I�u�W�F�N�g���I �،����ID = 11�A���X�R�[�h = 624
     *  "   "�敨OP��������L���[ Update
     *  �Г��������ڍX�V�O�F�g11111111111�h
     *   �Г��������ځF�@@this.get�Г���������(�����̒����P�ʁi�X�V��j)�̖߂�l
     *   �،����ID�{���X�R�[�h�{�����^�C�v�{���ʃR�[�h�{����Rev.�{""999""�ԉ�
     *  �i11624123999�j
     *   �����敪�X�V�O�I?�ׂ��g1�F���M�ρg
     *   -------�r
     *  �����敪�X�V��I?��""������"""
     *                                                    
     */
    public void testUpdateHostFotypeOrderAllAtAcceptOvertime_case003()
    {
        final String STR_METHOD_NAME = ".testUpdateHostFotypeOrderAllAtAcceptOvertime_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�����P�ʃI�u�W�F�N�g(Before)
            IfoOrderUnitParams l_beforeIfoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_beforeIfoOrderUnitParams.setOrderUnitId(1);
            l_beforeIfoOrderUnitParams.setBranchId(64246);
            l_beforeIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_beforeIfoOrderUnitParams.setOrderRequestNumber("123456789");
            l_beforeIfoOrderUnitParams.setSubmitOrderRouteDiv("1");
            l_beforeIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_beforeIfoOrderUnitParams.setMarketId(555555);
            l_beforeIfoOrderUnitParams.setSubmitOrderRouteDiv("9");
            l_beforeIfoOrderUnitParams.setOrderRev("30");
            TestDBUtility.insertWithDel(l_beforeIfoOrderUnitParams);

            l_beforeIfoOrderUnitParams.setOrderUnitId(2);
            l_beforeIfoOrderUnitParams.setOrderRev("99");
            l_beforeIfoOrderUnitParams.setAccountId(10010002L);
            TestDBUtility.insertWithDel(l_beforeIfoOrderUnitParams);

            //���X�I�u�W�F�N�g
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(64246);
            l_branchParams.setInstitutionId(10);
            l_branchParams.setInstitutionCode("10");
            l_branchParams.setBranchCode("624");
            TestDBUtility.insertWithDel(l_branchParams);

            //�敨OP�����L���[�e�[�u��
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            l_hostFotypeOrderAllParams.setInstitutionCode("10");
            l_hostFotypeOrderAllParams.setBranchCode("624");
            l_hostFotypeOrderAllParams.setOrderRequestNumber("123456789");
            l_hostFotypeOrderAllParams.setCorpCode("10624612345678999999");
            l_hostFotypeOrderAllParams.setStatus("0");
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostFotypeOrderAllParams.setStatus("1");
            l_hostFotypeOrderAllParams.setAccountId(123);

            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

            IfoOrderUnit l_orderUnitAfter = (IfoOrderUnit)l_orderMgr.getOrderUnit(1);
            IfoOrderUnit l_orderUnitBefore = (IfoOrderUnit)l_orderMgr.getOrderUnit(2);
            boolean l_blnIsCancel = false;

            log.debug("�����敪�X�V�O�ׂ�1�F���M��");
            assertEquals("1", l_hostFotypeOrderAllParams.getStatus());
            assertEquals("10624612345678999999", l_hostFotypeOrderAllParams.getCorpCode());
            this.l_ifoFrontOrderServiceImpl.updateHostFotypeOrderAllAtAcceptOvertime(
                l_orderUnitAfter, l_orderUnitBefore, l_blnIsCancel);

            List l_lisUpdateAfterRows =
                HostFotypeOrderAllDao.findRowsByAccountIdOrderRequestNumber(
                    new Long(123), "123456789");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisUpdateAfterRows.get(0);

            log.debug("�����敪�X�V��� ������");            
            assertEquals("0", l_row.getStatus());
            assertEquals("10624612345678930999", l_row.getCorpCode());
            log.debug("getLastUpdatedTimestamp" + l_row.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * ����Rev.�̒l��1���ꍇ���ۘ�O��0"
     * �����̒���Rev = "8"  �ԉ�?��"09"
     */
    public void testGetNextOrderRev_case001()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "8";

            String l_strNextOrderRev =
                this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);

            assertEquals("09", l_strNextOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * �����̒���Rev = "09"  �ԉ�?��"10"
     */
    public void testGetNextOrderRev_case002()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "09";

            String l_strNextOrderRev =
                this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);

            assertEquals("10", l_strNextOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * �����̒���Rev = "09"  �ԉ�?��"10"
     */
    public void testGetNextOrderRev_case003()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "09";

            String l_strNextOrderRev =
                this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);

            assertEquals("10", l_strNextOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * "
     * ����Rev.�̒l��2����������Rev.+1��3���I�ꍇ���ې���ԉ�"
     * "�����̒���Rev = ""13""
     * "   �ԉ�?��"14"
     */
    public void testGetNextOrderRev_case004()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "13";

            String l_strNextOrderRev =
                this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);

            assertEquals("14", l_strNextOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *����Rev.�̒l��2����������Rev.+1��3���I�ꍇ���ۘ��o��"
     *"�����̒���Rev = ""99""
     *"   "�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw
     *[BUSINESS_ERROR_02185]"
     */
    public void testGetNextOrderRev_case005()
    {
        final String STR_METHOD_NAME = ".testGetNextOrderRev_case005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strOrderRev = "99";

            this.l_ifoFrontOrderServiceImpl.getNextOrderRev(l_strOrderRev);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Error in WEB3BaseException..." , l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02185, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
