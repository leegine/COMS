head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.54.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointTradeBonusPlanReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���[�h�{�[�i�X�v�����Ɖ�X�|���X(WEB3PointTradeBonusPlanReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�g���[�h�{�[�i�X�v�����Ɖ�X�|���X)<BR>
 * �g���[�h�{�[�i�X�v�����Ɖ�X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3PointTradeBonusPlanReferenceResponse extends WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_tradeBonusPlanReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502241605L;
    
    /**
     * (�|�C���g�K�p�N��)<BR>
     * �|�C���g�K�p�N��<BR>
     * (YYYYMM)<BR>
     */
    public String applyMonthCurr;
    
    /**
     * (�g���[�h�|�C���g)<BR>
     * �g���[�h�|�C���g<BR>
     */
    public String trdPointCurr;
    
    /**
     * �L�����y�[���|�C���g<BR>
     * (�L�����y�[���|�C���g)<BR>
     */
    public String cmpPointCurr;
    
    /**
     * (���v�|�C���g)<BR>
     * ���v�|�C���g<BR>
     */
    public String totalPointCurr;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String cutRateCurr;
    
    /**
     * (�|�C���g�K�p�N���i�����j)<BR>
     * �|�C���g�K�p�N���i�����j<BR>
     * (YYYYMM)<BR>
     */
    public String applyMonthNext;
    
    /**
     * (�g���[�h�|�C���g�i�����j)<BR>
     * �g���[�h�|�C���g�i�����j<BR>
     */
    public String trdPointNext;
    
    /**
     * (�L�����y�[���|�C���g�i�����j)<BR>
     * �L�����y�[���|�C���g�i�����j<BR>
     */
    public String cmpPointNext;
    
    /**
     * (���v�|�C���g�i�����j)<BR>
     * ���v�|�C���g�i�����j<BR>
     */
    public String totalPointNext;
    
    /**
     * (�������i�����j)<BR>
     * �������i�����j<BR>
     */
    public String cutRateNext;    
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3PointTradeBonusPlanReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
