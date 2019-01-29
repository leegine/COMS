/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteProperties�N���X(DOTQuoteProperties.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/27 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.util.Map;

import jp.co.dir.dot.intellioms.util.DOTSystemPreferencesAdaptor;


/**
 * (�����A�_�v�^�v���p�e�B)<BR>
 * <BR>
 * �����A�_�v�^�̊e�ݒ����ێ�����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteProperties
{

    /** �v���p�e�B���F�����T�[�o��IP�A�h���X */
    public static final String SERVER_ADDRESS_PREF_NAME = "webbroker3.quoteadaptor.server.address";

    /** �v���p�e�B���F�����T�[�o�̃|�[�g�ԍ� */
    public static final String SERVER_PORT_PREF_NAME = "webbroker3.quoteadaptor.server.port";

    /** �v���p�e�B���F��M�X���b�h�̗D��x */
    public static final String RECEIVER_PRIOTIRY_PREF_NAME = "webbroker3.quoteadaptor.receiver.priority";

    /** �v���p�e�B���F�X�V�X���b�h�̗D��x */
    public static final String UPDATER_PRIORITY_PREF_NAME = "webbroker3.quoteadaptor.updater.priority";

    /** �v���p�e�B���F�ڑ������g���C����Ԋu�y�G�R�m�~�[�z */
    public static final String RETRY_INTERVAL_ECONOMY_PREF_NAME = "webbroker3.quoteadaptor.retry.interval.economy";

    /** �v���p�e�B���F�ڑ������g���C����Ԋu�y�ʏ�z */
    public static final String RETRY_INTERVAL_NORMAL_PREF_NAME = "webbroker3.quoteadaptor.retry.interval.normal";

    /** �v���p�e�B���F�ڑ������g���C����Ԋu��ύX����臒l */
    public static final String RETRY_THRESHOLD_PREF_NAME = "webbroker3.quoteadaptor.retry.threshold";

    /** �v���p�e�B���F�������b�Z�[�W�L���[�̗e�� */
    public static final String QUEUE_SIZE_PREF_NAME = "webbroker3.quoteadaptor.queue.size";

    /** �v���p�e�B���F�f�t�H���g�������Ǘ��e�[�u���̏����e�� */
    public static final String CACHE_SIZE_PREF_NAME = "webbroker3.quoteadaptor.cache.size";
    
    /** �v���p�e�B���FNK225�̎�����񂪍X�V����Ă��邩�Ď�����Ԋu */
    public static final String MONITOR_INTERVAL_PREF_NAME = "webbroker3.quoteadaptor.monitor.interval";

    /** �v���p�e�B���FNK225�̎�����񂪍X�V����Ă��Ȃ��ꍇ�Ɍx����\������臒l */
    public static final String MONITOR_WARNING_THRESHOLD_PREF_NAME = "webbroker3.quoteadaptor.monitor.waring.threshold";

    /** �v���p�e�B���FNK225�̎��������Ď����鎞�ԁy���z */
    public static final String MONITOR_START_TIME_PREF_NAME = "webbroker3.quoteadaptor.monitor.start.time";

    /** �v���p�e�B���FNK225�̎��������Ď����鎞�ԁy���z */
    public static final String MONITOR_END_TIME_PREF_NAME = "webbroker3.quoteadaptor.monitor.end.time";
    
    /** �v���p�e�B���F�����T�[�o�Ƀn�[�g�r�[�g���b�Z�[�W�𑗐M����Ԋu */
    public static final String HEARTBEAT_INTERVAL_PREF_NAME = "webbroker3.quoteadaptor.heartbeat.interval";
    
    /** �v���p�e�B���F�����T�[�o�Ƃ̐ڑ��\�P�b�g�ɐݒ肷��SO_TIMEOUT�I�v�V�����̒l */
    public static final String HEARTBEAT_TIMEOUT_PREF_NAME = "webbroker3.quoteadaptor.heartbeat.timeout";
    
    /** �v���p�e�B���F�n�[�g�r�[�g�X���b�h�̗D��x */
    public static final String HEARTBEAT_PRIORITY_PREF_NAME = "'webbroker3.quoteadaptor.heartbeat.priority";

    /** SYSTEM_PREFERENCES�e�[�u���A�_�v�^ */
    private DOTSystemPreferencesAdaptor properties;

    /**
     * �R���X�g���N�^<BR>
     * 
     * @param l_systemPreferencesAdaptor SYSTEM_PREFERENCES�e�[�u���A�_�v�^
     */
    public DOTQuoteProperties(
        DOTSystemPreferencesAdaptor l_systemPreferencesAdaptor)
    {
        this.properties = l_systemPreferencesAdaptor;
    }

    /**
     * �w�肵���ړ����Ŏn�܂�ݒ�̃L�[�Ɛݒ�l��ێ�����}�b�v���擾����B<BR>
     * 
     * @param l_strPrefix �ړ���
     * @return �w�肵���ړ����Ŏn�܂�ݒ�̃L�[�Ɛݒ�l��ێ�����}�b�v
     */
    public Map getProperties(String l_strPrefix)
    {
        return properties.getProperties(l_strPrefix);
    }

    /**
     * �w�肵���L�[�̐ݒ�l���擾����B<BR>
     * <BR>
     * �w�肵���L�[�̐ݒ�l�����݂��Ȃ��ꍇ<code>null</code>��Ԃ��B
     * 
     * @param l_strKey �L�[
     * @return �ݒ�l
     */
    public String getProperty(String l_strKey)
    {
        return properties.getProperty(l_strKey);
    }

    /**
     * �w�肵���L�[�̐ݒ�l���擾����B<BR>
     * <BR>
     * �w�肵���L�[�̐ݒ�l�����݂��Ȃ��ꍇ�f�t�H���g�l��Ԃ��B
     * 
     * @param l_strKey �L�[
     * @param l_intDefaultValue �f�t�H���g�l
     * @return
     */
    public int getProperty(String l_strKey, int l_intDefaultValue)
    {
        return properties.getProperty(l_strKey, l_intDefaultValue);
    }

    /**
     * �w�肵���L�[�̐ݒ�l���擾����B<BR>
     * <BR>
     * �w�肵���L�[�̐ݒ�l�����݂��Ȃ��ꍇ�f�t�H���g�l��Ԃ��B
     * 
     * @param l_strKey �L�[
     * @param l_lngDefaultValue �f�t�H���g�l
     * @return
     */
    public long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        return properties.getProperty(l_strKey, l_lngDefaultValue);
    }

    /**
     * �w�肵���L�[�̐ݒ�l���擾����B<BR>
     * <BR>
     * �w�肵���L�[�̐ݒ�l�����݂��Ȃ��ꍇ�f�t�H���g�l��Ԃ��B
     * 
     * @param l_strKey �L�[
     * @param l_strDefaultValue �f�t�H���g�l
     * @return
     */
    public String getProperty(String l_strKey, String l_strDefaultValue)
    {
        return properties.getProperty(l_strKey, l_strDefaultValue);
    }
}