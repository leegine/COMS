head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.53.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Җ��ύX���̓��X�|���X(WEB3AdminBondExecChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��Җ��ύX���̓��X�|���X)<BR>
 * �Ǘ��Җ��ύX���̓��X�|���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecChangeInputResponse extends WEB3GenResponse
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_change_input";

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
     * (�J�X�g�f�B�A���ꗗ)<BR>
     * �J�X�g�f�B�A���ꗗ<BR>
     * <BR>
     * �J�X�g�f�B�A���̔z��
     */
    public WEB3AdminBondCustodianUnit[] custodianList;
    
    /**
     * (���͎�������)<BR>
     * ���͎�������
     */
    public Date inpOrderDate;
    
    /**
     * @@roseuid 44E3363600AB
     */
    public WEB3AdminBondExecChangeInputResponse() 
    {
     
    }
    
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondExecChangeInputResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
