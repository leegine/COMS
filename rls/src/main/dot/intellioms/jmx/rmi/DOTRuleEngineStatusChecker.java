/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RuleEngineStatusChecker�N���X(DOTRuleEngineStatusChecker.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/06 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.jmx.rmi;

import com.fitechlabs.xtier.jmxrmi.JmxRmiClient;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;

/**
 * WEB3���[���G���W����JMX-RMI���g�p���Đڑ���
 * ���[���G���W���̉ғ���Ԃ��`�F�b�N����JMX-RMI�N���C�A���g�N���X�B<BR>
 * <BR>
 * ���[���G���W����JMX-MRI�|�[�g�ԍ����w�肷��ꍇ�͈ȉ��̂悤�Ƀp�����[�^���w�肷��B<BR>
 * <BR>
 * java DOTRuleEngineStatusChecker -rmi <JMX-RMI�|�[�g�ԍ�><BR>
 * <BR>
 * �I�����̃X�e�[�^�X�R�[�h��
 * �u0�v�̏ꍇ�͋N����
 * �u1�v�̏ꍇ�͕s���ȏ��<BR>
 * �u2�v�̏ꍇ�͒�~��<BR>
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRuleEngineStatusChecker 
{
    
    /** ���s����MBean�I�u�W�F�N�g�̃I�u�W�F�N�g�� */
    public static final String OBJECT_NAME = "IntelliOMS:name=RuleEngineConnectorManager";
    
    /** ���s����MBean�I�u�W�F�N�g�̃��\�b�h�� */
    public static final String METHOD_NAME = "isStarted";
    
    
    
    /**
     * ���C�����\�b�h
     */
    public static void main(String[] args)
    {
        int l_intRmiPort = JmxRmiClient.DMN_RMIREG_PORT;
        
        if (args != null && args.length > 1)
        {
            for (int i = 0; i < args.length; i++)
            {
                
                if (args[i].equalsIgnoreCase("-rmi") && (i < args.length - 1))
                {
                    try
                    {
                        l_intRmiPort = Integer.parseInt(args[++i]);
                    } catch (NumberFormatException l_nfe)
                    {
                        showUsage();
                    }
                } else
                {
                    showUsage();
                }
                
            }
        }
        
        try
        {
            JmxRmiClient l_rmiClient = null;
            l_rmiClient = new JmxRmiClient(l_intRmiPort);            
            Boolean l_ret = (Boolean)l_rmiClient.invoke(OBJECT_NAME, METHOD_NAME, null, null);
            
            if(Boolean.TRUE.equals(l_ret))
            {
                System.out.println("Rule Engine status : STARTED.");
                System.exit(0);                
            }
            else
            {
                System.out.println("Rule Engine status : STOPPED.");
                System.exit(2);                                
            }
            
            return;
            
        }
        catch(JmxRmiClientException l_jrce)
        {
            System.out.println("Rule Engine status : STOPPED.");
            System.exit(2);
            return;
        }
        catch (Exception l_e)
        {
            System.err.println("Rule Engine status : Unknown exception occured : " + l_e.getMessage());
            System.exit(1);
            return;
        }
                        
    }
    
    /**
     * ���̃N���X�̎g�p�@��W���o�͂ɏo�͂���B
     */
    private static void showUsage()
    {
        System.out.println("Usage: -rmi <rule-engine-rmi-port-number>");
        System.exit(-1);
    }
    

}
