head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdditionalGenerationResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追証発生画面表示レスポンス(WEB3TPAdditionalGenerationResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/10 劉剣（中訊）新規作成 モデルNo.312
*/
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (追証発生画面表示レスポンス) <BR>
 * 追証発生画面表示レスポンスクラス。<BR>
 *
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3TPAdditionalGenerationResponse extends WEB3GenResponse
{

    /**
     * (PTYPE) <BR>
     */
    public static final String PTYPE = "additional_generation";

    /**
     * (SerialVersionUID) <BR>
     */
    public static final long serialVersionUID = 200810101032L;

    /**
     * (追証発生状況)<BR>
     * 追証発生状況<BR>
     * <BR>
     * 0 : 追証未発生<BR>
     * 1 : 第一水準追証発生<BR>
     * 2 : 第二水準追証発生<BR>
     */
    public String additionalGenerationStateDiv;

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * 保証金自動振替後判定フラグ<BR>
     * <BR>
     * false : 保証金自動振替前<BR>
     * true : 保証金自動振替後<BR>
     */
    public boolean autoTransferAfterJudgeFlag;

    /**
     * (追証未入金発生フラグ)<BR>
     * 追証未入金発生フラグ<BR>
     * <BR>
     * false : 追証未入金無し<BR>
     * true : 追証未入金有り<BR>
     */
    public boolean additionalNonPayAmtFlag;

    /**
     * (第一水準追証情報)<BR>
     * 第一水準追証情報<BR>
     */
    public WEB3TPFirstAdditionalInfo firstAdditionalInfo;

    /**
     * (第二水準追証情報)<BR>
     * 第二水準追証情報<BR>
     */
    public WEB3TPSecondAdditionalInfo secondAdditionalInfo;

    /**
     * (コンストラクタ) <BR>
     */
    public WEB3TPAdditionalGenerationResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3TPAdditionalGenerationResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
