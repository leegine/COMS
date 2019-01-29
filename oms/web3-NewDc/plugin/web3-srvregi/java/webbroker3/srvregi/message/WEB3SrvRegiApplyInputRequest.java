head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込入力リクエスト(WEB3SrvRegiApplyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
Revesion History : 2007/06/05 趙林鵬 (中訊) モデル.248
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiApplyKindDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用申込入力リクエスト)<BR>
 * サービス利用申込入力リクエストクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiApplyInputRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3SrvRegiApplyInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_applyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151436L;
    
    /**
     * (ID)<BR>
     * サービス区分<BR>
     */
    public String serviceDiv;
    
    /**
     * (申込種別区分)<BR>
     * 0:通常申込　@1:継続申込　@2:試用申込　@3:無料申込<BR>
     */
    public String applyKindDiv;

    /**
     * (無料属性申込区分)<BR>
     * null or '0' ：通常申込　@'1'：無料属性申込<BR>
     */
    public String freeAttributeApplyDiv;

    /**
     * (サービス利用申込入力リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F1F2D70094
     */
    public WEB3SrvRegiApplyInputRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用申込入力レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F1F2D700A3
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3SrvRegiApplyInputResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) IDのチェック<BR>
     *  1-1) this.ID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     *  1-2) this.IDの桁数が、2桁または4桁以外の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * 2) 申込種別区分のチェック<BR> 
     * 2-1) this.申込種別区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00979<BR> 
     * 2-2) this.申込種別区分が以下の値以外の場合、例外をスローする。<BR> 
     *　@　@　@"通常申込" <BR>
     *　@　@　@"継続申込" <BR>
     *　@　@　@"試用申込" <BR>
     *　@　@　@"無料申込"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00980<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F1F34F023A
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //1-1) this.ID==nullの場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + STR_METHOD_NAME); 
        }
        
        //1-2) this.IDの桁数が、2桁または4桁以外の場合、例外をスローする。
        //U00871
        if (this.serviceDiv.length() != 2)
        {
            log.debug("****************************");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        //2-1) this.申込種別区分==nullの場合、例外をスローする。
        if (this.applyKindDiv == null || "".equals(applyKindDiv.trim()))
        {
            log.debug("%%%%%%%%%%%%%%%%%%%%%%%%%%");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00979,
                getClass().getName() + STR_METHOD_NAME); 
        }
        
        //2-2) this.申込種別区分が以下の値以外の場合、例外をスローする。
        if (!WEB3SrvRegiApplyKindDivDef.USUAL_APPLI.equals(this.applyKindDiv) &&
            !WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(this.applyKindDiv) &&
            !WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(this.applyKindDiv) &&
            !WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(this.applyKindDiv))
        {
            log.debug("#############################");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00980,
                getClass().getName() + STR_METHOD_NAME); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
