/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IfoDerivativeTypeクラス(IfoDerivativeType.java)
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
public final class IfoDerivativeType extends Enum
{
    
    public static final class IntValues
    {
        public static final int OTHER = 0;
        public static final int FUTURES = 1;
        public static final int CALL_OPTIONS = 2;
        public static final int PUT_OPTIONS = 3;
    }
    
    public static final IfoDerivativeType OTHER = new IfoDerivativeType(IntValues.OTHER, "OTHER");
    public static final IfoDerivativeType FUTURES = new IfoDerivativeType(IntValues.FUTURES, "FUTURES");
    public static final IfoDerivativeType CALL_OPTIONS = new IfoDerivativeType(IntValues.CALL_OPTIONS, "CALL_OPTIONS");
    public static final IfoDerivativeType PUT_OPTIONS = new IfoDerivativeType(IntValues.PUT_OPTIONS, "PUT_OPTIONS");

    /**
     * @param v
     * @param s
     */
    private IfoDerivativeType(int v, String s)
    {
        super(v, s);
    }
    
    public static IfoDerivativeType toIfoDerivativeType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.OTHER : return OTHER;
            case IntValues.FUTURES : return FUTURES;
            case IntValues.CALL_OPTIONS : return CALL_OPTIONS;
            case IntValues.PUT_OPTIONS : return PUT_OPTIONS;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

    public static PutAndCall toPutAndCall(int intValue)
    {
        switch (intValue)
        {
            case IntValues.CALL_OPTIONS : return PutAndCall.CALL;
            case IntValues.PUT_OPTIONS : return PutAndCall.PUT;
            case IntValues.OTHER :
            case IntValues.FUTURES :
            default :
                throw new IllegalArgumentException("toPutAndCall: no corresponding number. [intValue=" + intValue + "]");
        }
    }
}
