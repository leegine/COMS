head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.55.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ登録完了リクエスト(WEB3AdminMCAdminPermGrpRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御管理者権限グループ登録完了リクエスト)<BR>
 * 管理者メニュー制御管理者権限グループ登録完了リクエスト<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpRegistCompleteRequest extends WEB3GenRequest 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpRegistCompleteRequest.class); 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_adminPermGrpRegistComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     * <BR>
     */
    public String password;
    
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
     * @@roseuid 4198641E0261
     */
    public WEB3AdminMCAdminPermGrpRegistCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@管理者タイプ情報のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :     BUSINESS_ERROR_01218          <BR>
     * 　@１－２）　@管理者タイプ情報.validate()をコールする。<BR>
     * <BR>
     * ２）　@処理可能機@能カテゴリ一覧のチェック<BR>
     * 　@１－１）　@nullでない場合、処理可能機@能カテゴリ一覧.validate()をコールする。<BR>
     * <BR>
     * @@roseuid 417617C60206
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";

        log.entering(STR_METHOD_NAME);

        // １）　@管理者タイプ情報のチェック<BR>
        // 　@１－１）　@未入力の場合、例外をスローする。<BR>
        //                class :  WEB3BusinessLayerException <BR>
        //                tag :     BUSINESS_ERROR_01218          <BR>
        if (this.adminTypeUnit == null )
        {
            log.error("管理者タイプ情報未入力.");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01218,
                            this.getClass().getName() + STR_METHOD_NAME);            
        }   
        
        // 　@１－２）　@管理者タイプ情報.validate()をコールする。<BR>
        this.adminTypeUnit.validate();

        // ２）　@処理可能機@能カテゴリ一覧のチェック<BR>
        // 　@１－１）　@nullでない場合、処理可能機@能カテゴリ一覧.validate()をコールする。<BR>
        if (this.transactionCategoryUnits != null )
        {
            int l_length = this.transactionCategoryUnits.length;
            for(int i = 0; i < l_length; i++)
            {
                this.transactionCategoryUnits[i].validate();
            }
        }   
          
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4198641E02AF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMCAdminPermGrpRegistCompleteResponse(this);
    }
}
@
