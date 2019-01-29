head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOpenAtOrderDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式寄付注文ダウンロードリクエスト(WEB3AdminFeqOpenAtOrderDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者外国株式寄付注文ダウンロードリクエスト)<BR>
 * 管理者外国株式寄付注文ダウンロードリクエストクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqOpenAtOrderDownloadRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOpenAtOrderDownloadRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_openAtOrderDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (市場コード)<BR>
     * 画面にて選択された市場コード
     */
    public String marketCode;
    
    /**
     * @@roseuid 42CE39FF02CE
     */
    public WEB3AdminFeqOpenAtOrderDownloadRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）市場コード<BR>
     * <BR>
     *    this.市場コード == null<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00443<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42BF918D0391
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１）市場コード
        //this.市場コード == null
        if (WEB3StringTypeUtility.isEmpty(marketCode)) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + STR_METHOD_NAME,
                " 市場コード整合性をチェック"); 
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqOpenAtOrderDownloadResponse(this);
    }
}
@
