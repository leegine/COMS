head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�������͉�ʃ��X�|���X(WEB3AdminAioCashinNoticeChangeInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/20 ���r (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����ʒm�������͉�ʃ��X�|���X)<BR>
 * �����ʒm�������͉�ʃ��X�|���X�N���X<BR>
 * 
 * @@author ���r(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeChangeInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_notice_change_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211119L;        
    
    /**
     * (�����ʒm���׈ꗗ)<BR>
     */
    public WEB3AioCashinNoticeUnit2[] cashinNoticeList;
    
    
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminAioCashinNoticeChangeInputResponse(WEB3AdminAioCashinNoticeChangeInputRequest l_request)
    {
        super(l_request);
    }  
}
@
