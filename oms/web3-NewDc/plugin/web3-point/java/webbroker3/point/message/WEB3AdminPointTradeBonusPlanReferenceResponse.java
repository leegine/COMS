head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointTradeBonusPlanReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�X�|���X(WEB3AdminPointTradeBonusPlanReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/06/14 �s�p(���u) �V�K�쐬
*/

package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�X�|���X)<BR>
 * �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�X�|���X�N���X<BR>
 */
public class WEB3AdminPointTradeBonusPlanReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_tradeBonusPlanReference";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200506141000L;
    
    /**
     * (�|�C���g�K�p�N��)<BR>
     * (YYYYMM)<BR>
     */
    public String applyMonthCurr;
    
    /**
     * (�g���[�h�|�C���g)<BR>
     */
    public String trdPointCurr;
    
    /**
     * (�L�����y�[���|�C���g)<BR>
     */
    public String cmpPointCurr;
    
    /**
     * (���v�|�C���g)<BR>
     */
    public String totalPointCurr;
    
    /**
     * (������)<BR>
     */
    public String cutRateCurr;
    
    /**
     * (�|�C���g�K�p�N���i�����j)<BR>
     * (YYYYMM)<BR>
     */
    public String applyMonthNext;
    
    /**
     * (�g���[�h�|�C���g�i�����j)<BR>
     */
    public String trdPointNext;
    
    /**
     * (�L�����y�[���|�C���g�i�����j)<BR>
     */
    public String cmpPointNext;
    
    /**
     * (���v�|�C���g�i�����j)<BR>
     */
    public String totalPointNext;
    
    /**
     * (�������i�����j)<BR>
     */
    public String cutRateNext;
    
    /**
     * @@roseuid 42AE353303D8
     */
    public WEB3AdminPointTradeBonusPlanReferenceResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminPointTradeBonusPlanReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
