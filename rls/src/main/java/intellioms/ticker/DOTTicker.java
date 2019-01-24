/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3Ticker�N���X(DOTTicker.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/17 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.ticker;


import com.fitechlabs.fin.intellioms.omsclt.Price;

import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;

/**
 * (WEB3�p���ۃe�B�b�J�[)<BR>
 * <BR>
 * ��M����������񂩂烋�[���G���W����<code>Ticker</code>���擾���邽�߂�
 * �L�[�ƂȂ�N���X�B<BR>
 * ���[���G���W���Ŏg�p�����<code>Ticker</code>�̃L�[�́A����ID�Ǝs��ID��
 * �Ȃ��Ă��邪�A�����T�[�o����M���鎞�����̃L�[�́A��ʃR�[�h�A�����R�[�h�A
 * �s��R�[�h�A�����A�v�b�g&�R�[���A�s�g���i�ƂȂ��Ă������ID�Ǝs��ID�͊܂܂�Ă��Ȃ��B
 * ���̂��߁A�����T�[�o����M��������������ʃR�[�h�A�����R�[�h�A
 * �s��R�[�h�A�����A�v�b�g&�R�[���A�s�g���i���L�[�Ƃ���AWEB3�p���ۃe�B�b�J�[�N���X
 * �̃C���X�^���X�𐶐����A������L�[�Ƃ��ă��[���G���W���ɐݒ肷��<code>Ticker</code>
 * ���擾����B<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public abstract class DOTTicker
{

    /** ��ʃR�[�h */
    private final DataType dataType;

    /** �����R�[�h */
    private final String productCode;

    /** �s��R�[�h */
    private final String marketCode;

    /** ���� */
    private final String monthOfDelivery;

    /** �v�b�g&�R�[�� */
    private final PutAndCall putAndCall;

    /** �s�g���i */
    private final Price strikePrice;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * @param dataType ��ʃR�[�h
     * @param productCode �����R�[�h
     * @param marketCode �s��R�[�h
     * @param monthOfDelivery ����
     * @param putAndCall �v�b�g&�R�[��
     * @param strikePrice �s�g���i
     */
    protected DOTTicker(DataType dataType, String productCode,
        String marketCode, String monthOfDelivery, PutAndCall putAndCall,
        Price strikePrice)
    {
        this.dataType = dataType;
        this.productCode = productCode;
        this.marketCode = marketCode;
        this.monthOfDelivery = monthOfDelivery;
        this.putAndCall = putAndCall;
        this.strikePrice = strikePrice;
    }

    /**
     * (get��ʃR�[�h)<BR>
     * <BR>
     * ��ʃR�[�h���擾����B<BR>
     * <BR>
     * @return ��ʃR�[�h
     */
    public DataType getDataType()
    {
        return dataType;
    }

    /**
     * (get�����R�[�h)<BR>
     * <BR>
     * �����R�[�h���擾����B<BR>
     * <BR>
     * @return �����R�[�h
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * (get�s��R�[�h)<BR>
     * <BR>
     * �s��R�[�h���擾����B<BR>
     * <BR>
     * @return �s��R�[�h
     */
    public String getMarketCode()
    {
        return marketCode;
    }

    /**
     * (get����)<BR>
     * <BR>
     * �������擾����B<BR>
     * <BR>
     * @return ����
     */
    public String getMonthOfDelivery()
    {
        return monthOfDelivery;
    }

    /**
     * (get�v�b�g&�R�[��)<BR>
     * <BR>
     * �v�b�g&�R�[�����擾����B<BR>
     * <BR>
     * @return �v�b�g&�R�[��
     */
    public PutAndCall getPutAndCall()
    {
        return putAndCall;
    }

    /**
     * (get�s�g���i)<BR>
     * <BR>
     * �s�g���i���擾����B<BR>
     * <BR>
     * @return �s�g���i
     */
    public Price getStrikePrice()
    {
        return strikePrice;
    }

}