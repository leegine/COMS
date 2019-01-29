head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���j�����w���敨�ԍϒ����m�F���N�G�X�g�e�X�g(WEB3SuccFuturesCloseConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/18 �k�v�u (���u) �V�K�쐬
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
/**
 * �i�A���j�����w���敨�ԍϒ����m�F���N�G�X�g<BR>
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseConfirmRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseConfirmRequestTest.class);
    WEB3SuccFuturesCloseConfirmRequest succFuturesCloseConfirmRequest = null;
    public WEB3SuccFuturesCloseConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseConfirmRequest = new WEB3SuccFuturesCloseConfirmRequest();
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
            succFuturesCloseConfirmRequest.succCommonInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
    // �P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
    // "�敨�ԍρi�O�񒍕��j"
    // "�敨�ԍρi�����c�j"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.�A���������ʏ��!��null�̏ꍇ
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            //succCommonInfo.succTradingType
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "12";
            succFuturesCloseConfirmRequest.validate();
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
    //   �Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
    //  super.validate()���R�[������B
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "14";
            //super.super.validate
            succFuturesCloseConfirmRequest.orderPriceDiv = null;
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate
            succFuturesCloseConfirmRequest.orderPriceDiv = null;
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "14";
            //�Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
            //  super.validate()���R�[������B
            //  �ȊO�Asuper.validateAT���Ύ��()���R�[������B
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseConfirmRequest.limitPrice = "1000";
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder = "1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            //�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �A�������P�������l���.validate()���R�[������B
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesCloseConfirmRequest.validate();
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
    // �u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O��throw����
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
           //this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "14";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseConfirmRequest.limitPrice = "1000";
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder = "1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseConfirmRequest.limitPrice = "1000";
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder ="1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseConfirmRequest.limitPrice = null;
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder = null;
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseConfirmRequest.limitPrice = null;
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder ="3";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
    //�@@ �T�j�@@�A�������E���������`�F�b�N
    //  super.validate�A������()���R�[������B
    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseConfirmRequest.limitPrice = null;
            succFuturesCloseConfirmRequest.execCondType = "3";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder ="1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
    //correct case  this.�A���������ʏ��.�A����������敪!="�敨�ԍρi�����c�j"�̏ꍇ �A�������P�������l���!=null�̏ꍇ
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "13";
            // super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseConfirmRequest.limitPrice = null;
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder ="1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            //�A�������P�������l���!=null�̏ꍇ
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseConfirmRequest.validate();
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
    //correct case  this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ �A�������P�������l���==null�̏ꍇ
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseConfirmRequest.succCommonInfo.succTradingType = "14";
            // �Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
            //  super.validate()���R�[������B
            //  �ȊO�Asuper.validateAT���Ύ��()���R�[������B
            //super.super.validate()
            succFuturesCloseConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseConfirmRequest.limitPrice = "1000";
            succFuturesCloseConfirmRequest.execCondType = "1";
            succFuturesCloseConfirmRequest.expirationDateType = "1";
            succFuturesCloseConfirmRequest.expirationDate = null;
            succFuturesCloseConfirmRequest.orderCondType = "0";
            succFuturesCloseConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseConfirmRequest.wLimitPrice = null;
            succFuturesCloseConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseConfirmRequest.closingOrder = "1";
            succFuturesCloseConfirmRequest.futOrderQuantity = "1000";
            for (int i = 0; i < succFuturesCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succFuturesCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            //�A�������P�������l���==null�̏ꍇ
            succFuturesCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseConfirmRequest.validate();
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
            succFuturesCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseConfirmRequest.validate();
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
