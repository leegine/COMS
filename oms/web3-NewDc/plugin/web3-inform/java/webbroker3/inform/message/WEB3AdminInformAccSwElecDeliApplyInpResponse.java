head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliApplyInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����ؑցE�d�q��t�\�����̓��X�|���X(WEB3AdminInformAccSwElecDeliApplyInpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����ؑցE�d�q��t�\�����̓��X�|���X)<BR>
 * �Ǘ��ҁE�����ؑցE�d�q��t�\�����̓��X�|���X�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliApplyInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_inform_acc_sw_elec_deli_apply_inp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707190908L;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     */
    public String accountName;

    /**
     * (�ύX�O���)<BR>
     * �����ؑցE�d�q��t�\�����<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo beforeInfo;

    /**
     * (�ύX����)<BR>
     * �����ؑցE�d�q��t�\�����<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo;

    public WEB3AdminInformAccSwElecDeliApplyInpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminInformAccSwElecDeliApplyInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
