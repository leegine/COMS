head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.43.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToCommonRequestTest.java;


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

public class WEB3AdminToCommonRequestTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToCommonRequestTest.class);
    WEB3AdminToCommonRequest l_request = null;
    public WEB3AdminToCommonRequestTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_request = new  WEB3AdminToCommonRequest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //    �P�j���X�R�[�h�`�F�b�N 
    //    �P�|�P�j�@@this.���X�R�[�h == null�̏ꍇ
    //    �u���X�R�[�h��null�v�̗�O���X���[����B 

    public void testValidate_C0001()
    {
        
     final String STR_METHOD_NAME = "testValidate_C0001()";
     log.entering(STR_METHOD_NAME);
     
     try
     {
         l_request.branchCode = null;
         l_request.validate();
         fail();
     }
     catch(WEB3BaseException l_ex)
     {
         assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174,l_ex.getErrorInfo());
     }   
     catch(Exception l_ex)
     {
         log.exiting(STR_METHOD_NAME);
         fail();
     }
     log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
     log.exiting(STR_METHOD_NAME);
    }

    //  �P�|�Q�jthis.���X�R�[�h.length == 0�̏ꍇ�A  
    //�@@�@@�@@�u���X�R�[�h�̗v�f����0�v�̗�O���X���[����B
  public void testValidate_C0002()
  {
      
   final String STR_METHOD_NAME = "testValidate_C0002()";
   log.entering(STR_METHOD_NAME);
   
   try
   {
       l_request.branchCode = null;
       l_request.validate();
       fail();
   }
   catch(WEB3BaseException l_ex)
   {
       assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174,l_ex.getErrorInfo());
   }   
   catch(Exception l_ex)
   {
       log.exiting(STR_METHOD_NAME);
       fail();
   }
   log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
   log.exiting(STR_METHOD_NAME);
  }

  //�P�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B
  //�@@�@@�@@�P�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
  //�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
  //�@@�@@�@@�@@�@@�E���X�R�[�h������
  //�@@�@@�@@�@@�@@�E���X�R�[�h.length��3
  //normalcase
  public void testValidate_C0003()
  {
      final String STR_METHOD_NAME = "testValidate_C0003()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          l_request.branchCode =new String[]{"111", "111","111"};
          l_request.validate();
      }
      catch(Exception l_ex)
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }
      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
      log.exiting(STR_METHOD_NAME);
  }

  //�P�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B
  //�@@�@@�@@�P�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
  //�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
  //�@@�@@�@@�@@�@@�E���X�R�[�h.length��3
  //�����f�s�ޑ����������������������C�e�o�ُ�
  //BUSINESS_ERROR_00779
  public void testValidate_C0004()
  {
      final String STR_METHOD_NAME = "testValidate_C0004()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          l_request.branchCode =new String[]{"11", "111","111"};
          l_request.validate();
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
      }
      catch(Exception l_ex)
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }
      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
      log.exiting(STR_METHOD_NAME);
  }

  //�P�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B
  //�@@�@@�@@�P�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
  //�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
  //�@@�@@�@@�@@�@@�E���X�R�[�h������
  //�����f�s�ޑ����������������������C�e�o�ُ�
  //BUSINESS_ERROR_00779
  public void testValidate_C0005()
  {
      final String STR_METHOD_NAME = "testValidate_C0005()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          l_request.branchCode =new String[]{"aaa", "111","111"};
          l_request.validate();
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
      }
      catch(Exception l_ex)
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }
      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
      log.exiting(STR_METHOD_NAME);
  }
//�P�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B
  //�@@�@@�@@�P�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
  //�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
  //�@@�@@�@@�@@�@@�E���X�R�[�h������
  //��2���f�s�ޑ����������������������C�e�o�ُ�
  //BUSINESS_ERROR_00779
  public void testValidate_C0006()
  {
      final String STR_METHOD_NAME = "testValidate_C0006()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          l_request.branchCode =new String[]{"111", "aaa","111"};
          l_request.validate();
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
      }
      catch(Exception l_ex)
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }
      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
      log.exiting(STR_METHOD_NAME);
  }

  //�P�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B
  //�@@�@@�@@�P�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
  //�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
  //�@@�@@�@@�@@�@@�E���X�R�[�h.length��3
  //�����f�s�ޑ����������������������C�e�o�ُ�
  //BUSINESS_ERROR_00779
  public void testValidate_C0007()
  {
      final String STR_METHOD_NAME = "testValidate_C0007()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          l_request.branchCode =new String[]{"111", "11","111"};
          l_request.validate();
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
      }
      catch(Exception l_ex)
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }
      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
      log.exiting(STR_METHOD_NAME);
  }

  
  //�P�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B
  //�@@�@@�@@�P�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
  //�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
  //�@@�@@�@@�@@�@@�E���X�R�[�h������
  //��3���f�s�ޑ����������������������C�e�o�ُ�
  //BUSINESS_ERROR_00779
  public void testValidate_C0008()
  {
      final String STR_METHOD_NAME = "testValidate_C0008()";
      log.entering(STR_METHOD_NAME);

      try
      {
          l_request.branchCode =new String[]{"111", "111","aaa"};
          l_request.validate();
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
      }
      catch(Exception l_ex)
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }
      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
      log.exiting(STR_METHOD_NAME);
  }

  //�P�|�R�jthis.���X�R�[�h�̗v�f�����ȉ��̏������s���B
  //�@@�@@�@@�P�|�R�|�P�jthis.���X�R�[�h���ȉ��̏����ɊY������ꍇ�A
  //�@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B
  //�@@�@@�@@�@@�@@�E���X�R�[�h.length��3
  //��O���f�s�ޑ����������������������C�e�o�ُ�
  //BUSINESS_ERROR_00779
  public void testValidate_C0009()
  {
      final String STR_METHOD_NAME = "testValidate_C0009()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          l_request.branchCode =new String[]{"111", "111","11"};
          l_request.validate();
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00779, l_ex.getErrorInfo());
      }
      catch(Exception l_ex)
      {
          log.exiting(STR_METHOD_NAME);
          fail();
      }
      log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>test pass");
      log.exiting(STR_METHOD_NAME);
  }
  
}
@
