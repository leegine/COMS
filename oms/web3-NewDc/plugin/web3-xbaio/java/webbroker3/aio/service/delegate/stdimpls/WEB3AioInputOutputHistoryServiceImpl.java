head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputOutputHistoryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴サービスImpl(WEB3AioInputOutputHistoryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 艾興 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.aio.data.SecReceiptDeliveryActionRow;
import webbroker3.aio.define.WEB3AioInputOutputSortkeyItemDef;
import webbroker3.aio.define.WEB3InformInputOutputGroupDef;
import webbroker3.aio.define.WEB3InformProductGroupDef;
import webbroker3.aio.message.WEB3AioHistoryUnit;
import webbroker3.aio.message.WEB3AioInputOutputHistoryListRequest;
import webbroker3.aio.message.WEB3AioInputOutputHistoryListResponse;
import webbroker3.aio.message.WEB3AioSortKeyUnit;
import webbroker3.aio.service.delegate.WEB3AioInputOutputHistoryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入出庫履歴サービスImpl)<BR>
 * 入出庫履歴サービス実装クラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3AioInputOutputHistoryServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AioInputOutputHistoryService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3AioInputOutputHistoryServiceImpl.class);

    /**
     * @@roseuid 41EC855C0251
     */
    public WEB3AioInputOutputHistoryServiceImpl()
    {

    }

    /**
     * 入出庫履歴サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（入出庫履歴）履歴画面表示データ取得」 参照
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41B7B6D901A1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AioInputOutputHistoryListRequest l_listRequest = (WEB3AioInputOutputHistoryListRequest)l_request;
        try
        {
            //1.1validate( )
            l_listRequest.validate();
            //1.2validate注文受付可能( )(
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //get補助口座(補助口座タイプ : SubAccountTypeEnum)
            // [引数]
            //   補助口座タイプ： ”預り金口座”
            SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //1.3create取得条件文字列(String, String, String)(
            //商品グループ： リクエストデータ.商品グループ 
            //銘柄コード： リクエストデータ.銘柄コード 
            //出入グループ： リクエストデータ.出入グループ 
            String l_strGetCondString = this.createGetCondCharacterString(l_listRequest.productGroup,
                l_listRequest.productCode,
                l_listRequest.inputOutputGroup);
                
            //1.4リクエストデータ.表示期間（至） != null or (*1)-①@ != null の場合、実施
            //   *1)リクエストデータ.表示期間（自） == null and リクエストデータ.表示期間（至） == null の場合
            //      １）入出庫履歴テーブルの該当顧客のレコードのうち直近の受渡日(=①@)と、
            //          その1ヶ月前の日付(=②)を取得する。
            //      ２）表示期間（自） = ②、表示期間（至） = ①@とする。
            SecReceiptDeliveryActionRow l_actionRow = null;
            Date l_datDisplayStartDate = null;
            Date l_datDisplayEndDate = null;
            Date l_datFrom = null;
            Date l_datTo = null;
            WEB3AioHistoryUnit[] l_historyUnit = null;
            int l_intPageNumber = 0;
            int l_intTotalPages = 0;
            int l_intTotalSize = 0;
            QueryProcessor l_QueryProcessor = null; 
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            Object[] l_obj = new Object[3];
            l_obj[0] = new String(l_acc.getAccountCode().substring(0, 6));
            l_obj[1] = new String(l_acc.getInstitution().getInstitutionCode());
            l_obj[2] = new String(l_acc.getBranch().getBranchCode());

            l_QueryProcessor = Processors.getDefaultProcessor();
            List l_listSecReceiptDeliveryAction= l_QueryProcessor.doFindAllQuery(
                SecReceiptDeliveryActionRow.TYPE,
                " account_code like ? || '%' " +
                "and institution_code = ? " +
                "and branch_code = ? " +
                "and delivery_date is not null " +
                "and product_group is not null " +
                "and sec_receipt_delivery_group is not null " +
                "and io_group is not null",
                "delivery_date DESC nulls last ",
                null,
                l_obj);
            if (l_listSecReceiptDeliveryAction != null && l_listSecReceiptDeliveryAction.size() > 0)
            {
                l_actionRow = (SecReceiptDeliveryActionRow)l_listSecReceiptDeliveryAction.get(0); 
            }
            else
            {
                WEB3AioInputOutputHistoryListResponse l_response 
                    = (WEB3AioInputOutputHistoryListResponse)l_listRequest.createResponse();
                l_response.HistoryUnits = new WEB3AioHistoryUnit[0];
                l_response.pageIndex = "0";
                l_response.totalPages = "0";
                l_response.totalRecords = "0";
                return l_response;
            }


            boolean l_blnResult = (l_actionRow.getDeliveryDate() != null);
            Calendar l_calendar = new GregorianCalendar();
            l_calendar.setTime(l_actionRow.getDeliveryDate());

            l_calendar.add(Calendar.MONTH,-1);
            l_datFrom = l_calendar.getTime();
            l_datTo = WEB3DateUtility.toDay(l_actionRow.getDeliveryDate());
            
            if ((l_listRequest.displayStartDate != null) || l_blnResult)
            {
                //1.4.1create取得条件データコンテナ(Date, Date, String, String, String)(
                //表示期間（自）： （以下のとおり）
                //  リクエストデータ.表示期間（自） != null の場合、リクエストデータ.表示期間（自）
                //  リクエストデータ.表示期間（自） == null の場合、(*1)-②
                //表示期間（至）： （以下のとおり）
                //  リクエストデータ.表示期間（至） != null の場合、リクエストデータ.表示期間（至）
                //  リクエストデータ.表示期間（至） == null の場合、(*1)-①@
                //商品グループ： リクエストデータ.商品グループ
                //銘柄コード： リクエストデータ.銘柄コード
                //出入グループ： リクエストデータ.出入グループ

                if (l_listRequest.displayStartDate != null)
                {
                    l_datDisplayStartDate = l_listRequest.displayStartDate;
                }
                else
                {
                    l_datDisplayStartDate = l_datFrom;
                }
                if (l_listRequest.displayEndDate != null)
                {
                    l_datDisplayEndDate = l_listRequest.displayEndDate;
                }
                else
                {
                    l_datDisplayEndDate = l_datTo;
                }
                Object[] l_objCondDataContainer = this.createGetCondDataContainer(
                    l_subAccount,
                    l_datDisplayStartDate,
                    l_datDisplayEndDate,
                    l_listRequest.productGroup,
                    l_listRequest.productCode,
                    l_listRequest.inputOutputGroup);

                //1.4.2createソート条件文字列(入出庫履歴ソートキー[])(
                //ソートキー： リクエストデータ.ソートキー
                String l_strSortCond = this.createSortCondString(l_listRequest.sortKeys);
                //1.4.3 getDefaultProcessor( )(
                //l_QueryProcessor
                //1.4.4 doFindAllQuery(RowType, String, String, String, Object[], int, int)(
                //Rowタイプ： 入出庫履歴Row.TYPE
                //Where： create取得条件文字列()の戻り値
                //orderBy： createソート条件文字列()の戻り値
                //condition： null
                //リスト： create取得条件データコンテナ()の戻り値
                //ページサイズ： リクエストデータ.ページ内表示行数
                //ページ番号： リクエストデータ.要求ページ番号 - 1
                ListPage l_listAction= l_QueryProcessor.doFindAllQuery(
                    SecReceiptDeliveryActionRow.TYPE,
                    l_strGetCondString,
                    l_strSortCond,
                    null,
                    l_objCondDataContainer,
                    Integer.parseInt(l_listRequest.pageSize),
                    (Integer.parseInt(l_listRequest.pageIndex) - 1));
                //1.4.5ArrayList( )
                List l_list = new ArrayList();
                //1.4.6取得したレコード毎にLoop処理
                for (int i = 0; i < l_listAction.size(); i++)
                {
                    WEB3AioHistoryUnit l_aioHistoryUnit = new WEB3AioHistoryUnit();
                    SecReceiptDeliveryActionRow l_row = (SecReceiptDeliveryActionRow)l_listAction.get(i);
                    //1.4.6.1入出庫履歴明細( )(
                    //入出庫履歴明細.受渡日 = 入出庫履歴Params.受渡日
                    l_aioHistoryUnit.deliveryDate = WEB3DateUtility.toDay(l_row.getDeliveryDate());
                    //入出庫履歴明細.商品グループ = 入出庫履歴Params.商品グループ
                    l_aioHistoryUnit.productGroup = l_row.getProductGroup();
                    //入出庫履歴明細.銘柄コード = 入出庫履歴Params.銘柄コード
                    l_aioHistoryUnit.productCode = l_row.getProductCode();
                    //入出庫履歴明細.銘柄名 = 入出庫履歴Params.銘柄名
                    l_aioHistoryUnit.productName = l_row.getProductName();
                    //入出庫履歴明細.口座区分 = 入出庫履歴Params.特定口座区分
                    l_aioHistoryUnit.taxType = l_row.getSpecificAccountDiv();
                    //入出庫履歴明細.入出庫グループ = 入出庫履歴Params.入出庫グループ
                    l_aioHistoryUnit.inputOutputDetailGroup = l_row.getSecReceiptDeliveryGroup();
                    //入出庫履歴明細.数量 = 入出庫履歴Params.数量
                    l_aioHistoryUnit.quantity = Long.toString(l_row.getQuantity());
                    //入出庫履歴明細.数量単位 = 入出庫履歴Params.数量単位
                    l_aioHistoryUnit.quantityUnit = l_row.getQuantityUnit();
                    //入出庫履歴明細.単価 = 入出庫履歴Params.約定単価
                    if (l_row.getExecutedPriceIsNull())
                    {
                        l_aioHistoryUnit.price = null;               
                    }
                    else
                    {
                        l_aioHistoryUnit.price = WEB3StringTypeUtility.formatNumber(l_row.getExecutedPrice());
               
                    }
                    //1.4.6.2プロパティセット
                    //1.4.6.3add(arg0 : Object)
                    l_list.add(l_aioHistoryUnit);

                }
                //1.4.7toArray( )
                l_historyUnit = new WEB3AioHistoryUnit[l_list.size()];
                l_list.toArray(l_historyUnit);
                //1.4.8pageNumber( )(
                if (l_listAction.totalSize() != 0)
                {
                    l_intPageNumber = l_listAction.pageNumber() + 1;
                }
                //1.4.9totalPages( )(
                l_intTotalPages = l_listAction.totalPages();
                //1.4.10totalSize( )(
                l_intTotalSize = l_listAction.totalSize();
            }

            //1.5createResponse( 
            WEB3AioInputOutputHistoryListResponse l_response 
                = (WEB3AioInputOutputHistoryListResponse)l_listRequest.createResponse();
            //1.6プロパティセット
            //レスポンス.表示期間（自） = （以下のとおり）
            //   リクエスト.表示期間（自） == null and (*1)-② != null の場合、(*1)-②
            //   それ以外の場合、リクエスト.表示期間（自）
            if ((l_listRequest.displayStartDate == null) && (l_datFrom != null))
            {
                l_response.displayStartDate = l_datFrom; 
            }
            else
            {
                l_response.displayStartDate = l_listRequest.displayStartDate;
            }

            //レスポンス.表示期間（至） = （以下のとおり）
            //   リクエスト.表示期間（至） == null and (*1)-①@ != null の場合、(*1)-①@
            //   それ以外の場合、リクエスト.表示期間（至）
            if ((l_listRequest.displayEndDate == null) && (l_datTo != null))
            {
                l_response.displayEndDate = l_datTo;
            }
            else
            {
                l_response.displayEndDate = l_listRequest.displayEndDate;
            }
            //レスポンス.表示期間（自） != null and レスポンス.表示期間（至） != null の場合
            //   レスポンス.入出庫履歴一覧 = 入出庫履歴明細の配列
            //   レスポンス.表示ページ番号 = pageNumber()の戻り値
            //   レスポンス.総ページ数 = totalPages()の戻り値
            //   レスポンス.総レコード数 = totalSize()の戻り値
            
            if ((l_response.displayStartDate != null) && (l_response.displayEndDate != null))
            {
                l_response.HistoryUnits = l_historyUnit;
                l_response.pageIndex = Integer.toString(l_intPageNumber);
                l_response.totalPages = Integer.toString(l_intTotalPages);
                l_response.totalRecords = Integer.toString(l_intTotalSize);
            }
            //レスポンス.表示期間（自） == null and レスポンス.表示期間（至） == null の場合
            //   レスポンス.入出庫履歴一覧 = 要素数0の配列
            //   レスポンス.表示ページ番号 = 0
            //   レスポンス.総ページ数 = 0
            //   レスポンス.総レコード数 = 0
            if ((l_response.displayEndDate == null) && (l_response.displayStartDate == null))
            {
                l_response.HistoryUnits = new WEB3AioHistoryUnit[0];
                l_response.pageIndex = "0";
                l_response.totalPages = "0";
                l_response.totalRecords = "0";
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch(NotFoundException l_ex)
        {
            log.error("__an notFoundexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        catch(DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

    }

    /**
     * (create取得条件文字列)<BR>
     * 取得条件の文字列を生成する。<BR>
     * <BR>
     * １）以下の文字列を生成する。<BR>
     * <BR>
     *   "institution_code=? and branch_code=? and account_code like ?" <BR>
     * <BR>
     * ２）受渡日<BR>
     * <BR>
     *    " and delivery_date>=? and delivery_date<=?" <BR>
     *    を１）の文字列に追加する。<BR>
     * <BR>
     * ３）商品グループ<BR>
     * <BR>
     * ３－１）引数.商品グループ == "Z"（全商品）の場合<BR>
     * <BR>
     *    " and product_group!=null" を１）の文字列に追加する。<BR>
     * <BR>
     * ３－２）引数.商品グループ != "Z"（全商品）の場合<BR>
     * <BR>
     *    " and product_group=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ４）銘柄コード<BR>
     * <BR>
     *    引数.銘柄コード != nullの場合<BR>
     * <BR>
     *    " and substr(product_code, 5, 5)=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ５）出入グループ<BR>
     * <BR>
     * ５－１）引数.出入グループ == "Z"（入出庫）の場合<BR>
     * <BR>
     *    " and io_group!=null" を１）の文字列に追加する。<BR>
     * <BR>
     * ５－２）引数.出入グループ != "Z"（入出庫）の場合<BR>
     * <BR>
     *    " and io_group=?" を１）の文字列に追加する。<BR>
     * <BR>
     * ６）入出庫グループ<BR>
     *<BR>
     *   " and sec_receipt_delivery_group!=null" を１）の文字列に追加する。<BR>
     * ７）生成された文字列を返却する。
     * @@param l_strProductGroup - 商品グループ
     * 
     * @@param l_strProductCode - 銘柄コード
     * 
     * @@param l_strInputOutputGroup - 出入グループ
     * @@return String
     * @@roseuid 41B7BCD103D4
     */
    protected String createGetCondCharacterString(
        String l_strProductGroup,
        String l_strProductCode,
        String l_strInputOutputGroup)
    {
        final String STR_METHOD_NAME = "createGetCondCharacterString";
        log.entering(STR_METHOD_NAME);

        //空の文字列を生成する。
        StringBuffer l_strCreateString = new StringBuffer();

        // １）以下の文字列を生成する。
        l_strCreateString.append("institution_code = ? and branch_code = ? and account_code like ? || '%'");
        
        // ２）受渡日
        //    "delivery_date>=? and delivery_date<=?"を１）の文字列に追加する。
        l_strCreateString.append(" and delivery_date >= ? and delivery_date <= ?");

        // ３）商品グループ
        // ３－１）引数.商品グループ == "Z"（全商品）の場合
        //    " and product_group!=null" を１）の文字列に追加する。
        if (WEB3InformProductGroupDef.ALL_PRODUCT.equals(l_strProductGroup))
        {
            l_strCreateString.append(" and product_group is not null"); 
        }
        // ３－２）引数.商品グループ != "Z"（全商品）の場合
        //    " and product_group=?" を１）の文字列に追加する。
        else
        {
            l_strCreateString.append(" and product_group = ?");
        }

        // ４）銘柄コード 
        //    引数.銘柄コード != nullの場合 
        //    " and substr(product_code, 5, 5)=?" を１）の文字列に追加する。
        if (l_strProductCode != null)
        {
            l_strCreateString.append(" and substr(product_code, 5, 5) = ?");
        }
        
        //５）出入グループ 
        // ５－１）引数.出入グループ == "Z"（入出庫）の場合
        //    " and io_group!=null" を１）の文字列に追加する。
        if (WEB3InformInputOutputGroupDef.IN_OUT_BASE.equals(l_strInputOutputGroup)) 
        {
            l_strCreateString.append(" and io_group is not null");
        }
        // ５－２）引数.出入グループ != "Z"（入出庫）の場合 
        //    " and io_group=?" を１）の文字列に追加する。
        else
        {
            l_strCreateString.append(" and io_group = ?");             
        }
        //６）入出庫グループ
        //   " and sec_receipt_delivery_group!=null" を１）の文字列に追加する。
        l_strCreateString.append(" and sec_receipt_delivery_group is not null");

        // 7）生成された文字列を返却する。
        log.debug(l_strCreateString.toString());

        log.exiting(STR_METHOD_NAME);
        return l_strCreateString.toString();
    }

    /**
     * (create取得条件データコンテナ)<BR>
     * 取得条件にセットする値の配列を生成する。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）証券会社コード、部店コード、顧客コード<BR>
     *    補助口座.getInstitution().getInstitutionCode()<BR>
     *    補助口座.get取引店().getBranchCode()<BR>
     *    補助口座.getMainAccount().getAccountCode()の左6byte <BR>
     *    を１）のListに追加する。<BR>
     * <BR>
     * ３）受渡日<BR>
     * <BR>
     *    引数.表示期間（自）、引数.表示期間（至） を１）のListに追加する。<BR>
     * <BR>
     * ４）商品グループ<BR>
     * <BR>
     *    引数.商品グループ != "Z"（全商品）の場合<BR>
     * <BR>
     *    引数.商品グループ を１）のListに追加する。<BR>
     * <BR>
     * ５）銘柄コード<BR>
     * <BR>
     *    引数.銘柄コード != nullの場合<BR>
     * <BR>
     *    引数.銘柄コード を１）のListに追加する。<BR>
     * <BR>
     * ６）出入グループ<BR>
     * <BR>
     *    引数.出入グループ != "Z"（入出庫）の場合<BR>
     * <BR>
     *    引数.出入グループ を１）のListに追加する。<BR>
     * <BR>
     * ７）生成されたListから配列を取得し、返却する。
     * @@param l_subAccount - 補助口座
     * @@param l_datDisplayStartDate - 表示期間（自）
     * @@param l_datDisplayEndDate - 表示期間（至）
     * @@param l_strProductGroup - 商品グループ
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strInputOutputGroup - 出入グループ
     * @@return Object[]
     * @@roseuid 41B7BCE8027C
     */
    protected Object[] createGetCondDataContainer(
        SubAccount l_subAccount,
        Date l_datDisplayStartDate,
        Date l_datDisplayEndDate,
        String l_strProductGroup,
        String l_strProductCode,
        String l_strInputOutputGroup)
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer()";
        log.entering(STR_METHOD_NAME);

        // １）空のArrayListを生成する。
        ArrayList l_list = new ArrayList();

        // ２）証券会社コード、部店コード、顧客コード<BR>
        //     補助口座.getInstitution().getInstitutionCode()<BR>
        //     補助口座.get取引店().getBranchCode()<BR>
        //     補助口座.getMainAccount().getAccountCode()の左6byte <BR>
        //     を１）のListに追加する。<BR>
        l_list.add(l_subAccount.getInstitution().getInstitutionCode());
        l_list.add(l_subAccount.getMainAccount().getBranch().getBranchCode());
        l_list.add(l_subAccount.getMainAccount().getAccountCode().substring(0, 6));

        // ３）受渡日
        //    引数.表示期間（自）、引数.表示期間（至） を１）のListに追加する。
        l_list.add(l_datDisplayStartDate);
        l_list.add(l_datDisplayEndDate);
        
        // ４）商品グループ
        //    引数.商品グループ != "Z"（全商品）の場合
        //    引数.商品グループ を１）のListに追加する。
        if (!WEB3InformProductGroupDef.ALL_PRODUCT.equals(l_strProductGroup))
        {
            l_list.add(l_strProductGroup);
        }
        
        // ５）銘柄コード
        //    引数.銘柄コード != nullの場合 
        //    引数.銘柄コード を１）のListに追加する。
        if (l_strProductCode != null)
        {
            l_list.add(l_strProductCode);
        }

        // ６）出入グループ
        //    引数.出入グループ != "Z"（入出庫）の場合
        //    引数.出入グループ を１）のListに追加する。
        if (!WEB3InformInputOutputGroupDef.IN_OUT_BASE.equals(l_strInputOutputGroup))
        {
            l_list.add(l_strInputOutputGroup);
        }

        // ７）生成されたListから配列を取得し、返却する。
        log.exiting(STR_METHOD_NAME);
        Object[] l_obj = new Object[l_list.size()];
        l_list.toArray(l_obj);
        return l_obj;
    }

    /**
     * (createソート条件文字列)<BR>
     * 取得データのソート条件の文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）ソートキー配列の各要素毎に以下の処理を行う。<BR>
     * <BR>
     * ２－１）キー項目 == ”受渡日” の場合<BR>
     * <BR>
     *    ・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     *        "delivery_date"を１）の文字列に追加する。<BR>
     * <BR>
     *    ・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     *        "delivery_date desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－２）キー項目 == ”商品グループ” の場合<BR>
     * <BR>
     *    ・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     *        "product_group"を１）の文字列に追加する。<BR>
     * <BR>
     *    ・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     *        "product_group desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－３）キー項目 == ”銘柄コード” の場合<BR>
     * <BR>
     *    ・昇順/降順 == "A"（昇順） の場合<BR>
     * <BR>
     *        "product_code"を１）の文字列に追加する。<BR>
     * <BR>
     *    ・昇順/降順 == "D"（降順） の場合<BR>
     * <BR>
     *        "product_code desc"を１）の文字列に追加する。<BR>
     * <BR>
     * ２－４）該当の要素が配列内の最後の要素ではない場合<BR>
     * <BR>
     *    ", "を１）の文字列に追加する。<BR>
     * <BR>
     * ３）生成された文字列を返却する。
     * @@param l_sortKeys - (ソートキー)<BR>
     * ソートキーの配列
     * 
     * @@return String
     * @@roseuid 41B7BD0601D0
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
        for (int i = 0;i < l_intLength;i++ )
        {
            // ２－１）キー項目 == ”受渡日” の場合
            // 
            //    ・昇順/降順 == "A"（昇順） の場合
            // 
            //        "delivery_date"を１）の文字列に追加する。
            if (WEB3AioInputOutputSortkeyItemDef.DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("delivery_date");
                }
                // 
                //    ・昇順/降順 == "D"（降順） の場合
                // 
                //        "delivery_date desc"を１）の文字列に追加する。
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("delivery_date desc");
                }
            }

            // ２－２）キー項目 == ”商品グループ” の場合
            // 
            //    ・昇順/降順 == "A"（昇順） の場合
            // 
            //        "product_group"を１）の文字列に追加する。
            // 
            //    ・昇順/降順 == "D"（降順） の場合
            // 
            //        "product_group desc"を１）の文字列に追加する。
            else if (WEB3AioInputOutputSortkeyItemDef.PRODUCT_GROUP.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("product_group");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("product_group desc");
                }
            }
            // 
            // ２－３）キー項目 == ”銘柄コード” の場合
            // 
            //    ・昇順/降順 == "A"（昇順） の場合
            // 
            //        "product_code"を１）の文字列に追加する。
            // 
            //    ・昇順/降順 == "D"（降順） の場合
            // 
            //        "product_code desc"を１）の文字列に追加する。
            // 
            else if (WEB3AioInputOutputSortkeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("product_code");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("product_code desc");
                }
                
            }
            // ２－４）該当の要素が配列内の最後の要素ではない場合
            // 
            //    ", "を１）の文字列に追加する。
            // 
            if (i < (l_intLength - 1))
            {
                l_strSortString.append(",");
            }
        }            
        // ３）生成された文字列を返却する。
        log.debug(l_strSortString.toString());
        log.exiting(STR_METHOD_NAME);
        return l_strSortString.toString();
    }
}
@
