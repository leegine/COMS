head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiAccountDataUploadCsvTest_validateAppliLotDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AdminSrvRegiAccountDataUploadCsvTest_validateAppliLotDiv.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/11 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiAccountDataUploadCsvTest_validateAppliLotDiv extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataUploadCsvTest_validateAppliLotDiv.class);

    WEB3AdminSrvRegiAccountDataUploadCsv csv = new WEB3AdminSrvRegiAccountDataUploadCsv();

    public WEB3AdminSrvRegiAccountDataUploadCsvTest_validateAppliLotDiv(String arg0)
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

    public void testValidateAppliLotDiv01()
    {
        final String STR_METHOD_NAME = "testValidateAppliLotDiv01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            csv.validateAppliLotDiv("3", "0", "3");
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            fail();
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01022, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    public void testValidateAppliLotDiv011()
    {
        final String STR_METHOD_NAME = "testValidateAppliLotDiv01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            csv.validateAppliLotDiv("3", "0", "4");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01022, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateAppliLotDiv02()
    {
        final String STR_METHOD_NAME = "testValidateAppliLotDiv02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            csv.validateAppliLotDiv("2", "0", "3");
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01022, l_ex.getErrorInfo());
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
