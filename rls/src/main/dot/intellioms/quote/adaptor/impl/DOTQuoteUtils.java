/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : QuoteUtils�N���X(DOTQuoteUtils.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/26 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (�����A�_�v�^���[�e�B���e�B)<BR>
 * <BR>
 * �����A�_�v�^�Ŏg�p���郆�[�e�B���e�B���\�b�h���`�����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteUtils
{
    
    /** �u�����N */
    private static byte BLANK = 0;
    
    static
    {
        try
        {
            BLANK = " ".getBytes(DOTQuoteConstants.DEFAULT_ENCODING)[0];
        } catch (UnsupportedEncodingException uee)
        {
            throw new RuntimeException(uee);
        }
    }
    
    /** �X���b�h���[�J�����t�t�H�[�}�b�g�v�[�� */
    private static final ThreadLocal DATE_FORMAT_POOL = new ThreadLocal() {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };
    
    /** �X���b�h���[�J�����l�t�H�[�}�b�g�v�[�� */
    private static final ThreadLocal DECIMAL_FORMAT_POOL = new ThreadLocal() {
        protected Object initialValue()
        {
            return new HashMap();
        }
    };
    
    /**
     * �R���X�g���N�^<BR>
     */
    private DOTQuoteUtils()
    {
    }
    
    /**
     * (toString(byte, int, int, boolean))<BR>
     * <BR>
     * �w�肳�ꂽ�o�C�g�z���String�ɕ�������B<BR>
     * 
     * @param data �o�C�g�z��
     * @param offset �ϊ�����o�C�g�z��̐擪��INDEX
     * @param length �ϊ�����o�C�g�z��̒���
     * @param useTrimming �擪�ƍŌ�̋󔒂͏ȗ�����ꍇ<code>true</code>�A
     *                    �ȗ����Ȃ��ꍇ�́A<code>false</code>
     * @return ��������������
     */
    static String toString(byte[] data, int offset, int length, boolean useTrimming)
    {
        String s = null;
        try
        {
            s = new String(data, offset, length, DOTQuoteConstants.DEFAULT_ENCODING);
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
            throw new RuntimeException(uee);
        }
        return s;
    }

    /**
     * (toString(byte, int, int))<BR>
     * <BR>
     * �w�肳�ꂽ�o�C�g�z���String�ɕ�������B<BR>
     * 
     * @param data �o�C�g�z��
     * @param offset �ϊ�����o�C�g�z��̐擪��INDEX
     * @param length �ϊ�����o�C�g�z��̒���
     * @return ��������������
     */
    static String toString(byte[] data, int offset, int length)
    {
        return toString(data, offset, length, true);
    }

    /** �󕶎��� */
    private static final String EMPTY = "";
    
    /**
     * (is�󕶎���)<BR>
     * <BR>
     * �w�肵��������<code>null</code>�A�܂��͋󕶎���̏ꍇ��<code>true</code>�A
     * ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B<BR>
     */
    static boolean isEmpty(String s)
    {
        return (s == null || EMPTY.equals(s));
    }
    
    /**
     * (toDouble)<BR>
     * <BR>
     * �w�肵���o�C�g�z�񂩂畜��������double�l��Ԃ��B<BR>
     * 
     * @param data �����������o�C�g�z��
     * @param offset �����������擪�o�C�g�̃C���f�b�N�X
     * @param length ����������o�C�g��
     * @param defaultValue �w�肵���o�C�g�z��͈̔͂��S�ău�����N�̏ꍇ�ɕԂ����double�l
     * @return ����������double�l
     * @throws NumberFormatException
     */
    static double toDouble(byte[] data, int offset, int length, double defaultValue)
    {
        double d = defaultValue;
        String s = toString(data, offset, length);
        if (s != null && !isEmpty(s))
        {
            d = Double.parseDouble(s);
        }
        return d;
    }
    
    /**
     * (toInteger)<BR>
     * <BR>
     * �w�肵���o�C�g�z�񂩂畜��������int�l��Ԃ��B<BR>
     * 
     * @param data �����������o�C�g�z��
     * @param offset �����������擪�o�C�g�̃C���f�b�N�X
     * @param length ����������o�C�g��
     * @param �w�肵���o�C�g�z��͈̔͂��S�ău�����N�̏ꍇ�ɕԂ����int�l
     * @return ����������int�l
     * @throws NumberFormatException
     */
    static int toInteger(byte[] data, int offset, int length, int defaultValue)
    {
        int i = defaultValue;
        String s = toString(data, offset, length);
        if (s != null && !isEmpty(s))
        {
            i = Integer.parseInt(s);
        }
        return i;
    }
    
    /**
     * (toLong)<BR>
     * <BR>
     * �w�肵���o�C�g�z�񂩂畜��������long�l��Ԃ��B<BR>
     * 
     * @param data �����������o�C�g�z��
     * @param offset �����������擪�o�C�g�̃C���f�b�N�X
     * @param length ����������o�C�g��
     * @param defaultValue �w�肵���o�C�g�z��͈̔͂��S�ău�����N�̏ꍇ�ɕԂ����long�l
     * @return ����������int�l
     * @throws NumberFormatException
     */
    static long toLong(byte[] data, int offset, int length, long defaultValue)
    {
        long l = defaultValue;
        String s = toString(data, offset, length);
        if (s != null && !isEmpty(s))
        {
            l = Long.parseLong(s);
        }
        return l;
    }
    
    /**
     * (toDate)<BR>
     * <BR>
     * �w�肵���o�C�g�z�񂩂畜�����������t��Ԃ��B<BR>
     * 
     * @param data �����������o�C�g�z��
     * @param offset �����������擪�o�C�g�̃C���f�b�N�X
     * @param length ����������o�C�g��
     * @param pattern ���t�p�^�[��
     * @return �������������t
     */
    static Date toDate(byte[] data, int offset, int length, String pattern)
    {
        Date d = null;
        String s = toString(data, offset, length);
        if (!isEmpty(s))
        {
            try
            {
                d = getDateFormat(pattern).parse(s);
            } catch (ParseException pe)
            {
                throw new RuntimeException(pe);
            }
        }
        return d;
    }
    
    /**
     * (toTimestamp)<BR>
     * <BR>
     * �w�肵���o�C�g�z�񂩂畡���������^�C���X�^���v��Ԃ��B<BR>
     * 
     * @param data �����������o�C�g�z��
     * @param offset �����������擪�o�C�g�̃C���f�b�N�X
     * @param length ����������o�C�g��
     * @param pattern ���t�p�^�[��
     * @return �����������^�C���X�^���v
     */
    static Timestamp toTimestamp(byte[] data, int offset, int length, String pattern)
    {
        Timestamp t = null;
        String s = toString(data, offset, length);
        if (!isEmpty(s))
        {
            try
            {
                long time = getDateFormat(pattern).parse(s).getTime();
                t = new Timestamp(time);
            } catch (ParseException pe)
            {
                throw new RuntimeException(pe);
            }
        }
        return t;
    }
    
    /**
     * (isBlank)<BR>
     * <BR>
     * �w�肵���o�C�g�z�񂪃u�����N�����肷��B<BR>
     * 
     * @param data �o�C�g�z��
     * @param offset �C���f�b�N�X
     * @param length �o�C�g��
     * @return �w�肵���o�C�g�z��̈ꕔ���S�ău�����N�̏ꍇ��<code>true</code>�A
     *  ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B
     */
    static boolean isBlank(byte[] data, int offset, int length)
    {
        for (int i = 0; i < length; i++)
        {
            if (BLANK != data[offset + i])
            {
                return false;
            }
        }
        return true;
    }

    /**
     * (get���t�t�H�[�}�b�g)<BR>
     * <BR>
     * �p�����[�^�Ŏw�肵���p�^�[���̓��t�t�H�[�}�b�g���擾����B<BR>
     * 
     * @param pattern �p�^�[��
     * @return ���t�t�H�[�}�b�g
     */
    static DateFormat getDateFormat(String pattern)
    {
        Map dateFormatMap = (Map) DATE_FORMAT_POOL.get();
        DateFormat dateFormat = (DateFormat) dateFormatMap.get(pattern);
        if (dateFormat == null)
        {
            dateFormat = new SimpleDateFormat(pattern);
            dateFormatMap.put(pattern, dateFormat);
        }
        return dateFormat;
    }
    
    /**
     * (get���l�t�H�[�}�b�g)<BR>
     * <BR>
     * �p�����[�^�Ŏw�肵���p�^�[���̐��l�t�H�[�}�b�g���擾����B<BR>
     * 
     * @param pattern �p�^�[��
     * @return ���l�t�H�[�}�b�g
     */
    static DecimalFormat getDecimalFormat(String pattern)
    {
        Map decimalFormatMap = (Map) DECIMAL_FORMAT_POOL.get();
        DecimalFormat decimalFormat = (DecimalFormat) decimalFormatMap.get(pattern);
        if (decimalFormat == null)
        {
            decimalFormat = new DecimalFormat(pattern);
            decimalFormatMap.put(pattern, decimalFormat);
        }
        return decimalFormat;
    }
    
}
