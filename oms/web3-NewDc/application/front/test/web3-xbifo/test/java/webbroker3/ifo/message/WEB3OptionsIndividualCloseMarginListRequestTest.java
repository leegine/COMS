head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsIndividualCloseMarginListRequestTest.java;


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
Revesion History : 2008/07/03 ����(���u) �V�K�쐬  
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsIndividualCloseMarginListRequestTest extends TestBaseForMock
{
    /*
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsIndividualCloseMarginListRequestTest.class);

    private WEB3OptionsIndividualCloseMarginListRequest l_optionsIndividualCloseMarginListRequest = null;

    public WEB3OptionsIndividualCloseMarginListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_optionsIndividualCloseMarginListRequest = new WEB3OptionsIndividualCloseMarginListRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * �P�j�@@�\�[�g�L�[�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[��<BR>
     * �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = null;
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
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
     *  �@@�P�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f���� <BR>
     *  �@@�@@�@@�@@�O�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[0];
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *   �P�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
     * �@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B <BR>
     * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = null;
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR>
     * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
     * �@@�@@�@@�E����<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "aaaa";
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�@@�P�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = null;
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�@@�P�|�R�|�S�j�\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�E�hA:���� D�F�~���h<BR>
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = "B";
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *   �Q�j�@@�h�c�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsIndividualCloseMarginListRequest.id = null;
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     *  �@@�Q�|�Q�jthis.�h�c�̗v�f�����O�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsIndividualCloseMarginListRequest.id = new String[0];
            l_optionsIndividualCloseMarginListRequest.validate();
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00282, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case<BR>
     */
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].keyItem = "openDate";
            l_optionsIndividualCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsIndividualCloseMarginListRequest.id = new String[3];
            l_optionsIndividualCloseMarginListRequest.id[0] = "1001";
            l_optionsIndividualCloseMarginListRequest.validate();
            assertTrue(true);
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
