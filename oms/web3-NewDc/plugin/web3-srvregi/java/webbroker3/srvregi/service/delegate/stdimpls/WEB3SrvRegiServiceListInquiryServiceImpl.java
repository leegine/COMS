head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.49.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceListInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用サービス一覧照会サービスImpl(WEB3SrvRegiServiceListInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 李頴淵 新規作成
Revesion History : 2007/06/05 孫洪江(中訊) 仕様変更モデルNo.241
Revesion History : 2007/06/08 金傑(中訊) 仕様変更モデルNo.261
Revesion History : 2007/11/01 金傑(中訊) 仕様変更モデルNo.304
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AppliAttributeDef;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.define.WEB3SupplyDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceLotInfo;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUsePeriodAmt;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiChargeParams;
import webbroker3.srvregi.define.WEB3SrvRegiFreeAttributeApplyDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiChargeInfo;
import webbroker3.srvregi.message.WEB3SrvRegiLotteryGroup;
import webbroker3.srvregi.message.WEB3SrvRegiNoLotteryGroup;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceRequest;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceListInquiryService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

/**
 * (サービス利用サービス一覧照会サービスImpl)<BR>
 * サービス利用サービス一覧照会サービス実装クラス<BR>
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiServiceListInquiryServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiServiceListInquiryService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceListInquiryServiceImpl.class);

    /**
     * @@roseuid 416F39270167
     */
    public WEB3SrvRegiServiceListInquiryServiceImpl()
    {

    }

    /**
     * サービス利用サービス一覧照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）利用サービス一覧」参照<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F7831B0234
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3SrvRegiReferenceRequest l_srvRegiReferenceRequest = (WEB3SrvRegiReferenceRequest)l_request;

		//本番障害対応
        //1.1 getCommonOrderValidator - 削除 -

        //1.2  get補助口座
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

		//本番障害対応
        //1.3 validate取引可能顧客 - 削除 -

        //1.4  validate注文受付可能
        log.debug("validate注文受付可能");
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

        //1.5  getサービスマスター一覧
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster[] l_srvMasterList =
            l_srvRegiServiceInfoManagement.getSrvMasterList(l_strInstitutionCode, WEB3SrvRegiOfferingDivDef.REQUIRE);

        List l_lisSrvRegiNoLotteryGroup = new ArrayList();
        List l_lisSrvRegiLotteryGroup = new ArrayList();

        //1.6 <繰り返し処理 *1>
        int l_intLength = l_srvMasterList.length;
        for (int i = 0; i < l_intLength; i++)
        {
            log.debug("is顧客申込可能");
            //1.6.1  is顧客申込可能
            boolean l_blnAccountAppliPossible =
                l_srvRegiServiceInfoManagement.isAccountAppliPossible((WEB3GentradeSubAccount)l_subAccount, l_srvMasterList[i]);

            //1.6.2 is提供中
            boolean l_blnProviding = l_srvMasterList[i].isProviding();

            //1.6.3  <分岐処理 *1>
            if (l_blnProviding)
            {
                log.debug("l_blnProviding");
                //1.6.3.1 get申込要サービス
                WEB3SrvRegiApplicationRequiredService l_srvRegiApplicationRequiredService =
                    l_srvMasterList[i].getAppliRequiredSrv(false);
                if (l_srvRegiApplicationRequiredService == null)
                {
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + STR_METHOD_NAME);
                }

                //1.6.3.1.1 get抽選設定
                String l_strLotDiv = l_srvRegiApplicationRequiredService.getLotDiv();

                //1.6.3.2 <分岐処理 *2>
                WEB3SrvRegiRegistService l_srvRegiRegistService =
                    (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);
                String l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
                String l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();

                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
                {
                    log.debug("get提供形式");
                    //1.6.3.2.1  get提供形式
                    String l_strProvidModel = l_srvRegiApplicationRequiredService.getSupplyDiv();
                    
                    //障害対応 NO_2147                   
					//1.6.3.2.2 getサービス申込登録
					WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
						l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode,
						l_srvMasterList[i].getSrvDiv(), l_strAccountCode, WEB3SrvRegiCancelDivDef.USUAL_DEFAULT,
						WEB3EffectiveDivDef.EFFECTIVE, false);

                    //getサービス申込属性情報(証券会社コード : String, 部店コード : String,
                    // 口座コード : String, サービス区分 : String, アップロード区分 :)
                    List l_lisAppliAttributeInfo = l_srvRegiServiceInfoManagement.getServiceAppliAttributeInfo(
                        l_srvMasterList[i].getInstitutionCode(),
                        l_strBranchCode,
                        l_strAccountCode,
                        l_srvMasterList[i].getSrvDiv(),
                        null);

                    boolean l_blnCommCond = false;

                    //1.6.3.2.3 <get提供形式( )!= nullの場合>
                    if (l_strProvidModel != null)
                    {
                        log.debug("is手数料条件");
                        //is手数料条件
                        l_blnCommCond =
                            l_srvRegiServiceInfoManagement.isCommCond((WEB3GentradeSubAccount)l_subAccount, l_srvMasterList[i]);
                        
                        //障害対応 NO_U01568
                        //障害対応 NO_2147
                        //提供形式＝0:(無料) && is手数料条件()の戻り値＝false の場合、又は、
                        //提供形式＝2:(無料) && getサービス申込属性情報()＝nullの場合
                        
                        if ((WEB3SupplyDivDef.FREE_SUPPLY.equals(l_strProvidModel) && !l_blnCommCond) || 
                            (WEB3SupplyDivDef.CHARGE_SUPPLY_UTUMIYA.equals(l_strProvidModel) && l_lisAppliAttributeInfo == null))
                        {
                        	continue;
                        }
                    }

                    //1.6.3.2.4 サービス利用抽選無サービス明細情報一覧行
                    WEB3SrvRegiNoLotteryGroup l_srvRegiNoLotteryGroup = new WEB3SrvRegiNoLotteryGroup();

                    //1.6.3.2.5 ＜共通項目セット＞
                    //○ID=サービスマスターオブジェクト.getサービス区分( )
                    l_srvRegiNoLotteryGroup.serviceDiv = l_srvMasterList[i].getSrvDiv();
                    //○サービス名称=サービスマスターオブジェクト.getサービス名称( )
                    l_srvRegiNoLotteryGroup.serviceName = l_srvMasterList[i].getSrvName();
                    //○ステータス=サービスマスターオブジェクト.getステータス( )
                    l_srvRegiNoLotteryGroup.serviceStatus = l_srvMasterList[i].getStatus();
                    //○申込可能期間設定=(*1)
                    //(*1-1) 申込要サービスオブジェクト.get申込可能期間（自）( )、get申込可能期間（至）( )
                    //の戻り値が両方ともnullだった場合、"無"をセットする。
                    if (l_srvRegiApplicationRequiredService.getAppliDateFrom() == null &&
                        l_srvRegiApplicationRequiredService.getAppliDateTo() == null)
                    {
                        log.debug("無をセットする");
                        l_srvRegiNoLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT;
                    }
                    else
                    {
                        log.debug("有をセットする");
                        l_srvRegiNoLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE;
                    }

                    //○サービス説明URL=申込要サービスオブジェクト.getサービス説明URL( )
                    l_srvRegiNoLotteryGroup.explainURL = l_srvRegiApplicationRequiredService.getSrvExplanUrl();

                    // ○登録日=(*2)
                    //(*2-1) 取得したサービス申込登録オブジェクト=nullの場合、nullをセットする。
                    if (l_gentradeSrvRegiApplication == null)
                    {
                        log.debug("取得したサービス申込登録オブジェクト=nullの場合");
                        l_srvRegiNoLotteryGroup.registDate = null;
                        //(*2-1) 取得したサービス申込登録オブジェクト=nullの場合、nullをセットする
                        l_srvRegiNoLotteryGroup.useLimitDate = null;
                    }
                    else
                    {
                        log.debug("○登録日");
                        //サービス申込登録オブジェクト.get適用開始日( )をセットする。
                        l_srvRegiNoLotteryGroup.registDate = l_gentradeSrvRegiApplication.getAppliStartDate();
                        //サービス申込登録オブジェクト.get適用終了日( )をセットする
                        l_srvRegiNoLotteryGroup.useLimitDate = l_gentradeSrvRegiApplication.getAppliEndDate();
                    }

                    //○電子鳩設定区分=取得した申込要サービスオブジェクト.is電子鳩条件設定( )
                    l_srvRegiNoLotteryGroup.elePigeonDiv = l_srvRegiApplicationRequiredService.isElectricIssue();

                    //○継続申込可能区分=(*4)              
					//障害対応　@NO_U01563
					//(*4-1)提供形式＝無料提供の場合は、false をセットする。 
					if(WEB3SupplyDivDef.FREE_SUPPLY.equals(l_strProvidModel)){						
						l_srvRegiNoLotteryGroup.continuationDiv = false;
					}
					else {
						
						Timestamp l_tsAppliEndDate = null;
						Calendar l_cal = new GregorianCalendar();
						Date l_datAppliEndDateBefore = null;
						if (l_gentradeSrvRegiApplication != null)
						{
							l_tsAppliEndDate = l_gentradeSrvRegiApplication.getAppliEndDate();
							l_cal.setTime(l_tsAppliEndDate);
							l_cal.add(Calendar.MONTH, -1);
							l_datAppliEndDateBefore = l_cal.getTime();
						}
						Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
						
						//障害対応 NO_U01563
						//障害対応 NO_U02061
						//(*4-2) is顧客申込可能()=TRUE &&
						//「取得したサービス申込登録オブジェクト.get申込抽選区分( ) ≠ "試用申込"」 && 
						//取得したサービス申込登録オブジェクト.get適用終了日( )の一ヶ月前の日付 ≦ 
						//現在日付(*) ≦ 取得したサービス申込登録オブジェクト.get適用終了日( )」
						//の場合、trueをセットする。
	                    if (l_gentradeSrvRegiApplication != null && l_blnAccountAppliPossible &&
	                        !WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()) &&
	                        WEB3DateUtility.compareToDay(l_datAppliEndDateBefore, l_tsSystemTimestamp) <= 0 &&
	                        WEB3DateUtility.compareToDay(l_tsSystemTimestamp, l_tsAppliEndDate) <= 0)
	                    {
							log.debug("l_srvRegiNoLotteryGroup.continuationDiv = true;");
							l_srvRegiNoLotteryGroup.continuationDiv = true;
	                    }
						//(*4-3) 上記以外の場合、falseをセットする。
	                    else
	                    {
	                        log.debug("l_srvRegiNoLotteryGroup.continuationDiv = false;");
	                        l_srvRegiNoLotteryGroup.continuationDiv = false;
	                    }
					}
					
					//障害対応  NO_U01724
                    //○サービス利用可能区分=(*5)
                    //(*5-1) 取得したサービス申込登録オブジェクト=null または、
					//(*5-2) サービスマスターオブジェクト.getサービス利用URL=nullの場合、falseをセットする。
                    if (l_gentradeSrvRegiApplication == null || l_srvMasterList[i].getSrvUrl() == null)
                    {
                        log.debug("l_gentradeSrvRegiApplication == null");
                        l_srvRegiNoLotteryGroup.useAbleDiv = false;
                    }
                    else
                    {
                        log.debug("サービス利用可能区分");
                        l_srvRegiNoLotteryGroup.useAbleDiv = l_srvRegiRegistService.isUsePossible(
                            l_gentradeSrvRegiApplication.getInstitutionCode(),
                            l_gentradeSrvRegiApplication.getBranchCode(),
                            l_gentradeSrvRegiApplication.getSrvDiv(),
                            l_gentradeSrvRegiApplication.getAccountCode(),
                            l_gentradeSrvRegiApplication.getRegistId());
                    }

                    //1.6.3.2.6 ＜通常提供の場合＞
                    // is手数料条件()=falseの場合
                    if (!l_blnCommCond)
                    {

                        //1.6.3.2.6.1 getサービス利用期間料金一覧
                        WEB3SrvRegiServiceUsePeriodAmt[] l_srvUseTermAmtList = l_srvMasterList[i].getSrvUseTermAmtList();

                        SrvAppliAttributeRow l_srvAppliAttributeRow = null;
                        if (l_lisAppliAttributeInfo != null)
                        {
                            l_srvAppliAttributeRow = (SrvAppliAttributeRow) l_lisAppliAttributeInfo.get(0);
                        }

                        // getサービス申込属性情報オブジェクト.申込属性区分 = '無料対象' の場合
                        String l_strFreeTargetPeriod = null;
                        if (l_srvAppliAttributeRow != null)
                        {
                            if ((WEB3AppliAttributeDef.FREE_OBJECT).equals(l_srvAppliAttributeRow.getAppliAttribute()))
                            {
                                // get無料対象期間( )
                                l_strFreeTargetPeriod = l_srvRegiApplicationRequiredService.getFreeTargetPeriod();
                            }
                        }
                        
                        //1.6.3.2.6.2 <プロパティ・セット *1-1>

                        //○利用期間料金情報=(*1)
                        //(*1-1) サービスマスターオブジェクト.サービス利用期間料金一覧( )をコールし、
                        //戻り値の件数分以下を繰り返し、サービス利用期間料金情報の配列を作成する。
                        int l_intListLength = l_srvUseTermAmtList.length;
                        WEB3SrvRegiChargeInfo[] l_srvRegiChargeInfo = new WEB3SrvRegiChargeInfo[l_intListLength];
                        for (int j = 0; j < l_intListLength; j++)
                        {
                            log.debug("サービス利用期間料金情報の配列を作成する");
                            l_srvRegiChargeInfo[j] = new WEB3SrvRegiChargeInfo();
                            //(*1-2) 申込要サービス利用期間料金情報クラスを生成し、以下のプロパティをセットする。
                            SrvRegiChargeParams l_srvUsePeriodAmtParams = (SrvRegiChargeParams)l_srvUseTermAmtList[j].getDataSourceObject();
                            //○利用期間ID=サービス利用期間料金Params.get通番( )
                            l_srvRegiChargeInfo[j].chargeId = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getConsecutiveNumbers());
                            //○利用期間単位区分=サービス利用期間料金Params.get利用期間区分( )
                            l_srvRegiChargeInfo[j].chargeDiv = l_srvUsePeriodAmtParams.getSrvUsePeriodDiv();
                            //○利用期間=サービス利用期間料金Params.get利用期間( )
                            l_srvRegiChargeInfo[j].chargePeriod = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getSrvUsePeriod());
                            //○利用料金=サービス利用期間料金Params.get利用料金( )
                            l_srvRegiChargeInfo[j].chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getUseAmt());
                            //○無効区分="有効"
                            l_srvRegiChargeInfo[j].invalidDiv = false;
                        }
                        l_srvRegiNoLotteryGroup.chargeInfo = l_srvRegiChargeInfo;

                        //○試用期間単位・試用期間=(*2)
                        //(*2-1) サービス情報管理.is試用申込可能( )の戻り値がtrueだった場合、
                        if (l_srvRegiServiceInfoManagement.isTrialAppliPossible(l_strInstitutionCode, l_strBranchCode,
                            l_srvMasterList[i].getSrvDiv(), l_strAccountCode))
                        {
                            log.debug("戻り値がtrueだった場合");
                            //○試用期間単位=申込要サービスオブジェクト.get試用期間区分( )
                            l_srvRegiNoLotteryGroup.trialDiv = l_srvRegiApplicationRequiredService.getTrialPeriodDiv();
                            //○試用期間=申込要サービスオブジェクト.get試用期間( )
                            if (l_srvRegiApplicationRequiredService.getTrialPeriod() == null)
                            {
                                log.debug("l_srvRegiNoLotteryGroup.trialPeriod = null;");
                                l_srvRegiNoLotteryGroup.trialPeriod = null;
                            }
                            else
                            {
                                log.debug("試用期間");
                                l_srvRegiNoLotteryGroup.trialPeriod = l_srvRegiApplicationRequiredService.getTrialPeriod().toString();
                            }
                        }
                        else
                        {
                            log.debug("試用期間単位・試用期間");
                            //○試用期間単位=null
                            l_srvRegiNoLotteryGroup.trialDiv = null;
                            //○試用期間=null
                            l_srvRegiNoLotteryGroup.trialPeriod = null;
                        }

                        //○申込可能区分=(*3)
                        //(*3-1) サービス情報管理.is顧客申込可能( )の戻り値がfalseだった場合、
                        if (!l_blnAccountAppliPossible)
                        {
                            log.debug("is顧客申込可能( )の戻り値がfalseだった場合");
                            l_srvRegiNoLotteryGroup.applyAbleDiv = false;
                        }
                        //(*3-2) 取得したサービス申込登録オブジェクト!=nullであり、かつ
                        //サービス申込登録オブジェクト.get申込抽選区分( )の戻り値が"試用申込"以外の場合、
                        else if (l_gentradeSrvRegiApplication != null &&
                            !WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()))
                        {
                            log.debug("試用申込以外の場合");
                            l_srvRegiNoLotteryGroup.applyAbleDiv = false;
                        }
                        else
                        {
                            log.debug("else");
                            l_srvRegiNoLotteryGroup.applyAbleDiv = true;
                        }
                        // ○無料申込区分
                        l_srvRegiNoLotteryGroup.noChargeAbleDiv = false;

                        //申込属性区分、申込属性期間From、申込属性期間To、無料属性申込区分=(*4)
                        //(サービス情報管理.is顧客申込可能( )=true)
                        //且つ
                        //(*4-1)(getサービス申込属性情報オブジェクト.申込属性区分 = '無料対象'
                        //又は
                        //getサービス申込属性情報オブジェクト.申込属性区分 = '申込不可' の場合、以下をセットする.
                        if (l_srvAppliAttributeRow != null)
                        {
                            if ((l_blnAccountAppliPossible)
								&& (WEB3AppliAttributeDef.FREE_OBJECT).equals(l_srvAppliAttributeRow.getAppliAttribute())
                                || ((WEB3AppliAttributeDef.CANNOT_APPLI).equals(
                                    l_srvAppliAttributeRow.getAppliAttribute())))
                            {
                            	
                            	//(*4-1-1) 
                            	//取得したサービス申込登録オブジェクト != null
                            	//且つ
                            	//取得したサービス申込登録オブジェクト.get申込抽選区分( ) == "試用申込"
                            	//且つ
                            	//getサービス申込属性情報オブジェクト.申込属性区分 = '無料対象'
                            	//且つ
                            	//取得したサービス申込登録オブジェクト.get適用終了日が1ヶ月より先
                            	//*4-1-1の条件を満たしていない場合は、以下の処理を行う。
                            	Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();

                        		Calendar l_cal = new GregorianCalendar();
                        		Date l_datSystemTimestampMonthAfter = null;
								l_cal.setTime(l_tsSystemTimestamp);                  		
								l_cal.add(Calendar.MONTH, 1);
								l_datSystemTimestampMonthAfter = l_cal.getTime();

                            	if (!(l_gentradeSrvRegiApplication != null &&
                            		WEB3AppliLotDivDef.TRIAL_APPLI.equals(l_gentradeSrvRegiApplication.getAppliLotDiv()) &&
                            		(WEB3AppliAttributeDef.FREE_OBJECT).equals(l_srvAppliAttributeRow.getAppliAttribute()) &&
                            		WEB3DateUtility.compareToDay(l_gentradeSrvRegiApplication.getAppliEndDate(), l_datSystemTimestampMonthAfter) > 0))
                            	{
                            		//(*4-1-2)
                                    // 申込属性区分
                                    // getサービス申込属性情報().申込属性区分をセットする。
                                    l_srvRegiNoLotteryGroup.applyAttributeDiv =
                                        l_srvAppliAttributeRow.getAppliAttribute();

                                    // 申込属性期間From
                                    // getサービス申込属性情報().適用期間Fromをセットする。
                                    l_srvRegiNoLotteryGroup.applyAttributePeriodFrom =
                                        l_srvAppliAttributeRow.getAppliStartDate();

                                    // 申込属性期間To
                                    // getサービス申込属性情報().適用期間Toをセットする。
                                    l_srvRegiNoLotteryGroup.applyAttributePeriodTo =
                                        l_srvAppliAttributeRow.getAppliEndDate();
                                    
                                    //(*4-1-3)
                                    //(サービス情報管理.is顧客申込可能( )=true)
                                    //且つ
                                    //getサービス申込属性情報オブジェクト.申込属性区分 = '無料対象'
                                    //且つ
                                    //get無料対象期間()の戻り値 != null の場合、以下をセットする。
                                    if ((l_blnAccountAppliPossible)
                                    	&&	(WEB3AppliAttributeDef.FREE_OBJECT).equals(l_srvAppliAttributeRow.getAppliAttribute())
                                        && l_strFreeTargetPeriod != null)
                                    {
                                        //無料属性申込区分='1'
                                        l_srvRegiNoLotteryGroup.freeAttributeApplyDiv =
                                            WEB3SrvRegiFreeAttributeApplyDivDef.FREE_ATTRIBUTE_APPLY;
                                    }   
                            	}
                            }
                        }
                    }

                    //1.6.3.2.7 ＜無料提供の場合＞
                    // is手数料条件()=trueの場合
                    else if (l_blnCommCond)
                    {
                        //1.6.3.2.7.1 <プロパティ・セット *1-2>
                        
						//○試用期間単位・試用期間=null
						log.debug("試用期間単位・試用期間");
						//○試用期間単位=null
						l_srvRegiNoLotteryGroup.trialDiv = null;
						//○試用期間=null
						l_srvRegiNoLotteryGroup.trialPeriod = null;

						//○申込可能区分=false
						l_srvRegiNoLotteryGroup.applyAbleDiv = false;

                        //○無料申込区分
                        if(l_gentradeSrvRegiApplication == null)
                        {
                            l_srvRegiNoLotteryGroup.noChargeAbleDiv = true;
                        }
                        else
                        {
                            l_srvRegiNoLotteryGroup.noChargeAbleDiv = false;
                        }
                        
						//○利用期間料金情報
						if(WEB3SupplyDivDef.CHARGE_OR_FREE_SUPPLY.equals(l_strProvidModel) &&
							 l_gentradeSrvRegiApplication != null){
							
							//1.7.3.2.7.1 getサービス利用期間料金一覧
							WEB3SrvRegiServiceUsePeriodAmt[] l_srvUseTermAmtList = l_srvMasterList[i].getSrvUseTermAmtList();

							//1.7.3.2.7.2 <プロパティ・セット *1-1>

							//○利用期間料金情報=(*1)
							//(*1-1) サービスマスターオブジェクト.サービス利用期間料金一覧( )をコールし、
							//戻り値の件数分以下を繰り返し、サービス利用期間料金情報の配列を作成する。
							int l_intListLength = l_srvUseTermAmtList.length;
							WEB3SrvRegiChargeInfo[] l_srvRegiChargeInfo = new WEB3SrvRegiChargeInfo[l_intListLength];
							for (int j = 0; j < l_intListLength; j++)
							{
								log.debug("サービス利用期間料金情報の配列を作成する");
								l_srvRegiChargeInfo[j] = new WEB3SrvRegiChargeInfo();
								//(*1-2) 申込要サービス利用期間料金情報クラスを生成し、以下のプロパティをセットする。
								SrvRegiChargeParams l_srvUsePeriodAmtParams = (SrvRegiChargeParams)l_srvUseTermAmtList[j].getDataSourceObject();
								//○利用期間ID=サービス利用期間料金Params.get通番( )
								l_srvRegiChargeInfo[j].chargeId = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getConsecutiveNumbers());
								//○利用期間単位区分=サービス利用期間料金Params.get利用期間区分( )
								l_srvRegiChargeInfo[j].chargeDiv = l_srvUsePeriodAmtParams.getSrvUsePeriodDiv();
								//○利用期間=サービス利用期間料金Params.get利用期間( )
								l_srvRegiChargeInfo[j].chargePeriod = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getSrvUsePeriod());
								//○利用料金=サービス利用期間料金Params.get利用料金( )
								l_srvRegiChargeInfo[j].chargeAmt = WEB3StringTypeUtility.formatNumber(l_srvUsePeriodAmtParams.getUseAmt());
								//○無効区分="有効"
								l_srvRegiChargeInfo[j].invalidDiv = false;
							}
							l_srvRegiNoLotteryGroup.chargeInfo = l_srvRegiChargeInfo;
						}
						else
						{
							l_srvRegiNoLotteryGroup.chargeInfo = null;
						}
                    }
                    l_lisSrvRegiNoLotteryGroup.add(l_srvRegiNoLotteryGroup);
                }
                //1.6.3.3  <分岐処理 *3>
                else if(WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
                {
                    log.debug("<分岐処理 *3>");
                    //障害対応 NO_2049
                    //1.6.3.3.1 getサービス申込登録一覧
                    SrvRegiApplicationParams[] l_srvRegiApplicationParams =
                        l_srvRegiRegistService.getServiceRegistLists(l_strInstitutionCode, l_strBranchCode,
                        l_srvMasterList[i].getSrvDiv(), l_strAccountCode, true);

                    //1.6.3.3.2 getサービス抽選情報一覧
                    WEB3SrvRegiServiceLotInfo[] l_srvRegiServiceLotInfo = l_srvMasterList[i].getSrvLotInfoList();

                    int l_intInfoLength = l_srvRegiServiceLotInfo.length;
                    if (l_intInfoLength > 0)
                    {
                        //1.6.3.3.3 <マッチング処理>
                        //1.6.3.3.3.1 <プロパティ・セット *2>
                        //1) サービス利用抽選有サービス明細情報一覧行オブジェクトを生成する。
                        WEB3SrvRegiLotteryGroup l_srvRegiLotteryGroup = new WEB3SrvRegiLotteryGroup();

                        //2) サービス申込登録一覧とサービス抽選情報の全ての要素に対して、以下を実施する。
                        //2-1) サービス抽選情報一覧[n].get申込期間（自）( )、get申込期間（至）( )と
                        //サービス申込登録一覧[m].get申込日( )を比較。
                        boolean l_blnDiv = false;
                        for (int n = 0; n < l_intInfoLength; n++)
                        {
                            if (l_srvRegiApplicationParams != null)
                            {
                                log.debug("l_srvRegiApplicationParams != null");
                                int l_intParamsLength = l_srvRegiApplicationParams.length;
                                for (int m = 0; m < l_intParamsLength; m++)
                                {
                                    log.debug("l_intParamsLength");
                                    if (WEB3DateUtility.compareToSecond(l_srvRegiServiceLotInfo[n].getAppliDateFrom(),
                                        l_srvRegiApplicationParams[m].getAppliDate()) <= 0 &&
                                        WEB3DateUtility.compareToSecond(l_srvRegiApplicationParams[m].getAppliDate(),
                                        l_srvRegiServiceLotInfo[n].getAppliDateTo()) <= 0)
                                    {
                                        log.debug("l_blnDiv = true;");

                                        log.debug("<全パターン共通>");
                                        l_srvRegiLotteryGroup = new WEB3SrvRegiLotteryGroup();
                                        //*---<全パターン共通>---*
                                        //○ID=サービスマスターオブジェクト.getサービス区分( )
                                        l_srvRegiLotteryGroup.serviceDiv = l_srvMasterList[i].getSrvDiv();
                                        //○サービス名称=サービスマスターオブジェクト.getサービス名称( )
                                        l_srvRegiLotteryGroup.serviceName = l_srvMasterList[i].getSrvName();
                                        //○ステータス=サービスマスターオブジェクト.getステータス( )
                                        l_srvRegiLotteryGroup.serviceStatus = l_srvMasterList[i].getStatus();
                                        //○運用区分=サービス抽選情報一覧[n].get運用区分( )
                                        l_srvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo[n].getInvestDiv();
                                        //○申込可能期間設定=(*1)
                                        //(*1-1) 申込要サービスオブジェクト.get申込可能期間（自）( )、get申込可能期間（至）( )
                                        //の戻り値が両方ともnullだった場合、"無"をセットする。
                                        if (l_srvRegiApplicationRequiredService.getAppliDateFrom() == null &&
                                            l_srvRegiApplicationRequiredService.getAppliDateTo() == null)
                                        {
                                            log.debug("WEB3ConditionsValueDivDef.HAVE_NOT");
                                            l_srvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT;
                                        }
                                        else
                                        {
                                            log.debug("WEB3ConditionsValueDivDef.HAVE");
                                            l_srvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE;
                                        }

                                        //○サービス説明URL=申込要サービスオブジェクト.getサービス説明URL( )
                                        l_srvRegiLotteryGroup.explainURL = l_srvRegiApplicationRequiredService.getSrvExplanUrl();
                                        //○電子鳩設定区分=取得した申込要サービスオブジェクト.is電子鳩条件設定( )
                                        l_srvRegiLotteryGroup.elePigeonDiv = l_srvRegiApplicationRequiredService.isElectricIssue();

                                        l_blnDiv = true;
                                        //*---<パターン1>---*
                                        //○申込期間（自）=サービス抽選情報一覧[n].get申込期間（自）( )
                                        l_srvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo[n].getAppliDateFrom();
                                        //○申込期間（至）=サービス抽選情報一覧[n].get申込期間（至）( )
                                        l_srvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo[n].getAppliDateTo();
                                        //○抽選日=サービス抽選情報一覧[n].get抽選日( )
                                        l_srvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo[n].getLotDate();
                                        //○利用料金=(*2)
                                        //(*2-1) サービス申込登録一覧[m].get申込抽選区分( )が"申込"、
                                        //"当選／本申込"、"自動当選"のいずれかの場合,サービス申込登録一覧[m].get利用料金( )をセットする。
                                        if (WEB3AppliLotDivDef.APPLI.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()) ||
                                            WEB3AppliLotDivDef.ELECTION_FORMAL_APPLI.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()) ||
                                            WEB3AppliLotDivDef.AUTO_ELECTION.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()))
                                        {
                                            //○適用開始日=サービス申込登録一覧[m].get適用開始日( )
                                            l_srvRegiLotteryGroup.trialStartDate = l_srvRegiApplicationParams[m].getAppliStartDate();
                                            //○適用終了日=サービス申込登録一覧[m].get適用終了日( )
                                            l_srvRegiLotteryGroup.trialEndDate = l_srvRegiApplicationParams[m].getAppliEndDate();

                                            if (l_srvRegiApplicationParams[m].getUseAmtIsNull())
                                            {
                                                log.debug("l_srvRegiLotteryGroup.chargeAmt = null;");
                                                l_srvRegiLotteryGroup.chargeAmt = null;
                                            }
                                            else
                                            {
                                                log.debug("get利用料金");
                                                l_srvRegiLotteryGroup.chargeAmt =
                                                    WEB3StringTypeUtility.formatNumber(l_srvRegiApplicationParams[m].getUseAmt());
                                            }

                                        }
                                        //(*2-2) サービス申込登録一覧[m].get申込抽選区分( )="落選"の場合
                                        else if(WEB3AppliLotDivDef.DEFEAT.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()))
                                        {
                                            log.debug("落選");

                                            //○適用開始日=サービス抽選情報一覧[n].get適用開始日( )
                                            l_srvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo[n].getAppliStartDate();
                                            //○適用終了日=サービス抽選情報一覧[n].get適用終了日( )
                                            l_srvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo[n].getAppliEndDate();
                                            //利用料金=サービス抽選情報一覧[n].get利用料金( )をセットする。
                                            l_srvRegiLotteryGroup.chargeAmt =
                                                WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo[n].getUseAmt());
                                        }

                                        //○入札単位=サービス抽選情報一覧[n].get入札単位( )
                                        if (l_srvRegiServiceLotInfo[n].getBiddingPrice() == null)
                                        {
                                            log.debug("l_srvRegiLotteryGroup.biddingPriceUnit = null;");
                                            l_srvRegiLotteryGroup.biddingPriceUnit = null;
                                        }
                                        else
                                        {
                                            log.debug("○入札単位");
                                            l_srvRegiLotteryGroup.biddingPriceUnit =
                                                l_srvRegiServiceLotInfo[n].getBiddingPrice().toString();
                                        }

                                        //○申込日=(*3)
                                        //(*3-1) サービス申込登録一覧[m].get申込抽選区分( )="落選"の場合,nullをセットする。
                                        if (WEB3AppliLotDivDef.DEFEAT.equals(l_srvRegiApplicationParams[m].getAppliLotDiv()))
                                        {
                                            log.debug("l_srvRegiLotteryGroup.applyDate = null;");
                                            l_srvRegiLotteryGroup.applyDate = null;
                                        }
                                        //(*3-2) 上記以外の場合、サービス申込登録一覧[m].get申込日( )をセットする。
                                        else
                                        {
                                            log.debug("get申込日");
                                            l_srvRegiLotteryGroup.applyDate = l_srvRegiApplicationParams[m].getAppliDate();
                                        }
                                        //○申込可能区分=false
                                        l_srvRegiLotteryGroup.applyAbleDiv = false;
                                        //○取消可能区分=サービス利用申込登録サービス.is取消可能( )の戻り値
                                        l_srvRegiLotteryGroup.cancelAbleDiv =
                                            l_srvRegiRegistService.isCancelPossible(l_srvRegiApplicationParams[m].getInstitutionCode(),
                                            l_srvRegiApplicationParams[m].getBranchCode(),
                                            l_srvRegiApplicationParams[m].getSrvDiv(),
                                            l_srvRegiApplicationParams[m].getAccountCode(),
                                            l_srvRegiApplicationParams[m].getRegistId());
                                        //○サービス利用可能区分=サービス利用申込登録サービス.is利用可能( )の戻り値
                                        l_srvRegiLotteryGroup.useAbleDiv =
                                            l_srvRegiRegistService.isUsePossible(l_srvRegiApplicationParams[m].getInstitutionCode(),
                                            l_srvRegiApplicationParams[m].getBranchCode(),
                                            l_srvRegiApplicationParams[m].getSrvDiv(),
                                            l_srvRegiApplicationParams[m].getAccountCode(),
                                            l_srvRegiApplicationParams[m].getRegistId());

                                        l_lisSrvRegiLotteryGroup.add(l_srvRegiLotteryGroup);
                                    }
                                }
                            }


                            if (l_blnDiv)
                            {
                                log.debug("l_blnDiv = false;");
                                l_blnDiv = false;
                            }
                            else
                            {
                                log.debug("<全パターン共通>");
                                l_srvRegiLotteryGroup = new WEB3SrvRegiLotteryGroup();
                                //*---<全パターン共通>---*
                                //○ID=サービスマスターオブジェクト.getサービス区分( )
                                l_srvRegiLotteryGroup.serviceDiv = l_srvMasterList[i].getSrvDiv();
                                //○サービス名称=サービスマスターオブジェクト.getサービス名称( )
                                l_srvRegiLotteryGroup.serviceName = l_srvMasterList[i].getSrvName();
                                //○ステータス=サービスマスターオブジェクト.getステータス( )
                                l_srvRegiLotteryGroup.serviceStatus = l_srvMasterList[i].getStatus();
                                //○運用区分=サービス抽選情報一覧[n].get運用区分( )
                                l_srvRegiLotteryGroup.useDiv = l_srvRegiServiceLotInfo[n].getInvestDiv();
                                //○申込可能期間設定=(*1)
                                //(*1-1) 申込要サービスオブジェクト.get申込可能期間（自）( )、get申込可能期間（至）( )
                                //の戻り値が両方ともnullだった場合、"無"をセットする。
                                if (l_srvRegiApplicationRequiredService.getAppliDateFrom() == null &&
                                    l_srvRegiApplicationRequiredService.getAppliDateTo() == null)
                                {
                                    log.debug("WEB3ConditionsValueDivDef.HAVE_NOT");
                                    l_srvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE_NOT;
                                }
                                else
                                {
                                    log.debug("WEB3ConditionsValueDivDef.HAVE");
                                    l_srvRegiLotteryGroup.applyAbleSet = WEB3ConditionsValueDivDef.HAVE;
                                }

                                //○サービス説明URL=申込要サービスオブジェクト.getサービス説明URL( )
                                l_srvRegiLotteryGroup.explainURL = l_srvRegiApplicationRequiredService.getSrvExplanUrl();
                                //○電子鳩設定区分=取得した申込要サービスオブジェクト.is電子鳩条件設定( )
                                l_srvRegiLotteryGroup.elePigeonDiv = l_srvRegiApplicationRequiredService.isElectricIssue();

                                log.debug("<パターン2>");
                                // *---<パターン2>---*
                                //○申込期間（自）=サービス抽選情報一覧[n].get申込期間（自）( )
                                l_srvRegiLotteryGroup.applyStartDate = l_srvRegiServiceLotInfo[n].getAppliDateFrom();
                                //○申込期間（至）=サービス抽選情報一覧[n].get申込期間（至）( )
                                l_srvRegiLotteryGroup.applyEndDate = l_srvRegiServiceLotInfo[n].getAppliDateTo();
                                //○抽選日=サービス抽選情報一覧[n].get抽選日( )
                                l_srvRegiLotteryGroup.lotteryDate = l_srvRegiServiceLotInfo[n].getLotDate();
                                //○適用開始日=サービス抽選情報一覧[n].get適用開始日( )
                                l_srvRegiLotteryGroup.trialStartDate = l_srvRegiServiceLotInfo[n].getAppliStartDate();
                                //○適用終了日=サービス抽選情報一覧[n].get適用終了日( )
                                l_srvRegiLotteryGroup.trialEndDate = l_srvRegiServiceLotInfo[n].getAppliEndDate();
                                //○利用料金=サービス抽選情報一覧[n].get利用料金( )
                                l_srvRegiLotteryGroup.chargeAmt =
                                    WEB3StringTypeUtility.formatNumber(l_srvRegiServiceLotInfo[n].getUseAmt());
                                //○入札単位=サービス抽選情報一覧[n].get入札単位( )
                                if (l_srvRegiServiceLotInfo[n].getBiddingPrice() == null)
                                {
                                    log.debug("l_srvRegiLotteryGroup.biddingPriceUnit = null;");
                                    l_srvRegiLotteryGroup.biddingPriceUnit = null;
                                }
                                else
                                {
                                    log.debug("○入札単位");
                                    l_srvRegiLotteryGroup.biddingPriceUnit = l_srvRegiServiceLotInfo[n].getBiddingPrice().toString();
                                }

                                //○申込日=null
                                l_srvRegiLotteryGroup.applyDate = null;
                                //○申込可能区分=・サービス抽選情報一覧[n].get申込期間（自）( )
                                //≦現在日付≦サービス抽選情報一覧[n].get申込期間（至）( )の場合、
                                //サービス情報管理.is顧客申込可能( )の戻り値をセット。
                                Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
                                Timestamp l_tsAppliDateFrom = l_srvRegiServiceLotInfo[n].getAppliDateFrom();
                                Timestamp l_tsAppliDateTo = l_srvRegiServiceLotInfo[n].getAppliDateTo();

                                if (WEB3DateUtility.compareToSecond(l_tsAppliDateFrom, l_tsSystemTimestamp) <= 0 &&
                                    WEB3DateUtility.compareToSecond(l_tsSystemTimestamp, l_tsAppliDateTo) <= 0)
                                {
                                    l_srvRegiLotteryGroup.applyAbleDiv = l_blnAccountAppliPossible;
                                }
                                //上記以外の場合、falseをセット
                                else
                                {
                                    l_srvRegiLotteryGroup.applyAbleDiv = false;
                                }
                                //○取消可能区分=false
                                l_srvRegiLotteryGroup.cancelAbleDiv = false;
                                //○サービス利用可能区分=false
                                l_srvRegiLotteryGroup.useAbleDiv = false;

                                l_lisSrvRegiLotteryGroup.add(l_srvRegiLotteryGroup);
                            }
                        }
                    }
                }
            }

        }
        //1.7 createレスポンス
        WEB3SrvRegiReferenceResponse l_srvRegiReferenceResponse = (WEB3SrvRegiReferenceResponse)l_srvRegiReferenceRequest.createResponse();

        WEB3SrvRegiNoLotteryGroup[] l_srvRegiNoLotteryGroup = new WEB3SrvRegiNoLotteryGroup[l_lisSrvRegiNoLotteryGroup.size()];
        WEB3SrvRegiLotteryGroup[] l_srvRegiLotteryGroup = new WEB3SrvRegiLotteryGroup[l_lisSrvRegiLotteryGroup.size()];
        l_lisSrvRegiNoLotteryGroup.toArray(l_srvRegiNoLotteryGroup);
        l_lisSrvRegiLotteryGroup.toArray(l_srvRegiLotteryGroup);

        l_srvRegiReferenceResponse.noLotList = l_srvRegiNoLotteryGroup;
        l_srvRegiReferenceResponse.lotList = l_srvRegiLotteryGroup;

        log.exiting(STR_METHOD_NAME);
        return l_srvRegiReferenceResponse;
    }
}@
