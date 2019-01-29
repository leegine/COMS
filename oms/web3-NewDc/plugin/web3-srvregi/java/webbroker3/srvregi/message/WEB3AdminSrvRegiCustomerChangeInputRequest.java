head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更入力リクエスト(WEB3AdminSrvRegiCustomerChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客変更入力リクエスト)<BR>
 * サービス利用管理者顧客変更入力リクエストクラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerChangeInputRequest extends WEB3GenRequest 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiCustomerChangeInputRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (検索条件)
     */
    public WEB3AdminSrvRegiCustomerSearchCondition[ ] searchConditions;
    
    /**
     * (サービス利用管理者顧客変更入力リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40EE585301EA
     */
    public WEB3AdminSrvRegiCustomerChangeInputRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者顧客変更入力レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F4EC5F0298
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiCustomerChangeInputResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス区分のチェック<BR>
     *  1-1) this.サービス区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.サービス区分の桁数!=2桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) 検索条件のチェック<BR>
     * 　@this.検索条件の件数分、以下をチェックする。<BR>
     * 　@（nullの場合、または要素数が0の場合、例外をスローする。）<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00945<BR>
     *  2-1) this.検索条件.申込登録ID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00832<BR>
     *  2-2) this.検索条件.部店コード==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *  2-3) this.検索条件.部店コードの桁数!=3桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *  2-4) this.検索条件.顧客コード==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     *  2-5) this.検索条件.顧客コードの桁数!=6桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4EC7D01EC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス区分のチェック
        //1-1) this.サービス区分==nullの場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス区分.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //1-2) this.サービス区分の桁数!=2桁の場合、例外をスローする。
        if (this.serviceDiv.length() != 2)
        {         
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス区分の桁数.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //2) 検索条件のチェック
        if (this.searchConditions == null || this.searchConditions.length == 0)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00945,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("検索条件.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;            
        }
        else
        {
            int l_intArrayLengh = this.searchConditions.length;
            for (int i = 0; i < l_intArrayLengh; i++)
            {
                //2-1) this.検索条件.申込登録ID==nullの場合、例外をスローする。
                if (this.searchConditions[i].applyRegId == null || 
                    "".equals(this.searchConditions[i].applyRegId.trim()))
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00832,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("検索条件.申込登録ID.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                
                //2-2) this.検索条件.部店コード==nullの場合、例外をスローする。
                if (this.searchConditions[i].branchCode == null
                    || "".equals(this.searchConditions[i].branchCode.trim()))
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("検索条件.部店コード.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                
                //2-3) this.検索条件.部店コードの桁数!=3桁の場合、例外をスローする。
                if (this.searchConditions[i].branchCode.length() != 3)
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("検索条件.部店コードの桁数.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                
                //2-4) this.検索条件.顧客コード==nullの場合、例外をスローする。
                if (this.searchConditions[i].accountCode == null ||
                    "".equals(this.searchConditions[i].accountCode.trim()))
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("検索条件.顧客コード.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                                
                //2-5) this.検索条件.顧客コードの桁数!=6桁の場合、例外をスローする。
                if (this.searchConditions[i].accountCode.length() != 6)
                {         
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("検索条件.顧客コードの桁数.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }               
            }            
        }       
        log.exiting(STR_METHOD_NAME);
    }
}
@
