head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���������N�G�X�g�N���X(WEB3AioCashinNoticeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/


package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����A���������N�G�X�g)<BR>
 * �����A���������N�G�X�g�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCompleteRequest extends WEB3AioCashinNoticeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_notice_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111111L;
    
    /**
     * (�Ïؔԍ�)<BR>
     * ��ʂɂē��͂��ꂽ�Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 4158E9B80036
     */
    public WEB3AioCashinNoticeCompleteRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �����A���������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinNoticeCompleteResponse(this);
    }

}
@
