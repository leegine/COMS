head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3SuccOptionsOpenConfirmRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenConfirmRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenConfirmRequestTest.class);

    /**
     * <BR>
     */
    private WEB3SuccOptionsOpenConfirmRequest succOptionsOpenConfirmRequest = null;

    public WEB3SuccOptionsOpenConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenConfirmRequest = new WEB3SuccOptionsOpenConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�Q�j�@@�A���������ʏ��`�F�b�N
    // �@@�Q�|�P�j�@@�A���������ʏ��null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //�A���������ʏ��null�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo = null;
            succOptionsOpenConfirmRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02251, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�Q�|�R�j�@@�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
    // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
    // �@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j"<BR>
    // �@@�@@�@@�@@�@@"OP�V�K��"<BR>
    // �@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q��
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�Q�|�Q�j�@@�A���������ʏ��.validate()���R�[������B
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "14";
            succOptionsOpenConfirmRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02252, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //�R�j�@@�A�������P�������l���`�F�b�N
    // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�Q�|�Q�j�@@�A���������ʏ��.validate()���R�[������B
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "15";
            //�A�������P�������l���null�̏ꍇ�A
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsOpenConfirmRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02243, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
    //  �A���������ʏ��.�A����������敪��"�敨�V�K���i�O�񒍕��j"�ł����
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "16";
            //�A�������P�������l���null�̏ꍇ�A
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenConfirmRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02253, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�����P���敪��"���s"�̏ꍇ
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "1";
            succOptionsOpenConfirmRequest.limitPrice = "1000";
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId="0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "15";
            //�A�������P�������l���null�̏ꍇ�A
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenConfirmRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02254, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    // super.validate�A������()���R�[������
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "3";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId="0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "15";
            //�A�������P�������l���=null�̏ꍇ�A
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenConfirmRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02235, l_ex.getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case �A���������ʏ��.�A����������敪="OP�V�K���i�O�񒍕��j"  �A�������P�������l���==null�̏ꍇ
    public void testvalidate_C0007()
    {
        final String STR_METHOD_NAME = "testvalidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "16";
            //�A�������P�������l���=null�̏ꍇ�A
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenConfirmRequest.validate();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case �A���������ʏ��.�A����������敪="OP�V�K��" �A�������P�������l���null�̏ꍇ
    public void testvalidate_C0008()
    {
        final String STR_METHOD_NAME = "testvalidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200803";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //succOptionsOpenConfirmRequest.succCommonInfo != null;
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo.succTradingType = "15";
            //�A�������P�������l���null�̏ꍇ
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenConfirmRequest.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
        log.debug(TEST_END + STR_METHOD_NAME);
    }
    //�P�j�@@super.validate()���R�[������B
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = null;
            succOptionsOpenConfirmRequest.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�Q�|�Q�j�@@�A���������ʏ��.validate()���R�[������B
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenConfirmRequest.limitPrice = null;
            succOptionsOpenConfirmRequest.execCondType = "1";
            succOptionsOpenConfirmRequest.expirationDateType = "1";
            succOptionsOpenConfirmRequest.expirationDate = null;
            succOptionsOpenConfirmRequest.orderCondType = "0";
            succOptionsOpenConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenConfirmRequest.wLimitPrice = null;
            succOptionsOpenConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenConfirmRequest.contractType = "1";
            succOptionsOpenConfirmRequest.marketCode = "1";
            succOptionsOpenConfirmRequest.targetProductCode = "0001";
            succOptionsOpenConfirmRequest.delivaryMonth = "200103";
            succOptionsOpenConfirmRequest.opProductType = "C";
            succOptionsOpenConfirmRequest.strikePrice = "1000";
            succOptionsOpenConfirmRequest.opOrderQuantity = "1000";
            //this.�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsOpenConfirmRequest.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02258, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
