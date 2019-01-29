/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OrderEventTypeクラス(OrderEventType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/23 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * 
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public final class OrderEventType extends Enum
{
    
    public static final class IntValues
    {
        public static final int NEW  = 1;
        public static final int MODIFY = 2;
        public static final int CANCEL = 3;
    }
    
    public static final OrderEventType NEW  = new OrderEventType(IntValues.NEW , "NEW ");
    public static final OrderEventType MODIFY = new OrderEventType(IntValues.MODIFY, "MODIFY");
    public static final OrderEventType CANCEL = new OrderEventType(IntValues.CANCEL, "CANCEL");

    /**
     * @param v
     * @param s
     */
    private OrderEventType(int v, String s)
    {
        super(v, s);
    }
    
    public static OrderEventType toOrderEventType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.NEW  : return NEW ;
            case IntValues.MODIFY : return MODIFY;
            case IntValues.CANCEL : return CANCEL;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }
    
}
