head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountInfoSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座情報検索リクエスト(WEB3AdminFEqConAccountInfoSearchRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
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
 * (外株口座情報検索リクエスト)<BR>
 * 外株口座情報検索リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountInfoSearchRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_info_search";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (外株口座番号)<BR>
     * 外株口座番号
     */
    public String fstkAccountCode;
    
    /**
     * @@roseuid 423554FD03B9
     */
    public WEB3AdminFEqConAccountInfoSearchRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountInfoSearchRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     *   this.部店コード == nullの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）外株口座番号<BR>
     * <BR>
     * ２－１）<BR>
     *   this.外株口座番号 == nullの場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01953<BR>
     * <BR>
     * ２－２）<BR>
     *   this.外株口座番号 != 数字の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01954<BR>
     * <BR>
     * ２－３）<BR>
     *   this.外株口座番号.length != 9の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01954<BR>
     * <BR>
     * @@roseuid 41E611A9026F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード
        //this.部店コード == null
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        //２）外株口座番号 
        //２－１） 
        //this.外株口座番号 == null
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.fstkAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01953,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外株口座番号が未指定です。");
        }

        //２－２） 
        //this.外株口座番号 != 数字
        //の場合、例外をスローする。 
        if(!WEB3StringTypeUtility.isNumber(this.fstkAccountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01954,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外株口座番号 != 数字。");
        }
        
        //２－３） 
        //this.外株口座番号.length != 6
        //の場合、例外をスローする。 
        if(this.fstkAccountCode.length() != 6)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01954,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "外株口座番号.length != 6。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株口座情報検索レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountInfoSearchResponse(this);
    }
}
@
