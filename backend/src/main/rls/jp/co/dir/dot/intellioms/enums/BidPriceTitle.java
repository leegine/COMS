/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : BidPriceTitleクラス(BidPriceTitle.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 山田　卓司(FLJ) 新規作成
                   2006/07/04 山田　卓司(FLJ) 買気配値タイトル、売気配値タイトル項目追加対応
                                             「3:板寄せ中」と「5:特別気配」を追加
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (売気配値タイトル)<BR>
 * <BR>
 * 売気配値タイトルの定数クラス<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class BidPriceTitle extends Enum
{

    /**
     * (売気配値タイトルの整数値定義クラス)<BR>
     * <BR>
     * 売気配値タイトルの整数値を定義した内部クラス。<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /**
         * 未定義
         */
        public static final int UNDEFINED = 0;
        
        /** 
         * 板寄せ中
         */
        public static final int ITAYOSECHU = 3;
        
        /**
         * 売気配
         */
        public static final int BID = 4;
        
        /** 
         * 特別気配
         */
        public static final int SPECIAL_QUOTATION = 5;
        
    }
    
    
    /**
     * 未定義
     */
    public static final BidPriceTitle UNDEFINED = new BidPriceTitle(IntValues.UNDEFINED, "UNDEFINED");
    
    /** 
     * 板寄せ中
     */
    public static final BidPriceTitle ITAYOSECHU = new BidPriceTitle(IntValues.ITAYOSECHU, "ITAYOSECHU");

    /**
     * 売気配
     */
    public static final BidPriceTitle BID = new BidPriceTitle(IntValues.BID, "BID");
    
    /** 
     * 特別気配
     */
    public static final BidPriceTitle SPECIAL_QUOTATION = new BidPriceTitle(IntValues.SPECIAL_QUOTATION, "SPECIAL_QUOTATION");

    /**
     * コンストラクタ<BR>
     * <BR>
     * @param v 整数値
     * @param s 文字列値
     */
    private BidPriceTitle(int v, String s)
    {
        super(v, s);
    }
    
    /**
     * (to売気配値タイトル)<BR>
     * <BR>
     * 指定した整数値に対応する売気配値タイトルを取得する。<BR>
     * <BR>
     * @param intValue 整数値
     * @return 指定した整数値に対応する売気配値タイトル
     */
    public static BidPriceTitle toBidPriceTitle(int intValue)
    {
        switch (intValue)
        {
            case IntValues.ITAYOSECHU : return ITAYOSECHU;
            case IntValues.BID : return BID;
            case IntValues.SPECIAL_QUOTATION : return SPECIAL_QUOTATION;
            case IntValues.UNDEFINED : return UNDEFINED;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
