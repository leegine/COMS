/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteConstants�N���X(DOTQuoteConstants.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/24 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;


/**
 * (�����A�_�v�^�萔��`�C���^�[�t�F�[�X)<BR>
 * <BR>
 * �����A�_�v�^�Ŏg�p����萔���`�����C���^�[�t�F�[�X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
interface DOTQuoteConstants
{
    
    /**
     * �n�[�g�r�[�g���b�Z�[�W�Ɋ܂܂��z�X�g���̌���
     */
    static final int HOST_NAME_SIZE = 8;
    
    /**
     * �n�[�g�r�[�g���b�Z�[�W�Ɋ܂܂��t���O�̌���
     */
    static final int FLAG_SIZE = 1;
    
    /**
     * �n�[�g�r�[�g���b�Z�[�W�̌���
     */
    static final int HEARTBEAT_SIZE = (HOST_NAME_SIZE * 2) + FLAG_SIZE; // 17
    
    /**
     * �����T�[�o����M����V�[�P���XNO�̌���
     * 
     * ���[���G���W����ŊǗ�����鎞�����̃V�[�P���XNO�̌����Ƃ͕ʂɊǗ������
     */
    static final int SEQUENCE_NO_SIZE = 10;
    
    /**
     * �����T�[�o����M���郌�R�[�h���̌���
     */
    static final int NUM_OF_RECORDS_SIZE = 2;
    
    /**
     * �����T�[�o����M���鎞����񃁃b�Z�[�W.�w�b�_�[���̌���
     */
    static final int HEADER_SIZE = SEQUENCE_NO_SIZE + NUM_OF_RECORDS_SIZE; // 12
    
    /**
     * �����T�[�o����M����1���̎������̌���
     */
    static final int RECORD_SIZE = 180;
    
    /**
     * �����T�[�o���1�x�Ɏ�M����ő�̎�����񃌃R�[�h��
     */
    static final int MAX_RECORDS = 10;
    
    /**
     * �����T�[�o����M���鎞����񃁃b�Z�[�W.�f�[�^���̌���
     */
    static final int MAX_DATA_SIZE = RECORD_SIZE * MAX_RECORDS; // 1800
    
    /**
     * �����Z�b�g
     */
    static final String DEFAULT_ENCODING = "ISO8859_1";
    
    /**
     * �����T�[�o�̃A�h���X
     */
    static final String SERVER_ADDRESS = "localhost";
    
    /**
     * �����T�[�o�̃|�[�g�ԍ�
     */
    static final int SERVER_PORT = 8000;
    
    /**
     * �������b�Z�[�W�L���[�̃T�C�Y
     */
    static final int QUEUE_SIZE = 8;
    
    /**
     * WEB3DefaultQuoteCacheStoreImpl�̏����e��
     */
    static final int CACHE_SIZE = 4999;
    
    /**
     * ��M���������s�X���b�h�̗D��x
     */
    static final int RECEIVER_PRIORITY = 3;
    
    /**
     * �X�V���������s�X���b�h�̗D��x
     */
    static final int UPDATER_PRIORITY = 2;
    
    /**
     * �����T�[�o�ɍĐڑ������݂�Ԋu�i�ʏ�j
     */
    static final long RETRY_INTERVAL_NORAML = 5000;
    
    /**
     * �����T�[�o�ɍĐڑ������݂�Ԋu�i�G�R�m�~�[�j
     */
    static final long RETRY_INTERVAL_ECONOMY = 30000;
    
    /**
     * �����T�[�o�ɍĐڑ������݂�Ԋu��؂�ւ���臒l
     */
    static final int RETRY_THRESHOLD = 20;
    
    /**
     * ���o225�̎�����񂪍X�V����Ă��邩�m�F����Ԋu
     */
    static final long MONITOR_INTERVAL = 60000;
    
    /**
     * ���o225�̎�����񂪍X�V����Ă��Ȃ��ꍇ�ɃG���[�𔭐�������臒l
     */
    static final int MONITOR_WARNING_THRESHOLD = 3;
    
    /**
     * ���o225�̎�����񂪍X�V����Ă��邩�m�F���鎞�ԑ�
     */
    static final String MONITOR_TIME_DEF = "09:00-11:00,12:30-15:00";
    
    /**
     * �n�[�g�r�[�g���b�Z�[�W�������T�[�o�ɑ��M����Ԋu
     */
    static final long HEARTBEAT_INTERVAL = 5000;
    
    /**
     * �����T�[�o�Ƃ̐ڑ����s���\�P�b�g�ɐݒ肷��SO_TIMEOUT�I�v�V�����̒l
     */
    static final int HEARTBEAT_TIMEOUT = 10000;
    
    /**
     * �n�[�g�r�[�g���b�Z�[�W�������T�[�o�ɑ��M����X���b�h�̗D��x
     */
    static final int HEARTBEAT_PRIORITY = 1;
    

}
