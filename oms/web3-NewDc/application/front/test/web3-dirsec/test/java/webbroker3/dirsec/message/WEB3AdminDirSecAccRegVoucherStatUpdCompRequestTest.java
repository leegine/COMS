head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/20 ���G�� (���u) �V�K�쐬
*/
package webbroker3.dirsec.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest extends TestBaseForMock
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest.class);

    public WEB3AdminDirSecAccRegVoucherStatUpdCompRequest l_impl =
        new WEB3AdminDirSecAccRegVoucherStatUpdCompRequest();

    public WEB3AdminDirSecAccRegVoucherStatUpdCompRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * �ڋq���o�^�`�[���R�[�h�ڍהz�� == null �̏ꍇ
     *  �ڋq���o�^�`�[���R�[�h�ڍהz�� == null
     * �u�X�V�Ώۂ̃��R�[�h���s���ł��B�v��O���X���[����
     * BUSINESS_ERROR_02838"
     */
    public void testVolidate_case001()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case001";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02838, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �ڋq���o�^�`�[���R�[�h�ڍהz��̒��� == 0 �̏ꍇ
     * �ڋq���o�^�`�[���R�[�h�ڍהz��̒��� == 0
     * �u�X�V�Ώۂ̃��R�[�h���s���ł��B�v��O���X���[����
     * BUSINESS_ERROR_02838"
     */
    public void testVolidate_case002()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case002";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[0];
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02838, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * " �P-�P�j �،���ЃR�[�h�`�F�b�N
     *    �P-�P-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�،���ЃR�[�h == null�̏ꍇ
     *        �ڋq���o�^�`�[���R�[�h�ڍ�[index].�،���ЃR�[�h == null
     *            �u�،���ЃR�[�h���s���ł��B�v�̗�O���X���[����
     *                 BUSINESS_ERROR_01023"
     */
    public void testVolidate_case003()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case003";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01023, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "  �P-�Q�j ���X�R�[�h�`�F�b�N
     *     �P-�Q-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].���X�R�[�h == null�̏ꍇ
     *     �ڋq���o�^�`�[���R�[�h�ڍ�[index].���X�R�[�h == null
     *     "�u���X�R�[�h���s���ł��B�v�̗�O���X���[����B
     *     BUSINESS_ERROR_00779"
     */
    public void testVolidate_case004()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case004";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * �ڋq���o�^�`�[���R�[�h�ڍ�[index].�ڋq�R�[�h == null�̏ꍇ
     * �ڋq���o�^�`�[���R�[�h�ڍ�[index].�ڋq�R�[�h == null�̏ꍇ
     * �u�ڋq�R�[�h���s���ł��B�v�̗�O���X���[����
     * BUSINESS_ERROR_00780"
     */
    public void testVolidate_case005()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case005";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00780, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     *   �P-�S�j �f�[�^�R�[�h�`�F�b�N
     *       �P-�S-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�f�[�^�R�[�h == null �̏ꍇ
     *       �ڋq���o�^�`�[���R�[�h�ڍ�[index].�f�[�^�R�[�h == null
     *       �u�f�[�^�R�[�h���s���ł��B�v�̗�O���X���[����
     *       BUSINESS_ERROR_02828"
     */
    public void testVolidate_case006()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case006";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02828, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "  �P-�T�j ���ʃR�[�h�`�F�b
     *     �P-�T-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].���ʃR�[�h == null �̏ꍇ"
     *     �ڋq���o�^�`�[���R�[�h�ڍ�[index].���ʃR�[�h == null
     *     BUSINESS_ERROR_02829
     *
     */
    public void testVolidate_case007()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case007";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02829, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "  �P-�U�j �A����ʁA�`�[�ʔԃ`�F�b�N
     * �P-�U-�P�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�����J�ݓ`�[�t���O == FALSE
     *  AND �ڋq���o�^�`�[���R�[�h�ڍ�[index]�A����� == null �̏ꍇ"
     *  "�ڋq���o�^�`�[���R�[�h�ڍ�[index].�����J�ݓ`�[�t���O == FALSE
     *  AND �ڋq���o�^�`�[���R�[�h�ڍ�[index]�A����� == null "
     *   "�u�A����ʂ��s���ł��B�v�̗�O���X���[����
     *   BUSINESS_ERROR_02830"
     */
    public void testGetRequestCode_case008()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case008";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = false;
            l_impl.accVoucherRecord[0].infoType = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "    �P-�U-�Q�j �ڋq���o�^�`�[���R�[�h�ڍ�[index].�����J�ݓ`�[�t���O == TRUE
     *  AND �ڋq���o�^�`�[���R�[�h�ڍ�[index].�`�[�ʔ� == null �̏ꍇ"
     *  "�ڋq���o�^�`�[���R�[�h�ڍ�[index].�����J�ݓ`�[�t���O == TRUE
     *  AND �ڋq���o�^�`�[���R�[�h�ڍ�[index].�`�[�ʔ� == null
     *  �u�`�[�ʔԂ��s���ł��B�v�̗�O���X���[����B
     *  BUSINESS_ERROR_02831"
     */
    public void testGetRequestCode_case009()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case009";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02831, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�Q�j �`�[�쐬�󋵃`�F�b�N
     * �Q-�P�j this.�X�V_�`�[�쐬�� == null �̏ꍇ
     * this.�X�V_�`�[�쐬�� == null
     * �u�`�[�쐬�󋵂���͂��Ă��������B�v�̗�O���X���[����
     * BUSINESS_ERROR_02825"
     */
    public void testGetRequestCode_case010()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case010";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = "123";
            l_impl.updateVoucherMakeStatus = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02825, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�R�j �G���[���R�R�[�h�`�F�b�N
     * �R-�P�j this.�X�V_�G���[���R�R�[�h == null �̏ꍇ
     * this.�X�V_�G���[���R�R�[�h == null
     * �u�G���[���R�R�[�h����͂��Ă��������B�v�̗�O���X���[����
     * BUSINESS_ERROR_02826
     */
    public void testGetRequestCode_case011()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case011";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = "123";
            l_impl.accVoucherRecord[0].voucherMakeStatus = "123";
            l_impl.updateVoucherMakeStatus = "1";
            l_impl.updateErrorReasonCode = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02826, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "�R-�Q�j this.�X�V_�G���[���R�R�[�h�̒��� > 4 OR this.�X�V_�G���[���R�R�[�h�����p�p���ȊO�̏ꍇ
     * this.�X�V_�G���[���R�R�[�h�̒��� > 4 OR this.�X�V_�G���[���R�R�[�h�����p�p���ȊO
     * 1)
     * his.�X�V_�G���[���R�R�[�h�̒��� = 5
     * this.�X�V_�G���[���R�R�[�h = 23abc
     * 2)
     * this.�X�V_�G���[���R�R�[�h�̒��� = 4
     * this.�X�V_�G���[���R�R�[�h = �S�Sss
     * �u�G���[���R�R�[�h���s���ł��B�v�̗�O���X���[����
     * BUSINESS_ERROR_02827"
     */
    public void testGetRequestCode_case012()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case012";
        log.entering(STR_METHOD_NAME);

        l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
        l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
        l_impl.accVoucherRecord[0].institutionCode = "123";
        l_impl.accVoucherRecord[0].branchCode = "123";
        l_impl.accVoucherRecord[0].accountCode = "123";
        l_impl.accVoucherRecord[0].dataCode = "123";
        l_impl.accVoucherRecord[0].requestNumber = "123";
        l_impl.accVoucherRecord[0].voucherFlag = true;
        l_impl.accVoucherRecord[0].infoType = "123";
        l_impl.accVoucherRecord[0].voucherNumber = "123";
        l_impl.accVoucherRecord[0].voucherMakeStatus = "123";
        l_impl.updateVoucherMakeStatus = "1";

        for (int i = 0; i < 2; i++)
        {
            try
            {
                if (i == 0)
                {
                    l_impl.updateErrorReasonCode = "23abc";
                }
                else
                {
                    l_impl.updateErrorReasonCode = "�S�Sss";
                }
                this.l_impl.validate();
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02827, l_ex.getErrorInfo());
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * "
     * �S�j�@@�Ïؔԍ��`�F�b
     * �S-�P�j�@@this.�Ïؔԍ� == null�̏ꍇ
     * this.�Ïؔԍ� == null
     * �u�Ïؔԍ����s���ł��B�v�̗�O���X���[����BUSINESS_ERROR_02832
     */
    public void testGetRequestCode_case013()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case013";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = "123";
            l_impl.accVoucherRecord[0].voucherMakeStatus = "123";
            l_impl.updateVoucherMakeStatus = "1";
            l_impl.updateErrorReasonCode = "123";
            l_impl.password = null;
            this.l_impl.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02832, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * ���팋��
     */
    public void testGetRequestCode_case014()
    {
        final String STR_METHOD_NAME = "testGetRequestCode_case014";
        log.entering(STR_METHOD_NAME);

        try
        {
            l_impl.accVoucherRecord = new WEB3AdminDirSecAccVoucherRecordDetail[1];
            l_impl.accVoucherRecord[0] = new WEB3AdminDirSecAccVoucherRecordDetail();
            l_impl.accVoucherRecord[0].institutionCode = "123";
            l_impl.accVoucherRecord[0].branchCode = "123";
            l_impl.accVoucherRecord[0].accountCode = "123";
            l_impl.accVoucherRecord[0].dataCode = "123";
            l_impl.accVoucherRecord[0].requestNumber = "123";
            l_impl.accVoucherRecord[0].voucherFlag = true;
            l_impl.accVoucherRecord[0].infoType = "123";
            l_impl.accVoucherRecord[0].voucherNumber = "123";
            l_impl.accVoucherRecord[0].voucherMakeStatus = "123";
            l_impl.updateVoucherMakeStatus = "1";
            l_impl.updateErrorReasonCode = "123";
            l_impl.password = "12";
            this.l_impl.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
