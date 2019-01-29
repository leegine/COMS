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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : xTier���[�e�B���e�B(WEB3RlsXtierUtil.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/09 �V�� �h�O(FLJ) �V�K�쐬
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
 * ���[���G���W���ւ̒������M���[�e�B���e�B
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsXtierUtil
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsXtierUtil.class);
    
    /**
     * startXtier <BR>
     * <BR>
     * xTier���N������B <BR>
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
     * getXtier���[�g <BR>
     * <BR>
     * xTier�̃��[�g�f�B���N�g�����擾����B <BR>
     * <BR>
     * �@@�P�jWEB�A�v���̏ꍇ WEB-INF/classes��Ԃ��B <BR>
     * <BR>
     * �@@�Q�jWEB�A�v���ȊO�̏ꍇ�A�N���X�p�X ���� ��Əꏊ�ōŏ��ɔ�������/config/xtier_kernel.xml�����݂���f�B���N�g����Ԃ��B <BR>
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
     * get�N���X�p�Xand��ƃf�B���N�g��<BR>
     * <BR>
     * �N���X�p�X�f�B���N�g���A���݂̍�ƃf�B���N�g�����擾����B <BR>
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
