head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToManualLapseRunRequestTest.java;


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

public class WEB3AdminToManualLapseRunRequestTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToManualLapseRunRequestTest.class); // ///////////

    WEB3AdminToManualLapseRunRequest l_request = null; // ///////////////

    public WEB3AdminToManualLapseRunRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true); // //////////
        l_request = new WEB3AdminToManualLapseRunRequest(); // ////////////////
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //  �P�j�@@�����Ώے��������̃`�F�b�N
    //�@@�P�|�P�j�@@this.�����Ώے������� == null�̏ꍇ�A
    //�@@�@@�u�����Ώے��������������́v�̗�O���X���[����B
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
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

    //  �Q�j�@@this.�����Ώے�������.validate()���R�[������B
    //  �Q�|�P�j����ID�`�F�b�N
    //�@@        ����ID != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
    // �@@�Q�|�Q�j���X�R�[�h == null�̏ꍇ�A
    //�@@�@@�@@�@@�@@�@@�E���X�R�[�h != ����
    //�@@�@@�@@�@@�@@�@@�E���X�R�[�h.length != 3
    //  �Q�|�R�j�@@����������ʈꗗ�`�F�b�N
    //  �Q�|�S�j�@@���i�敪�ꗗ�`�F�b�N
    //�@@�Q�|�T�j ���i�敪�ꗗ == null�̏ꍇ�A�u���i�敪�ꗗ�����w��ł��B
    //�@@�Q�|�U�j�@@�����R�[�h�`�F�b�N
    //�@@        �����R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
    //�@@�Q�|�V�j�@@�ڋq�R�[�h�`�F�b�N
    //�@@        �ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B
    // normalcase
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
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
    
    //  �R�j�@@���X�R�[�h == null�̏ꍇ�A
    //   �u���X�R�[�h��null�v�̗�O���X���[����B
    // ���X�R�[�h���������������������C�e�o�ُ�
    // BUSINESS_ERROR_02174
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
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

}
@
