head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityErrorReasonCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文エラー理由コード定数定義インタフェイス(WEB3AdminEquityErrorReasonCodeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/28 張騰宇(中訊) 新規作成 仕様変更モデルNo.171
*/
package webbroker3.eqtypeadmin.define;

/**
 * 注文エラー理由コード 定数定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminEquityErrorReasonCodeDef
{
    /**
     * "建株残高不足エラー"
     */
    public final static String OPEN_INTERSET_SHORT_ERROR = "建株残高不足エラー";

    /**
     * "売買停止銘柄エラー"
     */
    public final static String TRADE_STOP_PRODUCT_ERROR = "売買停止銘柄エラー";

    /**
     * "決済期日到来済エラー"
     */
    public final static String SETTLEDAY_CAME_ERROR = "決済期日到来済エラー";

    /**
     * "現引・現渡注文登録済エラー"
     */
    public final static String SWAP_MARGIN_REGISTED_ERROR = "現引・現渡注文登録済エラー";

    /**
     * "その他エラー"
     */
    public final static String OTHRE_ERROR = "その他エラー";
}
@
