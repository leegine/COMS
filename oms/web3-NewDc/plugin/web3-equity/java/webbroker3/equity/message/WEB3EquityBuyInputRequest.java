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
filename	WEB3EquityBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文入力リクエスト(WEB3EquityOrderBuyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/19 欒学峰 (中訊) 新規作成
Revesion History : 2004/12/08 岡村（SAR）残案件対応 Ｎｏ.０５７
Revesion History : 2004/12/13 桑原 (SRA) 残案件対応 No.385
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1086
Revesion History : 2007/12/17 于瀟(中訊) モデルNo.1210
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.define.WEB3TradingTypeDef;

/**
 * （現物株式買付注文入力リクエスト）。<BR>
 * <BR>
 * 現物株式買付注文入力要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityBuyInputRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBuyInputRequest.class);

    /**
     * 銘柄コード <BR>
     * 直接指定の場合に必要<BR>
     */
    public String productCode;

    /**
     * 市場コード <BR>
     * 1:東京　@2:大阪　@3:名古屋　@6:福岡　@8:札幌　@9:NNM　@10:JASDAQ<BR>
     * 直接指定の場合に必要<BR>
     */
    public String marketCode;
    
    /**
     * (取引区分)<BR>
     * 1：現物買注文　@99：立会外分売<BR>
     */
    public String tradingType;

    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "equity_order_buy_input";

    /**
     * シリアルバージョンUID <BR>
     */
    public static final long serialVersionUId = 200405081330L;

    /**
     * コンストラクタ <BR>
     * @@roseuid 406118A90154
     */
    public WEB3EquityBuyInputRequest()
    {

    }

    /**
     * レスポンスデータを作成する。 <BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderBuyInputResponse
     * @@roseuid 40602B2B00BE
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityBuyInputResponse(this);
    }
    
	/**
	 * (validate)<BR>
	 * <BR>
	 * 当リクエストデータの整合性チェックを行う。<BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
	 * <BR>
	 * １）　@市場コードチェック<BR>
	 * 　@　@　@this.市場コード≠null、<BR>
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
     * 　@　@　@　@・９９：優先市場<BR>
	 * 　@　@　@class : WEB3BusinessLayerException<BR>
	 * 　@　@　@tag   : BUSINESS_ERROR_00608<BR>
	 * 
	 * ２）　@取引区分チェック<BR>
　@	 * ２-１）this.取引区分＝nullの場合、<BR>
     *　@　@　@「取引区分がnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00601<BR>
     *<BR>
	 * ２-２）this.取引区分≠null、<BR>
     *　@　@　@かつ下記の値以外の場合、<BR>
     * 　@　@　@「取引区分が未定義の値」の例外をスローする。<BR>
     *　@　@・１：現物買注文<BR>
     *　@　@・９９：立会外分売<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00602<BR>
     *<BR>
	 * ３）　@立会外分売・銘柄コード指定チェック<BR>
     * ３-１）this.取引区分＝”立会外分売”、<BR>
     *　@　@　@かつ this.銘柄コード＝nullの場合は、<BR>
     *　@　@　@「立会外分売で銘柄コード指定なし」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01377<BR>
     *<BR>
	 *
	 * ４）　@立会外分売・市場コード指定チェック<BR>
     * ４-１）this.取引区分＝”立会外分売”、<BR>
     *　@　@　@かつ this.市場コード＝nullの場合は、<BR>
     *　@　@　@「立会外分売で市場コード指定なし」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01378<BR>
     *<BR>
	 * 
	 * @@throws WEB3BusinessLayerException
	 */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);
        
        if(this.marketCode != null)
        {
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
        }
		//２）　@取引区分チェック<BR>
        //２-１）this.取引区分＝nullの場合、<BR>
        //　@　@「取引区分がnull」の例外をスローする。<BR>
		//　@　@　@class : WEB3BusinessLayerException<BR>
		// 　@　@　@tag   : BUSINESS_ERROR_00601<BR>
		
		log.debug("取引区分チェック");
        if(this.tradingType == null)
        {
			//例外
			throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00601,
			this.getClass().getName() + "validate");
        }

		//２-２）this.取引区分≠null、<BR>
		//　@　@かつ下記の値以外の場合、<BR>
		// 　@　@　@「取引区分が未定義の値」の例外をスローする。<BR>
		//　@　@・１：現物買注文<BR>
		//  　@・９９：立会外分売<BR>
		//　@　@　@class : WEB3BusinessLayerException<BR>
		// 　@　@　@tag   : BUSINESS_ERROR_602<BR>
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
		        	        	        	
		
		// ３）　@立会外分売・銘柄コード指定チェック<BR>
		// ３-１）this.取引区分＝”立会外分売”、<BR>
		//　@　@　@かつ this.銘柄コード＝nullの場合は、<BR>
		//　@　@　@「立会外分売で銘柄コード指定なし」の例外をスローする。<BR>
		//　@　@　@class : WEB3BusinessLayerException<BR>
		// 　@　@　@tag   : BUSINESS_ERROR_01377<BR>
		
		log.debug("立会外分売・銘柄コード指定チェック");
		if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(this.tradingType)
			&& this.productCode == null)
			{
				//
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01377,
				this.getClass().getName() + "validate");
			}
		
		// ４）　@立会外分売・市場コード指定チェック<BR>
		// ４-１）this.取引区分＝”立会外分売”、<BR>
		//　@　@　@かつ this.市場コード＝nullの場合は、<BR>
		//　@　@「立会外分売で市場コード指定なし」の例外をスローする。<BR>
		//　@　@　@class : WEB3BusinessLayerException<BR>
		// 　@　@　@tag   : BUSINESS_ERROR_01378<BR>
		
		log.debug("立会外分売・市場コード指定チェック");
		if (WEB3TradingTypeDef.PRESENCE_ORDER.equals(this.tradingType)
			&& this.marketCode == null)
			{
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01378,
				this.getClass().getName() + "validate");				
			}
        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
