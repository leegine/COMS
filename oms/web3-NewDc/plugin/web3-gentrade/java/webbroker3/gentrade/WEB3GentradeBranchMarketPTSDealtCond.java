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
filename	WEB3GentradeBranchMarketPTSDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （部店市場別・PTS）取扱条件(WEB3GentradeBranchMarketPTSDealtCond.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 何文敏 (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondDao;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.util.WEB3LogUtility;

/**
 * 証券会社、部店、市場毎の取扱可能注文条件を表現する。<BR>
 * <BR>
 * （DBレイアウト 「（部店市場別・PTS）取扱条件テーブル.xls」参照）<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3GentradeBranchMarketPTSDealtCond implements BusinessObject
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranchMarketPTSDealtCond.class);

    /**
     * (（部店市場別・PTS）取扱条件)
     */
    private BranchMarketPtsDealtCondRow branchMarketPTSDealtCondRow;

    /**
     * this.（部店市場別・PTS）取扱条件Rowを返却する。<BR>
     * <BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.branchMarketPTSDealtCondRow;
    }

    /**
     * コンストラクタ。<BR>
     * 引数の条件に一致する（部店市場別・PTS）取扱条件オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値にて（部店市場別・PTS）取扱条件テーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（（部店市場別・PTS）取扱条件Row）を<BR>
     * 　@this.（部店市場別・PTS）取扱条件にセットする。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * 市場コード<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GentradeBranchMarketPTSDealtCond(String l_strInstitutionCode,
        String l_strBranchCode, String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeBranchMarketPTSDealtCond(String, String, String)";
        log.entering(STR_METHOD_NAME);

        // １）　@DB検索
        // 引数の値にて（部店市場別・PTS）取扱条件テーブルを検索する。
        BranchMarketPtsDealtCondRow l_branchMarketPtsDealtCondRow = null;
        try
        {
            l_branchMarketPtsDealtCondRow = BranchMarketPtsDealtCondDao.findRowByPk(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strMarketCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        this.branchMarketPTSDealtCondRow = l_branchMarketPtsDealtCondRow;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * コンストラクタ。<BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、<BR>
     * 引数の行オブジェクトをプロパティにセットする。<BR>
     * <BR>
     * @@param l_branchMarketPTSDealtCondRow - (（部店市場別・PTS）取扱条件Row)<BR>
     * （部店市場別・PTS）取扱条件Row<BR>
     * @@throws WEB3BaseException
     */
    public WEB3GentradeBranchMarketPTSDealtCond(BranchMarketPtsDealtCondRow l_branchMarketPTSDealtCondRow)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "WEB3GentradeBranchMarketPTSDealtCond(BranchMarketPTSDealtCondRow)";
        log.entering(STR_METHOD_NAME);

        // 本オブジェクトをインスタンス化し、
        // 引数の行オブジェクトをプロパティにセットする。
        if (l_branchMarketPTSDealtCondRow == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        this.branchMarketPTSDealtCondRow = l_branchMarketPTSDealtCondRow;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is取扱可能)<BR>
     * 指定商品が取扱可能であるかを返却する。<BR>
     * <BR>
     * this.（部店市場別・PTS）取扱条件Rowのプロパティ「取扱可能」が、<BR>
     * ”取扱可能”であればtrue、以外falseを返却する。<BR>
     * <BR>
     * 銘柄タイプがProductTypeEnum.株式以外の場合<BR>
     * 例外をthrowする。<BR>
     * 　@　@　@　@class:　@WEB3SystemLayerException<BR>
     * 　@　@　@　@tag　@:　@SYSTEM_ERROR_80017<BR>
     * <BR>
     * @@param l_productTypeEnum - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isHandlingPossible(ProductTypeEnum l_productTypeEnum)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isHandlingPossible(ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        // 銘柄タイプがProductTypeEnum.株式以外の場合
        if (!ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
        {
            // 例外をthrowする。
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        // this.（部店市場別・PTS）取扱条件Rowのプロパティ「取扱可能」が
        // ”取扱可能”であればtrue、以外falseを返却する。
        String l_strMartcanDealtEquity = this.branchMarketPTSDealtCondRow.getMartCanDealtEquity();
        if (WEB3DealtDef.CAN_DEALT.equals(l_strMartcanDealtEquity))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get（部店市場別・PTS）取扱条件)<BR>
     * 引数の部店に該当する（部店市場別・PTS）取扱条件オブジェクトを全て取得する。<BR>
     * ※市場コード昇順で取得する。<BR>
     * <BR>
     * １）　@（部店市場別・PTS）取扱条件テーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 部店.getInstitution().getInstitutionCode()<BR>
     * 　@部店コード = 部店.getBranchCode()<BR>
     * 　@※市場コード昇順でソートして取得する。<BR>
     * <BR>
     * ２）　@検索結果の（部店市場別・PTS）取扱条件行オブジェクト毎に以下処理を行う。<BR>
     * <BR>
     * 　@２－１）　@（部店市場別・PTS）取扱条件オブジェクトを生成する。<BR>
     * <BR>
     * 　@２－２）　@生成したオブジェクトを配列に追加する。<BR>
     * <BR>
     * ３）　@２）で生成した配列を返却する。<BR>
     * <BR>
     * @@param l_gentradeBranch - (部店)<BR>
     * 部店<BR>
     * @@return WEB3GentradeBranchMarketPTSDealtCond[]
     * @@throws WEB3BaseException
     */
    public static WEB3GentradeBranchMarketPTSDealtCond[] getBranchMarketPTSDealtCond(
        WEB3GentradeBranch l_gentradeBranch) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchMarketPTSDealtCond(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);

        // １）　@（部店市場別・PTS）取扱条件テーブルを以下の条件で検索する。
        // [条件]
        // 証券会社コード = 部店.getInstitution().getInstitutionCode()
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? and ");
        // 部店コード = 部店.getBranchCode()
        l_sbWhere.append(" branch_code = ? ");

        // 部店.getInstitution().getInstitutionCode()
        String l_strInstitutionCode = l_gentradeBranch.getInstitution().getInstitutionCode();
        // 部店.getBranchCode()
        String l_strBranchCode = l_gentradeBranch.getBranchCode();
        Object[] l_objWheres = {l_strInstitutionCode, l_strBranchCode};

        List l_lisRecords = null;
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_lisRecords = processor.doFindAllQuery(
                BranchMarketPtsDealtCondRow.TYPE,
                l_sbWhere.toString(),
                " market_code asc ",
                null,
                l_objWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketPTSDealtCond.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketPTSDealtCond.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ２）　@検索結果の（部店市場別・PTS）取扱条件行オブジェクト毎に以下処理を行う。
        // ２－１）　@（部店市場別・PTS）取扱条件オブジェクトを生成する。
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            new WEB3GentradeBranchMarketPTSDealtCond[l_lisRecords.size()];
        BranchMarketPtsDealtCondRow l_branchMarketPtsDealtCondRow = null;
        WEB3GentradeBranchMarketPTSDealtCond l_branchMarketPTSDealtCond = null;
        int l_intSize = l_lisRecords.size();
        for (int i = 0; i < l_intSize; i++)
        {
            l_branchMarketPtsDealtCondRow = (BranchMarketPtsDealtCondRow) l_lisRecords.get(i);
            l_branchMarketPTSDealtCond =
                new WEB3GentradeBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondRow);
            l_branchMarketPTSDealtConds[i] = l_branchMarketPTSDealtCond;
        }
        
        // ３）　@２）で生成した配列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_branchMarketPTSDealtConds;
    }

    /**
     * (get（部店市場別・PTS）取扱条件)<BR>
     * 引数の証券会社コードに該当する<BR>
     * （部店市場別・PTS）取扱条件オブジェクトを全て取得する。<BR>
     * ※市場コード昇順で取得する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@（部店市場別・PTS）取扱条件テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@※市場コード昇順でソートして取得する。<BR>
     * <BR>
     * ２）オブジェクト生成<BR>
     * 　@検索結果の（部店市場別・PTS）取扱条件行オブジェクトを指定し、<BR>
     * 　@（部店市場別・PTS）取扱条件オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを配列で返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return WEB3GentradeBranchMarketPTSDealtCond[]
     * @@exception WEB3BaseException
     */
    public static WEB3GentradeBranchMarketPTSDealtCond[] getBranchMarketPTSDealtCond(
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchMarketPTSDealtCond(String)";
        log.entering(STR_METHOD_NAME);

        // １）DB検索
        // 　@（部店市場別・PTS）取扱条件テーブルを以下の条件で検索する。
        // [条件]
        // 証券会社コード = パラメータ.証券会社コード
        //※市場コード昇順でソートして取得する。
        List l_lisRecords = null;
        try
        {
            QueryProcessor processor = Processors.getDefaultProcessor();
            l_lisRecords = processor.doFindAllQuery(
                BranchMarketPtsDealtCondRow.TYPE,
                " institution_code = ? ",
                " market_code asc ",
                null,
                new Object[]{l_strInstitutionCode});
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketPTSDealtCond.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketPTSDealtCond.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ２）オブジェクト生成
        // 検索結果の（部店市場別・PTS）取扱条件行オブジェクトを指定し、
        // （部店市場別・PTS）取扱条件オブジェクトを生成する。
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            new WEB3GentradeBranchMarketPTSDealtCond[l_lisRecords.size()];
        BranchMarketPtsDealtCondRow l_branchMarketPtsDealtCondRow = null;
        WEB3GentradeBranchMarketPTSDealtCond l_branchMarketPTSDealtCond = null;
        int l_intSize = l_lisRecords.size();
        for (int i = 0; i < l_intSize; i++)
        {
            l_branchMarketPtsDealtCondRow = (BranchMarketPtsDealtCondRow) l_lisRecords.get(i);
            l_branchMarketPTSDealtCond =
                new WEB3GentradeBranchMarketPTSDealtCond(l_branchMarketPtsDealtCondRow);
            l_branchMarketPTSDealtConds[i] = l_branchMarketPTSDealtCond;
        }

        // 生成したオブジェクトを配列で返却する。
        log.exiting(STR_METHOD_NAME);
        return l_branchMarketPTSDealtConds;
    }

    /**
     * (get市場コード)<BR>
     * 本オブジェクトが保持する市場コードを取得する。<BR>
     * <BR>
     * this.（部店市場別・PTS）取扱条件Row.市場コードを返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getMarketCode()
    {
        return this.branchMarketPTSDealtCondRow.getMarketCode();
    }

    /**
     * (get取扱可能市場)<BR>
     * 引数の部店に該当する（部店市場別・PTS）取扱条件オブジェクトを全て取得し、<BR>
     * 引数の銘柄タイプに該当するオブジェクトの市場コードのみを<BR>
     * ArrayListに設定して返す。<BR>
     * ArrayListへの設定は、市場コード昇順とする。<BR>
     * <BR>
     * １）データ取得<BR>
     * 　@this.get（部店市場別・PTS）取扱条件(部店)により、<BR>
     * 　@引数の部店に該当する（部店市場別・PTS）取扱条件オブジェクトを全て取得する。<BR>
     * <BR>
     * ２）取扱可能チェック<BR>
     * 　@１）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     * <BR>
     * 　@チェック内容：<BR>
     * 　@this.is取扱可能(銘柄タイプ)==trueの場合、<BR>
     * 　@当該レコードの市場コードをArrayListに追加する。<BR>
     * <BR>
     * ３）作成したArrayListを返す。<BR>
     * <BR>
     * @@param l_genBranch - (部店)<BR>
     * 部店<BR>
     * @@param l_productTypeEnum - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] getHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productTypeEnum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleMarket(WEB3GentradeBranch, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        // データ取得
        // this.get（部店市場別・PTS）取扱条件(部店)により、
        // 引数の部店に該当する（部店市場別・PTS）取扱条件オブジェクトを全て取得する。
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(l_genBranch);

        // ArrayListに設定して返す。
        ArrayList l_lisRecords = new ArrayList();
        int l_intLength = l_branchMarketPTSDealtConds.length;
        for (int i = 0; i < l_intLength; i++)
        {
            // ２）取扱可能チェック
            // １）で取得したオブジェクト数分、以下のチェックを行う。
            // チェック内容：
            // this.is取扱可能(銘柄タイプ)==trueの場合、
            if (l_branchMarketPTSDealtConds[i].isHandlingPossible(l_productTypeEnum))
            {
                // 当該レコードの市場コードをArrayListに追加する。
                String l_strMarketCode =
                    l_branchMarketPTSDealtConds[i].getMarketCode();
                if(!l_lisRecords.contains(l_strMarketCode))
                {
                    l_lisRecords.add(l_strMarketCode);
                }
            }
        }

        // ３）作成したArrayListを返す。。
        String[] l_strHandlingPossibleMarkets =
            new String[l_lisRecords.size()];
        l_lisRecords.toArray(l_strHandlingPossibleMarkets);

        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (get取扱可能市場)<BR>
     * 引数の証券会社コードに該当する<BR>
     * （部店市場別・PTS）取扱条件オブジェクトを全て取得し、<BR>
     * 引数の銘柄タイプに該当するオブジェクトの市場コードのみを<BR>
     * ArrayListに設定して返す。<BR>
     * ArrayListへの設定は、市場コード昇順とする。<BR>
     * <BR>
     * １）データ取得<BR>
     * 　@this.get（部店市場別・PTS）取扱条件(証券会社コード)により、<BR>
     * 　@引数の証券会社コードに該当する<BR>
     * 　@（部店市場別・PTS）取扱条件オブジェクトを全て取得する。<BR>
     * <BR>
     * ２）取扱可能チェック<BR>
     * 　@１）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     * <BR>
     * 　@チェック内容：<BR>
     * 　@this.is取扱可能(銘柄タイプ)==trueの場合、<BR>
     * 　@当該レコードの市場コードをArrayListに追加する。<BR>
     * 　@※ただし、当該レコードの市場コードが既にArrayListに存在する場合は、<BR>
     * 　@　@追加しない。<BR>
     * <BR>
     * ３）作成したArrayList.toArray()の戻り値を返す。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_productTypeEnum - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     */
    public static String[] getHandlingPossibleMarket(
        String l_strInstitutionCode,
        ProductTypeEnum l_productTypeEnum) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleMarket(String, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        // データ取得
        // this.get（部店市場別・PTS）取扱条件(証券会社コード)により、
        // 引数の証券会社コードに該当する（部店市場別・PTS）取扱条件オブジェクトを全て取得する。
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(l_strInstitutionCode);

        // ArrayListに設定して返す。
        ArrayList l_lisRecords = new ArrayList();
        int l_intLength = l_branchMarketPTSDealtConds.length;
        for (int i = 0; i < l_intLength; i++)
        {
            // ２）取扱可能チェック
            // １）で取得したオブジェクト数分、以下のチェックを行う。
            // チェック内容：
            // this.is取扱可能(銘柄タイプ)==trueの場合、
            if (l_branchMarketPTSDealtConds[i].isHandlingPossible(l_productTypeEnum))
            {
                // 当該レコードの市場コードをArrayListに追加する。
                String l_strMarketCode =
                    l_branchMarketPTSDealtConds[i].getMarketCode();
                if (!l_lisRecords.contains(l_strMarketCode))
                {
                    l_lisRecords.add(l_strMarketCode);
                }
            }
        }

        // ３）作成したArrayListを返す。
        String[] l_strHandlingPossibleMarkets =
            new String[l_lisRecords.size()];
        l_lisRecords.toArray(l_strHandlingPossibleMarkets);

        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (get取引可能上限金額)<BR>
     * 本オブジェクトが保持する取引可能金額上限値を取得する。<BR>
     * <BR>
     * this.（部店市場別・PTS）取扱条件Row.取引可能金額上限値を返却する。<BR>
     * <BR>
     * @@return double
     */
    public double getMaxHandlingPrice()
    {
        return this.branchMarketPTSDealtCondRow.getMaxHandlingPrice();
    }

    /**
     * (get売買限度単位)<BR>
     * 本オブジェクトが保持する売買限度単位を取得する。<BR>
     * <BR>
     * this.（部店市場別・PTS）取扱条件Row.売買限度単位を返却する。<BR>
     * <BR>
     * @@return double
     */
    public double getLimitedUnit()
    {
        return this.branchMarketPTSDealtCondRow.getLimitedUnit();
    }
}
@
