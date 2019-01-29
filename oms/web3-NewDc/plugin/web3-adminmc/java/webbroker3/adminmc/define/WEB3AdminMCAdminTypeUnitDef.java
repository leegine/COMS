head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminTypeUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者タイプ情報 定数定義インタフェイス(WEB3AdminMCAdminTypeUnitDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 賈元春 (中訊) 新規作成
*/
package webbroker3.adminmc.define;

/**
 * 管理者タイプ情報 定数定義インタフェイス
 * 
 * @@author 賈元春(中訊)
 * @@version 1.0
 */
public interface WEB3AdminMCAdminTypeUnitDef
{
    /**
     * permissionLevel: 権限レベルコード
     */
    public static final String PERMISSION_LEVEL = "permissionLevel";
    
    /**
     * permissionLevelName: 権限レベル名称
     */
    public static final String PERMISSION_LEVEL_NAME = "permissionLevelName";
    
    /**
     * dirAdminFlag: DIR管理者フラグ
     */
    public static final String DIR_ADMIN_FLAG = "dirAdminFlag";

    /**
     * allBranchPermissionFlag: 全部店許可フラグ
     */
    public static final String ALL_BRANCH_PERMISSION_FLAG = "allBranchPermissionFlag";
    
    /**
     * branchCode: DIR管理者の開始文字 
     */
    public static final char PERMISSION_LEVEL_NAME_FIRST = '9';
}
@
