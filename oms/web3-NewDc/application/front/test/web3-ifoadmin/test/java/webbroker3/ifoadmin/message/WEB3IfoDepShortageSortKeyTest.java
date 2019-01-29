head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.14.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepShortageSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������(WEB3IfoDepShortageSortKeyTest.java.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/09 �����F (���u) �V�K�쐬 ���f��No.000
*/
package webbroker3.ifoadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepShortageSortKeyTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepShortageSortKeyTest.class);

    public WEB3IfoDepShortageSortKeyTest(String arg0)
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
     * Test method for 'webbroker3.ifoadmin.message.WEB3IfoDepShortageSortKey.validate()'
     */
    //�P�jthis.�L�[���� == null�̏ꍇ�A 
    //�@@�@@�u�\�[�g�L�[.�L�[���ڂ�null�v�̗�O���X���[����B
    public void testValidateCase1()
    {
        String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();

            l_sortKey.validate();
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

    //�Q�j this.�L�[���ڂɉ��L�̍��ڈȊO�� 
    //�@@�ݒ肳��Ă�����A 
    //�@@�u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B 
    //�@@�@@�E"���X�R�[�h" 
    //�@@�@@�E"�ڋq�R�[�h" 
    //�@@�@@�E"�����z" 
    //�@@�@@�E"���ݖ������z" 
    //�@@�@@�E"���ݏ؋������v�z" 
    //�@@�@@�E"���ʗL���t���O" 
    //�@@�@@�E"�����L���t���O"
    public void testValidateCase2()
    {
        String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.keyItem = "branchId";

            l_sortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("86" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�R�jthis.�����^�~����null�̏ꍇ�A 
    //�@@�@@�u�\�[�g�L�[.�����^�~����null�v�̗�O���X���[����B 
    public void testValidateCase3()
    {
        String STR_METHOD_NAME = "testValidateCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.keyItem = "branchCode";

            l_sortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("87" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�����^�~�������w��ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //�S�jthis.�����^�~�������L�̍��ڈȊO�̏ꍇ�A 
    //�@@�@@�u�\�[�g�L�[.�����^�~��������`�̒l�v�̗�O���X���[����B 
    //�@@�@@�@@�E�hA�F�����h 
    //�@@�@@�@@�E�hD�F�~���h  
    public void testValidateCase4()
    {
        String STR_METHOD_NAME = "testValidateCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "S";
            l_sortKey.keyItem = "branchCode";

            l_sortKey.validate();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals("88" , l_ex.getErrorInfo().getErrorCode());
            assertEquals("�����^�~�����hA�F�����h�A�hD�F�~���h�ȊO�̒l�ł��B" , l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //����ʉ�
    public void testValidateCase5()
    {
        String STR_METHOD_NAME = "testValidateCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "A";
            l_sortKey.keyItem = "accountCode";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //����ʉ�
    public void testValidateCase6()
    {
        String STR_METHOD_NAME = "testValidateCase6()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "A";
            l_sortKey.keyItem = "claimAmount";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //����ʉ�
    public void testValidateCase7()
    {
        String STR_METHOD_NAME = "testValidateCase7()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "curNonPayAmt";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //����ʉ�
    public void testValidateCase8()
    {
        String STR_METHOD_NAME = "testValidateCase8()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "curIfoDepositNecessaryAmt";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //����ʉ�
    public void testValidateCase9()
    {
        String STR_METHOD_NAME = "testValidateCase9()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "contractExistFlag";

            l_sortKey.validate();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //����ʉ�
    public void testValidateCase10()
    {
        String STR_METHOD_NAME = "testValidateCase10()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoDepShortageSortKey l_sortKey = new WEB3IfoDepShortageSortKey();
            l_sortKey.ascDesc = "D";
            l_sortKey.keyItem = "orderExistFlag";

            l_sortKey.validate();
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
