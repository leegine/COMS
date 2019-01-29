head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報案内メール配信指示取消確認応答(WEB3AdminAccInfoMailDistributionCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 彭巍 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報案内メール配信指示取消確認応答)<BR>
 *  管理者お客様情報案内メール配信指示取消確認応答<BR>
 */
public class WEB3AdminAccInfoMailDistributionCancelConfirmResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailDistributionCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412131129L;

    /**
     * @@roseuid 418F38570138
     */
    public WEB3AdminAccInfoMailDistributionCancelConfirmResponse ()
    {

    }

    /**
     * (管理者お客様情報案内メール配信指示取消確認応答)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse
     * @@roseuid 4136B90D03C8
     */
    public WEB3AdminAccInfoMailDistributionCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}


@
