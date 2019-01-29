head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.29.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポートサービス実装クラス(WEB3AdminAioSettleReportServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 韋念瓊 (中訊) 新規作成     
                   2004/10/27 周勇(中訊) レビュー               
                   2006/04/14 李小健 仕様変更・モデル530
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioSettleInstitution;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.define.WEB3AioSettleReportSortkeyDef;
import webbroker3.aio.define.WEB3AioTransactionStatusDef;
import webbroker3.aio.message.WEB3AdminAioSettleReportListRequest;
import webbroker3.aio.message.WEB3AdminAioSettleReportListResponse;
import webbroker3.aio.message.WEB3AioSettleReportUnit;
import webbroker3.aio.message.WEB3AioSortKeyUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (決済連携レポートサービスImpl)<BR>
 * 決済連携レポートサービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioSettleReportServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminAioSettleReportService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportServiceImpl.class);
        
    /**
     * 決済連携レポートサービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（決済連携レポート）レポート画面表示データ取得」 参照<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410101610138
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AdminAioSettleReportListRequest l_aioSettleReportListRequest = null;
        WEB3AdminAioSettleReportListResponse l_aioSettleReportListResponse = null;        
        
        if (l_request instanceof WEB3AdminAioSettleReportListRequest)
        {
            //リクエストデータの具象データ型が「 決済連携レポート一覧リクエスト 」の場合
            l_aioSettleReportListRequest = (WEB3AdminAioSettleReportListRequest) l_request;
        }
        else
        {
            log.debug("Error[入力値が不正です]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1 入力内容チェック 
        l_aioSettleReportListRequest.validate();
        
        //1.2 管理者インスタンスを取得する。         
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 該当の管理者がこの機@能が使えるか権限チェックを行う。 
        //validate権限(String, boolean)
        //[引数] 
        //機@能カテゴリコード： ”B0201”
        //is更新： false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_SETTLE_REPORT, 
            false);
        
        //1.4 該当の管理者が指定した部店に対する処理権限があるかチェックする。 
        //validate部店権限(String)
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        l_web3Administrator.validateBranchPermission(
            l_aioSettleReportListRequest.branchCode);
        
        //1.5 取得条件の文字列を生成する。
        String l_strWhereClause = null;
        l_strWhereClause = this.createGetCondCharacterString(l_aioSettleReportListRequest);
        
        //1.6 取得条件に設定する値の配列を生成する。
        Object l_bindVars[] = this.createGetCondDataContainer(l_aioSettleReportListRequest);
        
        //ページサイズ：
        int l_intPageSize = 0;
        l_intPageSize = Integer.parseInt(l_aioSettleReportListRequest.pageSize);
            
        //ページ番号：
        int l_intPageIndex = 0;
        l_intPageIndex = Integer.parseInt(l_aioSettleReportListRequest.pageIndex);
        
        //1.7 createソート条件文字列(AIOソートキー２[]) 
        //ソート文字列を生成する。 
        //[引数] 
        //ソートキー： リクエストデータ.ソートキー 
        String l_strSortCond = 
            this.createSortCondString(l_aioSettleReportListRequest.sortKeys);
        
        WEB3PageIndexInfo l_pageIndexInfo = null;
        //1.8, 1.9 金融機@関連携入出金状況テーブルから、レコードを取得する。
        List l_lisRows = null;
        try
        {
            //［引数］ 
            //Rowタイプ： 金融機@関連携入出金状況Row.TYPE 
            //Where： create取得条件文字列()の戻り値 
            //orderBy： createソート条件文字列()の戻り値  
            //condition： null 
            //リスト： create取得条件データコンテナ()の戻り値 
            //ページサイズ： リクエストデータ.ページ内表示行数 
            //ページ番号： リクエストデータ.要求ページ番号 - 1 
            
            List l_lisBankCashTransferStatusRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    BankCashTransferStatusRow.TYPE,
                    l_strWhereClause,
                    l_strSortCond,
                    null,
                    l_bindVars,
                    l_intPageSize,
                    l_intPageIndex - 1);
            
            l_pageIndexInfo = new WEB3PageIndexInfo(
                    l_lisBankCashTransferStatusRows, 
                    l_intPageIndex, 
                    l_intPageSize);   
            
            l_lisRows = l_pageIndexInfo.getListReturned();
                
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.10 証券会社オブジェクトを取得する。 
        Institution l_institution = l_web3Administrator.getInstitution();
        
        //1.11 ArrayListインスタンスを生成する。
        List l_lisAioSettleReportUnit = null;
        l_lisAioSettleReportUnit = new ArrayList();
                
        //1.12 取得した要素分のLoop処理         
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            BankCashTransferStatusRow l_bankCashTransferStatusRow = 
                (BankCashTransferStatusRow) l_lisRows.get(i);
                
            //1.12.1 決済連携レポート明細インスタンスを生成する。 
            //［引数］ 
            //証券会社： get証券会社()の戻り値 
            //金融機@関連携入出金状況： 金融機@関連携入出金状況Rowオブジェクト
            
            WEB3AioSettleReportUnit l_aioSettleReportUnit = null;
            l_aioSettleReportUnit = this.createSettleReportUnit(
                l_institution, l_bankCashTransferStatusRow);
                
            //1.12.2 決済連携レポート明細オブジェクトをリストに追加する。 

            l_lisAioSettleReportUnit.add(l_aioSettleReportUnit);                
        }
        
        WEB3AioSettleReportUnit[] l_aioSettleReportUnits = 
            new WEB3AioSettleReportUnit[l_lisAioSettleReportUnit.size()];
            
        //1.13 決済連携レポート明細の配列を取得する。
        l_lisAioSettleReportUnit.toArray(l_aioSettleReportUnits);
                
       
        //1.14 表示ページ番号を取得する。        
        l_intPageIndex = l_pageIndexInfo.getPageIndex();
        
        //1.15 総ページ数を取得する。 
        int l_intTotalPages = l_pageIndexInfo.getTotalPages();
        
        //1.16 総レコード数を取得する。       

        int l_intTotalRecords = l_pageIndexInfo.getTotalRecords();

        
        //1.17 決済機@関インスタンスを生成する。
        //［引数］ 
        //決済機@関ID： リクエストデータ.決済機@関ID 
        
        WEB3AioSettleInstitution l_web3AioSettleInstitution = 
            new WEB3AioSettleInstitution(l_aioSettleReportListRequest.paySchemeId);
        
        //1.18 決済機@関の名称を取得する。
        String l_strSettleName = l_web3AioSettleInstitution.getName();
        
        //1.19 レスポンスデータを生成する。
        l_aioSettleReportListResponse = (WEB3AdminAioSettleReportListResponse) 
            l_request.createResponse();
        
        //1.20  プロパティセット
        //レスポンス.部店コード = リクエストデータ.部店コード
        l_aioSettleReportListResponse.branchCode = 
            l_aioSettleReportListRequest.branchCode;
        
        log.debug("レスポンス.部店コード = " + l_aioSettleReportListResponse.branchCode);
        
        //レスポンス.決済機@関ID = リクエストデータ.決済機@関ID
        l_aioSettleReportListResponse.paySchemeId = 
            l_aioSettleReportListRequest.paySchemeId;
        
        log.debug("レスポンス.決済機@関ID = " + l_aioSettleReportListResponse.paySchemeId);
        
        //レスポンス.決済機@関名 = 提携決済機@関.get名称()の戻り値
        l_aioSettleReportListResponse.paySchemeName = l_strSettleName;
        
        log.debug("レスポンス.決済機@関名 = " + l_aioSettleReportListResponse.paySchemeName);
        
        //レスポンス.顧客コード = リクエストデータ.顧客コード
        l_aioSettleReportListResponse.accountCode = 
            l_aioSettleReportListRequest.accountCode;

        log.debug("レスポンス.顧客コード = " + l_aioSettleReportListResponse.accountCode);
        
        //レスポンス.受渡日 = リクエストデータ.受渡日
        l_aioSettleReportListResponse.deliveryDate = 
            l_aioSettleReportListRequest.deliveryDate;
        
        log.debug("レスポンス.受渡日 = " + l_aioSettleReportListResponse.deliveryDate);
        
        //レスポンス.注文日（自） = リクエストデータ.注文日（自）
        l_aioSettleReportListResponse.minOrtderDate = 
            l_aioSettleReportListRequest.minOrtderDate;
        
        log.debug("レスポンス.注文日（自） = " + l_aioSettleReportListResponse.minOrtderDate);
        
        //レスポンス.注文日（至） = リクエストデータ.注文日（至）
        l_aioSettleReportListResponse.maxOrtderDate = 
            l_aioSettleReportListRequest.maxOrtderDate;
            
        log.debug("レスポンス.注文日（至） = " + l_aioSettleReportListResponse.maxOrtderDate);

        //レスポンス.処理状態 = リクエストデータ.処理状態
        
        l_aioSettleReportListResponse.transactionStatus = 
            l_aioSettleReportListRequest.transactionStatus;
        
        log.debug("レスポンス.処理状態 = " + l_aioSettleReportListResponse.transactionStatus);
        
        //レスポンス..comデビット決済取引番号（自） = 
        //リクエストデータ..comデビット決済取引番号（自）
        l_aioSettleReportListResponse.minComDebitNumber = 
            l_aioSettleReportListRequest.minComDebitNumber;
        
        log.debug("レスポンス..comデビット決済取引番号（自）= " + 
                l_aioSettleReportListResponse.minComDebitNumber);
        
        //レスポンス..comデビット決済取引番号（至） = 
        //リクエストデータ..comデビット決済取引番号（至）
        l_aioSettleReportListResponse.maxComDebitNumber = 
            l_aioSettleReportListRequest.maxComDebitNumber;
            
        log.debug("レスポンス..comデビット決済取引番号（至）= " + 
                l_aioSettleReportListResponse.maxComDebitNumber);
        
        //レスポンス.決済連携レポート明細 = 決済連携レポート明細の配列
        
        l_aioSettleReportListResponse.settleReportUnits = l_aioSettleReportUnits;
        
        log.debug("レスポンス.決済連携レポート明細's length= " + 
                l_aioSettleReportListResponse.settleReportUnits.length);
        
        //レスポンス.表示ページ番号 = ListPage.pageNumber()の戻り値
        l_aioSettleReportListResponse.pageIndex = l_intPageIndex + "";
        
        log.debug("レスポンス.決済連携レポート明細= " + l_aioSettleReportListResponse.pageIndex);
                        
        //レスポンス.総ページ数 = ListPage.totalPages()の戻り値
        l_aioSettleReportListResponse.totalPages = l_intTotalPages + "";
        
        log.debug("レスポンス.総ページ数= " + l_aioSettleReportListResponse.totalPages);
        
        //レスポンス.総レコード数 = ListPage.totalSize()の戻り値
        l_aioSettleReportListResponse.totalRecords = l_intTotalRecords + "";
  
        log.debug("レスポンス.総レコード数= " + l_aioSettleReportListResponse.totalRecords);
        
        log.exiting(STR_METHOD_NAME);
        return l_aioSettleReportListResponse;
    }
    
    /**
     * (create取得条件文字列)<BR>
     * リクエストデータから、データ取得条件文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）部店コード条件生成<BR>
     * <BR>
     *   条件文字列： "branch_code=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ３）決済機@関ID条件生成<BR>
     * <BR>
     *   条件文字列： " and pay_scheme_id=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ４）顧客コード条件生成<BR>
     * <BR>
     *   引数.リクエストデータ.顧客コード != null の場合<BR>
     * <BR>
     *   条件文字列： " and account_code=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５）注文日条件生成<BR>
     * <BR>
     * ５－１） 引数.リクエストデータ.注文日（自） != null and <BR>
     * 引数.リクエストデータ.注文日（至） != null の場合<BR>
     * <BR>
     *   条件文字列： " and order_date_time>=? and order_date_time<=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５－２） 引数.リクエストデータ.注文日（自） != null and <BR>
     * 引数.リクエストデータ.注文日（至） = null の場合<BR>
     * <BR>
     *   条件文字列： " and order_date_time>=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５－３） 引数.リクエストデータ.注文日（自） = null and <BR>
     * 引数.リクエストデータ.注文日（至） != null の場合<BR>
     * <BR>
     *   条件文字列： " and order_date_time<=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ６）受渡日条件生成<BR>
     * <BR>
     *   引数.リクエストデータ.受渡日 != null の場合<BR>
     * <BR>
     *   条件文字列： " and delivery_scheduled_date=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ７）.comデビット決済取引番号条件生成<BR>
     * <BR>
     * ７－１） 引数.リクエストデータ..comデビット決済取引番号（自） != null <BR>
     * and 引数.リクエストデータ..comデビット決済取引番号（至） != null の場合<BR>
     * <BR>
     *   条件文字列： " and center_pay_id>=? and center_pay_id<=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ７－２） 引数.リクエストデータ..comデビット決済取引番号（自） != null <BR>
     * and 引数.リクエストデータ..comデビット決済取引番号（至） = null の場合<BR>
     * <BR>
     *   条件文字列： " and center_pay_id=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ８）処理状態条件生成<BR>
     * <BR>
     *   条件文字列： " and status=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ９）生成した文字列を返却する。<BR>
     * @@param l_request - リクエストデータ
     * @@return String
     * @@roseuid 41182B5503A5
     */
    protected String createGetCondCharacterString(
        WEB3AdminAioSettleReportListRequest l_request) 
    {
        final String STR_METHOD_NAME = "createGetCondCharacterString(" +
            "WEB3AdminAioSettleReportListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）空の文字列を生成する。
        String l_strWhereCondition = "";
        
        //２）部店コード条件生成 
        l_strWhereCondition += "branch_code = ?";
        
        //３）決済機@関ID条件生成 
        l_strWhereCondition += " and pay_scheme_id = ?";
 
        //４）顧客コード条件生成 
        //引数.リクエストデータ.顧客コード != null の場合 
        if (l_request.accountCode != null)
        {        
            l_strWhereCondition += " and substr(account_code, 0, 6) = ?";
        }
        
        //５）注文日条件生成
        //５－１） 引数.リクエストデータ.注文日（自） != null and 
        //        引数.リクエストデータ.注文日（至） != null の場合 
        if (l_request.minOrtderDate != null &&
            l_request.maxOrtderDate != null)
        {            
            l_strWhereCondition += " and order_date_time >= ? and order_date_time <= ?";
        }
        //５－２） 引数.リクエストデータ.注文日（自） != null and 
        //        引数.リクエストデータ.注文日（至） = null の場合 
        if (l_request.minOrtderDate != null &&
            l_request.maxOrtderDate == null)
        {
            l_strWhereCondition += " and order_date_time >= ?";
        }
       
        //５－３） 引数.リクエストデータ.注文日（自） = null and 
        //        引数.リクエストデータ.注文日（至） != null の場合
        if (l_request.minOrtderDate == null &&
            l_request.maxOrtderDate != null)
        {
            l_strWhereCondition += " and order_date_time<= ?" ;
        }

        //６）受渡日条件生成 
        //引数.リクエストデータ.受渡日 != null の場合 
        if (l_request.deliveryDate != null)
        {
            l_strWhereCondition += " and delivery_scheduled_date = ?";
        }
        
        //７）.comデビット決済取引番号条件生成 
        //７－１） 引数.リクエストデータ..comデビット決済取引番号（自） != null and 
        //        引数.リクエストデータ..comデビット決済取引番号（至） != null の場合 
        if (l_request.minComDebitNumber != null &&
            l_request.maxComDebitNumber != null)
        {
            l_strWhereCondition += " and center_pay_id >= ? and center_pay_id <= ?";
        }
        
        //７－２） 引数.リクエストデータ..comデビット決済取引番号（自） != null and 
        //        引数.リクエストデータ..comデビット決済取引番号（至） = null の場合 
        if (l_request.minComDebitNumber != null &&
            l_request.maxComDebitNumber == null)
        {
            l_strWhereCondition += " and center_pay_id = ?";
        }
        
        //８）処理状態条件生成 
        //引数.リクエストデータ.処理状態 != 4（すべて） の場合 
        if (!WEB3AioTransactionStatusDef.ALL.equals(l_request.transactionStatus))
        {
            l_strWhereCondition += " and transaction_status = ?";
        }
        log.debug("生成した文字列= " + l_strWhereCondition);
        
        //９）生成した文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strWhereCondition;
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータリストを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）部店コード<BR>
     * <BR>
     *   引数.リクエストデータ.部店コードを１）のリストに追加する。<BR>
     * <BR>
     * ３）決済機@関ID<BR>
     * <BR>
     *   引数.リクエストデータ.決済機@関IDを１）のリストに追加する。<BR>
     * <BR>
     * ４）顧客コード<BR>
     * <BR>
     *   引数.リクエストデータ.顧客コード != null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.顧客コードを１）のリストに追加する。<BR>
     * <BR>
     * ５）注文日<BR>
     * <BR>
     * ５－１） 引数.リクエストデータ.注文日（自） != null and <BR>
     * 引数.リクエストデータ.注文日（至） != null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.注文日（自）、<BR>
     *  引数.リクエストデータ.注文日（至）を１）のリストに追加する。<BR>
     * <BR>
     * ５－２） 引数.リクエストデータ.注文日（自） != null and <BR>
     * 引数.リクエストデータ.注文日（至） = null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.注文日（自）を１）のリストに追加する。<BR>
     * <BR>
     * ５－３） 引数.リクエストデータ.注文日（自） = null and <BR>
     * 引数.リクエストデータ.注文日（至） != null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.注文日（至）を１）のリストに追加する。<BR>
     * <BR>
     * ６）受渡日<BR>
     * <BR>
     *   引数.リクエストデータ.受渡日 != null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.受渡日を１）のリストに追加する。<BR>
     * <BR>
     * ７）.comデビット決済取引番号<BR>
     * <BR>
     * ７－１） 引数.リクエストデータ..comデビット決済取引番号（自） != null <BR>
     * and 引数.リクエストデータ..comデビット決済取引番号（至） != null の場合<BR>
     * <BR>
     *   
     * 引数.リクエストデータ..comデビット決済取引番号（自）、
     * 引数.リクエストデータ..comデビット決済取引番号（至）を１）のリ
     * ストに追加する。<BR>
     * <BR>
     * ７－２） 引数.リクエストデータ..comデビット決済取引番号（自） != null <BR>
     * and 引数.リクエストデータ..comデビット決済取引番号（至） = null の場合<BR>
     * <BR>
     *   引数.リクエストデータ..comデビット決済取引番号（自）を１）のリストに追加する。<BR>
     * <BR>
     * ８）処理状態<BR>
     * <BR>
     *   引数.リクエストデータ.処理状態を１）のリストに追加する。<BR>
     * <BR>
     * ９）リストから配列を取得し、返却する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return Object[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41182B5503B4
     */
    protected Object[] createGetCondDataContainer(
        WEB3AdminAioSettleReportListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer(" +
            "WEB3AdminAioSettleReportListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）空のArrayListを生成する。
        List l_lisAioSettleReportUnit = null;
        l_lisAioSettleReportUnit = new ArrayList();

        //２）部店コード
        //引数.リクエストデータ.部店コードを１）のリストに追加する。
        l_lisAioSettleReportUnit.add(l_request.branchCode);
        
        //３）決済機@関ID 
        //引数.リクエストデータ.決済機@関IDを１）のリストに追加する。 
        l_lisAioSettleReportUnit.add(l_request.paySchemeId);
        
        //４）顧客コード 
        //引数.リクエストデータ.顧客コード != null の場合
        //引数.リクエストデータ.顧客コードを１）のリストに追加する。        
        if (l_request.accountCode != null)
        {
            l_lisAioSettleReportUnit.add(l_request.accountCode);
        }

        //５）注文日 
        //５－１） 引数.リクエストデータ.注文日（自） != null and 
        //        引数.リクエストデータ.注文日（至） != null の場合 
        //引数.リクエストデータ.注文日（自）、引数.リクエストデータ.注文日（至）を１）のリストに追加する。
        
        if (l_request.minOrtderDate != null &&
            l_request.maxOrtderDate != null)
        {
            l_lisAioSettleReportUnit.add(l_request.minOrtderDate);
            l_lisAioSettleReportUnit.add(l_request.maxOrtderDate);
        }

        //５－２） 引数.リクエストデータ.注文日（自） != null and 
        //        引数.リクエストデータ.注文日（至） = null の場合 
        //引数.リクエストデータ.注文日（自）を１）のリストに追加する。 
        if (l_request.minOrtderDate != null &&
            l_request.maxOrtderDate == null)
        {
            l_lisAioSettleReportUnit.add(l_request.minOrtderDate);
        }
        
        //５－３） 引数.リクエストデータ.注文日（自） = null and 
        //        引数.リクエストデータ.注文日（至） != null の場合 
        //引数.リクエストデータ.注文日（至）を１）のリストに追加する。
        if (l_request.minOrtderDate == null &&
            l_request.maxOrtderDate != null)
        {
            l_lisAioSettleReportUnit.add(l_request.maxOrtderDate);
        }
        
        //６）受渡日 
        //引数.リクエストデータ.受渡日 != null の場合 
        //引数.リクエストデータ.受渡日を１）のリストに追加する。
        if (l_request.deliveryDate != null)
        {
            l_lisAioSettleReportUnit.add(l_request.deliveryDate);
        }
        
        //７）.comデビット決済取引番号 
        //７－１） 引数.リクエストデータ..comデビット決済取引番号（自） != null and         
        //        引数.リクエストデータ..comデビット決済取引番号（至） != null の場合 
        if (l_request.minComDebitNumber != null &&
            l_request.maxComDebitNumber != null)
        {
            l_lisAioSettleReportUnit.add(l_request.minComDebitNumber);
            l_lisAioSettleReportUnit.add(l_request.maxComDebitNumber);
        }
        
        //７－２） 引数.リクエストデータ..comデビット決済取引番号（自） != null and 
        //        引数.リクエストデータ..comデビット決済取引番号（至） = null の場合 
        if (l_request.minComDebitNumber != null &&
            l_request.maxComDebitNumber == null)
        {
            l_lisAioSettleReportUnit.add(l_request.minComDebitNumber);            
        }
        
        //８）処理状態 
        //引数.リクエストデータ.処理状態 != 4（すべて） の場合         
        if (!WEB3AioTransactionStatusDef.ALL.equals(l_request.transactionStatus))
        {
            l_lisAioSettleReportUnit.add(l_request.transactionStatus);
        }
        
        //９）リストから配列を取得し、返却する。 
        Object[] l_bindVars = 
            new Object[l_lisAioSettleReportUnit.size()];
            
        l_lisAioSettleReportUnit.toArray(l_bindVars);
        
        log.exiting(STR_METHOD_NAME);
        return l_bindVars;
    }
    
    /**
     * (create決済連携レポート明細)<BR>
     * 決済連携明細オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（決済連携レポート）create決済連携レポート明細」 参照<BR>
     * @@param l_institution - (証券会社オブジェクト)<BR>
     * @@param l_bankCashTransferStatusRow - (金融機@関連携入出金状況Rowオブジェクト)
     * @@return WEB3AioSettleReportUnit
     * @@throws WEB3BaseException
     * @@roseuid 41185B93038C
     */
    protected WEB3AioSettleReportUnit createSettleReportUnit(
        Institution l_institution, 
        BankCashTransferStatusRow l_bankCashTransferStatusRow) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleReportUnit()";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null || l_bankCashTransferStatusRow == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.1 決済連携レポート明細インスタンスを生成する。
        WEB3AioSettleReportUnit l_aioSettleReportUnit = new WEB3AioSettleReportUnit();
        WEB3GentradeMainAccount l_web3GentradeMainAcc = null;
        try
        {       
            //1.2 顧客インスタンスを生成する。 
            //［引数］
            //証券会社ID： 引数.証券会社.getInstitutionId()の戻り値 
            //部店コード： 引数.金融機@関連携入出金状況Params.部店コード 
            //顧客コード： 引数.金融機@関連携入出金状況Params.顧客コード 
            AccountManager l_accountManager = GtlUtils.getAccountManager();  
            l_web3GentradeMainAcc = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(), 
                    l_bankCashTransferStatusRow.getBranchCode(), 
                    l_bankCashTransferStatusRow.getAccountCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }     
        //1.3 顧客の名称を取得する。
        String l_strDisplayAccountName = l_web3GentradeMainAcc.getDisplayAccountName();
        
        //1.4 メッセージコードを取得する。 

        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                    WEB3AioMultiBankSettleControlService.class);

        //［引数］ 
        //証券会社コード： 引数.金融機@関連携入出金状況Params.証券会社コード 
        //部店コード： 引数.金融機@関連携入出金状況Params.部店コード 
        //顧客コード： 引数.金融機@関連携入出金状況Params.顧客コード 
        //識別コード： 引数.金融機@関連携入出金状況Params.識別コード 
        //.comデビット決済取引番号： 引数.金融機@関連携入出金状況Params..comデビット決済取引番号 
        //処理区分： 引数.金融機@関連携入出金状況Params.処理区分 
        //FLAG（注文）： 引数.金融機@関連携入出金状況Params.処理FLAG（注文） 
        //FLAG（決済開始）： 引数.金融機@関連携入出金状況Params.処理FLAG（決済開始） 
        //FLAG（決済結果）： 引数.l_bankCashTransferStatusRow金融機@関連携入出金状況Params.処理FLAG（決済結果） 
               
        String l_strMessageCode = l_aioMultiBankSettleControlService.getMessageCode(
            l_bankCashTransferStatusRow.getInstitutionCode(), 
            l_bankCashTransferStatusRow.getBranchCode(), 
            l_bankCashTransferStatusRow.getAccountCode(), 
            l_bankCashTransferStatusRow.getOrderRequestNumber(), 
            l_bankCashTransferStatusRow.getCenterPayId(), 
            l_bankCashTransferStatusRow.getTransactionStatus(), 
            l_bankCashTransferStatusRow.getOrderStatusFlag(), 
            l_bankCashTransferStatusRow.getStartStatusFlag(), 
            l_bankCashTransferStatusRow.getResultStatusFlag());
        
        //1.5 プロパティセット
        
        //決済連携レポート明細.顧客コード = 金融機@関連携入出金状況Params.顧客コード
        l_aioSettleReportUnit.accountCode = l_bankCashTransferStatusRow.getAccountCode().substring(0, 6);
        
        //決済連携レポート明細.顧客名 = 顧客.get顧客表示名()の戻り値
        l_aioSettleReportUnit.accountName = l_strDisplayAccountName;
        
        //決済連携レポート明細..comデビット決済取引番号 = 
        //金融機@関連携入出金状況Params..comデビット決済取引番号
        l_aioSettleReportUnit.comDebitNumber = 
            l_bankCashTransferStatusRow.getCenterPayId();
        
        //決済連携レポート明細.加盟店注文番号 = （以下のとおり）
        //    金融機@関連携入出金状況Params.証券会社コード+
        //    金融機@関連携入出金状況Params.部店コード+
        //    金融機@関連携入出金状況Params.識別コード
        
        //（３つの項目の文字列を連結したもの）
        l_aioSettleReportUnit.shopOrderId = 
            l_bankCashTransferStatusRow.getInstitutionCode() + 
            l_bankCashTransferStatusRow.getBranchCode() + 
            l_bankCashTransferStatusRow.getOrderRequestNumber();
        
        //決済連携レポート明細.受付日時 = 金融機@関連携入出金状況Params.注文日時
        l_aioSettleReportUnit.receptionDate = 
            l_bankCashTransferStatusRow.getOrderDateTime();
        
        log.debug("金融機@関連携入出金状況Params.処理時間（決済結果通知） = " + 
                l_bankCashTransferStatusRow.getResultRequestTime());
        log.debug("金融機@関連携入出金状況Params.処理時間（決済開始要求） = " + 
                l_bankCashTransferStatusRow.getStartRequestTime());        
        log.debug("金融機@関連携入出金状況Params.処理時間（注文要求） = " + 
                l_bankCashTransferStatusRow.getOrderRequestTime());  
        
        //決済連携レポート明細.戻り日時 = （以下のとおり）
        
        //    金融機@関連携入出金状況Params.処理時間（決済結果通知） != null の場合、
        //            金融機@関連携入出金状況Params.処理時間（決済結果通知）        
        if (l_bankCashTransferStatusRow.getResultRequestTime() != null)
        {
            l_aioSettleReportUnit.returnDate = 
                l_bankCashTransferStatusRow.getResultRequestTime();
        }
        
        //    金融機@関連携入出金状況Params.処理時間（決済開始要求） != null の場合、
        //            金融機@関連携入出金状況Params.処理時間（決済開始要求）        
        else if (l_bankCashTransferStatusRow.getStartRequestTime() != null)            
        {
            l_aioSettleReportUnit.returnDate = 
                l_bankCashTransferStatusRow.getStartRequestTime();
        }
        
        //    金融機@関連携入出金状況Params.処理時間（注文要求） != null の場合、
        //            金融機@関連携入出金状況Params.処理時間（注文要求）                 
        else if (l_bankCashTransferStatusRow.getOrderRequestTime() != null)
        {
            l_aioSettleReportUnit.returnDate = 
                l_bankCashTransferStatusRow.getOrderRequestTime();
        }
        
        //それ以外の場合、null
        else
        {
            l_aioSettleReportUnit.returnDate = null;
        }
        
        log.debug("決済連携レポート明細.戻り日時 = " + l_aioSettleReportUnit.returnDate);
        
        //決済連携レポート明細.受渡日 = 金融機@関連携入出金状況Params.受渡予定日
        l_aioSettleReportUnit.deliveryDate = 
            l_bankCashTransferStatusRow.getDeliveryScheduledDate();
        
        log.debug("決済連携レポート明細.受渡日 = " + l_aioSettleReportUnit.deliveryDate);
        
        //決済連携レポート明細.振込予定日 = 金融機@関連携入出金状況Params.振込入金予定日
        l_aioSettleReportUnit.transScheduledDate = 
            l_bankCashTransferStatusRow.getComondebiCaptureDate();
        
        log.debug("決済連携レポート明細.振込予定日 = " + l_aioSettleReportUnit.transScheduledDate);
        
        //決済連携レポート明細.金額 = 金融機@関連携入出金状況Params.金額
        l_aioSettleReportUnit.amount = l_bankCashTransferStatusRow.getAmount() + "";
        
        log.debug("決済連携レポート明細.金額 = " + l_aioSettleReportUnit.amount);
        
        //決済連携レポート明細.処理状態 = 金融機@関連携入出金状況Params.処理区分
        l_aioSettleReportUnit.transactionStatus = 
            l_bankCashTransferStatusRow.getTransactionStatus();
        
        log.debug("決済連携レポート明細.処理状態 = " + l_aioSettleReportUnit.transactionStatus);
        
        //決済連携レポート明細.メッセージコード = 
        //マルチバンク決済制御サービスImpl.getメッセージコード()の戻り値
        l_aioSettleReportUnit.messageCode = l_strMessageCode;
        
        log.debug("決済連携レポート明細.メッセージコード = " + l_aioSettleReportUnit.messageCode);
                
        //決済連携レポート明細.入力区分 = 金融機@関連携入出金状況Params.入力区分
        l_aioSettleReportUnit.inputDiv = l_bankCashTransferStatusRow.getInputDiv();
        
        log.debug("決済連携レポート明細.入力区分 = " + l_aioSettleReportUnit.inputDiv);
        
        log.exiting(STR_METHOD_NAME);
        return l_aioSettleReportUnit;        
    }
    
    /**
     * (createソート条件文字列)<BR>
     * ソート条件文字列を生成する。 <BR>
     * <BR>
     * １）空の文字列を生成する。 <BR>
     * <BR>
     * ２）引数.ソートキーの各要素について、以下の処理を行う。 <BR>
     * <BR>
     * ２－１）ソートキー.キー項目 == ”顧客コード” and ソートキー.昇順/降順 == ”昇順” の場合<BR> 
     * <BR>
     * １）の文字列に、"account_code" を追加する。 <BR>
     * <BR>
     * ２－２）ソートキー.キー項目 == ”顧客コード” and ソートキー.昇順/降順 == ”降順” の場合<BR>
     * <BR>
     * １）の文字列に、"account_code desc" を追加する。 <BR>
     * <BR>
     * ２－３）ソートキー.キー項目 == ”.comデビット決済取引番号” and <BR>
     *       ソートキー.昇順/降順 == ”昇順” の場合 <BR>
     * <BR>
     * １）の文字列に、"center_pay_id" を追加する。 <BR>
     * <BR>
     * ２－４）ソートキー.キー項目 == ”.comデビット決済取引番号” and <BR>
     *       ソートキー.昇順/降順 == ”降順” の場合 <BR>
     * <BR>
     * １）の文字列に、"center_pay_id desc" を追加する。 <BR>
     * <BR>
     * ２－５）ソートキー.キー項目 == ”加盟店注文番号” and <BR>
     *       ソートキー.昇順/降順 == ”昇順” の場合 <BR>
     * <BR>
     * １）の文字列に、"institution_code, branch_code, order_request_number" を追加する。 <BR>
     * <BR>
     * ２－６）ソートキー.キー項目 == ”加盟店注文番号” and <BR>
     *       ソートキー.昇順/降順 == ”降順” の場合 <BR>
     * <BR>
     * １）の文字列に、"institution_code desc, branch_code desc, <BR>
     *      order_request_number desc" を追加する。<BR> 
     * <BR>
     * ２－７）ソートキー.キー項目 == ”受付日時” and ソートキー.昇順/降順 == ”昇順” の場合<BR> 
     * <BR>
     * １）の文字列に、"order_date_time" を追加する。 <BR>
     * <BR>
     * ２－８）ソートキー.キー項目 == ”受付日時” and ソートキー.昇順/降順 == ”降順” の場合 <BR>
     * <BR>
     * １）の文字列に、"order_date_time desc" を追加する。 <BR>
     * <BR>
     * ２－９）該当の要素が最終要素ではない場合 <BR>
     * <BR>
     * １）の文字列に、", " を追加する。 <BR>
     * <BR>
     * ３）生成された文字列を返却する。<BR>
     */
    protected String createSortCondString(WEB3AioSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCondString(WEB3AioSortKeyUnit[] sortKeys)";
        log.entering(STR_METHOD_NAME);
        
        // １）空の文字列を生成する。
        StringBuffer l_strSortString = new StringBuffer();
        // ２）ソートキー配列の各要素毎に以下の処理を行う。
        int l_intLength = 0;
        if (l_sortKeys != null)
        {
            l_intLength = l_sortKeys.length;
        }
        //２）引数.ソートキーの各要素について、以下の処理を行う。 
        for (int i = 0; i < l_intLength; i++ )
        {
            //２－１）ソートキー.キー項目 == ”顧客コード” and 
            //       ソートキー.昇順/降順 == ”昇順” の場合 
            //  １）の文字列に、"account_code" を追加する。
            //２－２）ソートキー.キー項目 == ”顧客コード” and 
            //       ソートキー.昇順/降順 == ”降順” の場合 
            //  １）の文字列に、"account_code desc" を追加する。
            if (WEB3AioSettleReportSortkeyDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("account_code");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("account_code desc");
                }
            }

            //２－３）ソートキー.キー項目 == ”.comデビット決済取引番号” and 
            //      ソートキー.昇順/降順 == ”昇順” の場合 
            //  １）の文字列に、"center_pay_id" を追加する。 
            //２－４）ソートキー.キー項目 == ”.comデビット決済取引番号” and 
            //      ソートキー.昇順/降順 == ”降順” の場合 
            //  １）の文字列に、"center_pay_id desc" を追加する。
            else if (WEB3AioSettleReportSortkeyDef.COMDEBIT_NUMBER.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("center_pay_id");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("center_pay_id desc");
                }
            }
            //２－５）ソートキー.キー項目 == ”加盟店注文番号” and 
            //      ソートキー.昇順/降順 == ”昇順” の場合 
            //  １）の文字列に、"institution_code, branch_code, order_request_number" を追加する。 
            //２－６）ソートキー.キー項目 == ”加盟店注文番号” and 
            //      ソートキー.昇順/降順 == ”降順” の場合 
            //  １）の文字列に、"institution_code desc, branch_code desc, order_request_number desc" を追加する。
            else if (WEB3AioSettleReportSortkeyDef.SHOP_ORDERID.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("institution_code, branch_code, order_request_number");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("institution_code desc, branch_code desc, order_request_number desc");
                }                
            }
            //２－７）ソートキー.キー項目 == ”受付日時” and ソートキー.昇順/降順 == ”昇順” の場合 
            //  １）の文字列に、"order_date_time" を追加する。 
            //２－８）ソートキー.キー項目 == ”受付日時” and ソートキー.昇順/降順 == ”降順” の場合 
            //  １）の文字列に、"order_date_time desc" を追加する。
            else if (WEB3AioSettleReportSortkeyDef.RECEPTION_DATE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("order_date_time");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("order_date_time desc");
                }                
            }
            //２－９）該当の要素が最終要素ではない場合 
            //  １）の文字列に、", " を追加する。
            if (i < (l_intLength - 1))
            {
                l_strSortString.append(",");
            }
        }
        //３）生成された文字列を返却する。
        log.debug(l_strSortString.toString());
        log.exiting(STR_METHOD_NAME);
        
        return l_strSortString.toString();
    }
}
@
