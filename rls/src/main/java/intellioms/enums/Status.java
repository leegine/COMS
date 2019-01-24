/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : StatusEnum�N���X(StatusEnum.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/01/19 �V���@�h�O(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * 
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public final class Status extends Enum
{
    
    public static final class IntValues
    {
        public static final int UNPROCESSED = 0;
        public static final int PROCESSED = 1;
        public static final int ACCEPTED = 2;
        public static final int PROGRAM_ERROR = 8;
        public static final int DATA_ERROR = 9;
    }
    
    public static final Status UNPROCESSED = new Status(IntValues.UNPROCESSED, "UNPROCESSED");
    public static final Status PROCESSED = new Status(IntValues.PROCESSED, "PROCESSED");
    public static final Status ACCEPTED = new Status(IntValues.ACCEPTED, "ACCEPTED");
    public static final Status PROGRAM_ERROR = new Status(IntValues.PROGRAM_ERROR, "PROGRAM_ERROR");
    public static final Status DATA_ERROR = new Status(IntValues.DATA_ERROR, "DATA_ERROR");

    /**
     * @param v
     * @param s
     */
    private Status(int v, String s)
    {
        super(v, s);
    }
    
    public static Status toStatusEnum(int intValue)
    {
        switch (intValue)
        {
            case IntValues.UNPROCESSED : return UNPROCESSED;
            case IntValues.PROCESSED : return PROCESSED;
            case IntValues.ACCEPTED : return ACCEPTED;
            case IntValues.PROGRAM_ERROR : return PROGRAM_ERROR;
            case IntValues.DATA_ERROR : return DATA_ERROR;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
