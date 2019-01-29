head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminLoginHistoryReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン履歴情報(WEB3AdminTraderAdminLoginHistoryReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.005,007
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ログイン履歴情報)<BR>
 * ログイン履歴情報クラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminLoginHistoryReferenceUnit extends Message
{
    /**
     * (ログイン日時)<BR>
     * ログイン日時。<BR>
     */
    public Date loginDate;

    /**
     * (IPアドレス)<BR>
     * IPアドレス。<BR>
     */
    public String ipAddress;

    /**
     * (注文経路区分)<BR>
     * 注文経路区分。<BR>
     * <BR>
     * 受け取ったコードに対応した文字を<BR>
     * ログイン形態として画面で表示する。<BR>
     * ------------------------------<BR>
     * 1：コールセンター<BR>
     * 2：PC<BR>
     * 3：スリングショット<BR>
     * 4：i-mode<BR>
     * 5：Vodafone<BR>
     * 6：AU<BR>
     * 7：スリングショット(無料)<BR>
     * 9：HOST<BR>
     * A：管理者<BR>
     * B：保障金自動振替バッチ<BR>
     * C：リッチクライアント(RICH_CLIENT)<BR>
     * F：IVR(自動応答電話)<BR>
     * G：強制決済<BR>
     * ------------------------------<BR>
     */
    public String orderRootDiv;

    /**
     * (ログイン成否)<BR>
     * ログイン成否。<BR>
     * <BR>
     * 受け取ったコードに対応した文字を画面で表示する。<BR>
     * ---------------------<BR>
     * 0：成功<BR>
     * 1：失敗<BR>
     * ---------------------<BR>
     */
    public String loginResult;

    /**
     * (部店コード)<BR>
     * 部店コード。<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード。<BR>
     */
    public String accountCode;

    /**
     * @@roseuid 48D75CD6031B
     */
    public WEB3AdminTraderAdminLoginHistoryReferenceUnit()
    {

    }
}
@
