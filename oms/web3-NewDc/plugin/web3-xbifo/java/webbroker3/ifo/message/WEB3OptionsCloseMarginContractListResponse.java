head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginContractListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍό��ʈꗗ���X�|���X�N���X(WEB3OptionsCloseMarginContractListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ������ �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (�����w���I�v�V�����ԍό��ʈꗗ���X�|���X)<BR>
 * �����w���I�v�V�����ԍό��ʈꗗ���X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginContractListResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginContracList";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406101920L;
        
    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;
    
    /**
     * (������)<BR>
     */
    public String productName;
    
    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;
    
    /**
     * (���Ϗ���)<BR>
     * 0�F�����_���@@1�F�P���v���@@2�F�P�������@@3�F������<BR>
     */
    public String closingOrder;
    
    /**
     * (�ԍό��ʈꗗ���׍s)<BR>
     */
    public webbroker3.ifo.message.WEB3OptionsCloseMarginContractGroup[] closeMarginContractGroups;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3OptionsCloseMarginContractListResponse()
    {
        
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
         */
    protected WEB3OptionsCloseMarginContractListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
