head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointAdjustCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�����������N�G�X�g(WEB3AdminPointAdjustCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�����������N�G�X�g)<BR>
 * �|�C���g�����������N�G�X�g�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointAdjustCompleteRequest extends WEB3AdminPointManageCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_adjustComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290001L;
    
    /**
     * (�����|�C���g)<BR>
     * ���͂��ꂽ�����|�C���g<BR>
     */
    public String adjustPoint;
    
    /**
     * (�m�F�������O���p�\�|�C���g)<BR>
     * �m�F���ɎZ�o���������O�̗��p�\�|�C���g<BR>
     */
    public String beforeAvailablePoint;
    
    /**
     * (�Ïؔԍ�)<BR>
     * ���͂��ꂽ�Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D1254D02BF
     */
    public WEB3AdminPointAdjustCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125480167
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointAdjustCompleteResponse(this);
    }

}
@
