head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消サービスインターフェイス(WEB3MutualCancelService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/20 韋念瓊 (中訊) レビュー    
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * 投資信託取消サービスインターフェイス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public interface WEB3MutualCancelService extends WEB3BusinessService 
{
    
    /**
     * 投資信託取消サービス処理を実施する。
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4055749D0202
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
