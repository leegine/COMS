head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPAdjustConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��җ]�͒����m�F���N�G�X�g�N���X(WEB3AdminMutualTPAdjustConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ��O�� (���u) �V�K�쐬
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * ���M�Ǘ��җ]�͒����m�F���N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminMutualTPAdjustConfirmRequest extends WEB3AdminMutualTPAdjustCommonRequest
{   
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_tp_adjust_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200512191015L;

    /**
     * (���M�Ǘ��җ]�͒����m�F���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8A1C4018D
     */
    public WEB3AdminMutualTPAdjustConfirmRequest()
    {

    }

    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * ���M�Ǘ��җ]�͒����m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A1CE00E1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualTPAdjustConfirmResponse(this);
    }
}
@
