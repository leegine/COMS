head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҐV�K��芮�����X�|���X(WEB3AdminBondExecCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҐV�K��芮�����X�|���X)<BR>
 * �Ǘ��ҐV�K��芮�����X�|���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondExecCompleteResponse extends WEB3GenResponse 
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_exec_complete";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    
    /**
     * (�X�V����)<BR>
     * �X�V����
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (���ʔԍ�)<BR>
     * ���ʔԍ�
     */
    public String orderActionId;
    
    /**
     * @@roseuid 44E3363602BF
     */
    public WEB3AdminBondExecCompleteResponse() 
    {
     
    }
    
    /**
     *�@@�R���X�g���N�^�B<BR>
     *�@@�w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *�@@@@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminBondExecCompleteResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
        
    }
}
@
