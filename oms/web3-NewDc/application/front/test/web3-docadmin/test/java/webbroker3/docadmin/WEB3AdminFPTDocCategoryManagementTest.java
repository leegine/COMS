head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.06.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFPTDocCategoryManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.docadmin;

import java.util.Calendar;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.gentrade.data.DocCategoryManagementParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFPTDocCategoryManagementTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocCategoryManagementTest.class);
    public WEB3AdminFPTDocCategoryManagementTest(String arg0)
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

    public void testIsDocumentCategory()
    {
        final String STR_METHOD_NAME = "testIsDocumentCategory()";
        log.entering(STR_METHOD_NAME);

        try
        {

            WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement("0D", new String[]{"381"}, "111");

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);
            assertFalse(l_docCategoryManagement.isDocumentCategory());

            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);

            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            assertFalse(!l_docCategoryManagement.isDocumentCategory());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetDocCategoryManagement()
    {
        final String STR_METHOD_NAME = "testGetDocCategoryManagement()";
        log.entering(STR_METHOD_NAME);

        try
        {

            WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement("0D", new String[]{"381", "382"}, "111");

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);

            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);

            l_docCategoryManagementParams.setBranchCode("382");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);
            TestDBUtility.deleteAll(BatoProductManagementParams.TYPE);

            assertEquals(2, l_docCategoryManagement.getDocCategoryManagement().size());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testGetDocumentCateName()
    {
        final String STR_METHOD_NAME = "testGetDocumentCateName()";
        log.entering(STR_METHOD_NAME);

        try
        {

            WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement("0D", new String[]{"381"}, "111");

            TestDBUtility.deleteAll(DocCategoryManagementParams.TYPE);

            DocCategoryManagementParams l_docCategoryManagementParams = new DocCategoryManagementParams();
            l_docCategoryManagementParams.setBranchCode("381");
            l_docCategoryManagementParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070521","yyyyMMdd"));
            l_docCategoryManagementParams.setInstitutionCode("0D");
            l_docCategoryManagementParams.setDocumentCategory("111");
            l_docCategoryManagementParams.setDocumentCateName("test");
            TestDBUtility.insertWithDel(l_docCategoryManagementParams);

            assertEquals("test", l_docCategoryManagement.getDocumentCateName());
            l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement();
            l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement("0D", new String[]{"382"}, "111");
            l_docCategoryManagement.getDocumentCateName();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03000, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
    }

}
@
