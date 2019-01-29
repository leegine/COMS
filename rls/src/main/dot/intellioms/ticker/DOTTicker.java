/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3Tickerクラス(DOTTicker.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/17 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.ticker;


import com.fitechlabs.fin.intellioms.omsclt.Price;

import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;

/**
 * (WEB3用抽象ティッカー)<BR>
 * <BR>
 * 受信した時価情報からルールエンジンの<code>Ticker</code>を取得するための
 * キーとなるクラス。<BR>
 * ルールエンジンで使用される<code>Ticker</code>のキーは、銘柄IDと市場IDと
 * なっているが、時価サーバより受信する時価情報のキーは、種別コード、銘柄コード、
 * 市場コード、限月、プット&コール、行使価格となっており銘柄IDと市場IDは含まれていない。
 * このため、時価サーバより受信した時価情報より種別コード、銘柄コード、
 * 市場コード、限月、プット&コール、行使価格をキーとする、WEB3用抽象ティッカークラス
 * のインスタンスを生成し、それをキーとしてルールエンジンに設定する<code>Ticker</code>
 * を取得する。<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public abstract class DOTTicker
{

    /** 種別コード */
    private final DataType dataType;

    /** 銘柄コード */
    private final String productCode;

    /** 市場コード */
    private final String marketCode;

    /** 限月 */
    private final String monthOfDelivery;

    /** プット&コール */
    private final PutAndCall putAndCall;

    /** 行使価格 */
    private final Price strikePrice;

    /**
     * コンストラクタ<BR>
     * <BR>
     * @param dataType 種別コード
     * @param productCode 銘柄コード
     * @param marketCode 市場コード
     * @param monthOfDelivery 限月
     * @param putAndCall プット&コール
     * @param strikePrice 行使価格
     */
    protected DOTTicker(DataType dataType, String productCode,
        String marketCode, String monthOfDelivery, PutAndCall putAndCall,
        Price strikePrice)
    {
        this.dataType = dataType;
        this.productCode = productCode;
        this.marketCode = marketCode;
        this.monthOfDelivery = monthOfDelivery;
        this.putAndCall = putAndCall;
        this.strikePrice = strikePrice;
    }

    /**
     * (get種別コード)<BR>
     * <BR>
     * 種別コードを取得する。<BR>
     * <BR>
     * @return 種別コード
     */
    public DataType getDataType()
    {
        return dataType;
    }

    /**
     * (get銘柄コード)<BR>
     * <BR>
     * 銘柄コードを取得する。<BR>
     * <BR>
     * @return 銘柄コード
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * (get市場コード)<BR>
     * <BR>
     * 市場コードを取得する。<BR>
     * <BR>
     * @return 市場コード
     */
    public String getMarketCode()
    {
        return marketCode;
    }

    /**
     * (get限月)<BR>
     * <BR>
     * 限月を取得する。<BR>
     * <BR>
     * @return 限月
     */
    public String getMonthOfDelivery()
    {
        return monthOfDelivery;
    }

    /**
     * (getプット&コール)<BR>
     * <BR>
     * プット&コールを取得する。<BR>
     * <BR>
     * @return プット&コール
     */
    public PutAndCall getPutAndCall()
    {
        return putAndCall;
    }

    /**
     * (get行使価格)<BR>
     * <BR>
     * 行使価格を取得する。<BR>
     * <BR>
     * @return 行使価格
     */
    public Price getStrikePrice()
    {
        return strikePrice;
    }

}