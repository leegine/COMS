head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3SuccOptionsOpenCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenCompleteRequestTest.class);
    /**
     * <BR>
     */
    private WEB3SuccOptionsOpenCompleteRequest succOptionsOpenCompleteRequest = null;
    public WEB3SuccOptionsOpenCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenCompleteRequest = new WEB3SuccOptionsOpenCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�Q�j�@@�A���������ʏ��`�F�b�N
    // �@@�Q�|�P�j�@@�A���������ʏ��null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�u�A���������ʏ��w��Ȃ��v�̗�O���X���[����B
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //this.�A���������ʏ��null�̏ꍇ
            succOptionsOpenCompleteRequest.succCommonInfo = null;
            succOptionsOpenCompleteRequest.validate();
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
    //�Q�|�R�j�@@�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
    // �@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j"
    // �@@�@@�@@�@@�@@"OP�V�K��"
    // �@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //�Q�|�R�j�@@�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
            // �@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j"
            // �@@�@@�@@�@@�@@"OP�V�K��"
            // �@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "14";
            succOptionsOpenCompleteRequest.validate();
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
    // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "15";
            //�R�j�@@�A�������P�������l���`�F�b�N
            // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsOpenCompleteRequest.validate();
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
    // �@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪��"OP�V�K���i�O�񒍕��j"�ł����
    // �@@�@@�@@�@@�@@�@@�u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O��throw����B
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
            // �u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "16";
            //�A�������P�������l���null�̏ꍇ
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenCompleteRequest.validate();
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
    // �R�|�R�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
    // �@@�@@�@@�@@�@@�@@��O��throw����B
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "1";
            succOptionsOpenCompleteRequest.limitPrice = "1000";
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
            // �u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "15";
            //�A�������P�������l���null�̏ꍇ
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenCompleteRequest.validate();
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
    //�S�j�@@�A�������E���������`�F�b�N<BR>
    // �@@super.validate�A������()���R�[������B
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "3";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "15";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenCompleteRequest.validate();
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
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "16";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenCompleteRequest.validate();
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
    //correct case �A���������ʏ��.�A����������敪="OP�V�K��" �A�������P�������l���null�̏ꍇ
    public void testvalidate_C0008()
    {
        final String STR_METHOD_NAME = "testvalidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate();
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //succOptionsOpenCompleteRequest.succCommonInfo != null;
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            succOptionsOpenCompleteRequest.succCommonInfo.succTradingType = "15";
            //�A�������P�������l���null�̏ꍇ
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenCompleteRequest.validate();
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
            succOptionsOpenCompleteRequest.orderPriceDiv = null;
            succOptionsOpenCompleteRequest.validate();
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
            succOptionsOpenCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenCompleteRequest.limitPrice = null;
            succOptionsOpenCompleteRequest.execCondType = "1";
            succOptionsOpenCompleteRequest.expirationDateType = "1";
            succOptionsOpenCompleteRequest.expirationDate = null;
            succOptionsOpenCompleteRequest.orderCondType = "0";
            succOptionsOpenCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenCompleteRequest.wLimitPrice = null;
            succOptionsOpenCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenCompleteRequest.opProductCode = "0001";
            succOptionsOpenCompleteRequest.contractType = "1";
            succOptionsOpenCompleteRequest.marketCode = "1";
            succOptionsOpenCompleteRequest.opOrderQuantity = "1000";
            succOptionsOpenCompleteRequest.orderId = null;
            //this.�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsOpenCompleteRequest.validate();
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
