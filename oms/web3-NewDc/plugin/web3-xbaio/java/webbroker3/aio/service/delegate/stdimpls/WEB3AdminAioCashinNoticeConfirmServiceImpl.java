head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeConfirmServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認サービス実装クラス(WEB3AdminAioCashinNoticeConfirmServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 韋念瓊 (中訊) 新規作成   
                   2004/10/27 黄建(中訊) レビュー                                
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.aio.data.DepositInformRow;
import webbroker3.aio.define.WEB3OutPutDivDef;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmListRequest;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmListResponse;
import webbroker3.aio.message.WEB3AioCashinNoticeUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (入金連絡確認サービスImpl)<BR>
 * 入金連絡確認サービス実装クラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashinNoticeConfirmServiceImpl extends WEB3ClientRequestService implements WEB3AdminAioCashinNoticeConfirmService 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmServiceImpl.class);
       
    /**
     * 入金連絡確認サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入金連絡確認）一覧画面表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 410755600136
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
        WEB3AdminAioCashinConfirmListRequest l_cashinConfirmListRequest = null;
        WEB3AdminAioCashinConfirmListResponse l_cashinConfirmListResponse = null;        
        
        if (l_request instanceof WEB3AdminAioCashinConfirmListRequest)
        {
            //リクエストデータの具象データ型が「 入金連絡確認入力リクエスト 」の場合
            l_cashinConfirmListRequest = 
                (WEB3AdminAioCashinConfirmListRequest) l_request;
        }
        else
        {
            log.debug("Error[入力値が不正です]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 入力内容チェック 
        l_cashinConfirmListRequest.validate();
        
        //1.2 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
               
        //1.3 該当の管理者がこの機@能が使えるか権限チェックを行う。 
        //validate権限(String, boolean)
        //[引数] 
        //機@能カテゴリコード： ”B0301”
        //is更新： false 
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.AIO_CASH_IN_NOTICE,
            false);
        
        //1.4 該当の管理者が指定した部店に対する処理権限があるかチェックする。 
        //validate部店権限(String)
        //[引数] 
        //部店コード： リクエストデータ.部店コード 
        l_web3Administrator.validateBranchPermission(
            l_cashinConfirmListRequest.branchCode);
        
        //1.5 証券会社コードを取得する。
        String l_strInstitutionCode = null;
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //1.6 データ取得条件の文字列を生成する。
        //［引数］ 
        //リクエストデータ： 入金連絡確認一覧リクエスト 

        String l_strWhereClause = this.createGetCondCharacterString(
            l_cashinConfirmListRequest);
        
        String l_strOrderBy = "worked_timestamp desc";
        
        //1.7 データ取得条件のデータコンテナを生成する。 
        //［引数］ 
        //リクエストデータ： リクエストデータ 
        //証券会社コード： get証券会社コード()の戻り値 
    
        Object l_bindVars[] = this.createGetCondDataContainer(
            l_cashinConfirmListRequest, 
            l_strInstitutionCode);
            
        int l_intPageSize = 
            Integer.parseInt(l_cashinConfirmListRequest.pageSize);
            
        int l_intPageIndex = 
            Integer.parseInt(l_cashinConfirmListRequest.pageIndex);        
        
        List l_lisRows = null;
        
        log.debug("リクエストデータ.出力区分 = " + 
                l_cashinConfirmListRequest.outputDiv);
        
        WEB3PageIndexInfo l_listViewPageIndexInfo = null;
        //リクエストデータ.出力区分 = '0'（一覧） の場合、実施        
        if (WEB3OutPutDivDef.LIST_VIEW.equals(
                l_cashinConfirmListRequest.outputDiv))
        {        
            //1.8 1.9 入金連絡テーブルから、レコードを取得する。 
            //（ページング処理あり） 
    
            //［引数］ 
            //Rowタイプ： 入金連絡Row.TYPE 
            //Where： create取得条件文字列()の戻り値 
            //orderBy： "worked_timestamp desc" 
            //condition： null 
            //リスト： create取得条件データコンテナ()の戻り値 
            //ページサイズ： リクエストデータ.ページ内表示行数 
            //ページ番号： リクエストデータ.要求ページ番号 - 1        

            try
            {
                List l_lisDepositInformRows = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        DepositInformRow.TYPE,
                        l_strWhereClause,  
                        l_strOrderBy,                   
                        null,
                        l_bindVars);
//                        l_intPageSize, 
//                        l_intPageIndex - 1);
                        
                DepositInformRow[] l_depositInformRows = new DepositInformRow[l_lisDepositInformRows.size()];
                l_lisDepositInformRows.toArray(l_depositInformRows);
                
                l_listViewPageIndexInfo = new WEB3PageIndexInfo(
                        l_depositInformRows, 
                        l_intPageIndex, 
                        l_intPageSize);   
                
                l_lisRows = l_listViewPageIndexInfo.getListReturned();
                    
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
        }
        //リクエストデータ.出力区分 = '1'（CSV） の場合、実施
        else if (WEB3OutPutDivDef.CSV_VIEW.equals(
                    l_cashinConfirmListRequest.outputDiv))
        {
            //1.10 入金連絡テーブルから、レコードを取得する。 
            //（ページング処理なし） 

            //［引数］ 
            //Rowタイプ： 入金連絡Row.TYPE 
            //Where： create取得条件文字列()の戻り値 
            //orderBy： "worked_timestamp desc" 
            //condition： null 
            //リスト： create取得条件データコンテナ()の戻り値 

            try
            {
                l_lisRows = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        DepositInformRow.TYPE, 
                        l_strWhereClause,  
                        l_strOrderBy, 
                        null,
                        l_bindVars);
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
        }
                
        log.debug("search 入金連絡テーブル.size = " + l_lisRows.size());
        //1.11 ArrayListインスタンスを生成する。
        List l_lisAioCashinNoticeUnit = new ArrayList();
        
        //1.12 取得した要素分のLoop処理

        for (int i = 0; i < l_lisRows.size(); i++)
        {
            DepositInformRow l_depositInformRow = (DepositInformRow) l_lisRows.get(i);
            
            log.debug("入金連絡テーブルRow [" + i + "] = " + l_depositInformRow);
            
            //1.12.1 入金連絡明細インスタンスを生成する。
            //［引数］ 
            //証券会社： 管理者.get証券会社()の戻り値 
            //入金連絡Params： 入金連絡Paramsオブジェクト 
            
            WEB3AioCashinNoticeUnit l_web3AioCashinNoticeUnit = 
                this.createCashinNoticeUnits(
                    l_web3Administrator.getInstitution(), l_depositInformRow);
            
            //1.12.2 入金連絡明細をリストに追加する。
            //［引数］ 
            //arg0： 入金連絡明細オブジェクト 

            l_lisAioCashinNoticeUnit.add(l_web3AioCashinNoticeUnit); 
        }
        
        WEB3AioCashinNoticeUnit[] l_web3AioCashinNoticeUnits =  
            new WEB3AioCashinNoticeUnit[l_lisAioCashinNoticeUnit.size()];

        //1.13 入金連絡明細の配列を取得する。
        l_lisAioCashinNoticeUnit.toArray(l_web3AioCashinNoticeUnits);
        
        int l_intTotalPage = 0;
        int l_intTotalRecords = 0;
        
        String l_strFinInsNameDanji = null;
        
        //1.14 リクエストデータ.出力区分 = '0'（一覧） の場合、実施        
        if (WEB3OutPutDivDef.LIST_VIEW.equals(
                l_cashinConfirmListRequest.outputDiv))
        {
            //1.14.1 表示ページ番号を取得する。
            l_intPageIndex = l_listViewPageIndexInfo.getPageIndex();                    

            //1.14.2 総ページ数を取得する。 
            l_intTotalPage = l_listViewPageIndexInfo.getTotalPages();
            
            //1.14.3 総レコード数を取得する。
            l_intTotalRecords = l_listViewPageIndexInfo.getTotalRecords();
            
            log.debug("表示ページ番号 = " + l_intPageIndex);
            log.debug("総ページ数 = " + l_intTotalPage);
            log.debug("総レコード数 = " + l_intTotalRecords);            
        }
        
        log.debug("リクエストデータ.振込先金融機@関コード" + 
                l_cashinConfirmListRequest.finInstitutionCode);
        
        //1.15 リクエストデータ.振込先金融機@関コード != null の場合、実施        
        if (l_cashinConfirmListRequest.finInstitutionCode != null)
        {
            //1.15.1 金融機@関インスタンスを生成する。 

            //［引数］ 
            //証券会社コード： get証券会社コード()の戻り値 
            //金融機@関コード： リクエストデータ.振込先金融機@関コード 
            
            WEB3GentradeFinInstitution l_web3GentradeFinInstitution = 
                new WEB3GentradeFinInstitution(
                    l_strInstitutionCode, 
                    l_cashinConfirmListRequest.finInstitutionCode);
            
            //1.15.2 金融機@関名（漢字）を取得する。
            l_strFinInsNameDanji = l_web3GentradeFinInstitution.getFinInstitutionNameKanji();
            log.debug("金融機@関名（漢字） = " + l_strFinInsNameDanji);
        }
        
        //1.16 レスポンスデータを生成する。
        l_cashinConfirmListResponse = (WEB3AdminAioCashinConfirmListResponse) 
            l_request.createResponse(); 
        
        //1.17  プロパティセット
        
        //レスポンス.部店コード = リクエストデータ.部店コード
        l_cashinConfirmListResponse.branchCode = 
            l_cashinConfirmListRequest.branchCode;       
            
        log.debug("レスポンス.部店コード =" + l_cashinConfirmListResponse.branchCode);
        
        //レスポンス.顧客コード（自） = リクエストデータ.顧客コード（自）
        l_cashinConfirmListResponse.minAccountCode = 
            l_cashinConfirmListRequest.minAccountCode;

        log.debug("レスポンス.顧客コード（自）=" + l_cashinConfirmListResponse.minAccountCode);
        
        //レスポンス.顧客コード（至） = リクエストデータ.顧客コード（至）
        l_cashinConfirmListResponse.maxAccountCode = 
            l_cashinConfirmListRequest.maxAccountCode;
        
        log.debug("レスポンス.顧客コード（至）=" + l_cashinConfirmListResponse.maxAccountCode);
        
        //レスポンス.連絡日時（自） = リクエストデータ.連絡日時（自）
        l_cashinConfirmListResponse.minNoticeDate = 
            l_cashinConfirmListRequest.minNoticeDate;
            
        log.debug("レスポンス.連絡日時（自）=" + l_cashinConfirmListResponse.minNoticeDate);
        
        //レスポンス.連絡日時（至） = リクエストデータ.連絡日時（至）
        l_cashinConfirmListResponse.maxNoticeDate = 
            l_cashinConfirmListRequest.maxNoticeDate;
            
        log.debug("レスポンス.連絡日時（至）=" + l_cashinConfirmListResponse.maxNoticeDate);
        
        //レスポンス.振込日（自） = リクエストデータ.振込日（自）
        l_cashinConfirmListResponse.minTransferDate = 
            l_cashinConfirmListRequest.minTransferDate;
            
        log.debug("レスポンス.振込日（自）=" + l_cashinConfirmListResponse.minTransferDate);
        
        //レスポンス.振込日（至） = リクエストデータ.振込日（至）
        l_cashinConfirmListResponse.maxTransferDate = 
            l_cashinConfirmListRequest.maxTransferDate;
            
        log.debug("レスポンス.振込日（至）=" + l_cashinConfirmListResponse.maxTransferDate);
        
        //レスポンス.振込先金融機@関コード = リクエストデータ.振込先金融機@関コード
        l_cashinConfirmListResponse.finInstitutionCode = 
            l_cashinConfirmListRequest.finInstitutionCode;
            
        log.debug("レスポンス.振込先金融機@関コード =" + l_cashinConfirmListResponse.finInstitutionCode);
        
        //レスポンス.振込先金融機@関名 = （以下のとおり）
        //リクエストデータ.振込先金融機@関コード = null の場合、null

        if (l_cashinConfirmListRequest.finInstitutionCode == null)
        {
            l_cashinConfirmListResponse.finInstitutionName = null; 
        }
        //リクエストデータ.振込先金融機@関コード != null の場合、
        //    金融機@関.get金融機@関名（漢字）()の戻り値
        else
        {
            l_cashinConfirmListResponse.finInstitutionName = l_strFinInsNameDanji;
        }
        log.debug("レスポンス.振込先金融機@関名 =" + l_cashinConfirmListResponse.finInstitutionName);
        
        //レスポンス.件数 = ListPage.totalSize()の戻り値
        if (WEB3OutPutDivDef.LIST_VIEW.equals(
                l_cashinConfirmListRequest.outputDiv))
        {
            l_cashinConfirmListResponse.outputNumber = l_listViewPageIndexInfo.getTotalRecords() + "";        
        }
        else
        {
            l_cashinConfirmListResponse.outputNumber = l_lisRows.size() + "";
        }
        log.debug("レスポンス.件数 =" + l_cashinConfirmListResponse.outputNumber);
        
        //レスポンス.入金連絡明細一覧 = 入金連絡明細［］
        l_cashinConfirmListResponse.cashinNoticeUnits = l_web3AioCashinNoticeUnits;
                    
        //レスポンス.出力区分 = リクエストデータ.出力区分
        l_cashinConfirmListResponse.outputDiv = 
            l_cashinConfirmListRequest.outputDiv;
            
        //※レスポンス.出力区分 = '0'（一覧）の場合
        if (WEB3OutPutDivDef.LIST_VIEW.equals(
                l_cashinConfirmListRequest.outputDiv))
        {
            log.debug("レスポンス.出力区分 = '0'（一覧）の場合");
            l_cashinConfirmListResponse.pageIndex = l_intPageIndex + "";
                
            l_cashinConfirmListResponse.totalPages = l_intTotalPage + "";
            
            l_cashinConfirmListResponse.totalRecords = l_intTotalRecords + "";
        }
        //※レスポンス.出力区分 = '1'（CSV）の場合
        //レスポンス.表示ページ番号 = null
        //レスポンス.総ページ数 = null
        //レスポンス.総レコード数 = null
        else 
        {
            log.debug("レスポンス.出力区分 = '1'（CSV）の場合");
            l_cashinConfirmListResponse.pageIndex = null;
                
            l_cashinConfirmListResponse.totalPages = null;
            
            l_cashinConfirmListResponse.totalRecords = null;
        }
        
        log.debug("レスポンス.表示ページ番号 =" + l_cashinConfirmListResponse.pageIndex);
        log.debug("レスポンス.総ページ数 =" + l_cashinConfirmListResponse.totalPages);
        log.debug("レスポンス.総レコード数 =" + l_cashinConfirmListResponse.totalRecords);
        
        log.exiting(STR_METHOD_NAME);
        return l_cashinConfirmListResponse;
    }
    
    /**
     * (create取得条件文字列)<BR>
     * リクエストデータから、データ取得条件文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）証券会社コード条件を生成する。<BR>
     * <BR>
     *   条件文字列： "institution_code=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ３）部店コード条件生成<BR>
     * <BR>
     *   条件文字列： " and branch_code=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ４）顧客コード条件生成<BR>
     * <BR>
     * ４－１）引数.リクエストデータ.顧客コード（自） != <BR>
     * null and 引数.リクエストデータ.顧客コード（至） != null の場合<BR>
     * <BR>
     *   条件文字列： " and account_code>=? and account_code<=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ４－２）引数.リクエストデータ.顧客コード（自） !=<BR>
     *  null and 引数.リクエストデータ.顧客コード（至） = null の場合<BR>
     * <BR>
     *   条件文字列： " and account_code=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５）連絡日時条件生成<BR>
     * <BR>
     * ５－１）引数.リクエストデータ.連絡日時（自） !=<BR>
     *  null and 引数.リクエストデータ.連絡日時（至） != null の場合<BR>
     * <BR>
     *   条件文字列： " and worked_timestamp>=? and<BR> worked_timestamp<=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５－２）引数.リクエストデータ.連絡日時（至） = null の場合<BR>
     * <BR>
     *   条件文字列： " and worked_timestamp>=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ６）振込日条件生成<BR>
     * <BR>
     * ６－１）引数.リクエストデータ.振込日（自） != <BR>
     * null and 引数.リクエストデータ.振込日（至） != null の場合<BR>
     * <BR>
     *   条件文字列： " and transfer_date>=? and transfer_date<=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ６－２）引数.リクエストデータ.振込日（自） !=<BR>
     *  null and 引数.リクエストデータ.振込日（至） = null の場合<BR>
     * <BR>
     *   条件文字列： " and transfer_date>=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ７）振込先金融機@関条件生成<BR>
     * <BR>
     *   引数.リクエストデータ.振込先金融機@関コード != null の場合<BR>
     * <BR>
     *   条件文字列： " and fin_institution_code=?"<BR>
     * <BR>
     *   上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ８）生成された文字列を返却する。
     * @@param l_request - (リクエストデータ)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4108B52800FA
     */
    protected String createGetCondCharacterString(
        WEB3AdminAioCashinConfirmListRequest l_request) 
    {
        final String STR_METHOD_NAME = "createGetCondCharacterString(" + 
            "WEB3AdminAioCashinConfirmListRequest l_request)";
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
        
        //２）証券会社コード条件を生成する。
        l_strWhereCondition += "institution_code = ?";
        
        //３）部店コード条件生成
        l_strWhereCondition += " and branch_code = ?";
        
        //４）顧客コード条件生成 
        //４－１）引数.リクエストデータ.顧客コード（自） != null and 
        //       引数.リクエストデータ.顧客コード（至） != null の場合 
        //条件文字列： " and account_code>=? and account_code<=?" 

        if (l_request.minAccountCode != null &&
            l_request.maxAccountCode != null)
        {        
            l_strWhereCondition += " and substr(account_code, 0, 6) >= ? and substr(account_code, 0, 6) <= ? ";
        }
        //４－２）引数.リクエストデータ.顧客コード（自） != null and 
        //       引数.リクエストデータ.顧客コード（至） = null の場合 
        //条件文字列： " and account_code=?" 

        if (l_request.minAccountCode != null &&
            l_request.maxAccountCode == null)
        {
            l_strWhereCondition += " and substr(account_code, 0, 6) = ?";
        }

        //５）連絡日時条件生成 
        
        //５－１）引数.リクエストデータ.連絡日時（自） != null and 
        //       引数.リクエストデータ.連絡日時（至） != null の場合 
        //条件文字列： " and worked_timestamp>=? and worked_timestamp<=?" 
        
        if (l_request.minNoticeDate != null &&
            l_request.maxNoticeDate != null)
        {        
            l_strWhereCondition += " and worked_timestamp >= ? and worked_timestamp <= ?";
        }

        //５－２）引数.リクエストデータ.連絡日時（至） = null の場合 
        //条件文字列： " and worked_timestamp>=?" 
        
        if (l_request.maxNoticeDate == null)
        {
            l_strWhereCondition += " and worked_timestamp >= ?";
        }
        
        //６）振込日条件生成 

        //６－１）引数.リクエストデータ.振込日（自） != null and 
        //       引数.リクエストデータ.振込日（至） != null の場合 
        //条件文字列： " and transfer_date>=? and transfer_date<=?" 
        
        if (l_request.minTransferDate != null &&
            l_request.maxTransferDate != null)
        {        
            l_strWhereCondition += 
                " and to_char(transfer_date, 'yyyyMMdd') >= to_char(?, 'yyyyMMdd')" +
                " and to_char(transfer_date, 'yyyyMMdd') <= to_char(?, 'yyyyMMdd')";
        }
        
        //６－２）引数.リクエストデータ.振込日（自） != null and 
        //       引数.リクエストデータ.振込日（至） = null の場合 
        //条件文字列： " and transfer_date>=?" 
        
        if (l_request.minTransferDate != null &&
            l_request.maxTransferDate == null)
        {        
            l_strWhereCondition += " and to_char(transfer_date, 'yyyyMMdd') >= to_char(?, 'yyyyMMdd')";
        }

        //７）振込先金融機@関条件生成 

        //引数.リクエストデータ.振込先金融機@関コード != null の場合 
        //条件文字列： " and fin_institution_code=?" 
        
        if (l_request.finInstitutionCode != null)
        {
            l_strWhereCondition += " and fin_institution_code = ?";
        }
        
        log.debug("条件文字列 = " + l_strWhereCondition);
        
        //８）生成された文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strWhereCondition;
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータコンテナを生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     *   引数.証券会社コードをArrayListに追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     *   引数.リクエストデータ.部店コードをArrayListに追加する。<BR>
     * <BR>
     * ４）顧客コード<BR>
     * <BR>
     * ４－１）引数.リクエストデータ.顧客コード（自） != <BR>
     * null and 引数.リクエストデータ.顧客コード（至） != null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.顧客コード（自）、<BR>
     * 引数.リクエストデータ.顧客コード（至）をArrayListに追加する。<BR>
     * <BR>
     * ４－２）引数.リクエストデータ.顧客コード（自） != <BR>
     * null and 引数.リクエストデータ.顧客コード（至） = null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.顧客コード（自）をArrayListに追加する。<BR>
     * <BR>
     * ５）連絡日時条件生成<BR>
     * <BR>
     * ５－１）引数.リクエストデータ.連絡日時（自） !=<BR>
     *  null and 引数.リクエストデータ.連絡日時（至） != null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.連絡日時（自）、<BR>
     * 引数.リクエストデータ.連絡日時（至）をArrayListに追加する。<BR>
     * <BR>
     * ５－２）引数.リクエストデータ.連絡日時（自） != <BR>
     * null and 引数.リクエストデータ.連絡日時（至） = null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.連絡日時（自）をArrayListに追加する。<BR>
     * <BR>
     * ５－３）引数.リクエストデータ.連絡日時（自） = <BR>
     * null and 引数.リクエストデータ.連絡日時（至） = null の場合<BR>
     * <BR>
     * ５－３－１）前日営業日を算出する。<BR>
     * <BR>
     *    営業日計算.calc営業日()<BR>
     * <BR>
     *    ［引数］<BR>
     *    基準日： システムタイムスタンプ<BR>
     *    加算／減算日数： -1<BR>
     * <BR>
     * ５－３－２）入金受付締切時刻を取得する。<BR>
     * <BR>
     *    取引時間管理.get市場閉局時間()<BR>
     * <BR>
     *    ［引数］<BR>
     *    市場コード： 0（DEFAULT)<BR>
     *    商品コード： 0（DEFAULT)<BR>
     * <BR>
     * ５－３－３）前営業日日付と締切時刻を編集した日時をArrayListに追加する。<BR>
     * <BR>
     * ６）振込日条件生成<BR>
     * <BR>
     * ６－１）引数.リクエストデータ.振込日（自） !=<BR>
     *  null and 引数.リクエストデータ.振込日（至） != null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.振込日（自）、<BR>
     * 引数.リクエストデータ.振込日（至）をArrayListに追加する。<BR>
     * <BR>
     * ６－２）引数.リクエストデータ.振込日（自） != <BR>
     * null and 引数.リクエストデータ.振込日（至） = null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.振込日（自）をArrayListに追加する。<BR>
     * <BR>
     * ７）振込先金融機@関条件生成<BR>
     * <BR>
     *   引数.リクエストデータ.振込先金融機@関コード != null の場合<BR>
     * <BR>
     *   引数.リクエストデータ.振込先金融機@関コードをArrayListに追加する。<BR>
     * <BR>
     * ８）ArrayLisｔ..toArray()の戻り値を返却する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 4108BA730187
     */
    protected Object[] createGetCondDataContainer(
        WEB3AdminAioCashinConfirmListRequest l_request, 
        String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer(" + 
            "WEB3AdminAioCashinConfirmListRequest l_request, String l_strInstitutionCode";
        log.entering(STR_METHOD_NAME);        
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）空のArrayListを生成する。
        List l_lisDepositInform = null;
        l_lisDepositInform = new ArrayList();

        //２）証券会社コード 
        //引数.証券会社コードをArrayListに追加する。 
        l_lisDepositInform.add(l_strInstitutionCode);
        
        //３）部店コード 
        //引数.リクエストデータ.部店コードをArrayListに追加する。 
        l_lisDepositInform.add(l_request.branchCode);
        
        //４）顧客コード 

        //４－１）引数.リクエストデータ.顧客コード（自） != null and 
        //       引数.リクエストデータ.顧客コード（至） != null の場合
        //引数.リクエストデータ.顧客コード（自）、
        //引数.リクエストデータ.顧客コード（至）をArrayListに追加する。
        
        if (l_request.minAccountCode != null &&
            l_request.maxAccountCode != null)
        {
            l_lisDepositInform.add(l_request.minAccountCode);
            l_lisDepositInform.add(l_request.maxAccountCode);
        }
        
        //４－２）引数.リクエストデータ.顧客コード（自） != null and 
        //       引数.リクエストデータ.顧客コード（至） = null の場合
        //       引数.リクエストデータ.顧客コード（自）をArrayListに追加する。 

        if (l_request.minAccountCode != null &&
            l_request.maxAccountCode == null)
        {
            l_lisDepositInform.add(l_request.minAccountCode);
        }
    
        //５）連絡日時条件生成 

        //５－１）引数.リクエストデータ.連絡日時（自） != null and 
        //       引数.リクエストデータ.連絡日時（至） != null の場合
        //引数.リクエストデータ.連絡日時（自）、
        //引数.リクエストデータ.連絡日時（至）をArrayListに追加する。 

        if (l_request.minNoticeDate != null &&
            l_request.maxNoticeDate != null)
        {
            l_lisDepositInform.add(l_request.minNoticeDate);
            l_lisDepositInform.add(l_request.maxNoticeDate);
        }
        
        //５－２）引数.リクエストデータ.連絡日時（自） != null and 
        //       引数.リクエストデータ.連絡日時（至） = null の場合 
        //引数.リクエストデータ.連絡日時（自）をArrayListに追加する。 

        if (l_request.minNoticeDate != null &&
            l_request.maxNoticeDate == null)
        {
            l_lisDepositInform.add(l_request.minNoticeDate);
        }
        
        //５－３）引数.リクエストデータ.連絡日時（自） = null and 
        //       引数.リクエストデータ.連絡日時（至） = null の場合 
        //５－３－１）前日営業日を算出する。
        //営業日計算.calc営業日() 
        //［引数］ 
        //基準日： システムタイムスタンプ 
        //加算／減算日数： -1 
        
        if (l_request.minNoticeDate == null &&
            l_request.maxNoticeDate == null)
        {
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(
                    GtlUtils.getTradingSystem().getSystemTimestamp());
        
            Timestamp l_datBizDate = l_gentradeBizDate.roll(-1); 
            log.debug("前日営業日 = " + l_datBizDate);
            
            //５－３－２）入金受付締切時刻を取得する。 
    
            //取引時間管理.get市場閉局時間()
            //［引数］ 
            //市場コード： 0（DEFAULT) 
            //商品コード： 0（DEFAULT) 
            
            String l_strTradeCloseTime =
                WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                    WEB3MarketCodeDef.DEFAULT, WEB3ProductCodeDef.DEFAULT);
                                
            log.debug("入金受付締切時刻 = " + l_strTradeCloseTime);
            
            //５－３－３）前営業日日付と締切時刻を編集した日時をArrayListに追加する。

            String l_strDate = WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd") +  
                l_strTradeCloseTime;
            
            log.debug("前営業日日付と締切時刻を編集した日時 = " + l_strDate);
            
            Date l_datWorkedTimestamp = WEB3DateUtility.getDate(l_strDate, "yyyyMMddHHmmss");
            
            l_lisDepositInform.add(l_datWorkedTimestamp);
                    
        }
        
        //６）振込日条件生成 

        //６－１）引数.リクエストデータ.振込日（自） != null and 
        //       引数.リクエストデータ.振込日（至） != null の場合
        //引数.リクエストデータ.振込日（自）、
        //引数.リクエストデータ.振込日（至）をArrayListに追加する。
        
        if (l_request.minTransferDate != null &&
            l_request.maxTransferDate != null)
        {
            l_lisDepositInform.add(l_request.minTransferDate);
            l_lisDepositInform.add(l_request.maxTransferDate);   
        }
        
        //６－２）引数.リクエストデータ.振込日（自） != null and 
        //       引数.リクエストデータ.振込日（至） = null の場合 
        //引数.リクエストデータ.振込日（自）をArrayListに追加する。
        
        if (l_request.minTransferDate != null &&
            l_request.maxTransferDate == null)
        {
            l_lisDepositInform.add(l_request.minTransferDate);           
        }

        //７）振込先金融機@関条件生成 

        //引数.リクエストデータ.振込先金融機@関コード != null の場合 
        //引数.リクエストデータ.振込先金融機@関コードをArrayListに追加する。 
        
        if (l_request.finInstitutionCode != null)
        {
            l_lisDepositInform.add(l_request.finInstitutionCode);           
        }
        
        //８）ArrayList.toArray()の戻り値を返却する。 
        Object[] l_bindVars = 
            new Object[l_lisDepositInform.size()];
            
        l_lisDepositInform.toArray(l_bindVars);
        
        //debug =====================
        if (l_bindVars != null)
        {
            for (int i = 0; i < l_bindVars.length; i++)
            {
                log.debug("l_bindVars[" + i + "] = " + l_bindVars[i]);
            }
        }
        //============================
        
        log.exiting(STR_METHOD_NAME);
        return l_bindVars;
    }
    
    /**
     * (create入金連絡明細)<BR>
     * 入金連絡明細オブジェクトを生成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入金連絡確認）create入金連絡明細」 参照<BR>
     * @@param l_institution - (証券会社オブジェクト)<BR>
     * @@param l_depositInformRow - (入金連絡Row)<BR>
     * 入金連絡Paramsオブジェクト<BR>
     * @@return WEB3AioCashinNoticeUnit
     * @@throws WEB3BaseException
     * @@roseuid 4109C8B50240
     */
    protected WEB3AioCashinNoticeUnit createCashinNoticeUnits(
        Institution l_institution, 
        DepositInformRow l_depositInformRow) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCashinNoticeUnits(" + 
            "Institution l_institution, DepositInformRow l_depositInformRow)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null || l_depositInformRow == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 入金連絡明細インスタンスを生成する。
        WEB3AioCashinNoticeUnit l_web3AioCashinNoticeUnit = 
            new WEB3AioCashinNoticeUnit();
        
        String l_strFamilyNameAlt1 = null;
        
        try
        {
            //1.2 顧客インスタンスを生成する。
            //［引数］ 
            //証券会社ID： 引数.証券会社.getInstitutionId()の戻り値 
            //部店コード： 引数.入金連絡Params.部店コード 
            //顧客コード： 引数.入金連絡Params.顧客コード 
            AccountManager l_accountManager = GtlUtils.getAccountManager();  
            WEB3GentradeMainAccount l_web3MainAcc = (WEB3GentradeMainAccount)
                l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(), 
                    l_depositInformRow.getBranchCode(), 
                    l_depositInformRow.getAccountCode()); 
            
            MainAccountRow l_mainAccountRow = (MainAccountRow)
                l_web3MainAcc.getDataSourceObject();
            
            //名前（苗字1）を取得する。
            l_strFamilyNameAlt1 = l_mainAccountRow.getFamilyNameAlt1();
            
        }
        catch (NotFoundException l_ex)
        {
            log.error("顧客インスタンスを生成する:", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }    
        //1.3 金融機@関インスタンスを生成する。 
        //［引数］ 
        //証券会社コード： 引数.証券会社.getInstitutionCode()の戻り値 
        //金融機@関コード： 引数.入金連絡Params.金融機@関コード 
        WEB3GentradeFinInstitution l_web3GentradeFinInstitution = 
            new WEB3GentradeFinInstitution(
                l_institution.getInstitutionCode(), 
                l_depositInformRow.getFinInstitutionCode());
            
        //1.4 金融機@関名（漢字）を取得する。
        String l_strFinInsNameDanji = 
            l_web3GentradeFinInstitution.getFinInstitutionNameKanji();
        
        log.debug("金融機@関名（漢字） = " + l_strFinInsNameDanji);
        
        //1.5 プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。
        
        //入金連絡明細.部店コード = 引数.入金連絡Params.部店コード
        l_web3AioCashinNoticeUnit.branchCode = l_depositInformRow.getBranchCode();
        
        //入金連絡明細.顧客コード = 引数.入金連絡Params.顧客コード
        l_web3AioCashinNoticeUnit.accountCode = l_depositInformRow.getAccountCode().substring(0,6);
        
        //入金連絡明細.顧客名 = 顧客Params.名前（苗字1）        
        l_web3AioCashinNoticeUnit.accountName = l_strFamilyNameAlt1;
        
        //入金連絡明細.入金額 = 引数.入金連絡Params.入金額
        l_web3AioCashinNoticeUnit.cashinAmt = l_depositInformRow.getAmount() + "";
        
        //入金連絡明細.振込先金融機@関名 = 金融機@関.get金融機@関名（漢字）()の戻り値
        l_web3AioCashinNoticeUnit.finInstitutionName = l_strFinInsNameDanji;
        
        //入金連絡明細.振込日 = 引数.入金連絡Params.振込日
        l_web3AioCashinNoticeUnit.transferDate = l_depositInformRow.getTransferDate();
        
        //入金連絡明細.連絡日時 = 引数.入金連絡Params.作業日時
        l_web3AioCashinNoticeUnit.noticeDate = l_depositInformRow.getWorkedTimestamp();
        
        //入金連絡明細.識別コード = 引数.入金連絡Params.識別コード
        l_web3AioCashinNoticeUnit.orderRequestNumber = 
            l_depositInformRow.getOrderRequestNumber();
        
        log.exiting(STR_METHOD_NAME);
        return l_web3AioCashinNoticeUnit;
    }
}
@
