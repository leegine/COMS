head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityTradedProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部 
File Name        : 取引銘柄(WEB3EquityTradedProduct.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/20 中尾寿彦(SRA) 新規作成
Revesion History : 2007/02/10 趙林鵬(中訊) モデル No.1122
Revesion History : 2007/11/12 張騰宇(中訊) モデル No.1203
*/

package webbroker3.equity;

import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeTradedProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MartCanDealtDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3QuoteFromDivDef;
import webbroker3.common.define.WEB3QuoteTypeDivDef;
import webbroker3.common.define.WEB3RegistDivisionDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.equity.data.OrderCarryoverSkipProdRow;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.LastClosingPriceParams;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.quoteadaptor.WEB3EqTypeQuoteData;
import webbroker3.quoteadaptor.RealType;

/**
 * （取引銘柄）。
 * @@version 1.0
 */
public class WEB3EquityTradedProduct extends EqTypeTradedProductImpl
{

    /**
     * （証券会社コード）。
     */
    private String institutionCode;

    /**
     * （銘柄コード）。
     */
    private String productCode;

    /**
     * （市場コード）。
     */
    private String marketCode;

    /**
     * （基準日）。
     */
    private Date baseDate;

    /**
     * （Logger）。
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityTradedProduct.class);

    /**
     * @@param l_row
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@roseuid 4042EC8C0341
     */
    public WEB3EquityTradedProduct(TradedProductRow l_row)
        throws DataQueryException, DataNetworkException
    {
        super(l_row);

        institutionCode = l_row.getInstitutionCode();
        WEB3EquityProduct l_product =
            new WEB3EquityProduct(l_row.getProductId());
        productCode = l_product.getProductCode();
        WEB3GentradeMarket l_market =
            new WEB3GentradeMarket(l_row.getMarketId());
        marketCode = l_market.getMarketCode();
        baseDate = l_row.getBaseDate();
    }

    /**
     * （is現物売買規制）。<BR>
     * <BR>
     * 取引銘柄に、現物株式の売買規制がかかっていないかをチェックする。<BR>
     * <BR>
     * １）　@売注文が規制されていないか判定する。<BR>
     * 売注文の場合（引数.is売注文==true）、<BR>
     * 取引銘柄.売現物停止が”停止中”の場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * <BR> 
     * ２）　@買注文が規制されていないかチェックする。<BR>
     * 買注文の場合（引数.is売注文==false）、<BR>
     * 取引銘柄.買現物停止が”停止中”の場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * <BR>
     * @@param is売注文 - 売注文、買注文のフラグ。<BR>
     * 売注文の場合true、買注文の場合falseを指定する。<BR>
     * @@param l_isSellOrder
     * @@return boolean
     * @@roseuid 4042EC8D013F
     */
    public boolean isSpotTradeControl(boolean l_isSellOrder)
    {
        final String STR_METHOD_NAME = "isSpotTradeControl(boolean)";
        log.entering(STR_METHOD_NAME);

        int l_inSpotTradeControl;
        boolean l_boReturn;

        if (l_isSellOrder)
        {
            // １）　@売注文が規制されていないか判定する。
            l_inSpotTradeControl =
                ((EqtypeTradedProductParams)this.getDataSourceObject())
                    .getSellCashStop();
        }
        else
        {
            // ２）　@買注文が規制されていないかチェックする。
            l_inSpotTradeControl =
                ((EqtypeTradedProductParams)this.getDataSourceObject())
                    .getBuyCashStop();
        }

        if (l_inSpotTradeControl != Integer.parseInt(WEB3TradeStopDef.ACTIVE))
        {
            l_boReturn = true;
        }
        else
        {
            l_boReturn = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * （is現物成行指定規制）。<BR>
     * <BR>
     * 取引銘柄に、現物株式の成行注文規制がかかっていないかをチェックする。<BR>
     * <BR>
     * １）　@売注文が規制されていないか判定する。<BR>
     * 売注文の場合（引数.is売注文==true）、<BR>
     * 取引銘柄.売現物成行指定停止が”停止中”の場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * <BR>
     * ２）　@買注文が規制されていないかチェックする。<BR>
     * 買注文の場合（引数.is売注文==false）、<BR>
     * 取引銘柄.買現物成行指定停止が”停止中”の場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * @@param is売注文 - 売注文、買注文のフラグ。<BR>
     * 売注文の場合true、買注文の場合falseを指定する。<BR>
     * @@param l_isSellOrder
     * @@return boolean
     * @@roseuid 4042EC8D014E
     */
    public boolean isSpotMarketOrderDesignateCtrl(boolean l_isSellOrder)
    {
        final String STR_METHOD_NAME =
            "isSpotMarketOrderDesignateCtrl(boolean)";
        log.entering(STR_METHOD_NAME);

        int l_inSpotMarketOrderDesignateCtrl;
        boolean l_boReturn;

        if (l_isSellOrder)
        {
            //１）　@売注文が規制されていないか判定する。
            l_inSpotMarketOrderDesignateCtrl =
                ((EqtypeTradedProductParams)this.getDataSourceObject())
                    .getSellSpotMarketOrdDesStop();
        }
        else
        {
            //２）　@買注文が規制されていないかチェックする。
            l_inSpotMarketOrderDesignateCtrl =
                ((EqtypeTradedProductParams)this.getDataSourceObject())
                    .getBuySpotMarketOrdDesStop();
        }

        if (l_inSpotMarketOrderDesignateCtrl
            != Integer.parseInt(WEB3TradeStopDef.ACTIVE))
        {
            l_boReturn = true;
        }
        else
        {
            l_boReturn = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * （validateJASDAQ銘柄取扱可能）。<BR>
     * <BR>
     * 当取引銘柄がJASDAQ銘柄の場合、<BR>
     * 指定された部店が当該取引銘柄を取引可能かどうかをチェックする。<BR>
     * <BR>
     * １）　@this.市場ID に該当する市場オブジェクト.市場コード≠JASDAQの場合は、<BR>
     * 　@　@　@何もせずにreturnする。<BR>
     * <BR>
     * ２）　@上記以外の場合、以下の処理を行う。<BR>
     * <BR>
     * ２－１）　@this.店頭公開区分＝マーケットメイク の場合、<BR>
     * 　@　@　@　@　@引数の部店.マーケットメイク取扱＝取扱可能 の場合は、<BR>
     * 　@　@　@　@　@そのままreturnする。<BR>
     * 　@　@　@　@　@引数の部店.マーケットメイク取扱＝取扱不可 の場合は、<BR>
     * 　@　@　@　@　@例外をthrowする。<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00006<BR>
     * <BR>
     * @@param l_branch - (部店)<BR>
     * 部店オブジェクト。<BR>
     * @@return WEB3BusinessLayerException <BR>
     * @@roseuid 40A42A450242
     */
    public void validateJASDAQProductHandling(WEB3GentradeBranch l_branch)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateJASDAQProductHandling(WEB3GentradeBranch)";

        log.entering(STR_METHOD_NAME);
        if (l_branch == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        EqtypeTradedProductRow l_tradedProductRow =
            (EqtypeTradedProductRow)this.getDataSourceObject();
        long l_lngMarketId = l_tradedProductRow.getMarketId();

        Market l_market = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        try
        {
            l_market = l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "市場ID：[" + l_lngMarketId + "] に該当する市場データが見つかりません。",
                l_nfe);
        }
        String l_strMarketCode = l_market.getMarketCode();
        
        // １）　@this.市場ID に該当する市場オブジェクト.市場コード≠JASDAQの場合は、
        // 　@　@　@何もせずにreturnする。
        if (WEB3MarketCodeDef.JASDAQ.equals(l_strMarketCode) == false)
        {
            return;
        }

        // ２－１）　@this.店頭公開区分＝マーケットメイク の場合、
        // 　@　@　@　@　@引数の部店.マーケットメイク取扱＝取扱可能 の場合は、
        // 　@　@　@　@　@そのままreturnする。
        // 　@　@　@　@　@引数の部店.マーケットメイク取扱＝取扱不可 の場合は、
        // 　@　@　@　@　@例外をthrowする。
        if (WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
        {
            BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
            if (WEB3MartCanDealtDef.DEAL_DISABLED.equals(String.valueOf(l_branchRow.getHandlingMarketMake())))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00006,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }  
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （is繰越スキップ銘柄）。<BR>
     * <BR>
     * 注文繰越処理のスキップ対象銘柄であるかどうかを判定する。<BR>
     * 繰越スキップ銘柄である場合はtrueを、そうでない場合はfalseを返す。<BR>
     * <BR>
     * １）　@【注文繰越スキップ銘柄テーブル】より、以下の条件でデータを取得する。<BR>
     * <BR>
     * 　@　@証券会社コード＝this.証券会社コード<BR>
     * 　@　@かつ　@銘柄コード＝(ALL(全銘柄) or this.銘柄コード)<BR>
     * 　@　@かつ　@市場コード＝(F(全市場) or this.市場コード)<BR>
     * 　@　@かつ　@登録区分＝1(注文繰越スキップ)<BR>
     * <BR>
     * ２）　@該当するデータが存在する場合はtrueを、該当データなしの場合はfalseを返す。<
     * BR>
     * @@return boolean
     * @@roseuid 40A42ACB0128
     */
    public boolean isTransferSkipProduct() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTransferSkipProduct()";
        log.entering(STR_METHOD_NAME);

        //返還の値の設定
        boolean l_bReturn = false;
        List l_lisRows = null;
        try
        {
            String l_strWhere =
                " institution_code = ? and (product_code = 'ALL' or product_code = ?)"
                    + " and (market_code = 'F' or market_code = ?) and regist_division = '"
                    + WEB3RegistDivisionDef.ORDER_TRANSFER_STOP
                    + "' ";

            Object l_bindVars[] =
                { this.institutionCode, this.productCode, this.marketCode };
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    OrderCarryoverSkipProdRow.TYPE,
                    l_strWhere,
                    null,
                    null,
                    l_bindVars);
        }
        catch (DataFindException dfe)
        {
            log.error("テーブルに該当するデータがありません。", dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataNetworkException dne)
        {
            log.error("DBへのアクセスに失敗しました", dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }
        catch (DataQueryException dqe)
        {
            log.error("テーブルの検索に失敗しました", dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }

        if (l_lisRows.size() > 0)
        {
            //該当するデータが存在する場合はtrue
            l_bReturn = true;
        }

        log.exiting(STR_METHOD_NAME);
        return l_bReturn;
    }

    /**
     * （get基準日）。
     * @@return 基準日
     */
    public Date getBaseDate()
    {
        return baseDate;
    }

    /**
     * （get証券会社コード）。
     * @@return 証券会社コード
     */
    public String getInstitutionCode()
    {
        return institutionCode;
    }

    /**
     * （get市場コード）。
     * @@return 市場コード
     */
    public String getMarketCode()
    {
        return marketCode;
    }

    /**
     * （get銘柄コード）。
     * @@return 銘柄コード
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * （set基準日）。
     * @@param l_datBaseDate 基準日
     */
    public void setBaseDate(Date l_datBaseDate)
    {
        this.baseDate = l_datBaseDate;
    }

    /**
     * （set証券会社コード）。
     * @@param l_strInstitutionCode 証券会社コード
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        this.institutionCode = l_strInstitutionCode;
    }

    /**
     * （set市場コード）。
     * @@param l_strMarketCode 市場コード
     */
    public void setMarketCode(String l_strMarketCode)
    {
        this.marketCode = l_strMarketCode;
    }

    /**
     * （set銘柄コード）。
     * @@param l_strProductCode 銘柄コード
     */
    public void setProductCode(String l_strProductCode)
    {
        this.productCode = l_strProductCode;
    }

    /**
     * （is信用売買規制）。<BR>
     * <BR>
     * 取引銘柄に、信用取引の売買規制がかかっていないかを<BR>
     * 注文カテゴリ＋弁済区分別にチェックする。<BR>
     * <BR>
     * １）　@引数.注文カテゴリが以下のいずれでもない場合は、例外をthrowする。<BR>
     * <BR>
     * 　@　@　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）<BR>
     * 　@　@　@OrderCategEnum.”返済注文”（CLOSE_MARGIN）<BR>
     * 　@　@　@OrderCategEnum.”現引・現渡注文”（SWAP_MARGIN）<BR>
     * <BR>
     * ２）　@新規建注文が規制されていないかを判定する。<BR>
     * <BR>
     * 　@　@引数.注文カテゴリ＝OrderCategEnum.”新規建注文”<BR>
     * （OPEN_MARGIN）の場合、<BR>
     * 　@　@以下の判定を行う。<BR>
     * <BR>
     * 　@２－１）　@買建が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・買建（引数.is売建==false）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”DEFAULT”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買制度信用停止＝”停止中”、かつ、<BR>
     * 　@　@　@　@　@取引銘柄.買一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買制度信用停止＝”停止中”であれば、trueを返す。<BR>
     * 以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 以外、falseを返す。<BR>
     * <BR>
     * 　@２－２）　@売建が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・売建（引数.is売建==true）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”DEFAULT”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売制度信用停止＝”停止中”、かつ、<BR>
     * 　@　@　@　@　@取引銘柄.売一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売制度信用停止＝”停止中”であれば、trueを返す。<BR>
     * 以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 以外、falseを返す。<BR>
     * <BR>
     * ３）　@返済注文が規制されていないかを判定する。<BR>
     * <BR>
     * 　@　@引数.注文カテゴリ＝OrderCategEnum.”返済注文”（CLOSE_MARGIN）の場合、<BR>
     * 　@　@以下の判定を行う。<BR>
     * <BR>
     * 　@３－１）　@買建が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・買建（引数.is売建==false）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”DEFAULT”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買建返済（売返済）制度信用停止＝”停止中”、かつ、<BR>
     * 　@　@　@　@　@取引銘柄.買建返済（売返済）一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買建返済（売返済）制度信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買建返済（売返済）一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * <BR>
     * 　@３－２）　@売建が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・売建（引数.is売建==true）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”DEFAULT”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売建返済（買返済）制度信用停止＝”停止中”、かつ、<BR>
     * 　@　@　@　@　@取引銘柄.売建返済（買返済）一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売建返済（買返済）制度信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売建返済（買返済）一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * <BR>
     * ４）　@現引現渡注文が規制されていないかを判定する。<BR>
     * <BR>
     * 　@　@引数.注文カテゴリ＝OrderCategEnum.”現引・現渡注文”（SWAP_MARGIN）の場合、<BR>
     * 　@　@以下の判定を行う。<BR>
     * <BR>
     * 　@４－１）　@現引（買建）が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・現引（買建）（引数.is売建==false）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”DEFAULT”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現引制度信用停止＝”停止中”、かつ、<BR>
     * 　@　@　@　@　@取引銘柄.現引一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現引制度信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現引一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * <BR>
     * 　@４－２）　@現渡（売建）が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・現渡（売建）（引数.is売建==true）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”DEFAULT”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現渡制度信用停止＝”停止中”、かつ、<BR>
     * 　@　@　@　@　@取引銘柄.現渡一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現渡制度信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現渡一般信用停止＝”停止中”であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * @@param l_strRepaymentType 弁済区分。<BR>
     * 0：DEFAULT（指定なし）<BR>
     * 1：制度信用<BR>
     * 2：一般信用<BR>
     * @@param l_orderCategory 注文カテゴリ。（xTradeのOrderCategEnumにて定義）
     * @@param l_isOpenShortMargin (is売建)<BR>
     * 売建／買建のフラグ。<BR>
     * 建株＝売建の場合true、買建の場合falseを指定する。<BR>
     * @@return boolean
     * @@roseuid 40AAFC1B002D
     */
    public boolean isMarginTradeControl(
        String l_strRepaymentType,
        OrderCategEnum l_orderCategory,
        boolean l_isOpenShortMargin)
    {
        final String STR_METHOD_NAME = "isTransferSkipProduct()";
        log.entering(STR_METHOD_NAME);
        boolean l_isMarginTradeControl = false;
        //引数.注文カテゴリが以下のいずれでもない場合は、例外をthrowする。<BR>
        //OrderCategEnum.”新規建注文”（OPEN_MARGIN）<BR>
        //OrderCategEnum.”返済注文”（CLOSE_MARGIN）<BR>
        //OrderCategEnum.”現引・現渡注文”（SWAP_MARGIN）
        EqtypeTradedProductParams l_TradedProductParams =
            (EqtypeTradedProductParams)this.getDataSourceObject();
        if (l_orderCategory.equals(OrderCategEnum.OPEN_MARGIN)
            || l_orderCategory.equals(OrderCategEnum.CLOSE_MARGIN)
            || l_orderCategory.equals(OrderCategEnum.SWAP_MARGIN))
        {
            if (l_orderCategory.equals(OrderCategEnum.OPEN_MARGIN))
            {
                //買建（引数.is売建==false）の場合
                if (l_isOpenShortMargin == false)
                {
                    //－引数.弁済区分＝”DEFAULT”の場合、<BR>
                    //取引銘柄.買制度信用停止＝”停止中”、かつ、<BR>
                    //取引銘柄.買一般信用停止＝”停止中”であれば、trueを返す。<BR>
                    //以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongMarginSysStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.買制度信用停止＝”停止中”であれば、trueを返す。<BR>
                    //以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }

                    //－ 引数.弁済区分 ＝ ” 一般信用 ” の場合 、
                    //取引銘柄.買一般信用停止 ＝ ” 停止中 ” であれば 、 trueを返す 。
                    // 以外 、 falseを返す 。
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
                //２－２）　@売建が規制されていないか判定する。
                else
                {
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortMarginSysStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }

                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(
                                    l_TradedProductParams.getShortMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }

                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
            }
            if (l_orderCategory.equals(OrderCategEnum.CLOSE_MARGIN))
            {
                //３－１）　@買建が規制されていないか判定する。
                if (l_isOpenShortMargin == false)
                {
                    //・買建（引数.is売建==false）の場合
                    //－引数.弁済区分＝”DEFAULT”の場合、
                    //取引銘柄.買建返済（売返済）制度信用停止＝”停止中”、かつ、
                    //取引銘柄.買建返済（売返済）一般信用停止＝”停止中”であれば、trueを返す。
                    //以外、falseを返す。
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongCloseMarginGenStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongCloseMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //－引数.弁済区分＝”制度信用”の場合、
                    //取引銘柄.買建返済（売返済）制度信用停止＝”停止中”であれば、trueを返す。
                    //以外、falseを返す。
                    if (WEB3GentradeRepaymentDivDef
                        .REPAYMENT_DIV_MARGIN_SYS
                        .equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongCloseMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.買建返済（売返済）一般信用停止＝”停止中”であれば、trueを返す。
                    //以外、falseを返す。
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongCloseMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
                //３－２）　@売建が規制されていないか判定する。<BR>
                //・売建（引数.is売建==true）の場合<BR>
                else
                {
                    //－引数.弁済区分＝”DEFAULT”の場合、<BR>
                    //取引銘柄.売建返済（買返済）制度信用停止＝”停止中”、かつ、<BR>
                    //取引銘柄.売建返済（買返済）一般信用停止＝”停止中”であれば、trueを返す。<BR>
                    //以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortCloseMarginGenStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortCloseMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.売建返済（買返済）制度信用停止＝”停止中”であれば、trueを返す。<BR>
                    //以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef
                        .REPAYMENT_DIV_MARGIN_SYS
                        .equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortCloseMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.売建返済（買返済）一般信用停止＝”停止中”であれば、trueを返す。<BR>
                    //以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortCloseMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
            }
            if (l_orderCategory.equals(OrderCategEnum.SWAP_MARGIN))
            {
                //４－１）　@現引（買建）が規制されていないか判定する。<BR>
                //・現引（買建）（引数.is売建==false）の場合<BR>
                if (l_isOpenShortMargin == false)
                {
                    //－引数.弁済区分＝”DEFAULT”の場合、<BR>
                    //    取引銘柄.現引制度信用停止＝”停止中”、かつ、<BR>
                    //    取引銘柄.現引一般信用停止＝”停止中”であれば、trueを返す。<BR>
                    //    以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongSwapMarginGenStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getLongSwapMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //    取引銘柄.現引制度信用停止＝”停止中”であれば、trueを返す。<BR>
                    //    以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongSwapMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //    取引銘柄.現引一般信用停止＝”停止中”であれば、trueを返す。<BR>
                    //    以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongSwapMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }
                //４－２）　@現渡（売建）が規制されていないか判定する。<BR>
                //  ・現渡（売建）（引数.is売建==true）の場合<BR>
                else
                {
                    //－引数.弁済区分＝”DEFAULT”の場合、<BR>
                    //    取引銘柄.現渡制度信用停止＝”停止中”、かつ、<BR>
                    //    取引銘柄.現渡一般信用停止＝”停止中”であれば、trueを返す。<BR>
                    //    以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.DEFAULT.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortSwapMarginGenStop()))
                            || WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortSwapMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                    //    －引数.弁済区分＝”制度信用”の場合、<BR>
                    //    取引銘柄.現渡制度信用停止＝”停止中”であれば、trueを返す。<BR>
                    //    以外、falseを返す。<BR>

                    if (WEB3GentradeRepaymentDivDef
                        .REPAYMENT_DIV_MARGIN_SYS
                        .equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortSwapMarginSysStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    } //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //    取引銘柄.現渡一般信用停止＝”停止中”であれば、trueを返す。<BR>
                    //    以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortSwapMarginGenStop())))
                        {
                            l_isMarginTradeControl = false;
                        }
                        else
                        {
                            l_isMarginTradeControl = true;
                        }
                    }
                }

            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_isMarginTradeControl;
    }

    /**
     * （is信用成行指定規制）。<BR>
     * <BR>
     * 取引銘柄に、信用取引の成行注文規制がかかっていないかを、<BR>
     * 注文カテゴリ＋弁済区分別にチェックする。<BR>
     * <BR>
     * １）　@引数.注文カテゴリが以下のいずれでもない場合は、例外をthrowする。<BR>
     * <BR>
     * 　@　@　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）<BR>
     * 　@　@　@OrderCategEnum.”返済注文”（CLOSE_MARGIN）<BR>
     * 　@　@　@OrderCategEnum.”現引・現渡注文”（SWAP_MARGIN）<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00653<BR>
     * <BR>
     * ２）　@引数.注文カテゴリ＝OrderCategEnum.”現引・現渡注文”（<BR>SWAP_MARGIN）
     * の場合、<BR>
     * 　@　@　@falseを返す。<BR>
     * <BR>
     * ３）　@新規建成行注文が規制されていないかを判定する。<BR>
     * <BR>
     * 　@　@引数.注文カテゴリ＝OrderCategEnum.”新規建注文”<BR>
     * （OPEN_MARGIN）の場合、<BR>
     * 　@　@以下の判定を行う。<BR>
     * <BR>
     * 　@３－１）　@買建が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・買建（引数.is売建==false）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買制度信用成行指定停止＝”停止中”<BR>
     * であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買一般信用成行指定停止＝”停止中”であれば、<BR>
     * trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * <BR>
     * 　@３－２）　@売建が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・売建（引数.is売建==true）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売制度信用成行指定停止＝”停止中”<BR>
     * であれば、trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売一般信用成行指定停止＝”停止中”であれば、<BR>
     * trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * <BR>
     * ４）　@返済成行注文が規制されていないかを判定する。<BR>
     * <BR>
     * 　@　@引数.注文カテゴリ＝OrderCategEnum.”返済注文”<BR>
     * （CLOSE_MARGIN）の場合、<BR>
     * 　@　@以下の判定を行う。<BR>
     * <BR>
     * 　@４－１）　@買建が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・買建（引数.is売建==false）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買建返済（売返済）制度信用成行指定停止＝”停止中”であれば、<BR>
     * 　@　@　@　@　@trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買建返済（売返済）一般信用成行指定停止＝”停止中”であれば、<BR>
     * 　@　@　@　@　@trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * <BR>
     * 　@４－２）　@売建が規制されていないか判定する。<BR>
     * <BR>
     * 　@　@・売建（引数.is売建==true）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売建返済（買返済）制度信用成行指定停止＝”停止中”であれば、<BR>
     * 　@　@　@　@　@trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売建返済（買返済）一般信用成行指定停止＝”停止中”であれば、<BR>
     * 　@　@　@　@　@trueを返す。<BR>
     * 　@　@　@　@　@以外、falseを返す。<BR>
     * @@param l_strRepaymentType 弁済区分。<BR>
     * 0：DEFAULT（指定なし）<BR>
     * 1：制度信用<BR>
     * 2：一般信用<BR>
     * @@param l_orderCategory 注文カテゴリ。（xTradeのOrderCategEnumにて定義）
     * @@param l_isOpenShortMargin (is売建)<BR>
     * 売建／買建のフラグ。<BR>
     * 建株＝売建の場合true、買建の場合falseを指定する。<BR>
     * @@return boolean
     * @@roseuid 40AAFC1B002F
     */
    public boolean isMarginMarketOrderDesignateCtrl(
        String l_strRepaymentType,
        OrderCategEnum l_orderCategory,
        boolean l_isOpenShortMargin)
    {
        final String STR_METHOD_NAME = "isMarginMarketOrderDesignateCtrl()";
        log.entering(STR_METHOD_NAME);
        boolean l_isMarginMarketOrderDesignateCtrl = false;
        //引数.注文カテゴリが以下のいずれでもない場合は、例外をthrowする。<BR>
        //OrderCategEnum.”新規建注文”（OPEN_MARGIN）<BR>
        //OrderCategEnum.”返済注文”（CLOSE_MARGIN）<BR>
        //OrderCategEnum.”現引・現渡注文”（SWAP_MARGIN）
        EqtypeTradedProductParams l_TradedProductParams =
            (EqtypeTradedProductParams)this.getDataSourceObject();
        if (l_orderCategory.equals(OrderCategEnum.OPEN_MARGIN)
            || l_orderCategory.equals(OrderCategEnum.CLOSE_MARGIN)
            || l_orderCategory.equals(OrderCategEnum.SWAP_MARGIN))
        {
            if (l_orderCategory.equals(OrderCategEnum.OPEN_MARGIN))
            {
                //買建（引数.is売建==false）の場合
                if (l_isOpenShortMargin == false)
                {

                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.買制度信用成行指定停止＝”停止中”<BR>
                    //であれば、trueを返す。<BR>
                    //以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongMsMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }

                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    // 取引銘柄.買一般信用成行指定停止＝”停止中”であれば、<BR>
                    // trueを返す.以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongMgMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                }
                //・売建（引数.is売建==true）の場合<BR>
                else
                {
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.売制度信用成行指定停止＝”停止中”<BR>
                    //であれば、trueを返す。<BR>
                    //以外、falseを返す。<BR>                   
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE.equals(
                                Integer.toString(l_TradedProductParams.getShortMsMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.売一般信用成行指定停止＝”停止中”であれば、<BR>
                    //trueを返す。以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortMgMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                }
            }

            if (l_orderCategory.equals(OrderCategEnum.CLOSE_MARGIN))
            {
                //・買建（引数.is売建==false）の場合<BR>
                if (l_isOpenShortMargin == false)
                {
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.買建返済（売返済）制度信用成行指定停止＝”停止中”であれば、<BR>
                    //trueを返す。<BR>
                    //以外、falseを返す。<BR>                   
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongCmsMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.買建返済（売返済）一般信用成行指定停止＝”停止中”であれば、<BR>
                    //trueを返す。<BR>
                    //以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getLongCmgMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                }
                //売建が規制されていないか判定する。<BR>
                //・売建（引数.is売建==true）の場合<BR>
                else
                {
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.売建返済（買返済）制度信用成行指定停止＝”停止中”であれば、<BR>
                    //trueを返す。<BR>
                    //以外、falseを返す。<BR>               
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortCmsMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.売建返済（買返済）一般信用成行指定停止＝”停止中”であれば、<BR>
                    //trueを返す。<BR>
                    //以外、falseを返す。<BR>
                    if (WEB3GentradeRepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        if (WEB3TradeStopDef.ACTIVE
                            .equals(Integer.toString(l_TradedProductParams.getShortCmgMarketOrdDesStop())))
                        {
                            l_isMarginMarketOrderDesignateCtrl = false;
                        }
                        else
                        {
                            l_isMarginMarketOrderDesignateCtrl = true;
                        }
                    }
                }
            }
            if (l_orderCategory.equals(OrderCategEnum.SWAP_MARGIN))
            {
                l_isMarginMarketOrderDesignateCtrl = false;
            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_isMarginMarketOrderDesignateCtrl;
    }

    /**
     * （get信用売買規制停止）。<BR>
     * <BR>
     * 取引銘柄の、引数の弁済区分、注文カテゴリ、<BR>
     * s売注文に該当する売買停止項目の値を返す。<BR>
     * <BR>
     * １）　@引数.注文カテゴリが以下のいずれでもない場合は、例外をthrowする。<BR>
     * <BR>
     * 　@　@　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）<BR>
     * 　@　@　@OrderCategEnum.”返済注文”（CLOSE_MARGIN）<BR>
     * 　@　@　@OrderCategEnum.”現引・現渡注文”（SWAP_MARGIN）<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00653<BR>
     * <BR>
     * ２）　@新規建注文<BR>
     * <BR>
     * 　@　@引数.注文カテゴリ＝OrderCategEnum.”新規建注文”<BR>
     * （OPEN_MARGIN）の場合は、<BR>
     * 　@　@以下の値を返す。<BR>
     * <BR>
     * 　@　@・買建（引数.is売建==false）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買制度信用停止 を返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買一般信用停止 を返す。<BR>
     * <BR>
     * 　@　@・売建（引数.is売建==true）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売制度信用停止 を返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売一般信用停止 を返す。<BR>
     * <BR>
     * ３）　@返済注文<BR>
     * <BR>
     * 　@　@引数.注文カテゴリ＝OrderCategEnum.”返済注文”<BR>
     * （CLOSE_MARGIN）の場合は、<BR>
     * 　@　@以下の値を返す。<BR>
     * <BR>
     * 　@　@・買建（引数.is売建==false）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買建返済（売返済）制度信用停止 を返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.買建返済（売返済）一般信用停止 を返す。<BR>
     * <BR>
     * 　@　@・売建（引数.is売建==true）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売建返済（買返済）制度信用停止 を返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.売建返済（買返済）一般信用停止 を返す。<BR>
     * <BR>
     * ４）　@現引現渡注文<BR>
     * <BR>
     * 　@　@引数.注文カテゴリ＝OrderCategEnum.”現引・現渡注文”<BR>
     * （SWAP_MARGIN）の場合は、<BR>
     * 　@　@以下の値を返す。<BR>
     * <BR>
     * 　@　@・買建（引数.is売建==false）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現引制度信用停止 を返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現引一般信用停止 を返す。<BR>
     * <BR>
     * 　@　@・売建（引数.is売建==true）の場合<BR>
     * 　@　@　@－引数.弁済区分＝”制度信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現渡制度信用停止 を返す。<BR>
     * 　@　@　@－引数.弁済区分＝”一般信用”の場合、<BR>
     * 　@　@　@　@　@取引銘柄.現渡一般信用停止 を返す。<BR>
     * @@param l_strRepaymentType 弁済区分。<BR>
     * 0：DEFAULT（指定なし）<BR>
     * 1：制度信用<BR>
     * 2：一般信用<BR>
     * @@param l_orderCategory 注文カテゴリ。（xTradeのOrderCategEnumにて定義）
     * @@param l_isOpenShortMargin (is売建)<BR>
     * 売建／買建のフラグ。<BR>
     * 建株＝売建の場合true、買建の場合falseを指定する。<BR>
     * @@return String
     * @@roseuid 40B44ABA014F
     */
    public String getMarginTradeControlStop(
        String l_strRepaymentType,
        OrderCategEnum l_orderCategory,
        boolean l_isOpenShortMargin)
        throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME =
            "getMarginTradeControlStop(String l_strRepaymentType, OrderCategEnum l_orderCategory, boolean l_isOpenShortMargin)";
        log.entering(STR_METHOD_NAME);
        EqtypeTradedProductParams l_TradedProductParams =
            (EqtypeTradedProductParams)this.getDataSourceObject();
        long l_marginTradeControlStop = 0;
        if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCategory)
            || OrderCategEnum.CLOSE_MARGIN.equals(l_orderCategory)
            || OrderCategEnum.SWAP_MARGIN.equals(l_orderCategory))
        {
            if (OrderCategEnum.OPEN_MARGIN.equals(l_orderCategory))
            {
                //・売建（引数.is売建==true）の場合<BR>
                if (l_isOpenShortMargin == true)
                {
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.売一般信用停止 を返す。                   
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortMarginGenStop();
                    }
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.売制度信用停止 を返す。<BR>                   
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortMarginSysStop();
                    }
                }
                //・買建（引数.is売建==false）の場合<BR>
                else
                {
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.買制度信用停止 を返す。<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongMarginSysStop();
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.買一般信用停止 を返す。<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongMarginGenStop();
                    }
                }
            }

            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCategory))
            {
                //・買建（引数.is売建==false）の場合<BR>
                if (l_isOpenShortMargin == false)
                {
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.買建返済（売返済）制度信用停止 を返す。
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongCloseMarginSysStop();
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.買建返済（売返済）一般信用停止 を返す。<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongCloseMarginGenStop();
                    }

                }
                else
                {
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.売建返済（買返済）制度信用停止 を返す。
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortCloseMarginSysStop();
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.売建返済（買返済）一般信用停止 を返す。<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortCloseMarginGenStop();
                    }
                }
            }
            if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCategory))
            {
                //・買建（引数.is売建==false）の場合<BR>
                if (l_isOpenShortMargin == false)
                {
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.現引制度信用停止 を返す。<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongSwapMarginSysStop();
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.現引一般信用停止 を返す。<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getLongSwapMarginGenStop();
                    }
                }
                else
                {
                    //－引数.弁済区分＝”制度信用”の場合、<BR>
                    //取引銘柄.現渡制度信用停止 を返す。<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortSwapMarginSysStop();
                    }
                    //－引数.弁済区分＝”一般信用”の場合、<BR>
                    //取引銘柄.現渡一般信用停止 を返す。<BR>
                    if (WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_strRepaymentType))
                    {
                        l_marginTradeControlStop = l_TradedProductParams.getShortSwapMarginGenStop();
                    }
                }
            }
        }
        else
        {
            log.error(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                STR_METHOD_NAME);
        }
        return Long.toString(l_marginTradeControlStop);
    }

    /**
     * （isミニ株売買規制）。<BR>
     * <BR>
     * 取引銘柄に、現物株式の売買規制がかかっていないかをチェックする。<BR>                                                     
     * <BR>                                                                                                                     
     * １）　@売注文が規制されていないか判定する。 <BR>                                  
     * 売注文の場合（引数.is売注文==true）、<BR>                                  
     * （this.取引銘柄行.売ミニ株停止 == ”停止中”）の場合、trueを返却する。<BR> 
     * 以外、falseを返却する。<BR>                                                              
     * <BR>                                                                                       
     * ２）　@買注文が規制されていないかチェックする。<BR>                                                                                                                             
     * 買注文の場合（引数.is売注文==false）、<BR>                                                                                                                                            
     * （this.取引銘柄行.買ミニ株停止 == ”停止中”）の場合、trueを返却する。<BR>                                                                                                           
     * 以外、falseを返却する。<BR> 
     * @@param l_blnIsSellOrder (is売注文)<BR>
     * @@return boolean
     */
    public boolean isMiniStockTradeControl(boolean l_blnIsSellOrder)
    {

        final String STR_METHOD_NAME ="isMiniStockTradeControl(boolean)";
        log.entering(STR_METHOD_NAME);
                
        EqtypeTradedProductParams l_tradedProductParams = (EqtypeTradedProductParams)this.getDataSourceObject();
        String l_strSellMiniStockStop = "" + l_tradedProductParams.getSellMiniStockStop();
        String l_strBuyMiniStockStop = "" + l_tradedProductParams.getBuyMiniStockStop();
        if((l_blnIsSellOrder && !(WEB3TradeStopDef.ACTIVE.equals(l_strSellMiniStockStop)))
            || (!l_blnIsSellOrder && !(WEB3TradeStopDef.ACTIVE.equals(l_strBuyMiniStockStop))))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    /**
     * （get売買単位）。<BR>
     * <BR>
     * 株式売買単位を取得する。 <BR>
     * this.取引銘柄行.売買単位を返却する<BR>
     * @@return double
     */
    public double getLotSize()
    {
        EqtypeTradedProductParams l_tradedProductParams = (EqtypeTradedProductParams)this.getDataSourceObject();
        double l_dblLotSize;
        
        l_dblLotSize = l_tradedProductParams.getLotSize();
        
        return l_dblLotSize;
    }
    /**
     * （getミニ株売買単位）。<BR>
     * ミニ株売買単位を取得する。<BR> 
     * （this.取引銘柄行.売買単位 ÷ 10）を返却する。<BR>
     * @@return double
     */
    public double getMiniStockLotSize()
    {
        EqtypeTradedProductParams l_tradedProductParams = (EqtypeTradedProductParams)this.getDataSourceObject();
        double l_dblMiniLotSize;
        
        l_dblMiniLotSize = l_tradedProductParams.getLotSize()/10;
                
        return l_dblMiniLotSize;
    }

    /**
     * （get時価情報）。<BR>
     * <BR>
     * 時価情報（時価取得区分、時価、時価発表時間）を取得する。<BR>
     * 時価情報を取得できなかった場合は、nullを返す。<BR>
     * <BR>
     * シーケンス図「（取引銘柄）get時価情報」参照。<BR>
     * <BR>
     * @@param l_subAccount (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@return WEB3EquityProductQuote
     */
    public WEB3EquityProductQuote getProductQuote(
        WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME ="getProductQuote(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityProductQuote l_productQuote = null;
        
        // 1.1. 上場区分＝"非上場"の場合は、「指定市場は非上場」の例外をthrowする。
        EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)this.getDataSourceObject();
        if (WEB3ListTypeDef.UNLISTING.equals(l_tradedProductRow.getListType()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01367,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 拡張プロダクトマネージャ取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
            
        boolean l_isMarketOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        // 引け後の場合
        if (l_isMarketOpenTimeZone == false)
        {
            // 発注日を取得
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            
            // 終値レコードを取得
            LastClosingPriceParams l_lastClosingPriceParams =
                l_productManager.getLastClosingPrice(
                    this.getProduct().getProductId(), l_genBizDate.roll(-1));
	        
	        // 1.3. 終値レコードを取得できた場合
	        if (l_lastClosingPriceParams != null)
	        {
                // 優先市場取得
                Market l_primaryMarket = this.getProduct().getPrimaryMarket();
                
                // 優先市場時価、市場コードセット
                double l_dblClosingPrice = l_lastClosingPriceParams.getPrimaryClosingPrice();
                String l_strMarketCode = l_primaryMarket.getMarketCode();
                
                if (l_dblClosingPrice <= 0)
                {
                    // 東証終値、市場コードをセット
	                l_dblClosingPrice = l_lastClosingPriceParams.getTokyoClosingPrice();
	                l_strMarketCode = WEB3MarketCodeDef.TOKYO;
                }
                if (l_dblClosingPrice <= 0)
                {
                    // 大証終値、市場コードをセット
	                l_dblClosingPrice = l_lastClosingPriceParams.getOosakaClosingPrice();
	                l_strMarketCode = WEB3MarketCodeDef.OSAKA;
                }
                if (l_dblClosingPrice <= 0)
                {
                    // 名証終値、市場コードをセット
	                l_dblClosingPrice = l_lastClosingPriceParams.getNagoyaClosingPrice();
	                l_strMarketCode = WEB3MarketCodeDef.NAGOYA;
                }
                if (l_dblClosingPrice <= 0)
                {
                    // その他市場の終値、市場コードをセット
	                l_dblClosingPrice = l_lastClosingPriceParams.getOtherClosingPrice();
	                l_strMarketCode = null;
                }
                
	            // いずれかの市場で値が付いている場合
                if (l_dblClosingPrice > 0)
	            {
                    //終値を取得したのが、その他市場終値以外の場合
                    EqtypeTradedProductParams l_tradedProduct = null;
                    if (l_strMarketCode != null)
                    {
                        //株式銘柄：　@this.getProduct 
                        //市場：　@拡張金融オブジェクトマネージャ.get市場(
                        //    this.getInstitution(),当日終値を取得した市場の市場コード) 
                        //基準日：　@取引時間管理.get発注日( )で取得した発注日の前営業日
                        WEB3GentradeFinObjectManager l_finObjectManager =
                            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                        Market l_market = null;
                        try
                        {
                            l_market = l_finObjectManager.getMarket(this.getInstitution(), l_strMarketCode);
                        }
                        catch(NotFoundException l_ex)
                        {
                            log.error("テーブルに該当するデータがありません。", l_ex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + STR_METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);
                        }
                        l_tradedProduct =
                            l_productManager.getTradedProduct(
                                (WEB3EquityProduct)this.getProduct(),
                                l_market,
                                l_genBizDate.roll(-1));
                    }
	                // 戻り値オブジェクト生成
			        l_productQuote = new WEB3EquityProductQuote();
		        
                    // 時価
		            l_productQuote.setQuote(l_dblClosingPrice);
		            // 前日比
		            if (l_strMarketCode == null)
		            {
			            l_productQuote.setComparedPreviousDay(Double.NaN);
		            }
		            else
                    {
                        if (l_tradedProduct.getLastClosingPrice() == 0.0D)
                        {
	                        l_productQuote.setComparedPreviousDay(Double.NaN);
                        }
                        else
                        {
	                        l_productQuote.setComparedPreviousDay(l_dblClosingPrice - l_tradedProduct.getLastClosingPrice());
                        }
                    }
	                // 時価発表時間
		            l_productQuote.setQuoteTime(null);
		            // 時価取得区分
		            l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.CLOSING_PRICE);
		            // 時価区分
                    l_productQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.CURRENT_PRICE);
                    // 市場コード
		            l_productQuote.setMarketCode(l_strMarketCode);
		            
                    return l_productQuote;
	            }
	        }
        }
        
        WEB3QuoteDataSupplierService l_quoteDataSupplierService =
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getQuoteDataSupplierService();
            
        // 1.4. getEqTypeQuote(tradedProduct : EqTypeTradedProduct , realType : RealType)
        MainAccountRow l_accountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
        WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(l_accountRow);
        RealType l_realType;
        if (l_account.isRealCustomer())
        {
            l_realType = RealType.REAL;
        }
        else
        {
            l_realType = RealType.DELAY;
        }
        WEB3EqTypeQuoteData l_quoteData = null;
        try
        {
            l_quoteData = l_quoteDataSupplierService.getEqTypeQuote(this, l_realType);
        }
        catch (NotFoundException l_exp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コード：[" + this.getInstitution().getInstitutionCode()
                + "] 銘柄コード：[" + this.getProductCode()
                + "] 市場コード：[" + this.getMarketCode()
                + "に対応する時価情報が見つかりません。",
                l_exp);
        }
        // 1.5. 現在値、売気配値、買気配値の順序で価格情報を取得する
        //     （値が付いている（取得した値 > 0）の時点で、以降の価格情報は取得しない）。
        double l_dblMarketPrice = 0.0D;
        Timestamp l_tsQuoteTime = null;
        String l_strQuoteTypeDiv = null;
        // 1.5.1. getCurrentPrice
        l_dblMarketPrice = l_quoteData.getCurrentPrice();
        l_tsQuoteTime = l_quoteData.getCurrentPriceTime();
        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.CURRENT_PRICE;
        
        // 1.5.3. getBidPriceTime
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getBidPrice();
            l_tsQuoteTime = l_quoteData.getBidPriceTime();
	        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.BID_PRICE;
        }
        // 1.5.5. getAskPrice
        if (l_dblMarketPrice <= 0.0D)
        {
            l_dblMarketPrice = l_quoteData.getAskPrice();
            l_tsQuoteTime = l_quoteData.getAskPriceTime();
	        l_strQuoteTypeDiv = WEB3QuoteTypeDivDef.ASK_PRICE;
        }
        // 1.6. 現在値、売気配値、買気配値のいずれかを取得できた場合（取得した値 > 0の場合）
        if (l_dblMarketPrice > 0.0D)
        {
            l_productQuote = new WEB3EquityProductQuote();
            l_productQuote.setQuote(l_dblMarketPrice);
            l_productQuote.setComparedPreviousDay(l_quoteData.getChange());
            l_productQuote.setQuoteTime(l_tsQuoteTime);
            l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.MARKET_PRICE);
            l_productQuote.setQuoteTypeDiv(l_strQuoteTypeDiv);
            l_productQuote.setMarketCode(this.getMarketCode());
        }
        else
        {
            // 1.7. getLastClosingPrice
            double l_dblLastClosingPrice = this.getLastClosingPrice();
            // 1.8. 前日終値を取得できた場合（取得した値 > 0の場合）
            if (l_dblLastClosingPrice > 0.0D)
            {
                l_productQuote = new WEB3EquityProductQuote();
                l_productQuote.setQuote(l_dblLastClosingPrice);
                l_productQuote.setComparedPreviousDay(Double.NaN);
                l_productQuote.setQuoteTime(null);
                l_productQuote.setQuoteFromDiv(WEB3QuoteFromDivDef.LAST_CLOSING_PRICE);
                l_productQuote.setQuoteTypeDiv(WEB3QuoteTypeDivDef.LAST_CLOSING_PRICE);
                l_productQuote.setMarketCode(this.getMarketCode());
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_productQuote;
    }
}
@
