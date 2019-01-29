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
filename	WEB3FaqKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ソートキー項目 定数定義インタフェイス(WEB3FaqKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 張宝楠 (中訊) 新規作成
*/
package webbroker3.faq.define;

/**
 * (ソートキー項目 定数定義)<BR>
 * ソートキー項目 定数定義インタフェイス<BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public interface WEB3FaqKeyItemDef
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
     * 問合せコード
     */
    public static final String FAQ_NUMBER = "faqCode";
    
    /**
     * 問合せ日時
     */
    public static final String FAQ_DATETIME = "faqDateTime";
    
    /**
     * 機@能ＩＤ
     */
    public static final String TRANSACTION_ID = "transactionId";
}
@
