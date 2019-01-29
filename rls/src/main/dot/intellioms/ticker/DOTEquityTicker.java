/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3EquityTicker�N���X(DOTEquityTicker.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/18 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.ticker;

import jp.co.dir.dot.intellioms.enums.DataType;

/**
 * (�����e�B�b�J�[)<BR>
 * <BR>
 * WEB3�p���ۃe�B�b�J�[�N���X�̊����p�g���N���X�B<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTEquityTicker extends DOTTicker
{

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param productCode �����R�[�h
     * @param marketCode �s��R�[�h
     */
    public DOTEquityTicker(String productCode, String marketCode)
    {
        super(DataType.EQUITY, productCode, marketCode, null, null, null);
    }

    /**
     * ��ʃR�[�h�A�����R�[�h�A�s��R�[�h���������ꍇ��<code>true</code>�A
     * ����ȊO�̏ꍇ�́A<code>false</code>��Ԃ��B<BR>
     * <BR>
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof DOTEquityTicker)
        {
            DOTEquityTicker target = (DOTEquityTicker) obj;
            if (getDataType().equals(target.getDataType())
                && getProductCode().equals(target.getProductCode())
                && getMarketCode().equals(target.getMarketCode()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * ��ʃR�[�h�A�����R�[�h�A�s��R�[�h���琶�������n�b�V���l��Ԃ��B<BR>
     * <BR>
     * @see Object#hashCode()
     */
    public int hashCode()
    {
        int hash = 17;
        hash = hash * 37 + getDataType().hashCode();
        hash = hash * 37 + getProductCode().hashCode();
        hash = hash * 37 + getMarketCode().hashCode();
        return hash;
    }


    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DOTEquityTicker[");
        sb.append("productCode=").append(getProductCode());
        sb.append(", marketCode=").append(getMarketCode());
        sb.append("]");
        return sb.toString();
    }
}
