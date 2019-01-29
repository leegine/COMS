head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginInputRequestTest.java;


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
Revesion History : 2008/07/07 ����(���u) �V�K�쐬  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsCloseMarginInputRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsCloseMarginInputRequestTest.class);

    private WEB3OptionsCloseMarginInputRequest l_optionsCloseMarginInputRequest = null;

    public WEB3OptionsCloseMarginInputRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_optionsCloseMarginInputRequest = new WEB3OptionsCloseMarginInputRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = null;
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *�Q�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�\�[�g�L�[��null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = null;
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�Q�|�Q�jthis.�\�[�g�L�[�̗v�f�����O�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[0];
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�Q�|�R�jthis.�\�[�g�L�[�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�Q�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = null;
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�Q�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR>
     * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
     * �@@�@@�@@�E����<BR>
     * �@@�@@�@@�E���P��<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = "abc";
            l_optionsCloseMarginInputRequest.validate();
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
     * �@@�@@�Q�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].ascDesc = null;
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�@@�Q�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�E�hA�F�����h<BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].ascDesc = "B";
            l_optionsCloseMarginInputRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *  normal case
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginInputRequest.id = new String[1];
            l_optionsCloseMarginInputRequest.id[0] = "1001"; 
            l_optionsCloseMarginInputRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginInputRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsCloseMarginInputRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginInputRequest.validate();
            assertTrue(true);
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
