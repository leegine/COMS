head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioSLProductChangeService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄変更サービス(WEB3AdminAioSLProductChangeService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 トウ鋒鋼(中訊) 新規作成 仕様変更モデル760
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (担保銘柄変更サービス)<BR>
 * 担保銘柄変更サービスインタフェース<BR>
 * <BR>
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public interface WEB3AdminAioSLProductChangeService extends WEB3BusinessService
{

    /**
     * 担保銘柄変更処理を実施する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DF80BD0276
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
