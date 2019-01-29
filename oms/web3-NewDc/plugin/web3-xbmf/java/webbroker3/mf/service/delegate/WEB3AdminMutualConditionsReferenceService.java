head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsReferenceService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者投資信託　@銘柄条件登録照会サービス　@インターフェイス(WEB3AdminMutualConditionsReferenceService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 王蘭芬(中訊) 新規作成
                   2004/08/20 黄建 (中訊) レビュー
*/

package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * 管理者投資信託　@銘柄条件登録照会サービス　@インターフェイス<BR>
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3AdminMutualConditionsReferenceService extends WEB3BusinessService 
{
    
    /**
     * 管理者投資信託　@銘柄条件登録照会を実施する。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40E4CDF00029
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
