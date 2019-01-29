head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3QuoteCheckClientThread.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteCheckClientThread�N���X(WEB3QuoteCheckClientThread.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/03 �� (FLJ)�V�K�쐬
*/

package webbroker3.quotecheck.client;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;

/**
 * �i��������_�`�F�b�N�T�[�r�X�N���C�A���g�̃X���b�h�j
 *
 * @@author ��(FLJ)
 * @@version 1.0
 */
public class WEB3QuoteCheckClientThread implements Runnable
{

    private boolean is_stoped = false;

    private ServerAccessor accessor;

    public WEB3QuoteCheckClientThread(ServerAccessor l_accessor)
    {
        this.accessor = l_accessor;
    }

    public synchronized boolean isStopped()
    {
        return is_stoped;
    }

    /* (�� Javadoc)
     * @@see java.lang.Runnable#run()
     */
    public void run()
    {
        if(accessor!=null)
        {
            //���N�G�X�g���쐬
            String l_strRequest = "<request p_type=\""
                + "quote_check"
                + "\">"
                + "</request>";


            try
            {
                System.out.println(l_strRequest);

                //���N�G�X�g�𑗐M
                System.out.println(
                        accessor.toString() + ", "
                      + accessor.doRequest(l_strRequest));
            }
            catch (Throwable e)
            {
                System.out.println("ERROR: Exception occur :" + e.getMessage());
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("ERROE: accessor is Null.");
        }

        synchronized(this)
        {
            is_stoped = true;
        }
    }

}
@
