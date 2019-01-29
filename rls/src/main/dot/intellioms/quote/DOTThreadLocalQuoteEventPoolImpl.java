/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3ThreadLocalQuoteEventPoolImpl�N���X(DOTThreadLocalQuoteEventPoolImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import com.fitechlabs.fin.intellioms.event.Event;

/**
 * (�����C�x���g�v�[��Impl)<BR>
 * <BR>
 * �����C�x���g�v�[���̎����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTThreadLocalQuoteEventPoolImpl implements DOTThreadLocalQuoteEventPool
{

    /**
     * �����C�x���g��ۑ�����X���b�h���[�J���ϐ�
     */
    private ThreadLocal eventPool;

    /**
     * �R���X�g���N�^
     */
    public DOTThreadLocalQuoteEventPoolImpl()
    {
        super();
        this.eventPool = new ThreadLocal();
    }

    /**
     * @see DOTThreadLocalQuoteEventPool#getQuoteEvent()
     */
    public Event getQuoteEvent()
    {
        return (Event) eventPool.get();
    }

    /**
     * @see DOTThreadLocalQuoteEventPool#setQuoteEvent(com.fitechlabs.fin.intellioms.event.Event)
     */
    public void setQuoteEvent(Event l_event)
    {
        eventPool.set(l_event);
    }

    /**
     * @see DOTThreadLocalQuoteEventPool#removeQuoteEvent()
     */
    public void removeQuoteEvent()
    {
        eventPool.set(null);
    }
}
