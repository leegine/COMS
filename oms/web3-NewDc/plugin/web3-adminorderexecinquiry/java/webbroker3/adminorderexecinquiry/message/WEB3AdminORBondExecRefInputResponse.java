head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORBondExecRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���Ǘ��Ғ������Ɖ�����\�����X�|���X�N���X(WEB3AdminORBondExecRefInputResponse )
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/1 �����q(���u) �V�K�쐬   
Revesion History : 2007/07/9 ������(���u) �d�l�ύX���f��No.100
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;


/**
 *�i���Ǘ��Ғ������Ɖ�����\�����X�|���X�N���X)<BR>
 * ���Ǘ��Ғ������Ɖ�����\�����X�|���X�N���X<BR>
 *  
 * @@author �����q(���u)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefInputResponse extends  WEB3GenResponse
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_or_bond_exec_ref_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608091104L;
    
    /**
     * (������)<BR>
     * ������
     */
    public Date orderBizDate;
    
    /**
     * (������ʈꗗ)<BR>
     * ������ʈꗗ<BR>
     * <BR>
     * 401�F���t�@@�@@�@@�@@402�F���p�@@�@@�@@�@@404�F����
     */
    public String[] tradingTypeList;
    
    /**
     * (�������敪�ꗗ)<BR>
     * �������敪�ꗗ<BR>
     * <BR>
     * 0�F�����@@1�F���ρ@@2�F�����
     */
    public String[] executionStateList;
    
    /**
     * (���ϋ敪�ꗗ)<BR>
     * ���ϋ敪�ꗗ<BR>
     * 1�F�~�݁@@2�F�O��
     */
    public String[] settleDivList;
    
    /**
     * (�ʉ݃R�[�h�ꗗ)<BR>
     * �ʉ݃R�[�h�ꗗ
     */
    public String[] currencyCodeList;

    /**
     * (���^�C�v�ꗗ)<BR>
     * ���^�C�v�ꗗ<BR>
     * 4�F�O�����@@11�F�l�������� 12�F�Ѝ�
     */
    public String[] bondTypeList;

    public WEB3AdminORBondExecRefInputResponse() 
    {
     
    }

    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminORBondExecRefInputResponse(WEB3AdminORBondExecRefInputRequest l_request)
    {
        super(l_request);
}
}
@
