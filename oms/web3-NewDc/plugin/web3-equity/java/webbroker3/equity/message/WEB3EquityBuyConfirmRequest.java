head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBuyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文確認リクエスト(WEB3EquityBuyConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
Revesion History : 2004/12/08 岡村（SAR）残案件対応 Ｎｏ.０５７＆Ｎｏ.１６８
Revesion History : 2004/12/13 桑原 (SRA) 残案件対応 No.385
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1086
Revesion History : 2007/12/17 于瀟(中訊) モデルNo.1205
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3TradingTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderingConditionDef;



/**
 * （現物株式買付注文確認リクエスト）。<BR>
 * <BR>
 * 現物株式買付注文確認要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityBuyConfirmRequest extends WEB3EquityCommonRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBuyConfirmRequest.class);
        
    /**
     * PTYPE <BR>
     */
    public static final String PTYPE = "equity_buy_confirm";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200402041630L;

    /**
     * （銘柄コード）<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * （市場コード）<BR>
     * 1:東京　@2:大阪　@3:名古屋　@6:福岡　@8:札幌　@9:NNM　@10:JASDAQ<BR>
     */
    public String marketCode;

    /**
     * （口座区分）<BR>
     * 0：一般　@　@1：特定<BR>
     */
    public String taxType;
    
    /**
     * (取引区分)<BR>
     * 1：現物買注文　@99：立会外分売<BR>
     */
    public String tradingType;

    /**
     * (現物株式買付注文確認リクエスト) <BR>
     * コンストラクタ <BR>
     * @@roseuid 406118700183
     */
    public WEB3EquityBuyConfirmRequest()
    {

    }

    /**
     * レスポンスデータを作成する。
     * @@return WEB3EquityOrder
     * @@roseuid 40602AAF0071
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityBuyConfirmResponse(this);
    }
    
    /**
     * （validate）<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す。<BR>
     * <BR>
     * ２）　@銘柄コードチェック<BR>
     * 　@this.銘柄コード＝nullの場合、<BR>
     * 　@「銘柄コードがnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00079<BR>
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
     * 　@　@　@　@・１０：JASDAQ <BR>
     * 　@　@　@　@・１１：JNX-PTS <BR>
     * 　@　@　@　@・９９：優先市場<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00608<BR>
     * <BR>
     * ４）　@口座区分チェック<BR>
     * 　@４－１）this.口座区分＝nullの場合、<BR>
     * 　@　@　@　@　@「口座区分がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00604<BR>
     * <BR>
     * 　@４－２）this.口座区分≠null、<BR>
     * 　@　@　@　@　@かつ下記の値以外の場合、<BR> 
     * 　@　@　@　@　@「口座区分が未定義の値」の例外をスローする。<BR> 
     * 　@　@　@　@・０：一般<BR>
     * 　@　@　@　@・１：特定<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00605<BR>
     * <BR>
     * ５）　@取引区分チェック<BR>
     * ５－１）this.取引区分＝nullの場合、<BR>
     *　@「取引区分がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00601<BR>
     *<BR>
     * ５－２）this.取引区分≠null、<BR>
     *  かつ下記の値以外の場合、<BR>
     * 「取引区分が未定義の値」の例外をスローする。<BR>
     *　@・１：現物買注文 <BR>
     *　@・９９：立会外分売 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00602<BR>
     *<BR>
     * ６）　@立会外分売注文内容チェック <BR>
     * this.取引区分＝"立会外分売"の場合のみ、以下のチェックを行う。<BR>
     *<BR>
     * ６－１）super.注文単価区分≠"指値"の場合、<BR>
     *「立会外分売は指値のみ指定可（成行指定不可）」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01372<BR>
     *<BR>
     * ６－２）super.注文期限区分≠"当日限り"の場合、<BR>
     *「立会外分売は出来るまで注文指定不可」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01373<BR>
     *<BR>
     * ６－３）super.執行条件≠"無条件"の場合、 <BR>
     * 「立会外分売は執行条件指定不可」の例外をスローする。 <BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01374<BR>
     *<BR>
     * ６－４）super.発注条件区分≠"指定なし"の場合、<BR>
     * 「立会外分売は発注条件指定不可」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01375<BR>
     *<BR>
     * ６－５）super.値段条件≠"指定なし"の場合、<BR> 
     * 「立会外分売は値段条件指定不可」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01376<BR>
     *<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);
        
        super.validate();
        
        if(this.productCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
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
            || WEB3MarketCodeDef.PRIORITY_MARKET.equals(this.marketCode)
            || WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + ".validate()");
        }
        
        if(this.taxType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00604,
                this.getClass().getName() + ".validate()");
        }
        
        if(!(WEB3TaxTypeDef.NORMAL.equals(this.taxType)
            || WEB3TaxTypeDef.SPECIAL.equals(this.taxType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                this.getClass().getName() + ".validate()");
        }
		//５）　@取引区分チェック<BR>
		//５－１）this.取引区分＝nullの場合、<BR>
		//　@「取引区分がnull」の例外をスローする。<BR>
		//　@　@　@class : WEB3BusinessLayerException<BR>
		// 　@　@　@tag   : BUSINESS_ERROR_00601<BR>
		
		log.debug("取引区分チェック");
		if(this.tradingType == null)
		{
			//例外
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00601,
			this.getClass().getName() + "validate");
		}
				
		// ５－２）this.取引区分≠null、<BR>
		// かつ下記の値以外の場合、<BR>
		//「取引区分が未定義の値」の例外をスローする。<BR>
		// ・１：現物買注文 <BR>
		// ・９９：立会外分売 <BR>
		//　@　@　@class : WEB3BusinessLayerException<BR>
		// 　@　@　@tag   : BUSINESS_ERROR_00601<BR>
		
		if(this.tradingType != null)
		{
			if (!WEB3TradingTypeDef.BUY_ORDER.equals(this.tradingType)
				&& !WEB3TradingTypeDef.PRESENCE_ORDER.equals(this.tradingType))
			{
					//例外
					throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00602,
					this.getClass().getName() + "validate");
			}	
		}
		

		//６）　@立会外分売注文内容チェック <BR>
		// this.取引区分＝"立会外分売"の場合のみ、以下のチェックを行う。<BR>
		log.debug("取引区分チェック");
		if(WEB3TradingTypeDef.PRESENCE_ORDER.equals(this.tradingType))
		{
			// ６－１）super.注文単価区分≠"指値"の場合、<BR>
			//「立会外分売は指値のみ指定可（成行指定不可）」の例外をスローする。<BR>
			//　@　@　@class : WEB3BusinessLayerException<BR>
			// 　@　@　@tag   : BUSINESS_ERROR_01372<BR>
			if(!WEB3OrderPriceDivDef.LIMIT_PRICE.equals(super.orderPriceDiv))
			{
				//例外
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01372,
				this.getClass().getName() + "validate");
			}
			
			// ６－２）super.注文期限区分≠"当日限り"の場合、<BR>
			//「立会外分売は出来るまで注文指定不可」の例外をスローする。<BR>
			//　@　@　@class : WEB3BusinessLayerException<BR>
			// 　@　@　@tag   : BUSINESS_ERROR_01373<BR>
			if(!WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(super.expirationDateType))
			{
				//例外
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01373,
				this.getClass().getName() + "validate");
			}
			
			//６－３）super.執行条件≠"無条件"の場合、 <BR>
			//「立会外分売は執行条件指定不可」の例外をスローする。 <BR>
			//　@　@　@class : WEB3BusinessLayerException<BR>
			// 　@　@　@tag   : BUSINESS_ERROR_01374<BR>
			if(!WEB3ExecutionConditionDef.NO_CONDITION.equals(super.execCondType))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01374,
				this.getClass().getName() + "validate");
			}

			//６－４）super.発注条件区分≠"指定なし"の場合、<BR>
			//「立会外分売は発注条件指定不可」の例外をスローする。<BR>
			//　@　@　@class : WEB3BusinessLayerException<BR>
			// 　@　@　@tag   : BUSINESS_ERROR_01375<BR>
			if(!WEB3OrderingConditionDef.DEFAULT.equals(super.orderCondType))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01375,
				this.getClass().getName() + "validate");
			}

			//６－５）super.値段条件≠"指定なし"の場合、<BR> 
			//「立会外分売は値段条件指定不可」の例外をスローする。<BR>
			//　@　@　@class : WEB3BusinessLayerException<BR>
			// 　@　@　@tag   : BUSINESS_ERROR_01376<BR>
			
			if(!WEB3OrderingConditionDef.DEFAULT.equals(super.priceCondType))
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01376,
				this.getClass().getName() + "validate");
			}
			
			
		}       

        log.exiting(STR_METHOD_NAME);
    }
}
@
