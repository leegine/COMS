head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.00.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecWorkingConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecWorkingConfirmRequestTest extends TestBaseForMock
{

    /**
     * ÉçÉOèoóÕÉÜÅ[ÉeÉBÉäÉeÉB
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecWorkingConfirmRequestTest.class);

    public WEB3AdminDirSecWorkingConfirmRequestTest(String arg0)
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

    public void testValidate_Case001()
    {
        final String STR_METHOD_NAME = "testValidate_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            l_request.batoPreferenceRecord = null;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03076, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_Case002()
    {
        final String STR_METHOD_NAME = "testValidate_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            l_request.batoPreferenceRecord = new WEB3AdminDirSecBatoPreferenceRecordDetail[0];
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03077, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_Case003()
    {
        final String STR_METHOD_NAME = "testValidate_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = null;
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case004()
    {
        final String STR_METHOD_NAME = "testValidate_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "jidÇQ";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case005()
    {
        final String STR_METHOD_NAME = "testValidate_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "ÇPÇQÇR";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case006()
    {
        final String STR_METHOD_NAME = "testValidate_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "1234";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case007()
    {
        final String STR_METHOD_NAME = "testValidate_Case007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "123";
            l_batoPreferenceRecord[0].systemTroubleDiv = null;
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case008()
    {
        final String STR_METHOD_NAME = "testValidate_Case008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "123";
            l_batoPreferenceRecord[0].systemTroubleDiv = "jid12";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03078, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case009()
    {
        final String STR_METHOD_NAME = "testValidate_Case009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[1];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "123";
            l_batoPreferenceRecord[0].systemTroubleDiv = "ÇP";
            
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03078, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case010()
    {
        final String STR_METHOD_NAME = "testValidate_Case010()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[3];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "123";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            
            l_batoPreferenceRecord[1] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[1].branchCode = "456";
            l_batoPreferenceRecord[1].systemTroubleDiv = "2";
            
            l_batoPreferenceRecord[2] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[2].branchCode = "123";
            l_batoPreferenceRecord[2].systemTroubleDiv = "12";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03079, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidate_Case011()
    {
        final String STR_METHOD_NAME = "testValidate_Case011()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminDirSecWorkingConfirmRequest l_request =
                new WEB3AdminDirSecWorkingConfirmRequest();
            WEB3AdminDirSecBatoPreferenceRecordDetail[] l_batoPreferenceRecord =
                new WEB3AdminDirSecBatoPreferenceRecordDetail[3];
            l_batoPreferenceRecord[0] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[0].branchCode = "123";
            l_batoPreferenceRecord[0].systemTroubleDiv = "1";
            
            l_batoPreferenceRecord[1] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[1].branchCode = "456";
            l_batoPreferenceRecord[1].systemTroubleDiv = "2";
            
            l_batoPreferenceRecord[2] = new WEB3AdminDirSecBatoPreferenceRecordDetail();
            l_batoPreferenceRecord[2].branchCode = "123";
            l_batoPreferenceRecord[2].systemTroubleDiv = "3";
            l_request.batoPreferenceRecord = l_batoPreferenceRecord;
            l_request.validate();
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
