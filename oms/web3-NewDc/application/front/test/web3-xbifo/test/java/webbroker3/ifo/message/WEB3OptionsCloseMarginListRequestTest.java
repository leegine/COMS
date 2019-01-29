head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsCloseMarginListRequestTest.java;


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

public class WEB3OptionsCloseMarginListRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionsCloseMarginListRequestTest.class);

    private WEB3OptionsCloseMarginListRequest l_optionsCloseMarginListRequest = null;

    public WEB3OptionsCloseMarginListRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_optionsCloseMarginListRequest = new WEB3OptionsCloseMarginListRequest();
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
            l_optionsCloseMarginListRequest.futOpSortKeys = null;
            l_optionsCloseMarginListRequest.validate();
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
     * �@@�P�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
     * �@@�@@�@@�@@�O�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[0];
            l_optionsCloseMarginListRequest.validate();
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
     * �@@�P�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR>
     * �@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B<BR>
     * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = null;
            l_optionsCloseMarginListRequest.validate();
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
     * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR>
     * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
     * �@@�@@�@@�E�����R�[�h<BR>
     * �@@�@@�@@�E���敪<BR>
     * �@@�@@�@@�E���v<BR>
     * �@@�@@�@@�E���v(���o�)<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "aaaa";
            l_optionsCloseMarginListRequest.validate();
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
     * �@@�@@�P�|�R�|�R�j�\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = null;
            l_optionsCloseMarginListRequest.validate();
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
     * �@@�@@�P�|�R�|�S�j�\�[�g�L�[.�����^�~���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "B";
            l_optionsCloseMarginListRequest.validate();
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
     * �Q�j�@@�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�v���y�[�W�ԍ���null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = null;
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "abc";
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �R�j�@@�y�[�W���\���s���`�F�b�N<BR>
     * �@@�R�|�P�jthis.�y�[�W���\���s����null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = null;
            l_optionsCloseMarginListRequest.validate();
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
     * �@@�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C00010()
    {
        final String STR_METHOD_NAME = "testValidate_C00010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = "abc";
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �S�j�@@�����ݒ�`�F�b�N<BR>
     *   �S�|�P�j�ȉ��̑S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B<BR>
     *        �@@(�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ)<BR>
     *      �E�����R�[�h<BR>
     *      �E����s��<BR>
     *      �E�w�����<BR>
     *      �E����<BR>
     *      �E�I�v�V�������i�敪<BR>
     *      �E�s�g���i<BR>
     */
    public void testValidate_C00011()
    {
        final String STR_METHOD_NAME = "testValidate_C00011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = "02";
            l_optionsCloseMarginListRequest.opProductCode = "1001";
            l_optionsCloseMarginListRequest.marketCode = "1";
            l_optionsCloseMarginListRequest.targetProductCode = "0005";
            l_optionsCloseMarginListRequest.delivaryMonth = "200807";
            l_optionsCloseMarginListRequest.opProductType = "P";
            l_optionsCloseMarginListRequest.strikePrice = "2000";
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�S�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
     *      ����s��A�w����ʁA�����A�I�v�V�������i�敪�A�s�g���i�̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
     */
    public void testValidate_C00012()
    {
        final String STR_METHOD_NAME = "testValidate_C00012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = "02";
            l_optionsCloseMarginListRequest.marketCode = null;
            l_optionsCloseMarginListRequest.targetProductCode = null;
            l_optionsCloseMarginListRequest.delivaryMonth = null;
            l_optionsCloseMarginListRequest.opProductType = null;
            l_optionsCloseMarginListRequest.strikePrice = null;
            l_optionsCloseMarginListRequest.validate();
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�S�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
     *      ����s��A�w����ʁA�����A�I�v�V�������i�敪�A�s�g���i�̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
     */
    public void testValidate_C00013()
    {
        final String STR_METHOD_NAME = "testValidate_C00013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_optionsCloseMarginListRequest.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
            l_optionsCloseMarginListRequest.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_optionsCloseMarginListRequest.futOpSortKeys[0].keyItem = "opProductCode";
            l_optionsCloseMarginListRequest.futOpSortKeys[0].ascDesc = "A";
            l_optionsCloseMarginListRequest.pageIndex = "01";
            l_optionsCloseMarginListRequest.pageSize = "02";
            l_optionsCloseMarginListRequest.marketCode = null;
            l_optionsCloseMarginListRequest.targetProductCode = "0005";
            l_optionsCloseMarginListRequest.delivaryMonth = "200807";
            l_optionsCloseMarginListRequest.opProductType = "P";
            l_optionsCloseMarginListRequest.strikePrice = "2000";
            l_optionsCloseMarginListRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
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
