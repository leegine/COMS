head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MiniClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 株式ミニ投資クライアントリクエストサービス(WEB3MiniClientRequestService)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/10 岡村和明(SRA) 新規作成
                      2004/12/29 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * （株式ミニ投資クライアントリクエストサービス）。<BR>
 * <BR>
 * 汎用クライアントリクエストサービス(株式ミニ投資用)<BR>
 * <BR>
 * クライアントからのリクエストを実現するサービスの共通スーパークラス。
 * @@author 岡村和明(SRA)
 * @@version 1.0
 */
public class WEB3MiniClientRequestService extends WEB3ClientRequestService
{
    
    public WEB3MiniClientRequestService()
    {
    }
    
    /**
     * （get補助口座）。<BR>
     * <BR>
     * （getSubAccountのオーバーロード）<BR>
     * <BR>
     * ログインセキュリティサービスより補助口座を取得する。<BR>
     * <BR>
     * １）　@ログインセキュリティサービスより口座ＩＤを取得する。<BR>
     * <BR>
     * ２）　@アカウントマネージャ.getSubAccount( )にて、<BR>
     * 　@　@　@該当顧客の株式ミニ投資用補助口座オブジェクトを取得する。<BR>
     * <BR>
     * 　@[getSubAccount引数]<BR>
     * 　@SubAccountTypeEnum.株式取引口座
     * @@return 補助口座
     * @@throws WEB3SystemLayerException
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3SystemLayerException
    {
        return (WEB3GentradeSubAccount)super.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
    }
}
@
