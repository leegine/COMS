head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.47.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ�����m�F���X�|���X(WEB3AdminBondExecCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��Җ�����m�F���X�|���X)<BR>
 * �Ǘ��Җ�����m�F���X�|���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecCancelConfirmResponse extends WEB3GenResponse
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_cancel_confirm";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (�ڋq���)<BR>
     * �ڋq���
     */
    public WEB3AdminBondAccountInfo accountInfo;
    
    /**
     * (�������)<BR>
     * �������
     */
    public WEB3AdminBondProductInfo productInfo;
    
    /**
     * (�������)<BR>
     * �������
     */
    public WEB3AdminBondOrderInfo orderInfo;
    
    /**
     * (�����)<BR>
     * �����
     */
    public WEB3AdminBondOrderExecInfo execInfo;
    
    /**
     * @@roseuid 44E3363400CB
     */
    public WEB3AdminBondExecCancelConfirmResponse() 
    {
     
    }
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondExecCancelConfirmResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
