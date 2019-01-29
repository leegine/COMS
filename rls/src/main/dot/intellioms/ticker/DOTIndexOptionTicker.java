/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3IndexOptionTicker�N���X(DOTIndexOptionTicker.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/18 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.ticker;


import com.fitechlabs.fin.intellioms.omsclt.Price;

import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;

/**
 * (�w���I�v�V�����e�B�b�J�[)<BR>
 * <BR>
 * WEB3�p���ۃe�B�b�J�[�N���X�̎w���I�v�V�����p�g���N���X�B<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTIndexOptionTicker extends DOTTicker
{

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param productCode �����R�[�h
     * @param marketCode �s��R�[�h
     * @param monthOfDelivery ����
     * @param putAndCall �v�b�g&�R�[��
     * @param strikePrice �s�g���i
     */
    public DOTIndexOptionTicker(String underlyingProductCode,
        String marketCode, String monthOfDelivery, PutAndCall putAndCall,
        Price strikePrice)
    {
        super(DataType.INDEX_OPTION, underlyingProductCode, marketCode,
            monthOfDelivery, putAndCall, strikePrice);
    }

    /**
     * ��ʃR�[�h�A�����R�[�h�A�s��R�[�h�A�����A�v�b�g&�R�[���A�s�g���i���������ꍇ��<code>true</code>�A
     * ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B<BR>
     * <BR>
     * @see Object#equals(Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof DOTIndexOptionTicker)
        {
            DOTIndexOptionTicker target = (DOTIndexOptionTicker) obj;
            if (getDataType().equals(target.getDataType())
                && getProductCode().equals(target.getProductCode())
                && getMarketCode().equals(target.getMarketCode())
                && getMonthOfDelivery().equals(target.getMonthOfDelivery())
                && getPutAndCall().equals(target.getPutAndCall())
                && getStrikePrice().equals(target.getStrikePrice()))
            {
                return true;
            }
        }
        return false;
    }


    /**
     * ��ʃR�[�h�A�����R�[�h�A�s��R�[�h�A�����A�v�b�g&�R�[���A�s�g���i���琶�������n�b�V���l��Ԃ��B<BR>
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
        hash = hash * 37 + getPutAndCall().hashCode();
        hash = hash * 37 + getStrikePrice().hashCode();
        return hash;
    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("DOTIndexOptionTicker[");
        sb.append("underlyingProductCode=").append(getProductCode());
        sb.append(", marketCode=").append(getMarketCode());
        sb.append(", monthOfDelivery=").append(getMonthOfDelivery());
        sb.append(", putAndCall=").append(getPutAndCall());
        sb.append(", strikePrice=").append(getStrikePrice());
        sb.append("]");
        return sb.toString();
    }
}
