head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�����V�K���m�F���N�G�X�g�e�X�g(WEB3SuccFuturesOpenChangeConfirmRequestTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/17 �k�v�u (���u) �V�K�쐬
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�A���j�����w���敨�����V�K���m�F���N�G�X�g<BR>
 * <BR>
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenChangeConfirmRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenCompleteRequestTest.class);

    private WEB3SuccFuturesOpenChangeConfirmRequest succFuturesOpenChangeConfirm = null;

    public WEB3SuccFuturesOpenChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenChangeConfirm = new WEB3SuccFuturesOpenChangeConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�Q�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
    //  �A�������P�������l���.validate()���R�[������B
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "0";
            succFuturesOpenChangeConfirm.limitPrice = null;
            succFuturesOpenChangeConfirm.execCondType = "1";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //  �Q�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �A�������P�������l���.validate()���R�[������B
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.sign = null;
            succFuturesOpenChangeConfirm.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
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
    // �Q�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
    //  �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v
    //  �̗�O��throw����B
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "1";
            succFuturesOpenChangeConfirm.limitPrice = "1000";
            succFuturesOpenChangeConfirm.execCondType = "1";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //�A�������P�������l���null�̏ꍇ
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenChangeConfirm.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
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
    // �R�j�@@�A�������E���������`�F�b�N
    // super.validate�A������()���R�[������B
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "0";
            succFuturesOpenChangeConfirm.limitPrice = null;
            succFuturesOpenChangeConfirm.execCondType = "3";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //�A�������P�������l���==null�̏ꍇ
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = null;
            succFuturesOpenChangeConfirm.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
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
    //correct case �A�������P�������l���==null�̏ꍇ
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "0";
            succFuturesOpenChangeConfirm.limitPrice = null;
            succFuturesOpenChangeConfirm.execCondType = "1";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //�A�������P�������l���==null�̏ꍇ
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = null;
            succFuturesOpenChangeConfirm.validate();
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
    //correct case�@@�A�������P�������l���null�̏ꍇ
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = "0";
            succFuturesOpenChangeConfirm.limitPrice = null;
            succFuturesOpenChangeConfirm.execCondType = "1";
            succFuturesOpenChangeConfirm.expirationDateType = "1";
            succFuturesOpenChangeConfirm.expirationDate = null;
            succFuturesOpenChangeConfirm.orderCondType = "0";
            succFuturesOpenChangeConfirm.stopOrderCondPrice = null;
            succFuturesOpenChangeConfirm.stopOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondPrice = null;
            succFuturesOpenChangeConfirm.wlimitOrderCondOperator = null;
            succFuturesOpenChangeConfirm.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeConfirm.wLimitPrice = null;
            succFuturesOpenChangeConfirm.wlimitExecCondType = null;
            succFuturesOpenChangeConfirm.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeConfirm.id = "0001";
            succFuturesOpenChangeConfirm.futOrderQuantity = "1000";
            //�A�������P�������l���null�̏ꍇ
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenChangeConfirm.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenChangeConfirm.validate();
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
    // �P�j�@@super.validate()���R�[������B
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeConfirm.orderPriceDiv = null;
            succFuturesOpenChangeConfirm.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
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
}
@
