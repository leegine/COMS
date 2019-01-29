head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallGenerationResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 不足金発生状況画面表示レスポンス(WEB3TPShortfallGenerationResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/10 劉剣（中訊）新規作成 モデルNo.312
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (不足金発生状況画面表示レスポンス) <BR>
 * 不足金発生状況画面表示レスポンスクラス。<BR>
 *
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3TPShortfallGenerationResponse extends WEB3GenResponse
{

    /**
     * (PTYPE) <BR>
     */
    public static final String PTYPE = "shortfall_generation";

    /**
     * (SerialVersionUID) <BR>
     */
    public static final long serialVersionUID = 200810101022L;

    /**
     * (不足金発生状況)<BR>
     * 不足金発生状況<BR>
     * <BR>
     * 0 : 不足金未発生<BR>
     * 1 : 不足金発生<現物顧客><BR>
     * 2 : 不足金発生<信用顧客><BR>
     */
    public String shortfallGenerationStateDiv;

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * 保証金自動振替後判定フラグ<BR>
     * <BR>
     * false : 保証金自動振替前<BR>
     * true : 保証金自動振替後<BR>
     */
    public boolean autoTransferAfterJudgeFlag;

    /**
     * (入金遅延発生フラグ)<BR>
     * 入金遅延発生フラグ<BR>
     * <BR>
     * false : 入金遅延無し<BR>
     * true : 入金遅延有り<BR>
     */
    public boolean payDelayGenerationFlag;

    /**
     * (不足金発生情報)<BR>
     * 不足金発生情報<BR>
     */
    public WEB3TPShortfallGenerationInfo shortfallGenerationInfo;

    /**
     * (コンストラクタ) <BR>
     */
    public WEB3TPShortfallGenerationResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3TPShortfallGenerationResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
