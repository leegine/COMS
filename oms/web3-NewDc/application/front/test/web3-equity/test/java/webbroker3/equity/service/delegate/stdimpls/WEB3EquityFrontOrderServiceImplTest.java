head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityFrontOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������T�[�r�XImplTest(WEB3EquityFrontOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/23 �����F ���f�� 1243 1265
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityFrontOrderServiceImplTest extends TestBaseForMock
{
    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityFrontOrderServiceImplTest.class);

    public WEB3EquityFrontOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",st);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl.getPTSChangeOrderRev(EqTypeOrderUnit)'
     */
    public void testGetPTSChangeOrderRevCase0()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase0()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            //WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("123");
            l_tradingTimeParam.setMarketCode("N1");
            l_tradingTimeParam.setTradingTimeType("01");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("1");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strPTSChangOrderRev = l_service.getPTSChangeOrderRev(l_orderUnit);
            assertEquals("01", l_strPTSChangOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //PTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true
    //�����̒����P��.����Rev.�Ɂ{�P�����������ԋp����
    public void testGetPTSChangeOrderRevCase1()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("02");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            //WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("123");
            l_tradingTimeParam.setMarketCode("N1");
            l_tradingTimeParam.setTradingTimeType("01");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("1");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strPTSChangOrderRev = l_service.getPTSChangeOrderRev(l_orderUnit);
            assertEquals("03", l_strPTSChangOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //PTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == true
    //�����𒴂���l�ƂȂ�����
    //�u����Rev.�̒l���ő包���𒴉߁v�̗�O��throw����
    public void testGetPTSChangeOrderRevCase2()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("99");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            l_service.getPTSChangeOrderRev(l_orderUnit);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02185);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //PTS������ԊǗ�.is�s��J�ǎ��ԑ�( ) == false
    //�����̒����P��.����Rev.�����̂܂ܕԋp����
    public void testGetPTSChangeOrderRevCase3()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20071223","yyyyMMdd").getTime()));
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("02");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);

            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strPTSChangOrderRev = l_service.getPTSChangeOrderRev(l_orderUnit);
            assertEquals("02", l_strPTSChangOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //�����̒����P�� == null
    public void testGetPTSChangeOrderRevCase4()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            l_service.getPTSChangeOrderRev(null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //testgetFrontOrderSystemCode
    public void testGetFrontOrderSystemCode_C001()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityFrontOrderServiceImpl l_impl = new WEB3EquityFrontOrderServiceImpl();
        String l_strMarketCode = "1";
        String l_strOpenOtcDiv = "2";
        String l_strReurn =
            l_impl.getFrontOrderSystemCode(l_strMarketCode, l_strOpenOtcDiv);
        assertEquals("1", l_strReurn);
        log.exiting(STR_METHOD_NAME);
    }
    
    //testgetFrontOrderSystemCode
    public void testGetFrontOrderSystemCode_C002()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityFrontOrderServiceImpl l_impl = new WEB3EquityFrontOrderServiceImpl();
        String l_strMarketCode = "3";
        String l_strOpenOtcDiv = "2";
        String l_strReurn =
            l_impl.getFrontOrderSystemCode(l_strMarketCode, l_strOpenOtcDiv);
        assertEquals("1", l_strReurn);
        log.exiting(STR_METHOD_NAME);
    }
    
    //get�t�����g�����V�X�e���敪
    // �P�j�@@�����̎s��R�[�h == �i"����" or "����"�j�̏ꍇ��
    //�@@�@@"���؁A����"��ԋp����B
    //�s��R�[�h == ���� 1
    //�ԉ�1:���؁i�����̂�)�A���؁i�����̂�)
    public void testGetFrontOrderSystemCode_Case1()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("1","");
            assertEquals("1", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get�t�����g�����V�X�e���敪
    // �P�j�@@�����̎s��R�[�h == �i"����" or "����"�j�̏ꍇ��
    //�@@�@@"���؁A����"��ԋp����B
    //�s��R�[�h == ���� 3
    //�ԉ�1:���؁i�����̂�)�A���؁i�����̂�)
    public void testGetFrontOrderSystemCode_Case2()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("3","");
            assertEquals("1", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //get�t�����g�����V�X�e���敪
    // �����̎s��R�[�h == "NNM"�̏ꍇ�́A"�w���N���X"��ԋp����B
    //�s��R�[�h == NNM 9
    //�ԉ�4:�w���N���X
    public void testGetFrontOrderSystemCode_Case3()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("9","");
            assertEquals("4", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get�t�����g�����V�X�e���敪
    //      �S�j�@@�����̎s��R�[�h == "JASDAQ"�̏ꍇ�A�����̓X�����J�敪�̒l�ɂ��
    // �@@�@@�ȉ��̒l��ԋp����B
    // �@@�@@�@@�����̓X�����J�敪 != "�}�[�P�b�g���C�N����"�ł���΁A"JASDAQ"�B
    //�s��R�[�h == "JASDAQ"10
    //�����̓X�����J�敪 != "�}�[�P�b�g���C�N����" 0
    //�ԉ�5�FJASDAQ
    public void testGetFrontOrderSystemCode_Case4()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("10", "0");
            assertEquals("4", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get�t�����g�����V�X�e���敪
    //      �S�j�@@�����̎s��R�[�h == "JASDAQ"�̏ꍇ�A�����̓X�����J�敪�̒l�ɂ��
    // �@@�@@�ȉ��̒l��ԋp����B
    // �@@�@@�@@�����̓X�����J�敪 == "�}�[�P�b�g���C�N����"�ł���΁A"JASDAQ-MM"�B
    //�s��R�[�h == "JASDAQ"10
    //�����̓X�����J�敪 == "�}�[�P�b�g���C�N����" 3
    //�ԉ�3:JASDAQ-MM
    public void testGetFrontOrderSystemCode_Case5()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("9", "3");
            assertEquals("4", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get�t�����g����������敪�R�[�h
    //      �S�j�@@�����̎s��R�[�h��"9"�iNNM�j�̏ꍇ
    // �@@�@@"2"�i��؁j��Ԃ��B
    public void testGetFrontOrderExchangeCode_Case1()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderExchangeCode_Case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderExchangeCode("9");
            assertEquals("2", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get�t�����g����������敪�R�[�h
    //      �S�j�@@�����̎s��R�[�h��"10"�iJASDAQ�j�̏ꍇ
    // �@@�@@"2"�i��؁j��Ԃ��B
    public void testGetFrontOrderExchangeCode_Case2()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderExchangeCode_Case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderExchangeCode("10");
            assertEquals("2", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get�t�����g����������敪�R�[�h
    //      �T�j�@@�����̎s��R�[�h����L(1 2 3 9 10)�ȊO�̏ꍇ
    // �@@�@@null��Ԃ��B
    public void testGetFrontOrderExchangeCode_Case3()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderExchangeCode_Case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderExchangeCode("5");
            assertNull(l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get�t�����g�����V�X�e���敪
    //�����̎s��R�[�h == "10"(JASDAQ)�̏ꍇ�́A
    //"4"(�w���N���X)��ԋp����B
    public void testGetFrontOrderSystemCode_Case6()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("10","");
            assertEquals("4", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
		{
            log.error(l_ex.getMessage(), l_ex);
            fail();
		}
        log.exiting(STR_METHOD_NAME);
    }

    //get�t�����g�����V�X�e���敪
    // �����̎s��R�[�h == "10"(JASDAQ)�A�����̓X�����J�敪�̒l�Ɋւ�炸
    // "4"(�w���N���X)��ԋp����B
    //�����̓X�����J�敪 != "�}�[�P�b�g���C�N����" 0
    public void testGetFrontOrderSystemCode_Case7()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case7";
        log.entering(STR_METHOD_NAME);
        
        try
		{
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("10", "0");
            assertEquals("4", l_strFrontOrderSystemCode);
		}
        catch (Exception l_ex)
		{
            log.error(l_ex.getMessage(), l_ex);
            fail();
		}
        log.exiting(STR_METHOD_NAME);
    }

    //get�t�����g�����V�X�e���敪
    // �����̎s��R�[�h == "10"(JASDAQ)�A�����̓X�����J�敪�̒l�Ɋւ�炸
    // "4"(�w���N���X)��ԋp����B
    //�����̓X�����J�敪 == "�}�[�P�b�g���C�N����" 3
    public void testGetFrontOrderSystemCode_Case8()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case8";
        log.entering(STR_METHOD_NAME);
        
        try
		{
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("10", "3");
            assertEquals("4", l_strFrontOrderSystemCode);
		}
        catch (Exception l_ex)
		{
            log.error(l_ex.getMessage(), l_ex);
            fail();
		}
        log.exiting(STR_METHOD_NAME);
    }
}
@
