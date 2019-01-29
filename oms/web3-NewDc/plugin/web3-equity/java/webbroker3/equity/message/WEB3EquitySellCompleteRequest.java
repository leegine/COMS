head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付注文完了リクエスト(WEB3EquitySellCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
Revesion History : 2007/12/17 于瀟(中訊) モデルNo.1205
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式売付注文完了リクエスト）。<br>
 * <br>
 * 現物株式売付注文完了要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquitySellCompleteRequest extends WEB3EquityCommonRequest
{

    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellCompleteRequest.class);

    /**
     * （注文ＩＤ）。
     * <br>
     * 確認レスポンスで受信した値をそのまま送信<br>
     */
    public String orderId;

    /**
     * （ＩＤ）。
     * <br>
     * 資産ID<br>
     */
    public String id;

    /**
     * （市場コード）。
     * <br>
     * 1:東京　@2:大阪　@3:名古屋　@6:福岡　@8:札幌　@9:NNM　@10:JASDAQ
     */
    public String marketCode;

    /**
     * （確認時単価）。<br>
     * <br>
     * 確認時の単価
     */
    public String checkPrice;

    /**
     * （確認時発注日）。<br>
     * <br>
     * 確認時発注日
     */
    public Date checkDate;

    /**
     * （暗証番号）。<br>
     * <br>
     * 暗証番号（取引パスワード）入力値
     */
    public String password;

    /**
     * （PTYPE）。
     */
    public static final String PTYPE = "equity_sell_complete";

    /**
     * （serialVersionUID）。
     */
    public static final long serialVersionUID = 200402041600L;

    /**
     * （現物株式売付注文完了リクエスト）。<br>
     * コンストラクタ
     * @@roseuid 406118D501A2
     */
    public WEB3EquitySellCompleteRequest()
    {

    }

    /**
     * レスポンスデータを作成する。
     * @@return WEB3EquityOrder
     * @@roseuid 40602CAC0196
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquitySellCompleteResponse(this);
    }
    
    /**
     * （validate）。<br>
     * <br>
     * 当リクエストデータの整合性チェックを行う。<br>
     * （ただし、当クラス内で完結する簡易チェックのみとする。<br> 
     * <br>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<br>
     * <br>
     * ２）　@IDチェック<br>
     * 　@this.ID＝nullの場合、<br>
     * 　@「IDがnull」の例外をスローする。<br>
     * 　@　@　@class : WEB3BusinessLayerException<br>
     * 　@　@　@tag   : BUSINESS_ERROR_00080<br>
     * <br>
     * ３）　@市場コードチェック<br>
     * 　@３－１）this.市場コード＝nullの場合、<br>
     * 　@　@　@　@　@「市場コードがnull」の例外をスローする。<br>
     * 　@　@　@class : WEB3BusinessLayerException<br>
     * 　@　@　@tag   : BUSINESS_ERROR_00443<br>
     * <br>
     * 　@３－２）this.市場コード≠null、<br>
     * 　@　@　@　@　@かつ下記の値以外の場合、<br>
     * 　@　@　@　@　@「市場コードが未定義の値」の例外をスローする。<br>
     * 　@　@　@　@・１：東京<br>
     * 　@　@　@　@・２：大阪<br>
     * 　@　@　@　@・３：名古屋<br>
     * 　@　@　@　@・６：福岡<br>
     * 　@　@　@　@・８：札幌<br>
     * 　@　@　@　@・９：NNM<br>
     * 　@　@　@　@・１０：JASDAQ<br>
     * 　@　@　@　@・１１：JNX-PTS<br>
     * 　@　@　@class : WEB3BusinessLayerException<br>
     * 　@　@　@tag   : BUSINESS_ERROR_00608<br>
     * <br>
     * ４）　@確認時単価チェック（this.注文ID≠nullの場合、以下のチェックを行う。）<br>
     * 　@this.確認時単価＝nullの場合、<br>
     * 　@「確認時単価がnull」の例外をスローする。<br>
     * 　@　@　@class : WEB3BusinessLayerException<br>
     * 　@　@　@tag   : BUSINESS_ERROR_00206<br>
     * <br>
     * ５）　@確認時発注日チェック（this.注文ID≠nullの場合、以下のチェックを行う。）<br>
     * 　@this.確認時発注日＝nullの場合、<br>
     * 　@「確認時発注日がnull」の例外をスローする。<br>
     * 　@　@　@class : WEB3BusinessLayerException<br>
     * 　@　@　@tag   : BUSINESS_ERROR_00078<br>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        super.validate();
        
        if(this.id == null)
        {
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
        
        if (this.orderId != null)
        {
            if(this.checkPrice == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                    this.getClass().getName() + ".validate()");
            }
            
            if(this.checkDate == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                    this.getClass().getName() + ".validate()");
            }
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
     * ２）　@注文IDチェック<BR>
     * 　@this.注文ID＝nullの場合、<BR>
     * 　@「注文IDがnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00600<BR>
     * <BR>
     * ３）　@市場コードチェック<BR>
     * 　@３－１）this.市場コード＝nullの場合、<BR>
     * 　@　@　@　@　@「反対取引時は市場コード指定は必須です。」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_02257<BR>
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
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00608<BR>
     * <BR>
     * ４）　@確認時単価チェック<BR>
     * 　@this.確認時単価＝nullの場合、<BR>
     * 　@「確認時単価がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00206<BR>
     * <BR>
     * ５）　@確認時発注日チェック<BR>
     * 　@this.確認時発注日＝nullの場合、<BR>
     * 　@「確認時発注日がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00078<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validateAtReverseOrder() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validateAtReverseOrder()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if (this.orderId == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
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
        
        if (this.checkPrice == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        if (this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
