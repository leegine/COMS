/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteMessageQueue�N���X(DOTQuoteMessageQueue.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import com.fitechlabs.fin.intellioms.util.Log;


/**
 * (�������b�Z�[�W�L���[)<BR>
 * <BR>
 * �����T�[�o����z�M���ꂽ�������b�Z�[�W�̃L���[�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteMessageQueue
{
    
    /** ���K�[ */
    private static final Log log = Log.getLogger(DOTQuoteMessageQueue.class);
    
    /** �L���[ */
    private DOTQuoteMessage[] messages;
    
    private int head;
    private int tail;
    private int last;
    
    /** �u���b�N�t���O */
    private boolean block;
    
    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �p�����[�^.size�Ŏw�肷��L���[�̃T�C�Y��2�̏搔�ł��邱�ƁB<BR>
     * 
     * @param size �L���[�̗e��
     * @throws IllegalArgumentException �w�肳�ꂽ�L���[�̃T�C�Y��2�̏搔�łȂ��ꍇ
     */
    DOTQuoteMessageQueue(int size)
    {
        
        if ((size & (size - 1)) != 0)
        {
            throw new IllegalArgumentException("size must be the power of 2.");
        }
        
        messages = new DOTQuoteMessage[size];
        for (int i = 0; i < size; i++)
        {
            messages[i] = new DOTQuoteMessage();
        }
        
        head = 0;
        tail = 0;
        last = size - 1;
        block = true;
        
        log.info(
            "QuoteMessageQueue initialized. "
            + "[size=" + size + "]");
        
    }
    
    /**
     * (push)<BR>
     * <BR>
     * �������b�Z�[�W�L���[�̍Ō�ɂ���v�f��
     * �p�����[�^�Ŏw�肵���������b�Z�[�W�̓��e���R�s�[����B<BR>
     * �������b�Z�[�W�L���[�����łɂ����ς��̂Ƃ��A
     * �������b�Z�[�W�L���[���J�n���ꂢ��ꍇ�́A
     * ���̃��\�b�h�̌Ăяo���̓L���[����v�f���v�b�g�����܂Ńu���b�N�����B
     * �������b�Z�[�W�L���[���J�n����Ă��Ȃ��ꍇ�́A
     * �L���[�ɗv�f�̓v�b�g���ꂸ�A�����ɕԂ����B
     * 
     * @param source �������b�Z�[�W
     */
    synchronized void push(DOTQuoteMessage source)
    {
        
        while (((tail + 1) & last) == head)
        {
            
            if (!block)
            {
                return;
            }
            
            try
            {
                
                log.debug("QuoteMessageQueue is full.");
                wait();
                
            } catch (InterruptedException ie)
            {
            }
        }
        
        messages[tail].copy(source);
        tail = (tail + 1) & last;
        notify();
        
    }
    
    /**
     * (pop)<BR>
     * <BR>
     * �������b�Z�[�W�L���[�̐擪�ɂ���v�f����
     * �p�����[�^�Ŏw�肵���������b�Z�[�W�ɓ��e���R�s�[����B<BR>
     * �������b�Z�[�W�L���[����̂Ƃ��ŁA
     * �L���[�����ɊJ�n����Ă���ꍇ��
     * ���̃��\�b�h�̌Ăяo���́A�L���[�ɗv�f���v�b�g�����܂Ńu���b�N�����B
     * �L���[���J�n����Ă��Ȃ��ꍇ�͑�����<code>false</code>��Ԃ��B 
     * 
     * @param receivingMessage �|�b�v�����������b�Z�[�W�̓��e���R�s�[����鎞�����b�Z�[�W
     * @return
     */
    synchronized boolean pop(DOTQuoteMessage receivingMessage)
    {
        
        while (tail == head)
        {
            
            if (!block)
            {
                return false;
            }
            
            try
            {
                
                log.debug("QuoteMessageQueue is empty.");
                wait();
                
            } catch (InterruptedException ie)
            {
            }
            
        }
        
        if (((tail + 1) & last) == head)
        {
            notify();
        }
        
        receivingMessage.copy(messages[head]);
        head = (head + 1) & last;
        return true;
        
    }
    
    /**
     * (isEmpty)<BR>
     * <BR>
     * @return �������b�Z�[�W�L���[����̏ꍇ��<code>true</code>�A
     *  ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B
     */
    synchronized boolean isEmpty()
    {
        return (tail == head);
    }
    
    /**
     * (start)<BR>
     * <BR>
     * �L���[���J�n����B
     */
    synchronized void start()
    {
        block = true;
        log.debug("QuoteMessageQueue started.");
    }
    
    /**
     * (stop)<BR>
     * <BR>
     * �L���[���~����B
     */
    synchronized void stop()
    {
        block = false;
        notify();
        log.debug("QuoteMessageQueue stoped.");
    }

}
