head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAllTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文取引キューTransactionCallback(WEB3IfoOrderAllTransactionCallback.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/25 何文敏 (中訊) 新規作成 仕様変更　@モデルNO.587
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.util.WEB3LogUtility;


/**
 * (先物OP注文取引キューTransactionCallback)<BR>
 * 先物OP注文取引キューTransactionCallbackクラス。<BR>
 * 先物OP注文取引キューの共有ロックを実施し、MAXASとの間の排他制御を行うためのクラス。<BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3IfoOrderAllTransactionCallback implements TransactionCallback
{
    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOrderAllTransactionCallback.class);

    /**
     * (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     */
    private IfoOrderUnit orderUnit;

    /**
     * トランザクション処理を実施する。<BR>
     * <BR>
     * １）　@先物OP注文取引キューテーブルに対し、以下の条件を指定し<BR>
     * 　@　@　@select for updateを使用して共有ロックをかける。<BR>
     * <BR>
     * 　@　@　@[条件]<BR>
     * 　@　@　@証券会社コード = プロパティの注文単位.getBranch().証券会社コード<BR>
     * 　@　@かつ　@部店コード = プロパティの注文単位.getBranch().部店コード<BR>
     * 　@　@かつ　@識別コード = プロパティの注文単位.識別コード<BR>
     * 　@　@かつ　@社内処理項目に含まれる注文Rev.(*1) = プロパティの注文単位.注文Rev.<BR>
     * 　@　@かつ　@処理区分 = "未処理"<BR>
     * <BR>
     * 　@　@(*1)開始位置、桁数は<BR>
     * 　@　@　@　@先物OP発注サービス.get注文Rev開始位置IN社内処理項目()、get注文Rev桁数()で<BR>
     * 　@　@　@　@取得し指定する。<BR>
     * <BR>
     * ２）　@returnする。<BR>
     * <BR>
     * @@return Object
     * @@throws DataNetworkException 
     * @@throws DataCallbackException 
     * @@throws DataQueryException 
     */
    public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
    {
        final String STR_METHOD_NAME = " process()";
        log.entering(STR_METHOD_NAME);

        // １）　@先物OP注文取引キューテーブルに対し、以下の条件を指定し
        // select for updateを使用して共有ロックをかける
        // [条件]
        // 証券会社コード = プロパティの注文単位.getBranch().証券会社コード
        // 部店コード = プロパティの注文単位.getBranch().部店コード
        // 識別コード = プロパティの注文単位.識別コード
        // 社内処理項目に含まれる注文Rev.(*1) = プロパティの注文単位.注文Rev.
        // 処理区分 = "未処理
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        BranchRow l_branchRow = null;
        try
        {
            Branch l_branch = l_accountManager.getBranch(orderUnit.getBranchId());
            l_branchRow = (BranchRow)l_branch.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            throw new DataCallbackException(l_nfe.getMessage(), l_nfe);
        }

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)orderUnit.getDataSourceObject();
        Object[] l_objWhere =
        {
            l_branchRow.getInstitutionCode(),
            l_branchRow.getBranchCode(),
            l_orderUnitRow.getOrderRequestNumber(),
            l_orderUnitRow.getOrderRev(),
            WEB3FrontOrderStatusDef.NOT_DEAL
        };

        WEB3IfoFrontOrderService l_service =
            (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
        int l_intIndex = l_service.getIndexOfOrderRevInCorpCode();
        int l_intFigure = l_service.getFigureOfOrderRev();

        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append("institution_code=?");
        l_strWhere.append(" and branch_code=?");
        l_strWhere.append(" and order_request_number=?");
        l_strWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=?");
        l_strWhere.append(" and status=?");
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        List l_lisSearchResult = l_processor.doFindAllQuery(
            HostFotypeOrderAllRow.TYPE,
            l_strWhere.toString(),
            null,
            "for update",
            l_objWhere);
        int l_intSize = 0;
        if (l_lisSearchResult != null)
        {
            l_intSize = l_lisSearchResult.size();
        }
        log.debug("ロックした株式注文取引キューテーブルの件数:[" + l_intSize + "]");

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (先物OP注文取引キューTransactionCallback)<BR>
     * 引数で指定されたオブジェクトを、インスタンス変数にセットする。<BR>
     * <BR>
     * @@param l_ifoOrderUnit - (注文単位)<BR>
     * 注文単位オブジェクト
     */
    public WEB3IfoOrderAllTransactionCallback(IfoOrderUnit l_ifoOrderUnit)
    {
        orderUnit = l_ifoOrderUnit;
    }
}
@
