head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建注文入力リクエスト(WEB3MarginOpenMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
                   2006/12/25 張騰宇 (中訊) モデル 1086
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;

import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引新規建注文入力リクエスト）。<br>
 * <br>
 * 信用取引新規建注文入力リクエストクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputRequest extends WEB3GenRequest 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginOpenMarginInputRequest.class);
    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (銘柄コード)<BR>
     * <BR>
     * 銘柄指定の場合に使用。<BR>
     */
    public String productCode;
    
    /**
     * (市場コード)<BR>
     * <BR>
     * 銘柄指定の場合に使用。<BR>
     */
    public String marketCode;
    
    /**
     * (取引区分)<BR>
     * 3：新規買建注文　@4：新規売建注文 （ただし、null値も許可する）<BR>
     * <BR>
     * null値の場合、売建／買建が確定していない場合を意味する<BR>
     * (OrderTypeEnumにて定義)<BR>
     */
    public String tradingType;
    
    /**
     * @@roseuid 4140477E0074
     */
    public WEB3MarginOpenMarginInputRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@市場指定チェック<BR>
     * 　@this.市場コード≠nullでかつ、下記以外の値の場合、<BR>
     * 　@「市場コードが未定義の値」の例外をスローする。<BR>
     *          ・”1：東京”<BR>
     *          ・”2：大阪” <BR>
     *          ・”3：名古屋” <BR>
     *          ・”6：福岡” <BR>
     *          ・”8：札幌” <BR>
     *          ・”9：NNM” <BR>
     *          ・”10：JASDAQ”<BR>
     *          ・”99：優先市場”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * ２）　@取引区分チェック<BR>
     * 　@２－１）this.取引区分≠nullでかつ、下記以外の値の場合、<BR>
     * 　@　@　@　@「取引区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・”3：新規買建注文”<BR>
     * 　@　@　@　@・”4：新規売建注文”<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00602<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 407E5C560167
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3MarginOpenMarginInputRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("市場指定チェック");
        // １）　@市場指定チェック
        if (this.marketCode != null
                && !WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
                && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                && !WEB3MarketCodeDef.PRIORITY_MARKET.equals(this.marketCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00608,
            this.getClass().getName() + "validate");
        }

        log.debug("取引区分チェック");
        // ２）　@取引区分チェック
        if (this.tradingType != null
                && !WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(this.tradingType)
                && !WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(this.tradingType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00602,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140477E0088
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginOpenMarginInputResponse(this);
    }
}
@
