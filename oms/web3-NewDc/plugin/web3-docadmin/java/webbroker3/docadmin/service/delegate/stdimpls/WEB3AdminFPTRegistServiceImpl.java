head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧登録サービスImpl(WEB3AdminFPTRegistServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 柴双紅 (中訊) 新規作成
Revision History : 2007/10/15 柴双紅 (中訊) モデル006
Revision History : 2007/10/18 Inomata (SCS) モデル008
Revision History : 2007/10/19 Inomata (SCS) モデル009
Revision History : 2007/01/28 武波 (中訊) 仕様変更 モデル No.024
Revision History : 2008/02/13 武波 (中訊) 実装の問題001
Revision History : 2008/03/17 馮海濤 (中訊) 仕様変更・モデルNo.046
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTCommon;
import webbroker3.docadmin.WEB3AdminFPTDocDeliveryManagement;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputResponse;
import webbroker3.docadmin.message.WEB3FPTDocumentCategoryDetailsInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTRegistService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧登録サービスImpl)<BR>
 * 管理者金商法@交付閲覧登録サービス実装クラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminFPTRegistServiceImpl implements WEB3AdminFPTRegistService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTRegistServiceImpl.class);

    /**
     * @@roseuid 46FDDD350222
     */
    public WEB3AdminFPTRegistServiceImpl()
    {

    }

    /**
     * 金商法@交付閲覧登録処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧登録入力リクエストの場合<BR>
     * 　@－get入力画面()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧登録確認リクエストの場合<BR>
     * 　@－validate変更()をコールする。<BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付閲覧登録完了リクエストの場合<BR>
     * 　@－submit変更()をコールする。<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFPTRegistInputRequest)
        {
            l_response = this.getInputScreen(
                (WEB3AdminFPTRegistInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTRegistConfirmRequest)
        {
            l_response = this.validateChangedScreen(
                (WEB3AdminFPTRegistConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTRegistCompleteRequest)
        {
            l_response = this.submitChangedScreen(
                (WEB3AdminFPTRegistCompleteRequest)l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入力画面)<BR>
     * 金商法@交付閲覧登録入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get入力画面」参照。<BR>
     * <BR>
     * @@param l_request - (管理者金商法@交付閲覧登録入力リクエストデータオブジェクト)<BR>
     * 管理者金商法@交付閲覧登録入力リクエストデータオブジェクト<BR>
     * @@return WEB3AdminFPTRegistInputResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminFPTRegistInputResponse getInputScreen(
        WEB3AdminFPTRegistInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFPTRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："G0101"(金商法@交付閲覧登録)
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FPT_REG,
            true);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get部店コード( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //get書面種類詳細情報
        //証券会社コード： 管理者.get証券会社コード()の戻り値
        //部店コード：管理者.get部店コード()の戻り値
        //書面区分：null
        //書面種類コード： null
        WEB3FPTDocumentCategoryDetailsInfoUnit[] l_documentCategoryDetailsInfoUnits =
            WEB3AdminFPTCommon.getDocumentCategoryDetailsInfoUnit(
                l_strInstitutionCode,
                l_strBranchCode,
                null,
                null);

        //createResponse( )
        WEB3AdminFPTRegistInputResponse l_response =
            (WEB3AdminFPTRegistInputResponse)l_request.createResponse();

        //プロパティセット
        //現在日付：システム現在日時
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        //書面種類詳細情報[]：金商法@共通.get書名種類詳細情報()の戻り値
        l_response.documentCategoryList = l_documentCategoryDetailsInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更画面)<BR>
     * 金商法@交付閲覧登録確認画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「validate変更画面」参照。<BR>
     * <BR>
     * @@param l_request - (管理者金商法@交付閲覧登録確認リクエストデータオブジェクト)<BR>
     * 管理者金商法@交付閲覧登録確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminFPTRegistConfirmResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminFPTRegistConfirmResponse validateChangedScreen(
        WEB3AdminFPTRegistConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateChangedScreen(WEB3AdminFPTRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："G0101"(金商法@交付閲覧登録)
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FPT_REG,
            true);

        //validate部店権限(部店コード : String)
        //[引数]
        //リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //get証券会社( )
        Institution l_institution = l_administrator.getInstitution();

        //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //[引数]
        // 証券会社コード：管理者.get証券会社().get証券会社コード()の戻り値
        // 部店コード：リクエストデータ.部店コード
        // 口座コード：リクエストデータ.顧客コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            l_accountManager.getMainAccount(
                l_institution.getInstitutionCode(),
                l_request.branchCode,
                l_request.acceptCode);

        //create検索条件文字列
        String l_strQueryString = this.createQueryString(l_request);

        //create検索条件データコンテナ
        //口座ID： 顧客.getAccountId()の戻り値
        //検索情報： 管理者金商法@交付閲覧登録確認リクエスト
        Object[] l_queryDataContainers =
            this.createQueryDataContainer(l_mainAccount.getAccountId(), l_request);

        //get顧客表示名( )
        String l_strDisplayAccountName = l_mainAccount.getDisplayAccountName();

		//書面交付管理( )
		WEB3AdminFPTDocDeliveryManagement l_docDeliveryManagement =
			new WEB3AdminFPTDocDeliveryManagement();

        //get書面交付管理
        //検索文字列： create検索条件文字列()の戻り値
        //検索データコンテナ： create検索条件データコンテナ()の戻り値
        int l_intDocDivManagement = l_docDeliveryManagement.getDocDivManagement(
                l_strQueryString, l_queryDataContainers).size();

        //既に登録済みであった場合（get書面交付管理() > 0）、例外を生成する。
        //既に登録済みであった場合（書面交付管理.get書面交付管理()の戻り値長さ > 0）、例外をスローする。
		if (l_intDocDivManagement > 0){
					log.debug("指定の顧客は既に登録されています。");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_02950,
						this.getClass().getName() + STR_METHOD_NAME,
						"指定の顧客は既に登録されています。");
				}

        //createResponse( )
        WEB3AdminFPTRegistConfirmResponse l_response =
            (WEB3AdminFPTRegistConfirmResponse)l_request.createResponse();

        //プロパティセット
        //顧客名：顧客.get顧客表示名()の戻り値
        l_response.acceptName = l_strDisplayAccountName;

        //銘柄名： ""
        l_response.productName = "";

        log.entering(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更画面)<BR>
     * 金商法@交付閲覧登録完了画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「submit変更画面」参照。<BR>
     * <BR>
     * @@param l_request - (管理者金商法@交付閲覧登録完了リクエストデータオブジェクト)<BR>
     * 管理者金商法@交付閲覧登録完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminFPTRegistCompleteResponse
     * @@throws WEB3BaseException
     */
    protected WEB3AdminFPTRegistCompleteResponse submitChangedScreen(
        WEB3AdminFPTRegistCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitChangedScreen(WEB3AdminFPTRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："G0101"(金商法@交付閲覧登録)
        //is更新：true
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.FPT_REG,
            true);

        //validate部店権限(部店コード : String)
        //[引数]
        //リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        //validate取引パスワード(パスワード : String)
        //[引数]
        // パスワード：　@リクエストデータ.暗証番号
        l_administrator.validateTradingPassword(l_request.password);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        //get管理者コード( )
        String  l_strAdministratorCode = l_administrator.getAdministratorCode();

        //get顧客(証券会社コード : String, 部店コード : String, 口座コード : String)
        //[引数]
        // 証券会社コード：管理者.get証券会社コード()の戻り値
        // 部店コード：リクエストデータ.部店コード
        // 口座コード：リクエストデータ.顧客コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount =
            l_accountManager.getMainAccount(
                l_strInstitutionCode,
                l_request.branchCode,
                l_request.acceptCode);

        //getAccountId( )
        long l_lngAccountId = l_mainAccount.getAccountId();

        //create検索条件文字列
        String l_strQueryString = this.createQueryString(l_request);

        //create検索条件データコンテナ
        //口座ID： 顧客.getAccountId()の戻り値
        //検索情報： 管理者金商法@交付閲覧登録完了リクエスト
        Object[] l_queryDataContainers =
            this.createQueryDataContainer(l_lngAccountId, l_request);

        //書面交付管理( )
        WEB3AdminFPTDocDeliveryManagement l_docDeliveryManagement =
            new WEB3AdminFPTDocDeliveryManagement();

        //get書面交付管理
        //検索文字列： create検索条件文字列()の戻り値
        //検索データコンテナ： create検索条件データコンテナ()の戻り値
        int l_intDocDivManagement = l_docDeliveryManagement.getDocDivManagement(
            l_strQueryString, l_queryDataContainers).size();

		//get書面交付管理テーブル行( )
		if (l_intDocDivManagement > 0){
					log.debug("指定の顧客は既に登録されています。");
					log.exiting(STR_METHOD_NAME);
					throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_02950,
						this.getClass().getName() + STR_METHOD_NAME,
						"指定の顧客は既に登録されています。");
				}

        //プロパティセット
        //リクエストデータ.書面種類一覧の長さ回数LOOP処理
        int l_intDocumentCategoryList =
            l_request.documentCategoryList.length;
        for (int i = 0; i < l_intDocumentCategoryList; i++)
        {
            int l_intBatoProductCodeAdminInfo =
                l_request.documentCategoryList[i].batoProductCodeAdminInfo.length;
            for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
            {
                DocDeliveryManagementParams l_docDeliveryManagementParams =
                    new DocDeliveryManagementParams();

                //書面交付管理テーブルParams.口座ID = 顧客.getAccountId() の戻り値
                l_docDeliveryManagementParams.setAccountId(l_lngAccountId);

                //書面交付管理テーブルParams.証券会社コード = 管理者.get証券会社コード() の戻り値
                l_docDeliveryManagementParams.setInstitutionCode(l_strInstitutionCode);

                //書面交付管理テーブルParams.部店コード = リクエストデータ.部店コード
                l_docDeliveryManagementParams.setBranchCode(l_request.branchCode);

                //書面交付管理テーブルParams.顧客コード = 顧客.getAccountCode() の戻り値
                l_docDeliveryManagementParams.setAccountCode(l_mainAccount.getAccountCode());

                //書面交付管理テーブルParams.書面区分 = リクエストデータ.書面種類一覧[index].書面区分管理情報.書面区分
                l_docDeliveryManagementParams.setDocumentDiv(
                    l_request.documentCategoryList[i].documentDivList.documentDiv);

                //書面交付管理テーブルParams.銘柄コード = リクエストデータ.書面種類一覧[index].電子鳩銘柄コード管理情報[idx].電子鳩銘柄コード
                l_docDeliveryManagementParams.setProductCode(
                    l_request.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode);

                //書面交付管理テーブルParams.書面交付日 = リクエストデータ.書面交付日
                l_docDeliveryManagementParams.setDeliveryDate(l_request.docuDeliDate);

                //書面交付管理テーブルParams.削除フラグ = "0"
                l_docDeliveryManagementParams.setDeleteFlag(BooleanEnum.FALSE);

                //書面交付管理テーブルParams.更新者コード = 管理者.get管理者コード()の戻り値
                l_docDeliveryManagementParams.setLastUpdater(l_strAdministratorCode);

                //書面交付管理テーブルParams.作成日付 = 現在日時
                Timestamp l_tsCurrentTime = GtlUtils.getSystemTimestamp();
                l_docDeliveryManagementParams.setCreatedTimestamp(l_tsCurrentTime);

                //書面交付管理テーブルParams.更新日付 = 現在日時
                l_docDeliveryManagementParams.setLastUpdatedTimestamp(l_tsCurrentTime);

                //書面交付管理テーブルParams.書面種類コード = リクエストデータ.書面種類一覧[index].書面区分管理情報.書面種類コード
                l_docDeliveryManagementParams.setDocumentCategory(
                    l_request.documentCategoryList[i].documentDivList.documentCategory);

                //書面交付管理テーブルParams.みなし交付日 = null
                l_docDeliveryManagementParams.setDeemedDeliveryDate(null);

                //insert書面交付管理テーブル(書面交付管理テーブルParams)
                l_docDeliveryManagement.insertDocDeliveryManagementParams(l_docDeliveryManagementParams);
            }
        }

        // createResponse( )
        WEB3AdminFPTRegistCompleteResponse l_response =
            (WEB3AdminFPTRegistCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 取得条件にセットする値の配列を生成する。<BR>
     * <BR>
     * １）　@空のArrayListを生成する。<BR>
     * <BR>
     * ２）　@口座ID<BR>
     * <BR>
     * 　@　@　@　@引数.口座ID を１）のListに追加する。<BR>
     * <BR>
     * ３）　@書面区分・書面種類コード・電子鳩銘柄コード<BR>
     * 　@　@　@　@引数.検索情報.書面種類一覧の長さ分LOOP処理を行う。<BR>
     * <BR>
     * 　@３-１）　@引数.検索情報.書面種類一覧[index].書面区分管理情報.書面区分を<BR>
     * 　@　@　@　@　@　@　@１） のListに追加する。<BR>
     * <BR>
     * 　@３-２）　@引数.検索情報.書面種類一覧[index].書面区分管理情報.書面種類コードを<BR>
     * 　@　@　@　@　@　@　@１） のListに追加する。<BR>
     * <BR>
     * 　@３-３）　@引数.検索情報.書面種類一覧[index].電子鳩銘柄コード管理情報<BR>
     * 　@　@　@　@　@　@　@配列内の全ての電子鳩銘柄コードを １） のListに追加する。<BR>
     * <BR>
     * ４）　@書面交付日<BR>
     * <BR>
     * 　@　@　@　@引数.書面交付日 を１）のListに追加する。<BR>
     * <BR>
     * <BR>
     * ５）　@生成されたListから配列を取得し、返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * 口座ID<BR>
     * @@param l_request - (検索情報)<BR>
     * 検索情報<BR>
     * @@return Object[]
     * @@roseuid 4726EFED0213
     */
    private Object[] createQueryDataContainer(
        long l_lngAccountId,
        WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //１） 空のArrayListを生成する。
        List l_lisQueryDataContainers = new ArrayList();

        //２）　@口座ID
        //引数.口座ID を１）のListに追加する。
        l_lisQueryDataContainers.add(new Long(l_lngAccountId));

        //３）　@書面区分・書面種類コード・電子鳩銘柄コード
        //引数.検索情報.書面種類一覧の長さ分LOOP処理を行う。
        if (l_request instanceof WEB3AdminFPTRegistConfirmRequest)
        {
            WEB3AdminFPTRegistConfirmRequest l_registConfirmRequest =
                (WEB3AdminFPTRegistConfirmRequest)l_request;
            int l_intDocumentCategoryList = l_registConfirmRequest.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryList; i++)
            {
                //３-１）　@引数.検索情報.書面種類一覧[index].書面区分管理情報.書面区分を
                //１） のListに追加する。
                l_lisQueryDataContainers.add(
                    l_registConfirmRequest.documentCategoryList[i].documentDivList.documentDiv);

                //３-２）　@引数.検索情報.書面種類一覧[index].書面区分管理情報.書面種類コードを
                //１） のListに追加する。
                l_lisQueryDataContainers.add(
                    l_registConfirmRequest.documentCategoryList[i].documentDivList.documentCategory);

                //３-３）　@引数.検索情報.書面種類一覧[index].電子鳩銘柄コード管理情報
                //配列内の全ての電子鳩銘柄コードを １） のListに追加する。
                int l_intBatoProductCodeAdminInfo =
                    l_registConfirmRequest.documentCategoryList[i].batoProductCodeAdminInfo.length;
                for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
                {
                    l_lisQueryDataContainers.add(
                        l_registConfirmRequest.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode);
                }
            }

            //４）　@書面交付日
            //引数.書面交付日 を１）のListに追加する。
            l_lisQueryDataContainers.add(l_registConfirmRequest.docuDeliDate);
        }
        else if (l_request instanceof WEB3AdminFPTRegistCompleteRequest)
        {
            WEB3AdminFPTRegistCompleteRequest l_registCompleteRequest =
                (WEB3AdminFPTRegistCompleteRequest)l_request;
            int l_intDocumentCategoryList = l_registCompleteRequest.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryList; i++)
            {
                //３-１）　@引数.検索情報.書面種類一覧[index].書面区分管理情報.書面区分を
                //１） のListに追加する。
                l_lisQueryDataContainers.add(
                    l_registCompleteRequest.documentCategoryList[i].documentDivList.documentDiv);

                //３-２）　@引数.検索情報.書面種類一覧[index].書面区分管理情報.書面種類コードを
                //１） のListに追加する。
                l_lisQueryDataContainers.add(
                    l_registCompleteRequest.documentCategoryList[i].documentDivList.documentCategory);

                //３-３）　@引数.検索情報.書面種類一覧[index].電子鳩銘柄コード管理情報
                //配列内の全ての電子鳩銘柄コードを １） のListに追加する。
                int l_intBatoProductCodeAdminInfo =
                    l_registCompleteRequest.documentCategoryList[i].batoProductCodeAdminInfo.length;
                for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
                {
                    l_lisQueryDataContainers.add(
                        l_registCompleteRequest.documentCategoryList[i].batoProductCodeAdminInfo[j].batoProductCode);
                }
            }

            //４）　@書面交付日
            //引数.書面交付日 を１）のListに追加する。
            l_lisQueryDataContainers.add(l_registCompleteRequest.docuDeliDate);
        }

        //５）　@生成されたListから配列を取得し、返却する。
        Object[] l_queryDataContainers = new Object[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_queryDataContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryDataContainers;
    }


    /**
     * (create検索条件文字列)<BR>
     * 取得条件の文字列を生成する。<BR>
     * <BR>
     * １）　@空の文字列を生成する。<BR>
     * <BR>
     * ２）　@口座IDを１）の文字列に追加する。<BR>
     * <BR>
     * 　@　@　@"account_id = ? and ("<BR>
     * <BR>
     * ３）　@書面区分・書面種類コード・電子鳩銘柄コード<BR>
     * 　@　@　@引数.検索情報.書面種類一覧の長さ分LOOP処理を行う。<BR>
     * <BR>
     * 　@３-１）　@書面区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " (document_div = ? "<BR>
     * <BR>
     * 　@３-２）　@書面種類コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and document_category = ? "<BR>
     * <BR>
     * 　@３-３）　@引数.検索情報.書面種類一覧[index].電子鳩銘柄コード管理情報の長さ分<BR>
     * 　@　@　@　@　@　@電子鳩銘柄コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and product_code in (?, ?, ?,…)) or "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@※LOOP終了時は "or" 不要 <BR>
     * <BR>
     * ４）　@書面交付日を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@　@検索条件文字列 += ") and delivery_date = ?"<BR>
     * <BR>
     * ５）　@作成した検索条件文字列インスタンスを返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (検索情報)<BR>
     * 検索情報<BR>
     * @@return String
     * @@roseuid 4726EFEA0290
     */
    private String createQueryString(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME = "createQueryString()";
        log.entering(STR_METHOD_NAME);

        //１）　@空の文字列を生成する。
        StringBuffer l_sbQueryString = new StringBuffer();

        //２）　@口座IDを１）の文字列に追加する。
        //"account_id = ? and "
        l_sbQueryString.append(" account_id = ? and (");

        //３）　@書面区分・書面種類コード・電子鳩銘柄コード
        //引数.検索情報.書面種類一覧の長さ分LOOP処理を行う。
        if (l_request instanceof WEB3AdminFPTRegistConfirmRequest)
        {
            WEB3AdminFPTRegistConfirmRequest l_registConfirmRequest =
                (WEB3AdminFPTRegistConfirmRequest)l_request;
            int l_intDocumentCategoryList = l_registConfirmRequest.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryList; i++)
            {
                //３-１）　@書面区分を検索条件文字列に追加する。
                //検索条件文字列 += " (document_div = ? "
                l_sbQueryString.append(" (document_div = ? ");

                //３-２）　@書面種類コードを検索条件文字列に追加する。
                //検索条件文字列 += " and document_category = ? "
                l_sbQueryString.append(" and document_category = ? ");

                //３-３）　@引数.検索情報.書面種類一覧[index].電子鳩銘柄コード管理情報の長さ分
                //電子鳩銘柄コードを検索条件文字列に追加する。
                //検索条件文字列 += " and product_code in (?, ?, ?,…)) or "
                //※LOOP終了時は "or" 不要
                int l_intBatoProductCodeAdminInfo =
                    l_registConfirmRequest.documentCategoryList[i].batoProductCodeAdminInfo.length;
                String l_strBatoProductCodeAdminInfo = "";
                for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
                {
                    if (j == l_intBatoProductCodeAdminInfo - 1)
                    {
                        l_strBatoProductCodeAdminInfo += " ? ";
                    }
                    else
                    {
                        l_strBatoProductCodeAdminInfo += " ?, ";
                    }
                }

                if (i == l_intDocumentCategoryList - 1)
                {
                    l_sbQueryString.append(
                        " and product_code in (" + l_strBatoProductCodeAdminInfo + ")) ");
                }
                else
                {
                    l_sbQueryString.append(
                        " and product_code in (" + l_strBatoProductCodeAdminInfo + ")) or ");
                }
            }
        }
        else if (l_request instanceof WEB3AdminFPTRegistCompleteRequest)
        {
            WEB3AdminFPTRegistCompleteRequest l_registCompleteRequest =
                (WEB3AdminFPTRegistCompleteRequest)l_request;
            int l_intDocumentCategoryList = l_registCompleteRequest.documentCategoryList.length;
            for (int i = 0; i < l_intDocumentCategoryList; i++)
            {
                //３-１）　@書面区分を検索条件文字列に追加する。
                //検索条件文字列 += " (document_div = ? "
                l_sbQueryString.append(" (document_div = ? ");

                //３-２）　@書面種類コードを検索条件文字列に追加する。
                //検索条件文字列 += " and document_category = ? "
                l_sbQueryString.append(" and document_category = ? ");

                //３-３）　@引数.検索情報.書面種類一覧[index].電子鳩銘柄コード管理情報の長さ分
                //電子鳩銘柄コードを検索条件文字列に追加する。
                //検索条件文字列 += " and product_code in (?, ?, ?,…)) or "
                //※LOOP終了時は "or" 不要
                int l_intBatoProductCodeAdminInfo =
                    l_registCompleteRequest.documentCategoryList[i].batoProductCodeAdminInfo.length;
                String l_strBatoProductCodeAdminInfo = "";
                for (int j = 0; j < l_intBatoProductCodeAdminInfo; j++)
                {
                    if (j == l_intBatoProductCodeAdminInfo - 1)
                    {
                        l_strBatoProductCodeAdminInfo += " ? ";
                    }
                    else
                    {
                        l_strBatoProductCodeAdminInfo += " ?, ";
                    }
                }

                if (i == l_intDocumentCategoryList - 1)
                {
                    l_sbQueryString.append(
                        " and product_code in (" + l_strBatoProductCodeAdminInfo + ")) ");
                }
                else
                {
                    l_sbQueryString.append(
                        " and product_code in (" + l_strBatoProductCodeAdminInfo + ")) or ");
                }
            }
        }

        //４）　@書面交付日を検索条件文字列に追加する。
        //検索条件文字列 += ") and delivery_date = ?"
        l_sbQueryString.append(") and delivery_date = ? ");

        //５）　@作成した検索条件文字列インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }
}
@
