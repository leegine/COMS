head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointAdjustConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�����m�F���X�|���X(WEB3AdminPointAdjustConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�����m�F���X�|���X)<BR>
 * �|�C���g�����m�F���X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointAdjustConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_adjustConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410150127L;
    
    /**
     * (�����O���p�\�|�C���g)<BR>
     * �����O�̗��p�\�|�C���g<BR>
     */
    public String beforeAvailablePoint;
    
    /**
     * (�����㗘�p�\�|�C���g)<BR>
     * ������̗��p�\�|�C���g<BR>
     */
    public String afterAvailablePoint;
    
    /**
     * @@roseuid 41D1254D034B
     */
    public WEB3AdminPointAdjustConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointAdjustConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
