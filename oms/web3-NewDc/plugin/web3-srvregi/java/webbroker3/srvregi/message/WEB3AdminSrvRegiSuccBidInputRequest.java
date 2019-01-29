head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiSuccBidInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス落札額更新入力リクエスト(WEB3AdminSrvRegiSuccBidInputRequest.java)
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
 * (サービス利用管理者サービス落札額更新入力リクエスト)<BR>
 * サービス利用管理者サービス落札額更新入力リクエストクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiSuccBidInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_succBidInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiSuccBidInputRequest.class);        
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (抽選情報ID)<BR>
     * 通番<BR>
     */
    public String lotteryId;
    
    /**
     * (サービス利用管理者サービス落札額更新入力リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F4DFB20066
     */
    public WEB3AdminSrvRegiSuccBidInputRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者サービス落札額更新入力レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F4ED3103C1
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiSuccBidInputResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス区分のチェック<BR>
     *  1-1) this.IDがnull==場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     *  1-2) this.IDの桁数!=2桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * 2) 抽選情報IDのチェック<BR>
     * 　@this.抽選情報ID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00957<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4ED3103E0
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス区分のチェック
        //1-1) this.IDがnull==場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1-2) this.IDの桁数!=2桁の場合、例外をスローする。
        if (this.serviceDiv.length() != 2 )
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00954, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //2) 抽選情報IDのチェック
        if (this.lotteryId == null || "".equals(this.lotteryId.trim()))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00957, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.entering(STR_METHOD_NAME);
    }
}
@
