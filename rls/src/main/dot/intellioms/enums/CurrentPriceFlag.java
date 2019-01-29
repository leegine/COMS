/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : CurrentPriceFlagクラス(CurrentPriceFlag.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (現在値フラグ)<BR>
 * <BR>
 * 現在値フラグの定数クラス<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class CurrentPriceFlag extends Enum
{
    
    /**
     * (現在値フラグの整数値定義クラス)<BR>
     * <BR>
     * 現在値フラグの整数値を定義した内部クラス。<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /**
         * 未定義
         */
        public static final int UNDEFINED = 9;
        
        /**
         * 通常
         */
        public static final int NORMAL = 0;
        
        /**
         * 終値
         */
        public static final int CLOSING_PRICE = 1;
    }
    
    
    /**
     * 未定義
     */
    public static final CurrentPriceFlag UNDEFINED = new CurrentPriceFlag(IntValues.UNDEFINED, "UNDEFINED");
    
    /**
     * 通常
     */
    public static final CurrentPriceFlag NORMAL = new CurrentPriceFlag(IntValues.NORMAL, "NORMAL");
    
    /**
     * 終値
     */
    public static final CurrentPriceFlag CLOSING_PRICE = new CurrentPriceFlag(IntValues.CLOSING_PRICE, "CLOSING_PRICE");

    /**
     * コンストラクタ<BR>
     * <BR>
     * @param v 整数値
     * @param s 文字列値
     */
    private CurrentPriceFlag(int v, String s)
    {
        super(v, s);
    }
    
    /**
     * (to現在値フラグ)<BR>
     * <BR>
     * 指定した整数値に対応する現在値フラグを取得する。<BR>
     * <BR>
     * @param intValue 整数値
     * @return 指定した整数値に対応する現在値フラグ
     */
    public static CurrentPriceFlag toCurrentPriceFlag(int intValue)
    {
        switch (intValue)
        {
            case IntValues.UNDEFINED : return UNDEFINED;
            case IntValues.NORMAL : return NORMAL;
            case IntValues.CLOSING_PRICE : return CLOSING_PRICE;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
