head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更完了リクエスト(WEB3AdminSrvRegiCustomerChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3PaymentDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (サービス利用管理者顧客変更完了リクエスト)<BR>
 * サービス利用管理者顧客変更完了リクエストクラス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerChangeCompleteRequest extends WEB3GenRequest
{

    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiCustomerChangeCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;

    /**
     * (サービス区分)
     */
    public String serviceDiv;

    /**
     * (変更顧客一覧)
     */
    public WEB3AdminSrvRegiCustomerChangeGroup[] chgCustomerList;

    /**
     * (暗証番号)
     */
    public String password;

    /**
     * (サービス利用管理者顧客変更完了リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40F2330C0232
     */
    public WEB3AdminSrvRegiCustomerChangeCompleteRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * サービス利用管理者顧客変更完了レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F2330C0241
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiCustomerChangeCompleteResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス区分のチェック<BR>
     *  1-1) this.サービス区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.サービス区分の桁数!=2桁ではない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) 変更顧客一覧のチェック<BR>
     * 　@this.変更顧客一覧の件数分、以下のチェックを行う。<BR>
     * 　@（nullの場合、または要素数が0の場合、例外をスローする。）<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00893<BR>
     *  2-1) this.変更顧客一覧.申込登録ID==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00832<BR>
     *  2-2) this.変更顧客一覧.部店コード==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *  2-3) this.変更顧客一覧.部店コードの桁数＞3桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *  2-4) this.変更顧客一覧.顧客コードがnullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     *  2-5) this.変更顧客一覧.顧客コードの桁数＞6桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     *  2-6) this.変更顧客一覧.適用開始日がnullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     *  2-7) this.変更顧客一覧.適用終了日がnullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00838<BR>
     *  2-8) this.変更顧客一覧.適用開始日＞this.変更顧客一覧.適用終了日の場合、<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * 例外をスロー。<BR>
     *  2-9) this.変更顧客一覧.申込日==nullであり、<BR>
     * 　@　@かつthis.変更顧客一覧.申込日＞this.変更顧客一覧.適用開始日の場合、<BR>
     * 例外をスロー。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00840<BR>
     *  2-10) this.変更顧客一覧.登録区分=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01834<BR>
     *  2-11) this.変更顧客一覧.登録区分!=nullであり、かつ以下の値以外の場合、<BR>
     * 例外をスローする。<BR>
     * 　@　@　@"有料"<BR>
     * 　@　@　@"無料"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00841<BR>
     *  2-12) this.変更顧客一覧.利用料金!=nullであり、かつ数値以外の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00842<BR>
     *  2-13) this.変更顧客一覧.利用料金!=nullであり、かつthis.変更顧客一覧.利用料金の<BR>
     * 　@　@桁数＞9桁の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00843<BR>
     *  2-14) this.変更顧客一覧.抽選区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@"無"<BR>
     * 　@　@　@"有"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00844<BR>
     *  2-15) this.変更顧客一覧.申込抽選区分==nullの場合、<BR>
     * 　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00845<BR>
     *  2-16) this.変更顧客一覧.抽選区分="無"の場合、<BR>
     *       this.変更顧客一覧.申込抽選区分が以下の値以外の場合、<BR>
     *       例外をスローする。<BR>
     *        "試用" <BR>
     *        "本申込" <BR>
     *        "取消"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     *  2-17) this.変更顧客一覧.抽選区分="有"の場合、<BR>
     *        this.変更顧客一覧.申込抽選区分が以下の値以外の場合、<BR>
     *        例外をスローする。<BR>
     *        "本申込" <BR>
     *        "落選"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     *  2-18) this.抽選区分!="無"であり、かつthis.申込日==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00847<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F2330C0261
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

        //1-2) this.サービス区分の桁数!=2桁ではない場合、例外をスローする。
        if (this.serviceDiv.length() != 2)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス区分の桁数.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //2) 変更顧客一覧のチェック
        if (this.chgCustomerList == null || this.chgCustomerList.length == 0)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00893,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("変更顧客一覧.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;

        }
        else
        {
            int l_intArrayLengh = this.chgCustomerList.length;
            for (int i = 0; i< l_intArrayLengh; i++)
            {
                //2-1) this.変更顧客一覧.申込登録ID==nullの場合、例外をスローする。
                if (this.chgCustomerList[i].applyRegId == null || "".equals(this.chgCustomerList[i].applyRegId.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00832,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.申込登録ID.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-2) this.変更顧客一覧.部店コード==nullの場合、例外をスローする。
                if (this.chgCustomerList[i].branchCode == null || "".equals(this.chgCustomerList[i].branchCode.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.部店コード.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-3) this.変更顧客一覧.部店コードの桁数＞3桁の場合、例外をスローする。
                //U00871 start
                if (this.chgCustomerList[i].branchCode.length() != 3)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.部店コードの桁数.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                //U00871 end

                //2-4) this.変更顧客一覧.顧客コード==nullの場合、例外をスローする。
                if (this.chgCustomerList[i].accountCode == null || "".equals(this.chgCustomerList[i].accountCode.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.顧客コード.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-5) this.変更顧客一覧.顧客コードの桁数＞6桁の場合、例外をスローする。
                //U00871 start
                if (this.chgCustomerList[i].accountCode.length() != 6)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.顧客コードの桁数.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                //U00871 end

                //2-6) this.変更顧客一覧.適用開始日==nullの場合、例外をスローする。
                if (this.chgCustomerList[i].trialStartDate == null)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.適用開始日.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-7) this.変更顧客一覧.適用終了日==nullの場合、例外をスローする。
                if (this.chgCustomerList[i].trialEndDate == null)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00838,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.適用終了日.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-8) this.変更顧客一覧.適用開始日＞this.変更顧客一覧.適用終了日の場合、例外をスロー。
                if (WEB3DateUtility.compareToSecond(this.chgCustomerList[i].trialStartDate,
                    this.chgCustomerList[i].trialEndDate) > 0)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.適用開始日＞変更顧客一覧.適用終了日.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-9) this.変更顧客一覧.申込日!=nullであり、
                //かつthis.変更顧客一覧.申込日＞this.変更顧客一覧.適用開始日の場合、例外をスロー。
                if (this.chgCustomerList[i].applyDate != null &&
                    WEB3DateUtility.compareToSecond(this.chgCustomerList[i].applyDate,
                        this.chgCustomerList[i].trialStartDate) > 0)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00840,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.申込日＞変更顧客一覧.適用開始日.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-10) this.変更顧客一覧.登録区分=nullの場合、例外をスローする。
                if(this.chgCustomerList[i].registDiv == null || "".equals(this.chgCustomerList[i].registDiv.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01834,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("this.変更顧客一覧.登録区分=nullの場合.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-11) this.変更顧客一覧.登録区分!=nullであり、
                //かつ以下の値以外の場合、例外をスローする。
                //"有料" "無料"
                if (this.chgCustomerList[i].registDiv != null &&
                    !"".equals(this.chgCustomerList[i].registDiv.trim())
                    && !WEB3PaymentDivDef.CHARGE.equals(this.chgCustomerList[i].registDiv)
                    && !WEB3PaymentDivDef.FREE.equals(this.chgCustomerList[i].registDiv))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.登録区分.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-12) this.変更顧客一覧.利用料金!=nullであり、かつ数値以外の場合、例外をスローする。<BR>
                if (this.chgCustomerList[i].chargeAmt != null &&
                    !"".equals(this.chgCustomerList[i].chargeAmt.trim())
                    && !WEB3StringTypeUtility.isNumber(this.chgCustomerList[i].chargeAmt))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00842,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.利用料金.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                // 2-13) this.変更顧客一覧.利用料金!=nullであり、かつthis.変更顧客一覧.利用料金の
                //桁数＞9桁の場合、例外をスローする。
                if (this.chgCustomerList[i].chargeAmt != null &&
                    !"".equals(this.chgCustomerList[i].chargeAmt.trim())
                    && this.chgCustomerList[i].chargeAmt.length() > 9)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00843,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.利用料金. 変更顧客一覧.利用料金の桁数＞9桁", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-14) this.変更顧客一覧.抽選区分が以下の値以外の場合、例外をスローする。
                //"無"
                //"有"
                if (!WEB3ConditionsValueDivDef.HAVE.equals(this.chgCustomerList[i].lotteryDiv)
                    && !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.chgCustomerList[i].lotteryDiv))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00844,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.抽選区分", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-15) this.変更顧客一覧.申込抽選区分==nullの場合、例外をスローする。
                if (this.chgCustomerList[i].applyLotteryDiv == null || "".equals(this.chgCustomerList[i].applyLotteryDiv.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00845,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("変更顧客一覧.抽選区分 変更顧客一覧.申込抽選区分", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-16) this.変更顧客一覧.抽選区分="無"の場合、this.変更顧客一覧.申込抽選区分が以下の値以外の場合、例外をスローする。
                // "試用"
                // "本申込"
                // "取消"
                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.chgCustomerList[i].lotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.TRIAL_APPLI.equals(this.chgCustomerList[i].applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(this.chgCustomerList[i].applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.CANCEL.equals(this.chgCustomerList[i].applyLotteryDiv))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("抽選無 申込抽選区分 試用,本申込,取消　@以外", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                 //2-17) this.変更顧客一覧.抽選区分="有"の場合、this.変更顧客一覧.申込抽選区分が以下の値以外の場合、例外をスローする。
                 // "本申込"
                 // "落選"
                if (WEB3ConditionsValueDivDef.HAVE.equals(this.chgCustomerList[i].lotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(this.chgCustomerList[i].applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.DEFEAT.equals(this.chgCustomerList[i].applyLotteryDiv))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("抽選有 申込抽選区分 本申込,落選　@以外", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-18) this.抽選区分!="無"であり、かつthis.申込日==nullの場合、例外をスローする。
                if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.chgCustomerList[i].lotteryDiv)
                    && this.chgCustomerList[i].applyDate == null)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00847,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("抽選区分 申込日", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
