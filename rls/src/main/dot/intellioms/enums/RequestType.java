/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : RequestTypeクラス(RequestType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/01/19 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.CondOrderOpType;
import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * 
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public final class RequestType extends Enum
{
    
    public static final class IntValues
    {
        public static final int EXECUTE = 1;
        public static final int REGISTER = 2;
        public static final int CANCEL = 3;
        public static final int MODIFY = 4;
    }
    
    public static final RequestType EXECUTE = new RequestType(IntValues.EXECUTE, "EXECUTE");
    public static final RequestType REGISTER = new RequestType(IntValues.REGISTER, "REGISTER");
    public static final RequestType CANCEL = new RequestType(IntValues.CANCEL, "CANCEL");
    public static final RequestType MODIFY = new RequestType(IntValues.MODIFY, "MODIFY");

    /**
     * @param v
     * @param s
     */
    private RequestType(int v, String s)
    {
        super(v, s);
    }
    
    public static RequestType toRequestType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.EXECUTE : return EXECUTE;
            case IntValues.REGISTER : return REGISTER;
            case IntValues.CANCEL : return CANCEL;
            case IntValues.MODIFY : return MODIFY;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

    public static CondOrderOpType toCondOrderOpType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.REGISTER :return CondOrderOpType.NEW;
            case IntValues.CANCEL :return CondOrderOpType.CANCEL;
            case IntValues.MODIFY :return CondOrderOpType.MODIFY;
            
            case IntValues.EXECUTE :
            default :
                throw new IllegalArgumentException("toCondOrderOpType: no corresponding number. [intValue=" + intValue + "]");
        }
    }
}
