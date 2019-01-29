head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ソートキー項目 定数定義インタフェイス(WEB3AccInfoKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/
package webbroker3.accountinfo.define;

/**
 * ソートキー項目 定数定義インタフェイス
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3AccInfoKeyItemDef
{
    /**
     * 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * 更新日
     */
    public static final String UPDATED_DATE = "updateDate";

    /**
     * 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * 関係コード
     */
    public static final String RELATION_CODE = "relationCode";

    /**
     * 役員名
     */
    public static final String OFFICER_NAME = "executive";

    /**
     * 役職名
     */
    public static final String POST_NAME = "position";

    /**
     * 登録状況区分
     */
    public static final String REGIST_DIV = "registStateDiv";

    /**
     * 申込日
     */
    public static final String APPLY_DATE = "applyDate";

    /**
     * 判定日時
     */
    public static final String JUDGEMENT_DATE = "judgementDate";

    /**
     * 登録日時
     */
    public static final String REGIST_DATE = "registDate";

    /**
     * 管理者ID
     */
    public static final String MANAGER_ID = "managerID";

    /**
     * 扱者コード
     */
    public static final String TRADER_CODE = "traderCode";

    /**
     * 口座開設日
     */
    public static final String ACCOUNTOPENDATE = "accountOpenDate";
    
    /**
     * 対象期間From
     */
    public static final String TARGETPERIOD_FROM = "targetPeriodFrom";
    
    /**
     * 対象期間To
     */
    public static final String TARGETPERIOD_TO = "targetPeriodTo";
    
    /**
     * 対象日
     */
    public static final String TARGET_DATE = "targetDate";
    
    /**
     * 徴収率
     */
    public static final String COLLECT_RATE = "collectRate";

    /**
     * 口座開設日From
     */
    public static final String ACCOUNTOPENDATE_FROM = "accountOpenDateFrom";

    /**
     * 口座開設日To
     */
    public static final String ACCOUNTOPENDATE_TO = "accountOpenDateTo";
}
@
