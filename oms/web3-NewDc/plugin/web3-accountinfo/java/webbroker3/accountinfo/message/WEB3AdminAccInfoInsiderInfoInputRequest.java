head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部情報一覧入力リクエスト(WEB3AdminAccInfoInsiderInfoInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 李海波 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者お客様情報内部情報一覧入力リクエスト)<BR>
 * 管理者お客様情報内部者情報一覧入力リクエスト<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_InsiderInfoInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412211207L;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoInputRequest.class);
        
    /**
     * (部店コード一覧)<BR>
     * 管理者の部店コード一覧<BR>
     * ※部店権限のチェックに使用。<BR>
     */
    public String[] branchCodeList;
   /** 
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    *<BR>
    * @@return レスポンスオブジェクト
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3AdminAccInfoInsiderInfoInputResponse(this);
   }
   
    /**
     * validate<BR>
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@部店コード一覧のチェック<BR>
     * １－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.branchCodeList == null || branchCodeList.length == 0)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コード一覧未入力");  
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
