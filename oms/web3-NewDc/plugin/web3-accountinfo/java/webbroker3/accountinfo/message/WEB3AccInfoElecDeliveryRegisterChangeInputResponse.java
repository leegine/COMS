head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryRegisterChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �d�q��t�T�[�r�X�o�^�E�ύX���̓��X�|���X(WEB3AccInfoElecDeliveryRegisterChangeInputResponse.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 �����F(���u) �V�K�쐬 �d�l�ύX���f��No.277
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�d�q��t�T�[�r�X�o�^�E�ύX���̓��X�|���X)<BR>
 * �d�q��t�T�[�r�X�o�^�E�ύX���̓��X�|���X�N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryRegisterChangeInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "elec_delivery_register_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011121512L;

    /**
     * �d�q��t���<BR>
     */
    public WEB3AccInfoEleDeliveryInfo eleDeliveryInfo;

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
     public WEB3AccInfoElecDeliveryRegisterChangeInputResponse(WEB3GenRequest l_request)
     {
         super(l_request);
     }
}
@
