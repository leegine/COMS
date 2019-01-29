head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBranchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券部店別条件(WEB3BondBranchCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/30 柴雙紅 (中訊) 新規作成
                   2006/10/26 徐宏偉 (中訊)　@ＤＢレイアウト No.025
Revesion History : 2007/7/26 劉立峰 (中訊) 仕様変更・モデルNo.233
*/
package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.bd.data.BondBranchConditionDao;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券部店別条件)<BR>
 * 債券部店別条件クラス <BR>
 *
 * @@author 柴雙紅(中訊)
 * @@version 1.0
 */
public class WEB3BondBranchCondition
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBranchCondition.class);

    /**
     * (債券部店別条件行)<BR>
     * 債券部店別条件行オブジェクト <BR>
     */
    private BondBranchConditionRow bondBranchConditionRow;

    /**
     * (債券部店別条件)<BR>
     * コンストラクタ <BR>
     * <BR>
     * １）以下の条件で、債券部店別条件テーブルからレコードを取得する。<BR>
     * <BR>
     * 　@[取得条件] <BR>
     * 　@　@部店ID = 引数.部店ID<BR>
     *  <BR>
     * ２）取得したレコードをthis.債券部店別行にセットする。<BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID<BR>
     * @@throws WEB3BaseException
     */
    public WEB3BondBranchCondition(long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " WEB3BondBranchCondition(long)";
        log.entering(STR_METHOD_NAME);

        //１）以下の条件で、債券部店別条件テーブルからレコードを取得する
        BondBranchConditionRow l_bondBranchConditionRow = null;
        try
        {
            l_bondBranchConditionRow = BondBranchConditionDao.findRowByPk(l_lngBranchId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBアクセスが失敗の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBアクセスが失敗の場合");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）取得したレコードをthis.債券部店別行にセットする
        this.bondBranchConditionRow = l_bondBranchConditionRow;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get債券実施区分)<BR>
     * 債券実施区分を取得する。<BR>
     * <BR>
     * this.債券部店別条件行.get債券実施区分()の戻り値を返却する。<BR>
     * @@return String
     */
    public String getEnforceDiv()
    {
        final String STR_METHOD_NAME = " getEnforceDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getEnforceDiv();
    }

    /**
     * (get保有資産チェック区分)<BR>
     * 保有資産チェック区分を取得する。<BR>
     * <BR>
     * this.債券部店別条件行.get保有資産チェック区分()の戻り値を返却する。<BR>
     * @@return String
     */
    public String getAssetCheckDiv()
    {
        final String STR_METHOD_NAME = " getAssetCheckDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getAssetCheckDiv();
    }

    /**
     * (get注文ロック使用区分)<BR>
     * 注文ロック使用区分を取得する。<BR>
     * <BR>
     * this.債券部店別条件行.get注文ロック使用区分()の戻り値を返却する。<BR>
     * @@return String
     */
    public String getOrderLockUseDiv()
    {
        final String STR_METHOD_NAME = " getOrderLockUseDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getOrderLockUseDiv();
    }

    /**
     * (get入金日設定区分)<BR>
     * 入金日設定区分を取得する。<BR>
     * <BR>
     * this.債券部店別条件行.get入金日設定区分()の戻り値を返却する。<BR>
     * @@return String
     */
    public String getPaymentDateSetDiv()
    {
        final String STR_METHOD_NAME = " getPaymentDateSetDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getPaymentDateSetDiv();
    }

    /**
     * (get応募枠部店別管理区分)<BR>
     * 応募枠部店別管理区分を取得する。<BR>
     * <BR>
     * this.債券部店別条件行.get応募枠部店別管理区分()の戻り値を返却する。<BR>
     * @@return String
     */
    public String getBranchRecruitLimitDiv()
    {
        final String STR_METHOD_NAME = " getBranchRecruitLimitDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getBranchRecruitLimitDiv();
    }
}
@
