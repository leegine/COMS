head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約確認レスポンスクラス(WEB3MutualSellConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 黄建 (中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー
                   2004/12/07 于美麗 (中訊) 残対応 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 投資信託解約確認レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3MutualSellConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408111511L;
    
    /**
     * 解約注文内容警告区分<BR>
     * <BR>
     * null:エラー無し<BR>
     * 1:警告「解約金額拘束率の超過による"全部指定"変更」<BR>
     */
    public String sellWarningType;
    
    /**
     * 概算受渡代金通貨コード<BR>
     * <BR>
     * ブランク:円　@ブランク×２:円<BR>
     * A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$<BR>
     * A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr<BR>
     * F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS<BR>
     * M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     * T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR<BR>
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
     * デフォルトコンストラクタ
     * @@roseuid 40A89E130220
     */
    public WEB3MutualSellConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualSellConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
    
}
@
