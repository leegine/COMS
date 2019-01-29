head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeFeqBranchMarketDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (部店市場別.外株)取扱条件(WEB3GentradeFeqBranchMarketDealtCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/07 孟東 (中訊) 新規作成
Revesion History : 2005/07/19 孟東 (中訊) 仕様変更No.146を対応
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
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
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondDao;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondRow;
import webbroker3.util.WEB3LogUtility;

/**
 *（部店市場別.外株）取扱条件<BR>
 * 証券会社、部店、市場毎の取扱可能注文条件を表現する。<BR>
 * 外国株式で使用。<BR>
 * <BR>
 *（DBレイアウト 「（部店市場別.外株）取扱条件テーブル.xls」参照）<BR>
 * <BR>
 */
public class WEB3GentradeFeqBranchMarketDealtCond implements BusinessObject
{

    /**
     * （部店市場別.外株）取扱条件Row行オブジェクト <BR>
     * （DAO自動生成クラス）<BR>
     */
    private FeqBranchMarketDealtCondRow feqBranchMarketDealtCondRow;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeFeqBranchMarketDealtCond.class);

    /**
     * コンストラクタ。 <BR>
     * (部店市場別)取扱条件 <BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、 <BR>
     * 引数の行オブジェクトをプロパティにセットする。 <BR>
     * <BR>
     * @@param l_feqBranchMarketDealtCondRow （部店市場別.外株）取扱条件行オブジェクト
     * @@roseuid 40640F0102AE
     */
    public WEB3GentradeFeqBranchMarketDealtCond(
        FeqBranchMarketDealtCondRow l_feqBranchMarketDealtCondRow)
    {
        final String STR_METHOD_NAME = 
            "WEB3GentradeFeqBranchMarketDealtCond(FeqBranchMarketDealtCondRow)";
        if(l_feqBranchMarketDealtCondRow == null)
        {
            log.error("（部店市場別.外株）取扱条件行オブジェクト = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "（部店市場別.外株）取扱条件行オブジェクト = null");
        }

        this.feqBranchMarketDealtCondRow = l_feqBranchMarketDealtCondRow;
    }

    /**
     * コンストラクタ。 <BR>
     * 引数の条件に一致する（部店市場別.外株）取扱条件オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 引数の値にて（部店市場別.外株）取扱条件テーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR> 
     * 　@検索結果の行オブジェクト（（部店市場別.外株）取扱条件Row）を<BR>
     * this.（部店市場別.外株）取扱条件にセットする。<BR> 
     * <BR>
     * @@param l_strInstitutionCode 証券会社コード <BR>
     * @@param l_strBranchCode 部店コード <BR>
     * @@param l_strMarketCode 市場コード <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 4060ED5C0373
     */
    public WEB3GentradeFeqBranchMarketDealtCond(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeFeqBranchMarketDealtCond(String, String, String)";
        log.entering(STR_METHOD_NAME);

        FeqBranchMarketDealtCondRow l_row = null;
        try
        {
            //（部店市場別.外株）取扱条件テーブル
            l_row = FeqBranchMarketDealtCondDao.findRowByPk(
                 l_strInstitutionCode,
                 l_strBranchCode,
                 l_strMarketCode
                 );
        }
        catch (DataFindException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
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
            this.feqBranchMarketDealtCondRow = l_row;
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
     * this.部店市場別.外株-取扱条件Rowを返却する。
     * @@return Object
     * @@roseuid 4060ED5C0372
     */
    public Object getDataSourceObject()
    {
        return this.feqBranchMarketDealtCondRow;
    }

    /**
     * (get（部店市場別.外株）取扱条件)<BR>
     * （staticメソッド）<BR>
     * 引数の部店に該当する（部店市場別.外株）取扱条件オブジェクトを<BR>
     * 全て取得する。<BR> 
     * ※市場コード昇順で取得する。<BR>
     * <BR>
     * １）DB検索<BR>
     * （部店市場別.外株）取扱条件テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 部店.getInstitution().getInstitutionCode()<BR>
     * 　@部店コード = 部店.getBranchCode()<BR>
     * 　@※市場コード昇順でソートして取得する。<BR>
     * <BR>
     * ２）オブジェクト生成<BR>
     * 　@検索結果の（部店市場別.外株）取扱条件行オブジェクトを指定し、<BR>
     * 　@（部店市場別.外株）取扱条件オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを配列で返却する。<BR> 
     * <BR>
     * @@param l_branch 部店オブジェクト
     * @@return webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 40640F69033B
     */
    public static WEB3GentradeFeqBranchMarketDealtCond[] 
        getFeqHandlingCondBranchMarket(
            WEB3GentradeBranch l_branch)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getFeqHandlingCondBranchMarket(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);

        //証券会社コード 
        String l_strInstitutionCode = 
            l_branch.getInstitution().getInstitutionCode();
        //部店コード
        String l_strBranchCode = l_branch.getBranchCode();

        List l_lisRecords = null;
        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");   //証券会社コード
            l_sbWhere.append(" and  branch_code = ? ");   //部店コード

            Object[] l_objBranchMarketDealtCondWhere = { 
                l_strInstitutionCode,    //証券会社コード
                l_strBranchCode          //部店コード
                }; 

            //※市場コード昇順でソートして取得する。
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                FeqBranchMarketDealtCondRow.TYPE,
                l_sbWhere.toString(),
                " market_code asc ",
                null,
                l_objBranchMarketDealtCondWhere
                );
        }
        catch (DataException e)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeFeqBranchMarketDealtCond.class.getName()
                + "." + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        //２）オブジェクト生成
        int l_intSize = l_lisRecords.size();
        FeqBranchMarketDealtCondRow l_rowLoop = null;
        WEB3GentradeFeqBranchMarketDealtCond 
            l_gentradeFeqHandingCondBranchMarketLoop = null;
        WEB3GentradeFeqBranchMarketDealtCond[] l_feqBranchMarkets = 
            new WEB3GentradeFeqBranchMarketDealtCond[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_rowLoop = (FeqBranchMarketDealtCondRow) l_lisRecords.get(i);

            l_gentradeFeqHandingCondBranchMarketLoop =
                new WEB3GentradeFeqBranchMarketDealtCond(l_rowLoop);

            l_feqBranchMarkets[i] = l_gentradeFeqHandingCondBranchMarketLoop;
        }
                
        log.exiting(STR_METHOD_NAME);
        return l_feqBranchMarkets;
    }

    /**
     * (get（部店市場別.外株）取扱条件)<BR>
     * （staticメソッド）<BR>
     * 引数の証券会社コードに該当する<BR>
     * （部店市場別.外株）取扱条件オブジェクトを全て取得する。<BR>
     * ※市場コード昇順で取得する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@（部店市場別.外株）取扱条件テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@※市場コード昇順でソートして取得する。<BR>
     * <BR>
     * ２）オブジェクト生成<BR>
     * 　@検索結果の（部店市場別.外株）取扱条件行オブジェクトを指定し、<BR>
     * 　@（部店市場別.外株）取扱条件オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを配列で返却する。<BR> 
     * <BR>
     * @@param l_strInstitutionCode (証券会社コード) <BR>
     * @@return webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond[]
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeFeqBranchMarketDealtCond[] 
        getFeqHandlingCondBranchMarket(
            String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeFeqBranchMarketDealtCond(String)";
        log.entering(STR_METHOD_NAME);

        //１）（部店市場別）取扱条件テーブルを以下の条件で検索する。
        //証券会社コード = パラメータ.証券会社コード
        //※市場コード昇順でソートして取得する。
        List l_lstRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                FeqBranchMarketDealtCondRow.TYPE,
                " institution_code = ?",
                " market_code asc ",
                null,
                new Object[]{l_strInstitutionCode}
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeFeqBranchMarketDealtCond.class.getName()
                    + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //２）オブジェクト生成
        //検索結果の（部店市場別）取扱条件行オブジェクトを指定し、
        //（部店市場別）取扱条件オブジェクトを生成する。生成したオブジェクトを配列で返却する。
        int l_intSize = l_lstRecords.size();
        FeqBranchMarketDealtCondRow l_rowLoop = null;
        WEB3GentradeFeqBranchMarketDealtCond 
            l_gentradeFeqHandingCondBranchMarketLoop = null;
        WEB3GentradeFeqBranchMarketDealtCond[] l_feqBranchMarkets = 
            new WEB3GentradeFeqBranchMarketDealtCond[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_rowLoop = (FeqBranchMarketDealtCondRow) l_lstRecords.get(i);

            l_gentradeFeqHandingCondBranchMarketLoop =
                new WEB3GentradeFeqBranchMarketDealtCond(l_rowLoop);

            l_feqBranchMarkets[i] = l_gentradeFeqHandingCondBranchMarketLoop;
        }

        log.exiting(STR_METHOD_NAME);
        return l_feqBranchMarkets;    
    }

    /**
     * (get市場コード) <BR>
     * 本オブジェクトが保持する市場コードを取得する。<BR>
     * <BR>
     * this.（部店市場別.外株）取扱条件Row.市場コードを返却する。<BR> 
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4064213502BE
     */
    public String getMarketCode()
    {
        return this.feqBranchMarketDealtCondRow.getMarketCode();
    }

    /**
     * (get取扱可能市場) <BR>
     * （staticメソッド） <BR>
     * 引数の部店に該当する（部店市場別.外株）取扱条件オブジェクトを全て取得し、<BR>
     * 引数の銘柄タイプに該当するオブジェクトの市場コードのみを<BR>
     * ArrayListに設定して返す。<BR>
     * ArrayListへの設定は、市場コード昇順とする。<BR>
     * <BR>
     * １）データ取得<BR>
     * 　@this.get（部店市場別.外株）取扱条件(部店)により、<BR>
     * 　@引数の部店に該当する（部店市場別.外株）取扱条件オブジェクトを<BR>
     * 全て取得する。<BR> 
     * ２）取扱可能チェック<BR>
     * 　@１）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     * <BR>
     * 　@チェック内容：<BR>
     * 　@this.is取扱可能(銘柄タイプ)==trueの場合、<BR>
     * 　@当該レコードの市場コードをArrayListに追加する。<BR>
     * <BR>
     * ３）作成したArrayListを返す。<BR>
     * <BR>
     * @@param l_genBranch 部店オブジェクト
     * @@param l_productTypeEnum 銘柄タイプ
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

        //１）get（部店市場別.外株）取扱条件(部店)
        WEB3GentradeFeqBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getFeqHandlingCondBranchMarket(l_genBranch);

        //２）取扱可能チェック
        ArrayList l_lstHandlingPossibleMarkets =  new ArrayList();
        int l_intLength = l_handingCondBranchMarkets.length;
        //this.is取扱可能(銘柄タイプ)==trueの場合、
        //当該レコードの市場コードをArrayListに追加する。
        for (int i = 0; i < l_intLength; i++)
        {
            if(l_handingCondBranchMarkets[i].isHandlingPossible(l_productTypeEnum))
            {
                String l_strMarketCode = 
                    l_handingCondBranchMarkets[i].getMarketCode();
                if(!l_lstHandlingPossibleMarkets.contains(l_strMarketCode))
                {
                    l_lstHandlingPossibleMarkets.add(l_strMarketCode);
                }
            }
        }

        //３）作成したデータを返す。
        String[] l_strHandlingPossibleMarkets = 
            new String[l_lstHandlingPossibleMarkets.size()];
        l_lstHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (get取扱可能市場) <BR>
     * （staticメソッド） <BR>
     * 引数の証券会社コードに該当する <BR>
     * （部店市場別.外株）取扱条件オブジェクトを全て取得し、<BR>
     * 引数の銘柄タイプに該当するオブジェクトの市場コードのみを<BR>
     * ArrayListに設定して返す。<BR>
     * ArrayListへの設定は、市場コード昇順とする。<BR>
     * <BR>
     * １）データ取得<BR>
     * 　@this.get（部店市場別.外株）取扱条件(.証券会社コード)により、<BR>
     * 　@引数の証券会社コードに該当する<BR>
     * 　@（部店市場別.外株）取扱条件オブジェクトを全て取得する。<BR>
     * <BR>
     * ２）取扱可能チェック<BR>
     * 　@１）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     * <BR>
     * 　@チェック内容：<BR>
     * 　@this.is取扱可能(銘柄タイプ)==trueの場合、<BR>
     * 　@当該レコードの市場コードをArrayListに追加する。 <BR>
     * 　@※ただし、当該レコードの市場コードが既にArrayListに存在する場合は、<BR>
     * 　@　@追加しない。<BR>
     * <BR>
     * ３）作成したArrayList.toArray()の戻り値を返す。<BR>
     * <BR> 
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_productTypeEnum 銘柄タイプ
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
        //get（部店市場別.外株）取扱条件(.証券会社コード)により、
        //引数の証券会社コードに該当する（部店市場別）取扱条件オブジェクトを全て取得する。
        WEB3GentradeFeqBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getFeqHandlingCondBranchMarket(l_strInstitutionCode);
        
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
                if(!l_lstHandlingPossibleMarkets.contains(l_strMarketCode))
                {
                    l_lstHandlingPossibleMarkets.add(l_strMarketCode);
                }
            }
        }
        
        //３）作成したデータを返す。
        String[] l_strHandlingPossibleMarkets = 
            new String[l_lstHandlingPossibleMarkets.size()];
        l_lstHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (get取引中取扱可能市場) <BR>
     * （staticメソッド） <BR>
     * 引数の部店に該当する（部店市場別.外株）取扱条件オブジェクトを<BR>
     * 全て取得し、<BR> 
     * 引数の銘柄タイプに該当するオブジェクトの市場コードのうち、<BR>
     * 取引中の市場の市場コードのみを<BR>
     * ArrayListに設定して返す。<BR>
     * ArrayListへの設定は、市場コード昇順とする。<BR>
     * <BR>
     * １）データ取得<BR>
     * 　@this.get（部店市場別.外株）取扱条件(部店)により、<BR>
     * 　@引数の部店に該当する（部店市場別.外株）取扱条件オブジェクトを<BR>
     *   全て取得する。<BR>
     * <BR>
     * ２）取扱可能チェック・取引中チェック<BR>
     * 　@１）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     * <BR>
     * 　@チェック内容：<BR>
     * 　@this.is取扱可能(銘柄タイプ)==true、かつ 取引中の市場(*1)の場合は、<BR>
     * 　@当該レコードの市場コードをArrayListに追加する。<BR>
     * <BR>
     * 　@(*1)取引中の市場<BR>
     * 　@　@拡張金融オブジェクトマネージャ.get市場(<BR>
     *     部店.証券会社コード, 当該レコードの市場コード)により<BR>
     * 　@　@市場オブジェクトを取得。<BR>
     * 　@　@取得した市場.取引停止≠"取引停止中"の場合は、<BR>
     *     取引中の市場と判定する。<BR>
     * 　@　@※市場オブジェクトが取得できなかった場合は、<BR>
     *     取引中の市場ではないと判定する。<BR> 
     * <BR>
     * ３）作成したArrayListを返す。<BR> 
     * <BR>
     * @@param l_genBranch 部店オブジェクト
     * @@param l_productTypeEnum 銘柄タイプ
     * @@return String[]
     * @@throws WEB3SystemLayerException
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
        
        //１）get（部店市場別.外株）取扱条件(部店)
        WEB3GentradeFeqBranchMarketDealtCond[] l_handingCondBranchMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getFeqHandlingCondBranchMarket(l_genBranch);
        
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
                if(!WEB3SuspensionDef.SUSPENSION.equals(l_marketRow.getSuspension()))
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
        String[] l_strHandlingPossibleMarkets = 
            new String[l_lstHandlingPossibleMarkets.size()];
        l_lstHandlingPossibleMarkets.toArray(l_strHandlingPossibleMarkets);
        
        log.exiting(STR_METHOD_NAME);
        return l_strHandlingPossibleMarkets;
    }

    /**
     * (is取扱可能) <BR>
     * 指定商品が取扱可能であるかを返却する。<BR>
     * <BR>
     * 引数の条件によって、this.（部店市場別.外株）取扱条件Rowの<BR>
     * 対応する項目値(*1)を判定し、<BR>
     * ”取扱可能”であればtrue、以外falseを返却する。<BR>
     * <BR>
     * ○　@銘柄タイプがProductTypeEnum.外国株式以外の場合<BR>
     * 　@例外をthrowする。<BR>
     * ○　@銘柄タイプがProductTypeEnum.外国株式の場合<BR>
     * 　@　@：(*1)として、「取扱可能」を使用する。 <BR>
     * <BR>
     * @@param l_productTypeEnum 銘柄タイプ
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

        //銘柄タイプがProductTypeEnum.外国株式の場合
        if (l_productTypeEnum.equals(ProductTypeEnum.FOREIGN_EQUITY))
        {
            if(WEB3DealtDef.CAN_DEALT.equals(
                feqBranchMarketDealtCondRow.getMartCanDealtEquity()))
            {
                l_blnIsHandingPossible = true;
            }
            else
            {
                l_blnIsHandingPossible = false;
            }
        }
        //銘柄タイプがProductTypeEnum.外国株式以外の場合
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
     * (is取扱可能市場) <BR>
     *  (staticメソッド）<BR>
     * 引数の証券会社コード、部店コードに該当する<BR>
     * （部店市場別.外株）取扱条件オブジェクトを全て取得し、<BR>
     * 取得レコード中に、指定市場の、取扱可能プロパティ=="取扱可能"の<BR>
     * レコードが含まれている場合はtrueを、<BR>
     * 含まれていない場合はfalseを、それぞれ返却する。<BR>
     * <BR>
     * １）　@データ取得<BR>
     * <BR>
     * １－１）　@引数の部店コード == null（部店指定なし（全部店指定））の場合<BR>
     * <BR>
     * this.get（部店市場別.外株）取扱条件(引数の証券会社コード)により、<BR>
     * 引数の証券会社コードに該当する<BR>
     * （部店市場別.外株）取扱条件オブジェクトを全て取得する。<BR>
     * <BR>
     * １－２）　@引数の部店コード != null（部店指定あり）の場合<BR>
     * <BR>
     * this.get（部店市場別.外株）取扱条件(.部店)により、<BR>
     * 引数の証券会社コード、部店コードに該当する<BR>
     * （部店市場別.外株）取扱条件オブジェクトを全て取得する。<BR>
     * <BR>
     * ※部店オブジェクトは、<BR>
     * 拡張アカウントマネージャ.get部店(引数の証券会社コード, 引数の部店コード)<BR>
     * ※により取得する。<BR> 
     * <BR>
     * ２）　@取扱可能チェック<BR>
     * １）で取得したオブジェクト数分、以下のチェックを行う。<BR>
     * <BR>
     * チェック内容：<BR>
     * 市場コード==引数の市場コード、<BR>
     * かつ　@this.is取扱可能(銘柄タイプ)==trueの場合、<BR>
     * trueを返却する。<BR>
     * <BR>
     * ３）　@１）で該当データなし、<BR>
     * 　@　@　@もしくは　@取得した全オブジェクトが全て２）で<BR>
     * 　@　@　@取扱不可と判断された場合、<BR>
     * 　@　@　@falseを返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_productTypeEnum 銘柄タイプ
     * @@param l_strBranchCode 部店コード<BR>
     *     部店の指定なし（全部店を対象とする）の場合は、nullをセット
     * @@param l_strMarketCode 市場コード
     * @@return boolean
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
        WEB3GentradeFeqBranchMarketDealtCond[] l_handingCondBranchMarkets;
        if(l_strBranchCode == null)
        {
            l_handingCondBranchMarkets =
                getFeqHandlingCondBranchMarket(l_strInstitutionCode);
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
                    getFeqHandlingCondBranchMarket(l_genBranch);     
            }
            catch (NotFoundException nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeFeqBranchMarketDealtCond.class.getName()
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
       
        // ３）　@１）で該当データなし、
        // 　@　@　@もしくは　@取得した全オブジェクトが全て２）で
        // 　@　@　@取扱不可と判断された場合、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
