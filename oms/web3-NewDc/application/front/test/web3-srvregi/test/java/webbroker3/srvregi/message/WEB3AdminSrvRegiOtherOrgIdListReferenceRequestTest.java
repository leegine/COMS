head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdListReferenceRequestTest.java;


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
File Name           : WEB3AdminSrvRegiOtherOrgIdListReferenceRequestTest.java
Revision History    : 2008/03/14 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdListReferenceRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdCommonRequest.class);

    private WEB3AdminSrvRegiOtherOrgIdListReferenceRequest orgIdListReferenceRequest =
        new WEB3AdminSrvRegiOtherOrgIdListReferenceRequest();

    public WEB3AdminSrvRegiOtherOrgIdListReferenceRequestTest(String name)
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

        WEB3GenResponse orgIdListReferenceResponse=
            orgIdListReferenceRequest.createResponse();

        assertTrue(orgIdListReferenceResponse instanceof WEB3AdminSrvRegiOtherOrgIdListReferenceResponse);
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "2";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";

        try
        {
            orgIdListReferenceRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00890,l_e.getErrorInfo());
//            assertEquals("ステータスの値が不正です。",l_e.getErrorMessage());
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

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";
        orgIdListReferenceRequest.sortKeys = null;

        try
        {
            orgIdListReferenceRequest.validate();
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

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";
        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[0];

        try
        {
            orgIdListReferenceRequest.validate();
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

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";

        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[1];
        orgIdListReferenceRequest.sortKeys[0] = new WEB3SrvRegiSortKey();
        orgIdListReferenceRequest.sortKeys[0].keyItem = null;

        try
        {
            orgIdListReferenceRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00085,l_e.getErrorInfo());
            assertEquals("ソートキーのキー項目が未指定です。",l_e.getErrorMessage());
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

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";

        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[1];
        orgIdListReferenceRequest.sortKeys[0] = new WEB3SrvRegiSortKey();
        orgIdListReferenceRequest.sortKeys[0].keyItem = "name";
        orgIdListReferenceRequest.sortKeys[0].ascDesc = null;

        try
        {
            orgIdListReferenceRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00087,l_e.getErrorInfo());
            assertEquals("昇順／降順が未指定です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0006()
    {
        final String STR_METHOD_NAME = "testValidate_C0006()";
        log.entering(STR_METHOD_NAME);

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";
        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[1];
        orgIdListReferenceRequest.sortKeys[0] = new WEB3SrvRegiSortKey();
        orgIdListReferenceRequest.sortKeys[0].keyItem = "name";
        orgIdListReferenceRequest.sortKeys[0].ascDesc = "C";

        try
        {
            orgIdListReferenceRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00088,l_e.getErrorInfo());
            assertEquals("昇順／降順が”A：昇順”、”D：降順”以外の値です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0007()
    {
        final String STR_METHOD_NAME = "testValidate_C0007()";
        log.entering(STR_METHOD_NAME);

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";
        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[1];
        orgIdListReferenceRequest.sortKeys[0] = new WEB3SrvRegiSortKey();
        orgIdListReferenceRequest.sortKeys[0].ascDesc = "A";
        orgIdListReferenceRequest.sortKeys[0].keyItem = "name";
        orgIdListReferenceRequest.pageIndex = null;

        try
        {
            orgIdListReferenceRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00089,l_e.getErrorInfo());
            assertEquals("要求ページ番号が未指定です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(STR_METHOD_NAME);

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";
        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[1];   
        orgIdListReferenceRequest.sortKeys[0] = new WEB3SrvRegiSortKey();
        orgIdListReferenceRequest.sortKeys[0].ascDesc = "A";
        orgIdListReferenceRequest.sortKeys[0].keyItem = "name";
        orgIdListReferenceRequest.pageIndex = "a021";

        try
        {
            orgIdListReferenceRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00090,l_e.getErrorInfo());
            assertEquals("要求ページ番号が数字以外の値です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0009()
    {
        final String STR_METHOD_NAME = "testValidate_C0009()";
        log.entering(STR_METHOD_NAME);

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";
        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[1];
        orgIdListReferenceRequest.sortKeys[0] = new WEB3SrvRegiSortKey();
        orgIdListReferenceRequest.sortKeys[0].keyItem = "name";
        orgIdListReferenceRequest.sortKeys[0].ascDesc = "A";
        orgIdListReferenceRequest.pageIndex = "21";
        orgIdListReferenceRequest.pageSize = null;

        try
        {
            orgIdListReferenceRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02224,l_e.getErrorInfo());
            assertEquals("ページ内表示行数が未入力です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0010()
    {
        final String STR_METHOD_NAME = "testValidate_C0010()";
        log.entering(STR_METHOD_NAME);

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";
        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[1];
        orgIdListReferenceRequest.sortKeys[0] = new WEB3SrvRegiSortKey();
        orgIdListReferenceRequest.sortKeys[0].keyItem = "name";
        orgIdListReferenceRequest.sortKeys[0].ascDesc = "A";
        orgIdListReferenceRequest.pageIndex = "21";
        orgIdListReferenceRequest.pageSize = "10d5";

        try
        {
            orgIdListReferenceRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00092,l_e.getErrorInfo());
            assertEquals("ページ内表示行数が数字以外の値です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0011()
    {
        final String STR_METHOD_NAME = "testValidate_C0011()";
        log.entering(STR_METHOD_NAME);

        orgIdListReferenceRequest.serviceDiv = "03";
        orgIdListReferenceRequest.seqNumber = "000100001500104002";
        orgIdListReferenceRequest.id = "01021401";
        orgIdListReferenceRequest.status = "0";
        orgIdListReferenceRequest.branchCode = new String[]{"001","002"};
        orgIdListReferenceRequest.accountCode = "120001";
        orgIdListReferenceRequest.sortKeys = new WEB3SrvRegiSortKey[1];
        orgIdListReferenceRequest.sortKeys[0] = new WEB3SrvRegiSortKey();
        orgIdListReferenceRequest.sortKeys[0].keyItem = "name";
        orgIdListReferenceRequest.sortKeys[0].ascDesc = "A";
        orgIdListReferenceRequest.pageIndex = "21";
        orgIdListReferenceRequest.pageSize = "105";

        try
        {
            orgIdListReferenceRequest.validate();
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
