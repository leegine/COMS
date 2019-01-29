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
filename	WEB3GentradeBranchMarketDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (部店市場別)取扱条件(WEB3GentradeBranchMarketDealtCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 石炎 (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.gentrade.data.BranchMarketDealtCondDao;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (部店市場別）取扱条件 <BR>
 * 証券会社、部店、市場毎の取扱可能注文条件を表現する。 <BR>
 * 現物株式で使用。 <BR>
 * <BR>
 * （DBレイアウト 「（部店市場別）取扱条件テーブル.xls」参照） <BR>
 * <BR>
 */
public class WEB3GentradeBranchMarketDealtCond implements BusinessObject
{

    /**
     * （部店市場別）取扱条件Row行オブジェクト
     * （DAO自動生成クラス）
     */
    private BranchMarketDealtCondRow branchMarketDealtCondRow;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranchMarketDealtCond.class);

    /**
     * コンストラクタ。 <BR>
     * （部店市場別）取扱条件 <BR>
     * 
     * 本オブジェクトをインスタンス化し、 <BR>
     * 引数の行オブジェクトをプロパティにセットする。 <BR>
     * <BR>
     * @@param l_branchMarketDealtCondRow - （部店市場別）取扱条件行オブジェクト
     * @@roseuid 40640F0102AE
     */
    public WEB3GentradeBranchMarketDealtCond(BranchMarketDealtCondRow l_branchMarketDealtCondRow)
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeBranchMarketDealtCond(BranchMarketDealtCondRow)";
        if(l_branchMarketDealtCondRow == null)
        {
            log.error("（部店市場別）取扱条件行オブジェクト = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "（部店市場別）取扱条件行オブジェクト = null");
        }
        
        this.branchMarketDealtCondRow = l_branchMarketDealtCondRow;
    }

    /**
     * コンストラクタ。
     * 引数の条件に一致する（部店市場別）取扱条件オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索 <BR>
     * 　@引数の値にて（部店市場別）取扱条件テーブルを検索する。 <BR>
     * <BR>
     * ２）　@行オブジェクトセット <BR>
     * 　@検索結果の行オブジェクト（（部店市場別）取扱条件Row）を <BR>
     * this.（部店市場別）取扱条件にセットする。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード <BR>
     * @@param l_strBranchCode - 部店コード <BR>
     * @@param l_strMarketCode - 市場コード <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 4060ED5C0373
     */
    public WEB3GentradeBranchMarketDealtCond(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeHandingCondBranchMarket(String,String,String)";
        log.entering(STR_METHOD_NAME);

        BranchMarketDealtCondRow l_row = null;
        try
        {
            //（部店市場別）取扱条件テーブル
            l_row = BranchMarketDealtCondDao.findRowByPk(
                 l_strInstitutionCode,
                 l_strBranchCode,
                 l_strMarketCode
                 );
        }
        catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        if (l_row != null)
        {
            this.branchMarketDealtCondRow = l_row;
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.部店市場別取扱条件Rowを返却する。
     * @@return Object
     * @@roseuid 4060ED5C0372
     */
    public Object getDataSourceObject()
    {
        return this.branchMarketDealtCondRow;
    }

    /**
     * (is取扱可能) <BR>
     * 指定商品が取扱可能であるかを返却する。 <BR>
     * <BR>
     * 引数の条件によって、this.（部店市場別）取扱条件Rowの <BR>
     * 対応する項目値(*1)を判定し、 ”取扱可能”であればtrue、<BR>
     * 以外falseを返却する。 <BR>
     * <BR>
     * ○　@銘柄タイプがProductTypeEnum.株式以外の場合 <BR>
     * 　@：例外をthrowする。 <BR>
     * class    : WEB3BaseRuntimeException<BR>
     * tag      : SYSTEM_ERROR_80017<BR>
     * <BR>
     * ○　@銘柄タイプがProductTypeEnum.株式の場合 <BR>
     *   ：(*1)として、「取扱可能」を使用する<BR>
     * <BR>
     * @@param l_productTypeEnum - 銘柄タイプ <BR>
     * @@return boolean
     * @@roseuid 4060EE9D0362
     */
    public boolean isHandlingPossible(
        ProductTypeEnum l_productTypeEnum)
    {
        final String STR_METHOD_NAME =
            "isHandingPossible(ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnIsHandingPossible = false;

        //銘柄タイプがProductTypeEnum.株式の場合
        if (l_productTypeEnum.equals(ProductTypeEnum.EQUITY))
        {
            if(WEB3DealtDef.CAN_DEALT.equals(branchMarketDealtCondRow.getMartCanDealtEquity()))
            {
                l_blnIsHandingPossible = true;
            }
            else
            {
                l_blnIsHandingPossible = false;
            }

        }
        //銘柄タイプがProductTypeEnum.株式以外の場合
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄タイプ = " + l_productTypeEnum);
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsHandingPossible;
    }

    /**
     * (get売買限度単位) <BR>
     * 売買限度単位を取得する。 <BR>
     * <BR>
     * 　@以下の通り判定し、限度単位値を返却する。 <BR>
     * 　@－引数の上場区分が一部上場の場合は”売買限度単位（一部上場）”を返却する。 
     * <BR>
     * 　@－引数の上場区分が二部上場の場合は”売買限度単位（二部上場）”を返却する。 
     * <BR>
     * 　@－引数の上場区分が上記以外の場合は”売買限度単位（一部上場）”を返却する。 
     * <BR>
     * <BR>
     * @@param l_strListingDivision - (上場区分) <BR>
     * 株式取引銘柄マスタにて定義されている上場区分。 <BR>
     * @@return double
     * @@roseuid 4064038C0389
     */
    public double getDealingLimitUnit(String l_strListingDivision)
    {
        final String STR_METHOD_NAME = "getDealingLimitUnit(String)";
        log.entering(STR_METHOD_NAME);

        if (WEB3ListTypeDef.FIRST_SECTION.equals(l_strListingDivision))
        {
            // 上場区分が一部上場の場合
            log.debug("上場区分が一部上場の場合");
            log.exiting(STR_METHOD_NAME);
            return branchMarketDealtCondRow.getLimitedUnit1stSec();
        }
        else if (WEB3ListTypeDef.SECOND_SECTION.equals(l_strListingDivision))
        {
            // 上場区分が二部上場の場合
            log.debug("上場区分が二部上場の場合");
            log.exiting(STR_METHOD_NAME);
            return branchMarketDealtCondRow.getLimitedUnit2ndSec();
        }
        else
        {
            // 上場区分が上記以外の場合
            log.debug("上場区分が上記以外の場合");
            log.exiting(STR_METHOD_NAME);
            return branchMarketDealtCondRow.getLimitedUnit1stSec();
        }
    }

    /**
     * （(get部店市場別）取扱条件）<BR>
     * （staticメソッド） <BR>
     * 引数の部店に該当する（部店市場別）取扱条件オブジェクトを全て取得する。 <BR>
     * ※市場コード昇順で取得する。 <BR>
     * <BR>
     * １）DB検索 <BR>
     * 　@（部店市場別）取扱条件テーブルを以下の条件で検索する。 <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = 部店.getInstitution().getInstitutionCode() <BR>
     * 　@部店コード = 部店.getBranchCode() <BR>
     * 　@※市場コード昇順でソートして取得する。 <BR>
     * <BR>
     * ２）オブジェクト生成 <BR>
     * 　@検索結果の（部店市場別）取扱条件行オブジェクトを指定し、 <BR>
     * 　@（部店市場別）取扱条件オブジェクトを生成する。 <BR>
     * 　@生成したオブジェクトを配列で返却する。 <BR>
     * <BR>
     * @@param l_branch - 部店オブジェクト
     * @@return webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond
     * @@throws WEB3SystemLayerException
     * @@roseuid 40640F69033B
     */
    public static WEB3GentradeBranchMarketDealtCond[] getHandlingCondBranchMarket(WEB3GentradeBranch l_branch)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getHandingCondBranchMarket(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);

        //証券会社コード 
        String l_strInstitutionCode = l_branch.getInstitution().getInstitutionCode();
        //部店コード
        String l_strBranchCode = l_branch.getBranchCode();

        List l_lisRecords = null;
        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");   //証券会社コード
            l_sbWhere.append(" and  branch_code = ? ");    //部店コード

            Object[] l_objBranchMarketDealtCondWhere = { 
                l_strInstitutionCode,    //証券会社コード
                l_strBranchCode          //部店コード
                }; 

            //※市場コード昇順でソートして取得する。
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                BranchMarketDealtCondRow.TYPE,
                l_sbWhere.toString(),
                "to_number(market_code) ",
                null,
                l_objBranchMarketDealtCondWhere
                );
        }
        catch (DataNetworkException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketDealtCond.class.getName()
                + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataQueryException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketDealtCond.class.getName()
                + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
   
        int l_intSize = l_lisRecords.size();
        BranchMarketDealtCondRow l_rowLoop = null;
        WEB3GentradeBranchMarketDealtCond l_gentradeHandingCondBranchMarketLoop = null;
        WEB3GentradeBranchMarketDealtCond[] l_branchMarkets = 
            new WEB3GentradeBranchMarketDealtCond[l_lisRecords.size()];

        for (int l_loop = 0; l_loop < l_intSize; l_loop++)
        {
            l_rowLoop = (BranchMarketDealtCondRow) l_lisRecords.get(l_loop);

            l_gentradeHandingCondBranchMarketLoop =
                new WEB3GentradeBranchMarketDealtCond(l_rowLoop);

            l_branchMarkets[l_loop] = l_gentradeHandingCondBranchMarketLoop;
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_branchMarkets;
    }

    /**
     * (get市場コード) <BR>
     * 本オブジェクトが保持する市場コードを取得する。 <BR>
     * <BR>
     * this.（部店市場別）取扱条件Row.市場コードを返却する。 <BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4064213502BE
     */
    public String getMarketCode()
    {
        return this.branchMarketDealtCondRow.getMarketCode();
    }

    /**
     * (get取扱可能市場) <BR>
     * （staticメソッド） <BR>
     * 引数の部店に該当する（部店市場別）取扱条件オブジェクトを全て取得し、 <BR>
     * 引数の銘柄タイプ、信用取引区分に該当するオブジェクトの市場コードのみを <BR>
     * ArrayListに設定して返す。 <BR>
     * ArrayListへの設定は、市場コード昇順とする。 <BR>
     * <BR>
     * １）データ取得 <BR>
     * 　@this.get（部店市場別）取扱条件(部店)により、 <BR>
     * 　@引数の部店に該当する（部店市場別）取扱条件オブジェクトを全て取得する。 <BR>
     * <BR>
     * ２）取扱可能チェック <BR>
     * 　@１）で取得したオブジェクト数分、以下のチェックを行う。 <BR>
     * <BR>
     * 　@チェック内容： <BR>
     * 　@this.is取扱可能(銘柄タイプ)==trueの場合、 <BR>
     * 　@当該レコードの市場コードをArrayListに追加する。 <BR>
     * <BR>
     * ３）作成したArrayListを返す。 <BR>
     * @@param l_genBranch - 部店オブジェクト <BR>
     * @@param l_productTypeEnum - 銘柄タイプ <BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 40A069440336
     */
    public static String[] getHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getHandlingPossibleMarket(WEB3GentradeBranch, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        //１）get（部店市場別）取扱条件(部店)
        WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(l_genBranch);
        
        //２）取扱可能チェック
        ArrayList l_lstHandlingPossibleMarkets =  new ArrayList();
        int l_intLength = l_handingCondBranchMarkets.length;
        //this.is取扱可能(銘柄タイプ)==trueの場合、
        //当該レコードの市場コードをArrayListに追加する。
        for (int i = 0; i < l_intLength; i++)
        {
            if(l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum))
            {
                String l_strMarketCode = l_handingCondBranchMarkets[i].getMarketCode();
                if(l_lstHandlingPossibleMarkets.contains(l_strMarketCode) == false)
                {
                    l_lstHandlingPossibleMarkets.add(l_strMarketCode);
                }
            }
        }
        
        //３）作成したデータを返す。
        l_intLength = l_lstHandlingPossibleMarkets.size();
        String[] l_strHandlingPossibleMarkets = new String[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            l_strHandlingPossibleMarkets[i] = (String)l_lstHandlingPossibleMarkets.get(i);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;

    }
    
    /**
     * (get取扱可能市場) <BR>
     * （staticメソッド） <BR>
     * 引数の証券会社コードに該当する（部店市場別）取扱条件<BR>
     * オブジェクトを全て取得し、引数の銘柄タイプに該当するオブジェクト<BR>
     * の市場コードのみをArrayListに設定して返す。ArrayListへの設定<BR>
     * は、市場コード昇順とする。<BR>
     *  <BR>
     * １）データ取得<BR>
     * this.get（部店市場別）取扱条件(.証券会社コード)により、<BR>
     * 引数の証券会社コードに該当する（部店市場別）取扱条件<BR>
     * オブジェクトを全て取得する。<BR>
     *  <BR>
     * ２）取扱可能チェック<BR>
     * １）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     *  <BR>
     * チェック内容：<BR>
     * this.is取扱可能(銘柄タイプ)==trueの場合、<BR>
     * 当該レコードの市場コードをArrayListに追加する。<BR>
     * ※ただし、当該レコードの市場コードが既にArrayListに<BR>
     * 存在する場合は、追加しない。<BR>
     *  <BR>
     * ３）作成したArrayList.toArray()の戻り値を返す。<BR>
     *  <BR>
     * @@param l_strInstitutionCode - 証券会社コード <BR>
     * @@param l_productTypeEnum - 銘柄タイプ <BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     */
    public static String[] getHandlingPossibleMarket(
        String l_strInstitutionCode,
        ProductTypeEnum l_productTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getHandlingPossibleMarket(String, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        //１）データ取得
        //get（部店市場別）取扱条件(.証券会社コード)により、
        //引数の証券会社コードに該当する（部店市場別）取扱条件オブジェクトを全て取得する。
        WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(l_strInstitutionCode);
        
        //２）取扱可能チェック
        //１）で取得したオブジェクト数分、以下のチェックを行う。
        //チェック内容：
        //this.is取扱可能(銘柄タイプ)==trueの場合、当該レコードの市場コードをArrayListに追加する。
        int l_intSize = l_handingCondBranchMarkets.length;
        List l_lstHandlingPossibleMarkets = new ArrayList();
        for(int i = 0; i < l_intSize; i++)
        {
            if(l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum))
            {
                String l_strMarketCode = l_handingCondBranchMarkets[i].getMarketCode();
                if(l_lstHandlingPossibleMarkets.contains(l_strMarketCode) == false)
                {
                    l_lstHandlingPossibleMarkets.add(l_strMarketCode);
                }
            }
        }
        
        //３）作成したデータを返す。
        String[] l_strHandlingPossibleMarkets = new String[l_lstHandlingPossibleMarkets.size()];
        l_lstHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
        
        
    }
    
    /**
     * (get取引中取扱可能市場) <BR>
     * （staticメソッド） <BR>
     * 引数の部店に該当する（部店市場別）取扱条件オブジェクトを全て取得し、<BR>
     * 引数の銘柄タイプに該当するオブジェクトの市場コードのうち、取引中の市場<BR>
     * の市場コードのみをArrayListに設定して返す。ArrayListへの設定は、<BR>
     * 市場コード昇順とする。<BR>
     *  <BR>
     * １）データ取得<BR>
     *  this.get（部店市場別）取扱条件(部店)により、引数の部店に<BR>
     *  該当する（部店市場別）取扱条件オブジェクトを全て取得する。<BR>
     *  <BR>
     * ２）取扱可能チェック・取引中チェック<BR>
     *  １）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     *  <BR>
     * チェック内容：<BR>
     * this.is取扱可能(銘柄タイプ)==true、かつ 取引中の市場(*1)の場合は、<BR>
     * 当該レコードの市場コードをArrayListに追加する。<BR>
     *  <BR>
     * (*1)取引中の市場 <BR>
     * 拡張金融オブジェクトマネージャ.get市場(部店.証券会社コード, 当該<BR>
     * レコードの市場コード)により市場オブジェクトを取得。<BR>
     * 取得した市場.取引停止≠"取引停止中"の場合は、取引中の市場と判定する。<BR>
     * ※市場オブジェクトが取得できなかった場合は、取引中の市場ではないと判定する<BR>
     *  <BR>
     * ３）作成したArrayListを返す。<BR>
     * <BR>
     * @@param l_genBranch - 部店オブジェクト <BR>
     * @@param l_productTypeEnum - 銘柄タイプ <BR>
     * @@return String[]<BR>
     * @@throws WEB3SystemLayerException<BR>
     */
    public static String[] getTradingHandlingPossibleMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productTypeEnum)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getTradingHandlingPossibleMarket(WEB3GentradeBranch, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_genFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        
        //１）get（部店市場別）取扱条件(部店)
        WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(l_genBranch);
        
        //1)で取得したオブジェクト数分、以下のチェックを行う。
        ArrayList l_lstHandlingPossibleMarkets =  new ArrayList();
        int l_intLength = l_handingCondBranchMarkets.length;
        for (int i = 0; i < l_intLength; i++)
        {        
            //拡張金融オブジェクトマネージャ.get市場(部店.証券会社コード, 
            //当該レコードの市場コード)により市場オブジェクトを取得。
            Market l_market = null; 
            try
            {
                l_market =
                    l_genFinObjectManager.getMarket(
                        l_genBranch.getInstitution().getInstitutionCode(),
                        l_handingCondBranchMarkets[i].getMarketCode());
            }
            catch (NotFoundException nfe)
            {
                //※市場オブジェクトが取得できなかった場合は、取引中の市場ではないと判定する
                log.debug("市場オブジェクトが取得できなかった場合は、取引中の市場ではないと判定する");
            }
            
            //取得した市場.取引停止≠"取引停止中"の場合は、取引中の市場と判定する。
            //市場オブジェクトが取得できなかった場合は、取引中の市場ではないと判定する
            boolean l_isTradingMarket = false;
            if(l_market != null)
            {
                MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
                if(WEB3SuspensionDef.SUSPENSION.equals(l_marketRow.getSuspension()) == false)
                {
                    l_isTradingMarket = true;
                }
            }
            
            //this.is取扱可能(銘柄タイプ)==true、かつ 取引中の市場(*1)の場合は、
            //当該レコードの市場コードをArrayListに追加する。
            if(l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum) && l_isTradingMarket)
            {
                l_lstHandlingPossibleMarkets.add(l_handingCondBranchMarkets[i].getMarketCode());
            }

        }
        
        //３）作成したデータを返す。
        l_intLength = l_lstHandlingPossibleMarkets.size();
        String[] l_strHandlingPossibleMarkets = new String[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            l_strHandlingPossibleMarkets[i] = (String)l_lstHandlingPossibleMarkets.get(i);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }
    
    /**
     * （(get部店市場別)取扱条件）<BR>
     * （staticメソッド） <BR>
     * 引数の証券会社コードに該当する<BR>
     * （部店市場別）取扱条件オブジェクトを全て取得する。<BR>
     * ※市場コード昇順で取得する。<BR>
     *  <BR>
     * １）DB検索<BR>
     * （部店市場別）取扱条件テーブルを以下の条件で検索する。<BR>
     * [条件]<BR>
     * 証券会社コード = パラメータ.証券会社コード<BR>
     * ※市場コード昇順でソートして取得する。<BR>
     *  <BR>
     * ２）オブジェクト生成<BR>
     * 検索結果の（部店市場別）取扱条件行オブジェクトを指定し、<BR>
     * （部店市場別）取扱条件オブジェクトを生成する。<BR>
     * 生成したオブジェクトを配列で返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード) <BR>
     * @@return webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond[]
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeBranchMarketDealtCond[] getHandlingCondBranchMarket(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getHandingCondBranchMarket(String)";
        log.entering(STR_METHOD_NAME);
        
        //１）（部店市場別）取扱条件テーブルを以下の条件で検索する。
        //証券会社コード = パラメータ.証券会社コード
        //※市場コード昇順でソートして取得する。
        List l_lstRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                BranchMarketDealtCondRow.TYPE,
               "institution_code = ?",
                "to_number(market_code) ",
                null,
                new Object[]{l_strInstitutionCode}
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchMarketDealtCond.class.getName()
                    + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２）オブジェクト生成
        //検索結果の（部店市場別）取扱条件行オブジェクトを指定し、
        //（部店市場別）取扱条件オブジェクトを生成する。生成したオブジェクトを配列で返却する。
        int l_intSize = l_lstRecords.size();
        WEB3GentradeBranchMarketDealtCond[] l_gentBranchMarketDealtConds = 
            new WEB3GentradeBranchMarketDealtCond[l_intSize];
        BranchMarketDealtCondRow l_branchMarketDealtCondRow;
        for(int i = 0; i < l_intSize; i++)
        {
            l_branchMarketDealtCondRow = (BranchMarketDealtCondRow)l_lstRecords.get(i);
            l_gentBranchMarketDealtConds[i] =
                new WEB3GentradeBranchMarketDealtCond(l_branchMarketDealtCondRow);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_gentBranchMarketDealtConds;    
    }
    
    /**
     * (is取扱可能市場) <BR>
     * （staticメソッド） <BR>
     * 引数の証券会社コード、部店コードに該当する（部店市場別）取扱<BR>
     * 条件オブジェクトを全て取得し、取得レコード中に、指定市場の、<BR>
     * 取扱可能プロパティ=="取扱可能"のレコードが含まれている場合は<BR>
     * trueを、含まれていない場合はfalseを、それぞれ返却する。<BR>
     *  <BR>
     * １）　@データ取得<BR>
     *  <BR>
     * １－１）引数の部店コード == null（部店指定なし（全部店指定））の場合<BR>
     *  <BR>
     * this.get（部店市場別）取扱条件(引数の証券会社コード)により、<BR>
     * 引数の証券会社コードに該当する（部店市場別）取扱条件オブジェクト<BR>
     * を全て取得する。<BR>
     *  <BR>
     * １－２）引数の部店コード != null（部店指定あり）の場合<BR>
     *  <BR>
     * this.get（部店市場別）取扱条件(.部店)により、引数の<BR>
     * 証券会社コード、部店コードに該当する（部店市場別）取扱条件<BR>
     * オブジェクトを全て取得する。<BR>
     *  <BR>
     * ※部店オブジェクトは、拡張アカウントマネージャ.get部店(<BR>
     *    引数の証券会社コード, 引数の部店コード)により取得する。<BR>
     *  <BR>
     * ２）　@取扱可能チェック <BR>
     *   １）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     *  <BR>
     * チェック内容：<BR>
     * 市場コード==引数の市場コード、かつ　@<BR>
     * this.is取扱可能(銘柄タイプ)==trueの場合、<BR>
     * trueを返却する。<BR>
     *  <BR>
     * ３）　@１）で該当データなし、もしくは　@<BR>
     * 取得した全オブジェクトが全て２）で取扱不可と判断された場合、<BR>
     * falseを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード) <BR>
     * @@param l_productTypeEnum - (銘柄タイプ) <BR>
     * @@param l_strBranchCode - (部店コード) <BR>
     *     部店の指定なし（全部店を対象とする）の場合は、nullをセット<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@return boolean<BR>
     * @@throws WEB3SystemLayerException
     */
    public static boolean isHandlingPossibleMarket(
        String l_strInstitutionCode,
        ProductTypeEnum l_productTypeEnum,
        String l_strBranchCode,
        String l_strMarketCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "isHandlingPossibleMarket(String, ProductTypeEnum, String, String)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //１－１）引数の部店コード == null（部店指定なし（全部店指定））の場合
        //this.get（部店市場別）取扱条件(引数の証券会社コード)により、
        //引数の証券会社コードに該当する（部店市場別）取扱条件オブジェクトを全て取得する
        WEB3GentradeBranchMarketDealtCond[] l_handingCondBranchMarkets;
        if(l_strBranchCode == null)
        {
            l_handingCondBranchMarkets =
                getHandlingCondBranchMarket(l_strInstitutionCode);     
        }
        else
        {
            //１－２）引数の部店コード != null（部店指定あり）の場合
            //this.get（部店市場別）取扱条件(.部店)により、引数の
            //証券会社コード、部店コードに該当する（部店市場別）取扱条件
            //オブジェクトを全て取得する。
            try
            {
                Institution l_institution =
                    l_finApp.getAccountManager().getInstitution(l_strInstitutionCode);
                WEB3GentradeBranch l_genBranch =
                    (WEB3GentradeBranch) l_finApp.getAccountManager().getBranch(l_institution,l_strBranchCode);
                l_handingCondBranchMarkets =
                    getHandlingCondBranchMarket(l_genBranch);     
            }
            catch (NotFoundException nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeBranchMarketDealtCond.class.getName()
                        + "."
                        + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            }
        }
        
        //該当データなし、falseを返却する。
        if(l_handingCondBranchMarkets == null || l_handingCondBranchMarkets.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@取扱可能チェック 
        //１）で取得したオブジェクト数分、以下のチェックを行う。
        //チェック内容：
        //市場コード==引数の市場コード、かつ　@this.is取扱可能(銘柄タイプ)==trueの場合、
        //trueを返却する。
        int l_intSize = l_handingCondBranchMarkets.length;
        for(int i = 0; i < l_intSize; i++)
        {
            if(l_handingCondBranchMarkets[i].getMarketCode().equals(l_strMarketCode) 
            && l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
       
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    
}
@
