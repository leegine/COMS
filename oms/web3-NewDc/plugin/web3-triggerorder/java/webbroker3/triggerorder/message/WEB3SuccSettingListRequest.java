head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.44.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccSettingListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続設定一覧リクエスト(WEB3SuccSettingListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 呉艶飛(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.triggerorder.define.WEB3ToSuccKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (連続設定一覧リクエスト)<BR>
 * 連続設定一覧リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3SuccSettingListRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccSettingListRequest.class);
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_settingList";
   
    /**
     * (商品区分一覧)<BR>
     * 以下の商品区分の配列<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション<BR>
     */
    public String[] commodityTypeList;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;
    
    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3SuccSortKey[] sortKeys;
    
    /**
     * (要求ページ番号)<BR>
     * 表示させたいページ位置を指定　@<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 434896030290
     */
    public WEB3SuccSettingListRequest() 
    {
     
    }
    
    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@商品区分一覧チェック<BR>
     * 　@１－１）　@this.商品区分一覧が未入力の場合、<BR>
     * 　@　@「商品区分一覧が未入力」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_01462<BR>
     * <BR>
     * 　@１－２）　@this.商品区分一覧の要素中に以下の値以外が<BR>
     * 　@　@含まれる場合、「未定義の商品区分が存在」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_01068<BR>
     * 　@　@　@"現物株式"<BR>
     * 　@　@　@"信用取引"<BR>
     * 　@　@　@"先物"<BR>
     * 　@　@　@"オプション"<BR>
     * <BR>
     * ２）　@ソートキーチェック<BR>
     * 　@２－１）　@this.ソートキーが未入力の場合、<BR>
     * 　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@２－２）　@this.ソートキー[0].キー項目 != 連続注文明細.商品区分<BR>
     * 　@　@の場合、「第一ソートキーは商品区分のみ指定可」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_02241<BR>
     * <BR>
     * 　@２－３）　@this.ソートキーの要素数分、以下の処理を繰り返す。<BR>
     * 　@　@２－３－１）　@ソートキー.validate()をコールする。<BR>
     * <BR>
     * 　@　@２－３－２）　@ソートキー.キー項目に下記の項目以外が<BR>
     * 　@　@　@設定されていたら、<BR>
     * 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_00086<BR>
     * 　@　@　@　@・連続注文明細.商品区分<BR>
     * 　@　@　@　@・連続注文明細.銘柄コード<BR>
     * 　@　@　@　@・連続注文明細.市場コード<BR>
     * 　@　@　@　@・連続注文明細.口座区分<BR>
     * 　@　@　@　@・連続注文明細.取引区分<BR>
     * 　@　@　@　@・連続注文明細.弁済区分<BR>
     * 　@　@　@　@・連続注文明細.値段条件<BR>
     * 　@　@　@　@・連続注文明細.執行条件<BR>
     * 　@　@　@　@・連続注文明細.発注条件区分<BR>
     * 　@　@　@　@・連続注文明細.注文時間<BR>
     * 　@　@　@　@・連続注文明細.発注日<BR>
     * 　@　@　@　@・連続注文明細.注文有効期限<BR>
     * <BR>
     * ３）　@要求ページ番号チェック<BR>
     * 　@３－１）this.要求ページ番号が未入力であった場合、<BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@３－２）this.要求ページ番号が数字以外の値であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@３－３）this.要求ページ番号 <= 0であった場合、<BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ４）ページ内表示行数チェック<BR>
     * 　@４－１）this.ページ内表示行数が未入力であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@４－２）this.ページ内表示行数が数字以外の値であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_00092<BR>
     * 　@<BR>
     * 　@４－３）this.ページ内表示行数 <= 0であった場合、<BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     *  　@    class: WEB3BusinessLayerException <BR>
     *  　@    tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 431D20750087
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@商品区分一覧チェック
        // 　@１－１）　@this.商品区分一覧が未入力の場合、
        // 　@　@「商品区分一覧が未入力」の例外をスローする。
        if (this.commodityTypeList == null || this.commodityTypeList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01462,
                getClass().getName() + STR_METHOD_NAME,
                " 商品区分一覧が未指定です。");
        }
        //　@１－２）　@this.商品区分一覧の要素中に以下の値以外が
        // 　@　@含まれる場合、「未定義の商品区分が存在」の例外をスローする。
        for (int i = 0; i < this.commodityTypeList.length; i++)
        {
            String l_strCommodityType = this.commodityTypeList[i];
            
            if (!WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType)
                && !WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType)
                && !WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType)
                && !WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    getClass().getName() + STR_METHOD_NAME,
                    "商品区分が存在しないコード値です。");
            }
        }
        //２）　@ソートキーチェック
        // 　@２－１）　@this.ソートキーが未入力の場合、
        // 　@　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null || this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + STR_METHOD_NAME,
                " ソートキーが未指定です。");
        }
        
        //２－２）　@this.ソートキー[0].キー項目 != 連続注文明細.商品区分
        //の場合、「第一ソートキーは商品区分のみ指定可」の例外をスローする。
        if (!WEB3ToSuccKeyItemDef.COMMODITY_TYPE.equals(this.sortKeys[0].keyItem))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02241,
                getClass().getName() + STR_METHOD_NAME,
                "第一ソートキーは商品区分のみ指定可。");
        }
        
        //２－３）　@this.ソートキーの要素数分、以下の処理を繰り返す。        
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            //２－３－１）　@ソートキー.validate()をコールする。
            this.sortKeys[i].validate();
            //２－３－２）　@ソートキー.キー項目に下記の項目以外が
            // 　@　@　@設定されていたら、
            // 　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。
            if (!WEB3ToSuccKeyItemDef.COMMODITY_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.TAX_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.TRADING_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.REPAYMENT_DIV.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.PRICE_COND_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.ORDER_COND_TYPE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.ORDER_DATE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.ORDER_BIZ_DATE.equals(this.sortKeys[i].keyItem)
                && !WEB3ToSuccKeyItemDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + STR_METHOD_NAME,
                    " ソートキーのキー項目の値が存在しないコード値です。");
            }
            
        }
        
        //３）　@要求ページ番号チェック
        //　@３－１）this.要求ページ番号が未入力であった場合、
        // 「要求ページ番号がnull」の例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        //３－２）this.要求ページ番号が数字以外の値であった場合、
        //「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        //３－３）this.要求ページ番号 <= 0であった場合、
        //「要求ページ番号が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。" + this.pageIndex);
        }
        
        //４）ページ内表示行数チェック
        //４－１）this.ページ内表示行数が未入力であった場合、
        //「ページ内表示行数がnull」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。" + this.pageSize);
        }
        //４－２）this.ページ内表示行数が数字以外の値であった場合、
        //「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。" + this.pageSize);
        }
        //４－３）this.ページ内表示行数 <= 0であった場合、
        //「ページ内表示行数が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。" + this.pageSize);
        }
        
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccSettingListResponse(this);
    }
}
@
