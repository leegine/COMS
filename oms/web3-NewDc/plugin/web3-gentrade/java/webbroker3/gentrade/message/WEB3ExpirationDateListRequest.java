head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文有効期限取得リクエスト(WEB3ExpirationDateListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 于瀟(中訊) 新規作成モデル319
*/

package webbroker3.gentrade.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (注文有効期限取得リクエスト)<BR>
 * 注文有効期限取得リクエストクラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ExpirationDateListRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "expiration_date_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802141629L;

    /**
     * (商品区分)<BR>
     * 商品区分（1:現物株式 2:信用取引 3:先物 4:オプション）<BR>
     */
    public String commodityType;

    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;

    /**
     * (指数種別)<BR>
     * 指数種別（原資産銘柄コード）<BR>
     */
    public String targetProductCode;

    /**
     * @@roseuid 47B3E13100DA
     */
    public WEB3ExpirationDateListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@商品区分チェック <BR>
     * 　@１－１）this.商品区分＝nullの場合、 <BR>
     * 　@　@　@　@　@「商品区分がnull」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@BUSINESS_ERROR_02182<BR>
     * <BR>
     * 　@１－２）this.商品区分≠null、 <BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「商品区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・現物株式<BR>
     * 　@　@　@　@・信用取引<BR>
     * 　@　@　@　@・先物<BR>
     * 　@　@　@　@・オプション <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@BUSINESS_ERROR_01068<BR>
     * <BR>
     * ２）　@市場コードチェック <BR>
     * 　@２－１）this.市場コード＝nullの場合、 <BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@BUSINESS_ERROR_00443<BR>
     * <BR>
     * 　@２－２）this.市場コード≠null、 <BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、 <BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・東京 <BR>
     * 　@　@　@　@・大阪 <BR>
     * 　@　@　@　@・名古屋 <BR>
     * 　@　@　@　@・福岡 <BR>
     * 　@　@　@　@・札幌 <BR>
     * 　@　@　@　@・NNM <BR>
     * 　@　@　@　@・JASDAQ <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@BUSINESS_ERROR_00608<BR>
     * <BR>
     * ３）　@指数種別チェック <BR>
     * 　@３－１）this.商品区分＝”現物株式”または”信用取引”の場合、 <BR>
     * 　@　@　@　@　@かつ、this.指数種別≠nullの場合、例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@BUSINESS_ERROR_03018<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A2C1F50227
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //商品区分チェック
        //this.商品区分＝nullの場合
        //「商品区分がnull」の例外をスローする。
        if (this.commodityType == null)
        {
            log.debug("商品区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02182,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品区分が未指定です。");
        }

        //this.商品区分≠null、
        //かつ下記の値以外の場合、
        //「商品区分が未定義の値」の例外をスローする
        //  ・現物株式
        //　@・信用取引
        //　@・先物
        //　@・オプション
        if (this.commodityType != null
            && !(WEB3CommodityDivDef.EQUITY.equals(this.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(this.commodityType)
            || WEB3CommodityDivDef.FUTURE.equals(this.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(this.commodityType)))
        {
            log.debug("商品区分が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品区分が存在しないコード値です。");
        }

        //市場コードチェック
        //this.市場コード＝nullの場合、
        //「市場コードがnull」の例外をスローする
        if (this.marketCode == null)
        {
            log.debug("市場コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "市場コードが未指定です。");
        }

        //this.市場コード≠null、
        //かつ下記の値以外の場合、
        //「市場コードが未定義の値」の例外をスローする。
        //・東京
        //・大阪
        //・名古屋
        //・福岡
        //・札幌
        //・NNM
        //・JASDAQ
        if (this.marketCode != null
            && !(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
            || WEB3MarketCodeDef.NNM.equals(this.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)))
        {
            log.debug("市場コードが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "市場コードが存在しないコード値です。");
        }
        //指数種別チェック
        //this.商品区分＝”現物株式”または”信用取引”の場合、
        //かつ、this.指数種別≠nullの場合、例外をスローする
        if ((WEB3CommodityDivDef.EQUITY.equals(this.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(this.commodityType))
            && this.targetProductCode != null)
        {
            log.debug("商品区分が“現物株式”または”信用取引”の場合、指数種別が指定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "商品区分が“現物株式”または”信用取引”の場合、指数種別が指定不可です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3ExpirationDateListResponse(this);
    }

}
@
