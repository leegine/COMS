head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3IfoDepositCalcResultSaveClientThread.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3IfoDepositCalcResultSaveClientThread�N���X(WEB3IfoDepositCalcResultSaveClientThread.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/23 ��(FLJ) �V�K�쐬
*/

package webbroker3.ifodeposit.client;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;

/**
 * �i�؋����v�Z���ʕۑ��T�[�r�X�N���C�A���g�̃X���b�h�j
 *
 * @@author ��(FLJ)
 * @@version 1.0
 */
public class WEB3IfoDepositCalcResultSaveClientThread implements Runnable
{

    private boolean is_stoped = false;

    private ServerAccessor accessor;

    private String thread_no;

    private String fromAccId;

    private String toAccId;

    public WEB3IfoDepositCalcResultSaveClientThread(ServerAccessor l_accessor,String l_thread_no,String l_faid,String l_taid)
    {
        this.accessor = l_accessor;
        this.thread_no = l_thread_no;
        this.fromAccId = l_faid;
        this.toAccId = l_taid;
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
                + "ifodeposit_calc_result_save"
                + "\">"
                + "<threadNo>"
                + thread_no
                + "</threadNo>"
                + "<account_id_range>"
                + fromAccId+","+toAccId
                + "</account_id_range>"
                + "<fromAccountId>"
                + fromAccId
                + "</fromAccountId>"
                + "<toAccountId>"
                + toAccId
                + "</toAccountId>"
                + "</request>";


            try
            {
                System.out.println(l_strRequest);

                //���N�G�X�g�𑗐M
                System.out.print(accessor.doRequest(l_strRequest));
            }
            catch (Exception e)
            {
                System.out.print("ERROR: Exception occur :" + e.getMessage());
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
