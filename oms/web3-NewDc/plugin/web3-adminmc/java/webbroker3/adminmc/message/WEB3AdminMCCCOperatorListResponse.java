head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.54.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name          : �Ǘ��҃��j���[����CC���ڰ��ꗗڽ��ݽ(WEB3AdminMCCCOperatorListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/18 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.adminmc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��҃��j���[����CC���ڰ��ꗗڽ��ݽ)<BR>
 * �Ǘ��҃��j���[����CC���ڰ��ꗗڽ��ݽ<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorListResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "adminMC_CCOperatorList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411171410L; 
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (CC�I�y���[�^�o�^���ꗗ)<BR>
     * CC�I�y���[�^�o�^���ꗗ<BR>
     */
    public WEB3AdminMCCCOperatorRegistUnit[] ccOperatorRegistUnits;
    
    /**
     * (�Ǘ��҃��j���[����CC���ڰ��ꗗڽ��ݽ)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse
     * @@roseuid 417E28F000B4
     */
    public WEB3AdminMCCCOperatorListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
