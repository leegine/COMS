/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteMonitorSettings�N���X(DOTQuoteMonitorSettings.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/13 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

/**
 * (�������j�^�[�ݒ�)<BR>
 * <BR>
 * �������j�^�[�̊e��ݒ����ێ�����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteMonitorSettings
{

    /** �Ď��Ԋu */
    private final long interval;

    /** �x���\��臒l */
    private final int warningThreshold;

    /** �������j�^�[�L������ */
    private final String monitoringTimeDef;
    
    /**
     * �R���X�g���N�^<BR>
     * 
     * �������j�^�[�L�����Ԃɂ́uHH:mm-HH:mm�v�`���̕�������w�肷��B<BR>
     * <BR>
     * �u-�v�O�́uHH:mm�v���L�����ԁy���z�A�u-�v��́uHH:mm�v���L�����ԁy���z��\���B
     * �����̗L�����Ԃ�ݒ肷��ꍇ�́A�J���}��؂�Ŏw�肷��B<BR>
     * �y��z�u09:00-11:00,12:30-15:00�v<BR>
     * 
     * @param interval �Ď��Ԋu
     * @param warningThreshold �x���\��臒l
     * @param monitoringTimeDef �������j�^�[�L������
     */
    DOTQuoteMonitorSettings(long interval, int warningThreshold,
        String monitoringTimeDef)
    {
        this.interval = interval;
        this.warningThreshold = warningThreshold;
        this.monitoringTimeDef = monitoringTimeDef;
    }

    /**
     * (get�Ď��Ԋu)<BR>
     * <BR>
     * �Ď��Ԋu���擾����B<BR>
     * 
     * @return �Ď��Ԋu
     */
    public long getInterval()
    {
        return interval;
    }

    /**
     * (get�x���\��臒l)<BR>
     * <BR>
     * �x���\��臒l���擾����B<BR>
     * 
     * @return �x���\��臒l
     */
    public int getWarningThreshold()
    {
        return warningThreshold;
    }

    /**
     * (get�������j�^�[�L������)<BR>
     * <BR>
     * �������j�^�[�L�����Ԃ��擾����B<BR>
     * 
     * @return �������j�^�[�L������
     */
    public String getMonitoringTimeDef()
    {
        return monitoringTimeDef;
    }
    
}