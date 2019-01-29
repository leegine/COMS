/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PutAndCallクラス(PutAndCall.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (プット&コール)<BR>
 * <BR>
 * プット&コールの定数クラス<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class PutAndCall extends Enum
{
    
    /**
     * (プット&コールの整数値定義クラス)<BR>
     * <BR>
     * プット&コールの整数値を定義した内部クラス。<BR>
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
         * プット
         */
        public static final int PUT = 1;
        
        /**
         * コール
         */
        public static final int CALL = 2;
        
    }
    
    /**
     * 未定義
     */
    public static final PutAndCall UNDEFINED = new PutAndCall(IntValues.UNDEFINED, "UNDEFINED");
    
    /**
     * プット
     */
    public static final PutAndCall PUT = new PutAndCall(IntValues.PUT, "P");
    
    /**
     * コール
     */
    public static final PutAndCall CALL = new PutAndCall(IntValues.CALL, "C");
    
    /** 文字列値 */
    private final String stringValue;

    /**
     * コンストラクタ<BR>
     * <BR>
     * @param v 整数値
     * @param s 文字列値
     */
    private PutAndCall(int v, String s)
    {
        super(v, s);
        this.stringValue = s;
    }
    
    /**
     * (to文字列値)<BR>
     * <BR>
     * 文字列値を取得する。<BR>
     * <BR>
     * @return 文字列値
     */
    public String toStringValue()
    {
        return stringValue;
    }

}
