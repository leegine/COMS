/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RuleEngineStatusCheckerクラス(DOTRuleEngineStatusChecker.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/06 堀野　和美(FLJ) 新規作成
 */

package jp.co.dir.dot.intellioms.jmx.rmi;

import com.fitechlabs.xtier.jmxrmi.JmxRmiClient;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;

/**
 * WEB3ルールエンジンにJMX-RMIを使用して接続し
 * ルールエンジンの稼動状態をチェックするJMX-RMIクライアントクラス。<BR>
 * <BR>
 * ルールエンジンのJMX-MRIポート番号を指定する場合は以下のようにパラメータを指定する。<BR>
 * <BR>
 * java DOTRuleEngineStatusChecker -rmi <JMX-RMIポート番号><BR>
 * <BR>
 * 終了時のステータスコードが
 * 「0」の場合は起動中
 * 「1」の場合は不明な状態<BR>
 * 「2」の場合は停止中<BR>
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRuleEngineStatusChecker 
{
    
    /** 実行するMBeanオブジェクトのオブジェクト名 */
    public static final String OBJECT_NAME = "IntelliOMS:name=RuleEngineConnectorManager";
    
    /** 実行するMBeanオブジェクトのメソッド名 */
    public static final String METHOD_NAME = "isStarted";
    
    
    
    /**
     * メインメソッド
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
     * このクラスの使用法を標準出力に出力する。
     */
    private static void showUsage()
    {
        System.out.println("Usage: -rmi <rule-engine-rmi-port-number>");
        System.exit(-1);
    }
    

}
