head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsIndividualCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ʕԍψꗗ��ʕ\�����X�|���X
(WEB3Options�hndividualCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�����ʕԍψꗗ��ʕ\�����X�|���X)<BR>
 * �����w���I�v�V�����ʕԍψꗗ��ʕ\�����X�|���X�N���X<BR>                                                                    
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionsIndividualCloseMarginListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_individualCloseMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111735L;
    
    /**
     * (������)<BR>
     */
    public String opProductName;
    
    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����
     */
    public String contractType;
    
    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���
     */
    public String marketCode;
    
    /**
     * (���ݒl)<BR>
     */
    public String currentPrice;
    
    /**
     * (���ʖ���)<BR>
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits;
    
    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[
     */
    public String[] messageSuspension;    
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsIndividualCloseMarginListResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3OptionsIndividualCloseMarginListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
