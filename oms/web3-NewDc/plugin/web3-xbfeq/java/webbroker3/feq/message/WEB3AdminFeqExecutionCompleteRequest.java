head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o�����͊������N�G�X�g(WEB3AdminFeqExecutionCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO�������o�����͊������N�G�X�g)<BR>
 * �Ǘ��ҊO�������o�����͊������N�G�X�g�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionCompleteRequest extends WEB3AdminFeqExecutionCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionComplete";
        
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
     * @@roseuid 42CE39FD030D
     */
    public WEB3AdminFeqExecutionCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * super.validate()���R�[������B
     * @@roseuid 4292C2B1011D
     */
    public void validate() 
        throws WEB3BaseException
    {
        super.validate();
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqExecutionCompleteResponse(this);
    }
}
@
