/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : AskPriceTitleクラス(AskPriceTitle.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 山田　卓司(FLJ) 新規作成
                   2006/07/04 山田　卓司(FLJ) 買気配値タイトル、売気配値タイトル項目追加対応
                                             「5:特別気配」を追加
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (買気配値タイトル)<BR>
 * <BR>
 * 買気配値タイトルの定数クラス<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class AskPriceTitle extends Enum
{
    
    /**
     * (買気配値タイトルの整数値定義クラス)<BR>
     * <BR>
     * 買気配値タイトルの整数値を定義した内部クラス。<BR>
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
         * 買気配
         */
        public static final int ASK = 1;
        
        /** 
         * 売買停止
         */
        public static final int TRADING_SUSPENDED = 2;
        
        /** 
         * 板寄せ中
         */
        public static final int ITAYOSECHU = 3;
        
        /** 
         * 特別気配
         */
        public static final int SPECIAL_QUOTATION = 5;
        
    }
    
    /** 
     * 未定義
     */
    public static final AskPriceTitle UNDEFINED = new AskPriceTitle(IntValues.UNDEFINED, "UNDEFINED");
    
    /** 
     * 買気配
     */
    public static final AskPriceTitle ASK = new AskPriceTitle(IntValues.ASK, "ASK");
    
    /** 
     * 売買停止
     */
    public static final AskPriceTitle TRADING_SUSPENDED = new AskPriceTitle(IntValues.TRADING_SUSPENDED, "TRADING_SUSPENDED");
    
    /** 
     * 板寄せ中
     */
    public static final AskPriceTitle ITAYOSECHU = new AskPriceTitle(IntValues.ITAYOSECHU, "ITAYOSECHU");

    /** 
     * 特別気配
     */
    public static final AskPriceTitle SPECIAL_QUOTATION = new AskPriceTitle(IntValues.SPECIAL_QUOTATION, "SPECIAL_QUOTATION");

    /**
     * コンストラクタ<BR>
     * <BR>
     * @param v 整数値
     * @param s 文字列値
     */
    private AskPriceTitle(int v, String s)
    {
        super(v, s);
    }
    
    /**
     * (to買気配値タイトル)<BR>
     * <BR>
     * 指定した整数値に対応する買気配値タイトルを取得する。<BR>
     * <BR>
     * @param intValue 整数値
     * @return 指定した整数値に対応する買気配値タイトル
     */
    public static AskPriceTitle toAskPriceTitle(int intValue)
    {
        switch (intValue)
        {
            case IntValues.ASK : return ASK;
            case IntValues.TRADING_SUSPENDED : return TRADING_SUSPENDED;
            case IntValues.ITAYOSECHU : return ITAYOSECHU;
            case IntValues.SPECIAL_QUOTATION : return SPECIAL_QUOTATION;
            case IntValues.UNDEFINED : return UNDEFINED;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
