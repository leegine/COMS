head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocDivManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.gentrade.data.DocDivManagementParams;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTDocDivManagementTest extends TestBaseForMock
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocDivManagementTest.class);

    public WEB3AdminFPTDocDivManagementTest(String arg0)
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

    WEB3AdminFPTDocDivManagement l_adminDocAdminFPTDocDivManagement =
        new WEB3AdminFPTDocDivManagement("0D", "123", "1", "1");

    public void testGetDocDivManagementParams_case001()
    {
        final String STR_METHOD_NAME = "testGetDocDivManagementParams_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            l_adminDocAdminFPTDocDivManagement.getDocDivManagementParams();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01279, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetDocDivManagementParams_case002()
    {
        final String STR_METHOD_NAME = "testGetDocDivManagementParams_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setBranchCode("123");
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("2");
            l_docDivManagementParams.setDocumentNumber("1");
            l_docDivManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docDivManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docDivManagementParams.setDocumentCategory("1");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            DocDivManagementRow l_docDivManagementRow =
                l_adminDocAdminFPTDocDivManagement.getDocDivManagementParams();
            assertEquals("1", l_docDivManagementRow.getDocumentNumber());
            assertEquals("2", l_docDivManagementRow.getDocumentCheckDiv());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetDocDivManagementLists_case001()
    {
        final String STR_METHOD_NAME = "testGetDocDivManagementLists_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);

            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivAdminInfoUnits =
                l_adminDocAdminFPTDocDivManagement.getDocDivManagementLists();
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02998, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetDocDivManagementLists_case002()
    {
        final String STR_METHOD_NAME = "testGetDocDivManagementLists_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setBranchCode("123");
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("2");
            l_docDivManagementParams.setDocumentNumber("1");
            l_docDivManagementParams.setDocumentName("3");
            l_docDivManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docDivManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docDivManagementParams.setDocumentCategory("1");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("123");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("1");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);

            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivAdminInfoUnits =
                l_adminDocAdminFPTDocDivManagement.getDocDivManagementLists();
            assertEquals(1, l_documentDivAdminInfoUnits.length);
            assertEquals("2", l_documentDivAdminInfoUnits[0].docuCheckDiv);
            assertEquals("1", l_documentDivAdminInfoUnits[0].documentDiv);
            assertEquals("3", l_documentDivAdminInfoUnits[0].documentNames);
            assertEquals("1", l_documentDivAdminInfoUnits[0].documentCategory);
            assertEquals("test", l_documentDivAdminInfoUnits[0].documentCategoryName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetDocDivManagementLists_case003()
    {
        final String STR_METHOD_NAME = "testGetDocDivManagementLists_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(DocDivManagementParams.TYPE);
            DocDivManagementParams l_docDivManagementParams = new DocDivManagementParams();
            l_docDivManagementParams.setBranchCode("123");
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setDocumentDiv("1");
            l_docDivManagementParams.setDocumentCheckDiv("2");
            l_docDivManagementParams.setDocumentNumber("1");
            l_docDivManagementParams.setDocumentName("3");
            l_docDivManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docDivManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docDivManagementParams.setDocumentCategory("1");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            l_docDivManagementParams.setBranchCode("123");
            l_docDivManagementParams.setInstitutionCode("0D");
            l_docDivManagementParams.setDocumentDiv("2");
            l_docDivManagementParams.setDocumentCheckDiv("5");
            l_docDivManagementParams.setDocumentNumber("1");
            l_docDivManagementParams.setDocumentName("4");
            l_docDivManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docDivManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docDivManagementParams.setDocumentCategory("1");
            TestDBUtility.insertWithDel(l_docDivManagementParams);

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("123");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("1");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);

            l_docCategoryManagementParams.setBranchCode("123");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("1");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);

            WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivAdminInfoUnits =
                l_adminDocAdminFPTDocDivManagement.getDocDivManagementLists();
            assertEquals(2, l_documentDivAdminInfoUnits.length);
            assertEquals("2", l_documentDivAdminInfoUnits[0].docuCheckDiv);
            assertEquals("1", l_documentDivAdminInfoUnits[0].documentDiv);
            assertEquals("3", l_documentDivAdminInfoUnits[0].documentNames);
            assertEquals("1", l_documentDivAdminInfoUnits[0].documentCategory);
            assertEquals("test", l_documentDivAdminInfoUnits[0].documentCategoryName);

            assertEquals("5", l_documentDivAdminInfoUnits[1].docuCheckDiv);
            assertEquals("2", l_documentDivAdminInfoUnits[1].documentDiv);
            assertEquals("4", l_documentDivAdminInfoUnits[1].documentNames);
            assertEquals("1", l_documentDivAdminInfoUnits[1].documentCategory);
            assertEquals("test", l_documentDivAdminInfoUnits[1].documentCategoryName);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
