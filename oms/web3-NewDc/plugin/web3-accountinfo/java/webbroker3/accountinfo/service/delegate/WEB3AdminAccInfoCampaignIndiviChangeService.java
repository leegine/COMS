head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.17.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 個別顧客指定変更サービス(WEB3AdminAccInfoCampaignIndiviChangeService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (個別顧客指定変更サービス)<BR>
 * 個別顧客指定変更サービスインタフェイス<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public interface WEB3AdminAccInfoCampaignIndiviChangeService extends WEB3BusinessService 
{
    
    /**
     * 個別顧客指定変更処理を実施する。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 459DB5880094
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
