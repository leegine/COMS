head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.48.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccFuturesOpenInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�����w���敨�V�K���������͉�ʃ��N�G�X�g�e�X�g(WEB3SuccFuturesOpenInputRequestTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/03/17 �k�v�u (���u) �V�K�쐬
 */
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�A���j�����w���敨�V�K���������͉�ʃ��N�G�X�g<BR>
 * <BR>
 * 
 * @@author yang-fuzhi
 * @@version 1.0
 */
public class WEB3SuccFuturesOpenInputRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccFuturesOpenInputRequestTest.class);

    /**
     * <BR>
     */
    private WEB3SuccFuturesOpenInputRequest succFuturesOpenInputRequest = null;

    /**
     * <BR>
     * 
     * @@param arg0
     */
    public WEB3SuccFuturesOpenInputRequestTest(String arg0)
    {
        super(arg0);
    }

    /**
     * <BR>
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succFuturesOpenInputRequest = new WEB3SuccFuturesOpenInputRequest();
    }

    /**
     * <BR>
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //  �Q�j�@@�A���������ʏ��`�F�b�N
    //  �Q�|�P�j�@@this.�A���������ʏ��null�̏ꍇ�A
    //  �u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��null�̏ꍇ
            succFuturesOpenInputRequest.succCommonInfo = null;
            succFuturesOpenInputRequest.validate();
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

    //  �Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A
    //  �u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B
    //"�敨�V�K���i�O�񒍕��j"<BR>
    //"�敨�V�K��"
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            //this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ
            //"�敨�V�K���i�O�񒍕��j"<BR>
            //"�敨�V�K��"
            succFuturesOpenInputRequest.succCommonInfo.succTradingType = "10";
            succFuturesOpenInputRequest.validate();
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

    // �R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="�敨�V�K���i�O�񒍕��j"�̏ꍇ
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // �R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="�敨�V�K���i�O�񒍕��j"�̏ꍇ�A
            succFuturesOpenInputRequest.succCommonInfo.succTradingType = "11";
            succFuturesOpenInputRequest.futProductCode = null;
            succFuturesOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //correct case this.�A���������ʏ��.�A����������敪=="�敨�V�K��"
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // �R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="�敨�V�K��"�̏ꍇ�A
            succFuturesOpenInputRequest.succCommonInfo.succTradingType = "12";
            succFuturesOpenInputRequest.validate();
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
    //correct case this.�A���������ʏ��.�A����������敪=="�敨�V�K���i�O�񒍕��j"
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succFuturesOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // �R�|�P�j�@@this.�A���������ʏ��.�A����������敪="�敨�V�K���i�O�񒍕��j"�̏ꍇ�A
            succFuturesOpenInputRequest.succCommonInfo.succTradingType = "11";
            succFuturesOpenInputRequest.futProductCode = "0001";
            succFuturesOpenInputRequest.validate();
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
    public void testvalidate_C0006()
    {
        final String STR_METHOD_NAME = "testvalidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = null;
            succFuturesOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00263, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    //�P�j�@@  �Q�|�Q�j�@@�A���������ʏ��.validate()���R�[������B
    public void testvalidate_C0007()
    {
        final String STR_METHOD_NAME = "testvalidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succFuturesOpenInputRequest.contractType = "1";
            succFuturesOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succFuturesOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succFuturesOpenInputRequest.validate();
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
