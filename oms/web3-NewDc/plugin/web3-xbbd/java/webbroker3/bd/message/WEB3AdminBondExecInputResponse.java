head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҐV�K�����̓��X�|���X(WEB3AdminBondExecInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҐV�K�����̓��X�|���X)<BR>
 * �Ǘ��ҐV�K�����̓��X�|���X�N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecInputResponse extends WEB3GenResponse 
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_input";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
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
     * (������ʈꗗ)<BR>
     * ������ʈꗗ<BR>
     * <BR>
     * 401�F�����������@@402�F�����蒍��
     */
    public String[] tradingTypeList;
    
    /**
     * (����ꗗ)<BR>
     * ����ꗗ<BR>
     * <BR>
     * 35:��W����@@92:�����d�؎��
     */
    public String[] dealTypeList;
    
    /**
     * (���ϋ敪�ꗗ)<BR>
     * ���ϋ敪�ꗗ<BR>
     * <BR>
     * 1�F�~�݁@@2�F�O��
     */
    public String[] settleDivList;
    
    /**
     * @@roseuid 44E336380148
     */
    public WEB3AdminBondExecInputResponse() 
    {
     
    }
    
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondExecInputResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
