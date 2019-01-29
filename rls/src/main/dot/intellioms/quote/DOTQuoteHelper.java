/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteHelper�N���X(DOTQuoteHelper.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/17 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;


import com.fitechlabs.fin.intellioms.omsclt.Price;

import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.IndexType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.ticker.DOTEquityTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexFutureTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexOptionTicker;
import jp.co.dir.dot.intellioms.ticker.DOTIndexTicker;
import jp.co.dir.dot.intellioms.ticker.DOTTicker;

/**
 * (�����A�_�v�^�w���p�[)<BR>
 * <BR>
 * �����A�_�v�^�[�̃w���p�[�N���X�B<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteHelper
{
    
    /**
     * �����A�_�v�^�w���p�[�̃C���X�^���X
     */
    private static final DOTQuoteHelper INSTANCE = new DOTQuoteHelper();
    
    /**
     * �R���X�g���N�^
     */
    private DOTQuoteHelper()
    {
    }
    
    /**
     * (get�C���X�^���X)<BR>
     * <BR>
     * �����A�_�v�^�փ��p�[�̃C���X�^���X���擾����B<BR>
     * 
     * @return �����A�_�v�^�w���p�[�̃C���X�^���X
     */
    public static final DOTQuoteHelper getInstance()
    {
        return INSTANCE;
    }

    /**
     * (createWEB3�p���ۃe�B�b�J�[)<BR>
     * <BR>
     * �w�肵���������ɑΉ�����WEB3�p���ۃe�B�b�J�[���쐬����B<BR>
     * 
     * @param l_quoteData �������
     * @return �w�肵���������ɑΉ�����WEB3�p���ۃe�B�b�J�[
     */
    public DOTTicker createTicker(DOTQuoteData l_quoteData)
    {
        
        DataType l_dataType = l_quoteData.getDataType();
        
        if (DataType.EQUITY.equals(l_dataType))
        {
            return new DOTEquityTicker(
                l_quoteData.getProductCode(), 
                l_quoteData.getMarketCode());
        } else if (DataType.INDEX.equals(l_dataType))
        {
            return new DOTIndexTicker(
                IndexType.toIndexType(l_quoteData.getProductCode()));
        } else if (DataType.INDEX_FUTURE.equals(l_dataType))
        {
            return new DOTIndexFutureTicker(
                l_quoteData.getProductCode(),
                l_quoteData.getMarketCode(),
                l_quoteData.getMonthOfDelivery());
        } else if (DataType.INDEX_OPTION.equals(l_dataType))
        {
            return new DOTIndexOptionTicker(
                l_quoteData.getProductCode(),
                l_quoteData.getMarketCode(),
                l_quoteData.getMonthOfDelivery(),
                l_quoteData.getPutAndCall(),
                new Price(l_quoteData.getStrikePrice()));
        }
        
        throw new RuntimeException(l_dataType + " is unknown data type.");
        
    }

}