head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���j�����w���敨�����V�K���������N�G�X�g�e�X�g(WEB3SuccFuturesOpenChangeCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/17 �k�v�u (���u) �V�K�쐬
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
/**
 * �i�A���j�����w���敨�����V�K���������N�G�X�g<BR>
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenChangeCompleteRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenInputRequestTest.class);

    /**
     *<BR>
     */
    private WEB3SuccFuturesOpenChangeCompleteRequest succFuturesOpenChangeCompleteRequest = null;

    public WEB3SuccFuturesOpenChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenChangeCompleteRequest = new WEB3SuccFuturesOpenChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //   �Q�j�@@�m�F���T�Z������`�F�b�N
    //  this.�m�F���T�Z������̒l���ȉ��̂����ꂩ�ɊY������ꍇ��
    //  null
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            //  this.�m�F���T�Z������̒l���ȉ��̂����ꂩ�ɊY������ꍇ��
            //  null
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = null;
            succFuturesOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03061, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //  �Q�j�@@�m�F���T�Z������`�F�b�N
    //  this.�m�F���T�Z������̒l���ȉ��̂����ꂩ�ɊY������ꍇ��
    //  �����ȊO "XXXX"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //�����ȊO
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "aaa";
            succFuturesOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03061, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //  �Q�j�@@�m�F���T�Z������`�F�b�N
    //  this.�m�F���T�Z������̒l���ȉ��̂����ꂩ�ɊY������ꍇ��
    //  �����ȊO ""
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //�����ȊO
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "";
            succFuturesOpenChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03061, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�R�j �A�������P�������l���`�F�b�N
    // �@@�A�������P�������l���null�̏ꍇ�A
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //�A�������P�������l���null�̏ꍇ�A
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succFuturesOpenChangeCompleteRequest.validate();
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
    //�R�j �A�������P�������l���`�F�b�N
    //�A�������P�������l���null�̏ꍇ�A
    //�����P���敪��"���s"�̏ꍇ
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "1";
            succFuturesOpenChangeCompleteRequest.limitPrice = "1000";
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //�R�j�@@�A�������P�������l���null�̏ꍇ�A
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // �R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �A�������P�������l���.validate()���R�[������B
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenChangeCompleteRequest.validate();
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
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "3";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //�R�j�@@�A�������P�������l���=null�̏ꍇ�A
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesOpenChangeCompleteRequest.validate();
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
    //correct case �A�������P�������l���=null�̏ꍇ
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //�R�j �A�������P�������l���`�F�b�N
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succFuturesOpenChangeCompleteRequest.validate();
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
    //correct case �A�������P�������l���null�̏ꍇ
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = "0";
            succFuturesOpenChangeCompleteRequest.limitPrice = null;
            succFuturesOpenChangeCompleteRequest.execCondType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDateType = "1";
            succFuturesOpenChangeCompleteRequest.expirationDate = null;
            succFuturesOpenChangeCompleteRequest.orderCondType = "0";
            succFuturesOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succFuturesOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.wLimitPrice = null;
            succFuturesOpenChangeCompleteRequest.wlimitExecCondType = null;
            succFuturesOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succFuturesOpenChangeCompleteRequest.id = "0001";
            succFuturesOpenChangeCompleteRequest.futOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succFuturesOpenChangeCompleteRequest.estimatedContractPrice = "1000";
            //�R�j �A�������P�������l���`�F�b�N
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �A�������P�������l���.validate()���R�[������B
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succFuturesOpenChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succFuturesOpenChangeCompleteRequest.validate();
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
    //super.validate()���R�[������B
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succFuturesOpenChangeCompleteRequest.orderPriceDiv = null;
            succFuturesOpenChangeCompleteRequest.validate();
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
}
@
