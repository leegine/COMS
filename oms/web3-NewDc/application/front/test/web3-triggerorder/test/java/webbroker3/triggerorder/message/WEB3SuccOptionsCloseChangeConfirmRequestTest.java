head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsCloseChangeConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3SuccOptionsCloseChangeConfirmRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsCloseChangeConfirmRequestTest extends TestBaseForMock
{


    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsCloseChangeConfirmRequestTest.class);
    
    WEB3SuccOptionsCloseChangeConfirmRequest succOptionsCloseChangeConfirmRequest = null;
  
    public WEB3SuccOptionsCloseChangeConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsCloseChangeConfirmRequest = new WEB3SuccOptionsCloseChangeConfirmRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //�P�j�@@�A�������P�������l���`�F�b�N
    // �@@�P�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@�A�������P�������l���`�F�b�N
            // �@@�P�|�P�j�@@�A�������P�������l���null�̏ꍇ
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //�A�������P�������l���.validate()���R�[������B
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsCloseChangeConfirmRequest.validate();
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
    //�P�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
    //        �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
    //        ��O��throw����B
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@�A�������P�������l���`�F�b�N
            // �@@�P�|�P�j�@@�A�������P�������l���null�̏ꍇ
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //�A�������P�������l���.validate()���R�[������B
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            //�P�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            //        �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            //        ��O��throw����B
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseChangeConfirmRequest.validate();
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
    // correct case �@@�A�������P�������l���null�̏ꍇ
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@�A�������P�������l���`�F�b�N
            // �@@�P�|�P�j�@@�A�������P�������l���null�̏ꍇ
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //�A�������P�������l���.validate()���R�[������B
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            //�P�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            //        �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            //        ��O��throw����B
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = "0";
            succOptionsCloseChangeConfirmRequest.validate();
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
    //correct case �@@�A�������P�������l���==null�̏ꍇ
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@�A�������P�������l���`�F�b�N
            // �@@�P�|�P�j�@@�A�������P�������l���null�̏ꍇ
            succOptionsCloseChangeConfirmRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseChangeConfirmRequest.validate();
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
    //�P�j�@@�X�[�p�[�N���X��validate���\�b�h���R�[������B
    public void testValidateATExistingRemainderTrading_C0001()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@�X�[�p�[�N���X��validate���\�b�h���R�[������B
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = null;
            succOptionsCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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
    //�Q�j�A�������E���������`�F�b�N<BR>
    public void testValidateATExistingRemainderTrading_C0002()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate()
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseChangeConfirmRequest.limitPrice = "1000";
            succOptionsCloseChangeConfirmRequest.execCondType = "3";
            succOptionsCloseChangeConfirmRequest.expirationDateType = "1";
            succOptionsCloseChangeConfirmRequest.expirationDate = null;
            succOptionsCloseChangeConfirmRequest.orderCondType = "0";
            succOptionsCloseChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseChangeConfirmRequest.wLimitPrice = null;
            succOptionsCloseChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseChangeConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succOptionsCloseChangeConfirmRequest.id = "0001";
            succOptionsCloseChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
            {new WEB3FuturesOptionsCloseMarginContractUnit(), new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseChangeConfirmRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseChangeConfirmRequest.closeMarginContractUnits.length; i++)
            succOptionsCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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

    // correct case
    public void testValidateATExistingRemainderTrading_C0003()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate()
            succOptionsCloseChangeConfirmRequest.orderPriceDiv = "1";
            succOptionsCloseChangeConfirmRequest.limitPrice = "1000";
            succOptionsCloseChangeConfirmRequest.execCondType = "1";
            succOptionsCloseChangeConfirmRequest.expirationDateType = "1";
            succOptionsCloseChangeConfirmRequest.expirationDate = null;
            succOptionsCloseChangeConfirmRequest.orderCondType = "0";
            succOptionsCloseChangeConfirmRequest.stopOrderCondPrice = null;
            succOptionsCloseChangeConfirmRequest.stopOrderCondOperator = null;
            succOptionsCloseChangeConfirmRequest.wlimitOrderCondPrice = null;
            succOptionsCloseChangeConfirmRequest.wlimitOrderCondOperator = null;
            succOptionsCloseChangeConfirmRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseChangeConfirmRequest.wLimitPrice = null;
            succOptionsCloseChangeConfirmRequest.wlimitExecCondType = null;
            succOptionsCloseChangeConfirmRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succOptionsCloseChangeConfirmRequest.id = "0001";
            succOptionsCloseChangeConfirmRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
            {new WEB3FuturesOptionsCloseMarginContractUnit(), new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseChangeConfirmRequest.opOrderQuantity = "1000";
            succOptionsCloseChangeConfirmRequest.validateATExistingRemainderTrading();
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
