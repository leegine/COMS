head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\�����̓��X�|���X(WEB3InformAccSwElecDeliApplyInpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 �h�C(���u) �V�K�쐬 �d�l�ύX���f��097
*/
package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����ؑցE�d�q��t�\�����̓��X�|���X)<BR>
 * �����ؑցE�d�q��t�\�����̓��X�|���X�N���X<BR>
 *
 * @@author �h�C
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_acc_sw_elec_deli_apply_inp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707190952L;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;

    /**
     * (�ύX�O���)<BR>
     * �ύX�O���
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo beforeInfo;

    /**
     * @@roseuid 4663A9D7006D
     */
    public WEB3InformAccSwElecDeliApplyInpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3InformAccSwElecDeliApplyInpResponse(WEB3InformAccSwElecDeliApplyInpRequest l_request)
    {
        super(l_request);
    }
}
@
