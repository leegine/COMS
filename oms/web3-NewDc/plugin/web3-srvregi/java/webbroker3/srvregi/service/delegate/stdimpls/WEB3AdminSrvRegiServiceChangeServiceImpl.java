head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス変更サービスImpl(WEB3AdminSrvRegiServiceChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
Revesion History : 2005/10/05 鈴木　@美由紀(SRA) トランスリンク対応
Revesion History : 2005/10/18 鈴木　@美由紀(SRA) フィデリティ対応
Revesion History : 2007/06/05 孫洪江(中訊) 仕様変更モデルNo.251  ＤＢ更新仕様 038
Revesion History : 2008/03/14 松井　@亮二  (SCS) QTP連携対応
Revesion History : 2008/05/20 車進 (中訊) モデル372
Revesion History : 2008/07/18 武波 (中訊) モデル397
Revesion History : 2009/04/23 車進 (中訊) モデル412
Revesion History : 2009/05/20 柴双紅(中訊) モデル417
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3SrvStatusDef;
import webbroker3.common.define.WEB3SrvUseKeyTypeDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiCommCond;
import webbroker3.srvregi.WEB3SrvRegiConsDoc;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvRegiCommCondRow;
import webbroker3.srvregi.define.WEB3SrvRegiHashCalOrderDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMailDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiReservedWordDef;
import webbroker3.srvregi.define.WEB3SrvRegiSendHowToDivDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCommissionCondition;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.message.WEB3SrvRegiExecKey;
import webbroker3.srvregi.message.WEB3SrvRegiLotteryInfo;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者サービス変更サービスImpl)<BR>
 * サービス利用管理者サービス変更サービス実装クラス<BR>
 *
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceChangeServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiServiceChangeService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceChangeServiceImpl.class);

    /**
     * @@roseuid 416F392B01D4
     */
    public WEB3AdminSrvRegiServiceChangeServiceImpl()
    {

    }

    /**
     * サービス利用管理者サービス変更処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate変更( )または、<BR>
     * submit変更( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F514E001CD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";

        log.entering(STR_METHOD_NAME);

        //1.1:<l_request instanceof サービス利用管理者サービス変更確認リクエストの場合>
        if (l_request instanceof WEB3AdminSrvRegiServiceChangeConfirmRequest)
        {
            //1.1.1:validate変更(サービス利用管理者サービス変更確認リクエスト)
            log.debug(" WEB3AdminSrvRegiServiceChangeConfirmRequest ");
            WEB3AdminSrvRegiServiceChangeConfirmResponse l_serviceChangeConfirmResponse = validateChange(
                (WEB3AdminSrvRegiServiceChangeConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_serviceChangeConfirmResponse;
        }
        else if (l_request instanceof WEB3AdminSrvRegiServiceChangeCompleteRequest)
        //1.2:<l_request instanceof サービス利用管理者サービス変更完了リクエストの場合>
        {
            //1.2.1:submit変更(サービス利用管理者サービス変更完了リクエスト)
            log.debug(" WEB3AdminSrvRegiServiceChangeCompleteRequest ");
            WEB3AdminSrvRegiServiceChangeCompleteResponse l_serviceChangeCompleteResponse = submitChange(
                (WEB3AdminSrvRegiServiceChangeCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_serviceChangeCompleteResponse;
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

    /**
     * (validate変更)<BR>
     * サービス利用管理者サービス変更審査処理を行う。<BR>
     * <BR>
     *  ========================================================<BR>
     *  シーケンス図(「管理者サービス変更 / （サービス利用管理者）サービス変更審査」): <BR>
     *          1.8.2.2. get確認メール情報()の戻り値=nullの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01823<BR>
     *  ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *  シーケンス図(「管理者サービス変更 / （サービス利用管理者）サービス変更審査」): <BR>
     *          1.8.3.2. get契約期限メール情報()の戻り値=nullの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01824<BR>
     *  ==========================================================<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）サービス変更審査」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス変更審査」): <BR>
     *         1.8.1.1.1.<分岐処理 *3><BR>
     *         <分岐処理 *3><BR>
     *          DIR管理者ではない場合、現在申込期間中の抽選情報の編集は不可。<BR>
     *          （管理者オブジェクト.isDIR管理者( )=falseであり、<BR>
     *          リクエストデータ.募集期間情報.申込期間（自）≦現在日時の場合、例外をスローする。）<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00988<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス変更審査」): <BR>
     *         1.8.1.1.4.<分岐処理 *4><BR>
     *           <分岐処理 *4><BR>
     *          過去の抽選情報の変更は不可。<BR>
     *          （リクエストデータ.募集期間情報.申込期間（至）≦現在日時の場合、例外をスローする。） <BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00989<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス変更審査」): <BR>
     *         1.8.1.1.3.isBizDate(Timestamp)<BR>
     *         出金日に営業日が指定されているかチェックする。<BR>
     *         is営業日( )=falseの場合、例外をスローする。<BR>
     *         class:WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00990<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス変更確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F514F301EC
     */
    protected WEB3AdminSrvRegiServiceChangeConfirmResponse validateChange(WEB3AdminSrvRegiServiceChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminSrvRegiServiceChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1:validate( )
        l_request.validate();

        log.debug("validate request over!");

        //1.3:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, true);

        //1.5:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.6:isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //1.7:<現在日時の取得>
        Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        log.debug("1.7:<現在日時の取得>:over");

        //1.8:＜リクエストデータ.ステータス!="停止中"の場合＞
        if (!WEB3SrvStatusDef.STOP.equals(l_request.serviceStatus))
        {
            //1.8.1:getサービスマスター(String, String, boolean)
            WEB3SrvRegiServiceInfoManagement l_infoManager = new WEB3SrvRegiServiceInfoManagement();

            WEB3SrvRegiServiceMaster l_srvMaster = l_infoManager.getSrvMaster(
                l_strInstitutionCode, l_request.serviceDiv, false);
            //1.8.2:＜リクエストデータ.メール区分（確認メール）="送信する"の場合＞
            if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_request.confirmMailDiv))
            {
                //1.8.2.1:get確認メール情報( )
                //1.8.2.2＜get確認メール情報()の戻り値=nullの場合、例外をスローする＞
                if (l_srvMaster.getConfirmMailInfo() == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01823,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            //1.8.3:＜リクエストデータ.メール区分（契約期限メール）="送信する"の場合＞
            if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_request.noticeMailDiv))
            {
                //1.8.2.1:get契約期限メール情報( )
                //1.8.2.2＜get契約期限メール情報()の戻り値=nullの場合、例外をスローする＞
                if (l_srvMaster.getEndMaiDivInfo() == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01824,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //1.9:<分岐処理 *1>
        if (l_request.applyInfo != null)
        {
            log.debug("1.9:<分岐処理 *1>:start");
            //1.9.1:<繰り返し処理 *1>
            WEB3SrvRegiLotteryInfo l_lotInfo = null;
            int l_intApplyInfoCnt = l_request.applyInfo.length;
            log.debug("l_intApplyInfoCnt:" + l_intApplyInfoCnt);
            for (int i = 0; i < l_intApplyInfoCnt; i++)
            {
                log.debug("loop count:" + i);
                l_lotInfo = l_request.applyInfo[i];
                //1.9.1.1:<分岐処理 *2>
                if (!l_lotInfo.invalidDiv)
                {
                    log.debug("1.9.1.1:<分岐処理 *2>");
                    //1.9.1.1.1:<分岐処理 *3>
                    if (!l_blnIsDirAdmin)
                    {
                        log.debug("1.9.1.1.1:<分岐処理 *3>");

                        if (WEB3DateUtility.compareToMinute(l_tsNowDate, l_lotInfo.applyStartDate) >= 0)
                        {
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00988,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }

                    //1.9.1.1.2:<分岐処理 *4>
                    if (WEB3DateUtility.compareToMinute(l_tsNowDate, l_lotInfo.applyEndDate) >= 0)
                    {
                        log.debug("1.9.1.1.2:<分岐処理 *4>");
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00989,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //1.9.1.1.3:is営業日(Timestamp)
                    Timestamp l_tsPaymentDate = new Timestamp(l_lotInfo.paymentDate.getTime());
                    if (!WEB3SrvRegiTradingTimeManagement.isBizDate(l_tsPaymentDate))
                    {
                        log.debug("1.9.1.1.3:is営業日(Timestamp)=false");

                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00990,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //1.9.1.1.4:<期間チェック>
                    WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();

                    //1.9.1.1.4.1:validate申込期間(String, String, Timestamp, Timestamp)
                    Timestamp l_tsApplyStartDate = new Timestamp(l_lotInfo.applyStartDate.getTime());
                    Timestamp l_tsApplyEndDate = new Timestamp(l_lotInfo.applyEndDate.getTime());
                    Long l_lngLotInfoId = null;
                    if (l_lotInfo.lotteryId != null)
                    {
                        l_lngLotInfoId = new Long(l_lotInfo.lotteryId);
                    }

                    log.debug("1.9.1.1.4.1:validate申込期間(String, String, Long, Timestamp, Timestamp)");

                    l_srvInfoManagement.validateAppliDate(l_strInstitutionCode, l_request.serviceDiv,
                        l_lngLotInfoId,
                        l_tsApplyStartDate, l_tsApplyEndDate);

                    //1.9.1.1.4.2:validate適用期間(String, String, Timestamp, Timestamp)
                    Timestamp l_trTialStartDate = new Timestamp(l_lotInfo.trialStartDate.getTime());
                    Timestamp l_tsTrialEndDate = new Timestamp(l_lotInfo.trialEndDate.getTime());

                    log.debug("1.9.1.1.4.2:validate適用期間(String, String, Timestamp, Timestamp)");

                    l_srvInfoManagement.validateAppliPeriod(l_strInstitutionCode, l_request.serviceDiv,
                        l_lngLotInfoId,
                        l_trTialStartDate, l_tsTrialEndDate);
                }
            }
        }
		
		//障害対応 NO_U02018
        //1.10: ＜リクエストデータ.ステータス!="停止中"、かつisDir管理者＝"true"の場合＞
        if (!WEB3SrvStatusDef.STOP.equals(l_request.serviceStatus) && l_blnIsDirAdmin)
        {
        	log.debug("1.10:isDir管理者=true && serviceStatus!=stop");
            //1.10.1:validateサービス利用キー(String, String, String, String, String,
            //String, String, サービス利用起動キー[ ], サービス利用起動キー[ ])
            this.validateSrvUseKey(l_request.url,
                l_request.url2,
                l_request.hashCalHowToDiv,
                l_request.hashCalOrderDiv,
                l_request.sendHowToDiv,
                l_request.sendParamDiv,
                l_request.cryptAccountCodeDiv,
                l_request.hashList,
                l_request.paramList);
        }

        log.exiting(STR_METHOD_NAME);

        WEB3AdminSrvRegiServiceChangeConfirmResponse l_response = (WEB3AdminSrvRegiServiceChangeConfirmResponse)l_request.createResponse();
        return l_response;

    }

    /**
     * (submit変更)<BR>
     * サービス利用管理者サービス変更処理を行う。<BR>
     * <BR>
     *  ========================================================<BR>
     *  シーケンス図(「管理者サービス変更 / （サービス利用管理者）サービス変更」): <BR>
     *          1.9.2.2. get確認メール情報()の戻り値=nullの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01823<BR>
     *  ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     *  シーケンス図(「管理者サービス変更 / （サービス利用管理者）サービス変更」): <BR>
     *          1.9.3.2. get契約期限メール情報()の戻り値=nullの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01824<BR>
     *  ==========================================================<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）サービス変更」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス変更」): <BR>
     *         1.9.1.1.1.<分岐処理 *3>  <BR>
     *         <分岐処理 *3><BR>
     *         DIR管理者ではない場合、現在申込期間中の抽選情報の編集は不可。<BR>
     *         （管理者オブジェクト.isDIR管理者( )=falseであり、<BR>
     *       　@リクエストデータ.募集期間情報.申込期間（自）≦現在日時の場合、例外をスローする。）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00988<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス変更」): <BR>
     *         1.9.1.1.2.<分岐処理 *4> <BR>
     *          <分岐処理 *4><BR>
     *          過去の抽選情報の変更は不可。<BR>
     *          （リクエストデータ.募集期間情報.申込期間（至）≦現在日時の場合、例外をスローする。）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00989<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用管理者）サービス変更」): <BR>
     *         1.9.1.1.3.isBizDate(Timestamp)<BR>
     *          出金日に営業日が指定されているかチェックする。<BR>
     *          is営業日( )=falseの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00990<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス変更完了リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F514F80160
     */
    protected WEB3AdminSrvRegiServiceChangeCompleteResponse submitChange(WEB3AdminSrvRegiServiceChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminSrvRegiServiceChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1:validate( )
        l_request.validate();

        //1.3:getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4:validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_SERVICE, true);

        //1.5:validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);

        //1.6:get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.7:isDIR管理者( )
        boolean l_blnIsDirAdmin = l_admin.isDirAdministrator();

        //1.8:<現在日時の取得>
        Timestamp l_tsNowDate = GtlUtils.getTradingSystem().getSystemTimestamp();

        log.debug("1.8:<現在日時の取得> over");

        //1.9:＜リクエストデータ.ステータス!="停止中"の場合＞
        if (!WEB3SrvStatusDef.STOP.equals(l_request.serviceStatus))
        {
            //1.9.1:getサービスマスター(String, String, boolean)
            WEB3SrvRegiServiceInfoManagement l_infoManager = new WEB3SrvRegiServiceInfoManagement();

            WEB3SrvRegiServiceMaster l_srvMaster = l_infoManager.getSrvMaster(
                l_strInstitutionCode, l_request.serviceDiv, false);
            //1.9.2:＜リクエストデータ.メール区分（確認メール）="送信する"の場合＞
            if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_request.confirmMailDiv))
            {
                //1.9.2.1:get確認メール情報( )
                //1.9.2.2＜get確認メール情報()の戻り値=nullの場合、例外をスローする＞
                if (l_srvMaster.getConfirmMailInfo() == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01823,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }

            //1.9.3:＜リクエストデータ.メール区分（契約期限メール）="送信する"の場合＞
            if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_request.noticeMailDiv))
            {
                //1.9.2.1:get契約期限メール情報( )
                //1.9.2.2＜get契約期限メール情報()の戻り値=nullの場合、例外をスローする＞
                if (l_srvMaster.getEndMaiDivInfo() == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01824,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //1.10:<分岐処理 *1>
        if (l_request.applyInfo != null)
        {
            log.debug("1.10:<分岐処理 *1> start");
            WEB3SrvRegiLotteryInfo l_lotInfo = null;
            int l_intAppliInfoCnt = l_request.applyInfo.length;
            log.debug("l_intAppliInfoCnt:" + l_intAppliInfoCnt);
            //1.10.1:<繰り返し処理 *1>
            for (int i = 0; i < l_intAppliInfoCnt; i++)
            {
                log.debug("loop count:" + i);
                l_lotInfo = l_request.applyInfo[i];
                //1.10.1.1:<分岐処理 *2>
                if (!l_lotInfo.invalidDiv)
                {
                    log.debug("1.10.1.1:<分岐処理 *2>");
                    //1.10.1.1.1:<分岐処理 *3>
                    if (!l_blnIsDirAdmin)
                    {
                        log.debug("l_blnIsDirAdmin=false");
                        if ( WEB3DateUtility.compareToMinute(l_tsNowDate, l_lotInfo.applyStartDate) >= 0)
                        {
                            log.debug("1.10.1.1.1:<分岐処理 *3> throw execption");
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_00988,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }

                    //1.10.1.1.2:<分岐処理 *4>
                    if (WEB3DateUtility.compareToMinute(l_tsNowDate, l_lotInfo.applyEndDate) >= 0)
                    {
                        log.debug("1.10.1.1.2:<分岐処理 *3> throw execption");
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00989,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //1.10.1.1.3:is営業日(Timestamp)
                    Timestamp l_tsPaymentDate = new Timestamp(l_lotInfo.paymentDate.getTime());
                    if (!WEB3SrvRegiTradingTimeManagement.isBizDate(l_tsPaymentDate))
                    {
                        log.debug("1.10.1.1.3:is営業日(Timestamp) throw execption");
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00990,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //1.10.1.1.4:<期間チェック>
                    WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();

                    //1.10.1.1.4.1:validate申込期間(String, String, Timestamp, Timestamp)
                    Timestamp l_tsApplyStartDate = new Timestamp(l_lotInfo.applyStartDate.getTime());
                    Timestamp l_tsApplyEndDate = new Timestamp(l_lotInfo.applyEndDate.getTime());
                    Long l_lngLotInfoId = null;
                    if (l_lotInfo.lotteryId != null)
                    {
                        l_lngLotInfoId = new Long(l_lotInfo.lotteryId);
                    }

                    log.debug("1.10.1.1.4.1:validate申込期間(String, String, Long, Timestamp, Timestamp)");
                    l_srvInfoManagement.validateAppliDate(l_strInstitutionCode, l_request.serviceDiv,
                        l_lngLotInfoId,
                        l_tsApplyStartDate, l_tsApplyEndDate);

                    //1.10.1.1.4.2:validate適用期間(String, String, Timestamp, Timestamp)
                    Timestamp l_trTialStartDate = new Timestamp(l_lotInfo.trialStartDate.getTime());
                    Timestamp l_tsTrialEndDate = new Timestamp(l_lotInfo.trialEndDate.getTime());

                    log.debug("1.10.1.1.4.2:validate適用期間(String, String, Timestamp, Timestamp)");
                    l_srvInfoManagement.validateAppliPeriod(l_strInstitutionCode, l_request.serviceDiv,
                        l_lngLotInfoId,
                        l_trTialStartDate, l_tsTrialEndDate);
                }
            }
            log.debug("1.10:<分岐処理 *1> over");
        }

		//障害対応 NO_U02018
		//1.11: ＜リクエストデータ.ステータス!="停止中"、かつisDir管理者＝"true"の場合＞
        if (!WEB3SrvStatusDef.STOP.equals(l_request.serviceStatus))
        {
			log.debug("1.11.1:isDir管理者=true && serviceStatus!=stop");
            //1.11.1:validateサービス利用キー(String, String, String, String, String,
            //String, String, サービス利用起動キー[ ], サービス利用起動キー[ ])
            this.validateSrvUseKey(l_request.url,
                l_request.url2,
                l_request.hashCalHowToDiv,
                l_request.hashCalOrderDiv,
                l_request.sendHowToDiv,
                l_request.sendParamDiv,
                l_request.cryptAccountCodeDiv,
                l_request.hashList,
                l_request.paramList);
        }

        //1.10:<更新処理>
        //1:getサービスマスター(String, String, boolean)
        log.debug("1:getサービスマスター(String, String, boolean)");

        WEB3SrvRegiServiceInfoManagement l_srvInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvMaster =
            l_srvInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, true);

        //1.1:<サービスマスターオブジェクトへのプロパティ・セット>
        log.debug("1.1:<サービスマスターオブジェクトへのプロパティ・セット>");

        if (l_blnIsDirAdmin)
        {
            l_srvMaster.setSrvName(l_request.serviceName);
            l_srvMaster.setSrvUrl(l_request.url);
        }

        l_srvMaster.setStatus(l_request.serviceStatus);

        //1.1.1:saveサービスマスター( )
        l_srvMaster.saveSrvMaster();

        boolean l_blnIsAppliRequired = l_srvMaster.isAppliRequired();

        if (l_blnIsAppliRequired)
        {
            //2:get申込要サービス(boolean)
            log.debug("2:get申込要サービス(boolean)");
            WEB3SrvRegiApplicationRequiredService l_appliRequireSrv = l_srvMaster.getAppliRequiredSrv(true);

            if (l_appliRequireSrv == null)
            {
                log.debug("2:get申込要サービス(boolean)=null");

                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            log.debug("2.1:<申込要サービスオブジェクトへのプロパティ・セット>");
            //2.1:<申込要サービスオブジェクトへのプロパティ・セット>
            l_appliRequireSrv.setSummary(l_request.summary);

            l_appliRequireSrv.setTrialPeriodDiv(l_request.trialDiv);
            if (l_request.trialPeriod == null)
            {
                l_appliRequireSrv.setTrialPeriod(null);
            }
            else
            {
                l_appliRequireSrv.setTrialPeriod(new Integer(l_request.trialPeriod));
            }

            if (l_request.applyAbleStartDate == null)
            {
                l_appliRequireSrv.setAppliDateFrom(null);
            }
            else
            {
                l_appliRequireSrv.setAppliDateFrom(new Integer(l_request.applyAbleStartDate));
            }

            if (l_request.applyAbleEndDate == null)
            {
                l_appliRequireSrv.setAppliDateTo(null);
            }
            else
            {
                l_appliRequireSrv.setAppliDateTo(new Integer(l_request.applyAbleEndDate));
            }

            l_appliRequireSrv.setSrvContents(l_request.serviceContent);
            
            l_appliRequireSrv.setSrvExplanUrl(l_request.explainURL);

            l_appliRequireSrv.setStartMailDiv(l_request.confirmMailDiv);
            
            l_appliRequireSrv.setEndMailDiv(l_request.noticeMailDiv);

            if (l_request.noticeMailDate == null)
            {
                l_appliRequireSrv.setSendMailInterval(null);
            }
            else
            {
                l_appliRequireSrv.setSendMailInterval(new Integer(l_request.noticeMailDate));
            }
                l_appliRequireSrv.setElectricIssueDiv(l_request.elePigeonDiv);

            //無料対象期間=リクエストデータ.無料対象期間
            if (l_request.freeTargetPeriod == null)
            {
            	l_appliRequireSrv.setFreeTargetPeriod(null);
            }
            else
            {
                l_appliRequireSrv.setFreeTargetPeriod(l_request.freeTargetPeriod);
            }

            if (l_blnIsDirAdmin)
            {
                //(*) リクエストデータ.提供区分!=nullの場合
                if (l_request.offerType != null)
                {
                    log.debug("リクエストデータ.提供区分!=nullの場合");

                    //提供区分=リクエストデータ.提供区分
                    l_appliRequireSrv.setSupplyDiv(l_request.offerType);
                    //手数料条件基準合計額=リクエストデータ.手数料条件基準合計額
                    l_appliRequireSrv.setMinCommAmt(Double.parseDouble(l_request.commissionAttainTotal));
                }
                //(*) リクエストデータ.提供区分=nullの場合
                else
                {
                    log.debug("リクエストデータ.提供区分=nullの場合");

                    //提供区分=null
                    l_appliRequireSrv.setSupplyDiv(null);
                    //手数料条件基準合計額=0
                    l_appliRequireSrv.setMinCommAmt(0);
                }
            }

            //2.1.1:save申込要サービス( )
            l_appliRequireSrv.saveAppliRequiredSrv();
        }

        //3:get同意書文言(boolean)
        WEB3SrvRegiConsDoc l_consDoc = l_srvMaster.getConsDoc(true);

        //4:<get同意書文言( ).文言==nullの場合>
        if (l_consDoc.getCons() == null)
        {
            log.debug("4:<get同意書文言( )==nullの場合");

            //4.1:saveNew同意書文言(String, String, String)
            l_consDoc = new WEB3SrvRegiConsDoc();
            l_consDoc.saveNewConsDoc(l_strInstitutionCode, l_request.serviceDiv, l_request.consentSentence);
        }
        else
        //5:<get同意書文?( ).文言!=nullの場合>
        {
            log.debug("5:<get同意書文言( )!=nullの場合");

            //5.1:save同意書文言(String, String, String)
            l_consDoc.saveConsDoc(l_strInstitutionCode, l_request.serviceDiv, l_request.consentSentence);
        }

        //6:<リクエストデータ.利用期間料金情報の件数分、繰り返し>
        WEB3SrvRegiChargeInfo l_chargeInfo = null;
        if (l_request.chargeInfo != null)
        {
            log.debug("l_request.chargeInfo != null");
            int l_intChargeInfoCnt = l_request.chargeInfo.length;

            log.debug("l_intChargeInfoCnt:" + l_intChargeInfoCnt);
            for (int i=0; i < l_intChargeInfoCnt; i++)
            {
                log.debug("loop count:" + i);

                l_chargeInfo = l_request.chargeInfo[i];

                //6.1:<リクエストデータ.利用期間料金情報.無効区分="有効"の場合>
                if (!l_chargeInfo.invalidDiv)
                {
                    log.debug("6.1:<リクエストデータ.利用期間料金情報.無効区分=有効の場合");

                    String l_strChargeId = l_chargeInfo.chargeId;

                    //6.1.1:<リクエストデータ.利用期間料金情報.利用期間ID=nullの場合>
                    if (l_strChargeId == null)
                    {
                        log.debug("6.1.1:<リクエストデータ.利用期間料金情報.利用期間ID=nullの場合>");

                        //6.1.1.1:createNewサービス利用期間料金(String, String)
                        WEB3SrvRegiServiceUsePeriodAmt l_srvUsePeriodAmt =
                            WEB3SrvRegiServiceUsePeriodAmt.createNewSrvUsePeriodAmt(l_strInstitutionCode, l_request.serviceDiv);

                        //6.1.1.1.1 <サービス利用期間料金オブジェクトへのプロパティ・セット>
                        l_srvUsePeriodAmt.setSrvUsePeriodDiv(l_chargeInfo.chargeDiv);
                        l_srvUsePeriodAmt.setSrvUsePeriod(Integer.parseInt(l_chargeInfo.chargePeriod));
                        l_srvUsePeriodAmt.setUseAmt(Long.parseLong(l_chargeInfo.chargeAmt));

                        //6.1.1.1.2: saveNewサービス利用期間料金( )
                        l_srvUsePeriodAmt.saveNewSrvUsePeriodAmt();
                    }
                    else
                    //6.1.2:<リクエストデータ.利用期間料金情報.利用期間ID!=nullの場合>
                    {
                        log.debug("6.1.2:<リクエストデータ.利用期間料金情報.利用期間ID!=nullの場合>");

                        //6.1.2.1:getサービス利用期間料金(long, boolean)
                        WEB3SrvRegiServiceUsePeriodAmt l_srvUsePeriodAmt =
                            l_srvMaster.getSrvUseTermAmt(Long.parseLong(l_strChargeId), true);

                        if (l_srvUsePeriodAmt == null)
                        {
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            throw new WEB3BaseRuntimeException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                getClass().getName() + STR_METHOD_NAME);
                        }

                        //6.1.2.1.1:<サービス利用期間料金オブジェクトへのプロパティ・セット>
                        l_srvUsePeriodAmt.setSrvUsePeriodDiv(l_chargeInfo.chargeDiv);
                        l_srvUsePeriodAmt.setSrvUsePeriod(Integer.parseInt(l_chargeInfo.chargePeriod));
                        l_srvUsePeriodAmt.setUseAmt(Long.parseLong(l_chargeInfo.chargeAmt));

                        //6.1.2.1.1.1:saveサービス利用期間料金( )
                        l_srvUsePeriodAmt.saveSrvUsePeriodAmt();
                    }
                }
                //<リクエストデータ.利用期間料金情報.無効区分="無効"の場合>
                else
                {
                    //<リクエストデータ.利用期間料金情報.利用期間ID!=nullの場合>
                    String l_strChargeId = l_chargeInfo.chargeId;

                    if (l_strChargeId != null)
                    {
                        //getサービス利用期間料金(long, boolean)
                        WEB3SrvRegiServiceUsePeriodAmt l_srvUsePeriodAmt =
                            l_srvMaster.getSrvUseTermAmt(Long.parseLong(l_strChargeId), true);
                        //removeサービス利用期間料金( )
                        l_srvUsePeriodAmt.removeSrvUsePeriodAmt();
                    }
                }
            }
        }

        //7:<リクエストデータ.募集期間情報の件数分、繰り返し>
        WEB3SrvRegiLotteryInfo l_lotInfo = null;

        if (l_request.applyInfo != null)
        {
            log.debug("l_request.applyInfo != null");

            int l_intAppliInfoCnt = l_request.applyInfo.length;
            log.debug("l_intAppliInfoCnt:" + l_intAppliInfoCnt);
            for (int i=0; i < l_intAppliInfoCnt; i++)
            {
                log.debug("loop count:" + i);

                l_lotInfo = l_request.applyInfo[i];

                //7.1:<リクエストデータ.募集期間情報.無効区分="有効"の場合>
                if (!l_lotInfo.invalidDiv)
                {
                    log.debug("7.1:<リクエストデータ.募集期間情報.無効区分=有効の場合>");

                    String l_strLotInfoId = l_lotInfo.lotteryId;

                    //7.1.1:<リクエストデータ.募集期間情報.抽選情報ID=nullの場合>
                    if (l_strLotInfoId == null)
                    {
                        log.debug("7.1.1:<リクエストデータ.募集期間情報.抽選情報ID=nullの場合>");

                        //7.1.1.1:createNewサービス抽選情報(String, String)
                        WEB3SrvRegiServiceLotInfo l_srvLotInfo =
                            WEB3SrvRegiServiceLotInfo.createNewSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv);

                        //7.1.1.1.1<サービス抽選情報オブジェクトへのプロパティ・セット>
                        Timestamp l_tsApplyStartDate = new Timestamp(l_lotInfo.applyStartDate.getTime());
                        l_srvLotInfo.setAppliDateFrom(l_tsApplyStartDate);

                        Timestamp l_tsApplyEndDate = new Timestamp(l_lotInfo.applyEndDate.getTime());
                        l_srvLotInfo.setAppliDateTo(l_tsApplyEndDate);

                        if (l_lotInfo.lotteryDate == null)
                        {
                            l_srvLotInfo.setLotDate(null);
                        }
                        else
                        {
                            Timestamp l_tsLotteryDate = new Timestamp(l_lotInfo.lotteryDate.getTime());
                            l_srvLotInfo.setLotDate(l_tsLotteryDate);
                        }

                        Timestamp l_trTialStartDate = new Timestamp(l_lotInfo.trialStartDate.getTime());
                        l_srvLotInfo.setAppliStartDate(l_trTialStartDate);

                        Timestamp l_tsTrialEndDate = new Timestamp(l_lotInfo.trialEndDate.getTime());
                        l_srvLotInfo.setAppliEndDate(l_tsTrialEndDate);

                        l_srvLotInfo.setUseAmt(Long.parseLong(l_lotInfo.chargeAmt));

                        if (l_lotInfo.biddingPriceUnit == null)
                        {
                            l_srvLotInfo.setBiddingPrice(null);
                        }
                        else
                        {
                            l_srvLotInfo.setBiddingPrice(new Long(l_lotInfo.biddingPriceUnit));
                        }


                        Timestamp l_tsPaymentDate = new Timestamp(l_lotInfo.paymentDate.getTime());
                        l_srvLotInfo.setPaymentDate(l_tsPaymentDate);

                        if (l_lotInfo.applyMax == null)
                        {
                            l_srvLotInfo.setPublicOfferingQty(null);
                        }
                        else
                        {
                            l_srvLotInfo.setPublicOfferingQty(new Long(l_lotInfo.applyMax));
                        }

                        l_srvLotInfo.setInvestDiv(l_lotInfo.useDiv);
                        l_srvLotInfo.setHighSuccessBid(null);
                        l_srvLotInfo.setLowSuccessBid(null);
                        l_srvLotInfo.setAverageSuccessBid(null);

                        //7.1.1.1.2: saveNewサービス抽選情報( )
                        l_srvLotInfo.saveNewSrvLotInfo();
                    }
                    else
                    //7.1.2:<リクエストデータ.募集期間情報.抽選情報ID!=nullの場合>
                    {
                        log.debug("7.1.2:<リクエストデータ.募集期間情報.抽選情報ID!=nullの場合>");

                        //7.1.2.1:getサービス抽選情報(long, boolean)
                        WEB3SrvRegiServiceLotInfo l_srvLotInfo =
                            l_srvMaster.getSrvLotInfo(Long.parseLong(l_strLotInfoId), true);

                        if (l_srvLotInfo == null)
                        {
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            throw new WEB3BaseRuntimeException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                getClass().getName() + STR_METHOD_NAME);
                        }

                        //7.1.2.1.1:<サービス抽選情報オブジェクトへのプロパティ・セット>
                        Timestamp l_tsApplyStartDate = new Timestamp(l_lotInfo.applyStartDate.getTime());
                        l_srvLotInfo.setAppliDateFrom(l_tsApplyStartDate);

                        Timestamp l_tsApplyEndDate = new Timestamp(l_lotInfo.applyEndDate.getTime());
                        l_srvLotInfo.setAppliDateTo(l_tsApplyEndDate);

                        if (l_lotInfo.lotteryDate == null)
                        {
                            l_srvLotInfo.setLotDate(null);
                        }
                        else
                        {
                            Timestamp l_tsLotteryDate = new Timestamp(l_lotInfo.lotteryDate.getTime());
                            l_srvLotInfo.setLotDate(l_tsLotteryDate);
                        }

                        Timestamp l_trTialStartDate = new Timestamp(l_lotInfo.trialStartDate.getTime());
                        l_srvLotInfo.setAppliStartDate(l_trTialStartDate);

                        Timestamp l_tsTrialEndDate = new Timestamp(l_lotInfo.trialEndDate.getTime());
                        l_srvLotInfo.setAppliEndDate(l_tsTrialEndDate);

                        l_srvLotInfo.setUseAmt(Long.parseLong(l_lotInfo.chargeAmt));

                        if (l_lotInfo.biddingPriceUnit == null)
                        {
                            l_srvLotInfo.setBiddingPrice(null);
                        }
                        else
                        {
                            l_srvLotInfo.setBiddingPrice(new Long(l_lotInfo.biddingPriceUnit));
                        }

                        Timestamp l_tsPaymentDate = new Timestamp(l_lotInfo.paymentDate.getTime());
                        l_srvLotInfo.setPaymentDate(l_tsPaymentDate);

                        if (l_lotInfo.applyMax == null)
                        {
                            l_srvLotInfo.setPublicOfferingQty(null);
                        }
                        else
                        {
                            l_srvLotInfo.setPublicOfferingQty(new Long(l_lotInfo.applyMax));
                        }

                        l_srvLotInfo.setInvestDiv(l_lotInfo.useDiv);
                        l_srvLotInfo.setHighSuccessBid(null);
                        l_srvLotInfo.setLowSuccessBid(null);
                        l_srvLotInfo.setAverageSuccessBid(null);

                        //7.1.2.1.1.1:saveサービス抽選情報( )
                        l_srvLotInfo.saveSrvLotInfo();
                    }
                }
                // <リクエストデータ.募集期間情報.無効区分="無効"の場合>
                else
                {
                    //<リクエストデータ.募集期間情報.抽選情報ID!=nullの場合>
                    String l_strLotInfoId = l_lotInfo.lotteryId;

                    if (l_strLotInfoId != null)
                    {
                        //getサービス抽選情報(long, boolean)
                        WEB3SrvRegiServiceLotInfo l_srvLotInfo =
                            l_srvMaster.getSrvLotInfo(Long.parseLong(l_strLotInfoId), true);
                        //removeサービス抽選情報(String, String, long)
                        l_srvLotInfo.removeSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, Long.parseLong(l_strLotInfoId));
                    }
                }
            }

        }

        //8:＜isDIR管理者()=trueの場合＞
        if (l_blnIsDirAdmin)
        {
            //8.1:get第２URL( )
            String l_strUrl2 = l_srvMaster.getUrl2();
            //8.2:＜get第２URL()==nullであり、かつリクエストデータ.第２URL!=nullの場合＞
            if (l_strUrl2 == null && l_request.url2 != null)
            {
                log.debug("new url2");

                //8.2.1:createNewサービス利用キー(String, String, String)
                WEB3SrvRegiServiceUseKey l_srvUseKey =
                    WEB3SrvRegiServiceUseKey.createNewSrvUseKey(l_strInstitutionCode,
                        l_request.serviceDiv,
                        WEB3SrvUseKeyTypeDef.URL2);

                //8.2.2:setサービス利用キー(String)
                l_srvUseKey.setSrvUseKey(l_request.url2);
                //8.2.3:saveNewサービス利用キー( )
                l_srvUseKey.saveNewSrvUseKey();

            }
            //8.3＜get第２URL()!=nullの場合＞
            if (l_strUrl2 != null)
            {
                //8.3.1:getサービス利用キー(String, long, boolean)
                WEB3SrvRegiServiceUseKey l_srvUseKey = l_srvMaster.getSrvUseKey(
                    WEB3SrvUseKeyTypeDef.URL2, 1, true);

                //8.3.2:＜リクエストデータ.第２URL!=nullの場合＞
                if (l_request.url2 != null)
                {
                    log.debug("set url2");
                    //8.2.3.1: setサービス利用キー(String)
                    l_srvUseKey.setSrvUseKey(l_request.url2);
                    //8.2.3.2: saveサービス利用キー( )
                    l_srvUseKey.saveSrvUseKey();
                }
                //8.3.3:＜リクエストデータ.第２URL==nullの場合＞
                else
                {
                    log.debug("delete url2");
                    //8.3.3.1:removeサービス利用キー( )
                    l_srvUseKey.removeSrvUseKey();
                }
            }

            //8.4:＜サービス利用キー入力必須項目の更新＞
            //ハッシュ計算方式区分
            //8.4.1:getサービス利用キー(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.HASH_CAL_HOW_TO_DIV, 1, true);

            if (l_srvUseKey == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //8.4.2:setサービス利用キー(String)
            l_srvUseKey.setSrvUseKey(l_request.hashCalHowToDiv);
            //8.4.3:saveサービス利用キー( )
            l_srvUseKey.saveSrvUseKey();
            log.debug("ハッシュ計算方式区分");

            //ハッシュ計算手順区分
            //8.4.1:getサービス利用キー(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey2 = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.HASH_CAL_ORDER_DIV, 1, true);

            if (l_srvUseKey2 == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //8.4.2:setサービス利用キー(String)
            l_srvUseKey2.setSrvUseKey(l_request.hashCalOrderDiv);
            //8.4.3:saveサービス利用キー( )
            l_srvUseKey2.saveSrvUseKey();
            log.debug("ハッシュ計算手順区分");

            //送信方法@区分
            //8.4.1:getサービス利用キー(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey3 = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.SEND_HOW_TO_DIV, 1, true);

            if (l_srvUseKey3 == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //8.4.2:setサービス利用キー(String)
            l_srvUseKey3.setSrvUseKey(l_request.sendHowToDiv);
            //8.4.3:saveサービス利用キー( )
            l_srvUseKey3.saveSrvUseKey();
            log.debug("送信方法@区分");

            //送信パラメータ区分
            //8.4.1:getサービス利用キー(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey4 = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.SEND_PARAM_DIV, 1, true);
            if (l_srvUseKey4 == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //8.4.2:setサービス利用キー(String)
            l_srvUseKey4.setSrvUseKey(l_request.sendParamDiv);
            //8.4.3:saveサービス利用キー( )
            l_srvUseKey4.saveSrvUseKey();
            log.debug("送信パラメータ区分");

            //暗号化顧客コード区分
            //8.4.1:getサービス利用キー(String, long, boolean)
            WEB3SrvRegiServiceUseKey l_srvUseKey5 = l_srvMaster.getSrvUseKey(
                WEB3SrvUseKeyTypeDef.CRYPT_ACCOUNT_CODE_DIV, 1, true);
            if (l_srvUseKey5 == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //8.4.2:setサービス利用キー(String)
            l_srvUseKey5.setSrvUseKey(l_request.cryptAccountCodeDiv);
            //8.4.3:saveサービス利用キー( )
            l_srvUseKey5.saveSrvUseKey();
            log.debug("暗号化顧客コード区分");

            //8.5:＜リクエストデータ.ハッシュ値一覧の件数分、繰り返す＞
            if (l_request.hashList != null)
            {
                int l_intCnt = l_request.hashList.length;

                for (int i = 0; i < l_intCnt; i++)
                {
                    WEB3SrvRegiExecKey l_exceKey = l_request.hashList[i];

                    //8.5.1:＜リクエストデータ.ハッシュ値一覧[n].利用キーID!=nullの場合＞
                    if (l_exceKey.keyId != null)
                    {
                        //8.5.1.1:getサービス利用キー(String, long, boolean)
                        WEB3SrvRegiServiceUseKey l_srvUseKeyUpdate = l_srvMaster.getSrvUseKey(
                            WEB3SrvUseKeyTypeDef.HSAH_VALUE, Long.parseLong(l_exceKey.keyId), true);
                        //8.5.1.2:＜リクエストデータ.ハッシュ値一覧[n].無効区分="有効"の場合＞
                        if (!l_exceKey.invalidDiv)
                        {
                            log.debug("set hash");
                            //8.5.1.2.1:setサービス利用キー(String)
                            l_srvUseKeyUpdate.setSrvUseKey(l_exceKey.key);
                            //8.5.1.2.2:saveサービス利用キー( )
                            l_srvUseKeyUpdate.saveSrvUseKey();
                        }
                        //8.5.1.3:＜リクエストデータ.ハッシュ値一覧[n].無効区分="無効"の場合＞
                        else
                        {
                            log.debug("delete hash");
                            //8.5.1.3.1:removeサービス利用キー( )
                            l_srvUseKeyUpdate.removeSrvUseKey();
                        }
                    }
                    //8.5.2:＜リクエストデータ.ハッシュ値一覧[n].利用キーID=nullの場合＞
                    else
                    {
                        //8.5.2.1:＜リクエストデータ.ハッシュ値一覧[n].無効区分="有効"の場合＞
                        if (!l_exceKey.invalidDiv)
                        {
                            log.debug("new hash");

                            //8.5.2.1.1:createNewサービス利用キー(String, String, String)
                            WEB3SrvRegiServiceUseKey l_srvUseKeyNew =
                                WEB3SrvRegiServiceUseKey.createNewSrvUseKey(
                                    l_strInstitutionCode,
                                    l_request.serviceDiv,
                                    WEB3SrvUseKeyTypeDef.HSAH_VALUE);

                            //8.5.2.1.2:setサービス利用キー(String)
                            l_srvUseKeyNew.setSrvUseKey(l_exceKey.key);

                            //8.5.2.1.3:saveNewサービス利用キー( )
                            l_srvUseKeyNew.saveNewSrvUseKey();
                        }
                    }
                }
            }

            //8.6:＜リクエストデータ.送信パラメータ一覧の件数分、繰り返す＞
            if (l_request.paramList != null)
            {
                int l_intCnt = l_request.paramList.length;

                for (int i = 0; i < l_intCnt; i++)
                {
                    WEB3SrvRegiExecKey l_exceKey = l_request.paramList[i];

                    //8.6.1:＜リクエストデータ.送信パラメータ一覧[n].利用キーID!=nullの場合＞
                    if (l_exceKey.keyId != null)
                    {
                        //8.6.1.1:getサービス利用キー(String, long, boolean)
                        WEB3SrvRegiServiceUseKey l_srvUseKeyUpdate = l_srvMaster.getSrvUseKey(
                            WEB3SrvUseKeyTypeDef.SEND_PARAM, Long.parseLong(l_exceKey.keyId), true);
                        //8.6.1.2:＜リクエストデータ.送信パラメータ一覧[n].無効区分="有効"の場合＞
                        if (!l_exceKey.invalidDiv)
                        {
                            log.debug("set params");
                            //8.6.1.2.1:setサービス利用キー(String)
                            l_srvUseKeyUpdate.setSrvUseKey(l_exceKey.key);
                            //8.6.1.2.2:saveサービス利用キー( )
                            l_srvUseKeyUpdate.saveSrvUseKey();
                        }
                        //8.6.1.3:＜リクエストデータ.送信パラメータ一覧[n].無効区分="無効"の場合＞
                        else
                        {
                            log.debug("delete params");
                            //8.6.1.3.1:removeサービス利用キー( )
                            l_srvUseKeyUpdate.removeSrvUseKey();
                        }
                    }
                    //8.6.2:＜リクエストデータ.送信パラメータ一覧[n].利用キーID=nullの場合＞
                    else
                    {
                        //8.6.2.1:＜リクエストデータ.送信パラメータ一覧[n].無効区分="有効"の場合＞
                        if (!l_exceKey.invalidDiv)
                        {
                            log.debug("new params");
                            //8.6.2.1.1:createNewサービス利用キー(String, String, String)
                            WEB3SrvRegiServiceUseKey l_srvUseKeyNew =
                                WEB3SrvRegiServiceUseKey.createNewSrvUseKey(
                                    l_strInstitutionCode,
                                    l_request.serviceDiv,
                                    WEB3SrvUseKeyTypeDef.SEND_PARAM);

                            //8.6.2.1.2:setサービス利用キー(String)
                            l_srvUseKeyNew.setSrvUseKey(l_exceKey.key);

                            //8.6.2.1.3:saveNewサービス利用キー( )
                            l_srvUseKeyNew.saveNewSrvUseKey();
                        }
                    }
                }
            }

			//障害対応 NO_U01996
			log.debug("l_request.offerType = " + l_request.offerType);
			log.debug("applyCommissionConditions = " + l_request.applyCommissionConditions);
						
			if(l_request.applyCommissionConditions == null)
			{
				//登録済み適用手数料一覧の取得
				List l_listCommCond = l_srvMaster.getCommCondList();
				
				for(int i=0; i<l_listCommCond.size(); i++)
				{
					SrvRegiCommCondRow l_srvRegiCommCond;
					//get手数料条件()
					l_srvRegiCommCond = (SrvRegiCommCondRow) l_listCommCond.get(i);
								
					String l_institutionCode = l_srvRegiCommCond.getInstitutionCode();
					String l_srvDiv = l_srvRegiCommCond.getSrvDiv();
					String l_orderAccProduct = l_srvRegiCommCond.getOrderAccProduct();
					
					log.debug("【削除"+i+"】institutionCode = "+l_institutionCode+"  srvDiv = "+l_srvDiv+"  orderAccProduct = "+l_orderAccProduct);
					
					WEB3SrvRegiCommCond l_commCond = 
						WEB3SrvRegiCommCond.createNewSrvRegiCommCondition(l_institutionCode, l_srvDiv, l_orderAccProduct);
						
					l_commCond.removeSrvRegiCommCondition();
				}
								
			}
            else
            {
                //8.7:<リクエストデータ.適用手数料条件の件数分、繰り返す>
                WEB3SrvRegiApplyCommissionCondition[] l_applyCommiConds = l_request.applyCommissionConditions;
                int l_intApplyCommiCondCnt = l_applyCommiConds.length;

                log.debug("l_intApplyCommiCondCnt:" + l_intApplyCommiCondCnt);

                for (int i = 0; i < l_intApplyCommiCondCnt; i++)
                {
					log.debug("applyCommissionConditions["+i+"].productKindDiv = " + l_request.applyCommissionConditions[i].productKindDiv);
					log.debug("applyCommissionConditions["+i+"].invalidDiv = " + l_request.applyCommissionConditions[i].invalidDiv);

                    //8.7.1.1get手数料条件(String)
                    WEB3SrvRegiCommCond l_coomCond = l_srvMaster.getCommCond(l_applyCommiConds[i].productKindDiv);

					//8.7.2:get手数料条件( )!=nullの場合>
                    if(l_coomCond != null){
						//8.7.2.1:{リクエストデータ.提供区分=無料(0) or 無料/有料(1) or 無料(2) or 無料/有料(3)} 
						//          and 適用手数料条件.無効区分="有効"の場合、削除対象外（次の要素へスキップ）
                    	if((l_request.offerType.equals(WEB3SupplyDivDef.FREE_SUPPLY) || 
							l_request.offerType.equals(WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY) ||
							l_request.offerType.equals(WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA) ||
							l_request.offerType.equals(WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY__UTUMIYA)) &&
							!l_applyCommiConds[i].invalidDiv)
						{
              				log.debug("8.7.2.1:get手数料条件()!=null 【削除対象外】");
							continue;
						}
						log.debug("8.7.2.2:{リクエストデータ.提供区分=無料(0) or 無料/有料(1)} and 適用手数料条件.無効区分=無効の場合>");
                        //8.7.2.2:removeサービス利用手数料条件( )
                        l_coomCond.removeSrvRegiCommCondition();
                    }

					//8.7.3:get手数料条件( )=nullの場合>
                    if(l_coomCond == null)
                    {
						//8.7.3.1:<リクエストデータ.提供区分≠nullで、かつ適用手数料条件.無効区分="有効"の場合>
						if (!l_request.offerType.equals(WEB3SupplyDivDef.NO_CONDITION_ATTACHED) && 
							!l_applyCommiConds[i].invalidDiv)
						{
							log.debug("8.7.3.1:<リクエストデータ.提供区分≠nullで、かつ適用手数料条件.無効区分=有効の場合>");

							//8.7.3.2:createNewサービス利用手数料条件(String, String, String)
							WEB3SrvRegiCommCond l_coomCond2 =
								WEB3SrvRegiCommCond.createNewSrvRegiCommCondition(
									l_strInstitutionCode,
									l_request.serviceDiv,
									l_applyCommiConds[i].productKindDiv);

							//8.7.3.3:saveNewサービス利用手数料条件( )
							l_coomCond2.saveNewSrvRegiCommCondition();
						}

                    }
                }
            }
        }
        log.exiting(STR_METHOD_NAME);

        WEB3AdminSrvRegiServiceChangeCompleteResponse l_response =
            (WEB3AdminSrvRegiServiceChangeCompleteResponse)l_request.createResponse();
        return l_response;
    }

    /**
     * (validateサービス利用キー)<BR>
     * リクエストで渡されたサービス利用キーの入力値(*1)の整合性をチェックする。<BR>
     * <BR>
     * (*1) ステータス!="停止中"の場合のみ、以下の項目のチェックを行う。<BR>
     * 　@・URL <BR>
     * 　@・第２URL <BR>
     * 　@・ハッシュ計算方式区分 <BR>
     * 　@・ハッシュ計算手順区分 <BR>
     * 　@・送信方法@区分 <BR>
     * 　@・送信パラメータ区分 <BR>
     * 　@・暗号化顧客コード区分 <BR>
     * 　@・ハッシュ値一覧 <BR>
     * 　@・送信パラメータ一覧 <BR>
     * <BR>
     * １）引数.URLに含まれる予約語のチェック (*3) <BR>
     * 　@－引数.URL内に"%%～%%"で区切られた予約語が存在する場合、<BR>
     * 　@クラス「サービス利用予約語変換」で定義された予約語と異なる予約語が指定されていた場合、<BR>
     * 　@例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01827<BR>
     * <BR>
     * ２）引数.第２URLに含まれる予約語のチェック (*3) <BR>
     * 　@－引数.第２URL内に"%%～%%"で区切られた予約語が存在する場合、<BR>
     *　@ クラス「サービス利用予約語変換」で定義された予約語と異なる予約語が指定されていた場合、<BR>
     *　@ 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01828<BR>
     * <BR>
     * ３）引数.送信パラメータ一覧に含まれる予約語のチェック (*3) <BR>
     *　@ －引数.送信パラメータ一覧[n].利用キー内に"%%～%%"で区切られた予約語が存在する場合、<BR>
     *   クラス「サービス利用予約語変換」で定義された予約語と異なる予約語が指定されていた場合、<BR>
     *　@ 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01829<BR>
     * <BR>
     * ４）暗号化顧客コードのチェック
     *   －引数.暗号化顧客コード区分="有"であり、かつ引数.送信パラメータ一覧に含まれる予約語に<BR>
     * 　@"予約語：暗号化顧客コード"が存在しなかった場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01830<BR>
     * <BR>
     * ５）ハッシュ値の件数チェック <BR>
     *   －引数.ハッシュ計算手順区分="電子鳩"または"通常計算（１）"の場合 <BR>
     *　@ 引数.ハッシュ値一覧の有効な件数!=2件の場合、例外をスローする。(*2)<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01831<BR>
     * <BR>
     *   －引数.ハッシュ計算手順区分="通常計算（２）"または"２段階計算"の場合 <BR>
     * 　@引数.ハッシュ値一覧の有効な件数!=1件の場合、例外をスローする。(*2) <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01832<BR>
     * <BR>
     * ６）第２URLのチェック <BR>
     *   －引数.送信方法@区分="特殊（１）"であり、かつ引数.第２URL==nullの場合、<BR>
     *　@ 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_01833<BR>
     * <BR>
     * (*2) 引数.ハッシュ値一覧.無効区分="有効"の件数を用いて判定する。<BR>
     * <BR>
     * (*3) 予約語のチェックについて <BR>
     * 　@　@予約語：入力区分が存在した場合、以下のチェックも行い、<BR>
     * 　@条件に合致しなかった場合、例外をスローする。<BR>
     * 　@①@予約語：入力区分、予約語：入力区分末尾の両方が含まれている事。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01825<BR>
     * <BR>
     * 　@　@（予約語：入力区分・予約語：入力区分末尾の順序が守られている事が必須）<BR>
     * 　@②予約語：入力区分、予約語：入力区分末尾の間に数値が入っている事。<BR>
     *　@　@（数値以外、全てエラー）<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01826<BR>
     * <BR>
     * @@param l_strUrl - (URL)<BR>
     * @@param l_strUrl2 - (第２URL)<BR>
     * @@param l_strHashCalHowToDiv - (ハッシュ計算方式区分)<BR>
     * @@param l_strHashCalOrderDiv - (ハッシュ計算手順区分)<BR>
     * @@param l_strSendHowToDiv - (送信方法@区分)<BR>
     * @@param l_strSendParamDiv - (送信パラメータ区分)<BR>
     * @@param l_strCryptAccountCodeDiv - (暗号化顧客コード区分)<BR>
     * @@param l_hashList - (ハッシュ値一覧)<BR>
     * @@param l_paramList - (送信パラメータ一覧)<BR>
     * @@throws WEB3BaseException
     */
    protected void validateSrvUseKey(String l_strUrl,
        String l_strUrl2,
        String l_strHashCalHowToDiv,
        String l_strHashCalOrderDiv,
        String l_strSendHowToDiv,
        String l_strSendParamDiv,
        String l_strCryptAccountCodeDiv,
        WEB3SrvRegiExecKey[] l_hashList,
        WEB3SrvRegiExecKey[] l_paramList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSrvUseKey(String, String, String, " +
            "String, String, String, String, WEB3SrvRegiExecKey[], WEB3SrvRegiExecKey[])";

        log.entering(STR_METHOD_NAME);

        //１）引数.URLに含まれる予約語のチェック (*3)
        //  －引数.URL内に"%%～%%"で区切られた予約語が存在する場合、
        //  クラス「サービス利用予約語変換」で定義された予約語と異なる予約語が指定されていた場合、
        //  例外をスローする。
        this.validateInputDiv(l_strUrl);
        String[] l_strReservedWords = this.getReservedWords(l_strUrl);
        if (l_strReservedWords != null)
        {
            int l_intCnt = l_strReservedWords.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                String l_str = l_strReservedWords[i];
                int l_intInputDivFlag = l_str.indexOf(
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV);
                if (l_intInputDivFlag >= 0)
                {
                    continue;
                }

                if (!this.validateWord(l_str))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01827,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //２）引数.第２URLに含まれる予約語のチェック (*3)
        //  －引数.第２URL内に"%%～%%"で区切られた予約語が存在する場合、
        //  クラス「サービス利用予約語変換」で定義された予約語と異なる予約語が指定されていた場合、
        //  例外をスローする。
        this.validateInputDiv(l_strUrl2);
        l_strReservedWords = this.getReservedWords(l_strUrl2);
        if (l_strReservedWords != null)
        {
            int l_intCnt = l_strReservedWords.length;
            for (int i = 0; i < l_intCnt; i++)
            {
                String l_str = l_strReservedWords[i];
                int l_intInputDivFlag = l_str.indexOf(
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV);
                if (l_intInputDivFlag >= 0)
                {
                    continue;
                }

                if (!this.validateWord(l_str))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01828,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        //３）引数.送信パラメータ一覧に含まれる予約語のチェック (*3)
        //  －引数.送信パラメータ一覧[n].利用キー内に"%%～%%"で区切られた予約語が存在する場合、
        //  クラス「サービス利用予約語変換」で定義された予約語と異なる予約語が指定されていた場合、
        //  例外をスローする。
        if (l_paramList != null)
        {
            int l_intCnt = l_paramList.length;
            for (int i = 0; i < l_intCnt; i ++)
            {
                log.debug("i:" + i + "/" + l_intCnt);
                String l_strKey = l_paramList[i].key;
                this.validateInputDiv(l_strKey);
                l_strReservedWords = this.getReservedWords(l_strKey);
                if (l_strReservedWords != null)
                {
                    int l_intWordsCnt = l_strReservedWords.length;
                    for (int j = 0; j < l_intWordsCnt; j++)
                    {
                        log.debug("j:" + j + "/" + l_intWordsCnt);
                        String l_str = l_strReservedWords[j];
                        int l_intInputDivFlag = l_str.indexOf(
                            WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV);
                        if (l_intInputDivFlag >= 0)
                        {
                            continue;
                        }

                        if (!this.validateWord(l_str))
                        {
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01829,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                }
            }
        }

        //４）暗号化顧客コードのチェック
        //  －引数.暗号化顧客コード区分="有"であり、かつ引数.送信パラメータ一覧に含まれる予約語に
        //  "予約語：暗号化顧客コード"が存在しなかった場合、例外をスローする。
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strCryptAccountCodeDiv))
        {
            if (l_paramList != null && l_paramList.length > 0)
            {
                int l_intCnt = l_paramList.length;
                int l_intExpFlag = 0;
                for (int i = 0; i < l_intCnt; i++)
                {
                    String l_strKey = l_paramList[i].key;
                    if (l_strKey != null)
                    {
                        int l_intFlag =
                        l_strKey.indexOf(WEB3SrvRegiReservedWordDef.RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE);
                        if (l_intFlag >= 0)
                        {
                            l_intExpFlag ++;
                        }
                    }
                }
                if (l_intExpFlag == 0)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01830,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            else
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01830,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //５）ハッシュ値の件数チェック
        //  －引数.ハッシュ計算手順区分="電子鳩"または"通常計算（１）"、または"２段階計算"の場合
        //    引数.ハッシュ値一覧の有効な件数!=2件の場合、例外をスローする。(*2)
        if (WEB3SrvRegiHashCalOrderDivDef.ELE_PIGEON.equals(l_strHashCalOrderDiv) ||
            WEB3SrvRegiHashCalOrderDivDef.NORMAL1.equals(l_strHashCalOrderDiv) ||
            WEB3SrvRegiHashCalOrderDivDef.TWO_STEP_CALCULATION.equals(l_strHashCalOrderDiv))
        {
            if (l_hashList == null)
            {
                l_hashList = new WEB3SrvRegiExecKey[0];
            }
            int l_intCnt = l_hashList.length;
            int l_intValidCnt = 0;
            for (int i = 0; i < l_intCnt; i++)
            {
                if (!l_hashList[i].invalidDiv)
                {
                    l_intValidCnt ++;
                }
            }
            if (l_intValidCnt != 2)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01831,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }

        //  －引数.ハッシュ計算手順区分="通常計算（２）"の場合
        //    引数.ハッシュ値一覧の有効な件数!=1件の場合、例外をスローする。(*2)
        if (WEB3SrvRegiHashCalOrderDivDef.NORMAL2.equals(l_strHashCalOrderDiv))
        {
            if (l_hashList == null)
            {
                l_hashList = new WEB3SrvRegiExecKey[0];
            }
            int l_intCnt = l_hashList.length;
            int l_intValidCnt = 0;
            for (int i = 0; i < l_intCnt; i++)
            {
                if (!l_hashList[i].invalidDiv)
                {
                    l_intValidCnt ++;
                }
            }
            if (l_intValidCnt != 1)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01832,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }


        //６）第２URLのチェック
        //  －引数.送信方法@区分="特殊（１）"であり、かつ引数.第２URL==nullの場合、
        //    例外をスローする。
        if (WEB3SrvRegiSendHowToDivDef.SPECIAL1.equals(l_strSendHowToDiv))
        {
            if (l_strUrl2 == null)
            {                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01833,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
        }


        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 引数のURLの中から%%で囲まれた予約語の一覧を取得する
     * 
     * @@param l_strUrl URL
     * @@return 予約語の一覧
     */
    private String[] getReservedWords(String l_strUrl)
    {
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_strUrl))
        {
            return null;
        }

        final String l_strDoublePercents = "%%";
        List l_lisReservedWords = new ArrayList();

        int l_intUrlLength = l_strUrl.length();

        for (int i = 0; i < l_intUrlLength - 1; i++)
        {
            if (l_strDoublePercents.equals(l_strUrl.substring(i, i + 2)))
            {
                int l_intHead = 0;
                int l_intTail = 0;

                for (int j = i + 2; j < l_intUrlLength - 1; j++)
                {
                    l_intHead = i;
                    if (l_strDoublePercents.equals(l_strUrl.substring(j, j + 2)))
                    {
                        l_intTail = j + 2;
                        i = j + 1;
                        l_lisReservedWords.add(l_strUrl.substring(l_intHead, l_intTail));
                        break;
                    }
                }
            }
        }

        String[] l_strReservedWords = new String[l_lisReservedWords.size()];
        l_lisReservedWords.toArray(l_strReservedWords);

        return l_strReservedWords;

    }

    /**
     * 入力区分のチェック
     * 
     * @@param l_strContent
     * @@throws WEB3BaseException
     */
    private void validateInputDiv(String l_strContent) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateInputDiv(String)";
        log.entering(STR_METHOD_NAME);

        log.debug("1:" + l_strContent);

        boolean l_blnEndFlag = false;

        if (l_strContent != null)
        {
            l_strContent = l_strContent.trim();
        }
        else
        {
            l_blnEndFlag = true;
        }
        log.debug("2:" + l_strContent);

        while (!l_blnEndFlag)
        {
            log.debug("check:" + l_strContent);
            int l_intStartFlag = l_strContent.indexOf(
                WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV);
            if (l_intStartFlag >= 0)
            {
                int l_intEndFlag = l_strContent.indexOf(
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV_END);
                if (l_intEndFlag < 0)
                {
                    log.debug("has start,has not end");
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01825,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                else
                {
                    if (l_intStartFlag > l_intEndFlag)
                    {
                        log.debug("end....start");
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01825,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }
                    else
                    {
                        String l_strEquals = l_strContent.substring(
                            l_intStartFlag + WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV.length(),
                            l_intEndFlag);
                        boolean l_blnIsNumber = WEB3StringTypeUtility.isNumber(l_strEquals);
                        if (!l_blnIsNumber)
                        {
                            log.debug("start.."+ l_strEquals + "..end");
                            log.debug(getClass().getName() + STR_METHOD_NAME);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01826,
                                this.getClass().getName() + STR_METHOD_NAME);
                        }
                    }
                }

                l_strContent = l_strContent.substring(l_intEndFlag +
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV_END.length());

            }
            else
            {
                int l_intEndFlag = l_strContent.indexOf(
                    WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV_END);
                if (l_intEndFlag >= 0)
                {
                    log.debug("has not start, has end");
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01825,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                l_blnEndFlag = true;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
	
	/**
	 * 予約語のチェック
	 * 
	 * 以下の予約語のいずれかに一致する場合、trueを返却する
	 * ・予約語：証券会社コード
	 * ・予約語：部店コード
	 * ・予約語：顧客コード
	 * ・予約語：銘柄コード
	 * ・予約語：暗号化顧客コード
	 * ・予約語：ハッシュ計算結果
	 * ・予約語：Token
	 * ・予約語：注文チャネル
	 * ・予約語：扱者
	 * ・予約語：信用口座区分
	 * ・予約語：先物OP口座区分（大証）
	 * ・予約語：顧客名
	 * ・予約語：年月日（YYYYMMDD）
	 * ・予約語：年月日（YYYY-MM-DD-HH-MM）
	 * ・予約語：年月日（YYYYMMDDHHMM）
	 * ・予約語：契約期限（適用終了日）
	 * ・予約語：ハッシュ計算要素（１）
	 * ・予約語：ハッシュ計算要素（２）
	 * ・予約語：扱者名
	 * ・予約語：入力区分
	 * ・予約語：入力区分末尾
	 * ・予約語：（非置換）%%HSTR%%
	 * ・予約語：（非置換）%%FUNDTYPE%%
	 * ・予約語：（非置換）%%FUNDCODE%%
	 * ・予約語：（非置換）%%DELYEAR%%
	 * ・予約語：（非置換）%%DELMONTH%%
	 * ・予約語：（非置換）%%PUTCALL%%
	 * ・予約語：（非置換）%%STRIKEPRC%%
	 * ・予約語：（非置換）%%TRADETYPE%%
	 * ・予約語：（非置換）%%BUYSELLFLAG%%
	 * ・予約語：（非置換）%%STKEXCODE%%
	 * ・予約語：ハッシュ化された顧客ID
	 * ・予約語：市場コード
	 * ・予約語：タイプ
	 * ・予約語：SSID値
	 * ・予約語：暗号化保有銘柄情報
	 * ・予約語：年月日（YYYYMMDDHHMISS）
	 * ・予約語：GUID
	 * ・予約語：ID
	 * ・予約語：PASS
     * ・債券残高リスト<BR>
     * ・余力残高リスト<BR>
     * ・資産評価額一覧<BR>
     * ・債券取引用暗号化パスワード<BR>
     * ・電子鳩URL<BR>
     * ・居住区分 <BR>
     * ・情報サービスリスト<BR>
     * ・予約語：大証FXログインID<BR>
     * ・予約語：他サービス申込状況<BR>
     * ・予約語：現物税区分<BR>
     * ・予約語：現物税区分（次年）<BR>
     * ・予約語：信用税区分<BR>
     * ・予約語：信用税区分（次年）<BR>
     * ・予約語：CDキー<BR>
	 * <BR>
	 * 一致しない場合、falseを返却する
	 *  
	 * @@param l_strWord
	 * @@return
	 */
    private boolean validateWord(String l_strWord)
    {
        if (!(WEB3SrvRegiReservedWordDef.RESERVED_WORD_INSTITUTION_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_BRANCH_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MAIN_ACCOUNT_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_PRODUCT_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_ENCRYPTION_ACCOUNT_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HASH_CALC_VALUE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TOKEN.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_ORDER_CHANEL.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TRADER.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MARGIN_ACCOUNT_DIV.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_FUOP_OSE_ACCOUNT_DIV.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_ACCOUNT_NAME.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_YYYYMMDD.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_YYYYMMDDHHMM.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_APPLI_EXPIRE_DATE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HASH_ELEMENT_1.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HASH_ELEMENT_2.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TRADER_NAME.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_INPUT_DIV_END.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HSTR.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_FUNDTYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_FUNDCODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_DELYEAR.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_DELMONTH.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_PUTCALL.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_STRIKEPRC.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TRADETYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_BUYSELLFLAG.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_STKEXCODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_YYYYMMDDHHMM_2.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_HASH_ACCOUNT_ID.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MARKET_CODE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_TYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_SSID_VALUE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_ENCRYPTION_MF_ASSET.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_YEAR_MONTH_DAY.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_GUID.equals(l_strWord)
			|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_ID.equals(l_strWord)
			|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_PASS.equals(l_strWord)
			|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_BOND_BALANCE_LIST.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_TRADINGPOWER_BALANCE_LIST.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_STOCK_APPRAISAL_VALUE_INSPECTION.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_BOND_ENCRYPT_PASS.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_DENSHI_BATO_URL.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_RESIDENT.equals(l_strWord)
        	|| WEB3SrvRegiReservedWordDef.RESERVED_WORD_INFORMATION_SERVICE_LIST.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_OSE_LOGINID.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_OTHER_SRV_REGI_STATUS.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_EQUITY_TAXTYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_EQUITY_TAXTYPE_N.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MARGIN_TAXTYPE.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_MARGIN_TAXTYPE_N.equals(l_strWord)
            || WEB3SrvRegiReservedWordDef.RESERVED_WORD_CD_KEY.equals(l_strWord)))
        {
            return false;
        }
        else
        {
            return true;
        }

    }
}
@
