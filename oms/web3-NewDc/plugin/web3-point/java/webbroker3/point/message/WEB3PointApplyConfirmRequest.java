head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\���m�F���N�G�X�g(WEB3PointApplyConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�\���m�F���N�G�X�g)<BR>
 * �|�C���g�\���m�F���N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointApplyConfirmRequest extends WEB3PointApplyCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290007L;
    
    /**
     * @@roseuid 41D125510138
     */
    public WEB3PointApplyConfirmRequest() 
    {
     
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12552000F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3PointApplyConfirmResponse(this);
    }
    
}
@
