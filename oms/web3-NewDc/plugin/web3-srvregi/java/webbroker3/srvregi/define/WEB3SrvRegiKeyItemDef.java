head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目(WEB3SrvRegiKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 李頴淵(sinocom) 新規作成
Revesion History : 2007/06/19 崔遠鵬(sinocom) 仕様変更モデルNo.249
Revesion History : 2007/07/25 金傑(sinocom) 仕様変更モデルNo.292
Revesion History : 2008/03/03 武波(sinocom) 仕様変更モデルNo.330
Revesion History : 2008/03/26 武波(sinocom) 実装の問題002
*/
package webbroker3.srvregi.define;

/**
 * キー項目
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiKeyItemDef
{
    /**
     * branch：部店  　@
     */
    public final static String BRANCH_CODE = "branchCode";

    /**
     * account：顧客  　@
     */
    public final static String ACCOUNT_CODE = "accountCode";

    /**
     * appliLotDiv：申込抽選区分 　@
     */
    public final static String APPLI_LOT_DIV = "applyLotteryDiv";

    /**
     * appliDate：申込日  　@
     */
    public final static String APPLI_DATE = "applyDate";

    /**
     * appliStartDate：適用開始日  　@
     */
    public final static String APPLI_START_DATE = "trialStartDate";

    /**
     * appliEndDate：適用終了日  　@
     */
    public final static String APPLI_END_DATE = "trialEndDate";

    /**
     * paymentDiv ：登録区分  　@
     */
    public final static String PAYMENT_DIV = "registDiv";

    /**
     * transactionDiv ：処理区分
     */
    public final static String TRANSACTION_DIV = "transactionDiv";

    /**
     * useAmt：利用料金  　@
     */
    public final static String USE_AMT = "chargeAmt";

    /**
     * lastUpdatedTimestamp：最終更新日  　@
     */
    public final static String LAST_UPDATED_TIMESTAMP  = "lastUpdateTime";

    /**
     * lastUpdater：最終更新者  　@
     */
    public final static String LAST_UPDATER = "lastUpdater";

    /**
     * appliAttribute：申込属性区分
     */
    public final static String APPLI_ATTRIBUTE = "appliAttribute";

    /**
     * seqNumber：通番
     */
    public final static String SEQUENCE_NUMBER = "seqNumber";

    /**
     * id：ID
     */
    public final static String ID = "id";

    /**
     * status：ステータス
     */
    public final static String STATUS = "status";

    /**
     * appliStartDate：適用期間From
     */
    public final static String APPLI_START_DATE_FROM = "appliStartDate";

    /**
     * appliEndDate：適用期間To
     */
    public final static String APPLI_END_DATE_TO = "appliEndDate";
}
@
