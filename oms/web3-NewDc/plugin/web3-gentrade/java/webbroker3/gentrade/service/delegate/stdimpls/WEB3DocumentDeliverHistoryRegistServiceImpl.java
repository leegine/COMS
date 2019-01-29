head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverHistoryRegistServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 書面交付履歴登録サービスImpl(WEB3DocumentDeliverHistoryRegistServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 大澤喜宗@(SRA) 新規作成
Revesion History : 2008/01/23 魏キン(中訊) 仕様変更 モデルNo.309
Revesion History : 2008/01/26 栄イ (中訊) 仕様変更・モデルNo.308、312、313を対応
Revesion History : 2008/01/31 栄イ (中訊) 仕様変更・ＤＢ更新仕様No.007を対応
*/
package webbroker3.gentrade.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DocumentCheckDivDef;
import webbroker3.common.define.WEB3ValidFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BatoProductManagementDao;
import webbroker3.gentrade.data.BatoProductManagementRow;
import webbroker3.gentrade.data.DocDeliveryManagementParams;
import webbroker3.gentrade.data.DocDeliveryManagementRow;
import webbroker3.gentrade.data.DocDivManagementRow;
import webbroker3.gentrade.define.WEB3GentradeBatoCheckResultDef;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistRequest;
import webbroker3.gentrade.message.WEB3DocumentDeliverHistoryRegistResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;
import webbroker3.gentrade.service.delegate.WEB3DocumentDeliverHistoryRegistService;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (書面交付履歴登録サービスImpl)<BR>
 * <BR>
 * 書面交付履歴登録サービス実装クラス <BR>
 * @@author 大澤喜宗@
 * @@version 1.0
 */
public class WEB3DocumentDeliverHistoryRegistServiceImpl 
    extends WEB3ClientRequestService implements WEB3DocumentDeliverHistoryRegistService 
{

    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3DocumentDeliverHistoryRegistServiceImpl.class);
    
    /**
     * コンストラクタ<BR>
     */
    public WEB3DocumentDeliverHistoryRegistServiceImpl() 
    {
    }

    /**
     * (execute)<BR>
     * <BR>
     * 書面交付履歴登録サービス処理を行う。<BR>
     * <BR>
     * シーケンス図「書面交付履歴登録」参照。<BR>
     * <BR> 
     * @@param l_request リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3DocumentDeliverHistoryRegistRequest l_regRequest = 
            (WEB3DocumentDeliverHistoryRegistRequest) l_request;
        
        // validate()
        l_regRequest.validate();
        
        // get代理入力者()
        Trader l_trader = this.getTrader();
        
        // createResponse()
        WEB3DocumentDeliverHistoryRegistResponse l_response = 
            (WEB3DocumentDeliverHistoryRegistResponse)l_regRequest.createResponse();
        
        // 代理入力者インスタンスが取得できた場合
        if (l_trader != null)
        {
            // 以降の処理を行なわず、レスポンスを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        WEB3GentradeBatoClientService l_service = 
            (WEB3GentradeBatoClientService) 
                Services.getService(WEB3GentradeBatoClientService.class);
        
        // リクエスト.電子鳩チェックフラグ == false の場合
        if (!l_regRequest.eleBatoCheckFlg)
        {
            // 以降の処理を行なわず、レスポンスを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // リクエスト．銘柄コード件数分LOOP処理を行う
        for (int i = 0; i < l_regRequest.productCode.length; i++)
        {
            // validate電子鳩銘柄コード
            validateBatoProductCode(l_regRequest.typeCode, l_regRequest.productCode[i]);

            WEB3GentradeProspectusResult l_result = null;
            try
            {
                // validate目論見書閲覧
                l_result =
                    l_service.validateProspectus(l_regRequest.typeCode, l_regRequest.productCode[i]);
            }
            catch (WEB3BaseException l_ex)
            {
                // validate目論見書閲覧()が例外をスロー の場合
                if (l_ex instanceof WEB3BusinessLayerException)
                {
                    // "[電子鳩システム障害中]障害中注文不可。"の場合
                    // 例外をそのままスローする
                    if (WEB3ErrorCatalog.BUSINESS_ERROR_01984.equals(l_ex.getErrorInfo()))
                    {
                        throw l_ex;
                    }
                }
                // 上記以外の場合、LOOP処理の始めに戻る
                continue;
            }

            // is金商法@書面
            boolean l_blnFinancialProductsExchangeLawDocument =
                this.isFinancialProductsExchangeLawDocument(
                    l_regRequest.typeCode,
                    l_regRequest.productCode[i].substring(0, 3));

            // 目論見書閲覧チェック結果.チェック結果 == ”閲覧未済” and
            // is金商法@書面()の戻り値 == trueの場合
            if (WEB3GentradeBatoCheckResultDef.UNINSPECTION.equals(l_result.checkResult)
                && l_blnFinancialProductsExchangeLawDocument)
            {
                // 「書面未交付エラー」の例外をスローする。
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02940,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }

            // 目論見書閲覧チェック結果.チェック結果 != ”閲覧済”の場合
            if (!WEB3GentradeBatoCheckResultDef.INSPECTION.equals(l_result.checkResult))
            {
                // レスポンス返却
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            // save交付履歴()
            this.saveDeliveryHistory(l_regRequest.typeCode, l_regRequest.productCode[i]);
        }

        log.exiting(STR_METHOD_NAME);
        
        // レスポンス返却
        return l_response;
    }

    /**
     * (save交付履歴)<BR>
     * <BR>
     * 交付履歴を登録する。<BR>
     * <BR>
     * １）顧客インスタンスを取得する。<BR>
     * <BR>
     * 　@　@this.get口座()をコールする。<BR>
     * <BR>
     * ２）以下の条件で書面交付管理テーブルを検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@口座ID = 顧客.getAccountId()の戻り値<BR>
     * 　@　@書面区分 = 引数.書面区分<BR>
     * 　@　@銘柄コード = 引数.銘柄コード<BR>
     * 　@　@書面交付日 = 現在日時の日付部分<BR>
     * 　@　@削除フラグ = ”有効”<BR>
     * <BR>
     * 　@　@レコードが取得できた場合は、以降の処理を行わずに終了する。<BR>
     * <BR>
     * ３）書面交付管理行インスタンスを生成する。<BR>
     * <BR>
     * ４）各項目に値をセットする。<BR>
     * <BR>
     * 　@　@※詳細は、DB更新仕様参照。<BR>
     * <BR>
     * ５）レコードを追加する。<BR>
     * <BR>
     * 　@　@WEB3DataAccessUtillity.insertRow() をコールする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@l_row ： 生成した行インスタンス<BR>
     * <BR>
     * @@param l_documentDiv 書面区分（種別コード）
     * @@param l_productCode 銘柄コード
     * @@throws WEB3BaseException
     */    
    protected void saveDeliveryHistory(String l_documentDiv, String l_productCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveDeliveryHistory(String, String)";
        
        log.entering(STR_METHOD_NAME);
        
        int l_intSize;
        Date l_datBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        
        // １）顧客インスタンスを取得する。
        // this.get口座()をコールする。
        WEB3GentradeMainAccount l_mainAccount = 
            (WEB3GentradeMainAccount)this.getMainAccount();
        
        // ２）以下の条件で書面交付管理テーブルを検索する。
        // [条件]
        // 口座ID = 顧客.getAccountId()の戻り値
        // 書面区分 = 引数.書面区分
        // 銘柄コード = 引数.銘柄コード
        // 書面交付日 = 現在日時の日付部分
        // 削除フラグ = ”有効”
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" account_id = ? ");
        l_sbWhere.append(" and document_div = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and delivery_date = to_date(?, 'yyyyMMdd') ");
        l_sbWhere.append(" and delete_flag = ? ");

        Object[] l_objWhere = {
            String.valueOf(l_mainAccount.getAccountId()),
            l_documentDiv,
            l_productCode,
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
            BooleanEnum.FALSE
        };
        
        List l_lisRecords = null;
        QueryProcessor l_queryProcessor;
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = 
                l_queryProcessor.doFindAllQuery(
                    DocDeliveryManagementRow.TYPE, 
                    l_sbWhere.toString(), 
                    l_objWhere); 

        } 
        catch (DataException e) 
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);

        } 
        
        l_intSize = l_lisRecords.size();

        // レコードが取得できた場合は、以降の処理を行わずに終了する。
        if (l_intSize > 0)
        {
            log.debug("レコードが取得できた場合は、以降の処理を行わずに終了する。");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        // ３）書面交付管理行インスタンスを生成する。
        DocDeliveryManagementParams l_params = new DocDeliveryManagementParams();
        
        // ４）各項目に値をセットする。
        // 口座ＩＤ：顧客.口座ID
        l_params.setAccountId(l_mainAccount.getAccountId());
        
        // 証券会社コード：顧客.証券会社コード
        l_params.setInstitutionCode(l_mainAccount.getInstitution().getInstitutionCode());
        
        // 部店コード：顧客.部店コード
        l_params.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        
        // 顧客コード：顧客.口座コード
        l_params.setAccountCode(l_mainAccount.getAccountCode());
        
        // 書面区分：引数.書面区分
        l_params.setDocumentDiv(l_documentDiv);
        
        // 銘柄コード：引数.銘柄コード
        l_params.setProductCode(l_productCode);
        
        // 書面交付日：現在日時の日付部分
        l_params.setDeliveryDate(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
        
        // 削除フラグ：”有効”
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        
        // 更新者コード：顧客.口座コード
        l_params.setLastUpdater(l_mainAccount.getAccountCode());
        
        // 作成日付：現在日時
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        // 更新日付：現在日時
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        // 書面種類コード：引数.銘柄コードの1桁目から3桁目
        l_params.setDocumentCategory(l_productCode.substring(0, 3));
        
        // ５）レコードを追加する。
        try 
        {
            WEB3DataAccessUtility.insertRow(l_params);
        } 
        catch (DataException e) 
        {
            // 例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME);
        } 
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is金商法@書面)<BR>
     * <BR>
     * 当該書面が金商法@書面かどうかを判定する。<BR>
     * <BR>
     * １）顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@this.get口座()をコールする。<BR>
     * <BR>
     * ２）書面区分管理テーブルから金商法@書面の書面区分を取得する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード = 顧客.getInstitution().getInstitutionCode()の戻り値<BR>
     * 　@　@部店コード = 顧客.getBranch().getBranchCode()の戻り値<BR>
     * 　@　@書面チェック区分 = ”金商法@”<BR>
     * 　@　@書面通番 = ”0”<BR>
     * 　@　@書面種類コード = 引数.書面種類 <BR>
     * <BR>
     * ３）引数.書面区分 == ２）で取得した書面区分 の場合はtrueを、<BR>
     * 　@　@そうでない場合はfalseを返却する。<BR>
     * <BR>
     * @@param l_documentDivision 書面区分
     * @@param l_strDocumentType 書面種類
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isFinancialProductsExchangeLawDocument(String l_documentDiv, String l_strDocumentType)
        throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "isFinancialProductsExchangeLawDocument(String, String)";
        
        log.entering(STR_METHOD_NAME);

        // １）顧客オブジェクトを取得する
        //  this.get口座()をコールする。
        WEB3GentradeMainAccount l_account = 
            (WEB3GentradeMainAccount)this.getMainAccount();

        // ２）書面区分管理テーブルから金商法@書面の書面区分を取得する。
        // [条件]
        // 証券会社コード = 顧客.getInstitution().getInstitutionCode()の戻り値
        // 部店コード = 顧客.getBranch().getBranchCode()の戻り値
        // 書面チェック区分 = ”金商法@”
        // 書面通番 = ”0”
        // 書面種類コード = 引数.書面種類
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and document_check_div = ? ");
        l_sbWhere.append(" and document_number = ? ");
        l_sbWhere.append(" and document_category = ? ");

        Object[] l_objWhere = {
            l_account.getInstitution().getInstitutionCode(),
            l_account.getBranch().getBranchCode(),
            WEB3DocumentCheckDivDef.FINANCIAL_PRODUCTS_EXCHANGE_LAW,
            "0",
            l_strDocumentType
        };
        
        List l_lisRecords = null;
        QueryProcessor l_queryProcessor;
        
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = 
                l_queryProcessor.doFindAllQuery(
                    DocDivManagementRow.TYPE, 
                    l_sbWhere.toString(), 
                    l_objWhere); 

        }
        catch(DataException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        
        if (l_lisRecords.size() < 1)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        DocDivManagementRow l_docDivManagementRow = 
            (DocDivManagementRow)l_lisRecords.get(0);

        // ３）引数.書面区分 == ２）で取得した書面区分 の場合はtrueを、
        //    そうでない場合はfalseを返却する。
        if (l_documentDiv.equals(l_docDivManagementRow.getDocumentDiv()))
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

    /**
     * (validate電子鳩銘柄コード)<BR>
     * <BR>
     * 電子鳩銘柄コードが有効かどうかを確認する。<BR>
     * <BR>
     * １）顧客オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@this.get口座()をコールする。<BR>
     * <BR>
     * ２）以下の条件で電子鳩銘柄コード管理テーブルを検索する。 <BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@証券会社コード = 顧客.getInstitution().getInstitutionCode()の戻り値 <BR>
     * 　@　@部店コード = 顧客.getBranch().getBranchCode()の戻り値 <BR>
     * 　@　@書面区分 = 引数.書面区分 <BR>
     * 　@　@電子鳩銘柄コード = 引数.電子鳩銘柄コード <BR>
     * <BR>
     * ３）取得したレコードの有効区分が「1:invalid」の場合、<BR>
     * 　@　@「電子鳩銘柄コードが無効です」の例外をスローする。<BR>
     * <BR>
     * @@param l_strDocumentDivision 書面区分
     * @@param l_strBatoProductCode 電子鳩銘柄コード
     * @@throws WEB3BaseException
     */
    protected void validateBatoProductCode(String l_strDocumentDivision, String l_strBatoProductCode)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validateBatoProductCode(String, String)";

        log.entering(STR_METHOD_NAME);

        // １）顧客オブジェクトを取得する
        //  this.get口座()をコールする。
        WEB3GentradeMainAccount l_account =
            (WEB3GentradeMainAccount)this.getMainAccount();

        // ２）以下の条件で電子鳩銘柄コード管理テーブルを検索する
        // [条件]
        // 証券会社コード = 顧客.getInstitution().getInstitutionCode()の戻り値
        // 部店コード = 顧客.getBranch().getBranchCode()の戻り値
        // 書面区分 = 引数.書面区分
        // 電子鳩銘柄コード = 引数.電子鳩銘柄コード
        BatoProductManagementRow l_row = null;

        try
        {
            l_row = BatoProductManagementDao.findRowByPk(
                l_account.getInstitution().getInstitutionCode(),
                l_account.getBranch().getBranchCode(),
                l_strDocumentDivision,
                l_strBatoProductCode
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }


        //取得したレコードの有効区分が「1:invalid」の場合、
        //「電子鳩銘柄コードが無効です」の例外をスローする。
        String l_strValidFlag = l_row.getValidFlag();

        if (WEB3ValidFlagDef.INVALID.equals(l_strValidFlag))
        {
            log.debug("電子鳩銘柄コードが無効です");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03004,
                getClass().getName() + "." + STR_METHOD_NAME,
                "電子鳩銘柄コードが無効です");

        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
