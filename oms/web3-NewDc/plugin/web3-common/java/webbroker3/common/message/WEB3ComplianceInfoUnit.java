head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ComplianceInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : コンプライアンス情報クラス(WEB3ComplianceInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/23 栄イ (中訊) 新規作成
Revesion History : 2006/10/25 栄イ (中訊) 仕様変更 モデル214を対応
Revesion History : 2007/01/19 栄イ (中訊) 仕様変更 モデル220を対応
*/
package webbroker3.common.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (コンプライアンス情報)<BR>
 * コンプライアンス情報クラス<BR>
 * <BR>
 * @@author  栄イ (中訊)
 * @@version 1.0
 */
public class WEB3ComplianceInfoUnit extends Message
{
    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (サービスID)<BR>
     * サービスID<BR>
     * <BR>
     * 1：債券買付応募 <BR>
     * 2：債券売却  <BR>
     * 3：債券取消 <BR>
     * 4：口座開設 <BR>
     * 5：顧客ロック　@ <BR>
     * 6：顧客抹消 <BR>
     */
    public String serviceId;

    /**
     * (AuditID)<BR>
     * AuditID<BR>
     */
    public String auditId;

    /**
     * (トランザクションID)<BR>
     * トランザクションID(外部システム連携用）<BR>
     */
    public String transactionId;

    /**
     * (注文No)<BR>
     * 注文No<BR>
     */
    public String orderNo;

    /**
     * (操作区分)<BR>
     * 操作区分<BR>
     * <BR>
     * 1:審査データ送信 <BR>
     * 2:承認申請 <BR>
     * 3:否認申請 <BR>
     * 4:取消 <BR>
     * 5:承認 <BR>
     * 6:否認 <BR>
     */
    public String controlDiv;

    /**
     * (経路)<BR>
     * 経路<BR>
     * <BR>
     * １：WEB3入力 <BR>
     * ２：外部システム<BR>
     */
    public String routeDiv;

    /**
     * (摘要コード)<BR>
     * 摘要コード<BR>
     */
    public String remarksCode;

    /**
     * (摘要)<BR>
     * 摘要<BR>
     */
    public String remarks;

    /**
     * (摘要コード一覧)<BR>
     * 摘要コード一覧<BR>
     */
    public String[] remarksList;

    /**
     * (注文状態)<BR>
     * 注文状態<BR>
     * <BR>
     * １：承認申請中<BR>
     * ２：取消済 <BR>
     * ３：承認済 <BR>
     * ４：否認済 <BR>
     * ５：否認申請中 <BR>
     * ６：未申請<BR>
     */
    public String orderStatus;

    /**
     * (入力リクエスト)<BR>
     * 入力リクエスト<BR>
     */
    public WEB3GenRequest inputRequest;

    /**
     * (入力レスポンス)<BR>
     * 入力レスポンス<BR>
     */
    public WEB3GenResponse inputResponse;

    /**
     * (確認リクエスト)<BR>
     * 確認リクエスト<BR>
     */
    public WEB3GenRequest confirmRequest;

    /**
     * (確認レスポンス)<BR>
     * 確認レスポンス<BR>
     */
    public WEB3GenResponse confirmResponse;
}
@
