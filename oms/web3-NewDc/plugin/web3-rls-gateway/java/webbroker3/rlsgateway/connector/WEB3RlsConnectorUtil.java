head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ルールエンジンへの注文送信ユーティリティ(WEB3RlsConnectorUtil.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 齋藤 栄三(FLJ) 新規作成
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.enums.CondOrderType;
import com.fitechlabs.fin.intellioms.enums.OrderType;
import com.fitechlabs.fin.intellioms.enums.Side;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.rlsgateway.define.WEB3OrderTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 *
 * ルールエンジンへの注文送信ユーティリティ
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsConnectorUtil
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsConnectorUtil.class);
    
    /**
     * to売買Enum <BR>
     * <BR>
     * 引数に応じて売買Enumに変換する。 <BR>
     * <BR>
     * @@param int - (変換元の値)
     * @@return Side
     */
    public static Side toSide(int intValue)
    {
        switch (intValue)
        {
            case WEB3OrderTypeDef.EQUITY_BUY :
            case WEB3OrderTypeDef.MARGIN_LONG :
            case WEB3OrderTypeDef.CLOSE_MARGIN_SHORT :
            case WEB3OrderTypeDef.SWAP_MARGIN_LONG :
            case WEB3OrderTypeDef.IDX_FUTURES_BUY_TO_OPEN :
            case WEB3OrderTypeDef.IDX_FUTURES_BUY_TO_CLOSE :
            case WEB3OrderTypeDef.IDX_OPTIONS_BUY_TO_OPEN :
            case WEB3OrderTypeDef.IDX_OPTIONS_BUY_TO_CLOSE :
                return Side.BUY;

            case WEB3OrderTypeDef.EQUITY_SELL :
            case WEB3OrderTypeDef.MARGIN_SHORT :
            case WEB3OrderTypeDef.CLOSE_MARGIN_LONG :
            case WEB3OrderTypeDef.SWAP_MARGIN_SHORT :
            case WEB3OrderTypeDef.IDX_FUTURES_SELL_TO_OPEN :
            case WEB3OrderTypeDef.IDX_FUTURES_SELL_TO_CLOSE :
            case WEB3OrderTypeDef.IDX_OPTIONS_SELL_TO_OPEN :
            case WEB3OrderTypeDef.IDX_OPTIONS_SELL_TO_CLOSE :
                return Side.SELL;

            
            case WEB3OrderTypeDef.UNDEFINED :
            default :
                throw new IllegalArgumentException("toSide: no corresponding number. [intValue=" + intValue + "]");
        }
    }
    
    /**
     * to注文種別Enum <BR>
     * <BR>
     * 引数に応じて注文種別Enumに変換する。 <BR>
     * <BR>
     * @@param int - (変換元の値)
     * @@return OrderType
     */
    public static  OrderType toOrderType(int intValue)
    {
        switch (intValue)
        {
            case WEB3OrderTypeDef.EQUITY_BUY :
            case WEB3OrderTypeDef.EQUITY_SELL :
                return OrderType.EQUITY_CASH;
            
            case WEB3OrderTypeDef.MARGIN_LONG :
            case WEB3OrderTypeDef.MARGIN_SHORT :
            case WEB3OrderTypeDef.CLOSE_MARGIN_LONG :
            case WEB3OrderTypeDef.CLOSE_MARGIN_SHORT :
            case WEB3OrderTypeDef.SWAP_MARGIN_LONG :
            case WEB3OrderTypeDef.SWAP_MARGIN_SHORT :
                return OrderType.EQUITY_MARGIN;
                
            case WEB3OrderTypeDef.IDX_FUTURES_BUY_TO_OPEN :
            case WEB3OrderTypeDef.IDX_FUTURES_SELL_TO_OPEN :
            case WEB3OrderTypeDef.IDX_FUTURES_BUY_TO_CLOSE :
            case WEB3OrderTypeDef.IDX_FUTURES_SELL_TO_CLOSE :
                return OrderType.INDEX_FUTURE;
            
            case WEB3OrderTypeDef.IDX_OPTIONS_BUY_TO_OPEN :
            case WEB3OrderTypeDef.IDX_OPTIONS_BUY_TO_CLOSE :
            case WEB3OrderTypeDef.IDX_OPTIONS_SELL_TO_OPEN :
            case WEB3OrderTypeDef.IDX_OPTIONS_SELL_TO_CLOSE :
                return OrderType.INDEX_OPTION;

            
            case WEB3OrderTypeDef.UNDEFINED :
            default :
                throw new IllegalArgumentException("toOrderType: no corresponding number. [intValue=" + intValue + "]");
        }
    }
    
    /**
     * toルールエンジン銘柄ID <BR>
     * <BR>
     * 引数の銘柄IDをルールエンジン管理の銘柄IDに変換する。 <BR>
     * <BR>
     * @@param long - (銘柄ID)
     * @@return long
     */    
    public static long toRlsProductId(long l_productId)
    {
        String l_strRlsProudctId = String.valueOf(l_productId).substring(WEB3RlsConnectorConstants.INSTITUTION_CODE_SIZE);
        return new Long(l_strRlsProudctId).longValue();
    }
    
    /**
     * toルールエンジン市場ID <BR>
     * <BR>
     * 引数の市場IDをルールエンジン管理の市場IDに変換する。 <BR>
     * <BR>
     * @@param long - (市場ID)
     * @@return long
     */        
    public static long toRlsMarketId(long l_marketId)
    {
        String l_strRlsMarketId = String.valueOf(l_marketId).substring(WEB3RlsConnectorConstants.INSTITUTION_CODE_SIZE);
        return new Long(l_strRlsMarketId).longValue();
    }
    
    /**
     * to条件付き注文ID <BR>
     * <BR>
     * 引数に応じて条件付き注文IDに変換する。 <BR>
     * <BR>
     * @@param ProductTypeEnum - (銘柄タイプ)
     * @@param CondOrderType - (条件付き注文タイプ)
     * @@param long - (注文ID)
     * @@return long
     */        
    public static long toRlsCondOrderId(ProductTypeEnum l_productType, CondOrderType l_condOrderType, long l_lngOrderId)
    {
        StringBuffer l_sb = new StringBuffer(WEB3RlsConnectorConstants.RLS_COND_ORDER_SIZE); 
        String l_strOderId = String.valueOf(l_lngOrderId);
        int l_intOrderLength = l_strOderId.length();
        
        appendProductType(l_sb, l_productType);

        appendCondOrderType(l_sb, l_condOrderType);
        
        int l_sbLength = l_sb.length();
        
        appendZero(l_sb, WEB3RlsConnectorConstants.RLS_COND_ORDER_SIZE - l_sbLength - l_intOrderLength);
        
        l_sb.append(l_lngOrderId);
        
        return Long.parseLong(l_sb.toString()); 
    }
    
    /**
     * 0補正 <BR>
     * <BR>
     * 引数のバッファ@文字列に引数で指定した数の0を補正する。 <BR>
     * <BR>
     * @@param StringBuffer - (バッファ@文字列)
     * @@param long - (0を追加する数)
     */        
    private static void appendZero(StringBuffer l_sb, int length)
    {
        for (int i = 0; i < length; i++)
        {
            l_sb.append(0);
        }
    }
    
    /**
     * 銘柄タイプを文字列変換補正 <BR>
     * <BR>
     * 引数のバッファ@文字列に引数で指定した銘柄タイプの数値表現に変換して補正する。 <BR>
     * <BR>
     * @@param StringBuffer - (バッファ@文字列)
     * @@param ProductTypeEnum - (銘柄タイプ)
     */        
    private static void appendProductType(StringBuffer l_sb, ProductTypeEnum l_productType)
    {
        if(ProductTypeEnum.EQUITY.equals(l_productType))
        {
            l_sb.append(l_productType.intValue());
        }
        else if(ProductTypeEnum.IFO.equals(l_productType))
        {
            l_sb.append(l_productType.intValue());
        }
        else
        {
            throw new IllegalArgumentException("No implementation. [ProductTypeEnum=" + l_productType + "]");            
        }
    }
    
    /**
     * 条件付き注文タイプを文字列変換補正 <BR>
     * <BR>
     * 引数のバッファ@文字列に引数で指定した条件付き注文タイプの数値表現に変換して補正する。 <BR>
     * <BR>
     * @@param StringBuffer - (バッファ@文字列)
     * @@param CondOrderType - (条件付き注文タイプ)
     */        
    private static void appendCondOrderType(StringBuffer l_sb, CondOrderType l_condOrderType)
    {
        if(CondOrderType.PRICE.equals(l_condOrderType))
        {
            l_sb.append(l_condOrderType.toValue());
        }
        else if(CondOrderType.SIMPLE.equals(l_condOrderType))
        {
            l_sb.append(l_condOrderType.toValue());
        }
        else
        {
            throw new IllegalArgumentException("No implementation. [CondOrderType=" + l_condOrderType + "]");            
        }
    }
    

    /**
     * long型のプロパティを取得する。
     *
     * @@param key プロパティ・キー
     * @@param def デフォルト値
     * @@return 指定されたプロパティの値
     */
    public static long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        long l_lngReturnValue = l_lngDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            try
            {
                l_lngReturnValue = Long.parseLong(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
                log.error("Illegal property name=" + l_strKey +", value=" + l_strValue);
            }
        }
        return l_lngReturnValue;
    }
    
    
    protected static String getProperty(String l_strKey)
    {
        return GtlUtils.getTradingSystem().getPreference(l_strKey);
    }

}
@
