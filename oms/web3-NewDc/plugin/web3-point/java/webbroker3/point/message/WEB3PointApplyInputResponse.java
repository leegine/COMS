head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.59.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\���I�����X�|���X(WEB3PointApplyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�\���I�����X�|���X)<BR>
 * �|�C���g�\���I�����X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointApplyInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290004L;
    
    /**
     * (���p�\�|�C���g)<BR>
     * �Y���ڋq�̗��p�\�|�C���g<BR>
     */
    public String availablePoint;
    
    /**
     * (�������Ӄ|�C���g)<BR>
     * �Y���ڋq�̎������Ӄ|�C���g<BR>
     */
    public String lapseWarningPoint;
    
    /**
     * (�i�i�ꗗ)<BR>
     * �I���\�Ȍi�i�̈ꗗ<BR>
     */
    public WEB3PointPremiumUnit[] premiumList;
    
    /**
     * @@roseuid 41D12551031C
     */
    public WEB3PointApplyInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3PointApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
