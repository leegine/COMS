head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�\���������X�|���X(WEB3PointApplyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�\���������X�|���X)<BR>
 * �|�C���g�\���������X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointApplyCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290008L;
    
    /**
     * (�X�V����)<BR>
     * �\�����o�^���ꂽ����<BR>
     */
    public Date updateTimeStamp;
    
    /**
     * (�\��ID)<BR>
     * �o�^���ꂽ�\����ID<BR>
     */
    public String applyId;
    
    /**
     * @@roseuid 41D12551008C
     */
    public WEB3PointApplyCompleteResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3PointApplyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
