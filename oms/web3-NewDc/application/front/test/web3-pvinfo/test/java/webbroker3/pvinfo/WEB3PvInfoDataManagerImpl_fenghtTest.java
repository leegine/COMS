head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3PvInfoDataManagerImpl_fenghtTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo;

import java.util.List;

import junit.extensions.TestDecorator;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3PvInfoDataManagerImpl_fenghtTest extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PvInfoDataManagerImpl_fenghtTest.class);

    
    public WEB3PvInfoDataManagerImpl_fenghtTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3PvInfoDataManagerImpl l_impl = new WEB3PvInfoDataManagerImpl();
    
    /*
     * Test method for 'webbroker3.pvinfo.WEB3PvInfoDataManagerImpl.isDeliveryDate(WEB3GentradeMainAccount)'
     */
    public void testIsDeliveryDate_T001() 
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_T001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1234567");
            l_docDeliveryManagementParams.setDocumentCategory("111");
            l_docDeliveryManagementParams.setDeliveryDate(
                  WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20070331", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            boolean l_bon = l_impl.isDeliveryDate(l_mainAccount);
            
            assertEquals(true,l_bon);
            
        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        } 
    }

    public void testIsDeliveryDate_T002() 
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_T002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            //BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
//            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
//            l_batoProductManagementParams.setDocumentDiv("010");
//            l_batoProductManagementParams.setBatoProductCode("1234567");
//            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
//            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
//            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
//            l_docDeliveryManagementParams.setDocumentDiv("010");
//            l_docDeliveryManagementParams.setProductCode("1234567");
//            l_docDeliveryManagementParams.setDocumentCategory("111");
//            l_docDeliveryManagementParams.setDeliveryDate(
//                  WEB3DateUtility.getDate("20070206", "yyyyMMdd"));
//            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20070306", "yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            boolean l_bon = l_impl.isDeliveryDate(l_mainAccount);
            
            assertEquals(false,l_bon);
            
        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        } 
    }
    
    public void testIsDeliveryDate_T003() 
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_T003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234568");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234569");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_batoProductManagementParams.setBatoProductCode("1234570");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1234569");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070206", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20071206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070206", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20070306", "yyyyMMdd"));
            l_docDeliveryManagementParams.setProductCode("1234570");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            boolean l_bon = l_impl.isDeliveryDate(l_mainAccount);
            
            assertEquals(true,l_bon);
            
        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        } 
    }
    
    public void testIsDeliveryDate_T004() 
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_T004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234568");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234569");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_batoProductManagementParams.setBatoProductCode("1234570");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1234569");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070206", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20071206", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070206", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20071106", "yyyyMMdd"));
            l_docDeliveryManagementParams.setProductCode("1234570");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
  
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            boolean l_bon = l_impl.isDeliveryDate(l_mainAccount);
            
            assertEquals(true,l_bon);
            
        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        } 
    }
    
    public void testIsDeliveryDate_T005() 
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_T005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1234567");
            l_docDeliveryManagementParams.setDocumentCategory("111");
            l_docDeliveryManagementParams.setDeliveryDate(
                  WEB3DateUtility.getDate("20070206", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(null);
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            boolean l_bon = l_impl.isDeliveryDate(l_mainAccount);
            
            assertEquals(true,l_bon);
            
        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        } 
    }
    
    public void testIsDeliveryDate_T006() 
    {
        final String STR_METHOD_NAME = "testIsDeliveryDate_T006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1234567");
            l_docDeliveryManagementParams.setDocumentCategory("111");
            l_docDeliveryManagementParams.setDeliveryDate(
                  WEB3DateUtility.getDate("20070906", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(null);
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            boolean l_bon = l_impl.isDeliveryDate(l_mainAccount);
            
            assertEquals(true,l_bon);
            
        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        } 
    }
    
    /*
     * Test method for 'webbroker3.pvinfo.WEB3PvInfoDataManagerImpl.getDocDeliveryManagementParams(WEB3GentradeMainAccount)'
     */
    public void testGetDocDeliveryManagementParams_T001() 
    {
        final String STR_METHOD_NAME = "testGetDocDeliveryManagementParams_T001";
        
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1234567");
            
            l_docDeliveryManagementParams.setDocumentCategory("111");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            
            List l_lisRecodes =  l_impl.getDocDeliveryManagementParams(l_mainAccount);

            DocDeliveryManagementRow l_DocDeliveryManagementRow =  (DocDeliveryManagementRow)l_lisRecodes.get(0);
            
            assertEquals(l_lisRecodes.size(),1);
            assertEquals(l_DocDeliveryManagementRow.getDocumentDiv(),"010");
            assertEquals(l_DocDeliveryManagementRow.getProductCode(),"1234567");
            assertEquals(l_DocDeliveryManagementRow.getDocumentCategory(),"111");

        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        }
    }

    public void testGetDocDeliveryManagementParams_T002() 
    {
        final String STR_METHOD_NAME = "testGetDocDeliveryManagementParams_T002";
        
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
//            
//            //BatoProductManagementRow.TYPE
//            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
           TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
//            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
//            l_batoProductManagementParams.setDocumentDiv("1");
//            l_batoProductManagementParams.setBatoProductCode("1234567");
//            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
//            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
//            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
//            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
//            l_docDeliveryManagementParams.setDocumentDiv("1");
//            l_docDeliveryManagementParams.setProductCode("1234567");
//            l_docDeliveryManagementParams.setLastUpdater("2222222");
//            l_docDeliveryManagementParams.setDeliveryDate(
//                WEB3DateUtility.getDate("20070606", "yyyyMMdd"));
//            l_docDeliveryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607", "yyyyMMdd"));
//            l_docDeliveryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070608", "yyyyMMdd"));
//            l_docDeliveryManagementParams.setDocumentCategory("111");
//            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            
            List l_lisRecodes =  l_impl.getDocDeliveryManagementParams(l_mainAccount);

            assertEquals(l_lisRecodes,null);

            
        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        }
    }
    
    public void testGetDocDeliveryManagementParams_T003() 
    {
        final String STR_METHOD_NAME = "testGetDocDeliveryManagementParams_T003";
        
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234568");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234569");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_batoProductManagementParams.setBatoProductCode("1234570");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1234569");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            
            List l_lisRecodes =  l_impl.getDocDeliveryManagementParams(l_mainAccount);

            DocDeliveryManagementRow l_DocDeliveryManagementRow =  (DocDeliveryManagementRow)l_lisRecodes.get(0);
            
            assertEquals(l_lisRecodes.size(),1);
            assertEquals(l_DocDeliveryManagementRow.getDocumentDiv(),"010");
            assertEquals(l_DocDeliveryManagementRow.getProductCode(),"1234569");
            
        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        }
    }
    
    public void testGetDocDeliveryManagementParams_T004() 
    {
        final String STR_METHOD_NAME = "testGetDocDeliveryManagementParams_T004";
        
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234568");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234569");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_batoProductManagementParams.setBatoProductCode("1234570");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("2234569");
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            
            List l_lisRecodes =  l_impl.getDocDeliveryManagementParams(l_mainAccount);

            //DocDeliveryManagementRow l_DocDeliveryManagementRow =  (DocDeliveryManagementRow)l_lisRecodes.get(0);
            
            assertEquals(l_lisRecodes,null);

        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        }
    }
    
    public void testGetDocDeliveryManagementParams_T005() 
    {
        final String STR_METHOD_NAME = "testGetDocDeliveryManagementParams_T005";
        
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //AdministratorParams
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
           
            l_administratorParams.setLoginId(1001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("0");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            //BatoProductManagementRow.TYPE
            BatoProductManagementParams l_batoProductManagementParams = new BatoProductManagementParams();
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);
            l_batoProductManagementParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_batoProductManagementParams.setBranchCode(l_administratorParams.getBranchCode());
            l_batoProductManagementParams.setDocumentDiv("010");
            l_batoProductManagementParams.setBatoProductCode("1234567");
            l_batoProductManagementParams.setValidFlag(WEB3ValidFlagDef.VALID);
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234568");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);

            l_batoProductManagementParams.setBatoProductCode("1234569");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            l_batoProductManagementParams.setBatoProductCode("1234570");
            TestDBUtility.insertWithDel(l_batoProductManagementParams);
            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001l);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(DocDeliveryManagementParams.TYPE);
            DocDeliveryManagementParams l_docDeliveryManagementParams = TestDBUtility.getDocDeliveryManagementRow();
            l_docDeliveryManagementParams.setAccountId(l_mainAccountParams.getAccountId());
            l_docDeliveryManagementParams.setDocumentDiv("010");
            l_docDeliveryManagementParams.setProductCode("1234569");
            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070206", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20070306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            l_docDeliveryManagementParams.setProductCode("1234570");

            l_docDeliveryManagementParams.setDeliveryDate(WEB3DateUtility.getDate("20070206", "yyyyMMdd"));
            l_docDeliveryManagementParams.setDeemedDeliveryDate(WEB3DateUtility.getDate("20070306", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_docDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            
            List l_lisRecodes =  l_impl.getDocDeliveryManagementParams(l_mainAccount);

            //DocDeliveryManagementRow l_DocDeliveryManagementRow =  (DocDeliveryManagementRow)l_lisRecodes.get(0);
            
            assertEquals(l_lisRecodes.size(),2);

        }
        catch(Exception e)
        {
            fail();
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
        }
    }
    
    
}
@
