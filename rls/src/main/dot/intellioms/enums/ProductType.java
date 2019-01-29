/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ProductTypeクラス(ProductType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/01/20 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;
import com.fitechlabs.fin.intellioms.enums.OrderType;


/**
 * 
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public final class ProductType extends Enum
{
    
    public static final class IntValues
    {
        public static final int OTHER = 0;
        public static final int EQUITY = 1;
        public static final int IFO = 6;
    }
    
    public static final ProductType OTHER = new ProductType(IntValues.OTHER, "OTHER");
    public static final ProductType EQUITY = new ProductType(IntValues.EQUITY, "EQUITY");
    public static final ProductType IFO = new ProductType(IntValues.IFO, "IFO");

    /**
     * @param v
     * @param s
     */
    private ProductType(int v, String s)
    {
        super(v, s);
    }
    
    public static ProductType toProductType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.OTHER : return OTHER;
            case IntValues.EQUITY : return EQUITY;
            case IntValues.IFO : return IFO;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }
    
    public static ProductType toProductType(OrderType l_orderType)
    {
        switch (l_orderType.toValue())
        {
            case OrderType.Value.EQUITY_CASH :
            case OrderType.Value.EQUITY_MARGIN :
                return EQUITY;
            case OrderType.Value.INDEX_FUTURE :
            case OrderType.Value.INDEX_OPTION :
                return IFO;
            default :
                return OTHER;
        }
    }

}
