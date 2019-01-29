head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目定義インタフェイス(WEB3AdminTMKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/24 張騰宇 (中訊) 新規作成
*/
package webbroker3.trademanagement.define;

/**
 * キー項目定義インタフェイス
 *
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3AdminTMKeyItemDef
{
    /**
     * IPアドレス
     */
    public static final String IP_ADDRESS = "ipAddress";

    /**
     * 適用開始日時
     */
    public static final String START_DATE = "startDate";
    
    /**
     * ステータス
     */
    public static final String STATUS = "status";
    
    /**
     * ログイン日時
     */
    public static final String LOGIN_DATE = "loginDate";
    
    /**
     * 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";
}
@
