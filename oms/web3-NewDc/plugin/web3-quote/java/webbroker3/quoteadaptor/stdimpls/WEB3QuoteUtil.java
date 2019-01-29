head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �����T�[�r�X�Ŏg�p���郆�[�e�B���e�B�N���X(WEB3QuoteUtil.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/02/04 �R�c�@@��i(FLJ) �V�K�쐬
 *                  : 2005/05/17 �R�c�@@��i(FLJ) �ꕔ�̃��\�b�h�̃X�R�[�v��ύX
 *                    2009/04/20 ���@@�@@�@@�@@��(FLJ) �����V�X�e���֑ؑΉ�
 */
package webbroker3.quoteadaptor.stdimpls;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import webbroker3.quoteadaptor.AskPriceTitle;
import webbroker3.quoteadaptor.BidPriceTitle;
import webbroker3.quoteadaptor.CurrentPriceFlag;
import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.PutAndCall;
import webbroker3.quoteadaptor.RealType;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * WebBroker3�̎����T�[�r�X�Ŏg�p���郆�[�e�B���e�B���\�b�h���`�B<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3QuoteUtil
{
    
    static final String BLANK = " ";
    
    static final String LINE_SEPARATOR = System.getProperty("line.separator");
    
    private static final WEB3LogUtility _log = WEB3LogUtility.getInstance(WEB3QuoteUtil.class);
    
    private static final ThreadLocal decimalFormatPool = new ThreadLocal() {
        protected Object initialValue()
        {
            return new DecimalFormat(DECIMAL_FORMAT);
        }
    };
    
    private static final String DECIMAL_FORMAT = "#.##";
    
    static void dump(Hashtable cachedQuoteData)
    {
        
        _log.info("### ���������t�@@�C���ɏo�͂��܂��B ###");
        
        Timestamp now = GtlUtils.getSystemTimestamp();
        String timestamp = GtlUtils.getThreadSafeSimpleDateFormat(
                "yyyyMMdd_HHmm").format(now);
        File file = null;
        BufferedWriter writer = null;
        try
        {
            file = new File("./web3_quote_cached-" + timestamp + ".txt");
            file.createNewFile();
            
            writer = new BufferedWriter(new FileWriter(file));
            
            Set entrySet = cachedQuoteData.entrySet();
            for (Iterator it = entrySet.iterator(); it.hasNext();)
            {
                Map.Entry entry = (Map.Entry) it.next();
                AbstractQuoteData quoteData = (AbstractQuoteData) entry.getValue();
                writer.write(format(quoteData));
                writer.write(LINE_SEPARATOR);
            }
            
            _log.info("### ���������t�@@�C���ɏo�͂��܂����B �t�@@�C���� : "
                    + file.getName() + " ###");
            
        } catch (IOException ioe)
        {
            _log.error("###�������̏o�͒��ɃG���[���������܂����B###", ioe);
        } finally
        {
            if (writer != null)
            {
                try
                {
                    writer.flush();
                    writer.close();
                } catch (IOException ioe)
                {
                }
            }
            
        }
        
    }
    
    /**
     * �L���b�V������Ă��鎞�������t�@@�C����͗p�t�H�[�}�b�g�ɕϊ�����B
     * 
     * @@param quoteData �������
     * @@return �t�@@�C���o�͗p�t�H�[�}�b�g������
     */
    static String format(AbstractQuoteData quoteData)
    {
        StringBuffer sb = new StringBuffer();
        append(sb, quoteData.quoteDate, WEB3QuoteRecordFormat.QUOTE_DATE_SIZE);
        append(sb, quoteData.realType, WEB3QuoteRecordFormat.REAL_TYPE_SIZE);
        append(sb, quoteData.dataType, WEB3QuoteRecordFormat.DATA_TYPE_SIZE);
        append(sb, quoteData.marketCode, WEB3QuoteRecordFormat.MARKET_CODE_SIZE);
        append(sb, getProductCode(quoteData), WEB3QuoteRecordFormat.PRODUCT_CODE_SIZE, true);
        append(sb, quoteData.monthOfDelivery, WEB3QuoteRecordFormat.MONTH_OF_DELIVERY_SIZE);
        append(sb, quoteData.putAndCall, WEB3QuoteRecordFormat.PUT_AND_CALL_SIZE);
        append(sb, quoteData.strikePrice, WEB3QuoteRecordFormat.STRIKE_PRICE_SIZE);
        append(sb, quoteData.openPrice, WEB3QuoteRecordFormat.OPEN_PRICE_SIZE);
        append(sb, quoteData.openPriceTime, WEB3QuoteRecordFormat.OPEN_PRICE_TIME_SIZE);
        append(sb, quoteData.highPrice, WEB3QuoteRecordFormat.HIGH_PRICE_SIZE);
        append(sb, quoteData.highPriceTime, WEB3QuoteRecordFormat.HIGH_PRICE_TIME_SIZE);
        append(sb, quoteData.lowPrice, WEB3QuoteRecordFormat.LOW_PRICE_SIZE);
        append(sb, quoteData.lowPriceTime, WEB3QuoteRecordFormat.LOW_PRICE_TIME_SIZE);
        append(sb, quoteData.currentPrice, WEB3QuoteRecordFormat.CURRENT_PRICE_SIZE);
        append(sb, quoteData.currentPriceTime, WEB3QuoteRecordFormat.CURRENT_PRICE_TIME_SIZE);
        append(sb, quoteData.currentPriceFlag, quoteData.currentPrice, WEB3QuoteRecordFormat.CURRENT_PRICE_FLAG_SIZE);
        append(sb, quoteData.change, WEB3QuoteRecordFormat.CHANGE_SIZE);
        append(sb, quoteData.volume, WEB3QuoteRecordFormat.VOLUME_SIZE);
        append(sb, quoteData.volumeTime, WEB3QuoteRecordFormat.VOLUME_TIME_SIZE);
        append(sb, quoteData.askPriceTitle, quoteData.askPrice, WEB3QuoteRecordFormat.ASK_PRICE_TITLE_SIZE);
        append(sb, quoteData.askPrice, WEB3QuoteRecordFormat.ASK_PRICE_SIZE);
        append(sb, quoteData.askPriceTime, WEB3QuoteRecordFormat.ASK_PRICE_TIME_SIZE);
        append(sb, quoteData.bidPriceTitle, quoteData.bidPrice, WEB3QuoteRecordFormat.BID_PRICE_TITLE_SIZE);
        append(sb, quoteData.bidPrice, WEB3QuoteRecordFormat.BID_PRICE_SIZE);
        append(sb, quoteData.bidPriceTime, WEB3QuoteRecordFormat.BID_PRICE_TIME_SIZE);
        append(sb, quoteData.basePrice, WEB3QuoteRecordFormat.BASE_PRICE_SIZE);
        return sb.toString();
    }
    
    /**
     * �w�肳�ꂽ�o�C�g�z���String�ɕ�������B
     * 
     * @@param data �o�C�g�z��
     * @@param offset �ϊ�����o�C�g�z��̐擪��INDEX
     * @@param length �ϊ�����o�C�g�z��̒���
     * @@return ��������������
     */
    static String toString(byte[] data, int offset, int length, boolean useTrimming)
    {
        String s = null;
        try
        {
            s = new String(data, offset, length, WEB3QuoteConstants.ENCODING);
            if (s != null && useTrimming)
            {
                s = s.trim();
            }
            if ("".equals(s))
            {
                s = null;
            }
        } catch (UnsupportedEncodingException uee)
        {
            _log.error(uee.getMessage(), uee);
            
        }
        return s;
    }

    /**
     * �w�肳�ꂽ�o�C�g�z���String�ɕ�������B
     * 
     * @@param data �o�C�g�z��
     * @@param offset �ϊ�����o�C�g�z��̐擪��INDEX
     * @@param length �ϊ�����o�C�g�z��̒���
     * @@return ��������������
     */
    static String toString(byte[] data, int offset, int length)
    {
        return toString(data, offset, length, true);
    }

    /**
     * �w�肳�ꂽ�o�C�g�z���int�ɕ�������B
     * 
     * @@param data �o�C�g�z��
     * @@param offset �ϊ�����o�C�g�z��̐擪��INDEX
     * @@param length �ϊ�����o�C�g�z��̒���
     * @@return ��������int
     */
    static int toInteger(byte[] data, int offset, int length)
    {
        int i = 0;
        String s = toString(data, offset, length);
        if (s != null && !"".equals(s))
        {
            try
            {
                i = Integer.parseInt(s);
            } catch (NumberFormatException nfe)
            {
            }
        }
        return i;
    }

    /**
     * �w�肳�ꂽ�o�C�g�z���double�ɕ�������B
     * 
     * @@param data �o�C�g�z��
     * @@param offset �ϊ�����o�C�g�z��̐擪��INDEX
     * @@param length �ϊ�����o�C�g�z��̒���
     * @@return ��������double
     */
    static double toDouble(byte[] data, int offset, int length)
    {
        double d = Double.NaN;
        String s = toString(data, offset, length);
        if (s != null && !"".equals(s))
        {
            try
            {
                d = Double.parseDouble(s);
            } catch (NumberFormatException nfe)
            {
            }
        }
        return d;
    }


    /**
     * �w�肳�ꂽ�o�C�g�z���Date�ɕ�������B
     * yyyyMMdd�`���̕�����𕄍��������o�C�g�z��𕜍�����B
     * 
     * @@param data �o�C�g�z��
     * @@param offset �ϊ�����o�C�g�z��̐擪��INDEX
     * @@param length �ϊ�����o�C�g�z��̒���
     * @@return ��������Date
     */
    static Date toDate(byte[] data, int offset, int length)
    {
        Date date = null;
        try
        {
            DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            date = df.parse(toString(data, offset, length));
        } catch (ParseException pe)
        {
        }
        return date;
    }
    
    /**
     * (toDate)<BR>
     * <BR>
     * @@return Date
     */
    static Date toDate(String data)
    {
        if (data == null)
        {
            return null;
        }
        
        Date date = null;
        try
        {
            DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            date = df.parse(data);
        } catch (ParseException pe)
        {
        }
        return date;
    }

    /**
     * �w�肳�ꂽ�o�C�g�z���Timestamp�ɕ�������B
     * yyyyMMdd�`���̕�����𕄍��������o�C�g�z��𕜍�����B
     * 
     * @@param data �o�C�g�z��
     * @@param offset �ϊ�����o�C�g�z��̐擪��INDEX
     * @@param length �ϊ�����o�C�g�z��̒���
     * @@return ��������Timestamp
     */
    static Timestamp toTimestamp(byte[] data, int offset, int length)
    {
        Timestamp timestamp = null;
        try
        {
            DateFormat df =
                GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHHmm");
            timestamp = new Timestamp(df.parse(toString(data, offset, length)).getTime());
        } catch (ParseException pe)
        {
        }
        return timestamp;
    }
    
    /**
     * (toTimestamp)<BR>
     * <BR>
     * @@return �^�C���X�^���v
     */
    static Timestamp toTimestamp(String date, String time)
    {
        if (date == null || time == null)
        {
            return null;
        }
        String l_newDateString = date + time;
        Date l_newDate;
        try{
            l_newDate = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHHmm").parse(l_newDateString);
        }catch(ParseException pe){
            throw new RuntimeException(pe);
        }
        return new Timestamp(l_newDate.getTime());
    }

    static void append(StringBuffer sb, String source, int size)
    {
        append(sb, source, size, false);
    }
    
    static void append(StringBuffer sb, String source, int size, boolean isPaddingLeft)
    {
        int length = source != null ? source.length() : 0;
        if (length > size)
        {
            sb.append(source.substring(0, size));
        } else {
            // �E�l�߂̏ꍇ
            if (!isPaddingLeft)
            {
                appendBlank(sb, size - length);
            }
            if (length > 0)
            {
                sb.append(source);
            }
            // ���l�߂̏ꍇ
            if (isPaddingLeft)
            {
                appendBlank(sb, size - length);
            }
        }
    }
    
    private static void appendBlank(StringBuffer sb, int length)
    {
        for (int i = 0; i < length; i++)
        {
            sb.append(BLANK);
        }
    }
    
    private static void append(StringBuffer sb, Date source, int size)
    {

        String s = null;
        if (source != null)
        {
            DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd"); 
            s = df.format(source);
        }
        append(sb, s, size);
    }
    
    private static void append(StringBuffer sb, Timestamp source, int size)
    {
        String s = null;
        if (source != null)
        {
            DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("HHmm"); 
            s = df.format(source);
        }
        append(sb, s, size);
    }

    private static void append(StringBuffer sb, double source, int size)
    {
        String s = null;
        if (!Double.isNaN(source))
        {
            s = getDecimalFormat().format(source);
        }
        append(sb, s, size);
    }
    
    private static void append(StringBuffer sb, int source, int size)
    {
        String s = null;
        s = String.valueOf(source);
        append(sb, s, size);
    }
    
    private static void append(StringBuffer sb, RealType realType, int size)
    {
        if (realType != null 
            && RealType.IntValues.UNDEFINED != realType.intValue())
        {
            append(sb, realType.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, DataType dataType, int size)
    {
        if (dataType != null 
            && DataType.IntValues.UNDEFINED != dataType.intValue())
        {
            append(sb, dataType.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, PutAndCall putAndCall, int size)
    {
        if (putAndCall != null 
            && PutAndCall.IntValues.UNDEFINED != putAndCall.intValue())
        {
            append(sb, putAndCall.stringValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, CurrentPriceFlag currentPriceFlag, double currentPrice, int size)
    {
        if (currentPriceFlag != null && !Double.isNaN(currentPrice))
        {
            append(sb, currentPriceFlag.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, AskPriceTitle askPriceTitle, double askPrice, int size)
    {
        if (askPriceTitle != null 
            && AskPriceTitle.IntValues.UNDEFINED != askPriceTitle.intValue()
            && !Double.isNaN(askPrice))
        {
            append(sb, askPriceTitle.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }

    private static void append(StringBuffer sb, BidPriceTitle bidPriceTitle, double bidPrice, int size)
    {
        if (bidPriceTitle != null 
            && BidPriceTitle.IntValues.UNDEFINED != bidPriceTitle.intValue()
            && !Double.isNaN(bidPrice))
        {
            append(sb, bidPriceTitle.intValue(), size);
        } else {
            append(sb, BLANK, size);
        }
    }
    
    private static String getProductCode(AbstractQuoteData quoteData)
    {
        DataType dataType = quoteData.dataType;
        if (DataType.IntValues.EQUITY == dataType.intValue())
        {
            return quoteData.productCode + WEB3QuoteProductCodes.EQUITY_SUFFIX;
        } else {
            return quoteData.productCode;
        }
        
    }
    
    private static DecimalFormat getDecimalFormat()
    {
        return (DecimalFormat) decimalFormatPool.get();
    }
    
    /**
     * (toLong)<BR>
     * <BR>
     * �w�肵���o�C�g�z�񂩂畜��������long�l��Ԃ��B<BR>
     * 
     * @@param data �����������o�C�g�z��
     * @@param offset �����������擪�o�C�g�̃C���f�b�N�X
     * @@param length ����������o�C�g��
     * @@return ����������int�l
     * @@throws NumberFormatException
     */
    static long toLong(byte[] data, int offset, int length)
    {
        long l = 0;
        String s = toString(data, offset, length);
        if (s != null && !"".equals(s))
        {
            l = Long.parseLong(s);
        }
        return l;
    }

    /**
     * (getSystemDate)<BR>
     * <BR>
     * �w�肵�����t�t�H�[�}�b�g�iyyyyMMdd�j�ŃV�X�e�����t��Ԃ��B<BR>
     * 
     * @@return �V�X�e�����t
     */
    static String getSystemDate()
    {
        String date = null;
        DateFormat df = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        date = df.format(GtlUtils.getSystemTimestamp());
        return date;
    }
}
@
