head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliApplySrcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����ؑցE�d�q��t�\���������X�|���X(WEB3AdminInformAccSwElecDeliApplySrcResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�����ؑցE�d�q��t�\���������X�|���X)<BR>
 * �Ǘ��ҁE�����ؑցE�d�q��t�\���������X�|���X�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliApplySrcResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_inform_acc_sw_elec_deli_apply_src";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707190838L;

    public WEB3AdminInformAccSwElecDeliApplySrcResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminInformAccSwElecDeliApplySrcResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
