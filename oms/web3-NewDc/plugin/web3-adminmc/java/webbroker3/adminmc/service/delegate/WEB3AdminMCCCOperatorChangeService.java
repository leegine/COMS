head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.33.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御CCオペレータ変更サービス(WEB3AdminMCCCOperatorChangeService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/29 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate;

import webbroker3.common.service.delegate.WEB3BusinessService;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;


/**
 * (管理者メニュー制御CCオペレータ変更サービス)<BR>
 * 管理者メニュー制御CCオペレータ変更サービスインタフェイス<BR>
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public interface WEB3AdminMCCCOperatorChangeService extends WEB3BusinessService 
{
    
    /**
     * CCオペレータ変更処理を実施する。<BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 417F82D7035A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
