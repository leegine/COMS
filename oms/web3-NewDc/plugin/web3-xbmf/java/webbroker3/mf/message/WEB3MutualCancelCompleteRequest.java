head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.05.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託取消完了リクエストクラス(WEB3MutualCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/09 于美麗 (中訊) 新規作成
                   2004/08/24 黄建 (中訊) レビュー
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * 投資信託取消完了リクエストクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualCancelCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualCancelCompleteRequest.class);
  
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_cancel_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L; 
  
    /**
     * 注文ID
     */
    public String id;
    
    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * 確認時発注日<BR>
     * <BR>
     * 確認レスポンスの処理で使用した値を格納する。<BR>
     */
    public Date checkDate;
    
    /**
     * (投信取消完了リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40A9A888028C
     */
    public WEB3MutualCancelCompleteRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * （createResponseの実装）<BR>
     * <BR>
     * 投信取消完了レスポンスオブジェクトを生成して返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A9A897025D
     */
    public WEB3GenResponse createResponse() 
    {
        WEB3MutualCancelCompleteResponse l_response = new WEB3MutualCancelCompleteResponse(this);
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
     * <BR>
     * ２)　@確認時発注日のチェック<BR>
     * 　@this.確認時発注日がnullである場合、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00078 <BR>
     * @@roseuid 40A9A897026C
     */
    public void validate()throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１)　@IDチェック
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("ＩＤが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ＩＤが未指定です。");            
        }
        
        //２)　@確認時発注日のチェック
        if (WEB3StringTypeUtility.isEmpty(WEB3DateUtility.formatDate(this.checkDate, "yyyyMMdd")))
        {
            log.debug("確認時発注日が入力されていません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "確認時発注日が入力されていません。");            
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
