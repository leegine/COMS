head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3IfoDepositCalcResultSaveClient.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋����v�Z���ʕۑ��T�[�r�X�N���C�A���g(WEB3IfoDepositCalcResultSaveClient.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/16 ��(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit.client;

import webbroker3.system.tune.affinity.WEB3ServerMgrAccessor;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerConnector;

/**
 * �i�؋����v�Z���ʕۑ��T�[�r�X�N���C�A���g�j�B
 * @@version 1.0
 */
public class WEB3IfoDepositCalcResultSaveClient
{
    
    /**
     * �X���b�h�`�F�b�N���ԁ�100�~���b
     */
    final static int SLEEP_TIME = 100;
    
    /**
     * �^�C���A�E�g��10��
     */
    final static int DEFAULT_TIMEOUT = 10 * 60 * 1000;
    
    /**
     * �A�N�Z�X�L�[
     */
    public final static String ACCESSOR_KEY = "web3-static-cluster";
    
    public static void main(String args[]) throws Exception
    {
        if (args.length == 4 )
        {
            
            //Params���擾
            String l_strUrls = args[0];
            String[] l_urls = l_strUrls.split(";");
            
            String l_strThreads = args[1];
            String[] l_threads = l_strThreads.split(";");
            
            String l_strFromAccIds = args[2];
            String[] l_fromAccIds = l_strFromAccIds.split(";");
            
            String l_strToAccIds = args[3];
            String[] l_toAccIds = l_strToAccIds.split(";");
            
            //Accessor���쐬
            ServerAccessor accessor = ServerConnector.createAccessor(ACCESSOR_KEY,l_urls); 
            ((WEB3ServerMgrAccessor)accessor).setConnectTimeOut(DEFAULT_TIMEOUT);
            
            //�X���b�h�N��
            if((l_fromAccIds.length == l_toAccIds.length) && (l_threads.length == l_fromAccIds.length))
            {
                //�X���b�h���쐬���āA�X�^�[�g
                WEB3IfoDepositCalcResultSaveClientThread[] l_ths = new WEB3IfoDepositCalcResultSaveClientThread[l_threads.length];
                for (int i = 0; i < l_ths.length; i++)
                {
                    l_ths[i] = new WEB3IfoDepositCalcResultSaveClientThread(accessor,l_threads[i],l_fromAccIds[i],l_toAccIds[i]);
                    Thread l_th = new Thread(l_ths[i]);
                    l_th.start();
                }
                
                //���ׂẴX���b�h�I��������Aexit
                while(true)
                {
                    boolean l_isAllStopped = true;
                
                    for (int i = 0; i < l_ths.length; i++)
                    {
                        if(!l_ths[i].isStopped())
                        {
                            l_isAllStopped = l_ths[i].isStopped();
                        }
                    }
                    
                    if(l_isAllStopped)
                    {
                        break;
                    }
                    
                    try
                    {
                        Thread.sleep(SLEEP_TIME);
                    }
                    catch(InterruptedException e)
                    {
                        ;
                    }
                }

            }
            else
            {
                System.out.println("ERROR: parameter error! The length of urls,threads,fromaccids,toaccids must be equal!");
            }    
        }
        else
        {
            System.out.println("ERROR: Usage: java WEB3IfoDepositCalcResultSaveClient $urls $threads $fromaccids $toaccids");
        }
        
        System.exit(0);
    }

}
@
