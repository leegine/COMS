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
filename	WEB3AdminFPTDocumentListReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付書面照会サービスImpl(WEB3AdminFPTDocumentListReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.040
*/
package webbroker3.docadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.docadmin.WEB3AdminFPTBatoProductCodeManagement;
import webbroker3.docadmin.WEB3AdminFPTDocCategoryManagement;
import webbroker3.docadmin.WEB3AdminFPTDocDivManagement;
import webbroker3.docadmin.define.WEB3AdminFPTSortKeyItemDef;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTSortKey;
import webbroker3.docadmin.message.WEB3FPTDocumentDivAdminInfoUnit;
import webbroker3.docadmin.message.WEB3FPTDocumentUpdateInfoUnit;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentListReferenceService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.BatoProductManagementParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者金商法@交付書面照会サービスImpl)<BR>
 * 管理者金商法@交付書面照会サービス実装クラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentListReferenceServiceImpl implements WEB3AdminFPTDocumentListReferenceService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListReferenceServiceImpl.class);

    /**
     * @@roseuid 47CBC5AD0177
     */
    public WEB3AdminFPTDocumentListReferenceServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * 金商法@交付書面照会処理を実施する。<BR>
     * <BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。<BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付書面照会入力リクエストの場合<BR>
     * 　@－get交付書面照会検索入力()をコールする。  <BR>
     * ○ 引数のリクエストデータが、管理者金商法@交付書面照会一覧リクエストの場合<BR>
     * 　@－get交付書面照会一覧()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C26233001F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        //引数のリクエストデータが、管理者金商法@交付書面照会入力リクエストの場合
        if (l_request instanceof WEB3AdminFPTDocumentListSearchInputRequest)
        {
            l_response = this.getDocumentReferenceSearchInput(
                (WEB3AdminFPTDocumentListSearchInputRequest)l_request);
        }
        //引数のリクエストデータが、管理者金商法@交付書面照会一覧リクエストの場合
        else if (l_request instanceof WEB3AdminFPTDocumentListReferenceRequest)
        {
            l_response = this.getDocumentReferenceList(
                (WEB3AdminFPTDocumentListReferenceRequest)l_request);
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
     * (get交付書面照会検索入力)<BR>
     * 金商法@交付書面照会検索入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「get交付書面照会検索入力」参照。 <BR>
     * @@param l_request - 管理者金商法@交付書面照会検索入力リクエストデータオブジェクト
     * @@return WEB3AdminFPTDocumentListSearchInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C3E19501B7
     */
    protected WEB3AdminFPTDocumentListSearchInputResponse getDocumentReferenceSearchInput(
        WEB3AdminFPTDocumentListSearchInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentReferenceSearchInput(WEB3AdminFPTDocumentListSearchInputRequest)";

        log.entering(STR_METHOD_NAME);

        //getInstanceFromログイン情報( )
        // ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："G0104"(金商法@交付書面更新照会)
        //is更新：false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, false);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get部店コード( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //書面区分管理(String, String, String, String)
        //[指定する引数]
        // 証券会社コード ： 管理者.get証券会社コード() の戻り値
        // 部店コード ： 管理者.get部店コード() の戻り値
        // 書面区分 ： null
        // 書面種類コード ： null
        WEB3AdminFPTDocDivManagement l_adminFPTDocDivManagement =
            new WEB3AdminFPTDocDivManagement(l_strInstitutionCode, l_strBranchCode, null, null);

        //get書面区分管理一覧()
        WEB3FPTDocumentDivAdminInfoUnit[] l_fptDocumentDivAdminInfoUnits =
            l_adminFPTDocDivManagement.getDocDivManagementLists();

        WEB3AdminFPTDocumentListSearchInputResponse l_response =
            (WEB3AdminFPTDocumentListSearchInputResponse)l_request.createResponse();

        //プロパティセット
        l_response.documentDivList = l_fptDocumentDivAdminInfoUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get交付書面照会一覧)<BR>
     * 金商法@交付書面照会一覧画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「get交付書面照会一覧」参照。 <BR>
     * ======================================================== <BR>
     * 具体位置：検索対象レコードが存在しない<BR>
     * （get電子鳩銘柄コード管理一覧()の戻り値Listの長さ == 0）場合、例外をスローする。<BR>
     * 　@　@class: WEB3BaseException <BR>
     * 　@　@tag　@: BUSINESS_ERROR_00398 <BR>
     * ======================================================== <BR>
     * @@param l_request - 管理者金商法@交付書面照会一覧リクエストデータオブジェクト
     * @@return WEB3AdminFPTDocumentListReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 47C3E29200A1
     */
    protected WEB3AdminFPTDocumentListReferenceResponse getDocumentReferenceList(
        WEB3AdminFPTDocumentListReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDocumentReferenceList(WEB3AdminFPTDocumentListReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        //リクエストデータの整合性チェックを行う。
        l_request.validate();

        //getInstanceFromログイン情報( )
        // ログイン情報より、管理者オブジェクトを取得する。
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //validate権限(機@能カテゴリコード : String, is更新 : boolean)
        //[validate権限（）に指定する引数]
        //機@能カテゴリコード："G0104"(金商法@交付書面更新照会)
        //is更新：false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.FPT_UPLOAD, false);

        //get証券会社コード( )
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // get部店コード( )
        String l_strBranchCode = l_administrator.getBranchCode();

        //create検索条件文字列
        //取得条件の文字列を生成する。
        //[引数]
        //書面区分管理一覧： リクエストデータ.書面区分管理一覧
        String l_strQueryString = this.createQueryString(l_request.documentDivList);

        //create検索条件データコンテナ
        //取得条件にセットする値の配列を生成する。
        //[引数]
        //証券会社コード： get証券会社コード（）の戻り値
        //部店コード： get部店コード（）の戻り値
        //書面区分管理一覧： リクエストデータ.書面区分管理一覧
        Object[] l_objQueryDataContainers = this.createQueryDataContainer(
            l_strInstitutionCode,
            l_strBranchCode,
            l_request.documentDivList);

        // createソートキー
        //ソート条件文字列を編集する。
        //[引数]
        //ソートキー： リクエストデータ.ソートキー
        String l_strSortKeys = this.createSortKeys(l_request.sortKeys);

        //get電子鳩銘柄コード管理一覧
        //電子鳩銘柄コード管理テーブルに検索を行う。
        //[指定する引数]
        //  検索文字列： create検索文字列() の戻り値
        //  検索データコンテナ： create検索データコンテナ() の戻り値
        //  ソートキー： createソートキー() の戻り値
        List l_lisBatoProductManagement = WEB3AdminFPTBatoProductCodeManagement.getBatoProductManagementList(
            l_strQueryString,
            l_objQueryDataContainers,
            l_strSortKeys);

        //   検索対象レコードが存在しない（get電子鳩銘柄コード管理一覧()の戻り値Listの長さ == 0）場合、
        //   「該当するデータが存在しません」
        //   (BUSINESS_ERROR_00398)例外をスローする。
        if (l_lisBatoProductManagement == null || l_lisBatoProductManagement.size() == 0)
        {
            log.debug("該当するデータが存在しません。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当するデータが存在しません。");
        }

        //create書面一覧
        //   書面種類一覧を作成する。
        //   [引数]
        //   電子鳩銘柄コード管理行： 電子鳩銘柄コード管理.get電子鳩銘柄コード管理一覧() の戻り値
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit =
            this.createDocumentList(l_lisBatoProductManagement);

        WEB3AdminFPTDocumentListReferenceResponse l_response =
            (WEB3AdminFPTDocumentListReferenceResponse)l_request.createResponse();

        //プロパティセット
        l_response.documentCategoryList = l_documentUpdateInfoUnit;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (createソートキー)<BR>
     * ソート条件文字列を編集する。<BR>
     * <BR>
     * テーブル列物理名を使用し、以下の通り、ソート条件文字列（orderby句）を編集する。<BR>
     * <BR>
     * １）引数:ソートキーの要素数分以下の処理を繰り返し、ソート条件文字列を作成する。<BR>
     * <BR>
     * <BR>
     * 　@１－１）昇順ソートの場合<BR>
     * <BR>
     * 　@　@１－１－１）ソート条件ー = 電子鳩銘柄コード の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@substr(電子鳩銘柄コード管理テーブル.bato_product_code,1,3)ASC,<BR>
     * 　@　@　@　@　@　@　@substr(電子鳩銘柄コード管理テーブル.bato_product_code,4,4)DESC<BR>
     * <BR>
     * <BR>
     * 　@　@１－１－２）ソート条件ー = 有効区分の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@電子鳩銘柄コード管理テーブル.valid_flag ASC<BR>
     * <BR>
     * <BR>
     * 　@１－２）降順ソートの場合 <BR>
     * <BR>
     * 　@　@１－２－１）ソート条件ー = 電子鳩銘柄コード の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@　@substr(電子鳩銘柄コード管理テーブル.bato_product_code,1,3)DESC,<BR>
     * 　@　@　@　@　@　@　@substr(電子鳩銘柄コード管理テーブル.bato_product_code,4,4) ASC<BR>
     * <BR>
     * <BR>
     * 　@　@１－２－２）ソート条件ー = 有効区分の場合<BR>
     * 　@　@　@　@　@　@　@電子鳩銘柄コード管理テーブル.valid_flag DESC<BR>
     * <BR>
     * <BR>
     * ２） 作成したソート条件文字列を返却する。  <BR>
     * @@param l_sortKeys - ソートキー
     * @@return String
     * @@roseuid 47C3E7630361
     */
    private String createSortKeys(WEB3AdminFPTSortKey[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortKeys(WEB3AdminFPTSortKey[])";

        log.entering(STR_METHOD_NAME);

        StringBuffer l_sbSortKey = new StringBuffer();

        //１）引数:ソートキーの要素数分以下の処理を繰り返し、ソート条件文字列を作成する。
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //１－１）昇順ソートの場合
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                //ソート条件ー = 電子鳩銘柄コード の場合
                //substr(電子鳩銘柄コード管理テーブル.bato_product_code,1,3)ASC,
                //substr(電子鳩銘柄コード管理テーブル.bato_product_code,4,4)DESC
                if (WEB3AdminFPTSortKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortKey.append(" substr(bato_product_code,1,3) ASC, substr(bato_product_code,4,4) DESC ");
                }
                //１－１－２）ソート条件ー = 有効区分の場合
                //電子鳩銘柄コード管理テーブル.valid_flag ASC
                if (WEB3AdminFPTSortKeyItemDef.VALID_FLAG.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortKey.append(" valid_flag ASC ");
                }
            }
            //１－２）降順ソートの場合
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                //１－２－１）ソート条件ー = 電子鳩銘柄コード の場合<
                //substr(電子鳩銘柄コード管理テーブル.bato_product_code,1,3)DESC,
                //substr(電子鳩銘柄コード管理テーブル.bato_product_code,4,4) ASC
                if (WEB3AdminFPTSortKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortKey.append(" substr(bato_product_code,1,3) DESC, substr(bato_product_code,4,4) ASC ");
                }
                //１－２－２）ソート条件ー = 有効区分の場合<BR>
                //電子鳩銘柄コード管理テーブル.valid_flag DESC<BR>
                if (WEB3AdminFPTSortKeyItemDef.VALID_FLAG.equals(l_sortKeys[i].keyItem))
                {
                    l_sbSortKey.append(" valid_flag DESC ");
                }
            }

            l_sbSortKey.append(" , ");
        }
        //作成したソート条件文字列を返却する。
        String l_strSortKey = l_sbSortKey.toString();

        if (!WEB3StringTypeUtility.isEmpty(l_strSortKey))
        {
            l_strSortKey = l_strSortKey.substring(0, l_strSortKey.length() - 2);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSortKey;
    }

    /**
     * (create書面一覧)<BR>
     * 書面一覧を作成する。<BR>
     * <BR>
     * １） 返却用ArrayListを作成する。<BR>
     * <BR>
     * <BR>
     * ２） 引数.電子鳩銘柄コード管理行の長さ回数Loopを行う。（インデックス：index）<BR>
     * <BR>
     * 　@２-１） 書面更新情報インスタンスを生成し、以下をセットする。<BR>
     * <BR>
     * 　@　@　@　@　@書面区分 = 引数.電子鳩銘柄コード管理行[index].get書面区分()<BR>
     * 　@　@　@　@　@書面種類コード = <BR>
     * 　@　@　@　@　@　@　@引数.電子鳩銘柄コード管理行[index].get電子鳩銘柄コード() の左３桁<BR>
     * 　@　@　@　@　@書面通番 = 引数.電子鳩銘柄コード管理行[index].get電子鳩銘柄コード()の右４桁<BR>
     * 　@　@　@　@　@有効区分 = 引数.電子鳩銘柄コード管理行[index].get有効区分()<BR>
     * 　@　@　@　@　@摘要 = 引数.電子鳩銘柄コード管理行[index].get摘要()<BR>
     * 　@　@　@　@　@電子鳩銘柄コード = 引数.電子鳩銘柄コード管理行[index].get電子鳩銘柄コード()<BR>
     * <BR>
     * 　@２-２） 書面種類名称を取得する。<BR>
     * <BR>
     * 　@　@２-２-１） 書面種類名称オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@証券会社コード：引数.電子鳩銘柄コード管理行[index].get証券会社コード<BR>
     * 　@　@　@　@　@部店コード： 引数.電子鳩銘柄コード管理行[index].get部店コード<BR>
     * 　@　@　@　@　@書面種類コード：引数.電子鳩銘柄コード管理行[index].get電子鳩銘柄コード() の左３桁<BR>
     * <BR>
     * 　@　@２-２-２） 書面更新情報.書面種類名称をセットする。<BR>
     * <BR>
     * 　@　@　@　@　@書面種類名称 = 書面種類名称.get書面種類名称() の戻り値<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@※戻り値がnullの場合はnullをセットする。<BR>
     * <BR>
     * 　@２-３） 書面更新情報インスタンスをArrayListへ追加する。<BR>
     * <BR>
     * ３） ArrrayListを書面更新情報[]に変換し、返却する。<BR>
     * @@param l_lisBatoProductCodeManagementParams - 電子鳩銘柄コード管理行
     * @@return WEB3FPTDocumentUpdateInfoUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 47C3EC2601BB
     */
    private WEB3FPTDocumentUpdateInfoUnit[] createDocumentList(List l_lisBatoProductCodeManagementParams)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createDocumentList(List)";

        log.entering(STR_METHOD_NAME);

        //返却用ArrayListを作成する。
        ArrayList l_arrayList = new ArrayList();

        int l_intbatoProductCodeManagementParamsCnt = l_lisBatoProductCodeManagementParams.size();

        for (int i = 0; i < l_intbatoProductCodeManagementParamsCnt; i++)
        {
            BatoProductManagementParams l_batoProductManagementParams =
                (BatoProductManagementParams)l_lisBatoProductCodeManagementParams.get(i);
            //書面更新情報インスタンスを生成し、以下をセットする。
            WEB3FPTDocumentUpdateInfoUnit l_documentUpdateInfoUnit = new WEB3FPTDocumentUpdateInfoUnit();

            //書面区分 = 引数.電子鳩銘柄コード管理行[index].get書面区分()
            l_documentUpdateInfoUnit.documentDiv = l_batoProductManagementParams.getDocumentDiv();

            //書面種類コード = 引数.電子鳩銘柄コード管理行[index].get電子鳩銘柄コード() の左３桁
            l_documentUpdateInfoUnit.documentCategory =
                l_batoProductManagementParams.getBatoProductCode().substring(0, 3);

            //書面通番 = 引数.電子鳩銘柄コード管理行[index].get電子鳩銘柄コード()の右４桁
            int l_intBatoProductCodeLength = l_batoProductManagementParams.getBatoProductCode().length();
            l_documentUpdateInfoUnit.documentNumber = l_batoProductManagementParams.getBatoProductCode().substring(
                l_intBatoProductCodeLength - 4, l_intBatoProductCodeLength);

            //有効区分 = 引数.電子鳩銘柄コード管理行[index].get有効区分()
            l_documentUpdateInfoUnit.validFlag = l_batoProductManagementParams.getValidFlag();

            //摘要 = 引数.電子鳩銘柄コード管理行[index].get摘要()
            l_documentUpdateInfoUnit.remarks = l_batoProductManagementParams.getRemarks();

            //電子鳩銘柄コード = 引数.電子鳩銘柄コード管理行[index].get電子鳩銘柄コード()
            l_documentUpdateInfoUnit.batoProductCode = l_batoProductManagementParams.getBatoProductCode();

            //２-２） 書面種類名称を取得する。
            //２-２-１） 書面種類名称オブジェクトを生成する。
            //[引数]
            //証券会社コード：引数.電子鳩銘柄コード管理行[index].get証券会社コード
            //部店コード： 引数.電子鳩銘柄コード管理行[index].get部店コード
            //書面種類コード：引数.電子鳩銘柄コード管理行[index].get電子鳩銘柄コード() の左３桁
            String[] l_strBranchCodes = new String[1];
            l_strBranchCodes[0] = l_batoProductManagementParams.getBranchCode();
            WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
                new WEB3AdminFPTDocCategoryManagement(
                    l_batoProductManagementParams.getInstitutionCode(),
                    l_strBranchCodes,
                    l_batoProductManagementParams.getBatoProductCode().substring(0, 3));

            //２-２-２） 書面更新情報.書面種類名称をセットする。
            //書面種類名称 = 書面種類名称.get書面種類名称() の戻り値
            //※戻り値がnullの場合はnullをセットする。
            l_documentUpdateInfoUnit.documentCategoryName = l_docCategoryManagement.getDocumentCateName();

            //書面更新情報インスタンスをArrayListへ追加する。
            l_arrayList.add(l_documentUpdateInfoUnit);
        }

        //ArrrayListを書面更新情報[]に変換し、返却する。
        WEB3FPTDocumentUpdateInfoUnit[] l_documentUpdateInfoUnit =
            new WEB3FPTDocumentUpdateInfoUnit[l_arrayList.size()];
        l_arrayList.toArray(l_documentUpdateInfoUnit);

        log.exiting(STR_METHOD_NAME);
        return l_documentUpdateInfoUnit;
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * 取得条件にセットする値の配列を生成する。 <BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード <BR>
     * <BR>
     * 　@　@引数.証券会社コード を１）のListに追加する。<BR>
     * <BR>
     * ３）部店コード <BR>
     * <BR>
     * 　@　@引数.部店コード を１）のListに追加する。<BR>
     * <BR>
     * ４） 書面区分、書面種類コード<BR>
     * 　@４-１） 引数.書面区分管理一覧の長さ == 1 の場合、<BR>
     * 　@　@　@書面区分管理一覧.書面区分、書面区分管理一覧.書面種類コードを１）のListに追加する。<BR>
     * <BR>
     * 　@４-２） 引数.書面区分管理一覧の長さ > 1 の場合、<BR>
     * 　@　@　@配列の全ての要素の 書面区分、書面種類コードを、１）のListに追加する。<BR>
     * <BR>
     * ５）生成されたListから配列を取得し、返却する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_documentDivLists - 書面区分管理一覧
     * @@return Object[]
     * @@roseuid 47C3F696036B
     */
    private Object[] createQueryDataContainer(String l_strInstitutionCode,
        String l_strBranchCode,
        WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists)
    {
        final String STR_METHOD_NAME = "createQueryDataContainer(String, String, WEB3FPTDocumentDivAdminInfoUnit[])";
        log.entering(STR_METHOD_NAME);

        //１）空のArrayListを生成する。
        List l_lisQueryContainers = new ArrayList();

        //２）証券会社コード
        //引数.証券会社コード を１）のListに追加する。
        l_lisQueryContainers.add(l_strInstitutionCode);

        //３）部店コード
        //引数.部店コード を１）のListに追加する。
        l_lisQueryContainers.add(l_strBranchCode);

        //４） 書面区分、書面種類コード
        //４-１） 引数.書面区分管理一覧の長さ == 1 の場合、
        //書面区分管理一覧.書面区分、書面区分管理一覧.書面種類コードを１）のListに追加する。
        if (l_documentDivLists.length == 1)
        {
            l_lisQueryContainers.add(l_documentDivLists[0].documentDiv);
            l_lisQueryContainers.add(l_documentDivLists[0].documentCategory);
        }
        //４-２） 引数.書面区分管理一覧の長さ > 1 の場合、
        //配列の全ての要素の 書面区分、書面種類コードを、１）のListに追加する。
        else if (l_documentDivLists.length > 1)
        {
            for (int i = 0; i < l_documentDivLists.length; i++)
            {
                l_lisQueryContainers.add(l_documentDivLists[i].documentDiv);
                l_lisQueryContainers.add(l_documentDivLists[i].documentCategory);
            }
        }

        //５）生成されたListから配列を取得し、返却する。
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }

    /**
     * (create検索条件文字列)<BR>
     * 取得条件の文字列を生成する。 <BR>
     * <BR>
     * １） 空の文字列を生成する。<BR>
     * <BR>
     * ２） 証券会社コード <BR>
     * <BR>
     * 　@　@　@"institution_code = ? " を１）の文字列に追加する。<BR>
     * <BR>
     * ３） 部店コード<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and branch_code = ? " <BR>
     * <BR>
     * ４） 書面区分・書面種類コード<BR>
     * 　@４-１） 引数.書面区分管理一覧の長さ == 1 の場合、<BR>
     * 　@　@　@書面区分、書面種類コード(電子鳩銘柄コード左３桁)を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and document_div = ? and bato_product_code like ?% " <BR>
     * <BR>
     * 　@４-２） 引数.書面区分管理一覧の長さ > 1 の場合、<BR>
     * 　@　@　@配列の要素数分、書面区分、書面種類コード(電子鳩銘柄コード左３桁)のペアを<BR>
     * 　@　@　@検索条件文字列に追加する。<BR>
     * <BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "and ((document_div = ? and bato_product_code like ?%) or (…))" <BR>
     * <BR>
     * <BR>
     * ５） 作成した検索条件文字列インスタンスを返却する。<BR>
     * @@param l_documentDivLists - 書面区分管理一覧
     * @@return String
     * @@roseuid 47C3F6980243
     */
    private String createQueryString(WEB3FPTDocumentDivAdminInfoUnit[] l_documentDivLists)
    {
        final String STR_METHOD_NAME = "createQueryString(WEB3FPTDocumentDivAdminInfoUnit[])";
        log.entering(STR_METHOD_NAME);

        //１）　@空の文字列を生成する。
        StringBuffer l_sbQueryString = new StringBuffer();

        //２） 証券会社コード "institution_code = ? " を１）の文字列に追加する。
        l_sbQueryString.append(" institution_code = ? ");

        //３） 部店コード 検索条件文字列 += "and branch_code = ? "
        l_sbQueryString.append(" and branch_code = ? ");

        //４） 書面区分・書面種類コード
        //４-１） 引数.書面区分管理一覧の長さ == 1 の場合、
        //書面区分、書面種類コード(電子鳩銘柄コード左３桁)を検索条件文字列に追加する。
        //検索条件文字列 += "and document_div = ? and bato_product_code like ?% " 
        if (l_documentDivLists.length == 1)
        {
            l_sbQueryString.append(" and document_div = ? and bato_product_code like ? || '%' ");
        }
        //４-２） 引数.書面区分管理一覧の長さ > 1 の場合、
        else if (l_documentDivLists.length > 1)
        {
            l_sbQueryString.append(" and ((document_div = ? and bato_product_code like ? || '%') ");
            //配列の要素数分、書面区分、書面種類コード(電子鳩銘柄コード左３桁)のペアを検索条件文字列に追加する。
            //検索条件文字列 += "and ((document_div = ? and bato_product_code like ?%) or (…))"
            for (int i = 1; i < l_documentDivLists.length; i++)
            {
                l_sbQueryString.append(" or (document_div = ? and bato_product_code like ? || '%') ");
            }
            l_sbQueryString.append(")");
        }
        String l_strQueryString = l_sbQueryString.toString();

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
}
@
