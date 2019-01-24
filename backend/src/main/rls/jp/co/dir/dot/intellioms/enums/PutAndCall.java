/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PutAndCall�N���X(PutAndCall.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (�v�b�g&�R�[��)<BR>
 * <BR>
 * �v�b�g&�R�[���̒萔�N���X<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class PutAndCall extends Enum
{
    
    /**
     * (�v�b�g&�R�[���̐����l��`�N���X)<BR>
     * <BR>
     * �v�b�g&�R�[���̐����l���`���������N���X�B<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /**
         * ����`
         */
        public static final int UNDEFINED = 0;
        
        /**
         * �v�b�g
         */
        public static final int PUT = 1;
        
        /**
         * �R�[��
         */
        public static final int CALL = 2;
        
    }
    
    /**
     * ����`
     */
    public static final PutAndCall UNDEFINED = new PutAndCall(IntValues.UNDEFINED, "UNDEFINED");
    
    /**
     * �v�b�g
     */
    public static final PutAndCall PUT = new PutAndCall(IntValues.PUT, "P");
    
    /**
     * �R�[��
     */
    public static final PutAndCall CALL = new PutAndCall(IntValues.CALL, "C");
    
    /** ������l */
    private final String stringValue;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param v �����l
     * @param s ������l
     */
    private PutAndCall(int v, String s)
    {
        super(v, s);
        this.stringValue = s;
    }
    
    /**
     * (to������l)<BR>
     * <BR>
     * ������l���擾����B<BR>
     * <BR>
     * @return ������l
     */
    public String toStringValue()
    {
        return stringValue;
    }

}
