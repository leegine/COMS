head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceUseApplicationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込サービスImpl(WEB3SrvRegiServiceUseApplicationServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 李頴淵 新規作成
Revesion History : 2007/06/05 趙林鵬 (中訊) モデル244,245,246,ＤＢ更新仕様 037
Revesion History : 2007/06/07 趙林鵬 (中訊) モデル263
Revesion History : 2007/06/21 崔遠鵬 (中訊) モデル265
Revesion History : 2007/06/26 崔遠鵬 (中訊) モデル272
Revesion History : 2007/06/29 崔遠鵬 (中訊) モデル277
Revesion History : 2007/07/25 金傑   (中訊) モデル295
Revesion History : 2007/07/27 金傑   (中訊) モデル297,ＤＢ更新仕様 040,041
Revesion History : 2008/02/25 周墨洋 (中訊) 仕様変更・モデル311,325
Revesion History : 2008/03/03 武波 (中訊) 仕様変更 モデル331,341
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3InvestDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3PaymentDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvAppliAttributeProcDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiHistoryParams;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiApplyKindDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiFreeAttributeApplyDivDef;
import webbroker3.srvregi.define.WEB3SrvRegiMailDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiExecSendMailService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceUseApplicationService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

/**
 * (サービス利用申込サービスImpl)<BR>
 * サービス利用申込サービス実装クラス<BR>
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiServiceUseApplicationServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiServiceUseApplicationService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceUseApplicationServiceImpl.class);

    /**
     * @@roseuid 416F392502EE
     */
    public WEB3SrvRegiServiceUseApplicationServiceImpl()
    {

    }

    /**
     * サービス利用申込処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、validate利用申込( )または、 <BR>
     * submit利用申込( )メソッドをコールする。 <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5F05B0149
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if(l_request instanceof WEB3SrvRegiApplyConfirmRequest)
        {
            WEB3SrvRegiApplyConfirmResponse l_response =
                validateUseAppli((WEB3SrvRegiApplyConfirmRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if(l_request instanceof WEB3SrvRegiApplyCompleteRequest)
        {
            WEB3SrvRegiApplyCompleteResponse l_response =
                submitUseAppli((WEB3SrvRegiApplyCompleteRequest)l_request);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + STR_METHOD_NAME);
        }
    }

    /**
     * (validate利用申込)<BR>
     * サービス利用申込審査処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）利用申込審査」参照<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置  1.9.4.1.以下の条件の場合、例外をスローする。<BR>
     * 　@　@　@　@getサービス申込属性情報() == null の場合、又は、<BR>
     * 　@　@　@　@getサービス申込属性情報().申込属性区分 == '2'(申込不可)<BR>
     * 　@　@　@　@の場合、例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02805  <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.9.4.3.get無料対象期間( ) == null<BR>　@
     * 　@　@　@　@の場合例外をスローする<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02806 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.9.5.1getサービス申込属性情報().申込属性区分 == <BR>
     * 　@　@　@　@'1'(無料対象)　@若しくは<BR>
     * 　@　@　@　@'2'(申込不可) の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02807 <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用申込確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5F07D01B6
     */
    protected WEB3SrvRegiApplyConfirmResponse validateUseAppli(WEB3SrvRegiApplyConfirmRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateUseAppli(WEB3SrvRegiApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate
        l_request.validate();

        //1.2 validate注文受付可能
        log.debug("validate注文受付可能");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        log.debug("getCommonOrderValidator");
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4  get補助口座
        log.debug("get補助口座");
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.5 validate取引可能顧客
        log.debug("validate取引可能顧客");
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            WEB3SrvRegiApplyConfirmResponse l_response = (WEB3SrvRegiApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.6 get発注日
        log.debug("get発注日");
        Date l_datOrderBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();

        //1.7＜現在日付の取得＞
        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //1.7 validate申込登録
        log.debug("validate申込登録");
        Long l_dblChangeId = null;
        Double l_dblBidAmt = null;

        if (l_request.chargeId != null)
        {
            l_dblChangeId = new Long(l_request.chargeId);
        }
        if (l_request.bidAmt != null)
        {
            l_dblBidAmt = Double.valueOf(l_request.bidAmt);
        }

        this.validateAppliRegist((WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv, l_dblChangeId,
            l_dblBidAmt, l_tsSystemTimestamp, l_request.applyKindDiv, null);

        //1.8 <「適用開始日」「適用終了日」の取得>
        //1.8.1 getサービスマスター
        log.debug("getサービスマスター");
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        //1.8.1.1 get申込要サービス
        log.debug("get申込要サービス");
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        //getサービス申込属性情報(String, String, String, String, String)
        //証券会社コード=取得した補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
        //部店コード=取得した補助口座オブジェクト.getBranch( ).getBranchCode( )
        //口座コード=取得した補助口座オブジェクト.getMainAccount( ).getAccountCode( )
        //サービス区分=リクエストデータ.ID
        //アップロード区分=null
        List l_lisServiceAppliAttributeInfo = l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
            l_subAccount.getInstitution().getInstitutionCode(),
            l_subAccount.getMainAccount().getBranch().getBranchCode(),
            l_subAccount.getMainAccount().getAccountCode(),
            l_request.serviceDiv,
            null);

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
        String l_strFreeTargetPeriod = null;

        if (l_lisServiceAppliAttributeInfo != null)
        {
            l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0);
        }
        //リクエストデータ.無料属性申込区分 = '1'　@の場合
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //以下の条件の場合、例外をスローする。
            //getサービス申込属性情報() == null の場合、又は、
            //getサービス申込属性情報().申込属性区分 == '2'(申込不可) の場合

            if (l_lisServiceAppliAttributeInfo == null
                || WEB3AppliAttributeDef.CANNOT_APPLI.equals(l_srvAppliAttributeRow.getAppliAttribute()))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02805,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "サービス申込属性情報がnull、または申込属性区分が'2'(申込不可)です。");
            }

            // get無料対象期間( )
            l_strFreeTargetPeriod =
                l_srvRegiApplicationRequiredService.getFreeTargetPeriod();

            //get無料対象期間( ) == null　@の場合例外をスローする。
            if (l_strFreeTargetPeriod == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02806,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "無料対象期間が未指定です。");
            }
        }

        //1.8.1.1.1.  get抽選設定
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        //1.8.2 <分岐処理 *1>
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
            l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_request.serviceDiv, l_tsSystemTimestamp, 0);

        Timestamp l_tsAppliStartDate = null;
        Timestamp l_tsAppliEndDate = null;
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) &&
            !WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv))
        {
            log.debug("//1.8.2.1 calc適用終了日");
            l_tsAppliStartDate = l_tsSystemTimestamp;
            //1.8.2.1 calc適用終了日
            WEB3SrvRegiRegistService l_srvRegiRegistService =
                (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
            String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
            
            //リクエストデータ.無料属性申込区分 = '1'　@の場合 又は、
			//引数.申込種別区分 == "試用申込" の場合
            String l_chargeId = null;
            
            if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(l_request.freeAttributeApplyDiv) || 
				(WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_request.applyKindDiv))) 
            {
            	l_chargeId = "0";
            }
            else
            {
            	l_chargeId = l_request.chargeId;
            }
            l_tsAppliEndDate = l_srvRegiRegistService.calcAppliEndDate(l_strInstitutionCode, l_strBranchCode,
                    l_request.serviceDiv, l_strAccountCode, l_tsSystemTimestamp, Long.parseLong(l_chargeId), l_request.specialDiv, l_request.freeAttributeApplyDiv);
        }
        
        //1.8.3
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("//1.8.3.1 get適用開始日");
            //1.8.3.1 get適用開始日
            l_tsAppliStartDate = l_srvRegiServiceLotInfo.getAppliStartDate();

            //1.8.3.2 get適用終了日
            l_tsAppliEndDate = l_srvRegiServiceLotInfo.getAppliEndDate();
        }
        else if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) &&
            WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv))
        {
            l_tsAppliStartDate = l_tsSystemTimestamp;
            
			log.debug("適用終了日");
			//○適用終了日=引数.申込日に、１ヶ月を足した日付
			Calendar l_caleAppliStartDate = Calendar.getInstance();
			Calendar l_caleNewAppliEndDate = Calendar.getInstance();
				
			//適用終了日の計算
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String l_stAppliEndDate = formatter.format(l_tsAppliStartDate);
			Date l_datAppliEndDate = WEB3DateUtility.getDate(l_stAppliEndDate, "yyyyMMdd");
			l_caleAppliStartDate.setTime(l_datAppliEndDate);
			l_caleNewAppliEndDate.setTime(l_datAppliEndDate);
			int l_maxDay = 0;
			boolean l_maxDayFlag = false;
				
			//適用終了日（申込日より一ヶ月後）
			l_caleNewAppliEndDate.add(Calendar.MONTH, 1);
			log.debug("【月末調整前】l_caleNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				
				
			if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
			{
				l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
				l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
				l_maxDayFlag = true;
			}
			
			//一ヶ月後の前日を求める
			if (!l_maxDayFlag)
			{
				l_caleNewAppliEndDate.add(Calendar.DATE, -1);
			}
                
			log.debug("【月末調整後】l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				
			Date l_datNewAppliEndDate = l_caleNewAppliEndDate.getTime();
			l_tsAppliEndDate = (new Timestamp(l_datNewAppliEndDate.getTime()));

        }

        //1.10  createレスポンス
        WEB3SrvRegiApplyConfirmResponse l_response = (WEB3SrvRegiApplyConfirmResponse)l_request.createResponse();

        //1.11 <レスポンス・セット>
        log.debug("<レスポンス・セット>");
        //○適用開始日=<「適用開始日」「適用終了日」の取得>で取得した「適用開始日」
        l_response.trialStartDate = WEB3DateUtility.toDay(l_tsAppliStartDate);
        //○適用終了日=<「適用開始日」「適用終了日」の取得>で取得した「適用終了日」
        l_response.trialEndDate = WEB3DateUtility.toDay(l_tsAppliEndDate);
        //○確認時発注日=get発注日( )の戻り値
        l_response.checkDate = l_datOrderBizDate;
        //○税込入札額＝(*)
        if (l_srvRegiServiceLotInfo != null && l_srvRegiServiceLotInfo.isAuctionSetting() &&
            l_request.bidAmt != null &&  Double.valueOf(l_request.bidAmt).doubleValue() > 0)
        {
            //WEB3-SRVREGI-A-ＦＴ-0136
            //税率オブジェクトを生成する。
            WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                WEB3DutyTypeDef.CONSUMPTION_TAX,
                l_srvRegiServiceLotInfo.getPaymentDate());
            if (l_dblBidAmt == null)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //「税込入札額」への設定値を「（リクエストデータ.入札額×（１＋取得した税率オブジェクト.get税率()の戻り値/100））を四捨五入したもの」に変更する。
            l_response.taxBidAmt = WEB3StringTypeUtility.formatNumber(Math.rint(l_dblBidAmt.doubleValue() * (1 + l_taxRate.getTaxRate() / 100)));
        }
        else
        {
            log.debug("サービス抽選情報.isオークション設定( )==falseの場合");
            log.debug("税込入札額＝Nullをセットする。");
            l_response.taxBidAmt = null;
        }

        //[リクエストデータ.無料属性申込区分 = '1' の場合]
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //○申込属性区分 = getサービス申込属性情報().申込属性区分
            l_response.applyAttributeDiv = l_srvAppliAttributeRow.getAppliAttribute();

            //○無料対象期間 = get無料対象期間()の戻り値
            l_response.freeTargetPeriod = l_strFreeTargetPeriod;

            //○無料属性申込区分 = '1'(無料属性申込）
            l_response.freeAttributeApplyDiv = WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit利用申込)<BR>
     * サービス利用申込処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）利用申込登録」参照<BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置  1.12.1.以下の条件の場合、例外をスローする。<BR>
     * 　@　@　@　@getサービス申込属性情報() == null の場合、又は、<BR>
     * 　@　@　@　@getサービス申込属性情報().申込属性区分 == '2'(申込不可)<BR>
     * 　@　@　@　@の場合、例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02805  <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.12.3.get無料対象期間( ) == null<BR>　@
     * 　@　@　@　@の場合例外をスローする<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02806 <BR>
     * =============================================== <BR>
     * =============================================== <BR>
     * 　@　@　@　@具体位置    :1.13.1getサービス申込属性情報().申込属性区分 == <BR>
     * 　@　@　@　@'1'(無料対象)　@若しくは<BR>
     * 　@　@　@　@'2'(申込不可) の場合、<BR>
     * 　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class         :  WEB3BusinessLayerException <BR>
     * 　@　@　@　@tag            :  BUSINESS_ERROR_02807 <BR>
     * =============================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用申込完了リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F5F07D01C6
     */
    protected WEB3SrvRegiApplyCompleteResponse submitUseAppli(WEB3SrvRegiApplyCompleteRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitUseAppli(WEB3SrvRegiApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate
        log.debug("validate");
        l_request.validate();

        //1.2 validate注文受付可能
        log.debug("validate注文受付可能");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.3 getCommonOrderValidator
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.4  get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();

        //1.5 validate取引可能顧客
        log.debug("validate取引可能顧客");
        OrderValidationResult l_orderValidationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);
        if(l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            WEB3SrvRegiApplyCompleteResponse l_response = (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.6 get代理入力者
        log.debug("get代理入力者");
        Trader l_trader = this.getTrader();

        //1.7 validate取引パスワード
        log.debug("validate取引パスワード");
        OrderValidationResult l_orderValidationResult2 = l_gentradeOrderValidator.validateTradingPassword(l_trader, l_subAccount, l_request.password);
        if(l_orderValidationResult2.getProcessingResult().isFailedResult())
        {
            log.debug("validate取引パスワード error");
            WEB3SrvRegiApplyCompleteResponse l_response = (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_orderValidationResult2.getProcessingResult().getErrorInfo();
            l_response.errorMessage = l_orderValidationResult2.getProcessingResult().getErrorInfo().error_message;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.8 get発注日
        //Date l_datOrderBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

        //1.9 validate申込登録
        log.debug("validate申込登録");
        //Timestamp l_tsCheckDate = new Timestamp(l_request.checkDate.getTime());
        Long l_dblChangeId = null;
        Double l_dblBidAmt = null;

        if (l_request.chargeId != null)
        {
            l_dblChangeId = new Long(l_request.chargeId);
        }
        if (l_request.bidAmt != null)
        {
            l_dblBidAmt = Double.valueOf(l_request.bidAmt);
        }

        this.validateAppliRegist((WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv, l_dblChangeId,
            l_dblBidAmt, l_tsSystemTimestamp, l_request.applyKindDiv, l_request.password);

        // getサービスマスター
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_request.serviceDiv, false);

        // get申込要サービス
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);

        //getサービス申込属性情報(String, String, String, String, String)
        //証券会社コード=取得した補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
        //部店コード=取得した補助口座オブジェクト.getBranch( ).getBranchCode( )
        //口座コード=取得した補助口座オブジェクト.getMainAccount( ).getAccountCode( )
        //サービス区分=リクエストデータ.ID
        //アップロード区分=null
        List l_lisServiceAppliAttributeInfo =
            l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_request.serviceDiv,
                null);

        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
        String l_strFreeTargetPeriod = null;

        if (l_lisServiceAppliAttributeInfo != null)
        {
            l_srvAppliAttributeRow = (SrvAppliAttributeRow)l_lisServiceAppliAttributeInfo.get(0);
        }
        //リクエストデータ.無料属性申込区分 = '1'　@の場合
        if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
            l_request.freeAttributeApplyDiv))
        {
            //getサービス申込属性情報() == null の場合、又は、
            //getサービス申込属性情報().申込属性区分 == '2'(申込不可) の場合例外をスローする。
            if (l_lisServiceAppliAttributeInfo == null
                || WEB3AppliAttributeDef.CANNOT_APPLI.equals(l_srvAppliAttributeRow.getAppliAttribute()))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02805,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "サービス申込属性情報がnull、または申込属性区分が'2'(申込不可)です。");
                }

            // get無料対象期間( )
            l_strFreeTargetPeriod =
                l_srvRegiApplicationRequiredService.getFreeTargetPeriod();

            //get無料対象期間( ) == null　@の場合例外をスローする。
            if (l_strFreeTargetPeriod == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02806,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "無料対象期間が未指定です。");
            }
        }

        //1.10 <有料無料判定処理>
        String l_strChargeAmt = null;
        Timestamp l_tsPaymentDate = null;
        //1.10.1 <*1 分岐処理>
        if (WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_request.applyKindDiv)
            || WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_request.applyKindDiv)
            || WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(
                l_request.freeAttributeApplyDiv))
        {
            log.debug("1.10.1 <*1 分岐処理>");
            l_strChargeAmt = null;
        }
        //1.10.2  <*2 分岐処理>
        else
        {
            log.debug("1.10.2  <*2 分岐処理>");

            //1.10.2.1.1.1 get抽選設定
            String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

            //1.10.2.2 <*3 分岐処理>
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                log.debug("//1.10.2.2.1 getサービス利用期間料金");
                //1.10.2.2.1 getサービス利用期間料金
                WEB3SrvRegiServiceUsePeriodAmt l_srvRegiServiceUsePeriodAmt =
                    l_srvRegiServiceMaster.getSrvUseTermAmt(Long.parseLong(l_request.chargeId), false);

                //1.10.2.2.2 get利用料金
                l_strChargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceUsePeriodAmt.getUseAmt());
                //Model No.120
                Date l_datOrderBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();

                if (l_datOrderBizDate != null)
                {
                    //1.8:営業日計算(Timestamp)
                    WEB3GentradeBizDate l_datBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));

                    //roll(int)
                    l_tsPaymentDate = l_datBizDate.roll(1);
                }
                else
                {
                    l_tsPaymentDate = null;
                }
            }
            //1.10.2.3  <*4 分岐処理>
            else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
            {
                log.debug("//1.10.2.3.1 getサービス抽選情報");
                //1.10.2.3.1 getサービス抽選情報
                WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                    l_srvRegiServiceInfoManagement.getSrvLotInfo(l_srvRegiServiceMaster.getInstitutionCode(),
                    l_request.serviceDiv, l_tsSystemTimestamp, 0);

                //1.10.2.3.2 get出金日
                l_tsPaymentDate = l_srvRegiServiceLotInfo.getPaymentDate();

                //1.10.2.3.3 get運用区分
                String l_strInvestDiv = l_srvRegiServiceLotInfo.getInvestDiv();

                //1.10.2.3.4 <*5 分岐処理>
                if (WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(l_strInvestDiv) ||
                    WEB3InvestDivDef.USUAL_SELECTION_LOT.equals(l_strInvestDiv))
                {
                    log.debug("//1.10.2.3.4.1 get利用料金");
                    //1.10.2.3.4.1 get利用料金
                    l_strChargeAmt = WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo.getUseAmt());
                }
                //1.10.2.3.5 <*6 分岐処理>
                else if(WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv)
                         && l_request.bidAmt != null &&  Double.valueOf(l_request.bidAmt).doubleValue() > 0)
                {
                    log.debug("1.10.2.3.5 <*6 分岐処理>");

                    //1.10.2.3.5.1 calc消費税
                    //WEB3-SRVREGI-A-ＦＴ-0136
                    //税率オブジェクトを生成する。
                    WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                        WEB3DutyTypeDef.CONSUMPTION_TAX,
                        l_srvRegiServiceLotInfo.getPaymentDate());

                    if (l_dblBidAmt == null)
                    {
                        log.debug(getClass().getName() + STR_METHOD_NAME);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BaseRuntimeException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                            this.getClass().getName() + STR_METHOD_NAME);
                    }

                    //「利用料金」の設定を「calc消費税()」から「（リクエストデータ.入札額（１＋取得した税率オブジェクト.get税率()の戻り値/100））
                    //を四捨五入したもの」とする。
                    l_strChargeAmt = WEB3StringTypeUtility.formatNumber(Math.rint(l_dblBidAmt.doubleValue() * (1 + l_taxRate.getTaxRate() / 100)));
                }
            }
        }
        //1.11  <余力拘束処理>
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication = null;
        if (!(l_strChargeAmt == null ||
            Long.parseLong(l_strChargeAmt) == 0))
        {
            log.debug("1 getログインチャネル");
            //1.11.1 getログインチャネル
            String l_strLoginChannel = this.getLoginChannel();

            //1.11.2 submit余力拘束
            WEB3SrvRegiRegistService l_srvRegiRegistService =
                (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
            WEB3Crypt l_crypt = new WEB3Crypt();
            String l_strPassword = l_crypt.decrypt(l_request.password);
            long l_lngRemainingPowerRestraint =
                l_srvRegiRegistService.submitRemainingPowerRestraint((WEB3GentradeSubAccount)l_subAccount, l_trader,
                Double.parseDouble(l_strChargeAmt), l_tsPaymentDate, l_request.serviceDiv, l_strLoginChannel,l_strPassword);

            //1.12 submitサービス申込登録
            if (l_request.chargeId == null)
            {
                l_gentradeSrvRegiApplication =
                    this.submitSrvAppliRegist((WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv,
                    null, Double.valueOf(l_strChargeAmt),
                    l_tsSystemTimestamp, l_tsPaymentDate, new Long(l_lngRemainingPowerRestraint), l_request.applyKindDiv,
                    l_request.specialDiv, l_request.freeAttributeApplyDiv);
            }
            else
            {
                l_gentradeSrvRegiApplication =
                    this.submitSrvAppliRegist((WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv,
                    new Long(l_request.chargeId), Double.valueOf(l_strChargeAmt),
                    l_tsSystemTimestamp, l_tsPaymentDate, new Long(l_lngRemainingPowerRestraint), l_request.applyKindDiv,
					l_request.specialDiv, l_request.freeAttributeApplyDiv);
            }
        }
        else
        {
            //1.12 submitサービス申込登録
            if (l_request.chargeId == null)
            {
                l_gentradeSrvRegiApplication = this.submitSrvAppliRegist(
                    (WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv,
                    null, null,
                    l_tsSystemTimestamp, l_tsPaymentDate, null, l_request.applyKindDiv, l_request.specialDiv, l_request.freeAttributeApplyDiv);
            }
            else
            {
                l_gentradeSrvRegiApplication = this.submitSrvAppliRegist(
                    (WEB3GentradeSubAccount)l_subAccount, l_request.serviceDiv,
                    new Long(l_request.chargeId), null,
                    l_tsSystemTimestamp, l_tsPaymentDate, null, l_request.applyKindDiv, l_request.specialDiv, l_request.freeAttributeApplyDiv);
            }
        }
        //U00861
        if (WEB3SrvRegiMailDivDef.MAIL_SENDED.equals(l_srvRegiApplicationRequiredService.getStartMailDiv()) &&
            !WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()))
        {
            log.debug("1.13 sendMailProcess");
            //1.13 sendMailProcess
            WEB3SrvRegiExecSendMailService l_srvRegiExecSendMailService =
                (WEB3SrvRegiExecSendMailService)Services.getService(WEB3SrvRegiExecSendMailService.class);
            l_srvRegiExecSendMailService.sendMailProcess(l_gentradeSrvRegiApplication);
        }

        //1.14 createレスポンス
        WEB3SrvRegiApplyCompleteResponse l_response = (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
        l_response.lastUpdatedTimestamp = l_tsSystemTimestamp;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate申込登録)<BR>
     * 利用申込処理の発注審査を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）validate申込登録」参照<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込登録」): <BR>
     * 1.2 <補足入力チェック><BR>
     * 1) get抽選設定( )="無"の場合
     * 1-1) 引数.利用期間ID=nullの場合、例外をスローする。
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01075<BR>
     * 1-2) 引数.入札額!=nullの場合、例外をスローする。
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01076<BR>
     * 1-3) 引数.申込種別区分が以下の値以外の場合、例外をスローする。
     *　@　@"通常申込"
     *   　@"継続申込"
     *　@　@"試用申込"
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00980<BR>
     *
     * 2) get抽選設定( )="有"の場合
     * 2-1) 引数.利用期間ID!=nullの場合、例外をスローする。
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01077<BR>
     * 2-2) 引数.入札額!=nullの場合、例外をスローする。
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01862<BR>
     * 2-3) 引数.申込種別区分が以下の値以外の場合、例外をスローする。
     *    　@"通常申込"
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00980<BR>
     * =========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込登録」): <BR>
     *         1.3.isMainAccountAppli(補助口座, WEB3SrvRegiServiceMaster)<BR>
     *         is顧客申込可能( )=falseの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01000<BR>
     * =========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込登録」): <BR>
     *         1.4.2.1 is手数料条件<BR>
     *         is手数料条件( )=falseの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01177<BR>
     * =========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込登録」): <BR>
     *         1.5.1get申込抽選区分( )<BR>
     *         以下の条件に合致した場合、例外をスローする。<BR>
     * <BR>
     *         ○引数.申込種別区分=="通常申込"の場合<BR>
     *        　@-getサービス申込登録( )の戻り値!=nullであり、<BR>
     *         get申込抽選区分( )="試用"以外の場合<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01012<BR>
     * <BR>
     *         ○引数.申込種別区分=="継続申込"の場合<BR>
     *        　@-getサービス申込登録( )の戻り値=nullの場合<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01013<BR>
     *        　@-getサービス申込登録( )の戻り値!=nullであり、<BR>
     *         get申込抽選区分( )="当選／本申込"以外の場合<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01014<BR>
     * <BR>
     *         ○引数.申込種別区分=="試用申込"の場合<BR>
     *          　@-getサービス申込登録( )の戻り値!=nullの場合<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01015<BR>
     *
     *         ○引数.申込種別区分=="無料申込"の場合<BR>
     *          　@-getサービス申込登録( )の戻り値!=nullの場合<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01178<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込登録」): <BR>
     *         1.7.1.1.isTrialAppliPossible(String, String, String, String)<BR>
     *         is試用申込可能( )=falseの場合、<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01016<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込登録」): <BR>
     *         1.8.1<分岐処理 *5><BR>
     *          <分岐処理 *5><BR>
     *         getサービス申込登録( )!=nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01017<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込登録」): <BR>
     *         1.8.2getSrvLotInfo(String, String, Timestamp, int)<BR>
     *         サービス抽選情報オブジェクトが取得できなかった場合、<BR>
     *         例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01018<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）validate申込登録」): <BR>
     *         1.8.4<分岐処理 *7><BR>
     *          <分岐処理 *7><BR>
     *          以下の条件に合致する場合、例外をスローする。<BR>
     *          ○引数.入札額≧最低入札額（*)ではない場合<BR>
     *          または<BR>
     *          ○引数.入札額 mod 取得したサービス抽選情報オブジェクト.get入札単位( )=0ではない場合<BR>
     * <BR>
     *         (*) 取得したサービス抽選情報オブジェクト.get利用料金( )<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01019<BR>
     * ==========================================================<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座　@オブジェクト<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_srvUsePeriodId - (利用期間ID)<BR>
     * @@param l_biddingPrice - (入札額)<BR>
     * @@param l_tsAppliDate - (申込日)<BR>
     * @@param l_strAppliTpyeDiv - (申込種別区分)<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * @@roseuid 413E611202E8
     */
    private void validateAppliRegist(WEB3GentradeSubAccount l_subAccount, String l_strSrvDiv, Long l_srvUsePeriodId,
        Double l_biddingPrice, Timestamp l_tsAppliDate, String l_strAppliTpyeDiv, String l_strPassword)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " validateAppliRegist(WEB3GentradeSubAccount, String, Long, Double, Timestamp, String, String)";
        log.entering(STR_METHOD_NAME);
        //1.1  getサービスマスター
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //1.1 get申込要サービス
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);
        String l_strLotDiv = null;
        if (l_srvRegiApplicationRequiredService == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }

        //1.1.1. get抽選設定
        l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        //1.2 <補足入力チェック>

        //1) get抽選設定( )="無"の場合
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            //1-1) 引数.入札額!=nullの場合、例外をスローする。
            if (l_biddingPrice != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01076,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //1-2) 引数.申込種別区分が以下の値以外の場合、例外をスローする。
            if (!WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(l_strAppliTpyeDiv) &&
                !WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv) &&
                !WEB3SrvRegiApplyKindDivDef.USUAL_APPLI.equals(l_strAppliTpyeDiv) &&
                !WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00980,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        //2) get抽選設定( )="有"の場合
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            //2-1) 引数.利用期間ID!=nullの場合、例外をスローする。
            if (l_srvUsePeriodId != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01077,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //2-2) 引数.入札額==nullの場合、例外をスローする。
            //U00836
            //2-3) 引数.申込種別区分が以下の値以外の場合、例外をスローする。
            if (!WEB3SrvRegiApplyKindDivDef.USUAL_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00980,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.3 is顧客申込可能
        boolean l_blnAccountAppliPossible =
            l_srvRegiServiceInfoManagement.isAccountAppliPossible(l_subAccount, l_srvRegiServiceMaster);
        if (!l_blnAccountAppliPossible)
        {
            log.debug("is顧客申込可能");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01000,
                getClass().getName() + STR_METHOD_NAME);
        }

        //サービスマスタ.特殊処理区分 = null（通常サービス） 以外の場合
        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
        if (l_strSpecialProcessDiv != null)
        {
            //取得した補助口座オブジェクト
            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;

            //is新規申込(補助口座, String)
            //[引数]
            //補助口座 = 取得した補助口座オブジェクト
            //サービス区分=引数.サービス区分
            boolean l_blnIsNewApply =
                l_srvRegiServiceInfoManagement.isNewApply(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_strSrvDiv);

            //validate特殊申込(サービスマスター, String, String, String, boolean)
            //[引数]
            //サービスマスタ = 取得したサービスマスターオブジェクト
            //証券会社コード = 引数.補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
            //部店コード = 引数.補助口座オブジェクト.getBranch( ).getBranchCode( )
            //口座コード = 引数.補助口座オブジェクト.getMainAccount( ).getAccountCode( )
            //新規申込区分 = is新規申込( ) の戻り値
            l_srvRegiServiceInfoManagement.validateSpecialApply(
                l_srvRegiServiceMaster,
                l_gentradeSubAccount.getInstitution().getInstitutionCode(),
                l_gentradeSubAccount.getWeb3GenBranch().getBranchCode(),
                l_gentradeSubAccount.getMainAccount().getAccountCode(),
                l_blnIsNewApply);
        }

        //validate電子鳩同意()
        l_srvRegiServiceInfoManagement.validateBatoAgreement(l_srvRegiServiceMaster);
                
        //1.4  <引数.申込種別区分="無料申込"の場合>
        if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTpyeDiv))
        {
            log.debug("<引数.申込種別区分=無料申込の場合>");
            //1.4.1  get提供形式
            String l_strProvidModel = l_srvRegiApplicationRequiredService.getSupplyDiv();

            //1.4.2  <get提供形式( )!=nullの場合>
            if (l_strProvidModel != null)
            {
                log.debug("get提供形式( )!=nullの場合");
                //1.4.2.1 is手数料条件
                boolean l_blnCommCond = l_srvRegiServiceInfoManagement.isCommCond(l_subAccount, l_srvRegiServiceMaster);
                if (!l_blnCommCond)
                {
                    log.debug("!l_blnCommCond");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01177,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01179,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        //1.4 getサービス申込登録
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                WEB3SrvRegiCancelDivDef.USUAL_DEFAULT, WEB3EffectiveDivDef.EFFECTIVE, false);

        //1.7 <分岐処理 *1>
        if (l_gentradeSrvRegiApplication != null)
        {
            log.debug("getサービス申込登録");
            //1.7.1 get申込抽選区分
            String l_strAppliLotDiv = l_gentradeSrvRegiApplication.getAppliLotDiv();
            //1.7.2 get無料属性申込区分
            String l_freeSrvDiv = l_gentradeSrvRegiApplication.getFreeSrvDiv();
            
            //○引数.申込種別区分=="通常申込"の場合 -getサービス申込登録( )の戻り値!=nullであり、get申込抽選区分( )="試用"以外の場合
            //U00867
            if (WEB3SrvRegiApplyKindDivDef.USUAL_APPLI.equals(l_strAppliTpyeDiv) &&
                WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) &&
                !WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_strAppliLotDiv))
            {
                log.debug("BUSINESS_ERROR_01012");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01012,
                    getClass().getName() + STR_METHOD_NAME);
            }
            if (WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(l_strAppliTpyeDiv) &&
                !WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_strAppliLotDiv))
            {
                log.debug("BUSINESS_ERROR_01014");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01013,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            if (WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv)) 
            {
                log.debug("BUSINESS_ERROR_01015");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01015,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.debug("BUSINESS_ERROR_01178");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01178,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        else
        {
            if (WEB3SrvRegiApplyKindDivDef.CONTINUE_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.debug("BUSINESS_ERROR_01013");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01013,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }

        long l_lngUseAmt = 0;
        String l_strInvestDiv = null;
        WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo = null;

        //init消費税
        double l_dblSalesTax = 0;
        //1.7 <分岐処理 *2>
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv)");
            //1.7.1 <分岐処理 *3>
            if (WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv))
            {
                log.debug("WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv)");
                //1.7.1.1 is試用申込可能
                boolean l_blnTrialAppliPossible =
                    l_srvRegiServiceInfoManagement.isTrialAppliPossible(l_strInstitutionCode, l_strBranchCode,
                    l_strSrvDiv, l_strAccountCode);
                if(!l_blnTrialAppliPossible)
                {
                    log.debug("is試用申込可能");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01016,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        //1.8 <分岐処理 *4>
        else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv)");
            //U00867
            //1.8.1 <分岐処理 *5>

            //1.8.2 getサービス抽選情報
            l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);
            if (l_srvRegiServiceLotInfo == null)
            {
                log.debug("l_srvRegiServiceLotInfo == null");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01018,
                    getClass().getName() + STR_METHOD_NAME);
            }
            //U00867
            if (l_gentradeSrvRegiApplication != null)
            {
                long l_lngConsecutiveNumbersA = l_srvRegiServiceLotInfo.getConsecutiveNumbers();

                WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfoB =
                    l_srvRegiServiceInfoManagement.getSrvLotInfo(
                    l_strInstitutionCode, l_strSrvDiv, l_gentradeSrvRegiApplication.getAppliDate(), 0);

                long l_lngConsecutiveNumbersB = l_srvRegiServiceLotInfoB.getConsecutiveNumbers();

                if (l_lngConsecutiveNumbersA == l_lngConsecutiveNumbersB)
                {
                    log.debug("同一抽選情報への重複申込エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01926,
                        getClass().getName() + STR_METHOD_NAME,
                        "同一抽選情報への重複申込エラー。");
                }
            }

            //1.8.2.1 get運用区分
            l_strInvestDiv = l_srvRegiServiceLotInfo.getInvestDiv();

            //1.8.3 <分岐処理 *6>
            if (WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv)
                && (l_biddingPrice != null && l_biddingPrice.doubleValue() >= 0))
            {
                log.debug(" 2:通常運用（抽選有-オークション）");

                //1.8.3.1 calc消費税

                //1.8.3.2 get入札単位
                Long l_getBiddingPrice = l_srvRegiServiceLotInfo.getBiddingPrice();

                //1.8.3.3 get利用料金
                l_lngUseAmt = l_srvRegiServiceLotInfo.getUseAmt();

                //1.8.4<分岐処理 *7>
                //WEB3-SRVREGI-A-ＦＴ-0136
                //「（引数.入札額×（１＋取得した税率オブジェクト.get税率()の戻り値/100））を四捨五入したもの≧最低入札額(*)ではない場合」
                //税率オブジェクトを生成する。
                WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3DutyTypeDef.CONSUMPTION_TAX,
                    l_srvRegiServiceLotInfo.getPaymentDate());

                if (l_biddingPrice == null)
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                l_dblSalesTax = (Math.rint(l_biddingPrice.doubleValue() * (1 + l_taxRate.getTaxRate() / 100)));
                //○計算サービス.calc消費税( )の戻り値≧最低入札額（*)ではない場合 または
                //○引数.入札額 mod 取得したサービス抽選情報オブジェクト.get入札単位( )=0ではない場合
                long l_lngBiddingPrice = 0;
                if (l_getBiddingPrice != null)
                {
                    log.debug("l_getBiddingPrice != null");
                    l_lngBiddingPrice = l_getBiddingPrice.longValue();
                }
                double l_dblBiddingPrice = 0;
                if (l_biddingPrice != null)
                {
                    log.debug("l_biddingPrice != null");
                    l_dblBiddingPrice = l_biddingPrice.doubleValue();
                }
                if (l_dblSalesTax < l_lngUseAmt ||
                    (l_dblBiddingPrice % l_lngBiddingPrice) != 0)
                {
                    log.debug("BUSINESS_ERROR_01019");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01019,
                        getClass().getName() + STR_METHOD_NAME);
                }
            }
        }

        WEB3SrvRegiServiceUsePeriodAmt l_srvRegiServiceUsePeriodAmt = null;
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) && l_srvUsePeriodId != null)
        {
            l_srvRegiServiceUsePeriodAmt =
                l_srvRegiServiceMaster.getSrvUseTermAmt(l_srvUsePeriodId.longValue(), false);
        }

        //1.9 <分岐処理 *8>
        if ((WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv) &&
            !(WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTpyeDiv) ||
            WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTpyeDiv)) &&
            l_srvRegiServiceUsePeriodAmt != null &&
            l_srvRegiServiceUsePeriodAmt.getUseAmt() > 0)
            ||
            (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv) &&
            (WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(l_strInvestDiv) ||
            WEB3InvestDivDef.USUAL_SELECTION_LOT.equals(l_strInvestDiv)) &&
            l_lngUseAmt > 0)
            ||
            (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv) &&
            WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv) &&
            l_biddingPrice.doubleValue() > 0))
        {
            log.debug("<分岐処理 *8>");
            //1.9.1 get代理入力者
            Trader l_trader = this.getTrader();

            //1.9.2 getログインチャネル
            String l_strLoginChannel = this.getLoginChannel();

            //1.9.4 ＜get抽選設定()＝"無"の場合＞
            Date l_datOrderBizDate = null;
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                //1.9.4.1getget発注日
                l_datOrderBizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();
            }

            //1.9.4 validate取引余力
            double l_dblUseAmt = 0;
            Timestamp l_ts = null;
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                log.debug(" (*-1) get抽選設定( )='無'の場合、サービス利用期間料金オブジェクト.get利用料金( )の戻り値をセットする。");
                if (l_srvRegiServiceUsePeriodAmt != null)
                {
                    l_dblUseAmt = l_srvRegiServiceUsePeriodAmt.getUseAmt();
                }
                if (l_datOrderBizDate != null)
                {
                    // 営業日計算(Timestamp)
                    WEB3GentradeBizDate l_datBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));

                    l_ts = l_datBizDate.roll(1);
                }
            }
            else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv) &&
                WEB3InvestDivDef.USUAL_SELECTION_LOT_AUCTION.equals(l_strInvestDiv))
            {
                //WEB3-SRVREGI-A-ＦＴ-0136
                //税率オブジェクトを生成する。
                WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(l_subAccount.getInstitution().getInstitutionCode(),
                    WEB3DutyTypeDef.CONSUMPTION_TAX,
                    l_srvRegiServiceLotInfo.getPaymentDate());

                //「利用料金」への設定値を、「（引数.入札額×（１＋取得した税率オブジェクト.get税率()の戻り値/100））を四捨五入したもの」
				l_dblUseAmt = (Math.rint(l_biddingPrice.doubleValue() * (1 + l_taxRate.getTaxRate() / 100)));
            }
            else
            {
                log.debug(" (*-3) 上記以外の場合、getサービス抽選情報オブジェクト.get利用料金( )の戻り値をセットする。");
                l_dblUseAmt = l_lngUseAmt;
            }

            if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
            {
                log.debug("l_ts = l_srvRegiServiceLotInfo.getPaymentDate();");
                l_ts = l_srvRegiServiceLotInfo.getPaymentDate();
            }

            //WEB3Crypt l_crypt = new WEB3Crypt();
            l_srvRegiRegistService.validateTradingPower(l_subAccount, l_trader, l_dblUseAmt, l_ts, l_strSrvDiv,
                l_strLoginChannel);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submitサービス申込登録)<BR>
     * サービス利用申込の更新処理を行い、<BR>
     * 申込登録IDを返却する。<BR>
     * <BR>
     * シーケンス図「（サービス利用）submitサービス申込登録」参照<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座　@オブジェクト<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * @@param l_srvUsePeriodId - (利用期間ID)<BR>
     * @@param l_useAmt - (利用料金)<BR>
     * @@param l_tsAppliDate - (申込日)<BR>
     * @@param l_tsPaymentDate - (出金日)<BR>
     * @@param l_orderId - (注文ID)<BR>
     * @@param l_strAppliTypeDiv - (申込種別区分)<BR>
     * @@param l_strSpecialDiv - (特殊申込区分)<BR>
     * @@param l_strFreeAttributeApplyDiv - (無料属性申込区分)<BR>
     * @@roseuid 413E611703B3
     */
    private WEB3GentradeSrvRegiApplication submitSrvAppliRegist(WEB3GentradeSubAccount l_subAccount, String l_strSrvDiv,
    					Long l_srvUsePeriodId, Double l_useAmt, Timestamp l_tsAppliDate, Timestamp l_tsPaymentDate, Long l_orderId, String l_strAppliTypeDiv, String l_strSpecialDiv, String l_strFreeAttributeApplyDiv)
        						throws WEB3BaseException
    {
        String STR_METHOD_NAME = " submitSrvAppliRegist(WEB3GentradeSubAccount, String, Long, Double, Timestamp, Long, String)";
        log.entering(STR_METHOD_NAME);
        //1.1  getサービスマスター
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster =
            l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_strSrvDiv, false);

        //1.2 getサービス申込登録
        WEB3SrvRegiRegistService l_srvRegiRegistService =
            (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
        String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
        String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();

        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
            l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                null, WEB3EffectiveDivDef.EFFECTIVE, true);

        WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplicationNew = null;

        log.debug("createNew申込登録");
        l_gentradeSrvRegiApplicationNew =
            WEB3GentradeSrvRegiApplication.createNewSrvRegiApplication
            (l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);

        //1.4 get申込要サービス
        WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
            l_srvRegiServiceMaster.getAppliRequiredSrv(false);
        if (l_srvRegiApplicationRequiredService == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME);
        }
        //1.4.1get抽選設定
        String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

        Timestamp l_tsAppliDateFormat = new Timestamp(WEB3DateUtility.toDay(l_tsAppliDate).getTime());

        //1.9.1 get代理入力者
        Trader l_trader = this.getTrader();

        String l_strTraderCode = null;

        if (l_trader != null)
        {
            l_strTraderCode = l_trader.getTraderCode();
        }

        List l_lisServiceAppliAttributeInfo = null;
        String l_strFreeTargetPeriod = null;

        // is新規申込(補助口座, String)
        // [引数]
        // 補助口座 = 取得した補助口座オブジェクト
        // サービス区分=引数.サービス区分
        boolean l_blnIsNewApply =
            l_srvRegiServiceInfoManagement.isNewApply(
                (WEB3GentradeSubAccount)l_subAccount,
                l_strSrvDiv);

        //1.5 <分岐処理 *1>
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
        {
            log.debug("WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv)");

            //1.5.3 <分岐処理 *2>
            if (!WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTypeDiv))
            {

                //getサービス申込属性情報(String, String, String, String, String)
                //証券会社コード：getサービスマスター一覧( )の戻り値のサービスマスターオブジェクト.get証券会社コード( )
                //部店コード：取得した補助口座オブジェクト.getMainAccount( ).getBranch( ).getBranchCode( )
                //口座コード=取得した補助口座オブジェクト.getMainAccount( ).getAccountCode( )
                //サービス区分：getサービスマスター一覧( )の戻り値のサービスマスターオブジェクト.getサービス区分( )
                //アップロード区分=null
                l_lisServiceAppliAttributeInfo =
                    l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                        l_srvRegiServiceMaster.getInstitutionCode(),
                        l_subAccount.getMainAccount().getBranch().getBranchCode(),
                        l_subAccount.getMainAccount().getAccountCode(),
                        l_srvRegiServiceMaster.getSrvDiv(),
                        null);

                // getサービス申込属性情報() != null の場合
                if (l_lisServiceAppliAttributeInfo != null)
                {
                    //get無料対象期間( )
                    l_strFreeTargetPeriod =
                        l_srvRegiApplicationRequiredService.getFreeTargetPeriod();
                }

				//リクエストデータ.無料属性申込区分 = '1'　@の場合 又は、
				//引数.申込種別区分 == "試用申込" の場合
                long l_chargeId = 0;
				if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(l_strFreeAttributeApplyDiv) || 
					(WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTypeDiv))) 
                {
                	l_chargeId = Long.parseLong("0");                	
                }
                else
                {
                	l_chargeId = l_srvUsePeriodId.longValue();
                }
                
                log.debug("calc適用終了日");
                //1.5.3.1 calc適用終了日
                Timestamp l_tsAppliEndDate =
                    l_srvRegiRegistService.calcAppliEndDate(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode,
                    l_tsAppliDate, l_chargeId, l_strSpecialDiv, l_strFreeAttributeApplyDiv);

                //1.5.3.2 <本申込・継続申込のプロパティ・セット>
                //○適用開始日=引数.申込日
                l_gentradeSrvRegiApplicationNew.setAppliStartDate(l_tsAppliDateFormat);
                //○適用終了日=this.calc適用終了日( )の戻り値
                l_gentradeSrvRegiApplicationNew.setAppliEndDate(new Timestamp(WEB3DateUtility.toDay(l_tsAppliEndDate).getTime()));
                
                //○申込日=引数.申込日
                l_gentradeSrvRegiApplicationNew.setAppliDate(l_tsAppliDate);
                //○登録区分=(*1)
                // (*1-1) 引数.利用料金=nullの場合 "無料"をセットする。
                if (l_useAmt == null)
                {
                    log.debug("引数.利用料金=null");
                    l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.FREE);
                }
                else
                {
                    log.debug("引数.利用料金 != null");
                    l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.CHARGE);
                }

                //  ○利用料金=引数.利用料金
                l_gentradeSrvRegiApplicationNew.setUseAmt(l_useAmt);
                //  ○出金日=(*2)
                //      (*2-1) 引数.利用料金=nullの場合 nullをセットする。
                if (l_useAmt == null)
                {
                    log.debug("引数.利用料金=null");
                    l_gentradeSrvRegiApplicationNew.setPaymentDate(null);
                }
                //(*2-2) 引数.利用料金!=nullの場合
                //  this.get発注日( )の戻り値をセットする。
                else
                {
                    //Model No.120
                    log.debug("引数.利用料金!=null");
                    //森田さんの指定
                    l_gentradeSrvRegiApplicationNew.setPaymentDate(l_tsPaymentDate);
                }
                //○自動当選取消期限日=null
                l_gentradeSrvRegiApplicationNew.setCancelLimitDate(null);

                //set最終更新者
                if (l_trader != null)
                {
                    l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strTraderCode);
                }
                else
                {
					//障害対応 NO_2051
                    l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strAccountCode.substring(0,6));
                }

                //○無料属性申込区分=
                //getサービス申込属性情報() != null 尚且つ get無料対象期間() != nullの場合
                // '1'：無料属性申込をセット。
                if (l_lisServiceAppliAttributeInfo != null && l_strFreeTargetPeriod != null)
                {
                    l_gentradeSrvRegiApplicationNew.setFreeSrvDiv(
                        WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY);
                }
                
                //○注文ID、申込抽選区分のプロパティセット
                //申込種別区分 = "試用申込"の場合と、"試用申込"以外の場合
                if (WEB3SrvRegiApplyKindDivDef.TRIAL_APPLI.equals(l_strAppliTypeDiv))
                {
                	//○注文ID=null
                	l_gentradeSrvRegiApplicationNew.setOrderId(null);
                    //○申込抽選区分="試用"
                    l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.TRIAL_APPLI);
                }
                else
                {
                    //○注文ID=引数.注文ID
                    l_gentradeSrvRegiApplicationNew.setOrderId(l_orderId);
                    //○申込抽選区分="本申込"
                    l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI);
                }


                //get抽選設定( )="無"の場合、且つ、サービスマスタオブジェクト.特殊処理区分 = 1（外部連携サービス） の場合
                SrvRegiMasterParams l_srvRegiMasterParams =
                    (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
                String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv)
                    && WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
                {
                    // submit外部連携情報(String, String, String, String, Timestamp, Timestamp, boolean)
                    // 外部連携情報管理テーブルのUPDATEを行う。
                    // [引数]
                    // 証券会社コード=取得した補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
                    //部店コード=取得した補助口座オブジェクト.getBranch( ).getBranchCode( )
                    //口座コード=取得した補助口座オブジェクト.getMainAccoutn( ).getAccountCode( )
                    //サービス区分 = 引数.サービス区分
                    //適用開始日 = 現在日付
                    //適用終了日 = calc適用終了日( )の戻り値
                    //新規申込区分 = is新規申込( ) の戻り値
                    WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                        (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                    l_srvRegiOtherOrgService.submitOtherOrgInfo(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_subAccount.getWeb3GenBranch().getBranchCode(),
                        l_subAccount.getMainAccount().getAccountCode(),
                        l_strSrvDiv,
                        GtlUtils.getTradingSystem().getSystemTimestamp(),
                        l_tsAppliEndDate,
                        l_blnIsNewApply);
                }
            }

            //1.6.3 <分岐処理 *4>
            if (WEB3SrvRegiApplyKindDivDef.FREE_APPLI.equals(l_strAppliTypeDiv))
            {
                log.debug("無料申込のプロパティ・セット");
                //1.6.3.1  <無料申込のプロパティ・セット>
                //○適用開始日=引数.申込日
                l_gentradeSrvRegiApplicationNew.setAppliStartDate(l_tsAppliDateFormat);
                
				log.debug("適用終了日");
                //○適用終了日=引数.申込日に、１ヶ月を足した日付
				Calendar l_caleAppliStartDate = Calendar.getInstance();
				Calendar l_caleNewAppliEndDate = Calendar.getInstance();
				
				//適用終了日の計算
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String l_stAppliEndDate = formatter.format(l_tsAppliDateFormat);
				Date l_datAppliEndDate = WEB3DateUtility.getDate(l_stAppliEndDate, "yyyyMMdd");
				l_caleAppliStartDate.setTime(l_datAppliEndDate);
				l_caleNewAppliEndDate.setTime(l_datAppliEndDate);
				int l_maxDay = 0;
				boolean l_maxDayFlag = false;
				
				//適用終了日（申込日より一ヶ月後）
				l_caleNewAppliEndDate.add(Calendar.MONTH, 1);
				log.debug("【月末調整前】l_caleNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				
				
				if (l_caleAppliStartDate.get(Calendar.DATE) > l_caleNewAppliEndDate.get(Calendar.DATE))
				{
					l_maxDay = l_caleNewAppliEndDate.getActualMaximum(Calendar.DATE);
					l_caleNewAppliEndDate.set(Calendar.DATE, l_maxDay);
					l_maxDayFlag = true;
				}
				
				//一ヶ月後の前日を求める
				if (!l_maxDayFlag)
				{
					l_caleNewAppliEndDate.add(Calendar.DATE, -1);
				}
                
				log.debug("【月末調整後】l_datNewAppliEndDate = " + l_caleNewAppliEndDate.getTime());
				
				Date l_datNewAppliEndDate = l_caleNewAppliEndDate.getTime();
				l_gentradeSrvRegiApplicationNew.setAppliEndDate(new Timestamp(l_datNewAppliEndDate.getTime()));                
                
                //○注文ID=null
                l_gentradeSrvRegiApplicationNew.setOrderId(null);
                //○申込日=引数.申込日
                l_gentradeSrvRegiApplicationNew.setAppliDate(l_tsAppliDate);
                //○登録区分="無料"
                l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.FREE);
                //○申込抽選区分="本申込"
                l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI);
                //○利用料金=null
                l_gentradeSrvRegiApplicationNew.setUseAmt(null);
                //○出金日=null
                l_gentradeSrvRegiApplicationNew.setPaymentDate(null);
                //○自動当選取消期限日=null
                l_gentradeSrvRegiApplicationNew.setCancelLimitDate(null);

                //set最終更新者
                if (l_trader != null)
                {
                    l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strTraderCode);
                }
                else
                {
					//障害対応 NO_2051
                    l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strAccountCode.substring(0,6));
                }
                
                //get抽選設定( )="無"の場合、且つ、サービスマスタオブジェクト.特殊処理区分 = 1（外部連携サービス） の場合
                SrvRegiMasterParams l_srvRegiMasterParams =
                    (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
                String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv)
                    && WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
                {
                    // submit外部連携情報(String, String, String, String, Timestamp, Timestamp, boolean)
                    // 外部連携情報管理テーブルのUPDATEを行う。
                    // [引数]
                    // 証券会社コード=取得した補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
                    //部店コード=取得した補助口座オブジェクト.getBranch( ).getBranchCode( )
                    //口座コード=取得した補助口座オブジェクト.getMainAccoutn( ).getAccountCode( )
                    //サービス区分 = 引数.サービス区分
                    //適用開始日 = 現在日付
                    //適用終了日 = calc適用終了日( )の戻り値
                    //新規申込区分 = is新規申込( ) の戻り値
                    WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                        (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
                    l_srvRegiOtherOrgService.submitOtherOrgInfo(
                        l_subAccount.getInstitution().getInstitutionCode(),
                        l_subAccount.getWeb3GenBranch().getBranchCode(),
                        l_subAccount.getMainAccount().getAccountCode(),
                        l_strSrvDiv,
                        GtlUtils.getTradingSystem().getSystemTimestamp(),
                        new Timestamp(l_datNewAppliEndDate.getTime()),
                        l_blnIsNewApply);
                }
            }
			//U00877
			//障害対応 NO_2047
        }

        //  リクエストデータ.無料属性申込区分 = '1'　@の場合
		if (WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY.equals(l_strFreeAttributeApplyDiv))
        {
            // update顧客サービス申込属性

            Integer l_intUpdateCount = this.updateSrvApplyAttribute(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strSrvDiv);

            // update対象のレコードがなかった場合、例外をスローする。
            if (l_intUpdateCount == null)
            {
                log.debug("レコードが存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02837,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strBranchCode + "." + l_strSrvDiv + "." + l_strAccountCode);
            }
        }

        //1.7 <分岐処理 *5>
        if(WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
        {
            log.debug("1.6.1 getサービス抽選情報");
            //1.6.1 getサービス抽選情報
            WEB3SrvRegiServiceLotInfo l_srvRegiServiceLotInfo =
                l_srvRegiServiceInfoManagement.getSrvLotInfo(l_strInstitutionCode, l_strSrvDiv, l_tsAppliDate, 0);

            //1.6.2 <抽選有サービスのプロパティ・セット>
            //○適用開始日=取得したサービス抽選情報オブジェクト.get適用開始日( )
            l_gentradeSrvRegiApplicationNew.setAppliStartDate(l_srvRegiServiceLotInfo.getAppliStartDate());
            //○適用終了日=取得したサービス抽選情報オブジェクト.get適用終了日( )
            l_gentradeSrvRegiApplicationNew.setAppliEndDate(l_srvRegiServiceLotInfo.getAppliEndDate());
            //○注文ID=引数.注文ID
            l_gentradeSrvRegiApplicationNew.setOrderId(l_orderId);
            //○申込日=引数.申込日
            l_gentradeSrvRegiApplicationNew.setAppliDate(l_tsAppliDate);
            //○登録区分=(*1)
            //(*1-1) 引数.利用料金=nullの場合      "無料"をセットする。
            if (l_useAmt == null)
            {
                log.debug("利用料金=nullの場合");
                l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.FREE);
            }
            //(*1-2) 引数.利用料金!=nullの場合      "有料"をセットする。
            else
            {
                log.debug("引数.利用料金!=nullの場合");
                l_gentradeSrvRegiApplicationNew.setPaymentDiv(WEB3PaymentDivDef.CHARGE);
            }
            //○申込抽選区分=(*2)
            //(*2-1) 取得したサービス抽選情報オブジェクト.get運用区分="無条件当選"の場合  "自動当選"をセットする。
            //○自動当選取消期限日=(*4)
            if (WEB3InvestDivDef.NO_CONDITIONS_ELECTION.equals(l_srvRegiServiceLotInfo.getInvestDiv()))
            {
                log.debug("自動当選をセットする");
                l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.AUTO_ELECTION);
                l_gentradeSrvRegiApplicationNew.setCancelLimitDate(l_srvRegiServiceLotInfo.getAppliDateTo());
            }
            //(*2-2) 取得したサービス抽選情報オブジェクト.get運用区分="無条件当選"以外の場合 "申込"をセットする。
            else
            {
                log.debug("申込をセットする");
                l_gentradeSrvRegiApplicationNew.setAppliLotDiv(WEB3AppliLotDivDef.APPLI);
                l_gentradeSrvRegiApplicationNew.setCancelLimitDate(null);
            }
            //○利用料金=引数.利用料金をセットする。
            l_gentradeSrvRegiApplicationNew.setUseAmt(l_useAmt);
            //○出金日=取得したサービス抽選情報オブジェクト.get出金日( )
            l_gentradeSrvRegiApplicationNew.setPaymentDate(l_srvRegiServiceLotInfo.getPaymentDate());

            //set最終更新者
            if (l_trader != null)
            {
                l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strTraderCode);
            }
            else
            {
				//障害対応 NO_2051
                l_gentradeSrvRegiApplicationNew.setLastUpdater(l_strAccountCode.substring(0,6));
            }
        }
 
  		//障害対応 NO_2047		  		       
        //is新規申込の戻り値がfalseの場合、以下手続きを実施。
		if (!l_blnIsNewApply)
		{
			log.debug("<既存行の無効化処理>");
			//1.8.1 set有効区分
			l_gentradeSrvRegiApplication.setEffectiveDiv(WEB3EffectiveDivDef.INEFFECTIVE);

            //[WEB3-SRVREGI-A-FT-0166の再質問]というQAにより修正
			//1.8.2 set最終更新者
			if (l_trader != null)
			{
				l_gentradeSrvRegiApplication.setLastUpdater(l_strTraderCode);
			}
			else
			{
				//障害対応 NO_2051
				l_gentradeSrvRegiApplication.setLastUpdater(l_strAccountCode.substring(0,6));
			}

			//1.8.2 saveサービス申込登録
			l_gentradeSrvRegiApplication.saveSrvRegiApplication();
		}

        //1.7 saveNewサービス申込登録
        log.debug("saveNewサービス申込登録");
        l_gentradeSrvRegiApplicationNew.saveNewSrvRegiApplication();

        //U00877
        //1.8 <既存行の無効化処理>


        //1.9 get初期申込区分
        String l_strInitializeAppliDiv =
            l_srvRegiRegistService.getInitializeAppliDiv(l_strInstitutionCode, l_strBranchCode, l_strSrvDiv, l_strAccountCode);

        //getログインチャネル
        String l_strLoginChannel = this.getLoginChannel();

        try
        {
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strInitializeAppliDiv))
            {
                //1.10 「申込履歴管理テーブル」登録処理
                SrvRegiHistoryParams l_srvRegiHistoryParams = new SrvRegiHistoryParams();

                OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                AccountManager l_accountManager = l_finApp.getAccountManager();

                //証券会社コード
                String l_strInstitutionId = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.INSTITUTION_ID);
                String l_strInstitutionCodeSession =
                    l_accountManager.getInstitution(Long.parseLong(l_strInstitutionId)).getInstitutionCode();
                l_srvRegiHistoryParams.setInstitutionCode(l_strInstitutionCodeSession);

                //部店コード
                String l_strBranchId = l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.BRANCH_ID);
                String l_strBranchCodeSession = l_accountManager.getBranch(Long.parseLong(l_strBranchId)).getBranchCode(); //NotFoundException
                l_srvRegiHistoryParams.setBranchCode(l_strBranchCodeSession);

                //サービス区分
                l_srvRegiHistoryParams.setSrvDiv(l_strSrvDiv);

                //口座コード
                l_srvRegiHistoryParams.setAccountCode(l_strAccountCode);

                //申込日
                Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();
                l_srvRegiHistoryParams.setRegistDate(l_tsSystemTimestamp);

                //申込経路区分
                //U00793
                if (WEB3ChannelDef.CALL_CENTER.equals(l_strLoginChannel))
                {
                    l_srvRegiHistoryParams.setOrderRootDiv(WEB3OrderRootDivDef.CALLCENTER);
                }
                else
                {
                    l_srvRegiHistoryParams.setOrderRootDiv(WEB3OrderRootDivDef.PC);
                }

                //更新者コード
                if (l_trader != null)
                {
                    l_srvRegiHistoryParams.setLastUpdater(l_strTraderCode);
                }
                else
                {
                    l_srvRegiHistoryParams.setLastUpdater(l_strAccountCode);
                }

                l_srvRegiHistoryParams.setCreatedTimestamp(l_tsSystemTimestamp);
                l_srvRegiHistoryParams.setLastUpdatedTimestamp(l_tsSystemTimestamp);

                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();

                l_queryProcesser.doInsertQuery(l_srvRegiHistoryParams);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME
            );
        }
        

        log.exiting(STR_METHOD_NAME);
        return l_gentradeSrvRegiApplicationNew;
    }

    /**
     * 入力した日付に指定した月数をプラスし、返却します。
     *
     * @@param l_dat      日付
     * @@param l_intMonth 月数
     * @@return 計算後の結果を返却する。
     */
    private Date addMonth(Date l_dat, int l_intMonth)
    {
        String STR_METHOD_NAME = " addMonth(Date, int)";
        log.entering(STR_METHOD_NAME);

        if (l_dat == null)
        {
            return null;
        }

        Calendar l_cal = new GregorianCalendar();
        l_cal.setTime(l_dat);

        l_cal.add(Calendar.MONTH, l_intMonth);

        log.exiting(STR_METHOD_NAME);
        return l_cal.getTime();
    }

    /**
     * 入力した日付に指定した年数をプラスし、返却します。
     *
     * @@param l_dat      日付
     * @@param l_intYear 年数
     * @@return 計算後の結果を返却する。
     */
    private Date addYear(Date l_dat, int l_intYear)
    {
        String STR_METHOD_NAME = " addYear(Date, int)";
        log.entering(STR_METHOD_NAME);
        if (l_dat == null)
        {
            return null;
        }

        Calendar l_cal = new GregorianCalendar();
        l_cal.setTime(l_dat);

        l_cal.add(Calendar.YEAR, l_intYear);

        log.exiting(STR_METHOD_NAME);
        return l_cal.getTime();
    }
    
    /**
     * (update顧客サービス申込属性)<BR>
     * サービス申込属性テーブルのUPDATEを行う。<BR>
     * <BR>
     * １） サービス申込属性テーブルのUPDATE<BR>
     * 　@１）　@Object配列を生成し、以下を要素に設定 <BR>
     *　@　@　@　@　@Object[0]（引数）証券会社コード  <BR>
     *　@　@　@　@　@Object[1]（引数）部店コード  <BR>
     *　@　@　@　@　@Object[2]（引数）口座コード <BR>
     *　@　@　@　@　@Object[3]（引数）サービス区分<BR>
     * <BR>
     * 　@２）サービス申込属性テーブルのUPDATEを行う。<BR>
     *　@　@　@　@QueryProcessor.doUpdateQuery()メソッドをコールする。<BR>
     * <BR>
     *　@　@　@ [doUpdateQuery()にセットするパラメータ] <BR>
     *　@　@　@　@arg0：  サービス申込属性テーブルRowType <BR>
     *　@　@　@　@arg1：  "institution_code=?  <BR>
     *　@　@　@　@　@　@　@　@and branch_code=?  <BR>
     *　@　@　@　@　@　@　@　@and account_code=?  <BR>
     *　@　@　@　@　@　@　@　@and srv_div=?  <BR>
     *　@　@　@　@　@　@　@　@and (proc_div = '0' or proc_div is null)"  <BR>
     *　@　@　@　@arg2：  １）で作成したObject配列 <BR>
     * <BR>
     * ３） ２）の戻り値 > 0 の場合、２）の戻り値を返却する。<BR>
     * <BR>
     * ４） ２）の戻り値 = 0 の場合、nullを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMainAccountCode - (顧客コード)<BR>
     * 顧客コード<BR>
     * @@param l_strServiceDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@return Integer
     * @@throws WEB3BaseException
     */
    private Integer updateSrvApplyAttribute(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMainAccountCode,
        String l_strServiceDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSrvApplyAttribute(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@Object配列を生成し、以下を要素に設定
        Object[] l_bindVars = new Object[4];

        // Object[0]（引数）証券会社コード
        l_bindVars[0] = l_strInstitutionCode;

        // Object[1]（引数）部店コード
        l_bindVars[1] = l_strBranchCode;

        // Object[2]（引数）口座コード
        l_bindVars[2] = l_strMainAccountCode;

        // Object[3]（引数）サービス区分
        l_bindVars[3] = l_strServiceDiv;

        //２）サービス申込属性テーブルのUPDATEを行う

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and account_code = ? ");
        l_sbWhere.append(" and srv_div = ? ");
        l_sbWhere.append(" and (proc_div = '0' or proc_div is null)");

        Map l_mapUpdateChanes = new HashMap();

        // 更新者コードを更新する

        Trader l_trader = this.getTrader();

        // コールセンターからの入力の場合
        if (l_trader != null)
        {
            l_mapUpdateChanes.put("last_updater", l_trader.getTraderCode());
        }

        // 顧客入力の場合
        else
        {
            l_mapUpdateChanes.put("last_updater", l_strMainAccountCode.substring(0, 6));
        }
        // 更新日付を更新する
        l_mapUpdateChanes.put("last_updated_timestamp", GtlUtils.getTradingSystem().getSystemTimestamp());

        // 処理区分を更新する
        l_mapUpdateChanes.put("proc_div", WEB3SrvAppliAttributeProcDivDef.PROCESSED);

        int l_intCount = 0;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_intCount = l_queryProcessor.doUpdateAllQuery(
                SrvAppliAttributeRow.TYPE,
                l_sbWhere.toString(),
                l_bindVars,
                l_mapUpdateChanes);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_intCount == 0)
        {
            return null;
        }
        return new Integer(l_intCount);
    }
}

@
