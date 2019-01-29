head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.47.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SuccOptionsOpenInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3SuccOptionsOpenInputRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/03/25 �k�v�u (���u) �V�K�쐬  
*/
package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3SuccOptionsOpenInputRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SuccOptionsOpenInputRequestTest.class);

    /**
     * <BR>
     */
    private WEB3SuccOptionsOpenInputRequest succOptionsOpenInputRequest = null;

    public WEB3SuccOptionsOpenInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.succOptionsOpenInputRequest = new WEB3SuccOptionsOpenInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    //�Q�j�@@�A���������ʏ��`�F�b�N<
    // �@@�Q�|�P�j�@@this.�A���������ʏ��==null�̏ꍇ�A
    // �@@�@@�@@�@@�@@�@@�u�A���������ʏ�񂪖��w��v�̗�O���X���[����B
    public void testvalidate_C0001()
    {
        final String STR_METHOD_NAME = "testvalidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��null�̏ꍇ
            succOptionsOpenInputRequest.succCommonInfo = null;
            succOptionsOpenInputRequest.validate();
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

    // �Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
    // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
    // �@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j"<BR>
    // �@@�@@�@@�@@�@@"OP�V�K��"<BR>
    //�@@�@@�@@�@@�@@���R�[�h�l�́A�敨OP�\�񒍕��P�ʃe�[�u�����Q�ƁB
    public void testvalidate_C0002()
    {
        final String STR_METHOD_NAME = "testvalidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            //�Q�|�R�j�@@this.�A���������ʏ��.�A����������敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
            // �@@�@@�@@�@@�@@�@@�u�A����������敪�̒l�������ΏۊO�v�̗�O��throw����B<BR>
            // �@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j"<BR>
            // �@@�@@�@@�@@�@@"OP�V�K��"<BR>
            succOptionsOpenInputRequest.succCommonInfo.succTradingType = "14";
            succOptionsOpenInputRequest.validate();
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

    // �R�j�@@�A����������敪�`�F�b�N<BR>
    // �@@�R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="OP�V�K���i�O�񒍕��j"�̏ꍇ�A<BR>
    // �@@�@@�@@�@@�@@�@@�u�����R�[�h�v���ݒ肳��Ă��Ȃ���΁A<BR>
    // �@@�@@�@@�@@�@@�@@�u���̓p�����[�^�`�F�b�N�G���[�B�v�̗�O��throw����B
    public void testvalidate_C0003()
    {
        final String STR_METHOD_NAME = "testvalidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // �R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="OP�V�K���i�O�񒍕��j"�̏ꍇ�A
            succOptionsOpenInputRequest.succCommonInfo.succTradingType = "15";
            //�����R�[�h== null
            succOptionsOpenInputRequest.opProductCode = null;
            succOptionsOpenInputRequest.validate();
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

    //correct case �@@this.�A���������ʏ��.�A����������敪=="OP�V�K��"�̏ꍇ�A
    public void testvalidate_C0004()
    {
        final String STR_METHOD_NAME = "testvalidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // �R�|�P�j�@@this.�A���������ʏ��.�A����������敪=="OP�V�K��"�̏ꍇ�A
            succOptionsOpenInputRequest.succCommonInfo.succTradingType = "16";
            succOptionsOpenInputRequest.opProductCode = "0001";
            succOptionsOpenInputRequest.validate();
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
    //correct case this.�A���������ʏ��.�A����������敪=="OP�V�K���i�O�񒍕��j"
    public void testvalidate_C0005()
    {
        final String STR_METHOD_NAME = "testvalidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate();
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = null;
            // �Q�|�P�j this.�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            succOptionsOpenInputRequest.succCommonInfo.parentOrderId = "0001";
            // �R�|�P�j�@@this.�A���������ʏ��.�A����������敪="OP�V�K���i�O�񒍕��j"�̏ꍇ�A
            succOptionsOpenInputRequest.succCommonInfo.succTradingType = "16";
            succOptionsOpenInputRequest.validate();
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
            //super.validate();
            succOptionsOpenInputRequest.contractType = null;
            succOptionsOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
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
    //�Q�|�Q�j�@@�A���������ʏ��.validate()���R�[������B
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //super.validate()
            succOptionsOpenInputRequest.contractType = "1";
            succOptionsOpenInputRequest.marketCode = "1";
            succOptionsOpenInputRequest.targetProductCode = "0001";
            succOptionsOpenInputRequest.delivaryMonth = "200103";
            succOptionsOpenInputRequest.opProductType = "C";
            succOptionsOpenInputRequest.strikePrice = "1000";
            //this.�A���������ʏ��!��null�̏ꍇ
            succOptionsOpenInputRequest.succCommonInfo = new WEB3SuccCommonInfo();
            succOptionsOpenInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException  l_ex)
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
