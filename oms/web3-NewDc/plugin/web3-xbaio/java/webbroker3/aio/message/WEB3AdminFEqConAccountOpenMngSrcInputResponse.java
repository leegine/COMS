head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngSrcInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��������̓��X�|���X(WEB3AdminFEqConAccountOpenMngSrcInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������J�݊Ǘ��������̓��X�|���X)<BR>
 * �O�������J�݊Ǘ��������̓��X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AdminFEqConAccountOpenMngSrcInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_src_input";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (�\�����i���j)<BR>
     * �\�����i���j<BR>
     * (YYYYMMDD)
     */
    public String applyDateFrom;
    
    /**
     * (�\�����i���j)<BR>
     * �\�����i���j<BR>
     * (YYYYMMDD)
     */
    public String applyDateTo;
    
    /**
     * @@roseuid 423552E703B9
     */
    public WEB3AdminFEqConAccountOpenMngSrcInputResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminFEqConAccountOpenMngSrcInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
