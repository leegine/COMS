head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOrderExecuteDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Admin Order Execute Data Manager
                 : (WEB3AdminOrderExecuteDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 黄　@浩澎(中訊) モデル変更点087・090
                 : 2005/10/20 黄　@浩澎(中訊) モデル変更点092
                 : 2006/08/21 肖志偉(中訊) モデル変更点062
                 : 2006/10/06 唐性峰(中訊) モデル変更点078
                 : 2007/01/30 劉立峰(中訊) モデル変更点088
Revesion History : 2007/07/01 張騰宇(中訊) モデル変更点102 104
Revesion History : 2008/10/02 大澤(SRA) 【管理者注文約定照会】仕様変更管理台帳（モデル）No.128、129
*/

package webbroker3.adminorderexecinquiry;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.adminorderexecinquiry.define.WEB3AdminExecTypeDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminTradingTypeDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORDetailDispInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommon;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionRefCommonRequest;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.gentrade.WEB3GentradeTrader;

/**
 * (管理者注文約定照会データマネージャ)<BR>
 * 管理者注文約定照会のDB I/O、データ変換などを管理するクラス。<BR>
 * <BR>
 * -----<English>---------------<BR>
 * <BR>
 * WEB3AdminOrderExecuteDataManager<BR>
 * It is a class to manage DB I/O and data conversion in
 * web3-adminorderexecinquiry<BR>
 * <BR>
 * @@author Anil
 * @@version 1.0
 */
public class WEB3AdminOrderExecuteDataManager
{

    /** Log Variable.<BR>*/
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOrderExecuteDataManager.class);

    /**
     * @@roseuid 4213049F0046
     */
    public WEB3AdminOrderExecuteDataManager()
    {

    }

    /**
     * (create共通検索条件文字列)<BR>
     * <BR>
     * 検索条件文字列を作成する。<BR>
     * <BR>
     * １）検索条件文字列インスタンス(：String)を生成する。<BR>
     * <BR>
     * ２）部店条件を検索条件文字列に追加する。<BR>
     * 　@パラメータ.リクエストデータ.部店コードの要素数分"?"を追加する。 <BR>
     * <BR>
     * 　@検索条件文字列 += "branch_id in (?, ?,,,) " <BR>
     * <BR>
     * ３）パラメータ.リクエストデータ.注文ID != nullの場合、<BR>
     * 　@注文IDを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and order_id = ? "<BR>
     * <BR>
     * ４）発注日条件<BR>
     * 　@４－１）パラメータ.リクエストデータ.発注日 != nullの場合、<BR>
     * 　@　@以下の発注日条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and biz_date = ? "<BR>
     * <BR>
     * 　@４－２）パラメータ.リクエストデータ.発注日 == nullの場合、<BR>
     * 　@　@以下の発注日条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@　@検索条件文字列 += "and biz_date >= ? "<BR>
     * <BR>
     * ５）パラメータ.リクエストデータ.顧客コード != nullの場合、<BR>
     * 　@管理者注文約定照会データマネージャ.get顧客()の戻り値の<BR>
     * 　@要素数分"?"を追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and account_id in (?, ?,,,) "<BR>
     * <BR>
     * 　@[get顧客()にセットするパラメータ]<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@部店コード：　@パラメータ.リクエストデータ.部店コード<BR>
     * 　@　@顧客コード：　@パラメータ.リクエストデータ.顧客コード<BR>
     * <BR>
     * ６）パラメータ.リクエストデータ.扱者コード（SONAR） != nullの場合、<BR>
     * 　@扱者コード（SONAR）を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and sonar_trader_code = ? "<BR>
     * <BR>
     * ７）パラメータ.リクエストデータ.商品区分 != nullの場合、<BR>
     * 　@商品条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == "現物株式"の場合]<BR>
     * 　@　@検索条件文字列 += "and order_categ = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and order_type in (?, ?) "<BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == "信用取引"の場合]<BR>
     * 　@　@検索条件文字列 += "and order_categ in (?, ?, ?) "<BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == "株式ミニ投資"の場合]<BR>
     * 　@　@検索条件文字列 += "and order_categ = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and order_type in (?, ?) "<BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == ("オプション" or "先物")の場合]<BR>
     * 　@　@検索条件文字列 += "and future_option_div = ? "<BR>
     * <BR>
     * ８）パラメータ.リクエストデータ.取引区分 != nullの場合、<BR>
     * 　@取引を判別する条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.取引区分 == "立会外分売"の場合]<BR>
     * 　@　@検索条件文字列 += "and sonar_traded_code = ? "<BR>
     * 　@[パラメータ.リクエストデータ.取引区分 == "株式買付注文"の場合] <BR>
     * 　@　@検索条件文字列 += "and order_type = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and sonar_traded_code != ? " <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@検索条件文字列 += "and order_type = ? "<BR>
     * <BR>
     * ９）パラメータ.リクエストデータ.注文期限区分 != nullの場合、<BR>
     * 　@出来るまで注文かどうかを判別する条件を<BR>
     * 　@検索条件文字列に追加する。 （＊） <BR>
     * <BR>
     * 　@　@【※パラメータ.リクエストデータをinstanceofで判別し、処理を分岐する】 <BR>
     * 　@　@　@　@パラメータ.リクエストが"管理者・先物OP注文約定照会リクエスト"の場合 <BR>
     * 　@　@　@　@　@注文期限区分は検索条件文字列に追加しない。 <BR>
     * 　@　@　@　@　@（独自メソッドで追加を行う） <BR>
     * <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.注文期限区分 == "当日限り"の場合]  <BR>
     * 　@　@検索条件文字列 += "and first_order_unit_id is null "  <BR>
     * 　@[上記以外の場合]  <BR>
     * 　@　@検索条件文字列 += "and first_order_unit_id is not null " <BR>
     * <BR>
     * １０）パラメータ.リクエストデータ.注文経路区分 != nullの場合、<BR>
     * 　@注文経路区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and order_root_div = ? "<BR>
     * <BR>
     * １１）パラメータ.リクエストデータ.注文状態区分 != nullの場合、<BR>
     * 　@注文状態を判別する条件を検索条件文字列に追加する。 （＊） <BR>
     * <BR>
     * 　@　@【※パラメータ.リクエストデータをinstanceofで判別し、処理を分岐する】 <BR>
     * 　@　@　@　@パラメータ.リクエストが"管理者・先物OP注文約定照会リクエスト"の場合 <BR>
     * 　@　@　@　@　@注文状態区分は検索条件文字列に追加しない。 <BR>
     * 　@　@　@　@　@（独自メソッドで追加を行う） <BR>
     * <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "一部失効"の場合] <BR>
     * 　@　@検索条件文字列 += "and executed_quantity is not null"<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and executed_quantity != ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and order_open_status = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and expiration_status = ? "<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "全部失効"の場合]<BR>
     * 　@　@検索条件文字列 += " and (executed_quantity is null " <BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "or executed_quantity = ?) " <BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and order_open_status = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and expiration_status = ? "<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "無効"の場合]<BR>
     * 　@　@検索条件文字列 += "and order_open_status = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and expiration_status = ? "<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "手動失効"の場合]<BR>
     * 　@　@検索条件文字列 += "and order_open_status = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and expiration_status = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and error_reason_code in (?, ?) "<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "繰越済"の場合]<BR>
     * 　@　@検索条件文字列 += "and first_order_unit_id > ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and order_status = ? "<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "繰越失敗"の場合]<BR>
     * 　@　@検索条件文字列 += "and expiration_date >= ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and order_open_status = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and expiration_status = ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and error_reason_code not in (?, ?) "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and first_order_unit_id is not null "<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@検索条件文字列 += "and order_status = ? "<BR>
     * <BR>
     * １２）パラメータ.リクエストデータ.約定状態区分 != nullの場合、<BR>
     * 　@約定状態を判別する条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.約定状態区分 == "未約定"の場合]<BR>
     * 　@　@検索条件文字列 += "and executed_quantity is null "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "or executed_quantity == ? "<BR>
     * 　@[パラメータ.リクエストデータ.約定状態区分 == "一部成立"または"約定処理中(一部成立)"の場合]<BR>
     * 　@　@検索条件文字列 += "and executed_quantity is not null "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and executed_quantity != ? "<BR>
     * 　@　@　@　@　@　@　@　@　@　@+ "and executed_quantity < confirmed_quantity "<BR>
     * 　@[パラメータ.リクエストデータ.約定状態区分 == "全部成立"または"約定処理中(全部成立)"の場合]<BR>
     * 　@　@検索条件文字列 += "and executed_quantity = confirmed_quantity "<BR>
     * <BR>
     * １３）パラメータ.リクエストデータ.約定状態区分 != nullの場合、<BR>
     * 　@仮約定状態を判別する条件を検索条件文字列に追加する。 （＊）<BR>
     * <BR>
     * 　@　@【※パラメータ.リクエストデータをinstanceofで判別し、処理を分岐する】<BR>
     * 　@　@　@　@パラメータ.リクエストが以下いずれかに該当する場合のみ、<BR>
     * 　@　@　@　@　@　@・"管理者・外国株式出来入力一覧リクエスト"<BR>
     * 　@　@　@　@　@　@・"管理者・外国株式注文約定照会リクエスト"<BR>
     * 　@　@　@　@仮約定フラグを検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += " and temporary_execution_flag = ?"<BR>
     * <BR> 
     * １４）パラメータ.リクエストデータ.訂正取消区分 != nullの場合、<BR>
     * 　@訂正取消区分を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and modify_cancel_type = ? "<BR>
     * <BR>
     * １５）パラメータ.リクエストデータ.注文時間From != nullの場合、<BR>
     * 　@注文時間From条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and received_date_time >= to_date(?,'yyyyMMddhh24mi') "<BR>
     * <BR>
     * １６）パラメータ.リクエストデータ.注文時間To != nullの場合、<BR>
     * 　@注文時間To条件を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and received_date_time <= to_date(?,'yyyyMMddhh24mi') "<BR>
     * <BR>
     * １７）パラメータ.リクエストデータ.受渡日 != nullの場合、<BR>
     * 　@受渡日を検索条件文字列に追加する。<BR>
     * <BR>
     * 　@検索条件文字列 += "and delivery_date = ? "<BR>
     * <BR>
     * １８）作成した検索条件文字列を返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * <BR>
     * 証券会社コード<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・注文約定照会共通リクエストオブジェクト<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41B3B57B00FC
     */
    public String createCommonQueryString(
        String l_strInstitutionCode,
        WEB3AdminOROrderExecutionRefCommonRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createCommonQueryString(String l_strInstitutionCode, "
                + "WEB3AdminOROrderExecutionRefCommonRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeMainAccount[] l_gntradeMainAccounts = null;

        //Step1Create an instance of l_strQueryCond
        StringBuffer l_strQueryCond = new StringBuffer();

        int l_intlength = 0;
        int l_intcount = 0;

        //Step2 Add the condition about branch to l_strQueryCond
        l_intlength = l_request.branchCode.length;
        l_strQueryCond.append("");
        if (l_intlength == 1)
        {
            l_strQueryCond.append("branch_id in (?) ");
        } else
        {
            for (l_intcount = 0; l_intcount < l_intlength; l_intcount++)
            {
                if (l_intcount == 0)
                {
                    l_strQueryCond.append("branch_id in (?");
                } else if (l_intcount == (l_intlength - 1))
                {
                    l_strQueryCond.append(",?)");
                } else
                {
                    l_strQueryCond.append(",?");
                }
            }
        }

        /*
          * Step3 If l_request.orderId != null
          * Add orderId to l_strQueryCond
          */
        if (l_request.orderId != null)
        {
            l_strQueryCond.append(" and order_id = ?");
        }

        /*
          * Step4.1 l_request.orderBizDate != null
          * Add the following condition about orderBizDay to l_strQueryCond
          */
        if (l_request.orderBizDate != null)
        {
            l_strQueryCond.append(" and biz_date = ?");
        } else
        {
            l_strQueryCond.append(" and biz_date >= ? ");
        }

        /*
         *Step5 l_request.accountCode != null
         *Add "?" as many as the number of the elements of
         *return value of getAccountList()
         */
        if (l_request.accountCode != null)
        {
            l_gntradeMainAccounts =
                getAccountList(l_strInstitutionCode, l_request.branchCode, l_request.accountCode);
            l_intlength = l_gntradeMainAccounts.length;
            if (l_intlength == 1)
            {
                l_strQueryCond.append(" and account_id in (?) ");
            } else
            {
                for (l_intcount = 0; l_intcount < l_intlength; l_intcount++)
                {
                    if (l_intcount == 0)
                    {
                        l_strQueryCond.append(" and account_id in (?");
                    } else if (l_intcount == (l_intlength - 1))
                    {
                        l_strQueryCond.append(",?)");
                    } else
                    {
                        l_strQueryCond.append(",?");
                    }
                }
            }
        }

        /*
          * Step6 If l_request.sonarTraderCode != null
          * Add sonarTraderCode to l_strQueryCond
          */
        if (l_request.sonarTraderCode != null)
        {
            l_strQueryCond.append(" and sonar_trader_code = ?");
        }

        //Step 7 Add the conditions about a product to l_strQueryCond
        if (l_request.productDiv != null)
        {
            if (l_request.productDiv.equals(WEB3AdminProductDivDef.EQUITY))
            {
                l_strQueryCond.append(" and order_categ = ? and order_type in (?,?) ");
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.MARGIN))
            {
                l_strQueryCond.append(" and order_categ in (?,?,?) ");
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.MINI_STOCK))
            {
                l_strQueryCond.append(" and order_categ = ? and order_type in (?,?) ");
            } else if (
                l_request.productDiv.equals(WEB3AdminProductDivDef.OPTION)
                    || l_request.productDiv.equals(WEB3AdminProductDivDef.FUTURE))
            {
                l_strQueryCond.append(" and future_option_div = ? ");
            }
        }

        //Step 8 )If l_request.tradingType != null
        if (l_request.tradingType != null)
        {
            if (l_request.tradingType.equals(WEB3AdminTradingTypeDef.SALES_OUTSIDE_MARKET))
            {
                l_strQueryCond.append(" and sonar_traded_code = ?");
            }
            else if (l_request.tradingType.equals(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue())))
            {
                l_strQueryCond.append(" and order_type = ? and sonar_traded_code != ?");
            }
            else
            {
                l_strQueryCond.append(" and order_type = ?");
            }
        }

        //Step 9 If l_request.expirationDateType != null
        if (!(l_request instanceof WEB3AdminORFutOpOrderExecutionRefReferenceRequest))
        {
            if (l_request.expirationDateType != null)
            {
                if (l_request.expirationDateType.equals(WEB3OrderExpirationDateTypeDef.DAY_LIMIT))
                {
                    l_strQueryCond.append(" and first_order_unit_id is null");
                } else
                {
                    l_strQueryCond.append(" and first_order_unit_id is not null");
                }
            }
        }

        //Step 10 If l_request.orderRootDiv != null
        if (l_request.orderRootDiv != null)
        {
            l_strQueryCond.append(" and order_root_div = ?");
        }

        //Step 11 If l_request.orderState != null
        if (!(l_request instanceof WEB3AdminORFutOpOrderExecutionRefReferenceRequest))
        {
            if (l_request.orderState != null)
            {
                if (l_request.orderState.equals(WEB3OrderStatusDef.PART_INAFFECTED))
                {
                    l_strQueryCond.append(" and executed_quantity is not null");
                    l_strQueryCond.append(" and executed_quantity != ?");
                    l_strQueryCond.append(" and order_open_status = ?");
                    l_strQueryCond.append(" and expiration_status = ?");
                } else if (l_request.orderState.equals(WEB3OrderStatusDef.FULL_INAFFECTED))
                {
                    l_strQueryCond.append(" and (executed_quantity is null");
                    l_strQueryCond.append(" or executed_quantity = ?)");
                    l_strQueryCond.append(" and order_open_status = ?");
                    l_strQueryCond.append(" and expiration_status = ?");
    
                } else if (l_request.orderState.equals(WEB3OrderStatusDef.CLOSED))
                {
                    l_strQueryCond.append(" and order_open_status = ? and expiration_status = ?");
                }else if (l_request.orderState.equals(WEB3OrderStatusDef.MANUAL_EXPIRED))
                {
                    l_strQueryCond.append(" and order_open_status = ?");
                    l_strQueryCond.append(" and expiration_status = ?");
                    l_strQueryCond.append(" and error_reason_code in (?, ?)");
                } else if (l_request.orderState.equals(WEB3OrderStatusDef.TRANSFERED))
                {
                    l_strQueryCond.append(" and first_order_unit_id > ? and order_status = ?");
                } else if (l_request.orderState.equals(WEB3OrderStatusDef.NOT_TRANSFERED))
                {
                    l_strQueryCond.append(" and expiration_date >= ? and order_open_status = ?");
                    l_strQueryCond.append(" and expiration_status = ?");
                    l_strQueryCond.append(" and error_reason_code not in (?, ?) ");
                    l_strQueryCond.append(" and first_order_unit_id is not null ");
                } else
                {
                    l_strQueryCond.append(" and order_status = ?");
                }
            }
        }

        //Step12 If l_request.execType != null
        if (l_request.execType != null)
        {
            if (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_NOT_PROMISE))
            {
                l_strQueryCond.append(" and (executed_quantity is null");
                l_strQueryCond.append(" or executed_quantity = ?)");
            } else if (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_ONE_COMPLETE) ||
                    l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE))
            {
                l_strQueryCond.append(" and executed_quantity is not null");
                l_strQueryCond.append(" and executed_quantity != ?");
                l_strQueryCond.append(" and executed_quantity < confirmed_quantity");
            } else if (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_ALL_COMPLETE) ||
                    l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ALL_COMPLETE))
            {
                l_strQueryCond.append(" and executed_quantity = confirmed_quantity");
            }
            
            if (l_request instanceof WEB3AdminORFeqExecuteListRequest ||
                l_request instanceof WEB3AdminORFeqOrderExecutionRefReferenceRequest)
            {
                l_strQueryCond.append(" and temporary_execution_flag = ?");
            }
        }

        //Step 13 If l_request.changeCancelDiv != null
        if (l_request.changeCancelDiv != null)
        {
            l_strQueryCond.append(" and modify_cancel_type = ?");
        }

        //Step 14 If l_request.orderStartDate != null
        if (l_request.orderStartDate != null)
        {
            l_strQueryCond.append(" and received_date_time >= to_date(?,'yyyyMMddhh24mi')");
        }

        //Step 15 If l_request.orderEndDate != null
        if (l_request.orderEndDate != null)
        {
            l_strQueryCond.append(" and received_date_time <= to_date(?,'yyyyMMddhh24mi')");
        }

        //Step 16 If l_request.deliveryDate != null
        if (l_request.deliveryDate != null)
        {
            l_strQueryCond.append(" and delivery_date = ? ");
        }

        log.exiting(STR_METHOD_NAME);

        //Step 17 Return the created l_strQueryCond
        return l_strQueryCond.toString();
    }

    /**
     * (create共通検索条件データコンテナ)<BR>
     * <BR>
     * 検索条件データコンテナを作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）部店条件を生成したArrayListに追加する。<BR> 
     * 　@パラメータ.リクエストデータ.部店コードの各要素に<BR> 
     * 　@　@該当する部店IDを全てArrayListに追加する。<BR>
     * <BR>
     * ３）パラメータ.リクエストデータ.注文ID != nullの場合、<BR>
     * 　@注文IDを生成したArrayListに追加する。<BR>
     * <BR>
     * ４）発注日条件<BR>
     * 　@４－１）パラメータ.リクエストデータ.発注日 != nullの場合、<BR>
     * 　@　@パラメータ.リクエストデータ.発注日を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@４－２）パラメータ.リクエストデータ.発注日 == nullの場合、<BR>
     * 　@　@システム日付(*1)の前営業日を生成したArrayListに追加する。<BR>
     * <BR>
     * ５）パラメータ.リクエストデータ.顧客コード != nullの場合、<BR>
     * 　@口座IDを生成したArrayListに追加する。<BR>
     * <BR>
     * 　@※口座IDは、管理者注文約定照会データマネージャ.get顧客()メソッドにて<BR>
     * 　@　@取得した全ての顧客.口座IDをセット<BR>
     * <BR>
     * 　@[get顧客()にセットするパラメータ]<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@部店コード：　@パラメータ.リクエストデータ.部店コード<BR>
     * 　@　@顧客コード：　@パラメータ.リクエストデータ.顧客コード<BR>
     * <BR>
     * ６）パラメータ.リクエストデータ.扱者コード（SONAR） != nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.扱者コード（SONAR）を以下の条件で編集し、<BR>
     * 　@生成したArrayListに追加する。<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.扱者コード（SONAR）が5桁未満の場合<BR>
     * 　@（パラメータ.リクエストデータ.扱者コード（SONAR）.length < 5）]<BR>
     * 　@　@・前”0”詰に編集したパラメータ.リクエストデータ.扱者コード（SONAR）<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@・パラメータ.リクエストデータ.扱者コード（SONAR）<BR>
     * <BR>
     * ７）パラメータ.リクエストデータ.商品区分 != nullの場合、<BR>
     * 　@商品条件を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == "現物株式"の場合]<BR>
     * 　@　@・OrderCategEnum.現物注文<BR>
     * 　@　@・OrderTypeEnum.現物買注文<BR>
     * 　@　@・OrderTypeEnum.現物売注文<BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == "信用取引"の場合]<BR>
     * 　@　@・OrderCategEnum.新規建注文<BR>
     * 　@　@・OrderCategEnum.返済注文<BR>
     * 　@　@・OrderCategEnum.現引現渡注文<BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == "株式ミニ投資"の場合]<BR>
     * 　@　@・OrderCategEnum.現物注文<BR>
     * 　@　@・OrderTypeEnum.株式ミニ株買注文<BR>
     * 　@　@・OrderTypeEnum.株式ミニ株売注文<BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == "オプション"の場合]<BR>
     * 　@　@・"オプション"(先物／オプション区分)<BR>
     * 　@[パラメータ.リクエストデータ.商品区分 == "先物"の場合]<BR>
     * 　@　@・"先物"(先物／オプション区分)<BR>
     * <BR>
     * ８）パラメータ.リクエストデータ.取引区分 != nullの場合、<BR>
     * 　@取引を判別する条件を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.取引区分 == "立会外分売"の場合]<BR>
     * 　@　@"立会外分売"(取引コード(SONAR))<BR>
     * 　@[パラメータ.リクエストデータ.取引区分 == "株式買付注文"の場合]<BR>
     * 　@　@・管理者注文約定照会データマネージャ.get注文種別()の戻り値<BR>
     * 　@　@・"立会外分売"(取引コード(SONAR))<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@管理者注文約定照会データマネージャ.get注文種別()の戻り値<BR>
     * <BR>
     * 　@　@[get注文種別()にセットするパラメータ]<BR>
     * 　@　@　@取引区分：　@パラメータ.リクエストデータ.取引区分<BR>
     * <BR>
     * ９）パラメータ.リクエストデータ.注文経路区分 != nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.注文経路区分を生成したArrayListに追加する。<BR>
     * <BR>
     * １０）パラメータ.リクエストデータ.注文状態区分 != nullの場合、 <BR>
     * 　@注文状態を判別する条件を上から順に生成したArrayListに追加する。 （＊） <BR>
     * <BR>
     * 　@　@【※パラメータ.リクエストデータをinstanceofで判別し、処理を分岐する】 <BR>
     * 　@　@　@　@パラメータ.リクエストが"管理者・先物OP注文約定照会リクエスト"の場合 <BR>
     * 　@　@　@　@　@注文状態を判別する条件はArrayListに追加しない。 <BR>
     * 　@　@　@　@　@（独自メソッドで追加を行う） <BR>
     * <BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "一部失効"の場合]<BR>
     * 　@　@・0<BR>
     * 　@　@・"クローズ"(注文有効状態)<BR>
     * 　@　@・"マーケット拒否"(失効区分)<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "全部失効"の場合]<BR>
     * 　@　@・0<BR>
     * 　@　@・"クローズ"(注文有効状態)<BR>
     * 　@　@・"マーケット拒否"(失効区分)<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "無効"の場合]<BR>
     * 　@　@・"クローズ"(注文有効状態)<BR>
     * 　@　@・"終了"(失効区分)<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "手動失効"の場合]<BR>
     * 　@　@・"クローズ"(注文有効状態)<BR>
     * 　@　@・"マーケット拒否"(失効区分)<BR>
     * 　@　@・"株式管理者手動失効済"（注文エラー理由コード）<BR>
     * 　@　@・"先物OP管理者手動失効済"（注文エラー理由コード）<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "繰越済"の場合]<BR>
     * 　@　@・0<BR>
     * 　@　@・"受付済（新規注文）"(注文状態)<BR>
     * 　@[パラメータ.リクエストデータ.注文状態区分 == "繰越失敗"の場合] <BR>
     * 　@　@・業務日付(*1)<BR>
     * 　@　@・"クローズ"(注文有効状態)<BR>
     * 　@　@・"終了"(失効区分)<BR>
     * 　@　@・"正常"(注文エラー理由コード)<BR>
     * 　@　@・"トリガー注文管理者手動失効済"(注文エラー理由コード)<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@パラメータ.リクエストデータ.注文状態区分を追加する。<BR>
     * <BR>
     * １１）パラメータ.リクエストデータ.約定状態区分 != nullの場合、<BR>
     * 　@約定状態を判別する条件を生成したArrayListに追加する。<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.約定状態区分 == ("未約定" 
     * or "一部成立" or "約定処理中(一部成立)")の場合]<BR>
     * 　@　@・0<BR>
     * <BR>
     * １２）パラメータ.リクエストデータ.約定状態区分 != nullの場合、<BR>
     * 　@仮約定状態を判別する条件を生成したArrayListに追加する。（＊）<BR>
     * <BR>
     * 　@　@【※パラメータ.リクエストデータをinstanceofで判別し、処理を分岐する】<BR>
     * 　@　@　@　@パラメータ.リクエストが以下いずれかに該当する場合のみ、<BR>
     * 　@　@　@　@　@　@・"管理者・外国株式出来入力一覧リクエスト"<BR>
     * 　@　@　@　@　@　@・"管理者・外国株式注文約定照会リクエスト"<BR>
     * 　@　@　@　@仮約定フラグを判別する条件をArrayListに追加する。<BR>
     * <BR>
     * 　@[パラメータ.リクエストデータ.約定状態区分 == "約定処理中(一部成立)"または"約定処理中(全部成立)"の場合]<BR>
     * 　@　@・"仮約定"<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@・DEFAULT<BR>
     * <BR>
     * １３）パラメータ.リクエストデータ.訂正取消区分 != nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.訂正取消区分を生成したArrayListに追加する。<BR>
     * <BR>
     * １４）パラメータ.リクエストデータ.注文時間From != nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.注文時間Fromを生成したArrayListに追加する。<BR>
     * <BR>
     * １５）パラメータ.リクエストデータ.注文時間To != nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.注文時間Toを生成したArrayListに追加する。<BR>
     * <BR>
     * １６）パラメータ.リクエストデータ.受渡日 != nullの場合、<BR>
     * 　@パラメータ.リクエストデータ.受渡日を生成したArrayListに追加する。<BR>
     * <BR>
     * １７）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)GtlUtils.getTradingSystem().getBizDate() <BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * <BR>
     * 証券会社コード<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・注文約定照会共通リクエストオブジェクト<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@throws DataFindException DataFindException
     * @@throws DataQueryException DataQueryException
     * @@throws DataNetworkException DataNetworkException
     * @@throws NotFoundException NotFoundException
     * @@return String[]
     * @@roseuid 41B3B57B0112
     */
    public String[] createCommonQueryDataContainer(
        String l_strInstitutionCode,
        WEB3AdminOROrderExecutionRefCommonRequest l_request)
        throws
            WEB3BaseException,
            DataFindException,
            DataQueryException,
            DataNetworkException,
            NotFoundException
    {
        final String STR_METHOD_NAME =
            "createCommonQueryDataContainer(String l_strInstitutionCode, "
                + "WEB3AdminOROrderExecutionRefCommonRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminOrderExecuteDataManager l_web3AdminOrderExecuteDataManager = null;
        l_web3AdminOrderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();
        WEB3GentradeMainAccount[] l_gntradeMainAccounts = null;
        String[] l_arrBranchCodes = null;
        String[] l_branchCodes = null;
        String l_accountCode = null;
        String l_orderState = null;
        Date l_datPrevBizDate = null;
        String l_straccountId = null;
        int l_intAccountLength = 0;
        int l_intCount = 0;
        int l_intbranchCodeLength = 0;
        WEB3GentradeAccountManager l_accManager = null;
        WEB3Administrator l_administrator = null;
        Institution l_institution = null;
        Branch l_branch = null;
        OrderTypeEnum l_orderTypeEnum = null;
        String l_strdateFormat = "yyyyMMdd";

        //Step1 Create ArrayList
        ArrayList l_arrdataList = new ArrayList();

        /*
          * Step 2 Add branch conditions to created ArrayList
          * Add all elements of l_request.branchCode to ArrayList
          */
        l_intbranchCodeLength = l_request.branchCode.length;
        l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        l_institution = l_administrator.getInstitution();
        l_accManager = new WEB3GentradeAccountManager();
        for (l_intCount = 0; l_intCount < l_intbranchCodeLength; l_intCount++)
        {
            l_branch = l_accManager.getBranch(l_institution, l_request.branchCode[l_intCount]);
            l_arrdataList.add(WEB3StringTypeUtility.formatNumber(l_branch.getBranchId()));
        }

        // Step3 If l_request.orderId != null
        if (l_request.orderId != null)
        {
            l_arrdataList.add(l_request.orderId);
        }

        //Step 4-1 l_request.orderBizDate != null
        if (l_request.orderBizDate != null)
        {
            String l_strDate = WEB3DateUtility.formatDate(l_request.orderBizDate, l_strdateFormat);
            l_arrdataList.add(l_strDate);
        } else
        {
            Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
            WEB3GentradeBizDate l_dateCalc = new WEB3GentradeBizDate(l_tsBizDate);
            l_datPrevBizDate = l_dateCalc.roll(-1);
            l_arrdataList.add(WEB3DateUtility.formatDate(l_datPrevBizDate, l_strdateFormat));
        }

        // Step 5 l_request.accountCode != null
        if (l_request.accountCode != null)
        {
            l_branchCodes = l_request.branchCode;
            l_accountCode = l_request.accountCode;
            l_gntradeMainAccounts =
                this.getAccountList(l_strInstitutionCode, l_branchCodes, l_accountCode);
            l_intAccountLength = l_gntradeMainAccounts.length;

            //Set all accountId acquired at getAccountList()
            for (l_intCount = 0; l_intCount < l_intAccountLength; l_intCount++)
            {
                l_straccountId =
                    WEB3StringTypeUtility.formatNumber(
                        l_gntradeMainAccounts[l_intCount].getAccountId());
                l_arrdataList.add(l_straccountId);
            }
        }

        // Step6 If l_request.sonarTraderCode != null
        if (l_request.sonarTraderCode != null)
        {
            int l_intSonarTraderCodeLen = 5;
            if (l_request.sonarTraderCode.length() < l_intSonarTraderCodeLen)
            {
                StringBuffer l_strSonarTraderCodeFillZero = new StringBuffer();
                for (int i = l_request.sonarTraderCode.length(); i < l_intSonarTraderCodeLen; i++)
                {
                    l_strSonarTraderCodeFillZero.append("0");
                }
                l_arrdataList.add(l_strSonarTraderCodeFillZero + l_request.sonarTraderCode);
            }
            else
            {
                l_arrdataList.add(l_request.sonarTraderCode);
            }
        }

        /*
         * Step 7 If l_request.productDiv != null,
         * Add product conditions to created ArrayList
         */
        if (l_request.productDiv != null)
        {
            if (l_request.productDiv.equals(WEB3AdminProductDivDef.EQUITY))
            {
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.ASSET.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderTypeEnum.EQUITY_BUY.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderTypeEnum.EQUITY_SELL.intValue()));
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.MARGIN))
            {
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.OPEN_MARGIN.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.CLOSE_MARGIN.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.SWAP_MARGIN.intValue()));
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.MINI_STOCK))
            {
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderCategEnum.ASSET.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MINI_STOCK_BUY.intValue()));
                l_arrdataList.add(
                    WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MINI_STOCK_SELL.intValue()));
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.OPTION))
            {
                l_arrdataList.add(WEB3FuturesOptionDivDef.OPTION);
            } else if (l_request.productDiv.equals(WEB3AdminProductDivDef.FUTURE))
            {
                l_arrdataList.add(WEB3FuturesOptionDivDef.FUTURES);
            }
        }

        /*
          * Step8 If l_request.productDiv != null,
          * Add trading conditions to created ArrayList
          */
        if (l_request.tradingType != null)
        {
            if (l_request.tradingType.equals(WEB3AdminTradingTypeDef.SALES_OUTSIDE_MARKET))
            {
                l_arrdataList.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            }
            else if (l_request.tradingType.equals(String.valueOf(OrderTypeEnum.EQUITY_BUY.intValue())))
            {
                l_arrdataList.add(l_request.tradingType);
                l_arrdataList.add(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            }
            else
            {
                l_orderTypeEnum =
                    l_web3AdminOrderExecuteDataManager.getOrderType(l_request.tradingType);
                if (l_orderTypeEnum != null)
                {
                    l_arrdataList.add(String.valueOf(l_orderTypeEnum.intValue()));
                }
            }
        }

        //Step 9 Check orderRootDiv != null
        if (l_request.orderRootDiv != null)
        {
            l_arrdataList.add(l_request.orderRootDiv);
        }

        // Step10 Add Conditions judging orderState to created ArrayList sqquentially
        if (!(l_request instanceof WEB3AdminORFutOpOrderExecutionRefReferenceRequest))
        {
            l_orderState = l_request.orderState;
            if (l_request.orderState != null)
            {
                if (l_orderState.equals(WEB3OrderStatusDef.PART_INAFFECTED))
                {
                    l_arrdataList.add(String.valueOf(0));
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
                } else if (l_orderState.equals(WEB3OrderStatusDef.FULL_INAFFECTED))
                {
                    l_arrdataList.add(String.valueOf(0));
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
                } else if (l_orderState.equals(WEB3OrderStatusDef.CLOSED))
                {
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.EXPIRED.intValue()));
                } else if (l_orderState.equals(WEB3OrderStatusDef.MANUAL_EXPIRED))
                {
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()));
                    l_arrdataList.add(String.valueOf(WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED));
                    l_arrdataList.add(String.valueOf(WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED));
                } else if (l_orderState.equals(WEB3OrderStatusDef.TRANSFERED))
                {
                    l_arrdataList.add(String.valueOf(0));
                    l_arrdataList.add(String.valueOf(OrderStatusEnum.ACCEPTED.intValue()));
                } else if (l_orderState.equals(WEB3OrderStatusDef.NOT_TRANSFERED))
                {
                    Date l_datSystemTimestamp = GtlUtils.getTradingSystem().getBizDate();
                    l_arrdataList.add(WEB3DateUtility.formatDate(l_datSystemTimestamp, l_strdateFormat));
                    l_arrdataList.add(String.valueOf(OrderOpenStatusEnum.CLOSED.intValue()));
                    l_arrdataList.add(String.valueOf(OrderExpirationStatusEnum.EXPIRED.intValue()));
                    l_arrdataList.add(WEB3ErrorReasonCodeDef.NORMAL);
                    l_arrdataList.add(WEB3ErrorReasonCodeDef.TRIGGER_ADMIN_MANUAL_EXPIRED);
                } else
                {
                    l_arrdataList.add(l_orderState);
                }
            }
        }

        // Step11 Add Conditions judging execType to created ArrayList
        if (l_request.execType != null)
        {
            if ((l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_NOT_PROMISE))
                || (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_TYPE_ONE_COMPLETE))
                || (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE)))
            {
                l_arrdataList.add(WEB3OrderStatusDef.OTHER);
            }
            
            if (l_request instanceof WEB3AdminORFeqExecuteListRequest ||
                l_request instanceof WEB3AdminORFeqOrderExecutionRefReferenceRequest)
            {
                if (l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ONE_COMPLETE) ||
                    l_request.execType.equals(WEB3AdminExecTypeDef.EXEC_PROCESSING_ALL_COMPLETE))
                {
                    l_arrdataList.add(WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC);
                }
                else
                {
                    l_arrdataList.add(WEB3TemporaryExecutionFlagDef.DEFAULT);
                }
            }
        }

        // Step12 Check l_request.changeCancelDiv != null and add it.
        if (l_request.changeCancelDiv != null)
        {
            l_arrdataList.add(l_request.changeCancelDiv);
        }

        // Step13 Check l_request.orderStartDate != null and add it.
        if (l_request.orderStartDate != null)
        {
            l_arrdataList.add(l_request.orderStartDate);
        }

        // Step14 Check l_request.orderEndDate != null and add it.
        if (l_request.orderEndDate != null)
        {
            l_arrdataList.add(l_request.orderEndDate);
        }

        // Step15 Check l_request.deliveryDate != null and add it.
        if (l_request.deliveryDate != null)
        {
            l_arrdataList.add(l_request.deliveryDate);
        }
        l_arrBranchCodes = new String[l_arrdataList.size()];
        l_arrBranchCodes = (String[]) l_arrdataList.toArray(l_arrBranchCodes);

        log.exiting(STR_METHOD_NAME);

        // Step16 return List
        return l_arrBranchCodes;
    }

    /**
     * (get注文種別)<BR>
     * <BR>
     * 引数の取引区分より、AP層で使用する注文種別（OrderTypeEnum）を返却する。 <BR>
     * <BR>
     * １）パラメータ.取引区分により分岐し、対応する値を返却する。<BR>
     * <BR>
     * 　@パラメータ.取引区分が、<BR>
     * 　@["現物買付注文"の場合]<BR>
     * 　@　@OrderTypeEnum.現物買付注文を返却する。<BR>
     * 　@["現物売付注文"の場合]<BR>
     * 　@　@OrderTypeEnum.現物売付注文を返却する。<BR>
     * 　@["新規買建注文"の場合]<BR>
     * 　@　@OrderTypeEnum.新規買建注文を返却する。<BR>
     * 　@["新規売建注文"の場合]<BR>
     * 　@　@OrderTypeEnum.新規売建注文を返却する。<BR>
     * 　@["買建返済注文"の場合]<BR>
     * 　@　@OrderTypeEnum.買建返済注文を返却する。<BR>
     * 　@["売建返済注文"の場合]<BR>
     * 　@　@OrderTypeEnum.売建返済注文を返却する。<BR>
     * 　@["現引注文"の場合]<BR>
     * 　@　@OrderTypeEnum.現引注文を返却する。<BR>
     * 　@["現渡注文"の場合]<BR>
     * 　@　@OrderTypeEnum.現渡注文を返却する。<BR>
     * 　@["ミニ株買付注文"の場合]<BR>
     * 　@　@OrderTypeEnum.ミニ株買付注文を返却する。<BR>
     * 　@["ミニ株売付注文"の場合]<BR>
     * 　@　@OrderTypeEnum.ミニ株売付注文を返却する。<BR>
     * 　@["投資信託買付注文"の場合]<BR>
     * 　@　@OrderTypeEnum.投資信託買付注文を返却する。<BR>
     * 　@["投資信託売付注文"の場合]<BR>
     * 　@　@OrderTypeEnum.投資信託売付注文を返却する。<BR>
     * 　@["投信募集注文"の場合]<BR>
     * 　@　@OrderTypeEnum.投信募集注文を返却する。<BR>
     * 　@["投信乗換注文"の場合]<BR>
     * 　@　@OrderTypeEnum.投信乗換注文を返却する。<BR>
     * 　@["累投買付注文"の場合]<BR>
     * 　@　@OrderTypeEnum.累投買付注文を返却する。<BR>
     * 　@["累投売付注文"の場合]<BR>
     * 　@　@OrderTypeEnum.累投売付注文を返却する。<BR>
     * 　@["指数先物新規買建注文"の場合]<BR>
     * 　@　@OrderTypeEnum.指数先物新規買建注文を返却する。<BR>
     * 　@["指数先物新規売建注文"の場合]<BR>
     * 　@　@OrderTypeEnum.指数先物新規売建注文を返却する。<BR>
     * 　@["指数先物売建返済注文"の場合]<BR>
     * 　@　@OrderTypeEnum.指数先物売建返済注文を返却する。<BR>
     * 　@["指数先物買建返済注文"の場合]<BR>
     * 　@　@OrderTypeEnum.指数先物買建返済注文を返却する。<BR>
     * 　@["指数オプション新規買建注文"の場合]<BR>
     * 　@　@OrderTypeEnum.指数オプション新規買建注文を返却する。<BR>
     * 　@["指数オプション新規売建注文"の場合]<BR>
     * 　@　@OrderTypeEnum.指数オプション新規売建注文を返却する。<BR>
     * 　@["指数オプション売建返済注文"の場合]<BR>
     * 　@　@OrderTypeEnum.指数オプション売建返済注文を返却する。<BR>
     * 　@["指数オプション買建返済注文"の場合]<BR>
     * 　@　@OrderTypeEnum.指数オプション買建返済注文を返却する。<BR>
     * 　@["外国株式買付"の場合]<BR>
     * 　@　@OrderTypeEnum.外国株式買付注文を返却する。<BR>
     * 　@["外国株式売付"の場合]<BR>
     * 　@　@OrderTypeEnum.外国株式売付注文を返却する。<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * ------<English>-----------------------<BR>
     * <BR>
     * getOrderType<BR>
     * <BR>
     * Return orderType（OrderTypeEnum） to use in AP layer from the argument,
     * l_tradingType<BR>
     * <BR>
     * 1)Go to one of the following processes according to l_strtradingType, and<BR>
     * return the corresponding value<BR>
     * <BR>
     * 　@If [l_tradingType is Def.EQUITY_BUY]<BR>
     * 　@　@OrderTypeEnum.Def.EQUITY_BUY<BR>
     * 　@If [l_tradingType is Def.EQUITY_SELL]<BR>
     * 　@　@OrderTypeEnum.Def.EQUITY_SELL<BR>
     * 　@If [l_tradingType is Def.MARGIN_LONG]<BR>
     * 　@　@OrderTypeEnum.Def.MARGIN_LONG<BR>
     * 　@If [l_tradingType is Def.MARGIN_SHORT]<BR>
     * 　@　@OrderTypeEnum.Def.MARGIN_SHORT<BR>
     * 　@If [l_tradingType is Def.CLOSE_MARGIN_LONG]<BR>
     * 　@　@OrderTypeEnum.Def.CLOSE_MARGIN_LONG<BR>
     * 　@If [l_tradingType is Def.CLOSE_MARGIN_SHORT]<BR>
     * 　@　@OrderTypeEnum.Def.CLOSE_MARGIN_SHORT<BR>
     * 　@If [l_tradingType is Def.SWAP_MARGIN_LONG]<BR>
     * 　@　@OrderTypeEnum.Def.SWAP_MARGIN_LONG<BR>
     * 　@If [l_tradingType is Def.SWAP_MARGIN_SHORT]<BR>
     * 　@　@OrderTypeEnum.Def.SWAP_MARGIN_SHORT<BR>
     * 　@If [l_tradingType is Def.MINI_STOCK_BUY]<BR>
     * 　@　@OrderTypeEnum.Def.MINI_STOCK_BUY<BR>
     * 　@If [l_tradingType is Def.MINI_STOCK_SELL]<BR>
     * 　@　@OrderTypeEnum.Def.MINI_STOCK_SELL<BR>
     * 　@If [l_tradingType is Def.MF_BUY]<BR>
     * 　@　@OrderTypeEnum.Def.MF_BUY<BR>
     * 　@If [l_tradingType is Def.MF_SELL]<BR>
     * 　@　@OrderTypeEnum.Def.MF_SELL<BR>
     * 　@If [l_tradingType is Def.MF_RECRUIT]<BR>
     * 　@　@OrderTypeEnum.Def.MF_RECRUIT<BR>
     * 　@If [l_tradingType is Def.MF_SWITCHING]<BR>
     * 　@　@OrderTypeEnum.Def.MF_SWITCHING<BR>
     *   If [l_tradingType is Def.RUITO_BUY]<BR>
     * 　@　@OrderTypeEnum.Def.RUITO_BUY<BR>
     * 　@If [l_tradingType is Def.RUITO_SELL]<BR>
     * 　@　@OrderTypeEnum.Def.RUITO_SELL<BR>
     * 　@If [l_tradingType is Def.IDX_FUTURES_BUY_TO_OPEN]<BR>
     * 　@　@OrderTypeEnum.Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 　@If [l_tradingType is Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 　@　@OrderTypeEnum.Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 　@If [l_tradingType is Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 　@　@OrderTypeEnum.Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 　@If [l_tradingType is Def.IDX_FUTURES_SELL_TO_CLOSE]<BR>
     * 　@　@OrderTypeEnum.Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 　@If [l_tradingType is Def.IDX_OPTIONS_BUY_TO_OPEN]<BR>
     * 　@　@OrderTypeEnum.Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 　@If [l_tradingType is Def.IDX_OPTIONS_SELL_TO_OPEN<]<BR>
     * 　@　@OrderTypeEnum.Def.IDX_OPTIONS_SELL_TO_OPEN<<BR>
     * 　@If [l_tradingType is Def.IDX_OPTIONS_BUY_TO_CLOSE]<BR>
     * 　@　@OrderTypeEnum.Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 　@If [l_tradingType is Def.IDX_OPTIONS_SELL_TO_CLOSE]<BR>
     * 　@　@OrderTypeEnum.Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * 　@If [l_tradingType is Def.FEQ_BUY]<BR>
     * 　@　@OrderTypeEnum.Def.FEQ_BUY<BR>
     * 　@If [l_tradingType is Def.FEQ_SELL]<BR>
     * 　@　@OrderTypeEnum.Def.FEQ_SELL<BR>
     * 　@[For other cases]<BR>
     * 　@　@Return null<BR>
     * <BR>
     * @@param l_strtradingType - (取引区分)<BR>
     * <BR>
     * 取引区分<BR>
     * <BR>
     * 1：　@現物買付注文<BR>
     * 2：　@現物売付注文<BR>
     * 3：　@新規買建注文<BR>
     * 4：　@新規売建注文<BR>
     * 5：　@買建返済注文<BR>
     * 6：　@売建返済注文<BR>
     * 7：　@現引注文<BR>
     * 8：　@現渡注文<BR>
     * 99：　@立会外分売<BR>
     * 101：　@ミニ株買付注文<BR>
     * 102：　@ミニ株売付注文<BR>
     * 201：　@投資信託買付注文<BR>
     * 202：　@投資信託売付注文<BR>
     * 203：　@投信募集注文<BR>
     * 204：　@投信乗換注文<BR>
     * 501：　@累投買付注文<BR>
     * 502：　@累投売付注文<BR>
     * 601：　@指数先物新規買建注文<BR>
     * 602：　@指数先物新規売建注文<BR>
     * 603：　@指数先物売建返済注文<BR>
     * 604：　@指数先物買建返済注文<BR>
     * 605：　@指数オプション新規買建注文<BR>
     * 606：　@指数オプション新規売建注文<BR>
     * 607：　@指数オプション売建返済注文<BR>
     * 608：　@指数オプション買建返済注文<BR>
     * 701：　@外国株式買付<BR>
     * 702：　@外国株式売付<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * tradingType<BR>
     * 1: Def.EQUITY_BUY<BR>
     * 2: Def.EQUITY_SELL<BR>
     * 3: Def.MARGIN_LONG<BR>
     * 4: Def.MARGIN_SHORT<BR>
     * 5: Def.CLOSE_MARGIN_LONG<BR>
     * 6: Def.CLOSE_MARGIN_SHORT<BR>
     * 7: Def.SWAP_MARGIN_LONG<BR>
     * 8: Def.SWAP_MARGIN_SHORT<BR>
     * 99: Def.SALES_OUTSIDE_MARKET <BR>
     * 101: Def.MINI_STOCK_BUY<BR>
     * 102: Def.MINI_STOCK_SELL<BR>
     * 201: Def.MF_BUY<BR>
     * 202: Def.MF_SELL<BR>
     * 203: Def.MF_RECRUIT<BR>
     * 204: Def.MF_SWITCHING<BR>
     * 501: Def.RUITO_BUY<BR>
     * 502: Def.RUITO_SELL<BR>
     * 601: Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 602: Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 603: Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 604: Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 605: Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 606: Def.IDX_OPTIONS_SELL_TO_OPEN<BR>
     * 607: Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 608: Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * 701: Def.FEQ_BUY <BR>
     * 702: Def.FEQ_SELL <BR>
     * <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum
     * @@roseuid 41B3B90402C1
     */
    public OrderTypeEnum getOrderType(String l_strtradingType)
    {

        final String STR_METHOD_NAME = "getOrderType(String tradingType)";
        log.entering(STR_METHOD_NAME);

        OrderTypeEnum l_orderTypeEnum = null;

        /*
          *  Step1 Do one of following processes according to l_tradingType, and
          * return the corresponding value
          */
        if (l_strtradingType
            .equals(WEB3StringTypeUtility.formatNumber(OrderTypeEnum.EQUITY_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.EQUITY_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.EQUITY_SELL;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MARGIN_LONG.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MARGIN_SHORT.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.CLOSE_MARGIN_LONG.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.CLOSE_MARGIN_SHORT.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.SWAP_MARGIN_LONG.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.SWAP_MARGIN_SHORT.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_SHORT;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MINI_STOCK_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MINI_STOCK_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MINI_STOCK_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MINI_STOCK_SELL;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MF_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MF_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SELL;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MF_RECRUIT.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_RECRUIT;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.MF_SWITCHING.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.MF_SWITCHING;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.RUITO_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.RUITO_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.RUITO_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.RUITO_SELL;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.FEQ_BUY.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.FEQ_BUY;
        } else if (
            l_strtradingType.equals(
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.FEQ_SELL.intValue())))
        {
            l_orderTypeEnum = OrderTypeEnum.FEQ_SELL;
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderTypeEnum;

    }

    /**
     * (get取引区分)<BR>
     * <BR>
     * 引数の注文種別より、PR層で使用する取引区分を返却する。<BR>
     * <BR>
     * １）パラメータ.注文種別により分岐し、対応する値を返却する。<BR>
     * <BR>
     * 　@パラメータ.注文種別が、<BR>
     * 　@[OrderTypeEnum.現物買付注文の場合]<BR>
     * 　@　@"現物買付注文"を返却する。<BR>
     * 　@[OrderTypeEnum.現物売付注文の場合]<BR>
     * 　@　@"現物売付注文"を返却する。<BR>
     * 　@[OrderTypeEnum.新規買建注文の場合]<BR>
     * 　@　@"新規買建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.新規売建注文の場合]<BR>
     * 　@　@"新規売建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.買建返済注文の場合]<BR>
     * 　@　@"買建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.売建返済注文の場合]<BR>
     * 　@　@"売建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.現引注文の場合]<BR>
     * 　@　@"現引注文"を返却する。<BR>
     * 　@[OrderTypeEnum.現渡注文の場合]<BR>
     * 　@　@"現渡注文"を返却する。<BR>
     * 　@[OrderTypeEnum.ミニ株買付注文の場合]<BR>
     * 　@　@"ミニ株買付注文"を返却する。<BR>
     * 　@[OrderTypeEnum.ミニ株売付注文の場合]<BR>
     * 　@　@"ミニ株売付注文"を返却する。<BR>
     * 　@[OrderTypeEnum.投資信託買付注文の場合]<BR>
     * 　@　@"投資信託買付注文"を返却する。<BR>
     * 　@[OrderTypeEnum.投資信託売付注文の場合]<BR>
     * 　@　@"投資信託売付注文"を返却する。<BR>
     * 　@[OrderTypeEnum.投資信託募集注文の場合]<BR>
     * 　@　@"投資信託募集注文"を返却する。<BR>
     * 　@[OrderTypeEnum.投資信託乗換注文の場合]<BR>
     * 　@　@"投資信託乗換注文"を返却する。<BR>
     * 　@[OrderTypeEnum.累投買付注文の場合]<BR>
     * 　@　@"累投買付注文"を返却する。<BR>
     * 　@[OrderTypeEnum.累投売付注文の場合]<BR>
     * 　@　@"累投売付注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数先物新規買建注文の場合]<BR>
     * 　@　@"指数先物新規買建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数先物新規売建注文の場合]<BR>
     * 　@　@"指数先物新規売建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数先物売建返済注文の場合]<BR>
     * 　@　@"指数先物売建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数先物買建返済注文の場合]<BR>
     * 　@　@"指数先物買建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数オプション新規買建注文の場合]<BR>
     * 　@　@"指数オプション新規買建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数オプション新規売建注文の場合]<BR>
     * 　@　@"指数オプション新規売建注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数オプション売建返済注文の場合]<BR>
     * 　@　@"指数オプション売建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.指数オプション買建返済注文の場合]<BR>
     * 　@　@"指数オプション買建返済注文"を返却する。<BR>
     * 　@[OrderTypeEnum.外国株式買付の場合]<BR>
     * 　@　@"外国株式買付"を返却する。<BR>
     * 　@[OrderTypeEnum.外国株式売付の場合]<BR>
     * 　@　@"外国株式売付"を返却する。<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * ----<English>---------------------<BR>
     * <BR>
     * getTradingType<BR>
     * <BR>
     * Return tradingType to use in PR layer from the argument, l_orderType<BR>
     * <BR>
     * 1)Go to one of the following processes according to l_orderType, and<BR>
     * return the corresponding value<BR>
     * <BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.EQUITY_BUY]<BR>
     * 　@　@Return Def.EQUITY_BUY<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.EQUITY_SELL]<BR>
     * 　@　@Return Def.EQUITY_SELL<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.MARGIN_LONG]<BR>
     * 　@　@Return Def.MARGIN_LONG<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.MARGIN_SHORT]<BR>
     * 　@　@Return Def.MARGIN_SHORT<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.CLOSE_MARGIN_LONG]<BR>
     * 　@　@Return Def.CLOSE_MARGIN_LONG<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.CLOSE_MARGIN_SHORT]<BR>
     * 　@　@Return Def.CLOSE_MARGIN_SHORT<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.SWAP_MARGIN_LONG]<BR>
     * 　@　@Return Def.SWAP_MARGIN_LONG<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.SWAP_MARGIN_SHORT]<BR>
     * 　@　@Return Def.SWAP_MARGIN_SHORT<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.MINI_STOCK_BUY]<BR>
     * 　@　@Return Def.MINI_STOCK_BUY<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.MINI_STOCK_SELL]<BR>
     * 　@　@Return Def.MINI_STOCK_SELL<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.MF_BUY]<BR>
     * 　@　@Return Def.MF_BUY<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.MF_SELL]<BR>
     * 　@　@Return Def.MF_SELL<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.MF_RECRUIT]<BR>
     * 　@　@Return Def.MF_RECRUIT<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.MF_SWITCHING]<BR>
     * 　@　@Return Def.MF_SWITCHING<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.RUITO_BUY]<BR>
     * 　@　@Return Def.RUITO_BUY<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.RUITO_SELL]<BR>
     * 　@　@Return Def.RUITO_SELL<BR>
     * 　@If [l_orderType is OrderTypeEnum.DefIDX_FUTURES_BUY_TO_OPEN]<BR>
     * 　@　@Return Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.IDX_FUTURES_SELL_TO_OPEN]<BR>
     * 　@　@Return Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.IDX_FUTURES_BUY_TO_CLOSE]<BR>
     * 　@　@Return Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def..IDX_FUTURES_SELL_TO_CLOSE]<BR>
     * 　@　@Return Def..IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.IDX_OPTIONS_BUY_TO_OPEN]<BR>
     * 　@　@Return Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.IDX_OPTIONS_SELL_TO_OPEN]<BR>
     * 　@　@Return DefIDX_OPTIONS_SELL_TO_OPEN<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.IDX_OPTIONS_BUY_TO_CLOSE]<BR>
     * 　@　@Return Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def..IDX_OPTIONS_SELL_TO_CLOSE]<BR>
     * 　@　@Return Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.FEQ_BUY]<BR>
     * 　@　@Return Def.FEQ_BUY<BR>
     * 　@If [l_orderType is OrderTypeEnum.Def.FEQ_SELL]<BR>
     * 　@　@Return Def.FEQ_SELL<BR>
     * 　@[For other cases]<BR>
     * 　@　@Return null<BR>
     * <BR>
     * @@param l_orderType - (注文種別)<BR>
     * <BR>
     * 注文種別<BR>
     * (OrderTypeEnumにて定義)<BR>
     * <BR>
     * -----<English>-----------<BR>
     * <BR>
     * l_orderType<BR>
     * (Defined at OrderTypeEnum)<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41B3BC38033E
     */
    public String getTradingType(OrderTypeEnum l_orderType)
    {
        final String STR_METHOD_NAME = "getTradingType(OrderTypeEnum l_orderType)";
        log.entering(STR_METHOD_NAME);

        String l_strReturnValue = null;

        /*
         * Step1 Go to one of the following processes according to l_orderType, and
         * return the corresponding value
         */
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.EQUITY_BUY);
        } else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.EQUITY_SELL);
        } else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MARGIN_LONG);
        } else if (OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MARGIN_SHORT);
        } else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.CLOSE_MARGIN_LONG);
        } else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.CLOSE_MARGIN_SHORT);
        } else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_LONG);
        } else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT);
        } else if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MINI_STOCK_BUY);
        } else if (OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MINI_STOCK_SELL);
        } else if (OrderTypeEnum.MF_BUY.equals(l_orderType))
        {
            l_strReturnValue = WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MF_BUY);
        } else if (OrderTypeEnum.MF_SELL.equals(l_orderType))
        {
            l_strReturnValue = WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MF_SELL);
        } else if (OrderTypeEnum.MF_RECRUIT.equals(l_orderType))
        {
            l_strReturnValue = WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MF_RECRUIT);
        } else if (OrderTypeEnum.MF_SWITCHING.equals(l_orderType))
        {
            l_strReturnValue = WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.MF_SWITCHING);
        } else if (OrderTypeEnum.RUITO_BUY.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.RUITO_BUY);
        } else if (OrderTypeEnum.RUITO_SELL.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.RUITO_SELL);
        } else if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_OPEN);
        } else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_OPEN);
        } else if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_FUTURES_BUY_TO_CLOSE);
        } else if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_FUTURES_SELL_TO_CLOSE);
        } else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_OPEN);
        } else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_OPEN);
        } else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_OPTIONS_BUY_TO_CLOSE);
        } else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.IDX_OPTIONS_SELL_TO_CLOSE);
        } else if (OrderTypeEnum.FEQ_BUY.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.FEQ_BUY);
        } else if (OrderTypeEnum.FEQ_SELL.equals(l_orderType))
        {
            l_strReturnValue =
                WEB3StringTypeUtility.formatNumber(
                    OrderTypeEnum.IntValues.FEQ_SELL);
        } else
        {
            l_strReturnValue = null;
        }

        log.exiting(STR_METHOD_NAME);

        return l_strReturnValue;
    }

    /**
     * (get注文状態区分（PR層）)<BR>
     * <BR>
     * パラメータ.注文単位より、PR層で使用する注文状態区分を返却する。<BR>
     * <BR>
     * １）パラメータ.注文単位の保持するデータにより分岐し、<BR>
     * 　@対応する注文状態区分を返却する。<BR>
     * <BR>
     * 　@１－１）手動失効の判定<BR>
     * 　@　@　@注文単位.注文有効状態 == CLOSED（クローズ） かつ<BR>
     * 　@　@　@注文単位.失効区分 == INVALIDATED_BY_MKT（マーケット拒否）かつ<BR>
     * 　@　@　@注文単位.注文エラー理由コード == ("W001:株式管理者手動失効済", <BR>
     * "W004:先物OP管理者手動失効済")の場合、"手動失効"を返す。<BR>
     * <BR>
     * 　@１－２）上記以外の場合、パラメータ.注文単位の型により、<BR>
     * 　@　@以下のメソッドを呼び分ける。<BR>
     * 　@　@[パラメータ.注文単位の型 == "株式注文単位"の場合]<BR>
     * 　@　@　@株式データアダプタ.get注文状態区分(パラメータ.注文単位)メソッドを<BR>
     * 　@　@　@コールし、戻り値を返却する。<BR>
     * <BR>
     * 　@　@[パラメータ.注文単位の型 == "先物OP注文単位"の場合]<BR>
     * 　@　@　@先物OPデータアダプタ.get注文状態区分(パラメータ.注文単位)メソッドを<BR>
     * 　@　@　@コールし、戻り値を返却する。<BR>
     * <BR>
     * 　@　@[上記以外の型の場合]<BR>
     * 　@　@　@パラメータ.注文単位.注文状態.intValueを文字列で返す。 <BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * <BR>
     * 注文単位オブジェクト<BR>
     * <BR>
     * l_orderUnit<BR>
     * <BR>
     * @@return java.lang.String
     * @@throws WEB3BaseException
     * @@roseuid 41B3C47401D7
     */
    public String getOrderStateDivPR(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderStateDivPR(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        String l_strreturnValue = null;
        String l_strReasonCode = null;

        if ((l_orderUnit instanceof EqTypeOrderUnit) || (l_orderUnit instanceof IfoOrderUnit))
        {
            //Check if l_orderUnit is type of EqTypeOrderUnit
            if (l_orderUnit instanceof EqTypeOrderUnit)
            {
                EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit) l_orderUnit;
                EqtypeOrderUnitParams l_equityParams =
                    new EqtypeOrderUnitParams(
                        (EqtypeOrderUnitRow) l_eqtypeOrderUnit.getDataSourceObject());
                l_strReasonCode = l_equityParams.error_reason_code;
            } else
            {
                IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit) l_orderUnit;
                IfoOrderUnitParams l_ifoParams =
                    new IfoOrderUnitParams((IfoOrderUnitRow) l_ifoOrderUnit.getDataSourceObject());
                l_strReasonCode = l_ifoParams.error_reason_code;
            }
        }
        
        // 手動失効かどうかの判別
        if ((l_orderUnit.getOrderOpenStatus().equals(OrderOpenStatusEnum.CLOSED))
            && (l_orderUnit.getExpirationStatus().equals(
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT))
            && ((WEB3ErrorReasonCodeDef.EQUITY_ADMIN_MANUAL_EXPIRED.equals(l_strReasonCode))
            || (WEB3ErrorReasonCodeDef.FUTURE_OP_ADMIN_MANUAL_EXPIRED.equals(l_strReasonCode))))
        {
            l_strreturnValue = WEB3OrderStatusDef.MANUAL_EXPIRED;
            log.exiting(STR_METHOD_NAME);
            return l_strreturnValue;
        }
        
        // 上記以外の場合、パラメータ.注文単位の型により、以下のメソッドを呼び分ける。
        if (l_orderUnit instanceof EqTypeOrderUnit)
        {
            try
            {
	            l_strreturnValue = WEB3EquityDataAdapter.getOrderState((EqTypeOrderUnit)l_orderUnit);
            }
            catch (WEB3BaseException l_wbe)
            {
                log.error(l_wbe.getErrorMessage(), l_wbe);
            }
        }
        else if (l_orderUnit instanceof IfoOrderUnit)
        {
            l_strreturnValue = WEB3IfoDataAdapter.getOrderStatusType((IfoOrderUnit)l_orderUnit);
        }
        else
        {
            l_strreturnValue = Integer.toString(l_orderUnit.getOrderStatus().intValue());
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strreturnValue;
    }

    /**
     * (get約定状態区分（PR層）)<BR>
     * <BR>
     * パラメータ.注文単位より、PR層で使用する約定状態区分を返却する。<BR>
     * <BR>
     * １）パラメータ.注文単位の保持するデータにより分岐し、<BR>
     * 　@対応する約定状態区分を返却する。<BR>
     * <BR>
     * 　@１－１）パラメータ.注文単位.isPartiallyExecuted( ) == trueの場合は、<BR>
     * 　@　@"一部成立"を返す。 <BR>
     * <BR>
     * 　@１－２）パラメータ.注文単位.isFullyExecuted( ) == trueの場合は、<BR>
     * 　@　@"全部成立"を返す。 <BR>
     * <BR>
     * 　@１－３）上記以外の場合は、"未約定"を返す。 <BR>
     * <BR>
     * ------<English>---------------<BR>
     * <BR>
     * getExecTypeDivPR<BR>
     * <BR>
     * Return execType to use in PR layer from l_orderUnit<BR>
     * <BR>
     * 1)Go to one of the following processes according to the data in l_orderUnit,
     * and<BR>
     * 　@return the corresponding execType<BR>
     * <BR>
     * 　@1-1)If l_orderUnit.isPartiallyExecuted( ) == true,<BR>
     * 　@　@Return Def.EXEC_TYPE_ONE_COMPLETE<BR>
     * <BR>
     * 　@1-2)If l_orderUnit.isFullyExecuted( ) == true,<BR>
     * 　@　@Return Def.EXEC_TYPE_ALL_COMPLETE<BR>
     * <BR>
     * 　@1-3)Return Def.EXEC_TYPE_NOT_PROMISE for other cases<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * <BR>
     * 注文単位オブジェクト<BR>
     * <BR>
     * l_orderUnit<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 41B3DD74026B
     */
    public String getExecTypeDivPR(OrderUnit l_orderUnit)
    {

        final String STR_METHOD_NAME = "getExecTypeDivPR(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        String l_strreturnValue = null;

        /*
         * Step1 Go to one of the following processes according to the data in l_orderUnit,
         * return the corresponding execType
         */
        if (l_orderUnit.isPartiallyExecuted())
        {
            l_strreturnValue = WEB3AdminExecTypeDef.EXEC_TYPE_ONE_COMPLETE;
        } else if (l_orderUnit.isFullyExecuted())
        {
            l_strreturnValue = WEB3AdminExecTypeDef.EXEC_TYPE_ALL_COMPLETE;
        } else
        {
            l_strreturnValue = WEB3AdminExecTypeDef.EXEC_TYPE_NOT_PROMISE;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strreturnValue;
    }

    /**
     * (get顧客一覧)<BR>
     * <BR>
     * 引数の条件に該当する顧客の一覧を返却する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）DB検索<BR>
     * 　@パラメータ.部店コード[]の要素数分以下の処理を繰り返す。<BR>
     * 　@２－１）拡張アカウントマネージャ.get顧客()メソッドをコールする。<BR>
     * <BR>
     * 　@　@[get顧客()にセットするパラメータ]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@部店コード：　@処理対象の部店コード<BR>
     * 　@　@　@口座コード：　@パラメータ.口座コード<BR>
     * <BR>
     * 　@２－２）２－１）の結果が取得できた場合は、<BR>
     * 　@　@生成したArrayListに追加する。<BR>
     * 　@<BR>
     * ３）ArrayList.toArray()の戻り値を返却する。<BR>
     * 　@※toArray()の戻り値.length == 0の場合、<BR>
     * 　@　@「該当データなし」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00398<BR>
     * <BR>
     * -------<English>------------------<BR>
     * <BR>
     * getAccountList<BR>
     * <BR>
     * Return a list of account corresponding to conditions of the arguments<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)Serach DB<BR>
     * 　@Loop the following process for as many times as the number of the elements of
     * l_branchCode<BR>
     * 　@2-1)Call WEB3GentradeAccountManager.getMainAccount() method<BR>
     * <BR>
     * 　@　@[Parameter set into getMainAccount()]<BR>
     * 　@　@　@l_strInstitutionCode:　@parameter.l_strInstitutionCode<BR>
     * 　@　@　@l_branchCode: l_branchCode to be processed<BR>
     * 　@　@　@l_accountCode: parameter.l_accountCodeBR>
     * <BR>
     * 　@2-2)If it is able to acquire the result of 2-1),<BR>
     * 　@　@ add it to created ArrayList<BR>
     * 　@<BR>
     * 3)Return return values of ArrayList.toArray()<BR>
     * 　@※If return value of toArray().length == 0,<BR>
     * 　@　@Throw the exception "No corresponding data"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00398<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * <BR>
     * 証券会社コード<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_branchCode - (部店コード)<BR>
     * <BR>
     * 部店コード<BR>
     * <BR>
     * l_branchCode<BR>
     * <BR>
     * @@param l_accountCode - (口座コード)<BR>
     * <BR>
     * 口座コード<BR>
     * <BR>
     * l_accountCode<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@return WEB3GentradeMainAccount[]
     * @@roseuid 41B3EAEE03C3
     */
    public WEB3GentradeMainAccount[] getAccountList(
        String l_strInstitutionCode,
        String[] l_branchCode,
        String l_accountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getAccountList(String l_strInstitutionCode, "
                + "String[] l_branchCode, String l_accountCode)";
        log.entering(STR_METHOD_NAME);
        int l_intCount = 0;
        int l_intlbranchCodeLength = 0;

        //Step1 Create ArrayList
        ArrayList l_arrAccountMain = new ArrayList();
        WEB3GentradeAccountManager l_web3GentradeAccountManager = null;
        WEB3GentradeMainAccount l_web3GentradeMainAccount = null;
        l_intlbranchCodeLength = l_branchCode.length;

        /* Step 2 Loop the following process for as many times as the number of the
         * elements of l_branchCode
         */
        l_web3GentradeAccountManager = new WEB3GentradeAccountManager();
        for (l_intCount = 0; l_intCount < l_intlbranchCodeLength; l_intCount++)
        {
        	try
        	{
	            l_web3GentradeMainAccount =
	                l_web3GentradeAccountManager.getMainAccount(
	                    l_strInstitutionCode,
	                    l_branchCode[l_intCount],
	                    l_accountCode);
	
	            //Step 2.2 Add Object to List
	            l_arrAccountMain.add(l_web3GentradeMainAccount);
        	}
			catch (WEB3BaseException l_exp)
			{
				continue;
			}
        }

        /*
          * Step3 If return value of toArray().length == 0,<BR>
          * Throw the exception "No corresponding data"<BR>
          */
        if (l_arrAccountMain.size() == 0)
        {
            String l_strMsg = "No corresponding data ";
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strMsg);
        }

        WEB3GentradeMainAccount[] l_gntradeMainAccounts =
            new WEB3GentradeMainAccount[l_arrAccountMain.size()];
        l_arrAccountMain.toArray(l_gntradeMainAccounts);

        log.exiting(STR_METHOD_NAME);

        //Step3 Return return values of ArrayList.toArray()<BR>
        return l_gntradeMainAccounts;
    }

    /**
     * (create詳細画面情報一覧)<BR>
     * <BR>
     * 引数の注文約定照会共通Unit一覧より、詳細画面情報の一覧を<BR>
     * 作成し、返却する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）パラメータ.注文約定照会共通Unit一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@２－１）注文約定照会共通Unit.商品区分 == "株式ミニ投資"の場合、<BR>
     * 　@　@次の要素へ処理を移行する。(continue)<BR>
     * <BR>
     * 　@２－２）詳細画面情報インスタンスを生成する。<BR>
     * <BR>
     * 　@２－３）生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@注文ID = 注文約定照会共通Unit.ID<BR>
     * 　@　@商品区分 = 注文約定照会共通Unit.商品区分<BR>
     *     口座ID = 注文約定照会共通Unit.IDに該当する注文.口座ID<BR>
     * <BR>
     * 　@２－４）生成したArrayListにプロパティセットしたインスタンスを<BR>
     * 　@　@追加する。<BR>
     * <BR>
     * ３）生成したArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * -----<English>------------------------<BR>
     * <BR>
     * createExecuteDetailsInfoList<BR>
     * <BR>
     * Create executeDetailsInfoList from the argument,
     * l_orderExecutionRefReferenceUnitList, and<BR>
     * return it<BR>
     * <BR>
     * 1)Create ArrayList<BR>
     * <BR>
     * 2)Loop the following process for as many times as the number of<BR>
     * the elements of l_orderExecutionRefReferenceUnitList<BR>
     * <BR>
     * 　@2-1)
     *  If l_orderExecutionRefReferenceUnitList.productDiv == Def.MINI_STOCK<BR>
     *    continue processing to the next element(continue)<BR>
     * <BR>
     * 　@2-2)Create WEB3AdminORDetailDispInfoUnit<BR>
     * <BR>
     * 　@2-3)Set the following properties into the created instance<BR>
     * 　@　@orderId = l_orderExecutionRefReferenceUnitList.Id<BR>
     * 　@　@productDiv = l_orderExecutionRefReferenceUnitList.productDiv<BR>
     * <BR>
     * 　@2-4)Add the instance set in 'Property Set' to the created ArrayList<BR>
     * <BR>
     * 3)Return the return value of created ArrayList.toArray()<BR>
     * <BR>
     * @@param l_orderExecutionRefReferenceUnitList - (注文約定照会共通Unit一覧)<BR>
     * <BR>
     * 管理者注文約定照会共通Unitの配列<BR>
     * <BR>
     * l_orderExecutionRefReferenceUnitList<BR>
     * <BR>
     * @@return WEB3AdminDetailDispInfoUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 41B55AD500D7
     */
    public WEB3AdminORDetailDispInfoUnit[]
         createExecuteDetailsInfoList(
         WEB3AdminOROrderExecutionRefCommon[] l_orderExecutionRefReferenceUnitList)
         throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createExecuteDetailsInfoList(WEB3AdminOROrderExecutionRefCommon[] "
                + "l_orderExecutionRefReferenceUnitList)";
        log.entering(STR_METHOD_NAME);

        //Step1 Create ArrayList l_orderExecutionRefReferenceUnitList
        ArrayList l_arrorderExecutionRefReferenceUnitList = new ArrayList();
        WEB3AdminORDetailDispInfoUnit[] l_web3AdminORDetailDispInfoUnit = null;
        WEB3AdminORDetailDispInfoUnit l_adminORDetailDispInfoUnit = null;
        int l_intorderLength = 0;
        int l_intCount = 0;

        /*Step2 Loop the following process for as many times as elements
         * of l_orderExecutionRefReferenceUnitList
         */
        l_intorderLength = l_orderExecutionRefReferenceUnitList.length;
        for (l_intCount = 0; l_intCount < l_intorderLength; l_intCount++)
        {
            //Step2.1 Check with MINI_STOCK
            if (l_orderExecutionRefReferenceUnitList[l_intCount].productDiv
                == WEB3AdminProductDivDef.MINI_STOCK)
            {
                continue;
            }

            //Step2.2 Create WEB3AdminORDetailDispInfoUnit
            l_adminORDetailDispInfoUnit = new WEB3AdminORDetailDispInfoUnit();

            /*
             * Step2.3 Set the following properties into the created instance
             * orderId = l_orderExecutionRefReferenceUnitList.id
             * productDiv = l_orderExecutionRefReferenceUnitList.productDiv
             */
            l_adminORDetailDispInfoUnit.orderId =
                l_orderExecutionRefReferenceUnitList[l_intCount].id;
            l_adminORDetailDispInfoUnit.productDiv =
                l_orderExecutionRefReferenceUnitList[l_intCount].productDiv;
                
            long l_lngOrderId = Long.parseLong(l_adminORDetailDispInfoUnit.orderId);
            long l_lngAccountId = 0;
            try
            {
                // 株・信用注文の場合
	            if (l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.EQUITY)
	            || l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.MARGIN))
	            {
	                EqtypeOrderRow l_eqtypeOrderRow = EqtypeOrderDao.findRowByOrderId(l_lngOrderId);
	                if (l_eqtypeOrderRow == null)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                        "注文ID:" + l_lngOrderId + "に該当する注文が取得できませんでした。"
                        + "商品区分:" + l_adminORDetailDispInfoUnit.productDiv);
                    }
	                l_lngAccountId = l_eqtypeOrderRow.getAccountId();
	            }
	            // 先物・オプション注文の場合
	            else if (l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.OPTION)
	            || l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.FUTURE))
	            {
	                IfoOrderRow l_ifoOrderRow = IfoOrderDao.findRowByOrderId(l_lngOrderId);
                    if (l_ifoOrderRow == null)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                        "注文ID:" + l_lngOrderId + "に該当する注文が取得できませんでした。"
                        + "商品区分:" + l_adminORDetailDispInfoUnit.productDiv);
                    }
	                l_lngAccountId = l_ifoOrderRow.getAccountId();
	            }
                //外国株式注文の場合
                else if (l_adminORDetailDispInfoUnit.productDiv.equals(WEB3AdminProductDivDef.FEQ))
                {
                    FeqOrderRow l_feqOrderRow = FeqOrderDao.findRowByOrderId(l_lngOrderId);
                    if (l_feqOrderRow == null)
                    {
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                        "注文ID:" + l_lngOrderId + "に該当する注文が取得できませんでした。"
                        + "商品区分:" + l_adminORDetailDispInfoUnit.productDiv);
                    }
                    l_lngAccountId = l_feqOrderRow.getAccountId();
                }
            }
            catch (DataFindException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "注文ID:" + l_lngOrderId + "に該当する注文が取得できませんでした。"
                    + "商品区分:" + l_adminORDetailDispInfoUnit.productDiv,
                    l_ex);
            }
            catch (DataException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DBアクセスに失敗しました。",
                    l_ex);
            }
            l_adminORDetailDispInfoUnit.accountID = String.valueOf(l_lngAccountId);

            //Step2.4 Add the instance set in 'Property Set' to the created ArrayList
            l_arrorderExecutionRefReferenceUnitList.add(l_adminORDetailDispInfoUnit);
        }

        l_web3AdminORDetailDispInfoUnit =
            new WEB3AdminORDetailDispInfoUnit[l_arrorderExecutionRefReferenceUnitList.size()];
        l_arrorderExecutionRefReferenceUnitList.toArray(l_web3AdminORDetailDispInfoUnit);

        log.exiting(STR_METHOD_NAME);

        //Step3 Return the return value of created ArrayList.toArray()<BR>
        return l_web3AdminORDetailDispInfoUnit;
    }

    /**
     * (get商品実施情報)<BR>
     * <BR>
     * 引数の部店コード一覧に該当する部店の<BR>
     * 商品実施情報を作成し、返却する。<BR>
     * ※引数の部店コード一覧のうち、<BR>
     * 　@一部店でも実施していれば、実施となる。<BR>
     * <BR>
     * １）部店一覧の取得<BR>
     * 　@１－１）ArrayListを生成する。<BR>
     * 　@１－２）パラメータ.部店コード一覧の要素数分、<BR>
     * 　@　@拡張アカウントマネージャ.get部店()メソッドをコールし、<BR>
     * 　@　@結果をArrayListに追加する。<BR>
     * <BR>
     * 　@　@[get部店()にセットするパラメータ]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@部店コード：　@処理対象の部店コード<BR>
     * 　@１－３）ArrayList.toArray()メソッドをコールし、<BR>
     * 　@　@部店一覧(部店オブジェクトの配列)を取得する。<BR>
     * <BR>
     * ２）商品実施情報インスタンスを生成する。<BR>
     * <BR>
     * ３）１）にて取得した部店一覧の要素数分、<BR>
     * 　@以下の処理を繰り返す。<BR>
     * 　@３－１）処理対象の部店の各実施区分の値により、<BR>
     * 　@　@商品実施情報インスタンスにプロパティをセットする。<BR>
     * <BR>
     * 　@　@[処理対象の部店.制度信用実施区分 == "実施"の場合]<BR>
     * 　@　@　@商品実施情報.制度信用実施フラグ = true<BR>
     * 　@　@[処理対象の部店.一般信用実施区分 == "実施"の場合]<BR>
     * 　@　@　@商品実施情報.一般信用実施フラグ = true<BR>
     * 　@　@[処理対象の部店.ミニ株実施区分 == "実施"の場合]<BR>
     * 　@　@　@商品実施情報.ミニ株実施フラグ = true<BR>
     * 　@　@[処理対象の部店.オプション実施区分 == "実施"の場合]<BR>
     * 　@　@　@商品実施情報.オプション実施フラグ = true<BR>
     * 　@　@[処理対象の部店.先物実施区分 == "実施"の場合]<BR>
     * 　@　@　@商品実施情報.先物実施フラグ = true<BR>
     * 　@　@[処理対象の部店.投信実施区分 == "実施"の場合]<BR>
     * 　@　@　@商品実施情報.投信実施フラグ = true<BR>
     * 　@　@[処理対象の部店.累投実施区分 == "実施"の場合]<BR>
     * 　@　@　@商品実施情報.累投実施フラグ = true<BR>
     * 　@　@[処理対象の部店.外株実施区分 == "実施"の場合]<BR>
     * 　@　@　@商品実施情報.外国株式実施フラグ = true<BR>
     * <BR>
     * ４）プロパティセットした商品実施情報インスタンスを返却する。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getProductExecutionInfo<BR>
     * <BR>
     * Create WEB3ProductExecutionInfo of the branch corresponding to the argument,<BR>
     * l_branchCodeList, and return it<BR>
     * ※Let it Def.ENFORCEMENT if even one of branches executes in the argument,
     * l_branchCodeList<BR>
     * <BR>
     * 1)Acquire branch list<BR>
     * 　@1-1)Create ArrayList<BR>
     * 　@1-2)Call WEB3GentradeAccountManager.getBranch() method for as many times
     * as<BR>
     * the number of the elements of l_branchCodeList, and<BR>
     * 　@　@ add the result to ArrayList<BR>
     * <BR>
     * 　@　@[Parameter set into getBranch()]<BR>
     * 　@　@　@l_strInstitutionCode: parameter.institutionCode<BR>
     * 　@　@　@l_strBranchCode: branchCode to be processed<BR>
     * 　@1-3)Call ArrayList.toArray() method, and<BR>
     * 　@　@ acquire branch list(an array of l_branch)<BR>
     * <BR>
     * 2)Create WEB3ProductExecutionInfo instance<BR>
     * <BR>
     * 3)Loop the following process for as many times as the number of the elements
     * of<BR>
     * branchCodeList acquired at 1)<BR>
     * 　@3-1)Set properties into WEB3ProductExecutionInfo instance<BR>
     * 　@　@ based on the values of each execution division in branch to be
     * processed<BR>
     * <BR>
     * 　@　@If [l_branch to be processed.margin_sys_div == Def.ENFORCEMENET]<BR>
     * 　@　@　@WEB3ProductExecutionInfo.marginSysFlag = true<BR>
     * 　@　@If [l_branch to be processed.margin_gen_div == Def.ENFORCEMENET]<BR>
     * 　@　@　@WEB3ProductExecutionInfo.marginGenFlag = true<BR>
     * 　@　@If [l_branch to be processed.mstk_div == Def.ENFORCEMENET]<BR>
     * 　@　@　@WEB3ProductExecutionInfo.miniFlag = true<BR>
     * 　@　@If [l_branch to be processed.option_div == Def.ENFORCEMENET]<BR>
     * 　@　@　@WEB3ProductExecutionInfo.optionFlag = true<BR>
     * 　@　@If [l_branch to be processed.future_div == Def.ENFORCEMENET]<BR>
     * 　@　@　@WEB3ProductExecutionInfo.futureFlag = true<BR>
     * 　@　@If [l_branch to be processed.mf_div == Def.ENFORCEMENET]<BR>
     * 　@　@　@WEB3ProductExecutionInfo.mutalFlag = true<BR>
     * 　@　@If [l_branch to be processed.ruito_div == Def.ENFORCEMENET]<BR>
     * 　@　@　@WEB3ProductExecutionInfo.ruitoFlag = true<BR>
     * 　@　@If [l_branch to be processed.fstk_div == Def.ENFORCEMENET]<BR>
     * 　@　@　@WEB3ProductExecutionInfo.fstkFlag = true<BR>
     * <BR>
     * 4)Return WEB3ProductExecutionInfo instance set into 'Property Set'<BR>
     * <BR>
     * @@param l_strinstitutionCode - (証券会社コード)<BR>
     * <BR>
     * 証券会社コード<BR>
     * <BR>
     * l_strInstitutionCode<BR>
     * <BR>
     * @@param l_branchCodeList - (部店コード一覧)<BR>
     * <BR>
     * 部店コードの配列<BR>
     * <BR>
     * l_branchCodeList<BR>
     * <BR>
     * @@throws DataNetworkException DataNetworkException
     * @@throws DataQueryException DataQueryException
     * @@throws DataFindException DataFindException
     * @@throws NotFoundException NotFoundException
     * @@throws WEB3SystemLayerException WEB3SystemLayerException
     * @@return webbroker3.adminorderexecinquiry.WEB3ProductExecutionInfo
     * @@roseuid 41B6D4860113
     */
    public WEB3AdminProductExecutionInfo getProductExecutionInfo(
        String l_strinstitutionCode,
        String[] l_branchCodeList)
        throws
            WEB3SystemLayerException,
            NotFoundException,
            DataFindException,
            DataQueryException,
            DataNetworkException
    {
        final String STR_METHOD_NAME =
            "getProductExecutionInfo(String l_strInstitutionCode, String[] l_branchCodeList)";
        log.entering(STR_METHOD_NAME);

        //Step1.1 Create ArrayList
        ArrayList l_arrbranchCodeList = new ArrayList();
        int l_intBranchCodeSize = 0;
        int l_count = 0;
        int l_intBranchCount = 0;
        WEB3AdminProductExecutionInfo l_web3ProductExecution = null;

        Branch[] l_arrbranches = new WEB3GentradeBranch[1];
        WEB3GentradeBranch l_gentradeBranch = null;
        BranchParams l_branchParams = null;
        l_intBranchCodeSize = l_branchCodeList.length;
        WEB3GentradeAccountManager l_gentradeAccountManager = null;

        //Step 1.2
        FinApp l_finApp = null;
        l_finApp = (FinApp) Services.getService(FinApp.class);
        l_gentradeAccountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeInstitution l_institution = null;
        l_institution = new WEB3GentradeInstitution(l_strinstitutionCode);

        //Call WEB3GentradeAccountManager.getBranch() method for as many times of l_branchCodeList
        for (l_count = 0; l_count < l_intBranchCodeSize; l_count++)
        {
            l_gentradeBranch =
                (WEB3GentradeBranch) l_gentradeAccountManager.getBranch(
                    l_institution,
                    l_branchCodeList[l_count]);
            l_arrbranchCodeList.add(l_gentradeBranch);
        }

        //Step 1.3
        l_arrbranches = (WEB3GentradeBranch[]) l_arrbranchCodeList.toArray(l_arrbranches);

        //Step 2 Create WEB3ProductExecutionInfo instance
        l_web3ProductExecution = new WEB3AdminProductExecutionInfo();

        //Step 3 Loop the following process for as many elements of branchCodeList
        for (l_intBranchCount = 0; l_intBranchCount < l_arrbranches.length; l_intBranchCount++)
        {
            l_branchParams = (BranchParams) l_arrbranches[l_intBranchCount].getDataSourceObject();

            //Step3.1 Set properties into WEB3ProductExecutionInfo instance
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_sys_div))
            {
                l_web3ProductExecution.marginSysFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.margin_gen_div))
            {
                l_web3ProductExecution.marginGenFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.mstk_div))
            {
                l_web3ProductExecution.miniFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.option_div))
            {
                l_web3ProductExecution.optionFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.future_div))
            {
                l_web3ProductExecution.futureFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.mf_div))
            {
                l_web3ProductExecution.mutualFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.ruito_div))
            {
                l_web3ProductExecution.ruitoFlag = true;
            }
            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchParams.fstk_div))
            {
                l_web3ProductExecution.fstkFlag = true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_web3ProductExecution;
    }
    
    /**
     * (get扱者一覧)<BR>
     * 引数の条件に該当する扱者の一覧を返却する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）DB検索<BR>
     *　@パラメータ.部店コード[]の要素数分以下の処理を繰り返す。<BR>
     *　@２－１）拡張金融オブジェクトマネージャ.get扱者()メソッドをコールする。<BR>
     * <BR>
     *　@　@[get扱者()にセットするパラメータ]<BR>
     *　@　@　@証券会社：　@パラメータ.証券会社<BR>
     *　@　@　@扱者コード：　@パラメータ.扱者コード<BR>
     *　@　@　@部店コード：　@処理対象の部店コード<BR>
     * <BR>
     *　@２－２）２－１）の結果が取得できた場合は、<BR>
     *　@　@生成したArrayListに追加する。<BR>
     *　@<BR>
     * ３）ArrayList.toArray()の戻り値を返却する。<BR>
     *　@※toArray()の戻り値.length == 0の場合、<BR>
     *　@　@「該当データなし」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00398<BR>
     * @@param l_strInstitution - (証券会社)<BR>
     * <BR>
     * 証券会社<BR>
     * <BR>
     * l_strInstitution<BR>
     * <BR>
     * @@param l_branchCode - (部店コード)<BR>
     * <BR>
     * 部店コード<BR>
     * <BR>
     * l_branchCode<BR>
     * <BR>
     * @@param l_traderCode - (扱者コード)<BR>
     * <BR>
     * 口座コード<BR>
     * <BR>
     * l_accountCode<BR>
     * @@throws WEB3BaseException
     * @@return WEB3GentradeTrader[]
     */
    public WEB3GentradeTrader[] getTraderList(
            Institution l_institution,
            String[] l_branchCodes,
            String l_strTraderCode)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                "getTraderList(Institution l_institution, "
                    + "String[] l_branchCodes, String l_strTraderCode)";
            log.entering(STR_METHOD_NAME);

            //１）ArrayListを生成する。
            List l_lisTraders = new ArrayList();
            
            //２）DB検索
            //　@パラメータ.部店コード[]の要素数分以下の処理を繰り返す。
            //　@２－１）拡張金融オブジェクトマネージャ.get扱者()メソッドをコールする。
            //　@　@[get扱者()にセットするパラメータ]
            //　@　@　@証券会社：　@パラメータ.証券会社
            //　@　@　@扱者コード：　@パラメータ.扱者コード
            //　@　@　@部店コード：　@処理対象の部店コード
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            int l_intLength = 0;
            if (l_branchCodes != null || l_branchCodes.length != 0)
            {
                l_intLength = l_branchCodes.length;
            }
            for (int i = 0; i < l_intLength; i++)
            {
                try
                {
                    Trader l_trader = l_finObjManager.getTrader(
                        l_institution,
                        l_strTraderCode,
                        l_branchCodes[i]);
                    
                    //　@２－２）２－１）の結果が取得できた場合は、
                    //　@　@生成したArrayListに追加する。
                    l_lisTraders.add(l_trader);
                } 
                catch (NotFoundException l_ex)
                {
                    continue;
                }
            }

            //３）ArrayList.toArray()の戻り値を返却する。
            //　@※toArray()の戻り値.length == 0の場合、
            //　@　@「該当データなし」の例外をスローする。
            if (l_lisTraders.size() == 0)
            {
                String l_strMsg = "No corresponding data ";
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMsg);
            }
            WEB3GentradeTrader[] l_traders = new WEB3GentradeTrader[l_lisTraders.size()];            
            l_lisTraders.toArray(l_traders);
            
            return l_traders;
        }
}
@
