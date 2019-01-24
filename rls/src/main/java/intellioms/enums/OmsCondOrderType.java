/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OrderTypeクラス(OrderType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/01/19 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * 
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public final class OmsCondOrderType extends Enum
{
    
    public static final class IntValues
    {
        public static final int CHAIN = 1;
        public static final int OCO = 2;
        public static final int IFD = 3;
        public static final int PRICE = 4;
        public static final int W_PRICE = 5;
    }
    
    public static final OmsCondOrderType CHAIN = new OmsCondOrderType(IntValues.CHAIN, "CHAIN");
    public static final OmsCondOrderType OCO = new OmsCondOrderType(IntValues.OCO, "OCO");
    public static final OmsCondOrderType IFD = new OmsCondOrderType(IntValues.IFD, "IFD");
    public static final OmsCondOrderType PRICE = new OmsCondOrderType(IntValues.PRICE, "PRICE");
    public static final OmsCondOrderType W_PRICE = new OmsCondOrderType(IntValues.W_PRICE, "W_PRICE");

    /**
     * @param v
     * @param s
     */
    private OmsCondOrderType(int v, String s)
    {
        super(v, s);
    }
    
    public static OmsCondOrderType toOrderType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.CHAIN : return CHAIN;
            case IntValues.OCO : return OCO;
            case IntValues.IFD : return IFD;
            case IntValues.PRICE : return PRICE;
            case IntValues.W_PRICE : return W_PRICE;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
