head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
Author Name         : Daiwa Institute of Research
File Name           : WEB3AdminSrvRegiOtherOrgIdDownloadRequestTest.java
Revision History    : 2008/03/14 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdDownloadRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdCommonRequest.class);

    private WEB3AdminSrvRegiOtherOrgIdDownloadRequest orgIdDownloadRequest =
        new WEB3AdminSrvRegiOtherOrgIdDownloadRequest();

    public WEB3AdminSrvRegiOtherOrgIdDownloadRequestTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
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
     * Test method for 'webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest.createResponse()'
     */
    public void testCreateResponse_C0001()
    {
        final String STR_METHOD_NAME = "testCreateResponse_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse orgIdDownloadResponse=
            orgIdDownloadRequest.createResponse();

        assertTrue(orgIdDownloadResponse instanceof WEB3AdminSrvRegiOtherOrgIdDownloadResponse);
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0001()
    {

        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        orgIdDownloadRequest.serviceDiv = "03";
        orgIdDownloadRequest.seqNumber = "000100001500104002";
        orgIdDownloadRequest.id = "01021401";
        orgIdDownloadRequest.status = "0";
        orgIdDownloadRequest.branchCode = new String[]{"001","002"};
        orgIdDownloadRequest.accountCode = "１20001";

        try
        {
            orgIdDownloadRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01043,l_e.getErrorInfo());
            assertEquals("顧客コードの値が数字以外の値です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0002()
    {

        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        orgIdDownloadRequest.serviceDiv = "03";
        orgIdDownloadRequest.seqNumber = "000100001500104002";
        orgIdDownloadRequest.id = "01021401";
        orgIdDownloadRequest.status = "0";
        orgIdDownloadRequest.branchCode = new String[]{"001","002"};
        orgIdDownloadRequest.accountCode = "120001";
        orgIdDownloadRequest.sortKeys = null;

        try
        {
            orgIdDownloadRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231,l_e.getErrorInfo());
            assertEquals("ソートキーが未指定です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        orgIdDownloadRequest.serviceDiv = "03";
        orgIdDownloadRequest.seqNumber = "000100001500104002";
        orgIdDownloadRequest.id = "01021401";
        orgIdDownloadRequest.status = "0";
        orgIdDownloadRequest.branchCode = new String[]{"001","002"};
        orgIdDownloadRequest.accountCode = "120001";
        orgIdDownloadRequest.sortKeys = new WEB3SrvRegiSortKey[0];

        try
        {
            orgIdDownloadRequest.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00232,l_e.getErrorInfo());
            assertEquals("ソートキーの要素数が０です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);

        orgIdDownloadRequest.serviceDiv = "03";
        orgIdDownloadRequest.seqNumber = "000100001500104002";
        orgIdDownloadRequest.id = "01021401";
        orgIdDownloadRequest.status = "0";
        orgIdDownloadRequest.branchCode = new String[]{"001","002"};
        orgIdDownloadRequest.accountCode = "120001";
        orgIdDownloadRequest.sortKeys = new WEB3SrvRegiSortKey[1];

        try
        {
            orgIdDownloadRequest.validate();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
