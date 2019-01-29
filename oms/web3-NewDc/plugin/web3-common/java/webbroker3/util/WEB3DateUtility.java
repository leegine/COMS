head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DateUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���t�^�̃f�[�^�̃��[�e�B���e�B(WEB3DateUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ����� (���u) �V�K�쐬
Revesion History : 2004/09/08 ����� (���u) toDay(Date)��ǉ�
Revesion History : 2004/09/09 ����� (���u) compareToMonth(Date, Date)��ǉ�
Revesion History : 2004/09/09 ����� (���u) compareToYear(Date, Date)��ǉ�
Revesion History : 2004/09/13 ����� (���u) toDay(Date)�̕ԋp���C��
Revesion History : 2004/09/23 ����� (���u) compare(Date, Date)��ǉ�
Revesion History : 2007/02/07 ꎉ�   (���u) addMonth(Date, int)��ǉ�
Revesion History : 2008/11/05 SRA����       addMonth(Date, int)��synchronized��ǉ�
*/

package webbroker3.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * ���t�^�̃f�[�^�̏������s���֐��������[�e�B���e�B�N���X�B<BR>
 * <BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3DateUtility
{

    private static Calendar gCalendar = new GregorianCalendar();

    /**
     * ���������t�^�f�[�^�Ɍ^�ϊ����ĕԋp���܂��B
     *
     * @@param l_str ������
     * @@param l_strPattern �t�H�[�}�b�g
     * @@return ���͂��ꂽstr�͎w�肵���t�H�[�}�b�g�̓��t�Ȃ�A<BR>
     * �ϊ���̓��t�^�f�[�^��ԋp����B�����łȂ���false��Ԃ��B
     */
    public static Date getDate(String l_str, String l_strPattern)
    {

        if (l_str == null || l_strPattern == null)
        {
            return null;
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat(l_strPattern);
        l_dateFormat.setLenient(false);

        try
        {
            return l_dateFormat.parse(l_str);
        }
        catch (ParseException ex)
        {
            return null;
        }
    }

    /**
     * ���t�^�f�[�^���w�肵���t�H�[�}�b�g�ŕ�����Ɍ^�ϊ����܂��B
     *
     * @@param l_dat ���t�^�f�[�^
     * @@param l_strPattern �t�H�[�}�b�g
     * @@return �ϊ���̕������Ԃ��B
     */
    public static String formatDate(Date l_dat, String l_strPattern)
    {

        if (l_dat == null || l_strPattern == null)
        {
            return "";
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat(l_strPattern);

        return l_dateFormat.format(l_dat);
    }
    
    /**
     * ���͂������t�Ɏw�肵���������v���X���A�ԋp���܂��B
     *
     * @@param l_dat        ���t�@@     
     * @@param l_intMonth   ����
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    public static synchronized Date addMonth(Date l_dat, int l_intMonth)
    {
        if (l_dat == null)
        {
            return null;
        }
        
        gCalendar.setTime(l_dat);
        gCalendar.add(Calendar.MONTH, l_intMonth);
        
        return gCalendar.getTime();
    }
    
    /**
     * ���͂������t�Ɏw�肵���������v���X���A�ԋp���܂��B
     *
     * @@param l_dat    ���t
     * @@param l_lngDay ����
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    public static Date addDay(Date l_dat, long l_lngDay)
    {
        if (l_dat == null)
        {
            return null;
        }

        long l_lngTmpTime = l_dat.getTime();
        long l_lngNewTime = l_lngTmpTime + l_lngDay * 24 * 60 * 60 * 1000;

        return new Date(l_lngNewTime);
    }

    /**
     * ���͂������t�Ɏw�肵�����Ԑ����v���X���A�ԋp���܂��B
     *
     * @@param l_dat     ���t
     * @@param l_lngHour ���Ԑ�
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    public static Date addHour(Date l_dat, long l_lngHour)
    {
        if (l_dat == null)
        {
            return null;
        }

        long l_lngTmpTime = l_dat.getTime();
        long l_lngNewTime = l_lngTmpTime + l_lngHour * 60 * 60 * 1000;

        return new Date(l_lngNewTime);
    }

    /**
     * ���͂������t�Ɏw�肵���������v���X���A�ԋp���܂��B
     *
     * @@param l_dat       ���t
     * @@param l_lngMinute ����
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    public static Date addMinute(Date l_dat, long l_lngMinute)
    {
        if (l_dat == null)
        {
            return null;
        }

        long l_lngTmpTime = l_dat.getTime();
        long l_lngNewTime = l_lngTmpTime + l_lngMinute * 60 * 1000;

        return new Date(l_lngNewTime);
    }

    /**
     * ���͂������t�Ɏw�肵���b�����v���X���A�ԋp���܂��B
     *
     * @@param l_dat       ���t
     * @@param l_lngSecond �b��
     * @@return �v�Z��̌��ʂ�ԋp����B
     */
    public static Date addSecond(Date l_dat, long l_lngSecond)
    {
        if (l_dat == null)
        {
            return null;
        }

        long l_lngTmpTime = l_dat.getTime();
        long l_lngNewTime = l_lngTmpTime + l_lngSecond * 1000;

        return new Date(l_lngNewTime);
    }

    /**
     * ��̓��t���r���܂�(���x�͓��܂łƂ���)�B
     *
     * @@param l_dat1 ���t1
     * @@param l_dat2 ���t2
     * @@return l_dat1��l_dat2�̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
     *         l_dat1��l_dat2�̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
     *         l_dat1��l_dat2�����l�̏ꍇ�A�O��ԋp����B
     */
    public static int compareToDay(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * ��̓��t���r���܂�(���x�͎��Ԃ܂łƂ���)�B
     *
     * @@param l_dat1 ���t1
     * @@param l_dat2 ���t2
     * @@return l_dat1��l_dat2�̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
     *         l_dat1��l_dat2�̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
     *         l_dat1��l_dat2�����l�̏ꍇ�A�O��ԋp����B
     */
    public static int compareToHour(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHH");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * ��̓��t���r���܂�(���x�͕��܂łƂ���)�B
     *
     * @@param l_dat1 ���t1
     * @@param l_dat2 ���t2
     * @@return l_dat1��l_dat2�̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
     *         l_dat1��l_dat2�̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
     *         l_dat1��l_dat2�����l�̏ꍇ�A�O��ԋp����B
     */
    public static int compareToMinute(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHHmm");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * ��̓��t���r���܂�(���x�͕b�܂łƂ���)�B
     *
     * @@param l_dat1 ���t1
     * @@param l_dat2 ���t2
     * @@return l_dat1��l_dat2�̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
     *         l_dat1��l_dat2�̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
     *         l_dat1��l_dat2�����l�̏ꍇ�A�O��ԋp����B
     */
    public static int compareToSecond(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMddHHmmss");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * ���t���擾����B
     *
     * @@param l_dat    ����     
     * @@return ���t��ԋp����B
     */
    public static synchronized Date toDay(Date l_dat)
    {
        if (l_dat == null)
        {
            return null;
        }

        gCalendar.setTime(l_dat);
        gCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gCalendar.set(Calendar.MINUTE, 0);
        gCalendar.set(Calendar.SECOND, 0);
        gCalendar.set(Calendar.MILLISECOND, 0);

        //l_dat.setTime(gCalendar.getTimeInMillis());

        return gCalendar.getTime();
    }

    /**
     * ��̓��t���r���܂�(���x�͌��܂łƂ���)�B
     *
     * @@param l_dat1 ���t1
     * @@param l_dat2 ���t2
     * @@return l_dat1��l_dat2�̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
     *         l_dat1��l_dat2�̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
     *         l_dat1��l_dat2�����l�̏ꍇ�A�O��ԋp����B
     */
    public static int compareToMonth(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyyMM");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * ��̓��t���r���܂�(���x�͔N�܂łƂ���)�B
     *
     * @@param l_dat1 ���t1
     * @@param l_dat2 ���t2
     * @@return l_dat1��l_dat2�̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
     *         l_dat1��l_dat2�̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
     *         l_dat1��l_dat2�����l�̏ꍇ�A�O��ԋp����B
     */
    public static int compareToYear(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat("yyyy");
        String l_strDate1 = l_dateFormat.format(l_dat1);
        String l_strDate2 = l_dateFormat.format(l_dat2);

        return l_strDate1.compareTo(l_strDate2);
    }

    /**
     * ��̓��t���r���܂��B
     *
     * @@param l_dat1 ���t1
     * @@param l_dat2 ���t2
     * @@return l_dat1��l_dat2�̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
     *         l_dat1��l_dat2�̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
     *         l_dat1��l_dat2�����l�̏ꍇ�A�O��ԋp����B
     */
    public static int compare(Date l_dat1, Date l_dat2)
    {
        if (l_dat1 == null)
        {
            l_dat1 = new Date(0);
        }

        if (l_dat2 == null)
        {
            l_dat2 = new Date(0);
        }

        return l_dat1.compareTo(l_dat2);
    }
    
    /**
    * ��̓��t���r���܂�(���x�͎��ԕ����݂̂Ƃ���)�B
    *
    * @@param l_dat1 ���t1
    * @@param l_dat2 ���t2
    * @@return l_dat1�̎��ԕ�����l_dat2�̎��ԕ����̌�̏ꍇ�A�O���傫��������ԋp����B<BR>
    *         l_dat1�̎��ԕ�����l_dat2�̎��ԕ����̑O�̏ꍇ�A�O��菬����������ԋp����B<BR>
    *         l_dat1�̎��ԕ�����l_dat2�̎��ԕ��������l�̏ꍇ�A�O��ԋp����B
    */
   public static int compareTime(Date l_dat1, Date l_dat2)
   {
       if (l_dat1 == null)
       {
           l_dat1 = new Date(0);
       }
 
       if (l_dat2 == null)
       {
           l_dat2 = new Date(0);
       }
       String l_strDat1 = WEB3DateUtility.formatDate(l_dat1, "HHmmss");
       String l_strDat2 = WEB3DateUtility.formatDate(l_dat2, "HHmmss");
       return l_strDat1.compareTo(l_strDat2);
   }

}
@
