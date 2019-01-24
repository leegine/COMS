/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ThreadLocalQuoteEventPool�N���X(DOTThreadLocalQuoteEventPool.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/06 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import com.fitechlabs.fin.intellioms.event.Event;


/**
 * (�����C�x���g�v�[��)<BR>
 * <BR>
 * �X���b�h���[�J���Ȏ����C�x���g���Ǘ�����N���X�B<BR>
 * <BR>
 * �����C�x���g�̔����O�ɁA�������鎞���C�x���g���ݒ肳��A
 * �����C�x���g���I������ƁA�N���A�����B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTThreadLocalQuoteEventPool
{
    
    /**
     * (get�����C�x���g)<BR>
     * <BR>
     * �����C�x���g���擾����B<BR>
     * 
     * @return �����C�x���g
     */
    public Event getQuoteEvent();
    
    /**
     * (set�����C�x���g)<BR>
     * <BR>
     * �����C�x���g��ݒ肷��B<BR>
     * 
     * @param l_event �����C�x���g
     */
    public void setQuoteEvent(Event l_event);
    
    /**
     * (remove�����C�x���g)<BR>
     * <BR>
     * �ݒ肳��Ă��鎞���C�x���g���폜����B<BR>
     */
    public void removeQuoteEvent();

}
