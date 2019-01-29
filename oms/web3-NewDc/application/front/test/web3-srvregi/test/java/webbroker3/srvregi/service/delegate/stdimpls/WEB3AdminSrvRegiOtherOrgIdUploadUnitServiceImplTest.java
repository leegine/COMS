head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.srvregi.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.OtherOrgInfoAdminRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImplTest.class);
    WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl l_impl =
        new WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl();
    
    public WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImplTest(String arg0)
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

    public void testInsertOtherOrgInfo_T01()
    {
        final String STR_METHOD_NAME = "testInsertOtherOrgInfo_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("3216547");

            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams); 
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //OtherOrgInfoAdminParams
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);

            l_impl.insertOtherOrgInfo(
                1001l,
                "0",
                "1002",
                "123456",
                "1",
                "0D");
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);
            assertEquals(1, l_listResult.size());
            OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
                (OtherOrgInfoAdminRow)l_listResult.get(0);
            assertEquals(1001, l_otherOrgInfoAdminRow.getSequenceNumber());
            assertEquals("0", l_otherOrgInfoAdminRow.getSrvDiv());
            assertEquals("1002", l_otherOrgInfoAdminRow.getId());
            assertEquals("123456", l_otherOrgInfoAdminRow.getPassword());
            assertEquals("1", l_otherOrgInfoAdminRow.getStatus());
            assertEquals("0D", l_otherOrgInfoAdminRow.getInstitutionCode());
            assertEquals("3216547", l_otherOrgInfoAdminRow.getLastUpdater());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    public void testUpdateOtherOrgInfo_T01()
    {
        final String STR_METHOD_NAME = "testUpdateOtherOrgInfo_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //OtherOrgInfoAdminParams
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(1001l);
            l_otherOrgInfoAdminParams.setSrvDiv("0");
            l_otherOrgInfoAdminParams.setId("1002");
            l_otherOrgInfoAdminParams.setPassword("123456");
            l_otherOrgInfoAdminParams.setStatus("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setLastUpdater("321654");
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_otherOrgInfoAdminParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
            
            l_impl.updateOtherOrgInfo(1001l, "1", "1");
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03019, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateOtherOrgInfo_T02()
    {
        final String STR_METHOD_NAME = "testUpdateOtherOrgInfo_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //OtherOrgInfoAdminParams
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(1001l);
            l_otherOrgInfoAdminParams.setSrvDiv("0");
            l_otherOrgInfoAdminParams.setId("1002");
            l_otherOrgInfoAdminParams.setPassword("123456");
            l_otherOrgInfoAdminParams.setStatus("1");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setLastUpdater("321654");
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_otherOrgInfoAdminParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);
            
            l_impl.updateOtherOrgInfo(1001l, "0", "1");
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateOtherOrgInfo_T03()
    {
        final String STR_METHOD_NAME = "testUpdateOtherOrgInfo_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorCode("3216547");

            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams); 
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            
            //OtherOrgInfoAdminParams
            TestDBUtility.deleteAll(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
                new OtherOrgInfoAdminParams();
            l_otherOrgInfoAdminParams.setSequenceNumber(1001l);
            l_otherOrgInfoAdminParams.setSrvDiv("0");
            l_otherOrgInfoAdminParams.setId("1002");
            l_otherOrgInfoAdminParams.setPassword("123456");
            l_otherOrgInfoAdminParams.setStatus("9");
            l_otherOrgInfoAdminParams.setInstitutionCode("0D");
            l_otherOrgInfoAdminParams.setLastUpdater("1234567");
            l_otherOrgInfoAdminParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_otherOrgInfoAdminParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_otherOrgInfoAdminParams);

            l_impl.updateOtherOrgInfo(1001l, "0", "1");
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listResult =
                l_queryProcessor.doFindAllQuery(OtherOrgInfoAdminParams.TYPE);
            OtherOrgInfoAdminRow l_otherOrgInfoAdminRow =
                (OtherOrgInfoAdminRow)l_listResult.get(0);
            assertEquals("1", l_otherOrgInfoAdminRow.getStatus());
            assertEquals("3216547", l_otherOrgInfoAdminRow.getLastUpdater());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
