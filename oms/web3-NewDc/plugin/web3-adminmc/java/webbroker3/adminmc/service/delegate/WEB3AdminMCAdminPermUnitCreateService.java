head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermUnitCreateService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者権限情報作成サービス(WEB3AdminMCAdminPermUnitCreateService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 屈陽 (中訊) 新規作成 
*/

package webbroker3.adminmc.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (管理者権限情報作成サービス)<BR>
 * 管理者権限情報作成サービスインタフェイス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public interface WEB3AdminMCAdminPermUnitCreateService extends Service 
{
    
    /**
     * (update処理可能機@能カテゴリ)<BR>
     * 引数の内容で、管理者権限テーブルを更新する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPermissionLevel - (権限レベル)<BR>
     * 権限レベル<BR>
     * @@param l_operatePossibleTransactionCategory - (処理可能機@能カテゴリ)<BR>
     * 機@能カテゴリコード一覧<BR>
     * <BR>
     * @@roseuid 41760BA00292
     */
    public void updateOperatePossibleTransactionCategory(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        WEB3AdminMCTransactionCategoryUnit[] l_operatePossibleTransactionCategory,
        String l_strAdministratorCode)
            throws WEB3BaseException;
    
    /**
     * (create処理可能機@能カテゴリ一覧)<BR>
     * 証券会社，権限レベルに該当する機@能カテゴリ情報を配列を作成する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strPermissionLevel - (権限レベル)<BR>
     * 権限レベル<BR>
     * @@param l_strTransactionCategory - (機@能カテゴリコード)<BR>
     * 機@能カテゴリコードの配列<BR>
     * <BR>
     * ※ 指定しない場合はnull<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit[]
     * @@roseuid 41760BA00273
     */
    public WEB3AdminMCTransactionCategoryUnit[] createOperatePossibleTransactionCategoryUnit(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String[] l_strTransactionCategory)
            throws WEB3BaseException;
    
    /**
     * (create管理者タイプ情報)<BR>
     * 管理者タイプオブジェクトより、管理者タイプ情報メッセージデータオブジェクトを作成する。<BR>
     * <BR>
     * <BR>
     * @@param l_adminType - (管理者タイプ)<BR>
     * 管理者タイプ<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit
     * @@roseuid 417723210196
     */
    public WEB3AdminMCAdminTypeUnit createAdminTypeUnit(WEB3AdminMCAdminType l_adminType);
    
    /**
     * (create管理者タイプ情報)<BR>
     * 管理者タイプオブジェクトの配列より、管理者タイプ情報メッセージデータオブジェクトの配列を作成する。<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_adminType - (管理者タイプ)<BR>
     * 管理者タイプオブジェクトの配列<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit[]
     * @@roseuid 417749B30196
     */
    public WEB3AdminMCAdminTypeUnit[] createAdminTypeUnit(WEB3AdminMCAdminType[] l_adminType);
}
@
