head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequestTest.java;


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
File Name           : WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequestTest.java
Revision History    : 2008/03/14 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdCommonRequest.class);

    private WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest orgIdUploadConfirmRequest =
        new WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest();

    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequestTest(String name)
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

    public void testCreateResponse_C0001()
    {
        final String STR_METHOD_NAME = "testCreateResponse_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse orgIdUploadConfirmResponse=
            orgIdUploadConfirmRequest.createResponse();

        assertTrue(orgIdUploadConfirmResponse instanceof WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse);
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        orgIdUploadConfirmRequest.serviceDiv = null;

        try
        {
            orgIdUploadConfirmRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00758,l_e.getErrorInfo());
            assertEquals("サービス区分が未指定です。",l_e.getErrorMessage());
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

        orgIdUploadConfirmRequest.serviceDiv = "0012";

        try
        {
            orgIdUploadConfirmRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00831,l_e.getErrorInfo());
            assertEquals("サービス区分の桁数が不正です。",l_e.getErrorMessage());
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

        orgIdUploadConfirmRequest.serviceDiv = "０1";

        try
        {
            orgIdUploadConfirmRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00882,l_e.getErrorInfo());
            assertEquals("サービス区分が数値以外の値です。",l_e.getErrorMessage());
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

        orgIdUploadConfirmRequest.serviceDiv = "01";
        orgIdUploadConfirmRequest.lines = null;

        try
        {
            orgIdUploadConfirmRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00976,l_e.getErrorInfo());
            assertEquals("アップロードファ@イルが未指定です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0005()
    {
        final String STR_METHOD_NAME = "testValidate_C0005()";
        log.entering(STR_METHOD_NAME);

        orgIdUploadConfirmRequest.serviceDiv = "01";
        orgIdUploadConfirmRequest.lines = new String[]{"10", "12"};

        try
        {
            orgIdUploadConfirmRequest.validate();
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
