head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�T�[�r�X��ʃ��X�|���X(WEB3PointApplyReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�|�C���g�T�[�r�X��ʃ��X�|���X)<BR>
 * �|�C���g�T�[�r�X��ʃ��X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointApplyReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290002L;
    
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
     * (�J�e�S���[�ꗗ)<BR>
     * �I���\�ȃJ�e�S���[�̈ꗗ<BR>
     */
    public WEB3AdminPointCategoryUnit[] categoryList;
    
    /**
     * (�\���󋵈ꗗ)<BR>
     * �Y���ڋq�̃|�C���g�����̐\����<BR>
     */
    public WEB3PointApplyStateDetail[] applyStateList;
    
    /**
     * @@roseuid 41D1255200CB
     */
    public WEB3PointApplyReferenceResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3PointApplyReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
