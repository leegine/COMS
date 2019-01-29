head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資取消確認レスポンスクラス(WEB3RuitoCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * 累積投資取消確認レスポンスクラス<BR>
 */
public class WEB3RuitoCancelConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_cancel_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3RuitoCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

    /**
     * 確認時発注日<BR>
     * <BR>
     * 完了リクエストで送信する値を格納する。<BR>
     * （画面では非表示）<BR>
     */
    public Date checkDate;

    /**
     * 売買区分(累投)<BR>
     * 1：買付   2：全部解約　@　@3：金額指定解約　@　@4：口数指定解約<BR>
     */
    public String ruitoDealingType;

    /**
     * 注文数量区分<BR>
     * <BR>
     * 3: 金額　@4:口数<BR>
     */
    public String ruitoOrderQuantityType;

    /**
     * 注文数量<BR>
     */
    public String ruitoOrderQuantity;

    /**
     * 累積投資のファ@ンド名<BR>
     */
    public String ruitoProductName;

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922C480186
     */
    public WEB3RuitoCancelConfirmResponse()
    {

    }
}
@
