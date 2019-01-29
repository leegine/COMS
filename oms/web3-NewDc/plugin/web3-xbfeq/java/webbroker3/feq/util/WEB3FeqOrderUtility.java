head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文のユーティリティ(WEB3FeqOrderUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 王暁傑 (中訊) 新規作成
*/
package webbroker3.feq.util;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 注文のユーティリティクラス。<BR>
 * <BR>
 * 
 * @@author 王暁傑(中訊)
 * @@version 1.0
 */
public class WEB3FeqOrderUtility
{
    public static String FLOOR = "0";
    public static String CEIL = "1";
    public static String ROUND = "3";
    public static String CUTOFF = "4"; 
    /**
     * 
     */
    public WEB3FeqOrderUtility()
    {
        super();
    }

    /**
     * (get執行条件)<BR>
     * 引数の執行条件より、WEB3の執行条件を取得し返却する。<BR>
     * 外国株式注文マネージャ.get執行条件に委譲（deligate）する。<BR>
     * @@param l_strExecutionConditionType - (執行条件)<BR>
     * SONARの執行条件<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType
     * @@throws WEB3BaseException
     * @@roseuid 4296E5D801E8
     */
    public static FeqExecutionConditionType getExecutionCondition(
        String l_strExecutionConditionType) throws WEB3BaseException
    {
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        return l_orderManager.getExecutionCondition(l_strExecutionConditionType);
        
    }

    /**
     * (get執行条件（SONAR）)<BR>
     * 引数の執行条件より、SONARの執行条件を取得し返却する。<BR>
     * <BR>
     * 外国株式注文マネージャ.get執行条件に委譲（deligate）する。<BR>
     * @@param l_strExecutionConditionType - (執行条件)<BR>
     * WEB3の執行条件<BR>
     *
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4296E826011D
     */
    public static String getExecutionConditionTypeSonar(
        String l_strExecutionConditionType) throws WEB3BaseException
    {
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        return l_orderManager.getExecutionConditionTypeSonar(l_strExecutionConditionType);
    }
    
    /**
     * (get特定口座区分)<BR>
     * 引数の執行条件より、特定口座区分を取得し返却する。<BR>
     * <BR>
     * @@param l_strTaxTypeType- (特定口座区分)<BR>
     * WEB3の特定口座区分<BR>
     *
     * @@return TaxTypeEnum
     * @@throws WEB3BaseException
     * @@roseuid 4296E826011D
     */
    public static TaxTypeEnum getTaxType(
        String l_strTaxTypeType) throws WEB3BaseException
    {
        // １）引数の特定口座区分＝”0：一般口座” の場合
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxTypeType))
        {
            return TaxTypeEnum.NORMAL;
        }
        else 
        {
            //２）引数の特定口座区分＝”1:特定口座” の場合
            if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxTypeType))
            {
                return TaxTypeEnum.SPECIAL;
            }
        }
        return null;
    }
    
    /**
     * (get特定口座区分)<BR>
     * 引数の執行条件より、特定口座区分を取得し返却する。<BR>
     * <BR>
     * @@param l_taxTypeEnum- (特定口座区分)<BR>
     * WEB3の特定口座区分<BR>
     *
     * @@return String 
     * @@throws WEB3BaseException
     * @@roseuid 4296E826011D
     */
    public static String getTaxType(
        TaxTypeEnum l_taxTypeEnum) throws WEB3BaseException
    {
        // １）引数の特定口座区分＝”0：一般口座” の場合
        if (TaxTypeEnum.NORMAL.equals(l_taxTypeEnum))
        {
            return WEB3TaxTypeSpecialDef.NORMAL;
        }
        else 
        {
            //２）引数の特定口座区分＝”1:特定口座” の場合
            if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum))
            {
                return WEB3TaxTypeSpecialDef.SPECIAL;
            }
        }
        return null;
    }
    
    /**
     * (get丸め値)<BR>
     * 引数の丸め方式と小数桁数より、double値を丸める。<BR>
     * 例：<BR>
     *  FLOOR(以下)の場合
     * 　@　@1234.2345678,2  返却値 1234.23<BR>
     * 　@　@1234.2345678,3  返却値 1234.234<BR>
     * 　@　@-1234.2345678,2 返却値-1234.24<BR>
     * 　@　@-1234.2345678,3 返却値-1234.235<BR>
     * <BR>
     *  CEIL(以上)の場合
     * 　@　@1234.2345678,2  返却値 1234.24<BR>
     * 　@　@1234.2345678,3  返却値 1234.235<BR>
     * 　@　@-1234.2345678,2 返却値-1234.23<BR>
     * 　@　@-1234.2345678,3 返却値-1234.234<BR>
     * <BR>
     *  ROUND(通常の四捨五入)の場合
     * 　@　@1234.2345678,2  返却値 1234.23<BR>
     * 　@　@1234.2345678,3  返却値 1234.235<BR>
     * 　@　@-1234.2345678,2 返却値-1234.23<BR>
     * 　@　@-1234.2345,3    返却値-1234.235<BR>
     * 　@　@-1234.2345678,3 返却値-1234.235<BR>
     * <BR>
     *  CUTOFF(切捨)の場合
     * 　@　@1234.2345678,2  返却値 1234.23<BR>
     * 　@　@1234.2345678,3  返却値 1234.234<BR>
     * 　@　@-1234.2345678,2 返却値-1234.23<BR>
     * 　@　@-1234.2345678,3 返却値-1234.234<BR>
     * <BR>
     * @@param l_dblValueForCut 丸めされる値
     * @@param l_intSacle 桁数
     * 0より小さくない場合、小数の丸めの桁数<BR>
     * 0より小さい場合、整数の丸めの桁数<BR>
     * @@param l_strDealType 丸め方式
     * 0 以下 1 以上 2 Javaの四捨五入 3 通常の四捨五入 4 切捨<BR>  
     * @@return 丸め後の値
     */
    public static double getCutOutValue(
        double l_dblValueForCut, 
        int l_intSacle, 
        String l_strDealType)
    {
        double l_dblResult = 0.0D;
        
        if (FLOOR.equals(l_strDealType))
        {
            BigDecimal l_bdValue =
                new BigDecimal(String.valueOf(l_dblValueForCut)).setScale(l_intSacle, BigDecimal.ROUND_FLOOR);
            l_dblResult = l_bdValue.doubleValue();
        }
        else if (CEIL.equals(l_strDealType))
        {
            BigDecimal l_bdValue =
                new BigDecimal(String.valueOf(l_dblValueForCut)).setScale(l_intSacle, BigDecimal.ROUND_CEILING);
            l_dblResult = l_bdValue.doubleValue();
        }
        else if (ROUND.equals(l_strDealType))
        {
            BigDecimal l_bdValue =
               new BigDecimal(String.valueOf(l_dblValueForCut)).setScale(l_intSacle, BigDecimal.ROUND_HALF_UP);
            l_dblResult = l_bdValue.doubleValue();
        }
        else if (CUTOFF.equals(l_strDealType))
        {
            BigDecimal l_bdValue =
                new BigDecimal(String.valueOf(l_dblValueForCut)).setScale(l_intSacle, BigDecimal.ROUND_DOWN);
            l_dblResult = l_bdValue.doubleValue();
        }
        
        return l_dblResult;
    }


    /**
     * (Remainderがゼロの判断)<BR>
     * BigIngeterでmod
     * 
     * isRemainderZero(100 , 2) : true
     * isRemainderZero(100 , 0.2) : true
     * 
     * @@param l_dbl1 
     * @@param l_dbl1
     * @@return boolean
     * true Remainder = 0 
     * @@roseuid 4296E826011D
     */
    public static boolean isRemainderZero(double l_dbl1, double l_dbl2)
    {
        return (getRemainder(l_dbl1, l_dbl2) == 0.0D);
    }
    
    /**
     * (Remainderを取得)<BR>
     * BigIngeterでmod
     * 
     * getRemainder(100 , 2) : 0
     * getRemainder(100 , 0.3) : 0.1
     * 
     * @@param l_dbl1 
     * @@param l_dbl1
     * @@return double
     * @@roseuid 4296E826011D
     */
    public static double getRemainder(double l_dbl1, double l_dbl2)
    {
        BigDecimal l_bdNum1 =
            new BigDecimal(String.valueOf(l_dbl1));
        BigDecimal l_bdNum2 =
            new BigDecimal(String.valueOf(l_dbl2));
        BigDecimal l_bdNum3 =
            l_bdNum1.divide(l_bdNum2, 0, BigDecimal.ROUND_DOWN);
        BigDecimal l_bdNum4 = l_bdNum2.multiply(l_bdNum3);
        
        double l_dblRemainder = l_bdNum1.subtract(l_bdNum4).doubleValue();
        
        return l_dblRemainder;
    }

    /**
     * (minus operate)<BR>
     * BigDecimalでマイナスし、引数の最大の小数桁数が返却値の小数桁数とする、<BR>
     * 丸め方式は切捨です。<BR>
     * <BR>
     * @@param l_dbl1 
     * @@param l_dbl1
     * @@return double
     * @@roseuid 4296E826011D
     */
    public static double decimalMinus(double l_dbl1, double l_dbl2)
    {
        int l_intPos1 = 
            WEB3StringTypeUtility.getFractionDigits(new Double(l_dbl1).toString());
        int l_intPos2 =
            WEB3StringTypeUtility.getFractionDigits(new Double(l_dbl2).toString());

        int l_intPos = Math.max(l_intPos1, l_intPos2);
        
        BigDecimal l_bdNum1 = 
            new BigDecimal(l_dbl1).setScale(l_intPos, BigDecimal.ROUND_HALF_UP);
        BigDecimal l_bdNum2 =  
            new BigDecimal(l_dbl2).setScale(l_intPos, BigDecimal.ROUND_HALF_UP);
        
        BigDecimal l_bdReturn = l_bdNum1.subtract(l_bdNum2);  

        return l_bdReturn.doubleValue();
    }

    /**
     * (plus operate)<BR>
     * BigDecimalでプラスし、引数の最大の小数桁数が返却値の小数桁数とする、<BR>
     * 丸め方式は切捨です。<BR>
     * <BR>
     * @@param l_dbl1 
     * @@param l_dbl1
     * @@return double
     * @@roseuid 4296E826011D
     */
    public static double decimalPlus(double l_dbl1, double l_dbl2)
    {
        return decimalMinus(l_dbl1, (-1) * l_dbl2);
    }
}
@
