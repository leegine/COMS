head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報案内メール配信指示要求(WEB3AdminAccInfoMailDistributionRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 彭巍 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報案内メール配信指示要求)<BR>
 * 管理者お客様情報案内メール配信指示要求<BR>
 */
public class WEB3AdminAccInfoMailDistributionRequest extends WEB3GenRequest
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
     * @@roseuid 418F385C0280
     */
    public WEB3AdminAccInfoMailDistributionRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailDistributionResponse(this);
    }
}

@
