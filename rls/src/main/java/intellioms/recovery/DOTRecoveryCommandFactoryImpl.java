/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RecoveryAsyncCommandFactoryImpl�N���X(WEB3RecoveryAsyncCommandFactoryImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.kernel.XtierKernel;
import com.fitechlabs.xtier.services.ioc.IocServiceException;

/**
 * ��Q���������R�}���h�t�@�N�g���̎����N���X
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRecoveryCommandFactoryImpl implements
        DOTRecoveryCommandFactory 
{
    /**
     * ���K�[�B
     */
    private static final Log log = Log.getLogger(DOTRecoveryCommandFactoryImpl.class);
    
    private static final boolean DBG = log.isDebug();    
    
    /**
     * �����R�}���h���쐬����B
     * @param name �����R�}���h��
     * @return WEB3RecoveryAsyncCommand
     */
    public DOTRecoveryCommand createCommand(String name) 
    {
        try
        {
            if(DBG)
            {
                log.debug("command name=" + name);
            }
            return (DOTRecoveryCommand)XtierKernel.getInstance().ioc().makeIocObject(name);            
        }
        catch(IocServiceException l_ise)
        {
            log.error(l_ise.getMessage(), l_ise);
        }
        return null;
    }
    
    public String toString()
    {
        return getClass().getName();
    }
    
}
