head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション新規建注文銘柄選択画面レスポンス(WEB3OptionsProductSelectResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 李頴淵 (中訊) 新規作成
                 : 2006/08/18 郭英 (中訊) モデルNo.536
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (株価指数オプション新規建注文銘柄選択画面レスポンス)<BR>
 * 株価指数オプション新規建注文銘柄選択画面レスポンスクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionsProductSelectResponse extends WEB3GenResponse
{

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406141522L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_productSelect";

    /**
     * 新規建可能額
     */
    public String opTradingPower;

    /**
     * 取引市場一覧<BR>
     * 1：東京　@2：大阪　@3：名古屋<BR>
     */
    public String[] marketList;

    /**
     * 指数種別一覧<BR>
     * 0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225 <BR>
     */
    public String[] targetProductList;

    /**
     * 限月一覧<BR>
     * 各値はYYYYMM形式<BR>
     */
    public String[] delivaryMonthList;

    /**
     * 取引終了警告文言<BR>
     * 閉局間近の市場があれば、そのコードを格納<BR>
     */
    public String[] messageSuspension;
    
    /**
     * デフォルトコンストラクタ
     */
    public WEB3OptionsProductSelectResponse()
    {
        
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3OptionsProductSelectResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
