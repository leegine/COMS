head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報案内メール配信指示応答(WEB3AdminAccInfoMailDistributionResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 彭巍 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報案内メール配信指示応答)<BR>
 *  管理者お客様情報案内メール配信指示応答<BR>
 */
public class WEB3AdminAccInfoMailDistributionResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailDistribution";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412131129L;

    /**
     * (配信指示フラグ)<BR>
     * 配信指示フラグ<BR>
     */
    public boolean requestFlag;

    /**
     * (送信メール区分)<BR>
     * 送信メール区分<BR>
     */
    public String sendMailDiv;

    /**
     * (識別ID)<BR>
     *  識別ID<BR>
     */
    public String discernId;

    /**
     * (案内メール配信指示情報)<BR>
     * 案内メール配信指示情報<BR>
     */
    public WEB3AccInfoMailDistributionInfo mailDistributionInfo;

    /**
     * @@roseuid 418F38570138
     */
    public WEB3AdminAccInfoMailDistributionResponse ()
    {

    }

    /**
     * (管理者お客様情報案内メール配信指示応答)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse
     * @@roseuid 4136B90D03C8
     */
    public WEB3AdminAccInfoMailDistributionResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}


@
