head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsOpenChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�����w���I�v�V���������V�K���������X�|���X(WEB3SuccOptionsOpenChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 ���V�� (���u) �V�K�쐬 ���f�� No.263,304
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse;


/**
 * (�i�A���j�����w���I�v�V���������V�K���������X�|���X)<BR>
 * �i�A���j�����w���I�v�V���������V�K���������X�|���X�N���X<BR>
 * <BR>
 * @@author ���V��
 * @@version 1.0
 */
public class WEB3SuccOptionsOpenChangeCompleteResponse extends WEB3OptionsOpenMarginChangeCompleteResponse
{

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141538L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_open_change_complete";

    /**
     * @@roseuid 47D9F2CA0163
     */
    public WEB3SuccOptionsOpenChangeCompleteResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����<BR>
     * @@param l_request -���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccOptionsOpenChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
