head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �i�A���j�����w���敨�V�K�������m�F���N�G�X�g�e�X�g(WEB3SuccFuturesOpenConfirmRequestTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/17 �k�v�u (���u) �V�K�쐬
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�A���j�����w���敨�V�K�������m�F���N�G�X�g<BR>
 * 
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenConfirmRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenConfirmRequestTest.class);

    /**
     * <BR>
     */
    private WEB3SuccFuturesOpenConfirmRequest succFuturesOpenConfirmRequest = null;

    /**
     * <BR>
     * 
     * @@param arg0
     */
    public WEB3SuccFuturesOpenConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    /**
     * <BR>
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenConfirmRequest = new WEB3SuccFuturesOpenConfirmRequest();
    }

    /**
     * <BR>
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // �Q�j �A���������ʏ��`�F�b�N
    // �Q�|�P�j this.�A���������ʏ��null�̏ꍇ�A
    // �u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "0";
            succFuturesOpenConfirmRequest.limitPrice = null;
            succFuturesOpenConfirmRequest.execCondType = "1";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0001";
            succFuturesOpenConfirmRequest.delivaryMonth = "200803";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            // �A���������ʏ��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = null;
            succFuturesOpenConfirmRequest.validate();
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

    // �Q�|�R�j this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
    // �u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
    // "�敨�V�K���i�O�񒍕��j"<BR>
    // "�敨�V�K��"<BR>
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "0";
            succFuturesOpenConfirmRequest.limitPrice = null;
            succFuturesOpenConfirmRequest.execCondType = "1";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0001";
            succFuturesOpenConfirmRequest.delivaryMonth = "200803";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            // �A���������ʏ��!��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            // this.succCommonInfo.validate();
            succFuturesOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesOpenConfirmRequest.succCommonInfo.succTradingType = "10";
            succFuturesOpenConfirmRequest.validate();
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

    // �R�|�P�j �A�������P�������l���null�̏ꍇ�A
    // �A�������P�������l���.validate()���R�[������B
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "0";
            succFuturesOpenConfirmRequest.limitPrice = null;
            succFuturesOpenConfirmRequest.execCondType = "1";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0001";
            succFuturesOpenConfirmRequest.delivaryMonth = "200803";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            // �A���������ʏ��!��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            // this.succCommonInfo.validate();
            succFuturesOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesOpenConfirmRequest.succCommonInfo.succTradingType = "11";
            // �A�������P�������l���null�̏ꍇ�A
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // this.priceAdjustmentValueInfo.validate();
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesOpenConfirmRequest.validate();
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

    // �R�|�Q�j �A�������P�������l���null�̏ꍇ�A
    // �A���������ʏ��.�A����������敪��"�敨�V�K���i�O�񒍕��j"�ł����
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "0";
            succFuturesOpenConfirmRequest.limitPrice = null;
            succFuturesOpenConfirmRequest.execCondType = "1";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0001";
            succFuturesOpenConfirmRequest.delivaryMonth = "200803";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            // �A���������ʏ��!��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            // this.succCommonInfo.validate();
            succFuturesOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesOpenConfirmRequest.succCommonInfo.succTradingType = "12";
            // �A�������P�������l���null�̏ꍇ�A
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // this.priceAdjustmentValueInfo.validate();
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenConfirmRequest.validate();
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

    // �R�|�R�j �A�������P�������l���null�̏ꍇ�A
    // �����P���敪��"���s"�̏ꍇ
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "1";
            succFuturesOpenConfirmRequest.limitPrice = "1000";
            succFuturesOpenConfirmRequest.execCondType = "1";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0001";
            succFuturesOpenConfirmRequest.delivaryMonth = "200803";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            // �A���������ʏ��!��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            // this.succCommonInfo.validate();
            succFuturesOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesOpenConfirmRequest.succCommonInfo.succTradingType = "11";
            // �A�������P�������l���null�̏ꍇ�A
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // this.priceAdjustmentValueInfo.validate();
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenConfirmRequest.validate();
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
            // super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "0";
            succFuturesOpenConfirmRequest.limitPrice = null;
            succFuturesOpenConfirmRequest.execCondType = "3";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0001";
            succFuturesOpenConfirmRequest.delivaryMonth = "200803";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            // �A���������ʏ��!��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            // this.succCommonInfo.validate();
            succFuturesOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesOpenConfirmRequest.succCommonInfo.succTradingType = "11";
            // �A�������P�������l���=null�̏ꍇ�A
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesOpenConfirmRequest.validate();
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

    // correct case  �A���������ʏ��.�A����������敪="�敨�V�K��" �A�������P�������l���!=null�̏ꍇ�A
    public void testvalidate_C0007()
    {
        final String STR_METHOD_NAME = "testvalidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "0";
            succFuturesOpenConfirmRequest.limitPrice = null;
            succFuturesOpenConfirmRequest.execCondType = "1";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0001";
            succFuturesOpenConfirmRequest.delivaryMonth = "200803";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            // �A���������ʏ��!��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            // this.succCommonInfo.validate();
            succFuturesOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesOpenConfirmRequest.succCommonInfo.succTradingType = "12";
            // �A�������P�������l���=null�̏ꍇ�A
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesOpenConfirmRequest.validate();
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
    }

    // correct case �A���������ʏ��.�A����������敪="�敨�V�K���i�O�񒍕��j"  �A�������P�������l���!=null�̏ꍇ�A
    public void testvalidate_C0008()
    {
        final String STR_METHOD_NAME = "testvalidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "0";
            succFuturesOpenConfirmRequest.limitPrice = null;
            succFuturesOpenConfirmRequest.execCondType = "1";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0001";
            succFuturesOpenConfirmRequest.delivaryMonth = "200803";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            // �A���������ʏ��!��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            // this.succCommonInfo.validate();
            succFuturesOpenConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesOpenConfirmRequest.succCommonInfo.succTradingType = "11";
            // �A�������P�������l���!=null�̏ꍇ�A
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenConfirmRequest.validate();
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
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = null;
            succFuturesOpenConfirmRequest.validate();
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
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenConfirmRequest.orderPriceDiv = "0";
            succFuturesOpenConfirmRequest.limitPrice = null;
            succFuturesOpenConfirmRequest.execCondType = "1";
            succFuturesOpenConfirmRequest.expirationDateType = "1";
            succFuturesOpenConfirmRequest.expirationDate = null;
            succFuturesOpenConfirmRequest.orderCondType = "0";
            succFuturesOpenConfirmRequest.stopOrderCondPrice = null;
            succFuturesOpenConfirmRequest.stopOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesOpenConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesOpenConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenConfirmRequest.wLimitPrice = null;
            succFuturesOpenConfirmRequest.wlimitExecCondType = null;
            succFuturesOpenConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenConfirmRequest.futProductCode = "0001";
            succFuturesOpenConfirmRequest.contractType = "1";
            succFuturesOpenConfirmRequest.marketCode = "1";
            succFuturesOpenConfirmRequest.targetProductCode = "0018";
            succFuturesOpenConfirmRequest.delivaryMonth = "200101";
            succFuturesOpenConfirmRequest.futOrderQuantity = "1000";
            //�Q�|�P�jthis.�A���������ʏ��!��null�̏ꍇ
            succFuturesOpenConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesOpenConfirmRequest.validate();
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
