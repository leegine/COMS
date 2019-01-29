head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設条件指定変更サービスImpl(WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 魏 (中訊) 新規作成
Revision History : 2007/02/02 魏 (中訊) モデルNo.169
Revision History : 2007/02/02 魏 (中訊) モデルNo.172
Revision History : 2007/02/02 魏 (中訊) モデルNo.174
Revision History : 2007/02/05 魏 (中訊) モデルNo.179
Revision History : 2007/02/28 Inomata(SCS)モデルNo.204
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.accountinfo.WEB3AdminAccInfoCampaignCommon;
import webbroker3.accountinfo.WEB3AdminAccInfoCampaignSearchCondition;
import webbroker3.accountinfo.define.WEB3AccInfoUpdateFlagDef;
import webbroker3.accountinfo.message.WEB3AccInfoCampaignInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignAccOpenInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

/**
 * 口座開設条件指定変更サービスImpl<BR>
 * @@author  魏
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl implements WEB3AdminAccInfoCampaignAccOpenChangeService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl.class);

    /**
     * @@roseuid 45C08B53006D
     */
    public WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl()
    {

    }

    /**
     * 口座開設条件指定変更処理を実施する。<BR>
     * <BR>
     * １） リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR> ○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン<BR>
     * 口座開設条件入力リクエストの場合 <BR>
     * －get入力画面()をコールする。 <BR>
     * <BR> ○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン<BR>
     * 口座開設条件確認リクエストの場合 <BR>
     * －validate変更()をコールする。 <BR>
     * <BR> ○ 引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン<BR>
     * 口座開設条件完了リクエストの場合 <BR>
     * －submit変更()をコールする。 <BR>
     *　@<BR>
     * @@param l_request -
     *            リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B046E800AF
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストが未指定(null)です。");
        }

        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン口座開設条件入力リクエストの場合
        if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenInputRequest)
        {
            //get入力画面()
            l_response =
                this.getInputScreen((WEB3AdminAccInfoCampaignAccOpenInputRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン口座開設条件確認リクエストの場合
        else if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenConfirmRequest)
        {
            //validate変更()
            l_response =
                this.validateChange((WEB3AdminAccInfoCampaignAccOpenConfirmRequest)l_request);
        }
        //引数のリクエストデータが、管理者お客様情報手数料割引キャンペーン口座開設条件完了リクエストの場合
        else if (l_request instanceof WEB3AdminAccInfoCampaignAccOpenCompleteRequest)
        {
            //submit変更()
            l_response =
                this.submitChange((WEB3AdminAccInfoCampaignAccOpenCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 口座開設条件指定変更入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設条件指定変更get入力画面」参照。 <BR>
     *　@<BR>
     * @@param l_request -
     *            管理者お客様情報手数料割引キャンペーン口座開設条件変更入力リクエストオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B421390073
     */
    protected WEB3AdminAccInfoCampaignAccOpenInputResponse getInputScreen(
            WEB3AdminAccInfoCampaignAccOpenInputRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoCampaignAccOpenInputRequest) ";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報()
        WEB3Administrator l_web3Administrator  = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);

        //変更処理の場合
        WEB3AccInfoCampaignInfo l_campaignInfo = null;
        if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
        {
            //getキャンペーン条件(String)
            WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
                WEB3AdminAccInfoCampaignCommon.getInstance();
            l_campaignInfo = l_accInfoCampaignCommon.getCampaignCondition(l_request.campaignId);
            //validate部店権限(部店コード : String)
            l_web3Administrator.validateBranchPermission(l_campaignInfo.branchCode);
        }

        //createResponse( )
        WEB3AdminAccInfoCampaignAccOpenInputResponse l_response =
            (WEB3AdminAccInfoCampaignAccOpenInputResponse) l_request.createResponse();
        l_response.commissionCampaignInfo = l_campaignInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * 口座開設条件指定変更確認画面表示処理を実施する。<BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : 「口座開設条件指定変更validate変更」参照。<BR>
     * 具体位置 :1.6.1 is変更情報()の戻り値 == falseの場合、例外をスロー<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_02723 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : 「口座開設条件指定変更validate変更」参照。<BR>
     * 具体位置 :1.7.1.1  is部店()の戻り値 == falseの場合、例外をスロー<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_00779 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : 「口座開設条件指定変更validate変更」参照。<BR>
     * 具体位置 :1.7.4 get重複キャンペーン条件()の戻り値がnull以外、かつ<BR>
     * リクエストデータ.手数料割引キャンペーン条件情報.口座開設日From != NULLの場合、例外をスロー<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_02724 <BR>
     * =============================================== <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設条件指定変更validate変更」参照。 <BR>
     *　@<BR>
     * @@param l_request -
     *            管理者お客様情報手数料割引キャンペーン口座開設条件変更確認リクエストオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B422700228
     */
    protected WEB3AdminAccInfoCampaignAccOpenConfirmResponse validateChange(
            WEB3AdminAccInfoCampaignAccOpenConfirmRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChange(WEB3AdminAccInfoCampaignAccOpenConfirmRequest) ";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoCampaignInfo[] l_campaignInfos = null;
        
        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報()
         WEB3Administrator l_web3Administrator  = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);

        if (!WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
        {
			//validate部店権限(部店コード : String)
			l_web3Administrator.validateBranchPermission(l_request.commissionCampaignInfo.branchCode);
        }

        WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
            WEB3AdminAccInfoCampaignCommon.getInstance();

        String l_strComparePeriod = null;
        //変更処理・削除処理の場合（更新処理フラグ == 1 or 2）
        if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag)
            || WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
        {
            //validate対象期間(手数料割引キャンペーン条件情報, 更新処理フラグ)
            l_strComparePeriod =
                l_accInfoCampaignCommon.validateTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);
        }

        //変更処理の場合（更新処理フラグ == 1）
        if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
        {
            // is変更情報(手数料割引キャンペーン条件情報)
            boolean l_blnChangeInfo =
                l_accInfoCampaignCommon.isChangeInfo(l_request.commissionCampaignInfo);
            if (!l_blnChangeInfo)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02723,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "情報に変更がありません。");
            }
        }

        //登録処理の場合（更新処理フラグ == 0）
        if(WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_request.updateFlag))
        {
            boolean l_blnBranch = false;
            WEB3AdminAccInfoCampaignSearchCondition l_condition = new WEB3AdminAccInfoCampaignSearchCondition();;
            // 部店コード != NULLの場合
            if (l_request.commissionCampaignInfo.branchCode != null)
            {
				l_blnBranch = this.isBranch(l_request.commissionCampaignInfo.institutionCode,
					l_request.commissionCampaignInfo.branchCode);

				if (!l_blnBranch)
				{
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00779,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							"部店コードの入力が不正です。");
				}
            }
            
			//validate入力対象期間(手数料割引キャンペーン条件情報, 更新処理フラグ)
			l_accInfoCampaignCommon.validateInputTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);

            //証券会社コードをセットする
            l_condition.setInstitutionCode(l_request.commissionCampaignInfo.institutionCode);

            //部店コードをセットする
            if (l_request.commissionCampaignInfo.branchCode != null)
            {
				l_condition.setBranchCode(l_request.commissionCampaignInfo.branchCode);
            }
            else
            {
				l_condition.setBranchCode("");
            }

            //対象期間Fromをセットする
            if (l_request.commissionCampaignInfo.targetPeriodFrom != null)
            {
				l_condition.setTargetPeriodFrom(
					l_accInfoCampaignCommon.formatDate(l_request.commissionCampaignInfo.targetPeriodFrom));
            }

            //扱者コードをセットする
            if (l_request.commissionCampaignInfo.traderCode != null)
            {
				l_condition.setTraderCode(l_request.commissionCampaignInfo.traderCode);
            }
            else
            {
				l_condition.setTraderCode("");
            }

            //口座開設区分をセットする
            if (l_request.commissionCampaignInfo.accountOpenDiv != null)
            {
				l_condition.setAccountOpenDiv(l_request.commissionCampaignInfo.accountOpenDiv);
            }
            else
            {
				l_condition.setAccountOpenDiv("");
            }

            //口座開設日Fromをセットする
            if (l_request.commissionCampaignInfo.accountOpenDateFrom != null)
            {
                l_condition.setAccountOpenDateFrom(l_request.commissionCampaignInfo.accountOpenDateFrom);
            }

            //商品コードをセットする
            l_condition.setItemCode(l_request.commissionCampaignInfo.itemCode);

            //登録タイプをセットする。
            String[] l_registType = {l_request.commissionCampaignInfo.registType};
            l_condition.setRegisterType(l_registType);
            
            //get重複キャンペーン条件(キャンペーン検索条件)
            l_campaignInfos = l_accInfoCampaignCommon.getSameCampaignCondition(l_condition);

			//リクエストデータ.手数料割引キャンペーン条件情報.口座開設日From != NULLの場合、
            if (l_campaignInfos != null && l_request.commissionCampaignInfo.accountOpenDateFrom != null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02724,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "キャンペーンが既に存在する。");
            }
        }

        WEB3AdminAccInfoCampaignAccOpenConfirmResponse l_response =
            (WEB3AdminAccInfoCampaignAccOpenConfirmResponse) l_request.createResponse();

		//削除処理の場合（更新処理フラグ==2）
        if(WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
        {
            //getキャンペーン条件(String)
            WEB3AccInfoCampaignInfo l_campaignInfo =
                l_accInfoCampaignCommon.getCampaignCondition(l_request.commissionCampaignInfo.campaignId);

			//validate部店権限(部店コード : String)
			l_web3Administrator.validateBranchPermission(l_campaignInfo.branchCode);

            l_response.commissionCampaignInfo = l_campaignInfo;
        }

        if(WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_request.updateFlag)
                || WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
        {
            l_response.commissionCampaignInfo = null;
        }

        l_response.alertFlag = l_strComparePeriod;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更)<BR>
     * 口座開設条件指定変更完了画面表示処理を実施する。<BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : 「口座開設条件指定変更submit変更」参照。<BR>
     * 具体位置 :1.8.1 is変更情報()の戻り値 == falseの場合、例外をスロー<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_02723 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : 「口座開設条件指定変更submit変更」参照。<BR>
     * 具体位置 :1.9.1.1  is部店()の戻り値 == falseの場合、例外をスロー<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_00779 <BR>
     * =============================================== <BR>
     * <BR>
     * =============================================== <BR>
     * シーケンス図 : 「口座開設条件指定変更submit変更」参照。<BR>
     * 具体位置 :1.9.4 get重複キャンペーン条件()の戻り値がnull以外、かつ<BR>
     * リクエストデータ.手数料割引キャンペーン条件情報.口座開設日From != NULLの場合、例外をスロー<BR>
     * class : WEB3BusinessLayerException <BR>
     * tag : BUSINESS_ERROR_02724 <BR>
     * =============================================== <BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設条件指定変更submit変更」参照。 <BR>
     *　@<BR>
     * @@param l_request -
     *            管理者お客様情報手数料割引キャンペーン口座開設条件変更完了リクエストオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignAccOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B4227100A1
     */
    protected WEB3AdminAccInfoCampaignAccOpenCompleteResponse submitChange(
            WEB3AdminAccInfoCampaignAccOpenCompleteRequest l_request)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChange(WEB3AdminAccInfoCampaignAccOpenCompleteRequest) ";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoCampaignInfo[] l_campaignInfos = null;
        
        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報()
        WEB3Administrator l_web3Administrator  = WEB3Administrator.getInstanceFromLoginInfo();

       //validate権限(機@能カテゴリコード : String, is更新 : boolean)
       l_web3Administrator.validateAuthority(
           WEB3TransactionCategoryDef.ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN, true);

       //validate部店権限(部店コード : String)
       l_web3Administrator.validateBranchPermission(l_request.commissionCampaignInfo.branchCode);

       //validate取引パスワード
       l_web3Administrator.validateTradingPassword(l_request.password);

       //変更処理の場合
       WEB3AdminAccInfoCampaignCommon l_accInfoCampaignCommon = 
           WEB3AdminAccInfoCampaignCommon.getInstance();

       String l_strTargetPeriod = null;
       //変更処理・削除処理の場合（更新処理フラグ == 1 or 2）
       if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag)
           || WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
       {
           //validate対象期間(手数料割引キャンペーン条件情報, 更新処理フラグ)
           l_strTargetPeriod =
               l_accInfoCampaignCommon.validateTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);
       }

       //get管理者コード( )
       String l_strAdministratorCode = l_web3Administrator.getAdministratorCode();

       //変更処理の場合（更新処理フラグ == 1）
       if(WEB3AccInfoUpdateFlagDef.UPDATE.equals(l_request.updateFlag))
       {
           // is変更情報(手数料割引キャンペーン条件情報)
           boolean l_blnChangeInfo =
               l_accInfoCampaignCommon.isChangeInfo(l_request.commissionCampaignInfo);

           if (!l_blnChangeInfo)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_02723,
                       this.getClass().getName() + "." + STR_METHOD_NAME,
                       "情報に変更がありません。");
           }

           //updateキャンペーン条件(手数料割引キャンペーン条件情報, String)
           l_accInfoCampaignCommon.updateCampaignCondition(l_request.commissionCampaignInfo, l_strAdministratorCode);
       }

       //登録処理の場合（更新処理フラグ == 0）
       if(WEB3AccInfoUpdateFlagDef.LOGIN.equals(l_request.updateFlag))
       {
           boolean l_blnBranch = false;
           WEB3AdminAccInfoCampaignSearchCondition l_condition = new WEB3AdminAccInfoCampaignSearchCondition();;
           // 部店コード != NULLの場合
           if (l_request.commissionCampaignInfo.branchCode != null)
           {
				l_blnBranch = this.isBranch(l_request.commissionCampaignInfo.institutionCode,
					l_request.commissionCampaignInfo.branchCode);
	
				if (!l_blnBranch)
				{
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
							WEB3ErrorCatalog.BUSINESS_ERROR_00779,
							this.getClass().getName() + "." + STR_METHOD_NAME,
							"部店コードの入力が不正です。");
				}
           }

           //validate入力対象期間(手数料割引キャンペーン条件情報,更新処理フラグ)
           l_accInfoCampaignCommon.validateInputTargetPeriod(l_request.commissionCampaignInfo, l_request.updateFlag);

           //証券会社コードをセットする
           l_condition.setInstitutionCode(l_request.commissionCampaignInfo.institutionCode);

           //部店コードをセットする
           if (l_request.commissionCampaignInfo.branchCode != null)
           {
               l_condition.setBranchCode(l_request.commissionCampaignInfo.branchCode);
           }
           else
           {
               l_condition.setBranchCode("");
           }

           //対象期間Fromをセットする
           if (l_request.commissionCampaignInfo.targetPeriodFrom != null)
           {
			l_condition.setTargetPeriodFrom(
				l_accInfoCampaignCommon.formatDate(l_request.commissionCampaignInfo.targetPeriodFrom));
           }

           //扱者コードをセットする
           if (l_request.commissionCampaignInfo.traderCode != null)
           {
               l_condition.setTraderCode(l_request.commissionCampaignInfo.traderCode);
           }
           else
           {
               l_condition.setTraderCode("");
           }

           //口座開設区分をセットする
           if (l_request.commissionCampaignInfo.accountOpenDiv != null)
           {
			   l_condition.setAccountOpenDiv(l_request.commissionCampaignInfo.accountOpenDiv);
           }
           else
           {
			   l_condition.setAccountOpenDiv("");
           }

           //口座開設日Fromをセットする
           if (l_request.commissionCampaignInfo.accountOpenDateFrom != null)
           {
               l_condition.setAccountOpenDateFrom(l_request.commissionCampaignInfo.accountOpenDateFrom);
           }

           //商品コードをセットする
           l_condition.setItemCode(l_request.commissionCampaignInfo.itemCode);

           //登録タイプをセットする。
           String[] l_registType = {l_request.commissionCampaignInfo.registType};
           l_condition.setRegisterType(l_registType);
            
           //get重複キャンペーン条件(キャンペーン検索条件)
           l_campaignInfos = l_accInfoCampaignCommon.getSameCampaignCondition(l_condition);

           if (l_campaignInfos != null && l_request.commissionCampaignInfo.accountOpenDateFrom != null)
           {
               log.exiting(STR_METHOD_NAME);
               throw new WEB3BusinessLayerException(
                       WEB3ErrorCatalog.BUSINESS_ERROR_02724,
                       this.getClass().getName() + "." + STR_METHOD_NAME,
                       "キャンペーンが既に存在する。");
           }
           // insertキャンペーン条件(手数料割引キャンペーン条件情報, String)
           l_accInfoCampaignCommon.insertCampaignCondition(l_request.commissionCampaignInfo, l_strAdministratorCode);
       }

       //削除処理の場合（更新処理フラグ == 2）
       if(WEB3AccInfoUpdateFlagDef.DELETE.equals(l_request.updateFlag))
       {
           //deleteキャンペーン条件(String, String)
           l_accInfoCampaignCommon.deleteCampaignCondition(
                   l_request.commissionCampaignInfo.campaignId, l_strAdministratorCode);
       }

       //createResponse( )
       WEB3AdminAccInfoCampaignAccOpenCompleteResponse l_response =
           (WEB3AdminAccInfoCampaignAccOpenCompleteResponse) l_request.createResponse();
       l_response.alertFlag = l_strTargetPeriod;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (is部店)<BR>
     * （引数）部店コードが部店テーブル上に存在するかチェックを行う。<BR>
     * <BR>
     * 存在する場合はTRUE、存在しない場合はFALSEを返却する。<BR>
     * <BR>
     * １） 検索データコンテナ用長さ2のObject配列を作成する。<BR>
     * Object[0] = （引数）証券会社コード<BR>
     * Object[1] = （引数）部店コード<BR>
     * <BR>
     * ２） QueryProcessor.doFindAllQuery( )により、部店テーブルレコードを取得する。 <BR>
     * <BR>
     * [doFindAllQuery()に指定する引数] <BR>
     * arg1： 部店テーブルRow.TYPE <BR>
     * arg2： "institution_code = ? and branch_code = ?"<BR>
     * arg3： １）で作成したObject配列<BR>
     * <BR>
     * ３） ２）の戻り値Listの長さ > 0 の場合、TRUE を返却する。<BR>
     * <BR>
     * ４） ３）以外の場合、FALSE を返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_strInstitution -
     *            証券会社コード<BR>
     * @@param l_strBranchCode -
     *            部店コード<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 45B5BF9D0184
     */
    private boolean isBranch(String l_strInstitution, String l_strBranchCode)throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isBranch(String, String) ";
        log.entering(STR_METHOD_NAME);

        if (l_strInstitution == null || l_strBranchCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "証券会社コード又は部店コードが未指定(null)です。");
        }

        //検索データコンテナ用長さ2のObject配列を作成する。
        Object[] l_objQueryContainers = new Object[2];

        l_objQueryContainers[0] = l_strInstitution;
        l_objQueryContainers[1] = l_strBranchCode;

        String l_strWhere = " institution_code = ? and branch_code = ? ";
        List l_lisRecords = new ArrayList();
        QueryProcessor l_queryProcessor;
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                    BranchRow.TYPE,
                    l_strWhere,
                    l_objQueryContainers);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords != null && l_lisRecords.size() > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}
@
