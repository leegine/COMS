head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3SuccOptionsOpenChangeConfirmRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenChangeConfirmRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenChangeConfirmRequestTest.class);

    /**
     *<BR>
     */
    private WEB3SuccOptionsOpenChangeConfirmRequest succOptionsOpenChangeConfirmRequest = null;
    public WEB3SuccOptionsOpenChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenChangeConfirmRequest = new WEB3SuccOptionsOpenChangeConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�Q�j�@@�A�������P�������l���`�F�b�N
    // �@@�Q�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenChangeConfirmRequest.limitPrice = null;
            succOptionsOpenChangeConfirmRequest.execCondType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //�Q�j�@@�A�������P�������l���`�F�b�N
            // �@@�Q�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //�A�������P�������l���.validate()���R�[������B
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsOpenChangeConfirmRequest.validate();
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
    // �Q�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
    // �@@�@@�@@�@@�@@�@@��O��throw����B
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "1";
            succOptionsOpenChangeConfirmRequest.limitPrice = "1000";
            succOptionsOpenChangeConfirmRequest.execCondType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //�Q�j�@@�A�������P�������l���`�F�b�N
            // �@@�Q�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //�A�������P�������l���.validate()���R�[������B
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenChangeConfirmRequest.validate();
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
    //�R�j�@@�A�������E���������`�F�b�N
    // �@@super.validate�A������()���R�[������B�B
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenChangeConfirmRequest.limitPrice = null;
            succOptionsOpenChangeConfirmRequest.execCondType = "3";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //�A�������P�������l���==null�̏ꍇ
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenChangeConfirmRequest.validate();
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
    //correct case  �A�������P�������l���==null�̏ꍇ
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenChangeConfirmRequest.limitPrice = null;
            succOptionsOpenChangeConfirmRequest.execCondType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //�A�������P�������l���==null�̏ꍇ
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenChangeConfirmRequest.validate();
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
    //correct case  �A�������P�������l���null�̏ꍇ
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsOpenChangeConfirmRequest.limitPrice = null;
            succOptionsOpenChangeConfirmRequest.execCondType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDateType = "1";
            succOptionsOpenChangeConfirmRequest.expirationDate = null;
            succOptionsOpenChangeConfirmRequest.orderCondType = "0";
            succOptionsOpenChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.wLimitPrice = null;
            succOptionsOpenChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsOpenChangeConfirmRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeConfirmRequest.id = "0001";
            succOptionsOpenChangeConfirmRequest.opOrderQuantity = "1000";
            //�A�������P�������l���null�̏ꍇ
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenChangeConfirmRequest.validate();
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
    //�P�j�@@super.validate()���R�[������B
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeConfirmRequest.orderPriceDiv = null;
            succOptionsOpenChangeConfirmRequest.validate();
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
