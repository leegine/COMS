/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RecoveryAsyncCommand�N���X(WEB3RecoveryAsyncCommand.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/22 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

/**
 * ��Q���������N���X
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public abstract class DOTRecoveryCommand
{    
    /**
     * �ғ����
     */
    protected boolean running;
    
    /**
     * ������
     */
    protected String name;

    /**
     * �ғ���Ԃ�߂��܂��B
     * @return running 
     */
    public boolean isRunning() 
    {
        return running;
    }
    
    /**
     * �ғ���Ԃ�ݒ�B
     * @param running �ғ��t���O
     */
    public void setRunning(boolean running) 
    {
        this.running = running;
    }
    
    /**
     * ��������߂��܂��B
     * @return name ������
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * ��������ݒ肷��B
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * ���������s����B
     *
     */
    public abstract void execute();
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer(getClass().getName());
        l_sb.append("[name=" + this.name);
        l_sb.append(",running=" + this.running);
        l_sb.append("]");
        return l_sb.toString();
    }
    
        
}
