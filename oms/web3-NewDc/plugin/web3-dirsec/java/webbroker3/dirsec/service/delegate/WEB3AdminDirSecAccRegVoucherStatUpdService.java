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
filename	WEB3AdminDirSecAccRegVoucherStatUpdService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者顧客情報登録伝票ステータス更新サービス(WEB3AdminDirSecAccRegVoucherStatUpdService.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/12 何文敏 (中訊) 新規作成 仕様変更 モデルNo.098
Revision History    : 2007/06/18 柴双紅 (中訊) 仕様変更 モデルNo.101
*/

package webbroker3.dirsec.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者顧客情報登録伝票ステータス更新サービス)<BR>
 * 管理者顧客情報登録伝票ステータス更新サービスクラス。<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public interface WEB3AdminDirSecAccRegVoucherStatUpdService extends WEB3BusinessService
{
    /**
     * 管理者・顧客情報登録伝票ステータス更新処理を開始する。<BR>
     * <BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4653FC9A0088
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
