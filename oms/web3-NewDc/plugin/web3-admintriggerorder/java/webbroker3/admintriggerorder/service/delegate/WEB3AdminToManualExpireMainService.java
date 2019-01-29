head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualExpireMainService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効メインサービス(WEB3AdminToManualExpireMainService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (トリガー注文管理者・手動失効メインサービス)<BR>
 * （WEB3AdminToManualExpireMainService）<BR>
 * トリガー注文管理者・手動失効メインサービスインターフェイス<BR>
 * （非同期処理を行う為のエントリークラス）<BR>
 * （トランザクション属性：TX_CREATE_NEW）<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public interface WEB3AdminToManualExpireMainService extends WEB3BackBusinessService 
{
    
    /**
     * （非同期）手動失効処理を起動する。<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 440E805A0259
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
