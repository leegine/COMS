head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.55.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformSwElecDeliApplyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座切替・電子交付申込サービスインターフェイス（WEB3AdminInformSwElecDeliApplyService.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 金傑（中訊）新規作成 モデルNo.099
*/
package webbroker3.inform.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者口座切替・電子交付申込サービス) <BR>
 * 管理者口座切替・電子交付申込サービスインターフェイス <BR>
 * @@author 金傑
 * @@version 1.0
 */
public interface WEB3AdminInformSwElecDeliApplyService extends WEB3BusinessService
{
    /**
     * 管理者口座切替・電子交付申込サービス処理を行う。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
