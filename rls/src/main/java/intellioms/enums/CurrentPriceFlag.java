/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : CurrentPriceFlag�N���X(CurrentPriceFlag.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (���ݒl�t���O)<BR>
 * <BR>
 * ���ݒl�t���O�̒萔�N���X<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class CurrentPriceFlag extends Enum
{
    
    /**
     * (���ݒl�t���O�̐����l��`�N���X)<BR>
     * <BR>
     * ���ݒl�t���O�̐����l���`���������N���X�B<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {
        
        /**
         * ����`
         */
        public static final int UNDEFINED = 9;
        
        /**
         * �ʏ�
         */
        public static final int NORMAL = 0;
        
        /**
         * �I�l
         */
        public static final int CLOSING_PRICE = 1;
    }
    
    
    /**
     * ����`
     */
    public static final CurrentPriceFlag UNDEFINED = new CurrentPriceFlag(IntValues.UNDEFINED, "UNDEFINED");
    
    /**
     * �ʏ�
     */
    public static final CurrentPriceFlag NORMAL = new CurrentPriceFlag(IntValues.NORMAL, "NORMAL");
    
    /**
     * �I�l
     */
    public static final CurrentPriceFlag CLOSING_PRICE = new CurrentPriceFlag(IntValues.CLOSING_PRICE, "CLOSING_PRICE");

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param v �����l
     * @param s ������l
     */
    private CurrentPriceFlag(int v, String s)
    {
        super(v, s);
    }
    
    /**
     * (to���ݒl�t���O)<BR>
     * <BR>
     * �w�肵�������l�ɑΉ����錻�ݒl�t���O���擾����B<BR>
     * <BR>
     * @param intValue �����l
     * @return �w�肵�������l�ɑΉ����錻�ݒl�t���O
     */
    public static CurrentPriceFlag toCurrentPriceFlag(int intValue)
    {
        switch (intValue)
        {
            case IntValues.UNDEFINED : return UNDEFINED;
            case IntValues.NORMAL : return NORMAL;
            case IntValues.CLOSING_PRICE : return CLOSING_PRICE;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
