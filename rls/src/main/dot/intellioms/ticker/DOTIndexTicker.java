/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3IndexTicker�N���X(DOTIndexTicker.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/18 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.ticker;

import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.IndexType;

/**
 * (�w���e�B�b�J�[)<BR>
 * <BR>
 * WEB3�p���ۃe�B�b�J�[�N���X�̎w���p�g���N���X�B<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTIndexTicker extends DOTTicker
{

    /** �w���^�C�v */
    private final IndexType indexType;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param indexType �w���^�C�v
     */
    public DOTIndexTicker(IndexType indexType)
    {
        super(DataType.INDEX, indexType.getProductCode(), null, null, null,
            null);
        this.indexType = indexType;
    }

    /**
     * (get�w���^�C�v)<BR>
     * <BR>
     * �w���^�C�v���擾����B<BR>
     * <BR>
     * @return �w���^�C�v
     */
    public IndexType getIndexType()
    {
        return indexType;
    }

    /**
     * ��ʃR�[�h�A�����R�[�h���������ꍇ��<code>true</code>�A
     * ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B<BR>
     * <BR>
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof DOTIndexTicker)
        {
            DOTIndexTicker target = (DOTIndexTicker) obj;
            if (getDataType().equals(target.getDataType())
                && getProductCode().equals(target.getProductCode()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * ��ʃR�[�h�A�����R�[�h���琶�������n�b�V���l��Ԃ��B<BR>
     * <BR>
     * @see Object#hashCode()
     */
    public int hashCode()
    {
        int hash = 17;
        hash = hash * 37 + getDataType().hashCode();
        hash = hash * 37 + getProductCode().hashCode();
        return hash;
    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DOTIndexTicker[");
        sb.append("indexType=").append(getIndexType());
        sb.append(", productCode=").append(getProductCode());
        sb.append("]");
        return sb.toString();
    }
}
