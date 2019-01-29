head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductInformationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式銘柄情報表示リクエスト(WEB3EquityProductInfomationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 SRA坂上 新規作成
Revesion History : 2007/12/17 金傑(中訊) モデル 1237
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式銘柄情報表示リクエスト）。<BR>
 * <BR>
 * 株式銘柄情報表示要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquityProductInformationRequest extends WEB3GenRequest
{

	/**
	 * ログ出力ユーティリティ。
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3EquityProductInformationRequest.class);
        
	/**
	 * 銘柄コード
	 */
	public String productCode;

	/**
	 * 市場コード
	 */
	public String marketCode;

	/**
	 * 注文受付商品
	 */
	public String orderCommodityCode;	

	/**
	 * serialVersionUID<BR>
	 */
	public static final long serialVersionUID = 200412171100L;

	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE = "equity_product_information";

	/**
	 * @@roseuid XXXXXXXXXXXX
	 */
	public WEB3EquityProductInformationRequest()
	{

	}

	/**
	 * レスポンスデータを作成する。
	 * @@return WEB3EquityProductInfomationResponse
	 * @@roseuid XXXXXXXXXXXX
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3EquityProductInformationResponse(this);
	}

	/**
	 * (validate)<BR>
	 * <BR>
	 * 当クラスの整合性チェックを行う。<BR>
	 * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
	 * <BR>
	 * １）　@銘柄コードチェック<BR>
	 * 　@this.ソートキー＝nullの場合、<BR>
	 * 　@処理を終了する。<BR>
	 * <BR>
	 * ２）　@市場コードチェック<BR>
	 * 　@this.市場コード≠null、<BR>
	 * 　@かつ下記の値以外の場合、<BR>
	 * 　@処理を終了する。<BR>
	 * 　@　@　@・１：東京<BR>
	 * 　@　@　@・２：大阪<BR>
	 * 　@　@　@・３：名古屋<BR>
	 * 　@　@　@・６：福岡<BR>
	 * 　@　@　@・８：札幌<BR>
	 * 　@　@　@・９：NNM<BR>
	 * 　@　@　@・１０：JASDAQ<BR>
     * 　@　@　@・１１：JNX-PTS<BR>
	 * <BR>
 	 * ３）　@注文受付商品チェック<BR>
	 * 　@this.注文受付商品≠null、<BR>
	 * 　@かつ下記の値以外の場合、<BR>
	 * 　@処理を終了する。<BR>
	 * 　@　@　@・０１：株式<BR>
	 * 　@　@　@・０２：株式ミニ投資<BR>
	 * 　@　@　@・０３：信用取引<BR>
	 * <BR>
	 * @@throws WEB3BusinessLayerException
	 * @@roseuid XXXXXXXXXXX
	 */
	public void validate() throws WEB3BusinessLayerException
	{
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);

		// ２）　@市場コードチェック
		if(this.marketCode != null)
		{
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
		}

		// ３）　@注文受付商品チェック
		if(this.orderCommodityCode != null)
		{
			if(!(WEB3OrderAccProductDef.STOCK.equals(this.orderCommodityCode)
				|| WEB3OrderAccProductDef.MINI_STOCK.equals(this.orderCommodityCode)
				|| WEB3OrderAccProductDef.MARGIN.equals(this.orderCommodityCode)))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01699,
					this.getClass().getName() + ".validate()");
					
					
			}
		}
		

		log.exiting(STR_METHOD_NAME);
	}
}
@
