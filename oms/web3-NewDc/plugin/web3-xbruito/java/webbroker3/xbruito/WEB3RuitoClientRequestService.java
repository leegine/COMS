head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 汎用クライアントリクエストサービスクラス(WEB3RuitoClientRequestService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 李志強 (中訊) 新規作成
*/

package webbroker3.xbruito;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;

/**
 * 汎用クライアントリクエストサービス（累積投資用）<BR>
 * <BR>
 * クライアントからのリクエストを実現するサービスの共通スーパークラス。<BR>
 */
public class WEB3RuitoClientRequestService extends WEB3ClientRequestService
{
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3RuitoClientRequestService.class);
		
    /**
     * （getSubAccountのオーバーロード）<BR>
     * <BR>
     * ログインセキュリティサービスより補助顧客を取得する。<BR>
     * <BR>
     * １）　@super.get補助口座( )にて、該当顧客の累積投資取引用<BR>
     *       補助口座<BR>
     *       オブジェクトを取得する。<BR>
     * <BR>
     * 　@[get補助口座に渡すパラメタ]<BR>
     * 　@　@SubAccountTypeEnum.株式取引口座<BR>
     * @@return webbroker3.gentrade.WEB3GentradeSubAccount
     * @@roseuid 406133B20377
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "getSubAccount()";
		log.entering(STR_METHOD_NAME);
		log.exiting(STR_METHOD_NAME);
        return (WEB3GentradeSubAccount)super.getSubAccount(
                SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    }

    /**
     * @@param l_request
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40C4688A00BB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        return l_request.createResponse();
    }
}
@
