/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteConnectorクラス(DOTQuoteConnector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/22 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.jmx.rmi;

import com.fitechlabs.xtier.jmxrmi.JmxRmiClient;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;


/**
 * WEB3ルールエンジンにJMX-RMIを使用して接続し
 * 時価サーバとの接続を開始するように指示するJMX-RMIクライアントクラス。<BR>
 * <BR>
 * ルールエンジンのJMX-MRIポート番号を指定する場合は以下のようにパラメータを指定する。<BR>
 * <BR>
 * java DOTQuoteConnector -mri <JMX-RMIポート番号><BR>
 * <BR>
 * 終了時のステータスコードが「0」の場合は正常終了、
 * 「-1」の場合は異常終了。<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteConnector
{
    
    /** 実行するMBeanオブジェクトのオブジェクト名 */
    public static final String OBJECT_NAME = "WEB3IntelliOMS:name=QuoteFeederAdaptor";
    
    /** 実行するMBeanオブジェクトのメソッド名 */
    public static final String METHOD_NAME = "connect";

    /**
     * コンストラクタ
     */
    public DOTQuoteConnector()
    {
        super();
    }

    /**
     * メインメソッド
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
            System.err.println("NG: Unable to connect to xTier JMX Server : " + l_jrce.getMessage());
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
     * このクラスの使用法を標準出力に出力する。
     */
    private static void showUsage()
    {
        System.out.println("Usage: -rmi <rule-engine-rmi-port-number>");
        System.exit(-1);
    }
    
}
