head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.54.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座切替・電子交付申込サービスインタフェイス (WEB3InformAccSwElecDeliApplyService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 張騰宇 (中訊) 新規作成 仕様変更モデル098
*/
package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (口座切替・電子交付申込サービス)<BR>
 * 口座切替・電子交付申込サービスインタフェイス<BR>
 * 
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3InformAccSwElecDeliApplyService extends WEB3BusinessService
{
    /**
     * 口座切替・電子交付申込サービス処理を行う。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
