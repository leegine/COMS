head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellSwtListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����抷�ꗗ�Ɖ�X�|���X�N���X(WEB3MutualSellSwtListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 ���� (���u) �V�K�쐬
                   2004/08/25 ���E (���u) ���r���[  
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M�����抷�ꗗ�Ɖ�X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3MutualSellSwtListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_swt_list";
    
    /**
     * SerialVersionUID<BR>
     */
     public final static long serialVersionUID = 200408111740L;
    
    /**
     * �\���y�[�W�ԍ�<BR>
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * ���y�[�W��
     */
    public String totalPages;
    
    /**
     * �����R�[�h��
     */
    public String totalRecords;
    
    /**
     * ���抷�����ꗗ
     */
    public WEB3MutualSellSwitchingProductGroup[ ] sellSwitchingProductGroups;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A889E1015F
     */
    public WEB3MutualSellSwtListResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualSellSwtListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
