head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliChangeCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�������X�|���X�iWEB3AdminInformAccSwElecDeliChangeCmpResponse.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 �g�E�N�| (���u) �V�K�쐬�@@�d�l�ύX���f��110
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�������X�|���X)<BR>
 * �Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�������X�|���X<BR>
 * <BR>
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliChangeCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_acc_sw_elec_deli_change_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709191123L;

    /**
     * (���t���)<BR>
     * ���t���<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo dateInfo;

    /**
     * @@roseuid 46F0900C03D0
     */
    public WEB3AdminInformAccSwElecDeliChangeCmpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminInformAccSwElecDeliChangeCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
