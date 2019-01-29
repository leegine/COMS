head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiServiceMasterTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  (WEB3SrvRegiServiceMasterTest)
Author Name      : Daiwa Institute of Research
Revision History : 2009/04/28 �Ԑi (���u) �V�K�쐬 ���f��407
*/
package webbroker3.srvregi;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import test.util.TestDBUtility;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvRegiKeyParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiServiceMasterTest extends TestBaseForMock{

	   /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceMasterTest.class);
    
    public WEB3SrvRegiServiceMasterTest(String arg0)
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

    /**
     * �yWEB3�z�y�T�[�r�X���p�z�P�̃e�X�g�d�l���񍐏�NO.489
     * �T�[�r�X���p�L�[
     * �f�[�^���Ȃ��̏ꍇ
     * �߂�l��null
     */
    public void testgetAdditionDiv_001()
    {
        final String STR_METHOD_NAME = "testgetAdditionDiv_001()";
        log.entering(STR_METHOD_NAME);
        SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
        l_srvRegiMasRow.setInstitutionCode("0D");
        l_srvRegiMasRow.setSrvDiv("1");
        WEB3SrvRegiServiceMaster l_srvRegiSerMaster = null;
        try
        {
        	l_srvRegiSerMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            String l_strAdditionDiv = l_srvRegiSerMaster.getAdditionDiv();

            assertNull(l_strAdditionDiv);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �yWEB3�z�y�T�[�r�X���p�z�P�̃e�X�g�d�l���񍐏�NO.490
     * �T�[�r�X���p�L�[
     * �f�[�^��������
     * ����������Ȃ��̏ꍇ
     * �^�s�f�[�^
     * �،���ЃR�[�h�I= this.�،���ЃR�[�h and 
     * �T�[�r�X�敪=this.�T�[�r�X�敪 and 
     * ���p�L�[��ʋ敪="�t���敪" and 
     * �T�[�r�X���p�L�[ID=�P�i�Œ�l�j 
     * �߂�l��null
     */
    public void testgetAdditionDiv_002()
    {
        final String STR_METHOD_NAME = "testgetAdditionDiv_002()";
        log.entering(STR_METHOD_NAME);
        SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
        l_srvRegiMasRow.setInstitutionCode("0D");
        l_srvRegiMasRow.setSrvDiv("1");

        WEB3SrvRegiServiceMaster l_srvRegiSerMaster = null;
        try
        {
        	l_srvRegiSerMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("1D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(1l);
            TestDBUtility.insertWithDel(l_srvRegimasterparams);

            String l_strAdditionDiv = l_srvRegiSerMaster.getAdditionDiv();

            assertNull(l_strAdditionDiv);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    
    /**
     * �yWEB3�z�y�T�[�r�X���p�z�P�̃e�X�g�d�l���񍐏�NO.491
     * �T�[�r�X���p�L�[
     * �f�[�^��������
     * ����������Ȃ��̏ꍇ
     * �^�s�f�[�^
     * �،���ЃR�[�h== this.�،���ЃR�[�h and 
     * �T�[�r�X�敪 !=this.�T�[�r�X�敪 and 
     * ���p�L�[��ʋ敪="�t���敪" and 
     * �T�[�r�X���p�L�[ID=�P�i�Œ�l�j 
     * �߂�l��null
     */
    public void testgetAdditionDiv_003()
    {
        final String STR_METHOD_NAME = "testgetAdditionDiv_003()";
        log.entering(STR_METHOD_NAME);
        SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
        l_srvRegiMasRow.setInstitutionCode("0D");
        l_srvRegiMasRow.setSrvDiv("1");

        WEB3SrvRegiServiceMaster l_srvRegiSerMaster = null;
        try
        {
        	l_srvRegiSerMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("2");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(1l);
            TestDBUtility.insertWithDel(l_srvRegimasterparams);

            String l_strAdditionDiv = l_srvRegiSerMaster.getAdditionDiv();

            assertNull(l_strAdditionDiv);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �yWEB3�z�y�T�[�r�X���p�z�P�̃e�X�g�d�l���񍐏�NO.492
     * �T�[�r�X���p�L�[
     * �f�[�^��������
     * ����������Ȃ��̏ꍇ
     * �^�s�f�[�^
     * �،���ЃR�[�h== this.�،���ЃR�[�h and 
     * �T�[�r�X�敪 ==this.�T�[�r�X�敪 and 
     * ���p�L�[��ʋ敪!="�t���敪" and 
     * �T�[�r�X���p�L�[ID=�P�i�Œ�l�j 
     * �߂�l��null
     */
    public void testgetAdditionDiv_004()
    {
        final String STR_METHOD_NAME = "testgetAdditionDiv_004()";
        log.entering(STR_METHOD_NAME);
        SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
        l_srvRegiMasRow.setInstitutionCode("0D");
        l_srvRegiMasRow.setSrvDiv("1");

        WEB3SrvRegiServiceMaster l_srvRegiSerMaster = null;
        try
        {
        	l_srvRegiSerMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("7");
            l_srvRegimasterparams.setSrvUseId(1l);
            TestDBUtility.insertWithDel(l_srvRegimasterparams);

            String l_strAdditionDiv = l_srvRegiSerMaster.getAdditionDiv();

            assertNull(l_strAdditionDiv);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �yWEB3�z�y�T�[�r�X���p�z�P�̃e�X�g�d�l���񍐏�NO.493
     * �T�[�r�X���p�L�[
     * �f�[�^��������
     * ����������Ȃ��̏ꍇ
     * �^�s�f�[�^
     * �،���ЃR�[�h== this.�،���ЃR�[�h and 
     * �T�[�r�X�敪 ==this.�T�[�r�X�敪 and 
     * ���p�L�[��ʋ敪=="�t���敪" and 
     * �T�[�r�X���p�L�[ID!=�P�i�Œ�l�j 
     * �߂�l��null
     */
    public void testgetAdditionDiv_005()
    {
        final String STR_METHOD_NAME = "testgetAdditionDiv_005()";
        log.entering(STR_METHOD_NAME);
        SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
        l_srvRegiMasRow.setInstitutionCode("0D");
        l_srvRegiMasRow.setSrvDiv("1");

        WEB3SrvRegiServiceMaster l_srvRegiSerMaster = null;
        try
        {
        	l_srvRegiSerMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(2l);
            TestDBUtility.insertWithDel(l_srvRegimasterparams);

            String l_strAdditionDiv = l_srvRegiSerMaster.getAdditionDiv();

            assertNull(l_strAdditionDiv);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �yWEB3�z�y�T�[�r�X���p�z�P�̃e�X�g�d�l���񍐏�NO.494
     * �T�[�r�X���p�L�[
     * �f�[�^��������
     * ����������̏ꍇ
     * �^�s�f�[�^
     * �،���ЃR�[�h== this.�،���ЃR�[�h and 
     * �T�[�r�X�敪 ==this.�T�[�r�X�敪 and 
     * ���p�L�[��ʋ敪=="�t���敪" and 
     * �T�[�r�X���p�L�[ID==�P�i�Œ�l�j 
     * �擾�����T�[�r�X���p�L�[Params.get�T�[�r�X���p�L�[()�� 
     * �@@�߂�l��ԋp����B 

     */
    public void testgetAdditionDiv_006()
    {
        final String STR_METHOD_NAME = "testgetAdditionDiv_006()";
        log.entering(STR_METHOD_NAME);
        SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
        l_srvRegiMasRow.setInstitutionCode("0D");
        l_srvRegiMasRow.setSrvDiv("1");

        WEB3SrvRegiServiceMaster l_srvRegiSerMaster = null;
        try
        {
        	l_srvRegiSerMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(1l);
            l_srvRegimasterparams.setSrvUseKey("UserKey");
            TestDBUtility.insertWithDel(l_srvRegimasterparams);

            String l_strAdditionDiv = l_srvRegiSerMaster.getAdditionDiv();

            assertEquals("UserKey",l_strAdditionDiv);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testgetAdditionDiv2_001()
    {
        final String STR_METHOD_NAME = "testgetAdditionDiv2_001()";
        log.entering(STR_METHOD_NAME);
        SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
        l_srvRegiMasRow.setInstitutionCode("0D");
        l_srvRegiMasRow.setSrvDiv("1");
        WEB3SrvRegiServiceMaster l_srvRegiSerMaster = null;
        try
        {
            l_srvRegiSerMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            String l_strAdditionDiv = l_srvRegiSerMaster.getAdditionDiv(2);

            assertNull(l_strAdditionDiv);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testgetAdditionDiv2_002()
    {
        final String STR_METHOD_NAME = "testgetAdditionDiv2_002()";
        log.entering(STR_METHOD_NAME);
        SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
        l_srvRegiMasRow.setInstitutionCode("0D");
        l_srvRegiMasRow.setSrvDiv("1");

        WEB3SrvRegiServiceMaster l_srvRegiSerMaster = null;
        try
        {
            l_srvRegiSerMaster = new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            TestDBUtility.deleteAll(SrvRegiKeyParams.TYPE);
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("8");
            l_srvRegimasterparams.setSrvUseId(2);
            l_srvRegimasterparams.setSrvUseKey("jiddk");
            TestDBUtility.insertWithDel(l_srvRegimasterparams);

            String l_strAdditionDiv = l_srvRegiSerMaster.getAdditionDiv(2);

            assertEquals("jiddk", l_strAdditionDiv);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetHashList_Case001()
    {
        final String STR_METHOD_NAME = "testGetHashList_Case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(SrvRegiKeyParams.TYPE);
            SrvRegiMasterParams l_srvRegiMasRow = new SrvRegiMasterParams();
            l_srvRegiMasRow.setInstitutionCode("0D");
            l_srvRegiMasRow.setSrvDiv("1");
            WEB3SrvRegiServiceMaster l_srvRegiSerMaster =
                new WEB3SrvRegiServiceMaster(l_srvRegiMasRow);
            
            SrvRegiKeyParams l_srvRegimasterparams = TestDBUtility.getSrvRegiKeyRow();
            l_srvRegimasterparams.setInstitutionCode("0D");
            l_srvRegimasterparams.setSrvDiv("1");
            l_srvRegimasterparams.setSrvUseKeyType("1");
            l_srvRegimasterparams.setSrvUseId(2);
            l_srvRegimasterparams.setSrvUseKey("jiddk2");
            //111
            l_queryProcessor.doInsertQuery(l_srvRegimasterparams);
            
            //222
            l_srvRegimasterparams.setSrvUseId(1);
            l_srvRegimasterparams.setSrvUseKey("jiddk1");
            l_queryProcessor.doInsertQuery(l_srvRegimasterparams);
            
            //333
            l_srvRegimasterparams.setSrvUseId(3);
            l_srvRegimasterparams.setSrvUseKey("jiddk3");
            l_queryProcessor.doInsertQuery(l_srvRegimasterparams);
            
            WEB3SrvRegiServiceUseKey[] l_serviceUseKey =
                l_srvRegiSerMaster.getHashList();
            
            assertEquals("jiddk1", l_serviceUseKey[0].getSrvUseKey());
            assertEquals("jiddk2", l_serviceUseKey[1].getSrvUseKey());
            assertEquals("jiddk3", l_serviceUseKey[2].getSrvUseKey());
            
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
