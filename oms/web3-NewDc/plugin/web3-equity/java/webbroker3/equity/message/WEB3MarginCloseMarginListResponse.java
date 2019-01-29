head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p������ψꗗ���X�|���X(WEB3MarginCloseMarginListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p������ψꗗ���X�|���X�j�B<br>
 * <br>
 * �M�p������ψꗗ���X�|���X�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginCloseMarginListResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginList";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101759L;       
    /**
     * (�����ꗗ)<BR>
     * �M�p��������R�[�h���̂̈ꗗ
     */
    public WEB3MarginProductCodeNameUnit[] productCodeNames;
    
    /**
     * (�s��R�[�h�̈ꗗ)
     */
    public String[] marketList;
    
    /**
     * (���ψꗗ)<BR>
     * �M�p������ψꗗ�s�̈ꗗ
     */
    public WEB3MarginCloseMarginGroup[] closeMarginGroups;
    
    /**
     * (���y�[�W��)
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * <BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;
    
    /**
     * (�����\�z)
     */
    public String swapLongTradingPower;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I���x��������\������s��R�[�h�̈ꗗ
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 414032D1027C
     */
    public WEB3MarginCloseMarginListResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginCloseMarginListResponse(WEB3MarginCloseMarginListRequest l_request)
    {
        super(l_request);
    }
}
@
