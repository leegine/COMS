head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointHistoryReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�����Ɖ�X�|���X(WEB3AdminPointHistoryReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�����Ɖ�X�|���X)<BR>
 * �|�C���g�����Ɖ�X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointHistoryReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_historyReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290088L;
    
    /**
     * (���p�\�|�C���g)<BR>
     * �Y���ڋq�̗��p�\�|�C���g<BR>
     */
    public String availablePoint;
    
    /**
     * (�|�C���g�����ꗗ)<BR>
     * �|�C���g���𖾍ׂ̔z��<BR>
     */
    public WEB3AdminPointHistoryDetail[] pointHistoryList;
    
    /**
     * @@roseuid 41D1254E0119
     */
    public WEB3AdminPointHistoryReferenceResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointHistoryReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
