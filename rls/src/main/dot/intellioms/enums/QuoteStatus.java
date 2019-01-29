/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : QuoteStatus�N���X(QuoteStatus.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/09 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (�����T�[�o�Ƃ̐ڑ��X�e�[�^�X)<BR>
 * <BR>
 * �����T�[�o�Ƃ̐ڑ��X�e�[�^�X�̒萔�N���X<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class QuoteStatus extends Enum
{
    
    /**
     * (�����T�[�o�Ƃ̐ڑ��X�e�[�^�X�̐����l��`�N���X)<BR>
     * <BR>
     * �����T�[�o�Ƃ̐ڑ��X�e�[�^�X�̐����l���`���������N���X�B<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /** 
         * ���ڑ� 
         */
        public static final int CLOSED = 0;
        
        /** 
         * �ڑ��� 
         */
        public static final int CONNECTING = 1;
        
        /** 
         * �ڑ��� 
         */
        public static final int CONNECTED = 2;
        
    }
    
    /** 
     * ���ڑ� 
     */
    public static final QuoteStatus CLOSED = 
        new QuoteStatus(IntValues.CLOSED, "CLOSED");
    
    /** 
     * �ڑ��� 
     */
    public static final QuoteStatus CONNECTING = 
        new QuoteStatus(IntValues.CONNECTING, "CONNECTING");
    
    /** 
     * �ڑ��� 
     */
    public static final QuoteStatus CONNECTED = 
        new QuoteStatus(IntValues.CONNECTED, "CONNECTED");
    
    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param l_intIntValue �����l
     * @param l_strStringValue ������l
     */
    private QuoteStatus(int l_intIntValue, String l_strStringValue)
    {
        super(l_intIntValue, l_strStringValue);
    }
    
    /**
     * (to�����T�[�o�Ƃ̐ڑ��X�e�[�^�X)<BR>
     * <BR>
     * �w�肵�������l�ɑΉ����鎞���T�[�o�Ƃ̐ڑ��X�e�[�^�X���擾����B<BR>
     * <BR>
     * @param intValue �����l
     * @return �w�肵�������l�ɑΉ����鎞���T�[�o�Ƃ̐ڑ��X�e�[�^�X
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
