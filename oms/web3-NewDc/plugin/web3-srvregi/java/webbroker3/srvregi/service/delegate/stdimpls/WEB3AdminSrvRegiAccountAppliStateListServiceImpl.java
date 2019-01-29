head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.48.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountAppliStateListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客申込状況一覧サービスImpl(WEB3AdminSrvRegiAccountAppliStateListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/01 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.common.define.WEB3SrvRegiOfferingDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.SrvRegiApplicationParams;
import webbroker3.srvregi.WEB3SrvRegiApplicationRequiredService;
import webbroker3.srvregi.WEB3SrvRegiClientRequestService;
import webbroker3.srvregi.WEB3SrvRegiServiceInfoManagement;
import webbroker3.srvregi.WEB3SrvRegiServiceMaster;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.srvregi.message.WEB3AdminSrvRegiLotteryStateGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiNoLotteryStateGroup;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountAppliStateListService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * (サービス利用管理者顧客申込状況一覧サービスImpl)<BR>
 * サービス利用管理者顧客申込状況一覧サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountAppliStateListServiceImpl
    extends WEB3SrvRegiClientRequestService implements WEB3AdminSrvRegiAccountAppliStateListService
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountAppliStateListServiceImpl.class);

    /**
     * @@roseuid 416F392803D8
     */
    public WEB3AdminSrvRegiAccountAppliStateListServiceImpl()
    {

    }

    /**
     * サービス利用管理者顧客申込状況一覧照会処理を行う。<BR>
     * <BR>
     * シーケンス図「（サービス利用管理者）顧客申込状況検索」参照<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F520C70298
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME );

        WEB3AdminSrvRegiStateRequest l_stateRequest = null;


        if (l_request instanceof WEB3AdminSrvRegiStateRequest)
        {
            l_stateRequest = (WEB3AdminSrvRegiStateRequest)l_request;
        }
        else
        {
            String l_strErrorMessage = "パラメータの類型が不正。";
            log.debug(l_strErrorMessage);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.1 validate( )
        l_stateRequest.validate();

        //1.3 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.4 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, false);

        //1.5.1 validate部店権限(String[])
        l_admin.validateBranchPermission(l_stateRequest.branchCode);

        //1.6 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //1.7 getサービスマスター一覧(String, String)
        WEB3SrvRegiServiceInfoManagement l_srvRegiServiceInfoManagement = new WEB3SrvRegiServiceInfoManagement();
        WEB3SrvRegiServiceMaster[] l_srvMasterLists =
            l_srvRegiServiceInfoManagement.getSrvMasterList(l_strInstitutionCode, WEB3SrvRegiOfferingDivDef.REQUIRE);

        List l_lisNoLotteryStateGroups = new LinkedList();
        List  l_lisLotteryStateGroups = new LinkedList();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();

        int l_intListCount = l_srvMasterLists.length;
        //1.8 <繰り返し処理 *1>
        for (int i = 0; i < l_intListCount; i++)
        {
            WEB3SrvRegiServiceMaster  l_srvMaster = l_srvMasterLists[i];

            //1.8.1 get申込要サービス(boolean)
            WEB3SrvRegiApplicationRequiredService l_appRequiredService = l_srvMaster.getAppliRequiredSrv(false);

            if (l_appRequiredService == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME);
            }

            //1.8.2 get顧客(String, String, String)
            WEB3GentradeMainAccount l_mainAccount =
                l_accountManager.getMainAccount(l_admin.getInstitutionCode(), l_stateRequest.branchCode, l_stateRequest.accountCode);

            //1.8.3 getAccountCode( )
            String l_strAccountCode = l_mainAccount.getAccountCode();

            //1.8.4 getサービス申込登録一覧(String, String, String, String, boolean)
            WEB3SrvRegiRegistService l_appliRegiService =
                (WEB3SrvRegiRegistService) Services.getService(WEB3SrvRegiRegistService.class);

			//障害対応 NO_2049
            //引数は、「証券会社コード、部店コード、サービス区分、口座コード、適用終了日区分」
            SrvRegiApplicationParams[] l_appParams = l_appliRegiService.getServiceRegistLists(l_strInstitutionCode,
                l_stateRequest.branchCode, l_srvMaster.getSrvDiv(), l_strAccountCode, false);

            String l_strLotDiv = l_appRequiredService.getLotDiv();

            //1.8.5 <分岐処理 *1>
            if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(l_strLotDiv))
            {
                //1.8.5.1 サービス利用顧客申込状況一覧抽選無サービス一覧行( )
                WEB3AdminSrvRegiNoLotteryStateGroup l_noLotteryStateGroup = new WEB3AdminSrvRegiNoLotteryStateGroup();

                //1.8.5.2 メッセージ <プロパティセット *1>
                //○サービス区分=サービスマスターオブジェクト.getサービス区分( )の戻り値
                l_noLotteryStateGroup.serviceDiv = l_srvMaster.getSrvDiv();

                //○サービス名称=サービスマスターオブジェクト.getサービス名称( )の戻り値
                l_noLotteryStateGroup.serviceName = l_srvMaster.getSrvName();

                //●サービス申込登録オブジェクトが存在する場合
                if (l_appParams != null && l_appParams.length > 0)
                {
                    //○申込登録ID=サービス申込登録オブジェクト.get申込登録ID( )の戻り値
                    l_noLotteryStateGroup.applyRegId =
                        WEB3StringTypeUtility.formatNumber(l_appParams[0].getRegistId());

                    //○適用開始日=サービス申込登録オブジェクト.get適用開始日( )の戻り値
                    l_noLotteryStateGroup.trialStartDate = l_appParams[0].getAppliStartDate();

                    //○適用終了日=サービス申込登録オブジェクト.get適用終了日( )の戻り値
                    l_noLotteryStateGroup.trialEndDate = l_appParams[0].getAppliEndDate();

                    //○登録区分=サービス申込登録オブジェクト.get登録区分( )の戻り値
                    l_noLotteryStateGroup.registDiv = l_appParams[0].getPaymentDiv();

                    //○利用料金=サービス申込登録オブジェクト.get利用料金( )の戻り値
                    if (!l_appParams[0].getUseAmtIsNull())
                    {
                        l_noLotteryStateGroup.chargeAmt =
                            WEB3StringTypeUtility.formatNumber(l_appParams[0].getUseAmt());
                    }

                    //○申込抽選区分＝サービス申込登録オブジェクト.get申込抽選区分( )の戻り値
                    l_noLotteryStateGroup.applyLotteryDiv = l_appParams[0].getAppliLotDiv();

                    //○登録可能区分=false
                    l_noLotteryStateGroup.registAbleDiv = false;

					//障害対応 NO_2057
					try
					{
						//管理者更新権限チェック
						l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
						
						//障害対応 NO_2049
						Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
						Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
						
						//○登録可能区分
						if(l_srvMaster.isAppliPossible() &&
							WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()) &&
							WEB3DateUtility.compareToDay(l_datSystemDate, l_appParams[0].getAppliEndDate()) > 0)
						{
							//現在日付＞適用終了日の場合⇒登録可能
							l_noLotteryStateGroup.registAbleDiv = true;
						}
						else
						{
							//現在日付≦適用終了日の場合⇒登録不可
							l_noLotteryStateGroup.registAbleDiv = false;
						}
						
											  					
						//(*2-1) サービス申込登録オブジェクト.get取消区分( )="通常"の場合trueをセットする。
						if(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()))
						{
							//現在日付≦適用終了日の場合⇒変更可能
							if(WEB3DateUtility.compareToDay(l_datSystemDate, l_appParams[0].getAppliEndDate()) <= 0)
							{
								l_noLotteryStateGroup.changeAbleDiv = true;
							}
							else
							//現在日付≦適用終了日でない場合⇒変更不可能
							{
								l_noLotteryStateGroup.changeAbleDiv = false;
							}
						}
						//(*2-2) サービス申込登録オブジェクト.get取消区分( )="取消"の場合falseをセットする。
						else
						{
							l_noLotteryStateGroup.changeAbleDiv = false;
						}
					}
					catch(WEB3BaseException ex)
					{
						//更新権限がない為、登録、変更ボタンを表示しない。
						//登録可能区分=false
						l_noLotteryStateGroup.registAbleDiv = false;
						
						//変更可能区分=false
						l_noLotteryStateGroup.changeAbleDiv = false;
					}

                    //○最終更新日=サービス申込登録オブジェクト.get最終更新日( )の戻り値
                    l_noLotteryStateGroup.lastUpdateTime = l_appParams[0].getLastUpdatedTimestamp();

                    //○最終更新者=サービス申込登録オブジェクト.get最終更新者( )の戻り値
                    l_noLotteryStateGroup.lastUpdater = l_appParams[0].last_updater;
                }
                //●サービス申込登録オブジェクトが存在しない場合
                else
                {
                    //○申込登録ID=null
                    l_noLotteryStateGroup.applyRegId = null;

                    //○適用開始日=null
                    l_noLotteryStateGroup.trialStartDate = null;

                    //○適用終了日=null
                    l_noLotteryStateGroup.trialEndDate = null;

                    //○登録区分=null
                    l_noLotteryStateGroup.registDiv = null;

                    //○利用料金=null
                    l_noLotteryStateGroup.chargeAmt = null;

                    //○申込抽選区分=null
                    l_noLotteryStateGroup.applyLotteryDiv = null;

					//障害対応 NO_2057
					try
					{
						//管理者更新権限チェック
						l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
						
						//○登録可能区分=サービスマスターオブジェクト.is申込可能( )の戻り値
						l_noLotteryStateGroup.registAbleDiv = l_srvMaster.isAppliPossible();
						
						//○変更可能区分=false
						l_noLotteryStateGroup.changeAbleDiv = false;
					}
					catch(WEB3BaseException ex)
					{
						//更新権限がない為、登録、変更ボタンを表示しない。
						//登録可能区分=false
						l_noLotteryStateGroup.registAbleDiv = false;
						
						//変更可能区分=false
						l_noLotteryStateGroup.changeAbleDiv = false;
					}

                    //Q&A:WEB3-SRVREGI-A-UT-0079
                    //○最終更新日=null
                    l_noLotteryStateGroup.lastUpdateTime = null;

                    //Q&A:WEB3-SRVREGI-A-UT-0079
                    //○最終更新者=null
                    l_noLotteryStateGroup.lastUpdater = null;
                }
                //●初期申込区分=(*)
                if (l_noLotteryStateGroup.changeAbleDiv)
                {
                    //(*-1) 変更可能区分=true の場合、null をセットする。
                    l_noLotteryStateGroup.firstApplyDiv = null;
                }
                else
                {
                    //(*-2) 変更可能区分=false の場合、
                    //サービス利用申込登録サービス.get初期申込区分( )の戻り値をセットする。
                    l_noLotteryStateGroup.firstApplyDiv = l_appliRegiService.getInitializeAppliDiv(
                        l_strInstitutionCode,
                        l_stateRequest.branchCode,
                        l_srvMaster.getSrvDiv(),
                        l_strAccountCode);
                }

                //add to list
                l_lisNoLotteryStateGroups.add(l_noLotteryStateGroup);
            }
            //1.8.6 <分岐処理 *2>
            else if (WEB3ConditionsValueDivDef.HAVE.equals(l_strLotDiv))
            {
                //●サービス申込登録オブジェクトが存在しない場合
                if (l_appParams == null || l_appParams.length == 0)
                {
                    //1.8.6.1.1 サービス利用顧客申込状況一覧抽選有サービス一覧行( )
                    WEB3AdminSrvRegiLotteryStateGroup l_lotteryStateGroup = new WEB3AdminSrvRegiLotteryStateGroup();

                    //1.8.6.1.2 <プロパティセット *2>
                    //○サービス区分=サービスマスターオブジェクト.getサービス区分( )の戻り値
                    l_lotteryStateGroup.serviceDiv = l_srvMaster.getSrvDiv();

                    //○サービス名称=サービスマスターオブジェクト.getサービス名称( )の戻り値
                    l_lotteryStateGroup.serviceName = l_srvMaster.getSrvName();

                    //○申込登録ID=null
                    l_lotteryStateGroup.applyRegId = null;

                    //○適用開始日=null
                    l_lotteryStateGroup.trialStartDate = null;

                    //○適用終了日=null
                    l_lotteryStateGroup.trialEndDate = null;

                    //○登録区分=null
                    l_lotteryStateGroup.registDiv = null;

                    //○利用料金=null
                    l_lotteryStateGroup.chargeAmt = null;

                    //○申込抽選区分=null
                    l_lotteryStateGroup.applyLotteryDiv = null;

                    //○申込日=null
                    l_lotteryStateGroup.applyDate = null;

                    //○最終更新日=null
                    l_lotteryStateGroup.lastUpdateTime = null;

                    //○最終更新者=null
                    l_lotteryStateGroup.lastUpdater = null;
                    
					//障害対応 NO_2057
					try
					{
						//管理者更新権限チェック
						l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
						
						//○登録可能区分=サービスマスターオブジェクト.is申込可能( )の戻り値
						l_lotteryStateGroup.registAbleDiv = l_srvMaster.isAppliPossible();
						
						//○変更可能区分=false
						l_lotteryStateGroup.changeAbleDiv = false;
					}
					catch(WEB3BaseException ex)
					{
						//更新権限がない為、登録、変更ボタンを表示しない。
						//登録可能区分=false
						l_lotteryStateGroup.registAbleDiv = false;
						
						//変更可能区分=false
						l_lotteryStateGroup.changeAbleDiv = false;
					}
                     
                    //障害対応U01577
					//●初期申込区分=(*)
					if (l_lotteryStateGroup.changeAbleDiv)
					{
						//(*-1) 変更可能区分=true の場合、null をセットする。
						l_lotteryStateGroup.firstApplyDiv = null;
					}
					else
					{
						//(*-2) 変更可能区分=false の場合、
						//サービス利用申込登録サービス.get初期申込区分( )の戻り値をセットする。
						l_lotteryStateGroup.firstApplyDiv = l_appliRegiService.getInitializeAppliDiv(
							l_strInstitutionCode,
							l_stateRequest.branchCode,
							l_srvMaster.getSrvDiv(),
							l_strAccountCode);
					}

                    //add to list
                    l_lisLotteryStateGroups.add(l_lotteryStateGroup);
                }
                else
                {
                    //●サービス申込登録オブジェクトが存在する場合

                    //1.8.6.1.1 サービス利用顧客申込状況一覧抽選有サービス一覧行( )
                    WEB3AdminSrvRegiLotteryStateGroup l_lotteryStateGroup = new WEB3AdminSrvRegiLotteryStateGroup();

                    //1.8.6.1.2 <プロパティセット *2>
                    //○サービス区分=サービスマスターオブジェクト.getサービス区分( )の戻り値
                    l_lotteryStateGroup.serviceDiv = l_srvMaster.getSrvDiv();

                    //○サービス名称=サービスマスターオブジェクト.getサービス名称( )の戻り値
                    l_lotteryStateGroup.serviceName = l_srvMaster.getSrvName();

                    //○申込登録ID=サービス申込登録オブジェクト.get申込登録ID( )の戻り値
                    l_lotteryStateGroup.applyRegId =
                        WEB3StringTypeUtility.formatNumber(l_appParams[0].getRegistId());

                    //○適用開始日=サービス申込登録オブジェクト.get適用開始日( )の戻り値
                    l_lotteryStateGroup.trialStartDate = l_appParams[0].getAppliStartDate();

                    //○適用終了日=サービス申込登録オブジェクト.get適用終了日( )の戻り値
                    l_lotteryStateGroup.trialEndDate = l_appParams[0].getAppliEndDate();

                    //○登録区分=サービス申込登録オブジェクト.get登録区分( )の戻り値
                    l_lotteryStateGroup.registDiv = l_appParams[0].getPaymentDiv();

                    //○利用料金=サービス申込登録オブジェクト.get利用料金( )の戻り値
                    if (!l_appParams[0].getUseAmtIsNull())
                    {
                        l_lotteryStateGroup.chargeAmt =
                           WEB3StringTypeUtility.formatNumber(l_appParams[0].getUseAmt());
                    }

                    //○申込抽選区分=(*1)
                    //(*1-1) サービス申込登録オブジェクト.get取消区分( )="通常"の場合
                    if (WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()))
                    {
                        //(*1-1-1) サービス申込登録オブジェクト.get申込抽選区分( )="自動当選"の場合
                        if (WEB3SrvRegiAppliLotDivDef.AUTO_ELECTION.equals(l_appParams[0].getAppliLotDiv()))
                        {
                            //(*1-1-1-1)サービス申込登録オブジェクト.get自動当選取消期限日( )≧現在日付の場合"申込"をセットする。
                            if (WEB3DateUtility.compareToDay(l_appParams[0].getCancelLimitDate(), GtlUtils.getTradingSystem().getSystemTimestamp()) >= 0)
                            {
                                l_lotteryStateGroup.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.APPLI;
                            }
                            else
                            {
                                //(*1-1-1-2)サービス申込登録オブジェクト.get自動当選取消期限日( )＜現在日付の場合"当選／本申込"をセットする。
                                l_lotteryStateGroup.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI;
                            }
                        }
                        else
                        {
                            //(*1-1-2) サービス申込登録オブジェクト.get申込抽選区分( )="自動当選"以外の場合
                            //サービス申込登録オブジェクト.get申込抽選区分( )をセットする。
                            l_lotteryStateGroup.applyLotteryDiv = l_appParams[0].getAppliLotDiv();
                        }
                    }
                    else if (WEB3SrvRegiCancelDivDef.CANCEL.equals(l_appParams[0].getCancelDiv()))
                    {
                        //(*1-2) サービス申込登録オブジェクト.get取消区分( )="取消"の場合"取消"をセットする。
                        l_lotteryStateGroup.applyLotteryDiv = WEB3SrvRegiAppliLotDivDef.CANCEL;
                    }

                    //○最終更新日=サービス申込登録オブジェクト.get最終更新日( )の戻り値
                    l_lotteryStateGroup.lastUpdateTime = l_appParams[0].getLastUpdatedTimestamp();

                    //○最終更新者=サービス申込登録オブジェクト.get最終更新者( )の戻り値
                    l_lotteryStateGroup.lastUpdater = l_appParams[0].getLastUpdater();

                    //○申込日=サービス申込登録オブジェクト.get申込日( )の戻り値
                    l_lotteryStateGroup.applyDate = l_appParams[0].getAppliDate();

					//障害対応 NO_2057
					try
					{
						//管理者更新権限チェック
						l_admin.validateAuthority(WEB3TransactionCategoryDef.SRVREGI_ACCOUNT, true);
						
						//障害対応 NO_2049
						//○変更可能区分=(*2)
						Timestamp l_tsSystemTimestamp = GtlUtils.getTradingSystem( ).getSystemTimestamp();
						Date l_datSystemDate = WEB3DateUtility.toDay(l_tsSystemTimestamp);
						
						//○登録可能区分
						if(l_srvMaster.isAppliPossible() &&
							WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()) &&
							WEB3DateUtility.compareToDay(l_datSystemDate, l_appParams[0].getAppliEndDate()) > 0)
						{
							//現在日付＞適用終了日の場合⇒登録可能
							l_lotteryStateGroup.registAbleDiv = true;
						}
						else
						{
							//現在日付≦適用終了日の場合⇒登録不可
							l_lotteryStateGroup.registAbleDiv = false;
						}
					
						//(*2-1) サービス申込登録オブジェクト.get取消区分( )="通常"の場合trueをセットする。
						if(WEB3SrvRegiCancelDivDef.USUAL_DEFAULT.equals(l_appParams[0].getCancelDiv()))
						{
							//現在日付≦適用終了日の場合⇒変更可能
							if(WEB3DateUtility.compareToDay(l_datSystemDate, l_appParams[0].getAppliEndDate()) <= 0)
							{
								l_lotteryStateGroup.changeAbleDiv = true;
							}
							else
							//現在日付≦適用終了日でない場合⇒変更不可能
							{
								l_lotteryStateGroup.changeAbleDiv = false;
							}
						}
						//(*2-2) サービス申込登録オブジェクト.get取消区分( )="取消"の場合falseをセットする。
						else
						{
							l_lotteryStateGroup.changeAbleDiv = false;
						}
					}
					catch(WEB3BaseException ex)
					{
						//更新権限がない為、登録、変更ボタンを表示しない。
						//登録可能区分=false
						l_lotteryStateGroup.registAbleDiv = false;
						
						//変更可能区分=false
						l_lotteryStateGroup.changeAbleDiv = false;
					}

                    //●初期申込区分=(*)
                    if (l_lotteryStateGroup.changeAbleDiv)
                    {
                        //(*-1) 変更可能区分=true の場合、null をセットする。
                        l_lotteryStateGroup.firstApplyDiv = null;
                    }
                    else
                    {
                        //(*-2) 変更可能区分=false の場合、
                        //サービス利用申込登録サービス.get初期申込区分( )の戻り値をセットする。
                        l_lotteryStateGroup.firstApplyDiv = l_appliRegiService.getInitializeAppliDiv(
                            l_strInstitutionCode,
                            l_stateRequest.branchCode,
                            l_srvMaster.getSrvDiv(),
                            l_strAccountCode);
                    }

                    //add to list
                    l_lisLotteryStateGroups.add(l_lotteryStateGroup);
                }
            }
        }

        //1.9 createレスポンス( )
        WEB3AdminSrvRegiStateResponse l_response = (WEB3AdminSrvRegiStateResponse)l_request.createResponse();

        WEB3AdminSrvRegiNoLotteryStateGroup[] l_noLotteryStateGroups =
            new WEB3AdminSrvRegiNoLotteryStateGroup[l_lisNoLotteryStateGroups.size()];
        l_lisNoLotteryStateGroups.toArray(l_noLotteryStateGroups);

        WEB3AdminSrvRegiLotteryStateGroup[] l_lotteryStateGroups =
            new WEB3AdminSrvRegiLotteryStateGroup[l_lisLotteryStateGroups.size()];
        l_lisLotteryStateGroups.toArray(l_lotteryStateGroups);

        l_response.noLotDetails = l_noLotteryStateGroups;
        l_response.lotDetails = l_lotteryStateGroups;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
