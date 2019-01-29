head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �i�A���j�����w���敨�����ԍϊ������N�G�X�g�e�X�g(WEB3SuccFuturesCloseChangeCompleteRequestTest.java)
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
 * �i�A���j�����w���敨�����ԍϊ������N�G�X�g�e�X�g
 * 
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseChangeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseCompleteRequestTest.class);

    private WEB3SuccFuturesCloseChangeCompleteRequest succFuturesCloseChangeCompleteRequest = null;

    public WEB3SuccFuturesCloseChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseChangeCompleteRequest = new WEB3SuccFuturesCloseChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // �P�j �m�F���T�Z���ϑ��v�`�F�b�N
    // null
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = null;
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03063, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // �P�j �m�F���T�Z���ϑ��v�`�F�b�N
    // �����ȊO "XXXX"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "aaa";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03063, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // �P�j �m�F���T�Z���ϑ��v�`�F�b�N
    // �����ȊO ""
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03063, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // �Q�j �A�������P�������l���`�F�b�N
    // �Q�|�P�j �A�������P�������l���null�̏ꍇ�A
    // �A�������P�������l���.validate()���R�[������B
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "1000";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseChangeCompleteRequest.validate();
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

    // �Q�|�Q�j �A�������P�������l���null�̏ꍇ�A
    // �����P���敪��"���s"�̏ꍇ
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "1000";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseChangeCompleteRequest.validate();
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

    // correct case �A�������P�������l���==null�̏ꍇ
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "1000";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesCloseChangeCompleteRequest.validate();
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
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.estimatedSettleIncome = "1000";
            // �P�j �A�������P�������l���`�F�b�N
            // �P�|�P�j �A�������P�������l���null�̏ꍇ
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // �A�������P�������l���.validate()���R�[������B
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesCloseChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            // �P�|�Q�j �A�������P�������l���null�̏ꍇ�A
            // �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            // ��O��throw����B
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesCloseChangeCompleteRequest.validate();
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

    // �P�j�@@�X�[�p�[�N���X��validate���\�b�h���R�[������B
    public void testValidateATExistingRemainderTrading_C0001()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = null;
            succFuturesCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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
    //  �Q�j�@@�A�������E���������`�F�b�N
    public void testValidateATExistingRemainderTrading_C0002()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate()
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseChangeCompleteRequest.limitPrice = "1000";
            succFuturesCloseChangeCompleteRequest.execCondType = "3";
            succFuturesCloseChangeCompleteRequest.expirationDateType = "1";
            succFuturesCloseChangeCompleteRequest.expirationDate = null;
            succFuturesCloseChangeCompleteRequest.orderCondType = "0";
            succFuturesCloseChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseChangeCompleteRequest.wLimitPrice = null;
            succFuturesCloseChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate
            succFuturesCloseChangeCompleteRequest.id = "0001";
            succFuturesCloseChangeCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseChangeCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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
    //�Q�j�@@correct case
    public void testValidateATExistingRemainderTrading_C0003()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.super.validate()
            succFuturesCloseChangeCompleteRequest.orderPriceDiv = "1";
            succFuturesCloseChangeCompleteRequest.limitPrice = "1000";
            succFuturesCloseChangeCompleteRequest.execCondType = "1";
            succFuturesCloseChangeCompleteRequest.expirationDateType = "1";
            succFuturesCloseChangeCompleteRequest.expirationDate = null;
            succFuturesCloseChangeCompleteRequest.orderCondType = "0";
            succFuturesCloseChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesCloseChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesCloseChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesCloseChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesCloseChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesCloseChangeCompleteRequest.wLimitPrice = null;
            succFuturesCloseChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesCloseChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate
            succFuturesCloseChangeCompleteRequest.id = "0001";
            succFuturesCloseChangeCompleteRequest.futOrderQuantity = "1000";
            succFuturesCloseChangeCompleteRequest.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            succFuturesCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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
