head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBranchListmarketDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （部店市場上場区分別）取扱条件(WEB3GentradeBranchListmarketDealtCond.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/06 栄イ(中訊) 新規作成【共通】仕様変更・モデルNo.261
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.BranchListmarketDealtCondDao;
import webbroker3.gentrade.data.BranchListmarketDealtCondPK;
import webbroker3.gentrade.data.BranchListmarketDealtCondRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (（部店市場上場区分別）取扱条件)<BR>
 * DBレイアウト 「（部店市場上場区分別）取扱条件テーブル.xls」参照<BR>
 * <BR>
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public class WEB3GentradeBranchListmarketDealtCond implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log
        = WEB3LogUtility.getInstance(WEB3GentradeBranchListmarketDealtCond.class);

    /**
     * ((部店市場上場区分別)取扱条件Row)<BR>
     * （部店市場上場区分別）取扱条件Rowオブジェクト  <BR>
     * （DAO自動生成クラス）<BR>
     */
    private BranchListmarketDealtCondRow branchListmarketDealtCondRow;

    /**
     * ((部店市場上場区分別)取扱条件)<BR>
     * <BR>
     * コンストラクタ。  <BR>
     * 引数の条件に一致する（部店市場上場区分別）取扱条件オブジェクトを返却する。  <BR>
     * <BR>
     * １）　@DB検索  <BR>
     * 　@引数の値にて（部店市場上場区分別）取扱条件テーブルを検索する。  <BR>
     * <BR>
     * 　@※行オブジェクトが取得できない場合は例外をスローする。 <BR>
     * <BR>
     * ２）　@行オブジェクトセット  <BR>
     * 　@検索結果の行オブジェクト（（部店市場上場区分別）取扱条件Row）を <BR>
     * 　@this.（部店市場上場区分別）取扱条件にセットする。 <BR>
     * <BR>
     * @@param l_lngBranchId - 部店ID
     * @@param l_lngMarketId - 市場ID
     * @@param l_strListType - 上場区分
     * @@param l_strNewListType - 新市場区分
     * @@param l_strOpenOtcDiv - 店頭公開区分
     * @@throws WEB3SystemLayerException
     */
    public WEB3GentradeBranchListmarketDealtCond(
        long l_lngBranchId,
        long l_lngMarketId,
        String l_strListType,
        String l_strNewListType,
        String l_strOpenOtcDiv)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeBranchListmarketDealtCond(long, long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            BranchListmarketDealtCondPK l_branchListmarketDealtCondPK
                = new BranchListmarketDealtCondPK(
                    l_lngBranchId,
                    l_lngMarketId,
                    l_strListType,
                    l_strNewListType,
                    l_strOpenOtcDiv);

            this.branchListmarketDealtCondRow
                = BranchListmarketDealtCondDao.findRowByPk(l_branchListmarketDealtCondPK);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ((部店市場上場区分別)取扱条件)<BR>
     * <BR>
     * コンストラクタ。 <BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、 <BR>
     * 引数の行オブジェクトをプロパティにセットする。<BR>
     * @@param l_branchListmarketDealtCondRow - (部店市場上場区分別)取扱条件Row<BR>
     * (部店市場上場区分別)取扱条件行オブジェクト<BR>
     * @@throws WEB3SystemLayerException
     */
    public WEB3GentradeBranchListmarketDealtCond(
        BranchListmarketDealtCondRow l_branchListmarketDealtCondRow)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME
            = "WEB3GentradeBranchListmarketDealtCond(BranchListmarketDealtCondRow)";
        log.entering(STR_METHOD_NAME);

        if (l_branchListmarketDealtCondRow == null)
        {
            log.debug("(部店市場上場区分別)取扱条件行オブジェクト = null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "(部店市場上場区分別)取扱条件行オブジェクト = null");
        }

        this.branchListmarketDealtCondRow = l_branchListmarketDealtCondRow;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get建単元数上限値)<BR>
     * <BR>
     * 引数の顧客.口座タイプに対応した建単元数上限値を返却する。 <BR>
     * 　@ <BR>
     * 　@顧客.口座タイプ ＝ 法@人アカウントの場合 <BR>
     * 　@　@this.建単元数上限値(法@人)を返却する。 <BR>
     * <BR>
     * 　@顧客.口座タイプ ≠ 法@人アカウントの場合 <BR>
     * 　@　@this.建単元数上限値(個人)を返却する。<BR>
     * @@param l_mainAccount - 顧客
     * @@return Double
     * @@throws WEB3SystemLayerException
     */
    public Double getMaxContUnit(WEB3GentradeMainAccount l_mainAccount) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMaxContUnit(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("顧客 = null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客 = null");
        }

        //顧客.口座タイプ ＝ 法@人アカウントの場合
        //this.建単元数上限値(法@人)を返却する。
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccount.getMainAccountRow().getAccountType()))
        {
            if (this.branchListmarketDealtCondRow.getMaxContUnitCorpIsNull())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return new Double(this.branchListmarketDealtCondRow.getMaxContUnitCorp());
        }

        //顧客.口座タイプ ≠ 法@人アカウントの場合
        //this.建単元数上限値(個人)を返却する。
        if (this.branchListmarketDealtCondRow.getMaxContUnitIndIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return new Double(this.branchListmarketDealtCondRow.getMaxContUnitInd());
    }

    /**
     * (get建代金上限値)<BR>
     * <BR>
     * 引数の顧客.口座タイプに対応した建代金上限値を返却する。 <BR>
     * <BR>
     * 　@顧客.口座タイプ ＝ 法@人アカウントの場合 <BR>
     * 　@　@this.建代金上限値(法@人)を返却する。 <BR>
     * <BR>
     * 　@顧客.口座タイプ ≠ 法@人アカウントの場合 <BR>
     * 　@　@this.建代金上限値(個人)を返却する。<BR>
     * @@param l_mainAccount - 顧客
     * @@return Double
     * @@throws WEB3SystemLayerException
     */
    public Double getMaxContPrice(WEB3GentradeMainAccount l_mainAccount) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMaxContPrice(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("顧客 = null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客 = null");
        }

        //顧客.口座タイプ ＝ 法@人アカウントの場合
        //this.建代金上限値(法@人)を返却する。
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccount.getMainAccountRow().getAccountType()))
        {
            if (this.branchListmarketDealtCondRow.getMaxContPriceCorpIsNull())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return new Double(this.branchListmarketDealtCondRow.getMaxContPriceCorp());
        }

        //顧客.口座タイプ ≠ 法@人アカウントの場合
        //this.建代金上限値(個人)を返却する。
        if (this.branchListmarketDealtCondRow.getMaxContPriceIndIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return new Double(this.branchListmarketDealtCondRow.getMaxContPriceInd());
    }

    /**
     * (get制限二階建率)<BR>
     * <BR>
     * 制限二階建率を返却する。 <BR>
     * <BR>
     * [a.this.(部店市場上場区分別)取扱条件Row.制限二階建率 == null　@] <BR>
     * <BR>
     * 　@返却値：null <BR>
     * <BR>
     * [a.this.(部店市場上場区分別)取扱条件Row.制限二階建率 ≠ null　@] <BR>
     * <BR>
     * 　@返却値：new Double(this.(部店市場上場区分別)取扱条件Row.制限二階建率)<BR>
     * @@return Double
     */
    public Double getMarginSecCheckRate()
    {
        final String STR_METHOD_NAME = "getMarginSecCheckRate()";
        log.entering(STR_METHOD_NAME);

        if (this.branchListmarketDealtCondRow.getMarginSecCheckRateIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return new Double(this.branchListmarketDealtCondRow.getMarginSecCheckRate());
    }

    /**
     * this.(部店市場上場区分別)取扱条件Rowを返却する。<BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.branchListmarketDealtCondRow;
    }
}
@
