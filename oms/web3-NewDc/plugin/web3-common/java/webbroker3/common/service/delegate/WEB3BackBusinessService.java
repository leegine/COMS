head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BackBusinessService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 業務アプリケーション（下り処理）の業務ロジックのインタフェース(WEB3BackBusinessService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import webbroker3.common.*;
import webbroker3.common.message.*;

/**
 * 業務アプリケーション（下り処理）の業務ロジックのインタフェース。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.kernel.boot.Service
 */
public interface WEB3BackBusinessService extends Service
{
    /**
     * 引数で与えられたリクエストを基に業務処理を行い、処理結果をレスポンスに設定<BR>
     * して返す。<BR>
     *<BR>
     * @@param l_request リクエスト
     * @@return 処理結果が設定されたレスポンス
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
