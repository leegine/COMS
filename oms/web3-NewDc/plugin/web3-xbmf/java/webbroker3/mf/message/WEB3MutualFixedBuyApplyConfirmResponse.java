head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�V�K�\���m�F���X�|���X(WEB3MutualFixedBuyApplyConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 ���G�� (���u) �V�K�쐬
Revesion History : 2007/10/25 ���^�] (���u) �d�l�ύX ���f��No.585
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���M�莞��z���t�V�K�\���m�F���X�|���X)<BR>
 * ���M�莞��z���t�V�K�\���m�F���X�|���X<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyApplyConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_apply_confirm";
    
    /**
     * SerialVersionUID<BR>
     */   
    public final static long serialVersionUID = 200606261700L;
 
    /**
     * (�O���،������J�ݗv���t���O)<BR>
     * �O���،������J�ݗv���t���O<BR>
     * <BR>
     * true:�J�݂̕K�v���� <BR>
     * false:�J�݂̕K�v�Ȃ�<BR>
     */
    public boolean foreignSecAccOpenFlag;

    /**
     * (�d�q���`�F�b�N����)<BR>
     * �d�q���`�F�b�N����<BR>
     * <BR>
     * 0�F�{���ρ@@1�F�{�����ρ@@2�F�{������(��Q��)<BR>
     */
    public String batoCheckResult;

    /**
     * (�ژ_�����{�����ϖ����R�[�h�ꗗ)<BR>
     * �ژ_�����{�����ϖ����R�[�h�ꗗ<BR>
     */
    public String[] noReadProductCodeList;

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MutualFixedBuyApplyConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
   
    /**
     * (���M�莞��z���t�V�K�\���m�F���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyApplyConfirmResponse() 
    {     
    }    
}
@
