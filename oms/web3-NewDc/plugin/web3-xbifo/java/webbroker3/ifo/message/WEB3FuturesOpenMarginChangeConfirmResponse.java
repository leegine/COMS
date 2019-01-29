head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正新規建確認レスポンスクラス(WEB3FuturesOpenMarginChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強(中訊) 新規作成
Revesion History : 2007/11/19 孟亞南 仕様変更モデルNo.812
*/
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (株価指数先物訂正新規建確認レスポンス)<BR>
 * 株価指数先物訂正新規建確認レスポンスクラス
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginChangeConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_openMarginChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211148L;

    /**
     * (内約定数量)<BR>
     */
    public String partExecQuantity;

    /**
     * (概算建代金)<BR>
     */
    public String estimatedContractPrice;

    /**
     * (手数料コース)<BR>
     * 02：定額手数料(スタンダード)<BR>
     * 03：約定代金合計<BR>
     * 04：約定回数<BR>
     * 05：一日定額制<BR>
     * 12：定率手数料（ハイパーボックス）※徴収率 = 0%<BR>
     */
    public String commissionCourse;

    /**
     * (手数料)<BR>
     */
    public String commission;

    /**
     * (手数料消費税)<BR>
     */
    public String commissionConsumptionTax;
    
    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (確認時単価)<BR>
     * 画面では非表示。完了リクエストで送信する値。<BR>
     */
    public String checkPrice;
    
    /**
     * (確認時発注日)<BR>
     * 画面では非表示。完了リクエストで送信する値。<BR>
     */
    public Date checkDate;

    /**
     * (注文有効期限)<BR>
     * 注文失効日<BR>
     */
    public Date expirationDate;

    /**
     * @@roseuid 40F7AE12004E
     */
    public WEB3FuturesOpenMarginChangeConfirmResponse() 
    {
     
    }
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesOpenMarginChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
