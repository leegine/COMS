head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客登録共通リクエスト(WEB3AdminSrvRegiCustomerRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 李頴淵 新規作成
*/

package webbroker3.srvregi.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PaymentDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (サービス利用管理者顧客登録共通リクエスト)<BR>
 * サービス利用管理者顧客登録変更リクエスト共通情報クラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerRegistCommonRequest extends WEB3GenRequest 
{
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminSrvRegiCustomerRegistCommonRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerRegistCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151453L;
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (部店コード)
     */
    public String branchCode;
    
    /**
     * (顧客コード)
     */
    public String accountCode;
    
    /**
     * (申込日)
     */
    public Date applyDate;
    
    /**
     * (適用開始日)
     */
    public Date trialStartDate;
    
    /**
     * (適用終了日)
     */
    public Date trialEndDate;
    
    /**
     * (登録区分)<BR>
     * 0:有料　@1:無料<BR>
     */
    public String registDiv;
    
    /**
     * (利用料金)
     */
    public String chargeAmt;
    
    /**
     * (出金日)
     */
    public Date paymentDate;
    
    /**
     * (サービス利用管理者顧客登録変更リクエスト共通情報)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F2318600DA
     */
    public WEB3AdminSrvRegiCustomerRegistCommonRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス区分のチェック<BR>
     *  1-1) this.サービス区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.サービス区分の桁数が2桁ではない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) 部店コードのチェック<BR>
     *  2-1) this.部店コード==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *  2-2) this.部店コードの桁数＞3桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * 3) 顧客コードのチェック<BR>
     *  3-1) this.顧客コード==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     *  3-2) this.顧客コードの桁数＞6桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * <BR>
     * 4) 適用開始日のチェック<BR>
     * 　@this.適用開始日==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     * <BR>
     * 5) 適用終了日のチェック<BR>
     *  5-1) this.適用終了日==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00838<BR>
     *  5-2) this.適用終了日が、this.適用開始日より以前の日付だった場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * <BR>
     * 6) 利用料金のチェック<BR>
     *  6-1) this.利用料金!=nullであり、セットされている値が数値以外の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00842<BR>
     *  6-2) this.利用料金の桁数＞9桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00843<BR>
     * <BR>
     * 7) 申込日のチェック<BR>
     *  7-1) this.申込日!=nullであり、かつthis.申込日＞this.適用開始日の場合、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00840<BR>
     * 8) 出金日のチェック<BR>
     *  8-1) this.登録区分="有料"であり、かつthis.出金日==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00879<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4DCE201AE
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス区分のチェック
        //1-1) this.サービス区分==nullの場合、例外をスローする。
        if (this.serviceDiv == null || "".equals(serviceDiv.trim()))
        {
            log.debug("1-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //1-2) this.サービス区分の桁数が2桁ではない場合、例外をスローする。
        if (this.serviceDiv.length() != 2)
        {
            log.debug("1-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //2) 部店コードのチェック
        //2-1) this.部店コード==nullの場合、例外をスローする。
        if (this.branchCode == null || "".equals(branchCode.trim()))
        {
            log.debug("2-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME); 
        }

        // 2-2) this.部店コードの桁数＞3桁の場合、例外をスローする。
        //U00871 start
        if (this.branchCode.length() != 3)
        {
            log.debug("2-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                getClass().getName() + STR_METHOD_NAME); 
        }
        //U00871 end

        //3) 顧客コードのチェック
        //3-1) this.顧客コード==nullの場合、例外をスローする。
        if (this.accountCode == null || "".equals(accountCode.trim()))
        {
            log.debug("3-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + STR_METHOD_NAME); 
        }

        //3-2) this.顧客コードの桁数＞6桁の場合、例外をスローする。
        //U00871 start
        if (this.accountCode.length() != 6)
        {
            log.debug("3-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + STR_METHOD_NAME); 
        }
        //U00871 end

        // 4) 適用開始日のチェック
        //this.適用開始日==nullの場合、例外をスローする。
        if (this.trialStartDate == null)
        {
            log.debug("4)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                getClass().getName() + STR_METHOD_NAME);
        }

        //5) 適用終了日のチェック
        //5-1) this.適用終了日==nullの場合、例外をスローする。
        if (this.trialEndDate == null)
        {
            log.debug("5-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00838,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 5-2) this.適用終了日が、this.適用開始日より以前の日付だった場合、]
        //U00892
        if (WEB3DateUtility.compareToSecond(this.trialEndDate, this.trialStartDate) < 0)
        {
            log.debug("5-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6) 利用料金のチェック
        //6-1) this.利用料金!=nullであり、セットされている値が数値以外の場合、
        if (this.chargeAmt != null && !"".equals(chargeAmt.trim()) &&
            !WEB3StringTypeUtility.isNumber(this.chargeAmt))
        {
            log.debug("6-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00842,
                getClass().getName() + STR_METHOD_NAME);
        }

        //6-2) this.利用料金の桁数＞9桁の場合、例外をスローする。
        if (chargeAmt != null && this.chargeAmt.length() > 9)
        {
            log.debug("6-2)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00843,
                getClass().getName() + STR_METHOD_NAME);
        }

        //7) 申込日のチェック       
        // 7-1)  this.申込日!=nullであり、かつthis.申込日＞this.適用開始日の場合、例外をスローする。
        if (this.applyDate != null &&
            WEB3DateUtility.compareToSecond(this.applyDate, this.trialStartDate) > 0)
        {
            log.debug("7-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00840,
                getClass().getName() + STR_METHOD_NAME);
        }

        // 8) 出金日のチェック<BR>
        //8-1) this.登録区分="有料オ"であり、かつthis.出金日==nullの場合、例外をスローする。
        if (WEB3PaymentDivDef.CHARGE.equals(this.registDiv) && this.paymentDate == null)
        {
            log.debug("8-1)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00879,
                getClass().getName() + STR_METHOD_NAME);
        }
                
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 416F49840196
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
