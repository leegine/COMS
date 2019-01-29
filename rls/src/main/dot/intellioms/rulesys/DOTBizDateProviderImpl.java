/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : DOTBizDateProviderImpl(DOTBizDateProviderImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/22 �V���@�h�O (FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.rulesys;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.fin.intellioms.util.InitializationException;

import jp.co.dir.dot.intellioms.util.DOTSystemPreferencesAdaptor;


/**
 * DOTBizDateProviderImpl
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTBizDateProviderImpl implements DOTBizDateProvider
{
    private static final String YYYYMMDD_FORMAT = "yyyyMMdd";

    private boolean isShift;

    private String bizDate;

    private final DOTSystemPreferencesAdaptor properties;


    /**
     *
     */
    public DOTBizDateProviderImpl(DOTSystemPreferencesAdaptor l_adaptor)
    {
        properties = l_adaptor;
    }

    /**
     * @see com.fitechlabs.fin.intellioms.rulesys.BizDateProvider#getCurrentDate()
     */
    public Date getCurrentDate()
    {
        Date l_datBizDate = null;

        try
        {
            l_datBizDate = new SimpleDateFormat(YYYYMMDD_FORMAT).parse(bizDate);
        }
        catch (ParseException e)
        {
            l_datBizDate = new Date(getCurrentTimeMillis());
        }

        return l_datBizDate;
    }

    /**
     * @see com.fitechlabs.fin.intellioms.rulesys.BizDateProvider#getCurrentTimeMillis()
     */
    public long getCurrentTimeMillis()
    {
        return System.currentTimeMillis() + getTimeShift();
    }

    /**
     * @see com.fitechlabs.fin.intellioms.rulesys.BizDateProvider#getTimeShift()
     */
    public long getTimeShift()
    {
        long l_shift = 0;

        if(isShift)
        {
            l_shift = properties.getProperty(SHIFT_SYSTEM_TIMESTAMP_MILLISECS, 0L);
        }

        return l_shift;
    }

    /**
     * @see DOTBizDateProvider#getBizDate()
     */
    public String getBizDate()
    {
        return bizDate;
    }
    /**
     * @see Object#toString()
     */
    public String toString()
    {
        return "WEB3 biz date provider [current-date=" + getCurrentDate() + ", time-shift=" + getTimeShift() + "]";
    }

    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public void start() throws InitializationException
    {
        String l_strIsShift = properties.getProperty(SHIFT_SYSTEM_TIMESTAMP, "false");

        isShift = "true".equals(l_strIsShift);

        bizDate = properties.getProperty(SYSTEM_BIZDATE);
    }

    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public void stop()
    {

    }
}
