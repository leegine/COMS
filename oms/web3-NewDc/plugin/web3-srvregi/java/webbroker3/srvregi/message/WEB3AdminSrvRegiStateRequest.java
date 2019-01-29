head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.31.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiStateRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客申込状況一覧リクエスト(WEB3AdminSrvRegiStateRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客申込状況一覧リクエスト)<BR>
 * サービス利用管理者顧客申込状況一覧リクエストクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiStateRequest extends WEB3GenRequest 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiStateRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_state";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (部店コード)<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)
     */
    public String accountCode;
    
    /**
     * (サービス利用管理者顧客申込状況一覧リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE52B900A2
     */
    public WEB3AdminSrvRegiStateRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者顧客申込状況一覧レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40EE52B900C2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiStateResponse();
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) 部店コードのチェック <BR>
　@   *  1-1) this.部店コード==nullの場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
　@   *  1-2) this.部店コードの桁数!=3桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * 2) 顧客コードのチェック<BR>
     *  2-1) this.顧客コード==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     *  2-2) this.顧客コードの桁数!=6桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F6212E0197
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) 部店コードのチェック
        if(this.branchCode == null || "".equals(this.branchCode.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("部店コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        if(this.branchCode.length() != 3)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("部店コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //顧客コードのチェック
        //2-1) this.顧客コード==nullの場合、例外をスローする。
        if(this.accountCode == null || "".equals(this.accountCode.trim()))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("顧客コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //2-2) this.顧客コードの桁数!=6桁の場合、例外をスローする
        if(this.accountCode.length() != 6)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("顧客コードエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
