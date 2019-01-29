head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M�����t�ꗗ�Ɖ�X�|���X�N���X(WEB3MutualBuyListResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 ���E (���u) �V�K�쐬
                   2004/08/23 ������ (���u) ���r���[ 
*/
package webbroker3.mf.message;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����M�����t�ꗗ�Ɖ�X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3MutualBuyListResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_list";
    
    /**
     * ���M�����J�e�S���[�ꗗ<BR>
     * (null�̏ꍇ�̓J�e�S���w�薳���Ƃ���)
     */
    public WEB3MutualProductCategoryUnit[] categoryList;
    
    /**
     * �\���y�[�W�ԍ�<BR>
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��@@���擪�y�[�W��"1"�Ƃ���
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
     * ���t�����ꗗ
     */
    public WEB3MutualBuyProductGroup[] buyProductGroups;
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3MutualBuyListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
    
    /**
     * (���M���t�ꗗ�Ɖ�X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A8795E023A
     */
    public WEB3MutualBuyListResponse()
    {
    }
}
@
