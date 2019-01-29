head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信銘柄クラス(WEB3MutualFundProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/04 于美麗 (中訊) 新規作成
Revesion History : 2004/12/06 于美麗 (中訊) 残対応
Revesion History : 2005/10/18 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2006/03/08 鈴木 (SRA) 仕様変更（モデル）：399
Revesion History : 2006/03/08 玉岡 (SRA) 仕様変更（モデル）：401
Revesion History : 2006/05/15 周捷 (中訊) 仕様変更（モデル）：411
Revesion History : 2006/06/26 肖志偉 (中訊) 仕様変更（モデル）:417
Revesion History : 2006/09/11 周捷 仕様変更・モデル491
Revesion History : 2006/10/12 張騰宇 (中訊) モデル498
Revesion History : 2006/10/25 張騰宇 (中訊) モデル513
Revesion History : 2007/02/05 唐性峰 (中訊) モデル529,538
Revesion History : 2007/04/09 趙林鵬 (中訊) モデル 555
Revesion History : 2007/10/15 孫洪江 (中訊) モデル 583
*/
package webbroker3.mf;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuyLimitDivDef;
import webbroker3.common.define.WEB3FixedBuyPossibleDivDef;
import webbroker3.common.define.WEB3PerferenceMoneyDivDef;
import webbroker3.common.define.WEB3StockTypeBondTypeDef;
import webbroker3.common.define.WEB3SystemHandlingDivDef;
import webbroker3.common.define.WEB3UnitTypeProductDivDef;
import webbroker3.gentrade.data.ExchangeRateDao;
import webbroker3.gentrade.data.ExchangeRateParams;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.FrgnMmfExchangeRateDao;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.mf.define.WEB3MFPlowbackProductDivDef;
import webbroker3.mf.define.WEB3MFProcessDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張投信銘柄クラス
 *
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundProduct extends MutualFundProductImpl
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProduct.class);

    /**
     * (拡張投信銘柄)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * super(銘柄Row)をコールする。<BR>
     * @@param l_productRow - 銘柄Row
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 40AD8EBD03B9
     */
    public WEB3MutualFundProduct(ProductRow l_productRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
    }

    /**
     * (拡張投信銘柄)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * super(投信銘柄Row)をコールする。<BR>
     * @@param l_mutualFundProductRow - 投信銘柄Row
     * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
     * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
     * @@roseuid 40AD8EBD0399
     */
    public WEB3MutualFundProduct(MutualFundProductRow l_mutualFundProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_mutualFundProductRow);
    }

    /**
     * (is乗換可能)<BR>
     * 当銘柄が乗換可能な銘柄であれば true を、そうでなければ false を返す。<BR>
     * <BR>
     * １）　@投信銘柄Paramsオブジェクトの取得。<BR>
     * 　@this.getDataSourceObject()をコールして、<BR>
     * 投信銘柄Paramsオブジェクトを取得する。<BR>
     * <BR>
     * ２）　@投信銘柄Params.get乗換可能グループIDIsNull()の戻り値が true<BR>
     * 　@の場合は falseを、そうでなければ true を返す。<BR>
     * @@return boolean
     * @@roseuid 40ADA4600157
     */
    public boolean isSwitchingAble()
    {
        final String STR_METHOD_NAME = "isSwitchingAble()";
        log.entering(STR_METHOD_NAME);

        //投信銘柄Paramsオブジェクトの取得
        MutualFundProductParams l_mutualFundProductParams =
            new MutualFundProductParams(
                (MutualFundProductRow) this.getDataSourceObject());

        //投信銘柄Params.get乗換可能グループIDIsNull()の戻の判断
        if (l_mutualFundProductParams.getSwtPossibleGroupIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (get乗換可能銘柄リスト)<BR>
     * 乗換可能な銘柄のリストを返す。<BR>
     * <BR>
     * １）　@投信銘柄Paramsオブジェクトの取得。<BR>
     * 　@this.getDataSourceObject()をコールして、<BR>
     * 投信銘柄Paramsオブジェクトを取得する。<BR>
     * <BR>
     * ２）　@投信銘柄Params.get乗換可能グループIDIsNull()の戻り値が true<BR>
     * 　@の場合は null を返す。<BR>
     * <BR>
     * ３）　@投信銘柄Params.get乗換可能グループIDIsNull()の戻り値が false<BR>
     * 　@の場合は、以下の処理を行う。<BR>
     * <BR>
     * 　@３－１）　@投信銘柄マスターテーブルを検索し、<BR>
     * 乗換可能な銘柄の投信銘柄ParamsオブジェクトのListを取得する。<BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@乗換可能グループID = <BR>
     *          投信銘柄Params.get乗換可能グループID()の戻り値 and<BR>
     *　@　@　@ 証券会社コード = 投信銘柄Params.get証券会社コード()の戻り値　@and <BR>
     * 　@　@　@AND 銘柄ID != 投信銘柄Params.get銘柄ID()の戻り値 and<BR>
     * 　@　@　@再投資銘柄区分 = 投信銘柄Params.get再投資銘柄区分()の戻り値 and<BR>
     * 　@　@　@買付開始日 <= 現在日時<BR>
     * 　@　@［ソート条件］<BR>
     * 　@　@　@表示順位の昇順＞銘柄コードの昇順 <BR>
     * <BR>
     * 　@３－２）　@投信銘柄ParamsオブジェクトのListの件数分以下の処理を行う。<BR>
     * 　@　@－拡張投信銘柄オブジェクトを生成する。<BR>
     * 　@　@　@［コンストラクタに渡すパラメタ］<BR>
     * 　@　@　@　@銘柄Row： 投信銘柄Params<BR>
     * 　@　@－生成した拡張投信銘柄.isシステム取扱()をコールする。<BR>
     * 　@　@　@isシステム取扱()が false を返す場合は以下の処理をおこなわない。<BR>
     * 　@　@　@チェックエラーの場合は以下の処理を行わない。<BR>
     * 　@　@－生成した拡張投信銘柄を、拡張投信銘柄のListに追加する。<BR>
     * <BR>
     * 　@３－３）　@拡張投信銘柄のListをリターンする。<BR>
     * <BR>
     * 　@　@　@
     * @@return List
     * @@roseuid 40AD9EFD0251
     */
    public List getSwitchingAbleProductList()
                 throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getSwitchingAbleProductList(Timestamp l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        List rtnList = new Vector();

        //１）　@投信銘柄Paramsオブジェクトの取得。
        MutualFundProductParams l_mutualFundProductParams =
            new MutualFundProductParams(
                (MutualFundProductRow) this.getDataSourceObject());

        //２）　@投信銘柄Params.get乗換可能グループIDIsNull()
        if (l_mutualFundProductParams.getSwtPossibleGroupIdIsNull())
        {
            return null;
        }
        else
        {
            //３）　@投信銘柄Params.get乗換可能グループIDIsNull()の戻り値が falseの場合
            String l_whereClause =
                " swt_possible_group_id = ? and institution_code = ? and product_id <> ? " +
                " and plowback_product_div = ? and buy_start_date <= ? ";

            //現在日付の取得
            //ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。
            Timestamp l_tmsSystemTimestamp =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

            Object l_bindVars[] =
                {
                    new Long(l_mutualFundProductParams.getSwtPossibleGroupId()),
                    l_mutualFundProductParams.getInstitutionCode(),
                    new Long(l_mutualFundProductParams.getProductId()),
                    l_mutualFundProductParams.getPlowbackProductDiv(),
                    l_tmsSystemTimestamp
                };

            String l_strSort = " indication_ranking asc, product_code asc";

            List l_lisRows = new Vector();

            try
            {
                //３－１）　@投信銘柄マスターテーブルを検索し
                QueryProcessor l_QueryProcessor =
                    Processors.getDefaultProcessor();
                l_lisRows =
                    l_QueryProcessor.doFindAllQuery(
                        MutualFundProductRow.TYPE,
                        l_whereClause,
                        l_strSort,
                        null,
                        l_bindVars);
                if (l_lisRows != null && l_lisRows.size() != 0)
                {
                    //３－２）　@投信銘柄ParamsオブジェクトのListの件数分以下の処理を行う
                    int n = l_lisRows.size();
                    WEB3MutualFundOrderManagerReusableValidationsCheck l_mfOrderManagerCheck =
                        (WEB3MutualFundOrderManagerReusableValidationsCheck)MutualFundProductTypeOrderManagerReusableValidations.getInstance();
                    for (int i = 0; i < n; i++)
                    {
                        //－拡張投信銘柄オブジェクトを生成する
                        MutualFundProductParams l_mutualFundProductParamsTemp =
                            new MutualFundProductParams(
                                (MutualFundProductRow) l_lisRows.get(i));
                        WEB3MutualFundProduct l_web3MutualFundProduct =
                            new WEB3MutualFundProduct(l_mutualFundProductParamsTemp);
                        log.debug("ProductCode" + i + " = " + l_web3MutualFundProduct.getProductCode());

                        //－生成した拡張投信銘柄.isシステム取扱()をコールする
                        if (l_web3MutualFundProduct.isSystemHandling())
                        {
                            //－生成した拡張投信銘柄を、拡張投信銘柄のListに追加する
                            rtnList.add(l_web3MutualFundProduct);
                        }
                    }
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error(" DBへのアクセスに失敗しました when search table MutualFundProduct");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error(" DBへのアクセスに失敗しました when search table MutualFundProduct");
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);

            }
            finally
            {
                log.exiting(STR_METHOD_NAME);
            }
        }

        //３－３）　@拡張投信銘柄のListをリターンする
        return rtnList;
    }

    /**
     * (get銘柄名)<BR>
     * 銘柄名を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get銘柄名()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40AD9CF7035B
     */
    public String getMutualProductName()
    {
        String l_strMutualProductName =
            ((MutualFundProductRow) this.getDataSourceObject()).getStandardName();
        return l_strMutualProductName;
    }

    /**
     * (get買付基準価額)<BR>
     * 買付基準価額を返す。<BR>
     * <BR>
     *    this.getDataSourceObject().get買付基準価額()の戻り値を返す。<BR>
     * <BR>
     * @@return double
     * @@roseuid 40AD9D3C00CB
     */
    public double getConstantValue()
    {
        double l_dblConstantValue =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyConstantValue();

        return l_dblConstantValue;
    }

    /**
     * (get解約価額)<BR>
     * 解約価額を返す。<BR>
     * <BR>
     * 　@this.getDataSourceObject().get解約価額()の戻り値を返却する。 <BR>
     * <BR>
     * @@return double
     * @@roseuid 40AD9D6F009C
     */
    public double getSellValue()
    {
        double l_dblSellValue =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellConstantValue();

        return l_dblSellValue;
    }

    /**
     * (get通貨コード)<BR>
     * 通貨コードを返す。<BR>
     * <BR>
     * this.getDataSourceObject().get通貨コード()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40AD9EBB036A
     */
    public String getCurrencyCode()
    {
        String l_strCurrencyCode =
            ((MutualFundProductRow) this.getDataSourceObject()).getCurrencyCode();
        return l_strCurrencyCode;
    }

    /**
     * (get基準価額計算単位)<BR>
     * 基準価額計算単位を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get基準価額計算単位()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 40ADB1C00213
     */
    public double getConstantValueCalcUnit()
    {
        double l_dblgetConstantValueCalcUnit =
            ((MutualFundProductRow) this.getDataSourceObject()).getConstantValueCalcUnit();
        return l_dblgetConstantValueCalcUnit;
    }

    /**
     * (get決済（買付）)<BR>
     * 決済（買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get決済（買付）()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB26D0128
     */
    public String getAcquiredSettlement()
    {
        String l_strAcquiredSettlement =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuySettlementDiv();
        return l_strAcquiredSettlement;
    }

    /**
     * (get決済（解約）)<BR>
     * 決済（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get決済（解約）()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB28F008C
     */
    public String getSellSettlement()
    {
        String l_strSellSettlement =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellSettlementDiv();
        return l_strSellSettlement;
    }

    /**
     * (get受渡方法@)<BR>
     * 受渡方法@を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get受渡方法@()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB39203D8
     */
    public String getDeliveryDiv()
    {
        String l_strDeliveryDiv =
            ((MutualFundProductRow) this.getDataSourceObject()).getDeliveryMethod();
        return l_strDeliveryDiv;
    }

    /**
     * (get指定方法@（買付）)<BR>
     * 指定方法@（買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get指定方法@（買付）()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB3BA034B
     */
    public String getBuySelectable()
    {
        String l_strBuySelectable =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuySpecityDiv();
        return l_strBuySelectable;
    }

    /**
     * (get指定方法@（解約）)<BR>
     * 指定方法@（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get指定方法@（解約）()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB3DD0138
     */
    public String getSellSelectable()
    {
        String l_strSellSelectable =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellSpecifyDiv();
        return l_strSellSelectable;
    }

    /**
     * (get指定方法@（乗換）)<BR>
     * 指定方法@（乗換）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get指定方法@（乗換）()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB3F9003E
     */
    public String getSwitchingSelectable()
    {
        String l_strSwitchingSelectable =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtSpecifyDiv();
        return l_strSwitchingSelectable;
    }

//    /**
//     * (get請求方法@)<BR>
//     * 請求方法@を返す。<BR>
//     * <BR>
//     * this.getDataSourceObject().get請求方法@()の戻り値を返す。<BR>
//     * @@return String
//     * @@roseuid 40ADB41E02FD
//     */
//    public String getSellBuyDiv()
//    {
//        String l_strSellBuyDiv =
//            ((MutualFundProductRow) this.getDataSourceObject()).getDeliveryMethod();
//        return l_strSellBuyDiv;
//    }

    /**
     * (get株式型・債券型)<BR>
     * 株式型・債券型を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get株式型・債券型()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB44D033C
     */
    public String getStockTypeBondType()
    {
        String l_strStockTypeBondType =
            ((MutualFundProductRow) this.getDataSourceObject()).getStockTypeBondType();
        return l_strStockTypeBondType;
    }

    /**
     * (get契約型・会社型)<BR>
     * 契約型・会社型を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get契約型・会社型()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB4730399
     */
    public String getContractTypeCompanyType()
    {
        String l_strContractTypeCompanyType =
            ((MutualFundProductRow) this.getDataSourceObject()).getContractInstitutionType();
        return l_strContractTypeCompanyType;
    }

    /**
     * (is乗換優遇対象)<BR>
     * 当銘柄が乗換優遇対象かどうかを判定する。<BR>
     * <BR>
     * this.getDataSourceObject().get優遇金区分()の戻り値が”0：償還優遇不可”<BR>
     * の場合は false を、それ以外の場合は true を返す。<BR>
     * @@return boolean
     * @@roseuid 40ADB49C00DA
     */
    public boolean isSwitchingPerferenceObject()
    {
        final String STR_METHOD_NAME = "isSwitchingPerferenceObject()";
        log.entering(STR_METHOD_NAME);

        if (WEB3PerferenceMoneyDivDef.REDEMPTION_PREFERENCE_IMPOSSIBLE.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getPerferenceMoneyDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (get入力単位)<BR>
     * 入力単位を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get入力単位()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40ADB54D0222
     */
    public String getInputUnit()
    {
        String l_strInputUnit =
            ((MutualFundProductRow) this.getDataSourceObject()).getInputUnit();
        return l_strInputUnit;
    }

    /**
     * (get最低口数（新規買付）)<BR>
     * 最低口数（新規買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低口数（新規買付）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB56F02BF
     */
    public long getNewBuyMinQty()
    {
        long l_lngNewBuyMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getNewBuyMinQty();
        return l_lngNewBuyMinQty;
    }

    /**
     * (get最低口数（追加買付）)<BR>
     * 最低口数（追加買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低口数（追加買付）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB59B0399
     */
    public long getAddBuyMinQty()
    {
        long l_lngAddBuyMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getAddBuyMinQty();
        return l_lngAddBuyMinQty;
    }

    /**
     * (get最低口数（解約）)<BR>
     * 最低口数（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低口数（解約）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB59E02CE
     */
    public long getSellMinQty()
    {
        long l_lngSellMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellMinQty();
        return l_lngSellMinQty;
    }

    /**
     * (get最低口数（乗換）)<BR>
     * 最低口数（乗換）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低口数（乗換）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB5A1009C
     */
    public long getSwitchingMinQty()
    {
        long l_lngSwitchingMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtMinQty();
        return l_lngSwitchingMinQty;
    }

    /**
     * (get単位口数（新規買付）)<BR>
     * 単位口数（新規買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位口数（新規買付）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB6150222
     */
    public long getNewBuyUnitQty()
    {
        long l_lngNewBuyUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getNewBuyUnitQty();
        return l_lngNewBuyUnitQty;
    }

    /**
     * (get単位口数（追加買付）)<BR>
     * 単位口数（追加買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位口数（追加買付）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB6150223
     */
    public long getAddBuyUnitQty()
    {
        long l_lngAddBuyUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getAddBuyUnitQty();
        return l_lngAddBuyUnitQty;
    }

    /**
     * (get単位口数（解約）)<BR>
     * 単位口数（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位口数（解約）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB6150232
     */
    public long getSellUnitQty()
    {
        long l_lngSellUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellUnitQty();
        return l_lngSellUnitQty;
    }

    /**
     * (get単位口数（乗換）)<BR>
     * 単位口数（乗換）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位口数（乗換）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB6150233
     */
    public long getSwitchingUnitQty()
    {
        long l_lngSwitchingUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtUnitQty();
        return l_lngSwitchingUnitQty;
    }

    /**
     * (get最低金額（新規買付）)<BR>
     * 最低金額（新規買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低金額（新規買付）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB67C0261
     */
    public long getNewBuyMinAmt()
    {
        long l_lngNewBuyMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getNewBuyMinAmt();
        return l_lngNewBuyMinAmt;
    }

    /**
     * (get最低金額（追加買付）)<BR>
     * 最低金額（追加買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低金額（追加買付）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB67C0262
     */
    public long getAddBuyMinAmt()
    {
        long l_lngAddBuyMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getAddBuyMinAmt();
        return l_lngAddBuyMinAmt;
    }

    /**
     * (get最低金額（解約）)<BR>
     * 最低金額（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低金額（解約）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB67C0263
     */
    public long getSellMinAmt()
    {
        long l_lngSellMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellMinAmt();
        return l_lngSellMinAmt;
    }

    /**
     * (get最低金額（乗換）)<BR>
     * 最低金額（乗換）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低金額（乗換）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB67C0270
     */
    public long getSwitchingMinAmt()
    {
        long l_lngSwitchingMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtMinAmt();
        return l_lngSwitchingMinAmt;
    }

    /**
     * (get単位金額（新規買付）)<BR>
     * 単位金額（新規買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位金額（新規買付）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB67C0271
     */
    public long getNewBuyUnitAmt()
    {
        long l_lngNewBuyUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getNewBuyUnitAmt();
        return l_lngNewBuyUnitAmt;
    }

    /**
     * (get単位金額（追加買付）)<BR>
     * 単位金額（追加買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位金額（追加買付）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB67C0272
     */
    public long getAddBuyUnitAmt()
    {
        long l_lngAddBuyUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getAddBuyUnitAmt();
        return l_lngAddBuyUnitAmt;
    }

    /**
     * (get単位金額（解約）)<BR>
     * 単位金額（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位金額（解約）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB67C0280
     */
    public long getSellUnitAmt()
    {
        long l_lngSellUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellUnitAmt();
        return l_lngSellUnitAmt;
    }

    /**
     * (get単位金額（乗換）)<BR>
     * 単位金額（乗換）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位金額（乗換）()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB67C0281
     */
    public long getSwitchingUnitAmt()
    {
        long l_lngSwitchingUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getSwtUnitAmt();
        return l_lngSwitchingUnitAmt;
    }

    /**
     * (isシステム取扱)<BR>
     * 当銘柄がWEBBROKERⅢで取り扱われるかどうかを判定する。<BR>
     * <BR>
     * this.getDataSourceObject().getシステム取扱フラグ()の戻り値が<BR>
     * ”1：WEBBROKERⅢで取り扱う”の場合は true を、それ以外の<BR>
     * 場合は false を返す。<BR>
     * @@return boolean
     * @@roseuid 40B46AA301D4
     */
    public boolean isSystemHandling()
    {
        final String STR_METHOD_NAME = "isSystemHandling()";
        log.entering(STR_METHOD_NAME);

        if (WEB3SystemHandlingDivDef.WEBBROKER3_TREAT_IT_IN.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getSystemHandlingDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is買付制限有り)<BR>
     * 当銘柄に買付制限が課せられているかどうかを判定する。<BR>
     * <BR>
     * this.getDataSourceObject().get買付制限区分( )の戻り値が<BR>
     * "0：買付可能"の場合、falseを返却する。<BR>
     * this.getDataSourceObject().get買付制限区分( )の戻り値が<BR>
     * "1：乗換買付のみ可能"の場合、trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 40DB85A00399
     */
    public boolean isAcquiredDeregExistence()
    {
        final String STR_METHOD_NAME = "isAcquiredDeregExistence()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get買付制限区分( )の戻り値が"0：買付可能"の場合
        if (WEB3BuyLimitDivDef.BUY_POSSIBLE.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getBuyLimitDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //this.getDataSourceObject().get買付制限区分( )の戻り値が"1：乗換買付のみ可能"の場合
        if (WEB3BuyLimitDivDef.ONLY_SWITCHING_BUY_POSSIBLE.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getBuyLimitDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //その他
        log.debug("予期しない業務エラーが発生しました");
        log.exiting(STR_METHOD_NAME);
        throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.BUSINESS_ERROR_00010,
            this.getClass().getName() + "." + STR_METHOD_NAME,
            "予期しない業務エラーが発生しました");
    }

    /**
     * (get参考基準価額)<BR>
     * 参考基準価額を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get参考基準価額()の戻り値を返す。<BR>
     * @@return double
     * @@roseuid 40CE78CA0018
     */
    public double getRefConstantValue()
    {
        double l_dblRefConstantValue =
            ((MutualFundProductRow) this.getDataSourceObject()).getReferenceConstantValue();
        return l_dblRefConstantValue;
    }

    /**
     * (get基準価額適用日)<BR>
     * 基準価額適用日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get基準価額適用日()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40CE7CE800E4
     */
    public Timestamp getConstantValueAppDate()
    {
        Timestamp l_ConstantValueAppDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getConstantValueAppDate();
        return l_ConstantValueAppDate;
    }

    /**
     * (get設定日)<BR>
     * 設定日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get設定日()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40E93A7B03C7
     */
    public Timestamp getSettingDate()
    {
        Timestamp l_SettingDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getSettingDate();
        return l_SettingDate;
    }

    /**
     * (get償還日)<BR>
     * 償還日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get償還日()の戻り値を返す。<BR>
     * @@return Timestamp
     * @@roseuid 40E93AB9033A
     */
    public Timestamp getRefundDate()
    {
        Timestamp l_RefundDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRedemptionDate();
        return l_RefundDate;
    }

    /**
     * (is買付可能)<BR>
     * 当銘柄が現在日付と指定された発注日に買付可能かをチェックする。<BR>
     * <BR>
     * １）　@現在日付の取得
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、<BR>
     * 　@現在日時を取得する。<BR>
     * <BR>
     * ２）　@買付開始日を取得する。<BR>
     * 　@this.getDataSourceObject().get買付開始日()をコールして、<BR>
     * 買付開始日を取得する。<BR>
     * <BR>
     * ３）　@買付終了日を取得する。<BR>
     * 　@this.getDataSourceObject().get買付終了日()をコールして、<BR>
     * 買付終了日を取得する。<BR>
     * <BR>
     * ４）　@買付開始日と買付終了日が設定されていない場合は false を返す。<BR>
     * <BR>
     * ５）　@買付終了日、引数.発注日をYYYYMMDD形式の文字列に変換する<BR>
     * <BR>
     * ６）以下の条件に合致する場合は true を、そうでない場合は false を返す。<BR>
     * <BR>
     * 　@　@買付開始日 ≦ 現在日時 かつ 発注日 ≦ 買付終了日<BR>
     * <BR>
     * @@param l_datOrderBizDate - 発注日
     * @@return boolean
     * @@roseuid 40CE889F0299
     */
    public boolean isAcquiredPossible(Date l_datOrderBizDate)
    {
        final String STR_METHOD_NAME =
            "isAcquiredPossible(Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        //１）　@現在日付の取得
        //ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

        //２）　@買付開始日を取得する
        Timestamp l_tmsBuyStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyStartDate();

        //３）　@買付終了日を取得する
        Timestamp l_tmsBuyEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyEndDate();

        //４）　@買付開始日と買付終了日が設定されていない場合
        if (l_tmsBuyStartDate == null || l_tmsBuyEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //５）　@買付終了日、引数.発注日をYYYYMMDD形式の文字列に変換する
        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        String l_strOrderBizDate = l_dateFormat.format(l_datOrderBizDate);
        String l_strEndDate = l_dateFormat.format(l_tmsBuyEndDate);


        //６） 買付開始日 ≦ 現在日時 かつ　@発注日 ≦ 買付終了日
        if (l_tmsSystemTimestamp.compareTo(l_tmsBuyStartDate) >= 0
            && l_strEndDate.compareTo(l_strOrderBizDate) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is解約乗換可能)<BR>
     * 当銘柄が指定された発注日に解約または乗換が可能かをチェックする。<BR>
     * <BR>
     * １）　@解約乗換開始日を取得する。<BR>
     * 　@this.getDataSourceObject().get解約乗換開始日()をコールして、<BR>
     * 解約乗換開始日を取得する。<BR>
     * <BR>
     * ２）　@解約乗換終了日を取得する。<BR>
     * 　@this.getDataSourceObject().get解約乗換終了日()をコールして、<BR>
     * 解約乗換終了日を取得する。<BR>
     * <BR>
     * ３）　@解約乗換開始日と解約乗換終了日が設定されていない場合は false を返す。<BR>
     * <BR>
     * ４）　@解約乗換開始日、解約乗換終了日、引数.発注日を<BR>
     * YYYYMMDD形式の文字列に変換する。<BR>
     * <BR>
     * ５）　@４）で変換した文字列が以下の条件に合致する場合は true を、<BR>
     * そうでない場合は false を返す。<BR>
     * <BR>
     * 　@　@解約乗換開始日 ≦ 発注日 ≦ 解約乗換終了日<BR>
     * <BR>
     * @@param l_datOrderBizDate - 発注日
     * @@return boolean
     * @@roseuid 40CE8C8D0151
     */
    public boolean isSellSwitchingPossible(Date l_datOrderBizDate)
    {
        final String STR_METHOD_NAME =
            "isSellSwitchingPossible(Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        if (l_datOrderBizDate == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータはNullである");
        }

        //１）　@解約乗換開始日を取得する
        Timestamp l_SellSwtStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellSwtStartDate();

        //２）　@解約乗換終了日を取得する
        Timestamp l_SellSwtEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getSellSwtEndDate();

        //３）　@解約乗換開始日と解約乗換終了日が設定されていない場合
        if (l_SellSwtStartDate == null || l_SellSwtEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //４）　@解約乗換開始日、解約乗換終了日、引数.発注日を YYYYMMDD形式の文字列に変換する
        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        String l_strOrderBizDate = l_dateFormat.format(l_datOrderBizDate);
        String l_strSellSwtStartDate = l_dateFormat.format(l_SellSwtStartDate);
        String l_strSellSwtEndDate = l_dateFormat.format(l_SellSwtEndDate);

        //５）解約乗換開始日 ≦ 発注日 ≦ 解約乗換終了日
        if (l_strOrderBizDate.compareTo(l_strSellSwtStartDate) >= 0
            && l_strSellSwtEndDate.compareTo(l_strOrderBizDate) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is株式型)<BR>
     * 当銘柄が株式型かどうかを判定する。 <BR>
     * <BR>
     * this.getDataSourceObject().get株式型・債券型()の戻り値が <BR>
     * ”1：株式型”の場合は true を、それ以外の場合は false を返す。<BR>
     * @@return boolean
     * @@roseuid 40B46AA301D4
     */
    public boolean isStockType()
    {
        final String STR_METHOD_NAME = "isStockType()";
        log.entering(STR_METHOD_NAME);

        if (WEB3StockTypeBondTypeDef.STOCK_TYPE.equals(
                ((MutualFundProductRow) this.getDataSourceObject()).getStockTypeBondType()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get買取請求開始日)<BR>
     * 買取請求開始日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get買取請求開始日()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB6150222
     */
    public Timestamp getBuyClaimStartDate()
    {
        Timestamp l_datBuyClaimStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyClaimStartDate();
        return l_datBuyClaimStartDate;
    }

    /**
     * (get買取請求終了日)<BR>
     * 買取請求終了日を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get買取請求終了日()の戻り値を返す。<BR>
     * @@return long
     * @@roseuid 40ADB6150222
     */
    public Timestamp getBuyClaimEndDate()
    {
        Timestamp l_datBuyClaimEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getBuyClaimEndDate();
        return l_datBuyClaimEndDate;
    }

    /**
     * (get募集価額)<BR>
     * 募集価額を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get募集価額()の戻り値を返却する。 <BR>
     * <BR>
     * @@return double
     * @@roseuid 40ADB6150222
     */
    public double getRecruitConstantValue()
    {
        final String STR_METHOD_NAME = " getRecruitConstantValue()";
        log.entering(STR_METHOD_NAME);

        double l_dblRecruitConstantValue =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitConstantValue();

        log.exiting(STR_METHOD_NAME);
        return l_dblRecruitConstantValue;
    }


    /**
     * (get決済（募集）)<BR>
     * 決済（募集）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get決済（募集）()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitSettlementDiv()
    {
        final String STR_METHOD_NAME = " getRecruitSettlementDiv()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get決済（募集）()の戻り値を返す。
        String l_strRecruitSettlementDiv =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitSettlementDiv();
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitSettlementDiv;
    }

    /**
     * (get最低口数（募集）)<BR>
     * 最低口数（募集）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get最低口数（募集）()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitMinQty()
    {
        final String STR_METHOD_NAME = " getRecruitMinQty()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get最低口数（募集）()の戻り値を返す。
        String l_strRecruitMinQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitMinQty() + "";
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitMinQty;
    }

    /**
     * (get単位口数（募集）)<BR>
     * 単位口数（募集）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get単位口数（募集）()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitUnitQty()
    {
        final String STR_METHOD_NAME = " getRecruitUnitQty()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get単位口数（募集）()の戻り値を返す。
        String l_strRecruitUnitQty =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitUnitQty() + "";
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitUnitQty;
    }

    /**
     * (get最低金額（募集）)<BR>
     * 最低金額（募集）を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get最低金額（募集）()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitMinAmt()
    {
        final String STR_METHOD_NAME = " getRecruitMinAmt()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get最低金額（募集）()の戻り値を返す。
        String l_strRecruitMinAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitMinAmt() + "";
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitMinAmt;
    }

    /**
     * (get単位金額（募集）)<BR>
     * 単位金額（募集）を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get単位金額（募集）()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitUnitAmt()
    {
        final String STR_METHOD_NAME = " getRecruitUnitAmt";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get単位金額（募集）()の戻り値を返す。
        String l_strRecruitUnitAmt =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitUnitAmt() + "";
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitUnitAmt;
    }

    /**
     * (is募集可能) <BR>
     * 当銘柄が指定された発注日に募集可能かをチェックする。 <BR>
     * <BR>
     *１）　@現在日時を取得する。  <BR>
     *　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、<BR>
     *現在日時を取得する。<BR>
     *<BR>
     *２）　@募集開始日を取得する。  <BR>
     *　@this.getDataSourceObject().get募集開始日()をコールして、<BR>
     *募集開始日を取得する。  <BR>
     *<BR>
     *３）　@募集終了日を取得する。  <BR>
     *　@this.getDataSourceObject().get募集終了日()をコールして、<BR>
     *募集終了日を取得する。  <BR>
     *<BR>
     *４）　@募集開始日あるいは募集終了日が設定されていない場合は false を返す。  <BR>
     *<BR>
     *５）　@募集終了日、引数.発注日をYYYYMMDD形式の文字列に変換する。  <BR>
     *<BR>
     *６）　@５）で変換した文字列が以下の条件に合致する場合は true を、<BR>
     *そうでない場合はfalse を返す。  <BR>
     *<BR>
     *　@　@募集開始日 ≦ 現在日時 　@かつ　@発注日 ≦ 募集終了日 <BR>
     * <BR>
     * @@param l_datOrderBizDate - 発注日
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40ADB6150222
     */
    public boolean isRecruitPossible(Date l_datOrderBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " isRecruitPossible(Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        if (l_datOrderBizDate == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "パラメータはNullである");
        }

        //1現在日付の取得
        //ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、現在日時を取得する。
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);

        //2）　@募集開始日を取得する。
        //this.getDataSourceObject().get募集開始日()をコールして、募集開始日を取得する。
        Timestamp l_tsRecruitStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitStartDate();
        //3）　@募集終了日を取得する。
        //this.getDataSourceObject().get募集終了日()をコールして、募集終了日を取得する。
        Timestamp l_tsRecruitEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitEndDate();
        //4）　@募集開始日あるいは募集終了日が設定されていない場合は false を返す
        if (l_tsRecruitStartDate == null || l_tsRecruitEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //5）　@募集終了日、引数.発注日をYYYYMMDD形式の文字列に変換する。
        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        String l_strOrderBizDate = l_dateFormat.format(l_datOrderBizDate);
        String l_strRecruitEndDate = l_dateFormat.format(l_tsRecruitEndDate);

        //6）　@5）で変換した文字列が以下の条件に合致する場合は true を、
        //  そうでない場合は false を返す。
        //  募集開始日 ≦ 現在日時 　@かつ　@発注日 ≦ 募集終了日
        if (l_tmsSystemTimestamp.compareTo(l_tsRecruitStartDate) >= 0
            && l_strRecruitEndDate.compareTo(l_strOrderBizDate) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get募集開始日)<BR>
     * 募集開始日を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get募集開始日()の戻り値を返す。<BR>
     * <BR>
     * @@return Timestamp
     * @@roseuid 40ADB6150222
     */
    public Timestamp getRecruitStartDate()
    {
        final String STR_METHOD_NAME = " getRecruitStartDate()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get募集開始日()の戻り値を返す。
        Timestamp l_tsRecruitStartDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitStartDate();
        log.exiting(STR_METHOD_NAME);
        return l_tsRecruitStartDate;
    }

    /**
     * (get募集終了日)<BR>
     * 募集終了日を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get募集終了日()の戻り値を返す。<BR>
     * <BR>
     * @@return Timestamp
     * @@roseuid 40ADB6150222
     */
    public Timestamp getRecruitEndDate()
    {
        final String STR_METHOD_NAME = " getRecruitEndDate()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get募集終了日()の戻り値を返す。
        Timestamp l_tsRecruitEndDate =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitEndDate();
        log.exiting(STR_METHOD_NAME);
        return l_tsRecruitEndDate;
    }

    /**
     * (get為替レート)<BR>
     * 為替レートテーブルの行オブジェクトを返す。<BR>
     * <BR>
     * １）以下の条件で為替レートテーブルからレコードを取得する。<BR>
     *  [条件] <BR>
     * 証券会社コード = this.getInstitution().getInstitutionCode() の戻り値 <BR>
     * 通貨コード = this.get通貨コード() の戻り値 <BR>
     * ２）取得したレコードの行オブジェクトを返却する。<BR>
     * <BR>
     * @@return 為替レートParams
     * @@throws WEB3BaseException
     * @@roseuid 40ADB6150222
     */
    public ExchangeRateParams getExchangeRate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExchangeRate()";
        log.entering(STR_METHOD_NAME);

        //１）以下の条件で為替レートテーブルからレコードを取得する。
        //証券会社コード = this.getInstitution().getInstitutionCode() の戻り値
        String l_strInstitutionCode = this.getInstitution().getInstitutionCode();
        //通貨コード = this.get通貨コード() の戻り値
        String l_strCurrencyCode = this.getCurrencyCode();

        ExchangeRateParams l_exchangeRateParams = null;
        try
        {
            ExchangeRateRow l_exchangeRateRow =
                ExchangeRateDao.findRowByPk(
                    l_strInstitutionCode, l_strCurrencyCode);
            l_exchangeRateParams =
                new ExchangeRateParams(l_exchangeRateRow);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataException l_ex)
        {
            log.error("DBへのアクセスに失敗しました: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）取得したレコードの行オブジェクトを返却する
        log.exiting(STR_METHOD_NAME);
        return l_exchangeRateParams;
    }

    /**
     * (is外国投信)<BR>
     * 当銘柄が外国投信かどうかを判定する。<BR>
     * <BR>
     * 以下の条件と一致する場合は true を、それ以外の場合は false を返す。<BR>
     *  this.getDataSourceObject().get投信タイプ()の戻り値 == ”国外” <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40ADB6150222
     */
    public boolean isForeignFund()
    {
        final String STR_METHOD_NAME = " isForeignFund()";
        log.entering(STR_METHOD_NAME);
        //以下の条件と一致する場合は true を、それ以外の場合は false を返す。
        //this.getDataSourceObject().get投信タイプ()の戻り値 == ”国外”
        MutualFundTypeEnum l_mutualFundType =
            ((MutualFundProductRow) this.getDataSourceObject()).getFundType();

        if (MutualFundTypeEnum.FOREIGN.equals(l_mutualFundType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is外貨建投信)<BR>
     * 当銘柄が外貨建投信かどうかを判定する。<BR>
     * <BR>
     * 以下の条件と一致する場合は true を、それ以外の場合は false を返す。 <BR>
     * <BR>
     * this.get通貨コード()の戻り値 != ”円” かつ<BR>
     * this.is外貨MMF = false <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40ADB6150222
     */
    public boolean isForeignCurencyFund()
    {
        final String STR_METHOD_NAME = "isForeignCurencyFund()";
        log.entering(STR_METHOD_NAME);
        //以下の条件と一致する場合は true を、それ以外の場合は false を返す。
        //this.get通貨コード()の戻り値 != ”円”

        String l_strCurrencyCode = this.getCurrencyCode();

        //this.get通貨コード()の戻り値 != ”円” かつ
        //this.is外貨MMF = false
        if (!WEB3MFOrderQuantityType.EN.equals(l_strCurrencyCode)
            && !this.isFrgnMmf())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get指定方法@（募集）)<BR>
     * 指定方法@（募集）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get指定方法@（募集）()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     * @@roseuid 40ADB6150222
     */
    public String getRecruitSpecityDiv()
    {
        final String STR_METHOD_NAME = "getRecruitSpecityDiv()";
        log.entering(STR_METHOD_NAME);
        //this.getDataSourceObject().get指定方法@（募集）()の戻り値を返す。
        String l_strRecruitSpecityDiv =
            ((MutualFundProductRow) this.getDataSourceObject()).getRecruitSpecityDiv();
        log.exiting(STR_METHOD_NAME);
        return l_strRecruitSpecityDiv;
    }

    /**
     * (isFWF)<BR>
     * 当銘柄がFWF（フィデリティ・ワールド・ファ@ンズ）かどうかを判定する。 <BR>
     * <BR>
     * 以下の条件と一致する場合は true を、それ以外の場合は false を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get投信タイプ()の戻り値 == ”その他” <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40ADB6150222
     */
    public boolean isFWF()
    {
        final String STR_METHOD_NAME = "isFWF()";
        log.entering(STR_METHOD_NAME);

        MutualFundTypeEnum l_fundType =
            ((MutualFundProductRow) this.getDataSourceObject()).getFundType();

        //以下の条件と一致する場合は true を、それ以外の場合は false を返す。
        //this.getDataSourceObject().get投信タイプ()の戻り値 == ”その他”
        if (MutualFundTypeEnum.OTHER.equals(l_fundType))
        {
            log.debug("get投信タイプ()の戻り値 == ”その他”");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.debug("get投信タイプ()の戻り値 != ”その他”");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is国内投信)<BR>
     * 当銘柄が国内投信かどうかを判定する。<BR>
     * <BR>
     * 以下の条件と一致する場合は true を、それ以外の場合は false を返す。<BR>
     *  this.getDataSourceObject().get投信タイプ()の戻り値 == ”国内” <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 40ADB6150222
     */
    public boolean isDomesticFund()
    {
        final String STR_METHOD_NAME = " isDomesticFund()";
        log.entering(STR_METHOD_NAME);
        //以下の条件と一致する場合は true を、それ以外の場合は false を返す。
        //this.getDataSourceObject().get投信タイプ()の戻り値 == ”国内”
        MutualFundTypeEnum l_mutualFundType =
            ((MutualFundProductRow) this.getDataSourceObject()).getFundType();

        if (MutualFundTypeEnum.DOMESTIC.equals(l_mutualFundType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is再投資銘柄)<BR>
     * 当銘柄が再投資銘柄かどうかを判定する。<BR>
     * <BR>
     * 以下の条件と一致する場合は true を、それ以外の場合は false を返す。<BR>
     *  this.getDataSourceObject().get再投資銘柄区分()の戻り値 == ”再投資コース銘柄” <BR>
     * <BR>
     * @@return boolean
     * @@roseuid
     */
    public boolean isPlowbackProduct()
    {
        final String STR_METHOD_NAME = " isPlowbackProduct()";
        log.entering(STR_METHOD_NAME);
        //以下の条件と一致する場合は true を、それ以外の場合は false を返す。
        //this.getDataSourceObject().get再投資銘柄区分()の戻り値 == ”再投資コース銘柄”
        String l_strPlowbackProductDiv =
            ((MutualFundProductRow) this.getDataSourceObject()).getPlowbackProductDiv();

        if (l_strPlowbackProductDiv.equals(WEB3MFPlowbackProductDivDef.PLOWBACK_PRODUCT))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get円転基準価額)<BR>
     * 円転基準価額を取得する <BR>
     * <BR>
     * １）this.get為替レート()にて為替レートParams取得する。 <BR>
     * <BR>
     * ２）-１　@引数：処理区分＝買付の場合 <BR>
     *　@　@　@　@　@this.get買付基準価額() × 為替レートParamsのTTS　@÷　@<BR>
     *　@　@　@　@　@為替レートParamsの為替レート計算単位 (小数点以下四捨五入)<BR>
     *　@　@　@　@　@を返す。 <BR>
     * <BR>
     * ２）-２　@引数：処理区分＝解約の場合 <BR>
     *　@　@　@　@　@this.get解約価額() × 為替レートParamsのTTB　@÷　@<BR>
     *　@　@　@　@　@為替レートParamsの為替レート計算単位　@ (小数点以下四捨五入)<BR>
     *　@　@　@　@　@を返す。 <BR>
     * @@param  l_strStatus - (処理区分)
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getYenConstantValue(String l_strStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getYenConstantValue(String)";
        log.entering(STR_METHOD_NAME);

        //１）this.get為替レート()にて為替レートParams取得する。
        ExchangeRateParams l_exchangeRateParams = this.getExchangeRate();
        BigDecimal l_bdYenConstantValue = null;

        //２）-１　@引数：処理区分＝買付の場合
        //　@　@　@　@　@this.get買付基準価額() × 為替レートParamsのTTS　@
        //　@　@　@　@　@÷　@為替レートParamsの為替レート計算単位 (小数点以下四捨五入)
        //　@　@　@　@　@を返す。
        if(WEB3MFProcessDivDef.BUY.equals(l_strStatus))
        {
            BigDecimal l_bdTtSellRate =
                new BigDecimal(Double.toString(l_exchangeRateParams.getTtSellingRate()));
            BigDecimal l_bdExchangeCalcUnit =
                new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
            BigDecimal l_bdConstantValue =
                new BigDecimal(Double.toString(this.getConstantValue()));

            l_bdYenConstantValue =
                l_bdConstantValue.multiply(l_bdTtSellRate).divide(
                    l_bdExchangeCalcUnit, 0, BigDecimal.ROUND_HALF_UP);
        }

        //２）-２　@引数：処理区分＝解約の場合
        //　@　@　@　@　@this.get解約価額() × 為替レートParamsのTTB　@÷　@
        //　@　@　@　@　@為替レートParamsの為替レート計算単位　@ (小数点以下四捨五入)
        //　@　@　@　@　@を返す。
        else if(WEB3MFProcessDivDef.SELL.equals(l_strStatus))
        {
            BigDecimal l_bdTtBuyRate =
                new BigDecimal(Double.toString(l_exchangeRateParams.getTtBuyingRate()));
            BigDecimal l_bdExchangeCalcUnit =
                new BigDecimal(Double.toString(l_exchangeRateParams.getExchangeCalcUnit()));
            BigDecimal l_bdSellValue =
                new BigDecimal(Double.toString(this.getSellValue()));

            l_bdYenConstantValue =
                l_bdSellValue.multiply(l_bdTtBuyRate).divide(
                    l_bdExchangeCalcUnit, 0, BigDecimal.ROUND_HALF_UP);
        }

        log.exiting(STR_METHOD_NAME);
        return l_bdYenConstantValue.toString();
    }

    /**
     * (is定時定額買付可能)<BR>
     * 当銘柄が定時定額可能銘柄かどうかを判定する。<BR>
     * <BR>
     * this.getDataSourceObject().get定時定額買付可能区分( )の戻り値が <BR>
     * "1：定時定額買付可能"の場合、trueを返却する。 <BR>
     * それ以外の場合、falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isFixedBuyPossible()
    {
        final String STR_METHOD_NAME = " isFixedBuyPossible()";
        log.entering(STR_METHOD_NAME);

        //当銘柄が定時定額可能銘柄かどうかを判定する
        // "1：定時定額買付可能"の場合、trueを返却する。
        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();

        if (WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE.equals(
            l_fundProductRow.getFixedBuyPossibleDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //それ以外の場合、falseを返却する
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get募集開始日（SONAR）)<BR>
     * 募集開始日（SONAR）を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get募集開始日（SONAR）()の戻り値を返す。<BR>
     */
    public Timestamp getApplyAbleStartDateSonar()
    {
        final String STR_METHOD_NAME = " getApplyAbleStartDateSonar()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();

        Timestamp l_tsApplyAbleStartDateSonar = l_fundProductRow.getRecruitStartDateSonar();

        log.exiting(STR_METHOD_NAME);
        return l_tsApplyAbleStartDateSonar;
    }

    /**
     * (get募集終了日（SONAR）)<BR>
     * 募集終了日（SONAR）を返す。 <BR>
     * <BR>
     * this.getDataSourceObject().get募集終了日（SONAR）()の戻り値を返す。<BR>
     */
    public Timestamp getApplyAbleEndDateSonar()
    {
        final String STR_METHOD_NAME = " getApplyAbleEndDateSonar()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();

        Timestamp l_tsApplyAbleEndDateSonar = l_fundProductRow.getRecruitEndDateSonar();

        log.exiting(STR_METHOD_NAME);
        return l_tsApplyAbleEndDateSonar;
    }

    /**
     * (is特定日取引銘柄) <BR>
     *当銘柄が特定日取引銘柄かどうかを判定する。 <BR>
     *<BR>
     *１）特定日取引銘柄区分を取得する。 <BR>
     *　@ this.getDataSourceObject().get特定日取引銘柄区分() <BR>
     *<BR>
     *２　@以下の①@～④の場合はtrueを返す。それ以外の場合はfalseを返す。 <BR>
     *<BR>
     *　@①@ 「引数．注文種別＝投資信託買注文」　@かつ　@「特定日取引銘柄区分＝買付のみ」 <BR>
     *　@② 「引数．注文種別＝投資信託買注文」　@かつ　@「特定日取引銘柄区分＝両方」 <BR>
     *　@③ 「引数．注文種別＝投資信託売注文」　@かつ　@「特定日取引銘柄区分＝解約のみ」 <BR>
     *　@④ 「引数．注文種別＝投資信託売注文」　@かつ　@「特定日取引銘柄区分＝両方」<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR>
     * <BR>
     * 201：投資信託買注文<BR>
     * 202：投資信託売注文<BR>
     * @@return boolean
     */
    public boolean isUnitTypeProduct(OrderTypeEnum l_orderType)
    {
        final String STR_METHOD_NAME = " isUnitTypeProduct(OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);

        //１）特定日取引銘柄区分を取得する。
        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();
        String l_strUnitTypeProductDiv = l_fundProductRow.getUnitTypeProductDiv();

        //２　@以下の①@～④の場合はtrueを返す。それ以外の場合はfalseを返す。
        //①@ 「引数．注文種別＝投資信託買注文」　@かつ　@「特定日取引銘柄区分＝買付のみ」
        //② 「引数．注文種別＝投資信託買注文」　@かつ　@「特定日取引銘柄区分＝両方」
        //③ 「引数．注文種別＝投資信託売注文」　@かつ　@「特定日取引銘柄区分＝解約のみ」
        //④ 「引数．注文種別＝投資信託売注文」　@かつ　@「特定日取引銘柄区分＝両方」
        boolean l_blnIsUnitTypeProduct = false;
        if ((OrderTypeEnum.MF_BUY.equals(l_orderType) 
            && WEB3UnitTypeProductDivDef.BUY.equals(l_strUnitTypeProductDiv)))
        {
            l_blnIsUnitTypeProduct = true;
        }
        
        if ((OrderTypeEnum.MF_BUY.equals(l_orderType) 
            && WEB3UnitTypeProductDivDef.BOTH.equals(l_strUnitTypeProductDiv)))
        {
            l_blnIsUnitTypeProduct = true;
        }
        
        if ((OrderTypeEnum.MF_SELL.equals(l_orderType) 
            && WEB3UnitTypeProductDivDef.OF_A_CONTRACT.equals(l_strUnitTypeProductDiv)))
        {
            l_blnIsUnitTypeProduct = true;
        }
        
        if ((OrderTypeEnum.MF_SELL.equals(l_orderType) 
            && WEB3UnitTypeProductDivDef.BOTH.equals(l_strUnitTypeProductDiv)))
        {
            l_blnIsUnitTypeProduct = true;
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnIsUnitTypeProduct;
    }
    
    /**
     *(is募集可能(SONAR))<BR>
     *現在日時において、当銘柄が募集可能(SONAR)であるかをチェックする。 <BR> 
     *<BR>
     *１）　@現在日時を取得する。  <BR>
     *　@　@　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、<BR>
     * 　@　@　@現在日時を取得する。  <BR>
     *<BR>
     *２）　@募集開始日(SONAR)を取得する。  <BR>
     *　@this.get募集開始日（SONAR）をコールして、募集開始日(SONAR)を取得する。  <BR>
     *<BR>
     *３）　@募集終了日(SONAR)を取得する。  <BR>
     *　@this.get募集終了日（SONAR）をコールして、募集終了日(SONAR)を取得する。  <BR>
     *<BR>
     *４）　@募集開始日(SONAR)あるいは募集終了日(SONAR)がnullの場合は false を返す。  <BR>
     *<BR>
     *５）　@募集開始日(SONAR)、募集終了日(SONAR)、現在日時をYYYYMMDD形式の文字列に変換する。<BR>
     *<BR>
     *６）　@５）で変換した文字列が以下の条件に合致する場合は true を、<BR>
     * 　@　@　@　@そうでない場合はfalse を返す。  <BR>
     *<BR>
     *　@　@募集開始日(SONAR)(YYYYMMDD) ≦ 現在日時(YYYYMMDD) 　@かつ　@<BR>
     * 　@　@　@　@現在日時(YYYYMMDD) ≦ 募集終了日(SONAR)(YYYYMMDD)<BR>
     * @@return boolean
     */
    public boolean isRecruitPossibleSonar() 
    {
        final String STR_METHOD_NAME = " isRecruitPossibleSonar()";
        log.entering(STR_METHOD_NAME);
        
        //1現在日付の取得
        //ThreadLocalSystemAttributesRegistry.getAttribute()をコールし、
        //現在日時を取得する。
        Timestamp l_tmsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
        
        //２）　@募集開始日(SONAR)を取得する。
        //　@this.get募集開始日（SONAR）をコールして、募集開始日(SONAR)を取得する。
        Timestamp l_tsRecruitStartDateSonar = this.getApplyAbleStartDateSonar();
        
        //３）　@募集終了日(SONAR)を取得する。  
        //　@this.get募集終了日（SONAR）をコールして、募集終了日(SONAR)を取得する。
        Timestamp l_tsRecruitEndtDateSonar = this.getApplyAbleEndDateSonar();
        
        //４）　@募集開始日(SONAR)あるいは募集終了日(SONAR)がnullの場合は false を返す。
        if (l_tsRecruitStartDateSonar == null || l_tsRecruitEndtDateSonar == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //５）　@募集開始日(SONAR)、募集終了日(SONAR)、現在日時をYYYYMMDD形式の文字列に変換する。
        String l_strTsRecruitStartDateSonar =
            WEB3DateUtility.formatDate(l_tsRecruitStartDateSonar, "yyyyMMdd");
        String l_strTsRecruitEndtDateSonar =
            WEB3DateUtility.formatDate(l_tsRecruitEndtDateSonar, "yyyyMMdd");
        String l_strTmsSystemTimestamp =
            WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");
        
        //６）　@５）で変換した文字列が以下の条件に合致する場合は true を、
        //そうでない場合はfalse を返す。
        if (l_strTmsSystemTimestamp.compareTo(l_strTsRecruitStartDateSonar) >= 0
            && l_strTsRecruitEndtDateSonar.compareTo(l_strTmsSystemTimestamp) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     *(is外貨MMF)<BR>
     * 当銘柄が外貨MMFかどうかを判定する。<BR>
     * <BR>
     * 以下の条件と一致する場合は true を、それ以外の場合は false を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get投信タイプ()の戻り値 == ”外貨MMF”<BR>
     * @@return boolean
     */
    public boolean isFrgnMmf()
    {
        final String STR_METHOD_NAME = "isFrgnMmf()";
        log.entering(STR_METHOD_NAME);

        //以下の条件と一致する場合は true を、それ以外の場合は false を返す。
        //this.getDataSourceObject().get投信タイプ()の戻り値 == ”外貨MMF”
        boolean l_blnIsFrgnMmf = false;
        MutualFundProductRow l_fundProductRow =
            (MutualFundProductRow)this.getDataSourceObject();
        if (MutualFundTypeEnum.FOREIGN_MMF.equals(l_fundProductRow.getFundType()))
        {
            l_blnIsFrgnMmf = true;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsFrgnMmf;
    }

    /**
     *(get外貨MMF為替レート)<BR>
     * 外貨MMF為替レートテーブルの行オブジェクトを返す。<BR>
     * <BR>
     * １）以下の条件で外貨MMF為替レートテーブルからレコードを取得する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = this.getInstitution().getInstitutionCode() の戻り値 <BR>
     * 　@通貨コード = this.get通貨コード() の戻り値 <BR>
     * <BR>
     * ２）取得したレコードの行オブジェクトを返却する。<BR>
     * @@return 外貨MMF為替レートParams <BR>
     * @@throws WEB3BaseException 
     */
    public FrgnMmfExchangeRateParams getFrgnMmfExchangeRate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFrgnMmfExchangeRate()";
        log.entering(STR_METHOD_NAME);

        FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = null;
        String l_strInstitutionCode = null;
        String l_strCurrencyCode = null;
        try
        {
            //証券会社コード = this.getInstitution().getInstitutionCode()
            l_strInstitutionCode = this.getInstitution().getInstitutionCode();

            //通貨コード = this.get通貨コード()
            l_strCurrencyCode = this.getCurrencyCode();

            l_frgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams(
                FrgnMmfExchangeRateDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strCurrencyCode));
        }
        catch (DataFindException l_ex)
        {
            log.error("該当データなし", l_ex);
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
        
        log.exiting(STR_METHOD_NAME);
        return l_frgnMmfExchangeRateParams;
    }

    /**
     * (get外貨最低金額（新規買付）)<BR>
     * 外貨最低金額（新規買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get外貨最低金額（新規買付）()の戻り値を返す。<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnNewBuyMinAmt()
    {
        final String STR_METHOD_NAME = " getFrgnNewBuyMinAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get外貨最低金額（新規買付）()の戻り値を返す。
        long l_lngFrgnNewBuyMinAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnNewBuyMinAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnNewBuyMinAmt;
    }

    /**
     * (get外貨最低金額（追加買付）)<BR>
     * 外貨最低金額（追加買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get外貨最低金額（追加買付）()の戻り値を返す。<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnAddBuyMinAmt()
    {
        final String STR_METHOD_NAME = " getFrgnAddBuyMinAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get外貨最低金額（追加買付）()の戻り値を返す。
        long l_lngFrgnAddBuyMinAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnAddBuyMinAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnAddBuyMinAmt;
    }

    /**
     * (get外貨最低金額（解約）)<BR>
     * 外貨最低金額（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get外貨最低金額（解約）()の戻り値を返す。<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnSellMinAmt()
    {
        final String STR_METHOD_NAME = " getFrgnSellMinAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get外貨最低金額（解約）()の戻り値を返す。
        long l_lngFrgnSellMinAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnSellMinAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnSellMinAmt;
    }

    /**
     * (get外貨単位金額（新規買付）)<BR>
     * 外貨単位金額（新規買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get外貨単位金額（新規買付）()の戻り値を返す。<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnNewBuyUnitAmt()
    {
        final String STR_METHOD_NAME = " getFrgnNewBuyUnitAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get外貨単位金額（新規買付）()の戻り値を返す。
        long l_lngFrgnNewBuyUnitAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnNewBuyUnitAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnNewBuyUnitAmt;
    }

    /**
     * (get外貨単位金額（追加買付）)<BR>
     * 外貨単位金額（追加買付）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get外貨単位金額（追加買付）()の戻り値を返す。<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnAddBuyUnitAmt()
    {
        final String STR_METHOD_NAME = " getFrgnAddBuyUnitAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get外貨単位金額（追加買付）()の戻り値を返す。
        long l_lngFrgnAddBuyUnitAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnAddBuyUnitAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnAddBuyUnitAmt;
    }

    /**
     * (get外貨単位金額（解約）)<BR>
     * 外貨単位金額（解約）を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get外貨単位金額（解約）()の戻り値を返す。<BR>
     * <BR>
     * @@return long
     */
    public long getFrgnSellUnitAmt()
    {
        final String STR_METHOD_NAME = " getFrgnSellUnitAmt()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get外貨単位金額（追加買付）()の戻り値を返す。
        long l_lngFrgnSellUnitAmt =
            ((MutualFundProductRow)this.getDataSourceObject()).getFrgnSellUnitAmt();

        log.exiting(STR_METHOD_NAME);
        return l_lngFrgnSellUnitAmt;
    }

    /**
     * (get募集手数料区分)<BR>
     * 募集手数料区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().get募集手数料区分の戻り値を返す。<BR>
     * <BR>
     * @@return String
     */
    public String getRecruitCommissionDiv()
    {
        final String STR_METHOD_NAME = " getRecruitCommissionDiv()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().get募集手数料区分の戻り値を返す。
        String l_strApplyCommissionDiv =
            ((MutualFundProductRow)this.getDataSourceObject()).getRecruitCommissionDiv();

        log.exiting(STR_METHOD_NAME);
        return l_strApplyCommissionDiv;
    }

    /**
     * (getオープン・クローズ区分)<BR>
     * オープン・クローズ区分を返す。<BR>
     * <BR>
     * this.getDataSourceObject().getオープン・クローズ区分()の戻り値を返す。<BR>
     * <BR>
     * @@return String
     */
    public String getOpenCloseDiv()
    {
        final String STR_METHOD_NAME = " getOpenCloseDiv()";
        log.entering(STR_METHOD_NAME);

        //this.getDataSourceObject().getオープン・クローズ区分()の戻り値を返す。
        String l_strOpenCloseDiv =
            ((MutualFundProductRow)this.getDataSourceObject()).getOpenCloseDiv();

        log.exiting(STR_METHOD_NAME);
        return l_strOpenCloseDiv;
    }
}
@
