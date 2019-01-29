head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.39.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignIndiviConfirmRequestTest.java;


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
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更確認ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ個別顧客指定変更確認ﾘｸｴｽﾄ<BR>
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviConfirmRequestTest extends TestBaseForMock
{

    public WEB3AdminAccInfoCampaignIndiviConfirmRequestTest(String name) 
    {
        super(name);
    }
    
    private WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccInfoCampaignIndiviConfirmRequestTest.class);
    
    /**
     *１） 更新処理フラグのチェック 
     *更新処理フラグが '0' か '1' か '2'以外の場合
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
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
     *２） 登録タイプのチェック
     * 登録タイプが '1'(個別顧客指定) か '2'(強制個別顧客指定)以外の場合
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        l_accopenConditionInfo.registType = "0";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02711);
            assertEquals("登録タイプが'1'(個別顧客指定) か '2'(強制個別顧客指定)以外の値です。",
                l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *３）　@部店コードのチェック
     * 未入力の場合
     */
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "1";
        //部店コード
        l_accopenConditionInfo.branchCode = null;
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00833);
            assertEquals("部店コードが未指定です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *３）　@部店コードのチェック
     * ３桁以外の場合
     */
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "01";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00834);
            assertEquals("部店コードのサイズが不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *３）　@部店コードのチェック
     * ３桁以外の場合
     */
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "0101";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00834);
            assertEquals("部店コードのサイズが不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *４）　@顧客コードのチェック
     * 未入力の場合
     */
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = null;
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00835);
            assertEquals("顧客コードが未指定です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *４）　@顧客コードのチェック
     * 桁数が6でない場合
     */
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "1223";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00836);
            assertEquals("顧客コードのサイズが不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *４）　@顧客コードのチェック
     * 桁数が6でない場合
     */
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "1223123";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00836);
            assertEquals("顧客コードのサイズが不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *５）　@キャンペーン名称のチェック
     * 101バイト以上の場合
     */
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = "12345678901234567890123456789012345678901234567890123" +
                "456789012345678901234567890123456789012345678901";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02709);
            assertEquals("キャンペーン名称桁数エラー。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *５）　@キャンペーン名称のチェック
     * 101バイト以上の場合
     */
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = "12345678901234567890123456789012345678901234567890123" +
            "45678901234567890123456789012345678901234567890123";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02709);
            assertEquals("キャンペーン名称桁数エラー。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *５）　@キャンペーン名称のチェック
     * [登録タイプ = '1'(個別顧客指定)の場合のみ、５－２）のチェックを行う]
     * 未入力の場合
     */
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "1";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02712);
            assertEquals("キャンペーン名称未入力エラー。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *５）　@キャンペーン名称のチェック
     * [登録タイプ = '2'(強制顧客指定)の場合のみ、５－３）のチェックを行う]
     * 文字が入力されている場合
     */
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = "12";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02725);
            assertEquals("キャンペーン名称入力エラー。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *６）　@対象期間From、Toのチェック
     * 対象期間From,対象期間Toが入力された場合、
     * 対象期間From ＞ 対象期間Toの場合
     */
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = "testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070105","yyyyMMdd");
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *７）　@徴収率のチェック
     *未入力の場合
     */
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = "testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = null;
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02080);
            assertEquals("徴収率が未入力です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *７）　@徴収率のチェック
     *0～100の整数以外の場合
     */
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = "testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = "101";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *７）　@徴収率のチェック
     *0～100の整数以外の場合
     */
    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = "testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = "-1";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *７）　@徴収率のチェック
     *0～100の整数以外の場合
     */
    public void testValidate_0017()
    {
        final String STR_METHOD_NAME = "testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = "ab";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *８）　@対象期間Toのチェック
     *対象期間Toが入力された場合、日付が過去日付の場合
     */
    public void testValidate_0018()
    {
        final String STR_METHOD_NAME = "testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070109","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = "0";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '1'(変更)の場合]
     *６）　@対象期間From、Toのチェック
     * 対象期間From,対象期間Toが入力された場合、
     * 対象期間From ＞ 対象期間Toの場合
     */
    public void testValidate_0019()
    {
        final String STR_METHOD_NAME = "testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070105","yyyyMMdd");
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
            assertEquals("対象期間エラー。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '1'(変更)の場合]
     *７）　@徴収率のチェック
     *未入力の場合
     */
    public void testValidate_0020()
    {
        final String STR_METHOD_NAME = "testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = null;
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02080);
            assertEquals("徴収率が未入力です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '1'(変更)の場合]
     *７）　@徴収率のチェック
     *0～100の整数以外の場合
     */
    public void testValidate_0021()
    {
        final String STR_METHOD_NAME = "testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = "101";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '1'(変更)の場合]
     *７）　@徴収率のチェック
     *0～100の整数以外の場合
     */
    public void testValidate_0022()
    {
        final String STR_METHOD_NAME = "testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = "-1";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '1'(変更)の場合]
     *７）　@徴収率のチェック
     *0～100の整数以外の場合
     */
    public void testValidate_0023()
    {
        final String STR_METHOD_NAME = "testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = "ab";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02082);
            assertEquals("徴収率の値が不正です。", l_ex.getErrorInfo().getErrorMessage());
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
     * [リクエストデータ.更新処理フラグ = '1'(変更)の場合]
     *８）　@対象期間Toのチェック
     *対象期間Toが入力された場合、日付が過去日付の場合
     */
    //此代碼已經被刪除
//    public void testValidate_0024()
//    {
//        final String STR_METHOD_NAME = "testValidate_0024()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
//            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
//        
//        //更新処理フラグ
//        l_request.updateFlag = "1";
//        
//        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
//        
//        //登録タイプ
//        l_accopenConditionInfo.registType = "2";
//        //対象期間From
//        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
//        //対象期間To
//        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
//        //徴収率
//        l_accopenConditionInfo.collectRate = "100";
//        
//        //手数料割引キャンペーン条件情報
//        l_request.commissionCampaignInfo = l_accopenConditionInfo;
//        
//        try
//        {
//            l_request.validate();
//            fail();
//        } 
//        catch (WEB3BaseException l_ex) 
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
//            assertEquals("対象期間エラー。", l_ex.getErrorInfo().getErrorMessage());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_START + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
    
    /**
     * [リクエストデータ.更新処理フラグ = '2'(削除)の場合]
     *８）　@対象期間Toのチェック
     *対象期間Toが入力された場合、日付が過去日付の場合
     */
//    public void testValidate_0025()
//    {
//        final String STR_METHOD_NAME = "testValidate_0025()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
//            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
//        
//        //更新処理フラグ
//        l_request.updateFlag = "2";
//        
//        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
//        
//        //登録タイプ
//        l_accopenConditionInfo.registType = "2";
//        //対象期間From
//        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070109","yyyyMMdd");
//        //対象期間To
//        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20070108","yyyyMMdd");
//        //徴収率
//        l_accopenConditionInfo.collectRate = "100";
//        
//        //手数料割引キャンペーン条件情報
//        l_request.commissionCampaignInfo = l_accopenConditionInfo;
//        
//        try
//        {
//            l_request.validate();
//            fail();
//        } 
//        catch (WEB3BaseException l_ex) 
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02715);
//            assertEquals("対象期間エラー。", l_ex.getErrorInfo().getErrorMessage());
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !");
//        }
//        catch (Exception e)
//        {
//            log.exiting(TEST_START + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
    
    /**
     * [リクエストデータ.更新処理フラグ = '0'(登録)の場合]
     *８）　@対象期間Toのチェック
     *対象期間Toが入力された場合、日付が過去日付の場合
     */
    public void testValidate_0026()
    {
        final String STR_METHOD_NAME = "testValidate_0026()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "0";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //徴収率
        l_accopenConditionInfo.collectRate = "0";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
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
     * [リクエストデータ.更新処理フラグ = '1'(変更)の場合]
     *８）　@対象期間Toのチェック
     *対象期間Toが入力された場合、日付が過去日付の場合
     */
    public void testValidate_0027()
    {
        final String STR_METHOD_NAME = "testValidate_0027()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "1";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //徴収率
        l_accopenConditionInfo.collectRate = "0";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
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
     * [リクエストデータ.更新処理フラグ = '2'(削除)の場合]
     *８）　@対象期間Toのチェック
     *対象期間Toが入力された場合、日付が過去日付の場合
     */
    public void testValidate_0028()
    {
        final String STR_METHOD_NAME = "testValidate_0028()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request = 
            new WEB3AdminAccInfoCampaignIndiviConfirmRequest();
        
        //更新処理フラグ
        l_request.updateFlag = "2";
        
        WEB3AccInfoCampaignInfo l_accopenConditionInfo = new WEB3AccInfoCampaignInfo();
        
        //登録タイプ
        l_accopenConditionInfo.registType = "2";
        //部店コード
        l_accopenConditionInfo.branchCode = "011";
        //顧客コード
        l_accopenConditionInfo.accountCode = "123456";
        //キャンペーン名称
        l_accopenConditionInfo.campaignName = null;
        //対象期間From
        l_accopenConditionInfo.targetPeriodFrom = WEB3DateUtility.getDate("20070107","yyyyMMdd");
        //対象期間To
        l_accopenConditionInfo.targetPeriodTo = WEB3DateUtility.getDate("20071108","yyyyMMdd");
        //徴収率
        l_accopenConditionInfo.collectRate = "0";
        
        //手数料割引キャンペーン条件情報
        l_request.commissionCampaignInfo = l_accopenConditionInfo;
        
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
}
@
