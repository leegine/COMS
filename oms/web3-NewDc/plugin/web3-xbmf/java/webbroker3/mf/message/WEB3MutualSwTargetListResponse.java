head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwTargetListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�抷������ꗗ���X�|���X�N���X(WEB3MutualSwTargetListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 ��O�� (���u) �V�K�쐬      
*/

package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * ���M�抷������ꗗ���X�|���X�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3MutualSwTargetListResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sw_target_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200510181049L; 
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualSwTargetListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 

    /**
     * (���M�����J�e�S���[�ꗗ)<BR>
     *  ���M�����J�e�S���[�ꗗ<BR>
     * <BR>
     *  (null�̏ꍇ�̓J�e�S���w�薳���Ƃ���)<BR>
     */
    public WEB3MutualProductCategoryUnit[] categoryList;    
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     *  �\���y�[�W�ԍ� <BR>
     *  ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)<BR>
     *  ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     *  �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (�抷������ꗗ)<BR>
     *  �抷������ꗗ<BR>
     */
    public WEB3MutualBuyProductGroup[] switchingProductGroups;
   
    /**
     * (���M�抷������ꗗ���X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8AABD0130
     */
    public WEB3MutualSwTargetListResponse() 
    {
     
    }
}
@
