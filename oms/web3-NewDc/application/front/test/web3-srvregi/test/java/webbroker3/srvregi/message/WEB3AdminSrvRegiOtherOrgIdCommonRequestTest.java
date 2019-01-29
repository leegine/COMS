head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.05.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSrvRegiOtherOrgIdCommonRequestTest.java;


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
File Name           : WEB3AdminSrvRegiOtherOrgIdCommonRequestTest.java
Revision History    : 2008/03/14 王志葵(中訊) 新規作成 モデルNo.338
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSrvRegiOtherOrgIdCommonRequestTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdCommonRequest.class);

    private WEB3AdminSrvRegiOtherOrgIdCommonRequest orgIdCommonRequest=
        new WEB3AdminSrvRegiOtherOrgIdCommonRequest();

    public WEB3AdminSrvRegiOtherOrgIdCommonRequestTest(String name)
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
     * 　@this.サービス区分==nullの場合、例外をスローする。<BR>
     */
    public void testValidate_C0001()
    {
        final String STR_METHOD_NAME = "testValidate_C0001()";
        log.entering(STR_METHOD_NAME);

        orgIdCommonRequest.serviceDiv = null;

        try
        {
            orgIdCommonRequest.validate();
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

    //this.サービス区分!=nullであり、かつ桁数!=2桁の場合、例外をスローする。<BR>
    public void testValidate_C0002()
    {
        final String STR_METHOD_NAME = "testValidate_C0002()";
        log.entering(STR_METHOD_NAME);

        orgIdCommonRequest.serviceDiv = "003";

        try
        {
            orgIdCommonRequest.validate();
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

    // this.サービス区分!=nullであり、かつ半角数字以外が格納されている場合、例外をスローする。<BR>
    public void testValidate_C0003()
    {
        final String STR_METHOD_NAME = "testValidate_C0003()";
        log.entering(STR_METHOD_NAME);

        orgIdCommonRequest.serviceDiv = "１3";

        try
        {
            orgIdCommonRequest.validate();
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

    //  this.通番!=nullであり、かつ桁数>18桁の場合、例外をスローする。<BR>
    public void testValidate_C0004()
    {
        final String STR_METHOD_NAME = "testValidate_C0004()";
        log.entering(STR_METHOD_NAME);

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "0001000015001040023";

        try
        {
            orgIdCommonRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03054,l_e.getErrorInfo());
            assertEquals("通番のサイズが不正です。",l_e.getErrorMessage());
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

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "00010000150010400２";

        try
        {
            orgIdCommonRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03055,l_e.getErrorInfo());
            assertEquals("通番が数値以外の値です。",l_e.getErrorMessage());
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

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "010214001";

        try
        {
            orgIdCommonRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00954,l_e.getErrorInfo());
            assertEquals("IDのサイズが不正です。",l_e.getErrorMessage());
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

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "01021401";
        orgIdCommonRequest.status = "2";

        try
        {
            orgIdCommonRequest.validate();
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

    public void testValidate_C0008()
    {
        final String STR_METHOD_NAME = "testValidate_C0008()";
        log.entering(STR_METHOD_NAME);

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "01021401";
        orgIdCommonRequest.status = "0";
        orgIdCommonRequest.branchCode = null;

        try
        {
            orgIdCommonRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02174,l_e.getErrorInfo());
            assertEquals("部店コードがnullです。",l_e.getErrorMessage());
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

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "01021401";
        orgIdCommonRequest.status = "0";
        orgIdCommonRequest.branchCode = new String[]{"01", "002"};

        try
        {
            orgIdCommonRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00834,l_e.getErrorInfo());
            assertEquals("部店コードのサイズが不正です。",l_e.getErrorMessage());
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

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "01021401";
        orgIdCommonRequest.status = "0";
        orgIdCommonRequest.branchCode = new String[]{"0０1","002"};

        try
        {
            orgIdCommonRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01729,l_e.getErrorInfo());
            assertEquals("部店コードが数値以外の値です。",l_e.getErrorMessage());
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

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "01021401";
        orgIdCommonRequest.status = "0";
        orgIdCommonRequest.branchCode = new String[]{"001","002"};
        orgIdCommonRequest.accountCode = "001";
        
        try
        {
            orgIdCommonRequest.validate();
            fail();

        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00836,l_e.getErrorInfo());
            assertEquals("顧客コードのサイズが不正です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0012()
    {
        final String STR_METHOD_NAME = "testValidate_C0012()";
        log.entering(STR_METHOD_NAME);

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "01021401";
        orgIdCommonRequest.status = "0";
        orgIdCommonRequest.branchCode = new String[]{"001","002"};
        orgIdCommonRequest.accountCode = "１20001";
        
        try
        {
            orgIdCommonRequest.validate();
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

    public void testValidate_C0013()
    {
        final String STR_METHOD_NAME = "testValidate_C0013()";
        log.entering(STR_METHOD_NAME);

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "01021401";
        orgIdCommonRequest.status = "0";
        orgIdCommonRequest.branchCode = new String[]{"001","002"};
        orgIdCommonRequest.accountCode = "120001";
        orgIdCommonRequest.appliStartFrom = WEB3DateUtility.getDate("20070521","yyyyMMdd");
        orgIdCommonRequest.appliStartTo = WEB3DateUtility.getDate("20070520","yyyyMMdd");
        try
        {
            orgIdCommonRequest.validate();
            fail();
        }
        catch (WEB3BusinessLayerException l_e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03068,l_e.getErrorInfo());
//            assertEquals("適用開始日の値が不正です。",l_e.getErrorMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidate_C0014()
    {
        final String STR_METHOD_NAME = "testValidate_C0014()";
        log.entering(STR_METHOD_NAME);

        orgIdCommonRequest.serviceDiv = "03";
        orgIdCommonRequest.seqNumber = "000100001500104002";
        orgIdCommonRequest.id = "01021401";
        orgIdCommonRequest.status = "0";
        orgIdCommonRequest.branchCode = new String[]{"001","002"};
        orgIdCommonRequest.accountCode = "120001";
        orgIdCommonRequest.appliStartFrom = new Date("2008/01/02");
        orgIdCommonRequest.appliStartTo = new Date("2008/01/03");

        try
        {
            orgIdCommonRequest.validate();
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
