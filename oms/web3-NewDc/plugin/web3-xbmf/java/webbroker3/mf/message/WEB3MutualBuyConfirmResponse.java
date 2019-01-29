head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付注文確認レスポンスクラス(WEB3MutualBuyConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 周勇 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
                   2004/12/07 于美麗 (中訊) 残対応
*/
package webbroker3.mf.message;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投資信託買付注文確認レスポンスクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_confirm";
    
    /**
     * 概算受渡代金通貨コード<BR>
     * <BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR
     */
    public String estimatedPriceCurrencyCode;
    
    /**
     * 概算受渡代金
     */
    public String estimatedPrice;
    
    /**
     * 概算売買口数
     */
    public String estimatedQty;
    
    /**
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * (投信買付注文確認レスポンス)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A883B80101
     */
    public WEB3MutualBuyConfirmResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualBuyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
