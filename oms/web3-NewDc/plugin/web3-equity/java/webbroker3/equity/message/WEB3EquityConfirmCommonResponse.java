head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityConfirmCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式確認共通レスポンス(WEB3EquityConfirmCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 李雲峰 (中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式確認共通レスポンス）。<br>
 * <br>
 * 現物株式確認共通応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityConfirmCommonResponse extends WEB3GenResponse
{
    /**
     * 確認時発注日<BR>
     */
    public Date checkDate;

    /**
     * 概算受渡代金<BR>
     */
    public String estimatedPrice;

    /**
     * (取引終了警告市場コード一覧)<BR>
     * <BR>
     * 取引終了警告文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;

    /**
     * SerialVersionUID<BR>
     */
    public static  final long serialVersionUID = 20040520001L;

    /**
     * PTYPE<BR>
     */
    public static  final String PTYPE = "equity_common";

    /**
     * @@roseuid 409EFF6B02DC
     */
    public WEB3EquityConfirmCommonResponse()
    {

    }
    public WEB3EquityConfirmCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    
}
@
