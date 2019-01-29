/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : RealTypeEnum�N���X(RealType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (���A���敪)<BR>
 * <BR>
 * ���A���敪�̒萔�N���X<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class RealType extends Enum
{

    /**
     * (���A���敪�̐����l��`�N���X)<BR>
     * <BR>
     * ���A���敪�̐����l���`���������N���X�B<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {

        /**
         * ���A��
         */
        public static final int REAL = 1;

        /**
         * 20���f�B���C
         */
        public static final int DELAY = 2;

        /**
         * ���l
         */
        public static final int CLOSING_PRICE = 3;

    }

    /**
     * ���A��
     */
    public static final RealType REAL = new RealType(IntValues.REAL, "REAL");

    /**
     * 20���f�B���C
     */
    public static final RealType DELAY = new RealType(IntValues.DELAY, "DELAY");

    /**
     * ���l
     */
    public static final RealType CLOSING_PRICE = new RealType(IntValues.CLOSING_PRICE, "CLOSING_PRICE");

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param v �����l
     * @param s ������l
     */
    private RealType(int v, String s)
    {
        super(v, s);
    }

    /**
     * (to���A���敪)<BR>
     * <BR>
     * �w�肵�������l�ɑΉ����郊�A���敪���擾����B<BR>
     * <BR>
     * @param intValue �����l
     * @return �w�肵�������l�ɑΉ����郊�A���敪
     */
    public static RealType toRealType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.REAL : return RealType.REAL;
            case IntValues.DELAY : return RealType.DELAY;
            case IntValues.CLOSING_PRICE : return RealType.CLOSING_PRICE;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
