head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySellInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付注文入力要求　@リクエストデータクラス(WEB3EquitySellInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
Revesion History : 2004/12/08 岡村和明(SRA) 残案件対応 Ｎｏ.０５７
Revesion History : 2007/12/17 于瀟(中訊) モデルNo.1210
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式売付注文入力リクエスト）。<BR>
 * <BR>
 * 現物株式売付注文入力要求　@リクエストデータクラス
 * @@version 1.0
 */
public class WEB3EquitySellInputRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquitySellInputRequest.class);

    /**
     * (ID)<BR>
     * 保有資産ID<BR>
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
    public static final String PTYPE = "equity_order_sellinput";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200404301200L;

    /**
     * コンストラクタ<BR>
     * @@roseuid 406118E702BB
     */
    public WEB3EquitySellInputRequest()
    {

    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.equity.message.WEB3EquitySellInputResponse
     * @@roseuid 40602C24036C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquitySellInputResponse(this);
    }
    
    /**
     * (validate)<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。 
     * （ただし、当クラス内で完結する簡易チェックのみとする。） 
     * 
     * １）　@IDチェック 
     * 　@this.ID＝nullの場合、 <BR> 
     * 　@「IDがnull」の例外をスローする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00080<BR>
     * 
     * ２）　@市場コードチェック 
     * 　@this.市場コード≠null、 
     * 　@かつ下記の値以外の場合、 
     * 　@「市場コードが未定義の値」の例外をスローする。 
     * 　@　@　@・１：東京 
     * 　@　@　@・２：大阪 
     * 　@　@　@・３：名古屋 
     * 　@　@　@・６：福岡 
     * 　@　@　@・８：札幌 
     * 　@　@　@・９：NNM 
     * 　@　@　@・１０：JASDAQ
     * 　@　@　@・１１：JNX-PTS
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_00608<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + ".validate()");
        }
        
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

        log.exiting(STR_METHOD_NAME);
    }
}
@
