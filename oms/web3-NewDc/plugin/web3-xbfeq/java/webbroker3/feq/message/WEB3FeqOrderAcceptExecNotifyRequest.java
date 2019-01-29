head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付出来通知リクエスト(WEB3FeqOrderAcceptExecNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/13 何文敏(中訊) 新規作成
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文受付出来通知リクエスト)<BR>
 * 外国株式注文受付出来通知リクエストクラス<BR>
 * 
 * @@author 何文敏(中訊)
 * @@version 1.0
 */

public class WEB3FeqOrderAcceptExecNotifyRequest extends WEB3BackRequest
{  
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "feq_order_accept_exec_notify";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3FeqOrderAcceptExecNotifyRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecNotifyRequest.class);
    
    /**
     * (From口座ID)<BR>
     * From口座ID<BR>
     */
    public Long fromAccountId;
    
    /**
     * (To口座ID)<BR>
     * To口座ID<BR>
     */
    public Long toAccountId; 
    
    /**
     * (スレッドNo)<BR>
     * スレッドNo<BR>
     */
    public Long threadNo;   
    
    /**
     *  当リクエストデータの整合性チェックを行う。<BR> 
     *  （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）　@From口座IDとTo口座IDのチェック <BR>
     *  this.From口座ID==nullまたはthis.To口座ID==nullの場合、<BR> 
     * 「ＩＤが未指定です」の例外をthrowする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * ２）　@スレッドNo==nullの場合、「スレッド番号の指定なし」の例外をthrowする。<BR>
     *　@　@class: WEB3BusinessLayerException<BR>
     *　@　@tag:   BUSINESS_ERROR_01974<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@From口座IDとTo口座IDのチェック
        // this.From口座ID==nullまたはthis.To口座ID==nullの場合、
        //「ＩＤが未指定です」の例外をthrowする。
        if (this.fromAccountId == null  ||
            this.toAccountId == null)
        {
            log.debug("ＩＤが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + STR_METHOD_NAME,
                "ＩＤが未指定です。");
        }
        
        // ２）　@スレッドNo==nullの場合、「スレッド番号の指定なし」の例外をthrowする。
        if (this.threadNo == null)
        {
            log.debug("スレッド番号の指定なし。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + STR_METHOD_NAME,
                "スレッド番号の指定なし。" + this.threadNo);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3FeqOrderAcceptExecNotifyResponse(this);
    }
}
@
