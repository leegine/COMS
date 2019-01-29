head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceStartServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用サービス起動サービスImpl(WEB3SrvRegiServiceStartServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/26 李頴淵 新規作成
Revesion History : 2005/10/05 鈴木　@美由紀(SRA) トランスリンク対応
Revesion History : 2008/02/18 金シュ 仕様変更モデルNo.312
Revesion History : 2008/02/29 金シュ 仕様変更モデルNo.329
Revesion History : 2008/03/04 武波 仕様変更モデルNo.341
Revesion History : 2008/03/06 武波 (中訊) モデル344
Revesion History : 2008/05/20 車進 (中訊) モデル369
Revision History : 2008/07/18 馮海濤 (中訊) モデルNo.399
Revision History : 2008/08/11 馮海濤 (中訊) モデルNo.402
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.util.Date;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3EffectiveDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3SpecialProcessDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSrvRegiApplication;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiOtherOrgInfoAdmin;
import webbroker3.srvregi.WEB3SrvRegiReservedWordChange;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.WEB3SrvRegiServiceUseKey;
import webbroker3.srvregi.WEB3SrvRegiTradingTimeManagement;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.define.WEB3SrvRegiMobileFlagDef;
import webbroker3.srvregi.define.WEB3SrvRegiSrvRegiExecErrDivDef;
import webbroker3.srvregi.message.WEB3SrvRegiExecRequest;
import webbroker3.srvregi.message.WEB3SrvRegiExecResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceStartService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用サービス起動サービスImpl)<BR>
 * サービス利用サービス起動サービス実装クラス<BR>
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiServiceStartServiceImpl extends WEB3SrvRegiClientRequestService implements WEB3SrvRegiServiceStartService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceStartServiceImpl.class);

    /**
     * @@roseuid 416F3927009C
     */
    public WEB3SrvRegiServiceStartServiceImpl()
    {

    }

    /**
     * サービス利用サービス起動処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用）サービス起動」参照<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）サービス起動」): <BR>
     *         1.6.1is利用可能( )<BR>
     *          利用可能チェック<BR>
     *          is利用可能( )==falseの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01011<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）サービス起動」): <BR>
     * 　@　@　@　@　@1.7.3is外国証券口座開設( ) == false の場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01341<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）サービス起動」): <BR>
     * 　@　@　@　@　@1.7.4 is法@人( ) == true の場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02884<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）サービス起動」): <BR>
     * 　@　@　@　@　@1.7.5 顧客.get顧客行 ().居住／非居住区分 != 0:住居者の場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02708<BR>
     * ==========================================================<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(「（サービス利用）サービス起動」): <BR>
     * 　@　@　@　@　@1.9.2get外部連携情報()の戻り値が null の場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F784770169
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

        WEB3SrvRegiExecRequest l_srvRegiExecRequest = (WEB3SrvRegiExecRequest)l_request;

        //1.1 validate
        l_srvRegiExecRequest.validate();

        //1.2 validate注文受付可能
        WEB3SrvRegiTradingTimeManagement.validateOrderAccept();

		String l_strInstitutionCode = null; 
		String l_strBranchCode = null;
		String l_strAccountCode = null;
		SubAccount l_subAccount = null;
		
		Trader l_trader = null;
		String l_strLoginChannel = null;

		//オリックス.口座開設よりサービス起動の場合
		if(l_srvRegiExecRequest.institutionCode != null || l_srvRegiExecRequest.branchCode != null)
		{
			l_strInstitutionCode = l_srvRegiExecRequest.institutionCode;
			l_strBranchCode = l_srvRegiExecRequest.branchCode;
		}
		//通常のサービス起動
		else
		{
			//1.3 getCommonOrderValidator
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
			WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

			//1.4 get補助口座
			l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

			l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
			l_strBranchCode = l_subAccount.getMainAccount().getBranch().getBranchCode();
			l_strAccountCode = l_subAccount.getMainAccount().getAccountCode();
			
			//get代理入力者
			l_trader = this.getTrader();

			//getログインチャネル
			l_strLoginChannel = this.getLoginChannel();
		}

        //1.5 getサービスマスター
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster l_srvRegiServiceMaster = l_srvRegiServiceInfoManagement.getSrvMaster(l_strInstitutionCode, l_srvRegiExecRequest.serviceDiv, false);

        // is提供中
        if (!l_srvRegiServiceMaster.isProviding())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01927,
                getClass().getName() + STR_METHOD_NAME);
        }

        SrvRegiMasterParams l_srvRegiMasterParams =
            (SrvRegiMasterParams)l_srvRegiServiceMaster.getDataSourceObject();
        String l_strSpecialProcessDiv = l_srvRegiMasterParams.getSpecialProcessDiv();
        //getサービスマスター.特殊処理区分 = 2 （債券連携の場合） && 補助口座 != nullの場合
        if (WEB3SpecialProcessDivDef.STREAM.equals(l_strSpecialProcessDiv) &&
            l_subAccount != null)
        {
        	FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

            if(l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引停止顧客エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引停止顧客エラー");
            }

            //getMainAccount
            WEB3GentradeMainAccount l_genMainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            // 顧客.get顧客行 ().居住／非居住区分 != 0:住居者の場合、例外をスロー
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_genMainAccount.getDataSourceObject();
            if (!WEB3ResidentDef.RESIDENT.equals(l_mainAccountRow.getResident()))
            {
                log.debug("非居住者は申込むことができません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02708,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "非居住者は申込むことができません。");
             }

            //get代理入力者（）の戻り値 = null
            if (this.getTrader() == null)
            {
                if (!l_genMainAccount.isForeignAccountOpen())
                {
                    log.debug("当該顧客は外国証券口座開設なし");
                    throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "当該顧客は外国証券口座開設なし。");
                }

                //is法@人( )
                if (l_genMainAccount.isCorporation())
                {
                    log.debug("法@人顧客エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02884,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "法@人顧客エラー。");
                }
            }

        }

        //1.6 is申込要
        boolean l_blnIsAppliRequired = l_srvRegiServiceMaster.isAppliRequired();

        if (l_blnIsAppliRequired && l_srvRegiExecRequest.applyCheckDiv)
        {
            log.debug("is申込要()=trueの場合 && リクエストデータ.申込チェック区分=trueの場合");

            //1.7.1 getサービス申込登録
            WEB3SrvRegiRegistService l_srvRegiRegistService =
                (WEB3SrvRegiRegistService)Services.getService(WEB3SrvRegiRegistService.class);

            WEB3GentradeSrvRegiApplication l_gentradeSrvRegiApplication =
                l_srvRegiRegistService.getServiceRegist(l_strInstitutionCode, l_strBranchCode, l_srvRegiExecRequest.serviceDiv,
                    l_strAccountCode, WEB3SrvRegiCancelDivDef.USUAL_DEFAULT, WEB3EffectiveDivDef.EFFECTIVE, false);

            //getサービス申込登録()=nullかつ、リクエスト.モバイルフラグ=nullの場合、例外をスローする。
            if (l_gentradeSrvRegiApplication == null && (l_srvRegiExecRequest.mobileFlag == null))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00908,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //getサービス申込登録()=nullかつ、リクエスト.モバイルフラグ=1の場合
            if (l_gentradeSrvRegiApplication == null
                && WEB3SrvRegiMobileFlagDef.MOBILE.equals(l_srvRegiExecRequest.mobileFlag))
            {
                WEB3SrvRegiExecResponse l_srvRegiExecResponse =
                    (WEB3SrvRegiExecResponse)l_srvRegiExecRequest.createResponse();
                //○URL=#（仮データ）
                l_srvRegiExecResponse.url = "#";
                //○送信方法@区分=#（仮データ）
                l_srvRegiExecResponse.sendHowToDiv = "#";
                //○送信区分パラメータ区分一覧=#（仮データ）
                l_srvRegiExecResponse.sendParamList = new String[]{"#"};
                //○エラー区分=1（未申込）
                l_srvRegiExecResponse.srvRegiExecErrDiv = WEB3SrvRegiSrvRegiExecErrDivDef.UNAPPLY_ERROR;

                log.exiting(STR_METHOD_NAME);
                return l_srvRegiExecResponse;
            }

            //1.7.2 get申込登録ID
            long l_lngRegistId = l_gentradeSrvRegiApplication.getRegistId();

            //1.7.3 is利用可能
            boolean l_blnUsePossible =
                l_srvRegiRegistService.isUsePossible(l_strInstitutionCode, l_strBranchCode, l_srvRegiExecRequest.serviceDiv,
                l_strAccountCode, l_lngRegistId);
            if (!l_blnUsePossible)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01011,
                    getClass().getName() + STR_METHOD_NAME);
            }
        }
        //getサービスマスタ-.特殊処理区分 = 1(外部連携サービス）の場合
        WEB3SrvRegiOtherOrgInfoAdmin l_svRegiOtherOrgInfoAdmin = null;
        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
                (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);

            //get外部連携情報(String, String, String, String, boolean)
            //サービス区分 = getサービスマスター().サービス区分
            //証券会社コード = 取得した補助口座オブジェクト.getInstitution( ).getInstitutionCode( )
            //部店コード = 取得した補助口座オブジェクト.getBranch( ).getBranchCode( )
            //口座コード = 取得した補助口座オブジェクト.getMainAccoutn( ).getAccountCode( )
            //is行ロック = false
            l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_svRegiOtherOrgInfoAdmin =
                l_srvRegiOtherOrgService.getOtherOrgInfo(
                    l_srvRegiServiceMaster.getSrvDiv(),
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_subAccount.getMainAccount().getBranch().getBranchCode(),
                    l_subAccount.getMainAccount().getAccountCode(),
                    false);

            //get外部連携情報()の戻り値が null の場合、例外をスローする。
            if (l_svRegiOtherOrgInfoAdmin == null)
            {
                log.debug("外部連携情報を取得できません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                    getClass().getName() + STR_METHOD_NAME,
                    "外部連携情報を取得できません。");
            }
        }
		//仕様変更 NO_195 docId削除
        //1.8＜URL取得＞
        //1.8.1＜リクエストデータ.TokenがNullの場合＞
        String l_strSrvUrl = null;
        if (l_srvRegiExecRequest.token == null)
        {
            //1.8.1.1 getサービス利用URL
            log.debug("1.9.1.1 getサービス利用URL");
            l_strSrvUrl = l_srvRegiServiceMaster.getSrvUrl();
            log.debug("l_strSrvUrl = " + l_strSrvUrl);
        }
        else
        {
            //1.8.2.1get第２URL
            log.debug("1.9.2.1get第２URL");
            l_strSrvUrl = l_srvRegiServiceMaster.getUrl2();
            log.debug("l_strSrvUrl = " + l_strSrvUrl);
        }

		//障害対応 Q8 
        //1.9＜現在日付の取得＞
		Timestamp l_tsSystemTimestamp = new Timestamp(new Date().getTime());

		//オリックス　@口座開設対応で移動↑
        //1.10 get代理入力者
        //1.11 getログインチャネル

        //1.12 サービス利用予約語変換
        String l_strProductCode = l_srvRegiExecRequest.productCode;
        String l_strTraderCode = null;
        log.debug("l_trader = " + l_trader);
        if (l_trader != null)
        {
            log.debug("if (l_trader != null)");
            l_strTraderCode = l_trader.getTraderCode();
        }
        // getハッシュ計算方式区分
        String l_strHashCalHowToDiv = l_srvRegiServiceMaster.getHashCalHowToDiv();
        // replaceハッシュ計算方式
        WEB3SrvRegiStartInfoService l_srvRegiStartInfoService =
            (WEB3SrvRegiStartInfoService)Services.getService(WEB3SrvRegiStartInfoService.class);
        String l_strHashCalHowTo = l_srvRegiStartInfoService.replaceHashCalHowTo(l_strHashCalHowToDiv);

		//仕様変更 NO_195 docId削除
        //getサービスマスタ.特殊処理区分 = 1(外部連携サービス)の場合
        //Id=get外部連携情報().ID
        //Pass=get外部連携情報().Pass
        String l_strQtpId = null;
        String l_strQtpPass = null;
        if (WEB3SpecialProcessDivDef.OTHER_ORG_SERVICE.equals(l_strSpecialProcessDiv))
        {
            l_strQtpId = l_svRegiOtherOrgInfoAdmin.getId();

            l_strQtpPass = l_svRegiOtherOrgInfoAdmin.getPassword();

        }
        log.debug("QtpId=get外部連携情報().ID ===" + l_strQtpId);
        log.debug("QtpId=get外部連携情報().Pass ===" + l_strQtpPass);
        WEB3SrvRegiReservedWordChange l_srvRegiReservedWordChange =
            new WEB3SrvRegiReservedWordChange(l_strInstitutionCode, l_srvRegiExecRequest.serviceDiv, l_strBranchCode,
            l_strAccountCode, l_strProductCode, l_srvRegiExecRequest.token, l_strLoginChannel,
            l_strTraderCode, l_srvRegiExecRequest.marginTaxDiv, l_srvRegiExecRequest.futOpTaxDiv, l_tsSystemTimestamp,
            l_srvRegiExecRequest.marketCode, l_srvRegiExecRequest.type, l_strHashCalHowTo,
            l_srvRegiExecRequest.ssidValue, l_strQtpId, l_strQtpPass, l_subAccount);

        //1.13＜取得したURLに、"%%～%%"で区切られた予約語が含まれていた場合＞
        if (isRequiredFormat(l_strSrvUrl))
        {
            //1.13.1replace予約語
            log.debug("1.14.1replace予約語");
            l_strSrvUrl = l_srvRegiReservedWordChange.replaceReservedWord(l_strSrvUrl);
            log.debug("l_strSrvUrl = " + l_strSrvUrl);
        }

        //1.16  get送信パラメータ区分
        String l_strSendParamDiv = l_srvRegiServiceMaster.getSendParamDiv();

        String[] l_strUseKey = null;
        //1.17＜get送信パラメータ区分()の戻り値="有"の場合＞
        if (WEB3ConditionsValueDivDef.HAVE.equals(l_strSendParamDiv))
        {
            log.debug("1.18.1 get送信パラメータ一覧");
            //1.17.1 get送信パラメータ一覧
            WEB3SrvRegiServiceUseKey[] l_srvRegiServiceUseKey = l_srvRegiServiceMaster.getParamList();

            //1.17.2 ＜繰り返し処理＞
            int l_int = l_srvRegiServiceUseKey.length;
            l_strUseKey = new String[l_int];
            for (int i = 0; i < l_int; i++)
            {
                log.debug("1.18.2.1 ＜分岐処理＞");
                //1.17.2.1 ＜分岐処理＞
                String l_strSrvUseKey = l_srvRegiServiceUseKey[i].getSrvUseKey();
                if (isRequiredFormat(l_strSrvUseKey))
                {
                    String l_strReservedWordUseKey = l_srvRegiReservedWordChange.replaceReservedWord(l_strSrvUseKey);
                    l_strUseKey[i] = l_strReservedWordUseKey;
                    log.debug("l_strUseKey[i] = l_strReservedWordUseKey;");
                }
                else
                {
                    l_strUseKey[i] = l_strSrvUseKey;
                    log.debug("l_strUseKey[i] = l_strSrvUseKey;");
                }
            }
        }

        //1.18 get送信方法@区分
        String l_strSendHowToDiv = l_srvRegiServiceMaster.getSendHowToDiv();

        //1.19 createレスポンス
        WEB3SrvRegiExecResponse l_response = (WEB3SrvRegiExecResponse)l_srvRegiExecRequest.createResponse();

        l_response.url = l_strSrvUrl;
        l_response.sendHowToDiv = l_strSendHowToDiv;
        if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strSendParamDiv))
        {
            l_response.sendParamList = null;
            log.debug("l_response.sendParamList = null;");
        }
        else
        {
            l_response.sendParamList = l_strUseKey;
            log.debug("l_response.sendParamList = l_strUseKey;");
        }

        //○エラー区分=null
        l_response.srvRegiExecErrDiv = WEB3SrvRegiSrvRegiExecErrDivDef.NOT_ERROR;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    private boolean isRequiredFormat(String l_strUrl)
    {
        if (WEB3StringTypeUtility.isEmptyOrBlank(l_strUrl))
        {
            return false;
        }

        final String l_strDoublePercents = "%%";

        int l_intUrlLength = l_strUrl.length();

        for (int i = 0; i < l_intUrlLength - 1; i++)
        {
            if (l_strDoublePercents.equals(l_strUrl.substring(i, i + 2)))
            {
                for (int j = i + 2; j < l_intUrlLength - 1; j++)
                {
                    if (l_strDoublePercents.equals(l_strUrl.substring(j, j+2)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
@
