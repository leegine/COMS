head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報内部者情報変更完了リクエスト(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報内部者情報変更完了リクエスト)<BR>
 * 管理者お客様情報内部者情報変更完了リクエスト<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoInsiderInfoChangeCompleteRequest extends WEB3GenRequest
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_insiderInfoChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082131L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (内部者情報)<BR>
     * 内部者情報<BR>
     */
    public WEB3AccInfoInsiderInfo insiderInfo;

    /**
     * @@roseuid 418F3863029F
     */
    public WEB3AdminAccInfoInsiderInfoChangeCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoInsiderInfoChangeCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * 　@２－２）　@桁数が6でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * 　@２－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * ３）　@内部者情報のチェック<BR>
     * 　@３－１）　@内部者情報が未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01165<BR>
     * 　@３－２）　@内部者情報.validate()をコールする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 415CEC0701D2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コードのチェック
        //１－１）　@未入力の場合、例外をスローする。 
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コード未入力");  
        }
        
        //２）　@顧客コードのチェック 
        //２－１）　@未入力の場合、例外をスローする。 
        if (this.accountCode == null || "".equals(this.accountCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, 
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コード未入力");  
        }        
        
        //２－２）　@桁数が6でない場合、例外をスローする。 
        if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, 
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コード桁数が6でない場合"); 
        }
        
        //２－３）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01043, 
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コード数字以外の文字が含まれる"); 
        }
        

         
        /*
         * ３）　@内部者情報のチェック<BR>
         * 　@３－１）　@内部者情報が未入力の場合、例外をスローする。<BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01165<BR>
         */
         if(insiderInfo == null)
         {
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01165,
                this.getClass().getName() + STR_METHOD_NAME,
                "内部者情報が未入力");  
         }
         
         // 　@３－２）　@内部者情報.validate()をコールする。<BR>         
        insiderInfo.validate();
        
        log.exiting(STR_METHOD_NAME); 
    }
}
@
