head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.07.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\�����̓��X�|���X(WEB3AioCashoutInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�o���\�����̓��X�|���X)<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutInputResponse extends WEB3AioCashoutCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101145L; 
       
    /**
     * (�o���]��)<BR>
     * �ڋq�́iT+1�`T+5)�̏o���]�͂̃��X�g
     */
    public String[] paymentPowerList;
    
    /**
     * (�U���\���)<BR>
     * �iT+1�`T+5)�̐U���\����̃��X�g
     */
    public Date[] transScheduledDateList;
    
    /**
     * @@roseuid 4158EB620273
     */
    public WEB3AioCashoutInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashoutInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
