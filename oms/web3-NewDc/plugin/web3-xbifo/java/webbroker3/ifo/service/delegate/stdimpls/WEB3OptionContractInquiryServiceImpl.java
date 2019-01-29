head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionContractInquiryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP建玉照会サービスImpl(WEB3OptionContractInquiryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 張威 (中訊) 新規作成
              001: 2004/07/26 王暁傑 (中訊)  logの定義を変更 バッグ IFO_UT-000008
                                             execute()を変更 バッグ IFO_UT-000009、IFO_UT-0000010、IFO_UT-0000011
                                                                    IFO_UT-000012、IFO_UT-000013、IFO_UT-000014
              002: 2004/07/30 李媛　@ (中訊) WEB3_IFO_UT-000083を対応しました。
              003: 2004/07/30 李媛　@ (中訊) WEB3_IFO_UT-000092を対応しました。
              004: 2004/08/14 王暁傑 対応バグ BUG142

*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3PageIndexInfo;

import webbroker3.ifo.WEB3IfoOrderManagerReusableValidations;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsContractReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsContractReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.ifo.message.WEB3OptionsProductCodeComparator;
import webbroker3.ifo.message.WEB3OptionsContractDivisionComparator;
import webbroker3.ifo.message.WEB3OptionsProfitAndLossComparator;
import webbroker3.ifo.message.WEB3OptionsOpenDateComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.service.delegate.WEB3OptionContractInquiryService;

/**
 * (OP建玉照会サービスImpl)<BR>
 * 株価指数オプション建玉照会サービス実装クラス<BR>
 *
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionContractInquiryServiceImpl
    extends WEB3OptionClientRequestService
    implements WEB3OptionContractInquiryService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionContractInquiryServiceImpl.class);

    /**
     * @@roseuid 40C0BB580119
     */
    public WEB3OptionContractInquiryServiceImpl()
    {

    }

    /**
     * 株価指数オプション建玉照会サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP建玉照会サービス）建玉照会」参照。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057F932039C
     */
//  UTBUG（WEB3_IFO_UT-000083）を対応　@START　@20040729   李媛
//    public WEB3OptionsContractReferenceResponse execute(WEB3OptionsContractReferenceRequest l_request)
//        throws WEB3BaseException
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
//  UTBUG（WEB3_IFO_UT-000083）を対応　@END　@20040729   李媛
    {
        final String STR_METHOD_NAME =
            getClass().getName() + ".execute(WEB3OptionsContractReferenceRequest)";

        log.debug(STR_METHOD_NAME + " : ENTER");
        WEB3OptionsContractReferenceRequest l_OpRequest = (WEB3OptionsContractReferenceRequest)l_request;
        FinApp l_finApp = null;
        l_finApp = (FinApp)Services.getService(FinApp.class);

        try
        {
            //2.validate()
            log.debug("2.validate() : ENTER");
            l_OpRequest.validate();
            log.debug("2.validate() : END");

            //3.get補助口座()
            log.debug("3.get補助口座() : ENTER");
            SubAccount l_subAccount = this.getSubAccount();
            log.debug("3.get補助口座() : END");

            //4.システム売買停止(バッチ中、緊急停止中)チェックを実施する。
            log.debug("4.システム売買停止(バッチ中、緊急停止中)チェックを実施する。 : ENTER");
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("4.システム売買停止(バッチ中、緊急停止中)チェックを実施する。 : END");

            //5.getInstance()
            log.debug("5.getInstance() : ENTER");
            WEB3IfoOrderManagerReusableValidations l_managerReusableValidations =
                (WEB3IfoOrderManagerReusableValidations)WEB3IfoOrderManagerReusableValidations
                    .getInstance();
            log.debug("5.getInstance() : END");

            //6.validate先物OP口座開設(補助口座 : 補助口座, 先物／オプション区分 : String)
            log.debug("6.validate先物OP口座開設 : ENTER");
            //Start 2004/07/26 王暁傑  対応バッグ IFO_UT-000009
//            l_managerReusableValidations.validateFuturesOptionAccountOpen(
//                l_subAccount,
//                WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            l_managerReusableValidations.validateFuturesOptionAccountOpen(
                l_subAccount,
                WEB3FuturesOptionDivDef.OPTION);
            //End 2004/07/26 王暁傑  対応バッグ IFO_UT-000009

            log.debug("6.validate先物OP口座開設 : END");

            //7.(*1)分岐フロー
            //リクエストデータに銘柄コードがセットされている場のみ、
            //以下の処理を実施する。
            log.debug("7.(*1)分岐フロー : ENTER");
            if (l_OpRequest.opProductCode != null && !l_OpRequest.opProductCode.equals(""))
            {
                //8.get銘柄(Institution, String)
                log.debug("8.get銘柄 : ENTER");
                //Start 2004/07/26 王暁傑  対応バッグ IFO_UT-000010
                //WEB3IfoProductManagerImpl l_ifoProductManager = new WEB3IfoProductManagerImpl();
                WEB3IfoProductManagerImpl l_ifoProductManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

                //Start 2004/07/26 王暁傑  対応バッグ IFO_UT-000011
//                //NotFoundException
//                l_ifoProductManager.getProduct(
//                    l_subAccount.getInstitution(),
//                    l_OpRequest.opProductCode);
                l_ifoProductManager.getIfoProduct(
                    l_subAccount.getInstitution(),
                    l_OpRequest.opProductCode);
                //End 2004/07/26 王暁傑  対応バッグ IFO_UT-000011
                log.debug("8.get銘柄 : END");
            }

            //9.is取引可能顧客(補助口座 : 補助口座)
            log.debug("9.is取引可能顧客 : ENTER");
            //WEB3OptionOrderManagerImpl l_orderManagerImpl = new WEB3OptionOrderManagerImpl();
            WEB3OptionOrderManagerImpl l_orderManagerImpl = (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;

            boolean l_blnIsTradedPossibleCustomer =
                l_orderManagerImpl.isTradedPossibleCustomer(l_gentradeSubAccount);
            log.debug("9.is取引可能顧客 : END");

            WEB3IfoPositionManagerImpl l_positionManagerImpl = (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

            WEB3FuturesOptionsProductCodeNameUnit[] l_createProductCodeNameFromContract =
                l_positionManagerImpl.createProductCodeNameFromContract(
                    l_gentradeSubAccount,
                    false,
                    WEB3FuturesOptionDivDef.OPTION);

            //16.createResponse( )
            WEB3OptionsContractReferenceResponse l_response =
                (WEB3OptionsContractReferenceResponse)l_OpRequest.createResponse();

            //(該当データチェック)
            //create銘柄コード名称from建玉の返り値がNULLの場合、
            //レスポンス作成、プロパティセット処理を行う。
            //（建玉照会明細作成、ソート処理、ページング処理は行わない）
            if (l_createProductCodeNameFromContract == null)
            {
                log.debug("17.レスポンスデータにプロパティをセット : ENTER");
                //(1)該当データなしの場合(create銘柄コード名称from建玉の返り値がNULL)
                //レスポンス.建玉照会明細 = NULL
                //レスポンス.総ページ数 = 0
                //レスポンス.総レコード数 = 0
                //レスポンス.表示ページ番号 = 0
                //レスポンス.株価指数先物オプション銘柄コード名称 = NULL
                //レスポンス.顧客ロック区分 = is取引可能顧客の返り値がfalseならtrue
                l_response.contractReferenceUnits = null;
                l_response.totalPages = "0";
                l_response.totalRecords = "0";
                l_response.pageIndex = "0";
                l_response.futOpProductCodeNames = null;
                //Start 2004/08/14 王暁傑 対応バグ BUG142
                //l_response.accountLock = l_blnIsTradedPossibleCustomer;
                l_response.accountLock = !l_blnIsTradedPossibleCustomer;
                //End 2004/08/14 王暁傑 対応バグ BUG142

                log.debug("17.レスポンスデータにプロパティをセット : END");

                log.debug("7.(*1)分岐フロー : END");

                log.debug(STR_METHOD_NAME + " : END");

                return l_response;
            }

            //建玉照会明細作成
            WEB3OptionsContractReferenceUnit[] l_contractReferenceUnit = null;

            //11.create検索条件文字列(String)
            log.debug("11.create検索条件文字列 : ENTER");
            String l_createReferenceCondCharacterString =
                createQueryString(l_OpRequest.opProductCode);
            log.debug("11.create検索条件文字列 : END");

            //12.create検索条件データコンテナ(String)
            //NotFoundException, WEB3BaseException
            log.debug("12.create検索条件データコンテナ : ENTER");
            String[] l_createReferenceCondDataContainer =
                createQueryContainer(l_OpRequest.opProductCode);
            log.debug("12.create検索条件データコンテナ : END");

            //シーケンス図 (先物OP残高)createOP建玉照会明細
            //13.createOP建玉照会明細(補助口座, String, String, String, String[])
            log.debug("13.createOP建玉照会明細 : ENTER");
            l_contractReferenceUnit =
                l_positionManagerImpl.createOptionsContractReferenceUnits(
                    l_gentradeSubAccount,
                    WEB3FuturesOptionDivDef.OPTION,
                    l_OpRequest.settlementState,
                    l_createReferenceCondCharacterString,
                    l_createReferenceCondDataContainer);

            //l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit[20];

            log.debug("13.createOP建玉照会明細 : END");

            //(検索条件該当データチェック)
            //create建玉照会明細の返り値がNULLの場合、
            //レスポンス作成、プロパティセット処理を行う。
            //(ソート処理、ページング処理は行わない)
//          UTBUG（WEB3_IFO_UT-000090）を対応　@START　@20040730   李媛
            //if (l_contractReferenceUnit == null)
            if ((l_contractReferenceUnit == null) || (l_contractReferenceUnit.length == 0))
//          UTBUG（WEB3_IFO_UT-000090）を対応　@END　@20040730   李媛
            {
                log.debug("17.レスポンスデータにプロパティをセット : ENTER");
                //(2)検索条件該当データなしの場合(create建玉照会明細の返り値がNULLの場合)

                //レスポンス.建玉照会明細 = NULL
                //レスポンス.総ページ数 = 0
                //レスポンス.総レコード数 = 0
                //レスポンス.表示ページ番号 = 0
                //レスポンス.株価指数先物オプション銘柄コード名称 = create銘柄コード名称from建玉の返り値
                //レスポンス.顧客ロック区分 = is取引可能顧客の返り値がfalseならtrue
                l_response.contractReferenceUnits = null;
                l_response.totalPages = "0";
                l_response.totalRecords = "0";
                l_response.pageIndex = "0";
                l_response.futOpProductCodeNames = l_createProductCodeNameFromContract;
                //Start 2004/08/14 王暁傑 対応バグ BUG142
                //l_response.accountLock = l_blnIsTradedPossibleCustomer;
                l_response.accountLock = !l_blnIsTradedPossibleCustomer;
                //End 2004/08/14 王暁傑 対応バグ BUG142

                log.debug("17.レスポンスデータにプロパティをセット : END");

                log.debug("7.(*1)分岐フロー : END");

                log.debug(STR_METHOD_NAME + " : END");

                return l_response;
            }

            //14.sort建玉照会明細(株価指数オプション建玉照会明細[], 株価指数先物オプションソートキー[])
            log.debug("14.sort建玉照会明細 : ENTER");
            WEB3OptionsContractReferenceUnit[] l_sortContractInquiryDetails =
                sortContractInquiryDetails(l_contractReferenceUnit, l_OpRequest.futOpSortKeys);
            log.debug("14.sort建玉照会明細 : END");

            //15.createページ(株価指数オプション建玉照会リクエスト, 株価指数オプション建玉照会明細[])
            log.debug("15.createページ : ENTER");
            //Start 2004/07/26 王暁傑  対応バッグ IFO_UT-000012
//                WEB3OptionsContractReferenceUnit[] l_createPage =
//                    createPage(l_OpRequest, l_contractReferenceUnit);
            WEB3OptionsContractReferenceUnit[] l_createPage =
                createPage(l_OpRequest, l_sortContractInquiryDetails);
            //End 2004/07/26 王暁傑  対応バッグ IFO_UT-000012
            log.debug("15.createページ : END");

            //(*2)レスポンスデータにプロパティをセットする。
            //該当データチェック、検索条件該当データチェック結果にもとづき、セットされるプロパティが異なる。

            log.debug("17.レスポンスデータにプロパティをセット : ENTER");

            //(3)(1)(2)以外の場合

            //レスポンス.建玉照会明細 = createページの返り値
            //レスポンス.総レコード数 = create建玉照会明細の返り値のサイズ
            //レスポンス.総ページ数 = 総レコード数 ÷ リクエストデータ.ページ内表示行数 ※計算結果が1未満、または余りが存在する場合には更に1を加算する
            //レスポンス.表示ページ番号 = リクエストデータ.要求ページ番号
            //※レスポンス.総レコード数 <= (リクエストデータ.ページ内表示行数 * (リクエストデータ.要求ページ番号 - 1))の場合は、レスポンス.総ページ数をセット(最終ページ番号をセット)
            //レスポンス.株価指数先物オプション銘柄コード名称 = ｃreate銘柄コード名称from建玉の返り値
            //レスポンス.顧客ロック区分 = is取引可能顧客の返り値がfalseならtrue  ※ロック顧客の場合：true、ロック顧客でない場合：falseとなる
            int l_intTotalRecords = l_sortContractInquiryDetails.length;
            int l_intPageSize = Integer.parseInt(l_OpRequest.pageSize);
            //int l_intTotalPages = l_intTotalRecords / l_intPageSize;

            l_response.contractReferenceUnits = l_createPage;
            l_response.totalRecords = Integer.toString(l_intTotalRecords);
            //Start 2004/07/26 王暁傑  対応バッグ IFO_UT-000013
//            l_response.totalPages = Integer.toString(l_intTotalPages);
//            if (l_intTotalPages < 1)
//            {
//                l_response.totalPages = "1";
//            }
            if (l_intTotalRecords % l_intPageSize == 0)
            {
                l_response.totalPages = String.valueOf(l_intTotalRecords / l_intPageSize);
            }
            else
            {
                l_response.totalPages = String.valueOf(l_intTotalRecords / l_intPageSize + 1);
            }

            //End 2004/07/26 王暁傑  対応バッグ IFO_UT-000013
          l_response.pageIndex = l_OpRequest.pageIndex;
          if (l_intTotalRecords
              <= (l_intPageSize * (Integer.parseInt(l_OpRequest.pageIndex) - 1)))
          {
              l_response.pageIndex = l_response.totalPages;
          }
            l_response.futOpProductCodeNames = l_createProductCodeNameFromContract;
            //Start 2004/08/14 王暁傑 対応バグ BUG142
            //l_response.accountLock = l_blnIsTradedPossibleCustomer;
            l_response.accountLock = !l_blnIsTradedPossibleCustomer;
            //End 2004/08/14 王暁傑 対応バグ BUG142

            log.debug("17.レスポンスデータにプロパティをセット : END");

            log.debug("7.(*1)分岐フロー : END");

            log.debug(STR_METHOD_NAME + " : END");

            return l_response;
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
//        catch (DataQueryException l_ex)
//        {
//            log.error("テーブルに該当するデータがありません。", l_ex);
//
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
//                this.getClass().getName() + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.error("予期しないシステムエラーが発生しました", l_ex);
//
//            throw new WEB3SystemLayerException(
//                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
//                this.getClass().getName() + STR_METHOD_NAME,
//                l_ex.getMessage(),
//                l_ex);
//        }
    }

    /**
     * (create検索条件文字列)<BR>
     * リクエストデータをもとに、<BR>
     * 検索条件（where以下指定の文字列）を作成する。<BR>
     * <BR>
     * (1)戻り値となる文字列のインスタンスを生成<BR>
     * <BR>
     * (2)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、<BR>
     * 銘柄ID指定を文字列インスタンスに追加<BR>
     * （銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@" and product_id = ?"<BR>
     * <BR>
     * (3)文字列インスタンスを返却<BR>
     * （パラメータ.銘柄コード＝NULLの場合、NULLを返却する）<BR>
     * @@param l_strProductCode - 銘柄コード
     * @@return String
     * @@roseuid 407A1F1B006C
     */
    protected String createQueryString(String l_strProductCode)
    {
        //(1)戻り値となる文字列のインスタンスを生成<BR>
        String l_strReturnValue;

        if (l_strProductCode != null && !"".equals(l_strProductCode))
        {
            log.debug("パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合: ENTER");

            //Start 2004/07/26 王暁傑  対応バッグ IFO_UT-000014
            //l_strReturnValue = l_strProductCode + " and product_id =?";
            l_strReturnValue = " and product_id =?";

            //End 2004/07/26 王暁傑  対応バッグ IFO_UT-000014
            log.debug("パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合: END");
            return l_strReturnValue;
        }
        else
        {
            return null;
        }
    }

    /**
     * (create検索条件データコンテナ)<BR>
     * リクエストデータから、<BR>
     * 検索条件（where以下指定の文字列）のパラメータリストを作成する。<BR>
     * <BR>
     * (1)文字列配列を作成<BR>
     * <BR>
     * (2)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、銘柄IDを<BR>
     * 　@　@文字列配列にセット（銘柄コードに対応する銘柄IDで検索を行う)<BR>
     * <BR>
     * 　@　@　@銘柄ID ＝ 先物OPプロダクトマネージャ.get銘柄 <BR>
     * (証券会社オブジェクト(*1), パラメータ.銘柄コード).銘柄ID<BR>
     * <BR>
     * (*1)証券会社オブジェクトは、補助口座.getInstitution( )で取得し設定<BR>
     * <BR>
     * (3)文字列配列を返却<BR>
     * （パラメータ.銘柄コード＝NULLの場合、NULLを返却する）<BR>
     * @@param l_strProductCode - 銘柄コード
     * @@return java.lang.String[]
     * @@roseuid 407A1F1B006E
     */
    protected String[] createQueryContainer(String l_strProductCode)
        throws NotFoundException, WEB3BaseException
    {
        FinApp l_finApp = null;
                l_finApp = (FinApp)Services.getService(FinApp.class);

        //(1)文字列配列を作成
        String[] l_strReturnValue = new String[1];

        //(2)パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合、銘柄IDを<BR>
        //　@　@文字列配列にセット（銘柄コードに対応する銘柄IDで検索を行う)<BR>
        if (l_strProductCode != null && !"".equals(l_strProductCode))
        {
            log.debug("パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合: ENTER");

            String l_strProductId = null;

            //(*1)証券会社オブジェクトは、補助口座.getInstitution( )で取得し設定
            SubAccount l_subAccount = this.getSubAccount();

            Institution l_institution = l_subAccount.getInstitution();
            //Start 2004/07/26 王暁傑  対応バッグ IFO_UT-000009
            //WEB3IfoProductManagerImpl l_ifoProductManager = new WEB3IfoProductManagerImpl();

            WEB3IfoProductManagerImpl l_ifoProductManager = (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

            //End 2004/07/26 王暁傑  対応バッグ IFO_UT-000009

            l_strProductId =
                Long.toString(
                    l_ifoProductManager
                        .getIfoProduct(l_institution, l_strProductCode)
                        .getProductId());

            l_strReturnValue[0] = l_strProductId;

            log.debug("パラメータ.銘柄コード≠NULL（銘柄コード指定）の場合: END");
            return l_strReturnValue;
        }
        // (3)文字列配列を返却<BR>
        // （パラメータ.銘柄コード＝NULLの場合、NULLを返却する）<BR>
        else
        {
            return null;
        }
    }

    /**
     * (createページ)<BR>
     * 要求ページ番号に該当する<BR>
     * 株価指数オプション建玉照会明細の配列を作成する。<BR>
     * <BR>
     * (1)ページ内表示行数、要求ページ番号の取得<BR>
     * ページ内表示行数 = パラメータ.リクエストデータ.ページ内表示行数<BR>
     * 要求ページ番号 = パラメータ.リクエストデータ.要求ページ番号<BR>
     * <BR>
     * (1)要求ページ開始位置を決定<BR>
     * fromIndex = ページ内表示行数 * (要求ページ番号 - 1)<BR>
     * <BR>
     * (2)要求ページ終了位置を決定<BR>
     * toIndex = (ページ内表示行数 * 要求ページ番号) - 1<BR>
     * <BR>
     * ※パラメータ.建玉照会明細の要素数 <= fromIndexの場合、<BR>
     * (総レコード数が要求ページ番号に満たない場合)<BR>
     * fromIndex =　@パラメータ.建玉照会明細の要素数 - ページ内表示行数<BR>
     * toIndex = パラメータ.建玉照会明細の要素数 - 1<BR>
     * <BR>
     * (3)ArrayListを作成<BR>
     * <BR>
     * (4)パラメータ.建玉照会明細数分Loop処理<BR>
     * <BR>
     * 建玉照会明細のインデックスが<BR>
     * fromIndexとtoIndexの範囲内(fromIndex以上、toIndex以下)<BR>
     * である場合は、<BR>
     * 作成したArrayListに建玉照会明細オブジェクトを追加<BR>
     * <BR>
     * (5)ArrayList.toArrayで該当ページ番号の建玉照会明細の配列を返却<BR>
     * する<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株式指数オプション建玉照会リクエストオブジェクト<BR>
     * @@param l_contractInquiryDetails - (建玉照会明細)<BR>
     * 株価指数オプション建玉照会明細の配列<BR>
     * (総レコード数分の配列)<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceUnit[]
     * @@roseuid 407B5AA7025E
     */
    protected WEB3OptionsContractReferenceUnit[] createPage(
        WEB3OptionsContractReferenceRequest l_request,
        WEB3OptionsContractReferenceUnit[] l_contractInquiryDetails)
    {

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_contractInquiryDetails,
            Integer.parseInt(l_request.pageIndex),
            Integer.parseInt(l_request.pageSize));

        return (WEB3OptionsContractReferenceUnit[])l_pageIndexInfo.getArrayReturned(WEB3OptionsContractReferenceUnit.class);
    }

    /**
     * (sort建玉照会明細)<BR>
     * 指定されたソートキー、<BR>
     * 昇降順にもどついて建玉照会明細のソートを行う。<BR>
     * <BR>
     * (1)ArrayListを作成<BR>
     * <BR>
     * (2)パラメータ.ソートキーの配列数分Loop処理<BR>
     * 　@(2-1)パラメータ.ソートキー.キー項目を取得<BR>
     * <BR>
     * 　@(2-2)パラメータ.ソートキー.昇順/降順を取得<BR>
     * <BR>
     * 　@(2-3)キー項目による分岐処理<BR>
     * 　@　@キー項目が銘柄コードであった場合、<BR>
     * 銘柄コードComparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が損益であった場合、損益Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が建日であった場合、建日Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@　@キー項目が建区分であった場合、建区分Comparatorを生成<BR>
     * 　@　@[コンストラクタのパラメータ=(2-2)で取得した昇順/降順]<BR>
     * <BR>
     * 　@(2-4)(2-3)にて作成したComparatorをArrayListに追加<BR>
     * <BR>
     * (3)ArrayListからComparatorの配列を作成<BR>
     * <BR>
     * (4)Comparatorの配列順のソート処理<BR>
     * (web3-common)WEB3ArraysUtility.sort<BR>
     * (パラメータ.建玉照会明細、Comparator[])<BR>
     * <BR>
     * (5)ソートされた建玉照会明細の配列を返却<BR>
     * @@param l_contractInquiryDetails - (建玉照会明細)<BR>
     * 株価指数オプション建玉照会明細の配列<BR>
     * @@param l_sortKey - (ソートキー)<BR>
     * 株価指数オプションソートキーの配列<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceUnit[]
     * @@roseuid 408F300401C3
     */
    protected WEB3OptionsContractReferenceUnit[] sortContractInquiryDetails(
        WEB3OptionsContractReferenceUnit[] l_contractInquiryDetails,
        WEB3FuturesOptionsSortKey[] l_sortKey)
    {
        //(1)ArrayListを作成
        List l_arrayList = new ArrayList();

        //(2)パラメータ.ソートキーの配列数分Loop処理
        int l_intSortKeyLength = l_sortKey.length;
        log.debug("パラメータ.ソートキーのの要素数 = " + l_intSortKeyLength);

        String l_strKeyItem; //パラメータ.ソートキー.キー項目
        String l_strAscDesc; //パラメータ.ソートキー.昇順/降順
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //　@(2-1)パラメータ.ソートキー.キー項目を取得
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("パラメータ.ソートキー.キー項目 " + i + 1 + " = " + l_strKeyItem);

            //　@(2-2)パラメータ.ソートキー.昇順/降順を取得
            l_strAscDesc = l_sortKey[i].ascDesc;
            log.debug("パラメータ.ソートキー.昇順/降順 " + i + 1 + " = " + l_strAscDesc);

            //　@(2-3)キー項目による分岐処理
            //　@　@キー項目が銘柄コードであった場合、銘柄コードComparatorを生成
            if (WEB3IfoKeyItemDef.PRODUCTCODE.equals(l_strKeyItem))
            {
                log.debug("作成した銘柄コードComparatorをArrayListに追加: ENTER");
                WEB3OptionsProductCodeComparator l_productCodeComparator =
                    new WEB3OptionsProductCodeComparator(l_strAscDesc);
                l_arrayList.add(l_productCodeComparator); //作成したComparatorをArrayListに追加
                log.debug("作成した銘柄コードComparatorをArrayListに追加: END");
            }
            //キー項目が損益であった場合、損益Comparatorを生成
            else if (WEB3IfoKeyItemDef.INCOME.equals(l_strKeyItem))
            {
                log.debug("作成した損益ComparatorをArrayListに追加: ENTER");
                WEB3OptionsProfitAndLossComparator l_profitAndLossComparator =
                    new WEB3OptionsProfitAndLossComparator(l_strAscDesc);
                l_arrayList.add(l_profitAndLossComparator);
                log.debug("作成した損益ComparatorをArrayListに追加: END");
            }
            //キー項目が建日であった場合、建日Comparatorを生成
            else if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
            {
                log.debug("作成した建日ComparatorをArrayListに追加: ENTER");
                WEB3OptionsOpenDateComparator l_openDateComparator =
                    new WEB3OptionsOpenDateComparator(l_strAscDesc);
                l_arrayList.add(l_openDateComparator);
                log.debug("作成した建日ComparatorをArrayListに追加: END");
            }
            //キー項目が建区分であった場合、建区分Comparatorを生成
            else if (WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(l_strKeyItem))
            {
                log.debug("作成した建区分ComparatorをArrayListに追加: ENTER");
                WEB3OptionsContractDivisionComparator l_contractDivisionComparator =
                    new WEB3OptionsContractDivisionComparator(l_strAscDesc);
                l_arrayList.add(l_contractDivisionComparator);
                log.debug("作成した建区分ComparatorをArrayListに追加: END");
            }
        }

        //(3)ArrayListからComparatorの配列を作成
        Object[] l_comparatorObjects = l_arrayList.toArray();
        int l_intcomparatorObjectsLength = l_comparatorObjects.length;
        Comparator[] l_comparatorArrayList = new Comparator[l_intcomparatorObjectsLength];
        for (int i = 0; i < l_intcomparatorObjectsLength; i++)
        {
            l_comparatorArrayList[i] = (Comparator)l_comparatorObjects[i];
        }

        //(4)Comparatorの配列順のソート処理
        log.debug("Comparatorの配列順のソート処理: ENTER");
        WEB3ArraysUtility.sort((Object[])l_contractInquiryDetails, l_comparatorArrayList);
        log.debug("Comparatorの配列順のソート処理: END");

        return l_contractInquiryDetails;
    }

    /**
     * @@param l_request
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40C0BB5801D4
     */
//  UTBUG（WEB3_IFO_UT-000083）を対応　@START　@20040730   李媛
//    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
//    {
//        final String STR_METHOD_NAME = getClass().getName() + ".execute(WEB3GenRequest)";
//
//        log.entering(STR_METHOD_NAME);
//
//        WEB3GenResponse l_response = (WEB3GenResponse)l_request.createResponse();
//
//        log.exiting(STR_METHOD_NAME);
//
//        return l_response;
//    }
//  UTBUG（WEB3_IFO_UT-000083）を対応　@START　@20040730   李媛
}
@
