head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumDeleteConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�폜�m�F���X�|���X(WEB3AdminPointPremiumDeleteConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�i�i�폜�m�F���X�|���X)<BR>
 * �i�i�폜�m�F���X�|���X�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumDeleteConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumDeleteConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * (�\������)<BR>
     * �Y������i�i�ɑ΂���\���̌���<BR>
     */
    public String applyCount;
    
    /**
     * (�i�i��)<BR>
     * �i�i��<BR>
     */
    public String premiumName;
    
    /**
     * (�K�v�|�C���g)<BR>
     * �K�v�|�C���g<BR>
     */
    public String requiredPoint;
    
    /**
     * (�񋟊J�n����)<BR>
     * �񋟊J�n����<BR>
     */
    public Date startDate;
    
    /**
     * (�񋟏I������)<BR>
     * �񋟏I������<BR>
     */
    public Date endDate;
    
    /**
     * @@roseuid 41D1254703D8
     */
    public WEB3AdminPointPremiumDeleteConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointPremiumDeleteConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
