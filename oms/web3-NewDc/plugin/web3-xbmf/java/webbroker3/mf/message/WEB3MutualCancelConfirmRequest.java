head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消確認リクエストクラス(WEB3MutualCancelConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/24 黄建 (中訊) レビュー
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * 投資信託取消確認リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualCancelConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelConfirmRequest.class);
       
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_cancel_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
        
    /**
     * 注文ID
     */
    public String id;
    
    /**
     * (投信取消確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40A9A82D01EF
     */
    public WEB3MutualCancelConfirmRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * （createResponseの実装）<BR>
     * <BR>
     * 投信取消確認レスポンスオブジェクトを生成して返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A9A83C002A
     */
    public WEB3GenResponse createResponse() 
    {
        WEB3MutualCancelConfirmResponse l_response = new WEB3MutualCancelConfirmResponse(this);
        return l_response;
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@IDチェック<BR>
     * 　@this.IDがnullの場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00080 <BR>
     * @@roseuid 40A9A83C003A
     */
    public void validate()throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
         if (WEB3StringTypeUtility.isEmpty(this.id))
         {
             log.debug("ＩＤが未指定です。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "ＩＤが未指定です。");
         }
        log.exiting(STR_METHOD_NAME);
    }
}
@
