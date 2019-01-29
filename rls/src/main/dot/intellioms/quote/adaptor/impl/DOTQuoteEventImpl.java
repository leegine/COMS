/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteEventImpl�N���X(DOTQuoteEventImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/26 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.sql.Timestamp;
import java.util.Date;


import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.AskPriceTitle;
import jp.co.dir.dot.intellioms.enums.BidPriceTitle;
import jp.co.dir.dot.intellioms.enums.CurrentPriceFlag;
import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.PutAndCall;
import jp.co.dir.dot.intellioms.enums.RealType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEvent;



/**
 * (�����C�x���gImpl)<BR>
 * <BR>
 * �����C�x���g�̎����N���X�B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteEventImpl extends DOTBaseQuoteData implements DOTQuoteEvent
{
    
    /** ���K�[ */
    private static final Log log = Log.getLogger(DOTQuoteEventImpl.class);
    
    /** ��� */
    private byte[] baseDate;

    /**
     * �R���X�g���N�^
     */
    public DOTQuoteEventImpl()
    {
        super();
        baseDate = new byte[12];
    }
    
    /**
     * @see jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteEvent#getQuoteData()
     */
    public DOTQuoteData getQuoteData()
    {
        DOTQuoteDataImpl quoteData = new DOTQuoteDataImpl();
        quoteData.setParams(this);
        return quoteData;
    }
    
    /**
     * (set�f�[�^)<BR>
     * <BR>
     * �w�肵���o�C�g�z�񂩂玞������ǂݍ��݊e�p�����[�^�ɒl��ݒ肷��B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @param length ����
     * @param sequenceNo �V�[�P���XNO
     * @param updatedTime �X�V����
     * @return �ǂݍ��݂ɐ��������ꍇ��<code>true</code>�A����ȊO�̏ꍇ��<code>false</code>
     */
    boolean setData(byte[] data, int offset, int length, long sequenceNo, long updatedTime)
    {
        
        int index = offset;
        setBaseDate(data, index, 8);
        
        try
        {
            // [1] �������t
            quoteDate = toDate(data, index);
            index += 8;

            // [2] ���A���敪
            realType = toRealType(data, index);
            index += 1;

            // [3] ��ʋ敪
            dataType = toDataType(data, index);
            index += 1;

            // [4] �s��R�[�h
            marketCode = DOTQuoteUtils.toString(data, index, 2);
            index += 2;

            // [5] �����R�[�h
            productCode = toProductCode(data, index);
            index += 9;

            // [6] ����
            monthOfDelivery = DOTQuoteUtils.toString(data, index, 6);
            index += 6;

            // [7] �v�b�g���R�[��
            putAndCall = toPutAndCall(data, index);
            index += 1;

            // [8] �s�g���i
            strikePrice = toDouble(data, index, 12);
            index += 12;

            // [9] �n�l
            openPrice = toDouble(data, index, 12);
            index += 12;

            // [10] �n�l����
            openPriceTime = toTimestamp(data, index);
            index += 4;

            // [11] ���l
            highPrice = toDouble(data, index, 12);
            index += 12;

            // [12] ���l����
            highPriceTime = toTimestamp(data, index);
            index += 4;

            // [13] ���l
            lowPrice = toDouble(data, index, 12);
            index += 12;

            // [14] ���l����
            lowPriceTime = toTimestamp(data, index);
            index += 4;

            // [15] ���ݒl
            currentPrice = toDouble(data, index, 12);
            index += 12;

            // [16] ���ݒl����
            currentPriceTime = toTimestamp(data, index);
            index += 4;

            // [17] ���ݒl�t���O
            currentPriceFlag = toCurrentPriceFlag(data, index);
            index += 1;

            // [18] �O����
            change = toDouble(data, index, 12);
            index += 12;

            // [19] �o����
            volume = toDouble(data, index, 12);
            index += 12;

            // [20] �o��������
            volumeTime = toTimestamp(data, index);
            index += 4;

            // [21] ���C�z�l�^�C�g��
            askPriceTitle = toAskPriceTitle(data, index);
            index += 1;

            // [22] ���C�z�l
            askPrice = toDouble(data, index, 12);
            index += 12;

            // [23] ���C�z�l����
            askPriceTime = toTimestamp(data, index);
            index += 4;

            // [24] ���C�z�l�^�C�g��
            bidPriceTitle = toBidPriceTitle(data, index);
            index += 1;

            // [25] ���C�z�l
            bidPrice = toDouble(data, index, 12);
            index += 12;

            // [26] ���C�z�l����
            bidPriceTime = toTimestamp(data, index);
            index += 4;

            // [27] ��l�i
            basePrice = toDouble(data, index, 12);
            
            // �V�[�P���XNO
            this.sequenceNo = sequenceNo;
            
            // �X�V����
            this.updatedTime = updatedTime;
            
            return true;
            
        } catch (Exception ex)
        {
            String message = "Unexpected exception occured while parsing quote data."
                + "[errorOffset=" + index
                + ", data=" + new String(data, offset, length) + "]";
            log.error(message, ex);
            return false;
        }
        
        
        
    }
    
    /**
     * (set���)<BR>
     * <BR> 
     * �����ݒ肷��B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @param length ����
     */
    private void setBaseDate(byte[] data, int offset, int length)
    {
        System.arraycopy(data, offset, baseDate, 0, length);
    }
    
    /**
     * (to���A���敪)<BR>
     * <BR>
     * ���A���敪���擾����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return ���A���敪
     */
    private RealType toRealType(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, Integer.MIN_VALUE);
        return RealType.toRealType(intValue);
    }
    
    /**
     * (to��ʃR�[�h)<BR>
     * <BR>
     * ��ʃR�[�h���擾����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return ��ʃR�[�h
     */
    private DataType toDataType(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, Integer.MIN_VALUE);
        return DataType.toDataType(intValue);
    }
    
    /**
     * (to�����R�[�h)<BR>
     * <BR>
     * �����R�[�h���擾����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return �����R�[�h
     */
    private String toProductCode(byte[] data, int offset)
    {
        String productCode = null;
        if (DataType.EQUITY.equals(dataType))
        {
            productCode = DOTQuoteUtils.toString(data, offset, 5);
        } else
        {
            productCode = DOTQuoteUtils.toString(data, offset, 9);
        }
        return productCode;
    }
    
    /**
     * (to�v�b�g&�R�[��)<BR>
     * <BR>
     * �v�b�g&�R�[�����擾����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return �v�b�g&�R�[��
     */
    private PutAndCall toPutAndCall(byte[] data, int offset)
    {
        String stringValue = DOTQuoteUtils.toString(data, offset, 1);
        if (PutAndCall.PUT.toStringValue().equals(stringValue))
        {
            return PutAndCall.PUT;
        } else if (PutAndCall.CALL.toStringValue().equals(stringValue))
        {
            return PutAndCall.CALL;
        } else
        {
            return PutAndCall.UNDEFINED;
        }
    }
    
    /**
     * (to���t)<BR>
     * <BR>
     * �uyyyyMMdd�v�`���œ��t���擾����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return ���t
     */
    private Date toDate(byte[] data, int offset)
    {
        return DOTQuoteUtils.toDate(data, offset, 8, "yyyyMMdd");
    }
    
    /**
     * (to�^�C���X�^���v)<BR>
     * <BR>
     * �uyyyyMMddHHmm�v�`���Ń^�C���X�^���v���擾����B<BR>
     * �����T�[�o���瑗�M�����^�C���X�^���v�ɂ́A�uHHmm�v���������܂܂�Ă��Ȃ����߁A
     * �uyyyyMMdd�v�����ɂ͊�����g�p����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return �^�C���X�^���v
     */
    private Timestamp toTimestamp(byte[] data, int offset)
    {
        Timestamp timestamp = null;
        if (!DOTQuoteUtils.isBlank(data, offset, 4))
        {
            System.arraycopy(data, offset, baseDate, 8, 4);
            timestamp = DOTQuoteUtils.toTimestamp(baseDate, 0, 12, "yyyyMMddHHmm");
        }
        return timestamp;
    }
    
    /**
     * (toDouble)<BR>
     * <BR>
     * double�l�ɕϊ�����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @param length ����
     * @return double�l
     */
    private double toDouble(byte[] data, int offset, int length)
    {
        return DOTQuoteUtils.toDouble(data, offset, length, Double.NaN);
    }
    
    /**
     * (to���ݒl�t���O)<BR>
     * <BR>
     * ���ݒl�t���O���擾����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return ���ݒl�t���O
     */
    private CurrentPriceFlag toCurrentPriceFlag(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, CurrentPriceFlag.IntValues.UNDEFINED);
        return CurrentPriceFlag.toCurrentPriceFlag(intValue);
    }
    
    /**
     * (to���C�z�l�^�C�g��)<BR>
     * <BR>
     * ���C�z�^�C�g�����擾����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return ���C�z�t���O
     */
    private AskPriceTitle toAskPriceTitle(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, AskPriceTitle.IntValues.UNDEFINED);
        return AskPriceTitle.toAskPriceTitle(intValue);
    }
    
    /**
     * (to���C�z�l�^�C�g��)<BR>
     * <BR>
     * ���C�z�l�^�C�g�����擾����B<BR>
     * 
     * @param data ��������ێ�����o�C�g�z��
     * @param offset �I�t�Z�b�g
     * @return ���C�z�l�t���O
     */
    private BidPriceTitle toBidPriceTitle(byte[] data, int offset)
    {
        int intValue = DOTQuoteUtils.toInteger(data, offset, 1, BidPriceTitle.IntValues.UNDEFINED);
        return BidPriceTitle.toBidPriceTitle(intValue);
    }
    
}
