head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCampaignAccOpenListRequestTest.java;


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
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ<BR>
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenListRequestTest extends TestBaseForMock 
{

    public WEB3AdminAccInfoCampaignAccOpenListRequestTest(String name) 
    {
        super(name);
    }
    
    private WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccInfoCampaignAccOpenListRequestTest.class);
    
    /**
     *１） キャンペーン名称のチェック
     *キャンペーン名称101バイト以上の場合
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = "testValidate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890123456789012" +
                "3456789012345678901234567890123456789012345678901";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *１） キャンペーン名称のチェック
     *キャンペーン名称101バイト以上の場合
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = "testValidate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890123456789012" +
                "345678901234567890123456789012345678901234567890112";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *２） 徴収率のチェック
     * 徴収率が 0 ～ 100 の整数以外の場合
     */
    public void testValidate_0003()
    {
        final String STR_METHOD_NAME = "testValidate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = "1234567890123456789012345678901234567890123456789012" +
                "34567890123456789012345678901234567890";
        //徴収率
        l_campaignSearchItem.collectRate = "ab";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *２） 徴収率のチェック
     * 徴収率が 0 ～ 100 の整数以外の場合
     */
    public void testValidate_0004()
    {
        final String STR_METHOD_NAME = "testValidate_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "101";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *２） 徴収率のチェック
     * 徴収率が 0 ～ 100 の整数以外の場合
     */
    public void testValidate_0005()
    {
        final String STR_METHOD_NAME = "testValidate_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "-1";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
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
     *３）　@要求ページ番号チェック 
     * 未入力の場合
     */
    public void testValidate_0006()
    {
        final String STR_METHOD_NAME = "testValidate_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "0";
        
        //要求ページ番号
        l_request.pageIndex = null;
        //ページ内表示行数
        l_request.pageSize = "1";
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        l_sortKey.keyItem = "collectRate";
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = sortKeys;
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            assertEquals("1", l_request.pageIndex);
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
     *３）　@要求ページ番号チェック 
     * 数字以外の文字が含まれる場合
     */
    public void testValidate_0007()
    {
        final String STR_METHOD_NAME = "testValidate_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "100";
        //要求ページ番号
        l_request.pageIndex = "ab";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00090);
            assertEquals("要求ページ番号が数字以外の値です。",l_ex.getErrorInfo().getErrorMessage());
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
     *３）　@要求ページ番号チェック 
     * マイナス値の場合
     */
    public void testValidate_0008()
    {
        final String STR_METHOD_NAME = "testValidate_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "-12";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00616);
            assertEquals("要求ページ番号の値が0以下です。",l_ex.getErrorInfo().getErrorMessage());
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
     *４）　@ページ内表示行数チェック 
     * 未入力の場合
     */
    public void testValidate_0009()
    {
        final String STR_METHOD_NAME = "testValidate_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = null;
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02224);
            assertEquals("ページ内表示行数が未入力です。",l_ex.getErrorInfo().getErrorMessage());
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
     *４）　@ページ内表示行数チェック 
     * 数字以外の文字が含まれる場合
     */
    public void testValidate_0010()
    {
        final String STR_METHOD_NAME = "testValidate_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "ab";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00092);
            assertEquals("ページ内表示行数が数字以外の値です。",l_ex.getErrorInfo().getErrorMessage());
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
     *４）　@ページ内表示行数チェック 
     * マイナス値の場合
     */
    public void testValidate_0011()
    {
        final String STR_METHOD_NAME = "testValidate_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "-66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00617);
            assertEquals("ページ内表示行数の値が0以下です。",l_ex.getErrorInfo().getErrorMessage());
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
     *５）　@ソートキーのチェック  
     * ソートキーが未入力lの場合
     */
    public void testValidate_0012()
    {
        final String STR_METHOD_NAME = "testValidate_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKeys[] = null;
        
        l_request.sortKeys = l_sortKeys;
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00231);
            assertEquals("ソートキーが未指定です。",l_ex.getErrorInfo().getErrorMessage());
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
     *５）　@ソートキーのチェック  
     * （ソートキーの要素数 == 0）の場合
     */
    public void testValidate_0013()
    {
        final String STR_METHOD_NAME = "testValidate_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKeys[] = new WEB3AccInfoSortKey[0];
        
        l_request.sortKeys = l_sortKeys;
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00232);
            assertEquals("ソートキーの要素数が０です。",l_ex.getErrorInfo().getErrorMessage());
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
     *５）　@ソートキーのチェック  
     * ソートキー.validate()
     */
    public void testValidate_0014()
    {
        final String STR_METHOD_NAME = "testValidate_0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        
        l_sortKey.ascDesc = null;
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00087);
            assertEquals("昇順／降順が未指定です。",l_ex.getErrorInfo().getErrorMessage());
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
     *５）　@ソートキーのチェック  
     * ソートキー.キー項目が下記の項目名以外の場合
     */
    public void testValidate_0015()
    {
        final String STR_METHOD_NAME = "testValidate_0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "ああああ";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        try
        {
            l_request.validate();
            fail();
        } 
        catch (WEB3BaseException l_ex) 
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00086);
            assertEquals("ソートキーのキー項目の値が存在しないコード値です。",l_ex.getErrorInfo().getErrorMessage());
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
     *５）　@ソートキーのチェック  
     * ソートキー.キー項目が下記の項目名以外の場合
     */
    public void testValidate_0016()
    {
        final String STR_METHOD_NAME = "testValidate_0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "collectRate";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        
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
     *５）　@ソートキーのチェック  
     * ソートキー.キー項目が下記の項目名以外の場合
     */
    public void testValidate_0017()
    {
        final String STR_METHOD_NAME = "testValidate_0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "branchCode";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        
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
     *５）　@ソートキーのチェック  
     * ソートキー.キー項目が下記の項目名以外の場合
     */
    public void testValidate_0018()
    {
        final String STR_METHOD_NAME = "testValidate_0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "traderCode";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        
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
     *５）　@ソートキーのチェック  
     * ソートキー.キー項目が下記の項目名以外の場合
     */
    public void testValidate_0019()
    {
        final String STR_METHOD_NAME = "testValidate_0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "accountOpenDateFrom";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        
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
     *５）　@ソートキーのチェック  
     * ソートキー.キー項目が下記の項目名以外の場合
     */
    public void testValidate_0020()
    {
        final String STR_METHOD_NAME = "testValidate_0020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "accountOpenDateTo";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        
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
     *５）　@ソートキーのチェック  
     * ソートキー.キー項目が下記の項目名以外の場合
     */
    public void testValidate_0021()
    {
        final String STR_METHOD_NAME = "testValidate_0021()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "registDate";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        
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
     *５）　@ソートキーのチェック  
     * ソートキー.キー項目が下記の項目名以外の場合
     */
    public void testValidate_0022()
    {
        final String STR_METHOD_NAME = "testValidate_0022()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = "88";
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "updateDate";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        
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
     * null
     */
    public void testValidate_0023()
    {
        final String STR_METHOD_NAME = "testValidate_0023()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignAccOpenListRequest l_request = 
            new WEB3AdminAccInfoCampaignAccOpenListRequest();
        
        WEB3AccInfoCampaignSearchCondition l_campaignSearchItem = new WEB3AccInfoCampaignSearchCondition();
        
        //キャンペーン名称
        l_campaignSearchItem.campaignName = null;
        //徴収率
        l_campaignSearchItem.collectRate = null;
        //要求ページ番号
        l_request.pageIndex = "55";
        //ページ内表示行数
        l_request.pageSize = "66";
        
        l_request.campaignSearchItem = l_campaignSearchItem;
        
        WEB3AccInfoSortKey l_sortKey = new WEB3AccInfoSortKey();
        
        l_sortKey.keyItem = "updateDate";
        
        l_sortKey.ascDesc = "A";
        
        WEB3AccInfoSortKey l_sortKeys[] = {l_sortKey};
        
        l_request.sortKeys = l_sortKeys;
        
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
