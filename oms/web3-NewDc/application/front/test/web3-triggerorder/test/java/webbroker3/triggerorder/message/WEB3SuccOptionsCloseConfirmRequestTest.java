head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsCloseConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3SuccOptionsCloseConfirmRequestTest.java
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

public class WEB3SuccOptionsCloseConfirmRequestTest extends TestBaseForMock
{


    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsCloseConfirmRequestTest.class);
    WEB3SuccOptionsCloseConfirmRequest succOptionsCloseConfirmRequest = null;

    public WEB3SuccOptionsCloseConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsCloseConfirmRequest = new WEB3SuccOptionsCloseConfirmRequest();
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
            succOptionsCloseConfirmRequest.succCommonInfo = null;
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            //succCommonInfo.succTradingType
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "16";
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate()
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "18";
            //super.super.validate
            succOptionsCloseConfirmRequest.orderPriceDiv = null;
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate
            succOptionsCloseConfirmRequest.orderPriceDiv = null;
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "18";
            //�Q�j�@@this.�A���������ʏ��.�A����������敪=="OP�ԍρi�����c�j"�̏ꍇ�̂݁A
            // �@@�@@�@@super.validate()���R�[������B
            // �@@�@@�@@�ȊO�Asuper.validateAT���Ύ��()���R�[������B
            //super.super.validate()
            succOptionsCloseConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseConfirmRequest.limitPrice = "1000";
            succOptionsCloseConfirmRequest.execCondType = "1";
            succOptionsCloseConfirmRequest.expirationDateType = "1";
            succOptionsCloseConfirmRequest.expirationDate = null;
            succOptionsCloseConfirmRequest.orderCondType = "0";
            succOptionsCloseConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseConfirmRequest.wLimitPrice = null;
            succOptionsCloseConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseConfirmRequest.closingOrder = "1";
            succOptionsCloseConfirmRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //�P�|�Q�j�@@this.�A���������ʏ��.validate()���R�[������B
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "18";
            //�Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
            //  super.validate()���R�[������B
            //  �ȊO�Asuper.validateAT���Ύ��()���R�[������B
            //super.super.validate()
            succOptionsCloseConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseConfirmRequest.limitPrice = "1000";
            succOptionsCloseConfirmRequest.execCondType = "1";
            succOptionsCloseConfirmRequest.expirationDateType = "1";
            succOptionsCloseConfirmRequest.expirationDate = null;
            succOptionsCloseConfirmRequest.orderCondType = "0";
            succOptionsCloseConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseConfirmRequest.wLimitPrice = null;
            succOptionsCloseConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseConfirmRequest.closingOrder = "1";
            succOptionsCloseConfirmRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseConfirmRequest.limitPrice = "1000";
            succOptionsCloseConfirmRequest.execCondType = "1";
            succOptionsCloseConfirmRequest.expirationDateType = "1";
            succOptionsCloseConfirmRequest.expirationDate = null;
            succOptionsCloseConfirmRequest.orderCondType = "0";
            succOptionsCloseConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseConfirmRequest.wLimitPrice = null;
            succOptionsCloseConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseConfirmRequest.closingOrder ="1";
            succOptionsCloseConfirmRequest.opOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseConfirmRequest.orderPriceDiv = "0";
            succOptionsCloseConfirmRequest.limitPrice = null;
            succOptionsCloseConfirmRequest.execCondType = "1";
            succOptionsCloseConfirmRequest.expirationDateType = "1";
            succOptionsCloseConfirmRequest.expirationDate = null;
            succOptionsCloseConfirmRequest.orderCondType = "0";
            succOptionsCloseConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseConfirmRequest.wLimitPrice = null;
            succOptionsCloseConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseConfirmRequest.closingOrder = null;
            succOptionsCloseConfirmRequest.opOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseConfirmRequest.orderPriceDiv = "0";
            succOptionsCloseConfirmRequest.limitPrice = null;
            succOptionsCloseConfirmRequest.execCondType = "1";
            succOptionsCloseConfirmRequest.expirationDateType = "1";
            succOptionsCloseConfirmRequest.expirationDate = null;
            succOptionsCloseConfirmRequest.orderCondType = "0";
            succOptionsCloseConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseConfirmRequest.wLimitPrice = null;
            succOptionsCloseConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseConfirmRequest.closingOrder ="3";
            succOptionsCloseConfirmRequest.opOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseConfirmRequest.validate();
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
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "17";
            //super.super.validate()
            succOptionsCloseConfirmRequest.orderPriceDiv = "0";
            succOptionsCloseConfirmRequest.limitPrice = null;
            succOptionsCloseConfirmRequest.execCondType = "3";
            succOptionsCloseConfirmRequest.expirationDateType = "1";
            succOptionsCloseConfirmRequest.expirationDate = null;
            succOptionsCloseConfirmRequest.orderCondType = "0";
            succOptionsCloseConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseConfirmRequest.wLimitPrice = null;
            succOptionsCloseConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseConfirmRequest.closingOrder ="1";
            succOptionsCloseConfirmRequest.opOrderQuantity = "1000";
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseConfirmRequest.validate();
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
    //correct case correct case �A���������ʏ��.�A����������敪��"OP�ԍρi�����c�j"  �A�������P�������l���=null�̏ꍇ
    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "18";
            //super.super.validate()
            succOptionsCloseConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseConfirmRequest.limitPrice = "1000";
            succOptionsCloseConfirmRequest.execCondType = "1";
            succOptionsCloseConfirmRequest.expirationDateType = "1";
            succOptionsCloseConfirmRequest.expirationDate = null;
            succOptionsCloseConfirmRequest.orderCondType = "0";
            succOptionsCloseConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseConfirmRequest.wLimitPrice = null;
            succOptionsCloseConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseConfirmRequest.closingOrder = "1";
            succOptionsCloseConfirmRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseConfirmRequest.closeMarginContractUnits.length; i++)
            {
                succOptionsCloseConfirmRequest.closeMarginContractUnits[i].id = "0001";
                succOptionsCloseConfirmRequest.closeMarginContractUnits[i].settlePriority = null;
            }
            // �A�������P�������l���=null�̏ꍇ
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseConfirmRequest.validate();
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
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // this.succCommonInfo.validate()
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId = "0001";
            succOptionsCloseConfirmRequest.succCommonInfo.succTradingType = "17";
            // super.super.validate()
            succOptionsCloseConfirmRequest.orderPriceDiv = "0";
            succOptionsCloseConfirmRequest.limitPrice = null;
            succOptionsCloseConfirmRequest.execCondType = "1";
            succOptionsCloseConfirmRequest.expirationDateType = "1";
            succOptionsCloseConfirmRequest.expirationDate = null;
            succOptionsCloseConfirmRequest.orderCondType = "0";
            succOptionsCloseConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseConfirmRequest.wLimitPrice = null;
            succOptionsCloseConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validateAtReverseOrder()
            succOptionsCloseConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseConfirmRequest.closingOrder ="1";
            succOptionsCloseConfirmRequest.opOrderQuantity = "1000";
            //�A�������P�������l���!=null�̏�
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsCloseConfirmRequest.validate();
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
            succOptionsCloseConfirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsCloseConfirmRequest.validate();
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
