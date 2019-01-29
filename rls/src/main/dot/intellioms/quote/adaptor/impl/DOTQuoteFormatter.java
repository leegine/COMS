/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFormatter�N���X(DOTQuoteFormatter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/30 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.sql.Timestamp;
import java.util.Date;

import jp.co.dir.dot.intellioms.enums.AskPriceTitle;
import jp.co.dir.dot.intellioms.enums.BidPriceTitle;
import jp.co.dir.dot.intellioms.enums.CurrentPriceFlag;
import jp.co.dir.dot.intellioms.enums.DataType;
import jp.co.dir.dot.intellioms.enums.RealType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;




/**
 * (�������t�H�[�}�b�^)<BR>
 * <BR>
 * ���������Œ蒷�̕�����Ƀt�H�[�}�b�g����B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteFormatter
{
    
    /** ���񂹃t���O */
    private static final int ALIGNMENT_LEFT = 0;
    
    /** �E�񂹃t���O */
    private static final int ALIGNMENT_RIGHT = 1;
    
    /** ���������R�[�h�̐ڔ��� */
    private static final String EQUITY_PRODUCT_CODE_SUFFIX = "0000";

    /**
     * �R���X�g���N�^
     */
    private DOTQuoteFormatter()
    {
    }
    
    /**
     * (format(�������))<BR>
     * <BR>
     * ���������Œ蒷�̕�����Ƀt�H�[�}�b�g����B<BR>
     * 
     * @param l_quoteData �������
     * @return ���������t�H�[�}�b�g�����Œ�s�̕������Ԃ��B
     */
    static String format(DOTQuoteData l_quoteData)
    {
        StringBuffer l_sb = new StringBuffer();
        format(l_sb, l_quoteData);
        return l_sb.toString();
    }
    
    /**
     * (format(StringBuffer, �������))<BR>
     * <BR>
     * ���������t�H�[�}�b�g�����Œ蒷�̕�������w�肵��������o�b�t�@�ɒǉ�����B<BR>
     * 
     * @param sb ���������t�H�[�}�b�g�����������ǉ����镶����o�b�t�@
     * @param quoteData �������
     */
    static void format(StringBuffer sb, DOTQuoteData quoteData)
    {
        appendLong(sb, quoteData.getSequenceNo(), DOTQuoteFormats.AP_SEQUENCE_NO_SIZE);
        appendTimestamp(sb, quoteData.getUpdatedTime(), DOTQuoteFormats.UPDATED_TIME_SIZE);
        appendDate(sb, quoteData.getQuoteDate(), DOTQuoteFormats.QUOTE_DATE_SIZE);
        appendRealType(sb, quoteData.getRealType());
        appendDataType(sb, quoteData.getDataType());
        appendString(sb, quoteData.getMarketCode(), DOTQuoteFormats.MARKET_CODE_SIZE);
        appendProductCode(sb, quoteData);
        appendMonthOfDelivery(sb, quoteData);
        appendPutAndCall(sb, quoteData);
        appendStrikePrice(sb, quoteData);
        appendDouble(sb, quoteData.getOpenPrice(), DOTQuoteFormats.OPEN_PRICE_SIZE);
        appendTime(sb, quoteData.getOpenPriceTime(), DOTQuoteFormats.OPEN_PRICE_TIME_SIZE);
        appendDouble(sb, quoteData.getHighPrice(), DOTQuoteFormats.HIGH_PRICE_SIZE);
        appendTime(sb, quoteData.getHighPriceTime(), DOTQuoteFormats.HIGH_PRICE_TIME_SIZE);
        appendDouble(sb, quoteData.getLowPrice(), DOTQuoteFormats.LOW_PRICE_SIZE);
        appendTime(sb, quoteData.getLowPriceTime(), DOTQuoteFormats.LOW_PRICE_TIME_SIZE);
        appendDouble(sb, quoteData.getCurrentPrice(), DOTQuoteFormats.CURRENT_PRICE_SIZE);
        appendTime(sb, quoteData.getCurrentPriceTime(), DOTQuoteFormats.CURRENT_PRICE_TIME_SIZE);
        appendCurrentPriceFlag(sb, quoteData.getCurrentPriceFlag());
        appendDouble(sb, quoteData.getChange(), DOTQuoteFormats.CHANGE_SIZE);
        appendDouble(sb, quoteData.getVolume(), DOTQuoteFormats.VOLUME_SIZE);
        appendTime(sb, quoteData.getVolumeTime(), DOTQuoteFormats.VOLUME_TIME_SIZE);
        appendAskPriceTitle(sb, quoteData.getAskPriceTitle());
        appendDouble(sb, quoteData.getAskPrice(), DOTQuoteFormats.ASK_PRICE_SIZE);
        appendTime(sb, quoteData.getAskPriceTime(), DOTQuoteFormats.ASK_PRICE_TIME_SIZE);
        appendBidPriceTitle(sb, quoteData.getBidPriceTitle());
        appendDouble(sb, quoteData.getBidPrice(), DOTQuoteFormats.BID_PRICE_SIZE);
        appendTime(sb, quoteData.getBidPriceTime(), DOTQuoteFormats.BID_PRICE_TIME_SIZE);
        appendDouble(sb, quoteData.getBasePrice(), DOTQuoteFormats.BASE_PRICE_SIZE);
    }
    
    /**
     * (appendString(StringBuffer, String, int, int))<BR>
     * <BR>
     * �w�肵��������o�b�t�@�ɕ������ǉ�����B<BR>
     * �p�����[�^.data�Ŏw�肵��������̒������A�p�����[�^.length�Ŏw�肵��
     * ������̒������Z���ꍇ�́A����Ȃ������u�����N�i<code>" "</code>�j
     * ��ݒ肷��B�u�����N��ݒ肷��ʒu�́A�p�����[�^.alignment�Ŏw�肷��B
     * �p�����[�^.data�Ŏw�肵��������̒������A�p�����[�^.length�Ŏw�肵��
     * ������̒�����蒷���ꍇ�́A���̕���؂�̂Ă�B<BR>
     *  
     * @param sb �w�肵���������ǉ�����StringBuffer
     * @param data �ǉ����镶����
     * @param length �ǉ����镶����̒���
     * @param alignment 0�F���񂹁A1�F�E��
     */
    private static void appendString(StringBuffer sb, String data, int length, int alignment)
    {
        int l = data != null ? data.length() : 0;
        if (l > length)
        {
            sb.append(data.substring(0, length));
        } else
        {
            if (ALIGNMENT_RIGHT == alignment)
            {
                appendBlank(sb, length - l);
            }
            if (l > 0)
            {
                sb.append(data);
            }
            if (ALIGNMENT_LEFT == alignment)
            {
                appendBlank(sb, length - l);
            }
        }
    }
    
    /**
     * (appendString(StringBuffer, String, int))<BR>
     * <BR>
     * �w�肵��������o�b�t�@�ɕ������ǉ�����B<BR>
     * 
     * @param sb �w�肵���������ǉ�����StringBuffer
     * @param data �ǉ����镶����
     * @param length �ǉ����镶����̒���
     */
    private static void appendString(StringBuffer sb, String data, int length)
    {
        appendString(sb, data, length, ALIGNMENT_RIGHT);
    }
    
    /**
     * (appendBlank)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ƀu�����N�i<code>" "</code>�j��ǉ�����B<BR>
     * 
     * @param sb �u�����N��ǉ�����StringBuffer
     * @param length �ݒ肷��u�����N�̒���
     */
    private static void appendBlank(StringBuffer sb, int length)
    {
        for (int i = 0; i < length; i++)
        {
            sb.append(" ");
        }
    }
    
    /**
     * (appendDate)<BR>
     * <BR>
     * �w�肵��StringBuffer�ɁuyyyyMMdd�v�t�H�[�}�b�g�œ��t��ǉ�����B<BR>
     * 
     * @param sb �w�肵�����t��ǉ�����StringBuffer
     * @param date �ǉ�������t
     */
    private static void appendDate(StringBuffer sb, Date date, int length)
    {
        String s = null;
        if (date != null)
        {
            s = DOTQuoteUtils.getDateFormat(DOTQuoteFormats.DATE_FORMAT).format(date);
        }
        appendString(sb, s, length);
    }
    
    /**
     * (appendTimestamp)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ɂuyyyy-MM-dd HH:mm:ss.SSS�v�t�H�[�}�b�g�Ń^�C���X�^���v��ǉ�����B<BR>
     * 
     * @param sb �w�肵���^�C���X�^���v��ǉ����镶����o�b�t�@
     * @param timestamp �ǉ�����^�C���X�^���v
     */
    private static void appendTimestamp(StringBuffer sb, Timestamp timestamp, int length)
    {
        String s = null;
        if (timestamp != null)
        {
            s = DOTQuoteUtils.getDateFormat(DOTQuoteFormats.TIMESTAMP_FORMAT).format(timestamp);
        }
        appendString(sb, s, length);
    }
    
    /**
     * (appendTime)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�ɁuHHmm�v�t�H�[�}�b�g�Ń^�C���X�^���v��ǉ�����B<BR>
     * 
     * @param sb �w�肵�����Ԃ�ǉ����镶����o�b�t�@
     * @param timestamp �ǉ�����^�C���X�^���v
     */
    private static void appendTime(StringBuffer sb, Timestamp timestamp, int length)
    {
        String s = null;
        if (timestamp != null)
        {
            s = DOTQuoteUtils.getDateFormat(DOTQuoteFormats.TIME_FORMAT).format(timestamp);
        }
        appendString(sb, s, length);
    }
    
    /**
     * (appendDouble)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�ɉE�񂹂�double�l��ǉ�����B<BR>
     * 
     * @param sb �w�肵��double�l��ǉ����镶����o�b�t�@
     * @param number �ǉ�����double�l
     * @param length �ǉ�����double�l�̒���
     */
    private static void appendDouble(StringBuffer sb, double number, int length)
    {
        String s = null;
        if (!Double.isNaN(number))
        {
            s = DOTQuoteUtils.getDecimalFormat(DOTQuoteFormats.DECIMAL_FORMAT).format(number);
        }
        appendString(sb, s, length);
    }
    
    /**
     * (appendLong)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�ɉE�񂹂�long�l��ǉ�����B<BR>
     * 
     * @param sb �w�肵��long�l��ǉ����镶����o�b�t�@
     * @param number �ǉ�����long�l
     * @param length �ǉ�����long�l�̒���
     */
    private static void appendLong(StringBuffer sb, long number, int length)
    {
        String s = String.valueOf(number);
        appendString(sb, s, length);
    }
    
    /**
     * (appendInteger)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�ɉE�񂹂�int�l��ǉ�����B<BR>
     * 
     * @param sb �w�肵��int�l��ǉ����镶����o�b�t�@
     * @param number �ǉ�����int�l
     * @param length �ǉ�����int�l�̒���
     */
    private static void appendInteger(StringBuffer sb, int number, int length)
    {
        String s = String.valueOf(number);
        appendString(sb, s, length);
    }
    
    /**
     * (append���A���敪)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ƀ��A���敪��int�l��ǉ�����B<BR>
     * 
     * @param sb �w�肵�����A���敪��ǉ����镶����o�b�t�@
     * @param realType �ǉ����郊�A���敪
     */
    private static void appendRealType(StringBuffer sb, RealType realType)
    {
        if (realType == null)
        {
            throw new IllegalStateException("realType must be not null.");
        }
        appendInteger(sb, realType.toValue(), DOTQuoteFormats.REAL_TYPE_SIZE);
    }
    
    /**
     * (append��ʃR�[�h)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ɏ�ʃR�[�h��int�l��ǉ�����B<BR>
     * 
     * @param sb �w�肵����ʃR�[�h��ǉ����镶����o�b�t�@
     * @param realType �ǉ������ʃR�[�h
     */
    private static void appendDataType(StringBuffer sb, DataType dataType)
    {
        if (dataType == null)
        {
            throw new IllegalStateException("dataType must be not null.");
        }
        appendInteger(sb, dataType.toValue(), DOTQuoteFormats.REAL_TYPE_SIZE);
    }
    
    /**
     * (append�����R�[�h)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ɏw�肵���������̖����R�[�h��ǉ�����B<BR>
     * �w�肵���������̎�ʃR�[�h�������̏ꍇ�͖����R�[�h��
     * <code>"0000"</code>��ǉ�����B
     * 
     * @param sb �����R�[�h��ǉ����镶����o�b�t�@
     * @param quoteData �������
     */
    private static void appendProductCode(StringBuffer sb, DOTQuoteData quoteData)
    {
        if (DataType.EQUITY.equals(quoteData.getDataType()))
        {
            appendString(sb, quoteData.getProductCode(), (DOTQuoteFormats.PRODUCT_CODE_SIZE - EQUITY_PRODUCT_CODE_SUFFIX.length()));
            appendString(sb, EQUITY_PRODUCT_CODE_SUFFIX, EQUITY_PRODUCT_CODE_SUFFIX.length());
        } else
        {
            appendString(sb, quoteData.getProductCode(), DOTQuoteFormats.PRODUCT_CODE_SIZE, ALIGNMENT_LEFT);
        }
    }
    
    /**
     * (append����)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ɏw�肵���������̌�����ǉ�����B<BR>
     * �w�肵���������̎�ʃR�[�h�������w���敨�������w���I�v�V�����ȊO�̏ꍇ��
     * �u�����N�i<code>" "</code>�j��ݒ肷��B<BR>
     * 
     * @param sb ������ǉ����镶����o�b�t�@
     * @param quoteData �������
     */
    private static void appendMonthOfDelivery(StringBuffer sb, DOTQuoteData quoteData)
    {
        switch (quoteData.getDataType().toValue())
        {
            case DataType.IntValues.INDEX_FUTURE :
            case DataType.IntValues.INDEX_OPTION :
                appendString(sb, quoteData.getMonthOfDelivery(), DOTQuoteFormats.MONTH_OF_DELIVERY_SIZE);
                return;
            default :
                appendBlank(sb, DOTQuoteFormats.MONTH_OF_DELIVERY_SIZE);
                return;
        }
    }
    
    /**
     * (append�v�b�g&�R�[��)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ɏw�肵���������̃v�b�g&�R�[����ǉ�����B<BR>
     * �w�肵���������̎�ʃR�[�h�������w���I�v�V�����ȊO�̏ꍇ��
     * �u�����N�i<code>" "</code>�j��ݒ肷��B<BR>
     * 
     * @param sb �v�b�g&�R�[����ǉ����镶����o�b�t�@
     * @param quoteData �������
     */
    private static void appendPutAndCall(StringBuffer sb, DOTQuoteData quoteData)
    {
        switch (quoteData.getDataType().toValue())
        {
            case DataType.IntValues.INDEX_OPTION :
                appendString(sb, quoteData.getPutAndCall().toStringValue(), DOTQuoteFormats.PUT_AND_CALL_SIZE);
                return;
            default :
                appendBlank(sb, DOTQuoteFormats.PUT_AND_CALL_SIZE);
                return;
        }
    }
    
    /**
     * (append�s�g���i)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ɏw�肵���������̍s�g���i��ǉ�����B<BR>
     * �w�肵���������̎�ʃR�[�h�������w���I�v�V�����ȊO�̏ꍇ��
     * �u�����N�i<code>" "</code>�j��ݒ肷��B<BR>
     * 
     * @param sb �s�g���i��ǉ����镶����o�b�t�@
     * @param quoteData �������
     */
    private static void appendStrikePrice(StringBuffer sb, DOTQuoteData quoteData)
    {
        switch (quoteData.getDataType().toValue())
        {
            case DataType.IntValues.INDEX_OPTION :
                appendDouble(sb, quoteData.getStrikePrice(), DOTQuoteFormats.STRIKE_PRICE_SIZE);
                return;
            default :
                appendBlank(sb, DOTQuoteFormats.STRIKE_PRICE_SIZE);
                return;
        }
    }
    
    /**
     * (append���ݒl�t���O)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�Ɍ��ݒl�t���O��int�l��ǉ�����B<BR>
     * �w�肵�����ݒl�t���O������`�̏ꍇ�̓u�����N�i<code>" "</code>�j��ݒ肷��B<BR>
     * 
     * @param sb �w�肵�����ݒl�t���O��ǉ����镶����o�b�t�@
     * @param currentPriceFlag ���ݒl�t���O
     */
    private static void appendCurrentPriceFlag(StringBuffer sb, CurrentPriceFlag currentPriceFlag)
    {
        int intValue = currentPriceFlag != null ? 
            currentPriceFlag.toValue() : 
            CurrentPriceFlag.IntValues.UNDEFINED;
        if (CurrentPriceFlag.IntValues.UNDEFINED == intValue)
        {
            appendBlank(sb, DOTQuoteFormats.CURRENT_PRICE_FLAG_SIZE);
        } else
        {
            appendInteger(sb, intValue, DOTQuoteFormats.CURRENT_PRICE_FLAG_SIZE);
        }
    }
    
    /**
     * (append���C�z�l�^�C�g��)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�ɔ��C�z�l�^�C�g����int�l��ǉ�����B<BR>
     * �w�肵�����C�z�l�^�C�g��������`�̏ꍇ�̓u�����N�i<code>" "</code>�j��ݒ肷��B<BR>
     * 
     * @param sb �w�肵�����C�z�l�^�C�g����ǉ����镶����o�b�t�@
     * @param currentPriceFlag ���C�z�l�^�C�g��
     */
    private static void appendAskPriceTitle(StringBuffer sb, AskPriceTitle askPriceTitle)
    {
        int intValue = askPriceTitle != null ? 
            askPriceTitle.toValue() : 
            AskPriceTitle.IntValues.UNDEFINED;
        if (AskPriceTitle.IntValues.UNDEFINED == intValue)
        {
            appendBlank(sb, DOTQuoteFormats.ASK_PRICE_TITLE_SIZE);
        } else
        {
            appendInteger(sb, intValue, DOTQuoteFormats.ASK_PRICE_TITLE_SIZE);
        }
    }
    
    /**
     * (append���C�z�l�^�C�g��)<BR>
     * <BR>
     * �w�肵��������o�b�t�@�ɔ��C�z�l�^�C�g����int�l��ǉ�����B<BR>
     * �w�肵�����C�z�l�^�C�g��������`�̏ꍇ�̓u�����N�i<code>" "</code>�j��ݒ肷��B<BR>
     * 
     * @param sb �w�肵�����C�z�l�^�C�g����ǉ����镶����o�b�t�@
     * @param currentPriceFlag ���C�z�l�^�C�g��
     */
    private static void appendBidPriceTitle(StringBuffer sb, BidPriceTitle bidPriceTitle)
    {
        int intValue = bidPriceTitle != null ? 
            bidPriceTitle.toValue() : 
            BidPriceTitle.IntValues.UNDEFINED;
        if (BidPriceTitle.IntValues.UNDEFINED == intValue)
        {
            appendBlank(sb, DOTQuoteFormats.BID_PRICE_TITLE_SIZE);
        } else
        {
            appendInteger(sb, intValue, DOTQuoteFormats.BID_PRICE_TITLE_SIZE);
        }
    }
    
}
