head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�i�������̓��X�|���X(WEB3AdminPointPremiumChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 ���w��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�i�i�������̓��X�|���X)<BR>
 * �i�i�������̓��X�|���X�N���X<BR>
 *
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminPointPremiumChangeInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumChangeInput";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * (�����O�i�i��)<BR>
     * �����O�̌i�i��<BR>
     */
    public String beforePremiumName;
    
    /**
     * (�����O�K�v�|�C���g)<BR>
     * �����O�̕K�v�|�C���g<BR>
     */
    public String beforeRequiredPoint;
    
    /**
     * (�����O�񋟊J�n����)<BR>
     * �����O�̒񋟊J�n����<BR>
     */
    public Date beforeStartDate;
    
    /**
     * (�����O�񋟏I������)<BR>
     * �����O�̒񋟏I������<BR>
     */
    public Date beforeEndDate;
    
    /**
     * @@roseuid 41D12549031C
     */
    public WEB3AdminPointPremiumChangeInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointPremiumChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
