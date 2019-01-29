head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.25.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionsOpenMarginInputRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K���������͉�ʃ��N�G�X�g(WEB3OptionsOpenMarginInputRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/27 ���^�](���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionsOpenMarginInputRequestTest extends TestBaseForMock
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
        .getInstance(WEB3OptionsOpenMarginInputRequestTest.class);

    public WEB3OptionsOpenMarginInputRequestTest(String arg0)
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
     * �P�j�@@���敪�`�F�b�N
     * �P�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B
     */
    public void testValidate_0001()
    {
        String STR_METHOD_NAME = ".testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        l_request.contractType = null;
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00263, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �P�j�@@���敪�`�F�b�N
     * �P�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B
     */
    public void testValidate_0002()
    {
        String STR_METHOD_NAME = ".testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        l_request.contractType = "1";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �P�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B�@@�@@
     * �E�h1�F�����h
     * �E�h2�F�����h
     */
    public void testValidate_0003()
    {
        String STR_METHOD_NAME = ".testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        l_request.contractType = "3";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00264, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �P�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B�@@�@@
     * �E�h1�F�����h
     * �E�h2�F�����h
     */
    public void testValidate_0004()
    {
        String STR_METHOD_NAME = ".testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        l_request.contractType = "2";
        
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �Q�j�����ݒ�`�F�b�N
     * �@@�Q�|�P�j�S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B
     * �@@�@@�@@�E�����R�[�h
     * �@@�@@�@@�E����s��
     * �@@�@@�@@�E�w�����
     * �@@�@@�@@�E����
     * �@@�@@�@@�E�I�v�V�������i�敪
     * �@@�@@�@@�E�s�g���i
     */
    public void testValidate_0005()
    {
        String STR_METHOD_NAME = ".testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        
        l_request.opProductCode = "1";               
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �Q�j�����ݒ�`�F�b�N
     * �@@�Q�|�P�j�S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B
     * �@@�@@�@@�E�����R�[�h
     * �@@�@@�@@�E����s��
     * �@@�@@�@@�E�w�����
     * �@@�@@�@@�E����
     * �@@�@@�@@�E�I�v�V�������i�敪
     * �@@�@@�@@�E�s�g���i
     */
    public void testValidate_0006()
    {
        String STR_METHOD_NAME = ".testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        
        
        l_request.opProductCode = null;               
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �Q�|�Q�j�����I������
     * ����s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�� 
     * �����ꂩ�̍��ڂ��ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B
     */
    public void testValidate_0007()
    {
        String STR_METHOD_NAME = ".testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= null;               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �Q�|�Q�j�����I������
     * ����s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�� 
     * �����ꂩ�̍��ڂ��ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B
     */
    public void testValidate_0008()
    {
        String STR_METHOD_NAME = ".testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= "1";               
        l_request.targetProductCode= null;                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �Q�|�Q�j�����I������
     * ����s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�� 
     * �����ꂩ�̍��ڂ��ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B
     */
    public void testValidate_0009()
    {
        String STR_METHOD_NAME = ".testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= null;                
        l_request.opProductType= "1";                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �Q�|�Q�j�����I������
     * ����s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�� 
     * �����ꂩ�̍��ڂ��ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B
     */
    public void testValidate_0010()
    {
        String STR_METHOD_NAME = ".testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= null;                
        l_request.strikePrice= "1";
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �Q�|�Q�j�����I������
     * ����s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�� 
     * �����ꂩ�̍��ڂ��ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B
     */
    public void testValidate_0011()
    {
        String STR_METHOD_NAME = ".testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= "1";               
        l_request.targetProductCode= "1";                
        l_request.delivaryMonth= "1";                
        l_request.opProductType= "1";                
        l_request.strikePrice= null;
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00830, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
    
    /*
     * �Q�|�Q�j�����I������
     * ����s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�� 
     * �����ꂩ�̍��ڂ��ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B
     */
    public void testValidate_0012()
    {
        String STR_METHOD_NAME = ".testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
                       
        l_request.marketCode= null;               
        l_request.targetProductCode= null;                
        l_request.delivaryMonth= null;                
        l_request.opProductType= null;                
        l_request.strikePrice= null;
        l_request.contractType ="1";
        try
        {
            l_request.validate();

            log.exiting(TEST_END + STR_METHOD_NAME);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
    }
}
@
