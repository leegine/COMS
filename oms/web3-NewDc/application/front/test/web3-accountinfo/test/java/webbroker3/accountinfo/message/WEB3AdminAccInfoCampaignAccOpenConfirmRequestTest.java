head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件変更確認ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件変更確認ﾘｸｴｽﾄ<BR>
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest extends TestBaseForMock 
{

    public WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest(String name) 
    {
        super(name);
    }
    
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenConfirmRequestTest.class);
    
    /**
     *１） 更新処理フラグのチェック
     *更新処理フラグ != (0 or 1 or 2) の場合
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "3";
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02710);
            assertEquals("更新処理フラグの値が存在しないコード値です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *  商品コードのチェック
     *  商品コード配列がnullの場合
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = null;
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02718);
            assertEquals("商品未選択エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *  キャンペーン名称のチェック
     *  キャンペーン名称が未入力の場合
     */
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = null;
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02712);
            assertEquals("キャンペーン名称未入力エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *  キャンペーン名称のチェック
     *  キャンペーン名称101バイト以上の場合
     */
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12345678901234567890123456789012345678901234" +
                "567890123456789012345678901234567890123456789012345678901";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02709);
            assertEquals("キャンペーン名称桁数エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *  キャンペーン名称のチェック
     *  キャンペーン名称101バイト以上の場合
     */
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12345678901234567890123456789012345678901234" +
                "567890123456789012345678901234567890123456789012345678901123";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02709);
            assertEquals("キャンペーン名称桁数エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   部店コードのチェック
     *   部店コードが3桁以外の場合
     */
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "0";
        //部店コード
        l_commissionCampaignInfo.branchCode = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00834);
            assertEquals("部店コードのサイズが不正です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   部店コードのチェック
     *   部店コードが3桁以外の場合
     */
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "1234";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00834);
            assertEquals("部店コードのサイズが不正です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   扱者コードのチェック
     *   扱者コードが6桁以上の場合
     */
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "123456";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01912);
            assertEquals("扱者コード（文字列）の長さが不正です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   扱者コードのチェック
     *   扱者コードが6桁以上の場合
     */
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "1234567";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01912);
            assertEquals("扱者コード（文字列）の長さが不正です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設経過期間（月）が未入力 && 口座開設経過期間（日）が未入力の場合
     */
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "23";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "32";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設経過期間（月）が未入力 && 口座開設経過期間（日）が未入力の場合
     */
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "1";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "a";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20060108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設経過期間（月）が０～１２の整数以外の場合
     */
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "11";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "36";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設経過期間（月）が０～１２の整数以外の場合
     */
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = "testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "-1";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "26";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設経過期間（日）が０～３１の整数以外の場合
     */
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = "testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "0";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "32";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設経過期間（日）が０～３１の整数以外の場合
     */
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = "testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "-1";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設日To!=nullの場合
     *   口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）を加算した日付が過去日付の場合
     */
    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = "testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "0";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "0";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   徴収率のチェック
     *   徴収率が未入力の場合
     */
    public void testValidate_0017()
    {
        final String STR_METHOD_NAME = "testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "0";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        
        l_commissionCampaignInfo.accountOpenDiv="1";
        
        l_commissionCampaignInfo.collectRate ="";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02080);
            assertEquals("徴収率が未入力です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   徴収率のチェック
     *   徴収率が 0 ～ 100 の整数以外の場合
     */
    public void testValidate_0018()
    {
        final String STR_METHOD_NAME = "testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "101";
        
        l_commissionCampaignInfo.accountOpenDiv="1";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設日To!=nullの場合
     *   口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）を加算した日付が過去日付の場合
     *   徴収率のチェック
     *   徴収率が 0 ～ 100 の整数以外の場合
     */
    public void testValidate_0019()
    {
        final String STR_METHOD_NAME = "testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "-1";
        
        l_commissionCampaignInfo.accountOpenDiv="1";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設日To!=nullの場合
     *   口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）を加算した日付が過去日付の場合
     *   徴収率のチェック
     *   徴収率が 0 ～ 100 の整数以外の場合
     */
    public void testValidate_0020()
    {
        final String STR_METHOD_NAME = "testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "ab";
        
        l_commissionCampaignInfo.accountOpenDiv="1";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   口座開設経過期間（月）==null && 口座開設経過期間（日）==null 
     *   対象期間From又は対象期間Toが未入力の場合
     */
    public void testValidate_0021()
    {
        final String STR_METHOD_NAME = "testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02713);
            assertEquals("対象期間未入力エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   口座開設経過期間（月）==null && 口座開設経過期間（日）==null 
     *   対象期間From又は対象期間Toが未入力の場合
     */
    public void testValidate_0022()
    {
        final String STR_METHOD_NAME = "testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02713);
            assertEquals("対象期間未入力エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   口座開設経過期間（月）==null && 口座開設経過期間（日）==null 
     *   対象期間From > 対象期間Toの場合
     */
    public void testValidate_0023()
    {
        final String STR_METHOD_NAME = "testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20071108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   口座開設経過期間（月）==null && 口座開設経過期間（日）==null 
     *   対象期間Toの日付が過去日付の場合
     */
    public void testValidate_0024()
    {
        final String STR_METHOD_NAME = "testValidate_0024()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   口座開設区分のチェック
     *   口座開設区分が 1:総合口座　@2:信用口座 3:先物OP口座　@4:FX口座　@5:中国株口座 以外の場合
     */
    public void testValidate_0025()
    {
//        final String STR_METHOD_NAME = "testValidate_0025()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
//            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
//        
//        //更新処理フラグ
//        l_request.updateFlag = "0";
//        
//        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
//        
//        String[] l_strItemCode = {"12"};
//        //商品コード配列
//        l_commissionCampaignInfo.itemCode = l_strItemCode;
//        //キャンペーン名称
//        l_commissionCampaignInfo.campaignName = "12";
//        //部店コード
//        l_commissionCampaignInfo.branchCode = "123";
//        //扱者コード
//        l_commissionCampaignInfo.traderCode = "12345";
//
//        //対象期間To
//        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20071108","yyyyMMdd");
//        //対象期間From
//        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
//        //徴収率
//        l_commissionCampaignInfo.collectRate = "100";
//        //口座開設区分
//        l_commissionCampaignInfo.accountOpenDiv = "6";
//        
//        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
//        
//        try
//        {
//            l_request.validate();
//            fail();
//        } 
//        catch (WEB3BaseException l_ex) 
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02719);
//            assertEquals("口座開設区分エラー。",l_ex.getErrorInfo().getErrorMessage());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_START + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *    口座開設日のチェック
     *    (対象期間From != null && 対象期間To != null) AND (口座開設日From !=null OR 口座開設日To != null)の場合、
     */
    public void testValidate_0026()
    {
        final String STR_METHOD_NAME = "testValidate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20071108","yyyyMMdd");
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "1";
        //口座開設日From
        l_commissionCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02720);
            assertEquals("口座開設日エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *    口座開設日のチェック
     *    (対象期間From != null && 対象期間To != null) AND (口座開設日From !=null OR 口座開設日To != null)の場合、
     */
    public void testValidate_0027()
    {
        final String STR_METHOD_NAME = "testValidate_0027()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20071108","yyyyMMdd");
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "2";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02720);
            assertEquals("口座開設日エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *    口座開設日のチェック
     *    口座開設日Toが過去日付の場合
     */
    public void testValidate_0029()
    {
        final String STR_METHOD_NAME = "testValidate_0029()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "4";
        
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02720);
            assertEquals("口座開設日エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 1 （更新）の場合、
     *    口座開設日のチェック
     *    口座開設日Toが過去日付の場合
     */
    public void testValidate_0030()
    {
        final String STR_METHOD_NAME = "testValidate_0030()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "5";
        
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02720);
            assertEquals("口座開設日エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *３） 更新処理フラグが 1 （更新）の場合、
     *    手数料割引キャンペーン条件IDのチェック
     *    手数料割引キャンペーン条件IDが未入力の場合
     */
    public void testValidate_0031()
    {
        final String STR_METHOD_NAME = "testValidate_0031()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "1";
        
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        
        l_commissionCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071108","yyyyMMdd");
        
        l_commissionCampaignInfo.registType = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02716);
            assertEquals("手数料割引キャンペーン条件IDが未指定です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *３） 更新処理フラグが 2（削除）の場合、
     *    手数料割引キャンペーン条件IDのチェック
     *    手数料割引キャンペーン条件IDが未入力の場合
     */
    public void testValidate_0032()
    {
        final String STR_METHOD_NAME = "testValidate_0032()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "2";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02716);
            assertEquals("手数料割引キャンペーン条件IDが未指定です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *４） 登録タイプのチェック
     *登録タイプ != 0 の場合
     */
    public void testValidate_0033()
    {
        final String STR_METHOD_NAME = "testValidate_0033()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
//        //更新処理フラグ
//        l_request.updateFlag = "2";
//             
//        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
//        
//        //手数料割引キャンペーン条件ID
//        l_commissionCampaignInfo.campaignId = "12";
//        //登録タイプ 
//        l_commissionCampaignInfo.registType = "1";
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20070108","yyyyMMdd");
        
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "1";
        
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        
        l_commissionCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071108","yyyyMMdd");
        
        l_commissionCampaignInfo.registType = "1";
        
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02722);
            assertEquals("登録タイプが'0'以外の値です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *OK_0
     */
    public void testValidate_0034()
    {
        final String STR_METHOD_NAME = "testValidate_0034()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "1";
        
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        
        //手数料割引キャンペーン条件ID
        l_commissionCampaignInfo.campaignId = "12";
        //登録タイプ 
        l_commissionCampaignInfo.registType = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *OK_1
     */
    public void testValidate_0035()
    {
        final String STR_METHOD_NAME = "testValidate_0035()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "1";
        
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        
        //手数料割引キャンペーン条件ID
        l_commissionCampaignInfo.campaignId = "12";
        //登録タイプ 
        l_commissionCampaignInfo.registType = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * OK_2
     */
    public void testValidate_0036()
    {
        final String STR_METHOD_NAME = "testValidate_0036()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "2";
             
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        //手数料割引キャンペーン条件ID
        l_commissionCampaignInfo.campaignId = "12";
        //登録タイプ 
        l_commissionCampaignInfo.registType = "0";
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設経過期間（月）が未入力 && 口座開設経過期間（日）が未入力の場合
     */
    public void testValidate_0037()
    {
        final String STR_METHOD_NAME = "testValidate_0037()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = null;
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = null;
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02713);
            assertEquals("対象期間未入力エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設経過期間（月）が未入力 && 口座開設経過期間（日）が未入力の場合
     */
    public void testValidate_0038()
    {
        final String STR_METHOD_NAME = "testValidate_0038()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = null;
        //口座開設経過期間（日）
        //l_commissionCampaignInfo.accopenPassPeriodDay = "23";
        //口座開設日To
        l_commissionCampaignInfo.targetPeriodTo = WEB3DateUtility.getDate("20060107","yyyyMMdd");
        
        l_commissionCampaignInfo.targetPeriodFrom = WEB3DateUtility.getDate("20060108","yyyyMMdd");
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     *２） 更新処理フラグが 0 （登録）の場合、
     *   対象期間From==null && 対象期間To==null
     *   口座開設日To!=nullの場合
     *   口座開設日Toに口座開設経過期間(月)と口座開設経過期間（日）を加算した日付が過去日付の場合
     *   徴収率のチェック
     *   徴収率が 0 ～ 100 の整数以外の場合
     */
    public void testValidate_0039()
    {
        final String STR_METHOD_NAME = "testValidate_0039()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = "123";
        //扱者コード
        l_commissionCampaignInfo.traderCode = "12345";
        //口座開設経過期間（月）
        l_commissionCampaignInfo.accopenPassPeriodMonth = "12";
        //口座開設経過期間（日）
        l_commissionCampaignInfo.accopenPassPeriodDay = "31";
        //口座開設日To
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "1.5";
        
        l_commissionCampaignInfo.accountOpenDiv = "1";
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    
    /**
     * null
     */
    public void testValidate_0040()
    {
        final String STR_METHOD_NAME = "testValidate_0028()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_commissionCampaignInfo = new WEB3AccInfoCampaignInfo();
        
        String[] l_strItemCode = {"12"};
        //商品コード配列
        l_commissionCampaignInfo.itemCode = l_strItemCode;
        //キャンペーン名称
        l_commissionCampaignInfo.campaignName = "12";
        //部店コード
        l_commissionCampaignInfo.branchCode = null;
        //扱者コード
        l_commissionCampaignInfo.traderCode = null;
        //対象期間To
        l_commissionCampaignInfo.targetPeriodTo =  WEB3DateUtility.getDate("20071104","yyyyMMdd");
        //対象期間From
        l_commissionCampaignInfo.targetPeriodFrom =  WEB3DateUtility.getDate("20070104","yyyyMMdd");
        //徴収率
        l_commissionCampaignInfo.collectRate = "100";
        //口座開設区分
        l_commissionCampaignInfo.accountOpenDiv = "3";
        
        l_commissionCampaignInfo.accountOpenDateFrom = WEB3DateUtility.getDate("20071104","yyyyMMdd");
        l_commissionCampaignInfo.accountOpenDateTo = WEB3DateUtility.getDate("20071106","yyyyMMdd");
        
        
        l_request.commissionCampaignInfo = l_commissionCampaignInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02722);
            assertEquals("登録タイプが'0'以外の値です。",l_ex.getErrorInfo().getErrorMessage());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
        }
        catch (Exception e)
        {
            log.exiting(TEST_START + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
