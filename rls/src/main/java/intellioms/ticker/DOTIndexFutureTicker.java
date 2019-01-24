/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3IndexFutureTicker�N���X(DOTIndexFutureTicker.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/18 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.ticker;

import jp.co.dir.dot.intellioms.enums.DataType;

/**
 * (�w���敨�e�B�b�J�[)<BR>
 * <BR>
 * WEB3�p���ۃe�B�b�J�[�N���X�̎w���敨�p�g���N���X�B<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTIndexFutureTicker extends DOTTicker
{

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param underlyingProductCode �����Y�����R�[�h
     * @param marketCode �s��R�[�h
     * @param monthOfDelivery ����
     */
    public DOTIndexFutureTicker(String underlyingProductCode,
        String marketCode, String monthOfDelivery)
    {
        super(DataType.INDEX_FUTURE, underlyingProductCode, marketCode,
            monthOfDelivery, null, null);
    }

    /**
     * ��ʃR�[�h�A�����R�[�h�A�s��R�[�h�A�������������ꍇ��<code>true</code>�A
     * ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B<BR>
     * <BR>
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof DOTIndexFutureTicker)
        {
            DOTIndexFutureTicker target = (DOTIndexFutureTicker) obj;
            if (getDataType().equals(target.getDataType())
                && getProductCode().equals(target.getProductCode())
                && getMarketCode().equals(target.getMarketCode())
                && getMonthOfDelivery().equals(target.getMonthOfDelivery()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * ��ʃR�[�h�A�����R�[�h�A�s��R�[�h�A�������琶�������n�b�V���l��Ԃ��B<BR>
     * <BR>
     * @see Object#hashCode()
     */
    public int hashCode()
    {
        int hash = 17;
        hash = hash * 37 + getDataType().hashCode();
        hash = hash * 37 + getProductCode().hashCode();
        hash = hash * 37 + getMarketCode().hashCode();
        hash = hash * 37 + getMonthOfDelivery().hashCode();
        return hash;
    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DOTIndexFutureTicker[");
        sb.append("underlyingProductCode=").append(getProductCode());
        sb.append(", marketCode=").append(getMarketCode());
        sb.append(", monthOfDelivery=").append(getMonthOfDelivery());
        sb.append("]");
        return sb.toString();
    }
}
