/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3QuoteHelperクラス(DOTQuoteHelper.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/17 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.quote;


import com.fitechlabs.fin.intellioms.omsclt.Price;

import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.IndexType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.ticker.DOTEquityTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexFutureTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexOptionTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexTicker;
import jp.co.dir.dot.intellioms.ticker.DOTTicker;

/**
 * (時価アダプタヘルパー)<BR>
 * <BR>
 * 時価アダプターのヘルパークラス。<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteHelper
{
    
    /**
     * 時価アダプタヘルパーのインスタンス
     */
    private static final DOTQuoteHelper INSTANCE = new DOTQuoteHelper();
    
    /**
     * コンストラクタ
     */
    private DOTQuoteHelper()
    {
    }
    
    /**
     * (getインスタンス)<BR>
     * <BR>
     * 時価アダプタへルパーのインスタンスを取得する。<BR>
     * 
     * @return 時価アダプタヘルパーのインスタンス
     */
    public static final DOTQuoteHelper getInstance()
    {
        return INSTANCE;
    }

    /**
     * (createWEB3用抽象ティッカー)<BR>
     * <BR>
     * 指定した時価情報に対応するWEB3用抽象ティッカーを作成する。<BR>
     * 
     * @param l_quoteData 時価情報
     * @return 指定した時価情報に対応するWEB3用抽象ティッカー
     */
    public DOTTicker createTicker(DOTQuoteData l_quoteData)
    {
        
        DataType l_dataType = l_quoteData.getDataType();
        
        if (DataType.EQUITY.equals(l_dataType))
        {
            return new DOTEquityTicker(
                l_quoteData.getProductCode(), 
                l_quoteData.getMarketCode());
        } else if (DataType.INDEX.equals(l_dataType))
        {
            return new DOTIndexTicker(
                IndexType.toIndexType(l_quoteData.getProductCode()));
        } else if (DataType.INDEX_FUTURE.equals(l_dataType))
        {
            return new DOTIndexFutureTicker(
                l_quoteData.getProductCode(),
                l_quoteData.getMarketCode(),
                l_quoteData.getMonthOfDelivery());
        } else if (DataType.INDEX_OPTION.equals(l_dataType))
        {
            return new DOTIndexOptionTicker(
                l_quoteData.getProductCode(),
                l_quoteData.getMarketCode(),
                l_quoteData.getMonthOfDelivery(),
                l_quoteData.getPutAndCall(),
                new Price(l_quoteData.getStrikePrice()));
        }
        
        throw new RuntimeException(l_dataType + " is unknown data type.");
        
    }

}