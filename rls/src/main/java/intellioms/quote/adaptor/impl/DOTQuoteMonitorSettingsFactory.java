/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteMonitorSettingsFactory�N���X(DOTQuoteMonitorSettingsFactory.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/27 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * (�������j�^�[�ݒ�t�@�N�g��)<BR>
 * 
 * �������j�^�[�ݒ�̃t�@�N�g���N���X�B<BR>
 * <BR>
 * SYSTEM_PREFERENCES�e�[�u������ǂݍ��񂾒l�ŁA
 * �������j�^�[�ݒ�̃C���X�^���X�𐶐�����B<BR>
 * 
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteMonitorSettingsFactory
{

    /**
     * �����A�_�v�^�v���p�e�B
     */
    private DOTQuoteProperties properties;

    /**
     * �R���X�g���N�^
     * 
     * @param l_properties �����A�_�v�^�v���p�e�B
     */
    public DOTQuoteMonitorSettingsFactory(DOTQuoteProperties l_properties)
    {
        this.properties = l_properties;
    }

    /**
     * (create)<BR>
     * <BR>
     * WEB3QuoteMonitorSettings�N���X�̃C���X�^���X�𐶐�����B<BR>
     * 
     * @return �������ꂽWEB3QuoteMonitorSettings�N���X�B
     */
    public DOTQuoteMonitorSettings create()
    {
        long l_lngInterval = properties.getProperty(
            DOTQuoteProperties.MONITOR_INTERVAL_PREF_NAME,
            DOTQuoteConstants.MONITOR_INTERVAL);
        int l_intWarningThreshold = properties.getProperty(
            DOTQuoteProperties.MONITOR_WARNING_THRESHOLD_PREF_NAME,
            DOTQuoteConstants.MONITOR_WARNING_THRESHOLD);
        String l_strMonitoringTimeDef = getMonitoringTimeDef();
        return new DOTQuoteMonitorSettings(
            l_lngInterval,
            l_intWarningThreshold,
            l_strMonitoringTimeDef);
    }

    /**
     * (get�������j�^�[�L������)<BR>
     * <BR>
     * �������j�^�[�L�����Ԃ��擾����B<BR>
     * 
     * @return �����Ď����ԑ�
     */
    private String getMonitoringTimeDef()
    {

        Map l_startTimeDefs = properties
            .getProperties(DOTQuoteProperties.MONITOR_START_TIME_PREF_NAME);

        int l_intCount = 0;
        StringBuffer l_sb = new StringBuffer();

        if (l_startTimeDefs != null && !l_startTimeDefs.isEmpty())
        {

            l_startTimeDefs = new TreeMap(l_startTimeDefs);
            for (Iterator l_it = l_startTimeDefs.entrySet().iterator(); l_it
                .hasNext();)
            {

                String l_strStartTime = null;
                String l_strEndTime = null;

                Map.Entry l_entry = (Map.Entry) l_it.next();
                String l_strStartTimeKey = (String) l_entry.getKey();
                l_strStartTime = (String) l_entry.getValue();
                l_strEndTime = getEndTime(l_strStartTimeKey);

                if (l_strStartTime != null && l_strEndTime != null)
                {
                    if (l_intCount > 0)
                    {
                        l_sb.append(",");
                    }
                    l_sb.append(l_strStartTime);
                    l_sb.append("-");
                    l_sb.append(l_strEndTime);
                    l_intCount++;
                }

            }
        }
        return (l_intCount > 0) ? l_sb.toString()
            : DOTQuoteConstants.MONITOR_TIME_DEF;
    }

    /**
     * (get�������j�^�[�L�����ԁy���z)<BR>
     * <BR>
     * �����Ď����ԑсy���z�ɑΉ����鎞���Ď����ԑсy���z���擾����B<BR>
     * 
     * @param l_startTimeKey�@�����Ď����ԑсy���z�̃v���p�e�B�� 
     * @return �����Ď����ԑсy���z
     */
    private String getEndTime(String l_startTimeKey)
    {
        String l_strEndTimeKey = DOTQuoteProperties.MONITOR_END_TIME_PREF_NAME;
        if (!DOTQuoteProperties.MONITOR_START_TIME_PREF_NAME
            .equals(l_startTimeKey))
        {
            l_strEndTimeKey = DOTQuoteProperties.MONITOR_END_TIME_PREF_NAME;
            int l_intIndex = l_startTimeKey.lastIndexOf(".");
            if (l_intIndex >= DOTQuoteProperties.MONITOR_START_TIME_PREF_NAME
                .length())
            {
                l_strEndTimeKey += l_startTimeKey.substring(l_intIndex);
            }
        }
        return properties.getProperty(l_strEndTimeKey);
    }

}