/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : DataType�N���X(DataType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/25 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 * (��ʃR�[�h)<BR>
 * <BR>
 * ��ʃR�[�h�̒萔�N���X<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public final class DataType extends Enum
{

    /**
     * (��ʃR�[�h�̐����l��`�N���X)<BR>
     * <BR>
     * ��ʃR�[�h�̐����l���`���������N���X�B<BR>
     * <BR>
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    public static final class IntValues
    {

        /**
         * ����
         */
        public static final int EQUITY = 1;

        /**
         * �w��
         */
        public static final int INDEX = 2;

        /**
         * �����w���敨
         */
        public static final int INDEX_FUTURE = 3;

        /**
         * �����w���I�v�V����
         */
        public static final int INDEX_OPTION = 4;

    }

    /**
     * ����
     */
    public static final DataType EQUITY = new DataType(IntValues.EQUITY, "EQUITY");

    /**
     * �w��
     */
    public static final DataType INDEX = new DataType(IntValues.INDEX, "INDEX");

    /**
     * �����w���敨
     */
    public static final DataType INDEX_FUTURE = new DataType(IntValues.INDEX_FUTURE, "INDEX_FUTURE");

    /**
     * �����w���I�v�V����
     */
    public static final DataType INDEX_OPTION = new DataType(IntValues.INDEX_OPTION, "INDEX_OPTION");

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param v �����l
     * @param s ������l
     */
    private DataType(int v, String s)
    {
        super(v, s);
    }

    /**
     * (to��ʃR�[�h)<BR>
     * <BR>
     * �w�肵�������l�ɑΉ������ʃR�[�h���擾����B<BR>
     * <BR>
     * @param intValue �����l
     * @return �w�肵�������l�ɑΉ������ʃR�[�h
     */
    public static DataType toDataType(int intValue)
    {
        switch (intValue)
        {
            case (IntValues.EQUITY) :
                return DataType.EQUITY;
            case (IntValues.INDEX) :
                return DataType.INDEX;
            case (IntValues.INDEX_FUTURE) :
                return DataType.INDEX_FUTURE;
            case (IntValues.INDEX_OPTION) :
                return DataType.INDEX_OPTION;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

}
