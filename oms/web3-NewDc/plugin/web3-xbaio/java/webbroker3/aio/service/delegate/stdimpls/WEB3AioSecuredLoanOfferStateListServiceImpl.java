head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecuredLoanOfferStateListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保ローン申込状況サービス一覧Impl(WEB3AioSecuredLoanOfferStateListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.756，782, 785
Revision History : 2007/10/22 柴双紅 (中訊) 仕様変更・モデルNo.810, No.812
Revision History : 2008/01/10 柴双紅 (中訊) 仕様変更・モデルNo.829
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;

import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListResponse;
import webbroker3.aio.message.WEB3SLAccountOpenApplyDetailUnit;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanDataControlService;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanOfferStateListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (担保ローン申込状況サービス一覧Impl)<BR>
 * 担保ローン申込状況サービス実装クラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioSecuredLoanOfferStateListServiceImpl implements WEB3AioSecuredLoanOfferStateListService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanOfferStateListServiceImpl.class);

    /**
     * @@roseuid 46E0BE470157
     */
    public WEB3AioSecuredLoanOfferStateListServiceImpl()
    {

    }

    /**
     * 担保ローン申込状況一覧表示処理を行う。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 46DE2D950240
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータNull出来ない。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminSLAccountOpenApplyListRequest)
        {
            l_response =
                this.getListScreen((WEB3AdminSLAccountOpenApplyListRequest)l_request);
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

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧画面)<BR>
     * 担保ローン申込状況一覧表示処理を行う。<BR>
     * <BR>
     * ========================================================<BR>
     * シーケンス図:担保ローン申込状況一覧 / 担保ローン申込状況一覧<BR>
     * get一覧画面<BR>
     * 設定後、レスポンス.総ページ数＝0 の場合は、レスポンス.担保ローン申込顧客明細一覧<BR>
     * (担保ローン申込顧客明細一覧行[ ])にnullをセットし例外をスローする。<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@　@BUSINESS_ERROR_02908<BR>
     * ========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 口座開設データの行オブジェクト<BR>
     * <BR>
     * ※DDLより自動生成<BR>
     * @@return WEB3AdminSLAccountOpenApplyListResponse
     * @@throws WEB3BaseException
     * @@roseuid 46CE3E2B0350
     */
    protected WEB3AdminSLAccountOpenApplyListResponse getListScreen(
        WEB3AdminSLAccountOpenApplyListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getListScreen(WEB3AdminAioStockSecuredLoanAppliAccListRequest)";
        log.entering(STR_METHOD_NAME);

        // getInstanceFromログイン情報( )
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        // validate権限(機@能カテゴリコード : String, is更新 : boolean)
        // 機@能カテゴリコード：機@能カテゴリコード.口座開設管理(証券担保ローン)
        // is更新：false
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.SECURITY_LOAN_ACCOUNT_OPEN_MANAGE, false);

        // validate部店権限(部店コード : String[])
        // 部店コード：リクエストデータ.部店コード
        l_administrator.validateBranchPermission(l_request.branchCode);

        // validate
        l_request.validate();

        // get証券会社コード( )
        // this.管理者行.証券会社コードを返却する。
        String l_strInstitutionCode = l_administrator.getInstitutionCode();

        // オブジェクト配列[16]を生成し、以下の値を設定する。
        Object[] l_objValues = new Object[16];

        // [0]--証券会社コード：get証券会社コード()の戻り値
        l_objValues[0] = l_strInstitutionCode;

        // [1]--部店コード：リクエスト.部店コード
        l_objValues[1] = l_request.branchCode;

        // [2]--顧客コード：リクエスト.顧客コード
        l_objValues[2] = l_request.accountCode;

        // [3]--ストックローン口座番号：リクエスト.ストックローン口座番号
        l_objValues[3] = l_request.stockLoanAccount;

        // [4]--申込状況：リクエスト.申込状況
        l_objValues[4] = l_request.applyStatus;

        // [5]--申込日From：リクエスト.申込日From
        l_objValues[5] = l_request.applyDateFrom;

        // [6]--申込日To：リクエスト.申込日To
        l_objValues[6] = l_request.applyDateTo;

        // [7]--開設日From：リクエスト.開設日From
        l_objValues[7] = l_request.accountOpenDateFrom;

        // [8]--開設日To：リクエスト.開設日To
        l_objValues[8] = l_request.accountOpenDateTo;

        // [9]--成約日From：リクエスト.成約日From
        l_objValues[9] = l_request.executeDateFrom;

        // [10]--成約日To：リクエスト.成約日To
        l_objValues[10] = l_request.executeDateTo;

        // [11]--解約日From：リクエスト.解約日From
        l_objValues[11] = l_request.cancelDateFrom;

        // [12]--解約日To：リクエスト.解約日To
        l_objValues[12] = l_request.cancelDateTo;

        // [13]--閉鎖日From：リクエスト.閉鎖日From
        l_objValues[13] = l_request.closeDateFrom;

        // [14]--閉鎖日To：リクエスト.閉鎖日To
        l_objValues[14] = l_request.closeDateTo;

        // [15]--ソート条件[]：リクエスト.担保ローンソートキー
        l_objValues[15] = l_request.sortKeys;

        // get株券担保ローン一覧(株券担保ローン配列[])
        // 株券担保ローン配列[]：上記で生成したオブジェクト配列[16]
        WEB3AioSecuredLoanDataControlService l_aioSecuredLoanDataControlService =
            (WEB3AioSecuredLoanDataControlService)Services.getService(
                WEB3AioSecuredLoanDataControlService.class);
        StockSecuredLoanParams[] l_stockSecureLoanParams =
            l_aioSecuredLoanDataControlService.getStockSecuredLoanList(l_objValues);
        int l_intStockSecureLoanLength = l_stockSecureLoanParams.length;
        List l_lisResults = new ArrayList();
        for (int i = 0; i < l_intStockSecureLoanLength; i++)
        {
            //  担保ローン申込顧客明細一覧行( )
            WEB3SLAccountOpenApplyDetailUnit l_slAccountOpenDatailUnit = new WEB3SLAccountOpenApplyDetailUnit();

            // (*)プロパティセット
            // ○部店コード=株券担保ローンParams.get部店コード( )
            l_slAccountOpenDatailUnit.branchCode = l_stockSecureLoanParams[i].getBranchCode();

            // ○顧客コード=株券担保ローンParams.get顧客コード( )
            l_slAccountOpenDatailUnit.accountCode = l_stockSecureLoanParams[i].getAccountCode();

            // ○ストックローン口座番号=株券担保ローンParams.getストックローン口座番号( )
            l_slAccountOpenDatailUnit.stockLoanAccount =
                l_stockSecureLoanParams[i].getStockLoanAccountCode();

            // ○申込状況=株券担保ローンParams.get開設状況( )
            l_slAccountOpenDatailUnit.applyStatus =
                l_stockSecureLoanParams[i].getAccountOpenStatus();

            // ○申込日=株券担保ローンParams.get申込日( )
            l_slAccountOpenDatailUnit.applyDateFrom =
                WEB3DateUtility.toDay(l_stockSecureLoanParams[i].getAppliDate());

            // ○開設日=株券担保ローンParams.get開設日( )
            l_slAccountOpenDatailUnit.accountOpenDate =
                l_stockSecureLoanParams[i].getAccountOpenDate();

            // ○成約日=株券担保ローンParams.get成約データ受信日( )
            l_slAccountOpenDatailUnit.executeDate =
                WEB3DateUtility.toDay(l_stockSecureLoanParams[i].getOrderDataReceptionDate());

            // ○解約日=株券担保ローンParams.get解約データ受信日( )
            l_slAccountOpenDatailUnit.cancelDate =
                WEB3DateUtility.toDay(l_stockSecureLoanParams[i].getCancelDataReceptionDate());

            // ○閉鎖日=株券担保ローンParams.get閉鎖日( )
            l_slAccountOpenDatailUnit.closeDate = l_stockSecureLoanParams[i].getCloseDate();

            // ○Y客ロック=株券担保ローンParams.get申込時Y客情報( )
            l_slAccountOpenDatailUnit.yellowAccountLockDiv =
                l_stockSecureLoanParams[i].getYCustomerData();

            // ○考査ロック=株券担保ローンParams.get申込時ロック客情報（考査ロック）( )
            l_slAccountOpenDatailUnit.examinationLockDiv =
                l_stockSecureLoanParams[i].getExaminLockFlag();

            // ○支店ロック=株券担保ローンParams.get申込時ロック客情報（支店ロック）( )
            l_slAccountOpenDatailUnit.branchLockDiv =
                l_stockSecureLoanParams[i].getBranchLock();

            // ○管理ロック=株券担保ローンParams.get申込時ロック客情報（管理ロック）( )
            l_slAccountOpenDatailUnit.mngLockDiv =
                l_stockSecureLoanParams[i].getMngLockFlag();

            //(※1)管理ロック理由の４つに関しては、nullの場合は 0 を代入する。
            // ○管理ロック理由(立替金)=
            // 株券担保ローンParams.get申込時ロック客情報（管理ロック理由・立替金）( )
            if (l_stockSecureLoanParams[i].getMngLockFlagAdvance() == null)
            {
                l_slAccountOpenDatailUnit.mngExpenseLockReasonFlag =
                    BooleanEnum.FALSE.intValue() + "";
            }
            else
            {
                l_slAccountOpenDatailUnit.mngExpenseLockReasonFlag =
                    l_stockSecureLoanParams[i].getMngLockFlagAdvance().intValue() + "";
            }

            // ○管理ロック理由(保証金未入)=
            // 株券担保ローンParams.get申込時ロック客情報（管理ロック理由・保証金未入）( )
            if (l_stockSecureLoanParams[i].getMngLockFlagUnpayMargin() == null)
            {
                l_slAccountOpenDatailUnit.mngDepositLockReasonFlag =
                    BooleanEnum.FALSE.intValue() + "";
            }
            else
            {
                l_slAccountOpenDatailUnit.mngDepositLockReasonFlag =
                    l_stockSecureLoanParams[i].getMngLockFlagUnpayMargin().intValue() + "";
            }

            // ○管理ロック理由(適格担保不足)=
            // 株券担保ローンParams.get申込時ロック客情報（管理ロック理由・適格担保不足）( )
            if (l_stockSecureLoanParams[i].getMngLockFlagShortSecurity() == null)
            {
                l_slAccountOpenDatailUnit.mngCollateralLockReasonFlag =
                    BooleanEnum.FALSE.intValue() + "";
            }
            else
            {
                l_slAccountOpenDatailUnit.mngCollateralLockReasonFlag =
                    l_stockSecureLoanParams[i].getMngLockFlagShortSecurity().intValue() + "";
            }

            // ○管理ロック理由(預り証長期未差換)=
            // 株券担保ローンParams.get申込時ロック客情報（管理ロック理由・預り証長期未差換）( )
            if (l_stockSecureLoanParams[i].getMngLockFlagUnsubstitDepo() == null)
            {
                l_slAccountOpenDatailUnit.mngReceiptLockReasonFlag =
                    BooleanEnum.FALSE.intValue() + "";
            }
            else
            {
                l_slAccountOpenDatailUnit.mngReceiptLockReasonFlag =
                    l_stockSecureLoanParams[i].getMngLockFlagUnsubstitDepo().intValue() + "";
            }

            l_lisResults.add(l_slAccountOpenDatailUnit);
        }

        WEB3SLAccountOpenApplyDetailUnit[] l_slAccountOpenDatailUnits =
            new WEB3SLAccountOpenApplyDetailUnit[l_lisResults.size()];
        l_lisResults.toArray(l_slAccountOpenDatailUnits);

        // createResponse( )
        WEB3AdminSLAccountOpenApplyListResponse l_response =
            (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();

        // ＜ページング制御＞
        // １）レスポンスの以下の項目を設定する。
        // ○レスポンス.総ページ数＝担保ローン申込顧客明細一覧の要素数÷リクエスト.ページ内表示行数0
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);

        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(
                l_slAccountOpenDatailUnits,
                l_intPageIndex,
                l_intPageSize);

        // レスポンス.総ページ数
        l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";

        // レスポンス.総レコード数
        l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";

        // レスポンス.表示ページ番号
        l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";

        // ２）設定後、レスポンス.総ページ数＝0 の場合は、レスポンス.担保ローン申込顧客明細一覧
        // 　@(担保ローン申込顧客明細一覧行[ ])にnullをセットし例外をスローする。
        if (l_pageIndexInfo.getTotalPages() == 0)
        {
            l_response.accountOpenApplyDetailList = null;
            log.debug("担保ローン申込顧客明細一覧が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02908,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "担保ローン申込顧客明細一覧が未指定です。");
        }

        //レスポンス.表示ページ番号
        int l_intPageIndexs = l_pageIndexInfo.getPageIndex();

        // 【確定した担保ローン申込顧客明細一覧のうち、レスポンスに設定する明細を決める。】
        // １)　@(リクエスト.ページ内表示行数×(レスポンス.表示ページ番号-1)数分、
        // 確定した担保ローン申込顧客明細一覧の要素をスキップする。
        int l_intIndex = l_intPageSize * (l_intPageIndexs - 1);

        // (担保ローン申込顧客明細一覧の要素番号＋リクエスト.ページ内表示行数)
        int l_intIndexOf = l_intIndex + l_intPageSize;
        if (l_slAccountOpenDatailUnits.length < l_intIndexOf)
        {
            l_intIndexOf = l_slAccountOpenDatailUnits.length;
        }

        // 該当する担保ローン申込顧客明細一覧を、レスポンスデータ.担保ローン申込顧客明細一覧行セットする。
        List l_lisAccOpenDetailUnits = new ArrayList();
        for (int i = l_intIndex; i < l_intIndexOf; i++)
        {
            l_lisAccOpenDetailUnits.add(l_slAccountOpenDatailUnits[i]);
        }

        WEB3SLAccountOpenApplyDetailUnit[] l_slAccountOpenDatailUnitSecs =
            new WEB3SLAccountOpenApplyDetailUnit[l_lisAccOpenDetailUnits.size()];
        l_lisAccOpenDetailUnits.toArray(l_slAccountOpenDatailUnitSecs);

        // ○レスポンス.担保ローン申込顧客明細一覧行＝ページング制御を行って
        // 確定させた担保ローン申込顧客明細一覧の配列
        l_response.accountOpenApplyDetailList = l_slAccountOpenDatailUnitSecs;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
