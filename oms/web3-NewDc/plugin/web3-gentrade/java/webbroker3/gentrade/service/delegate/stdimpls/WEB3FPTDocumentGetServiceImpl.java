head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.57.32;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentGetServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : 金商法@書面情報取得サービスImplクラス(WEB3FPTDocumentGetServiceImpl.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 劉レイ(北京中訊) 新規作成 仕様変更モデルNo.354
 */
package webbroker3.gentrade.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3DocumentDeliverDivDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocCategoryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.message.WEB3FPTDocumentGetRequest;
import webbroker3.gentrade.message.WEB3FPTDocumentGetResponse;
import webbroker3.gentrade.message.WEB3FPTDocumentInfoUnit;
import webbroker3.gentrade.service.delegate.WEB3FPTDocumentGetService;
import webbroker3.util.WEB3LogUtility;

/**
 * (金商法@書面情報取得サービスImpl)<BR>
 * 金商法@書面情報取得サービスImplクラス<BR>
 * <BR>
 * @@author 劉レイ(北京中訊)
 * @@version 1.0
 */
public class WEB3FPTDocumentGetServiceImpl extends WEB3ClientRequestService
    implements WEB3FPTDocumentGetService
{
    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTDocumentGetServiceImpl.class);

    /**
     * コンストラクタ<BR>
     */
    public WEB3FPTDocumentGetServiceImpl()
    {

    }

    /**
     * (execute)<BR>
     * <BR>
     * 金商法@書面情報取得サービス処理を行う。<BR>
     * <BR>
     * シーケンス図「金商法@書面情報取得」参照。<BR>
     * <BR> 
     * @@param l_request リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
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
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        WEB3FPTDocumentGetRequest l_fptDocumentGetRequest = null;

        if (l_request instanceof WEB3FPTDocumentGetRequest)
        {
            l_fptDocumentGetRequest =
                (WEB3FPTDocumentGetRequest)l_request;

            l_fptDocumentGetRequest.validate();
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }

        //get口座( )
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)this.getMainAccount();

        //書面種類を取得する
        //書面種類管理テーブルを下記条件で検索する。
        //[条件]
        //証券会社コード = 顧客.証券会社コード
        //部店コード = 顧客.部店コード
        //交付対象 = リクエスト.交付対象
        List l_lisDocCategoryManagements = null;

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and delivery_target = ? ");

        Object[] l_objWhere = {
            l_mainAccount.getInstitution().getInstitutionCode(),
            l_mainAccount.getBranch().getBranchCode(),
            l_fptDocumentGetRequest.deliveryTarget
        };

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisDocCategoryManagements = l_queryProcessor.doFindAllQuery(
                DocCategoryManagementRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
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

        //取得した書面種類管理テーブルの件数
        int l_intDocCategoryManagementsLength =l_lisDocCategoryManagements.size();

        //書面情報
        List l_lisFPTDocumentInfoUnits = new ArrayList();

        //取得した書面種類管理テーブルの件数分ループする
        for (int i = 0; i < l_intDocCategoryManagementsLength; i++)
        {
            //書面区分を取得する
            //書面区分管理テーブルから書面区分を取得する。
            //[条件]
            //証券会社コード = 顧客.証券会社コード
            //部店コード = 顧客.部店コード
            //書面チェック区分 = ”金商法@”
            //書面通番 = 0
            //書面種類コード = 取得した書面種類
            List l_lisDocDivManagementRows = null;

            StringBuffer l_sbWhereDocDivManagementRow = new StringBuffer();
            l_sbWhereDocDivManagementRow.append(" institution_code = ?");
            l_sbWhereDocDivManagementRow.append(" and branch_code = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_check_div = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_number = ? ");
            l_sbWhereDocDivManagementRow.append(" and document_category = ? ");

            Object[] l_valueDocDivManagementRows = new Object[5];
            DocCategoryManagementRow l_docCategoryManagementRow =
                (DocCategoryManagementRow)l_lisDocCategoryManagements.get(i);
            l_valueDocDivManagementRows[0] = l_mainAccount.getInstitution().getInstitutionCode();
            l_valueDocDivManagementRows[1] = l_mainAccount.getBranch().getBranchCode();
            l_valueDocDivManagementRows[2] = WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW;
            l_valueDocDivManagementRows[3] = "0";
            l_valueDocDivManagementRows[4] = l_docCategoryManagementRow.getDocumentCategory();

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisDocDivManagementRows = l_queryProcessor.doFindAllQuery(
                    DocDivManagementRow.TYPE,
                    l_sbWhereDocDivManagementRow.toString(),
                    l_valueDocDivManagementRows);
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

            //※レコードが取得できなかった場合は、ループの始めに戻る。
            if (l_lisDocDivManagementRows.isEmpty())
            {
                continue;
            }

            //電子鳩銘柄コードを取得する
            //電子鳩銘柄コード管理テーブルから電子鳩銘柄コードを取得する
            //[条件]
            //証券会社コード = 顧客.証券会社コード
            //部店コード = 顧客.部店コード
            //書面区分 = 取得した書面区分
            //電子鳩銘柄コードの1～3文字目 = 取得した書面種類 
            //有効区分 = 「0：valid」
            List l_lisBatoProductManagements = null;

            StringBuffer l_sbWhereBatoProductManagement = new StringBuffer();
            l_sbWhereBatoProductManagement.append(" institution_code = ? ");
            l_sbWhereBatoProductManagement.append(" and branch_code = ? ");
            l_sbWhereBatoProductManagement.append(" and document_div = ? ");
            l_sbWhereBatoProductManagement.append(" and substr(bato_product_code, 1, 3) = ? ");
            l_sbWhereBatoProductManagement.append(" and valid_flag = ? ");

            Object[] l_valueBatoProductManagements = new Object[5];
            DocDivManagementRow l_docDivManagementRow =
                (DocDivManagementRow)l_lisDocDivManagementRows.get(0);
            l_valueBatoProductManagements[0] = l_mainAccount.getInstitution().getInstitutionCode();
            l_valueBatoProductManagements[1] = l_mainAccount.getBranch().getBranchCode();
            l_valueBatoProductManagements[2] = l_docDivManagementRow.getDocumentDiv();
            l_valueBatoProductManagements[3] = l_docCategoryManagementRow.getDocumentCategory();
            l_valueBatoProductManagements[4] = WEB3ValidFlagDef.VALID;

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisBatoProductManagements = l_queryProcessor.doFindAllQuery(
                    BatoProductManagementRow.TYPE,
                    l_sbWhereBatoProductManagement.toString(),
                    l_valueBatoProductManagements);
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

            //※レコードが取得できなかった場合は、ループの始めに戻る。
            if (l_lisBatoProductManagements.isEmpty())
            {
                continue;
            }

            //※複数件レコードが取得できた場合は、
            //業務エラー「有効な電子鳩銘柄コードが複数存在します。」の例外をスローする。
            if (l_lisBatoProductManagements.size() > 1)
            {
                log.debug("有効な電子鳩銘柄コードが複数存在します。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "有効な電子鳩銘柄コードが複数存在します。");
            }

            BatoProductManagementRow l_batoProductManagementRow =
                (BatoProductManagementRow)l_lisBatoProductManagements.get(0);

            //顧客.is書面交付をコールする
            //[引数]
            //書面区分 = 取得した書面区分
            //銘柄コード = 取得した電子鳩銘柄コード
            boolean l_blnIsDocumentDelivery =
                l_mainAccount.isDocumentDelivery(
                    l_docDivManagementRow.getDocumentDiv(),
                    l_batoProductManagementRow.getBatoProductCode());

            //プロパティセット
            WEB3FPTDocumentInfoUnit l_fptDocumentInfoUnit = new WEB3FPTDocumentInfoUnit();

            //書面区分 ＝ 取得した書面区分
            l_fptDocumentInfoUnit.documentDiv = l_docDivManagementRow.getDocumentDiv();

            //書面種類 ＝ 取得した書面種類
            l_fptDocumentInfoUnit.documentCategory = l_docCategoryManagementRow.getDocumentCategory();

            //電子鳩銘柄コード ＝ 取得した電子鳩銘柄コード
            l_fptDocumentInfoUnit.batoProductCode = l_batoProductManagementRow.getBatoProductCode();

            //交付済フラグ
            if (l_blnIsDocumentDelivery)
            {
                //is書面交付メソッドの戻り値がtrueの場合、 「1：交付済」
                l_fptDocumentInfoUnit.deliverFlag = WEB3DocumentDeliverDivDef.DELIVERY;
            }
            else
            {
                //is書面交付メソッドの戻り値がfalseの場合、「0：交付未済」
                l_fptDocumentInfoUnit.deliverFlag = WEB3DocumentDeliverDivDef.UNDELIVERY;
            }

            //書面情報を一件追加する
            l_lisFPTDocumentInfoUnits.add(l_fptDocumentInfoUnit);
        }

        //生成した書面情報をリターンする
        WEB3FPTDocumentInfoUnit[] l_fptDocumentInfoUnits =
            new WEB3FPTDocumentInfoUnit[l_lisFPTDocumentInfoUnits.size()];

        l_lisFPTDocumentInfoUnits.toArray(l_fptDocumentInfoUnits);

        // createResponse()
        WEB3FPTDocumentGetResponse l_response = 
            (WEB3FPTDocumentGetResponse)l_request.createResponse();

        l_response.documentList = l_fptDocumentInfoUnits;
        
        log.exiting(STR_METHOD_NAME);
        //レスポンス返却
        return l_response;
    }
}
@
