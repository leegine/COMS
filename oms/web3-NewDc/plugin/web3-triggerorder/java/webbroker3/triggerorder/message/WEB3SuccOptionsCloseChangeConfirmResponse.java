head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����w���I�v�V���������ԍϊm�F���X�|���X(WEB3SuccOptionsCloseChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 ���V�� (���u) �V�K�쐬 ���f�� No.263,305
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse;


/**
 * (�i�A���j�����w���I�v�V���������ԍϊm�F���X�|���X)<BR>
 * �i�A���j�����w���I�v�V���������ԍϊm�F���X�|���X�N���X<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseChangeConfirmResponse extends WEB3OptionsCloseMarginChangeConfirmResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141438L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_change_confirm";

    /**
     * (������P��)<BR>
     * ������P���B <BR>
     * �}�w�l���w�肳�ꂽ�ꍇ�̂݃Z�b�g�B<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 47D9F2C9023E
     */
    public WEB3SuccOptionsCloseChangeConfirmResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����<BR>
     * @@param l_request -���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccOptionsCloseChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
