head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\���m�F���X�|���X(WEB3PointApplyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�\���m�F���X�|���X)<BR>
 * �|�C���g�\���m�F���X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointApplyConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290006L;
    
    /**
     * @@roseuid 41D125510196
     */
    public WEB3PointApplyConfirmResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3PointApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
