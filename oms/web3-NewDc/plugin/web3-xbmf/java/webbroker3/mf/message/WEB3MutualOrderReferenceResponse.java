head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�������Ɖ�X�|���X�N���X(WEB3MutualOrderReferenceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 ���� (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M�������Ɖ�X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualOrderReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_order_reference";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408121215L;
    
    /**
     * �\���y�[�W�ԍ�<BR>
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * �������ꗗ<BR>
     */
    public WEB3MutualOrderGroup[] mutualOrderGroups;
    
    /**
     * (���M�����Ɖ�X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9A62202F9
     */
    public WEB3MutualOrderReferenceResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualOrderReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
