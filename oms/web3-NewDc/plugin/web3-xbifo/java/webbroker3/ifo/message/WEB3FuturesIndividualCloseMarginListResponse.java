head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesIndividualCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物個別返済一覧画面表示レスポンス(WEB3FuturesIndividualCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (株価指数先物個別返済一覧画面表示レスポンス)<BR>
 * <BR>
 * 株価指数先物個別返済一覧画面表示レスポンスクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesIndividualCloseMarginListResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_individualCloseMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191453L;

    /**
     * (銘柄名)<BR>
     */
    public String futProductName;

    /**
     * (建区分)<BR>
     * 1：買建　@2：売建
     */
    public String contractType;

    /**
     * (取引市場)<BR>
     * 1：東京　@2：大阪
     */
    public String marketCode;

    /**
     * (現在値)<BR>
     */
    public String currentPrice;

    /**
     * (建玉明細)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;

    /**
     * (取引終了警告文言)<BR>
     * 閉局間近の市場があれば、そのコードを格納
     */
    public String[] messageSuspension;

    /**
     * @@roseuid 40F7AE11001F
     */
    public WEB3FuturesIndividualCloseMarginListResponse()
    {

    }
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesIndividualCloseMarginListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
