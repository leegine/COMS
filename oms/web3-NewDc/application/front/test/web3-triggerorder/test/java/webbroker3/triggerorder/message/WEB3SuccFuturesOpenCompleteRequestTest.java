head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���j�����w���敨�V�K�������������N�G�X�g�e�X�g(WEB3SuccFuturesOpenCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/17 �k�v�u (���u) �V�K�쐬
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
/**
 * �i�A���j�����w���敨�V�K�������������N�G�X�g<BR>
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenCompleteRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenCompleteRequestTest.class);
    /**
     * <BR>
     */
    private WEB3SuccFuturesOpenCompleteRequest succFuturesOpenComplete = null;
    /**
     * <BR>
     * @@param arg0
     */
    public WEB3SuccFuturesOpenCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    /**
     *<BR>
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenComplete = new WEB3SuccFuturesOpenCompleteRequest();
    }

    /**
     *<BR>
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // �Q�j�@@�A���������ʏ��`�F�b�N
    //  �Q�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A
    // �u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //this.�A���������ʏ��null�̏ꍇ
            succFuturesOpenComplete.succCommonInfo = null;
            succFuturesOpenComplete.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
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
    // �Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
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
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succFuturesOpenComplete.succCommonInfo.succTradingType = "10";
            succFuturesOpenComplete.validate();
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
    //�@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�A�������P�������l���.validate()���R�[������
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succFuturesOpenComplete.succCommonInfo.succTradingType = "11";
            //�A�������P�������l���null�̏ꍇ
            succFuturesOpenComplete.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesOpenComplete.priceAdjustmentValueInfo.sign = null;
            succFuturesOpenComplete.validate();
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
    // �A���������ʏ��.�A����������敪��"�敨�V�K���i�O�񒍕��j"�ł����
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
            // �u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
            succFuturesOpenComplete.succCommonInfo.succTradingType = "12";
            //�A�������P�������l���null�̏ꍇ
            succFuturesOpenComplete.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenComplete.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenComplete.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenComplete.validate();
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
    // �@@�R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A
    //  �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v
    //  �̗�O��throw����B
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "1";
            succFuturesOpenComplete.limitPrice = "1000";
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
            // �u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
            succFuturesOpenComplete.succCommonInfo.succTradingType = "11";
            //�A�������P�������l���null�̏ꍇ
            succFuturesOpenComplete.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenComplete.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenComplete.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenComplete.validate();
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
    //�S�j�@@�A�������E���������`�F�b�N
    // super.validate�A������()���R�[������B
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "3";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succFuturesOpenComplete.succCommonInfo.succTradingType = "11";
            succFuturesOpenComplete.priceAdjustmentValueInfo = null;
            succFuturesOpenComplete.validate();
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
    //correct case �A���������ʏ��.�A����������敪="�敨�V�K��"  �A�������P�������l���=null�̏ꍇ
    public void testvalidate_C0007()
    {
        final String STR_METHOD_NAME = "testvalidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succFuturesOpenComplete.succCommonInfo.succTradingType = "12";
            //�A�������P�������l���=null�̏ꍇ
            succFuturesOpenComplete.priceAdjustmentValueInfo = null;
            succFuturesOpenComplete.validate();
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
    //correct case �A���������ʏ��.�A����������敪="�敨�V�K���i�O�񒍕��j" �A�������P�������l���null�̏ꍇ
    public void testvalidate_C0008()
    {
        final String STR_METHOD_NAME = "testvalidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //succFuturesOpenComplete.succCommonInfo != null;
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenComplete.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succFuturesOpenComplete.succCommonInfo.succTradingType = "11";
            //�A�������P�������l���null�̏ꍇ
            succFuturesOpenComplete.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenComplete.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenComplete.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenComplete.validate();
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
    //  �P�j�@@super.validate()���R�[������B
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succFuturesOpenComplete.orderPriceDiv = null;
            succFuturesOpenComplete.validate();
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
            succFuturesOpenComplete.orderPriceDiv = "0";
            succFuturesOpenComplete.limitPrice = null;
            succFuturesOpenComplete.execCondType = "1";
            succFuturesOpenComplete.expirationDateType = "1";
            succFuturesOpenComplete.expirationDate = null;
            succFuturesOpenComplete.orderCondType = "0";
            succFuturesOpenComplete.stopOrderCondPrice = null;
            succFuturesOpenComplete.stopOrderCondOperator = null;
            succFuturesOpenComplete.wlimitOrderCondPrice = null;
            succFuturesOpenComplete.wlimitOrderCondOperator = null;
            succFuturesOpenComplete.wLimitOrderPriceDiv = null;
            succFuturesOpenComplete.wLimitPrice = null;
            succFuturesOpenComplete.wlimitExecCondType = null;
            succFuturesOpenComplete.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenComplete.futProductCode = "0001";
            succFuturesOpenComplete.contractType = "1";
            succFuturesOpenComplete.marketCode = "1";
            succFuturesOpenComplete.futOrderQuantity = "1000";
            succFuturesOpenComplete.orderId = null;
            //�Q�|�P�jthis.�A���������ʏ��!��null�̏ꍇ
            succFuturesOpenComplete.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesOpenComplete.validate();
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
