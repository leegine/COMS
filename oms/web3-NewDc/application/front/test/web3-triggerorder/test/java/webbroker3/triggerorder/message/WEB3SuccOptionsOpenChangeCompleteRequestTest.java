head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenChangeCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3SuccOptionsOpenChangeCompleteRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenChangeCompleteRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenChangeCompleteRequestTest.class);

    /**
     *<BR>
     */
    private WEB3SuccOptionsOpenChangeCompleteRequest succOptionsOpenChangeCompleteRequest = null;
    public WEB3SuccOptionsOpenChangeCompleteRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenChangeCompleteRequest = new WEB3SuccOptionsOpenChangeCompleteRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�Q�j�@@�m�F���T�Z��n����`�F�b�N
    // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
    // �@@��O��throw����B
    // �@@�@@�Enull
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z��n����`�F�b�N
            // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
            // �@@��O��throw����B
            // �@@�@@�Enull
            succOptionsOpenChangeCompleteRequest.estimatedPrice = null;
            succOptionsOpenChangeCompleteRequest.validate();
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
    // �Q�j�@@�m�F���T�Z��n����`�F�b�N
    // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
    // �@@��O��throw����B
    // �@@�@@�E�����ȊO "XXXX"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z��n����`�F�b�N
            // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
            // �@@��O��throw����B
            // �@@�@@�E�����ȊO"XXXX"
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "aaa";
            succOptionsOpenChangeCompleteRequest.validate();
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
    //�Q�j�@@�m�F���T�Z��n����`�F�b�N
    // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
    // �@@��O��throw����B
    // �@@�@@�E�����ȊO ""
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z��n����`�F�b�N
            // �@@this.�m�F���T�Z��n����̒l���ȉ��̂����ꂩ�ɊY������ꍇ�́A
            // �@@��O��throw����B
            // �@@�@@�E�����ȊO""
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "";
            succOptionsOpenChangeCompleteRequest.validate();
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
    //�R�j�@@�A�������P�������l���`�F�b�N
    // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //�R�j�@@�A�������P�������l���`�F�b�N
            // �@@�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            // �@@�@@�@@�@@�@@�@@�A�������P�������l���.validate()���R�[������B
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = null;
            succOptionsOpenChangeCompleteRequest.validate();
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
    //        �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
    //        ��O��throw����B
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "1";
            succOptionsOpenChangeCompleteRequest.limitPrice = "1000";
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //�R�j�@@�A�������P�������l���null�̏ꍇ�A
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            // �R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �A�������P�������l���.validate()���R�[������B
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            //�R�|�Q�j�@@�A�������P�������l���null�̏ꍇ�A
            //        �����P���敪��"���s"�̏ꍇ�́u�P�������l�ƒ����P���敪�̎w�肪�s�����v��
            //        ��O��throw����BRequest.orderPriceDiv = "1";
            succOptionsOpenChangeCompleteRequest.validate();
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
    // �@@super.validate�A������()���R�[������B
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "3";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //�R�j�@@�A�������P�������l���=null�̏ꍇ�A
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenChangeCompleteRequest.validate();
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
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //�R�j �A�������P�������l���`�F�b�N
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = null;
            succOptionsOpenChangeCompleteRequest.validate();
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
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = "0";
            succOptionsOpenChangeCompleteRequest.limitPrice = null;
            succOptionsOpenChangeCompleteRequest.execCondType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDateType = "1";
            succOptionsOpenChangeCompleteRequest.expirationDate = null;
            succOptionsOpenChangeCompleteRequest.orderCondType = "0";
            succOptionsOpenChangeCompleteRequest.stopOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.stopOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitOrderCondOperator = null;
            succOptionsOpenChangeCompleteRequest.wLimitOrderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.wLimitPrice = null;
            succOptionsOpenChangeCompleteRequest.wlimitExecCondType = null;
            succOptionsOpenChangeCompleteRequest.wlimitEnableStatusDiv = null;
            //super.validate()
            succOptionsOpenChangeCompleteRequest.id = "0001";
            succOptionsOpenChangeCompleteRequest.opOrderQuantity = "1000";
            //�Q�j�@@�m�F���T�Z������`�F�b�N
            succOptionsOpenChangeCompleteRequest.estimatedPrice = "1000";
            //�R�j �A�������P�������l���`�F�b�N
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //�R�|�P�j�@@�A�������P�������l���null�̏ꍇ�A
            //  �A�������P�������l���.validate()���R�[������B
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.sign = "+";
            succOptionsOpenChangeCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            succOptionsOpenChangeCompleteRequest.validate();
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
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //�P�j�@@super.super.validate()
            succOptionsOpenChangeCompleteRequest.orderPriceDiv = null;
            succOptionsOpenChangeCompleteRequest.validate();
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
