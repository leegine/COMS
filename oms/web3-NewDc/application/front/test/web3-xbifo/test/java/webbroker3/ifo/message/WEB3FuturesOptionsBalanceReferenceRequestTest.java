head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOptionsBalanceReferenceRequestTest.java;


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

public class WEB3FuturesOptionsBalanceReferenceRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOptionsBalanceReferenceRequestTest.class);

    private WEB3FuturesOptionsBalanceReferenceRequest l_futuresOptionsBalanceReferenceRequest = null;

    public WEB3FuturesOptionsBalanceReferenceRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_futuresOptionsBalanceReferenceRequest = new WEB3FuturesOptionsBalanceReferenceRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * �P�j�@@�敨�^�I�v�V�����敪�̃`�F�b�N<BR>
     * �@@�P�|�P�jnull�̏ꍇ�A��O�Ƃ���B<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01736, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �@@�P�|�Q�j�ȉ��̍��ڈȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�E1(�敨)<BR>
     * �@@�@@�@@�E2(�I�v�V����)<BR>
     */
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "0";
            l_futuresOptionsBalanceReferenceRequest.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01737, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �Q�j�@@���Ϗ�ԋ敪�̃`�F�b�N<BR>
     * �@@�@@�@@�ȉ��̏�ԈȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�Enull(�w��Ȃ�) <BR>
     *       �E1(������)<BR>
     *       �E2(���ϒ�)<BR>
     */
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = "0";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �R�j�@@�����w���敨�I�v�V�����\�[�g�L�[�`�F�b�N <BR>
     * �@@�R�|�P�jthis.�����w���敨�I�v�V�����\�[�g�L�[�� <BR>
     * �@@�@@�@@�@@null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �@@�R�|�Q�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR> 
     * �@@�@@�@@�@@�O�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[0];
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �@@�R�|�R�jthis.�����w���敨�I�v�V�����\�[�g�L�[�̗v�f����<BR> 
     * �@@�@@�@@�@@�J��Ԃ��ă`�F�b�N���s���B <BR>
     * �@@�@@�R�|�R�|�P�j�����w���敨�I�v�V�����\�[�g�L�[.�L�[���ڂ�null�̒l�ł���Η�O���X���[����B<BR> 
     */
    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �@@�@@�R�|�R�|�Q�j�����w���敨�I�v�V�����\�[�g�L�[.�L�[���ڂɈȉ��̍��ږ��ȊO�̒l��<BR> 
     * �@@�@@�@@�@@�@@�@@�@@���݂������O���X���[����B<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.�����R�[�h<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.���敪<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.����<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.���v<BR>
     * �@@�@@�@@�E�敨OP�c���Ɖ��.���v(���o�)<BR>
     */
    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "abc";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �@@�@@�R�|�R�|�R�j�����w���敨�I�v�V�����\�[�g�L�[.�����^�~����null�̒l�ł���Η�O���X���[����B<BR>
     */
    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �@@�@@�R�|�R�|�S�j�����w���敨�I�v�V�����\�[�g�L�[.�����^�~�����ȉ��̒l�ȊO�ł���Η�O���X���[����B <BR>
     * �@@�@@�@@�E�hA�F�����h <BR>
     * �@@�@@�@@�E�hD�F�~���h<BR>
     */
    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "B";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �S�j�@@�v���y�[�W�ԍ��̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ������w��̏ꍇ�A<BR>
     * ���N�G�X�g�f�[�^�D�v���y�[�W�ԍ��Ɂu�P�v���Z�b�g����B<BR>
     */
    public void testValidate_C00010()
    {
        final String STR_METHOD_NAME = "testValidate_C00010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.validate();
            assertEquals(l_futuresOptionsBalanceReferenceRequest.pageIndex = "1",l_futuresOptionsBalanceReferenceRequest.pageIndex);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �T�j�@@�y�[�W���\���s���̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�A<BR>
     */
    public void testValidate_C00011()
    {
        final String STR_METHOD_NAME = "testValidate_C00011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �T�j�@@�y�[�W���\���s���̃`�F�b�N<BR>
     * ���N�G�X�g�f�[�^�D�y�[�W���\���s�����O�A�܂��͖��w��̏ꍇ�A<BR>
     */
    public void testValidate_C00012()
    {
        final String STR_METHOD_NAME = "testValidate_C00012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "0";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �U�|�P�j�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ�͗�O���X���[����B<BR>
     *      �敨�̏ꍇ(�敨/�I�v�V�����敪���敨)<BR>
     */
    public void testValidate_C00013()
    {
        final String STR_METHOD_NAME = "testValidate_C00013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = "1";
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = "0005";
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = "200807";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     *  �@@�U�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
     *            ����s��A�w����ʁA�����̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
     *         �敨�̏ꍇ(�敨/�I�v�V�����敪���敨)<BR>
     */
    public void testValidate_C00014()
    {
        final String STR_METHOD_NAME = "testValidate_C00014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = null;
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = null;
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     *  �@@�U�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
     *            ����s��A�w����ʁA�����̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
     *         �敨�̏ꍇ(�敨/�I�v�V�����敪���敨)<BR>
     */
    public void testValidate_C00015()
    {
        final String STR_METHOD_NAME = "testValidate_C00015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = null;
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = "0005";
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = "200807";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     * �U�|�P�j�����R�[�h�Ɩ������荀�ڂ��ǂ�����ݒ肳��Ă���ꍇ�͗�O���X���[����B<BR>
     *      �I�v�V�����̏ꍇ(�敨/�I�v�V�����敪���I�v�V����)<BR>
     */
    public void testValidate_C00016()
    {
        final String STR_METHOD_NAME = "testValidate_C00016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "2";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = "1";
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = "0005";
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = "200807";
            l_futuresOptionsBalanceReferenceRequest.opProductType = "P";
            l_futuresOptionsBalanceReferenceRequest.strikePrice = "1234";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     *  �@@�U�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
     *            ����s��A�w����ʁA�����̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
     *         �I�v�V�����̏ꍇ(�敨/�I�v�V�����敪���I�v�V����)<BR>
     */
    public void testValidate_C00017()
    {
        final String STR_METHOD_NAME = "testValidate_C00017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = null;
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = null;
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = null;
            l_futuresOptionsBalanceReferenceRequest.opProductType = null;
            l_futuresOptionsBalanceReferenceRequest.strikePrice = null;
            l_futuresOptionsBalanceReferenceRequest.validate();
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
     *  �@@�U�|�Q�j�������荀�ڂɂ������w��̏ꍇ<BR>
     *            ����s��A�w����ʁA�����̑S�Ă��ݒ肳��Ă��Ȃ���΁A��O���X���[����B<BR>
     *         �I�v�V�����̏ꍇ(�敨/�I�v�V�����敪���I�v�V����)<BR>
     */
    public void testValidate_C00018()
    {
        final String STR_METHOD_NAME = "testValidate_C00018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_futuresOptionsBalanceReferenceRequest.fuOpDiv = "1";
            l_futuresOptionsBalanceReferenceRequest.settlementState = null;
            l_futuresOptionsBalanceReferenceRequest.sortKeys = new WEB3FuturesOptionsSortKey[1];
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].keyItem = "openDate";
            l_futuresOptionsBalanceReferenceRequest.sortKeys[0].ascDesc = "A";
            l_futuresOptionsBalanceReferenceRequest.pageIndex = null;
            l_futuresOptionsBalanceReferenceRequest.pageSize = "8888";
            l_futuresOptionsBalanceReferenceRequest.productCode = "1001";
            l_futuresOptionsBalanceReferenceRequest.marketCode = null;
            l_futuresOptionsBalanceReferenceRequest.targetProductCode = "0005";
            l_futuresOptionsBalanceReferenceRequest.delivaryMonth = "200807";
            l_futuresOptionsBalanceReferenceRequest.opProductType = "P";
            l_futuresOptionsBalanceReferenceRequest.strikePrice = "1234";
            l_futuresOptionsBalanceReferenceRequest.validate();
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
