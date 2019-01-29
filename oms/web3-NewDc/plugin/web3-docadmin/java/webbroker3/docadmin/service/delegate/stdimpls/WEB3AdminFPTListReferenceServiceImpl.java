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
filename	WEB3AdminFPTListReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧照会サービスImpl(WEB3AdminFPTListReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 武波 (中訊) 新規作成
Revision History : 2007/10/15 武波 (中訊) モデルNo.005
Revision History : 2007/10/16 Inomata (SCS) モデルNo.007
Revision History : 2007/10/18 Inomata (SCS) モデル008
Revision History : 2008/01/28 柴双紅 (中訊) モデルNo.025
Revision History : 2008/03/17 馮海濤 (中訊) モデルNo.047
Revision History : 2008/04/18 柴双紅 (中訊) モデルNo.053
*/

package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTDocCategoryManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDivManagement;
import webbroker3.docadmin.define.WEB3AdminFPTSortKeyItemDef;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSortKey;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTHistoryInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTListReferenceService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者金商法@交付閲覧照会サービスImpl)<BR>
 * 管理者金商法@交付閲覧照会サービス実装クラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTListReferenceServiceImpl implements WEB3AdminFPTListReferenceService
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTListReferenceServiceImpl.class);

    /**
     * @@roseuid 46FDDD360157
     */
    public WEB3AdminFPTListReferenceServiceImpl()
    {

    }

    /**
     * 金商法@交付閲覧照会処理を実施する。<BR>
     * <BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * １－１）　@引数のリクエストデータが、管理者金商法@交付閲覧検索入力リクエストの場合<BR>
     * 　@－get交付閲覧検索入力()をコールする。<BR>
     * <BR>
     * １－２）　@引数のリクエストデータが、管理者金商法@交付閲覧一覧照会リクエストの場合<BR>
     * 　@－get交付閲覧照会()をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46F1D60C02A4
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

        if (l_request instanceof WEB3AdminFPTSearchInputRequest)
        {
            //管理者金商法@交付閲覧検索入力リクエストの場合
            l_response = this.getListSearchInput((WEB3AdminFPTSearchInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFPTListReferenceRequest)
        {
            //管理者金商法@交付閲覧一覧照会リクエスト
            l_response = this.getListReference((WEB3AdminFPTListReferenceRequest)l_request);
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
     * (get交付閲覧検索入力)<BR>
     * 管理者金商法@交付閲覧検索入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get交付閲覧検索入力」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 46F1F2CE011D
     */
    protected WEB3AdminFPTSearchInputResponse getListSearchInput(
        WEB3AdminFPTSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSearchInput(WEB3AdminFPTSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY, false);

        //get証券会社コード()
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get部店コード()
        String l_strBranchCode = l_admin.getBranchCode();

        //書面区分管理(String, String, String, String)
        // [引数]
        // 証券会社コード： 管理者.get証券会社コード() の戻り値
        // 部店コード： 管理者.get部店コード() の戻り地
        // 書面区分： null
        // 書面種類コード： null
        WEB3AdminFPTDocDivManagement l_adminDocAdminFPTDocDivManagement =
            new WEB3AdminFPTDocDivManagement(
                l_strInstitutionCode,
                l_strBranchCode,
                null,
                null);

        //get書面区分管理一覧()
        WEB3FPTDocumentDivAdminInfoUnit[] l_fPTDocumentDivAdminInfoUnits =
            l_adminDocAdminFPTDocDivManagement.getDocDivManagementLists();

        //creatResponse
        WEB3AdminFPTSearchInputResponse l_response =
            (WEB3AdminFPTSearchInputResponse)l_request.createResponse();

        //レスポンス.書面区分管理一覧 = get書面区分管理一覧()
        l_response.documentDivList = l_fPTDocumentDivAdminInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get交付閲覧照会)<BR>
     * 管理者金商法@交付閲覧照会画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「get交付閲覧照会」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTListReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 46F1F2E602FE
     */
    protected WEB3AdminFPTListReferenceResponse getListReference(
        WEB3AdminFPTListReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListReference(WEB3AdminFPTListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //validate()
        l_request.validate();

        //getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FPT_REGIST_INQUIRY, false);

        //validate部店権限(部店コード : String[])
        l_admin.validateBranchPermission(l_request.branchCode);

        //get証券会社コード()
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //validate検索条件(String, 管理者金商法@交付閲覧一覧照会リクエスト)
        // [引数]
        //  証券会社コード： 管理者.get証券会社コード()の戻り値
        //  検索条件：  管理者金商法@交付閲覧一覧照会リクエスト
        this.validateQueryCondition(l_strInstitutionCode, l_request);

        //create検索条件文字列(管理者金商法@交付閲覧一覧照会リクエスト)
        String l_strQueryString = this.createQueryString(l_request);

        //create検索条件データコンテナ(String, 管理者金商法@交付閲覧一覧照会リクエスト)
        Object[] l_queryContainers = this.createQueryDataContainer(l_strInstitutionCode, l_request);

        //createソートキー(管理者金商法@ソートキー[])
        String l_strSortKey = this.createSortKey(l_request.sortKeys);

        WEB3AdminFPTListReferenceResponse l_response;

        try
        {
            //getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            ListPage l_lisDocDeliveryManagements = l_queryProcessor.doFindAllQuery(
                DocDeliveryManagementRow.TYPE,
                l_strQueryString,
                l_strSortKey,
                null,
                l_queryContainers,
                Integer.parseInt(l_request.pageSize),
                Integer.parseInt(l_request.pageIndex) - 1);

			//レコードを取得できない場合、例外の「該当なしエラー」をthrowする。
			if (l_lisDocDeliveryManagements == null || l_lisDocDeliveryManagements.isEmpty())
			{
				log.debug("該当するデータが存在しません。");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00398,
					this.getClass().getName() + STR_METHOD_NAME);
			}

            WEB3PageIndexInfo l_lisViewPageIndexInfo =
                new WEB3PageIndexInfo(
                	l_lisDocDeliveryManagements,
                    Integer.parseInt(l_request.pageIndex),
                    Integer.parseInt(l_request.pageSize));

            int l_intRowsLength = l_lisDocDeliveryManagements.size();

            //get証券会社()
            WEB3GentradeInstitution l_gentradeInstitution =
                (WEB3GentradeInstitution)l_admin.getInstitution();

            List l_lisHistoryInfoUnits = new ArrayList();
            //取得したレコードの要素分、Loop処理
            for (int i = 0; i < l_intRowsLength; i++)
            {
                //create金商法@交付閲覧情報(書面交付管理Params, 証券会社)
                DocDeliveryManagementParams l_docDeliveryManagementParams =
                    new DocDeliveryManagementParams(
                        (DocDeliveryManagementRow)l_lisDocDeliveryManagements.get(i));
                WEB3FPTHistoryInfoUnit l_fPTHistoryInfoUnit =
                    this.createHistoryInfoUnit(l_docDeliveryManagementParams, l_gentradeInstitution);

                //add(arg0 : Object)
                l_lisHistoryInfoUnits.add(l_fPTHistoryInfoUnit);
            }

            //createResponse()
            l_response = (WEB3AdminFPTListReferenceResponse)l_request.createResponse();

            //金商法@交付閲覧一覧
            WEB3FPTHistoryInfoUnit[] l_fPTHistoryInfoUnits =
                new WEB3FPTHistoryInfoUnit[l_lisHistoryInfoUnits.size()];
            l_lisHistoryInfoUnits.toArray(l_fPTHistoryInfoUnits);
            l_response.financialProductTradeList = l_fPTHistoryInfoUnits;

            //表示ページ番号
            l_response.pageIndex = String.valueOf(l_lisViewPageIndexInfo.getPageIndex());

            //総ページ数
            l_response.totalPages = String.valueOf(l_lisViewPageIndexInfo.getTotalPages());

            //総レコード数
            l_response.totalRecords = String.valueOf(l_lisViewPageIndexInfo.getTotalRecords());
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列)<BR>
     * 取得条件の文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 　@"institution_code=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ３） 部店コード <BR>
     * ３-１） 引数:検索情報. 部店コードの長さ=1 の場合、<BR> 
     * 部店コードを検索条件文字列に追加する。  <BR>
     * 検索条件文字列 += "and branch_code = ? "  <BR>
     * ３-２） 引数:検索情報. 部店コードの長さ > 1の場合、 <BR>
     * 部店コード配列の要素数分、部店コード条件を追加する。<BR>
     * 検索条件文字列 +="and branch_code in (?,?, …）"   <BR>
     * <BR>
     * ４）　@引数:検索情報. 顧客コード != null の場合、<BR>
     * 顧客コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 検索条件文字列 += "and account_code like ? %"<BR>
     * <BR>
     * ５）　@書面区分・書面種類コード<BR>
     * 　@５-１） 引数:検索情報.書面区分管理一覧の長さ = 1 の場合、<BR>
     * 　@　@書面区分、書面種類コードを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and document_div = ? and document_category = ? "<BR>
     * <BR>
     * 　@５-２） 引数:検索情報.書面区分管理一覧の長さ > 1 の場合、<BR>
     * 　@　@配列の要素数分、書面区分、書面種類コードのペアを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and ((document_div = ? and document_category = ?) or (…))"<BR>
     * <BR>
     * ６）　@引数:検索情報.書面交付日from != nullの場合、<BR>
     * 　@　@書面交付日fromを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and delivery_date　@>=　@?"<BR>
     * <BR>
     * ７）　@引数:検索情報.書面交付日to != nullの場合、<BR>
     * 　@　@書面交付日toを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and delivery_date　@<=　@?"<BR>
     * <BR>
     * <BR>
     * ８）　@作成した検索条件文字列インスタンスを返却する。<BR>
     * <BR>
     * @@param l_request - (検索条件)<BR>
     * 検索条件<BR>
     * @@return String
     * @@roseuid 46F20689028C
     */
    private String createQueryString(WEB3AdminFPTListReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminFPTListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //１）空の文字列を生成する。
        StringBuffer l_sbQueryString = new StringBuffer();

        //２）証券会社コード
        //"institution_code=?" を１）の文字列に追加する。
        l_sbQueryString.append(" institution_code = ? ");

		//３）　@部店コード 
		//  ３-１） 引数:検索情報. 部店コードの長さ=1 の場合、  
		//		 部店コードを検索条件文字列に追加する。  
		//		 検索条件文字列 += "and branch_code = ? "  
		//  ３-２） 引数:検索情報. 部店コードの長さ > 1の場合、 
		//		部店コード配列の要素数分、部店コード条件を追加する。   
		//	   検索条件文字列 +="and branch_code in (?,?, …）"   
    	if (l_request.branchCode.length == 1)
    	{
			l_sbQueryString.append(" and branch_code = ? ");
    	} else if (l_request.branchCode.length > 1)
    	{
			l_sbQueryString.append(" and branch_code in (");

			for (int i = 0; i < l_request.branchCode.length; i++)
			{
				l_sbQueryString.append("?");
				if (i != l_request.branchCode.length-1){
					l_sbQueryString.append(", ");
				}
			}
			l_sbQueryString.append(")");
    	}

        //４）　@引数:検索情報. 顧客コード != null の場合、
        //顧客コードを検索条件文字列に追加する。
        //検索条件文字列 += "and account_code like ? %"
        if (l_request.acceptCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%' ");
        }

        //書面区分・書面種類コード
        // 引数:検索情報.書面区分管理一覧の長さ = 1 の場合
        // 書面区分、書面種類コードを検索条件文字列に追加する。
        // 検索条件文字列 += "and document_div = ? and document_category = ? "
        if (l_request.documentDivList.length == 1)
        {
            l_sbQueryString.append(" and document_div = ? and document_category = ? ");
        }

        // 引数:検索情報.書面区分管理一覧の長さ > 1 の場合、
        // 配列の要素数分、書面区分、書面種類コードのペアを検索条件文字列に追加する。
        // 検索条件文字列 += "and ((document_div = ? and document_category = ?) or (…))"
        if (l_request.documentDivList.length > 1)
        {
            l_sbQueryString.append(" and( ");
            for (int i = 0; i < l_request.documentDivList.length; i++)
            {
                l_sbQueryString.append(" (document_div = ? and document_category = ?) ");
                if (i < l_request.documentDivList.length - 1)
                {
                    l_sbQueryString.append(" or ");
                }
            }
            l_sbQueryString.append(" ) ");
        }

        //７）　@引数:検索情報.書面交付日from != nullの場合、
        //書面交付日fromを検索条件文字列に追加する。
        //検索条件文字列 += "and delivery_date　@>=　@?"
        if (l_request.docuDeliDateFrom != null)
        {
            l_sbQueryString.append(" and delivery_date >= ? ");
        }

        //８）　@引数:検索情報.書面交付日to != nullの場合、
        //書面交付日toを検索条件文字列に追加する。
        //検索条件文字列 += "and delivery_date　@<=　@?"
        if (l_request.docuDeliDateTo != null)
        {
            l_sbQueryString.append(" and delivery_date <= ? ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 取得条件にセットする値の配列を生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 　@引数.証券会社コード を１）のListに追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     *   ３-１） 引数:検索情報. 部店コードの長さ=1 の場合、  <BR>
     *   引数:検索情報.部店コード[0]  を１）のListに追加する。<BR>
     *   ３-２） 引数:検索情報. 部店コードの長さ > 1の場合、<BR>
     *   引数:検索情報.部店コード配列の全ての要素を１）のListに追加する。<BR> 
     * 　@引数:検索情報.部店コードの各要素 を１）のListに追加する。<BR>
     * <BR>
     * ４）顧客コード<BR>
     * <BR>
     * 　@引数:検索情報.顧客コード != nullの場合<BR>
     * <BR>
     * 　@引数:検索情報.顧客コード を１）のListに追加する。<BR>
     * <BR>
     * ５）書面区分、書面種類コード<BR>
     * 　@５-１） 引数:検索情報.書面区分管理一覧の長さ = 1 の場合、<BR>
     * <BR>
     * 　@　@書面区分、書面種類コードを１）のListに追加する。<BR>
     * <BR>
     * 　@５-２） 引数:検索情報.書面区分管理一覧の長さ > 1 の場合、<BR>
     * 　@　@配列の全ての要素の 書面区分、書面種類コードを、１）のListに追加する。<BR>
     * <BR>
     * ６）書面交付日from<BR>
     * <BR>
     * 　@引数:検索情報.書面交付日from != nullの場合<BR>
     * <BR>
     * 　@引数:検索情報.書面交付日from を１）のListに追加する。<BR>
     * <BR>
     * ７）書面交付日to<BR>
     * <BR>
     * 　@引数:検索情報.書面交付日to != nullの場合<BR>
     * <BR>
     * 　@引数:検索情報.書面交付日to を１）のListに追加する。<BR>
     * <BR>
     * <BR>
     * ８）生成されたListから配列を取得し、返却する。<BR>
     * <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_request - (検索情報)<BR>
     * 検索情報<BR>
     * @@return Object[]
     * @@roseuid 46F2069C0049
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode, WEB3AdminFPTListReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(String, WEB3AdminFPTListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //１）空のArrayListを生成する。
        List l_lisQueryContainers = new ArrayList();

        //２）証券会社コード
        //引数.証券会社コード を１）のListに追加する。
        l_lisQueryContainers.add(l_strInstitutionCode);

		//   ３-１） 引数:検索情報. 部店コードの長さ=1 の場合、
		//   引数:検索情報.部店コード[0]  を１）のListに追加する。
		//   ３-２） 引数:検索情報. 部店コードの長さ > 1の場合、<BR>
		//   引数:検索情報.部店コード配列の全ての要素を１）のListに追加する。
		// 　@引数:検索情報.部店コードの各要素 を１）のListに追加する。
    	if (l_request.branchCode.length == 1)
    	{
        	l_lisQueryContainers.add(l_request.branchCode[0]);
        } else if (l_request.branchCode.length > 1)
        {
			for (int i = 0; i < l_request.branchCode.length; i++)
			{
				l_lisQueryContainers.add(l_request.branchCode[i]);
			}
        }
        

        //４）顧客コード
        //   引数:検索情報.顧客コード != nullの場合
        //   引数:検索情報.顧客コード を１）のListに追加する。
        if (l_request.acceptCode != null)
        {
            l_lisQueryContainers.add(l_request.acceptCode);
        }

        //書面区分、書面種類コード
        // 引数:検索情報.書面区分管理一覧の長さ = 1 の場合
        // 書面区分、書面種類コードを１）のListに追加する
        if (l_request.documentDivList.length == 1)
        {
            l_lisQueryContainers.add(l_request.documentDivList[0].documentDiv);
            l_lisQueryContainers.add(l_request.documentDivList[0].documentCategory);
        }

        // 引数:検索情報.書面区分管理一覧の長さ > 1 の場合
        // 配列の全ての要素の 書面区分、書面種類コードを、１）のListに追加する。
        if (l_request.documentDivList.length > 1)
        {
            for (int i = 0; i < l_request.documentDivList.length; i++)
            {
                l_lisQueryContainers.add(l_request.documentDivList[i].documentDiv);
                l_lisQueryContainers.add(l_request.documentDivList[i].documentCategory);
            }
        }

        //７）書面交付日from
        //  引数:検索情報.書面交付日from != nullの場合
        //  引数:検索情報.書面交付日from を１）のListに追加する。
        if (l_request.docuDeliDateFrom != null)
        {
            l_lisQueryContainers.add(l_request.docuDeliDateFrom);
        }

        //８）書面交付日to
        //引数:検索情報.書面交付日to != nullの場合
        //引数:検索情報.書面交付日to を１）のListに追加する。
        if (l_request.docuDeliDateTo != null)
        {
            l_lisQueryContainers.add(l_request.docuDeliDateTo);
        }

        //９）生成されたListから配列を取得し、返却する。
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (createソートキー)<BR>
     * ソート条件文字列を編集する。<BR>
     * <BR>
     * テーブル列物理名を使用し、以下の通り、<BR>
     * ソート条件文字列（orderby句）を編集する。<BR>
     * <BR>
     * １）引数:ソートキーの要素数分以下の処理を繰り返し、<BR>
     * 　@　@　@ソート条件文字列を作成する。<BR>
     * 　@１－１）ソートキーを編集<BR>
     * 　@　@１－１－１）ソート条件ー = 部店コードの場合<BR>
     * 　@　@　@　@　@　@　@　@書面交付管理テーブル.branch_code<BR>
     * <BR>
     * 　@　@１－１－２）ソート条件ー = 顧客コードの場合<BR>
     * 　@　@　@　@　@　@　@　@書面交付管理テーブル.account_code<BR>
     * <BR>
     * 　@　@１－１－３）ソート条件 = 書面交付日の場合<BR>
     * 　@　@　@　@　@　@　@　@書面交付管理テーブル.delivery_date<BR>
     * <BR>
     * 　@　@１－１－４）ソート条件 = 書面種類 の場合<BR>
     * 　@　@　@　@　@　@　@　@書面交付管理テーブル.document_category<BR>
     * <BR>
     * 　@　@１－１－５）ソート条件 = 電子鳩銘柄コード の場合<BR>
     * 　@　@　@　@　@　@　@　@書面交付管理テーブル.product_code<BR>
     * <BR>
     * 　@　@１－１－６）ソート条件 = みなし交付日 の場合<BR> 
     * 　@　@　@　@　@　@　@　@書面交付管理テーブル.deemed_delivery_date <BR>
     * <BR>
     * 　@１－２）ソート条件に該当するソート条件を編集する。<BR>
     * 　@　@　@　@　@昇順：asc<BR>
     * 　@　@　@　@　@降順：desc<BR>
     * <BR>
     * 　@　@※１－１－６)みなし交付日が該当する場合ソート条件は以下設定とすること。<BR>
     * 　@　@　@　@　@昇順：asc nulls first<BR>
     * 　@　@　@　@　@降順：desc nulls last<BR>
     * <BR>
     * ２） 作成したソート条件文字列を返却する。<BR>
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキー<BR>
     * @@return String
     * @@roseuid 46F206EF0287
     */
    private String createSortKey(WEB3AdminFPTSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = " createSortKey(WEB3AdminFPTSortKey)";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortKey = new StringBuffer();

        //１）引数:ソートキーの要素数分以下の処理を繰り返し、ソート条件文字列を作成する。
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //１－１）ソートキーを編集
            if (WEB3AdminFPTSortKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //１－１－１）ソート条件ー = 部店コードの場合
                //  書面交付管理テーブル.branch_code
                l_sbSortKey.append(" branch_code ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.ACCEPT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //１－１－２）ソート条件ー = 顧客コードの場合
                //　@　@　@　@書面交付管理テーブル.account_code
                l_sbSortKey.append(" account_code ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.DOCU_DELI_DATE.equals(l_sortKeys[i].keyItem))
            {
                //１－１－３）ソート条件 = 書面交付日の場合
                //　@　@　@　@　@書面交付管理テーブル.delivery_date
                l_sbSortKey.append(" delivery_date ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.DOCUMENT_CATEGORY.equals(l_sortKeys[i].keyItem))
            {
                //ソート条件 = 書面種類 の場合
                // 書面交付管理テーブル.document_category
                l_sbSortKey.append(" document_category ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //ソート条件 = 電子鳩銘柄コード の場合
                // 書面交付管理テーブル.product_code
                l_sbSortKey.append(" product_code ");
            }
            else if (WEB3AdminFPTSortKeyItemDef.DEEMED_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                //ソート条件 = みなし交付日 の場合
                //書面交付管理テーブル.deemed_delivery_date
                l_sbSortKey.append(" deemed_delivery_date ");
            }
            else
            {
                continue;
            }

            //１－２）ソート条件に該当するソート条件を編集する。
            //　@　@　@昇順：asc
            //　@　@　@降順：desc
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                if (WEB3AdminFPTSortKeyItemDef.DEEMED_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
                {
                    //みなし交付日が該当する場合ソート条件は以下設定とすること
                    //　@昇順：asc nulls first
                    l_sbSortKey.append(" ASC NULLS FIRST ");
                }
                else
                {
                    l_sbSortKey.append(" ASC ");
                }
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                if (WEB3AdminFPTSortKeyItemDef.DEEMED_DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
                {
                    //みなし交付日が該当する場合ソート条件は以下設定とすること
                    //　@降順：desc nulls last
                    l_sbSortKey.append(" DESC NULLS LAST ");
                }
                else
                {
                    l_sbSortKey.append(" DESC ");
                }
            }

            l_sbSortKey.append(" , ");
        }

        String l_strSortKey = l_sbSortKey.toString();

        if (!WEB3StringTypeUtility.isEmpty(l_strSortKey))
        {
            l_strSortKey = l_strSortKey.substring(0, l_strSortKey.length() - 2);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSortKey;
    }

    /**
     * (create金商法@交付閲覧情報)<BR>
     * 金商法@交付閲覧履歴情報インスタンスを生成する。<BR>
     * <BR>
     * <BR>
     * １）　@金商法@交付閲覧履歴情報インスタンスを生成<BR>
     * <BR>
     * ２）　@書面チェック区分の取得<BR>
     * 　@２－１）　@書面区分管理クラスオブジェクトの生成<BR>
     * 　@[引数]<BR>
     * 　@証券会社コード：引数:書面交付管理Params.証券会社コード<BR>
     * 　@部店コード：引数:書面交付管理Params.部店コード<BR>
     * 　@書面区分：引数:書面交付管理Params.書面区分<BR>
     * 　@書面種類コード：引数:書面交付管理Params.書面種類コード<BR>
     * <BR>
     * 　@２－２）　@get書面区分管理テーブル行（）で書面区分管理テーブル行を取得<BR>
     * <BR>
     * ３）　@以下のように、担保銘柄登録情報インスタンスに値をセットする。<BR>
     * <BR>
     * 　@３－１）　@金商法@交付閲覧履歴情報.部店コード =<BR>
     * 　@　@引数:書面交付管理Params.部店コード<BR>
     * <BR>
     * 　@３－２）　@金商法@交付閲覧履歴情報.顧客コード =<BR>
     * 　@　@拡張アカウントマネージャ.get顧客（）.get表示顧客コード()<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@[get顧客（）の引数]<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@証券会社コード：<BR>
     * 　@　@　@　@　@　@　@　@　@引数:書面交付管理Params.証券会社コード<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@部店コード：<BR>
     * 　@　@　@　@　@　@　@　@　@引数:書面交付管理Params.部店コード<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@顧客コード：<BR>
     * 　@　@　@　@　@　@　@　@　@引数:書面交付管理Params.顧客コード<BR>
     * <BR>
     * 　@３－３）　@金商法@交付閲覧履歴情報.顧客名 =<BR>
     * 　@　@拡張アカウントマネージャ.get顧客（）.get顧客表示名()<BR>
     * <BR>
     * 　@３－４）　@金商法@交付閲覧履歴情報.銘柄名 = ""<BR>
     * <BR>
     * 　@３－５）　@金商法@交付閲覧履歴情報.書面区分 =<BR>
     * 　@　@引数:書面交付管理Params.書面区分<BR>
     * <BR>
     * 　@３－６）　@金商法@交付閲覧履歴情報.書面名称 =<BR>
     * 　@　@２）で取得した値.get書面名称（）<BR>
     * <BR>
     * 　@３－７）　@金商法@交付閲覧履歴情報.電子鳩銘柄コード =<BR>
     * 　@　@引数:書面交付管理Params.銘柄コード<BR>
     * <BR>
     * 　@３－８）　@金商法@交付閲覧履歴情報.書面種類コード =<BR>
     * 　@　@引数:書面交付管理Params.書面種類コード<BR>
     * <BR>
     * 　@３－９）　@金商法@交付閲覧履歴情報.書面種類名称 =<BR>
     * 　@　@書面種類管理#.get書面種類名称() の戻り値<BR>
     * 　@　@　@　@[書面種類管理()の引数]<BR>
     * 　@　@　@　@　@証券会社コード ： 引数:書面交付管理Params.証券会社コード<BR>
     * 　@　@　@　@　@部店コード ： 引数:書面交付管理Params.部店コード<BR>
     * 　@　@　@　@　@書面種類コード ： 引数:書面交付管理Params.書面種類コード<BR>
     * <BR>
     * 　@３－１０）　@金商法@交付閲覧履歴情報.書面種類通番 =<BR>
     * 　@　@（引数:書面交付管理Params.銘柄コード)の右4桁<BR>
     * <BR>
     * 　@３－１１）　@金商法@交付閲覧履歴情報.書面交付日 =<BR>
     * 　@　@引数:書面交付管理Params.書面交付日<BR>
     * <BR>
     * 　@３－１２）　@金商法@交付閲覧履歴情報.削除フラグ =<BR>
     * 　@　@引数:書面交付管理Params.削除フラグ<BR>
     * <BR>
     * 　@３－１３）　@金商法@交付閲覧履歴情報.更新者コード =<BR>
     * 　@　@引数:書面交付管理Params.更新者コード<BR>
     * <BR>
     * 　@３－１４）　@金商法@交付閲覧履歴情報.作成日付 =<BR>
     * 　@　@引数:書面交付管理Params.作成日付<BR>
     * <BR>
     * 　@３－１５）　@金商法@交付閲覧履歴情報.更新日付 =<BR>
     * 　@　@引数:書面交付管理Params.更新日付<BR>
     * <BR>
     * 　@３－１６）　@金商法@交付閲覧履歴情報.みなし交付日 =<BR>
     * 　@　@引数:書面交付管理Params.みなし交付日<BR>
     * <BR>
     * <BR>
     * ４）　@担保銘柄登録情報インスタンスを返却する。<BR>
     * <BR>
     * @@param l_docDeliveryManagementParams - (書面交付管理Paramsオブジェクト)<BR>
     * 書面交付管理Paramsオブジェクト<BR>
     * @@param l_institution - (証券会社オブジェクト)<BR>
     * 証券会社オブジェクト<BR>
     * @@return WEB3FPTHistoryInfoUnit
     * @@throws WEB3BaseException
     * @@roseuid 46F35CAC02D5
     */
    private WEB3FPTHistoryInfoUnit createHistoryInfoUnit(
        DocDeliveryManagementParams l_docDeliveryManagementParams,
        WEB3GentradeInstitution l_institution) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createHistoryInfoUnit(DocDeliveryManagementParams, WEB3GentradeInstitution)";
        log.entering(STR_METHOD_NAME);

        if (l_docDeliveryManagementParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@金商法@交付閲覧履歴情報インスタンスを生成
        WEB3FPTHistoryInfoUnit l_fPTHistoryInfoUnit = new WEB3FPTHistoryInfoUnit();

        //２）　@書面チェック区分の取得
        //２－１）　@書面区分管理クラスオブジェクトの生成
        WEB3AdminFPTDocDivManagement l_adminDocAdminFPTDocDivManagement =
            new WEB3AdminFPTDocDivManagement(
                l_docDeliveryManagementParams.getInstitutionCode(),
                l_docDeliveryManagementParams.getBranchCode(),
                l_docDeliveryManagementParams.getDocumentDiv(),
                l_docDeliveryManagementParams.getDocumentCategory());

        //２－２）　@get書面区分管理テーブル行（）で書面区分管理テーブル行を取得
        DocDivManagementRow l_docDivManagementRow = l_adminDocAdminFPTDocDivManagement.getDocDivManagementParams();

        //３）　@以下のように、担保銘柄登録情報インスタンスに値をセットする。
        //３－１）　@金商法@交付閲覧履歴情報.部店コード = 引数:書面交付管理Params.部店コード
        l_fPTHistoryInfoUnit.branchCode = l_docDeliveryManagementParams.getBranchCode();

        //３－２）　@金商法@交付閲覧履歴情報.顧客コード = 拡張アカウントマネージャ.get顧客（）.get表示顧客コード()
		FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
		WEB3GentradeAccountManager l_accountMananger =
			(WEB3GentradeAccountManager)l_finApp.getAccountManager();
		 WEB3GentradeMainAccount l_mainAccount= l_accountMananger.getMainAccount(
			l_docDeliveryManagementParams.getInstitutionCode(),
			l_docDeliveryManagementParams.getBranchCode(),
			l_docDeliveryManagementParams.getAccountCode());
        l_fPTHistoryInfoUnit.acceptCode = l_mainAccount.getDisplayAccountCode();

        //３－３）　@金商法@交付閲覧履歴情報.顧客名 = 拡張アカウントマネージャ.get顧客（）.get顧客表示名()

		l_fPTHistoryInfoUnit.acceptName = l_mainAccount.getDisplayAccountName();

        //３－４）　@金商法@交付閲覧履歴情報.電子鳩銘柄コード = 引数:書面交付管理Params.銘柄コード
        l_fPTHistoryInfoUnit.batoProductCode = l_docDeliveryManagementParams.getProductCode();

        //３－５）　@金商法@交付閲覧履歴情報.銘柄名
//        WEB3AdminFPTCommon l_adminDocAdminFPTCommon = new WEB3AdminFPTCommon();
//        l_fPTHistoryInfoUnit.productName = l_adminDocAdminFPTCommon.getProductName(
//            l_institution,
//            l_docDeliveryManagementParams.getProductCode(),
//            l_docDivManagementRow.getDocumentCheckDiv());
		l_fPTHistoryInfoUnit.productName = "";

        //３－６）　@金商法@交付閲覧履歴情報.書面区分 = 引数:書面交付管理Params.書面区分
        l_fPTHistoryInfoUnit.documentDiv = l_docDeliveryManagementParams.getDocumentDiv();

        //３－７）　@金商法@交付閲覧履歴情報.書面名称 = ２）で取得した値.get書面名称（）
        l_fPTHistoryInfoUnit.documentNames = l_docDivManagementRow.getDocumentName();

        //金商法@交付閲覧履歴情報.書面種類コード = 引数:書面交付管理Params.書面種類コード
        l_fPTHistoryInfoUnit.documentCategory = l_docDeliveryManagementParams.getDocumentCategory();

        //金商法@交付閲覧履歴情報.書面種類名称 = 書面種類管理#.get書面種類名称() の戻り値
        // [書面種類管理()の引数]
        //   証券会社コード ： 引数:書面交付管理Params.証券会社コード
        //   部店コード ： 引数:書面交付管理Params.部店コード
        //   書面種類コード ： 引数:書面交付管理Params.書面種類コード
        WEB3AdminFPTDocCategoryManagement  l_docCategoryManagement =
            new WEB3AdminFPTDocCategoryManagement(
                l_docDeliveryManagementParams.getInstitutionCode(),
                new String[]{l_docDeliveryManagementParams.getBranchCode()},
                l_docDeliveryManagementParams.getDocumentCategory());
        l_fPTHistoryInfoUnit.documentCategoryName = l_docCategoryManagement.getDocumentCateName();

        //金商法@交付閲覧履歴情報.書面種類通番 = （引数:書面交付管理Params.銘柄コード)の右4桁
        String l_strProductCode = l_docDeliveryManagementParams.getProductCode();
        l_fPTHistoryInfoUnit.documentCategoryNumber =
            l_strProductCode.substring(l_strProductCode.length() - 4);

        //３－８）　@金商法@交付閲覧履歴情報.書面交付日 = 引数:書面交付管理Params.書面交付日
        l_fPTHistoryInfoUnit.docuDeliDate = l_docDeliveryManagementParams.getDeliveryDate();

        //３－９）　@金商法@交付閲覧履歴情報.削除フラグ = 引数:書面交付管理Params.削除フラグ
        l_fPTHistoryInfoUnit.deleteFlg = l_docDeliveryManagementParams.getDeleteFlag().intValue() + "";

        //３－１０）　@金商法@交付閲覧履歴情報.更新者コード = 引数:書面交付管理Params.更新者コード
        l_fPTHistoryInfoUnit.updaterCode = l_docDeliveryManagementParams.getLastUpdater();

        //３－１１）　@金商法@交付閲覧履歴情報.作成日付 = 引数:書面交付管理Params.作成日付
        l_fPTHistoryInfoUnit.createDate = l_docDeliveryManagementParams.getCreatedTimestamp();

        //３－１２）　@金商法@交付閲覧履歴情報.更新日付 = 引数:書面交付管理Params.更新日付
        l_fPTHistoryInfoUnit.updateTimeStamp = l_docDeliveryManagementParams.getLastUpdatedTimestamp();

        //金商法@交付閲覧履歴情報.みなし交付日 = 引数:書面交付管理Params.みなし交付日
        l_fPTHistoryInfoUnit.deemedDeliveryDate = l_docDeliveryManagementParams.getDeemedDeliveryDate();

        log.exiting(STR_METHOD_NAME);
        return l_fPTHistoryInfoUnit;
    }

    /**
     * (validate検索条件)<BR>
     * 検索条件の整合性チェックを行う。<BR>
     * <BR>
     * １） 引数.検索情報.書面区分管理一覧 の長さ > 0 の場合、長さ回数LOOP処理<BR>
     * <BR>
     * 　@１-１） 書面種類コードチェック<BR>
     * 　@　@１-１-１） 引数.検索情報.書面区分管理一覧[index].書面種類コード<BR>
     * 　@　@　@　@!= null の場合<BR>
     * 　@　@　@　@　@　@　@　@書面種類管理オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@　@　@[書面種類管理()に指定する引数]<BR>
     * 　@　@　@　@　@　@証券会社コード： 引数.証券会社コード<BR>
     * 　@　@　@　@　@　@部店コード： 引数.検索情報.部店コード<BR>
     * 　@　@　@　@　@　@書面種類コード： 引数.検索情報.書面区分管理一覧[index]<BR>
     * 　@　@　@　@　@　@.書面種類コード<BR>
     * <BR>
     * 　@　@１-１-２） 書面種類管理#is書面種類コード()をコールする。<BR>
     * 　@　@　@　@　@　@　@　@戻り値が false の場合は、<BR>
     * 　@　@　@　@「書面種類コードが不正です。」例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02997<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * 証券会社コード
     * @@param l_request - (検索情報)
     * 検索情報
     * @@throws WEB3BaseException
     */
    private void validateQueryCondition(
        String l_strInstitutionCode,
        WEB3AdminFPTListReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateQueryCondition(String, WEB3AdminFPTListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        //引数.検索情報.書面区分管理一覧 の長さ > 0 の場合、長さ回数LOOP処理
        int l_intCnt = 0;
        if (l_request.documentDivList != null)
        {
            l_intCnt = l_request.documentDivList.length;
        }

        for (int i = 0; i < l_intCnt; i++)
        {
            //書面種類コードチェック
            //引数.検索情報.書面区分管理一覧[index].書面種類コード != null の場合
            //書面種類管理オブジェクトを生成する。
            // [書面種類管理()に指定する引数]
            //   証券会社コード： 引数.証券会社コード
            //   部店コード： 引数.検索情報.部店コード
            //   書面種類コード： 引数.検索情報.書面区分管理一覧[index].書面種類コード
            if (l_request.documentDivList[i] != null)
            {
                WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                    new WEB3AdminFPTDocCategoryManagement(
                        l_strInstitutionCode,
                        l_request.branchCode,
                        l_request.documentDivList[i].documentCategory);

                // 書面種類管理#is書面種類コード()をコールする。
                //戻り値が false の場合は、「書面種類コードが不正です。」例外をスローする。
                if (!l_docCategoryManagement.isDocumentCategory())
                {
                    log.debug("書面種類コードが不正です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "書面種類コードが不正です。");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
