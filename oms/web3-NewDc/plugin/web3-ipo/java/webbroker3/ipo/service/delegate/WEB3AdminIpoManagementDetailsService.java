head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoManagementDetailsService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄管理・詳細サービス(WEB3AdminIpoManagementDetailsService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 李頴淵 新規作成
*/

package webbroker3.ipo.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;


/**
 * (管理者IPO銘柄管理・詳細サービス)<BR>
 * 管理者IPO銘柄管理・詳細サービスインタフェイス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3AdminIpoManagementDetailsService extends WEB3BusinessService 
{
    
    /**
     * 管理者IPO銘柄管理・詳細処理を実施する。
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C6666E00D7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
