head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\���������X�|���X(WEB3InformAccSwElecDeliApplyCmpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 ���^�] (���u) �V�K�쐬 �d�l�ύX���f��097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����ؑցE�d�q��t�\���������X�|���X)<BR>
 * �����ؑցE�d�q��t�\���������X�|���X�N���X<BR>
 *
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_acc_sw_elec_deli_apply_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707191055L;

    /**
     * (���t���)<BR>
     * �����ؑցE�d�q��t�\�����t���<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo dateInfo;

    public WEB3InformAccSwElecDeliApplyCmpResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3InformAccSwElecDeliApplyCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
