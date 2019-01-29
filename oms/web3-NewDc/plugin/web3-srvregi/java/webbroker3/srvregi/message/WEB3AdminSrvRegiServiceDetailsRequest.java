head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceDetailsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス詳細リクエスト(WEB3AdminSrvRegiServiceDetailsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者サービス詳細リクエスト)<BR>
 * サービス利用管理者サービス詳細リクエストクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceDetailsRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_serviceDetails";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceDetailsRequest.class);
    
    /**
     * (ID)<BR>
     * サービス区分<BR>
     */
    public String serviceDiv;
    
    /**
     * (サービス利用管理者サービス詳細リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE4E48013F
     */
    public WEB3AdminSrvRegiServiceDetailsRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者サービス詳細レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE4E48015E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiServiceDetailsResponse(this);
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
     * @@throws WEB3BaseException
     * @@roseuid 40EE4E48016D
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //1) IDのチェック
        //1-1) this.ID==nullの場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1-2) this.IDの桁数が、2桁または4桁以外の場合、例外をスローする。
        if (this.serviceDiv.length() != 2 && this.serviceDiv.length() != 4)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00954, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.entering(STR_METHOD_NAME);
    }
}
@
