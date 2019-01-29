head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoStopStateChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報停止状況変更入力リクエスト(WEB3AdminAccInfoStopStateChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 李海波 (中訊) 新規作成
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
 * (管理者お客様情報停止状況変更入力リクエスト)<BR>
 * 管理者お客様情報停止状況変更入力リクエスト<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoStopStateChangeInputRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoStopStateChangeInputRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_stopStateChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082101L;

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
     * @@roseuid 418F38640280
     */
    public WEB3AdminAccInfoStopStateChangeInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoStopStateChangeInputResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@顧客コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * 　@１－２）　@桁数が6でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * 　@１－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41748F6F0366
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //1）　@顧客コードのチェック 
        //1－１）　@未入力の場合、例外をスローする。 
        if (this.accountCode == null || "".equals(this.accountCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00835, 
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コード未入力");  
        }        
        
        //1－２）　@桁数が6でない場合、例外をスローする。 
        if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00836, 
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コード桁数が6でない場合"); 
        }
        
        //1－３）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01043, 
                this.getClass().getName() + STR_METHOD_NAME,
                "顧客コード数字以外の文字が含まれる"); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
