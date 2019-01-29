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
filename	WEB3GentradeHandlingOrderCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取扱可能注文条件(WEB3GentradeHandlingOrderCond.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/15 鄒政 (中訊) 新規作成
Revesion History : 2004/06/28 王暁傑 (中訊) isHandlingPossibleExecCond() をオーバーロードする
Revesion History : 2004/10/10 孟東 (中訊) WEB3GentradeHandlingOrderCond(String,
                                          ProductTypeEnum,String,String,String)を追加
Revesion History : 2004/10/10 孟東 (中訊) WEB3GentradeHandlingOrderCond(String,
                                          ProductTypeEnum,String,String)を修正
Revesion History : 2004/10/10 孟東 (中訊) WEB3GentradeHandlingOrderCond(String,
                                          ProductTypeEnum,String,String)を修正
Revesion History : 2004/10/10 孟東 (中訊) getHandlingPriceCond()を追加
Revesion History : 2004/10/10 孟東 (中訊) isHandlingPriceCond(String)を追加
Revesion History : 2005/08/15 孟東 (中訊) isHandlingPossibleExecCond(FeqExecutionConditionType)を追加
Revesion History : 2007/06/08 栄イ (中訊)【共通】仕様変更・モデルNo.234、235、241
Revesion History : 2007/06/18 栄イ (中訊)【共通】仕様変更・モデルNo.247
Revesion History : 2007/06/29 栄イ (中訊)【共通】仕様変更・モデルNo.258
Revesion History : 2007/06/29 栄イ (中訊)【共通】仕様変更・モデルNo.257
Revesion History : 2007/11/19 栄イ (中訊)【共通】仕様変更・モデルNo.286
Revesion History : 2007/11/27 栄イ (中訊)【共通】仕様変更・モデルNo.293
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CarriedOrderDef;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3EveningSessionOrderDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.EnableOrderConditionDao;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.define.WEB3GentradePriceCondDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (取扱可能注文条件) <BR>
 * <BR>
 * 証券会社毎の取扱可能注文条件を表現する。 <BR>
 * <BR>
 * （DBレイアウト 「取扱可能注文条件テーブル.xls」参照） <BR>
 */
public class WEB3GentradeHandlingOrderCond implements BusinessObject
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeHandlingOrderCond.class);

    /**
     * (取扱可能注文条件Row) <BR>
     * 取扱可能注文条件行オブジェクト <BR>
     * （自動生成DAOクラス） <BR>
     */
    private EnableOrderConditionRow enableOrderConditionRow;

    /**
     * (取引最終日) <BR>
     * 取引最終日<BR>
     */
    private Date tradingEndDate;

    /**
     * コンストラクタ。 <BR>
     * 引数の条件に一致する取扱可能注文条件オブジェクトを返却する。<BR>
     *  <BR>
     * this.取扱可能注文条件(証券会社コード, 銘柄タイプ, <BR>
     * 先物／オプション区分, 信用取引区分, 市場コード)にdelegateする。<BR>
     *  <BR>
     * ＜delegate時引数設定仕様＞ <BR>
     * 引数の市場コードには「0：DEFAULT」を設定する。<BR>
     * その他の引数には、当メソッドの引数をそのまま設定する。<BR>
     *  <BR>
     * @@param l_strInstitutionCode - (証券会社コード)
     * @@param l_productType - (銘柄タイプ)
     * @@param l_strFuturesOptionDiv - (先物／オプション区分) <BR>
     *    0： DEFAULT（先物OP以外） <BR>
     *    1： 先物 <BR>
     *    2： オプション <BR>
     * @@param l_strMarginTradingDiv - (信用取引区分)<BR>
     *    0： DEFAULT（固定）<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E4FF8009E
     */
    public WEB3GentradeHandlingOrderCond(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strFuturesOptionDiv,
        String l_strMarginTradingDiv)
        throws WEB3SystemLayerException
    {   
        this(l_strInstitutionCode,
            l_productType,
            l_strFuturesOptionDiv,
            l_strMarginTradingDiv,
            WEB3MarketCodeDef.DEFAULT);
    }

    /**
     * this.取扱可能注文条件Rowを返却する。
     * @@return Object
     * @@roseuid 405E4DE9033E
     */
    public Object getDataSourceObject()
    {
        return this.enableOrderConditionRow;
    }

    /**
     * (取扱可能執行条件取得) <BR>
     * <BR>
     * 取扱可能執行条件を取得する。 <BR>
     * <BR>
     * this.取扱可能注文条件Rowの（執行条件）項目で <BR>
     * ”取扱可能”になっている項目に該当する執行条件コード <BR>
     * (*1)を配列にて返却する。 <BR>
     * 但し、”無条件”は必ず配列に含める。 <BR>
     * <BR>
     * (*1) 執行条件コード <BR>
     * 1： 無条件 <BR>
     * 3： 寄付 <BR>
     * 4： 引け <BR>
     * 7： 不出来引け成行 <BR>
     * <BR>
     * @@return String[]
     * @@roseuid 405E527501F6
     */
    public String[] getHandlingPossibleExecCond()
    {
        ArrayList l_lstExecConds = new ArrayList();

        //set 無条件
        l_lstExecConds.add(WEB3ExecutionConditionDef.NO_CONDITION);

        //set 寄付
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getAtMarketOpen()))
        {
            l_lstExecConds.add(WEB3ExecutionConditionDef.AT_MARKET_OPEN);
        }
        //set 引け
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getAtMarketClose()))
        {
            l_lstExecConds.add(WEB3ExecutionConditionDef.AT_MARKET_CLOSE);
        }
        //set 不出来引け成行
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getNoExecAtMartClose()))
        {
            l_lstExecConds.add(WEB3ExecutionConditionDef.AT_MARKET_CLOSE_NOT_EXECUTED);
        }

        int l_intSize = l_lstExecConds.size();
        String[] l_strExecConds = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strExecConds[i] = (String)l_lstExecConds.get(i);
        }
        return l_strExecConds;

    }

    /**
     * (取扱可能発注条件取得)
     * <BR>
     * 取扱可能発注条件を取得する。 <BR>
     * <BR>
     * this.取扱可能注文条件Rowの（発注条件）項目で <BR>
     * ”取扱可能”になっている項目に該当する発注条件コード <BR>
     * (*1)を配列にて返却する。 <BR>
     * 但し、”DEFAULT（条件指定なし）”は必ず配列に含める。 <BR>
     * <BR>
     * (*1) 発注条件コード <BR>
     * 0：DEFAULT（条件指定なし） <BR>
     * 1：逆指値 <BR>
     * 2：W指値 <BR>
     * <BR>
     * @@return String[]
     * @@roseuid 405E5C35032F
     */
    public String[] getHandlingPossibleOrderCond()
    {
        ArrayList l_lstOrderConds = new ArrayList();

        //set DEFAULT（条件指定なし）
        l_lstOrderConds.add(WEB3OrderingConditionDef.DEFAULT);

        //set 逆指値
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getStopOrder()))
        {
            l_lstOrderConds.add(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);
        }
        //set W指値
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getWLimitOrder()))
        {
            l_lstOrderConds.add(WEB3OrderingConditionDef.W_LIMIT_PRICE);
        }

        int l_intSize = l_lstOrderConds.size();
        String[] l_strOrderConds = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strOrderConds[i] = (String) l_lstOrderConds.get(i);
        }
        return l_strOrderConds;

    }

    /**
     * (取扱可能注文単価区分取得)<BR>
     * (買建、売建指定)<BR>
     * <BR>
     * [新規建（パラメータ.is新規建 == true）の場合]<BR>
     * 　@　@-- 買建(パラメータ.is買建 == true) の場合 --<BR>
     * 　@　@　@this.取扱可能注文条件Rowの成行注文（新規買建） == ”取扱可能”<BR>
     * 　@　@であれば、注文単価区分.”指値”、注文単価区分.”成行”を配列で返却する。<BR>
     * 　@　@-- 売建(パラメータ.is買建 == false) の場合 --<BR>
     * 　@　@　@this.取扱可能注文条件Rowの成行注文（新規売建） == ”取扱可能”<BR>
     * 　@　@であれば、注文単価区分.”指値”、注文単価区分.”成行”を配列で返却する。<BR>
     * 　@　@以外、注文単価区分.”指値”をサイズ1の配列で返却する。<BR>
     * <BR>
     * [返済（パラメータ.is新規建 == false）の場合]<BR>
     * 　@　@this.取扱可能注文条件Rowの成行注文（返済） == ”取扱可能”であれば、<BR>
     * 　@　@注文単価区分.”指値”、注文単価区分.”成行”を配列で返却する。<BR>
     * 　@　@以外、注文単価区分.”指値”をサイズ1の配列で返却する。<BR>
     * <BR>
     * (*1) 注文単価区分<BR>
     * 0：成行<BR>
     * 1：指値<BR>
     * <BR>
     * @@param l_isOpenContract 新規建取引かどうかの判定。<BR>
     *          新規建の場合true、返済の場合false。
     * @@param l_isBuyOrder 買建かどうかの判別。<BR>
     *          買建の場合はtrue、売建の場合はfalse。
     * @@return String[]
     */
    public String[] getHandlingPossibleOrderPriceDiv(
        boolean l_isOpenContract,
        boolean l_isBuyOrder)
    {
        final String STR_METHOD_NAME = 
            "getHandlingPossibleOrderPriceDiv(boolean l_blnIsOpenContract, boolean l_blnIsBuyOrder)";
        log.entering(STR_METHOD_NAME + " begin");
                
        String[] l_strOrderPriceDivs = null;
                
        if (l_isOpenContract) //新規建         
        {
            if (l_isBuyOrder     //買建
                && WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceBuyToOpen())) //成行注文（新規買建） == ”取扱可能”
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE}; 
            }
            else if (!l_isBuyOrder  //売建
                       && WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceSellToOpen())) //成行注文（新規売建） == ”取扱可能”
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE};
            }
            else
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE};
            }
                    
        }    
        else    //返済
        {
            if ( WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceOrderSettleCont())) //成行注文（返済） == ”取扱可能
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE};                    
            }
            else
            {
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE};
            }
        }
                
        return l_strOrderPriceDivs;

    }
    
    /**
     * (取扱可能注文単価区分取得)<BR>
     * (新規建指定)<BR>
     * <BR>
     *  取扱可能注文単価区分を取得する。<BR>
     *  <BR>
     * --新規建（is新規建 == true）の場合  <BR>
     *   this.取扱可能注文条件Rowの成行注文（新規買建） == ”取扱可能”または、<BR> 
     *   this.取扱可能注文条件Rowの成行注文（新規売建） == ”取扱可能” <BR>
     *   であれば、注文単価区分.”指値”、注文単価区分.”成行”を配列で返却する。<BR>
     *   以外、注文単価区分.”指値”をサイズ1の配列で返却する。<BR> 
     * --返済（is新規建 == false）の場合 <BR>
     *   this.取扱可能注文条件Rowの成行注文（返済） == ”取扱可能”であれば、<BR>
     *   注文単価区分.”指値”、注文単価区分.”成行”を配列で返却する。 <BR>
     *   以外、注文単価区分.”指値”をサイズ1の配列で返却する。<BR> 
     * <BR>
     * (*1) 注文単価区分<BR>
     * 0：成行<BR>
     * 1：指値<BR>
     * <BR>
     * @@param l_isOpenContract - (is新規建) <BR>
     *     新規建取引かどうかの判定。新規建の場合true、返済の場合false。 <BR>
     * @@return String[]
     */
    public String[] getHandlingPossibleOrderPriceDiv(
        boolean l_isOpenContract)
    {
        String[] l_strOrderPriceDivs = null;
        
        //新規建
        if (l_isOpenContract)          
        {
            //this.取扱可能注文条件Rowの成行注文（新規買建） == ”取扱可能”または、 
            //this.取扱可能注文条件Rowの成行注文（新規売建） == ”取扱可能” 
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceBuyToOpen())
                ||WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceSellToOpen()))
            {
                //注文単価区分.”指値”、注文単価区分.”成行”を配列で返却する
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE};               
            }
            else
            {
                //注文単価区分.”指値”をサイズ1の配列で返却する
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE};
            }
        }
        //返済
        else    
        {
            //this.取扱可能注文条件Rowの成行注文（返済） == ”取扱可能”
            if(WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceOrderSettleCont()))
            {
                //注文単価区分.”指値”、注文単価区分.”成行”を配列で返却する
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE, WEB3OrderPriceDivDef.MARKET_PRICE};               
            }
            else
            {
                //注文単価区分.”指値”をサイズ1の配列で返却する
                l_strOrderPriceDivs = 
                    new String[]{WEB3OrderPriceDivDef.LIMIT_PRICE};
            }
            
        }
        return l_strOrderPriceDivs;
    }

    /**
     * (取扱可能注文期限区分取得) <BR>
     * <BR>
     * １）this.取扱可能注文条件Rowの「銘柄タイプ」項目が”先物オプション”の場合 <BR>
     * <BR>
     * 　@　@　@１－１） 返却する注文期限区分へ"当日限り"を設定する。 <BR>
     * 　@　@　@１－２） this.is出来るまで注文取扱可能＜取引最終日考慮＞()==trueの場合 <BR>
     * 　@　@　@　@　@　@　@　@返却する注文期限区分へ"出来るまで注文"を追加設定する。 <BR>
     * <BR>
     * 　@　@　@１－３） 取引時間管理.is夕場時間帯() == false、かつ、 <BR>
     * 　@　@　@　@　@　@　@this.is夕場まで注文取扱可能<取引最終日考慮>() == trueの場合、 <BR>
     * 　@　@　@　@　@　@　@返却する注文期限区分へ”夕場まで注文”を設定する。 <BR>
     * <BR>
     * 　@　@　@１－４） 設定した注文期限区分を返却する。 <BR>
     * <BR>
     * <BR>
     * ２）　@１）以外の場合 <BR>
     * <BR>
     * 　@　@　@２－１） this.取扱可能注文条件Rowの「出来るまで注文」項目が <BR>
     * 　@　@　@　@　@　@　@　@”取扱不可”の場合”当日限り”のみ、<BR>
     * 以外”当日限り”、”出来るまで注文”を返却する。 <BR>
     * <BR>
     * <BR>
     * (*) 注文期限区分 <BR>
     * 1：当日限り <BR>
     * 2：出来るまで注文 <BR>
     * 3：夕場まで注文<BR>
     * <BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E5E6D01D7
     */
    public String[] getHandlingPossibleExpirationDateType() throws WEB3SystemLayerException
    {
        ArrayList l_lstExpirationDateTypes = new ArrayList();

        //１）this.取扱可能注文条件Rowの「銘柄タイプ」項目が”先物オプション”の場合
        ProductTypeEnum l_productType = this.enableOrderConditionRow.getProductType();
        if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //返却する注文期限区分へ"当日限り"を設定する。
            l_lstExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            //返却する注文期限区分へ"出来るまで注文"を追加設定する。
            if (this.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                l_lstExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            }
            //返却する注文期限区分へ”夕場まで注文”を設定する。
            if (!WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                && this.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering())
            {
                l_lstExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER);
            }
        }
        //２）　@１）以外の場合
        else
        {
            l_lstExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            if (!WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
            {
                l_lstExpirationDateTypes.add(
                    WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            }
        }

        int l_intSize = l_lstExpirationDateTypes.size();
        String[] l_strExpirationDateTypes = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strExpirationDateTypes[i] =
                (String) l_lstExpirationDateTypes.get(i);
        }
        return l_strExpirationDateTypes;

    }

    /**
     * (取扱可能注文期限区分一覧取得) <BR>
     * <BR>
     * 取扱可能注文期限区分一覧を取得する。 <BR>
     * ※時間帯に関わらず注文期限区分一覧を取得する。 <BR>
     * <BR>
     * １）this.取扱可能注文条件Rowの「銘柄タイプ」項目が”先物オプション”の場合 <BR>
     * <BR>
     * 　@　@　@１－１）　@返却する注文期限区分へ"当日限り"を設定する。 <BR>
     * <BR>
     * 　@　@　@１－２）　@this.is出来るまで注文取扱可能＜取引最終日考慮＞()==trueの場合 <BR>
     * 　@　@　@　@　@　@　@　@返却する注文期限区分へ"出来るまで注文"を追加設定する。 <BR>
     * <BR>
     * 　@　@　@１－３）　@this.is夕場まで注文取扱可能＜取引最終日考慮＞() == trueの場合、 <BR>
     * 　@　@　@　@　@　@　@　@返却する注文期限区分へ”夕場まで注文”を設定する。 <BR>
     * <BR>
     * 　@　@　@１－４）　@設定した注文期限区分を返却する。 <BR>
     * <BR>
     * ２）　@１）以外の場合 <BR>
     * <BR>
     * 　@　@　@２－１）　@this.取扱可能注文条件Rowの「出来るまで注文」項目が <BR>
     * 　@　@　@　@　@　@　@　@”取扱不可”の場合”当日限り”のみ、<BR>
     * 以外”当日限り”、”出来るまで注文”を返却する。 <BR>
     * <BR>
     * @@return String[]
     * @@throws WEB3SystemLayerException
     */
    public String[] getHandlingPossibleExpirationDateTypes() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getHandlingPossibleExpirationDateTypes()";
        log.entering(STR_METHOD_NAME);

        List l_lisExpirationDateTypes = new ArrayList();

        //１）this.取扱可能注文条件Rowの「銘柄タイプ」項目が”先物オプション”の場合
        ProductTypeEnum l_productType = this.enableOrderConditionRow.getProductType();
        if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //返却する注文期限区分へ"当日限り"を設定する。
            l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            if (this.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering())
            {
                //返却する注文期限区分へ"出来るまで注文"を追加設定する。
                l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            }
            if (this.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering())
            {
                //返却する注文期限区分へ”夕場まで注文”を設定する。
                l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER);
            }
        }
        //２）　@１）以外の場合
        else
        {
            l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.DAY_LIMIT);
            if (!WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
            {
                l_lisExpirationDateTypes.add(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER);
            }
        }

        int l_intSize = l_lisExpirationDateTypes.size();
        String[] l_strExpirationDateTypes = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strExpirationDateTypes[i] = (String)l_lisExpirationDateTypes.get(i);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strExpirationDateTypes;
    }

    /**
     * (get注文期限内祝日一覧) <BR>
     * <BR>
     * 出来るまで注文の有効期限までのすべての休日（祝日）を配列で返却する。 <BR>
     * （祝日のみ。土日は含めない） <BR>
     * <BR>
     * 　@１）失効日指定可否の判定 <BR>
     * 　@this.取扱可能注文条件Rowの(出来るまで注文)失効日指定が <BR>
     *   ”1：最終日のみ指定可”の場合はnullを返却する。 <BR>
     * <BR>
     * 　@２）失効日までの休日取得 <BR>
     * 　@this.get出来るまで注文最終日()にて最終日を取得する。 <BR>
     * 　@※this.get出来るまで注文最終日(void)でnullが返却された場合は、<BR>
     * 　@※「出来るまで注文取扱不可」の例外をthrowする。     <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00413 <BR>        
     * 　@（発注日(*1)～最終日）間のすべての非営業日(*2)を配列にて返却する。 <BR>
     * <BR>
     * (*1) 発注日 <BR>
     * 取引時間管理.get発注日()にて取得。 <BR>
     * <BR>
     * (*2) 非営業日 <BR>
     * カレンダテーブルより営業日区分==”非営業日”の行の日付を全て取得する。 <BR>
     * 取得した日付のうち、（発注日(*1)～最終日）の期間に含まれるもの。 <BR>
     * <BR>
     * @@return Date[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E5F810189
     */
    public Date[] getExpirationDateHoliday() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDateHoliday()";
        log.entering(STR_METHOD_NAME);

        //１）失効日指定可否の判定 
        // 　@this.取扱可能注文条件Rowの(出来るまで注文)失効日指定が 
        //   ”1：最終日のみ指定可”の場合はnullを返却する
        if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(this.enableOrderConditionRow.getCarriedOrderLapseDateSpec()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）失効日までの休日取得 

        //   this.get出来るまで注文最終日()にて最終日を取得する。
        Date l_datEndDay = this.getOrderUntilDeadLineEndDay();
        if(l_datEndDay == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00413,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        String l_strEndDay = l_format.format(l_datEndDay);

        //発注日取得
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        String l_strBizDay = l_format.format(l_datBizDate);

        //カレンダテーブルより営業日区分==”非営業日”の行の日付を全て取得する
        List l_lisRecords;
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" holiday >= ? ");
        l_sbWhere.append(" and holiday <= ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        Object[] l_objCalenderWhere = { 
            l_strBizDay,      //発注日 
            l_strEndDay,      //最終日
            WEB3BizDateTypeDef.NO_BIZ_DATE }; //非営業日
        String l_strOrderBy = " holiday ";
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                CalendarRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objCalenderWhere);

        }
        catch (DataFindException dfe)
        {
            log.error(STR_METHOD_NAME, dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(STR_METHOD_NAME, dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(STR_METHOD_NAME, dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        int l_intLength = l_lisRecords.size();
        //件数チェック
        if (l_intLength == 0)
        {
            return null;
        }

        // （発注日(*1)～最終日）間のすべての非営業日(*2)を配列にて返却する
        Date[] l_holidays = new Date[l_intLength];
        CalendarRow l_calendarRow;
        Date l_tmpDate;
        for (int i = 0; i < l_intLength; i++)
        {
            l_calendarRow = (CalendarRow)l_lisRecords.get(i);
            l_tmpDate = new Date(l_calendarRow.getHoliday().getTime());
            l_holidays[i] = WEB3DateUtility.toDay(l_tmpDate);
        }

        log.exiting(STR_METHOD_NAME);
        return l_holidays;

    }

    /**
     * (get注文期限内祝日一覧) <BR>
     * <BR>
     * 出来るまで注文の有効期限までのすべての休日（祝日）を配列で返却する。 <BR>
     * （開始日指定） <BR>
     * （祝日のみ。土日は含めない） <BR>
     * <BR>
     * 引数の「出来るまで注文from日付」＝nullの場合は、 <BR>
     * this.get注文期限内祝日一覧(void)に <BR>
     * 委譲する。 <BR>
     * 引数の「出来るまで注文from日付」≠nullの場合は、以下の処理を行う。 <BR>
     * <BR>
     * 　@１）失効日指定可否の判定 <BR>
     * 　@this.取扱可能注文条件Rowの(出来るまで注文)失効日指定が <BR>
     *   ”1：最終日のみ指定可”の場合はnullを返却する。 <BR>
     * <BR>
     * 　@２）失効日までの休日取得 <BR>
     * 　@・this.get出来るまで注文最終日(出来るまで注文from日付)にて、 <BR>
     *   最終日を取得する。 <BR>
     * 　@※this.get出来るまで注文最終日(Date)でnullが返却された場合は、<BR>
     * 　@※「出来るまで注文取扱不可」の例外をthrowする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00413 <BR>        
     * <BR>
     * 　@・（引数の「出来るまで注文from日付」～最終日）間の、 <BR>
     * 　@　@すべての非営業日(*)を配列にて返却する。 <BR>
     * <BR>
     * (*) 非営業日 <BR>
     * カレンダテーブルより営業日区分==”非営業日”の行の日付を全て取得する。<BR>
     * 取得した日付のうち、指定の期間に含まれるもの。 <BR>
     * <BR>
     * @@param l_datOrderUntilDeadLineFromDate - (出来るまで注文from日付) <BR>
     * <BR>
     * 出来るまで注文の最終日を求める際の基準となるfrom日付。 <BR>
     * @@return Date[]
     * @@throws WEB3SystemLayerException
     * @@roseuid 4069194302B7
     */
    public Date[] getExpirationDateHoliday(Date l_datOrderUntilDeadLineFromDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDateHoliday(Date)";
        log.entering(STR_METHOD_NAME);

        //引数の「出来るまで注文from日付」＝nullの場合は、 
        // this.get注文期限内祝日一覧(void)に委譲する。
        if (l_datOrderUntilDeadLineFromDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return this.getExpirationDateHoliday();
        }

        //１）失効日指定可否の判定 
        // 　@this.取扱可能注文条件Rowの(出来るまで注文)失効日指定が 
        //   ”1：最終日のみ指定可”の場合はnullを返却する
        if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(this.enableOrderConditionRow.getCarriedOrderLapseDateSpec()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）失効日までの休日取得 

        //   this.get出来るまで注文最終日()にて最終日を取得する。
        Date l_datEndDay = this.getOrderUntilDeadLineEndDay(l_datOrderUntilDeadLineFromDate);
        if(l_datEndDay == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00413,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        String l_strEndDay = l_format.format(l_datEndDay);
        //注文from日付を取得する
        l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        String l_strFromDay = l_format.format(l_datOrderUntilDeadLineFromDate);

        //カレンダテーブルより営業日区分==”非営業日”の行の日付を全て取得する
        List l_lisRecords;
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" holiday >= ? ");
        l_sbWhere.append(" and holiday <= ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        Object[] l_objCalenderWhere = { 
            l_strFromDay,      //注文from日付 
            l_strEndDay,       //最終日
            WEB3BizDateTypeDef.NO_BIZ_DATE }; //非営業日
        String l_strOrderBy = " holiday ";
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    CalendarRow.TYPE,
                    l_sbWhere.toString(),
                    l_strOrderBy,
                    null,
                    l_objCalenderWhere);

        }
        catch (DataFindException dfe)
        {
            log.error(STR_METHOD_NAME, dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(STR_METHOD_NAME, dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(STR_METHOD_NAME, dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        int l_intLength = l_lisRecords.size();
        //件数チェック
        if (l_intLength == 0)
        {
            return null;
        }

        // 　@（発注日(*1)～最終日）間のすべての非営業日(*2)を配列にて返却する
        Date[] l_holidays = new Date[l_intLength];
        CalendarRow l_calendarRow;
        Date l_tmpDate;
        for (int i = 0; i < l_intLength; i++)
        {
            l_calendarRow = (CalendarRow)l_lisRecords.get(i);
            l_tmpDate = new Date(l_calendarRow.getHoliday().getTime());
            l_holidays[i] = WEB3DateUtility.toDay(l_tmpDate);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_holidays;
    }

    /**
     * (is取扱可能執行条件) <BR>
     * <BR>
     * 指定執行条件が取扱可能かを判定する。 <BR>
     * <BR>
     * 引数の執行条件が、条件なし（NONE）の場合はtrueを返却する。 <BR>
     * 
     * 以外、this.取扱可能注文条件Rowの（執行条件）項目で <BR>
     * 引数の執行条件に対応する項目(*1)が、”取扱可能”であればtrue、 <BR>
     * 以外はfalseを返却する。 <BR>
     * <BR>
     * (*1) 執行条件対応項目 <BR>
     * LIMIT_PRICE ：（注文単価区分）指値 <BR>
     * MARKET_PRICE ：（注文単価区分）成行 <BR>
     * AT_MARKET_OPEN ：（執行条件）寄り <BR>
     * AT_MARKET_CLOSE ：（執行条件）引け <BR>
     * AT_MARKET_CLOSE_NOT_EXECUTED ：（執行条件）不出来引け成行 <BR>
     * <BR>
     * @@param l_executionCondType - 執行条件
     * @@return boolean
     * @@roseuid 405E550202F0
     */
    public boolean isHandlingPossibleExecCond(EqTypeExecutionConditionType l_executionCondType)
    {
        boolean l_result = true;

        // AT_MARKET_OPEN ：（執行条件）寄り
        if (l_executionCondType.intValue() == EqTypeExecutionConditionType.AT_MARKET_OPEN.intValue())
        {
            if (this.enableOrderConditionRow.getAtMarketOpen().compareTo(WEB3DealtDef.CAN_DEALT) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }
        //AT_MARKET_CLOSE ：（執行条件）引け 
        if (l_executionCondType.intValue() == EqTypeExecutionConditionType.AT_MARKET_CLOSE.intValue())
        {
            if (this.enableOrderConditionRow.getAtMarketClose().compareTo(WEB3DealtDef.CAN_DEALT) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }
        //AT_MARKET_CLOSE_NOT_EXECUTED ：（執行条件）不出来引け成行
        if (l_executionCondType.intValue() == EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.intValue())
        {
            if (this.enableOrderConditionRow.getNoExecAtMartClose().compareTo(WEB3DealtDef.CAN_DEALT) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }

        return l_result;
    }

    /**
     * (is取扱可能執行条件) <BR>
     * <BR>
     * 指定執行条件が取扱可能かを判定する。 <BR>
     * <BR>
     * 引数の執行条件が、条件なし（NONE）の場合はtrueを返却する。 <BR>
     * 
     * 以外、this.取扱可能注文条件Rowの（執行条件）項目で <BR>
     * 引数の執行条件に対応する項目(*1)が、”取扱可能”であればtrue、 <BR>
     * 以外はfalseを返却する。 <BR>
     * <BR>
     * (*1) 執行条件対応項目 <BR>
     * LIMIT_PRICE ：（注文単価区分）指値 <BR>
     * MARKET_PRICE ：（注文単価区分）成行 <BR>
     * AT_MARKET_OPEN ：（執行条件）寄り <BR>
     * AT_MARKET_CLOSE ：（執行条件）引け <BR>
     * AT_MARKET_CLOSE_NOT_EXECUTED ：（執行条件）不出来引け成行 <BR>
     * <BR>
     * @@param l_ifoOrderExecutionCondType - 執行条件
     * @@return boolean
     * @@roseuid 405E550202F0
     */
    public boolean isHandlingPossibleExecCond(IfoOrderExecutionConditionType l_ifoOrderExecutionCondType)
    {
        boolean l_result = true;
            
        if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoOrderExecutionCondType))
        {
            //AT_MARKET_OPEN ：（執行条件）寄り
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_OPEN);
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoOrderExecutionCondType))
        {
            //AT_MARKET_CLOSE ：（執行条件）引け
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
        }
        else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoOrderExecutionCondType))
        {
            //AT_MARKET_CLOSE_NOT_EXECUTED ：（執行条件）不出来引け成行
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        }
    
        return l_result;  
    }
        
    /**
     * (is取扱可能発注条件) <BR>
     * <BR>
     * 指定発注条件が取扱可能かを判定する。 <BR>
     * <BR>
     * 引数の発注条件が、DEFAULT（条件指定なし）の場合はtrueを返却する。 <BR>
     * <BR>
     * 以外、this.取扱可能注文条件Rowの（発注条件）項目で <BR>
     * 引数の発注条件に対応する項目(*1)が、”取扱可能”であればtrue、 <BR>
     * 以外はfalseを返却する。 <BR>
     * <BR>
     * (*1) 発注条件対応項目 <BR>
     * 逆指値：　@（発注条件）逆指値 <BR>
     * W指値：　@（発注条件）W指値 <BR>
     * <BR>
     * @@param l_strOrderCond - (発注条件) <BR>
     * 　@0：DEFAULT（条件指定なし）　@1：逆指値　@2：W指値 <BR>
     * @@return boolean
     * @@roseuid 405E59270206
     */
    public boolean isHandlingPossibleOrderCond(String l_strOrderCond)
    {
        boolean l_result = false;

        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderCond))
        {
            //引数の発注条件が、DEFAULT（条件指定なし）の場合はtrueを返却する
            l_result = true;
        }
        else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderCond))
        {
            //逆指値
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getStopOrder()))
            {
                l_result = true;
            }
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderCond))
        {
            //W指値
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getWLimitOrder()))
            {
                l_result = true;
            }

        }
        else
        {
            l_result = false;
        }

        return l_result;

    }

    /**
     * (is出来るまで注文可能日) <BR>
     * 引数の失効日が出来るまで注文可能日かどうかを判定する。
     * <BR>
     * １）　@引数の注文失効日が営業日でない場合はfalseを返却する。 <BR>
     * <BR>
     * ２）　@this.get出来るまで注文失効日指定にて最終日指定区分を取得する。 <BR>
     * <BR>
     * ３）　@this.取引最終日≠nullかつ最終日指定区分が『1：最終日のみ指定可』の場合 <BR>
     * 　@　@　@trueを返却する。 <BR>
     * <BR>
     * ４）　@this.get出来るまで注文最終日()にて最終日を取得する。 <BR>
     * 　@　@　@nullが返却された場合は、「出来るまで注文取扱不可」の例外をthrowする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00413 <BR>
     * <BR>
     * ５）　@指定日付が、（発注日～最終日）の範囲外であればfalseを返却する。 <BR>
     * <BR>
     * ６）　@最終日指定区分が『0：期間内失効日ユーザ指定可』の場合、trueを返却する。 <BR>
     * 　@　@　@最終日指定区分が『1：最終日のみ指定可』の場合 <BR>
     * 　@　@　@指定日付＝最終日付の場合trueを返却する。 <BR>
     * 　@　@　@指定日付<>最終日付の場合falseを返却する。<BR>
     * <BR>
     * @@param l_datOrderExpDate - 注文失効日
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 405E611E0120
     */
    public boolean isOrderUntilDeadLinePossibleDay(Date l_datOrderExpDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOrderUntilDeadLinePossibleDay(Date)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数の注文失効日が営業日でない場合はfalseを返却する
        Timestamp l_tsExpDate = new Timestamp(l_datOrderExpDate.getTime());
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsExpDate);
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //this.get出来るまで注文失効日指定にて
        //最終日指定区分を取得する
        String l_strCarriedOrderLapseDateSpec = getOrderUntilDeadLineExpDay();
        //this.取引最終日≠nullかつ最終日指定区分が『1：最終日のみ指定可』の場合
        //trueを返却する
        if ((this.tradingEndDate != null)
            && (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(
                l_strCarriedOrderLapseDateSpec)))
        {
            return true;
        }

        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");

        //  this.get出来るまで注文最終日()にて最終日を取得する。
        String l_strEndDay;
        Date l_datEndDay = this.getOrderUntilDeadLineEndDay();
        if(l_datEndDay == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00413,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            l_strEndDay = l_format.format(l_datEndDay);    
        }
        
        
        //発注日取得
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        String l_strBizDay = l_format.format(l_datBizDate);
        
        //指定日付(引数の失効日)
        String l_strOrderExpDay = l_format.format(l_datOrderExpDate);

        //指定日付が、（発注日～最終日）の範囲外であればfalseを返却する
        if ((l_strOrderExpDay.compareTo(l_strBizDay) < 0)
            || (l_strOrderExpDay.compareTo(l_strEndDay) > 0))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //最終日指定区分が『0：期間内失効日ユーザ指定可』の場合、
        //trueを返却する。 
        //最終日指定区分が『1：最終日のみ指定可』の場合 
        //指定日付＝最終日付の場合trueを返却する。 
        //指定日付<>最終日付の場合falseを返却する。
        boolean l_result = false;
        if (WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES.equals(l_strCarriedOrderLapseDateSpec))
        {
            l_result = true;
        }
        else if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
        {
            if (l_strOrderExpDay.compareTo(l_strEndDay) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
        
    }
    
    /**
     * (is出来るまで注文可能日) <BR>
     * 引数の失効日が出来るまで注文可能日かどうかを判定する。<BR>
     * (引数の原注文発注日から起算した出来るまで注文最終日を使用する。)<BR>
     * <BR>
     * １）　@引数の注文失効日が営業日でない場合はfalseを返却する。 <BR>
     * <BR>
     * ２）　@this.get出来るまで注文失効日指定にて最終日指定区分を取得する。 <BR>
     * <BR>
     * ３）　@this.取引最終日≠nullかつ最終日指定区分が『1：最終日のみ指定可』の場合 <BR>
     * 　@　@　@trueを返却する。 <BR>
     * <BR>
     * ４）　@this.get出来るまで注文最終日(引数.原注文発注日)にて最終日を取得する。 <BR>
     * 　@　@　@nullが返却された場合は、「出来るまで注文取扱不可」の例外をthrowする。 <BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00413 <BR>
     * <BR>
     * ５）　@指定日付が、（引数.原注文発注日～最終日）の範囲外であればfalseを返却する。 <BR>
     * <BR>
     * ６）　@最終日指定区分が『0：期間内失効日ユーザ指定可』の場合、trueを返却する。 <BR>
     * 　@　@　@最終日指定区分が『1：最終日のみ指定可』の場合 <BR>
     * 　@　@　@指定日付＝最終日付の場合trueを返却する。 <BR>
     * 　@　@　@指定日付<>最終日付の場合falseを返却する。 <BR>
     * <BR>
     * @@param l_datOrderExpDate - 注文失効日
     * @@param l_datOrderBizDate - 原注文の発注日
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isOrderUntilDeadLinePossibleDay(
        Date l_datOrderExpDate,
        Date l_datOrderBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOrderUntilDeadLinePossibleDay(Date,Date)";
        log.entering(STR_METHOD_NAME);
        
        //１）引数の注文失効日が営業日でない場合はfalseを返却する
        Timestamp l_tsExpDate = new Timestamp(l_datOrderExpDate.getTime());
        String l_strBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsExpDate);
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２）get出来るまで注文失効日指定にて最終日指定区分を取得する。
        String l_strCarriedOrderLapseDateSpec = getOrderUntilDeadLineExpDay();
        //３）this.取引最終日≠nullかつ最終日指定区分が『1：最終日のみ指定可』の場合
        //trueを返却する
        if ((this.tradingEndDate != null)
            && (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(
                l_strCarriedOrderLapseDateSpec)))
        {
            return true;
        }

        //４）this.get出来るまで注文最終日(引数.原注文発注日)にて 
        // 最終日を取得する。nullが返却された場合は、
        // 「出来るまで注文取扱不可」の例外をthrowする 
        Date l_datEndDay = this.getOrderUntilDeadLineEndDay(l_datOrderBizDate);
        if(l_datEndDay == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00413,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        //指定日付(引数の失効日)
        String l_strOrderExpDay = l_format.format(l_datOrderExpDate);
        //原注文の発注日
        String l_strBizDay = l_format.format(l_datOrderBizDate);
        //最終日
        String l_strEndDay = l_format.format(l_datEndDay);  
        
        //５）指定日付が、（引数.原注文発注日～最終日）の範囲外で 
        //あればfalseを返却する。
        if ((l_strOrderExpDay.compareTo(l_strBizDay) < 0)
            || (l_strOrderExpDay.compareTo(l_strEndDay) > 0))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //６）最終日指定区分が『0：期間内失効日ユーザ指定可』の場合、
        //trueを返却する。 
        //最終日指定区分が『1：最終日のみ指定可』の場合 
        //指定日付＝最終日付の場合trueを返却する。 
        //指定日付<>最終日付の場合falseを返却する。
        boolean l_result = false;
        if (l_strCarriedOrderLapseDateSpec.equals(WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES))
        {
            l_result = true;
        }
        else if (l_strCarriedOrderLapseDateSpec.equals(WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA))
        {
            if (l_strOrderExpDay.compareTo(l_strEndDay) == 0)
            {
                l_result = true;
            }
            else
            {
                l_result = false;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
        
    }

    /**
     * (get出来るまで注文最終日) <BR>
     * 出来るまで注文可能な最終日付を取得する。 <BR>
     * <BR>
     * 　@this.取扱可能注文条件Rowの「出来るまで注文」項目が <BR>
     *   示す有効期限最終日を取得する。 <BR>
     * 　@取得した日付が非営業日であった場合、 <BR>
     *   直前の営業日（ex. 取得した日付が土曜日だったら、 <BR>
     *   その前日の金曜日）を返却する。 <BR>
     * <BR>
     * 　@※ ”取扱可能（日数指定）”の場合は、 <BR>
     *   発注日(*1)～「（出来るまで注文）有効日数」日後間の営業日を対象とする。 <BR>
     * 　@※ 「取扱不可」の場合は、nullを返却する。 <BR>
     * <BR>
     * (*1) 発注日 <BR>
     * 取引時間管理.get発注日()にて取得。 <BR>
     * <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 405E61B701C5
     */
    public Date getOrderUntilDeadLineEndDay() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOrderUntilDeadLineEndDay()";
        log.entering(STR_METHOD_NAME);

        //get発注日
        Date l_datOrderBizDate;

        l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //get出来るまで注文
        String l_strCarriedOrder = this.enableOrderConditionRow.getCarriedOrder();
        Calendar l_calender = Calendar.getInstance();
        l_calender.setTime(l_datOrderBizDate);

        int l_intYear = l_calender.get(Calendar.YEAR);
        int l_intMonth = l_calender.get(Calendar.MONTH);
        int l_intDate = l_calender.get(Calendar.DATE);

        //「取扱不可」の場合は、nullを返却する
        if (WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(l_strCarriedOrder))
        {
            return null;
        }

        //取扱可能（週末営業日まで)
        if (WEB3CarriedOrderDef.DEALT_TO_WEEK_END.equals(l_strCarriedOrder))
        {
            if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
            {
                l_intDate = l_intDate + 6;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY)
            {
                l_intDate = l_intDate + 5;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
            {
                l_intDate = l_intDate + 4;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
            {
                l_intDate = l_intDate + 3;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
            {
                l_intDate = l_intDate + 2;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            {
                l_intDate = l_intDate + 1;
            }
        }

        //取扱可能（月末営業日まで）
        if (WEB3CarriedOrderDef.DEALT_TO_MONTH_END.equals(l_strCarriedOrder))
        {
            l_intMonth = l_intMonth + 1;
            l_intDate = 0;
        }

        //取扱可能（3ヶ月後）
        if (WEB3CarriedOrderDef.DEALT_TO_THREE_MONTH.equals(l_strCarriedOrder))
        {
            l_intMonth = l_intMonth + 4;
            l_intDate = 0;
        }
        
        //有効期限最終日を取得する
        l_calender.set(Calendar.YEAR,l_intYear);
        l_calender.set(Calendar.MONTH,l_intMonth);
        l_calender.set(Calendar.DATE,l_intDate);
        Date l_orderExecEndDay = l_calender.getTime();        

        //取扱可能（日数指定） ： 有効期限最終日を取得する
        //（注）非営業日（土日など）の考慮をするべき。
        //（注）有効期限最終日は、発注日を含めて算出する
        if (WEB3CarriedOrderDef.DEALT_DESIGNATA_DAYS.equals(l_strCarriedOrder))
        {
            //set 発注日
            Timestamp l_tsOrderBizDate = new Timestamp(l_datOrderBizDate.getTime());
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsOrderBizDate);
            //get （出来るまで注文）有効日数
            int l_intCarriedOrderDayCount = this.enableOrderConditionRow.getCarriedOrderDayCount();
            if (l_intCarriedOrderDayCount > 1)
            {
                l_orderExecEndDay = l_genBizDate.roll(l_intCarriedOrderDayCount - 1);
                log.debug(" ****** [発注日] ： " + l_tsOrderBizDate);
                log.debug(" ****** [（出来るまで注文）有効日数] ： " + l_intCarriedOrderDayCount);
                log.debug(" ****** [出来るまで注文最終日] ： " + l_orderExecEndDay);
            }
            else
            {
                l_orderExecEndDay = l_datOrderBizDate;
            }
        }
        
        //取得した日付が非営業日であった場合、直前の営業日を返却する。
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_orderExecEndDay.getTime()));
        if (l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
        {
            WEB3GentradeBizDate l_dateCalc =
                new WEB3GentradeBizDate(
                    new Timestamp(l_orderExecEndDay.getTime()));
            l_orderExecEndDay = l_dateCalc.roll(-1);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderExecEndDay;
    }

    /**
     * (get出来るまで注文最終日) <BR>
     * 出来るまで注文可能な最終日付を取得する。 <BR>
     * （開始日指定） <BR>
     * <BR>
     * 引数の「出来るまで注文from日付」＝nullの場合は、 <BR>
     * this.get出来るまで注文最終日(void)に委譲する。 <BR>
     * 引数の「出来るまで注文from日付」≠nullの場合は、以下の処理を行う。 <BR>
     * <BR>
     * 　@this.取扱可能注文条件Rowの「出来るまで注文」項目が <BR>
     *   示す有効期限最終日を取得する。 <BR>
     * 　@取得した日付が非営業日であった場合、 <BR>
     *   直前の営業日（ex. 取得した日付が土曜日だったら、 <BR>
     *   その前日の金曜日）を返却する。 <BR>
     * <BR>
     * 　@※ ”取扱可能（日数指定）”の場合は、
     *   引数の「出来るまで注文from日付」～ <BR>
     *   「（出来るまで注文）有効日数」日後間の営業日を対象とする。 <BR>
     * 　@※ 「取扱不可」の場合は、nullを返却する。 <BR>
     * <BR>
     * @@param l_datOrderUntilDeadLineFromDate - (出来るまで注文from日付) <BR>
     * <BR>
     * 出来るまで注文の最終日を求める際の基準となるfrom日付。 <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 4069157403C0
     */
    public Date getOrderUntilDeadLineEndDay(Date l_datOrderUntilDeadLineFromDate) 
       throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOrderUntilDeadLineEndDay(Date)";
        log.entering(STR_METHOD_NAME);

        //引数の「出来るまで注文from日付」＝nullの場合は、
        //this.get出来るまで注文最終日(void)に委譲する。 
        if (l_datOrderUntilDeadLineFromDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return getOrderUntilDeadLineEndDay();
        }

        //get出来るまで注文
        String l_strCarriedOrder =
            this.enableOrderConditionRow.getCarriedOrder();
        Calendar l_calender = Calendar.getInstance();
        l_calender.setTime(l_datOrderUntilDeadLineFromDate);

        int l_intYear = l_calender.get(Calendar.YEAR);
        int l_intMonth = l_calender.get(Calendar.MONTH);
        int l_intDate = l_calender.get(Calendar.DATE);

        //「取扱不可」の場合は、nullを返却する
        if (WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(l_strCarriedOrder))
        {
            return null;
        }

        //取扱可能（週末営業日まで)
        if (WEB3CarriedOrderDef.DEALT_TO_WEEK_END.equals(l_strCarriedOrder))
        {
            if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
            {
                l_intDate = l_intDate + 6;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY)
            {
                l_intDate = l_intDate + 5;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
            {
                l_intDate = l_intDate + 4;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY)
            {
                l_intDate = l_intDate + 3;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY)
            {
                l_intDate = l_intDate + 2;
            }
            else if (l_calender.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
            {
                l_intDate = l_intDate + 1;
            }
        }

        //取扱可能（月末営業日まで）
        if (WEB3CarriedOrderDef.DEALT_TO_MONTH_END.equals(l_strCarriedOrder))
        {
            l_intMonth = l_intMonth + 1;
            l_intDate = 0;
        }

        //取扱可能（3ヶ月後）
        if (WEB3CarriedOrderDef.DEALT_TO_THREE_MONTH.equals(l_strCarriedOrder))
        {
            l_intMonth = l_intMonth + 4;
            l_intDate = 0;
        }

        //有効期限最終日を取得する
        l_calender.set(Calendar.YEAR,l_intYear);
        l_calender.set(Calendar.MONTH,l_intMonth);
        l_calender.set(Calendar.DATE,l_intDate);
        Date l_orderExecEndDay = l_calender.getTime();
        
        //取扱可能（日数指定） ： 有効期限最終日を取得する
        //（注）非営業日（土日など）の考慮をするべき。
        //（注）有効期限最終日は、出来るまで注文from日付を含めて算出する
        if (WEB3CarriedOrderDef.DEALT_DESIGNATA_DAYS.equals(l_strCarriedOrder))
        {
            //set 出来るまで注文from日付
            Timestamp l_tsOrderFromDate = new Timestamp(l_datOrderUntilDeadLineFromDate.getTime());
            WEB3GentradeBizDate l_genBizDate = new WEB3GentradeBizDate(l_tsOrderFromDate);
            //get （出来るまで注文）有効日数
            int l_intCarriedOrderDayCount = this.enableOrderConditionRow.getCarriedOrderDayCount();
            if (l_intCarriedOrderDayCount > 1)
            {
                l_orderExecEndDay = l_genBizDate.roll(l_intCarriedOrderDayCount - 1);
                log.debug(" ****** [出来るまで注文from日付] ： " + l_tsOrderFromDate);
                log.debug(" ****** [（出来るまで注文）有効日数] ： " + l_intCarriedOrderDayCount);
                log.debug(" ****** [出来るまで注文最終日] ： " + l_orderExecEndDay);
            }
            else
            {
                l_orderExecEndDay = l_datOrderUntilDeadLineFromDate;
            }
        }
        
        //取得した日付が非営業日であった場合、直前の営業日を返却する。
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_orderExecEndDay.getTime()));
        if (l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
        {
            WEB3GentradeBizDate l_dateCalc =
                new WEB3GentradeBizDate(
                    new Timestamp(l_orderExecEndDay.getTime()));
            l_orderExecEndDay = l_dateCalc.roll(-1);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderExecEndDay;

    }

    /**
     * (get出来るまで注文失効日指定) <BR>
     * <BR>
     * 出来るまで注文失効日指定を取得する。 <BR>
     * <BR>
     * 　@this.取扱可能注文条件Rowの「出来るまで注文失効日指定」項目が <BR>
     *   示す出来るまで注文失効日指定を取得する。 <BR>
     * 　@取得した出来るまで注文失効日指定を返却する。 <BR>
     * @@return String
     * @@roseuid 4063BE8802C8
     */
    public String getOrderUntilDeadLineExpDay()
    {
        return this.enableOrderConditionRow.getCarriedOrderLapseDateSpec();
    }

    /**
     * (get出来るまで注文開始日) <BR>
     * <BR>
     * 出来るまで注文可能な開始日付を取得する。<BR> 
     * <BR>
     * ・this.取扱可能注文条件Rowの「出来るまで注文」項目 
     *   ≠「取扱不可」の場合 <BR>
     *      「(出来るまで注文)失効日指定」＝”最終日のみ指定可”の <BR> 
     *      場合は、this.get出来るまで注文最終日(void)に委譲する。<BR> 
     *      「(出来るまで注文)失効日指定」≠”最終日のみ指定可”の <BR> 
     *      場合は、発注日(*1)を返却する。<BR> 
     *  <BR>
     * ・「取扱不可」の場合は、nullを返却する。<BR> 
     *  <BR>
     * (*1) 発注日 <BR>
     * 取引時間管理.get発注日()にて取得。 <BR>
     * @@return Date
     * @@roseuid 406AAFD2013E
     */
    public Date getOrderUntilDeadLineStartDay() 
    {
        Date l_datStartDate = null;
        try
        {
            //this.取扱可能注文条件Rowの「出来るまで注文」項目≠「取扱不可」の場合
            if ( ! WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
            {
                String l_strCarriedOrderLapseDateSpec = 
                    this.enableOrderConditionRow.getCarriedOrderLapseDateSpec();
                if(WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
                {
                    //「(出来るまで注文)失効日指定」＝”最終日のみ指定可”の場合は、
                    //this.get出来るまで注文最終日(void)に委譲する。
                    l_datStartDate = this.getOrderUntilDeadLineEndDay();
                }
                else
                {
                    //「(出来るまで注文)失効日指定」≠”最終日のみ指定可”の場合は、
                    //発注日(*1)を返却する。
                    l_datStartDate =
                        WEB3GentradeTradingTimeManagement.getOrderBizDate();
                }
            }
            else
            {
                //「取扱不可」の場合は、nullを返却する
                l_datStartDate = null;
            }
        }
        catch(WEB3SystemLayerException wse)
        {
            log.error("getOrderUntilDeadLineStartDay",wse);  
            l_datStartDate = null;
        }
        
        return l_datStartDate;
    }

    /**
     * (get出来るまで注文開始日) <BR>
     *  <BR>
     * 出来るまで注文可能な開始日付を取得する。<BR> 
     * （開始日指定）<BR> 
     *  <BR>
     * 引数の「出来るまで注文from日付」＝nullの場合は、<BR>
     *   this.get出来るまで注文開始日(void)に委譲する。 <BR>
     * 引数の「出来るまで注文from日付」≠nullの場合は、<BR>
     *   以下の処理を行う。<BR> 
     *  <BR>
     * ・this.取扱可能注文条件Rowの「出来るまで注文」項目<BR>
     *   ≠「取扱不可」の場合<BR> 
     *     「(出来るまで注文)失効日指定」＝”最終日のみ指定可”の<BR>
     *     場合は、this.get出来るまで注文最終日(Date)に委譲する。<BR> 
     *     「(出来るまで注文)失効日指定」≠”最終日のみ指定可”の<BR>
     *     場合は、引数をそのまま返却する。<BR> 
     *  <BR>
     * ・「取扱不可」の場合は、nullを返却する。<BR>
     * <BR>
     * @@param l_datOrderUntilDeadLineFromDate - (出来るまで注文from日付) <BR>
     * <BR>
     * 出来るまで注文の最終日を求める際の基準となるfrom日付。 <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 406AB3580374
     */
    public Date getOrderUntilDeadLineStartDay(Date l_datOrderUntilDeadLineFromDate) 
        throws WEB3SystemLayerException
    {
        //引数の「出来るまで注文from日付」＝nullの場合は、 
        // this.get出来るまで注文開始日(void)に委譲する
        if (l_datOrderUntilDeadLineFromDate == null)
        {
            return this.getOrderUntilDeadLineStartDay();
        }
        
        Date l_datStartDate;
        //this.取扱可能注文条件Rowの「出来るまで注文」項目≠「取扱不可」の場合 
        if ( ! WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
        {
            String l_strCarriedOrderLapseDateSpec = 
                this.enableOrderConditionRow.getCarriedOrderLapseDateSpec();
            if(WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
            {
                //「(出来るまで注文)失効日指定」＝”最終日のみ指定可”の場合は、
                //this.get出来るまで注文最終日(Date)に委譲する
                l_datStartDate = this.getOrderUntilDeadLineEndDay(l_datOrderUntilDeadLineFromDate);
            }
            else
            {
                //「(出来るまで注文)失効日指定」≠”最終日のみ指定可”の場合は、
                //引数をそのまま返却する
                l_datStartDate = l_datOrderUntilDeadLineFromDate;
            }
        }
        else
        {
            //「取扱不可」の場合は、nullを返却する
            l_datStartDate = null;
        }
        
        return l_datStartDate;
    }
    
    /**
     * (get出来るまで注文開始日) <BR>
     *  <BR>
     * 出来るまで注文可能な開始日付を取得する。<BR>
     *  （注文訂正時に使用）<BR>
     *  <BR>
     * ・this.取扱可能注文条件Rowの「出来るまで注文」項目≠「取扱不可」の場合<BR>
     *   「(出来るまで注文)失効日指定」＝”最終日のみ指定可”の場合は、<BR>
     *   引数の「出来るまで注文to日付」をそのまま返却する。<BR>
     *  <BR>
     * 「(出来るまで注文)失効日指定」≠”最終日のみ指定可”の場合は、<BR>
     *   this.get出来るまで注文開始日(引数の「出来るまで注文from日付」)に<BR>
     *   delegateする。<BR>
     * <BR>
     * ・ 「取扱不可」の場合は、nullを返却する。<BR>
     *   <BR>
     * @@param l_orderUntilDeadLineFromDate - (出来るまで注文from日付) <BR>
     * @@param l_orderUntilDeadLineToDate - (出来るまで注文to日付) <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     */
    public Date getOrderUntilDeadLineStartDay(
        Date l_orderUntilDeadLineFromDate,
        Date l_orderUntilDeadLineToDate)
        throws WEB3SystemLayerException
    {
        Date l_datStartDate;
        
        //this.取扱可能注文条件Rowの「出来るまで注文」項目≠「取扱不可」の場合
        if ( ! WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(this.enableOrderConditionRow.getCarriedOrder()))
        {
            String l_strCarriedOrderLapseDateSpec = 
                this.enableOrderConditionRow.getCarriedOrderLapseDateSpec();
            if(WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strCarriedOrderLapseDateSpec))
            {
                //「(出来るまで注文)失効日指定」＝”最終日のみ指定可”の場合は、
                //引数の「出来るまで注文to日付」をそのまま返却する。
                l_datStartDate = l_orderUntilDeadLineToDate;
            }
            else
            {
                //「(出来るまで注文)失効日指定」≠”最終日のみ指定可”の場合は、
                //this.get出来るまで注文開始日(引数の「出来るまで注文from日付」)にdelegateする。
                l_datStartDate = this.getOrderUntilDeadLineStartDay(l_orderUntilDeadLineFromDate);
            }
        }
        else
        {
            //「取扱不可」の場合は、nullを返却する
            l_datStartDate = null;
        }
        
        return l_datStartDate;
    }

    /**
     * (is出来るまで注文取扱可能) <BR>
     * <BR>
     * 出来るまで注文が取扱可能かを判定する。 <BR>
     * <BR>
     * ※「夕場まで注文」に対応した商品はこのメソッドの使用不可 <BR>
     * <BR>
     * １）　@this.取扱可能注文期限区分取得( )をコールし、<BR>
     * 　@　@　@戻り値Listの要素数が1のみの場合はfalseを、 <BR>
     *       要素数が2の場合はtrueを返す。 <BR>
     * 　@　@　@上記以外の場合は、例外をthrowする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00131 <BR>
     * <BR>
     * (*1) 注文期限区分 <BR>
     * 1：当日限り <BR>
     * 2：出来るまで注文 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 406B9F07024E
     */
    public boolean isOrderUntilDeadLinePossibleHandling()
        throws WEB3BaseException
        
    {
        final String STR_METHOD_NAME = "isOrderUntilDeadLinePossibleHandling()";
        
        String[] l_strHandingPossibleExpirationDateTypes =
            this.getHandlingPossibleExpirationDateType();
        if (l_strHandingPossibleExpirationDateTypes.length == 1)
        {
            return false;
        }
        else if (l_strHandingPossibleExpirationDateTypes.length == 2)
        {
            return true;
        }
        else
        {
            String l_strMessage  = 
                "this.取扱可能注文期限区分取得( )をコールし、戻り値Listの要素数が1,2以外の場合";
            WEB3BusinessLayerException l_wbe = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00131,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strMessage);
            log.error(STR_METHOD_NAME,l_wbe);
            throw l_wbe;
        }
    }

    /**
     * (is出来るまで注文取扱可能<取引最終日考慮>) <BR>
     * <BR>
     * 取引最終日を考慮して、出来るまで注文が取扱可能かを判定する。 <BR>
     * <BR>
     * 　@　@・this.取扱可能注文条件Rowから、項目「出来るまで注文」の値を取得する。 <BR>
     * <BR>
     * 　@　@［「出来るまで注文 == ”取扱不可”」の場合］ <BR>
     * 　@　@　@　@falseを返却する。 <BR>
     * <BR>
     * 　@　@［「出来るまで注文 == ”取扱可能”」の場合］ <BR>
     * 　@　@　@　@this.取引最終日≠null <BR>
     * and this.取引最終日 <= 取引時間管理.get発注日()の場合、<BR>
     * falseを返却する。 <BR>
     * 　@　@　@　@以外、trueを返却する。 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     * @@roseuid 406B9F07024E
     */
    public boolean isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering()";
        log.entering(STR_METHOD_NAME);

        //this.取扱可能注文条件Rowから、項目「出来るまで注文」の値を取得する。
        String l_strCarriedOrder = this.enableOrderConditionRow.getCarriedOrder();

        //「出来るまで注文 == ”取扱不可”」の場合　@falseを返却する。
        if (WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(l_strCarriedOrder))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //「出来るまで注文 == ”取扱可能”」の場合　@this.取引最終日≠null
        //and this.取引最終日 <= 取引時間管理.get発注日()の場合、falseを返却する。
        //　@　@　@　@以外、trueを返却する。
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if ((this.tradingEndDate != null)
            && (WEB3DateUtility.compare(this.tradingEndDate, l_datBizDate)) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (is夕場まで注文取扱可能) <BR>
     * <BR>
     * 夕場まで注文可能かどうかを判定する。 <BR>
     * <BR>
     * 　@　@・this.取扱可能注文条件Rowの「夕場まで注文 == ”取扱可能”」であればtrueを返却する。 <BR>
     * <BR>
     * 　@　@以外の場合、falseを返却する。<BR>
     * <BR>
     * @@return boolean
     */
    public boolean isEveningSessionOrderPossibleHandling()
    {
        final String STR_METHOD_NAME = "isEveningSessionOrderPossibleHandling()";
        log.entering(STR_METHOD_NAME);

        String l_strEveningSessionOrder =
            this.enableOrderConditionRow.getEveningSessionOrder();
        if (WEB3EveningSessionOrderDef.DEAL_ENABLE.equals(l_strEveningSessionOrder))
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
     * (is夕場まで注文取扱可能<取引最終日考慮>) <BR>
     * <BR>
     * 取引最終日を考慮して、夕場まで注文可能かどうかを判定する。 <BR>
     * <BR>
     * 　@　@・this.is夕場まで注文取扱可能()をコールする。 <BR>
     * <BR>
     * 　@　@［this.is夕場まで注文取扱可能() == falseの場合］ <BR>
     * 　@　@　@　@falseを返却する。 <BR>
     * <BR>
     * 　@　@［this.is夕場まで注文取扱可能() == trueの場合］ <BR>
     * 　@　@　@　@this.取引最終日≠null <BR>
     * and this.取引最終日 <= 取引時間管理.get発注日()の場合、<BR>
     * falseを返却する。 <BR>
     * 　@　@　@　@以外、trueを返却する。<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public boolean isEveningSessionOrderPossibleHandlingTradingEndDateConsidering()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "isEveningSessionOrderPossibleHandlingTradingEndDateConsidering()";
        log.entering(STR_METHOD_NAME);

        //this.is夕場まで注文取扱可能()をコールする。
        boolean l_blnIsEveningSessionOrderPossibleHandling =
            this.isEveningSessionOrderPossibleHandling();

        //［this.is夕場まで注文取扱可能() == falseの場合］ falseを返却する。
        if (!l_blnIsEveningSessionOrderPossibleHandling)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //［this.is夕場まで注文取扱可能() == trueの場合］　@this.取引最終日≠null
        //and this.取引最終日 <= 取引時間管理.get発注日()の場合、falseを返却する。
        //　@　@　@　@以外、trueを返却する。
        if ((this.tradingEndDate != null)
            && (WEB3DateUtility.compare(this.tradingEndDate, l_datBizDate)) <= 0)
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
     * (is成行注文可能) <BR>
     * <BR>
     * 成行注文可能かどうかを判定する。<BR>
     * <BR>
     * 新規建（is新規建 == true）の場合、<BR>
     *   買建（is買建 == true）の場合<BR>
     *    this.取扱可能注文条件Rowの成行注文（新規買建） == ”取扱可能”<BR>
     *    であれば、trueを返却する。以外、falseを返却する。<BR>
     * <BR>
     *   売建（is買建 == false）の場合<BR>
     *    this.取扱可能注文条件Rowの成行注文（新規売建） == ”取扱可能”<BR>
     *    であれば、trueを返却する。以外、falseを返却する。<BR>
     * <BR>
     * 返済（is新規建 == false）の場合、<BR>
     *    this.取扱可能注文条件Rowの成行注文（返済） == ”取扱可能”<BR>
     *    であれば、trueを返却する。以外、falseを返却する。<BR>
     * <BR>
     * @@param l_isOpenContract - （新規建） <BR>
     *     新規建取引かどうかの判定。 <BR>
     *     新規建の場合true、返済の場合false。 <BR>
     * @@param l_isBuyOrder - 買建かどうかの判別。<BR>
     *     買建の場合はtrue、売建の場合はfalse。<BR>
     * @@return boolean
     * @@roseuid 4076733901A5
     */
    public boolean isMarketOrderPossible(boolean l_isOpenContract, boolean l_isBuyOrder)
    {
        //新規建（is新規建 == true）の場合、 
        if (l_isOpenContract)
        {
            //買建（is買建 == true）の場合
            //this.取扱可能注文条件Rowの成行注文（新規買建） == ”取扱可能”
            //であれば、trueを返却する。以外、falseを返却する。
            if (l_isBuyOrder)
            {
                if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceBuyToOpen()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                //売建（is買建 == false）の場合
                //this.取扱可能注文条件Rowの成行注文（新規売建） == ”取扱可能”
                //であれば、trueを返却する。以外、falseを返却する。
                if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceSellToOpen()))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            //返済（is新規建 == false）の場合、 
            //this.取扱可能注文条件Rowの成行注文（返済） == ”取扱可能”であれば、
            //trueを返却する。 以外、falseを返却する
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMartPriceOrderSettleCont()))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * コンストラクタ。 <BR>
     * 引数の条件に一致する取扱可能注文条件オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@引数の値にて取扱可能注文条件テーブルを検索する。<BR>
     * <BR>
     * ２）　@行オブジェクトセット<BR>
     * 　@検索結果の行オブジェクト（取扱可能注文条件Row）を<BR>
     * this.取扱可能注文条件Rowにセットする。<BR>
     * <BR> 
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * @@param l_strFuturesOptionDiv - (先物／オプション区分) <BR>
     * @@param l_strMarginTradingDiv - (信用取引区分)<BR>
     *     0： DEFAULT（固定）<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     *    （WEB3DealtDefにて定義）<BR>
     * @@throws WEB3SystemLayerException<BR>
     * @@roseuid 405E4FF8009E
     */
    public WEB3GentradeHandlingOrderCond(
        String l_strInstitutionCode,
        ProductTypeEnum l_productType,
        String l_strFuturesOptionDiv,
        String l_strMarginTradingDiv,
        String l_strMarketCode)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeHandingOrderCond(String,ProductTypeEnum,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        if(WEB3MarginTradingDivDef.DEFAULT.equals(l_strMarginTradingDiv) == false)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "信用取引区分 ： DEFAULT（固定）、引数の信用取引区分 = " + l_strMarginTradingDiv);
        }
        
        EnableOrderConditionRow l_row = null;
        try
        {
            //取扱可能注文条件テーブル
            l_row = EnableOrderConditionDao.findRowByPk(
                l_strInstitutionCode,
                l_productType,
                l_strFuturesOptionDiv,
                l_strMarginTradingDiv,
                l_strMarketCode);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        this.enableOrderConditionRow = l_row;

        log.exiting(STR_METHOD_NAME);
    }


    /**
     * (取扱可能値段条件取得) <BR>
     * <BR>
     * 取扱可能値段条件を取得する。<BR>
     * <BR>
     * this.取扱可能注文条件Rowの（値段条件）項目で”取扱可能”に<BR>
     * なっている項目に該当する値段条件コード(*1)を配列にて返却する。<BR>
     * 但し、”DEFAULT(条件指定なし)”は必ず配列に含める。<BR>
     * <BR>
     * (*1) 値段条件コード<BR>
     *  （WEB3GentradePriceCondDefにて定義）<BR>
     * 0：　@DEFAULT(条件指定なし)<BR>
     * 1：　@現在値指値注文<BR>
     * 3：　@優先指値注文<BR>
     * 5：　@成行残数指値注文<BR>
     * 7：　@成行残数取消注文<BR>
     * @@return String[]
     * @@roseuid 4076733901A5
     */
    public String[] getHandlingPriceCond()
    {
        final String STR_METHOD_NAME = "getHandlingPriceCond()";
        log.entering(STR_METHOD_NAME);

        ArrayList l_lstHandlingPriceConds = new ArrayList();

        //但し、”DEFAULT(条件指定なし)”は必ず配列に含める。
        l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.DEFAULT);
        
        //現在値指値注文
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getCurrentPriceOrder()))
        {
            l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.CURRENT_PRICE_ORDER);
        }
        
        //優先指値注文
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getEaseCurrentPriceOrder()))
        {
            l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.EASE_CURRENT_PRICE_ORDER);
        }

        //成行残数指値注文
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMarketPriceRestLimitPrice()))
        {
            l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.MARKET_PRICE_REST_LIMIT_PRICE);
        }

        //成行残数取消注文
        if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMarketPriceRestCancel()))
        {
            l_lstHandlingPriceConds.add(WEB3GentradePriceCondDef.MARKET_PRICE_REST_CANCEL);
        }

        int l_intSize = l_lstHandlingPriceConds.size();
        String[] l_handlingPriceConds = new String[l_intSize];
        for(int i = 0; i < l_intSize; i++)
        {
            l_handlingPriceConds[i] = (String)l_lstHandlingPriceConds.get(i);
        }   
        
        log.exiting(STR_METHOD_NAME);
        return l_handlingPriceConds;
    }

    /**
     * (is取扱可能値段条件) <BR>
     * <BR>
     * 指定値段条件が取扱可能かを判定する。 <BR>
     * <BR>
     * 引数の値段条件が、”0（DEFAULT(条件指定なし)）”の場合はtrueを返却する。<BR>
     * <BR>
     * 以外、this.取扱可能注文条件Rowの（値段条件）項目で引数の値段条件に<BR>
     * 対応する項目(*1)が、”取扱可能”であればtrue、以外はfalseを返却する。 <BR>
     * <BR>
     * (*1) 値段条件対応項目<BR>
     * 1（現在値指値注文）　@ ：　@（値段条件）現在値指値<BR>
     * 3（優先指値注文）　@　@　@：　@（値段条件）優先指値<BR>
     * 5（成行残数指値注文）：　@（値段条件）成行残数指値<BR>
     * 7（成行残数取消注文）：　@（値段条件）成行残数取消<BR>
     * <BR>
     * @@param l_strPriceCond - (値段条件)
     *  （WEB3GentradePriceCondDefにて定義）<BR>
     * @@return boolean
     * @@roseuid 4076733901A5
     */
    public boolean isHandlingPriceCond(String l_strPriceCond)
    {
        final String STR_METHOD_NAME = "isHandlingPriceCond(String)";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3GentradePriceCondDef.DEFAULT.equals(l_strPriceCond))
        {
            //”0（DEFAULT(条件指定なし)）”の場合はtrueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;    
        }
        
        boolean l_isHandlingPriceCond = false;
        
        if (WEB3GentradePriceCondDef.CURRENT_PRICE_ORDER.equals(l_strPriceCond))
        {
            //現在値指値注文    
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getCurrentPriceOrder()))
            {
                //”取扱可能”であればtrue
                l_isHandlingPriceCond =  true;
            }
        }
        else if (WEB3GentradePriceCondDef.EASE_CURRENT_PRICE_ORDER.equals(l_strPriceCond))
        {
            //優先指値注文    
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getEaseCurrentPriceOrder()))
            {
                //”取扱可能”であればtrue
                l_isHandlingPriceCond = true;
            }
        }
        else if (WEB3GentradePriceCondDef.MARKET_PRICE_REST_LIMIT_PRICE.equals(l_strPriceCond))
        {
            //成行残数指値注文    
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMarketPriceRestLimitPrice()))
            {
                //”取扱可能”であればtrue
                l_isHandlingPriceCond = true;
            }
        }
        else if (WEB3GentradePriceCondDef.MARKET_PRICE_REST_CANCEL.equals(l_strPriceCond))
        {
            //成行残数取消注文
            if (WEB3DealtDef.CAN_DEALT.equals(this.enableOrderConditionRow.getMarketPriceRestCancel()))
            {
                //”取扱可能”であればtrue
                l_isHandlingPriceCond = true;
            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "値段条件 = " + l_strPriceCond);
        }

        log.exiting(STR_METHOD_NAME);
        return l_isHandlingPriceCond;
    }

    /**
     * (is取扱可能執行条件) <BR>
     * <BR>
     * 指定執行条件が取扱可能かを判定する。 <BR>
     * <BR>
     * 引数の執行条件が、条件なし（NONE）の場合はtrueを返却する。 <BR>
     * <BR>
     * 以外、this.取扱可能注文条件Rowの（執行条件）項目で <BR>
     * 引数の執行条件に対応する項目(*1)が、”取扱可能”であればtrue、 <BR>
     * 以外はfalseを返却する。 <BR>
     * <BR>
     * (*1) 執行条件対応項目 <BR>
     * LIMIT_PRICE ：（注文単価区分）指値 <BR>
     * MARKET_PRICE ：（注文単価区分）成行 <BR>
     * AT_MARKET_OPEN ：（執行条件）寄り <BR>
     * AT_MARKET_CLOSE ：（執行条件）引け <BR>
     * AT_MARKET_CLOSE_NOT_EXECUTED ：（執行条件）不出来引け成行 <BR>
     * <BR>
     * (*)引数のIfoExecutionConditionTypeに対応するFeqExecutionConditionTypeに変換して、<BR>
     * this.is取扱可能執行条件（EqTypeExecutionConditionType）に処理を委譲する。 <BR>
     * <BR>
     * @@param l_executionCondType 執行条件
     * @@return boolean
     * @@roseuid 405E550202F0
     */
    public boolean isHandlingPossibleExecCond(FeqExecutionConditionType l_executionCondType)
    {
        boolean l_result = true;
            
        if (FeqExecutionConditionType.AT_MARKET_OPEN.equals(l_executionCondType))
        {
            //AT_MARKET_OPEN ：（執行条件）寄り
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_OPEN);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE.equals(l_executionCondType))
        {
            //AT_MARKET_CLOSE ：（執行条件）引け
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionCondType))
        {
            //AT_MARKET_CLOSE_NOT_EXECUTED ：（執行条件）不出来引け成行
            l_result = this.isHandlingPossibleExecCond(EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        }
    
        return l_result;
    }

    /**
     * (set取引最終日) <BR>
     * <BR>
     * 取引最終日をセットする。 <BR>
     * <BR>
     * this.取引最終日に引数の値をセットする。 <BR>
     * <BR>
     * @@param l_datTradingEndDate 取引最終日
     */
    public void setTradingEndDate(Date l_datTradingEndDate)
    {
        this.tradingEndDate = l_datTradingEndDate;
    }

    /**
     * (get出来るまで注文最終日<取引最終日考慮>) <BR>
     * <BR>
     * 取引最終日を考慮した出来るまで注文可能な最終日付を取得する。  <BR>
     * <BR>
     * １）　@以下の条件で注文有効期限最終日を取得する。 <BR>
     * <BR>
     * 　@１－１）　@パラメータ.原注文発注日＝nullの場合 <BR>
     * 　@　@　@　@　@　@this.get出来るまで注文最終日(void)をコールする。 <BR>
     * <BR>
     * 　@１－２）　@パラメータ.原注文発注日≠nullの場合 <BR>
     * 　@　@　@　@　@　@this.get出来るまで注文最終日(引数.原注文発注日)をコールする。 <BR>
     * <BR>
     * ２）　@１）で戻り値がnull（出来るまで注文取扱不可）の場合、nullを返却する。 <BR>
     * <BR>
     * ３）　@上記以外の場合 <BR>
     * <BR>
     * 　@３－１）　@this.取引最終日＝nullの場合、<BR>
     * １）で取得した注文有効期限最終日を返却する。 <BR>
     * <BR>
     * 　@３－２）　@上記以外の場合、this.取引最終日と注文有効期限最終日を比較し <BR>
     * 　@　@　@　@　@　@小さい方の値を返却する。<BR>
     * <BR>
     * @@param l_datOrderBizDate 原注文発注日
     * @@return Date
     * @@throws WEB3SystemLayerException
     */
    public Date getOrderUntilDeadLineEndDayTradingEndDateConsidering(
        Date l_datOrderBizDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getOrderUntilDeadLineEndDayTradingEndDateConsidering(Date)";
        log.entering(STR_METHOD_NAME);

        //１）　@注文有効期限最終日を取得する。
        Date l_datOrderExpirationEndDay = null;
        if (l_datOrderBizDate == null)
        {
            l_datOrderExpirationEndDay = this.getOrderUntilDeadLineEndDay();
        }
        else
        {
            l_datOrderExpirationEndDay =
                this.getOrderUntilDeadLineEndDay(l_datOrderBizDate);
        }

        //２）　@１）で戻り値がnull（出来るまで注文取扱不可）の場合、nullを返却する。
        if (l_datOrderExpirationEndDay == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //３－１）　@this.取引最終日＝nullの場合、
        //１）で取得した注文有効期限最終日を返却する。
        else if (this.tradingEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datOrderExpirationEndDay;
        }
        //３－２）　@上記以外の場合、this.取引最終日と注文有効期限最終日を比較し
        //　@　@　@　@　@　@小さい方の値を返却する。
        else
        {
            if (WEB3DateUtility.compare(this.tradingEndDate, l_datOrderExpirationEndDay) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                return this.tradingEndDate;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_datOrderExpirationEndDay;
            }
        }
    }

    /**
     * (get出来るまで注文開始日<取引最終日考慮>) <BR>
     * <BR>
     * 取引最終日を考慮した出来るまで注文開始日を取得する。  <BR>
     * <BR>
     * １）　@以下の条件で出来るまで注文開始日を取得する。  <BR>
     * <BR>
     * 　@１－１）　@パラメータ.出来るまで注文開始日＝nullの場合  <BR>
     * 　@　@　@　@　@　@this.get出来るまで注文開始日(void)をコールする。  <BR>
     * <BR>
     * 　@１－２）　@パラメータ.出来るまで注文開始日≠nullの場合  <BR>
     * 　@　@　@　@　@　@this.get出来るまで注文開始日(パラメータ.出来るまで注文開始日)をコールする。  <BR>
     * <BR>
     * ２）　@返却値を取得する。 <BR>
     * <BR>
     * 　@２－１）　@１）で戻り値がnull（出来るまで注文取扱不可）の場合、nullを返却する。  <BR>
     * <BR>
     * 　@２－２）　@上記以外の場合  <BR>
     * <BR>
     * 　@　@２－２－１）　@this.取引最終日＝nullの場合、<BR>
     * １）で取得した出来るまで注文開始日を返却する。  <BR>
     * <BR>
     * 　@　@２－２－２）　@上記以外の場合、this.取引最終日と<BR>
     * １）で取得した出来るまで注文開始日を比較して小さい方の値を返却する。 <BR>
     * <BR>
     * 　@　@　@２－２－２－１）　@this.取引最終日＜＝１）で取得した出来るまで注文開始日の場合、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@this.取引最終日を返却する。  <BR>
     * <BR>
     * 　@　@　@２－２－２－２）　@this.取引最終日＞１）で取得した出来るまで注文開始日の場合、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@１）で取得した出来るまで注文開始日を返却する。 <BR>
     * <BR>
     * @@param l_datOrderUntilDeadLineStartDate 出来るまで注文開始日
     * @@return Date
     * @@throws WEB3SystemLayerException
     */
    public Date getOrderUntilDeadLineStartDayTradingEndDateConsidering(
        Date l_datOrderUntilDeadLineStartDate) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getOrderUntilDeadLineStartDayTradingEndDateConsidering(Date)";
        log.entering(STR_METHOD_NAME);

        //１）　@出来るまで注文開始日を取得する。
        Date l_datOrderUntilDeadLineStartDay = null;
        if (l_datOrderUntilDeadLineStartDate == null)
        {
            l_datOrderUntilDeadLineStartDay = this.getOrderUntilDeadLineStartDay();
        }
        else
        {
            l_datOrderUntilDeadLineStartDay =
                this.getOrderUntilDeadLineStartDay(l_datOrderUntilDeadLineStartDate);
        }

        //２）　@１）で戻り値がnull（出来るまで注文取扱不可）の場合、nullを返却する。
        if (l_datOrderUntilDeadLineStartDay == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //２－２－１）　@this.取引最終日＝nullの場合、
        //１）で取得した出来るまで注文開始日を返却する。
        else if (this.tradingEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datOrderUntilDeadLineStartDay;
        }
        //２－２－２）　@上記以外の場合、
        //this.取引最終日と１）で取得した出来るまで注文開始日を比較して
        //小さい方の値を返却する。
        else
        {
            if (WEB3DateUtility.compare(this.tradingEndDate, l_datOrderUntilDeadLineStartDay) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                return this.tradingEndDate;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return l_datOrderUntilDeadLineStartDay;
            }
        }
    }

    /**
     * (is出来るまで注文取扱可能<取引最終日考慮無>) <BR>
     * <BR>
     * 出来るまで注文が取扱可能かを判定する。 <BR>
     * <BR>
     * 　@　@・this.取扱可能注文条件Rowから、項目「出来るまで注文」の値を取得する。 <BR>
     * <BR>
     * 　@　@［「出来るまで注文 == ”取扱不可”」の場合］ <BR>
     * 　@　@　@　@falseを返却する。 <BR>
     * <BR>
     * 　@　@［「出来るまで注文 == ”取扱可能”」の場合］ <BR>
     * 　@　@　@　@trueを返却する。 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public boolean isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering()";
        log.entering(STR_METHOD_NAME);

        //this.取扱可能注文条件Rowから、項目「出来るまで注文」の値を取得する。
        String l_strCarriedOrder = this.enableOrderConditionRow.getCarriedOrder();

        //「出来るまで注文 == ”取扱不可”」の場合　@falseを返却する。
        if (WEB3CarriedOrderDef.CAN_NOT_DEALT.equals(l_strCarriedOrder))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        //「出来るまで注文 == ”取扱可能”」の場合　@trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
