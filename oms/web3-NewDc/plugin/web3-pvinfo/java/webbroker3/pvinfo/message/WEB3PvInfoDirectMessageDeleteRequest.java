head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDirectMessageDeleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ダイレクト指定メッセージ削除リクエスト(WEB3PvInfoDirectMessageDeleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/25 李丁銀(中訊) 作成
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (ダイレクト指定メッセージ削除リクエスト)<BR>
 * ダイレクト指定メッセージ削除リクエストクラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoDirectMessageDeleteRequest extends WEB3GenRequest 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoDirectMessageDeleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_directMessageDelete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (表示内容ID)<BR>
     * 表示内容ID<BR>
     */
    public String displayContentsId;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）表示内容IDチェック<BR>
     * 　@１－１）this.表示内容ID == nullの場合は、<BR>
     * 　@　@　@　@　@「表示内容IDがnull」の例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01040<BR>
     * @@roseuid 4147C4090301
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）表示内容IDチェック
        //１－１）this.表示内容ID == nullの場合は.「表示内容IDがnull」の例外をスローする。        
        if(this.displayContentsId == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01040.error_message);
            log.exiting(STR_METHOD_NAME);            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01040,
                getClass().getName() + "." + STR_METHOD_NAME);       
        }
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41734399034B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3PvInfoDirectMessageDeleteResponse(this);
    }
}
@
