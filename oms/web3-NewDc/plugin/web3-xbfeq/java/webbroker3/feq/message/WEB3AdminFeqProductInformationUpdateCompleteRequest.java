head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInformationUpdateCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������������X�V�������N�G�X�g(WEB3AdminFeqProductInformationUpdateCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO�������������X�V�������N�G�X�g)<BR>
 * �Ǘ��ҊO�������������X�V�������N�G�X�g�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqProductInformationUpdateCompleteRequest 
    extends WEB3AdminFeqProductInfomationUpdateCommonRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productInformationUpdateComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 42CE39FA037A
     */
    public WEB3AdminFeqProductInformationUpdateCompleteRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqProductInformationUpdateCompleteResponse(this);
    }
}
@
