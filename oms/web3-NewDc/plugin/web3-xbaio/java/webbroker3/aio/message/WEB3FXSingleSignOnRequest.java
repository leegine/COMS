head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXSingleSignOnRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  シングルサインオンリクエスト (WEB3FXSingleSignOnRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/8/25 王暁傑 (中訊) 新規作成   
Revesion History : 2008/05/19 柴双紅(中訊) 仕様変更 モデルNo.865
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (シングルサインオンリクエスト) <BR>
 * Fシングルサインオンリクエストクラス<BR>
 * 
 * @@author 王暁傑(中訊)
 * @@version 1.0
 */
public class WEB3FXSingleSignOnRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_single_sign_on";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (電子鳩チェックフラグ) <BR>
     * FXドキュメント閲覧履歴チェックの要/不要<BR> 
     * <BR>
     * true：チェック要<BR> 
     * false：チェック不要 <BR>
     */
    public boolean batoCheckFlag;

    /**
     * (種別コード) <BR>
     * FXドキュメント閲覧履歴チェック処理における<BR> 
     * 目論見書チェックの第一引数<BR>
     */
    public String typeCode;

    /**
     * (識別コード) <BR>
     * FXドキュメント閲覧履歴チェック処理における<BR> 
     * 目論見書チェックの第二引数<BR>
     */
    public String[] requestCode;

    /**
     * (説明不要承諾履歴作成フラグ) <BR>
     * 説明不要承諾履歴作成の要／不要 <BR>
     * <BR>
     * true：作成要<BR> 
     * false：作成不要<BR>
     */
    public boolean noExplainAgreeHistoryFlag;

    /**
     * (FXシステムコード)<BR>
     * FXシステムコード
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E784690232
     */
    public WEB3FXSingleSignOnRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXSingleSignOnRequest.class);

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>  
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>  
     * <BR>
     * 電子鳩接続チェックフラグ==trueの場合<BR>
     * 以下の１）、２）のチェックを行う。<BR> 
     * <BR>
     * １）　@種別コードチェック<BR> 
     * this.種別コード==nullの場合、例外をthrowする。<BR> 
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_02202 <BR>
     * ２）　@識別コードチェック<BR> 
     * this.識別コード ==null or  <BR>
     * this.識別コード.length==0の場合、例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00829 <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C656B50132
     */
    public void validate() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //電子鳩接続チェックフラグ==trueの場合以下の１）、２）のチェックを行う。  
        if (batoCheckFlag)
        {
            //１）　@種別コードチェック 
            //this.種別コード==nullの場合、例外をthrowする。 
            if (WEB3StringTypeUtility.isEmpty(typeCode))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02202,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "種別コードが未指定です。"); 
            }
            
            //２）　@識別コードチェック 
            //this.識別コード ==null or  
            //this.識別コード.length==0の場合、例外をthrowする。 
            if (requestCode == null || requestCode.length == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "識別コードが未指定です。"); 
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E784690271
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXSingleSignOnResponse(this);
    }
}@
