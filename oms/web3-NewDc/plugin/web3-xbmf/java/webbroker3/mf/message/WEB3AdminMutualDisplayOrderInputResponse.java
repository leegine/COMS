head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��Җ����\�������o�^���͉�ʃ��X�|���X(WEB3AdminMutualDisplayOrderInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�Ǘ��Җ����\�������o�^���͉�ʃ��X�|���X)<BR>
 * �����M���Ǘ��Җ����\�������o�^���͉�ʃ��X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_display_order_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031008L;
    
    /**
     * (�����\�������o�^�ꗗ)<BR>
     * ���M�Ǘ��Җ����\�������o�^�ꗗ�s�I�u�W�F�N�g�̔z��
     */
    public WEB3MutualDisplayOrderGroup[] displayOrderGroups;
    
    /**
     * (���M�Ǘ��Җ����\�������o�^���͉�ʃ��X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4153BC2700B4
     */
    public WEB3AdminMutualDisplayOrderInputResponse()
    {
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminMutualDisplayOrderInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
