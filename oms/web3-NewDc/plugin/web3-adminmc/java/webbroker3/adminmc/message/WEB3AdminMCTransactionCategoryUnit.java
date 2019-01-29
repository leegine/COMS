head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.48.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCTransactionCategoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (機@能カテゴリ情報(WEB3AdminMCTransactionCategoryUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;



/**
 * (機@能カテゴリ情報)<BR>
 * 機@能カテゴリ情報<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3AdminMCTransactionCategoryUnit extends Message 
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCTransactionCategoryUnit.class);

    /**
     * (機@能カテゴリコード)<BR>
     * 機@能カテゴリコード<BR>
     * <BR>
     * ※ 権限のある機@能カテゴリコード<BR>
     * <BR>
     */
    public String transactionCategory;
    
    /**
     * (更新許可フラグ)<BR>
     * 更新許可フラグ<BR>
     * <BR>
     * 更新処理が可能な管理者の場合true<BR>
     * 更新処理が不可の管理者の場合false<BR>
     * <BR>
     */
    public boolean updatePermissionFlag;
    
    /**
     * @@roseuid 4198642A0177
     */
    public WEB3AdminMCTransactionCategoryUnit() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする<BR>
     * <BR>
     * １）　@機@能カテゴリコードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *                class :  WEB3BusinessLayerException <BR>
     *                tag :    BUSINESS_ERROR_01220           <BR>
     * <BR>
     * @@roseuid 41760FBE02D1
     */
    public void validate() throws WEB3BaseException
    {
         final String STR_METHOD_NAME = " validate()"; 
         log.entering(STR_METHOD_NAME);
         // １）　@機@能カテゴリコード未入力の場合
         if (this.transactionCategory == null || "".equals(this.transactionCategory))
         {
             //例外
             log.error("「機@能カテゴリコード未入力」の例外をスローする。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                                             WEB3ErrorCatalog.BUSINESS_ERROR_01220,
                                             this.getClass().getName() + STR_METHOD_NAME);
         }
           
         log.exiting(STR_METHOD_NAME);
    }
}
@
