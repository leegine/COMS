head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIpoLotResultUploadCsvTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ipo;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIpoLotResultUploadCsvTest extends TestBaseForMock
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadCsvTest.class);
    
    public WEB3AdminIpoLotResultUploadCsvTest(String arg0)
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
     * Test method for 'webbroker3.ipo.WEB3AdminIpoLotResultUploadCsv.validateDuplicateAccount(int)'
     */
    public void testValidateDuplicateAccount_T01()
    {
        final String STR_METHOD_NAME = "validateDuplicateAccountTest_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminIpoLotResultUploadCsvForMock l_testClass =
                new WEB3AdminIpoLotResultUploadCsvForMock(123456L);
            l_testClass.validateDuplicateAccount(2);
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testVateDuplicateAccount_T02()
    {
        final String STR_METHOD_NAME = "validateDuplicateAccountTest_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminIpoLotResultUploadCsvForMock l_testClass =
                new WEB3AdminIpoLotResultUploadCsvForMock(123456L);
            l_testClass.validateDuplicateAccount(3);
            fail();
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_exc.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00517);
            log.info(STR_METHOD_NAME + "----------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set�ڋq
     */
    public void test_setAccount_0001()
    {
        final String STR_METHOD_NAME = "test_setAccount_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIpoLotResultUploadCsv l_adminIpoLotResultUploadCsv = new WEB3AdminIpoLotResultUploadCsv(1);
        try
        {
            l_adminIpoLotResultUploadCsv.setAccount(11,1012L);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00398,e.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3AdminIpoLotResultUploadCsvForMock extends WEB3AdminIpoLotResultUploadCsv
    {

        public WEB3AdminIpoLotResultUploadCsvForMock(long l_lngUploadId)
        {
            super(l_lngUploadId);
            // TODO Auto-generated constructor stub
        }
        
        /**
         * (get�ڋq�R�[�h)<BR>
         * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B<BR>
         * <BR>
         * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B<BR>
         * <BR>
         * [get���ڒl()�Ɏw�肷�����]<BR>
         * �s�ԍ��F�@@�s�ԍ�<BR>
         * �J�����F�@@get�J�������f��(���I���ʃA�b�v���[�hCSV.�ڋq�R�[�h���x��)�̖߂�l�B
         * @@param l_intLineNo - �s�ԍ�
         * @@return String
         * @@roseuid 40F505C90393
         */
        public String getAccountCode(int l_intLineNo) 
        {

            final String STR_METHOD_NAME = " getAccountCode(int)";
            log.entering(STR_METHOD_NAME);
            String l_strValue=null;

            try
            {
                if(l_intLineNo==0)
                {
                    l_strValue="123";
                }
                
                if(l_intLineNo==1)
                {
                    l_strValue="456";
                }
                
                if(l_intLineNo==2)
                {
                    l_strValue="789";
                }
                
                if(l_intLineNo==3)
                {
                    l_strValue="456";
                }
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
            }

            log.exiting(STR_METHOD_NAME);
            return l_strValue;

        }
        
        /**
         * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B<BR>
         * <BR>
         * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B<BR>
         * <BR>
         * [get���ڒl()�Ɏw�肷�����]<BR>
         * �s�ԍ��F�@@�s�ԍ�<BR>
         * �J�����F�@@get�J�������f��(���I���ʃA�b�v���[�hCSV.���X�R�[�h���x��)�̖߂�l�B<BR>
         * @@param l_intLineNo - �s�ԍ�
         * @@return String
         * @@roseuid 40F4FC2601FD
         */
        public String getBranchCode(int l_intLineNo) 
        {
            final String STR_METHOD_NAME = " getAccountCode(int)";
            log.entering(STR_METHOD_NAME);
            String l_strValue=null;

            try
            {
                if(l_intLineNo==0)
                {
                    l_strValue="123";
                }
                
                if(l_intLineNo==1)
                {
                    l_strValue="456";
                }
                
                if(l_intLineNo==2)
                {
                    l_strValue="789";
                }
                
                if(l_intLineNo==3)
                {
                    l_strValue="456";
                }
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
            }

            log.exiting(STR_METHOD_NAME);
            return l_strValue;
        }
        
        /**
         * (validate�d���ڋq)<BR>
         * �d���ڋq���ǉ�����Ă��Ȃ����`�F�b�N���s���B<BR>
         * <BR>
         * get�ڋq�R�[�h(�ԍ�)<BR>
         * get���X�R�[�h(�ԍ�)<BR>
         * �ɂāA�w��s�ԍ��̌ڋq�R�[�h���擾����B<BR>
         * <BR>
         * �擾�������X�R�[�h+�ڋq�R�[�h�Ǝw��s�ԍ����<BR>
         * �O�̖��׍s�̕��X�R�[�h+�ڋq�R�[�h���r���A<BR>
         * �����l�����݂���ꍇ�́A��O���X���[����B<BR>
         * <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_00517<BR>
         * @@param l_intLineNo - �s�ԍ�
         * @@roseuid 40F512FA0374
         */
        public void validateDuplicateAccount(int l_intLineNo) throws WEB3BaseException
        {
            
            final String STR_METHOD_NAME = " validateRepeatAccount(int)";
            log.entering(STR_METHOD_NAME);
            
            //get�ڋq�R�[�h(�ԍ�)
            //get���X�R�[�h(�ԍ�)
            //�ɂāA�w��s�ԍ��̌ڋq�R�[�h���擾����
            String l_strAccountCode = this.getAccountCode(l_intLineNo);
            String l_strBranchCode = this.getBranchCode(l_intLineNo);
            String l_strCompareCode = l_strBranchCode + l_strAccountCode;
            for(int i = 0; i < l_intLineNo; i++)
            {
                
                String l_strAccCode = getAccountCode(i);
                String l_strBraCode = getBranchCode(i);
                String l_strComCode = l_strBraCode + l_strAccCode;
                if(l_strCompareCode.equals(l_strComCode))
                {
                    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                        getClass().getName() + STR_METHOD_NAME);
                    
                }
                
            }
            
            log.exiting(STR_METHOD_NAME);
            
        }
        
    }
}
@
