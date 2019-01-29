head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiApplyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込完了リクエスト(WEB3SrvRegiApplyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用申込完了リクエスト)<BR>
 * サービス利用申込完了リクエストクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiApplyCompleteRequest extends WEB3SrvRegiApplyCommonRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3SrvRegiApplyCompleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "srvregi_applyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151434L;
    
    /**
     * (暗証番号)
     */
    public String password;
    
    /**
     * (確認時発注日)
     */
    public Date checkDate;
    
    /**
     * (サービス利用申込完了リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F1FDD90268
     */
    public WEB3SrvRegiApplyCompleteRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用申込完了レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F1FDD90278
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3SrvRegiApplyCompleteResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) super.validate( )をコールする。<BR>
     * <BR>
     * 2) 確認時発注日のチェック<BR>
     *  this.確認時発注日=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * @@roseuid 41299E4A0065
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //1) super.validate( )をコールする。
        super.validate();
        
        //2) 確認時発注日のチェック
        if (this.checkDate == null)
        {
            log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
