head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecCalculateResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��n����v�Z���X�|���X(WEB3AdminBondExecCalculateResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (��n����v�Z���X�|���X)<BR>
 * ��n����v�Z���X�|���X�N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecCalculateResponse extends WEB3GenResponse
{
    
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_calculate";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (�����)<BR>
     * �����
     */
    public WEB3AdminBondOrderExecInfo execInfo;
    
    /**
     * (��n����v�Z���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 44BDDD660089
     */
    public WEB3AdminBondExecCalculateResponse() 
    {
     
    }
    
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondExecCalculateResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
