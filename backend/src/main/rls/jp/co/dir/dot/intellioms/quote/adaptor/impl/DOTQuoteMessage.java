/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteMessage�N���X(DOTQuoteMessage.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;



/**
 * (�������b�Z�[�W)<BR>
 * <BR>
 * �����T�[�o����z�M����鎞�������܂ރ��b�Z�[�W�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteMessage
{
    
    /** �V�[�P���XNO */
    long sequenceNo;
    
    /** �X�V���� */
    long updatedTime;
    
    /** ���R�[�h�� */
    int count;
    
    /** ������� */
    byte[] data;
    
    /**
     * �R���X�g���N�^
     */
    DOTQuoteMessage()
    {
        data = new byte[DOTQuoteConstants.MAX_DATA_SIZE];
    }
    
    /**
     * �R���X�g���N�^
     * 
     * @param count ���R�[�h��
     * @param data �������
     * @deprecated
     */
    DOTQuoteMessage(int count, byte[] data)
    {
        this.count = count;
        this.data = data;
    }
    
    /**
     * (copy)<BR>
     * <BR>
     * �w�肵���������b�Z�[�W���炱�̎������b�Z�[�W�ɓ��e���R�s�[����B<BR>
     * 
     * @param src �R�s�[���������b�Z�[�W
     */
    void copy(DOTQuoteMessage src)
    {
        if (src != null)
        {
            this.sequenceNo = src.sequenceNo;
            this.updatedTime = src.updatedTime;
            this.count = src.count;
            System.arraycopy(
                src.data, 
                0, 
                this.data, 
                0, 
                src.count * DOTQuoteConstants.RECORD_SIZE);
        }
    }
    
    /**
     * (parse)<BR>
     * <BR>
     * �p�����[�^�Ŏw�肵�������C�x���gImpl�ɂ��̎������b�Z�[�W��
     * ����������ݒ肷��B<BR>
     * 
     * @param events ��������ݒ肷�鎞���C�x���gImpl
     * @return �����C�x���gImpl�ɐݒ肳�ꂽ�������̌���
     */
    int parse(DOTQuoteEventImpl[] events)
    {
        int validDataCnt = 0;
        int offset = 0;
        for (int i = 0; i < count; i++, offset += DOTQuoteConstants.RECORD_SIZE)
        {
            
            // �����T�[�o����z�M���ꂽ�V�[�P���XNO�Ɏ}�Ԃ�����
            long l_lngSequenceNo = (sequenceNo * 100) + i + 1;
            
            // �������̊e�t�B�[���h�ɒl��ݒ肷��B
            if (events[i].setData(
                data, offset, 
                DOTQuoteConstants.RECORD_SIZE, 
                l_lngSequenceNo, updatedTime))
            {
                validDataCnt++;
            }
        }
        return validDataCnt;
    }

}
