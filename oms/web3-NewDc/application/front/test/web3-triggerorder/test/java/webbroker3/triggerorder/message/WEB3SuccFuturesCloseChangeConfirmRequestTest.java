head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �i�A���j�����w���敨�����ԍϊm�F���N�G�X�g�e�X�g(WEB3SuccFuturesCloseChangeConfirmRequestTest.java)
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
 * �i�A���j�����w���敨�����ԍϊm�F���N�G�X�g�e�X�g
 * 
 * @@author yang-fuzhi
 * 
 */
public class WEB3SuccFuturesCloseChangeConfirmRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseCompleteRequestTest.class);

    WEB3SuccFuturesCloseChangeConfirmRequest succFuturesCloseChangeConfirmRequest = null;

    public WEB3SuccFuturesCloseChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseChangeConfirmRequest = new WEB3SuccFuturesCloseChangeConfirmRequest();
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // �P�j �A�������P�������l���`�F�b�N
    // �P�|�P�j �A�������P�������l���null�̏ꍇ�A
    // �A�������P�������l���.validate()���R�[������B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesCloseChangeConfirmRequest.validate();
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

    // �P�|�Q�j �A�������P�������l���null�̏ꍇ�A
    // �����P���敪��"���s"�̏ꍇ
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "10000";
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseChangeConfirmRequest.validate();
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

    // correct case �A�������P�������l���=null�̏ꍇ
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeConfirmRequest.validate();
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

    // correct case �A�������P�������l���null�̏ꍇ
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "10000";
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = "0";
            succFuturesCloseChangeConfirmRequest.validate();
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

    // �P�j �X�[�p�[�N���X��validate���\�b�h���R�[������B
    public void testValidateATExistingRemainderTrading_C0001()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = null;
            succFuturesCloseChangeConfirmRequest.validateATExistingRemainderTrading();
            fail();
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

    // �Q�j �A�������E���������`�F�b�N
    public void testValidateATExistingRemainderTrading_C0002()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate()
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseChangeConfirmRequest.limitPrice = "1000";
            succFuturesCloseChangeConfirmRequest.execCondType = "3";
            succFuturesCloseChangeConfirmRequest.expirationDateType = "1";
            succFuturesCloseChangeConfirmRequest.expirationDate = null;
            succFuturesCloseChangeConfirmRequest.orderCondType = "0";
            succFuturesCloseChangeConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseChangeConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseChangeConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseChangeConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseChangeConfirmRequest.wLimitPrice = null;
            succFuturesCloseChangeConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseChangeConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate();
            succFuturesCloseChangeConfirmRequest.id = "0001";
            succFuturesCloseChangeConfirmRequest.futOrderQuantity = "1000";
            succFuturesCloseChangeConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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
    //correct case
    public void testValidateATExistingRemainderTrading_C0003()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate()
            succFuturesCloseChangeConfirmRequest.orderPriceDiv = "1";
            succFuturesCloseChangeConfirmRequest.limitPrice = "1000";
            succFuturesCloseChangeConfirmRequest.execCondType = "1";
            succFuturesCloseChangeConfirmRequest.expirationDateType = "1";
            succFuturesCloseChangeConfirmRequest.expirationDate = null;
            succFuturesCloseChangeConfirmRequest.orderCondType = "0";
            succFuturesCloseChangeConfirmRequest.stopOrderCondPrice = null;
            succFuturesCloseChangeConfirmRequest.stopOrderCondOperator = null;
            succFuturesCloseChangeConfirmRequest.wlimitOrderCondPrice = null;
            succFuturesCloseChangeConfirmRequest.wlimitOrderCondOperator = null;
            succFuturesCloseChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseChangeConfirmRequest.wLimitPrice = null;
            succFuturesCloseChangeConfirmRequest.wlimitExecCondType = null;
            succFuturesCloseChangeConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate();
            succFuturesCloseChangeConfirmRequest.id = "0001";
            succFuturesCloseChangeConfirmRequest.futOrderQuantity = "1000";
            succFuturesCloseChangeConfirmRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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
}
@
