head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAioOrderUnitTableUpdateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文単位テーブル注文状態更新サービス(WEB3AdminDirSecAioOrderUnitTableUpdateService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/19  徐大方 (中訊) 新規作成
*/

package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * (管理者注文単位テーブル注文状態更新サービス)<BR>
 * 管理者注文単位テーブル注文状態更新サービスインターフェイス。<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public interface WEB3AdminDirSecAioOrderUnitTableUpdateService extends WEB3BusinessService
{

	/**
	 * 管理者・注文単位テーブル注文状態更新処理を開始する。<BR>
	 * 
	 * @@param l_request - (リクエストデータ)<BR>
	 * リクエストデータ。<BR>
	 * @@return WEB3GenResponse
	 * @@throws WEB3BaseException
	 * @@roseuid 444E02BE00BC
	 */
	public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
