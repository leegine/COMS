head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualFrgncalReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���C�O�s��J�����_�[�o�^�Ɖ�X�|���X(WEB3AdminMutualFrgncalReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/15 �����(���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[ 
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �����M���C�O�s��J�����_�[�o�^�Ɖ�X�|���X�N���X
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualFrgncalReferenceResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_mutual_frgncal_reference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408151424L;
    
    /**
     * (�J�����_�[�x���ꗗ)<BR>
     * �Y�����̔�c�Ɠ����<BR>
     */
    public Date[] notBizDateList;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40C00CDF00E5
     */
    public WEB3AdminMutualFrgncalReferenceResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminMutualFrgncalReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
