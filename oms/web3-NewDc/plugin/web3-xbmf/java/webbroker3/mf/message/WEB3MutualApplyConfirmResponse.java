head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信募集注文確認レスポンスクラス(WEB3MutualApplyConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/26 黄建 (中訊) 新規作成
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 投信募集注文確認レスポンスクラス <BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0   
 */

public class WEB3MutualApplyConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_apply_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200509261532L;
    
    /**
     * (概算受渡代金通貨コード)<BR>  
     *  概算受渡代金通貨コード <BR>  
     *  A0:US$　@A1:C$　@A2:A$　@A3:HK$　@A4:S$ <BR>
     *  A5:NZ$　@D0:￡　@D1:Irish ￡　@F0:Fr <BR>
     *  F1:SFr　@I0:DM　@J0:G　@K0:Lit　@L0:AS <BR>
     *  M0:DKr　@M1:NKr　@M2:SKr　@N0:Pts　@T0:円<BR>
     *  T3:FIM　@U1:Bath　@Z3:ECU　@Z4:EUR <BR> 
     */
    public String estimatedPriceCurrencyCode;
    
    /**
     * (概算受渡代金)<BR>
     *  概算受渡代金<BR>
     */
    public String estimatedPrice;
    
    /**
     * (概算売買口数)<BR>
     *  概算売買口数<BR>
     */
    public String estimatedQty;
    
    /**
     * (注文ID)<BR>
     *  注文ID<BR>
     */
    public String orderId;
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
