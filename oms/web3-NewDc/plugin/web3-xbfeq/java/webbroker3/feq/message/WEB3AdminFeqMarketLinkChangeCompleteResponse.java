head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqMarketLinkChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������s��A���敪�ύX�������X�|���X(WEB3AdminFeqMarketLinkChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҊO�������s��A���敪�ύX�������X�|���X)<BR>
 * �Ǘ��ҊO�������s��A���敪�ύX�������X�|���X�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */


public class WEB3AdminFeqMarketLinkChangeCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_market_link_change_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD005D
     */
    public WEB3AdminFeqMarketLinkChangeCompleteResponse() 
    {
     
    }
    
    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;
    
    /**
     * (�O�������s��A���󋵈ꗗ)<BR>
     * �O�������s��A���󋵂̔z��<BR>
     */
    public WEB3FeqMarketLinkStateUnit[] feqMarketLinkStateList;
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqMarketLinkChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
