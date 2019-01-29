head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoDepShortageReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������(WEB3AdminIfoDepShortageReferenceRequestTest.java.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/06 �����F (���u) �V�K�쐬 ���f��No.000
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoDepShortageReferenceRequestTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageReferenceRequestTest.class);

    public WEB3AdminIfoDepShortageReferenceRequestTest(String arg0)
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
     * Test method for 'WEB3AdminIfoDepShortageReferenceRequest.validate()'
     */
    // �P�j�@@�������t�`�F�b�N 
    // �@@�P�|�P�j�@@this.�������t== null�̏ꍇ�A 
    // �@@�@@�u�������t��null�v�̗�O���X���[����B
    // �@@�@@�@@�@@�@@class:�@@WEB3BusinessLayerException
    // �@@�@@�@@�@@�@@tag�@@:�@@BUSINESS_ERROR_03154
    public void testValidateCase1()
    {
        String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("3154" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�������t��null�B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // �Q�j�@@���X�R�[�h�`�F�b�N
    // �@@�Q�|�P�j�@@this.���X�R�[�h�ꗗ == null�̏ꍇ�A
    // �@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B 
    // �@@�@@�iBUSINESS_ERROR_01429�j
    public void testValidateCase2()
    {
        String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("1429" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("���X�R�[�h�ꗗ�����w��ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    // �@@�Q�|�Q�j�@@this.���X�R�[�h�ꗗ�̗v�f�����ȉ��̏������s���B
    // �@@�@@�Q�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
    // �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
    // �@@�@@�@@�@@�E���X�R�[�h != ����
    // �@@�@@�@@�@@�E���X�R�[�h.length != 3
    public void testValidateCase3()
    {
        String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "10a";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            l_request.branchCode = l_strBranchCodes;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("779" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("���X�R�[�h�̓��͂��s���ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // �@@�Q�|�Q�j�@@this.���X�R�[�h�ꗗ�̗v�f�����ȉ��̏������s���B
    // �@@�@@�Q�|�Q�|�P�j�@@this.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
    // �@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
    // �@@�@@�@@�@@�E���X�R�[�h != ����
    // �@@�@@�@@�@@�E���X�R�[�h.length != 3
    public void testValidateCase4()
    {
        String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "1021";
            l_strBranchCodes[2] = "103";
            l_request.branchCode = l_strBranchCodes;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("779" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("���X�R�[�h�̓��͂��s���ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�R�j�@@�ڋq�R�[�h�`�F�b�N 
    //�@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
    //�@@�R�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A 
    //�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B 
    //�@@�@@�@@�E�ڋq�R�[�h != ���� 
    //�@@�@@�@@�E�ڋq�R�[�h.length != 6 
    //�@@�@@�iBUSINESS_ERROR_00780�j 
    public void testValidateCase5()
    {
        String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "a12345";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("780" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�ڋq�R�[�h�̓��͂��s���ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�R�j�@@�ڋq�R�[�h�`�F�b�N 
    //�@@this.�ڋq�R�[�h != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B 
    //�@@�R�|�P�j�@@this.�ڋq�R�[�h���ȉ��̏����ɊY������ꍇ�A 
    //�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B 
    //�@@�@@�@@�E�ڋq�R�[�h != ���� 
    //�@@�@@�@@�E�ڋq�R�[�h.length != 6 
    //�@@�@@�iBUSINESS_ERROR_00780�j 
    public void testValidateCase6()
    {
        String STR_METHOD_NAME = "testValidateCase6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "1234567";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("780" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�ڋq�R�[�h�̓��͂��s���ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�S�j�@@�������q�敪�`�F�b�N 
    //�S�|�P�j�@@this.�������q�敪== null�̏ꍇ�A 
    //�@@�@@�u�������q�敪��null�v�̗�O���X���[����B
    //this.�ڋq�R�[�h == null
    public void testValidateCase7()
    {
        String STR_METHOD_NAME = "testValidateCase7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.uncancelDiv = null;
//            l_request.accountCode = "1234567";
            
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("3155" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�������q�敪��null�B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�@@�S�|�Q�j�@@this.�������q�敪�ɉ��L�̍��ڈȊO���ݒ肳��Ă�����A 
    //�@@�@@�u�������q�敪������`�̒l�v�̗�O���X���[����B 
    //�@@�@@�@@�E"0�F�������q " 
    //�@@�@@�@@�E"1�F�s�������S�ڋq " 
    public void testValidateCase8()
    {
        String STR_METHOD_NAME = "testValidateCase8()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "2";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("3156" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�������q�敪������`�̒l�B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�T�j�@@�\�[�g�L�[�`�F�b�N 
    //�@@�T�|�P�j�@@this.�\�[�g�L�[�������͂̏ꍇ�A 
    //�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B 
    //�@@�@@�iBUSINESS_ERROR_00231�j 
    public void testValidateCase9()
    {
        String STR_METHOD_NAME = "testValidateCase9()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("231" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�\�[�g�L�[�����w��ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�@@�T�|�Q�j�@@this.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B 
    //�@@�@@�T�|�Q�|�P�j�@@�\�[�g�L�[.validate()���R�[������B 
    public void testValidateCase10()
    {
        String STR_METHOD_NAME = "testValidateCase10()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[3];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("85" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�\�[�g�L�[�̃L�[���ڂ����w��ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�U�j�@@�v���y�[�W�ԍ��`�F�b�N 
    //�@@�U�|�P�j�@@this.�v���y�[�W�ԍ� == null�ł������ꍇ�A 
    //�@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B 
    //�@@�@@�@@�iBUSINESS_ERROR_00089�j 
    public void testValidateCase11()
    {
        String STR_METHOD_NAME = "testValidateCase11()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("89" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�v���y�[�W�ԍ������w��ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�@@�U�|�Q�j�@@this.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A 
    //�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B 
    //�@@�@@�iBUSINESS_ERROR_00090�j 
    public void testValidateCase12()
    {
        String STR_METHOD_NAME = "testValidateCase12()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1.1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("90" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�v���y�[�W�ԍ��������ȊO�̒l�ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    //�@@�U�|�R�j�@@this.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A 
    //�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
    //�@@�iBUSINESS_ERROR_00616�j
    public void testValidateCase13()
    {
        String STR_METHOD_NAME = "testValidateCase13()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "-1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("616" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�v���y�[�W�ԍ��̒l��0�ȉ��ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�V�j�@@�y�[�W���\���s���`�F�b�N 
    //�@@�V�|�P�j�@@this.�y�[�W���\���s�� == null�ł������ꍇ�A 
    //�@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B 
    //�@@�@@�@@�iBUSINESS_ERROR_00091�j 
    public void testValidateCase14()
    {
        String STR_METHOD_NAME = "testValidateCase14()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("91" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�y�[�W���\���s���̓��͂��s���ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�@@�V�|�Q�j�@@this.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A 
    //�@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B 
    //�@@ �@@�@@�iBUSINESS_ERROR_00092�j 
    public void testValidateCase15()
    {
        String STR_METHOD_NAME = "testValidateCase15()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "a";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("92" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�y�[�W���\���s���������ȊO�̒l�ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�@@�V�|�R�j�@@this.�y�[�W���\���s�� <= 0�ł������ꍇ�A 
    //�@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B 
    //�@@�@@�@@�@@�iBUSINESS_ERROR_00617�j 
    public void testValidateCase16()
    {
        String STR_METHOD_NAME = "testValidateCase16()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "-1";
            l_request.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("617" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�y�[�W���\���s���̒l��0�ȉ��ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //����ʉ�
    public void testValidateCase17()
    {
        String STR_METHOD_NAME = "testValidateCase17()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
        
        try
        {
            l_request.searchDate = WEB3DateUtility.getDate("20090101", "yyyyMMdd");
            String[] l_strBranchCodes = new String[3];
            l_strBranchCodes[0] = "101";
            l_strBranchCodes[1] = "102";
            l_strBranchCodes[2] = "103";
            
            WEB3IfoDepShortageSortKey[] l_sortKeys = new WEB3IfoDepShortageSortKey[1];
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "branchCode";
            l_sortKeys[0] = l_sortKey;
            
            l_request.branchCode = l_strBranchCodes;
            l_request.accountCode = "123456";
            l_request.uncancelDiv = "1";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
}
@
