head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundInstCommission.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信会社別手数料(WEB3MutualFundInstCommission)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 韋念瓊 (中訊) 新規作成
                   2006/06/26 周捷 (中訊) 計算式書  No.022
                   2006/07/18 松本 (SRA) 計算式書  No.023
*/

package webbroker3.mf;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.mf.data.MutualFundInstCommissionParams;
import webbroker3.mf.data.MutualFundInstCommissionRow;
import webbroker3.mf.define.WEB3MFCommissionDivDef;
import webbroker3.mf.define.WEB3MFDealDivDef;
import webbroker3.util.WEB3LogUtility;


/**
 * 投信会社別手数料<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundInstCommission
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundInstCommission.class);

    /**
     * 投信会社別手数料行オブジェクト <BR>
     */
    private MutualFundInstCommissionParams mfInstCommissionParams;
    
    /**
     * (投信会社別手数料)
     * コンストラクタ <BR>
     * <BR>
     * １）以下の条件で、投信会社別手数料テーブルを検索する。 <BR>
     * <BR>
     * [検索条件] <BR>
     * 証券会社コード = 引数.証券会社コード <BR>
     * 銘柄コード = 引数.銘柄コード <BR>
     * 取引区分 = 引数.取引区分 <BR>
     * 注文チャネル = 引数.注文チャネル <BR>
     * 適用開始年月日 <= 引数.発注日 <BR>
     * 適用終了年月日 > 引数.発注日 <BR>
     * <BR>
     * [ソート順] <BR>
     * 適用開始年月日の降順 <BR>
     * <BR>
     * ２）取得されたレコードをthis.投信会社別手数料行にセットする。 <BR>
     * <BR>
     *    ※レコードが複数取得できた場合は、先頭のもの（適用開始年月日が直近のもの）をセットする。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strDealDiv - 取引区分
     * @@param l_strOrderChanel - 注文チャネル
     * @@param l_datBizDate - 発注日
     * @@roseuid 4010AF2C0227
     */

    public WEB3MutualFundInstCommission(
        String l_strInstitutionCode, 
        String l_strProductCode, 
        String l_strDealDiv, 
        String l_strOrderChanel, 
        Date l_datBizDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB3MutualFundInstCommission(String l_strInstitutionCode, " +
            "String l_strProductCode, String l_strDealDiv, " +
            "String l_strOrderChanel, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        //１）以下の条件で、投信会社別手数料テーブルを検索する。 
        //[検索条件] 
        //証券会社コード = 引数.証券会社コード 
        //銘柄コード = 引数.銘柄コード 
        //取引区分 = 引数.取引区分 
        //注文チャネル = 引数.注文チャネル 
        //適用開始年月日 <= 引数.発注日 
        //適用終了年月日 > 引数.発注日 

        //[ソート順] 
        //適用開始年月日の降順 
        
        List l_lisRows = new ArrayList();

        String l_strWhere = 
            "institution_code = ? and product_code = ? and deal_div = ? " +
            "and order_chanel = ? and valid_date_from <= ? and valid_date_to > ?";
            
        String l_strSortCond =
            " valid_date_from desc ";        

        Object[] l_bindVars = {
            l_strInstitutionCode,
            l_strProductCode,
            l_strDealDiv, 
            l_strOrderChanel, 
            l_datBizDate, 
            l_datBizDate};        

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRows = l_queryProcessor.doFindAllQuery(
                MutualFundInstCommissionRow.TYPE,
                l_strWhere,
                l_strSortCond, 
                null, 
                l_bindVars);
        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました when search MutualFundInstCommission");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました when search MutualFundInstCommission");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        if(l_lisRows == null || l_lisRows.size() == 0)
        {
            //－検索結果の件数=0件の場合。
            log.debug("テーブルに該当するデータがありません!"); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME
            );
        }
        //２）取得されたレコードをthis.投信会社別手数料行にセットする。 
        //※レコードが複数取得できた場合は、先頭のもの（適用開始年月日が直近のもの）をセットする。 
        
        MutualFundInstCommissionRow l_mfInstCommissionRow = 
            (MutualFundInstCommissionRow)l_lisRows.get(0);
        
        MutualFundInstCommissionParams l_mfInstCommissionParams = 
            new MutualFundInstCommissionParams(l_mfInstCommissionRow);
        
        this.mfInstCommissionParams = l_mfInstCommissionParams;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get手数料区分)
     * 手数料区分を取得する。 <BR>
     * <BR>
     * this.投信会社別手数料.手数料区分を返却する。<BR> 
     * @@roseuid 40AD92050133
     */
    public String getCommisionDiv() 
    {
        return this.mfInstCommissionParams.getCommissionDiv();
    }
    
    /**
     * (get取引区分)
     * 取引区分を取得する。 <BR> 
     * <BR> 
     * this.投信会社別手数料.取引区分を返却する。 <BR> 
     * <BR> 
     * @@roseuid 40AD92050133
     */
    public String getDealDiv() 
    {
        return this.mfInstCommissionParams.getDealDiv();
    }
    
    /**
     * (get手数料単価、率)
     * 手数料率、単価を取得する。 <BR> 
     * <BR> 
     * 詳細は、計算式書参照。<BR>
     * <BR> 
     * @@param l_mainAccount - 顧客
     * @@param l_mfProduct - 銘柄
     * @@param l_swtProduct - 乗換先の銘柄
     * @@param l_strDesignDiv - 指定区分
     * @@param l_bdFindValue - 検索値
     * @@param l_datBizDate - 発注日
     * @@return BigDecimal
     * @@roseuid 40AD92050133
     */
    public BigDecimal getCommisionPriceRate(
        WEB3GentradeMainAccount l_mainAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_swtProduct, 
        String l_strDesignDiv, 
        BigDecimal l_bdFindValue, 
        Date l_datBizDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getCommisionPriceRate(MainAccount l_mainAccount, " +
            "WEB3MutualFundProduct l_mfProduct, String l_strDesignDiv, " +
            "double l_dblFindValue, Date l_datBizDate)";
        log.entering(STR_METHOD_NAME);
        
        MFCommisionPriceRate l_commisionPriceRate = 
            new MFCommisionPriceRate(
                this.mfInstCommissionParams, 
                l_mainAccount.getInstitution().getInstitutionCode(), 
                l_datBizDate,
                l_bdFindValue);

		//  拡張投信注文マネージャを取得する
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		WEB3MutualFundOrderManager l_orderManager =
			(WEB3MutualFundOrderManager) l_finApp.getTradingModule(
				 ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        
        //手数料計算に使用する手数料単価、率
        BigDecimal l_bdCommisionPriceRate = new BigDecimal("0");
        
        WEB3MutualFundProduct l_wkMfProduct = null;
        //①@ 引数.乗換先の銘柄がnullの場合
        if (l_swtProduct == null)
        {
            log.debug("①@ 引数.乗換先の銘柄がnullの場合");
        	l_wkMfProduct = l_mfProduct;
        }
        //② 引数.乗換先の銘柄がnull以外の場合
        else
        {
        	l_wkMfProduct = l_swtProduct;
            log.debug("② 引数.乗換先の銘柄がnull以外の場合");
        }
        
		//③ 拡張投信注文マネージャ.is手数料無料顧客(引数.顧客,引数.銘柄) ＝ true の場合
		//手数料単価、率として、0を返却する。
		if (l_orderManager.isCommissionFreeAccount(l_mainAccount, l_wkMfProduct))
		{
			log.debug("③手数料無料顧客の場合");
		}
		//④ 拡張投信注文マネージャ.is手数料無料顧客(引数.顧客,引数.銘柄) ＝ false の場合
		else
		{
			log.debug("④手数料無料顧客でない場合");
			//１） 引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合     
	        if (l_mfProduct.isForeignFund() || l_mfProduct.isFWF())
	        {
				log.debug("１）引数.銘柄.is外国投信() ＝ true or 引数.銘柄.isFWF() ＝ true の場合");
	            //(a)
	            //this.手数料区分＝“受渡代金（手数料率）” 
	            //and （ this.取引区分＝“買付” or “募集” ）   
	            //and 引数.指定区分＝“口数指定”の場合       
	            if (WEB3MFCommissionDivDef.PAYMENT_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv())
	                && (WEB3MFDealDivDef.BUY.equals(this.getDealDiv()) 
	                || WEB3MFDealDivDef.RECRUIT.equals(this.getDealDiv())) 
	                && WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv))
	            {          
					log.debug("(a) this.手数料区分＝“受渡代金（手数料率）" +
						"and （ this.取引区分＝“買付” or “募集” ）" +
						"and 引数.指定区分＝“口数指定”の場合");
	                //検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） × 
	                //（ １ － this.手数料単価、率 × （ １ ＋  消費税率(*)  ） ）
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_1);
	            }
	            //(b)
	            //this.手数料区分＝“売買代金（手数料率）” 
	            //and （ this.取引区分＝“買付” or “募集” ） 
	            //and 引数.指定区分＝“金額指定”
				else if (WEB3MFCommissionDivDef.TRADE_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv())
	                && (WEB3MFDealDivDef.BUY.equals(this.getDealDiv()) 
	                || WEB3MFDealDivDef.RECRUIT.equals(this.getDealDiv())) 
	                && WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))
	            {
					log.debug("(b) this.手数料区分＝“売買代金（手数料率）" +
						"and （ this.取引区分＝“買付” or “募集” ）" +
						"and 引数.指定区分＝“金額指定”の場合");
	                //検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） × 
	                //（ １ ＋ this.手数料単価、率 × （ １ ＋ 消費税率(*)  ） ）
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_2);
	            }
	            //(c)上記の条件に当てはまらない場合、検索条件を以下のものとする。
	            else
	            {
					log.debug("(c) それ以外の場合");
	                //検索条件 ⇒ this.数量範囲（自） ≦ 引数.検索値 ≦ this.数量範囲（至）
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_3);
	            }               
	        }
	        //２） 上記以外の場合
	        else
	        {            
				log.debug("２）上記以外の場合");
	            //(a) 
	            //（ （ this.手数料区分＝“受渡代金（手数料率）” and 
	            //（ this.取引区分＝“買付” or “募集” ） and 引数.指定区分＝“口数指定” ） or
	            //（ this.手数料区分＝“売買代金（手数料率）” and 
	            //（this.取引区分＝“解約” or “乗換” ） and 引数.指定区分＝“金額指定”））
				if (((WEB3MFCommissionDivDef.PAYMENT_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv()) &&
	                (WEB3MFDealDivDef.BUY.equals(this.getDealDiv()) || 
	                WEB3MFDealDivDef.RECRUIT.equals(this.getDealDiv())) && 
	                WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv)) ||
	                (WEB3MFCommissionDivDef.TRADE_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv()) &&
	                (WEB3MFDealDivDef.SELL.equals(this.getDealDiv()) || 
	                WEB3MFDealDivDef.SWITCHING.equals(this.getDealDiv())) && 
	                WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv)))) 
	            {
					log.debug("(a) （ （this.手数料区分＝“受渡代金（手数料率）” " +
						"and （this.取引区分＝“買付” or “募集”） " +
						"and 引数.指定区分＝“口数指定”） or" +
						"（this.手数料区分＝“売買代金（手数料率）” " +
						"and （this.取引区分＝“解約” or “乗換”） " +
						"and 引数.指定区分＝“金額指定”））の場合");
	                //検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） × 
	                //（ １ － this.手数料単価、率 × （ １ ＋ 消費税率(*) ） ）
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_1);
	            }
	            //(b)
	            //（ （ this.手数料区分＝“受渡代金（手数料率）” and 
	            //（ this.取引区分＝“解約” or “乗換” ） 
	            //    and 引数.指定区分＝“口数指定” ） or
	            //（ this.手数料区分＝“売買代金（手数料率）” and 
	            //（ this.取引区分＝“買付” or “募集” ） 
	            //    and 引数.指定区分＝“金額指定” ） ）
	            else if (((WEB3MFCommissionDivDef.PAYMENT_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv()) &&
	                (WEB3MFDealDivDef.SELL.equals(this.getDealDiv()) || 
	                WEB3MFDealDivDef.SWITCHING.equals(this.getDealDiv())) &&
	                WEB3DesignateMethodDef.NUMBER.equals(l_strDesignDiv)) ||
	                (WEB3MFCommissionDivDef.TRADE_PRICE_COMMISSION_RATE.equals(this.getCommisionDiv()) &&
	                (WEB3MFDealDivDef.BUY.equals(this.getDealDiv()) || 
	                WEB3MFDealDivDef.RECRUIT.equals(this.getDealDiv())) &&
	                WEB3DesignateMethodDef.AMOUNT.equals(l_strDesignDiv))))
	            {
					log.debug("(b) （ （this.手数料区分＝“受渡代金（手数料率）” " +
						"and （this.取引区分＝“解約” or “乗換”） " +
						"and 引数.指定区分＝“口数指定”） or" +
						"（this.手数料区分＝“売買代金（手数料率）” " +
						"and （this.取引区分＝“買付” or “募集”） " +
						"and 引数.指定区分＝“金額指定”） ）の場合");
	                //検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） × 
	                //（ １ ＋ this.手数料単価、率 × （ １ ＋ 消費税率(*) ） ）
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_2);
	            }
	            //(c)上記の条件に当てはまらない場合、検索条件を以下のものとする。
	            else
	            {
					log.debug("(c) それ以外の場合");
	                //検索条件 ⇒ this.数量範囲（自） ≦ 引数.検索値 ≦ this.数量範囲（至）
	                l_bdCommisionPriceRate = 
	                    l_commisionPriceRate.getMFCommisionPriceRate(MFCommisionPriceRate.CONDITION_3);
	            }
	        }
		}
		
        log.exiting(STR_METHOD_NAME);        
        return l_bdCommisionPriceRate;
    }
    
    private class MFCommisionPriceRate
    {
        /**
         * 検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） ×
         *  （ １ － this.手数料単価、率 × （ １ ＋ this.消費税率 ） ）
         */
        public static final String CONDITION_1 = "condition_1";
        
        /**
         * 検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） ×
         *  （ １ ＋ this.手数料単価、率 × （ １ ＋ this.消費税率 ） ）
         */
        public static final String CONDITION_2 = "condition_2";
        
        /**
         * 検索条件 ⇒ this.数量範囲（自） ≦ 引数.検索値 ≦ this.数量範囲（至）
         */
        public static final String CONDITION_3 = "condition_3";
        
        /**
         * 投信会社別手数料行オブジェクト <BR>
         */
        private MutualFundInstCommissionParams mfInstCommissionParams;
        
        /**
         * 手数料単価、率オブジェクト <BR>
         */
        private Map commisionPriceRate;
        
        private String institutionCode;
      
        private Date bizDate;
        
        private  BigDecimal l_bdNewFindValue;
        
        public MFCommisionPriceRate(
            MutualFundInstCommissionParams l_mfInstCommissionParams, 
            String l_strInstitutionCode, 
            Date l_datBizDate,
            BigDecimal l_bdFindValue)
        {
            this.mfInstCommissionParams = l_mfInstCommissionParams;
            this.institutionCode = l_strInstitutionCode;
            this.bizDate = l_datBizDate;
            commisionPriceRate = new HashMap();
            this.l_bdNewFindValue = l_bdFindValue;
        }

        public BigDecimal getMFCommisionPriceRate(String l_strType) throws WEB3BaseException
        {
            BigDecimal l_bdMFCommisionPriceRate = null;

            if (CONDITION_1.equals(l_strType))
            {
                //検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） ×
                //（ １ － this.手数料単価、率 × （ １ ＋ 消費税率(*)  ） ）
                this.setMFCommisionPriceRate(l_bdNewFindValue, CONDITION_1);
                if (commisionPriceRate.get(CONDITION_1) != null)
                {
                    l_bdMFCommisionPriceRate = (BigDecimal)commisionPriceRate.get(CONDITION_1);
                }
            }
            else if (CONDITION_2.equals(l_strType))
            {
                //検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） ×
                //（ １ ＋ this.手数料単価、率 × （ １ ＋ 消費税率(*) ） ）
                this.setMFCommisionPriceRate(l_bdNewFindValue, CONDITION_2);
                if (commisionPriceRate.get(CONDITION_2) != null)
                {
                    l_bdMFCommisionPriceRate = (BigDecimal)commisionPriceRate.get(CONDITION_2);
                }       
            }
            else if (CONDITION_3.equals(l_strType))
            {
                //検索条件 ⇒ this.数量範囲（自） ≦ 引数.検索値 ≦ this.数量範囲（至）
                this.setMFCommisionPriceRate(l_bdNewFindValue, CONDITION_3);
                if (commisionPriceRate.get(CONDITION_3) != null)
                {
                    l_bdMFCommisionPriceRate = (BigDecimal)commisionPriceRate.get(CONDITION_3);
                } 
            }
            return l_bdMFCommisionPriceRate;
        }
        
        public void setMFCommisionPriceRate(BigDecimal l_bdSearchValue, String l_strFalg) throws WEB3BaseException
        {
            //数量範囲(自)
            String l_strAmountFrom = "";
            //数量範囲(至)
            String l_strAmountTo = "";
            //手数料単価、率
            String l_strCommissionPriceRate = "";
            
            //消費税率(*)            
            WEB3GentradeTaxRate l_taxRate = new WEB3GentradeTaxRate(
                this.institutionCode, 
                WEB3DutyTypeDef.CONSUMPTION_TAX, 
                new Timestamp(this.bizDate.getTime()));
        
            //税率を取得する。        
            //取得した税率インスタンス.get税率()メソッドをコールする。            
			BigDecimal l_bdConsumptionTaxRate = new BigDecimal(Double.toString(l_taxRate.getTaxRate())).multiply(
				new BigDecimal("0.01"));
            
            for (int i = 1; i <= 10; i++)
            {
                if (i < 10)
                {
                    l_strAmountFrom = "amount_from_" + "0" + i;
                    l_strAmountTo = "amount_to_" + "0" + i;
                    l_strCommissionPriceRate = "commission_price_rate_" + "0" + i;
                }
                else
                {
                    l_strAmountFrom = "amount_from_" + i;
                    l_strAmountTo = "amount_to_" + i;
                    l_strCommissionPriceRate = "commission_price_rate_" + i;
                }
                
                //数量範囲(自)
                long l_lngAmountFrom = 0;
                if (this.mfInstCommissionParams.getColumn(l_strAmountFrom) != null)
                {
                    l_lngAmountFrom = 
                        ((Long)this.mfInstCommissionParams.getColumn(l_strAmountFrom)).longValue(); 
                }
                
                //数量範囲（至)
                long l_lngAmountTo = 0;
                if (this.mfInstCommissionParams.getColumn(l_strAmountTo) != null)
                {
                    l_lngAmountTo = 
                        ((Long)this.mfInstCommissionParams.getColumn(l_strAmountTo)).longValue(); 
                }
                
                //手数料単価、率
				BigDecimal l_bdCommissionPriceRate = null;
                if (this.mfInstCommissionParams.getColumn(l_strCommissionPriceRate) != null)
                {
                	if(WEB3MFCommissionDivDef.UNIT_COUNT_COMMISSION_PRICE.equals(this.mfInstCommissionParams.getCommissionDiv()))
                	{
						l_bdCommissionPriceRate = 
							new BigDecimal(
								((Double)this.mfInstCommissionParams.getColumn(l_strCommissionPriceRate)).toString());
                	}
                	else
                	{
						l_bdCommissionPriceRate = 
							new BigDecimal(
								((Double)this.mfInstCommissionParams.getColumn(l_strCommissionPriceRate)).toString());
						l_bdCommissionPriceRate = 
							l_bdCommissionPriceRate.multiply(new BigDecimal("0.01"));
                	}
                }

                //検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） ×
                //（ １ － this.手数料単価、率 × （ １ ＋ 消費税率(*)  ） ）
                if (CONDITION_1.equals(l_strFalg))
                {
					BigDecimal l_bdSearchValueTmp =
						new BigDecimal(l_lngAmountTo).multiply(
							new BigDecimal("1").subtract(
								l_bdCommissionPriceRate.multiply(new BigDecimal("1").add(l_bdConsumptionTaxRate))));

					if (l_bdSearchValue.compareTo(l_bdSearchValueTmp) <= 0)
					{
						if (!commisionPriceRate.containsKey(CONDITION_1))
						{
							commisionPriceRate.put(CONDITION_1, l_bdCommissionPriceRate);
						}
					}
                }
				//検索条件 ⇒ 引数.検索値 ≦ this.数量範囲（至） ×
				//（ １ ＋ this.手数料単価、率 × （ １ ＋ 消費税率(*)  ） ）
                else if (CONDITION_2.equals(l_strFalg))
                {
					BigDecimal l_bdSearchValueTmp =
						new BigDecimal(l_lngAmountTo).multiply(
							new BigDecimal("1").add(
								l_bdCommissionPriceRate.multiply(new BigDecimal("1").add(l_bdConsumptionTaxRate))));
                    
					if (l_bdSearchValue.compareTo(l_bdSearchValueTmp) <= 0)
                    {
                        if (!commisionPriceRate.containsKey(CONDITION_2))
                        {
                            commisionPriceRate.put(CONDITION_2, l_bdCommissionPriceRate);
                        }
                    }
                }
				//検索条件 ⇒ this.数量範囲（自） ≦ 引数.検索値 ≦ this.数量範囲（至）
                else if (CONDITION_3.equals(l_strFalg))
                {
					if (l_bdSearchValue.compareTo(new BigDecimal(l_lngAmountFrom)) >= 0 &&
						l_bdSearchValue.compareTo(new BigDecimal(l_lngAmountTo)) <= 0)
                    {
                        if (!commisionPriceRate.containsKey(CONDITION_3))
                        {
                            commisionPriceRate.put(CONDITION_3, l_bdCommissionPriceRate);
                        }
                    }
                }
            }           
        }
    }
}
@
