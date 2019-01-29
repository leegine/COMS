head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.19.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ(WEB3AdminAccInfoMailAddressChangeAccountDownloadService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽインタフェイス<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public interface WEB3AdminAccInfoMailAddressChangeAccountDownloadService extends WEB3BusinessService 
{
    
    /**
     * メールアドレス変更顧客ダウンロード処理を実施する。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F2B100D6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
