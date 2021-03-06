head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.52.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ変更確認リクエスト(WEB3AdminMCAdminPermGrpChangeConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 屈陽 (中訊) 新規作成 
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御管理者権限グループ変更確認リクエスト)<BR>
 * 管理者メニュー制御管理者権限グループ変更確認リクエスト<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0

 */
public class WEB3AdminMCAdminPermGrpChangeConfirmRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_admin_perm_grp_change_confirm";
                                             
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411181357L;
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpChangeConfirmRequest.class);
    
    /**
     * (管理者タイプ情報)<BR>
     * 管理者タイプ情報<BR>
     */
    public WEB3AdminMCAdminTypeUnit adminTypeUnit;
    
    /**
     * (処理可能機@能カテゴリ一覧)<BR>
     * 処理可能機@能カテゴリ一覧<BR>
     */
    public WEB3AdminMCTransactionCategoryUnit[] transactionCategoryUnits;
    
    /**
     * @@roseuid 4198641B02AF
     */
    public WEB3AdminMCAdminPermGrpChangeConfirmRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@管理者タイプ情報のチェック<BR>
     * 　@１−１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01218           <BR>
     * 　@１−２）　@管理者タイプ情報.validate()をコールする。<BR>
     * <BR>
     * ２）　@処理可能機@能カテゴリ一覧のチェック<BR>
     * 　@１−１）　@nullでない場合、処理可能機@能カテゴリ一覧.validate()をコールする。<BR>
     * <BR>
     * @@roseuid 417724F101D4
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //１）管理者タイプ情報のチェック
        //１−１）未入力の場合、例外をスローする。
        //       class:WEB3BusinessLayerException 
        //         tag:BUSINESS_ERROR_01218      
        if (this.adminTypeUnit == null)
        {
            log.error("管理者タイプ情報未入力");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01218,
                this.getClass().getName() + "." + l_strMethodName);
        }    
        //１−２）管理者タイプ情報.validate()をコールする。
        this.adminTypeUnit.validate();

        //２）処理可能機@能カテゴリ一覧のチェック
        //１−１）nullでない場合、処理可能機@能カテゴリ一覧.validate()をコールする。
        if (this.transactionCategoryUnits != null)
        {
            for (int i = 0; i < transactionCategoryUnits.length; i++)
            {
                this.transactionCategoryUnits[i].validate(); 
            }            
        }
        
        log.exiting(l_strMethodName); 
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641B030D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpChangeConfirmResponse(this);
    }
}
@
