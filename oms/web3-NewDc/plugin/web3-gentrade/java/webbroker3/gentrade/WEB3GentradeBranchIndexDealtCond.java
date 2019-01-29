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
filename	WEB3GentradeBranchIndexDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （部店指数別）取扱条件(WEB3GentradeBranchIndexDealtCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/20 張宝楠 (中訊) 新規作成
Revesion History : 2006/05/11 凌建平 (中訊)【共通】仕様変更・モデルNo.184
Revesion History : 2006/05/17 凌建平 (中訊)【共通】仕様変更・モデルNo.190
Revesion History : 2006/05/17 凌建平 (中訊)【共通】実装の問題・モデルNo.012
Revesion History : 2006/08/09  栄イ (中訊)【共通】仕様変更・モデルNo.203
*/

package webbroker3.gentrade;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradedProductImpl;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.data.BranchIndexDealtCondDao;
import webbroker3.gentrade.data.BranchIndexDealtCondPK;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;


/**
 * (（部店指数別）取扱条件)<BR>
 * 証券会社、部店、原資産銘柄（指数種別）毎の取扱可能注文条件を表現する。<BR>
 * 先物オプションで使用。<BR>
 * <BR>
 * （DBレイアウト 「（部店指数別）取扱条件テーブル.xls」参照）<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3GentradeBranchIndexDealtCond implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3GentradeBranchIndexDealtCond.class);

    /**
     * (（部店指数別）取扱条件Row)<BR>
     * （部店指数別）取扱条件行オブジェクト<BR>
     * （自動生成DAOクラス）<BR>
     */
    private BranchIndexDealtCondRow branchIndexDealtCondRow;

    /**
     * (（部店指数別）取扱条件)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * <BR>
     * 本オブジェクトをインスタンス化し、<BR>
     * 引数の行オブジェクトをプロパティにセットする。<BR>
     * @@param l_branchIndexDealtCondRow - （部店指数別）取扱条件行オブジェクト
     * @@roseuid 40640EA5002E
     */
    public WEB3GentradeBranchIndexDealtCond(BranchIndexDealtCondRow l_branchIndexDealtCondRow)
    {
        final String STR_METHOD_NAME = "WEB3GentradeBranchIndexDealtCond(BranchIndexDealtCondRow)";
        if(l_branchIndexDealtCondRow == null)
        {
            log.error("（部店指数別）取扱条件行オブジェクト = null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "（部店指数別）取扱条件行オブジェクト = null");
        }
        
        this.branchIndexDealtCondRow = l_branchIndexDealtCondRow;
    }

    /**
     * (（部店指数別）取扱条件)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * 引数の条件に一致する（部店指数別）取扱条件オブジェクトを生成し返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@以下の条件にて（部店指数別）取扱条件テーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 証券会社コード<BR>
     * 　@部店コード = 部店コード<BR>
     * 　@市場コード = 市場コード<BR>
     * 　@原資産銘柄コード = 原資産銘柄コード<BR>
     * 　@先物／オプション区分 = 先物／オプション区分<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（（部店指数別）取扱条件Row）を<BR>
     * this.（部店指数別）取扱条件Rowにセットする。<BR>
     * 
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strMarketCode - 市場コード
     * @@param l_strFuturesOptionDivision - 先物／オプション区分<BR>
     * 　@ 1：先物 2：オプション<BR>
     * @@param l_strUnderlyingProductCode - 原資産銘柄コード<BR>
     *  　@0005：TOPIX　@0018：日経225　@0016：日経300　@0019：ミニ日経225<BR>
     * @@return WEB3GentradeBranchIndexDealtCond <BR>
     * @@throws WEB3SystemLayerException <BR>
     * @@roseuid 405FC33C034B
     */
    public WEB3GentradeBranchIndexDealtCond(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strMarketCode,
        String l_strFuturesOptionDivision,
        String l_strUnderlyingProductCode) throws WEB3SystemLayerException
    {   
        final String STR_METHOD_NAME =
            "WEB3GentradeBranchIndexDealtCond(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            BranchIndexDealtCondPK l_condPK = new BranchIndexDealtCondPK();
            
            l_condPK.institution_code    = l_strInstitutionCode;        //証券会社コード
            l_condPK.branch_code         = l_strBranchCode;             //部店コード
            l_condPK.market_code         = l_strMarketCode;             //市場コード
            l_condPK.future_option_div   = l_strFuturesOptionDivision;  //先物／オプション区分
            l_condPK.target_product_code = l_strUnderlyingProductCode;  //原資産銘柄コード
            
            this.branchIndexDealtCondRow = BranchIndexDealtCondDao.findRowByPk(l_condPK); 
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (（部店指数別）取扱条件)<BR>
     * <BR>
     * コンストラクタ。<BR>
     * 引数の証券会社、部店、先物OP取引銘柄に一致する<BR>
     * （部店指数別）取扱条件オブジェクトを生成し返却する。<BR>
     * １）　@銘柄、市場取得<BR>
     * 　@－先物OP取引銘柄.getProduct()にて、<BR>
     * 先物OP取引銘柄に関連する先物OP銘柄オブジェクトを取得する。<BR>
     * 　@－先物OP取引銘柄.getMarket()にて、<BR>
     * 先物OP取引銘柄に関連する市場オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@コンストラクタコール<BR>
     * 　@オーバーロードのコンストラクタをコールする。<BR>
     * <BR>
     * 　@[指定するパラメータ]<BR>
     * 　@証券会社コード = 先物OP銘柄.証券会社コード<BR>
     * 　@部店コード = 部店コード<BR>
     * 　@市場コード = 市場.市場コード<BR>
     * 　@原資産銘柄コード = 先物OP銘柄.原資産銘柄コード<BR>
     * 　@先物オプション区分 = 先物OP銘柄.先物／オプション区分<BR>
     * 
     * @@param l_strBranchCode - 部店コード <BR>
     * @@param l_ifoTradedProductImpl - 先物OP取引銘柄オブジェクト <BR>
     * @@return WEB3GentradeBranchIndexDealtCond <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E77B003E5
     */
    public WEB3GentradeBranchIndexDealtCond(String l_strBranchCode, IfoTradedProductImpl l_tradedProductImpl) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "WEB3GentradeBranchIndexDealtCond(String, IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //先物OP取引銘柄に関連する先物OP銘柄オブジェクトを取得する。
        Product l_product = l_tradedProductImpl.getProduct();
        IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();
        
        //先物OP取引銘柄に関連する市場オブジェクトを取得する。
        Market l_market = l_tradedProductImpl.getMarket();

        try
        {
            BranchIndexDealtCondPK l_condPK = new BranchIndexDealtCondPK();
            
            l_condPK.institution_code    = l_productRow.getInstitutionCode();        //証券会社コード
            l_condPK.branch_code         = l_strBranchCode;                          //部店コード
            l_condPK.market_code         = l_market.getMarketCode();                 //市場コード
            l_condPK.future_option_div   = l_productRow.getFutureOptionDiv();        //先物／オプション区分
            l_condPK.target_product_code = l_productRow.getUnderlyingProductCode();  //原資産銘柄コード
            
            this.branchIndexDealtCondRow = BranchIndexDealtCondDao.findRowByPk(l_condPK); 

        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * this.先物OP指数条件Rowを返却する。
     * @@return Object
     * @@roseuid 405E77B003E4
     */
    public java.lang.Object getDataSourceObject()
    {
        return this.branchIndexDealtCondRow;
    }

    /**
     * (get上限数量)<BR>
     * <BR>
     * 　@本オブジェクトが保持する行オブジェクトの項目値より<BR>
     * 上限数量を取得する。<BR>
     * <BR>
     * １） 各取引の規制判定<BR>
     * ○　@新規建買（is買建==true && is新規建==true）の場合<BR>
     * 　@（部店指数別）取扱条件Row.買建上限金額を返却する。<BR>
     * <BR>
     * ○　@新規建売（is買建==false && is新規建==true）の場合<BR>
     * 　@（部店指数別）取扱条件Row.売建上限金額を返却する。<BR>
     * <BR>
     * ○　@売建買返済（is買建==false && is新規建==false）の場合<BR>
     * 　@（部店指数別）取扱条件Row.買返済上限金額を返却する。<BR>
     * <BR>
     * ○　@買建売返済（is買建==true && is新規建==false）の場合<BR>
     * 　@（部店指数別）取扱条件Row.売返済上限金額を返却する。<BR>
     * @@param l_blnIsBuyToOpenOrder - is買建（isBuyToOpenOrder）
     * 買建かどうかの判定。
     * 買建の場合true、売建の場合false。
     * @@param l_blnIsOpenContract - is新規建（isOpenContract）<BR>
     * 新規建取引かどうかの判定。<BR>
     * 新規建の場合true、返済の場合false。<BR>
     * 
     * @@return double
     * @@roseuid 40611EEC03A6
     */
    public double getMaxQuantity(boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract)
    {
        final String STR_METHOD_NAME = 
            "getMaxQuantity(boolean l_blnIsBuyToOpenOrder, boolean l_blnIsOpenContract)";
        log.entering(STR_METHOD_NAME);
        
        if (l_blnIsBuyToOpenOrder && l_blnIsOpenContract) //新規建買        
        {
            return this.branchIndexDealtCondRow.getOpenContLongOrderLimit(); //買建上限数量
        }
        else if (!l_blnIsBuyToOpenOrder && l_blnIsOpenContract) //新規建売
        {
            return this.branchIndexDealtCondRow.getOpenContShortOrderLimit(); //売建上限数量
        }
        else if (!l_blnIsBuyToOpenOrder && !l_blnIsOpenContract) //売建買返済
        {
            return this.branchIndexDealtCondRow.getSettleContLongOrderLimit(); //買返済上限数量
        }
        else if (l_blnIsBuyToOpenOrder && !l_blnIsOpenContract) //買建売返済
        {
            return this.branchIndexDealtCondRow.getSettleContShortOrderLimit(); //売返済上限数量
        }

        log.exiting(STR_METHOD_NAME);        
        return 0;
    }

    /**
     * (is取扱可能)<BR>
     * 該当指数が取扱可能であるかを返却する。<BR>
     * <BR>
     * （this.先物OP指数条件Row.取扱可能 == ”取扱可能”）の場合true、<BR>
     * 以外はfalseを返却する。<BR>
     * @@return boolean
     * @@roseuid 406400BE0212
     */
    public boolean isHandlingPossible()
    {
        return WEB3DealtDef.CAN_DEALT.equals(this.branchIndexDealtCondRow.getEnableOrder());
    }

    /**
     * (get（部店指数別）取扱条件)<BR>
     * <BR>
     * （staticメソッド）<BR>
     * 引数の証券会社コード、部店コードに該当する取扱可能な<BR>
     * （部店指数別）取扱条件オブジェクトを全て取得する。<BR>
     * <BR>
     * １）DB検索<BR>
     * 　@（部店指数別）取扱条件テーブルを以下の条件で検索する。<BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = パラメータ.証券会社コード<BR>
     * 　@部店コード = パラメータ.部店コード<BR>
     *   先物／オプション区分 = パラメータ.先物／オプション区分<BR> 
     * 　@取扱可能 = 1(:取扱可能)<BR>
     * <BR>
     * ２）オブジェクト生成<BR>
     * 　@検索結果の（部店指数別）取扱条件行オブジェクトを指定し、<BR>
     * 　@（部店指数別）取扱条件オブジェクトを生成する。<BR>
     * 　@生成したオブジェクトを配列で返却する。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード <BR>
     * @@param l_strBranchCode - 部店コード <BR>
     * @@param l_strFutureOptionDiv - 先物／オプション区分 <BR>
     * @@return WEB3GentradeBranchIndexDealtCond[] <BR>
     * @@throws WEB3SystemLayerException <BR>
     * 
     * @@roseuid 406410E10251
     */
    public static WEB3GentradeBranchIndexDealtCond[] getHandlingCondBranchIndex(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strFutureOptionDiv) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getHandlingCondBranchIndex(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRecords = null;
        
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");     //証券会社コード
            l_sbWhere.append(" and branch_code = ? ");      //部店コード
            l_sbWhere.append(" and future_option_div = ? ");//先物／オプション区分
            l_sbWhere.append(" and enable_order = ? ");     //取扱可能
            
            Object[] l_objWhere = { 
                l_strInstitutionCode,   //証券会社コード
                l_strBranchCode,        //部店コード
                l_strFutureOptionDiv,   //先物／オプション区分
                WEB3DealtDef.CAN_DEALT  //1(:取扱可能)
                };
            
            //※市場コード昇順でソートして取得する。            
            l_lstRecords = l_processor.doFindAllQuery(
                BranchIndexDealtCondRow.TYPE,
                l_sbWhere.toString(),
                "to_number(market_code)",
                null,
                l_objWhere
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        
            
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexes = 
            new WEB3GentradeBranchIndexDealtCond[l_lstRecords.size()];
        
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            l_branchIndexes[i] =
                new WEB3GentradeBranchIndexDealtCond((BranchIndexDealtCondRow)l_lstRecords.get(i));
        }
            
        log.exiting(STR_METHOD_NAME);
            
        return l_branchIndexes;      
    }
    
    /**
     * (get（部店指数別）取扱条件)<BR>
     * （staticメソッド）<BR>
     * 引数の証券会社コードに該当する取扱可能な（部店指数別）取扱条件<BR>
     * オブジェクトを全て取得する。<BR>
     *  <BR>
     * １）DB検索<BR>
     * （部店指数別）取扱条件テーブルを以下の条件で検索する。<BR>
     * [条件]<BR>
     * 証券会社コード = パラメータ.証券会社コード<BR>
     * 取扱可能 = 1(:取扱可能)<BR>
     *  <BR>
     * ２）オブジェクト生成<BR>
     * 検索結果の（部店指数別）取扱条件行オブジェクトを指定し、<BR>
     * （部店指数別）取扱条件オブジェクトを生成する。生成した<BR>
     * オブジェクトを配列で返却する。<BR>
     *  <BR>
     * @@param l_strInstitutionCode - 証券会社コード <BR>
     * @@return WEB3GentradeBranchIndexDealtCond[] <BR>
     * @@throws WEB3SystemLayerException <BR>
     */
    public static WEB3GentradeBranchIndexDealtCond[] getHandlingCondBranchIndex(String l_strInstitutionCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =  "getHandlingCondBranchIndex(String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRecords;
        try
        {
            QueryProcessor L_queryProcessor = Processors.getDefaultProcessor();  
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");     //証券会社コード
            l_sbWhere.append(" and enable_order = ? ");     //取扱可能
            
            Object[] l_objWhere = { 
                l_strInstitutionCode,   //証券会社コード
                WEB3DealtDef.CAN_DEALT  //1(:取扱可能)
                };
            
            //※市場コード昇順でソートして取得する。            
            l_lstRecords = L_queryProcessor.doFindAllQuery(
                BranchIndexDealtCondRow.TYPE,
                l_sbWhere.toString(),
                "to_number(market_code)",
                null,
                l_objWhere
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        int l_intSize = l_lstRecords.size();
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexes = 
            new WEB3GentradeBranchIndexDealtCond[l_intSize];
        for (int i = 0; i < l_intSize; i++) 
        {
            l_branchIndexes[i] =
                new WEB3GentradeBranchIndexDealtCond((BranchIndexDealtCondRow)l_lstRecords.get(i));
        }
            
        log.exiting(STR_METHOD_NAME);
        return l_branchIndexes;  
        
    }
    
    /**
     * (get（部店指数別）取扱条件一覧)<BR>
     * （staticメソッド） <BR>
     * 引数の証券会社コード、部店コードに該当する（部店指数別）取扱条件オブジェクトを、<BR>
     * 取扱いの可／不可に関わらず全て取得する。 <BR>
     *  <BR>
     * １）DB検索 <BR>
     * 　@（部店指数別）取扱条件テーブルを以下の条件で検索する。 <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = パラメータ.証券会社コード <BR>
     * 　@部店コード = パラメータ.部店コード <BR>
     * <BR>
     * ２）オブジェクト生成 <BR>
     * 　@検索結果の（部店指数別）取扱条件行オブジェクトを指定し、 <BR>
     * 　@（部店指数別）取扱条件オブジェクトを生成する。 <BR>
     * 　@生成したオブジェクトを配列で返却する。<BR>
     *  <BR>
     * @@param l_strInstitutionCode - 証券会社コード <BR>
     * @@param l_strBranchCode - 部店コード <BR>
     * @@return WEB3GentradeBranchIndexDealtCond[] <BR>
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeBranchIndexDealtCond[] getHandlingCondBranchIndexList(
        String l_strInstitutionCode, String l_strBranchCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =  "getHandlingCondBranchIndexList(String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRecords = null;
        try
        {
            //１）DB検索 
            //（部店指数別）取扱条件テーブルを以下の条件で検索する。 
            //[条件] 
            //証券会社コード = パラメータ.証券会社コード 
            //部店コード = パラメータ.部店コード 
            QueryProcessor L_queryProcessor = Processors.getDefaultProcessor();  
            StringBuffer l_strQueryString = new StringBuffer();
            l_strQueryString.append(" institution_code = ? ");     //証券会社コード
            l_strQueryString.append(" and branch_code = ? ");     //部店コード
            
            Object[] l_objQueryValue = { 
                l_strInstitutionCode,   //証券会社コード
                l_strBranchCode  //部店コード
                };
            
            l_lstRecords = L_queryProcessor.doFindAllQuery(
                BranchIndexDealtCondRow.TYPE,
                l_strQueryString.toString(),
                l_objQueryValue
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２）オブジェクト生成 
        //検索結果の（部店指数別）取扱条件行オブジェクトを指定し、 
        //（部店指数別）取扱条件オブジェクトを生成する。 
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtConds = 
            new WEB3GentradeBranchIndexDealtCond[l_lstRecords.size()];
        
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            l_branchIndexDealtConds[i] =
                new WEB3GentradeBranchIndexDealtCond(
                    (BranchIndexDealtCondRow)l_lstRecords.get(i));
        }
            
        log.exiting(STR_METHOD_NAME);
        //生成したオブジェクトを配列で返却する。
        return l_branchIndexDealtConds;
    }
    
    /**
     * (get（部店指数別）取扱条件一覧)<BR>
     * （staticメソッド） <BR>
     * 引数の証券会社コード、部店コードに該当する（部店指数別）取扱条件オブジェクトを、<BR>
     * 取扱いの可／不可に関わらず全て取得する。 <BR>
     *  <BR>
     * １）DB検索 <BR>
     * 　@（部店指数別）取扱条件テーブルを以下の条件で検索する。 <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = パラメータ.証券会社コード <BR>
     * 　@部店コード = パラメータ.部店コード <BR>
     *   先物／オプション区分 = パラメータ.先物／オプション区分<BR>
     * <BR>
     * ２）オブジェクト生成 <BR>
     * 　@検索結果の（部店指数別）取扱条件行オブジェクトを指定し、 <BR>
     * 　@（部店指数別）取扱条件オブジェクトを生成する。 <BR>
     * 　@生成したオブジェクトを配列で返却する。<BR>
     *  <BR>
     * @@param l_strInstitutionCode - 証券会社コード <BR>
     * @@param l_strBranchCode - 部店コード <BR>
     * @@param l_strFutureOptionDiv - 先物／オプション区分 <BR>
     * @@return WEB3GentradeBranchIndexDealtCond[] <BR>
     * @@throws WEB3SystemLayerException
     */
    public static WEB3GentradeBranchIndexDealtCond[] getHandlingCondBranchIndexList(
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strFutureOptionDiv)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =  
            "getHandlingCondBranchIndexList(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRecords = null;
        try
        {
            //１）DB検索 
            //（部店指数別）取扱条件テーブルを以下の条件で検索する。 
            //[条件] 
            //証券会社コード = パラメータ.証券会社コード 
            //部店コード = パラメータ.部店コード 
            //先物／オプション区分 = パラメータ.先物／オプション区分
            QueryProcessor L_queryProcessor = Processors.getDefaultProcessor();  
            StringBuffer l_strQueryString = new StringBuffer();
            l_strQueryString.append(" institution_code = ? ");  //証券会社コード
            l_strQueryString.append(" and branch_code = ? ");   //部店コード
            l_strQueryString.append(" and future_option_div = ? "); //先物／オプション区分
            
            Object[] l_objQueryValue = { 
                l_strInstitutionCode,   //証券会社コード
                l_strBranchCode,  //部店コード
                l_strFutureOptionDiv  //先物／オプション区分
                };
            
            l_lstRecords = L_queryProcessor.doFindAllQuery(
                BranchIndexDealtCondRow.TYPE,
                l_strQueryString.toString(),
                l_objQueryValue
                );
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeBranchIndexDealtCond.class.getName() + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２）オブジェクト生成 
        //検索結果の（部店指数別）取扱条件行オブジェクトを指定し、 
        //（部店指数別）取扱条件オブジェクトを生成する。 
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtConds = 
            new WEB3GentradeBranchIndexDealtCond[l_lstRecords.size()];
        
        for (int i = 0; i < l_lstRecords.size(); i++) 
        {
            l_branchIndexDealtConds[i] =
                new WEB3GentradeBranchIndexDealtCond(
                    (BranchIndexDealtCondRow)l_lstRecords.get(i));
        }
            
        log.exiting(STR_METHOD_NAME);
        //生成したオブジェクトを配列で返却する。
        return l_branchIndexDealtConds;
    }

    /**
     * (get原資産銘柄コード)<BR>
     * <BR>
     * 本オブジェクトが保持する原資産銘柄コードを取得する。<BR>
     * <BR>
     * this.（部店指数別）取扱条件Row.原資産銘柄コードを返却する。<BR>
     * @@roseuid 406425180109
     */
    public String getUnderlyingProductCode()
    {
        return this.branchIndexDealtCondRow.getTargetProductCode();
    }

    /**
     * (get総売建上限数量)<BR>
     * <BR>
     * 本オブジェクトが保持する総売建上限数量を取得する。<BR>
     * <BR>
     * （部店指数別）取扱条件Row.総売建上限数量を返却する。<BR>
     * @@return double
     * @@roseuid 40AAC33A00F2
     */
    public double getTotalOpenSellMaxQuantity()
    {
        return this.branchIndexDealtCondRow.getOpenContLimit();
    }
}
@
