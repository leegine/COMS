head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsXtierUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : xTierユーティリティ(WEB3RlsXtierUtil.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/09 齋藤 栄三(FLJ) 新規作成
*/
package webbroker3.rlsgateway.connector;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import com.fitechlabs.xtier.microkernel.MicroKernelException;
import com.fitechlabs.xtier.microkernel.std.StandardMicroKernel;
import com.fitechlabs.xtier.microkernel.std.StandardMicroKernelParams;

import webbroker3.util.WEB3LogUtility;

/**
 *
 * ルールエンジンへの注文送信ユーティリティ
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsXtierUtil
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsXtierUtil.class);
    
    /**
     * startXtier <BR>
     * <BR>
     * xTierを起動する。 <BR>
     * <BR>
     * @@Exception MicroKernelException
     */        
    public static void startXtier() throws MicroKernelException
    {
        
        log.info("xtier starting...");

        StandardMicroKernelParams params = new StandardMicroKernelParams();

        params.setAppRegion(WEB3RlsConnectorConstants.XTIER_APP_REGION); 
        params.setLocale(Locale.JAPAN);

        String path = getXtierRoot();
        params.setRoot(path);

        log.info("xtier root = " + params.getRoot());

        StandardMicroKernel.start(params);
        
        log.info("xtier started.");

    }
    
    /**
     * getXtierルート <BR>
     * <BR>
     * xTierのルートディレクトリを取得する。 <BR>
     * <BR>
     * 　@１）WEBアプリの場合 WEB-INF/classesを返す。 <BR>
     * <BR>
     * 　@２）WEBアプリ以外の場合、クラスパス 又は 作業場所で最初に発見した/config/xtier_kernel.xmlが存在するディレクトリを返す。 <BR>
     * <BR>
     * @@return String
     */        
    public static String getXtierRoot()
    {
        
        String l_root = null;

        URL l_url = WEB3RlsXtierUtil.class.getClassLoader().getResource(WEB3RlsConnectorConstants.XTIER_KERNEL_FILE);

        if(l_url != null)
        {
            File l_kernel = new File(l_url.getPath());
            File l_configDir = new File(l_kernel.getParent());
            l_root = l_configDir.getParent();
        }
        
        if(l_root == null || l_root.length() == 0)
        {
            String[] l_dirs = getClassPathAndWorkingDir();
            
            if(l_dirs != null)
            {
                for(int i=0;i < l_dirs.length;i++)
                {
                    File l_file = new File(l_dirs[i], WEB3RlsConnectorConstants.XTIER_KERNEL_FILE);
                    
                    if(l_file.exists())
                    {
                        File l_rootDir = new File(l_dirs[i]);
                        l_root = l_rootDir.getPath();
                        break;
                    }
                }
            }
        }
        
        return l_root;
    }
    
    /**
     * getクラスパスand作業ディレクトリ<BR>
     * <BR>
     * クラスパスディレクトリ、現在の作業ディレクトリを取得する。 <BR>
     * <BR>
     * @@return String
     */        
    private static String[] getClassPathAndWorkingDir()
    {
        String cp = System.getProperty(WEB3RlsConnectorConstants.JAVA_CLASS_PATH);
        String delim = System.getProperty(WEB3RlsConnectorConstants.PATH_SEPARATOR);
        cp = cp + delim + new File(".").getAbsolutePath();
        
        log.info("classpath = " + cp);
        log.info("delim = " + delim);

        StringTokenizer st = new StringTokenizer(cp, delim);
        List dirs = new ArrayList();
        for(int i = 0; st.hasMoreTokens(); i++)
        {
            String dir = st.nextToken();
            if(!dir.endsWith(".jar") && !dir.endsWith(".zip"))
            {
                dirs.add(dir);
            }
        }
        
        return (String[])dirs.toArray(new String[dirs.size()]);
    }
}
@
