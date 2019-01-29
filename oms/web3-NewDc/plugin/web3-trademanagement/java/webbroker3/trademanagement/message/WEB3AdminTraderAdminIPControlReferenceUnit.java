head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.20.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン拒否IP情報(WEB3AdminTraderAdminIPControlReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (ログイン拒否IP情報)<BR>
 * ログイン拒否IP情報クラス。<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlReferenceUnit extends Message
{
    /**
     * (ログイン拒否ID)<BR>
     * ログイン拒否ID<BR>
     */
    public String denyLoginID;

    /**
     * (IPアドレス)<BR>
     * IPアドレス<BR>
     */
    public String ipAddress;

    /**
     * (ステータス)<BR>
     * ステータス<BR>
     * <BR>
     * 0： 対象<BR>
     * 1： 無効<BR>
     * 2： 対象外<BR>
     */
    public String status;

    /**
     * (適用開始日時)<BR>
     * 適用開始日時<BR>
     */
    public Date startDate;

    /**
     * (適用終了日時)<BR>
     * 適用終了日時<BR>
     */
    public Date endDate;

    /**
     * (登録区分)<BR>
     * 登録区分<BR>
     * <BR>
     * 0： デーモン<BR>
     * 1： 管理者<BR>
     */
    public String registDiv;

    /**
     * (更新区分)<BR>
     * 更新区分<BR>
     * <BR>
     * 0： デーモン<BR>
     * 1： 管理者<BR>
     */
    public String updateDiv;

    /**
     * (更新者コード)<BR>
     * 更新者コード<BR>
     */
    public String updaterCode;

    /**
     * (ログイン拒否IP情報)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 48BE613E030B
     */
    public WEB3AdminTraderAdminIPControlReferenceUnit()
    {

    }
}
@
