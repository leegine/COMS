head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���C�O�s��J�����_�[�o�^���̓��N�G�X�g(WEB3AdminMutualFrgncalInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 �����(���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[ 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �����M���C�O�s��J�����_�[�o�^���̓��N�G�X�g�N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_mutual_frgncal_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151434L;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40C0077E03B3
     */
    public WEB3AdminMutualFrgncalInputRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�C�O�s��J�����_�[�o�^���͉�ʃ��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40C0078800E5
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualFrgncalInputResponse(this);
    }
}
@
