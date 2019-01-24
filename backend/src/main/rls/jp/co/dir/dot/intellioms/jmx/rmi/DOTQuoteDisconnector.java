/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteDisconnector�N���X(DOTQuoteDisconnector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/22 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx.rmi;

import com.fitechlabs.xtier.jmxrmi.JmxRmiClient;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;


/**
 * WEB3���[���G���W����JMX-RMI���g�p���Đڑ���
 * �����T�[�o�Ƃ̐ڑ����I������悤�Ɏw������JMX-RMI�N���C�A���g�N���X�B<BR>
 * <BR>
 * ���[���G���W����JMX-MRI�|�[�g�ԍ����w�肷��ꍇ�͈ȉ��̂悤�Ƀp�����[�^���w�肷��B<BR>
 * <BR>
 * java DOTQuoteConnector -mri <JMX-RMI�|�[�g�ԍ�><BR>
 * <BR>
 * �I�����̃X�e�[�^�X�R�[�h���u0�v�̏ꍇ�͐���I���A
 * �u-1�v�̏ꍇ�ُ͈�I���B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteDisconnector
{
    
    /** ���s����MBean�I�u�W�F�N�g�̃I�u�W�F�N�g�� */
    public static final String OBJECT_NAME = "WEB3IntelliOMS:name=QuoteFeederAdaptor";
    
    /** ���s����MBean�I�u�W�F�N�g�̃��\�b�h�� */
    public static final String METHOD_NAME = "disconnect";

    /**
     * �R���X�g���N�^
     */
    public DOTQuoteDisconnector()
    {
        super();
    }

    /**
     * ���C�����\�b�h
     */
    public static void main(String[] args)
    {
        
        int l_intRmiPort = JmxRmiClient.XTIER_RMIREG_PORT;
        
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
        
        JmxRmiClient l_jmxClient = null;
        try
        {
            l_jmxClient = new JmxRmiClient(l_intRmiPort);
        } catch (JmxRmiClientException l_jrce)
        {
            System.err.println("Unable to connect to xTier JMX Server : " + l_jrce.getMessage());
            System.exit(-1);
            return;
        }
        
        try
        {
            l_jmxClient.invoke(OBJECT_NAME, METHOD_NAME, null, null);
        } catch (Exception l_e)
        {
            System.err.println("NG: Unknown exception occured : " + l_e.getMessage());
            System.exit(-1);
            return;
        }
        
        System.out.println("OK");
        System.exit(0);
        
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
