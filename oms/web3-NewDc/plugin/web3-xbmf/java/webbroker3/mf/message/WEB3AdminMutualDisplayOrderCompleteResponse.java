head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��Җ����\�������o�^�������X�|���X(WEB3AdminMutualDisplayOrderCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�Ǘ��Җ����\�������o�^�������X�|���X)<BR>
 * �����M���Ǘ��Җ����\�������o�^�������X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_display_order_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031046L;
   
    /**
     * (���M�Ǘ��Җ����\�������o�^�������X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4153BC4A00A4
     */
    public WEB3AdminMutualDisplayOrderCompleteResponse()
    {
    }

    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminMutualDisplayOrderCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
