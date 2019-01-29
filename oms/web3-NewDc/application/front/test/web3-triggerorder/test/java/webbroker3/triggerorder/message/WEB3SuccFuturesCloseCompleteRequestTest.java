head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���j�����w���敨�ԍϒ����������N�G�X�g�e�X�g(WEB3SuccFuturesCloseCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/18 �k�v�u (���u) �V�K�쐬
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
/***
 * �i�A���j�����w���敨�ԍϒ����������N�G�X�g�e�X�g
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseCompleteRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseCompleteRequestTest.class);
    WEB3SuccFuturesCloseCompleteRequest succFuturesCloseCompleteRequest = null;

    public WEB3SuccFuturesCloseCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseCompleteRequest = new WEB3SuccFuturesCloseCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�P�j�@@�A���������ʏ��`�F�b�N
    //�P�|�P�j�@@this.�A���������ʏ��null�̏ꍇ
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseCompleteRequest.succCommonInfo = null;
            succFuturesCloseCompleteRequest.validate();
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
    // �P�|�R�jthis.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
    // "�敨�ԍρi�O�񒍕��j"
    // "�敨�ԍρi�����c�j"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.�A���������ʏ��!��null�̏ꍇ
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //succCommonInfo.succTradingType
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "12";
            succFuturesCloseCompleteRequest.validate();
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
    //  �Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
    //  super.validate()���R�[������B
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "14";
            //super.super.validate
            succFuturesCloseCompleteRequest.orderPriceDiv = null;
            succFuturesCloseCompleteRequest.validate();
        }
        catch (WEB3BaseException l_ex)
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
    // �Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
    //  �ȊO�Asuper.validateAT���Ύ��()���R�[������B
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate
            succFuturesCloseCompleteRequest.orderPriceDiv = null;
            succFuturesCloseCompleteRequest.validate();
        }
        catch (WEB3BaseException l_ex)
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
    // �R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
    //  �A�������P�������l���.validate()���R�[������B
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�|�P�j�@@this.�A���������ʏ��!��null�̏ꍇ
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "14";
            //�Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
            //  super.validate()���R�[������B
            //  �ȊO�Asuper.validateAT���Ύ��()���R�[������B
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseCompleteRequest.limitPrice = "1000";
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = "1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succFuturesCloseCompleteRequest.orderId = null;
            //�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �A�������P�������l���.validate()���R�[������B
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesCloseCompleteRequest.validate();
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
    //  �A���������ʏ��.�A����������敪��"�敨�ԍρi�O�񒍕��j"�ł����
    //  �u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O��throw����B
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�|�P�j�@@this.�A���������ʏ��!��null�̏ꍇ
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "14";
            //�Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
            //  super.validate()���R�[������B
            //  �ȊO�Asuper.validateAT���Ύ��()���R�[������B
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseCompleteRequest.limitPrice = "1000";
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = "1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succFuturesCloseCompleteRequest.orderId = null;
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseCompleteRequest.validate();
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
    //  �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v
    //  �̗�O��throw����B
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseCompleteRequest.limitPrice = "1000";
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder ="1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseCompleteRequest.validate();
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
    //  �S�j�@@this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�O�񒍕��j"�̏ꍇ
    //���N�G�X�g.���Ϗ���==�inull�j�̏ꍇ��
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseCompleteRequest.limitPrice = null;
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = null;
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02306, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //  �S�j�@@this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�O�񒍕��j"�̏ꍇ
    //���N�G�X�g.���Ϗ���==�i"������"�j�̏ꍇ��
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseCompleteRequest.limitPrice = null;
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = "3";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02306, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�@@�A�������E���������`�F�b�N
    // super.validate�A������()���R�[������B
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseCompleteRequest.limitPrice = null;
            succFuturesCloseCompleteRequest.execCondType = "3";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder ="1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseCompleteRequest.validate();
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
    //correct case this.�A���������ʏ��.�A����������敪!="�敨�ԍρi�����c�j" �A�������P�������l���!=null�̏ꍇ
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "13";
            // super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseCompleteRequest.limitPrice = null;
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseCompleteRequest.orderId = "0001";
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder ="1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseCompleteRequest.checkPrice = "1000";
            succFuturesCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd"); 
            //�A�������P�������l���!=null�̏ꍇ
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseCompleteRequest.validate();
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
    //correct case this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j" �A�������P�������l���==null�̏ꍇ
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseCompleteRequest.succCommonInfo.succTradingType = "14";
            // �Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
            //  super.validate()���R�[������B
            //super.super.validate()
            succFuturesCloseCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseCompleteRequest.limitPrice = "1000";
            succFuturesCloseCompleteRequest.execCondType = "1";
            succFuturesCloseCompleteRequest.expirationDateType = "1";
            succFuturesCloseCompleteRequest.expirationDate = null;
            succFuturesCloseCompleteRequest.orderCondType = "0";
            succFuturesCloseCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseCompleteRequest.wLimitPrice = null;
            succFuturesCloseCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseCompleteRequest.closingOrder = "1";
            succFuturesCloseCompleteRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succFuturesCloseCompleteRequest.orderId = null; 
            //�A�������P�������l���==null�̏ꍇ
            succFuturesCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseCompleteRequest.validate();
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
    //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
    public void testValidate_C0013()
    {
        final String STR_METHOD_NAME = "testValidate_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.�A���������ʏ��!��null�̏ꍇ
            succFuturesCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
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
