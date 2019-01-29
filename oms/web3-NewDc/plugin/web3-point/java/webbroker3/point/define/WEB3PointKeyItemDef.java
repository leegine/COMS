head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.03.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3PointKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.define;

/**
 * キー項目
 *                                                                     
 * @@author 鄭海良
 * @@version 1.1
 */
public interface WEB3PointKeyItemDef
{

    /**
     * "branchCode" : 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * "accountCode" : 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * "premiumNo" : 景品番号
     */
    public static final String PREMINUM_NO = "premiumNo";

    /**
     * "premiumName" : 景品名
     */
    public static final String PREMIUM_NAME = "premiumName";

    /**
     * "applyDate" : 申込日時
     */
    public static final String APPLY_TIMESTAMP = "applyDate";
    
    /**
     * "acceptDiv" : 受付区分
     */
    public static final String APPLY_ACCEPT_DIV = "acceptDiv";    
        
    /**
     * "updateTimeStamp" : 更新日時
     */
    public static final String LAST_UPDATED_TIMESTAMP = "updateTimeStamp";

    /**
     * "acceptUser" : 受付ユーザ
     */
    public static final String APPLY_ACCEPT_USER = "acceptUser";

    /**
     * "cancelDiv" : 取消区分
     */
    public static final String APPLY_CANCEL_DIV = "cancelDiv";
    
}@
