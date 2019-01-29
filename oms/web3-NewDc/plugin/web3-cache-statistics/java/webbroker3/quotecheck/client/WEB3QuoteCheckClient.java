head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckClient.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ��������_�`�F�b�N�T�[�r�X�N���C�A���g(WEB3QuoteCheckClient.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03 �� (FLJ)�V�K�쐬
 */
package webbroker3.quotecheck.client;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.SocketPoolAccessor;

/**
 * �i��������_�`�F�b�N�T�[�r�X�N���C�A���g�j�B
 * @@version 1.0
 */
public class WEB3QuoteCheckClient
{
    /**
     * �X���b�h�`�F�b�N���ԁ�100�~���b
     */
    final static int SLEEP_TIME = 100;
       
    public static void main(String args[]) throws Exception
    {
        if (args.length == 1 )
        {
            //Params���擾
            String l_strUrls = args[0];
            String[] l_urls = l_strUrls.split(";");
            
            //�X���b�h���쐬���āA�X�^�[�g
            WEB3QuoteCheckClientThread[] l_ths = new WEB3QuoteCheckClientThread[l_urls.length];
            for (int i = 0; i < l_ths.length; i++)
            {
                //Accessor���쐬
                ServerAccessor accessor = new SocketPoolAccessor(l_urls[i]);
                
                l_ths[i] = new WEB3QuoteCheckClientThread(accessor);
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
            System.out.println("ERROR: Usage: java WEB3QuoteCheckClient $urls");
        }
        
        System.exit(0);
    }

}
@
