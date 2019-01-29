head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesCloseInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g�e�X�g(WEB3SuccFuturesCloseInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/17 �k�v�u (���u) �V�K�쐬
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
/**
 * �i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g(BR)
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesCloseInputRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesCloseInputRequestTest.class);

    private WEB3SuccFuturesCloseInputRequest succFuturesCloseInputRequest= null;

    public WEB3SuccFuturesCloseInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesCloseInputRequest = new WEB3SuccFuturesCloseInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�P�j�@@�A���������ʏ��`�F�b�N
    //  �P�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A
    // �u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = null;
            succFuturesCloseInputRequest.validate();
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

    //   �P�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
    // "�敨�ԍρi�O�񒍕��j"
    // "�敨�ԍρi�����c�j"
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succFuturesCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseInputRequest.succCommonInfo.succTradingType = "12";
            succFuturesCloseInputRequest.validate();
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
    //   �Q�j this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ�̂݁A
    //  super.validate()���R�[������B
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succFuturesCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseInputRequest.succCommonInfo.succTradingType = "14";
            // super.validate()���R�[������B
            succFuturesCloseInputRequest.id = null;
            succFuturesCloseInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�O�񒍕��j"�̏ꍇ
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succFuturesCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseInputRequest.succCommonInfo.succTradingType = "13";
            succFuturesCloseInputRequest.validate();
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
    //correct case  this.�A���������ʏ��.�A����������敪=="�敨�ԍρi�����c�j"�̏ꍇ
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate
            succFuturesCloseInputRequest.succCommonInfo.parentOrderId = "0001";
            succFuturesCloseInputRequest.succCommonInfo.succTradingType = "14";
            // super.validate()���R�[������B
            succFuturesCloseInputRequest.id = new String[]{"0001","0002"};
            succFuturesCloseInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[]
                          { new WEB3FuturesOptionsSortKey(), new WEB3FuturesOptionsSortKey()};
            int l_intObjectItemLength = succFuturesCloseInputRequest.futOpSortKeys.length;
            for (int i = 0; i < l_intObjectItemLength; i++)
            {
                succFuturesCloseInputRequest.futOpSortKeys[i].keyItem = "openDate";
                succFuturesCloseInputRequest.futOpSortKeys[i].ascDesc = "A";
            }
            succFuturesCloseInputRequest.validate();
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
    //this.�A���������ʏ��.validate()���R�[������B
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            succFuturesCloseInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesCloseInputRequest.validate();
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
