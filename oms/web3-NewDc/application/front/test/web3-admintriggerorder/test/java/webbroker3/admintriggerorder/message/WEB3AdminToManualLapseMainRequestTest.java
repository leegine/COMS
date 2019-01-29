head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToManualLapseMainRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminToManualLapseMainRequestTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToManualLapseMainRequestTest.class); // ///////////

    WEB3AdminToManualLapseMainRequest l_request = null; // ///////////////

    public WEB3AdminToManualLapseMainRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true); // //////////
        l_request = new WEB3AdminToManualLapseMainRequest(); // ////////////////
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    // �P�j �،���ЃR�[�h�`�F�b�N
    // �P�|�P�j this.�،���ЃR�[�h == null�̏ꍇ�A
    // �u�،���ЃR�[�h�����w��ł��B�v�̗�O���X���[����B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00827, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    
    // �Q�j �X���b�hNo�`�F�b�N
    // �Q�|�P�j this.�X���b�hNo == null�̏ꍇ�A
    // �u�X���b�h�ԍ��̎w��Ȃ��B�v�̗�O���X���[����B
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01974, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    // �R�j From����ID�`�F�b�N
    // �R�|�P�j this.From����ID == null�̏ꍇ�A
    // �uFrom����ID�������́v�̗�O���X���[����B
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02421, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    // �S�j To����ID�`�F�b�N
    // �S�|�P�j this.To����ID == null�̏ꍇ�A
    // �uTo����ID�i���j�������́v�̗�O���X���[����B
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = new Long(2);
            l_request.rangeTo = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02422, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }
    // �T�j �����Ώے��������̃`�F�b�N
    // this.�����Ώے������� == null�̏ꍇ�A
    // �u�����Ώے��������������́v�̗�O���X���[����B
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = new Long(2);
            l_request.rangeTo = new Long(3);
            l_request.lapseTargetOrderCondition = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02420, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }    
    // �U�j ���X�R�[�h == null�̏ꍇ�A
    // �u���X�R�[�h��null�v�̗�O���X���[����B
    // ���X�R�[�h���������������������C�e�o�ُ�
    // BUSINESS_ERROR_02174
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = new Long(2);
            l_request.rangeTo = new Long(3);
            l_request.lapseTargetOrderCondition = new WEB3AdminToLapseTargetOrderCondition();
            l_request.lapseTargetOrderCondition.branchCode = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
        log.exiting(STR_METHOD_NAME);
    }



    // �V�j this.�����Ώے�������.validate()���R�[������B
    // �V�|�P�j����ID�`�F�b�N
    // ����ID != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
    // �V�|�Q�j���X�R�[�h == null�̏ꍇ�A
    // �E���X�R�[�h != ����
    // �E���X�R�[�h.length != 3
    // �V�|�R�j ����������ʈꗗ�`�F�b�N
    // �V�|�S�j ���i�敪�ꗗ�`�F�b�N
    // �V�|�T�j ���i�敪�ꗗ == null�̏ꍇ�A�u���i�敪�ꗗ�����w��ł��B
    // �V�|�U�j �����R�[�h�`�F�b�N
    // �����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
    // �V�|�V�j �ڋq�R�[�h�`�F�b�N
    // �ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
    // normalcase
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_request.institutionCode = "1111";
            l_request.threadNo = new Long(1);
            l_request.rangeFrom = new Long(2);
            l_request.rangeTo = new Long(3);
            l_request.lapseTargetOrderCondition = new WEB3AdminToLapseTargetOrderCondition();
            l_request.lapseTargetOrderCondition.id = "11111";
            l_request.lapseTargetOrderCondition.branchCode = new String[]{"222"};
            l_request.lapseTargetOrderCondition.triggerOrderTypeList =new String[]{"1"};
            l_request.lapseTargetOrderCondition.productDivList = new String[]{"1"};
            l_request.lapseTargetOrderCondition.marketList = new String[]{"1"};
            l_request.lapseTargetOrderCondition.productCode = "11111";
            l_request.lapseTargetOrderCondition.accountCode = "111111";
            l_request.validate();
    }
    catch (Exception l_ex)
    {
        log.exiting(STR_METHOD_NAME);
        fail();
    }
    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
    log.exiting(STR_METHOD_NAME);
}
    
    
}
@
