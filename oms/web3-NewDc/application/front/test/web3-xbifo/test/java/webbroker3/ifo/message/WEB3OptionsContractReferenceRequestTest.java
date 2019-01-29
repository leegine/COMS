head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsContractReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e����� 
File Name        : WEB3OptionsOrderHistoryRequestTest.java
Author Name      : Daiwa Institute of Research  
Revesion History : 2008/07/04 ����(���u) �V�K�쐬  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsContractReferenceRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsContractReferenceRequestTest.class);

    private WEB3OptionsContractReferenceRequest l_optionsContractReferenceRequest = null;

    public WEB3OptionsContractReferenceRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_optionsContractReferenceRequest = new WEB3OptionsContractReferenceRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     *  �P�j�@@���Ϗ�ԋ敪�̃`�F�b�N<BR>
     *�@@�@@�@@�ȉ��̏�ԈȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     *�@@�@@�@@�Enull(�w��Ȃ�) <BR>
     *�@@�@@�@@�E0(���ύ�)<BR>
     *      �E1(������)<BR>
     *      �E2(���ϒ�)<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "3";
            l_optionsContractReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00233, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *  �Q�j�@@�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�@@�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�@@�E����<BR>
     * �@@�@@�@@�@@�E����<BR>
     * �@@�@@�@@�@@�E���v<BR>
     * �@@�@@�@@�@@�E���敪<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = null;
            l_optionsContractReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *  �Q�j�@@�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�@@�\�[�g�L�[�̔z��̌����A�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�@@�E����<BR>
     * �@@�@@�@@�@@�E����<BR>
     * �@@�@@�@@�@@�E���v<BR>
     * �@@�@@�@@�@@�E���敪<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsContractReferenceRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsContractReferenceRequest.futOpSortKeys[0].keyItem = "aaaa";
            l_optionsContractReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *   �R�j�@@�v���y�[�W�ԍ��̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ������w��̏ꍇ�A<BR>
     * ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsContractReferenceRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsContractReferenceRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsContractReferenceRequest.pageIndex = null;
            l_optionsContractReferenceRequest.pageSize = "8888";
            l_optionsContractReferenceRequest.validate();
            assertEquals(l_optionsContractReferenceRequest.pageIndex = "1", l_optionsContractReferenceRequest.pageIndex);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *   �S�j�@@�y�[�W���\���s���̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�A<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsContractReferenceRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsContractReferenceRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsContractReferenceRequest.pageIndex = null;
            l_optionsContractReferenceRequest.pageSize = "0";
            l_optionsContractReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *   �S�j�@@�y�[�W���\���s���̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�A<BR>
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsContractReferenceRequest.settlementState = "0";
            l_optionsContractReferenceRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsContractReferenceRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsContractReferenceRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsContractReferenceRequest.pageIndex = null;
            l_optionsContractReferenceRequest.pageSize = "";
            l_optionsContractReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00091, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
}
@
