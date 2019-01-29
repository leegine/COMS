head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsCloseCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3SuccOptionsCloseCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsCloseCompleteRequestTest extends TestBaseForMock
{


    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsCloseCompleteRequestTest.class);
    WEB3SuccOptionsCloseCompleteRequest succOptionsCloseCompleteRequest = null;

    public WEB3SuccOptionsCloseCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsCloseCompleteRequest = new WEB3SuccOptionsCloseCompleteRequest();
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
            succOptionsCloseCompleteRequest.succCommonInfo = null;
            succOptionsCloseCompleteRequest.validate();
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
    //�P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
    // �@@�@@�@@�@@�@@"OP�ԍρi�O�񒍕��j"
    // �@@�@@�@@�@@�@@"OP�ԍρi�����c�j"
    // �@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.�A���������ʏ��!��null�̏ꍇ
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            //succCommonInfo.succTradingType
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "16";
            succOptionsCloseCompleteRequest.validate();
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
    //�Q�j�@@this.�A���������ʏ��.�A����������敪=="OP�ԍρi�����c�j"�̏ꍇ�̂݁A
    // �@@�@@�@@super.validate()���R�[������B
    // �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.�A���������ʏ��!��null�̏ꍇ
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "18";
            //super.super.validate
            succOptionsCloseCompleteRequest.orderPriceDiv = null;
            succOptionsCloseCompleteRequest.validate();
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
    // �Q�j�@@this.�A���������ʏ��.�A����������敪=="OP�ԍρi�����c�j"�̏ꍇ�̂݁A
    // �@@�@@�@@super.validate()���R�[������B
    // �@@�@@�@@�ȊO
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate
            succOptionsCloseCompleteRequest.orderPriceDiv = null;
            succOptionsCloseCompleteRequest.validate();
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
    //�R�j�@@�A�������P�������l���`�F�b�N
    // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�|�P�j�@@this.�A���������ʏ��!��null�̏ꍇ
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "18";
            //�Q�j�@@this.�A���������ʏ��.�A����������敪=="OP�ԍρi�����c�j"�̏ꍇ�̂݁A
            // �@@�@@�@@super.validate()���R�[������B
            // �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������B
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseCompleteRequest.limitPrice = "1000";
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder = "1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succOptionsCloseCompleteRequest.orderId = null;
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsCloseCompleteRequest.validate();
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
    // �@@�@@�@@�@@�@@�@@�A���������ʏ��.�A����������敪��"OP�ԍρi�O�񒍕��j"�ł����
    // �@@�@@�@@�@@�@@�@@�u�A����������敪���A�A�������́}�w�l�w��s�̋敪�v�̗�O��throw����B
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�|�P�j�@@this.�A���������ʏ��!��null�̏ꍇ
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "18";
            //�Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
            //  super.validate()���R�[������B
            //  �ȊO�Asuper.validateAT���Ύ��()���R�[������B
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseCompleteRequest.limitPrice = "1000";
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder = "1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succOptionsCloseCompleteRequest.orderId = null;
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseCompleteRequest.validate();
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
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseCompleteRequest.limitPrice = "1000";
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder ="1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseCompleteRequest.validate();
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
    //this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�O�񒍕��j"�̏ꍇ
    //���N�G�X�g.���Ϗ���==�inull�j�̏ꍇ��
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseCompleteRequest.limitPrice = null;
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = null;
            //this.validate
            succOptionsCloseCompleteRequest.closingOrder = null;
            succOptionsCloseCompleteRequest.validate();
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
    //this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�O�񒍕��j"�̏ꍇ
    //���N�G�X�g.���Ϗ���==�i"������"�j�̏ꍇ��
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseCompleteRequest.limitPrice = null;
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder ="3";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseCompleteRequest.validate();
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
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseCompleteRequest.limitPrice = null;
            succOptionsCloseCompleteRequest.execCondType = "3";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder ="1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd");
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseCompleteRequest.validate();
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
    //correct case �A���������ʏ��.�A����������敪��"OP�ԍρi�����c�j"  �A�������P�������l���=null�̏ꍇ
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "18";
            //super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseCompleteRequest.limitPrice = "1000";
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder = "1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseCompleteRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseCompleteRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            succOptionsCloseCompleteRequest.orderId = null;
            // �A�������P�������l���=null�̏ꍇ
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseCompleteRequest.validate();
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
    //correct case �A���������ʏ��.�A����������敪���@@"OP�ԍρi�O�񒍕��j"  �A�������P�������l���!=null�̏ꍇ
    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseCompleteRequest.succCommonInfo.succTradingType = "17";
            // super.super.validate()
            succOptionsCloseCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseCompleteRequest.limitPrice = null;
            succOptionsCloseCompleteRequest.execCondType = "1";
            succOptionsCloseCompleteRequest.expirationDateType = "1";
            succOptionsCloseCompleteRequest.expirationDate = null;
            succOptionsCloseCompleteRequest.orderCondType = "0";
            succOptionsCloseCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseCompleteRequest.wLimitPrice = null;
            succOptionsCloseCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseCompleteRequest.orderId = "0001";
            succOptionsCloseCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseCompleteRequest.closingOrder ="1";
            succOptionsCloseCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseCompleteRequest.checkPrice = "1000";
            succOptionsCloseCompleteRequest.checkDate = WEB3DateUtility.getDate("20010325", "yyyyMMdd"); 
            //�A�������P�������l���!=null�̏�
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseCompleteRequest.validate();
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
            succOptionsCloseCompleteRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseCompleteRequest.validate();
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
