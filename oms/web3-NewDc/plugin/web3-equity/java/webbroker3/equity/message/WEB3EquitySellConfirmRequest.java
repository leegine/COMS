head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付注文確認リクエスト(WEB3EquitySellConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
Revesion History : 2007/12/17 于瀟(中訊) モデルNo.1205
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式売付注文確認リクエスト）。<BR>
 * <BR>
 * 現物株式売付注文確認要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquitySellConfirmRequest extends WEB3EquityCommonRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellConfirmRequest.class);

    /**
     * (ID)<BR>
     * 資産ID<BR>
     */
    public String id;

    /**
     * (市場コード)<BR>
     * 1:東京　@2:大阪　@3:名古屋　@6:福岡　@8:札幌　@9:NNM　@10:JASDAQ<BR>
     */
    public String marketCode;

    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "equity_sell_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200402041611L;

    /**
     * (現物株式売付注文確認リクエスト) <BR>
     * コンストラクタ <BR>
     * @@roseuid 406118BE024E
     */
    public WEB3EquitySellConfirmRequest()
    {

    }

    /**
     * レスポンスデータを作成する。
     * @@return WEB3EquityOrder
     * @@roseuid 40602C5D0204
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquitySellConfirmResponse(this);
    }

    /**
     * (validate)<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２）　@IDチェック<BR>
     * 　@this.ID＝nullの場合、<BR>
     * 　@「IDがnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00080<BR>
     * <BR>
     * ３）　@市場コードチェック<BR>
     * 　@３－１）this.市場コード＝nullの場合、<BR>
     * 　@　@　@　@　@「市場コードがnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00443<BR>
     * <BR>
     * 　@３－２）this.市場コード≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・１：東京<BR>
     * 　@　@　@　@・２：大阪<BR>
     * 　@　@　@　@・３：名古屋<BR>
     * 　@　@　@　@・６：福岡<BR>
     * 　@　@　@　@・８：札幌<BR>
     * 　@　@　@　@・９：NNM<BR>
     * 　@　@　@　@・１０：JASDAQ<BR>
     * 　@　@　@　@・１１：JNX-PTS<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00608<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        super.validate();
        
        if(this.id == null) {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + ".validate()");
        }
        
        if(this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + ".validate()");
        }
        
        if(!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
            || WEB3MarketCodeDef.NNM.equals(this.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT反対取引)<BR>
     * 反対取引指定時の、当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （連続注文用のメソッド）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２）　@市場コードチェック<BR>
     * 　@２－１）this.市場コード＝nullの場合、<BR>
     * 　@　@　@　@　@「反対取引時は市場コード指定は必須です。」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02257<BR>
     * <BR>
     * 　@２－２）this.市場コード≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・１：東京<BR>
     * 　@　@　@　@・２：大阪<BR>
     * 　@　@　@　@・３：名古屋<BR>
     * 　@　@　@　@・６：福岡<BR>
     * 　@　@　@　@・８：札幌<BR>
     * 　@　@　@　@・９：NNM<BR>
     * 　@　@　@　@・１０：JASDAQ<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00608<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validateAtReverseOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if (this.marketCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02257,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
           || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
           || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
           || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
           || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
           || WEB3MarketCodeDef.NNM.equals(this.marketCode)
           || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
