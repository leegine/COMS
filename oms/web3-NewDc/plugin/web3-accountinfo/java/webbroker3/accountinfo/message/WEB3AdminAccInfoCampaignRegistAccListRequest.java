head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignRegistAccListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ登録顧客照会ﾘｸｴｽﾄ(WEB3AdminAccInfoCampaignRegistAccListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
Revision History : 2007/2/1  モデルNo.165
Revision History : 2007/2/3  モデルNo.178
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ登録顧客照会ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報 手数料割引キャンペーン登録顧客照会リクエスト<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignRegistAccListRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignRegistAccListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignRegistAccList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312045L;
    
    /**
     * (商品コード)<BR>
     * 商品コード<BR>
     * <BR>
     * 10 ： 上場株式<BR>
     * 11 ： JASDAQ<BR>
     * 12 ： ミニ株式 <BR>
     * 30 ： 債券 <BR>
     * 31 ： 債券（店頭） <BR>
     * 40 ： 外国株式 <BR>
     * 50 ： 先物 <BR>
     * 51 ： 株価指数ＯＰ<BR>
     */
    public String itemCode;
    
    /**
     * (キャンペーン名称)<BR>
     * キャンペーン名称<BR>
     */
    public String campaignName;
    
    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;
    
    /**
     * (扱者コード)<BR>
     * 扱者コード<BR>
     */
    public String traderCode;
    
    /**
     * (口座開設区分)<BR>
     * 口座開設区分<BR>
     * <BR>
     * 1 ： 総合口座<BR>
     * 2 ： 信用口座<BR>
     * 3 ： 先物OP口座<BR>
     * 4 ： FX口座<BR>
     * 5 ： 中国株口座<BR>
     */
    public String accountOpenDiv;
    
    /**
     * (徴収率)<BR>
     * 徴収率<BR>
     */
    public String collectRate;
    
    /**
     * (対象日)<BR>
     * 対象日<BR>
     */
    public Date targetDate;
    
    /**
     * (登録タイプ)<BR>
     * 登録タイプ<BR>
     * <BR>
     * 0 ： 口座開設条件指定<BR>
     * 1 ： 個別顧客指定<BR>
     * 2 ： 強制個別顧客指定<BR>
     */
    public String registType;
    
    /**
     * (有効区分)<BR>
     * 有効区分<BR>
     * <BR>
     * 0 ： 無効<BR>
     * 1 ： 有効<BR>
     */
    public String activeDiv;
    
    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;
    
    /**
     * ソートキー
     */
    public WEB3AccInfoSortKey sortKeys[];
    
    /**
     * @@roseuid 45C08762018B
     */
    public WEB3AdminAccInfoCampaignRegistAccListRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignRegistAccListResponse(this);
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）「キャンペーン名称」チェック<BR>
     * 　@　@・キャンペーン名称 != null の場合は、以下のチェックを行う。<BR>
     * <BR>
     * １-１）キャンペーン名称101バイト以上の場合、『キャンペーン名称桁数エラー』例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02709<BR>
     * <BR>
     * <BR>
     * ２）「部店コード」チェック<BR>
     * 　@　@・部店コード != null の場合は、以下のチェックを行う。<BR>
     * <BR>
     * ２-１）部店コードが3桁以外の場合、『部店コード桁数エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00834<BR>
     * <BR>
     * <BR>
     * ３）「顧客コード」チェック<BR>
     * 　@　@・顧客コード != null の場合は、以下のチェックを行う。<BR>
     * <BR>
     * ３-１）顧客コードが7桁以上の場合、『顧客コード桁数エラー』例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00836<BR>
     * <BR>
     * <BR>
     * ４）「扱者コード」チェック<BR>
     * 　@　@・扱者コード != null の場合は、以下のチェックを行う。<BR>
     * <BR>
     * ４-１）扱者コードが6桁以上の場合、『扱者コード桁数エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01912<BR>
     * <BR>
     * <BR>
     * ５）「徴収率」チェック<BR>
     * 　@　@・徴収率 != null の場合は、以下のチェックを行う。<BR>
     * <BR>
     * ５-１）徴収率が 0～100 の整数以外の場合、『徴収率エラー』例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02082<BR>
     * <BR>
     * ６）「要求ページ番号」チェック<BR>
     * ６-１）未入力の場合、 要求ページ番号に”１”をセットする。<BR>
     * <BR>
     * ６-２）数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00090<BR>
     * <BR>
     * ６-２）マイナス値の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00616<BR>
     * <BR>
     * <BR>
     * ７）「ページ内表示行数」チェック<BR>
     * ７-１）未入力の場合、例外をスローする。  <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02224<BR>
     * <BR>
     * ７-２）数字以外の文字が含まれる場合、例外をスローする。  <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00092<BR>
     * <BR>
     * ７-３）マイナス値の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00617<BR>
     * <BR>
     * <BR>
     * ８）「ソートキー」チェック<BR>
     * ８-１）ソートキー = null の場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00231<BR>
     * <BR>
     * ８-２）ソートキーの要素数 = 0 の場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00232<BR>
     * <BR>
     * ８-３）ソートキーの要素数分、下記のチェックを繰り返して行う。<BR>
     * 　@　@８-３-１）ソートキー.validate()をコールする。<BR>
     * 　@　@８-３-２）ソートキー.キー項目が下記の項目名以外の場合、例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@・部店コード<BR>
     * 　@　@　@　@　@　@　@　@　@・顧客コード<BR>
     * 　@　@　@　@　@　@　@　@　@・扱者コード<BR>
     * 　@　@　@　@　@　@　@　@　@・徴収率<BR>
     * 　@　@　@　@　@　@　@　@　@・対象期間From<BR>
     * 　@　@　@　@　@　@　@　@　@・対象期間To<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00086<BR>
     * @@roseuid 45A5CE770240
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）「キャンペーン名称」チェック 
        //　@　@・キャンペーン名称 != null の場合は、以下のチェックを行う。 
        if (this.campaignName != null)
        {
            //１-１）キャンペーン名称101バイト以上の場合、『キャンペーン名称桁数エラー』例外をスローする。  
            if (this.campaignName.getBytes().length >= 101)
            {
                log.debug("キャンペーン名称桁数エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "キャンペーン名称桁数エラー。");
            }
        }
        
        //２）「部店コード」チェック 
        //　@　@・部店コード != null の場合は、以下のチェックを行う。 
        if (this.branchCode != null)
        {
            //２-１）部店コードが3桁以外の場合、『部店コード桁数エラー』例外をスローする。 
            if (this.branchCode.length() != 3)
            {
                log.debug("部店コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードのサイズが不正です。");
            }
        }
        
        //３）「顧客コード」チェック 
        //　@　@・顧客コード != null の場合は、以下のチェックを行う。 
        if (this.accountCode != null)
        {
            //３-１）顧客コードが7桁以上の場合、『顧客コード桁数エラー』例外をスローする。  
            if (this.accountCode.length() >= 7)
            {
                log.debug("顧客コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードのサイズが不正です。");
            }    
        }
        
        //４）「扱者コード」チェック 
        //　@　@・扱者コード != null の場合は、以下のチェックを行う。 
        if (this.traderCode != null)
        {
            //４-１）扱者コードが6桁以上の場合、『扱者コード桁数エラー』例外をスローする。 
            if (this.traderCode.length() >= 6)
            {
                log.debug("扱者コード（文字列）の長さが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "扱者コード（文字列）の長さが不正です。");
            }
        }

        //５）「徴収率」チェック 
        //　@　@・徴収率 != null の場合は、以下のチェックを行う。 
        if (this.collectRate != null)
        {
            //５-１）徴収率が 0～100 の整数以外の場合、『徴収率エラー』例外をスローする。
            if (WEB3StringTypeUtility.isInteger(this.collectRate))
            {
                String l_strCollectRate = this.collectRate;
                int l_intCollectRate = Integer.parseInt(l_strCollectRate);
                if (l_intCollectRate > 100 || l_intCollectRate < 0)
                {
                    log.debug("徴収率の値が不正です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "徴収率の値が不正です。");  
                }
            }
            else
            {
                log.debug("徴収率の値が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "徴収率の値が不正です。"); 
            }
        }
        
        //６）「要求ページ番号」チェック 
        //６-１）未入力の場合、 要求ページ番号に”１”をセットする。
        if (this.pageIndex == null)
        {
            this.pageIndex = "1";
        }
        
        //６-２）数字以外の文字が含まれる場合、例外をスローする。 
        if (this.pageIndex != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
            {
                log.debug("要求ページ番号が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "要求ページ番号が数字以外の値です。");  
            }
        }

        //６-２）マイナス値の場合、例外をスローする。
        if (this.pageIndex != null)
        {
            if (WEB3StringTypeUtility.isMinus(this.pageIndex))
            {
                log.debug("要求ページ番号の値が0以下です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "要求ページ番号の値が0以下です。");  
            }
        }

        //７）「ページ内表示行数」チェック 
        //７-１）未入力の場合、例外をスローする。  
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");   
        }
        
        //７-２）数字以外の文字が含まれる場合、例外をスローする。   
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");   
        }
        
        //７-３）マイナス値の場合、例外をスローする。  
        if (WEB3StringTypeUtility.isMinus(this.pageSize))
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");  
        }
        
        //８）「ソートキー」チェック 
        //８-１）ソートキー = null の場合、例外をスローする。 
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ソートキーが未指定です。");  
        }
        
        //８-２）ソートキーの要素数 = 0 の場合、例外をスローする。 
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");  
        }
        
        //８-３）ソートキーの要素数分、下記のチェックを繰り返して行う。 
        //　@　@８-３-１）ソートキー.validate()をコールする。 
        int l_intSortKeysLength = this.sortKeys.length;
        
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();
            
            //　@　@８-３-２）ソートキー.キー項目が下記の項目名以外の場合、例外をスローする。 
            //　@・部店コード 
            //　@・顧客コード 
            //　@・扱者コード 
            //　@・徴収率 
            //　@・対象期間From  
            //　@・対象期間To  
            if (this.sortKeys[i].keyItem != null)
            {
                if (!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.TRADER_CODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.COLLECT_RATE.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.TARGETPERIOD_FROM.equals(this.sortKeys[i].keyItem)
                    && !WEB3AccInfoKeyItemDef.TARGETPERIOD_TO.equals(this.sortKeys[i].keyItem))
                {
                    log.debug("「ソートキー」 = " + this.sortKeys);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName()  + STR_METHOD_NAME,
                        "ソートキーのキー項目の値が存在しないコード値です。");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
