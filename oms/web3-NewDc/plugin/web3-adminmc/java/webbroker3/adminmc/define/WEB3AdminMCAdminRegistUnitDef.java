head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義管理者登録情報(WEB3AdminMCAdminRegistUnitDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26  温 顕 法@(sinocom)　@新規作成
*/
package webbroker3.adminmc.define;

/**
 * ソートキー.キー項目の区分
 *                                                                     
 * @@author 温 顕 法@
 * @@version 1.0
 */
public interface WEB3AdminMCAdminRegistUnitDef
{

    /**
     * branchCode：部店コード
     */
    public static final String BRANCHCODE = "branchCode";
    /**
     * administratorCode：管理者コード
     */
    public static final String ADMINISTRATORCODE = "administratorCode";
    /**
     * mailAddress：メールアドレス
     */
    public static final String MAILADDRESS = "mailAddress";
    /**
     * permissionLevel：権限レベルコード
     */
    public static final String PERMISSIONLEVEL = "permissionLevel";
    /**
     * errorCount：エラー回数
     */
    public static final String ERRORCOUNT = "errorCount";
    /**
     * updateTimeStamp：更新日時
     */
    public static final String UPDATETIMESTAMP = "updateTimeStamp";
    /**
     * updaterCode：更新者コード
     */
    public static final String UPDATERCODE = "updaterCode";

}
@
