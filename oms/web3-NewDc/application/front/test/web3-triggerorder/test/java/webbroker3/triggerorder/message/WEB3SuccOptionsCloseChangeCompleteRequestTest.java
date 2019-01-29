head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsCloseChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
 File Name        : WEB3SuccOptionsCloseChangeCompleteRequestTest.java
 Author Name      : Daiwa Institute of Research  
 Revesion History : 2008/03/25 �k�v�u (���u) �V�K�쐬  
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsCloseChangeCompleteRequestTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsCloseChangeCompleteRequestTest.class);

    WEB3SuccOptionsCloseChangeCompleteRequest succOptionsCloseChangeCompleteRequest = null;

    public WEB3SuccOptionsCloseChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsCloseChangeCompleteRequest = new WEB3SuccOptionsCloseChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // 2�j �A�������P�������l���`�F�b�N
    // 2�|�P�j �A�������P�������l���null�̏ꍇ�A
    // �A�������P�������l���.validate()���R�[������B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1�j�@@�m�F���T�Z��n����`�F�b�N
            succOptionsCloseChangeCompleteRequest.estimatedPrice = "1000";
            // 2�j �A�������P�������l���`�F�b�N
            // 2�|�P�j �A�������P�������l���null�̏ꍇ
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // �A�������P�������l���.validate()���R�[������B
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsCloseChangeCompleteRequest.validate();
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

    // 2�|�Q�j �A�������P�������l���null�̏ꍇ�A
    // �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
    // ��O��throw����B
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1�j�@@�m�F���T�Z��n����`�F�b�N
            succOptionsCloseChangeCompleteRequest.estimatedPrice = "1000";
            // 2�j �A�������P�������l���`�F�b�N
            // 2�|�P�j �A�������P�������l���null�̏ꍇ
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // �A�������P�������l���.validate()���R�[������B
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            // 2�|�Q�j �A�������P�������l���null�̏ꍇ�A
            // �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            // ��O��throw����B
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseChangeCompleteRequest.validate();
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

    // correct case �A�������P�������l���null�̏ꍇ
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1�j�@@�m�F���T�Z��n����`�F�b�N
            succOptionsCloseChangeCompleteRequest.estimatedPrice = "1000";
            // 2�j �A�������P�������l���`�F�b�N
            // 2�|�P�j �A�������P�������l���null�̏ꍇ
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // �A�������P�������l���.validate()���R�[������B
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            // 2�|�Q�j �A�������P�������l���null�̏ꍇ�A
            // �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            // ��O��throw����B
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsCloseChangeCompleteRequest.validate();
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

    // correct case �A�������P�������l���==null�̏ꍇ
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1�j�@@�m�F���T�Z��n����`�F�b�N
            succOptionsCloseChangeCompleteRequest.estimatedPrice = "1000";
            // 2�j �A�������P�������l���`�F�b�N
            // 2�|�P�j �A�������P�������l���null�̏ꍇ
            succOptionsCloseChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsCloseChangeCompleteRequest.validate();
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
            // �P�j �X�[�p�[�N���X��validate���\�b�h���R�[������B
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = null;
            succOptionsCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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

    // �Q�j�A�������E���������`�F�b�N<BR>
    public void testValidateATExistingRemainderTrading_C0002()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate()
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseChangeCompleteRequest.limitPrice = "1000";
            succOptionsCloseChangeCompleteRequest.execCondType = "3";
            succOptionsCloseChangeCompleteRequest.expirationDateType = "1";
            succOptionsCloseChangeCompleteRequest.expirationDate = null;
            succOptionsCloseChangeCompleteRequest.orderCondType = "0";
            succOptionsCloseChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseChangeCompleteRequest.wLimitPrice = null;
            succOptionsCloseChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseChangeCompleteRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succOptionsCloseChangeCompleteRequest.id = "0001";
            succOptionsCloseChangeCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
            {new WEB3FuturesOptionsCloseMarginContractUnit(), new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseChangeCompleteRequest.opOrderQuantity = "1000";
            for (int i = 0; i < succOptionsCloseChangeCompleteRequest.closeMarginContractUnits.length; i++)
            succOptionsCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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

    // correct case
    public void testValidateATExistingRemainderTrading_C0003()
    {
        final String STR_METHOD_NAME = "testValidateATExistingRemainderTrading_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.super.validate()
            succOptionsCloseChangeCompleteRequest.orderPriceDiv = "1";
            succOptionsCloseChangeCompleteRequest.limitPrice = "1000";
            succOptionsCloseChangeCompleteRequest.execCondType = "1";
            succOptionsCloseChangeCompleteRequest.expirationDateType = "1";
            succOptionsCloseChangeCompleteRequest.expirationDate = null;
            succOptionsCloseChangeCompleteRequest.orderCondType = "0";
            succOptionsCloseChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsCloseChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsCloseChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsCloseChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsCloseChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsCloseChangeCompleteRequest.wLimitPrice = null;
            succOptionsCloseChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsCloseChangeCompleteRequest.wlimitEnableStatusDiv = null;
            // super.validate()
            succOptionsCloseChangeCompleteRequest.id = "0001";
            succOptionsCloseChangeCompleteRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]
            {new WEB3FuturesOptionsCloseMarginContractUnit(), new WEB3FuturesOptionsCloseMarginContractUnit()};
            succOptionsCloseChangeCompleteRequest.opOrderQuantity = "1000";
            succOptionsCloseChangeCompleteRequest.validateATExistingRemainderTrading();
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
    //1�j�@@�m�F���T�Z��n����`�F�b�N
    // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
    // �@@��O��throw����B
    // �@@�@@�Enull
    // �@@�@@�E�����ȊO
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 1�j�@@�m�F���T�Z��n����`�F�b�N
            succOptionsCloseChangeCompleteRequest.estimatedPrice = null;
            succOptionsCloseChangeCompleteRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02292, l_ex.getErrorInfo());
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
