head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文取消確認リクエスト(WEB3MstkCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 カク寛新 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資注文取消確認リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資注文取消確認リクエストクラス
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3MstkCancelConfirmRequest extends WEB3GenRequest 
{
    
    /**         
     * （ログ出力ユーティリティ）。        
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkCancelConfirmRequest.class);
        
    /**
     * （PTYPE）。
     */
    public final static  String PTYPE = "mstk_cancelConfirm";
        
    /**
     * （SerialVersionUID）。
     */
    public final static long serialVersionUID = 200410101054L;
    
    /**
     * （ID）。<BR>
     * <BR>
     * 取消対象データの注文ＩＤ
     */
    public String id;
    
    /**
     * （株式ミニ投資注文取消確認リクエスト）。<BR>
     * <BR>
     * コンストラクタ
     */
    public WEB3MstkCancelConfirmRequest() 
    {
    }
    
    /**
     * （validate）。<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@　@ＩＤチェック<BR>
     * 　@　@　@　@this.ＩＤ＝nullであった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080
     */
     public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@　@ＩＤチェック
        if(this.id == null)
        {
            
            log.error("ＩＤ＝nullであった場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                    this.getClass().getName() + "validate()");
                             
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * （createレスポンス）。
     * @@return WEB3GenResponse 株式ミニ投資注文取消確認レスポンス
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkCancelConfirmResponse(this);
    }
}
@
