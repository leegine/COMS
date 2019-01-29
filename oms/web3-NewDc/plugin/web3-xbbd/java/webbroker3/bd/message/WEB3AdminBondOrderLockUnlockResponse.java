head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.45.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderLockUnlockResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ��������b�N�敪�X�V���X�|���X(WEB3AdminBondOrderLockUnlockResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҍ��������b�N�敪�X�V���X�|���X)<BR>
 * �Ǘ��ҍ��������b�N�敪�X�V���X�|���X�N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondOrderLockUnlockResponse extends WEB3GenResponse
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_order_lock_unlock";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (���b�N�����{�^���敪)<BR>
     * ���b�N�����{�^���敪<BR>
     * <BR>
     * 0�F��\�� 1�F�����{�^�� 2�F���b�N�{�^���@@�@@
     */
    public String lockDiv;
    
    /**
     * (���ύX�{�^���敪)<BR>
     * ���ύX�{�^���敪<BR>
     * <BR>
     * 0�F��\���@@1�F���{�^���@@2�F�ύX�{�^��
     */
    public String execChgDiv;
    
    /**
     * (����{�^���敪)<BR>
     * ����{�^���敪<BR>
     * <BR>
     * 0�F��\���@@1�F����{�^��
     */
    public String cancelDiv;
    
    /**
     * (�X�V����)<BR>
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * @@roseuid 44E3363903A9
     */
    public WEB3AdminBondOrderLockUnlockResponse() 
    {
     
    }
    
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondOrderLockUnlockResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
