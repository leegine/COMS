head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCalendarRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������J�����_�[�o�^�������N�G�X�g(WEB3AdminFeqCalendarRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 �A�C��(���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҊO�������J�����_�[�o�^�������N�G�X�g)<BR>
 * �Ǘ��ҊO�������J�����_�[�o�^�������N�G�X�g�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqCalendarRegistCompleteRequest extends WEB3AdminFeqCalendarRegistCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_calendarRegistComplete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 42CE3A000261
     */
    public WEB3AdminFeqCalendarRegistCompleteRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqCalendarRegistCompleteResponse(this);
    }
}
@
