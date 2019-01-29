head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.09.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ
                       (WEB3AdminAccInfoCampaignAccOpenListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 孟亜南 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料割引ｷｬﾝﾍﾟｰﾝ口座開設条件一覧ﾘｸｴｽﾄ<BR>
 * @@author 孟亜南
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenListRequest extends WEB3GenRequest 
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenListRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_campaignAccOpenList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701312035L;
    
    /**
     * (要求ページ番号)<BR>
     * キャンペーン名称<BR>
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
     * キャンペーン検索項目
     */
    public WEB3AccInfoCampaignSearchCondition campaignSearchItem;
    
    /**
     * @@roseuid 45C08760019B
     */
    public WEB3AdminAccInfoCampaignAccOpenListRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCampaignAccOpenListResponse(this);
    }
        
    /**
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １） キャンペーン名称のチェック<BR>
     *   １-１） キャンペーン名称101バイト以上の場合、『キャンペーン名称桁数エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02709<BR>
     * <BR>
     * ２） 徴収率のチェック<BR>
     *   ２-１） 徴収率が 0 ～ 100 の整数以外の場合、『徴収率エラー』例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02082<BR>
     * <BR>
     * ３）　@要求ページ番号チェック <BR>
     * 　@３－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00090<BR>
     * 　@３－３）　@マイナス値の場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00616<BR>
     * <BR>
     * ４）　@ページ内表示行数チェック <BR>
     * 　@４－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02224<BR>
     * 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00092<BR>
     * 　@４－３）　@マイナス値の場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00617<BR>
     * <BR>
     * ５）　@ソートキーのチェック  <BR>
     * 　@５－１）　@ソートキーが未入力lの場合、例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00231<BR>
     * 　@５－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00232<BR>
     * 　@５－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。  <BR>
     * 　@　@　@５－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@５－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。<BR>
     * 　@　@　@　@　@　@　@・徴収率<BR>
     * 　@　@　@　@　@　@　@・部店コード<BR>
     * 　@　@　@　@　@　@　@・扱者コード<BR>
     * 　@　@　@　@　@　@　@・口座開設日From<BR>
     * 　@　@　@　@　@　@　@・口座開設日To<BR>
     * 　@　@　@　@　@　@　@・登録日<BR>
     * 　@　@　@　@　@　@　@・更新日<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00086<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 45A7568B034D
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);
        
        //１） キャンペーン名称のチェック
        //    １-１） キャンペーン名称101バイト以上の場合、『キャンペーン名称桁数エラー』例外をスローする。
        if (this.campaignSearchItem.campaignName != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.campaignSearchItem.campaignName) >= 101)
            {
                log.debug("キャンペーン名称桁数エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02709,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " キャンペーン名称 = " + this.campaignSearchItem.campaignName);
            }
        }
        
        //２） 徴収率のチェック
        //  ２-１） 徴収率が 0 ～ 100 の整数以外の場合、『徴収率エラー』例外をスローする。
        if (this.campaignSearchItem.collectRate != null)
        {
            if (WEB3StringTypeUtility.isInteger(this.campaignSearchItem.collectRate))
            {
                if (Integer.parseInt(this.campaignSearchItem.collectRate) > 100 
                        || Integer.parseInt(this.campaignSearchItem.collectRate) < 0)
                {
                    log.debug("徴収率の値が不正です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " 徴収率 = " + this.campaignSearchItem.collectRate);
                }
            }
            else
            {
                log.debug("徴収率の値が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02082,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " 徴収率 = " + this.campaignSearchItem.collectRate);
            }
        }
        
        //３）　@要求ページ番号チェック 
        //  ３－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        
        //  ３－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                " 要求ページ番号 = " + this.pageIndex);
        }
        
        //  ３－３）　@マイナス値の場合、例外をスローする。
        if (WEB3StringTypeUtility.isMinus(this.pageIndex))
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                " 要求ページ番号 = " + this.pageIndex);
        }
        
        //４）　@ページ内表示行数チェック 
        //  ４－１）　@未入力の場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数 = " + this.pageSize);
        }
        
        //  ４－２）　@数字以外の文字が含まれる場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数 = " + this.pageSize);
        }
        
        //  ４－３）　@マイナス値の場合、例外をスローする。
        if (WEB3StringTypeUtility.isMinus(this.pageSize))
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                " ページ内表示行数 = " + this.pageSize);
        }
        
        //５）　@ソートキーのチェック  
        //  ５－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                " ソートキー = " + this.sortKeys);
        }
        
        //  ５－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                " ソートキー = " + this.sortKeys);
        }
        
        //  ５－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。  
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i ++)
        {
            WEB3AccInfoSortKey l_key = sortKeys[i];
            if (l_key != null)
            {
                //  ５－３－１）　@ソートキー.validate()をコールする。 
                l_key.validate();
                
                //  ５－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
                //              ・徴収率                              
                //              ・部店コード
                //              ・扱者コード
                //              ・口座開設日From
                //              ・口座開設日To
                //              ・登録日
                //              ・更新日
                if (!(WEB3AccInfoKeyItemDef.COLLECT_RATE.equals(l_key.keyItem) 
                        || WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.TRADER_CODE.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE_FROM.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE_TO.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.REGIST_DATE.equals(l_key.keyItem)
                        || WEB3AccInfoKeyItemDef.UPDATED_DATE.equals(l_key.keyItem)))
                {
                    log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ソートキー.キー項目 = " + l_key.keyItem);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
