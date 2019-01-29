/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : QuoteStatusクラス(QuoteStatus.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (時価サーバとの接続ステータス)<BR>
 * <BR>
 * 時価サーバとの接続ステータスの定数クラス<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class QuoteStatus extends Enum
{
    
    /**
     * (時価サーバとの接続ステータスの整数値定義クラス)<BR>
     * <BR>
     * 時価サーバとの接続ステータスの整数値を定義した内部クラス。<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /** 
         * 未接続 
         */
        public static final int CLOSED = 0;
        
        /** 
         * 接続中 
         */
        public static final int CONNECTING = 1;
        
        /** 
         * 接続済 
         */
        public static final int CONNECTED = 2;
        
    }
    
    /** 
     * 未接続 
     */
    public static final QuoteStatus CLOSED = 
        new QuoteStatus(IntValues.CLOSED, "CLOSED");
    
    /** 
     * 接続中 
     */
    public static final QuoteStatus CONNECTING = 
        new QuoteStatus(IntValues.CONNECTING, "CONNECTING");
    
    /** 
     * 接続済 
     */
    public static final QuoteStatus CONNECTED = 
        new QuoteStatus(IntValues.CONNECTED, "CONNECTED");
    
    /**
     * コンストラクタ<BR>
     * <BR>
     * @param l_intIntValue 整数値
     * @param l_strStringValue 文字列値
     */
    private QuoteStatus(int l_intIntValue, String l_strStringValue)
    {
        super(l_intIntValue, l_strStringValue);
    }
    
    /**
     * (to時価サーバとの接続ステータス)<BR>
     * <BR>
     * 指定した整数値に対応する時価サーバとの接続ステータスを取得する。<BR>
     * <BR>
     * @param intValue 整数値
     * @return 指定した整数値に対応する時価サーバとの接続ステータス
     */
    public static QuoteStatus toQuoteStatus(int l_intValue)
    {
        switch (l_intValue)
        {
            case (IntValues.CLOSED):
                return CLOSED;
            case (IntValues.CONNECTING):
                return CONNECTING;
            case (IntValues.CONNECTED):
                return CONNECTED;
            default:
                throw new IllegalArgumentException(
                    "Undefined intValue. [intValue="+ l_intValue + "]");
        }
    }
    
}
