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
filename	WEB3GentradeBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスクラス(WEB3GentradeBizLogicProvider.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/04 中尾　@寿彦(SRA) 新規作成
Revesion History : 2004/05/14 欒学峰(中訊) 変更
Revesion History : 2004/06/12 欒学峰(中訊) 変更
Revesion History : 2006/05/12 凌建平 (中訊)【共通】仕様変更・モデルNo.187
Revesion History : 2006/05/12 凌建平 (中訊)【共通】仕様変更・計算式書No.012
Revesion History : 2006/05/29 凌建平 (中訊) 障害管理 U02843対応
Revesion History : 2007/02/09 栄イ (中訊)【共通】仕様変更・計算式書No.013
Revesion History : 2007/10/10 栄イ (中訊)【共通】仕様変更・計算式書No.014
*/
package webbroker3.gentrade;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ConsolidatedCommissionInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommissionExecutionConditionDef;
import webbroker3.common.define.WEB3CommissionFeeCondFlagDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.data.EquityCommAccountCondMstDao;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.EquityCommCondMstRow;
import webbroker3.gentrade.data.EquityCommCondRow;
import webbroker3.gentrade.data.EquityCommRevMstRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeNumberConstDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (計算サービス)<BR>
 * 各種計算を提供するユーティリティクラス。<BR>
 * 余力計算は、xTradeが提供するインタフェイス以外にもパラメータが必要。(要検討)<BR>
 */
public class WEB3GentradeBizLogicProvider implements GlobalBizLogicProvider
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBizLogicProvider.class);
    
    /**
     * @@roseuid 40A3495902F0
     */
    public WEB3GentradeBizLogicProvider()
    {

    }
    
    public  OrderValidationResult checkTradingPower(SubAccount subaccount, OrderSpec orderspec)
    {
        return null;
    }
    
    /**
     * @@param l_dblData
     * @@return double
     * @@roseuid 40A3495903AE
     */
    public double calcSalesTax(double l_dblData)
    {   
        return 0.0;
    }
    
    /**
     * @@roseuid 40A3495902F0
     */    
    public ConsolidatedCommissionInfo calcCommission(Object[] l_objs) {
        return null;
    }
        
    /**
     * (calc消費税)<BR>
     * <BR>
     * 指定金額に対する消費税を返却する。<BR> 
     *（calcSalesTaxの実装）<BR> 
     * <BR>
     * ※基本設計計算式書（共通）.doc「６．消費税」を参照<BR> 
     * <BR>
     * @@param l_dblAmount - (金額)<BR>
     *   消費税を算出する対象の金額<BR>
     * @@param l_tsBaseDate - (基準日)<BR>
     *   消費税を算出する基準日、注文の場合は発注日にあたる。<BR>
     * @@param l_subAccount - (補助口座) <BR>
     * @@return double
     * @@throws WEB3BaseException 消費税の計算に失敗した場合
     * @@roseuid 3FFBC420033B
     */
    public double calcSalesTax(double l_dblAmount, Timestamp l_tsBaseDate, SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        long l_lngSalesTax = 0L;
        double l_dblSalesTax = 0.0;
        WEB3GentradeTaxRate l_taxRate = null;
        
        final String STR_METHOD_NAME = "calcSalesTax(double, Timestamp, SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_dblAmount < 0)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "金額 = " + l_dblAmount);
        }
        
        MainAccount l_mainAccount = l_subAccount.getMainAccount();       
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
       
       //口座オブジェクト.居住／非居住区分＝（ 1:特別非住居者 or
       // 2:非住居者）の場合は、消費税として０を返す。
        String l_strResident = l_mainAccountRow.getResident();
        if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_strResident)
            || WEB3ResidentDef.NON_RESIDENT.equals(l_strResident))
        {
            log.debug("口座オブジェクト.居住／非居住区分＝" +
                "（ 1:特別非住居者 or 2:非住居者）の場合は、消費税として０を返す");
            return 0;
        }
       
        // 消費税率を取得する
        l_taxRate = new WEB3GentradeTaxRate(
            l_subAccount.getInstitution().getInstitutionCode(),
            WEB3GentradeTaxRate.CONSUMPTION_TAX, 
            l_tsBaseDate);           
       
        // 消費税 ＝ 金額 × 消費税率
        //BigDecimal l_oneHandred = new BigDecimal("100");
        BigDecimal l_oneHandred = new BigDecimal(WEB3GentradeNumberConstDef.HYAKU);
        //int l_intScale = 10;
        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
        
        BigDecimal l_bdAmount = new BigDecimal(l_dblAmount);
        BigDecimal l_bdTaxRate = 
            new BigDecimal(l_taxRate.getTaxRate()).divide(l_oneHandred, l_intScale, BigDecimal.ROUND_HALF_EVEN);
        l_dblSalesTax = l_bdAmount.multiply(l_bdTaxRate).doubleValue();
        
        log.debug("金額：[" + l_bdAmount.doubleValue() + "]");
        log.debug("消費税率：[" + l_bdTaxRate.doubleValue() + "]");
        log.debug("消費税 ＝ 金額 × 消費税率：[" + l_dblSalesTax + "]");
        // 小数点以下切り捨て
        l_lngSalesTax = (long)l_dblSalesTax;

        log.exiting(STR_METHOD_NAME);
        return (double)l_lngSalesTax;
    }
  
    /**
     * (get委託手数料コース)<BR>
     * <BR>
     *顧客に該当する現在の委託手数料コースを取得する。<BR>
     * <BR>
     * １）委託手数料顧客条件登録マスターを以下の条件で読み、<BR>
     * 手数料No.を取得する。<BR>
     *  <BR>
     * [条件] <BR>
     * 委託手数料顧客条件登録マスター.証券会社コード =  <BR>
     *    顧客.getInstitution().getInstitutionCode() <BR>
     * 委託手数料顧客条件登録マスター.部店ＩＤ =  <BR>
     *    顧客.getBranch().getBranchId() <BR>
     * 委託手数料顧客条件登録マスター.口座ＩＤ = 顧客.getAccountId() <BR>
     * 委託手数料顧客条件登録マスター.手数料商品コード =  <BR>
     *    手数料商品コード <BR>
     * 委託手数料顧客条件登録マスター.有効日 = 有効日<BR>
     *  <BR>
     * ２）以下の条件で委託手数料条件登録マスターを読み、<BR>
     * 手数料コースコードを取得し返却する。<BR>
     * 複数行取得できた場合は、1件目のレコードの <BR>
     * 手数料コースコードを返却する。<BR>
     *  <BR>
     * [条件]
     * 委託手数料条件登録マスター.証券会社コード =  <BR>
     *     顧客.getInstitution().getInstitutionCode() <BR> 
     * And 委託手数料条件登録マスター.手数料商品コード = 
     *    手数料商品コード <BR>
     * 委託手数料条件登録マスター.登録No = 手数料No. + ' ' <BR>
     * And 委託手数料条件登録マスター.適用開始年月日＜＝有効日<BR>
     * And 委託手数料条件登録マスター.適用終了年月日 >= 有効日<BR>
     * <BR>
     * @@param l_genMainAccount - (顧客)<BR>
     * @@param l_strCommProductCode - (手数料商品コード) <BR>
     *    10：上場株式  11：店頭株式  <BR>
     *    12：ミニ株式    40：外国株式  <BR>
     *    50：先物         51：株価指数ＯＰ <BR>
     * @@param l_validUntilBizDate - (有効日)<BR>
     * @@return String
     * @@throws WEB3SystemLayerException
     */
    public String getCommissionCourseDiv(
        WEB3GentradeMainAccount l_genMainAccount,
        String l_strCommProductCode,
        Date l_validUntilBizDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getCommissionCourseDiv(WEB3GentradeMainAccount, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        String l_strValidUntilBizDate =
            WEB3DateUtility.formatDate(
                l_validUntilBizDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        
        //１）委託手数料顧客条件登録マスターを以下の条件で読み、
        //手数料No.を取得する。
        EquityCommAccountCondMstRow l_equityCommAccountCondMstRow;
        try
        {
            l_equityCommAccountCondMstRow =
                EquityCommAccountCondMstDao.findRowByPk(
                    l_genMainAccount.getInstitution().getInstitutionCode(),
                    l_genMainAccount.getBranch().getBranchId(),
                    l_genMainAccount.getAccountId(),
                    l_strCommProductCode,
                    l_strValidUntilBizDate);
        }
        catch (DataFindException dfe)
        {
            // 該当データなし
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataException de)
        {
            // DBアクセスエラー
            log.error(STR_METHOD_NAME,de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //２）以下の条件で委託手数料条件登録マスターを読み、
        //手数料コースコードを取得し返却する。
        //複数行取得できた場合は、1件目のレコードの 
        //手数料コースコードを返却する
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and comm_product_code = ? ");
        l_sbWhere.append(" and reg_no = ? ");
//        l_sbWhere.append(" and appli_start_date <= ? ");
//        l_sbWhere.append(" and appli_end_date >= ? ");
        Object[] l_objWhere =
        {   l_genMainAccount.getInstitution().getInstitutionCode(),//証券会社コード
            l_strCommProductCode, //手数料商品コード
            l_equityCommAccountCondMstRow.getCommissionNo() + ' '//手数料No.
//            l_validUntilBizDate, //有効日
//            l_validUntilBizDate //有効日
        };
        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                EquityCommCondMstRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataException de)
        {
            // DBアクセスエラー
            log.error(STR_METHOD_NAME,de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        int l_intSize = l_lisRecords.size();
        EquityCommCondMstRow l_equityCommCondMstRow = null;
        for(int i = 0; i < l_intSize; i ++)
        {
            EquityCommCondMstRow l_tmpEquityCommCondMstRow =  
                (EquityCommCondMstRow)l_lisRecords.get(i);
            Timestamp l_tsStart = l_tmpEquityCommCondMstRow.getAppliStartDate();
            Timestamp l_tsEnd = l_tmpEquityCommCondMstRow.getAppliEndDate();
            if((WEB3DateUtility.compare(l_tsStart,l_validUntilBizDate) <= 0) 
                && (WEB3DateUtility.compare(l_tsEnd,l_validUntilBizDate) >= 0))
            {
                l_equityCommCondMstRow = l_tmpEquityCommCondMstRow;
                break;
            }
        }
        //件数チェック
        if (l_equityCommCondMstRow == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "委託手数料条件登録マスター検索： 件数 = 0");
        }

        log.exiting(STR_METHOD_NAME);
        return l_equityCommCondMstRow.getCommissionCourseDiv();
    }
  
    
    /**
     * (calc委託手数料)<BR>
     * <BR>
     * 諸経費計算用代金に対する委託手数料を算出します。<BR> 
     *（calcCommissionの実装）<BR> 
     * <BR>
     * ※基本設計計算式書（共通）.doc「５．委託手数料」を参照<BR> 
     * <BR>
     * 引数の手数料オブジェクトに <BR>
     * 手数料金額、手数料No、手数料No枝番、手数料コースコード、最低手数料を設定し、returnする。<BR>
     * <BR>
     * @@param l_commission - (手数料)<BR>
     *     手数料を決定するために必要な情報<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@throws WEB3BaseException 委託手数料の計算に失敗した場合
     * @@roseuid 3FFBBEFD02BE
     */
    public void calcCommission(WEB3GentradeCommission l_commission, SubAccount l_subAccount) 
        throws WEB3BaseException
    {
        List l_list = null;
        int l_intSize;

        String l_strErrorMessage1 = "会社部店商品テーブルに該当するデータがありません。";
        //String l_strErrorMessage4 = "委託手数料顧客条件登録マスターに該当するデータがありません。";
        //String l_strErrorMessage7 = "委託手数料枝番登録マスターに該当するデータがありません。";
        //String l_strErrorMessage8 = "委託手数料枝番登録マスターで該当するデータが重複しています";
        String l_strErrorMessage10 = "委託手数料条件テーブルに該当するデータがありません。";
        String l_strErrorMessage11 =  "委託手数料条件テーブルで該当するデータが重複しています";
        String l_strErrorMessage13 = "委託手数料条件登録マスターに該当するデータがありません。";
        String l_strErrorMessage14 = "委託手数料条件登録マスターで該当するデータが重複しています";
        
        QueryProcessor l_qp = null;
        
        String l_strSqlDateFormat = "yyyyMMdd";
        SimpleDateFormat l_sqlDateFormatter = GtlUtils.getThreadSafeSimpleDateFormat(l_strSqlDateFormat);
 
        BigDecimal l_oneHandred = new BigDecimal(WEB3GentradeNumberConstDef.HYAKU);
        int l_intScale = Integer.parseInt(WEB3GentradeNumberConstDef.JYU);
        
        final String SPACE = " ";
       
        final String STR_METHOD_NAME = "calcCommission(WEB3EquityCommission , SubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_commission == null || l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.debug("証券会社コード：[" + l_commission.getInstitutionCode() + "]");
        log.debug("部店ID：[" + l_commission.getBranchId() + "]");
        log.debug("手数料商品コード：[" + l_commission.getCommissionProductCode() + "]");
        log.debug("注文チャンネル：[" + l_commission.getOrderChannel() + "]");
        log.debug("取引コード(SONAR)：[" + l_commission.getSonarTradedCode() + "]");
        log.debug("執行条件：[" + l_commission.isLimitPrice() + "]");
        log.debug("発注日：[" + l_sqlDateFormatter.format(l_commission.getOrderBizDate()) + "]");
        log.debug("弁済区分：[" + l_commission.getPayType() + "]");
        
        //(1) 手数料の登録No.の決定
        //委託手数料は、手数料No.＋枝番によって決定された登録No.に紐付けられている。
        //以下の手順で登録No.を決定する。
        //
        //①@原注文手数料No.が未設定の場合、初回注文と判断し、(2)の計算手順を実施する。
        //
        //②原注文手数料No.に値が設定されている場合、注文訂正あるいは約定時と判断し、
        //以下の判定処理を実施する。
        // a.会社部店商品テーブルの手数料条件決定FLAGが、「L：最終注文の条件」の
        //場合は、(2)の計算手順を実施する。
        // b.会社部店商品テーブルの手数料条件決定FLAGが、「F:原注文の条件」の場合は、
        //原注文の登録No.で手数料を決定するため、設定された原注文手数料No.および
        //原注文手数料No.枝番から登録No.を決定する。よって、(2)の計算手順を実施する
        //にあたり、(2)-③の処理をスキップする。
        
        //(2) 手数料の計算手順
        
        //①@手数料条件決定FLAGを取得する。
        //【対象テーブル】
        //  会社部店商品テーブル
        //【取得項目】
        //  手数料条件決定FLAG
        //【取得条件】
        //  部店ID　@＝　@入力顧客の部店ID　@and
        //手数料商品コード　@＝　@入力顧客の手数料商品コード
        InstBranchProductRow l_instBranchProductRow;
        try
        {
            l_instBranchProductRow =
                InstBranchProductDao.findRowByPk(
                    l_commission.getBranchId(),
                    l_commission.getCommissionProductCode());
        }
        catch (DataFindException dfe)
        {
            // 該当データなし
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataException de)
        {
            // DBアクセスエラー。
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        if (l_instBranchProductRow == null)
        {
            // 該当するデータが無い。
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage1);
        }
        log.debug(
            "会社部店商品テーブル：手数料条件決定FLAG：["
                + l_instBranchProductRow.getCommissionFeeCondFlag()
                + "]");
       
        // ②手数料No.、顧客徴収率を取得する。
        //発注日に対応する手数料Noと顧客徴収率を取得する。
        //有効日はYYYYMMDDで保有しているため、注文日の型と異なるため、変換が必要。
        //【対象テーブル】
        //   委託手数料顧客条件登録マスター
        //【取得項目】
        //   手数料No
        //   顧客徴収率
        //【取得条件】
        //   証券会社コード　@＝　@証券会社コード　@and
        //   部店ID　@＝　@入力顧客の部店ID　@and 
        //   手数料商品コード　@＝　@入力顧客の手数料商品コード and
        //   口座ID　@＝　@入力顧客の口座ID　@and
        //   有効日　@＝　@注文日
        EquityCommAccountCondMstRow l_equityCommAccountCondMstRow = null;
        try
        {
            l_equityCommAccountCondMstRow = EquityCommAccountCondMstDao.findRowByPk(
                l_commission.getInstitutionCode(),
                l_commission.getBranchId(),
                l_subAccount.getAccountId(),
                l_commission.getCommissionProductCode(),
                l_sqlDateFormatter.format(l_commission.getOrderBizDate()));
        }
        catch (DataFindException dfe)
        {
            // 該当データなし
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataException de)
        {
            // DBアクセスエラー。
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        log.debug("委託手数料顧客条件登録マスター：手数料No：["
            + l_equityCommAccountCondMstRow.getCommissionNo() + "]");
        log.debug("委託手数料顧客条件登録マスター：顧客徴収率：["
            + l_equityCommAccountCondMstRow.getAccountChargeRatio() + "]");
            
        // ③枝番を取得する。
        //【対象テーブル】
        //    委託手数料枝番登録マスター
        //【取得項目】
        //    枝番
        //【取得条件】（注S）
        //　@  証券会社コード　@＝　@入力顧客の証券会社コード　@and
        //    手数料商品コード　@＝　@手数料商品コード  and
        //    注文チャネル　@＝　@(注文チャネル or “ ”)　@and
        //　@  取引コード(SONAR)　@＝　@(取引コード(SONAR) or “ ”)　@and
        //　@　@執行条件(注S1)　@＝　@(執行条件（1：成行　@2：指値） or “ ”)　@and
        //　@　@適用開始年月日　@≦　@発注日　@≦　@適用終了年月日　@and
        //　@　@弁済区分(注S2)　@＝　@(弁済区分 or “ ”) 　@and
        //　@　@市場コード(SONAR) (注S3)　@＝　@(市場コード(SONAR) or “ ”)
        //	  原資産銘柄コード(注S4)　@＝　@(原資産銘柄コード（0005：TOPIX　@0016：日経300　@0018：日経225　@
        //	  	0019：ミニ日経225　@or “ ”）
        //	  日計り区分(注S4)　@＝　@(日計り区分（1：日計りでない　@5：日計り　@　@or “ ”）
        //【取得順序】
        //		注文チャネル、取引コード(SONAR)、執行条件、弁済区分、市場コード(SONAR)、原資産銘柄コード、
        //		日計り区分の降順指定
        //		（“ ”より、取得条件に指定した文字列に該当するレコードのほうを優先して取得する）
        //（注）対象のデータが見つからない場合は、枝番を空白として、
        //「④登録No.を決定する。」以降の処理を続行する。
        //また、対象のデータが複数件見つかった場合は、１レコード目を採用する。
        //
        //（注S）注文チャネル、取引コード(SONAR)、執行条件、弁済区分、市場コード(SONAR)、原資産銘柄コード、
        //		日計り区分の取得条件（or）には、
        //		“ ”（１桁の半角空白文字列）も指定する。
        //（注S1）執行条件の「成行／指値」の別の判定は、手数料オブジェクト.is指値()にて行う。
        //（注S2）【取得条件】における弁済区分は、手数料オブジェクト.弁済区分より、以下の桁落としを行った値を指定する。
        //　@　@　@ 手数料オブジェクト.弁済区分＝00：その他 → “ ”（１桁の半角空白文字列）に変換
        //　@　@　@ 手数料オブジェクト.弁済区分≠00：その他 → 手数料オブジェクト.弁済区分の２桁目のみの１桁文字列に変換
        //        ※ただし、手数料オブジェクト.弁済区分が１桁の場合は、
        //        ※手数料オブジェクト.弁済区分をそのまま使用する。
        //        ※（手数料オブジェクト.弁済区分が１桁の場合は、
        //        ※　@「(部店市場弁済別)取扱条件テーブル」の
        //        ※　@「弁済区分(SONAR)」と同じコードが設定されてくる。）
        //（注S3）市場コード(SONAR)の指定は以下の通りとする。
        //　@　@　@手数料オブジェクト.市場コード(SONAR)≠null 
        //          → (手数料オブジェクト.市場コード(SONAR) or “ ” （１桁の半角空白文字列）) を指定して検索する。
        //　@　@　@手数料オブジェクト.市場コード(SONAR)＝null
        //          →　@“ ”（１桁の半角空白文字列）のみを指定して検索する。
        //　@　@（注S4）原資産銘柄コード、日計り区分の指定は以下の通りとする。
        //　@　@　@手数料オブジェクト. 原資産銘柄コード、日計り区分＝null
        //          →　@“ ”（１桁の半角空白文字列）のみを指定して検索する。
        
        StringBuffer l_sbEquityCommRevMstWhere = new StringBuffer();
        l_sbEquityCommRevMstWhere.append(" institution_code = ? ");//証券会社コード
        l_sbEquityCommRevMstWhere.append(" and comm_product_code = ? ");//手数料商品コード
        l_sbEquityCommRevMstWhere.append(" and (order_channel = ? or order_channel = ? ) ");//注文チャネル
        l_sbEquityCommRevMstWhere.append(" and (transaction_type = ? or transaction_type = ? ) ");//取引コード(SONAR)
        l_sbEquityCommRevMstWhere.append(" and (exec_cond_type = ? or exec_cond_type = ? ) ");//執行条件
        l_sbEquityCommRevMstWhere.append(" and (appli_start_date <= ? and appli_end_date >= ?) ");//適用年月日
        l_sbEquityCommRevMstWhere.append(" and (pay_type = ? or pay_type = ? ) ");//弁済区分
        l_sbEquityCommRevMstWhere.append(" and (sonar_market_code = ? or sonar_market_code = ? ) ");//市場コード(SONAR)
        l_sbEquityCommRevMstWhere.append(" and (underlying_product_code = ? or underlying_product_code = ? ) ");//原資産銘柄コード
        l_sbEquityCommRevMstWhere.append(" and (day_trade_type = ? or day_trade_type = ? ) ");//日計り区分
        
        //get 執行条件：執行条件の「成行／指値」の別の判定は、手数料オブジェクト.is指値()にて行う。
        String l_strExecCondType;
        if(l_commission.isLimitPrice())
        {
            l_strExecCondType = WEB3CommissionExecutionConditionDef.LIMIT_PRICE;
        }
        else
        {
            l_strExecCondType = WEB3CommissionExecutionConditionDef.MARKET_PRICE;
        }
        //get 弁済区分
        String l_strPayType;
        if(WEB3PayTypeDef.OTHER.equals(l_commission.getPayType()))
        {
            l_strPayType = SPACE;
        }
        else
        {
            //手数料オブジェクト.弁済区分は、２桁であれば、文字列変換を行う
            if(l_commission.getPayType().length() == 2)
            {
                l_strPayType = l_commission.getPayType().substring(1,2);
            }
            else
            {
                l_strPayType = l_commission.getPayType();
            }
        }
        //get 市場コード(SONAR)
        String l_strSonarMarketCode;
        if(l_commission.getSonarMarketCode() == null)
        {
            l_strSonarMarketCode = SPACE;
        }
        else
        {
            l_strSonarMarketCode = l_commission.getSonarMarketCode();
        }
        
        //原資産銘柄コードを取得
        String l_strUnderlyingProductCode;
        if(l_commission.getUnderlyingProductCode() == null)
        {
        	l_strUnderlyingProductCode = SPACE;
        }
        else
        {
        	l_strUnderlyingProductCode = l_commission.getUnderlyingProductCode();
        }
        
        //日計り区分を取得
        String l_strDayTradeType;
        if(l_commission.getDayTradeType() == null)
        {
        	l_strDayTradeType = SPACE;
        }
        else
        {
        	l_strDayTradeType = l_commission.getDayTradeType();
        }
        
        Object[] l_objEquityCommRevMst =
            new Object[] {
                l_commission.getInstitutionCode(),//証券会社コード
                l_commission.getCommissionProductCode(),//手数料商品コード
                l_commission.getOrderChannel(),//注文チャネル
                new String(" "),//注文チャネル == ' '
                l_commission.getSonarTradedCode(),//取引コード(SONAR)
                new String(" "),//取引コード(SONAR) == ' '
                l_strExecCondType,//執行条件
                new String(" "),//執行条件 == ' '
                l_sqlDateFormatter.format(l_commission.getOrderBizDate()),//適用年月日
                l_sqlDateFormatter.format(l_commission.getOrderBizDate()),//適用年月日
                l_strPayType,//弁済区分
                new String(" "),//弁済区分 == ' '
                l_strSonarMarketCode,//市場コード(SONAR)
                new String(" "),    //市場コード(SONAR) == ' '
                l_strUnderlyingProductCode, //原資産銘柄コード
                new String(" "),            //原資産銘柄コード == ' '
                l_strDayTradeType,          //日計り区分
                new String(" ")};          //日計り区分 == ' '
        
        //【取得順序】
        //　@　@注文チャネル、取引コード(SONAR)、執行条件、弁済区分、市場コード(SONAR)、原資産銘柄コード、
        //		日計り区分の降順指定
        String l_strOrderBy = "order_channel desc , transaction_type desc , exec_cond_type desc , " +
            "pay_type desc, sonar_market_code desc, underlying_product_code desc, day_trade_type desc";
        
        String l_strCommissionNo = null;// 返却情報：手数料No
        String l_strRevision = null;      // 返却情報：手数料No枝番
        
        //原注文手数料No.が未設定の場合、初回注文と判断し、(2)の計算手順を実施する。        
        if (l_commission.getOrgCommissionNo() == null)
        {
            // 初回注文
            try
            {
                l_qp = Processors.getDefaultProcessor();
                l_list =
                    l_qp.doFindAllQuery(
                        EquityCommRevMstRow.TYPE,
                        l_sbEquityCommRevMstWhere.toString(),
                        l_strOrderBy,
                        null,
                        l_objEquityCommRevMst);
            }
            catch (DataException de)
            {
                // DBアクセスエラー
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            l_intSize = l_list.size();
            if (l_intSize < 1)
            {
                // 対象のデータが見つからない場合は、枝番を空白として、
                //「④登録No.を決定する。」以降の処理を続行する。
                l_strRevision = SPACE; 
                l_strCommissionNo = l_equityCommAccountCondMstRow.getCommissionNo();
            }
            else 
            {
                //対象のデータが複数件見つかった場合は、１レコード目を採用する。
                EquityCommRevMstRow l_equityCommRevMstRow = (EquityCommRevMstRow)l_list.get(0);
                l_strCommissionNo = l_equityCommAccountCondMstRow.getCommissionNo();
                log.debug("手数料No = 委託手数料顧客条件登録マスター．手数料No：["
                    + l_equityCommAccountCondMstRow.getCommissionNo() + "]");
                l_strRevision = l_equityCommRevMstRow.getRevision();
                log.debug("手数料No枝番 = 委託手数料枝番登録マスター．枝番：["
                    + l_equityCommRevMstRow.getRevision() + "]");
            }

        }
        //原注文手数料No.に値が設定されている場合
        else
        {
            if (WEB3CommissionFeeCondFlagDef.FIRST_ORDER.equals(l_instBranchProductRow.getCommissionFeeCondFlag()))
            {
                // 手数料条件が「原注文の条件」の場合
                l_strCommissionNo = l_commission.getOrgCommissionNo();
                l_strRevision = l_commission.getOrgCommissionRevNo();
                log.debug("手数料条件が「原注文の条件」の場合");
                log.debug("手数料No = 原注文手数料No：["
                    + l_commission.getOrgCommissionNo() + "]");
                log.debug("手数料No枝番 = 原注文手数料No枝番：["
                    + l_commission.getOrgCommissionRevNo() + "]");
            }
            else
            {
                // 会社部店商品テーブルの手数料条件決定FLAGが、
                //「L：最終注文の条件」の場合は、(2)の計算手順を実施する。
                try
                {
                    l_qp = Processors.getDefaultProcessor();
                    l_list =
                        l_qp.doFindAllQuery(
                            EquityCommRevMstRow.TYPE,
                            l_sbEquityCommRevMstWhere.toString(),
                            l_strOrderBy,
                            null,
                            l_objEquityCommRevMst);
                }
                catch (DataException de)
                {
                    // DBアクセスエラー
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }
                l_intSize = l_list.size();
                if (l_intSize < 1)
                {
                    // 対象のデータが見つからない場合は、枝番を空白として、
                    //「④登録No.を決定する。」以降の処理を続行する。
                    l_strRevision = SPACE; 
                    l_strCommissionNo = l_equityCommAccountCondMstRow.getCommissionNo();
                }
                else 
                {
                    //対象のデータが複数件見つかった場合は、１レコード目を採用する。
                    EquityCommRevMstRow l_equityCommRevMstRow = (EquityCommRevMstRow)l_list.get(0);
                    l_strCommissionNo = l_equityCommAccountCondMstRow.getCommissionNo();
                    log.debug("手数料No = 委託手数料顧客条件登録マスター．手数料No：["
                        + l_equityCommAccountCondMstRow.getCommissionNo() + "]");
                    l_strRevision = l_equityCommRevMstRow.getRevision();
                    log.debug("手数料No枝番 = 委託手数料枝番登録マスター．枝番：["
                        + l_equityCommRevMstRow.getRevision() + "]");
                }
            }
        }
        
        // ④登録No.を決定する。
        //   手数料No.＋枝番より決定した番号を登録No.とする。
        log.debug("登録No：[" + l_strCommissionNo + l_strRevision + "]");  

        // ⑤ - 1手数料情報を取得する。
        //【対象テーブル
        //   委託手数料条件テーブル
        // 【取得項目】
        //   徴収率
        //   付加金額
        //   手数料乗数
        // 【取得条件】
        //    証券会社コード　@＝　@入力顧客の証券会社コード　@and
        //    手数料商品コード　@＝　@手数料商品コード and
        //    登録No.　@＝　@④で決定した登録No.　@and
        //    売買代金（自）　@＜　@諸経費計算用代金（※１）　@≦　@売買代金（至）　@and
        //    適用開始年月日　@≦　@発注日　@≦　@適用終了年月日  
        //----------------------------------------------------------------
        //  （※１）買い時は「拘束売買代金」を、売り時は「売買代金」を、約定時は「約定代金」のように、
        // 委託手数料算出の基準となる代金を「諸経費計算用代金」として使用する。
        // 委託手数料条件テーブルの「売買代金（自）」「売買代金（至）」は万円単位で設定されているため、
        // 取得条件には、「諸経費計算用代金（※１）」を”10000”で割った値（小数点以下も有効）を指定する
        // ことに注意。
        //----------------------------------------------------------------       
        //（注）枝番に有効文字が設定されている時（空白ではない状態）に、
        //  登録No.で検索して対象のデータが見つからない場合は、枝番を空白にして
        //  手数料Noだけで検索する。
        EquityCommCondRow l_equityCommCondRow = null;
        String l_strWhereClause2 = "institution_code=? and comm_product_code=? "
            + "and reg_no=? and (min_turnover<? and max_turnover>=?) "
            + "and (appli_start_date<=? and appli_end_date>=?)"; 
        BigDecimal l_bdTurnover =
            new BigDecimal(l_commission.getExpensesCalcAmount()).divide(
                new BigDecimal(WEB3GentradeNumberConstDef.MAN),
                l_intScale,
                BigDecimal.ROUND_HALF_EVEN);    
        try
        {
            if (l_qp == null)
            {
                l_qp = Processors.getDefaultProcessor();
            }
            l_list = l_qp.doFindAllQuery(
                EquityCommCondRow.TYPE,
                l_strWhereClause2,
                new Object[] { l_commission.getInstitutionCode(),
                    l_commission.getCommissionProductCode(),
                    l_strCommissionNo + l_strRevision,
                    l_bdTurnover,
                    l_bdTurnover,
                    l_sqlDateFormatter.format(l_commission.getOrderBizDate()),
                    l_sqlDateFormatter.format(l_commission.getOrderBizDate()) });
        }
        catch (DataException de)
        {
            // DBアクセスエラー
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        l_intSize = l_list.size();
        if (l_intSize < 1)
        {
            // 該当するデータが無い場合
            //枝番に有効文字が設定されている時（空白ではない状態）に、
            //  登録No.で検索して対象のデータが見つからない場合は、枝番を空白にして
            //  手数料Noだけで検索する。
            log.debug("枝番に有効文字が設定されている時（空白ではない状態）に、" +
                "登録No.で検索して対象のデータが見つからない場合は、" +
                "枝番を空白にして手数料Noだけで検索する。");
            try 
            {
                l_strRevision = SPACE;
                l_list = l_qp.doFindAllQuery(
                    EquityCommCondRow.TYPE,
                    l_strWhereClause2,
                    new Object[] { l_commission.getInstitutionCode(),
                        l_commission.getCommissionProductCode(),
                        l_strCommissionNo + l_strRevision,
                        l_bdTurnover,
                        l_bdTurnover,
                        l_sqlDateFormatter.format(l_commission.getOrderBizDate()),
                        l_sqlDateFormatter.format(l_commission.getOrderBizDate()) });
            }
            catch (DataException de)
            {
                // DBアクセスエラー
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            l_intSize = l_list.size();
            if (l_intSize < 1)
            {
                // 該当するデータが無い場合
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage10);
            }
            else if (l_intSize == 1)
            {
                l_equityCommCondRow = (EquityCommCondRow)l_list.get(0);
            }
            else
            {
                // 該当するデータが重複している場合
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage11);
            }
        }
        else if (l_intSize == 1)
        {
            l_equityCommCondRow = (EquityCommCondRow)l_list.get(0);
        }
        else
        {
            // 該当するデータが重複している場合
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage11);
        }
        log.debug("委託手数料条件テーブル：徴収率：["
            + l_equityCommCondRow.getChargeRatio() + "]");
        log.debug("委託手数料条件テーブル：付加金額：["
            + l_equityCommCondRow.getAddedPrice() + "]");
        log.debug("委託手数料条件テーブル：手数料乗数：["
                + l_equityCommCondRow.getCommitionPerUnit() + "]");
        
        // ⑤ - 2 手数料情報を取得する。
        //【対象テーブル】
        //   委託手数料条件登録マスター
        //【取得項目】
		// 	  最低手数料
		//    最高手数料
		//    特別設定開始年月日
		//    特別設定終了年月日
		//    特別徴収率
		//    特別最低手数料
		//    特別最高手数料
        //    手数料タイプ
        //【取得条件】
        //    証券会社コード　@＝　@入力顧客の証券会社コード　@and
        //　@　@手数料商品コード　@＝　@手数料商品コード and
        //　@　@登録No.　@＝　@④で決定した登録No.　@and
        //    適用開始年月日　@≦　@発注日　@≦　@適用終了年月日
        //----------------------------------------------------------
        //（注）枝番に有効文字が設定されている時（空白ではない状態）に、
        //  登録No.で検索して対象のデータが見つからない場合は、
        //  枝番を空白にして手数料Noだけで検索する。
        EquityCommCondMstRow l_eccmRow = null;
        String l_strWhereClause3 = 
            "institution_code=? and comm_product_code=? and reg_no=? and (appli_start_date<=? and appli_end_date>=?)";
        try
        {
            l_list = l_qp.doFindAllQuery(
                EquityCommCondMstRow.TYPE,
                l_strWhereClause3,
                new Object[] { l_commission.getInstitutionCode(),
                    l_commission.getCommissionProductCode(),
                    l_strCommissionNo + l_strRevision,
                    l_sqlDateFormatter.format(l_commission.getOrderBizDate()),
                    l_sqlDateFormatter.format(l_commission.getOrderBizDate()) });
        }
        catch (DataException de)
        {
            // DBアクセスエラー
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        l_intSize = l_list.size();
        if (l_intSize < 1)
        {
            // 該当するデータが無い場合
            //枝番に有効文字が設定されている時（空白ではない状態）に、
            //  登録No.で検索して対象のデータが見つからない場合は、枝番を空白にして
            //  手数料Noだけで検索する。
            log.debug("枝番に有効文字が設定されている時（空白ではない状態）に、" +
                "登録No.で検索して対象のデータが見つからない場合は、" +
                "枝番を空白にして手数料Noだけで検索する。");
            try 
            {
                l_strRevision = SPACE;
                l_list = l_qp.doFindAllQuery(
                    EquityCommCondMstRow.TYPE,
                    l_strWhereClause3,
                    new Object[] { l_commission.getInstitutionCode(),
                        l_commission.getCommissionProductCode(),
                        l_strCommissionNo + l_strRevision,
                        l_sqlDateFormatter.format(l_commission.getOrderBizDate()),
                        l_sqlDateFormatter.format(l_commission.getOrderBizDate()) });
            }
            catch (DataException de)
            {
                // DBアクセスエラー
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }
            l_intSize = l_list.size();
            if (l_intSize < 1)
            {
                // 該当するデータが無い場合
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage13);
            }
            else if (l_intSize == 1)
            {
                l_eccmRow = (EquityCommCondMstRow)l_list.get(0);
            }
            else
            {
                // 該当するデータが重複している場合
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage14);
            }
        }
        else if (l_intSize == 1)
        {
            l_eccmRow = (EquityCommCondMstRow)l_list.get(0);
        }
        else
        {
            // 該当するデータが重複している場合
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_strErrorMessage14);
        }
        log.debug("委託手数料条件登録マスター：最低手数料：["
                + l_eccmRow.getMinCommission() + "]");
        log.debug("委託手数料条件登録マスター：特別徴収率：["
                + l_eccmRow.getSpcChargeRatio() + "]");
        log.debug("委託手数料条件登録マスター：特別最低手数料：["
                + l_eccmRow.getSpcMinCommission() + "]");
        log.debug("委託手数料条件登録マスター：手数料コースコード：["
                + l_eccmRow.getCommissionCourseDiv() + "]");
        

        //⑥委託手数料を計算する。
        //
        //a.通常の場合の手数料を求める。
		//　@a-1．手数料乗数に値が設定されている場合（≠ZERO）
		//   　@　@手数料a ＝ 手数料オブジェクト.数量 × 手数料乗数
		//  a-2．手数料乗数に値が設定されていない場合（＝ZERO）
		//   　@　@手数料a ＝ 諸経費計算用代金 × 徴収率 ＋ 付加金額　@（小数点以下切り捨て）
        //  ※手数料aの計算結果が、最低手数料より少額の場合は、最低手数料を手数料aとする。
        //  ※手数料乗数に値が設定されている場合のみ、手数料オブジェクト.手数料乗数へセットする。
        //
        //b.注文発注日が特別徴収率期間の場合の追加計算をする。
        //   手数料b ＝ 手数料a × 特別徴収率　@（小数点以下切り捨て）
        //   ※手数料bの計算結果が、特別最低手数料より少額の場合は、特別最低手数料を手数料bとする。
        // 　@※手数料bの計算結果が、特別最高手数料より多額の場合は、特別最高手数料を手数料bとする。
        //
        //c.委託手数料 ＝ 手数料a（b.の計算をした場合は手数料b） × 顧客徴収率　@（小数点以下切り捨て）
        //　@※委託手数料の計算結果が、最高手数料より多額の場合は、最高手数料を委託手数料とする。
        //
        //d.立会外分売の場合、０円（固定）とする。
        //
        //（注）当該取引が立会外分売であった場合は、委託手数料を０とするが、
        //手数料Ｎｏ．および手数料Ｎｏ．枝番、手数料コースコード等の手数料情報について返却が必要なため、
        // 委託手数料条件登録マスターの取得処理を実施する。
        
        //立会外分売の場合
        if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_commission.getSonarTradedCode()))
        {
            // 当該取引が立会外分売の場合は、委託手数料をゼロとする
            l_commission.setCommission(0.0);
            //手数料Ｎｏ．
            l_commission.setCommissionNo(l_strCommissionNo);
            //手数料Ｎｏ．枝番
            l_commission.setCommissionRevNo(l_strRevision);
            //set 手数料コースコード
            l_commission.setCommissionCourseDiv(l_eccmRow.getCommissionCourseDiv());
            log.debug("立会外分売の場合 ： 委託手数料 = [ 0 ]");
            return;
        }

        double l_dblCommission = 0.0;     // 返却情報：手数料金額
        
        //a.通常の場合の手数料を求める
        BigDecimal l_bdCommissionA = null;       //手数料a

        //　@a-1．手数料乗数に値が設定されている場合（≠ZERO）
        if (l_equityCommCondRow.getCommitionPerUnit() != 0)
        {
        	//数量を取得
            BigDecimal l_bdQuantity = new BigDecimal(l_commission.getQuantity());
            //手数料乗数を取得
            BigDecimal l_bdCommitionPerUnit = new BigDecimal(l_equityCommCondRow.getCommitionPerUnit());
            //手数料オブジェクト.手数料乗数へセットする
            l_commission.setCommitionPerUnit(l_equityCommCondRow.getCommitionPerUnit());
    		//手数料a ＝ 手数料オブジェクト.数量 × 手数料乗数
            l_bdCommissionA = l_bdQuantity.multiply(l_bdCommitionPerUnit);

            log.debug("数料オブジェクト.数量：[" + l_bdQuantity.doubleValue() + "]");
            log.debug("手数料乗数：[" + l_bdCommitionPerUnit.doubleValue() + "]");
            log.debug("手数料a ＝ 手数料オブジェクト.数量 × 手数料乗数 ：[" + l_bdCommissionA.doubleValue() + "]");
        }
		//  a-2．手数料乗数に値が設定されていない場合（＝ZERO）
        else
        {
        	//諸経費計算用代金を取得
        	BigDecimal l_bdRestraintTurnover = new BigDecimal(l_commission.getExpensesCalcAmount());
        	//徴収率を取得
        	BigDecimal l_bdChargeRatio = new BigDecimal(l_equityCommCondRow.getChargeRatio()).divide(
            	l_oneHandred, l_intScale, BigDecimal.ROUND_HALF_EVEN);
        	//付加金額を取得
        	BigDecimal l_bdAddedPrice = new BigDecimal(l_equityCommCondRow.getAddedPrice());
    		//手数料a ＝ 諸経費計算用代金 × 徴収率 ＋ 付加金額　@（小数点以下切り捨て）
            l_bdCommissionA = l_bdRestraintTurnover.multiply(l_bdChargeRatio).add(l_bdAddedPrice);

            //小数点以下切り捨て
            long l_lngCommissionA = (long)l_bdCommissionA.doubleValue();
            l_bdCommissionA = new BigDecimal(l_lngCommissionA);

            log.debug("諸経費計算用代金：[" + l_bdRestraintTurnover.doubleValue() + "]");
            log.debug("徴収率：[" + l_bdChargeRatio.doubleValue() + "]");
            log.debug("付加金額：[" + l_bdAddedPrice.doubleValue() + "]");
            log.debug("手数料a ＝ 諸経費計算用代金 × 徴収率 ＋ 付加金額　@（小数点以下切り捨て）：[" + l_bdCommissionA.doubleValue() + "]");
        }

        //最低手数料を取得
        BigDecimal l_bdMinCommission = new BigDecimal(l_eccmRow.getMinCommission());
        //最高手数料を取得
        BigDecimal l_bdMaxCommission = new BigDecimal(l_eccmRow.getMaxCommission());

        BigDecimal l_bdAccountChargeRatio = new BigDecimal(
        	l_equityCommAccountCondMstRow.getAccountChargeRatio()).divide(
        		l_oneHandred, l_intScale, BigDecimal.ROUND_HALF_EVEN);
        
        if (l_bdCommissionA.compareTo(l_bdMinCommission) == -1)
        {
            // 手数料aの計算結果が、最低手数料より少額の場合は
        	// 最低手数料を手数料aとする
            l_bdCommissionA = l_bdMinCommission;
            log.debug("手数料ａの計算結果が最低手数料より小額の場合：[" + l_bdCommissionA + "]");
        }
        
        //b.注文発注日が特別徴収率期間の場合の追加計算をする。
        BigDecimal l_bdCommissionB = null;
        if (l_eccmRow.getSpcStartDate() != null  && l_eccmRow.getSpcEndDate() != null)
        {
            SimpleDateFormat l_simpleDateFormat =
                new SimpleDateFormat(l_strSqlDateFormat);
            String l_strSpcStartDate =
                l_simpleDateFormat.format(l_eccmRow.getSpcStartDate());
            String l_strSpcEndDate =
                l_simpleDateFormat.format(l_eccmRow.getSpcEndDate());
            String l_strOrderBizDate =
                l_simpleDateFormat.format(l_commission.getOrderBizDate());
            if (l_strOrderBizDate.compareTo(l_strSpcStartDate) >= 0 && l_strOrderBizDate.compareTo(l_strSpcEndDate) <= 0)
            {
                // 注文発注日が特別徴収率期間の場合の追加計算をする
                // 手数料ｂ ＝ 手数料ａ × 特別徴収率
                BigDecimal l_bdSpcChargeRatio =
                    new BigDecimal(l_eccmRow.getSpcChargeRatio()).divide(
                        l_oneHandred,
                        l_intScale,
                        BigDecimal.ROUND_HALF_EVEN);
                        
                log.debug("設定開始年月日(特別徴収) ： [" + l_strSpcStartDate + "]");
                log.debug("設定終了年月日(特別徴収) ： [" + l_strSpcEndDate + "]");
                log.debug("注文発注日 ： [" + l_strOrderBizDate + "]");
                l_bdCommissionB = l_bdCommissionA.multiply(l_bdSpcChargeRatio);
                //小数点以下切り捨て
                long l_lngCommissionB = (long)l_bdCommissionB.doubleValue();
                l_bdCommissionB = new BigDecimal(l_lngCommissionB);
                log.debug("手数料ｂ：注文発注日が特別徴収率期間の場合の追加計算をする（小数点以下切り捨て）");
                log.debug("特別徴収率：[" + l_bdSpcChargeRatio + "]");
                log.debug("手数料ｂ ＝ 手数料ａ × 特別徴収率：[" + l_bdCommissionB + "]");
                //特別最低手数料
                BigDecimal l_bdSpcMinCommission = new BigDecimal(l_eccmRow.getSpcMinCommission());
                //特別最高手数料
                BigDecimal l_bdSpcMaxCommission = new BigDecimal(l_eccmRow.getSpcMaxCommission());
                
                if (l_bdCommissionB.compareTo(l_bdSpcMinCommission) == -1)
                {
                    // 手数料ｂの計算結果が特別最低手数料より小額の場合は
                    // 特別最低手数料を手数料ｂとする
                    l_bdCommissionB = l_bdSpcMinCommission;
                    log.debug("手数料ｂの計算結果が特別最低手数料より小額の場合：[" + l_bdCommissionB + "]");
                }
                else if (l_bdCommissionB.compareTo(l_bdSpcMaxCommission) == 1)
                {
                    // 手数料bの計算結果が、特別最高手数料より多額の場合は
                    // 特別最高手数料を手数料bとする
                    l_bdCommissionB = l_bdSpcMaxCommission;
                    log.debug("手数料bの計算結果が、特別最高手数料より多額の場合：[" + l_bdCommissionB + "]");
                }
            }
        }

        //c.委託手数料 ＝ 手数料a（b.の計算をした場合は手数料b） × 顧客徴収率
        BigDecimal l_bdCommissionC;
        if(l_bdCommissionB == null)
        {
            l_bdCommissionC = l_bdCommissionA.multiply(l_bdAccountChargeRatio);
        }
        else
        {
            l_bdCommissionC = l_bdCommissionB.multiply(l_bdAccountChargeRatio);
        }
        //小数点以下切り捨て
        long l_lngCommissionC = (long)l_bdCommissionC.doubleValue();
        l_bdCommissionC = new BigDecimal(l_lngCommissionC);

        log.debug("顧客徴収率：[" + l_bdAccountChargeRatio.doubleValue() + "]");
        log.debug("委託手数料cを計算する（小数点以下切り捨て）");
        log.debug("委託手数料c ＝ 手数料a（b.の計算をした場合は手数料b） × 顧客徴収率 ：[" + l_bdCommissionC + "]");

        if (l_bdCommissionC.compareTo(l_bdMaxCommission) == 1)
        {
            //委託手数料の計算結果が、最高手数料より多額の場合は、最高手数料を委託手数料とする。
            l_bdCommissionC = l_bdMaxCommission;
            log.debug("委託手数料の計算結果が、最高手数料より多額の場合：[" + l_bdCommissionC + "]");
        }
        l_dblCommission = l_bdCommissionC.doubleValue();
        
        // 返却情報の設定
        //set 手数料金額
        l_commission.setCommission(l_dblCommission);
        //set 手数料No
        l_commission.setCommissionNo(l_strCommissionNo);
        //set 手数料No枝番
        l_commission.setCommissionRevNo(l_strRevision);
        //set 手数料コースコード
        l_commission.setCommissionCourseDiv(l_eccmRow.getCommissionCourseDiv());
        //set 最低手数料
        l_commission.setMinCommission(l_eccmRow.getMinCommission());
        
        log.exiting(STR_METHOD_NAME);
        
    }
}
@
