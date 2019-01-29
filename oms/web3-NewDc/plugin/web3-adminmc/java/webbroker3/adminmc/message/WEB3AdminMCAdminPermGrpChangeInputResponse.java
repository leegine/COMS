head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.53.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ変更入力レスポンス(WEB3AdminMCAdminPermGrpChangeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 屈陽 (中訊) 新規作成 
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者メニュー制御管理者権限グループ変更入力レスポンス)<BR>
 * 管理者メニュー制御管理者権限グループ変更入力レスポンス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_admin_perm_grp_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411181548L;
    
    /**
     * (処理可能機@能カテゴリ一覧)<BR>
     * 処理可能機@能カテゴリ一覧<BR>
     */
    public WEB3AdminMCTransactionCategoryUnit[] transactionCategoryUnits;
    
    /**
     * (管理者タイプ情報)<BR>
     * 管理者タイプ情報<BR>
     */
    public WEB3AdminMCAdminTypeUnit adminTypeUnit;
    
    /**
     * (管理者メニュー制御管理者権限グループ変更入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - リクエストオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse
     * @@roseuid 4175E007037D
     */
    public WEB3AdminMCAdminPermGrpChangeInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
