head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.39.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteConstants.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WebBroker3�p�����T�[�r�X�̂��߂̒萔��`�N���X(WEB3QuoteConstants.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
                 : 2005/04/28 �R�c�@@��i(FLJ) �����Ď��̂��߂̒萔��`��ǉ�
                 : 2005/05/17 �R�c�@@��i(FLJ) �n�[�g�r�[�g�֘A�̒萔��`��ǉ�
                 : 2005/05/24 �R�c�@@��i(FLJ) ���[�J���|�[�g�A�n�[�g�r�[�g�X���b�h�̗D��x�̃f�t�H���g�l��ύX
                 : 2009/01/28 ���@@�@@��(FLJ) CSK�������`�F�b�N�̋����Ή�
                 : 2009/04/23 �V���@@�h�O(FLJ) �����V�X�e��QUICK�ւ̈ڍs
*/
package webbroker3.quoteadaptor.stdimpls;

/**
 * WebBroker3�p�����T�[�r�X�̂��߂̒萔��`�B
 *
 * @@author Yoshihara Tadafumi
 * @@version 1.0
 */
public interface WEB3QuoteConstants
{
    
    /**
     * �n�[�g�r�[�g���b�Z�[�W�Ɋ܂܂��z�X�g���̌���
     */
    int HOST_NAME_SIZE = 8;
    
    /**
     * �n�[�g�r�[�g���b�Z�[�W�̒���
     */
    int HEARTBEAT_SIZE = 17;
    
    /**
     * �����T�[�o����M����FROM�z�X�g���̌���
     * 
     */
    int FROM_MACHINE_NAME_SIZE = 8;
    
    /**
     * �����T�[�o����M����TO�z�X�g���̌���
     * 
     */
    int TO_MACHINE_NAME_SIZE = 8;

    /**
     * �������f�[�^���j�b�g�̃V�[�P���X�ԍ��̃T�C�Y�i�o�C�g���j�B
     *
     */
    int SEQUENCE_NO_SIZE = 10;

    /**
     * �������f�[�^���j�b�g�̃��R�[�h���̃T�C�Y�i�o�C�g���j�B
     *
     */
    int NUM_OF_RECORDS_SIZE = 2;

    /**
     *�@@�������f�[�^���j�b�g�̃w�b�_���̃T�C�Y�i�o�C�g���j�B
     *
     */
    int HEADER_SIZE = SEQUENCE_NO_SIZE + NUM_OF_RECORDS_SIZE; //12

    /**
     * �������f�[�^���j�b�g�Ɋ܂܂�郌�R�[�h�̃T�C�Y�i�o�C�g���j�B
     *
     */
    int RECORD_SIZE = 180;

    /**
     * �������f�[�^���j�b�g�Ɋ܂܂�郌�R�[�h���̏���B
     *
     */
    int MAX_RECORDS = 10;

    /**
     * �������f�[�^���j�b�g�̃f�[�^���̃T�C�Y�̏���i�o�C�g���j�B
     *
     */
    int MAX_DATA_SIZE = MAX_RECORDS * RECORD_SIZE; //1790

    /**
     * Encoding for char->int/double conversion.
     */
    String ENCODING = "ISO8859_1";

    /**
     * �f�t�H���g�̃T�[�r�XID�B
     */
    String WEB3_QUOTE_SERVICE = "TCP";

    /**
     * �f�t�H���g�̃T�[�r�XID�B
     */
    String WEB3_QUOTE_RMM_PRIMARY_SERVICE = "RMM.PRIMARY";

    /**
     * �f�t�H���g�̃T�[�r�XID�B
     */
    String WEB3_QUOTE_RMM_SECONDARY_SERVICE = "RMM.SECONDARY";

    /**
     * �S�T�[�r�X��\���T�[�r�XID�B
     */
    String ALL_SERVICES = "ALL";

    /**
     * �f�t�H���g�̃f�[�^�擾�p�ڑ���A�h���X�B
     *
     */
    String SERVER_ADDRESS = "localhost";

    /**
     * �f�t�H���g�̃f�[�^�擾�p�ڑ���|�[�g�ԍ��B
     *
     */
    int SERVER_PORT = 8000;

    /**
     * �f�t�H���g�̃f�[�^�擾�p�ڑ����A�h���X�B
     *
     */
    String LOCAL_ADDRESS = "localhost";

    /**
     * �f�t�H���g�̃f�[�^�擾�p�ڑ����|�[�g�ԍ��B
     *
     */
    int LOCAL_PORT = 0;
    
    /**
     * ��M�����������f�[�^���j�b�g���琶������鎞�����
     * �ʒm�C�x���g���ꎞ�I�ɕۑ����邽�߂̃L���[�̃T�C�Y�B
     *
     */
    int QUEUE_SIZE = 16; // must be the power of two

    /**
     * �ŏI�I�Ɏ�������ۑ�����e�[�u���̏����T�C�Y�B
     *
     */
    int CACHE_SIZE = 5009; // prime number is better

    /**
     * �����T�[�o�ւ̐ڑ��Ɏ��s�����ꍇ�̍Ď��s�Ԋu�i�~���b�j�B
     *
     */
    long RETRY_INTERVAL_NORMAL = 3000;

    /**
     * �����T�[�o�ւ̐ڑ���RETRY_THRESHHOLD�Ŏw�肳�ꂽ�񐔈ȏ�
     * ���s�����ꍇ�̍Ď��s�Ԋu�i�~���b�j�B
     *
     */
    long RETRY_INTERVAL_ECONOMY = 60000;

    /**
     * �����Ŏw�肳�ꂽ�񐔈ȏ�A�����T�[�o�ւ̐ڑ��Ɏ��s����
     * �ƍĎ��s�Ԋu��RETRY_INTERVAL_NORMAL����RETRY_INTERVAL_ECONOMY
     * �ɕς��B
     *
     */
    int RETRY_THRESHHOLD = 20;

    /**
     * �������ʒm�C�x���g�������T�[�o�����M����X���b�h�́A
     * �f�t�H���g�̃X���b�h�v���C�I���e�B�[�ɑ΂���D��x�B
     * java.lang.Thread.NORM_PRIORITY + RECEIVER_PRIORITY��
     * ���Y�X���b�h�̃X���b�h�v���C�I���e�B�[�ƂȂ�B
     */
    int RECEIVER_PRIORITY = 3;

    /**
     * �������ʒm�C�x���g���n���h���[�ɓn���X���b�h�́A
      * �f�t�H���g�̃X���b�h�v���C�I���e�B�[�ɑ΂���D��x�B
     * java.lang.Thread.NORM_PRIORITY + RECEIVER_PRIORITY��
     * ���Y�X���b�h�̃X���b�h�v���C�I���e�B�[�ƂȂ�B
     */
    int UPDATER_PRIORITY = 2;
    
    /**
     * ����̖����i�w���j���X�V����Ă��邩�Ď�����Ԋu
     */
    long MONITOR_INTERVAL = 60000;
    
    /**
     * ����̖����i�w���j���X�V����Ă��Ȃ��ꍇ�Ɍx����\������臒l
     */
    int MONITOR_WARNING_THRESHOLD = 3;
    
    /**
     * �����X�V����Ă��Ȃ��ꍇ�Ƀ`�F�b�N�G���[�Ƃ�������̌�臒l
     */
    int MONITOR_COUNT_THRESHOLD = 1;
    
    /**
     * �n�[�g�r�[�g�����s����Ԋu
     */
    int HEARTBEAT_INTERVAL = 5000;
    
    /**
     * �n�[�g�r�[�g�����s����\�P�b�g�̃^�C���A�E�g����
     */
    int HEARTBEAT_TIMEOUT = 10000;
    
    /**
     * �n�[�g�r�[�g�����s����X���b�h�̃v���C�I���e�B�[
     */
    int HEARTBEAT_PRIORITY = 1;
    
    /**
     * ����ID�A�s��ID�Ɋ܂܂���ЃR�[�h�̌���
     */
    int INSTITUTION_CODE_SIZE = 2;
    
    /**
     * ����ID�Ɋ܂܂���ЁA���i�R�[�h�̌���
     */
    int PRODUCT_HEAD_SIZE = 4;
    
    /**
     * �����R�[�h�̌���
     */
    int PRODUCT_CODE_LENGTH = 5;
    
    /**
     * �Ď��ΏۊO�̐敨��������
     */
    String EXCEPT_MONTH_OF_DELIVERY = "999912";
    
    /**
     * �Ď����O�o�͂̏���l
     */
    int MISSING_QUOTE_MAX_NUMBER = 50;
    
    /**
     * �����T�[�o����M����RMM������񃁃b�Z�[�W.�w�b�_�[���̌���
     */
    int RMM_HEADER_SIZE = FROM_MACHINE_NAME_SIZE + TO_MACHINE_NAME_SIZE + SEQUENCE_NO_SIZE + NUM_OF_RECORDS_SIZE; // 28
    
    /**
     * RMM�������v�����g���C�Ԋu(�~���b)
     */
    long RMM_INIT_RETRY_INTERVAL = 5000;
    
    /**
     * RMM�f�[�^�\�[�X��ؑւ��鏉�����v�����s臒l
     */
    int RMM_DS_CHANGE_INIT_THRESHOLD = 2;
    
    /**
     * �����v���g�R��(TCP)
     */
    String QUOTE_PROTOCOL_TCP = "TCP";
    
    /**
     * �����v���g�R��(RMM)
     */
    String QUOTE_PROTOCOL_RMM = "RMM";
    
    /**
     * ��؂蕶��
     */
    String DELIMITER = ",";

    /**
     * RMM�������ʒm�C�x���g���n���h���[�ɓn���X���b�h�́A
      * �f�t�H���g�̃X���b�h�v���C�I���e�B�[�ɑ΂���D��x�B
     * java.lang.Thread.NORM_PRIORITY + RMM_UPDATER_PRIORITY��
     * ���Y�X���b�h�̃X���b�h�v���C�I���e�B�[�ƂȂ�B
     */
    int RMM_UPDATER_PRIORITY = 2;
    
    /**
     * ��M�����������f�[�^���j�b�g���琶�������RMM�������
     * �ʒm�C�x���g���ꎞ�I�ɕۑ����邽�߂̃L���[�̃T�C�Y�B
     *
     */
    int RMM_QUEUE_SIZE = 8192; // must be the power of two
    
    /**
     * RMM��M�g���[�X�ݒ�
     */
    boolean RMM_RCVD_TRACE = false;
}
@
