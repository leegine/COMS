head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.46.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҐV�K���m�F���X�|���X(WEB3AdminBondExecConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҐV�K���m�F���X�|���X)<BR>
 * �Ǘ��ҐV�K���m�F���X�|���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecConfirmResponse extends WEB3GenResponse
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_confirm";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (����ID)<BR>
     * ����ID
     */
    public String id;
    
    /**
     * (���͎�������)<BR>
     * ���͎�������
     */
    public Date inpOrderDate;
    
    /**
     * (�����)<BR>
     * �����
     */
    public WEB3AdminBondOrderExecInfo execInfo;
    
    /**
     * (�ڋq���)<BR>
     * �ڋq���
     */
    public WEB3AdminBondAccountInfo accountInfo;
    
    /**
     * @@roseuid 44E3363701A5
     */
    public WEB3AdminBondExecConfirmResponse() 
    {
     
    }
    
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondExecConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);  
    }
}
@
