head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込完了レスポンス(WEB3AccOpenApplyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (口座開設申込完了レスポンス)<BR>
 * 口座開設申込完了レスポンス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenApplyCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_applyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081612L;

    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 41B45E7C02DE
     */
    public WEB3AccOpenApplyCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccOpenApplyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
