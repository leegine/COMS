head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioListService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者入出金一覧サービス(WEB3AdminAioListService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/02 何文敏 (中訊) 新規作成 仕様変更 モデルNO.694
*/

package webbroker3.aio.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者入出金一覧サービス)<BR>
 * 管理者入出金一覧サービスインタフェイス
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public interface WEB3AdminAioListService extends WEB3BusinessService
{
    /**
     * 管理者入出金一覧処理を行う。
     * 
     * @@param l_request - (リクエスト)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
