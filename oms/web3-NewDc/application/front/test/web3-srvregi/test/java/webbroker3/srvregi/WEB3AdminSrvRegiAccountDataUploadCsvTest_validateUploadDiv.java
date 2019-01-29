head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.43.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadCsvTest_validateUploadDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountDataUploadCsvTest_validateUploadDiv.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/08 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadCsvTest_validateUploadDiv extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadCsvTest_validateUploadDiv.class);

    WEB3AdminSrvRegiAccountDataUploadCsv csv = new WEB3AdminSrvRegiAccountDataUploadCsv();

    public WEB3AdminSrvRegiAccountDataUploadCsvTest_validateUploadDiv(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateUploadDiv01()
    {
        final String STR_METHOD_NAME = "testValidateUploadDiv01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            csv.validateUploadDiv("5");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01020, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCheckDetailLines01()
    {
        final String STR_METHOD_NAME = "testCheckDetailLines01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            csv.checkDetailLines(1, 2);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCheckDetailLines02()
    {
        final String STR_METHOD_NAME = "testCheckDetailLines02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                new InstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(1);
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.SRVREGI_ACCOUNT_UPLOAD_MAXCOUNT);
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("5");
            l_institutionPreferencesParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_institutionPreferencesParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            csv.checkDetailLines(1, 2);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCheckDetailLines03()
    {
        final String STR_METHOD_NAME = "testCheckDetailLines03()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                new InstitutionPreferencesParams();
            l_institutionPreferencesParams.setInstitutionId(1);
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.SRVREGI_ACCOUNT_UPLOAD_MAXCOUNT);
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("5");
            l_institutionPreferencesParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_institutionPreferencesParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            csv.checkDetailLines(1, 7);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02418, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
