head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�ʒm���X�|���X(WEB3AioCashinCooperationNotifyResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 ����(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * �����A�g�ʒm���X�|���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyResponse extends WEB3BackResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "aio_cashin_cooperation_notify";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200601111656L;
    
    /**
     * (�����A�g�ʒm���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40A9A18C001B
     */
    public WEB3AioCashinCooperationNotifyResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashinCooperationNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }    
}
@
