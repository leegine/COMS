head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailDistributionChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報案内メール配信設定変更完了レスポンス(WEB3AccInfoMailDistributionChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報案内メール配信設定変更完了レスポンス)<BR>
 * お客様情報案内メール配信設定変更完了レスポンス<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoMailDistributionChangeCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mailDistributionChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082157L;

    /**
     * @@roseuid 418F39F40213
     */
    public WEB3AccInfoMailDistributionChangeCompleteResponse()
    {

    }

    /**
     * (お客様情報案内メール配信設定変更完了レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailDistributionChangeCompleteResponse
     * @@roseuid 41368DC00390
     */
    public WEB3AccInfoMailDistributionChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
